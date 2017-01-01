package jp.nyasba.tool.docforce2.domain.approvalprocess

import jp.nyasba.tool.docforce2.domain.folder.SfdcFolder

/**
 * ApprovalProcessメタデータの読み取り結果（XML）
 */
class SfdcApprovalProcess {

    def fileName
    def xml
    
    def SfdcApprovalProcess(String fileName, String rawXml){
        this.fileName = fileName
        def slurper = new XmlSlurper()
        xml = slurper.parseText(rawXml)
    }

    def String 表示ラベル(){
        return xml.label
    }

    def String API参照名(){
        return fileName.tokenize('.').get(1)
    }

    def String 説明(){
        return xml.description
    }
    
    def String 開始条件(){
        if(xml.entryCriteria.formula != null){
            return xml.entryCriteria.formula
        }
        return ""
    }
    
    def String レコードの編集(){
        switch (xml.recordEditability){
            case "AdminOnly" : return "システム管理者のみ"
            // TODO 「承認者＋管理者」用の変換を追加する
            default: return ""
        }
    }
    
    def String 申請者の取り消し(){
        return ( xml.allowRecall == "true" ) ? "○" : "×"
    }
    
    def String 承認割り当てメールテンプレート(){
        return SfdcFolder.convert(xml.emailTemplate.text())
    }
    
    def String 承認ページ表示項目(){
        return xml.approvalPageFields.field.join("\n")
    }
    
    def List<SfdcApprovalProcessRequestAction> 申請時のアクションリスト(){
        return [ SfdcApprovalProcessRequestAction.レコードロック() ]
        // FIXME 申請時のアクションのタグを調べて追加する
    }
    
    def List<SfdcApprovalProcessStep> 承認ステップリスト(){
        return xml.approvalStep.collect{ new SfdcApprovalProcessStep(it) }
    }
}
