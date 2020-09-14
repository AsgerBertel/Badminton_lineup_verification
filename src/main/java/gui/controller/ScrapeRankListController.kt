package gui.controller

import function.RankListScraper
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import io.JsonFileHandler
import javafx.application.Platform
import javafx.scene.control.Alert
import model.Player
import tornadofx.*

class ScrapeRankListController : Controller() {
    val scraper = RankListScraper()
    val view: ScrapeRankListView by inject()

    fun scrape() {
        var players: List<Player> = listOf()

        try { // Scrape players from rank list
            players = scraper.scrapeRankList()
        } catch (e: Exception) {
            println("Exception in scraper)")
            println("$e\n")
            println(e.stackTrace.joinToString(separator = "\n"))
        }

        if (players.isNotEmpty()) {
            try {
                println("Saving players in appdata...")
                JsonFileHandler.saveJsonPlayerFile(players)
                println("Player saved successfully!")
            } catch (e: Exception) {
                println("Unable to save players from local JSON")
                Platform.runLater {
                    alert(Alert.AlertType.ERROR, "Unknown error in saving downloaded players locally...\n " +
                            "Using previous locally saved players.")
                }
                throw e
            }
        } else {
            println("No players scraped...")
            Platform.runLater {
                alert(Alert.AlertType.INFORMATION, "Could not download players from badmintonplayer.dk" +
                        "Using locally saved players from previous download.")
            }
        }

        Platform.runLater {
            view.replaceWith(StandardLineupView::class)
        }
    }
}