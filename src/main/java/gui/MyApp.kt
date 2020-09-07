package gui

import tornadofx.*

class MyApp: App(StandardLineupView::class, LineupStyle::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}