package gui

import gui.style.LineupStyle
import gui.view.MainView
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import tornadofx.*

class MyApp: App(ScrapeRankListView::class, LineupStyle::class) {
    init {

        reloadStylesheetsOnFocus()
    }
}