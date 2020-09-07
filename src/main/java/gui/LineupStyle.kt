package gui

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class LineupStyle : Stylesheet() {

    companion object {
        val singlesBox by cssclass()
        val singlesPlayerPicker by cssclass()
        val doublesBox by cssclass()
        val doublesPlayerPicker by cssclass()
        val specifierBox by cssclass()

        val doublesBoxHeight = 70.px
        val singlesBoxHeight = 40.px
        val pickerWidth = 200.px
    }

    init {
        singlesBox {
            backgroundColor += c("#cecece")
            borderColor += box(c("#a1a1a1"))
            minHeight = singlesBoxHeight
        }

        singlesPlayerPicker {
            prefWidth = pickerWidth
            minHeight = singlesBoxHeight
        }

        doublesBox {
            backgroundColor += c("#cecece")
            borderColor += box(c("#a1a1a1"))
            minHeight = doublesBoxHeight
        }

        doublesPlayerPicker {
            minHeight = doublesBoxHeight / 2
            prefWidth = pickerWidth
        }

        specifierBox {
            alignment = Pos.CENTER
            prefWidth = 50.px
            fontWeight = FontWeight.BOLD
            fontSize = 16.px
            textFill = Color.DARKRED
        }
    }
}