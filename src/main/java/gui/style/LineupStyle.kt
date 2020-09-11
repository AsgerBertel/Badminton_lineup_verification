package gui.style

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class LineupStyle : Stylesheet() {
    companion object {
        val standardLineupBox by cssclass()
        val singlesBox by cssclass()
        val singlesPlayerName by cssclass()
        val singlesPlayerNameIllegal by cssclass()
        val singlesPlayerNameLegal by cssclass()
        val singlesPlayerNameEmpty by cssclass()
        val doublesBox by cssclass()
        val doublesPlayerName by cssclass()
        val doublesPlayerNameIllegal by cssclass()
        val doublesPlayerNameLegal by cssclass()
        val doublesPlayerNameEmpty by cssclass()
        val specifierBox by cssclass()
        val pointsBox by cssclass()
        val playerName by cssclass()
        val buttonsBox by cssclass()

        val nameFontSize = 12.px
        val doublesBoxHeight = 60.px
        val singlesBoxHeight = 40.px
        val playerLabelWidth = 220.px

        val standardPadding = 5.px
        val standardRadius = 15.px

        const val betweenPositionSize = 5
        const val betweenPlayersSize = 1
        const val betweenLineupGroup = 15

        private const val standardGrey = 230
        private const val activeSpotGrey = 245
        private const val emptyGrey = 200
        val standardBackgroundColor = c(standardGrey, standardGrey, standardGrey)
        val playerNameBackgroundColor = c(activeSpotGrey, activeSpotGrey, activeSpotGrey)
        val playerNameEmptyBackgroundColor = c(emptyGrey, emptyGrey, emptyGrey)

        val legalPlayerColor = c(220,255,220)
        val illegalPlayerColor = c(255,220,220)
    }

    init {
        standardLineupBox {
            backgroundColor += standardBackgroundColor
        }

        singlesBox {
            minHeight = singlesBoxHeight
            maxHeight = singlesBoxHeight
            alignment = Pos.CENTER
        }

        doublesBox {
            minHeight = doublesBoxHeight + 2 * standardPadding
            maxHeight = doublesBoxHeight
            alignment = Pos.CENTER
            textAlignment = TextAlignment.CENTER
        }

        specifierBox {
            padding = box(standardPadding)
            fontWeight = FontWeight.BOLD
            fontSize = 16.px
            textFill = Color.DARKRED
            alignment = Pos.CENTER
            minWidth = 60.px
        }

        pointsBox {
            padding = box(standardPadding)
            fontWeight = FontWeight.BOLD
            fontSize = 14.px
            alignment = Pos.CENTER
            minWidth = 40.px
        }

        doublesPlayerName {
            padding = box(standardPadding)
            backgroundRadius += box(standardRadius)
            backgroundColor += playerNameBackgroundColor
            fontSize = nameFontSize
            minHeight = doublesBoxHeight / 2
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        doublesPlayerNameIllegal {
            padding = box(standardPadding)
            backgroundRadius += box(standardRadius)
            backgroundColor += illegalPlayerColor
            fontSize = nameFontSize
            minHeight = doublesBoxHeight / 2
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        doublesPlayerNameLegal {
            padding = box(standardPadding)
            backgroundRadius += box(standardRadius)
            backgroundColor += legalPlayerColor
            fontSize = nameFontSize
            minHeight = doublesBoxHeight / 2
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        doublesPlayerNameEmpty {
            padding = box(standardPadding)
            backgroundRadius += box(standardRadius)
            backgroundColor += playerNameEmptyBackgroundColor
            fontSize = nameFontSize
            minHeight = doublesBoxHeight / 2
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        singlesPlayerName {
            backgroundRadius += box(standardRadius)
            padding = box(standardPadding)
            backgroundColor += playerNameBackgroundColor
            fontSize = nameFontSize
            minHeight = singlesBoxHeight
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        singlesPlayerNameIllegal {
            backgroundRadius += box(standardRadius)
            padding = box(standardPadding)
            backgroundColor += illegalPlayerColor
            fontSize = nameFontSize
            minHeight = singlesBoxHeight
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        singlesPlayerNameLegal {
            backgroundRadius += box(standardRadius)
            padding = box(standardPadding)
            backgroundColor += legalPlayerColor
            fontSize = nameFontSize
            minHeight = singlesBoxHeight
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        singlesPlayerNameEmpty {
            backgroundRadius += box(standardRadius)
            padding = box(standardPadding)
            backgroundColor += playerNameEmptyBackgroundColor
            fontSize = nameFontSize
            minHeight = singlesBoxHeight
            minWidth = playerLabelWidth
            alignment = Pos.CENTER_LEFT
        }

        buttonsBox {
            alignment = Pos.CENTER
            padding = box(standardPadding)
        }
    }
}