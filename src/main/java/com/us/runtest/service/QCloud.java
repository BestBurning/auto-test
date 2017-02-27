package com.us.runtest.service;


import com.us.runtest.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Bruce
 * @date 2016-11-03
 */
@Component
@Scope("prototype")
public class QCloud extends BaseTest implements Runnable {

    public void init() throws InterruptedException {

    }

    @Override
    public void run() {
        webDriver.get(websiteUrl);
        webDriver.switchTo().frame(webDriver.findElement(By.id("login_frame")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.id("u")).sendKeys("591178118");
        webDriver.findElement(By.id("p")).sendKeys("19961229ql");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.findElement(By.id("login_button")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webDriver.switchTo()
                .defaultContent()
                .navigate()
                .to("https://www.qcloud.com/act/campus");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean t = true;
        while (t){
            WebElement button = webDriver.findElement(By.xpath("(//button[@type='button'])[2]"));
            if (button.isEnabled()){
                button.click();
            }else {
                webDriver.navigate().refresh();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        webDriver.close();
    }
}
