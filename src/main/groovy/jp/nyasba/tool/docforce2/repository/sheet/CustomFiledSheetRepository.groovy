package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.field.SfdcField
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

/**
 * Excelの「カスタム項目」シートを作成するためのRepository
 */
class CustomFiledSheetRepository {
    
    def createSheet(Workbook workbook, SfdcCustomObject customObject) {
        
        CellStyle[] style = [
                CellStyleUtil.normal(workbook),
                CellStyleUtil.alignCenter(workbook)
        ]
        
        Sheet customFieldSheet = workbook.getSheet("カスタム項目")
        
        writeRow(customFieldSheet, 2, customObject.NameField(), style)
        
        List<SfdcField> customFieldList = customObject.Fieldリスト()
        customFieldList.eachWithIndex {
            f, i -> writeRow(customFieldSheet, i + 3, f, style)
        }
        
    }
    
    private writeRow(Sheet sheet, int rowNumber, SfdcField customField, CellStyle... style) {
        println customField.dump()
        int i = 0
        sheet.createRow(rowNumber)
        CellUtil.setValue(sheet, rowNumber, i++, rowNumber, style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.ラベル(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.API参照名(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.タイプ(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.length(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.デフォルト値or選択リスト値(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.数式(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.ヘルプテキスト(), style[0])
        CellUtil.setValue(sheet, rowNumber, i++, customField.必須(), style[1])
        CellUtil.setValue(sheet, rowNumber, i++, customField.外部ID(), style[1])
        CellUtil.setValue(sheet, rowNumber, i++, customField.説明(), style[0])
    }
    
}
