package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static final String title=" Guru99 Bank Manager HomePage ";
    WebDriver localDriver;

    public LoginPage(WebDriver remoteDriver){
        localDriver=remoteDriver;
        PageFactory.initElements(localDriver,this);
    }

    //Used @FindBy()
    @FindBy(name="uid")
    WebElement txtUserName;

    @FindBy(name="password")
    WebElement txtPassword;

    @FindBy(name = "btnLogin")
    WebElement loginButton;

    @FindBy(linkText = "Log out")
    WebElement logoutButton;

    //Action methods
    public void setUsername(String name){
        txtUserName.sendKeys(name);
    }

    public void setTxtPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void clickLogOutButton(){ logoutButton.click();}


}
