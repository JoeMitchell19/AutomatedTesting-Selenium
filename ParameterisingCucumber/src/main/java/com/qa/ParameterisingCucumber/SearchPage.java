package com.qa.ParameterisingCucumber;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage {
	@FindBy(xpath = "//*[@id=\"sb_form_q\"]")
	private WebElement searchedbar;
	
	public WebElement checkterms() {
		return searchedbar;
		
	}

}
