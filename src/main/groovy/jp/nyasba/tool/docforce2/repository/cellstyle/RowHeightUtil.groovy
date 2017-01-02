package jp.nyasba.tool.docforce2.repository.cellstyle

class RowHeightUtil {
    
    def static float optimizedValue(String value){
        return getValueFromLines(value?.readLines()?.size()) ?: -1 // 行数×18pt
    }
    
    def static float getValueFromLines(int lineNumber){
        return lineNumber * 18 // 行数×18pt
    }
}
