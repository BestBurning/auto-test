package com.us.pages.mytest;

import com.us.util.PropertyLoader;
import com.us.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class Main{
    private static String defaultFilePath = "/file.jpg";

    private static String filePath = PropertyLoader.loadProperty("usms.service.publish.filepath");

    public static void main(String[] args) throws IOException, InterruptedException {
//        if(StringUtils.isBlank(filePath)){
//            filePath = defaultFilePath;
//        }
//        System.out.println(Main.class.getResource(filePath).getFile());
//        File realFile = new File(Main.class.getResource(filePath).getFile());
//        System.out.println(realFile.getAbsoluteFile().toPath());
//        System.out.println(PropertyLoader.loadProperty("usms.service.publish.systemDescription1"));
        String chromeBinary = new File(WebDriverFactory.class.getResource("/drivers/chrome/chromedriver").getFile()).getAbsolutePath();

        Runtime.getRuntime().exec("chmod +x /Users/Bruce/gitrepo/auto-test/target/classes/drivers/chrome/*");
        System.out.println(chromeBinary);
        System.setProperty("webdriver.chrome.driver", chromeBinary);

        WebDriver webDriver = new ChromeDriver();
    }


}
