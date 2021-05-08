package com.libraries.pages;

import com.libraries.Reporter.Reporter;
import com.libraries.WebUI.Core;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Navigation extends Core {
    public Navigation(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//a[@href='/home']")
    WebElement home;
    @FindBy(xpath="//a[@href='/calendar']")
    WebElement calendar;
    @FindBy(xpath="//a[@href='/contacts']")
    WebElement contacts;
    @FindBy(xpath="//a[@href='/companies']")
    WebElement companies;
    @FindBy(xpath="//a[@href='/deals']")
    WebElement deals;
    @FindBy(xpath="//a[@href='/tasks']")
    WebElement tasks;
    @FindBy(xpath = "//div[@id='dashboard-toolbar']")
    WebElement toolBar;
    @FindBy(xpath = "//a[@name='help']")
    WebElement help;


    public void navigateToCompanies() throws InterruptedException {
        Thread.sleep(100);
        companies.click();
        Thread.sleep(1000);
        Reporter.report("info","Clicked on Companies from left navigation pane",2,false);
        Reporter.assertContains(toolBar.getText(),"Validate navigation to Companies page ","Companies",2,true);

    }
    public void navigateToDeals(){
        deals.click();
        Reporter.report("info","Clicked on Deals from left navigation pane",2,false);
        Reporter.assertContains(toolBar.getText(),"Validate navigation to Deals page ","Deals",2,true);

    }
    public void navigateToHome(){
        home.click();
        Reporter.report("info","Clicked on Home from left navigation pane",2,false);
        Reporter.assertElementDisplay(help,"Validate Home page is displayed ","true",2,true);
    }
    public void navigateToCalendar(){
        calendar.click();
        Reporter.report("info","Clicked on Calendar from left navigation pane",2,false);
        Reporter.assertContains(toolBar.getText(),"Validate navigation to Calendar page ","Calendar",2,true);
    }
    public void navigateToContacts(){
        contacts.click();
        Reporter.report("info","Clicked on Contacts from left navigation pane",2,false);
        Reporter.assertContains(toolBar.getText(),"Validate navigation to Contacts page ","Contacts",2,true);
    }
}
