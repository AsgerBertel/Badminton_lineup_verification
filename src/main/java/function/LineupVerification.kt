package function

import model.*
import model.lineupError.*

class LineupVerification {
    companion object {
        fun VerifyLineup(lineup: LineupStructure): List<LineupError> {
            val errors = mutableListOf<LineupError>()

            for (lcg in lineup.categoryGroups) {
                verifyLineupCategoryGroup(lcg).forEach { errors.add(it) }
            }

            return errors
        }

        private fun verifyLineupCategoryGroup(lc: LineupCategoryGroup): List<LineupError> {
            val errors = mutableListOf<LineupError>()

            var lastPos = lc.positions[0]
            var lastPoints = lastPos.getPoints() ?: return emptyList()

            for (i in 1..lc.positions.size-1) {
                val currentPos = lc.positions[i]
                val currentPoints = currentPos.getPoints() ?: break

                if (currentPoints - lastPoints > lc.allowedPointMargin)
                    errors.add(PointDifferenceError(lastPos, currentPos))

                lastPos = currentPos
                lastPoints = currentPoints
            }

            return errors
        }
    }
}