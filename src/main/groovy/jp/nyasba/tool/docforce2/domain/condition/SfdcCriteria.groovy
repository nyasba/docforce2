package jp.nyasba.tool.docforce2.domain.condition

import java.util.regex.Matcher

/**
 * メタデータに含まれるCriteriaを扱うドメインクラス
 *
 *  formula(数式) or criteriaItems(複数判定項目)を持つXMLノードを指定すること
 */
class SfdcCriteria {
    
    public static String getCriteria(def xml){
        
        // 数式の場合
        if(xml.formula != ""){
            return xml.formula as String
        }
        
        // 項目指定の場合
        List<String> conditionList = xml.criteriaItems.collect {
            "${it?.field} ${SfdcOperation.convert(it?.operation)} ${it?.value}"
        }
        
        if(xml.booleanFilter == ""){
            return conditionList.join("\nAND ")
        }
        
        String booleanFilter = xml.booleanFilter
    
        def regex = /^([^0-9]*)${(1..conditionList.size()).join("([^0-9]*)")}([^0-9]*)$/
        Matcher matcher = ( booleanFilter =~ regex )
        
        // matcherは正規表現でヒットした配列を返すため、matcher[0]は1件目にヒットした文字列が含まれる
        // matcher[0][0] はヒットした全行、matcher[0][1]からは()内の文字列が含まれる
        
        if(matcher.size() == 0 || matcher[0].size() <= conditionList.size()){
            return conditionList.join("\n")
        }
        
        List booleanFilterList = (matcher[0] as List).subList(1, matcher[0].size())
        
        def result = (0..conditionList.size()).collect {
            if(it != conditionList.size()){
                booleanFilterList.get(it) + conditionList.get(it) + "\n"
            }
            else {
                booleanFilterList.get(it)
            }
        }.join("")
        
        return result
        
    }
    
}
