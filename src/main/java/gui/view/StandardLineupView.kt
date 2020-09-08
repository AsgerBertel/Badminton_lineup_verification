package gui.view

import gui.style.LineupStyle
import io.JsonFileHandler
import javafx.collections.ObservableList
import model.*
import tornadofx.*

class StandardLineupView(private val players: List<Player> = JsonFileHandler().loadPlayerFile("""D:\Programming\Team-match-verify\src\main\resources\PlayerList.json"""), private val lineup:StandardLineupStructure = FakeData.getLineup()) : View() {
    override val root = vbox {
        primaryStage.minWidth = 450.0
        primaryStage.minHeight = 870.0
        primaryStage.isResizable = false
    }

    val obPlayers = playersToObservable()

    init {
        with(root) {
            addClass(LineupStyle.standardLineupBox)
            for (lcg in lineup.categoryGroups) {
                vbox {
                    if (lcg != lineup.categoryGroups.first()) {
                        separator {
                            this.minHeight = LineupStyle.betweenLineupGroup.toDouble()
                        }
                    }
                    vbox(LineupStyle.betweenPositionSize) {
                        for (pos in lcg.positions) {
                            borderpane {
                                var height: Dimension<Dimension.LinearUnits> = 0.px
                                when (pos) {
                                    is MixedPosition -> {
                                        addClass(LineupStyle.doublesBox)
                                        height = LineupStyle.doublesBoxHeight
                                    }
                                    is DoublesPosition -> {
                                        addClass(LineupStyle.doublesBox)
                                        height = LineupStyle.doublesBoxHeight
                                    }
                                    is SinglesPosition -> {
                                        addClass(LineupStyle.singlesBox)
                                        height = LineupStyle.singlesBoxHeight
                                    }
                                }

                                this.left = label {
                                    style {
                                        prefHeight = height
                                    }
                                    addClass(LineupStyle.specifierBox)
                                    text = pos.specifier
                                }
                                when (pos) {
                                    is MixedPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        text = pos.p1.name
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                            pos.p1 = choosePlayerFragment.getResult() ?: pos.p1
                                                            l.text = pos.p1.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.p1 = Player()
                                                            l.text = ""
                                                        }
                                                    }
                                                }
                                            }
                                            borderpane {
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        text = pos.p2.name
                                                    }
                                                }
                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                            pos.p2 = choosePlayerFragment.getResult() ?: pos.p2
                                                            l.text = pos.p2.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.p2 = Player()
                                                            l.text = ""
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    is DoublesPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        text = pos.p1.name
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                            pos.p1 = choosePlayerFragment.getResult() ?: pos.p1
                                                            l.text = pos.p1.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.p1 = Player()
                                                            l.text = ""
                                                        }
                                                    }
                                                }
                                            }
                                            borderpane {
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        text = pos.p2.name
                                                    }
                                                }
                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                            pos.p2 = choosePlayerFragment.getResult() ?: pos.p2
                                                            l.text = pos.p2.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.p2 = Player()
                                                            l.text = ""
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    is SinglesPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.singlesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        text = pos.player.name
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers).apply { openModal(block = true) }
                                                            pos.player = choosePlayerFragment.getResult() ?: pos.player
                                                            l.text = pos.player.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.player = Player()
                                                            l.text = ""
                                                        }
                                                    }
                                                }
                                            }
                                        }
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
