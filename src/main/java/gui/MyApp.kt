package gui

import gui.style.LineupStyle
import gui.view.ScrapeRankListView
import tornadofx.*

public class MyApp: App(ScrapeRankListView::class, LineupStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}