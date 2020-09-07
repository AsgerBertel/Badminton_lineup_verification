package model

enum class Sex {
    MALE, FEMALE, UNKNOWN;

    override fun toString() =
        when(this) {
            FEMALE -> "w"
            MALE -> "m"
            UNKNOWN -> "N/A"
        }
}