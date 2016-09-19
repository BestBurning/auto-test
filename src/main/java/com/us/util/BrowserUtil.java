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
        String sysOS = System.getProperties().getProperty("os.name");
        CharSequence newTabKeys = Keys.COMMAND + "t";
        if ("Mac OS X".startsWith(sysOS)){

        }else if ("Linux".startsWith(sysOS)){
            newTabKeys = Keys.CONTROL + "t";
        }else if ("Windows".startsWith(sysOS)){
            newTabKeys = Keys.CONTROL + "t";
        }else {
            throw new RuntimeException("不支持的系统类型");
        }
        webDriver.findElement(By.cssSelector("body")).sendKeys(newTabKeys);
        webDriver.navigate().to(newPath);
    }

}
