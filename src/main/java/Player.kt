class Player(val name: String, val birthday: Int, val badmintonId: Int) {
    var sex = Sex.UNKNOWN
    var levelPoints = 0
    var singlesPoints = 0
    var doublesPoints = 0
    var mixedPoints = 0

    override fun toString() =
            "Name: $name, Sex: $sex" +
            "\nID: $badmintonId, birthday: $birthday"+
            "\nPoints: $levelPoints, $singlesPoints, $doublesPoints, $mixedPoints"

    fun toCSVLine() = "$name;" +
            when (sex) {
                Sex.MALE -> "m"
                Sex.FEMALE -> "w"
                else -> "N/A"
            } +
            ";$levelPoints" +
            ";$singlesPoints" +
            ";$doublesPoints" +
            ";$mixedPoints"
}