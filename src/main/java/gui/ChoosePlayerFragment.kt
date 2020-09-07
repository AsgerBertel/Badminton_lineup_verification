package gui

import Player
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import tornadofx.*

class ChoosePlayerFragment(sender:View, players: ObservableList<Player>) : Fragment() {
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

    fun getResult():Player? {
        return selectedPlayer
    }
}