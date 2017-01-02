package jp.nyasba.tool.docforce2.domain.workflow

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.folder.SfdcFolder

/**
 * メールアラートドメイン
 */
class SfdcWorkflowMailAlert {

    String ラベル
    String API参照名
    String メールテンプレート
    String 差出人
    String 受信者
    
    public SfdcWorkflowMailAlert(NodeChild xml){
        this.ラベル = xml.description as String
        this.API参照名 = xml.fullName as String
        this.メールテンプレート = xml.template as String
        this.差出人 = xml.senderType as String
        this.受信者 = xml.recipients.collect{
            if(it.type == "owner") { "所有者"}
            else if (it.type == "user") { "ユーザ:${it.recipient}"}
            else "${it.type}"
        }.join("\n")
    }
    
    public String 差出人outputValue(){
        return this.差出人.replace("CurrentUser", "現在のユーザ")
    }
    
    public String メールテンプレートoutputValue(){
        return SfdcFolder.convert(this.メールテンプレート)
    }
}
