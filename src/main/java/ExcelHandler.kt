import org.apache.poi.ss.SpreadsheetVersion
import org.apache.poi.ss.util.AreaReference
import org.apache.poi.ss.util.CellReference
import org.apache.poi.xssf.usermodel.XSSFTable
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class ExcelHandler(val inputPath:String) {
    //Instantiate Excel workbook:
    private val xlWb = XSSFWorkbook(FileInputStream(inputPath))

    private var tableHeight = 0

    //Instantiate Excel worksheet:
    private var sheet = xlWb.getSheet("RankList")

    fun clearTable() {
        for (i in 1 until sheet.lastRowNum) {
            if (sheet.getRow(i) != null) // safety for empty rows
                sheet.removeRow(sheet.getRow(i))
        }
    }

    fun fillTable(players:List<Player>) {
        var i = 1
        tableHeight = players.count()

        for (p in players) {
            val row = sheet.createRow(i++)

            row.createCell(0).setCellValue(p.name)
            row.createCell(1).setCellValue(p.sex.toString())
            row.createCell(2).setCellValue(p.levelPoints.toDouble())
            row.createCell(3).setCellValue(p.singlesPoints.toDouble())
            row.createCell(4).setCellValue(p.doublesPoints.toDouble())
            row.createCell(5).setCellValue(p.mixedPoints.toDouble())
        }
        updateTableSize()
    }
    fun updateTableSize(){
        print(xlWb.getTable("RankList").dataRowCount)
        sheet.getTables().get(0).setDataRowCount(tableHeight)
    }

    fun close() {
        val outputStream = FileOutputStream(inputPath)
        File(inputPath).delete()
        xlWb.write(outputStream)
        xlWb.close()
    }
}

