package com.inetbanking.testCases;

import com.beust.jcommander.Parameter;
import com.inetbanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;


public class BaseClass {

    ReadConfig readConfig = new ReadConfig();

    public final String baseURL=readConfig.getApplicationURL();
    public final String userName=readConfig.getUserName();
    public final String password=readConfig.getPassword();
    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser){
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("log4j.properties");

        if(browser.equals("chrome")) {
            // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", readConfig.getChromeDriverPath());
            driver = new ChromeDriver();
        }

        else if(browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", readConfig.getIEDriverPath());
            driver = new EdgeDriver();
        }

        else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver",readConfig.getFirfoxDriverPath());
            driver= new FirefoxDriver();
        }
        else{
            logger.info("Browser parameter in testng.xml is not matched.");
        }
        driver.manage().window().maximize();
        driver.get(baseURL);
        logger.info("URL is opened");


    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

//    public void captureScreen(String tname)throws IOException {
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        File target= new File(System.getProperty("user.dir")+"//Screenshots//"+tname+".png");
//        FileUtils.copyFile(source,target);
//        System.out.println("Screenshot taken");
//
//    }
}
