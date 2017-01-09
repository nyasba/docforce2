package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowType

/**
 * ApprovalProcessメタデータ中の「取消時のアクション」の読み取り結果
 */
class SfdcApprovalProcessRecallAction implements SfdcApprovalProcessAction {

    String type
    String name
    
    def SfdcApprovalProcessRecallAction(NodeChild xml){
        this.type = xml.type
        this.name = xml.getProperty('name')
    }
    
    def SfdcApprovalProcessRecallAction(String type, String name){
        this.type = type
        this.name = name
    }
    
    def String getType(){
        return SfdcWorkflowType.convert(type)
    }
    
    def static SfdcApprovalProcessRecallAction unlock(){
        return new SfdcApprovalProcessRecallAction("unlock", "")
    }
}
