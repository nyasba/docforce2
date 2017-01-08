package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.operator.SfdcOperation

/**
 * ApprovalProcessメタデータ中の「承認ステップ」の読み取り結果
 */
class SfdcApprovalProcessStep {

    def ラベル
    def API参照名
    def 条件
    def 承認割り当て先
    def 代理承認
    def 却下時の処理
    def 説明
    
    def SfdcApprovalProcessStep(NodeChild xml){
        this.ラベル = xml.label
        this.API参照名 = xml.getProperty("name")
        this.条件 = xml.entryCriteria.criteriaItems.collect{ "${it?.field} ${SfdcOperation.convert(it?.operation)} ${it?.value}"}.join("\n")
        this.承認割り当て先 = xml.assignedApprover.approver.collect{ convert承認割り当て先(it) }.join("\n")
        this.代理承認 = (xml.allowDelegate == "true") ? "○" : "×"
        this.却下時の処理 = xml.rejectBehavior.type.collect{ convert却下時の処理(it) }.join("\n")
        this.説明 = xml.description
    }
    
    def String convert承認割り当て先(NodeChild approver){
        if( approver.getProperty('name').toString() == "" ){
            return "${approver.type}"
        }
        return "${approver.type}:${approver.getProperty('name')}"
    }
    
    def String convert却下時の処理(NodeChild type){
        switch (type.text()){
            case "RejectRequest" : return "申請を却下する"
        }
        return type.text()
    }
}
