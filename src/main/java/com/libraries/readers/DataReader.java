package com.libraries.readers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataReader {

    static String fieldValue = "";

    public static String getTestName() {
        return testName;
    }

    public static void setTestName(String testName) {
        DataReader.testName = testName;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static void setEnvironment(String environment) {
        DataReader.environment = environment;
    }

    static String testName;
    static String environment;

    public static int getCurrentRow() {
        return currentRow;
    }

    public static void setCurrentRow(int currentRow) {
        DataReader.currentRow = currentRow;
    }

    public static int currentRow;

    public static String getCurrentTest() {
        return currentTest;
    }

    public static void setCurrentTest(String currentTest) {
        DataReader.currentTest = currentTest;
    }

    public static String currentTest;

    public static void setFieldValue(String fieldValue) {
        fieldValue = fieldValue;
    }

   /* public static String getFieldValues(String fieldName) throws IOException {
        String fieldValue = "";
        ExcelReader xlReader = new ExcelReader();
        String testName = getCurrentTest();
        String env = getEnvironment();
        //String path = "C:\\gitsource\\test-input\\login.xlsx";
        String path = "C:\\gitsource\\test-input\\"+testName+".xlsx";
        Map<Integer, Map<String,String>> testData  = xlReader.loadData(path,env);
        int cRow = getCurrentRow();
        fieldValue= testData.get(cRow).get(fieldName);
        return fieldValue;

    }*/

    public static String getFieldValue(String fieldName) throws IOException {
        JsonReader jsonReader = new JsonReader();

        String fieldValue = "";
        String testName = ConfigReader.getCurrentTest();
        String env = ConfigReader.getEnvironment();
        int iteration = ConfigReader.getCurrentRow();

        String pageName = ConfigReader.getCurrentPage();
        String actionName = ConfigReader.getCurrentAction();
        String f = "";
        if(fieldName.startsWith("temp")){
            f = ConfigReader.getTmpData().get(iteration).get(fieldName);

        }else{
            f = jsonReader.getDataElementValue(testName,env,iteration,fieldName);
        }


        if (f==null){
            fieldValue = "";
        }else if(f.equalsIgnoreCase("No element found")){
            fieldValue = "";
        }
        else if(f.contains("{{")){
            f = f.substring(2,f.length()-2);
            fieldValue = ConfigReader.getTmpData().get(iteration).get(f);
        }else{
                fieldValue= f;
        }

        /*Map<Integer, Map<String,String>> testData = ConfigReader.getTestData();
        int cRow = ConfigReader.getCurrentRow();


        String f = testData.get(cRow).get(fieldName);
        if(f==null){
            fieldValue="";
        }else if(f.contains("<<")){
            f = f.substring(2,f.length()-2);
            fieldValue = ConfigReader.getTmpData().get(cRow).get(f);
        }else{
            testData  = ConfigReader.getTestData();
            fieldValue= testData.get(cRow).get(fieldName);
        }*/
        if(fieldValue == null){
            fieldValue = "";
        }
        return fieldValue;
    }

    public static void setFieldValue(String fieldName, String fieldValue){
        int cRow = ConfigReader.getCurrentRow();
       /* if(fieldName.startsWith("temp")){
            if (ConfigReader.tempData.get(cRow)==null){
                ConfigReader.tempData.put(cRow).put(fieldName,fieldValue);
            }
        }*/

        Map<Integer, Map<String,String>> settingData = new HashMap<Integer, Map<String, String>>();
        if(fieldName.startsWith("temp")){
            if(ConfigReader.getTmpData()==null){
                Map<String,String> val = new HashMap<String,String>();
                val.put(fieldName,fieldValue);
                settingData.put(cRow,val);
            } else {
                settingData = ConfigReader.getTmpData();
                if(settingData.get(cRow) == null){
                    Map<String,String> val = new HashMap<String,String>();
                    val.put(fieldName,fieldValue);
                    settingData.put(cRow,val);
                }else{
                    settingData.get(cRow).put(fieldName,fieldValue);
                }

            }
            //System.out.println(fieldValue);
            ConfigReader.setTmpData(settingData);


        }else {
           /* settingData = ConfigReader.getTestData();
            settingData.get(cRow).put(fieldName, fieldValue);
            ConfigReader.setTestData(settingData);*/
        }

    }





}
