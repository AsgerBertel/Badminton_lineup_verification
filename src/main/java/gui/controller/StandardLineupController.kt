package gui.controller

import gui.view.MainView
import gui.view.StandardLineupView
import io.JsonFileHandler
import javafx.collections.ObservableList
import model.Player
import model.Sex
import tornadofx.*

class StandardLineupController : Controller() {
    private val view:StandardLineupView by inject()

    init {

    }

    fun loadPlayersFromJSON(): ObservableList<Player> {
        val jfh = JsonFileHandler()

        val res = observableList<Player>()

        jfh.loadPlayerFile("""C:\git\Team-match-verify\src\main\resources\PlayerList.json""").forEach { res.add(it) }

        return res
    }
}