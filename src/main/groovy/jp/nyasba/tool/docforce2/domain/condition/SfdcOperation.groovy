package jp.nyasba.tool.docforce2.domain.condition

/**
 * メタデータに含まれるOperationを扱うドメインクラス
 */
class SfdcOperation {
    
    /**
     * operation (ex. equals) をドキュメント出力用の文字列(ex. = )に変換する
     *
     * @param operation salesforceのopration
     * @return ドキュメント出力用の文字列
     */
    public static String convert(def operation){
        switch (operation as String){
            case "equals" : return "="
            case "notEqual" : return "!="
            case "greaterThan" : return ">"
            case "greaterOrEqual" : return ">="
            case "lessThan" : return "<"
            case "lessOrEqual" : return "<="
        }
        return operation as String
    }
    
}
