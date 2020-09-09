package function

import model.*
import model.lineupError.*
import kotlin.reflect.typeOf

class LineupVerification {
    companion object {
        fun verifyLineup(lineup: LineupStructure)  {
            val spots = lineup.serialize()

            clearErrors(spots)

            for (lcg in lineup.categoryGroups) {
                verifyPoints(lcg)
            }

            verifyTwoPositionsPerPlayer(lineup)

            setIllegality(spots)
        }

        private fun clearErrors(l:List<PositionSpot>) {
            l.forEach { it.errors.clear() }
        }

        private fun setIllegality(l:List<PositionSpot>) {
            l.forEach {
                if (it.errors.isEmpty())
                    it.verdict = IllegalityVerdict.LEGAL
                else if(it.errors.any { s -> s is LineupError })
                    it.verdict = IllegalityVerdict.ILLEGAL
                else if (it.errors.any { s -> s is LineupWarning })
                    it.verdict = IllegalityVerdict.WARNING
            }
        }

        private fun verifyPoints(lc: LineupCategoryGroup) {
            var lastPos = lc.positions[0]
            var lastPoints = lastPos.points

            for (i in 1 until lc.positions.size) {
                if (lastPoints == 0)
                    break

                val currentPos = lc.positions[i]
                val currentPoints = currentPos.points ?: break

                if (lastPoints + lc.allowedPointMargin < currentPoints) {
                    errorToPosition(lastPos, PointDifferenceError(lastPos, currentPos))
                }

                lastPos = currentPos
                lastPoints = currentPoints
            }
        }

        private fun verifyTwoPositionsPerPlayer(lineup: LineupStructure) {
            val luser = lineup.serialize()
            for (player in luser.map { it.player }) {
                val count = luser.count {it.player == player}
                if (count < 2) {
                    luser.filter { it.player == player }.forEach {
                        it.errors.add(TooFewOccurrencesWarning(player))
                    }
                } else if (count > 2) {
                    luser.filter { it.player == player }.forEach {
                        it.errors.add(TooManyOccurrencesError(player, count))
                    }
                }
            }
        }

        private fun errorToPosition(pos:Position, error:LineupError) {
            when(pos) {
                is SinglesPosition -> pos.spot.errors.add(error)
                is DoublesPosition -> { pos.spot1.errors.add(error); pos.spot2.errors.add(error) }
                is MixedPosition -> { pos.spot1.errors.add(error); pos.spot2.errors.add(error) }
            }
        }
    }
}