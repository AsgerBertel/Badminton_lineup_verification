package model

import exception.WrongSexException
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

abstract class Position(val specifier:String) {
    abstract val sexReq:Sex?

    abstract fun getPoints():Int
}

class SinglesPosition(specifier:String, override val sexReq: Sex?) : Position(specifier) {
    private val category = Category.SINGLES

    var spot = PositionSpot(sexReq)

    override fun getPoints() = spot.player.getPoints(category)
}

class DoublesPosition(specifier:String, override val sexReq: Sex?) : Position(specifier) {
    private val category = Category.DOUBLES

    var spot1 = PositionSpot(sexReq)
    var spot2 = PositionSpot(sexReq)

    override fun getPoints() = spot1.player.getPoints(category) + spot2.player.getPoints(category)
}

class MixedPosition(specifier:String) : Position(specifier) {
    override val sexReq: Sex? = null
    private val category = Category.MIXED

    var spot1 = PositionSpot(Sex.MALE)
    var spot2 = PositionSpot(Sex.FEMALE)

    override fun getPoints() = spot1.player.getPoints(category) + spot2.player.getPoints(category)
}


