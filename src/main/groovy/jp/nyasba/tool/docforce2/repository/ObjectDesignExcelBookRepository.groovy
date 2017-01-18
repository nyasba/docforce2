package jp.nyasba.tool.docforce2.repository

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.domain.approvalprocess.SfdcApprovalProcess
import jp.nyasba.tool.docforce2.domain.workflow.SfdcWorkflow
import jp.nyasba.tool.docforce2.repository.sheet.*
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * オブジェクト定義書(Excel)にアクセスするためのRepository
 */
class ObjectDesignExcelBookRepository {

    private static Path TEMPLATE = Paths.get(ClassLoader.getSystemResource("template/オブジェクト定義書.xlsx").toURI())

    private String outputDir
    private String author
    
    public ObjectDesignExcelBookRepository(String outputDir = "output/", String author = "aaa"){
        this.outputDir = outputDir.endsWith("/") ? outputDir : outputDir + "/"
        this.author = author
    }

    def void save(
            SfdcCustomObject customObject,
            List<SfdcApprovalProcess> approvalProcessList = Collections.emptyList(),
            SfdcWorkflow workflow = new SfdcWorkflow()
    ){

        Path outputFile = outputFilePath(customObject)
        Files.deleteIfExists(outputFile)

        Workbook workbook = WorkbookFactory.create(TEMPLATE.toFile())
    
        new TitleSheetRepository(author).createSheet(workbook,customObject)
        new ObjectSheetRepository().createSheet(workbook, customObject)
        new CustomFiledSheetRepository().createSheet(workbook, customObject)
        new ValidationSheetRepository().createSheet(workbook, customObject)
        new ApprovalProcessSheetRepository().createSheets(workbook, approvalProcessList)
        new WorkflowRuleSheetRepository().createSheet(workbook, workflow)
        new WorkflowActionSheetRepository().createSheet(workbook, workflow)

        saveWorkbook(outputFile, workbook)
    }

    private Path outputFilePath(SfdcCustomObject customObject){
        return Paths.get("${this.outputDir}/オブジェクト定義書_${customObject.表示ラベル()}.xlsx")
    }

    private static void saveWorkbook(Path filePath, Workbook workbook){
        new FileOutputStream(filePath.toFile()).withStream {
            workbook.write(it)
        }
    }
}
