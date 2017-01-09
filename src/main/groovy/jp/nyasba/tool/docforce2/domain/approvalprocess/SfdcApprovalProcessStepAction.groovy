package jp.nyasba.tool.docforce2.domain.approvalprocess

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflowType

/**
 * ApprovalProcessメタデータ中の「承認ステップ中の承認時/却下時のアクション」の読み取り結果
 */
class SfdcApprovalProcessStepAction implements SfdcApprovalProcessAction {

    String type
    String name
    
    def SfdcApprovalProcessStepAction(NodeChild xml){
        this.type = xml.type
        this.name = xml.getProperty('name')
    }
    
    def SfdcApprovalProcessStepAction(String type, String name){
        this.type = type
        this.name = name
    }
    
    def String getType(){
        return SfdcWorkflowType.convert(type)
    }
    
}
