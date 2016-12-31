package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.vallidation.SfdcValidation
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.PrintSetup
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

/**
 * Excelの「承認プロセス」シートを作成するためのRepository
 */
class ApprovalProcessSheetRepository {

    def createSheets(Workbook workbook, List<SfdcApprovalProcess> approvalProcessList){
        approvalProcessList.eachWithIndex{ SfdcApprovalProcess ap, int i -> createSheet(workbook, ap, i) }
        workbook.removeSheetAt(workbook.getSheetIndex("承認プロセス"))
    }
    
    def createSheet(Workbook workbook, SfdcApprovalProcess ap, int i){
        CellStyle normal = CellStyleUtil.normal(workbook)
        CellStyle inactive = CellStyleUtil.inactive(workbook)
        CellStyle sectionTitle = CellStyleUtil.sectionTitle(workbook)
    
        Sheet sheet = workbook.cloneSheet(workbook.getSheetIndex("承認プロセス"))
        workbook.setSheetName(workbook.getSheetIndex(sheet), "承認プロセス(${i+1})")
    
        CellUtil.setValueAndCellsMerged(sheet, 1, 1, 2, ap.表示ラベル(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 2, 1, 2, ap.API参照名(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 3, 1, 2, ap.説明(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 4, 1, 2, ap.開始条件(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 5, 1, 2, ap.レコードの編集(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 6, 1, 2, ap.申請者の取り消し(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 7, 1, 2, ap.承認割り当てメールテンプレート(), normal)
        CellUtil.setValueAndCellsMerged(sheet, 8, 1, 2, ap.承認ページ表示項目(), normal)
    
        CellUtil.setValueWithCreateRecord(sheet, 10, 0, "申請時のアクション", sectionTitle, 24 as float)
    
        printSetting(sheet)
    
    }
    
    
    def void printSetting(Sheet sheet){
        PrintSetup printSetup = sheet.getPrintSetup()
        printSetup.setPaperSize(PrintSetup.A4_PAPERSIZE);
        printSetup.setLandscape(true);//横向き
        printSetup.setScale(50 as short)
    }
}
