package com.us.pages;

import com.us.util.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class LoginPageTest extends TestBase {

    LoginPage loginPage;

    @Parameters({ "path" })
    @BeforeClass
    public void testInit(@Optional("/#/login") String path) throws InterruptedException {

        // Load the page in the browser
        webDriver.get(websiteUrl + path);
        List<WebElement> input = webDriver.findElements(new By.ByTagName("input"));
        input.get(0).sendKeys(PropertyLoader.loadProperty("usms.user.username","admin"));
        input.get(1).sendKeys(PropertyLoader.loadProperty("usms.user.password","888888"));
        input.get(2).click();
        Thread.sleep(1000);
    }

}
