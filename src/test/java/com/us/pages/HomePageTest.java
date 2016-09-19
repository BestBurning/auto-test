package com.us.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

	HomePage homepage;

	LoginPage loginPage;

	@Parameters({ "path" })
//	@BeforeClass
	public void testInit(@Optional("/") String path) {

		// Load the page in the browser
		webDriver.get(websiteUrl + path);
		homepage = PageFactory.initElements(webDriver, HomePage.class);
	}

	@Test
	public void testH1Existing() throws InterruptedException {
		Assert.assertTrue(homepage.getH1() != null);
	}

	@Test
	public void test2() throws InterruptedException {
		Assert.assertTrue(true);
	}

	@Test
	public void login(@Optional("/#/login") String path) throws InterruptedException {
		webDriver.get(websiteUrl+path);
		loginPage = PageFactory.initElements(webDriver, LoginPage.class);
		System.out.println(loginPage.getUsername());
		System.out.println(loginPage.getPassword());

		Assert.assertTrue(true);
	}
}
