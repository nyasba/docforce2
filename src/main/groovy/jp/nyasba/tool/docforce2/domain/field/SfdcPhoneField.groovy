package jp.nyasba.tool.docforce2.domain.field

import groovy.util.slurpersupport.GPathResult

/**
 * 電話番号フィールド
 */
class SfdcPhoneField implements SfdcField {

    def fieldXml

    def SfdcPhoneField(GPathResult fieldXml){
        this.fieldXml = fieldXml
    }

    @Override
    def String ラベル(){
        return fieldXml.label
    }

    @Override
    String API参照名() {
        return fieldXml.fullName
    }

    @Override
    String タイプ() {
        return fieldXml.type
    }

    @Override
    String length() {
        return "-"
    }

    @Override
    String デフォルト値or選択リスト値() {
        return ""
    }

    @Override
    String 数式() {
        return "" // 数式は設定できない
    }

    @Override
    String ヘルプテキスト() {
        return fieldXml.inlineHelpText
    }

    @Override
    String 必須() {
        return  fieldXml.required == "true" ? "○" : ""
    }

    @Override
    String 外部ID() {
        return "" // 外部IDにはできない
    }

    @Override
    String 説明() {
        return fieldXml.description
    }
}
