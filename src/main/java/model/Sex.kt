package model

enum class Sex {
    MALE, FEMALE, UNKNOWN;

    fun knownEqual(other: Sex): Boolean {
        return other == UNKNOWN || this == UNKNOWN || other == this
    }

    override fun toString() =
            when (this) {
                FEMALE -> "w"
                MALE -> "m"
                UNKNOWN -> "N/A"
            }
}