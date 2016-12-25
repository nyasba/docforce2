package jp.nyasba.tool.docforce2.domain.field

import groovy.util.slurpersupport.GPathResult

/**
 * 選択リストフィールド
 */
class SfdcPicklistField implements SfdcField {
    
    def fieldXml
    
    def SfdcPicklistField(GPathResult fieldXml) {
        this.fieldXml = fieldXml
    }
    
    @Override
    def String ラベル() {
        return fieldXml.label
    }
    
    @Override
    String API参照名() {
        return fieldXml.fullName
    }
    
    @Override
    String タイプ() {
        if(fieldXml.picklist.restrictedPicklist == "true"){
            return  "${fieldXml.type}\n(Restricted)"
        }
        return fieldXml.type
    }
    
    @Override
    String length() {
        return "-"
    }
    
    @Override
    String デフォルト値or選択リスト値() {
        
        // グローバル選択リストを使う場合
        if(fieldXml.globalPicklist.text() != ""){
            return "グローバル選択リスト「${fieldXml.globalPicklist}」を利用"
        }
        
        // 通常の選択リスト
        return fieldXml.picklist.picklistValues
                .collect { new SfdcPicklistValue(it).toString() }
                .join("\n")
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
        return fieldXml.required == "true" ? "○" : ""
    }
    
    @Override
    String 外部ID() {
        return fieldXml.externalId == "true" ? "○" : ""
    }
    
    @Override
    String 説明() {
        return fieldXml.description
    }
    
    public class SfdcPicklistValue{
        def xml
    
        def SfdcPicklistValue(GPathResult xml) {
            this.xml = xml
        }
        
        public String 選択肢名(){
            return xml.fullName
        }
        public boolean isDefault(){
            return (xml.default == "true")
        }
        
        public String toString(){
            if (isDefault()){
                return 選択肢名() + " …default"
            }
            return 選択肢名()
        }
    }
}
