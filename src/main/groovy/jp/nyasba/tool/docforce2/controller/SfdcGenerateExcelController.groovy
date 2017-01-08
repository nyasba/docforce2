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

        String inputBaseDir = getInputBaseDir(yaml)
        String outputDir = getOutputDir(yaml)
    
        yaml.resources.collect{ createResouceDocument(inputBaseDir, outputDir, it) }
    }
    
    private static String getInputBaseDir(def yaml){
        String inputBaseDir = yaml.inputBaseDir
        if(!inputBaseDir.endsWith("/")){
            inputBaseDir = inputBaseDir + "/"
        }
        return inputBaseDir
    }
    
    private static String getOutputDir(def yaml){
        return yaml.outputDir ?: "output"
    }
    
    private static void createResouceDocument(String inputBaseDir, String outputDir, def resourceYaml){
        SfdcCustomObject object = createObject(inputBaseDir, resourceYaml.resource?.object)
        List<SfdcApprovalProcess> approvalProcessList = createApprovalProcessList(inputBaseDir, resourceYaml.resource?.approvalProcesses)
        SfdcWorkflow workflow = createWorkflow(inputBaseDir, resourceYaml.resource?.workflow)
    
        new ObjectDesignExcelBookRepository(outputDir).save(object, approvalProcessList, workflow)
    }
    
    private static SfdcCustomObject createObject(String inputBaseDir, String param){
        assert param != null
        String inputObjectPath = inputBaseDir + param
        def xmlFile = Paths.get(inputObjectPath)
        def fileName = inputObjectPath.find('[^/]*$')
        return new SfdcCustomObject(fileName, xmlFile.text)
    }
    
    private static List<SfdcApprovalProcess> createApprovalProcessList(String inputBaseDir, def paramList){
        if(paramList == null || !(paramList instanceof List)){
            return []
        }
        return paramList.collect{
            String inputApprovalProcessPath = inputBaseDir + it
            def xmlFile = Paths.get(inputApprovalProcessPath)
            def fileName = inputApprovalProcessPath.find('[^/]*$')
            return new SfdcApprovalProcess(fileName, xmlFile.text)
        }
    }
    
    private static SfdcWorkflow createWorkflow(String inputBaseDir, String param){
        assert param != null
        String inputWorkflowPath = inputBaseDir + param
        def xmlFile = Paths.get(inputWorkflowPath)
        def fileName = inputWorkflowPath.find('[^/]*$')
        return new SfdcWorkflow(xmlFile.text)
    }
    
}
