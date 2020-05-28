package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class ReadConfig {

    Properties pro;

    public ReadConfig(){
        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis= new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        }catch(Exception e){
            System.out.println("Error is "+ e.getMessage());
        }
    }

    public String getApplicationURL(){
        return pro.getProperty("baseURL");
    }
    public String getUserName(){
        return pro.getProperty("userName");
    }

    public String getPassword()
    {
        return pro.getProperty("password");
    }

    public String getChromeDriverPath()
    {
        return pro.getProperty("chromeDriverPath");
    }

    public String getIEDriverPath(){
        return pro.getProperty("ieDriverPath");
    }
    public String getFirfoxDriverPath(){
        return pro.getProperty("firfoxDriverpath");
    }

}
