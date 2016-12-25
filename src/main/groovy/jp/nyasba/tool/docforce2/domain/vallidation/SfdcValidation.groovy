package jp.nyasba.tool.docforce2.domain.vallidation

import groovy.util.slurpersupport.GPathResult
import jp.nyasba.tool.docforce2.domain.field.SfdcField

/**
 * 入力規則
 */
class SfdcValidation {

    def fieldXml

    def SfdcValidation(GPathResult fieldXml){
        this.fieldXml = fieldXml
    }

    def String 名前(){
        return fieldXml.fullName
    }

    def boolean isActive(){
        return fieldXml.active == "true"
    }

    def String エラーメッセージ(){
        return fieldXml.errorMessage
    }

    def String 数式(){
        return fieldXml.errorConditionFormula
    }
}
