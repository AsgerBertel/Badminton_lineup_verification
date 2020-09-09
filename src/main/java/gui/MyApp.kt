package gui

import gui.style.LineupStyle
import gui.view.MainView
import gui.view.ScrapeRankListView
import gui.view.StandardLineupView
import gui.view.TestView
import javafx.stage.Stage
import model.StandardLineupStructure
import tornadofx.*

class MyApp(): App(TestView::class, LineupStyle::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.width = 300.0
        stage.height = 400.0
    }

    init {
        reloadStylesheetsOnFocus()
    }
}