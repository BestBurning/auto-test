package com.us.pages.mytest;

import com.us.pages.LoginPageTest;
import org.testng.annotations.Test;

/**
 * @author Bruce
 * @date 16/9/21
 */
public class MyTest extends LoginPageTest{

    @Test
    public void testUrlJump() throws InterruptedException {
        webDriver.navigate().to("https://www.baidu.com");
        Thread.sleep(3000);
    }

}
