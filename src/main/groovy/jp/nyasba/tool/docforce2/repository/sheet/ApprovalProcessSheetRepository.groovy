package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcessAction
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcessStep
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.RowHeightUtil
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress

/**
 * Excelの「承認プロセス」シートを作成するためのRepository
 */
class ApprovalProcessSheetRepository {
    
    CellStyle sectionTitle;
    CellStyle normal;
    CellStyle tableHeader;
    CellStyle tableHeader2;

    def createSheets(Workbook workbook, List<SfdcApprovalProcess> approvalProcessList){
        
        // 利用するスタイルを作成
        sectionTitle = CellStyleUtil.sectionTitle(workbook)
        normal = CellStyleUtil.normal(workbook)
        tableHeader = CellStyleUtil.tableHeader(workbook)
        tableHeader2 = CellStyleUtil.tableHeader2(workbook)
    
        approvalProcessList.eachWithIndex{ SfdcApprovalProcess ap, int i -> createSheet(workbook, ap, i) }
        workbook.removeSheetAt(workbook.getSheetIndex("承認プロセス"))
    }
    
    def createSheet(Workbook workbook, SfdcApprovalProcess ap, int i){
        Sheet sheet = workbook.cloneSheet(workbook.getSheetIndex("承認プロセス"))
        workbook.setSheetName(workbook.getSheetIndex(sheet), "承認プロセス(${i+1})")
        workbook.setSheetOrder("承認プロセス(${i+1})", i+5)
    
        int row = 1;

        承認プロセス情報1行(sheet, row++, "表示ラベル", ap.表示ラベル())
        承認プロセス情報1行(sheet, row++, "API参照名", ap.API参照名())
        承認プロセス情報1行(sheet, row++, "説明", ap.説明())
        承認プロセス情報1行(sheet, row++, "開始条件", ap.開始条件())
        承認プロセス情報1行(sheet, row++, "レコードの編集", ap.レコードの編集())
        承認プロセス情報1行(sheet, row++, "申請者の取り消し", ap.申請者の取り消し())
        承認プロセス情報1行(sheet, row++, "承認割り当てメールテンプレート", ap.承認割り当てメールテンプレート())
        承認プロセス情報1行(sheet, row++, "承認ページ表示項目", ap.承認ページ表示項目())
    
        row += 2
        row = アクションリスト(sheet, row++, "申請時のアクション", ap.申請時のアクションリスト())
        row = アクションリスト(sheet, row++, "取消時のアクション", ap.取消時のアクションリスト())
        
        row++
        row = 承認ステップ(sheet, row++, ap.承認ステップリスト())
    
        row++
        row = アクションリスト(sheet, row++, "最終承認時のアクション", ap.最終承認時のアクションリスト())
        row = アクションリスト(sheet, row++, "最終却下時のアクション", ap.最終却下時のアクションリスト())

        印刷設定(sheet)
    
    }
    
    def void 承認プロセス情報1行(Sheet sheet, int row, String key, String value){
        Row r =sheet.createRow(row)
        CellUtil.setValueAndCellsMerged(sheet, row, 0, 1, key, tableHeader)
        CellUtil.setValueAndCellsMerged(sheet, row, 2, 3, value, normal)
        r.setHeightInPoints(RowHeightUtil.optimizedValue(value))
    }
    
    def int アクションリスト(Sheet sheet, int row, String actionLabel, List<SfdcApprovalProcessAction> actionList){
        int originalRow = row
        CellUtil.setValueWithCreateRecord(sheet, row, 1, actionLabel, tableHeader2)
        CellUtil.setValue(sheet, row, 2, "種別", tableHeader2)
        CellUtil.setValueAndCellsMerged(sheet, row, 3, 4, "名前", tableHeader2)
        row++
        actionList.each {
            CellUtil.setValueWithCreateRecord(sheet, row, 1, "", tableHeader2)
            CellUtil.setValue(sheet, row, 2, it.type, normal)
            CellUtil.setValueAndCellsMerged(sheet, row, 3, 4, it.name, normal)
            row++
        }
        sheet.addMergedRegion(new CellRangeAddress(originalRow, row -1 , 1, 1))
        return row
    }
    
    def int 承認ステップ(Sheet sheet, int row, List<SfdcApprovalProcessStep> stepList){
        CellUtil.setValueWithCreateRecord(sheet, row++, 0, "承認ステップ", sectionTitle, 24 as float)
    
        sheet.createRow(row)
        CellUtil.setValueAndCellsMerged(sheet, row, 0, 1, "ラベル", tableHeader)
        CellUtil.setValue(sheet, row, 2, "API参照名", tableHeader)
        CellUtil.setValue(sheet, row, 3, "条件", tableHeader)
        CellUtil.setValue(sheet, row, 4, "承認割り当て先", tableHeader)
        CellUtil.setValue(sheet, row, 5, "代理承認", tableHeader)
        CellUtil.setValue(sheet, row, 6, "却下時の処理", tableHeader)
        CellUtil.setValue(sheet, row, 7, "説明", tableHeader)
        row++
        stepList.each {
            Row r = sheet.createRow(row)
            CellUtil.setValueAndCellsMerged(sheet, row, 0, 1, it.ラベル, normal)
            CellUtil.setValue(sheet, row, 2, it.API参照名, normal)
            CellUtil.setValue(sheet, row, 3, it.条件, normal)
            CellUtil.setValue(sheet, row, 4, it.承認割り当て先, normal)
            CellUtil.setValue(sheet, row, 5, it.代理承認, normal)
            CellUtil.setValue(sheet, row, 6, it.却下時の処理, normal)
            CellUtil.setValue(sheet, row, 7, it.説明, normal)
            r.setHeightInPoints( [
                    RowHeightUtil.optimizedValue(it.条件),
                    RowHeightUtil.optimizedValue(it.承認割り当て先),
                    RowHeightUtil.optimizedValue(it.却下時の処理),
                    RowHeightUtil.optimizedValue(it.説明 as String),
            ].max())
            row++
        }
        return row
    }
    
    
    def void 印刷設定(Sheet sheet){
        PrintSetup printSetup = sheet.getPrintSetup()
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(true);//横向き
        printSetup.setScale(50 as short)
    }
}
