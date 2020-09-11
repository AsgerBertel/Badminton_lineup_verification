package gui.controller

import function.RankListScraper
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import io.JsonFileHandler
import javafx.application.Platform
import javafx.collections.ObservableList
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
            players = loadPlayersFromJSON()
            println("Unable to load any players")
        }
        else
            players = scrapedPlayers

        Platform.runLater {
            view.replaceWith(StandardLineupView(players)::class)
        }
    }

    private fun loadPlayersFromJSON() = JsonFileHandler().loadPlayerFile("""PlayerList.json""")
}