package gui.view

import javafx.geometry.Pos
import tornadofx.*

class ScrapeRankListFinishView: View() {
    override val root = vbox()

    init {
        with(root) {
            vbox {
                hbox(alignment = Pos.CENTER) {
                    label("Update finished")
                }
                hbox(alignment = Pos.CENTER) {
                    button("Return").action { replaceWith(MainView()) }
                }
            }
        }
    }
}