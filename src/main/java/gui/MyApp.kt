package gui

import gui.style.LineupStyle
import gui.view.ScrapeRankListView
import tornadofx.*

class MyApp : App(ScrapeRankListView::class, LineupStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}