package gui

import gui.ProgressBarView
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.layout.StackPane
import tornadofx.*
import tornadofx.EventBus.RunOn.*

class MainView : View()  {

    override val root = vbox()
    init {
        with(root) {
            this.prefHeight = 100.0
            this.prefWidth = 300.0
            hbox(alignment = Pos.CENTER) {
                button("Update Players") {
                    runAsync {
                        action {
                            this@MainView.replaceWith(ProgressBarView())
                        }
                    }
                }
            }
        }
    }
}