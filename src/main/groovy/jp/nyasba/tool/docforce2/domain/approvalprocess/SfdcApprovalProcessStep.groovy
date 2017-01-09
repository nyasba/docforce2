package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.condition.SfdcCriteria

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
    List<SfdcApprovalProcessStepAction> 承認時のアクションリスト
    List<SfdcApprovalProcessStepAction> 却下時のアクションリスト
    
    def SfdcApprovalProcessStep(NodeChild xml){
        this.ラベル = xml.label
        this.API参照名 = xml.getProperty("name")
        this.条件 = SfdcCriteria.getCriteria(xml.entryCriteria)
        this.承認割り当て先 = xml.assignedApprover.approver.collect{ convert承認割り当て先(it) }.join("\n")
        this.代理承認 = (xml.allowDelegate == "true") ? "○" : "×"
        this.却下時の処理 = xml.rejectBehavior.type.collect{ convert却下時の処理(it) }.join("\n")
        this.説明 = xml.description
        this.承認時のアクションリスト = xml.approvalActions.action.collect { new SfdcApprovalProcessStepAction(it) }
        this.却下時のアクションリスト = xml.rejectionActions.action.collect { new SfdcApprovalProcessStepAction(it) }
    }
    
    def String convert承認割り当て先(NodeChild approver){
        if(approver.getProperty('name') == "" ){
            return "${convert承認割り当て先Type(approver.type)}"
        }
        return "${convert承認割り当て先Type(approver.type)}:${approver.getProperty('name')}"
    }
    
    def String convert承認割り当て先Type(def type){
        switch (type as String){
            case "userHierarchyField" : return "マネージャ"
            case "relatedUserField" : return "関連ユーザ"
            case "queue" : return "キュー"
            default: return type as String
        }
    }
    
    def String convert却下時の処理(NodeChild type){
        switch (type.text()){
            case "RejectRequest" : return "申請を却下する"
        }
        return type.text()
    }
}
