package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

    WebDriver localDriver;

    public AddCustomerPage(WebDriver remoteDriver){
        localDriver=remoteDriver;
        PageFactory.initElements(localDriver,this);
    }

    //used findby with HOW function
    @FindBy(how = How.LINK_TEXT, using ="New Customer")
    WebElement linkAddNewCx;

    @FindBy(how =How.NAME, using ="name")
    WebElement cxName;

    @FindBy(how =How.XPATH,using="//input[@name='rad1 and @value='f']")
    WebElement fRadioBtn;

    @FindBy(how =How.NAME, using="dob")
    WebElement DateOfbirth;

    @FindBy(how = How.NAME,using="addr")
    WebElement address;

    @FindBy(how = How.NAME,using="city")
    WebElement city;

    @FindBy(how = How.NAME,using="state")
    WebElement state;

    @FindBy(how=How.NAME, using="pinno")
    WebElement pinNo;

    @FindBy(how=How.NAME,using="telephoneno")
    WebElement telPhone;

    @FindBy(how = How.NAME,using="emailid")
    WebElement emailID;

    @FindBy(how = How.NAME ,using="password")
    WebElement passwordElm;

    @FindBy(how =How.NAME,using ="sub")
    WebElement submitButton;

    public void clickCxSubmitButton(){
        submitButton.click();
    }

    public void setPasswordElm(String passwordText){
        passwordElm.sendKeys(passwordText);
    }


    public void setEmailID(String emailIDText){
        emailID.sendKeys(emailIDText);
    }

    public void setTelPhone(String telPhoneText){
        telPhone.sendKeys(telPhoneText);
    }

    public void setPinNo(String pinNoText){
        pinNo.sendKeys(pinNoText);
    }

    public void setState(String stateText){
        state.sendKeys(stateText);
    }

    public void setCity(String cityText){
        city.sendKeys(cityText);
    }

    public void setAddress(String addressText){
        address.sendKeys(addressText);
    }

    public void setDateOfBirth(String dd,String mm ,String yyyy){
        DateOfbirth.sendKeys(dd);
        DateOfbirth.sendKeys(mm);
        DateOfbirth.sendKeys(yyyy);

    }

    public void clickFRadioBtn(String gender){
        if(gender.equalsIgnoreCase("male"))
        fRadioBtn.click();
        else
            System.out.println("LOLLA");
           //fRadioBtn.click();
    }

    public void setCxName(String cxNameText){
        cxName.sendKeys(cxNameText);
    }

    public void clickAddNewCx(){
        linkAddNewCx.click();
    }


}
