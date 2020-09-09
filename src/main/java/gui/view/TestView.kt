package gui.view

import javafx.beans.InvalidationListener
import javafx.beans.Observable
import javafx.beans.binding.ObjectBinding
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableObjectValue
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
                text = player.name
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

class PlayerO(spot:PositionSpot) : Observable {
    override fun addListener(p0: InvalidationListener?) {

    }

    override fun removeListener(p0: InvalidationListener?) {
        TODO("Not yet implemented")
    }
}