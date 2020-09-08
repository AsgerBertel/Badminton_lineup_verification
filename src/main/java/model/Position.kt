package model

import exception.WrongSexException

abstract class Position(val specifier:String) {
    abstract val sexReq:Sex?

    fun sexMismatchError(expected:Sex, actual:Sex):Nothing {
        throw WrongSexException("Wrong sex of player. Expected $expected but got $actual.")
    }

    abstract fun getPoints():Int?
}

class SinglesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    val category = Category.SINGLES

    var player:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }

    override fun getPoints(): Int? {
        if (player != null)
            return player!!.getPoints(category)
        return null
    }
}

class DoublesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    val category = Category.DOUBLES

    var p1:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }
    var p2:Player? = null
        set(value) {
            if (value != null && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }

    override fun getPoints(): Int? {
        if (p1 != null && p2 != null) {
            return p1!!.getPoints(category) + p2!!.getPoints(category)
        }
        return null
    }
}

class MixedPosition(_specifier:String) : Position(_specifier) {
    override val sexReq: Sex? = null
    val category = Category.MIXED

    var p1:Player? = null
        set(value) {
            if (value != null && value.sex != Sex.MALE)
                sexMismatchError(expected = Sex.MALE, actual = value.sex)
            field = value
        }
    var p2:Player? = null
        set(value) {
            if (value != null && value.sex != Sex.FEMALE)
                sexMismatchError(expected = Sex.FEMALE, actual = value.sex)
            field = value
        }
    override fun getPoints(): Int? {
        if (p1 != null && p2 != null) {
            return p1!!.getPoints(category) + p2!!.getPoints(category)
        }
        return null
    }
}


