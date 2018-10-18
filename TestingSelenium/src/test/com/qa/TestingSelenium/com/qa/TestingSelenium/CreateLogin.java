package com.qa.TestingSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateLogin {
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
	@Test
	public void test1() {
		driver.get("http://thedemosite.co.uk/addauser.php");
		WebElement box = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]"));
		box.sendKeys("jm1");
		
		WebElement box2 = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[1]/p/font/small"));
		box2.sendKeys("workplz");
	}

}
