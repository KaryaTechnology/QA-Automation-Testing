package VertexSystem.SeleniumAutomation;

import library.LibraryInterface;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class AppTest implements LibraryInterface {

	public static LinkedHashMap<String, String> excelData;

	@BeforeClass
	public static void Login() throws InterruptedException, InvalidFormatException, IOException {
		excelData = FileLibrary.getExcelData("MasterExcel", "Sheet1", "1");

		_webLibInterface.launchBrowser(excelData.get("URL"));

		_webLibInterface.sendKeyToElement("Enter the value " + excelData.get("Username") + " in the field username",
				"id=Username", excelData.get("Username"));

		_webLibInterface.sendKeyToElement("Enter the value " + excelData.get("Password") + " in the field username",
				"id=Password", excelData.get("Password"));

		_webLibInterface.clickElement("Click on the button Login", "id=submit", "Click");

		_webLibInterface.waitForElementToBeClickable("xpath=//strong[contains(.,'11:00 pm')]");

	}

	@Test
	public void NavigateToConsumer() {
		_reportLibInterface.startTest("Navigate To Consumer");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("New Service Session dropdown",
				"xpath=//div[@title='New Service Session']/div/div[2]", "Click");

		_webLibInterface.clickElement("Multi Consumers from the dropdown",
				"id=buttons_buttonGroup_undefinedNew_Service_Session_Multi_Consumers", "Click");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.sendKeyToElement("People group dropdown", "id=fakeEntityPeopleGroupFk_relationship",
				"(All Consumers)");

		_webLibInterface.clickElement("(All Consumers) from the people group", "xpath=//span[text()='(All Consumers)']",
				"Click");

		_webLibInterface.waitForPageLoad();

		CreateMultiConsumer();
	}

	// @Test

	public void CreateMultiConsumer() {

		_reportLibInterface.startTest("CreateMulti Consumer");

		_webLibInterface.sendKeyToElement("Enter the consumer to be searched", "id=consumerSearch",
				excelData.get("ConsumerName").trim());

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("Consumer " + excelData.get("ConsumerName").trim() + " ",
				"xpath=//div[@class='consumer column ng-scope']/div[contains(.,'" + excelData.get("ConsumerName").trim()
						+ "')]",
				"click");

		_webLibInterface.clickElement("Next Button ", "xpath=//button[contains(.,'Next')]", "click");

		_webLibInterface.waitForPageLoad();

		if (_webLibInterface.elementDisplayed("xpath=//button[contains(.,'New session')]")) {
			_webLibInterface.clickElement("New session Button ", "xpath=//button[contains(.,'New session')]", "click");
		}

		_webLibInterface.waitForPageLoad();

		_webLibInterface.sendKeyToElement("Location", "id=serviceSessionServiceLocationFk_relationship",
				"1083880488 - Allendale House");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("1083880488 - Allendale House option ",
				"xpath=//span[text()='1083880488 - Allendale House']", "yes");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.sendKeyToElement("Activity", "id=serviceSessionServiceFk_relationship", "Work");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("Activity Work",
				"xpath=//*[@id='serviceSessionServiceFk_element']//span[text()='Work']", "click");

		_webLibInterface.waitForPageLoad();

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String StartTime = dateFormat.format(date).toString();

		_webLibInterface.sendKeyToElement("Start At", "id=startAt_time_attribute", StartTime);

		_webLibInterface.getWebDriver().findElement(By.id("startAt_time_attribute")).sendKeys(Keys.TAB);

		Calendar Cal = Calendar.getInstance();
		Cal.setTime(date);
		Cal.add(Calendar.HOUR, 1);
		String EndTime = dateFormat.format(Cal.getTime()).toString();

		_webLibInterface.sendKeyToElement("End At", "id=endAt_time_attribute", EndTime);

		_webLibInterface.getWebDriver().findElement(By.id("endAt_time_attribute")).sendKeys(Keys.TAB);

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("Next Button ", "xpath=//button[contains(.,'Next')]", "click");

		_webLibInterface.waitForPageLoad();

		if (_webLibInterface.elementDisplayed("xpath=//button[contains(.,'Start')]")) {

			_webLibInterface.clickElement("Consumers Select All", "id=Consumers_Select_All", "click");

			_webLibInterface.clickElement("Start", "xpath=//button[contains(.,'Start')]", "click");

			// _webLibInterface.waitForElementToBeClickable("xpath=//*[@id='service-entry']/div");

			_webLibInterface.waitForPageLoad();
		}

		selectConsumerToSign();
	}

	public void selectConsumerToSign() {
		_reportLibInterface.startTest("Sign and Aprove Consumer Session");

		_webLibInterface.clickElement(excelData.get("ConsumerName").trim() + " consumer card",
				"xpath=//a//div[text()='" + excelData.get("ConsumerName").trim() + "']", "click");

		_webLibInterface.waitForElementToBeClickable("xpath='//*[@id='Actions_Sign']");

		int DocReviewSize = _webLibInterface.getWebElementsList("xpath=//div[@data-ng-repeat='row in model']").size();

		for (int i = 1; i <= DocReviewSize; i++) {
			String TransStatus = _webLibInterface
					.getWebElement("xpath=//div[" + i + "][@data-ng-repeat='row in model']/div[9]/div[2]/span/a")
					.getText();

			if (TransStatus.equals("Not signed")) {

				String SNcolor = _webLibInterface
						.getWebElement(
								"xpath=//div[" + i + "][@data-ng-repeat='row in model']//div[text()='Service Note']")
						.getCssValue("color");
				String Progresscolor = _webLibInterface
						.getWebElement("xpath=//div[" + i + "][@data-ng-repeat='row in model']//div[text()='Progress']")
						.getCssValue("color");

				String Supportcolor = _webLibInterface
						.getWebElement("xpath=//div[" + i + "][@data-ng-repeat='row in model']//div[text()='Supports']")
						.getCssValue("color");

				String SRcolor = _webLibInterface
						.getWebElement(
								"xpath=//div[" + i + "][@data-ng-repeat='row in model']//div[text()='Service Record']")
						.getCssValue("color");
				if (SNcolor.equals("rgba(0, 0, 0, 1)") && Progresscolor.equals("rgba(0, 0, 0, 1)")
						&& Supportcolor.equals("rgba(0, 0, 0, 1)") && SRcolor.equals("rgba(35, 141, 18, 1)")) {

					_webLibInterface.clickElement("Check box",
							"//div[" + i + "][@data-ng-repeat='row in model']//div[@class='icon fa fa-lg fa-square-o']",
							"click");

					SignConsumer();

					_webLibInterface.waitForPageLoad();

					_webLibInterface.clickElement("Check box",
							"//div[" + i + "][@data-ng-repeat='row in model']//div[@class='icon fa fa-lg fa-square-o']",
							"click");

				}
			}
		}
	}

	public void SignConsumer() {

		_webLibInterface.clickElement("Sign", "id=Actions_Sign", "click");

		_webLibInterface.waitForElementToBeClickable("xpath=//*[@id='Actions_Sign']");

		_webLibInterface.waitForPageLoad();

	}

	public void ApproveConsumer() throws InterruptedException {

		_webLibInterface.clickElement("Sign", "id=Actions_Approve", "click");

		_webLibInterface.waitForElementToBeClickable("xpath=//*[@id='Actions_Approve']");

		_webLibInterface.waitSeconds(2);
	}

	@AfterClass
	public static void closeBrowser() {
		_webLibInterface.endChromeDriver();
	}

}
