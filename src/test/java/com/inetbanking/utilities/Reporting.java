package com.inetbanking.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

    public void onStart(ITestContext testContext){

        //created a timestamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        //Create a name for test report.
        String repName="Test-Report-"+timeStamp+".html";
        //Specify location
        htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "//Test_output//"+repName);
       // htmlReporter=new ExtentHtmlReporter("./Test-output/"+repName);
        htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"//extent-config.xml");

        extent = new ExtentReports();

        //attached the reporter we created
        extent.attachReporter(htmlReporter);
        //set properties key values to extent.
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("user","Prageeth");

        htmlReporter.config().setDocumentTitle("InetBanking Test Project");//Title of the report
        htmlReporter.config().setReportName("Functional test report");//Name of the report
        htmlReporter.config().setTheme(Theme.DARK); //select theme

    }

    public void onTestSuccess(ITestResult tr){
        //logger.extent.createTest(tr.getName());
        logger=extent.createTest(tr.getName()); // Create new entry in the report
        logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed information
    }

    public void onTestFailure(ITestResult tr){
        logger=extent.createTest(tr.getName()); //create new entry in the report
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));

// create base calss object and ccall capturing method.
//        BaseClass bs = new BaseClass();
//        try {
//            bs.captureScreen(tr.getName());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //craete name for screenthos
        String screenShotPath= System.getProperty("user.dir")+"//Screenshots//"+tr.getName()+".png";

        //Create setpes to capture a screenshot
        BaseClass bs = new BaseClass();
        TakesScreenshot ts = (TakesScreenshot) bs.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target= new File(System.getProperty("user.dir")+"//Screenshots//"+tr.getName()+".png");
        try {
            FileUtils.copyFile(source,target);
        }catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Screenshot taken");

        File f= new File(screenShotPath);

        if(f.exists()){
            try {
                logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenShotPath));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult tr){
        logger=extent.createTest(tr.getName()); //create new entry in the report
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    }

    public void onFinish(ITestContext testContext){
        extent.flush();
    }
}
