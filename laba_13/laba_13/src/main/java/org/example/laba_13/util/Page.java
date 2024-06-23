package org.example.laba_13.util;

public enum Page {
    LOGIN_PAGE("/Login.jsp"),
    REGISTER_PAGE("/Registration.jsp"),
    WELCOME_PAGE ("/Welcome.jsp"),
    ERROR_PAGE ("/errorPage.jsp");
    private final String value;
    Page(String value) {
        this.value = value;
    }
    public String getPage() {
        return value;
    }

}
