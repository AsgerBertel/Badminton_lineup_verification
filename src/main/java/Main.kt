import function.LineupVerification
import function.RankListScraper
import io.JsonFileHandler
import model.StandardLineupStructure

fun main(args: Array<String>) {
    val players = JsonFileHandler().loadPlayerFile("""C:\git\Team-match-verify\src\main\resources\PlayerList.json""")
    //val players = RankListScraper().scrapeRankList()

    val lineup = StandardLineupStructure()
    lineup.XD1.p1 = players.find { it.name == "Frederik Bjergen" }
    lineup.XD1.p2 = players.find { it.name == "Sofie Røjkjær Kjøbsted" }
    lineup.XD2.p1 = players.find { it.name == "Lasse Sonnesen" }
    lineup.XD2.p2 = players.find { it.name == "Jillie Knorborg" }

    lineup.WS1.player = players.find { it.name == "Nikoline Søndergaard Laugesen" }
    lineup.WS2.player = players.find { it.name == "Ida Janum Riis" }

    lineup.MS1.player = players.find { it.name == "Frederik Bjergen" }
    lineup.MS2.player = players.find { it.name == "Christoffer Geisler" }
    lineup.MS3.player = players.find { it.name == "Kristian Ødum Nielsen" }
    lineup.MS4.player = players.find { it.name == "Philip Valentin Bak" }

    lineup.WD1.p1 = players.find { it.name == "Sofie Røjkjær Kjøbsted" }
    lineup.WD1.p2 = players.find { it.name == "Nikoline Søndergaard Laugesen" }
    lineup.WD2.p1 = players.find { it.name == "Jillie Knorborg" }
    lineup.WD2.p2 = players.find { it.name == "Ida Janum Riis" }

    lineup.MD1.p1 = players.find { it.name == "Lasse Sonnesen" }
    lineup.MD1.p2 = players.find { it.name == "Frederik Bjergen" }
    lineup.MD2.p1 = players.find { it.name == "Victor Nexø" }
    lineup.MD2.p2 = players.find { it.name == "Christoffer Geisler" }
    lineup.MD3.p1 = players.find { it.name == "Kristian Ødum Nielsen" }
    lineup.MD3.p2 = players.find { it.name == "Philip Valentin Bak" }


    val errors = LineupVerification.VerifyLineup(lineup)

    println(errors)

    //launch<MyApp>(args)
}