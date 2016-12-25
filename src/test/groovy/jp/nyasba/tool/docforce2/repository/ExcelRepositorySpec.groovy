package jp.nyasba.tool.docforce2.repository

import jp.nyasba.tool.docforce2.domain.SfdcCustomObject
import spock.lang.Specification
import spock.lang.Unroll

import java.nio.file.Files
import java.nio.file.Paths


class ExcelRepositorySpec extends Specification {

    @Unroll
    def "カスタムオブジェクト#objectNameをExcelに変換する"(String objectName, String apiName){
        setup:
            def output = Paths.get("output/オブジェクト定義書_${objectName}.xlsx")
            Files.deleteIfExists(output)

            def fileName = "${apiName}__c.object"
            def xmlFile = ClassLoader.getSystemResource("object/${fileName}")
            SfdcCustomObject object = new SfdcCustomObject(fileName, xmlFile.text)
            def sut = new ObjectDesignExcelBookRepository()

        expect:
            sut.save(object)
            assert Files.exists(output)

        where:
            objectName | apiName
            "出張申請" | "Travel_Request"
            "休暇申請" | "HolidayRequest"
    }

}
