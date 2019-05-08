package pageObjectLocators;

import java.util.HashMap;

public class PageLocators {
	HashMap<String, String> Locator = new HashMap<String, String>();

	public String loginPage(String strLocatorName) {

		

		Locator.put("Username", "id=");
		Locator.put("Password", "xpath=");
		
		String locator = Locator.get(strLocatorName);
		Locator.clear();
		return locator;

	}

}
