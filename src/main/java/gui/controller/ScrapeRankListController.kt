package gui.controller

import function.RankListScraper
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import io.JsonFileHandler
import javafx.application.Platform
import model.Player
import tornadofx.*


class ScrapeRankListController: Controller() {
    val scraper = RankListScraper()
    val view: ScrapeRankListView by inject()

    fun scrape() {
        val scrapedPlayers:List<Player>? = try {
            scraper.scrapeRankList()
        }
        catch (e:Exception) {
            null
        }
        val players: List<Player>

        if(scrapedPlayers == null) {
            println("Unable to scrape any players")
            try {
                players = loadPlayersFromJSON()
                println("Players loaded from local JSON")
            }
            catch (e:Exception) {
                println("Unable to load players from local JSON")
                throw e
            }

        }
        else {
            println("Players has been loaded from scraper")
            players = scrapedPlayers
        }
        Platform.runLater {
            view.replaceWith(StandardLineupView(players)::class)
        }
    }

    private fun loadPlayersFromJSON() = JsonFileHandler.loadPlayerFile("src/main/resources/PlayerList.json")
}