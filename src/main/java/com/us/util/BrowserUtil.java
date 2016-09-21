package com.us.util;

import org.omg.SendingContext.RunTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class BrowserUtil {

    public static void jumpAnotherPage(WebDriver webDriver,String newPath){
        webDriver.navigate().to(newPath);
    }

}
