import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import java.lang.Exception

const val RankListUrl = """https://www.badmintonplayer.dk/DBF/Ranglister/#287,2020,,0,,,1492,0,,,,15,,,,0,,,,,,"""
const val sleepTime = 2000

fun scrapeRankList() {
    val players = mutableListOf<Player>()

    // Starting up the web client and waits for JavaScript to finish
    val webClient = WebClient()
    var page = webClient.getPage<HtmlPage>(RankListUrl)
    waitForJavaScript()

    // The level rank list must first scrape ID, name and birthday
    var pTable = scrapeRankListTable(page)
    for (elem in pTable)
        players.add(initPlayerFromLevelRankList(elem))

    // Scrape the second page
    try {
        page = page.getFirstByXPath<DomElement>("//*[@id=\"PanelResults\"]/table/tbody/tr[102]/td/a").click()
        waitForJavaScript()

        pTable = scrapeRankListTable(page)
        for (elem in pTable)
            players.add(initPlayerFromLevelRankList(elem))
    } catch (e: Exception) {}


    // Iterates over the 7 different rank lists to get the points
    for (i in 1..7) {
        for (row in pTable) {
            try {
                val currentPlayer = players.firstOrNull { it.badmintonId == parseBadmintonId(row.childElements.toMutableList()[2].textContent) }
                        ?: throw Exception("A player was found on specifying rank list but not on the level rank list. Name: ${row.childElements.toMutableList()[3].childElements.first().textContent}")
                parseRankListRow(row, currentPlayer)
            }
            catch (e:Exception) {
                continue
            }

        }
        // Change to next rank list page and set the new player table
        page = page.getFirstByXPath<DomElement>("//*[@id='PanelResults']/div/div[${i}]/a").click()
        waitForJavaScript()
        pTable = scrapeRankListTable(page)
    }

    println(players[1])
}

private fun waitForJavaScript() = Thread.sleep(2000)

private fun scrapeRankListTable(page:HtmlPage):MutableList<DomElement> {
    val grid = page.getFirstByXPath<HtmlTableBody>("""//*[@id="PanelResults"]/table/tbody""")
    val pList = (grid.childElements).toMutableList()
    pList.removeAt(0)
    pList.removeAt(pList.count()-1)

    return pList
}

private fun initPlayerFromLevelRankList(elem:DomElement):Player {
    val list = elem.childElements.toMutableList()
    val id = parseBadmintonId(list[2].textContent)
    val birthday = idToBirthday(id)
    val name = list[3].firstElementChild.textContent

    return Player(name, birthday, id)
}

private fun parseRankListRow(elem:DomElement, p:Player) {
    val pChildren = elem.childElements.toMutableList()
    p.levelPoints = pChildren[5].textContent.toInt()
}

private fun idToBirthday(id:Int):Int {
    val s:String = id.toString()
    return StringBuilder(s).removeRange(s.length-2, s.length).toString().toInt()
}

private fun parseBadmintonId(s:String):Int {
    val list = s.toMutableList()

    list.removeIf { it == '‑' }

    return (list.joinToString("")).toInt()
}