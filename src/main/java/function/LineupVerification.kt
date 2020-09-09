package function

import model.*
import model.lineupError.*

class LineupVerification {
    companion object {
        fun verifyLineup(lineup: LineupStructure)  {
            val spots = lineup.serialize()

            clearErrors(spots)

            for (lcg in lineup.categoryGroups) {
                verifyPoints(lcg)
            }

            setIllegality(spots)
        }

        private fun clearErrors(l:List<PositionSpot>) {
            l.forEach { it.errors.clear() }
        }

        private fun setIllegality(l:List<PositionSpot>) {
            l.forEach {
                if (it.errors.isEmpty())
                    it.verdict = IllegalityVerdict.LEGAL
                else if(it.errors.any { s -> s is PointDifferenceError })
                    it.verdict = IllegalityVerdict.ILLEGAL
                else if (it.errors.any { s -> s is TooManyOccurrencesError })
                    it.verdict = IllegalityVerdict.WARNING
            }
        }

        private fun verifyPoints(lc: LineupCategoryGroup) {
            var lastPos = lc.positions[0]
            var lastPoints = lastPos.getPoints()

            for (i in 1 until lc.positions.size) {
                if (lastPoints == 0)
                    break

                val currentPos = lc.positions[i]
                val currentPoints = currentPos.getPoints() ?: break

                if (lastPoints + lc.allowedPointMargin < currentPoints) {
                    errorToPosition(lastPos, PointDifferenceError(lastPos, currentPos))
                }

                lastPos = currentPos
                lastPoints = currentPoints
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