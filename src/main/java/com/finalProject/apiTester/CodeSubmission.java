package com.finalProject.apiTester;

public class CodeSubmission {
    private String code;
    private String tests;


    @Override
    public String toString() {
        return code + "\n\n\n\n" + tests;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    // Getters, setters, and maybe constructors
}
