import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream
import java.io.FileOutputStream

class ExcelHandler(val inputPath:String) {
    //Instantiate Excel workbook:
    private val xlWb = WorkbookFactory.create(FileInputStream(inputPath + ".xlsx"))

    //Instantiate Excel worksheet:
    private val sheet = xlWb.getSheet("RankList")

    fun clearTable() {
        for (i in 1 until sheet.physicalNumberOfRows) {
            sheet.removeRow(sheet.getRow(i))
        }
    }

    fun fillTable(players:List<Player>) {
        for ((i, p) in players.withIndex()) {
            val row = sheet.createRow(i)

            row.createCell(0).setCellValue(p.name)
            row.createCell(1).setCellValue(p.sex.toString())
            row.createCell(2).setCellValue(p.levelPoints.toDouble())
            row.createCell(3).setCellValue(p.singlesPoints.toDouble())
            row.createCell(4).setCellValue(p.doublesPoints.toDouble())
            row.createCell(5).setCellValue(p.mixedPoints.toDouble())
        }
    }

    fun close() {
        val outputStream = FileOutputStream(inputPath + "-new.xlsx")


        xlWb.write(outputStream)
        xlWb.close()
    }
}

