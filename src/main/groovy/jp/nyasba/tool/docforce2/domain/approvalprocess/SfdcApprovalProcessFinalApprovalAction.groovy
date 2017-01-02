package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowType

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
    
    def String getType(){
        return SfdcWorkflowType.convert(type)
    }
    
    def static SfdcApprovalProcessFinalApprovalAction lock(){
        return new SfdcApprovalProcessFinalApprovalAction("lock", "")
    }
    
    def static SfdcApprovalProcessFinalApprovalAction unlock(){
        return new SfdcApprovalProcessFinalApprovalAction("unlock", "")
    }
}
