package gui.controller

import function.IllegalityVerdict
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
        setPoints()
        setSpotsVerdict()
    }

    private fun setPoints() {
        view.pointsList.forEach { it.first.text = it.second.getPoints().toString() }
    }

    private fun setSpotsVerdict() {
        val positions = view.lineup.serialize()

        for ((i, h) in view.hboxList.withIndex()) {
            h.first.styleClass.clear()

            val style = when(h.second) {
                Category.SINGLES -> {
                    when(positions[i].verdict) {
                        IllegalityVerdict.LEGAL -> LineupStyle.singlesPlayerNameLegal.name
                        IllegalityVerdict.ILLEGAL -> LineupStyle.singlesPlayerNameIllegal.name
                        IllegalityVerdict.WARNING -> LineupStyle.singlesPlayerName.name
                        IllegalityVerdict.UNKNOWN -> LineupStyle.singlesPlayerName.name
                    }
                }
                Category.MIXED, Category.DOUBLES -> {
                    when(positions[i].verdict) {
                        IllegalityVerdict.LEGAL -> LineupStyle.doublesPlayerNameLegal.name
                        IllegalityVerdict.ILLEGAL -> LineupStyle.doublesPlayerNameIllegal.name
                        IllegalityVerdict.WARNING -> LineupStyle.doublesPlayerName.name
                        IllegalityVerdict.UNKNOWN -> LineupStyle.doublesPlayerName.name
                    }
                }
                else -> throw Exception()
            }

            h.first.styleClass.add(style)
        }
    }
}