package ObjectPageLocators;

import java.util.HashMap;

public class PageLocators {
	HashMap<String, String> Locator = new HashMap<String, String>();

	public String loginPage(String strLocatorName) {
		Locator.put("Username", "id=Username");
		Locator.put("Password", "id=Password");
		Locator.put("Submit", "id=submit");

		return returnAndClear("loginPage", strLocatorName);
	}

	public String myPage(String strLocatorName) {
		Locator.put("NewSessionDrop", "xpath=//div[@title='New Service Session']/div/div[2]");
		Locator.put("NewMultiConsumersSession", "id=buttons_buttonGroup_undefinedNew_Service_Session_Multi_Consumers");

		return returnAndClear("myPage", strLocatorName);
	}

	public String multiSesPro(String strLocatorName) {

		Locator.put("PropleGroupTxtBx", "id=fakeEntityPeopleGroupFk_relationship");
		Locator.put("PropleGroupOpt", "xpath=//span[text()='PPGRP']");

		Locator.put("ConsumerTxtBx", "id=consumerSearch");
		Locator.put("ConsumerOpt", "xpath=//div[@class='consumer column ng-scope']/div[contains(.,'CONSOPT')]");

		Locator.put("NextButton", "xpath=//button[contains(.,'Next')]");
		Locator.put("NewSessionButton", "xpath=//button[contains(.,'New session')]");

		Locator.put("LocationTxtBx", "id=serviceSessionServiceLocationFk_relationship");
		Locator.put("LocationOpt", "xpath=//span[text()='LOCOPT']");

		Locator.put("ActivituTxtBx", "id=serviceSessionServiceFk_relationship");
		Locator.put("ActivituOpt", "xpath=//*[@id='serviceSessionServiceFk_element']//span[text()='ACTVOPT']");

		Locator.put("StartAtTxtBx", "id=startAt_time_attribute");
		Locator.put("EndAtTxtBx", "id=endAt_time_attribute");

		Locator.put("SelectAllConsumer", "id=Consumers_Select_All");
		Locator.put("StartButton", "xpath=//button[contains(.,'Start')]");

		Locator.put("ConsumerCard", "xpath=//a//div[text()='CONSUMER']");

		Locator.put("DocRevSize", "xpath=//div[@data-ng-repeat='row in model']");
		Locator.put("DocRevOrder", "xpath=//div[ORDER][@data-ng-repeat='row in model']");
		Locator.put("DocRevCheckBox", Locator.get("DocRevOrder") + "//div[@class='icon fa fa-lg fa-square-o']");
		Locator.put("TransStatus", Locator.get("DocRevOrder") + "/div[9]//a");
		Locator.put("SNLink", Locator.get("DocRevOrder") + "//div[text()='Service Note']");
		Locator.put("PLink", Locator.get("DocRevOrder") + "//div[text()='Progress']");
		Locator.put("SLink", Locator.get("DocRevOrder") + "//div[text()='Supports']");
		Locator.put("SRLink", Locator.get("DocRevOrder") + "//div[text()='Service Record']");

		return returnAndClear("multiSesPro", strLocatorName);

	}

	public String returnAndClear(String Methodname, String strLocatorName) {
		String locator = "";
		if (!Locator.containsKey(strLocatorName))
			System.out.println(Methodname + " does not contains key " + strLocatorName);
		else
			locator = Locator.get(strLocatorName);
		Locator.clear();
		return locator;
	}

}
