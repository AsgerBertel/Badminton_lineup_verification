import tornadofx.*

fun main(args: Array<String>) {
    val inputPath = if(args.size > 1) {
        args[1]
    } else {
        """C:\\git\\Team-match-verify\\src\\main\\resources\\TeamMatchVerify.xlsx"""
    }

    //launch<MyApp>(args)

    UpdatePlayers(inputPath)
}

fun UpdatePlayers(inputPath:String) {
    val players = RankListScraper().scrapeRankList()

    val eh = ExcelHandler(inputPath)
    println("Found File")
    eh.clearTable()
    eh.fillTable(players)
    eh.close()
}