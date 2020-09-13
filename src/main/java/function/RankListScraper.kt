package function

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import io.JsonFileHandler
import model.Player
import model.Sex
import java.lang.Exception

const val RankListUrl = """https://www.badmintonplayer.dk/DBF/Ranglister/#287,2020,,0,,,1492,0,,,,15,,,,0,,,,,,"""
const val progressFrac = (1 - (0.05 + 0.1)) / 7 // = 0,1214

class RankListScraper {
    var progress = 0.0

    fun scrapeRankList(): List<Player> {
        println("Starting player update.")
        val players = mutableListOf<Player>()

        // Starting up the web client and waits for JavaScript to finish
        val webClient = WebClient()
        var page = webClient.getPage<HtmlPage>(RankListUrl)
        waitForJavaScript(webClient)
        progress = 0.05

        // Choose correct version
        try {
            page = page.getFirstByXPath<DomElement>("//*[@id=\"DropDownListVersions\"]").click()
            findAndClickCorrectVersion(page)
            clickSearch(page)
            waitForJavaScript(webClient)
        }
        catch(e: Exception) {
            println(e.stackTrace)
        }


        progress = 0.10

        // The level rank list must first scrape ID, name and birthday
        var pTable = scrapeRankListTable(page)

        for (elem in pTable) {
            players.add(initPlayerFromLevelRankList(elem))
        }

        // Scrape the second page
        try {
            page = page.getFirstByXPath<DomElement>("//*[@id=\"PanelResults\"]/table/tbody/tr[102]/td/a").click()
            waitForJavaScript(webClient)

            pTable = scrapeRankListTable(page)
            for (elem in pTable)
                players.add(initPlayerFromLevelRankList(elem))
        } catch (e: Exception) { }
        progress += progressFrac

        // Iterates over the 7 different rank lists to get the points
        for (i in 1..7) {
            // Change to next rank list page and set the new player table
            page = page.getFirstByXPath<DomElement>("//*[@id='PanelResults']/div/div[${i}]/a").click()
            waitForJavaScript(webClient)
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
            progress += progressFrac
        }

        progress = 1.0
        println("Scraping finished. Found ${players.size} players.")

        JsonFileHandler.saveJsonPlayerFile(players)
        return players
    }

    private fun clickSearch(page: HtmlPage){
        page.getFirstByXPath<DomElement>("//*[@id=\"LinkButtonSearch\"]").click<HtmlPage>()
    }

    private fun findAndClickCorrectVersion(page: HtmlPage) {
        val listOfVersions = page.getFirstByXPath<HtmlSelect>("//*[@id=\"DropDownListVersions\"]")
        val vList = (listOfVersions.childElements).toMutableList()

        for(element in  vList){
            if(element.textContent.contains("(senior)", true)) {
                element.click<(HtmlPage)>()
                println("Found version:" + element.textContent)
                return
            }
        }
    }

    private fun waitForJavaScript(webClient: WebClient) = webClient.waitForBackgroundJavaScriptStartingBefore(100)

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
        var name = list[3].firstElementChild.textContent.removeSuffix(" (udl.)")
        name = name.removeSuffix(" (EU)")
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