package com.libraries.pages;

        import com.libraries.Reporter.Reporter;
        import com.libraries.WebUI.Core;
        import com.libraries.WebUI.Uiutilities;
        import com.libraries.readers.DataReader;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;

        import javax.xml.crypto.Data;
        import java.io.IOException;
        import java.util.concurrent.TimeUnit;

public class Login extends Core {
    @FindBy(name="email")
    WebElement txtUsername;

    @FindBy(name="password")
    WebElement txtPassword;

    @FindBy(xpath = "//div[contains(text(),'Login')]")
    WebElement btnLogin;

    @FindBy(xpath = "//a[@name='help']")
    WebElement help;

    public Login(){
        PageFactory.initElements(driver,this);
      }

    public void enterLoginInfo()  {
        String username = null;
        String password="";
        try {
            username = DataReader.getFieldValue("username");
            password = DataReader.getFieldValue("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);

        Reporter.report("info","Login information entered",2,true);
    }


    public void clickLogin() {
        btnLogin.click();
        Reporter.report("info", "Login clicked", 2,false);

    }

    public void verifyLoginErrorMessage(){

    }

    public void verifyHomePageDisplay(){
        Reporter.assertElementDisplay(help,"Validate Home page is displayed ","true",2,true);
    }

    public void verifyThis(){
        
    }

    public void verifymaster(){

    }



}
