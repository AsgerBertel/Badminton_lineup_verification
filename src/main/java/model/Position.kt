package model

import javafx.beans.property.SimpleIntegerProperty
import tornadofx.*

abstract class Position(val specifier: String) {
    abstract val sexReq: Sex?
    val pointsProperty = SimpleIntegerProperty()
    val points by pointsProperty

    abstract fun calcPoints(): Int
}

class SinglesPosition(specifier: String, override val sexReq: Sex?) : Position(specifier) {
    var spot = PositionSpot(sexReq)

    init {
        spot.playerProperty.onChange {
            pointsProperty.set(calcPoints())
        }
    }

    override fun calcPoints(): Int = spot.player.singlesPoints
}

class DoublesPosition(specifier: String, override val sexReq: Sex?) : Position(specifier) {
    var spot1 = PositionSpot(sexReq)
    var spot2 = PositionSpot(sexReq)

    init {
        spot1.playerProperty.onChange {
            pointsProperty.set(calcPoints())
        }
        spot2.playerProperty.onChange {
            pointsProperty.set(calcPoints())
        }
    }

    override fun calcPoints(): Int = spot1.player.doublesPoints + spot2.player.doublesPoints
}

open class MixedPosition(specifier: String) : Position(specifier) {
    override val sexReq: Sex? = null

    var spot1 = PositionSpot(Sex.MALE)
    var spot2 = PositionSpot(Sex.FEMALE)

    init {
        spot1.playerProperty.onChange {
            pointsProperty.set(calcPoints())
        }
        spot2.playerProperty.onChange {
            pointsProperty.set(calcPoints())
        }
    }

    override fun calcPoints(): Int = spot1.player.mixedPoints + spot2.player.mixedPoints
}

