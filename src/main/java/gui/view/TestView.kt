package gui.view

import javafx.beans.property.SimpleObjectProperty
import javafx.event.EventHandler
import javafx.util.StringConverter
import model.Player
import tornadofx.*

class TestView : View() {
    override val root = vbox()

    val playerProperty = SimpleObjectProperty(Player("Hans"))
    val player by playerProperty

    override fun onDock() {
        super.onDock()
        println("TestView Dock")
    }

    override fun onBeforeShow() {
        super.onBeforeShow()
        println("TestView Beforeshow")
    }

    override fun onRefresh() {
        super.onRefresh()
        println("TestView refresh")
    }

    override fun onUndock() {
        super.onUndock()
        println("TestView undock")
    }

    init {
        with(root) {
            button("Change view") {
                action {
                    replaceWith(StandardLineupView::class)
                }
            }

            hbox {
                val l = label("Ready") { }

                this.onMouseClicked = EventHandler {
                    l.text = "GO"
                }
            }

            label("Navn skal stå her hvis man er nice :)")
            val l = label("Navn") {
                bind(playerProperty, converter = MyConverter())
            }

            button("Change Player") {
                action {
                    playerProperty.set(Player("Egon"))
                }
            }

            button("Update") {
                action {
                    l.text = player.name
                }
            }
        }
    }
}

class MyConverter : StringConverter<Player>() {
    override fun toString(p0: Player): String = p0.name

    override fun fromString(p0: String): Player = Player()
}