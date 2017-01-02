package jp.nyasba.tool.docforce2.domain.folder

/**
 * メタデータに含まれるFolderを扱うドメインクラス
 */
class SfdcFolder {
    
    /**
     * SFDC標準フォルダのメタデータ中の文字列(英語)をドキュメント出力用の日本語に変換する
     *
     * @param s メタデータ中の文字列
     * @return ドキュメント出力用の日本語
     */
    public static String convert(String s){
        return s.replace('unfiled$public/', "未整理公開フォルダ/")
    }
    
}
