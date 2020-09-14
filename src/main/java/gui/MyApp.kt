package gui

import gui.style.LineupStyle
import gui.view.ScrapeRankListView
import javafx.scene.image.Image
import javafx.stage.Stage
import tornadofx.*

class MyApp : App(ScrapeRankListView::class, LineupStyle::class) {
    override fun start(stage: Stage) {
        super.start(stage)

        stage.icons += Image("logo.png")
    }

    init {
        reloadStylesheetsOnFocus()
    }
}