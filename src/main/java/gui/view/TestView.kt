package gui.view

import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.beans.binding.ObjectBinding
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableObjectValue
import javafx.util.StringConverter
import model.Player
import model.PositionSpot
import tornadofx.View
import tornadofx.label
import tornadofx.vbox
import tornadofx.*

class TestView : View() {
    override val root = vbox()

    val playerProperty = SimpleObjectProperty(Player("Hans"))
    val player by playerProperty

    init {
        with(root) {
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

class MyConverter: StringConverter<Player>() {
    override fun toString(p0: Player): String = p0.name

    override fun fromString(p0: String): Player = Player()
}