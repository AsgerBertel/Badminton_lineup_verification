package model

import exception.WrongSexException
import function.IllegalityVerdict
import javafx.beans.property.SimpleObjectProperty
import model.lineupError.LineupError
import model.lineupError.LineupVerdict
import tornadofx.*

class PositionSpot(val sexReq:Sex?) {
    val playerProperty = SimpleObjectProperty(Player())
    var player: Player by playerProperty

    var verdict = IllegalityVerdict.UNKNOWN

    val errors = mutableListOf<LineupVerdict>()
}