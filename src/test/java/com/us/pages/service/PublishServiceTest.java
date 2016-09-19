package com.us.pages.service;

import com.us.model.MsService;
import com.us.pages.HomePage;
import com.us.pages.LoginPageTest;
import com.us.pages.MsServicePage;
import com.us.util.BrowserUtil;
import com.us.util.PropertyLoader;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author Bruce
 * @date 16/9/18
 */
public class PublishServiceTest extends LoginPageTest{

    private final String baseProperties = "usms.service.publish.";

    private final String publishPath = "/#/main/add_service/0/"+System.currentTimeMillis()+"?isActive=0";

    private final String auditPath = "/#/main/checking_service_list";

    private final String defaultFilePath = "/file.jpg";

    private String filePath = PropertyLoader.loadProperty("usms.service.publish.filepath");

    private void publishService(MsService msService) throws InterruptedException {
        BrowserUtil.jumpAnotherPage(webDriver,websiteUrl+publishPath);
        Thread.sleep(200);
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
        if(StringUtils.isBlank(filePath)){
            filePath = defaultFilePath;
            filePath = PublishServiceTest.class.getResource(filePath).getFile();
        }
        System.out.println(filePath);
        file.sendKeys(filePath);

        Thread.sleep(1000);
        WebElement upload = webDriver.findElement(new By.ByXPath("//button[@class='btn btn-success btn-xs']"));
        upload.click();
        Thread.sleep(2000);
        WebElement save = webDriver.findElement(new By.ByXPath("//input[@type='submit']"));
        save.click();

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

    @Test
    public void auditService(MsService msService,boolean option,String auditText) throws InterruptedException {
        BrowserUtil.jumpAnotherPage(webDriver,websiteUrl+auditPath);
        Thread.sleep(200);
        List<WebElement> serviceNames = webDriver.findElements(By.xpath("//tr/td[2]"));
        List<WebElement> options = webDriver.findElements(By.xpath("//tr/td[8]/a"));
        for (int i = 0; i < serviceNames.size(); i++) {
            if (serviceNames.get(i).getText().equals(msService.getServiceName())){
                options.get(i).click();
                break;
            }
        }
        Thread.sleep(200);
        WebElement text = webDriver.findElement(By.xpath("//textarea"));
        text.sendKeys(auditText);
        Thread.sleep(1000);
        WebElement optionEle = null;
        if (option){
            //同意
            optionEle = webDriver.findElement(By.xpath("//input[@value='同意']"));
        }else {
            //拒绝
            optionEle = webDriver.findElement(By.xpath("//input[@value='拒绝']"));
        }
        optionEle.click();
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(webDriver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
        Thread.sleep(2000);
    }

    @Test
    public void publishServices() throws InterruptedException {
        Properties props = PropertyLoader.getProperties();
        Set<Object> keys = props.keySet();
        int num = 1;
        while (keys.contains(baseProperties+"serviceName"+num)){
            MsService msService = new MsService();

            msService.setServiceName(PropertyLoader.loadProperty(baseProperties+"serviceName"+num,"serviceName"));
            msService.setUrl(PropertyLoader.loadProperty(baseProperties+"url"+num,"url"));
            msService.setServiceInterface(PropertyLoader.loadProperty(baseProperties+"serviceInterface"+num,"serviceInterface"));
            msService.setSystemName(PropertyLoader.loadProperty(baseProperties+"systemName"+num,"systemName"));
            msService.setSystemEgName(PropertyLoader.loadProperty(baseProperties+"systemEgName"+num,"systemEgName"));
            msService.setSystemDescription(PropertyLoader.loadProperty(baseProperties+"systemDescription"+num,"systemDescription"));
            msService.setOwner(PropertyLoader.loadProperty(baseProperties+"owner"+num,"owner"));
            msService.setPhone(PropertyLoader.loadProperty(baseProperties+"phone"+num,"1111111111"));
            msService.setEmail(PropertyLoader.loadProperty(baseProperties+"email"+num,"email@email.com"));

            publishService(msService);
            auditService(msService,true,"同意");
            num++;
        }
        Assert.assertTrue(true);
    }

}
