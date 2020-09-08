package gui.view

import javafx.beans.property.Property
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

        tableview(players) {
            setPrefSize(275.0, 500.0)
            readonlyColumn("Name", Player::name)
            readonlyColumn("ID", Player::badmintonId)
            readonlyColumn("Point", Player::levelPoints)
            readonlyColumn("Point", Player::singlesPoints)
            readonlyColumn("Point", Player::doublesPoints)
            readonlyColumn("Point", Player::mixedPoints)
            bindSelected(selectedPlayerProperty)
            this.onDoubleClick {
                resultPlayer = selectedPlayer
                close()
            }

        }.fixedCellSize

    }

    fun getResult(): Player? {
        return resultPlayer
    }
}