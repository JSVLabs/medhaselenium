package com.libraries.readers;

import java.util.Map;

public  class ConfigReader {

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        ConfigReader.environment = environment;
    }

    public static int getCurrentRow() {
        return currentRow;
    }

    public static void setCurrentRow(int currentRow) {
        ConfigReader.currentRow = currentRow;
    }

    public static String getCurrentTest() {
        return currentTest;
    }

    public static void setCurrentTest(String currentTest) {
        ConfigReader.currentTest = currentTest;
    }

    public static Map<Integer, Map<String, String>> getTestData() {
        return testData;
    }

    public static void setTestData(Map<Integer, Map<String, String>> testData) {
        ConfigReader.testData = testData;
    }
    public static Map<Integer, Map<String, String>> getTmpData() {
        return tmpData;
    }

    public static void setTmpData(Map<Integer, Map<String, String>> tmpData) {
        ConfigReader.tmpData = tmpData;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        ConfigReader.browser = browser;
    }


    public static String getCurrentPage() {
        return currentPage;
    }

    public static void setCurrentPage(String currentPage) {
        ConfigReader.currentPage = currentPage;
    }

    public static String getCurrentAction() {
        return currentAction;
    }

    public static void setCurrentAction(String currentAction) {
        ConfigReader.currentAction = currentAction;
    }

    static String currentPage;
    static String currentAction;
    static String environment;
    static int currentRow;
    static String currentTest;
    static Map<Integer, Map<String,String>> testData;
    static Map<Integer, Map<String,String>> tmpData;
    static String browser;


}
