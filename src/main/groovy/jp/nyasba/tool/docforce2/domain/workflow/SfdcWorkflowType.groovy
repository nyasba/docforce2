package jp.nyasba.tool.docforce2.domain.workflow

/**
 * ワークフローのタイプ
 */
enum SfdcWorkflowType {
    LOCK("lock", "レコードのロック"),
    UNLOCK("unlock", "レコードのロック解除"),
    WORKFLOW_RULE("rule", "ワークフロールール"),
    FIELD_UPDATE("FieldUpdate", "項目自動更新"),
    MAIL_ALERT("Alert", "メールアラート")
    
    private String typeName
    private String outputName
    
    public SfdcWorkflowType(String typeName, String outputName){
        this.typeName = typeName
        this.outputName = outputName
    }
    
    public static String convert(String typeName){
        for(SfdcWorkflowType e : this.values()){
            if(e.typeName == typeName){ return e.outputName }
        }
        return ""
    }
    
}