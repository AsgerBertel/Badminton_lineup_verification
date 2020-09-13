package model

abstract class LineupStructure {
    abstract val categoryGroups: List<LineupCategoryGroup>

    fun serialize():List<PositionSpot> {
        val l = mutableListOf<PositionSpot>()

        for (group in categoryGroups) {
            for (pos in group.positions) {
                when(pos) {
                    is SinglesPosition -> l.add(pos.spot)
                    is DoublesPosition -> { l.add(pos.spot1); l.add(pos.spot2) }
                    is MixedPosition -> { l.add(pos.spot1); l.add(pos.spot2) }
                }
            }
        }

        return l
    }
}