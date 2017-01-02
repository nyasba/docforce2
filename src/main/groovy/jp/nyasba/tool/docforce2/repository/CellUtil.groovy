package jp.nyasba.tool.docforce2.repository

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.util.CellRangeAddress

/**
 * セルに関するUtil
 */
class CellUtil {
    
    /**
     * セルを作成し、値をセットする
     *
     * @param sheet シート
     * @param rowNumber 行番号
     * @param colNumber 列番号
     * @param value 値
     * @param style スタイル
     * @return なし
     */
    def static setValue(Sheet sheet, int rowNumber, int colNumber, def value, CellStyle style) {
        Cell cell = sheet.getRow(rowNumber).createCell(colNumber)
        cell.setCellValue(value as String)
        cell.setCellStyle(style)
    }
    
    /**
     * 行およびセルを作成し、値をセットする
     *
     * @param sheet シート
     * @param rowNumber 行番号
     * @param colNumber 列番号
     * @param value 値
     * @param style スタイル
     * @return なし
     */
    def static setValueWithCreateRecord(Sheet sheet, int rowNumber, int colNumber, def value, CellStyle style, float height = -1) {
        Row row = sheet.createRow(rowNumber)
        row.setHeightInPoints(height)
        setValue(sheet, rowNumber, colNumber, value, style)
    }
    
    /**
     * セルを作成し、値をセットした上で同一行のセルを結合する
     *
     * @param sheet シート
     * @param rowNumber 行番号
     * @param firstColNumber 列番号
     * @param lastColNumber 列番号
     * @param value 値
     * @param style スタイル
     * @return なし
     */
    def static setValueAndCellsMerged(Sheet sheet, int rowNumber, int firstColNumber, int lastColNumber, def value, CellStyle style){
        setValue(sheet, rowNumber, firstColNumber, value, style)
        (firstColNumber+1..lastColNumber).each { setValue(sheet, rowNumber, it, "", style) }        // スタイルを統一するため
        sheet.addMergedRegion(new CellRangeAddress(rowNumber, rowNumber, firstColNumber, lastColNumber))
    }
    
}
