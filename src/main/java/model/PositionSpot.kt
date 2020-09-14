package model

import function.Verdict
import javafx.beans.property.SimpleObjectProperty
import model.lineupError.LineupVerdict
import tornadofx.*

class PositionSpot(val sexReq: Sex?) {
    val playerProperty = SimpleObjectProperty(Player())
    var player: Player by playerProperty

    var verdict = Verdict.UNKNOWN

    val errors = mutableListOf<LineupVerdict>()
}