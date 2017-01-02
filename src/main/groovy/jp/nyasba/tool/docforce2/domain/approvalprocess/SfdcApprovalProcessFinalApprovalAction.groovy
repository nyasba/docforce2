package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild

/**
 * ApprovalProcessメタデータ中の「最終承認時のアクション」の読み取り結果
 */
class SfdcApprovalProcessFinalApprovalAction implements SfdcApprovalProcessAction {

    String type
    String name
    
    def SfdcApprovalProcessFinalApprovalAction(NodeChild xml){
        this.type = xml.type
        this.name = xml.getProperty('name')
    }
    
    def SfdcApprovalProcessFinalApprovalAction(String type, String name){
        this.type = type
        this.name = name
    }
    
    def static SfdcApprovalProcessFinalApprovalAction レコードロック(){
        return new SfdcApprovalProcessFinalApprovalAction("レコードのロック", "")
    }
    
    def static SfdcApprovalProcessFinalApprovalAction レコードロック解除(){
        return new SfdcApprovalProcessFinalApprovalAction("レコードのロック解除", "")
    }
}
