package org.example.laba_13.Repository.dbconstants;

public enum UnivClassTableConstants {
    ID("ID"),
    NAME("PEOPLE"),
    DAY("PEOPLE_COUNTRY"),
    HOURS("PEOPLE_COUNT");
    private String fieldName;
    private UnivClassTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }

}
