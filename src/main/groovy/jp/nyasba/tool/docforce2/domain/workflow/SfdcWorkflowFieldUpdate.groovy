package jp.nyasba.tool.docforce2.domain.workflow

import groovy.util.slurpersupport.NodeChild

/**
 * 項目自動更新ドメイン
 */
class SfdcWorkflowFieldUpdate {

    def ラベル
    def API参照名
    def 対象項目
    MappingType マッピング種別
    def マッピング詳細
    
    public SfdcWorkflowFieldUpdate(NodeChild xml){
        this.ラベル = xml.getProperty("name")
        this.API参照名 = xml.fullName
        this.対象項目 = xml.field
        this.マッピング種別 = MappingType.convert(xml.operation as String)
        
        switch (this.マッピング種別){
            case MappingType.LITERAL : this.マッピング詳細 = xml.literalValue; break
            case MappingType.FORMULA : this.マッピング詳細 = xml.formula; break
            default: this.マッピング詳細 = ""
        }
    }
    
    
    enum MappingType{
        LITERAL("Literal", "値"),
        FORMULA("Formula", "数式"),
        NULL("Null", "Null更新"),
        ERROR("Error", "")
        
        String xmlValue
        String outputValue
        
        public MappingType(String xmlValue, String outputValue){
            this.xmlValue = xmlValue
            this.outputValue = outputValue
        }
        
        public static MappingType convert(String xmlValue){
            for(MappingType e : values()){
                if(e.xmlValue == xmlValue){
                    return e
                }
            }
            return ERROR
        }
    }
    
}
