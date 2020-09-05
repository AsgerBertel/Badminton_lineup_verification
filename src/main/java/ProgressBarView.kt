import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import tornadofx.*
import java.text.NumberFormat
import kotlin.concurrent.thread
import kotlin.math.ceil

class ProgressBarView: View() {
    override val root = vbox()
    private val excelPath = """src/main/resources/TeamMatchVerify.xlsx"""
    private val scraper = RankListScraper()
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
                runAsync {
                    updatePlayers(excelPath)
                }
                thread {
                    while (scraper.progress < 1.0) {
                        sync()
                        Thread.sleep(25)
                    }
                    sync()
                }
            }
        }
    }

    private fun sync() {
        Platform.runLater {
            bar.set(scraper.progress)
            status.set(
                ceil(scraper.progress * 100).toInt().toString() + "%"
            )
        }
    }

    private fun updatePlayers(inputPath:String) {
        val players = scraper.scrapeRankList()

        val eh = ExcelHandler(inputPath)
        println("Found File")
        eh.clearTable()
        eh.fillTable(players)
        eh.close()
    }
}