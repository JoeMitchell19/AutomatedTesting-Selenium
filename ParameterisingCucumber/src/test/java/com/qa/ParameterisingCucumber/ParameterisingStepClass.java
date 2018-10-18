package com.qa.ParameterisingCucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParameterisingStepClass {
	WebDriver driver = null;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Given("^I go to \"([^\"]*)\" website$")
	public void i_go_to_website(String arg1) throws Throwable {
	   driver.get(arg1);
	}

	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String arg1) throws Throwable {
		BingHomepage b = PageFactory.initElements(driver, BingHomepage.class);
		b.enterterms(arg1);
		b.pressbutton();
		Actions a = new Actions(driver);
		a.moveToElement(b.pressbutton()).click().perform();
	}

	@Then("^I am taken to a list of data for that search$")
	public void i_am_taken_to_a_list_of_data_for_that_search() throws Throwable {
		Thread.sleep(1000);
	}


}
