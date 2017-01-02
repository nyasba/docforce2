package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild

/**
 * ApprovalProcessメタデータ中の「最終却下時のアクション」の読み取り結果
 */
class SfdcApprovalProcessFinalRejectionAction implements SfdcApprovalProcessAction {

    String type
    String name
    
    def SfdcApprovalProcessFinalRejectionAction(NodeChild xml){
        this.type = xml.type
        this.name = xml.getProperty('name')
    }
    
    def SfdcApprovalProcessFinalRejectionAction(String type, String name){
        this.type = type
        this.name = name
    }
    
    def static SfdcApprovalProcessFinalRejectionAction レコードロック(){
        return new SfdcApprovalProcessFinalRejectionAction("レコードのロック", "")
    }
    
    def static SfdcApprovalProcessFinalRejectionAction レコードロック解除(){
        return new SfdcApprovalProcessFinalRejectionAction("レコードのロック解除", "")
    }
}
