package com.libraries.Reporter;

import com.aventstack.extentreports.*;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.utils.FileUtil;
import com.libraries.WebUI.Core;
import com.libraries.readers.ConfigReader;
//import org.apache.commons.compress.archivers.zip.ExtraFieldUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Reporter {
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent;
    static ExtentTest test;
    static ExtentTest level1;
    static ExtentTest level2;

    public static void initiateReport(String environment) {
        // specify location of the report
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long v = timestamp.getTime();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/AutomationReport_"+v+".html");

        htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
        htmlReporter.config().setReportName("Functional Testing"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Passing General information
        //extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environemnt", environment);
        extent.setSystemInfo("user", "murali");
    }

    public static void closeReport(){
        extent.flush();
//        Core.driver.quit();
    }

    public static void createTest(String testName){
        Reporter.test = extent.createTest(testName);
    }

    public static void createLevel1Node(String nodeName){
        //if(Reporter.level1 == null) {
            Reporter.level1 = Reporter.test.createNode(nodeName);
        //}
    }
    public static void createLevel2Node(String nodeName){
        //if(Reporter.level2 ==null) {
            Reporter.level2 = Reporter.level1.createNode(nodeName);
        //}
    }

    public static void clearNodes(int level){
        if(level == 1){
            Reporter.level1 = null;
        }else if(level ==2){
            Reporter.level2 =null;
        }else if(level ==0){
            Reporter.test = null;
        }
    }

    public static void report(String status,String message, int level, boolean capturescreen)  {


        try {
        if(status.equalsIgnoreCase("fail")){
            if(level==0){
                Reporter.test.log(Status.FAIL,message);
            }else if(level == 1){
                Reporter.level1.fail(message);
            }else if(level ==2){
               //tmp = captureImage(message);
                if(capturescreen == true) {
                    String tmp = captureImage(message);
                    Reporter.level2.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(tmp).build());
                }else{
                    Reporter.level2.fail(message);
                }

            }
        }else if(status.equalsIgnoreCase("pass")){
            if(level==0){
                Reporter.test.log(Status.PASS,message);
            }else if(level == 1){
                Reporter.level1.pass(message);

            }else if(level ==2){
               // tmp = captureImage(message);
                if(capturescreen == true) {
                    String tmp = captureImage(message);
                    Reporter.level2.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(tmp).build());
                }else{
                    Reporter.level2.pass(message);
                }
            }
        }else if(status.equalsIgnoreCase("info")){
            if(level==0){
                Reporter.test.log(Status.INFO,message);
            }else if(level == 1){
                Reporter.level1.info(message);
            }else if(level ==2){
                //tmp = captureImage(message);
                if(capturescreen == true) {
                    String tmp = captureImage(message);
                    Reporter.level2.info(message, MediaEntityBuilder.createScreenCaptureFromPath(tmp).build());
                }else{
                    Reporter.level2.info(message);
                }
            }

        }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void assertContains(String element, String message, String expected, int level, boolean captureimage){
        if(element.toString().contains(expected)){
            Reporter.report("pass",message+" Expected - "+expected,level,captureimage);
        }else{
            Reporter.report("fail",message+" Expected - "+expected,level,captureimage);
        }
    }

    public static void assertElementDisplay(WebElement element, String message, String expected, int level, boolean captureimage){
        boolean disp = element.isDisplayed();
        String display = Boolean.toString(disp);
        if (expected != ""){
            if (display.equalsIgnoreCase(expected)){
                Reporter.report("pass",message+" Expected - "+expected,level,captureimage);
            }else{
                Reporter.report("fail",message+" Expected - "+expected,level,captureimage);
            }
        }
       /* if(element.isDisplayed() == expected){
            Reporter.report("pass",message+" Expected - "+expected,level,captureimage);
        }else{
            Reporter.report("fail",message+" Expected - "+expected,level,captureimage);
        }*/
    }


   private static String captureImage(String imagename)  {
        //File sourceFile = ((TakesScreenshot) Core.driver).getScreenshotAs(OutputType.FILE);
        //String impgPath = "./test-output/screenshots/"+imagename+".png";
       Calendar c = GregorianCalendar.getInstance();
       //String v = String.format("%1$tm%1$te%1$tY", c);
       long v;
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }

       TakesScreenshot ts=(TakesScreenshot) Core.driver;

       File src=ts.getScreenshotAs(OutputType.FILE);
       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       v = timestamp.getTime();
       String path="screenshots/"+imagename+v+".png";
       // String path =   "C:\\gitsource\\test-output\\screenshots\\"+imagename+"_"+v+".png";
       File destination=new File(path);

       try
       {
           FileUtils.copyFile(src, destination);
       } catch (IOException e)
       {
           System.out.println("Capture Failed "+e.getMessage());
       }
       return path;
    }

}
