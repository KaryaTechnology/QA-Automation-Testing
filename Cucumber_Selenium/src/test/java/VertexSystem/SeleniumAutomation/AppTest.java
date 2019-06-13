package VertexSystem.SeleniumAutomation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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

import Library.LibraryInterface;
import ObjectPageLocators.PageLocators;

public class AppTest extends PageLocators implements LibraryInterface {

	@BeforeClass
	public static void Login() throws InterruptedException, InvalidFormatException, IOException {

		_webLibInterface.launchBrowser(excelData.get("URL"));

		_webLibInterface.sendKeyToElement("Field Username", pgLoc.loginPage("Username"), excelData.get("Username"));

		_webLibInterface.sendKeyToElement("Field Password", pgLoc.loginPage("Password"), excelData.get("Password"));

		_webLibInterface.clickElement("Login button", pgLoc.loginPage("Submit"), excelData.get("Clklogin"));

		_webLibInterface.waitForElementToBeClickable("xpath=//strong[contains(.,'11:00 pm')]");

	}

	@Test
	public void NavigateToConsumer() {
		_reportLibInterface.startTest("Navigate To Consumer");

		_webLibInterface.waitForPageLoad();

		_webLibInterface.clickElement("New Service Session dropdown", pgLoc.myPage("NewSessionDrop"),
				excelData.get("ClkNewSessionDrop"));

		_webLibInterface.clickElement("Multi Consumers from the dropdown", pgLoc.myPage("NewMultiConsumersSession"),
				excelData.get("ClkMultConsumerSession"));

		_webLibInterface.waitForPageLoad();

		CreateMultiConsumer();
	}

	// @Test

	public void CreateMultiConsumer() {

		_reportLibInterface.startTest("CreateMulti Consumer");

		if (excelData.get("EntPeopleGroup").trim() != "") {
			String PeopleGroup = excelData.get("EntPeopleGroup").trim();
			_webLibInterface.sendKeyToElement("People group dropdown", pgLoc.multiSesPro("PropleGroupTxtBx"),
					PeopleGroup);

			_webLibInterface.clickElement(PeopleGroup + " from the people group",
					pgLoc.multiSesPro("PropleGroupOpt").replace("PPGRP", PeopleGroup), "Click");

			_webLibInterface.waitForPageLoad();
		}

		if (excelData.get("EntConsumer").trim() != "") {
			String[] consumers = excelData.get("EntConsumer").trim().split(";");

			for (String consumer : consumers) {
				consumer = consumer.trim();
				_webLibInterface.sendKeyToElement("Consumer Searched Box", pgLoc.multiSesPro("ConsumerTxtBx"),
						consumer);

				_webLibInterface.waitForPageLoad();

				_webLibInterface.clickElement("Consumer " + consumer,
						pgLoc.multiSesPro("ConsumerOpt").replace("CONSOPT", consumer), "click");

				_webLibInterface.waitForPageLoad();
			}
		}

		_webLibInterface.clickElement("Next Button ", pgLoc.multiSesPro("NextButton"), excelData.get("NextButton"));

		_webLibInterface.waitForPageLoad();

		if (_webLibInterface.elementDisplayed(pgLoc.multiSesPro("NewSessionButton"))) {
			_webLibInterface.clickElement("New session Button ", pgLoc.multiSesPro("NewSessionButton"), "click");
		}

		_webLibInterface.waitForPageLoad();

		if (excelData.get("EntLocation") != "") {
			String Location = excelData.get("EntLocation");
			_webLibInterface.sendKeyToElement("Location", pgLoc.multiSesPro("LocationTxtBx"), Location);

			_webLibInterface.waitForPageLoad();

			_webLibInterface.clickElement(Location, pgLoc.multiSesPro("LocationOpt").replace("LOCOPT", Location),
					"yes");

			_webLibInterface.waitForPageLoad();
		}

		if (excelData.get("EntActivity") != "") {
			String Activity = excelData.get("EntActivity");
			_webLibInterface.sendKeyToElement("Activity", pgLoc.multiSesPro("ActivituTxtBx"), Activity);

			_webLibInterface.waitForPageLoad();

			_webLibInterface.clickElement("Activity Work",
					pgLoc.multiSesPro("ActivituOpt").replace("ACTVOPT", Activity), "click");

			_webLibInterface.waitForPageLoad();
		}

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String StartTime = excelData.get("EntStartAt");
		if (StartTime != "") {
			if (StartTime.equalsIgnoreCase("systime"))
				StartTime = dateFormat.format(date).toString();

			_webLibInterface.sendKeyToElement("Start At", pgLoc.multiSesPro("StartAtTxtBx"), StartTime);

			_webLibInterface.getWebDriver().findElement(By.id("startAt_time_attribute")).sendKeys(Keys.TAB);
		}

		String EndTime = excelData.get("EntEndAt").toLowerCase();
		if (EndTime != "") {

			if (EndTime.contains("systime+")) {
				Calendar Cal = Calendar.getInstance();
				try {
					date = dateFormat.parse(StartTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Cal.setTime(date);
				int addtime;
				String strAddTime = EndTime.replace("systime+", "");
				if (strAddTime.contains("h")) {
					addtime = Integer.parseInt(strAddTime.replace("h", ""));
					Cal.add(Calendar.HOUR, addtime);
				} else if (strAddTime.contains("m")) {
					addtime = Integer.parseInt(strAddTime.replace("m", ""));
					Cal.add(Calendar.MINUTE, addtime);
				}

				EndTime = dateFormat.format(Cal.getTime()).toString();
			}

			_webLibInterface.sendKeyToElement("End At", pgLoc.multiSesPro("EndAtTxtBx"), EndTime);

			_webLibInterface.getWebDriver().findElement(By.id("endAt_time_attribute")).sendKeys(Keys.TAB);

		}

		_webLibInterface.waitForPageLoad();

		if (excelData.get("ClkNextCreatSession") != "") {

			_webLibInterface.clickElement("Next Button ", pgLoc.multiSesPro("NextButton"), "click");

			_webLibInterface.waitForPageLoad();

			if (_webLibInterface.elementDisplayed(pgLoc.multiSesPro("StartButton"))) {

				_webLibInterface.clickElement("Consumers Select All", pgLoc.multiSesPro("SelectAllConsumer"), "click");

				_webLibInterface.clickElement("Start", pgLoc.multiSesPro("StartButton"), "click");

				_webLibInterface.waitForPageLoad();
			}
		}

		if (excelData.get("SelectConsumerToSign") != "")
			selectConsumerToSign();
	}

	public void selectConsumerToSign() {
		_reportLibInterface.startTest("Sign and Aprove Consumer Session");

		if (excelData.get("SelectConsumerToSign") != "Sign All") {

			String consumer = excelData.get("SelectConsumerToSign");

			_webLibInterface.clickElement(consumer + " consumer card",
					pgLoc.multiSesPro("ConsumerCard").replace("CONSUMER", consumer), "click");

			_webLibInterface.waitForPageLoad();

			int DocReviewSize = _webLibInterface.getWebElementsList(pgLoc.multiSesPro("DocRevSize")).size();

			for (int i = 1; i <= DocReviewSize; i++) {
				String TransStatus = _webLibInterface
						.getTextElement(pgLoc.multiSesPro("TransStatus").replace("ORDER", i + ""));

				if (TransStatus.equals("Not signed")) {

					String SNcolor = _webLibInterface
							.getWebElement(pgLoc.multiSesPro("SNLink").replace("ORDER", i + "")).getCssValue("color");
					String Progresscolor = _webLibInterface
							.getWebElement(pgLoc.multiSesPro("PLink").replace("ORDER", i + "")).getCssValue("color");

					String Supportcolor = _webLibInterface
							.getWebElement(pgLoc.multiSesPro("SLink").replace("ORDER", i + "")).getCssValue("color");

					String SRcolor = _webLibInterface
							.getWebElement(pgLoc.multiSesPro("SRLink").replace("ORDER", i + "")).getCssValue("color");

					if (SNcolor.equals("rgba(0, 0, 0, 1)") && Progresscolor.equals("rgba(0, 0, 0, 1)")
							&& Supportcolor.equals("rgba(0, 0, 0, 1)") && SRcolor.equals("rgba(35, 141, 18, 1)")) {

						_webLibInterface.clickElement("Check box",
								pgLoc.multiSesPro("DocRevCheckBox").replace("ORDER", i + ""), "click");

						SignConsumer();

						_webLibInterface.waitForPageLoad();

					}
				}
			}
		}
	}

	public void SignConsumer() {

		_webLibInterface.clickElement("Sign", "id=Actions_Sign", "click");

		_webLibInterface.waitForPageLoad();

	}

	public void ApproveConsumer() throws InterruptedException {

		_webLibInterface.clickElement("Sign", "id=Actions_Approve", "click");

		_webLibInterface.waitSeconds(2);
	}

	@AfterClass
	public static void closeBrowser() {
		_webLibInterface.endChromeDriver();
	}

	public static LinkedHashMap<String, String> excelData = _fileLibInstance.getExcelData("MasterExcel", "Sheet1", "1");
	public static PageLocators pgLoc = new PageLocators();
}
