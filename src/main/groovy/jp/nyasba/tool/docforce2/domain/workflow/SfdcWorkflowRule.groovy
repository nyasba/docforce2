package jp.nyasba.tool.docforce2.domain.workflow

import groovy.util.slurpersupport.NodeChild
import groovy.util.slurpersupport.NodeChildren
import jp.nyasba.tool.docforce2.domain.operator.SfdcOperation

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
        this.評価条件 = conditionToString(xml.formula, xml.criteriaItems)
        this.トリガータイプ = convertTriggerType(xml.triggerType)
        this.アクションリスト = xml.actions.collect{ new Action(it.type, it.name) }
    }
    
    private String conditionToString(def formula, NodeChildren criteriaItems){

        // 数式の場合は数式をそのまま返却する
        if (formula != null){
            return formula
        }
        
        // 評価項目指定の場合 TODO 複数時のAND/ORなどへの対応が未
        return criteriaItems.collect{ NodeChild item ->
            "${criteriaField(item)} ${criteriaOperation(item)} ${criteriaValue(item)}"
        }.join("\n")
    }
    
    private String criteriaField(NodeChild item){
        if(item.field.text().contains(".")){
            return item.field.text().tokenize(".").get(1) // オブジェクト名を除外し、フィールド名の未返却
        }
        return item.field.text()
    }
    
    private String criteriaOperation(NodeChild item){
        return SfdcOperation.convert(item.operation.text())
    }
    
    private String criteriaValue(NodeChild item){
        return item.value.text() + item.valueField.text() // どちらか一方になるはずなのでとりあえずつなげる
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
    }
}
