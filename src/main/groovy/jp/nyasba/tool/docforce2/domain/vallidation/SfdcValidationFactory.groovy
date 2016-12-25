package jp.nyasba.tool.docforce2.domain.vallidation

import groovy.util.slurpersupport.GPathResult
import jp.nyasba.tool.docforce2.domain.field.*

class SfdcValidationFactory {

    public static SfdcValidation create(GPathResult fieldXml){
        return new SfdcValidation(fieldXml);
    }
}
