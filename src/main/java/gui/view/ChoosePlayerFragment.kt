package gui.view

import model.Player
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import tornadofx.*

class ChoosePlayerFragment(players: ObservableList<Player>) : Fragment() {
    override val root = vbox()

    private val selectedPlayerProperty = SimpleObjectProperty<Player>()
    private val selectedPlayer: Player by selectedPlayerProperty

    init {
        listview(players) {
            bindSelected(selectedPlayerProperty)
            this.onDoubleClick {
                close()
            }
        }
    }

    fun getResult(): Player? {
        return selectedPlayer
    }
}