package gui.viewModel

import javafx.util.StringConverter
import model.Player

class PlayerConverter : StringConverter<Player>() {
    override fun toString(p0: Player) = p0.name
    override fun fromString(p0: String) = Player()
}