import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import java.lang.Exception

const val RankListUrl = """https://www.badmintonplayer.dk/DBF/Ranglister/#287,2020,,0,,,1492,0,,,,15,,,,0,,,,,,"""
class RankListScraper {
    var progress = 0.0

    fun scrapeRankList(): List<Player> {
        val players = mutableListOf<Player>()

        // Starting up the web client and waits for JavaScript to finish
        val webClient = WebClient()
        var page = webClient.getPage<HtmlPage>(RankListUrl)
        waitForJavaScript()

        // Choose correct version
        //TODO

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
        } catch (e: Exception) {
        }


        // Iterates over the 7 different rank lists to get the points
        for (i in 1..7) {
            // Change to next rank list page and set the new player table
            page = page.getFirstByXPath<DomElement>("//*[@id='PanelResults']/div/div[${i}]/a").click()
            waitForJavaScript()
            pTable = scrapeRankListTable(page)

            // Try to match the player with one from the level rank list and give the points
            for (row in pTable) {
                try {
                    val currentPlayer = players.firstOrNull { it.badmintonId == parseBadmintonId(row.childElements.toMutableList()[2].textContent) }
                            ?: throw Exception("A player was found on specifying rank list but not on the level rank list. Name: ${row.childElements.toMutableList()[3].childElements.first().textContent}")
                    parseRankListRow(row, currentPlayer, i)
                } catch (e: Exception) {
                    continue
                }
            }
        }

        return players
    }

    private fun waitForJavaScript() = Thread.sleep(500)

    private fun scrapeRankListTable(page: HtmlPage): MutableList<DomElement> {
        val grid = page.getFirstByXPath<HtmlTableBody>("""//*[@id="PanelResults"]/table/tbody""")
        val pList = (grid.childElements).toMutableList()
        pList.removeAt(0)
        pList.removeAt(pList.count() - 1)

        return pList
    }

    private fun initPlayerFromLevelRankList(elem: DomElement): Player {
        val list = elem.childElements.toMutableList()
        val id = parseBadmintonId(list[2].textContent)
        val birthday = idToBirthday(id)
        val name = list[3].firstElementChild.textContent.removeSuffix(" (udl.)")
        val points = list[5].textContent.toInt()

        val p = Player(name, birthday, id)
        p.levelPoints = points

        return p
    }

    private fun parseRankListRow(elem: DomElement, p: Player, category: Int) {
        val pChildren = elem.childElements.toMutableList()
        val points = pChildren[5].textContent.toInt()
        when (category) {
            1 -> throw Exception("Should not assign level points in this function.")
            2 -> {
                p.singlesPoints = points
                p.sex = Sex.MALE
            }
            3 -> {
                p.singlesPoints = points
                p.sex = Sex.FEMALE
            }
            4 -> {
                p.doublesPoints = points
                p.sex = Sex.MALE
            }
            5 -> {
                p.doublesPoints = points
                p.sex = Sex.FEMALE
            }
            6 -> {
                p.mixedPoints = points
                p.sex = Sex.MALE
            }
            7 -> {
                p.mixedPoints = points
                p.sex = Sex.FEMALE
            }
            else -> {
                throw Exception("Category had a mismatch.")
            }
        }
    }

    private fun idToBirthday(id: Int): Int {
        val s: String = id.toString()
        return StringBuilder(s).removeRange(s.length - 2, s.length).toString().toInt()
    }

    private fun parseBadmintonId(s: String): Int {
        val list = s.toMutableList()

        list.removeIf { it == '‑' }

        return (list.joinToString("")).toInt()
    }
}