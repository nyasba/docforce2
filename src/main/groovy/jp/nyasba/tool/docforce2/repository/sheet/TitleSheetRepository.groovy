package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Excelの「表紙」シートを作成するためのRepository
 */
class TitleSheetRepository {

    private String author;
    
    def TitleSheetRepository(String author){
        this.author = author
    }
    
    def createSheet(Workbook workbook, SfdcCustomObject customObject){

        Sheet titleSheet = workbook.getSheet("表紙")

        def title = CellStyleUtil.title(workbook)
        def titleDate = CellStyleUtil.titleDate(workbook)

        CellUtil.setValueWithCreateRecord(titleSheet, 8, 16, customObject.title(), title)

        String todayString = LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu/MM/dd"));
        CellUtil.setValueWithCreateRecord(titleSheet, 14, 31, author, titleDate)
        CellUtil.setValueWithCreateRecord(titleSheet, 15, 31, todayString, titleDate)

    }

}
