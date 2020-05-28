package com.inetbanking.testCases;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_AddCustomerTest_003 extends BaseClass {

    @Test
    public void addNewCustomer() throws InterruptedException {
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(userName);
        lp.setTxtPassword(password);
        lp.clickLoginButton();

        Thread.sleep(3000);

        AddCustomerPage acp = new AddCustomerPage(driver);
        acp.clickAddNewCx();
        acp.setCxName("Kivindu");
        acp.clickFRadioBtn("female");
        acp.setDateOfBirth("29","04","1990");
        acp.setAddress("Jandy Road");
        acp.setCity("Kurunegala");
        acp.setState("Sri Lanka");
        acp.setPinNo("111111");
        acp.setTelPhone(randomNumber());
        acp.setEmailID(randomString()+"@gmail.com");
        acp.setPasswordElm("123Test123");
        acp.clickCxSubmitButton();

        Thread.sleep(3000);

        Assert.assertTrue(driver.getPageSource().contains("Customer Registered Successfully!!!"));

        }
    public String randomString(){
        String rString = RandomStringUtils.randomAlphabetic(8);
        return rString;
    }
    public String randomNumber() {
        String rNumber = String.valueOf(RandomStringUtils.randomNumeric(4));
        return rNumber;
    }
}
