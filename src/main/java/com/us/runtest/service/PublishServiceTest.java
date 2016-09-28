package com.us.runtest.service;

import com.us.exception.PublishServiceException;
import com.us.model.MsService;
import com.us.pages.MsServicePage;
import com.us.runtest.base.LoginPageTest;
import com.us.util.ExcelReader;
import com.us.util.PropertyLoader;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.io.File;
import java.util.*;

/**
 * @author Bruce
 * @date 16/9/18
 */
@Component
public class PublishServiceTest extends LoginPageTest {

    private final String baseProperties = "usms.service.publish.";

    private final String publishPath = "/#/main/add_service/0/" + System.currentTimeMillis() + "?isActive=0";

    private final String auditPath = "/#/main/checking_service_list";

    private final String defaultFilePath = "/cmb-demo-api-1.0.jar";

    private String filePath = PropertyLoader.loadProperty("usms.service.publish.filepath");

    private void publishService(MsService msService) throws InterruptedException, PublishServiceException {
        webDriver.navigate().to(websiteUrl + publishPath);
        Thread.sleep(2000);
        MsServicePage msServicePage = PageFactory.initElements(webDriver, MsServicePage.class);

        msServicePage.getServiceName().sendKeys(msService.getServiceName());
        msServicePage.getUrl().sendKeys(msService.getUrl());
        msServicePage.getServiceInterface().sendKeys(msService.getServiceInterface());
        msServicePage.getSystemName().sendKeys(msService.getSystemName());
        msServicePage.getSystemEgName().sendKeys(msService.getSystemEgName());
        msServicePage.getSystemDescription().sendKeys(msService.getSystemDescription());
        msServicePage.getOwner().sendKeys(msService.getOwner());
        msServicePage.getPhone().sendKeys(msService.getPhone());
        msServicePage.getEmail().sendKeys(msService.getEmail());

        //logo
        WebElement logo = webDriver.findElement(new By.ByClassName("service-logo-list-img0"));
        logo.click();
        Thread.sleep(1000);
        WebElement realLogo = webDriver.findElement(new By.ByXPath("//button[1]"));
        realLogo.click();

        Thread.sleep(1000);
        //上传文件
        WebElement file = webDriver.findElement(new By.ByXPath("//input[@type='file']"));
        if (StringUtils.isBlank(filePath)) {
            filePath = defaultFilePath;
            filePath = new File(PublishServiceTest.class.getResource(filePath).getFile()).getAbsolutePath();
        }
        file.sendKeys(filePath);

        Thread.sleep(1000);
        WebElement upload = webDriver.findElement(new By.ByXPath("//button[@class='btn btn-success btn-xs']"));
        upload.click();
        Thread.sleep(3000);
        WebElement save = webDriver.findElement(new By.ByXPath("//input[@type='submit']"));
        save.click();
        Thread.sleep(2000);
        if (isAlertPresent(webDriver)) {
            //存在重名
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
            throw new PublishServiceException("存在重名服务");
        }else {
            //继续
            Thread.sleep(1000);
            WebElement deleteMethod = webDriver.findElement(new By.ByXPath("(//a[@class='btn btn-default'])[2]"));
            deleteMethod.click();
            Thread.sleep(1000);
            WebElement publish = webDriver.findElement(new By.ByXPath("//input[@type='submit' and @value='发布']"));
            publish.click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void auditService(MsService msService, boolean option, String auditText) throws InterruptedException {
        webDriver.navigate().to(websiteUrl + auditPath);
        Thread.sleep(2000);

        List<WebElement> serviceNames = webDriver.findElements(By.xpath("//tr/td[2]"));
        List<WebElement> options = webDriver.findElements(By.xpath("//tr/td[8]/a"));
        for (int i = 0; i < serviceNames.size(); i++) {
            if (serviceNames.get(i).getText().equals(msService.getServiceName())) {
                options.get(i).click();
                break;
            }
        }
        Thread.sleep(1000);
        WebElement text = webDriver.findElement(By.xpath("//textarea"));
        text.sendKeys(auditText);
        Thread.sleep(1000);
        WebElement optionEle = null;
        if (option) {
            //同意
            optionEle = webDriver.findElement(By.xpath("//input[@value='同意']"));
        } else {
            //拒绝
            optionEle = webDriver.findElement(By.xpath("//input[@value='拒绝']"));
        }
        optionEle.click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, 3);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        Thread.sleep(2000);
    }

    public void publishServices() throws InterruptedException {
        String file = new File(this.getClass().getResource("/publishServices.xlsx").getFile()).getAbsolutePath();
        List<Map<String, String>> list = ExcelReader.readExcelContent(file);
        Map<String, String> map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);

            MsService msService = new MsService();

            msService.setServiceName(map.getOrDefault("serviceName", "serviceName"));
            msService.setUrl(map.getOrDefault("url", "url"));
            msService.setServiceInterface(map.getOrDefault("serviceInterface", "serviceInterface"));
            msService.setSystemName(map.getOrDefault("systemName", "systemName"));
            msService.setSystemEgName(map.getOrDefault("systemEgName", "systemEgName"));
            msService.setSystemDescription(map.getOrDefault("systemDescription", "systemDescription"));
            msService.setOwner(map.getOrDefault("owner", "owner"));
            msService.setPhone(map.getOrDefault("phone", "1111111111"));
            msService.setEmail(map.getOrDefault("email", "email@email.com"));

            try {
                publishService(msService);
                auditService(msService, true, "同意");
            } catch (PublishServiceException e) {
                e.printStackTrace();
            }


            System.out.println("............");
        }
    }

    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }

    }
}