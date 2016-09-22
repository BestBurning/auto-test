package com.us.runtest.base;

import com.us.util.PropertyLoader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;

/**
 * Bruce
 */
@Component
public abstract class BaseTest {
	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	protected String websiteUrl;

	@Autowired
	protected WebDriver webDriver;

	public BaseTest() {
		websiteUrl = PropertyLoader.loadProperty("site.url");
	}

	public abstract void init() throws InterruptedException;

	@PreDestroy
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

  public void setScreenshot(ITestResult result) {
    if (!result.isSuccess()) {
      try {
        WebDriver returned = new Augmenter().augment(webDriver);
        if (returned != null) {
          File f = ((TakesScreenshot) returned).getScreenshotAs(OutputType.FILE);
          try {
            FileUtils.copyFile(f,
                new File(SCREENSHOT_FOLDER + result.getName() + SCREENSHOT_FORMAT));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      } catch (ScreenshotException se) {
        se.printStackTrace();
      }
    }
  }
}
