package jp.nyasba.tool.docforce2.repository.cellstyle

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Font
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFColor

import java.awt.Color

/**
 * CellStyleのビルダークラス
 */
class CellStyleBuilder {

    private Workbook workbook;
    private XSSFCellStyle style;

    public CellStyleBuilder(Workbook workbook){
        this.workbook = workbook
        style = workbook.createCellStyle() as XSSFCellStyle
    }

    public CellStyle build(){
        return style
    }

    public CellStyleBuilder メイリオ(int point = 11, List option = []){
        Font font = workbook.createFont()
        font.setFontName("メイリオ")
        font.setFontHeightInPoints(point as short)
        if(option.contains("bold")){
            font.setBold(true)
        }
        if(option.contains("white")){
            font.setColor(IndexedColors.WHITE.index)
        }
        style.setFont(font)
        return this
    }
    
    public CellStyleBuilder 罫線で囲む(){
        style.setBorderTop(XSSFCellStyle.BORDER_THIN)
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN)
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN)
        style.setBorderRight(XSSFCellStyle.BORDER_THIN)
        return this
    }

    public CellStyleBuilder 罫線なし(){
        style.setBorderTop(XSSFCellStyle.BORDER_NONE)
        style.setBorderBottom(XSSFCellStyle.BORDER_NONE)
        style.setBorderLeft(XSSFCellStyle.BORDER_NONE)
        style.setBorderRight(XSSFCellStyle.BORDER_NONE)
        return this
    }

    public CellStyleBuilder 縦位置中央(){
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return this
    }

    public CellStyleBuilder 改行OK(){
        style.setWrapText(true)
        return this
    }

    public CellStyleBuilder 改行NG(){
        style.setWrapText(false)
        return this
    }

    public CellStyleBuilder 背景を網掛け(){
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index)
        style.setFillPattern(CellStyle.SOLID_FOREGROUND)
        return this
    }
    
    public CellStyleBuilder 見出し用背景塗りつぶし(){
        style.setFillForegroundColor(new XSSFColor(new Color(59, 104, 159)))
        style.setFillPattern(CellStyle.SOLID_FOREGROUND)
        return this
    }
    
    public CellStyleBuilder 中央揃え(){
        style.setAlignment(CellStyle.ALIGN_CENTER)
        return this
    }
    
    public CellStyleBuilder 右揃え(){
        style.setAlignment(CellStyle.ALIGN_RIGHT)
        return this
    }
}
