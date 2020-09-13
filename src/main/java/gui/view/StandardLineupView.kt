package gui.view

import com.sun.javafx.binding.BidirectionalBinding.bind
import gui.PlayerPointsConverter
import gui.PlayerStringConverter
import gui.controller.StandardLineupController
import gui.style.LineupStyle
import io.JsonFileHandler
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import model.*
import tornadofx.*


class StandardLineupView(val players: List<Player> = JsonFileHandler.loadPlayerFile(), val lineup: StandardLineupStructure = StandardLineupStructure()) : View() {
    override val root = vbox()

    val controller: StandardLineupController by inject()
    val obPlayers = playersToObservable()
    val hboxList = mutableListOf<Pair<HBox, Category>>()
    val pointsList = mutableListOf<Pair<Label, Position>>()

    override fun onDock() {
        super.onDock()

        title = "Standard Lineup"

        primaryStage.sizeToScene()
        primaryStage.minHeight = primaryStage.height
        primaryStage.minWidth = primaryStage.width

        controller.verify()
    }

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

                                this.left = hbox {
                                    label {
                                        style {
                                            prefHeight = height
                                        }
                                        addClass(LineupStyle.specifierBox)
                                        text = pos.specifier
                                    }
                                    separator(orientation = Orientation.VERTICAL)
                                    label {
                                        style {
                                            prefHeight = height
                                        }
                                        addClass(LineupStyle.pointsBox)
                                        textProperty().bind(pos.pointsProperty.asString())
                                        pointsList.add(Pair(this, pos))
                                    }
                                }
                                when (pos) {
                                    is MixedPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot1.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.MIXED)
                                                    onMouseClicked = EventHandler { changePlayer(pos.spot1, Category.MIXED) }
                                                    tooltip {
                                                        bind(textProperty(), pos.spot1.playerProperty, PlayerPointsConverter(Category.MIXED))
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Remove") {
                                                        action {
                                                            resetSpot(pos.spot1)
                                                        }
                                                    }
                                                }
                                            }
                                            borderpane {
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot2.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.MIXED)
                                                    onMouseClicked = EventHandler { changePlayer(pos.spot2, Category.MIXED) }
                                                    tooltip {
                                                        bind(textProperty(), pos.spot2.playerProperty, PlayerPointsConverter(Category.MIXED))
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Remove") {
                                                        action {
                                                            resetSpot(pos.spot2)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    is DoublesPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot1.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.DOUBLES)
                                                    onMouseClicked = EventHandler { changePlayer(pos.spot1, Category.DOUBLES) }
                                                    tooltip {
                                                        bind(textProperty(), pos.spot1.playerProperty, PlayerPointsConverter(Category.DOUBLES))
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Remove") {
                                                        action {
                                                            resetSpot(pos.spot1)
                                                        }
                                                    }
                                                }
                                            }
                                            borderpane {
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot2.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.DOUBLES)
                                                    onMouseClicked = EventHandler { changePlayer(pos.spot2, Category.DOUBLES) }
                                                    tooltip {
                                                        bind(textProperty(), pos.spot2.playerProperty, PlayerPointsConverter(Category.DOUBLES))
                                                    }
                                                }
                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Remove") {
                                                        action {
                                                            resetSpot(pos.spot2)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    is SinglesPosition -> {
                                        center = vbox(LineupStyle.betweenPlayersSize) {
                                            borderpane {
                                                center = hbox {
                                                    addClass(LineupStyle.singlesPlayerName)
                                                    label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.SINGLES)
                                                    onMouseClicked = EventHandler { changePlayer(pos.spot, Category.SINGLES) }
                                                    tooltip {
                                                        bind(textProperty(), pos.spot.playerProperty, PlayerPointsConverter(Category.SINGLES))
                                                    }
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Remove") {
                                                        action {
                                                            resetSpot(pos.spot)
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

    private fun resetSpot(spot: PositionSpot) {
        spot.player = Player()
        controller.verify()
    }

    private fun changePlayer(spot: PositionSpot, sortAfter: Category? = null) {
        val choosePlayerFragment = ChoosePlayerFragment(obPlayers, sortAfter) { spot.sexReq == null || it.sex.knownEqual(spot.sexReq) }.apply { openModal(block = true) }

        if (choosePlayerFragment.getResult() != null) {
            spot.player = choosePlayerFragment.getResult()
                    ?: spot.player

            controller.verify()
        }
    }

    private fun bindPlayerToColorProperty(h: HBox, cat: Category) {
        hboxList.add(Pair(h, cat))
    }

    private fun playersToObservable(): ObservableList<Player> {
        val res = observableList<Player>()

        res.addAll(players)

        return res
    }
}
