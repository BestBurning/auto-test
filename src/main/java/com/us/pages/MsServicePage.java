package com.us.pages;

import com.us.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MsServicePage extends Page {

	@FindBy(how = How.NAME, using = "serviceName")
	@CacheLookup
	private WebElement serviceName;

	@FindBy(how = How.NAME, using = "url")
	@CacheLookup
	private WebElement url ;

	@FindBy(how = How.NAME, using = "serviceInterface")
	@CacheLookup
	private WebElement serviceInterface ;

	@FindBy(how = How.NAME, using = "systemName")
	@CacheLookup
	private WebElement systemName ;

	@FindBy(how = How.NAME, using = "systemEgName")
	@CacheLookup
	private WebElement systemEgName ;

	@FindBy(how = How.NAME, using = "systemDescription")
	@CacheLookup
	private WebElement systemDescription ;

	@FindBy(how = How.NAME, using = "owner")
	@CacheLookup
	private WebElement owner ;

	@FindBy(how = How.NAME, using = "phone")
	@CacheLookup
	private WebElement phone ;

	@FindBy(how = How.NAME, using = "email")
	@CacheLookup
	private WebElement email;

	public MsServicePage(WebDriver webDriver) {
		super(webDriver);
	}

	public WebElement getServiceName() {
		return serviceName;
	}

	public void setServiceName(WebElement serviceName) {
		this.serviceName = serviceName;
	}

	public WebElement getUrl() {
		return url;
	}

	public void setUrl(WebElement url) {
		this.url = url;
	}

	public WebElement getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(WebElement serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public WebElement getSystemName() {
		return systemName;
	}

	public void setSystemName(WebElement systemName) {
		this.systemName = systemName;
	}

	public WebElement getSystemEgName() {
		return systemEgName;
	}

	public void setSystemEgName(WebElement systemEgName) {
		this.systemEgName = systemEgName;
	}

	public WebElement getSystemDescription() {
		return systemDescription;
	}

	public void setSystemDescription(WebElement systemDescription) {
		this.systemDescription = systemDescription;
	}

	public WebElement getOwner() {
		return owner;
	}

	public void setOwner(WebElement owner) {
		this.owner = owner;
	}

	public WebElement getPhone() {
		return phone;
	}

	public void setPhone(WebElement phone) {
		this.phone = phone;
	}

	public WebElement getEmail() {
		return email;
	}

	public void setEmail(WebElement email) {
		this.email = email;
	}
}