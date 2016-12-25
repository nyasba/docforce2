package jp.nyasba.tool.docforce2.domain.field

interface SfdcField {

    def String ラベル()
    def String API参照名()
    def String タイプ()
    def String length()
    def String デフォルト値or選択リスト値()
    def String 数式()
    def String ヘルプテキスト()
    def String 必須()
    def String 外部ID()
    def String 説明()

}