import com.libraries.Reporter.Reporter;
import com.libraries.readers.ConfigReader;
import com.libraries.readers.DataReader;
import com.libraries.readers.ExcelReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ExecuteTest {

    public static void main(String[] args) throws IOException {
        ExecutionDriver executeTest = new ExecutionDriver();
        executeTest.startTest(args);


        /*ExcelReader xlReader = new ExcelReader();
        String path = "C:\\gitsource\\test-input\\login.xlsx";
        Map<Integer, Map<String,String>> testData = xlReader.loadData(path,"TestData");
        String[] businessProcesses = xlReader.loadBusinessProcess(path,"BusinessProcess");
        int bpCount = businessProcesses.length;
        int dataCount = testData.size();
        for(String str : args) {
            System.out.println("Args:"+str);

            for (int j=0;j<dataCount;j++){
                DataReader.setCurrentRow(j+1);
                for (int i=0;i<bpCount;i++){
                    //System.out.println(businessProcesses[i]);
                    executeAction("Login",businessProcesses[i]);

                }
            }
        }
        ExcelReader xlReader = new ExcelReader();
        String[] argss = {"Login:QA","Registration:QA"};
        //String[] argss = {"Registration:QA"};
        Reporter.initiateReport();
        for(String test : argss) {
            String[] testAttributes = test.split(":");
            String testName = testAttributes[0];
            String environment = testAttributes[1];
            String path = "C:\\gitsource\\test-input\\"+ testName+".xlsx";
            //Map<Integer, Map<String,String>> testData = xlReader.loadData(path,environment);
            String[] businessProcesses = xlReader.loadBusinessProcess(path,"BusinessProcess");
            String[] pages = xlReader.loadPages(path,"BusinessProcess");
            int bpCount = businessProcesses.length;
            int dataCount = xlReader.getRowCount(path,environment);

            //System.out.println("Args:"+test);
            //DataReader.setCurrentTest(testName);
            ConfigReader.setCurrentTest(testName);
            ConfigReader.setEnvironment(environment);
            //DataReader.setEnvironment(environment);
            ConfigReader.setTestData(xlReader.loadData(path,environment));
            Reporter.createTest(testName);
            for (int j=0;j<dataCount;j++){
                //DataReader.setCurrentRow(j+1);
                ConfigReader.setCurrentRow(j+1);
                Reporter.createLevel1Node("Iteration : "+ ConfigReader.getCurrentRow());
                for (int i=0;i<bpCount;i++){
                   Reporter.createLevel2Node(businessProcesses[i]);
                   executeAction(pages[i],businessProcesses[i]);

                }
            }
        }
        Reporter.closeReport();


    }

    private static  void executeAction(String className, String actionName) {
        Class[] noparams = {};
        try{
            //load the AppTest at runtime
            String fullClassPath = "com.libraries.pages."+className;
            Class cls = Class.forName(fullClassPath);
            Object obj = cls.newInstance();
            //call the printIt method
            Method method = cls.getDeclaredMethod(actionName, noparams);
            method.invoke(obj, null);
        }catch(Exception ex) {

            System.out.println("Exception occured while executing the action"+className + actionName+"error: "+ex.getMessage());

        }



    }*/
    }

}
