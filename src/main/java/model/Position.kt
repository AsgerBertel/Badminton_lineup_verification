package model

import exception.WrongSexException

abstract class Position(val specifier:String) {
    abstract val sexReq:Sex?

    fun sexMismatchError(expected:Sex, actual:Sex):Nothing {
        throw WrongSexException("Wrong sex of player. Expected $expected but got $actual.")
    }
}

class SinglesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    var player:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }
}

class DoublesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    var player1:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }
    var player2:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }
}

class MixedPosition(_specifier:String) : Position(_specifier) {
    override val sexReq: Sex? = null

    var player1:Player? = null
        set(value) {
            if (value != null && value.sex != Sex.MALE)
                sexMismatchError(expected = Sex.MALE, actual = value.sex)
            field = value
        }
    var player2:Player? = null
        set(value) {
            if (value != null && value.sex != Sex.FEMALE)
                sexMismatchError(expected = Sex.FEMALE, actual = value.sex)
            field = value
        }
}


