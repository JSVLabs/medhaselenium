import com.libraries.Reporter.Reporter;
import com.libraries.WebUI.Core;
import com.libraries.readers.ConfigReader;
import com.libraries.readers.ExcelReader;
import com.libraries.readers.JsonReader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class ExecutionDriver extends Core {
   /* public ExecutionDriver(){
        super();
    }*/

    public void startTest(String[] argss) throws IOException {
        ExcelReader xlReader = new ExcelReader();
        JsonReader jsonReader = new JsonReader();
       //String[] argss = {"create_company", "update_company",  "QA", "Chrome"};
        //String[] argss = {"add_company","modify_company","QA","chrome"};
        String environment = argss[argss.length-2];
        String browser = argss[argss.length-1];
        //System.out.println(browser);
       // System.out.println(argss.length);
        Reporter.initiateReport(environment);
        //for(String test : argss) {
          for(int ex =0;ex<argss.length-2;ex++){
            //String[] testAttributes = test.split(":");
            //String testName = testAttributes[0];
            //String environment = testAttributes[1];
            String testName = argss[ex];


            //String path = "C:\\gitsource\\test-input\\"+ testName+".xlsx";
            //Map<Integer, Map<String,String>> testData = xlReader.loadData(path,environment);

            //String[] businessProcesses = xlReader.loadBusinessProcess(path,"BusinessProcess");
            //String[] pages = xlReader.loadPages(path,"BusinessProcess");
              String[] businessProcesses = jsonReader.getTestElements(testName,"action");
              String[] pages = jsonReader.getTestElements(testName,"page");
            int bpCount = businessProcesses.length;
            //int dataCount = xlReader.getRowCount(path,environment);
              int dataCount = jsonReader.getIterationCount(testName);

            //System.out.println("Args:"+test);
            //DataReader.setCurrentTest(testName);
            ConfigReader.setCurrentTest(testName);
            ConfigReader.setEnvironment(environment);
            //DataReader.setEnvironment(environment);
            //ConfigReader.setTestData(xlReader.loadData(path,environment));
            //Core.initialize("chrome");
            Reporter.createTest(testName);
            for (int j=0;j<dataCount;j++){
                //DataReader.setCurrentRow(j+1);
                ConfigReader.setCurrentRow(j);
               // Core.initialize("chrome");
                Core.initialize(browser);
                Reporter.createLevel1Node("Iteration : "+ ConfigReader.getCurrentRow()+1);
                for (int i=0;i<bpCount;i++){
                    Reporter.createLevel2Node(businessProcesses[i]);
                    ConfigReader.setCurrentPage(pages[i]);
                    ConfigReader.setCurrentAction(businessProcesses[i]);
                    executeAction(pages[i],businessProcesses[i]);

                }

            }

        }
        Reporter.closeReport();
        //Core.driver.quit();

    }

    private static  void executeAction(String className, String actionName) {
        Class[] noparams = {};
        try{
            //load the AppTest at runtime
            String fullClassPath = "com.libraries.pages."+className;
            Class cls = Class.forName(fullClassPath);
            Object obj = cls.newInstance();
          //System.out.println(actionName.trim());
            //call the printIt method
            Method method = cls.getDeclaredMethod(actionName, noparams);
            method.invoke(obj, null);
        }catch(Exception ex) {

            System.out.println("Exception occured while executing the action"+className + actionName+"error: "+ex.getMessage());

        }
    }

}
