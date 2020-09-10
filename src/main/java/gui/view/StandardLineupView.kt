package gui.view

import gui.PlayerStringConverter
import gui.IntConverter
import gui.controller.StandardLineupController
import gui.style.LineupStyle
import io.JsonFileHandler
import javafx.collections.ObservableList
import javafx.geometry.Orientation
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import model.*
import tornadofx.*


class StandardLineupView(val players: List<Player> = JsonFileHandler().loadPlayerFile("src/main/resources/PlayerList.json"), val lineup: StandardLineupStructure = FakeData.getLineup()) : View() {
    override val root = vbox()

    val controller:StandardLineupController by inject()
    val obPlayers = playersToObservable()
    val hboxList = mutableListOf<Pair<HBox, Category>>()
    val pointsList = mutableListOf<Pair<Label, Position>>()

    override fun onDock() {
        primaryStage.minWidth = 0.0
        primaryStage.minHeight = 0.0
        primaryStage.sizeToScene()
        primaryStage.minHeight = primaryStage.height
        primaryStage.minWidth = primaryStage.width
    }

    override fun onBeforeShow() {
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
                                                var l = label()
                                                center = hbox {
                                                    addClass(LineupStyle.doublesPlayerName)
                                                    l = label {
                                                        addClass(LineupStyle.playerName)
                                                        bind(pos.spot1.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.MIXED)
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers) { pos.spot1.sexReq == null || it.sex.knownEqual(pos.spot1.sexReq!!)  }.apply { openModal(block = true) }
                                                            if(choosePlayerFragment.getResult() != null) {
                                                                pos.spot1.player = choosePlayerFragment.getResult()
                                                                        ?: pos.spot1.player
                                                                controller.verify()
                                                            }
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.spot1.player = Player()
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
                                                        bind(pos.spot2.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.MIXED)
                                                }
                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers) { pos.spot2.sexReq == null || it.sex.knownEqual(pos.spot2.sexReq!!) }.apply { openModal(block = true) }
                                                            if(choosePlayerFragment.getResult() != null) {
                                                                pos.spot2.player = choosePlayerFragment.getResult()
                                                                        ?: pos.spot2.player

                                                                controller.verify()
                                                            }
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.spot2.player = Player()
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
                                                        bind(pos.spot1.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.DOUBLES)
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers) {pos.spot1.sexReq == null || it.sex.knownEqual(pos.spot1.sexReq!!)}.apply { openModal(block = true) }
                                                            if(choosePlayerFragment.getResult() != null) {
                                                                pos.spot1.player = choosePlayerFragment.getResult()
                                                                        ?: pos.spot1.player

                                                                controller.verify()
                                                            }
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.spot1.player = Player()
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
                                                        bind(pos.spot2.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.DOUBLES)
                                                }
                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers) {pos.spot2.sexReq == null || it.sex == pos.spot2.sexReq}.apply { openModal(block = true) }
                                                            if(choosePlayerFragment.getResult() != null) {
                                                                pos.spot2.player = choosePlayerFragment.getResult()
                                                                        ?: pos.spot2.player

                                                                controller.verify()
                                                            }
                                                            l.text = pos.spot2.player.name
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.spot2.player = Player()
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
                                                        bind(pos.spot.playerProperty, converter = PlayerStringConverter())
                                                    }
                                                    bindPlayerToColorProperty(this, Category.SINGLES)
                                                }

                                                right = hbox(5) {
                                                    addClass(LineupStyle.buttonsBox)
                                                    button("Change") {
                                                        action {
                                                            val choosePlayerFragment = ChoosePlayerFragment(obPlayers) {pos.spot.sexReq == null || it.sex.knownEqual(pos.spot.sexReq!!)}.apply { openModal(block = true) }
                                                            if(choosePlayerFragment.getResult() != null) {
                                                                pos.spot.player = choosePlayerFragment.getResult()
                                                                        ?: pos.spot.player

                                                                controller.verify()
                                                            }
                                                        }
                                                    }
                                                    button("Remove") {
                                                        action {
                                                            pos.spot.player = Player()
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

    private fun bindPlayerToColorProperty(h: HBox, cat:Category) {
        hboxList.add(Pair(h, cat))
    }

    private fun playersToObservable(): ObservableList<Player> {
        val res = observableList<Player>()

        res.addAll(players)

        return res
    }

    fun getDefaultPlayers(): List<Player> = JsonFileHandler().loadPlayerFile(resources["PlayerList.json"])
}
