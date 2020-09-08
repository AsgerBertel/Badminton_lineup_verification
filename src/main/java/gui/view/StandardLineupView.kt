package gui.view

import gui.style.LineupStyle
import gui.viewModel.PlayerConverter
import gui.viewModel.StandardLineupStructureModel
import javafx.collections.ObservableList
import javafx.util.StringConverter
import model.*
import tornadofx.*

class StandardLineupView(private val players: List<Player> = FakeData.getPlayers(), private val lineup:StandardLineupStructure = FakeData.getLineup()) : View() {
    override val root = vbox()
    val obPlayers = playersToObservable()
    val model:StandardLineupStructureModel by inject()

    init {
        with(root) {
            addClass(LineupStyle.lineupBox)
            for (lcg in lineup.categoryGroups) {
                vbox {
                    for (pos in lcg.positions) {
                        hbox {
                            when (pos) {
                                is MixedPosition -> {
                                    addClass(LineupStyle.doublesBox)
                                }
                                is DoublesPosition -> {
                                    addClass(LineupStyle.doublesBox)
                                }
                                is SinglesPosition -> {
                                    addClass(LineupStyle.singlesBox)
                                }
                            }
                            label {
                                addClass(LineupStyle.specifierBox)
                                text = pos.specifier
                            }
                            when (pos) {
                                is MixedPosition -> {
                                    vbox {
                                        hbox {
                                            label {
                                                addClass(LineupStyle.playerName)
                                            }
                                            button("Change") {
                                                action {
                                                    val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                    pos.p1 = choosePlayerFragment.getResult() ?: Player()
                                                }
                                            }
                                        }
                                        hbox {
                                            val l = label {
                                                addClass(LineupStyle.playerName)
                                                text = "Mix dame her"
                                            }
                                            button("Change") {
                                                action {
                                                    val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                    
                                                    pos.p2 = choosePlayerFragment.getResult() ?: Player()
                                                    l.text = "Hey"
                                                }
                                            }
                                        }
                                    }
                                }
                                is DoublesPosition -> {
                                    vbox {
                                        label {
                                            addClass(LineupStyle.playerName)
                                            text = pos.p1.name
                                        }
                                        label {
                                            addClass(LineupStyle.playerName)
                                            text = pos.p2.name
                                        }
                                    }
                                }
                                is SinglesPosition -> {
                                    label {
                                        addClass(LineupStyle.playerName)
                                        text = pos.player.name
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun playersToObservable(): ObservableList<Player> {
        val res = observableList<Player>()

        res.addAll(players)

        return res
    }
}
