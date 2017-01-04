package jp.nyasba.tool.docforce2.controller

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflow
import jp.nyasba.tool.docforce2.repository.ObjectDesignExcelBookRepository

import java.nio.file.Paths

/**
 * エクセルを作成するためのエンドポイントとなるController
 */
class SfdcGenerateExcelController {
    
    static void main(String[] args){
        // サンプル
        String inputBaseDir = "C:/dev/sfdc/docforce2/src/test/resources/"
        String inputObjectPath = inputBaseDir + "object/Travel_Request__c.object"
        String inputApprovalProcessPath1 = inputBaseDir + "approvalProcess/Travel_Request__c.TravelRequestApprovalProcess.approvalProcess"
        String inputWorkflowPath = inputBaseDir + "workflow/Travel_Request__c.workflow"
        
        SfdcCustomObject object = createObject(inputObjectPath)
        SfdcApprovalProcess approvalProcess1 = createApprovalProcess(inputApprovalProcessPath1)
        SfdcWorkflow workflow = createWorkflow(inputWorkflowPath)
        
        new ObjectDesignExcelBookRepository().save(object, [approvalProcess1], workflow)
    }
    
    private static SfdcCustomObject createObject(String inputPath){
        def xmlFile = Paths.get(inputPath)
        def fileName = inputPath.find('[^/]*$')
        return new SfdcCustomObject(fileName, xmlFile.text)
    }
    
    private static SfdcApprovalProcess createApprovalProcess(String inputPath){
        def xmlFile = Paths.get(inputPath)
        def fileName = inputPath.find('/[^/]*$')
        return new SfdcApprovalProcess(fileName, xmlFile.text)
    }
    
    private static SfdcWorkflow createWorkflow(String inputPath){
        def xmlFile = Paths.get(inputPath)
        return new SfdcWorkflow(xmlFile.text)
    }
    
}
