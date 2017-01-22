package jp.nyasba.tool.docforce2.domain.field

import groovy.util.slurpersupport.GPathResult

class SfdcCustomFieldFactory {
    
    public static SfdcField create(GPathResult fieldXml) {
        
        switch (fieldXml.type) {
            case "AutoNumber": return new SfdcAutoNumberField(fieldXml)
            case "Checkbox": return new SfdcCheckboxField(fieldXml)
            case "Currency": return new SfdcCurrencyField(fieldXml)
            case "Date":
            case "DateTime": return new SfdcDateTimeField(fieldXml)
            case "Lookup" : return new SfdcLookupField(fieldXml)
            case "Number": return new SfdcNumberField(fieldXml)
            case "Phone": return new SfdcPhoneField(fieldXml)
            case "Picklist":
            case "MultiselectPicklist": return new SfdcPicklistField(fieldXml)
            case "TextArea": return new SfdcLongTextAreaField(fieldXml)
            case "LongTextArea": return new SfdcTextAreaField(fieldXml)
            case "Text": return new SfdcTextField(fieldXml)
        }
        return new SfdcNotDefinedField(fieldXml)
    }
    
}
