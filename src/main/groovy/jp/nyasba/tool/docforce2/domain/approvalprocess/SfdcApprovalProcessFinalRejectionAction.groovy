package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowType

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
    
    def String getType(){
        return SfdcWorkflowType.convert(type)
    }
    
    def static SfdcApprovalProcessFinalRejectionAction lock(){
        return new SfdcApprovalProcessFinalRejectionAction("lock", "")
    }
    
    def static SfdcApprovalProcessFinalRejectionAction unlock(){
        return new SfdcApprovalProcessFinalRejectionAction("unlock", "")
    }
}
