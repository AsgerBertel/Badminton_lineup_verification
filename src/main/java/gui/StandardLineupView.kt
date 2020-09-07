package gui

import Player
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

class StandardLineupView() : View() {
    override val root = vbox()

    val controller: StandardLineupController by inject()
    val firstWSName = SimpleStringProperty("Empty")

    init {
        with(root) {
            vbox {
                hbox {// 1. XD
                    addClass(LineupStyle.doublesBox)
                    label("1. XD") {
                        addClass(LineupStyle.specifierBox)
                    }
                    vbox {
                        textfield {
                            addClass(LineupStyle.doublesPlayerPicker)
                        }
                        textfield {
                            addClass(LineupStyle.doublesPlayerPicker)
                        }
                    }
                }.alignment = Pos.CENTER_LEFT
                hbox { // 1. WS
                    addClass(LineupStyle.singlesBox)
                    label("1. WS") {
                        addClass(LineupStyle.specifierBox)
                    }
                    label {
                        textProperty().bind(firstWSName)
                    }
                    button("Choose") {
                        action {
                            val view = ChoosePlayerFragment(this@StandardLineupView, controller.players).apply { openModal(block = true) }
                            firstWSName.set(if(view.getResult() != null) view.getResult()!!.name else "Still empty")
                        }
                    }
                }
            }
        }
    }
}

class StandardLineupController : Controller() {
    val players = observableList<Player>()

    init {
        players.add(Player("Jens", 202020, 20202001))
        players.add(Player("Frederik", 101010, 10101001))
    }
}
