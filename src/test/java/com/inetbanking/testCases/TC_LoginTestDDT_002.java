package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;


public class TC_LoginTestDDT_002 extends BaseClass{

    @Test(dataProvider="LoginData")
    public void loginDDT(String username,String password){
        logger.info("THIS IS TEST CASE @ STARTED");
        System.out.println("Test data username=" +username + "and password "+password);

        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        lp.setTxtPassword(password);
        lp.clickLoginButton();

        if(isAlertPresent()==true){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(true);
            logger.warn("This test should be fail");

        }
        else{
            lp.clickLogOutButton();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertEquals(driver.getTitle(),"Guru99 Bank Home Page");
            logger.info("This test case should pass");
        }

    }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        }catch(NoAlertPresentException e){
            return false;
        }
    }

    @DataProvider(name="LoginData")
    Object[][] getData() throws IOException {

        String xlPath=".//src//test//java//com//inetbanking//testData//inetTestData.xlsx";

        int rownum= XLUtils.getRowCount(xlPath,"Sheet1");
        int cellnum= XLUtils.getCellCount(xlPath,"Sheet1",1);
        String loginDataArray[][]= new String[rownum][cellnum];

        for(int i=1;i<=rownum;i++){
            for(int j=0;j<cellnum;j++){
                loginDataArray[i-1][j]=XLUtils.getCellData(xlPath,"Sheet1",i,j);
            }
        }
        return loginDataArray;
    }

}
