package gui

import javafx.util.StringConverter
import model.Category
import model.Player

class PlayerPointsConverter(val cat: Category) : StringConverter<Player>() {
    override fun toString(p0: Player): String = p0.getPoints(cat).toString()

    override fun fromString(p0: String): Player = Player()
}