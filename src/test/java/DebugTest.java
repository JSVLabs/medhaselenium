import com.libraries.Reporter.Reporter;
import com.libraries.readers.JsonReader;

import java.util.HashMap;
import java.util.Map;

public class DebugTest {

    public static void main(String[] args) throws InterruptedException {
        JsonReader jsonreader = new JsonReader();
        String[] pages = jsonreader.getTestElements("sample","page");
         String s = jsonreader.getDataElementValue("sample","QA",1,"user");
         System.out.println(s);

        /*String reg = "<<tempCompany>>";
        reg = reg.substring(2,reg.length()-2);
        System.out.println(reg);


        System.out.println(reg);

        WebDriver driver;
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") +"/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ui.cogmento.com/");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Uiutilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Uiutilities.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.findElement(By.name("email")).sendKeys("murali.kunda@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Qasniper@1234");
        driver.findElement(By.xpath("//div[contains(text(),'Login')]")).click();

        driver.findElement(By.xpath ("//a[@href='/companies']")).click();
        Thread.sleep(300);
        //WebElement el = driver.findElement(By.xpath("//td[contains(text(),'first company')]"));
        ////td[contains(text(),'first company')]/preceding-sibling::td/div/input

        driver.findElement(By.xpath("//a[@href ='/companies/new']")).click();

        driver.findElement(By.xpath("//input[@name='name' and @placeholder='']")).sendKeys("abc");

        driver.findElement(By.xpath("//input[@name ='address']")).sendKeys("address");
        driver.findElement(By.xpath("//input[@name ='city']")).sendKeys("city");
        driver.findElement(By.xpath("//input[@name ='state']")).sendKeys("Florida");
        driver.findElement(By.xpath("//input[@name ='zip']")).sendKeys("32216");



        driver.findElement(By.xpath("//input[@class='search']")).sendKeys("United States");

        driver.findElement(By.xpath("//input[@name='value' and @placeholder = 'Number']")).sendKeys("9045555555");

        System.out.println("debug msg");
        Reporter.initiateReport("QA");
    for(int i =1;i<=4;i++){

        Reporter.createTest("First Test"+i);
        Reporter.createLevel1Node("firstnode");
        Reporter.createLevel2Node("secondnode");
            //Reporter.report("info","Iteration "+1,1,false);
        Reporter.report("info", "Reporting" + 1, 2, false);

        Reporter.createTest("Second Test"+i);
        Reporter.createLevel1Node("firstnode");
        Reporter.createLevel2Node("secondnode");
            //Reporter.report("info","Iteration "+1,1,false);
        Reporter.report("info", "Reporting" + 1, 2, false);

        }
        Reporter.closeReport();*/
    }
}
