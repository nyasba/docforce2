package jp.nyasba.tool.docforce2.domain

import jp.nyasba.tool.docforce2.domain.field.SfdcField
import jp.nyasba.tool.docforce2.domain.field.SfdcCustomFieldFactory
import jp.nyasba.tool.docforce2.domain.field.SfdcNameField
import jp.nyasba.tool.docforce2.domain.recordtype.SfdcRecordType
import jp.nyasba.tool.docforce2.domain.recordtype.SfdcRecordTypeFactory
import jp.nyasba.tool.docforce2.domain.vallidation.SfdcValidation
import jp.nyasba.tool.docforce2.domain.vallidation.SfdcValidationFactory

/**
 * CustomObjectメタデータの読み取り結果（XML）
 */
class SfdcCustomObject {

    def fileName
    def xml

    def SfdcCustomObject(String fileName, String rawXml){
        this.fileName = fileName
        def slurper = new XmlSlurper()
        xml = slurper.parseText(rawXml)
    }

    def String title(){
        return "${表示ラベル()}(${API参照名()})"
    }
    
    def String 表示ラベル(){
        return xml.label
    }

    def String API参照名(){
        return fileName.tokenize('.').get(0)
    }

    def String 説明(){
        return xml.description
    }

    def SfdcNameField NameField(){
        return new SfdcNameField(xml.nameField)
    }
    
    def List<SfdcRecordType> レコードタイプリスト() {
        return xml.recordTypes.collect { SfdcRecordTypeFactory.create(it) }
    }
    
    def List<SfdcField> Fieldリスト() {
        return xml.fields.collect { SfdcCustomFieldFactory.create(it) }
    }

    def List<SfdcValidation> 入力規則リスト() {
        return xml.validationRules.collect { SfdcValidationFactory.create(it) }
    }
}
