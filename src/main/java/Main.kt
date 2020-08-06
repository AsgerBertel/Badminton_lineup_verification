fun main(args: Array<String>) {
    val inputPath = if(args.size > 1) {
        args[1]
    } else {
        "TeamMatchVerify.xlsx"
    }

    val players = scrapeRankList()

    val eh = ExcelHandler(inputPath)
    eh.clearTable()
    eh.fillTable(players)
    eh.close()
}

