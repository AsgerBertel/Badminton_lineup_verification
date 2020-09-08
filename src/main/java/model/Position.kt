package model

import exception.WrongSexException
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

abstract class Position(val specifier:String) {
    abstract val sexReq:Sex?

    fun sexMismatchError(expected:Sex, actual:Sex):Nothing {
        throw WrongSexException("Wrong sex of player. Expected $expected but got $actual.")
    }

    abstract fun getPoints():Int?
}

class SinglesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    val category = Category.SINGLES

    var player:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }
    val playerProperty = SimpleObjectProperty(player)

    override fun getPoints() = player.getPoints(category)
}

class DoublesPosition(_specifier:String, override val sexReq: Sex?) : Position(_specifier) {
    val category = Category.DOUBLES

    var p1:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }

    var p2:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && sexReq != null && value.sex != sexReq)
                sexMismatchError(expected = sexReq, actual = value.sex)
            field = value
        }

    override fun getPoints() = p1.getPoints(category) + p2.getPoints(category)
}

class MixedPosition(_specifier:String) : Position(_specifier) {
    override val sexReq: Sex? = null
    val category = Category.MIXED

    var p1:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && value.sex != Sex.MALE)
                sexMismatchError(expected = Sex.MALE, actual = value.sex)
            field = value
        }

    var p2:Player = Player()
        set(value) {
            if (value.badmintonId != 0 && value.sex != Sex.FEMALE)
                sexMismatchError(expected = Sex.FEMALE, actual = value.sex)
            field = value
        }

    override fun getPoints() = p1.getPoints(category) + p2.getPoints(category)
}


