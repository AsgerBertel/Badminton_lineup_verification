package gui

import javafx.util.StringConverter
import model.Player

class PlayerStringConverter : StringConverter<Player>() {
    override fun toString(p0: Player): String = p0.name

    override fun fromString(p0: String): Player = Player()
}