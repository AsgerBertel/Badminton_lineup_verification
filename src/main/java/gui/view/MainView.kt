package gui.view

import gui.controller.MainController
import model.Player
import tornadofx.*

class MainView : View()  {
    override val root = vbox()

    init {
        with(root) {
            this.prefHeight = 100.0
            this.prefWidth = 300.0
            label("MainView")

            this@MainView.replaceWith(ScrapeRankListView())
        }
    }
}