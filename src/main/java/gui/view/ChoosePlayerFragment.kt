package gui.view

import model.Player
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import tornadofx.*

class ChoosePlayerFragment(players: ObservableList<Player>) : Fragment() {
    override val root = vbox()

    private val selectedPlayerProperty = SimpleObjectProperty<Player>()
    private val selectedPlayer: Player by selectedPlayerProperty
    private var resultPlayer:Player? = null

    init {
        listview(players) {
            bindSelected(selectedPlayerProperty)
            this.onDoubleClick {
                resultPlayer = selectedPlayer
                close()
            }
        }
    }

    fun getResult(): Player? {
        return resultPlayer
    }
}