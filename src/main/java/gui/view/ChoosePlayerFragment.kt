package gui.view

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.collections.transformation.SortedList
import javafx.scene.control.TableRow
import javafx.util.Duration
import model.Player
import tornadofx.*
import javax.swing.text.TableView


class ChoosePlayerFragment(players: ObservableList<Player>, predicate: (Player) -> Boolean) : Fragment() {
    override val root = vbox()

    private val selectedPlayerProperty = SimpleObjectProperty<Player>()
    private val selectedPlayer: Player by selectedPlayerProperty
    private var resultPlayer:Player? = null

    init {
        val filteredPlayers = players.filtered { predicate(it)}
        textfield {
            promptText = "Search for name or ID";
            isFocusTraversable = false;

            textProperty().addListener { observable, oldValue, newValue ->
                filteredPlayers.setPredicate { myObject ->
                    // If filter text is empty, display all players
                    if (newValue == null || newValue.isEmpty()) {
                        return@setPredicate true
                    }

                    // Compare name field in object with filter.
                    val lowerCaseFilter: String = newValue.toLowerCase()
                    if (java.lang.String.valueOf(myObject.name).toLowerCase().contains(lowerCaseFilter)) {
                        return@setPredicate true
                        // Filter matches name.
                    }
                    
                    // Compare badmintonID field in object with filter.
                    if(myObject.badmintonId.toString().contains(lowerCaseFilter)){
                        return@setPredicate true
                    }
                    false
                }
            }

        }
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
            setRowFactory {
                val row: TableRow<Player> = TableRow()
                row.setOnMouseClicked { event ->
                    if (event.getClickCount() === 2 && !row.isEmpty()) {
                        resultPlayer = selectedPlayer
                        runLater(Duration.millis(100.0)) {
                            close()
                        }
                    }
                }
                row
            }


        }

    }

    fun getResult(): Player? {
        return resultPlayer
    }
}