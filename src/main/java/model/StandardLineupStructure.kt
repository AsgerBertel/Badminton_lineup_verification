package model

import model.lineupError.LineupError
import model.lineupError.PointDifferenceError

class StandardLineupStructure : LineupStructure() {
    var XD1 = MixedPosition("1. XD")
    var XD2 = MixedPosition("2. XD")

    var WS1 = SinglesPosition("1. WS", Sex.FEMALE)
    var WS2 = SinglesPosition("2. WS", Sex.FEMALE)

    var MS1 = SinglesPosition("1. MS", Sex.MALE)
    var MS2 = SinglesPosition("2. MS", Sex.MALE)
    var MS3 = SinglesPosition("3. MS", Sex.MALE)
    var MS4 = SinglesPosition("4. MS", Sex.MALE)

    var WD1 = DoublesPosition("1. WD", Sex.FEMALE)
    var WD2 = DoublesPosition("2. WD", Sex.FEMALE)

    var MD1 = DoublesPosition("1. MD", Sex.MALE)
    var MD2 = DoublesPosition("2. MD", Sex.MALE)
    var MD3 = DoublesPosition("3. MD", Sex.MALE)

    val mixedDoubles = listOf(XD1, XD2)
    val womensSingles = listOf(WS1, WS2)
    val mensSingles = listOf(MS1, MS2, MS3, MS4)
    val womensDoubles = listOf(WD1, WD2)
    val mensDoubles = listOf(MD1, MD2, MD3)

    override fun verify(): List<LineupError> {
        val errors = mutableListOf<LineupError>()

        for (player in mensSingles.withIndex()) {
            val i = player.index
            val p1 = player.value.player ?: break
            val p2 = mensSingles[i + 1].player ?: break

            if (p1.singlesPoints < p2.singlesPoints + 50)
                errors.add(PointDifferenceError("Too many points between ${p1.name} and ${p2.name}."))
        }

        return errors
    }
}