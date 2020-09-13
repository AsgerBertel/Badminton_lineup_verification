package model

class LineupCategoryGroup(val positions: MutableList<Position>, val category: Category, val allowedPointMargin: Int) {
    fun serialize(): List<PositionSpot> {
        val res = mutableListOf<PositionSpot>()

        for (pos in positions) {
            when (pos) {
                is MixedPosition -> {
                    res.add(pos.spot1); res.add(pos.spot2)
                }
                is DoublesPosition -> {
                    res.add(pos.spot1); res.add(pos.spot2)
                }
                is SinglesPosition -> res.add(pos.spot)
            }
        }

        return res
    }
}