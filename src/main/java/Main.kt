fun main(args:Array<String>) {
    val inputPath = """C:\Users\krist\.tmv\TeamMatchVerify"""

    //val players = scrapeRankList()
    val players:List<Player> = listOf(Player("Hans", 20202, 2020201), Player("Jens", 123456, 12345601))
    players[0].levelPoints = 1000
    players[1].levelPoints = 1000

    val eh = ExcelHandler(inputPath)
    eh.clearTable()
    eh.fillTable(players)
    eh.close()

    println(players[1].toCSVLine())
}

