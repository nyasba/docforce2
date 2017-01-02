package jp.nyasba.tool.docforce2.repository

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflow
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Paths

class ExcelRepositorySpec extends Specification {

    def "カスタムオブジェクト出張申請をExcelに変換する"(){
        setup:
            def output = Paths.get("output/オブジェクト定義書_出張申請.xlsx")
            Files.deleteIfExists(output)

            SfdcCustomObject object = createObject("Travel_Request__c.object")
            SfdcApprovalProcess ap1 = createApprovalProcess("Travel_Request__c.TravelRequestApprovalProcess.approvalProcess")
            SfdcWorkflow workflow = createWorkflow("Travel_Request__c.workflow")
        
            def sut = new ObjectDesignExcelBookRepository()

        expect:
            sut.save(object, [ap1], workflow)
            assert Files.exists(output)
    }
    
    def "カスタムオブジェクト休暇申請をExcelに変換する"(){
        setup:
        def output = Paths.get("output/オブジェクト定義書_休暇申請.xlsx")
        Files.deleteIfExists(output)

        SfdcCustomObject object = createObject("HolidayRequest__c.object")
        SfdcWorkflow workflow = createWorkflow("HolidayRequest__c.workflow")
        def sut = new ObjectDesignExcelBookRepository()
        
        expect:
        sut.save(object, [], workflow)
        assert Files.exists(output)
    }
    
    private SfdcCustomObject createObject(String fileName){
        def xmlFile = ClassLoader.getSystemResource("object/${fileName}")
        return new SfdcCustomObject(fileName, xmlFile.text)
    }
    
    private SfdcApprovalProcess createApprovalProcess(String fileName){
        def xmlFile = ClassLoader.getSystemResource("approvalprocess/${fileName}")
        return new SfdcApprovalProcess(fileName, xmlFile.text)
    }
    
    private SfdcWorkflow createWorkflow(String fileName){
        def xmlFile = ClassLoader.getSystemResource("workflow/${fileName}")
        return new SfdcWorkflow(xmlFile.text)
    }
    
}
