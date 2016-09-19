package com.us.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class LoginPage extends Page {

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(how = How.TAG_NAME, using = "input")
    @CacheLookup
    private WebElement username;

    @FindBy(how = How.TAG_NAME, using = "input")
    @CacheLookup
    private WebElement password;


    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }
}
