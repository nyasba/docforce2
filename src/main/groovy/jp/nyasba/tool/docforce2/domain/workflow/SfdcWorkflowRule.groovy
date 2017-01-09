package jp.nyasba.tool.docforce2.domain.workflow

import groovy.util.slurpersupport.NodeChild
import jp.nyasba.tool.docforce2.domain.condition.SfdcCriteria

/**
 * ワークフロールールドメイン
 */
class SfdcWorkflowRule {

    String ラベル
    String 評価条件
    String トリガータイプ
    List<Action> アクションリスト
    
    public SfdcWorkflowRule(NodeChild xml){
        this.ラベル = xml.fullName
        this.評価条件 = SfdcCriteria.getCriteria(xml)
        this.トリガータイプ = convertTriggerType(xml.triggerType)
        this.アクションリスト = xml.actions.collect{ new Action(it.type, it.name) }
    }
    
    private String convertTriggerType(def xmlValue){
        switch (xmlValue as String){
            case "onCreateOnly" : return "作成時のみ"
            case "onAllChanges" : return "作成時/全ての更新時"
            case "onCreateOrTriggeringUpdate" : return "作成時/基準を満たすように更新時"
            default: return xmlValue as String
        }
    }
    
    public class Action{
        String type
        String name
        
        public Action(def type, def name){
            this.type = type
            this.name = name
        }
        
        public String getType(){
            return SfdcWorkflowType.convert(this.type)
        }
    }
}
