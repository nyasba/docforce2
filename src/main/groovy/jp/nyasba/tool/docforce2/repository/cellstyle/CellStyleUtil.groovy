package jp.nyasba.tool.docforce2.repository.cellstyle

import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Workbook

/**
 * セルのスタイルを作成するUtil
 */
class CellStyleUtil {

    def static CellStyle normal(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ()
                .縦位置中央()
                .罫線で囲む()
                .改行OK()
                .build()
    }

    def static CellStyle inactive(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ()
                .縦位置中央()
                .罫線で囲む()
                .改行OK()
                .背景を網掛け()
                .build()
    }
    
    def static CellStyle alignCenter(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ()
                .縦位置中央()
                .罫線で囲む()
                .改行OK()
                .中央揃え()
                .build()
    }
    
    def static CellStyle title(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ(36)
                .縦位置中央()
                .罫線なし()
                .改行NG()
                .中央揃え()
                .build()
    }
    
    def static CellStyle titleDate(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ(18)
                .縦位置中央()
                .罫線なし()
                .改行NG()
                .右揃え()
                .build()
    }
    
    def static CellStyle sectionTitle(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ(12, ["bold"])
                .縦位置中央()
                .罫線なし()
                .改行NG()
                .build()
    }
    
    def static CellStyle tableHeader(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ(11, ["white"])
                .縦位置中央()
                .罫線で囲む()
                .改行OK()
                .見出し用背景塗りつぶし()
                .build()
    }
    
    def static CellStyle tableHeader2(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ(11)
                .縦位置上()
                .罫線で囲む()
                .改行OK()
                .見出し用背景塗りつぶし2()
                .build()
    }
    
    def static CellStyle normalWithTopBold(Workbook workbook) {
        return new CellStyleBuilder(workbook)
                .メイリオ()
                .縦位置中央()
                .上部だけ太い罫線で囲む()
                .改行OK()
                .build()
    }
    
}
