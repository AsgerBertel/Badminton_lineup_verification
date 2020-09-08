import function.LineupVerification
import function.RankListScraper
import gui.MyApp
import io.JsonFileHandler
import model.Player
import model.StandardLineupStructure
import tornadofx.launch

fun main(args: Array<String>) {
    //val players = JsonFileHandler().loadPlayerFile("""C:\git\Team-match-verify\src\main\resources\PlayerList.json""")
    //val players = RankListScraper().scrapeRankList()
    launch<MyApp>(args)
}

fun getPlayer(name:String, players:List<Player>) = players.find { it.name == name } ?: Player()