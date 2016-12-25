package jp.nyasba.tool.docforce2.repository

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Sheet

/**
 * セルに関するUtil
 */
class CellUtil {
    
    def static setValue(Sheet sheet, int rowNumber, int cellNumber, def value, CellStyle style) {
        Cell cell = sheet.getRow(rowNumber).createCell(cellNumber)
        cell.setCellValue(value)
        cell.setCellStyle(style)
    }
    
    def static setValueWithCreateRecord(Sheet sheet, int rowNumber, int cellNumber, def value, CellStyle style) {
        sheet.createRow(rowNumber)
        setValue(sheet, rowNumber, cellNumber, value, style)
    }
    
}
