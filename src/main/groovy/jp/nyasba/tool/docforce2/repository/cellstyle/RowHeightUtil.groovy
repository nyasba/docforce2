package jp.nyasba.tool.docforce2.repository.cellstyle

class RowHeightUtil {
    
    def static float optimizedValue(String value){
        return value?.readLines()?.size() * 18 ?: -1 // 行数×18pt
    }
}
