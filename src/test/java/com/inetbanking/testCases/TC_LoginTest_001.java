package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_LoginTest_001 extends BaseClass {

    @Test
    public void loginTest(){
        LoginPage lp= new LoginPage(driver);
        lp.setUsername(userName);
        logger.info("Entered username");
        lp.setTxtPassword(password);
        logger.info("Entered the password");
        lp.clickLoginButton();

        Assert.assertEquals(driver.getTitle(),lp.title.trim());
    }

}
