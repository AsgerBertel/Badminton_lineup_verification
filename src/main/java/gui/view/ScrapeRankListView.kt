package gui.view

import gui.controller.ScrapeRankListController
import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import kotlin.concurrent.thread
import kotlin.math.ceil

class ScrapeRankListView: View() {
    override val root = vbox()
    private val controller: ScrapeRankListController by inject()

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
                    while (bar.value < 1.0) {
                        sync()
                        Thread.sleep(25)
                    }
                    sync()
                }

                runAsync { controller.scrape() }
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