package jp.nyasba.tool.docforce2.domain.operator

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
            case "greaterThan" : return ">"
        }
        return ""
    }
    
}
