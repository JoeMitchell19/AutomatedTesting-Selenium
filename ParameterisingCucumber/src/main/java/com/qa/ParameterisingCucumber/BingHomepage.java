package com.qa.ParameterisingCucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingHomepage {
	@FindBy(xpath = "//*[@id=\"sb_form_q\"]")
	private WebElement searchbar;
	@FindBy(xpath = "//*[@id=\"sb_form_go\"]")
	private WebElement button;
	
	public void enterterms(String a) {
		searchbar.sendKeys(a);
		
	}
	public WebElement pressbutton() {
		return button;
	}

}
