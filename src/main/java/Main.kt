import io.JsonFileHandler
import model.StandardLineupStructure

fun main(args: Array<String>) {
    val players = JsonFileHandler().loadPlayerFile("""C:\git\Team-match-verify\src\main\resources\PlayerList.json""")
    val lineup = StandardLineupStructure()
    lineup.MS1.player = players.find { it.name == "Frederik Bjergen" }
    lineup.MS2.player = players.find { it.name == "Magnus Johannesen" }

    val errors = lineup.verify()

    println(errors)

    //launch<MyApp>(args)
}