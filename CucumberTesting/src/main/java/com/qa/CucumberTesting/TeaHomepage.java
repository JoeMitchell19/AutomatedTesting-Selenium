package com.qa.CucumberTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TeaHomepage {
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000450914886\"]/div/h1")
	private WebElement title;
	@FindBy(xpath = "//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[3]/a")
	private WebElement menubutton;
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000453230000\"]/div/p/span/span/strong")
	private WebElement menutext;
	@FindBy(xpath = "//*[@id=\"wsb-nav-00000000-0000-0000-0000-000450914915\"]/ul/li[5]/a")
	private WebElement button;
	
	
	public WebElement getTitle() {
		return title;
}
	public WebElement getMenu() {
		return menubutton;
	}
	public WebElement getButton() {
		return button;
	}
}

