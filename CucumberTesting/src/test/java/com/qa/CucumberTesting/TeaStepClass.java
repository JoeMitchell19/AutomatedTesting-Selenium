package com.qa.CucumberTesting;

import static org.junit.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TeaStepClass {
	WebDriver driver = null;
	public static ExtentReports report;
	public static ExtentTest test;
	int counter = 1;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
		driver = new ChromeDriver();
		report = new ExtentReports("C:\\Users\\Admin\\Desktop\\Joe's Reporting\\cucumbertest.html",true);
		
	}
	
	@After
	public void teardown() {
		driver.quit();
	}

	@Given("^the correct web address$")
	
	public void the_correct_web_address() throws Throwable{
		if(counter == 1) {
		test = report.startTest("Test 1 - Checking the list of available products");
		}
		else if (counter == 2) {
			test = report.startTest("Test 2 - Checking the payment page");
		}
		else {
			report.flush();
		}
		test.log(LogStatus.INFO, "Checking homepage Web Address");
		driver.get("http://www.practiceselenium.com/welcome.html");
		String address = driver.getCurrentUrl();
		assertEquals("http://www.practiceselenium.com/welcome.html", address);
		if(address == "http://www.practiceselenium.com/welcome.html") {
			test.log(LogStatus.PASS, "Correct homepage Web Address");
		}
		else {
			test.log(LogStatus.FAIL, "Inorrect homepage Web Address");
		}
		
	}

	@When("^I navigate to the 'Menu' page$")
	public void i_navigate_to_the_Menu_page() throws Throwable {
		test.log(LogStatus.INFO, "Checking menu navigation and functionality");
		TeaHomepage t = PageFactory.initElements(driver, TeaHomepage.class);
		t.getMenu();
		Actions a = new Actions(driver);
		a.moveToElement(t.getMenu()).click().perform();
		String URLnew = driver.getCurrentUrl();
		assertEquals("http://www.practiceselenium.com/menu.html", URLnew);
		if(URLnew == "http://www.practiceselenium.com/menu.html") {
			test.log(LogStatus.PASS, "Correct menu Web Address and button functionality");
		}
		else {
			test.log(LogStatus.FAIL, "incorrect menu Web Address and button functionality");
		}
		
	}

	@Then("^I can browse a list of the available products\\.$")
	public void i_can_browse_a_list_of_the_available_products() throws Throwable {
		test.log(LogStatus.INFO, "List of available products");
		MenuPage m = PageFactory.initElements(driver, MenuPage.class);
		m.getmenuText();
		assertEquals("Green Tea", m.getmenuText().getText());
		if(m.getmenuText().getText().equals("Green Tea")) {
			test.log(LogStatus.PASS, "List of available products seen");
			report.endTest(test);
			
		}
		else {
			test.log(LogStatus.FAIL, "List of available products not seen");
		}
		counter++;
	}

	@When("^I click the checkout button$")
	public void i_click_the_checkout_button() throws Throwable {
		test.log(LogStatus.INFO, "Checking button functionality");
		TeaHomepage t = PageFactory.initElements(driver, TeaHomepage.class);
		t.getButton();
		Actions a = new Actions(driver);
		a.moveToElement(t.getButton()).click().perform();
		String URLnew = driver.getCurrentUrl();
		assertEquals("http://www.practiceselenium.com/check-out.html", URLnew);
		if(URLnew == "http://www.practiceselenium.com/check-out.html" ) {
			test.log(LogStatus.PASS, "Correct Checkout Web Address");
		}
		else {
			test.log(LogStatus.FAIL, "Correct Checkout Web Address");
		}
		
		
	}

	@Then("^I am taken to the checkout page$")
	public void i_am_taken_to_the_checkout_page() throws Throwable {
		test.log(LogStatus.INFO, "Checking payment page");
		CheckoutPage c = PageFactory.initElements(driver, CheckoutPage.class);
		c.getcustomerInfo();
		String label = c.getcustomerInfo().getText();
		assertEquals("Customer Info",label);
		if(label == "Customer Info") {
			test.log(LogStatus.PASS,"Payment details can be entered");
			report.endTest(test);
		}
		else {
			test.log(LogStatus.FAIL,"Payment details cannot be entered");
			report.endTest(test);
		}
		counter++;
	}

}
