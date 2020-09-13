package gui.controller

import function.RankListScraper
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import io.JsonFileHandler
import javafx.application.Platform
import model.Player
import tornadofx.*


class ScrapeRankListController : Controller() {
    private val view: ScrapeRankListView by inject()
    val scraper = RankListScraper()

    fun scrape() {
        var scrapedPlayers: List<Player> = listOf()
        val players: List<Player>

        try {
            scrapedPlayers = scraper.scrapeRankList()
        } catch (e: Exception) {
            println("Exception in scraper")
        }

        if (scrapedPlayers.isEmpty()) {
            println("Loading players from local json...")
            try {
                players = JsonFileHandler.loadPlayerFile() ?: listOf()
                println("Players loaded from local JSON")
            } catch (e: Exception) {
                println("Unable to load players from local JSON")
                throw e
            }

        } else {
            println("Players has been loaded from scraper")
            players = scrapedPlayers
        }

        Platform.runLater {
            view.replaceWith(StandardLineupView(players)::class)
        }
    }
}