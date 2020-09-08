package gui.style

import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class LineupStyle : Stylesheet() {
    companion object {
        val singlesBox by cssclass()
        val singlesPlayerPicker by cssclass()
        val doublesBox by cssclass()
        val doublesPlayerPicker by cssclass()
        val mixedBox by cssclass()
        val specifierBox by cssclass()
        val lineupBox by cssclass()
        val playerName by cssclass()

        val doublesBoxHeight = 60.px
        val singlesBoxHeight = 40.px
        val playerLabelWidth = 200.px
    }

    init {
        singlesBox {
            backgroundColor += c("#eeeeee")
            borderColor += box(c("#a1a100"))
            minHeight = singlesBoxHeight
            alignment = Pos.CENTER
        }

        doublesBox {
            backgroundColor += c("#cecece")
            borderColor += box(c("#a100a1"))
            minHeight = doublesBoxHeight
            alignment = Pos.CENTER
            textAlignment = TextAlignment.CENTER
        }

        mixedBox {
            backgroundColor += c("#c0c0c0")
            borderColor += box(c("#00a1a1"))
            minHeight = doublesBoxHeight
            alignment = Pos.CENTER
        }

        specifierBox {
            alignment = Pos.CENTER
            prefWidth = 50.px
            fontWeight = FontWeight.BOLD
            fontSize = 16.px
            textFill = Color.DARKRED
            textAlignment = TextAlignment.CENTER
        }

        lineupBox {
            prefHeight = 600.px
            prefWidth = 400.px
        }

        playerName {
            prefWidth = playerLabelWidth
        }
    }
}