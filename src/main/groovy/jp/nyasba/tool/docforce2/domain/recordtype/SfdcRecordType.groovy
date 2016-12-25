package jp.nyasba.tool.docforce2.domain.recordtype

import groovy.util.slurpersupport.GPathResult

/**
 * レコードタイプ
 */
class SfdcRecordType {

    def fieldXml

    def SfdcRecordType(GPathResult fieldXml){
        this.fieldXml = fieldXml
    }

    def String ラベル(){
        return fieldXml.label
    }
    
    def String API参照名(){
        return fieldXml.fullName
    }
    
    def boolean isActive(){
        return fieldXml.active == "true"
    }

    def String 説明(){
        return fieldXml.description
    }
}
