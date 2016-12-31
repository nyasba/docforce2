package jp.nyasba.tool.docforce2.repository

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Sheet

/**
 * セルに関するUtil
 */
class CellUtil {
    
    /**
     * セルを作成し、値をセットする
     *
     * @param sheet シート
     * @param rowNumber 行番号
     * @param cellNumber 列番号
     * @param value 値
     * @param style スタイル
     * @return なし
     */
    def static setValue(Sheet sheet, int rowNumber, int cellNumber, def value, CellStyle style) {
        Cell cell = sheet.getRow(rowNumber).createCell(cellNumber)
        cell.setCellValue(value)
        cell.setCellStyle(style)
    }
    
    /**
     * 行およびセルを作成し、値をセットする
     *
     * @param sheet シート
     * @param rowNumber 行番号
     * @param cellNumber 列番号
     * @param value 値
     * @param style スタイル
     * @return なし
     */
    def static setValueWithCreateRecord(Sheet sheet, int rowNumber, int cellNumber, def value, CellStyle style) {
        sheet.createRow(rowNumber)
        setValue(sheet, rowNumber, cellNumber, value, style)
    }
    
}
