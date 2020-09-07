package gui.view

import gui.style.LineupStyle
import gui.controller.StandardLineupController
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Pos
import model.Player
import tornadofx.*

class StandardLineupView(private val playersInput: List<Player>) : View() {
    override val root = vbox()

    val controller: StandardLineupController by inject()
    val players = loadPlayers()

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
                            val view = ChoosePlayerFragment(players).apply { openModal(block = true) }
                            firstWSName.set(if(view.getResult() != null) view.getResult()!!.name else "Still empty")
                        }
                    }
                }
            }
        }
    }

    private fun loadPlayers(): ObservableList<Player> {
        val res = observableList<Player>()

        if(playersInput.isEmpty())
            controller.loadPlayersFromJSON().forEach { res.add(it) }
        else
            playersInput.forEach { res.add(it) }

        return res
    }
}
