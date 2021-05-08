package com.libraries.pages;

import com.libraries.Reporter.Reporter;
import com.libraries.WebUI.Core;
import com.libraries.readers.DataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Companies extends Core {
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String companyName;
    @FindBy(xpath = "//a[@href ='/companies/new']")
    WebElement btnNew;

    @FindBy(xpath = "//input[@name='name' and @placeholder='']")
    WebElement txtName;

    @FindBy(xpath = "//input[@name ='address']")
    WebElement txtAddress;

    @FindBy(xpath = "//input[@name ='city']")
    WebElement txtCity;

    @FindBy(xpath = "//input[@name ='state']")
    WebElement txtState;

    @FindBy(xpath = "//input[@name ='zip']")
    WebElement txtZip;

    @FindBy(xpath = "//input[@class='search']")
    WebElement slctCountry;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement btnSave;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement btnCancel;

    @FindBy(xpath = "//input[@name='value' and @placeholder = 'Number']")
    WebElement txtPhone;
    @FindBy(xpath = "//div[@class='actions']/button[2]")
    WebElement btnPopupDelete;
    public Companies(){
        PageFactory.initElements(driver,this);
    }

    public void clickNew() throws InterruptedException {
        Thread.sleep(500);
        btnNew.click();
        Thread.sleep(500);
        Reporter.report("info","Clicked New button",2,false);

        Reporter.assertElementDisplay(txtName,"Validate Create company page is displayed ","true",2,true);
    }

    public void enterDemographicInfo() throws IOException, InterruptedException {
        String companyname = DataReader.getFieldValue("companyname");
        String streetaddress = DataReader.getFieldValue("streetaddress");
        String city = DataReader.getFieldValue("city");
        String zipcode = DataReader.getFieldValue("zipcode");
        //int zip = Integer.parseInt(zipcode);
        String country = DataReader.getFieldValue("country");
        String phone = DataReader.getFieldValue("phone");
        //long ph = Integer.parseInt(phone);
        //this.setCompanyName(companyname);
        txtName.sendKeys(companyname);
        txtAddress.click();
        Thread.sleep(500);
        txtAddress.clear();
        Thread.sleep(500);
       // System.out.println(streetaddress);
        txtAddress.sendKeys(streetaddress);
        txtCity.sendKeys(city);
        //System.out.println(zipcode);
        txtZip.sendKeys(zipcode);
        slctCountry.sendKeys(country);
        txtPhone.sendKeys(phone);
        Reporter.report("info","Demographic information entered",2,true);
    }

    public void clickSave(){
        btnSave.click();
        Reporter.report("info","Save button clicked",2,false);
     }

    public void clickCancel(){
        btnCancel.click();
        Reporter.report("info","Cancel button clicked",2,false);
    }

    public void storeCompanyName() throws IOException {
        DataReader.setFieldValue("tempCompanyName",DataReader.getFieldValue("companyname"));
        String company = DataReader.getFieldValue("companyname");
        //System.out.println("company name:"+company);
        DataReader.setFieldValue("tempCompanyName",company);
        //System.out.println("In temp: "+DataReader.getFieldValue("tempCompanyName"));
    }

    public void validateCompanyInfo() throws IOException {

        String companyname = DataReader.getFieldValue("companyname");
        //String companyname = getCompanyName();
        //String companyToDisplay = DataReader.getFieldValue("companyisdisplayed");
        String companyToDisplay = "true";
        String xpath = "//p[contains(text(),'"+companyname+"')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        Reporter.assertElementDisplay(element,"Validate company name is displayed",companyToDisplay,2,true);
    }

    public void clickEditCompany() throws IOException {
        String companyName = DataReader.getFieldValue("companyname_search");
        //System.out.println(companyName);
        String xpath = "//td[contains(text(),'"+companyName+"')]/following-sibling::td[2]/a[2]";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        Reporter.report("info","Clicked Edit on "+companyName,2,false);
        //td[contains(text(),'first company')]/following-sibling::td[2]/a[2]
    }

    public void clickDeleteCompany() throws IOException {
        String companyName = DataReader.getFieldValue("companyname_search");
        String xpath = "//td[contains(text(),'"+companyName+"')]/following-sibling::td[2]/div/button";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        Reporter.report("info","Clicked Delete on "+companyName,2,false);
    }

    public void clickDeleteInDeletePopup() throws InterruptedException {
        Thread.sleep(500);

        btnPopupDelete.click();
        Thread.sleep(500);
        Reporter.report("info","Click Delete button in popup",2,true);
    }



}
