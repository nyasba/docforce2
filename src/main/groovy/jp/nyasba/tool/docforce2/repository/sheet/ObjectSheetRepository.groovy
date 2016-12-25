package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.recordtype.SfdcRecordType
import jp.nyasba.tool.docforce2.domain.vallidation.SfdcValidation
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Excelの「表紙」シートを作成するためのRepository
 */
class ObjectSheetRepository {

    def createSheet(Workbook workbook, SfdcCustomObject customObject){

        Sheet objectSheet = workbook.getSheet("オブジェクト情報")

        def normal = CellStyleUtil.normal(workbook)
        def inactive = CellStyleUtil.inactive(workbook)

        objectSheet.createRow(3)
        CellUtil.setValue(objectSheet, 3, 1, customObject.表示ラベル(), normal)
        CellUtil.setValue(objectSheet, 3, 2, customObject.API参照名(), normal)
        CellUtil.setValue(objectSheet, 3, 3, customObject.説明(), normal)
        
        List<SfdcRecordType> recordTypeList = customObject.レコードタイプリスト()
        recordTypeList.eachWithIndex {
            v, i ->
                if(v.isActive()){
                    writeRow(objectSheet, i+8, v, normal)
                }
                else {
                    writeRow(objectSheet, i+8, v, inactive)
                }
        }
    
    }
    
    private writeRow(Sheet sheet, int rowNumber, SfdcRecordType recordType, CellStyle style){
        println recordType.dump()
        sheet.createRow(rowNumber)
        CellUtil.setValue(sheet, rowNumber, 1, recordType.ラベル(), style)
        CellUtil.setValue(sheet, rowNumber, 2, recordType.API参照名(),  style)
        CellUtil.setValue(sheet, rowNumber, 3, recordType.説明(), style)
    }

}
