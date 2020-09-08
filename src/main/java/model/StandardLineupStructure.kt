package model

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


    val mixedDoubles = LineupCategoryGroup(mutableListOf(XD1, XD2), Category.MIXED, 100)
    val womensSingles = LineupCategoryGroup(mutableListOf(WS1, WS2), Category.SINGLES, 50)
    val mensSingles = LineupCategoryGroup(mutableListOf(MS1, MS2, MS3, MS4), Category.SINGLES, 50)
    val womensDoubles = LineupCategoryGroup(mutableListOf(WD1, WD2), Category.DOUBLES, 100)
    val mensDoubles = LineupCategoryGroup(mutableListOf(MD1, MD2, MD3), Category.DOUBLES, 100)

    override val categoryGroups = listOf(mixedDoubles, womensSingles, mensSingles, womensDoubles, mensDoubles)
}