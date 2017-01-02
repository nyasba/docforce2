package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowType

/**
 * ApprovalProcessメタデータ中の「申請時のアクション」の読み取り結果
 */
class SfdcApprovalProcessRequestAction implements SfdcApprovalProcessAction {

    String type
    String name
    
    def SfdcApprovalProcessRequestAction(NodeChild xml){
        this.type = xml.type
        this.name = xml.getProperty('name')
    }
    
    def SfdcApprovalProcessRequestAction(String type, String name){
        this.type = type
        this.name = name
    }
    
    def String getType(){
        return SfdcWorkflowType.convert(type)
    }
    
    def static SfdcApprovalProcessRequestAction lock(){
        return new SfdcApprovalProcessRequestAction("lock", "")
    }
}
