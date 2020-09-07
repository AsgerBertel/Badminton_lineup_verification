package gui

import ExcelHandler
import RankListScraper
import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import java.awt.Label
import kotlin.concurrent.thread
import kotlin.math.ceil
import tornadofx.EventBus.RunOn.*

class ProgressBarView: Fragment() {
    override val root = vbox()
    private val controller: ProgressBarController by inject()

    private val bar = SimpleDoubleProperty()
    private val status = SimpleStringProperty()

    init {
        with(root) {
            vbox {
                hbox(alignment = Pos.CENTER) {
                    label {
                        textProperty().bind(status)
                    }
                }
                hbox(alignment = Pos.CENTER) {
                    progressbar {
                        minWidth = 300.0
                        minHeight = 50.0
                        progressProperty().bind(bar)
                    }
                }

                thread {
                    while (controller.scraper.progress < 1.0) {
                        sync()
                        Thread.sleep(25)
                    }
                    sync()
                }

                runAsync {
                    controller.scrapeRankList()
                    Platform.runLater {
                        this@ProgressBarView.replaceWith(ProgressBarFinishView())
                    }
                }
            }
        }
    }

    private fun sync() {
        Platform.runLater {
            bar.set(controller.scraper.progress)
            status.set(
                    ceil(controller.scraper.progress * 100).toInt().toString() + "%"
            )
        }
    }
}

class ProgressBarController:Controller() {
    val scraper = RankListScraper()

    fun scrapeRankList() = scraper.scrapeRankList()
}

class ProgressBarFinishView: Fragment() {
    override val root = vbox()

    init {
        with(root) {
            vbox {
                hbox(alignment = Pos.CENTER) {
                    label("Update finished")
                }
                hbox(alignment = Pos.CENTER) {
                    button("Return").action { this@ProgressBarFinishView.replaceWith(MainView()) }
                }
            }
        }
    }
}