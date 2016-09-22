package com.us.runtest;

import com.us.pages.HomePage;
import com.us.runtest.base.BaseTest;
import org.openqa.selenium.support.PageFactory;

import javax.annotation.PostConstruct;

public class HomePageTest extends BaseTest {

	private HomePage homepage;

	public HomePageTest() {

	}

	@PostConstruct
	public void init(){
		// Load the page in the browser
		webDriver.get(websiteUrl + "/");
		homepage = PageFactory.initElements(webDriver, HomePage.class);
	}

	public void testH1Existing() throws InterruptedException {
		System.out.println(homepage.getH1());
	}


}
