package com.qa.CucumberTesting;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage {
	@FindBy(xpath = "//*[@id=\"wsb-element-00000000-0000-0000-0000-000452010925\"]/div/div/form/fieldset[1]/legend")
	private WebElement customerInfo;
	
	public WebElement getcustomerInfo() {
		return customerInfo;
	}

}
