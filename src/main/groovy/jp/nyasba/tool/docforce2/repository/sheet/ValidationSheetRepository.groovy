package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.vallidation.SfdcValidation
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.PrintSetup
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

/**
 * Excelの「入力規則」シートを作成するためのRepository
 */
class ValidationSheetRepository {

    def createSheet(Workbook workbook, SfdcCustomObject customObject){

        CellStyle normal = CellStyleUtil.normal(workbook)
        CellStyle inactive = CellStyleUtil.inactive(workbook)

       Sheet validationSheet = workbook.getSheet("入力規則")

        List<SfdcValidation> validationList = customObject.入力規則リスト()
        validationList.eachWithIndex {
            v, i ->
                if(v.isActive()){
                    writeRow(validationSheet, i+2, v, normal)
                }
                else {
                    writeRow(validationSheet, i+2, v, inactive)
                }
        }

        印刷設定(validationSheet)
    }

    private writeRow(Sheet sheet, int rowNumber, SfdcValidation validation, CellStyle style){
        println validation.dump()
        sheet.createRow(rowNumber)
        CellUtil.setValue(sheet, rowNumber, 0, rowNumber-1, style)
        CellUtil.setValue(sheet, rowNumber, 1, validation.名前(), style)
        CellUtil.setValue(sheet, rowNumber, 2, validation.エラーメッセージ(), style)
        CellUtil.setValue(sheet, rowNumber, 3, validation.数式(), style)
        CellUtil.setValue(sheet, rowNumber, 4, validation.説明(), style)
    }
    
    def void 印刷設定(Sheet sheet){
        PrintSetup printSetup = sheet.getPrintSetup()
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE)
        printSetup.setLandscape(true) //横向き
        printSetup.setScale(60 as short)
    }
}
