package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild

/**
 * ApprovalProcessメタデータ中の「申請時のアクション」の読み取り結果
 */
class SfdcApprovalProcessRequestAction {

    def type
    def description
    
    def SfdcApprovalProcessRequestAction(NodeChild xml){
        this.type = xml.action.type
        this.description = xml.action.name
    }
    
    def SfdcApprovalProcessRequestAction(String type, String description){
        this.type = type
        this.description = description
    }
    
    def static SfdcApprovalProcessRequestAction レコードロック(){
        return new SfdcApprovalProcessRequestAction("レコードのロック", "")
    }
}
