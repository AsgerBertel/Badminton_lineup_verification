package gui.view

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.collections.transformation.SortedList
import javafx.util.Duration
import model.Player
import tornadofx.*


class ChoosePlayerFragment(players: ObservableList<Player>, predicate: (Player) -> Boolean) : Fragment() {
    override val root = vbox()

    private val selectedPlayerProperty = SimpleObjectProperty<Player>()
    private val selectedPlayer: Player by selectedPlayerProperty
    private var resultPlayer:Player? = null

    init {
        // Filter the players
        val filteredPlayers = players.filtered { predicate(it) }
        val sortableData: SortedList<Player> = SortedList<Player>(filteredPlayers)

        tableview(sortableData) {
            sortableData.comparatorProperty().bind(comparatorProperty())
            root.setPrefSize(500.0, 500.0)
            prefHeightProperty().bind(root.heightProperty())
            prefWidthProperty().bind(root.widthProperty())
            readonlyColumn("Name", Player::name)
            readonlyColumn("ID", Player::badmintonId)
            readonlyColumn("Point", Player::levelPoints)
            readonlyColumn("Point", Player::singlesPoints)
            readonlyColumn("Point", Player::doublesPoints)
            readonlyColumn("Point", Player::mixedPoints)
            bindSelected(selectedPlayerProperty)
            this.onSelectionChange {
                resultPlayer = selectedPlayer

                runLater(Duration.millis(100.0)) {
                    close()
                }
            }


        }

    }

    fun getResult(): Player? {
        return resultPlayer
    }
}