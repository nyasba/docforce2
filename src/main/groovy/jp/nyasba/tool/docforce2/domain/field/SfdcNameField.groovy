package jp.nyasba.tool.docforce2.domain.field

import groovy.util.slurpersupport.GPathResult

/**
 * 名前フィールド
 */
class SfdcNameField implements SfdcField{

    SfdcField field

    def SfdcNameField(GPathResult fieldXml){
        this.field = SfdcCustomFieldFactory.create(fieldXml)
    }

    /*
     * API参照名をName固定、必須項目とし、
     * その他はカスタム項目と同じロジックで判断する
     */

    @Override
    def String ラベル(){
        return this.field.ラベル()
    }

    @Override
    String API参照名() {
        return "Name"
    }

    @Override
    String タイプ() {
        return this.field.タイプ()
    }

    @Override
    String length() {
        return this.field.length()
    }

    @Override
    String 説明() {
        return this.field.説明()
    }

    @Override
    String デフォルト値or選択リスト値() {
        return this.field.デフォルト値or選択リスト値()
    }

    @Override
    String 必須() {
        return  "○"
    }

    @Override
    String 外部ID() {
        return this.field.外部ID()
    }

    @Override
    String 数式() {
        return this.field.数式()
    }

    @Override
    String ヘルプテキスト() {
        return this.field.ヘルプテキスト()
    }
}
