package org.example.laba_13.Repository.dbconstants;

public enum UserTableConstants {
    ID("ID"),
    LOGIN("USER_LOG"),
    PASSWORD("USER_PAS"),
    ROLE("USER_ROL");
    private String fieldName;
    private UserTableConstants(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }

}
