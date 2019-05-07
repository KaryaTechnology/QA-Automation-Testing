package seleniumgluecode;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import library.DemoInterface;
import library.WebLibrary;

public class test implements DemoInterface {

	public static WebDriver driver;
	public static String strBasepath;

	public static WebLibrary webLibaray;

	File file;

	@Given("^user is  on homepage \"([^\"]*)\"$")
	public void user_is_on_homepage(String URL) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		file = new File(".");
		try {
			System.out.println(file.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", "D:/Automation/Driver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(URL);
		} catch (Throwable t) {
			t.printStackTrace();
		}
		throw new PendingException();
	}

	@When("^user navigates to Login Page$")
	public void user_navigates_to_Login_Page() throws Throwable {
		// driver.findElement(By.linkText("Sign in")).click();
		System.out.println("Url launched");
	}

	@When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_username_and_Password(String Username, String Password) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.id("Username")).sendKeys(Username);
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).sendKeys(Password);
		Thread.sleep(2000);
		driver.findElement(By.id("submit")).click();
		WebDriverWait loadmypage = new WebDriverWait(driver, 1000);
		loadmypage.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[text()='11:00 pm']")));
	}

	@Then("^success message is displayed$")
	public void success_message_is_displayed() throws Throwable {
		String exp_message = "Hauser, Eve (My Page)";

		String actual = driver.findElement(By.xpath("//span[text()='Hauser, Eve (My Page)']")).getText();
		Assert.assertEquals(exp_message, actual);
		Thread.sleep(4000);

		/* Multi consumer session creation */
		driver.findElement(By.xpath("//*[@id='buttons_buttonGroup']/div/div[1]/div[1]/div[2]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("buttons_buttonGroup_undefinedNew_Service_Session_Multi_Consumers")).click();
		WebDriverWait loadmypage3 = new WebDriverWait(driver, 1000);
		loadmypage3.until(ExpectedConditions.elementToBeClickable(By.id("consumerSearch")));
		Thread.sleep(4000);
		driver.findElement(By.id("fakeEntityPeopleGroupFk_relationship")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id='fakeEntityPeopleGroupFk_element']/div[2]/div[1]/table/tbody/tr[1]/td[1]/span"))
				.click();
		loadmypage3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='consumer343']")));
		Thread.sleep(2000);
		driver.findElement(By.id("consumerSearch")).sendKeys("Anderson, Timothy");
		loadmypage3.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='consumer238']")));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id='consumer238']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='params.id']/div[2]")).click();
		loadmypage3.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='service-entry']/aside/div[2]/button[2]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='service-entry']/aside/div[2]/button[2]")).click();
		loadmypage3.until(ExpectedConditions.elementToBeClickable(By.id("mileage_attribute")));
		Thread.sleep(2000);
		driver.findElement(By.id("serviceSessionServiceLocationFk_relationship")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"//*[@id='serviceSessionServiceLocationFk_element']/div[2]/div[1]/table/tbody/tr[1]/td[1]/span"));
		Thread.sleep(2000);
		driver.findElement(By.id("serviceSessionServiceFk_relationship")).click();
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("//*[@id='serviceSessionServiceFk_element']/div[2]/div[1]/table/tbody/tr[1]/td[1]/span"));
		Thread.sleep(2000);
		// driver.findElement(By.xpath("//*[@id='delivery_serviceSession_startAt_element']/div/div/div/div/div/div")).click();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String StartTime = dateFormat.format(date).toString();
		driver.findElement(By.id("startAt_time_attribute")).sendKeys(StartTime);
		Calendar Cal = Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(Calendar.HOUR, 1);
		String EndTime = dateFormat.format(Cal.getTime()).toString();
		driver.findElement(By.id("endAt_time_attribute")).sendKeys(EndTime);
		driver.findElement(By.id("//*[@id='service-entry']/div/div[2]/button[2]"));
		loadmypage3.until(ExpectedConditions.elementToBeClickable(By.id("//*[@id='service-entry-start-at']")));
	}

	@When("^user navigates to Consumer Page$")
	public void user_navigates_to_Consumer_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//*[@id=\'TopMenu_Consumers\']/span")).click();
		WebDriverWait loadmypage2 = new WebDriverWait(driver, 1000);
		loadmypage2.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[3]/main/article/div/table/tbody/tr[20]/td[2]")));
		Thread.sleep(2000);
		driver.findElement(By.id("List_New")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("lastName_attribute")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("lastName_attribute")).sendKeys("TestQA");
		Thread.sleep(2000);
		driver.findElement(By.id("firstName_attribute")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("firstName_attribute")).sendKeys("Automation");
		Thread.sleep(2000);
		driver.findElement(By.id("Actions_Save")).click();
		loadmypage2.until(ExpectedConditions
				.elementToBeClickable(By.xpath("/html/body/div[3]/main/article/div/table/tbody/tr[20]/td[2]")));
		Thread.sleep(2000);
		throw new PendingException();
	}

}
