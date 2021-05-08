package com.libraries.pages;

import com.libraries.Reporter.Reporter;
import com.libraries.WebUI.Core;
import com.libraries.WebUI.Uiutilities;
import com.libraries.readers.DataReader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser extends Core {

    public void openBrowser() throws IOException {

        String url = DataReader.getFieldValue("url");
        //System.out.println(url);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Uiutilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Uiutilities.IMPLICIT_WAIT, TimeUnit.SECONDS);
        Reporter.report("info","Browser launched",2,true);

    }

    public void closeBrowser() throws InterruptedException {
        Thread.sleep(500);
        driver.close();
        Reporter.report("info","Browser closed",2,false);
    }

}
