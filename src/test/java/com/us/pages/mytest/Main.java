package com.us.pages.mytest;

import com.us.util.PropertyLoader;
import com.us.webdriver.WebDriverFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class Main{
//    private static String defaultFilePath = "/file.jpg";
    private static String defaultFilePath = "/publishServices.xlsx";

//    private static String filePath = PropertyLoader.loadProperty("usms.service.publish.filepath");

    public static void main(String[] args) throws IOException, InterruptedException, InvalidFormatException {
//        if(StringUtils.isBlank(filePath)){
//            filePath = defaultFilePath;
//        }
//        System.out.println(Main.class.getResource(filePath).getFile());
//        File realFile = new File(Main.class.getResource(filePath).getFile());
//        System.out.println(Main.class.getResource(defaultFilePath).getPath());
//        System.out.println(Main.class.getResource(defaultFilePath).getRef());
//        System.out.println(Main.class.getResource(defaultFilePath).getFile());
//        Workbook workbook = new XSSFWorkbook(Main.class.getResource(defaultFilePath).getFile());
//        Runtime.getRuntime().exec("chmod 777 "+Main.class.getResource(defaultFilePath).getFile());
//        Workbook workbook = WorkbookFactory.create(new File(Main.class.getResource(defaultFilePath).getFile()));
//
// System.out.println(PropertyLoader.loadProperty("usms.service.publish.systemDescription1"));
//        String chromeBinary = new File(WebDriverFactory.class.getResource("/drivers/chrome/chromedriver").getFile()).getAbsolutePath();
//
//        Runtime.getRuntime().exec("chmod +x /Users/Bruce/gitrepo/auto-test/target/classes/drivers/chrome/*");
//        System.out.println(chromeBinary);
//        System.setProperty("webdriver.chrome.driver", chromeBinary);
//
//        WebDriver webDriver = new ChromeDriver();
        Set<String> toEmails = new HashSet<>();
        toEmails.add("asdadad");
        String[] strings = toEmails.toArray(new String[]{});
        for (String s:strings) {
            System.out.println(s);
        }


    }


}
