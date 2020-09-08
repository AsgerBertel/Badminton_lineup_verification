package gui.controller

import gui.style.LineupStyle
import gui.view.StandardLineupView
import model.Category
import tornadofx.*

class StandardLineupController : Controller() {
    private val view:StandardLineupView by inject()

    init {

    }

    fun verify() {
        highlightErrorPlayer(0)
        highlightErrorPlayer(1)
        highlightFulfillingPlayer(2)
        highlightFulfillingPlayer(3)
        highlightErrorPlayer(4)
        highlightFulfillingPlayer(5)
    }

    fun highlightErrorPlayer(playerIndex:Int) {
        val h = view.hboxList[playerIndex]
        h.first.styleClass.clear()

        when(h.second) {
            Category.SINGLES -> h.first.styleClass.add(LineupStyle.singlesPlayerNameIllegal.name)
            Category.MIXED, Category.DOUBLES -> h.first.styleClass.add(LineupStyle.doublesPlayerNameIllegal.name)
            else -> throw Exception()
        }
    }

    fun highlightFulfillingPlayer(playerIndex:Int) {
        val h = view.hboxList[playerIndex]
        h.first.styleClass.clear()

        when(h.second) {
            Category.SINGLES -> h.first.styleClass.add(LineupStyle.singlesPlayerNameLegal.name)
            Category.MIXED, Category.DOUBLES -> h.first.styleClass.add(LineupStyle.doublesPlayerNameLegal.name)
            else -> throw Exception()
        }
    }
}