package com.us.runtest.base;

import com.us.util.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Bruce
 * @date 16/9/18
 */
//@Component
public class LoginPageTest extends BaseTest {

    @PostConstruct
    public void init() throws InterruptedException {
        // Load the page in the browser
        webDriver.get(websiteUrl + "/#/login");
        List<WebElement> input = webDriver.findElements(new By.ByTagName("input"));
        input.get(0).sendKeys(PropertyLoader.loadProperty("usms.user.username","admin"));
        input.get(1).sendKeys(PropertyLoader.loadProperty("usms.user.password","888888"));
        input.get(2).click();
        Thread.sleep(2000);
    }

}
