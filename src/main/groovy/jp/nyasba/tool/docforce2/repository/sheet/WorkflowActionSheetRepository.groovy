package jp.nyasba.tool.docforce2.repository.sheet

import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflow
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowFieldUpdate
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowMailAlert
import jp.nyasba.tool.docforce2.repository.CellUtil
import jp.nyasba.tool.docforce2.repository.cellstyle.CellStyleUtil
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

/**
 * Excelの「ワークフローアクション」シートを作成するためのRepository
 */
class WorkflowActionSheetRepository {
    
    CellStyle sectionTitle
    CellStyle normal
    CellStyle tableHeader
    
    def createSheet(Workbook workbook, SfdcWorkflow workflow){
    
        // 利用するスタイルを作成
        sectionTitle = CellStyleUtil.sectionTitle(workbook)
        normal = CellStyleUtil.normal(workbook)
        tableHeader = CellStyleUtil.tableHeader(workbook)

        Sheet sheet = workbook.getSheet("ワークフローアクション")
        int row = 0
        
        row = 項目自動更新(sheet, row, workflow.項目自動更新リスト()) + 2
        row = メールアラート(sheet, row, workflow.メールアラートリスト()) + 2

    }
    
    private int 項目自動更新(Sheet sheet, int row, List<SfdcWorkflowFieldUpdate> list){
        // タイトル行
        CellUtil.setValueWithCreateRecord(sheet, row++, 0, "■項目自動更新", sectionTitle, 24 as float )
        
        // 見出し行
        Row r = sheet.createRow(row)
        CellUtil.setValue(sheet, row, 0, "No", tableHeader)
        CellUtil.setValue(sheet, row, 1, "ラベル", tableHeader)
        CellUtil.setValue(sheet, row, 2, "API参照名", tableHeader)
        CellUtil.setValue(sheet, row, 3, "対象項目", tableHeader)
        CellUtil.setValue(sheet, row, 4, "マッピング種別", tableHeader)
        CellUtil.setValue(sheet, row, 5, "マッピング", tableHeader)
        row++
        
        // データ行
        list.eachWithIndex{ SfdcWorkflowFieldUpdate fieldUpdate, int i ->
            Row r1 = sheet.createRow(row)
            CellUtil.setValue(sheet, row, 0, i+1, normal)
            CellUtil.setValue(sheet, row, 1, fieldUpdate.ラベル, normal)
            CellUtil.setValue(sheet, row, 2, fieldUpdate.API参照名, normal)
            CellUtil.setValue(sheet, row, 3, fieldUpdate.対象項目, normal)
            CellUtil.setValue(sheet, row, 4, fieldUpdate.マッピング種別.outputValue, normal)
            CellUtil.setValue(sheet, row, 5, fieldUpdate.マッピング詳細, normal)
            row++
        }
        
        if(list.isEmpty()){
            Row r1 = sheet.createRow(row)
            CellUtil.setValue(sheet, row, 0, "", normal)
            CellUtil.setValue(sheet, row, 1, "", normal)
            CellUtil.setValue(sheet, row, 2, "", normal)
            CellUtil.setValue(sheet, row, 3, "", normal)
            CellUtil.setValue(sheet, row, 4, "", normal)
            CellUtil.setValue(sheet, row, 5, "", normal)
            row++
        }
        return row
    }
    
    private int メールアラート(Sheet sheet, int row, List<SfdcWorkflowMailAlert> list){
        // タイトル行
        CellUtil.setValueWithCreateRecord(sheet, row++, 0, "■メールアラート", sectionTitle, 24 as float )
        
        // 見出し行
        Row r = sheet.createRow(row)
        CellUtil.setValue(sheet, row, 0, "No", tableHeader)
        CellUtil.setValue(sheet, row, 1, "ラベル", tableHeader)
        CellUtil.setValue(sheet, row, 2, "API参照名", tableHeader)
        CellUtil.setValue(sheet, row, 3, "メールテンプレート", tableHeader)
        CellUtil.setValue(sheet, row, 4, "差出人", tableHeader)
        CellUtil.setValue(sheet, row, 5, "受信者", tableHeader)
        row++
        
        // データ行
        list.eachWithIndex{ SfdcWorkflowMailAlert alert, int i ->
            Row r1 = sheet.createRow(row)
            CellUtil.setValue(sheet, row, 0, i+1, normal)
            CellUtil.setValue(sheet, row, 1, alert.ラベル, normal)
            CellUtil.setValue(sheet, row, 2, alert.API参照名, normal)
            CellUtil.setValue(sheet, row, 3, alert.メールテンプレートoutputValue(), normal)
            CellUtil.setValue(sheet, row, 4, alert.差出人outputValue(), normal)
            CellUtil.setValue(sheet, row, 5, alert.受信者, normal)
            row++
        }
        
        if(list.isEmpty()){
            Row r1 = sheet.createRow(row)
            CellUtil.setValue(sheet, row, 0, "", normal)
            CellUtil.setValue(sheet, row, 1, "", normal)
            CellUtil.setValue(sheet, row, 2, "", normal)
            CellUtil.setValue(sheet, row, 3, "", normal)
            CellUtil.setValue(sheet, row, 4, "", normal)
            CellUtil.setValue(sheet, row, 5, "", normal)
            row++
        }
        return row
    }
}
