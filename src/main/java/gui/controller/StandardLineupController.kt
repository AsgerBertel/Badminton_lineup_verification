package gui.controller

import function.Verdict
import function.LineupVerification
import gui.style.LineupStyle
import gui.view.StandardLineupView
import model.Category
import tornadofx.*

class StandardLineupController : Controller() {
    private val view:StandardLineupView by inject()

    init {

    }

    fun verify() {
        LineupVerification.verifyLineup(view.lineup)
        setSpotsVerdict()
    }

    private fun setSpotsVerdict() {
        val positions = view.lineup.serialize()

        for ((i, h) in view.hboxList.withIndex()) {
            h.first.styleClass.clear()

            val style = when(h.second) {
                Category.SINGLES -> {
                    when(positions[i].verdict) {
                        Verdict.LEGAL -> LineupStyle.singlesPlayerNameLegal.name
                        Verdict.ILLEGAL -> LineupStyle.singlesPlayerNameIllegal.name
                        Verdict.EMPTY -> LineupStyle.singlesPlayerNameEmpty.name
                        Verdict.WARNING -> LineupStyle.singlesPlayerName.name
                        Verdict.UNKNOWN -> LineupStyle.singlesPlayerName.name
                    }
                }
                Category.MIXED, Category.DOUBLES -> {
                    when(positions[i].verdict) {
                        Verdict.LEGAL -> LineupStyle.doublesPlayerNameLegal.name
                        Verdict.ILLEGAL -> LineupStyle.doublesPlayerNameIllegal.name
                        Verdict.EMPTY -> LineupStyle.doublesPlayerNameEmpty.name
                        Verdict.WARNING -> LineupStyle.doublesPlayerName.name
                        Verdict.UNKNOWN -> LineupStyle.doublesPlayerName.name
                    }
                }
                else -> throw Exception()
            }

            h.first.styleClass.add(style)
        }
    }
}