package jp.nyasba.tool.docforce2.repository

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import jp.nyasba.tool.docforce2.repository.sheet.CustomFiledSheetRepository
import jp.nyasba.tool.docforce2.repository.sheet.ObjectSheetRepository
import jp.nyasba.tool.docforce2.repository.sheet.TitleSheetRepository
import jp.nyasba.tool.docforce2.repository.sheet.ValidationSheetRepository
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


    def void save(SfdcCustomObject customObject){

        Path outputFile = outputFilePath(customObject)
        Files.deleteIfExists(outputFile)

        Workbook workbook = WorkbookFactory.create(TEMPLATE.toFile())

        new TitleSheetRepository().createSheet(workbook,customObject)
        new ObjectSheetRepository().createSheet(workbook, customObject)
        new CustomFiledSheetRepository().createSheet(workbook, customObject)
        new ValidationSheetRepository().createSheet(workbook, customObject)

        saveWorkbook(outputFile, workbook)
    }

    private static Path outputFilePath(SfdcCustomObject customObject){
        def outputPath = (System.properties.get("outputPath")) ?: "output"
        return Paths.get("${outputPath}/オブジェクト定義書_${customObject.表示ラベル()}.xlsx")
    }

    private static void saveWorkbook(Path filePath, Workbook workbook){
        new FileOutputStream(filePath.toFile()).withStream {
            workbook.write(it)
        }
    }
}
