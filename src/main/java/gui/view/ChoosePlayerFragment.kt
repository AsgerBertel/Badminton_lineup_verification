package gui.view

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.collections.transformation.FilteredList
import javafx.collections.transformation.SortedList
import javafx.scene.control.TableColumn
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.util.Duration
import model.Category
import model.Player
import tornadofx.*


class ChoosePlayerFragment(players: ObservableList<Player>, val cat: Category? = null, predicate: (Player) -> Boolean) : Fragment() {
    override val root = vbox()

    private val selectedPlayerProperty = SimpleObjectProperty<Player>()
    private val selectedPlayer: Player by selectedPlayerProperty
    private var resultPlayer: Player? = null

    private var table: TableView<Player>? = null
    private var nameColumn: TableColumn<Player, String>? = null
    private var idColumn: TableColumn<Player, Int>? = null
    private var levelColumn: TableColumn<Player, Int>? = null
    private var singlesColumn: TableColumn<Player, Int>? = null
    private var doublesColumn: TableColumn<Player, Int>? = null
    private var mixedColumn: TableColumn<Player, Int>? = null

    override fun onDock() {
        super.onDock()

        title = "Choose a player"

        nameColumn!!.remainingWidth()

        levelColumn!!.sortType = TableColumn.SortType.DESCENDING
        singlesColumn!!.sortType = TableColumn.SortType.DESCENDING
        doublesColumn!!.sortType = TableColumn.SortType.DESCENDING
        mixedColumn!!.sortType = TableColumn.SortType.DESCENDING
        when (cat) {
            Category.LEVEL -> table!!.sortOrder.add(levelColumn)
            Category.SINGLES -> table!!.sortOrder.addAll(singlesColumn, levelColumn)
            Category.DOUBLES -> table!!.sortOrder.addAll(doublesColumn, levelColumn)
            Category.MIXED -> table!!.sortOrder.addAll(mixedColumn, levelColumn)
        }

        for (c in table!!.columns.filter { it != nameColumn })
            c.contentWidth(padding = 5.0, useAsMin = true, useAsMax = true)

        nameColumn!!.minWidth = 120.0
    }


    init {
        val filteredPlayers = FilteredList(players)
        filteredPlayers.setPredicate(predicate)


        textfield {
            promptText = "Search for name or ID"
            isFocusTraversable = false

            textProperty().addListener { _, _, newValue ->
                filteredPlayers.setPredicate { myObject ->
                    // Compare name field in object with filter.
                    val lowerCaseFilter: String = newValue.toLowerCase()

                    // Must adhere to the original predicate from parameter
                    if (predicate(myObject)) {
                        // Filter matches name.
                        if (java.lang.String.valueOf(myObject.name).toLowerCase().contains(lowerCaseFilter))
                            return@setPredicate true

                        // Compare badmintonID field in object with filter.
                        if (myObject.badmintonId.toString().contains(lowerCaseFilter))
                            return@setPredicate true
                    }
                    return@setPredicate false
                }
            }
        }
        val sortableData: SortedList<Player> = SortedList<Player>(filteredPlayers)

        table = tableview(sortableData) {
            sortableData.comparatorProperty().bind(comparatorProperty())

            prefHeightProperty().bind(root.heightProperty())
            prefWidthProperty().bind(root.widthProperty())
            root.setPrefSize(650.0, 500.0)
            columnResizePolicy = SmartResize.POLICY

            nameColumn = readonlyColumn("Name", Player::name)
            idColumn = readonlyColumn("ID", Player::badmintonId)
            levelColumn = readonlyColumn("Level Points", Player::levelPoints)
            singlesColumn = readonlyColumn("Single Points", Player::singlesPoints)
            doublesColumn = readonlyColumn("Double Points", Player::doublesPoints)
            mixedColumn = readonlyColumn("Mixed Points", Player::mixedPoints)

            bindSelected(selectedPlayerProperty)

            setRowFactory {
                val row: TableRow<Player> = TableRow()
                row.setOnMouseClicked { event ->
                    if (event.clickCount == 1 && !row.isEmpty) {
                        resultPlayer = selectedPlayer
                        runLater(Duration.millis(60.0)) {
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