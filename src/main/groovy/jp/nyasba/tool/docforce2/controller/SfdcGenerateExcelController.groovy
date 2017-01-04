package jp.nyasba.tool.docforce2.controller

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflow
import jp.nyasba.tool.docforce2.repository.ObjectDesignExcelBookRepository
import org.yaml.snakeyaml.Yaml

import java.nio.file.Paths

/**
 * エクセルを作成するためのエンドポイントとなるController
 */
class SfdcGenerateExcelController {
    
    static void main(String[] args){

        def yamlPath = Paths.get("C:/dev/sfdc/docforce2/input/setting.yml")
        def yaml = new Yaml().load(yamlPath.text)

        String inputBaseDir = yaml.inputBaseDir
        String outputDir = yaml.outputDir
        if(!inputBaseDir.endsWith("/")){
            inputBaseDir = inputBaseDir + "/"
        }
        String inputObjectPath = inputBaseDir + yaml.resource.object
        String inputApprovalProcessPath1 = inputBaseDir + yaml.resource.approvalProcesses.get(0)
        String inputWorkflowPath = inputBaseDir + yaml.resource.workflow

        
        SfdcCustomObject object = createObject(inputObjectPath)
        SfdcApprovalProcess approvalProcess1 = createApprovalProcess(inputApprovalProcessPath1)
        SfdcWorkflow workflow = createWorkflow(inputWorkflowPath)
        
        new ObjectDesignExcelBookRepository(outputDir).save(object, [approvalProcess1], workflow)
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
