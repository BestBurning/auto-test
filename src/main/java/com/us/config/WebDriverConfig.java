package com.us.config;

import com.us.util.Browser;
import com.us.util.PropertyLoader;
import com.us.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Bruce
 * @date 16/9/21
 */
@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver getWebDriver(){
        String websiteUrl = PropertyLoader.loadProperty("site.url");
        String gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

        Browser browser = new Browser();
        browser.setName(PropertyLoader.loadProperty("browser.name"));
        browser.setVersion(PropertyLoader.loadProperty("browser.version"));
        browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

        String username = PropertyLoader.loadProperty("user.username");
        String password = PropertyLoader.loadProperty("user.password");

        WebDriver webDriver = WebDriverFactory.getInstance(gridHubUrl, browser, username,
                password);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return webDriver;
    }
}
