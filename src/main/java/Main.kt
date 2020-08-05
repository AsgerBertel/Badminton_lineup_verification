import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import java.lang.Exception

const val RankListUrl = """https://www.badmintonplayer.dk/DBF/Ranglister/#287,2020,,0,,,1492,0,,,,15,,,,0,,,,,,"""

fun main() {
    val players = mutableListOf<Player>()

    // Starting up the web client and waits for JavaScript to finish
    val webClient = WebClient()
    var page = webClient.getPage<HtmlPage>(RankListUrl)
    Thread.sleep(3000)

    // The level rank list must first scrape ID, name and birthday
    var pTable = scrapeRankListTable(page)
    for (elem in pTable)
        players.add(initPlayerFromLevelRankList(elem))

    // Iterates over the 7 different rank lists to get the points
    for (i in 1..7) {
        for (row in pTable) {
            val currentPlayer = players.firstOrNull { it.badmintonId == parseBadmintonId(row.childElements.toMutableList()[2].textContent) }
                    ?: throw Exception("A player was found on specifying rank list but not on the level rank list. Name: ${row.childElements.toMutableList()[3].childElements.first().textContent}")
            parseRankListRow(row, currentPlayer)
        }
        // Change to next rank list page and set the new player table
        page = page.getFirstByXPath<DomElement>("//*[@id='PanelResults']/div/div[${i}]/a").click()
        Thread.sleep(3000)
        pTable = scrapeRankListTable(page)
    }

    println(players[1])
}

fun scrapeRankListTable(page:HtmlPage):MutableList<DomElement> {
    val grid = page.getFirstByXPath<HtmlTableBody>("""//*[@id="PanelResults"]/table/tbody""")
    val pList = (grid.childElements).toMutableList()
    pList.removeAt(0)
    pList.removeAt(pList.count()-1)

    return pList
}

fun initPlayerFromLevelRankList(elem:DomElement):Player {
    val list = elem.childElements.toMutableList()
    val id = parseBadmintonId(list[2].textContent)
    val birthday = idToBirthday(id)
    val name = list[3].firstElementChild.textContent

    return Player(name, birthday, id)
}

fun parseRankListRow(elem:DomElement, p:Player) {
    val pChildren = elem.childElements.toMutableList()
    p.levelPoints = pChildren[5].textContent.toInt()
}

fun idToBirthday(id:Int):Int {
    val s:String = id.toString()
    return StringBuilder(s).removeRange(s.length-2, s.length).toString().toInt()
}

fun parseBadmintonId(s:String):Int {
    val list = s.toMutableList()

    list.removeIf { it == '‑' }

    return (list.joinToString("")).toInt()
}