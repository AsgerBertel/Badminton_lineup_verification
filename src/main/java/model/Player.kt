package model

class Player(val name: String = "", val birthday: Int = 0, val badmintonId: Int = 0) {
    var sex = Sex.UNKNOWN
    var levelPoints = 0
    var singlesPoints = 0
    var doublesPoints = 0
    var mixedPoints = 0

    override fun toString() =
            "Name: $name, sex: $sex" +
            "\nID: $badmintonId, birthday: $birthday"+
            "\nPoints: $levelPoints, $singlesPoints, $doublesPoints, $mixedPoints"

    fun getPoints(type:Category):Int {
        return when(type) {
            Category.LEVEL -> levelPoints
            Category.SINGLES -> singlesPoints
            Category.DOUBLES -> doublesPoints
            Category.MIXED -> mixedPoints
        }
    }
}