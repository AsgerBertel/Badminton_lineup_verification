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
            root.setPrefSize(500.0, 500.0)
            columnResizePolicy = SmartResize.POLICY
            prefHeightProperty().bind(root.heightProperty())
            prefWidthProperty().bind(root.widthProperty())
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


        }

    }

    fun getResult(): Player? {
        return resultPlayer
    }
}