package gui.controller

import function.RankListScraper
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import javafx.application.Platform
import model.Player
import tornadofx.*


class ScrapeRankListController: Controller() {
    var players = listOf<Player>()
    val scraper = RankListScraper()

    val view: ScrapeRankListView by inject()

    fun scrape() {
        //TODO -> add scraping back
        //players = scraper.scrapeRankList()

        Platform.runLater {
            view.replaceWith(StandardLineupView(players))
        }
    }
}