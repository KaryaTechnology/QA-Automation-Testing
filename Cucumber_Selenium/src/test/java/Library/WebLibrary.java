package Library;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebLibrary implements LibraryInterface {

	protected static WebDriver webDriver;
	static WebLibrary webLibInstance = null;

	// Need to change the path
	public void launchBrowser(String URL) {
		try {
			_reportLibInterface.createReport();
			_reportLibInterface.startTest("Launch Application");

			File file = new File(".");

			System.setProperty("webdriver.chrome.driver",
					file.getCanonicalPath() + "\\DependentFiles\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getCanonicalPath() + "\\Driver\\chromedriver.exe");

			webDriver = new ChromeDriver();
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			webDriver.manage().window().maximize();
			logReport("Browser should be launched", "Browser is launched successfully", "Pass", false);

			webDriver.get(URL);
			logReport("The application should be launched", "The application is opened successfully", "pass", false);
		} catch (IOException e) {
			_reportLibInterface.logReportStepsStatus("Browser to be launched and application to be loaded",
					"Browser Didnot launch " + e.toString(), "error", "");
			e.printStackTrace();
		}
	}

	// Find the object
	public WebElement getWebElement(String... Locator) {

		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(webDriver, 10);
			if (Locator[0].startsWith("css=")) {
				Locator[0] = Locator[0].substring(4);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Locator[0])));
				element = webDriver.findElement(By.cssSelector(Locator[0]));
			} else if (Locator[0].startsWith("id=")) {
				Locator[0] = Locator[0].substring(3);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator[0])));
				element = webDriver.findElement(By.id(Locator[0]));
			} else if (Locator[0].startsWith("xpath=")) {
				Locator[0] = Locator[0].substring(6);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator[0])));
				element = webDriver.findElement(By.xpath(Locator[0]));
			} else if (Locator[0].startsWith("name=")) {
				Locator[0] = Locator[0].substring(5);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator[0])));
				element = webDriver.findElement(By.name(Locator[0]));
			} else if (Locator[0].startsWith("link=")) {
				Locator[0] = Locator[0].substring(5);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Locator[0])));
				element = webDriver.findElement(By.linkText(Locator[0]));
			} else if (Locator[0].startsWith("class=")) {
				Locator[0] = Locator[0].substring(6);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Locator[0])));
				element = webDriver.findElement(By.className(Locator[0]));
			} else {
				element = (WebElement) ((JavascriptExecutor) webDriver).executeScript(Locator[0]);
			}
		} catch (Exception e) {

			String msg = "";
			if (Locator.length > 1) {
				msg = Locator[1];
				System.out.println("Object not found locator :" + msg);
			}
			System.out.println("Object not found locator :" + Locator[0]);
			_reportLibInterface.logReportStepsStatus("Object is to be found", "Object is not found : " + Locator[0],
					"WARNING",
					_reportLibInterface.getScreenShot(webDriver, "Objectnot Found in " + GlobalParameters.strPageName));
		}

		HighlightMyElement(element);

		return element;
	}

	@SuppressWarnings("unchecked")
	public List<WebElement> getWebElementsList(String... Locator) {

		List<WebElement> element = null;
		try {

			WebDriverWait wait = new WebDriverWait(webDriver, 10);

			if (Locator[0].startsWith("css=")) {
				Locator[0] = Locator[0].substring(4);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Locator[0])));
				element = webDriver.findElements(By.cssSelector(Locator[0]));
			} else if (Locator[0].startsWith("id=")) {
				Locator[0] = Locator[0].substring(3);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator[0])));
				element = webDriver.findElements(By.id(Locator[0]));
			} else if (Locator[0].startsWith("xpath=")) {
				Locator[0] = Locator[0].substring(6);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator[0])));
				element = webDriver.findElements(By.xpath(Locator[0]));
			} else if (Locator[0].startsWith("name=")) {
				Locator[0] = Locator[0].substring(5);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator[0])));
				element = webDriver.findElements(By.name(Locator[0]));
			} else if (Locator[0].startsWith("link=")) {
				Locator[0] = Locator[0].substring(5);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(Locator[0])));
				element = webDriver.findElements(By.linkText(Locator[0]));
			} else if (Locator[0].startsWith("class=")) {
				Locator[0] = Locator[0].substring(6);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Locator[0])));
				element = webDriver.findElements(By.className(Locator[0]));
			} else {
				element = (List<WebElement>) ((JavascriptExecutor) webDriver).executeScript(Locator[0]);
			}
		} catch (Exception e) {

			String msg = "";
			if (Locator.length > 1) {
				msg = Locator[1];
				System.out.println("Object not found locator :" + msg);
			}
			System.out.println("Object not found locator :" + Locator[0]);
			_reportLibInterface.logReportStepsStatus("Object is to be found", "Object is not found : " + Locator[0],
					"WARNING",
					_reportLibInterface.getScreenShot(webDriver, "Objectnot Found in " + GlobalParameters.strPageName));
			return null;
		}
		return element;
	}

	// Highlight the element
	protected void HighlightMyElement(WebElement element) {
		if (GlobalParameters.booHighliteFlag) {
			for (int i = 0; i < 10; i++) {
				JavascriptExecutor javascript = (JavascriptExecutor) webDriver;
				javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: orange; border: 4px solid orange;");
				javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: pink; border: 4px solid pink;");
				javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
						"color: yellow; border: 4px solid yellow;");
				javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}
			waitSeconds(500);
		}
		waitSeconds(500);

	}

	public String getElementText(String Locator) {
		String text = getWebElement(Locator).getText();
		if (text.isEmpty()) {
			logReport("Expected Text to be returned", "Text not returned", "Fail", true);
			return text;
		} else
			return text;
	}

	// Element is displayed or not
	public boolean elementDisplayed(String xpathLocator) {
		boolean status = false;
		try {
			if (xpathLocator.startsWith("xpath=")) {
				xpathLocator = xpathLocator.substring(6);
			}
			status = webDriver.findElement(By.xpath(xpathLocator)).isDisplayed();

		} catch (Exception e) {
			// TODO: handle exception
			status = false;
		}
		return status;
	}

	// Waits for start page to load
	public boolean waitForElementToBeClickable(String xpathLocator) {

		try {
			if (xpathLocator.startsWith("xpath=")) {
				xpathLocator = xpathLocator.substring(6);
			}

			WebDriverWait loadmypage = new WebDriverWait(webDriver, 1000);
			loadmypage.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
			logReport("waitForElementToBeClickable() method is called will wait until the element is clickable",
					"The element is clickable", "Pass", GlobalParameters.boolScreenShortForEachStep);
			return true;
		} catch (Exception e) {
			System.out.println(
					"Page Loading waitForElementToBeClickable() is complete or might be an exception on Page Loading");
			logReport("waitForElementToBeClickable() method called the page is loading",
					"Error while waiting for the object : xpath=" + xpathLocator, "Error", true);
			return false;
		}

	}

	// Waits for page to load
	public void waitForPageLoad() {

		try {
			waitSeconds(1);
			webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			for (int i = 1; i <= 400; i++) {
				int loadingStatus = 0;
				try {
					loadingStatus = webDriver.findElements(By.xpath("//div[@class='busy active']")).size();
				} catch (Exception e) {
				}

				if (loadingStatus > 0) {
					waitSeconds(2);
					System.out.println("Page loading times taken " + i * 2 + " seconds");
				} else {
					break;
				}
			}
			waitSeconds(1);

		} catch (Exception e) {
			System.out.println(
					"Page Loading waitForStartPageloade() is complete or might be an exception on Page Loading");
			logReport("waitForPageLoad() method called the page is loading", "Error while loading page ", "Error",
					true);
		} finally {
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

	}

	// Click on the object
	public void clickElement(String nameOfObject, String locator, String action) {
		if (!action.equalsIgnoreCase("")) {
			try {

				WebElement element = getWebElement(locator);
				if (element.isDisplayed()) {
					((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", element);

					logReport("The " + nameOfObject + " should be clicked",
							"The " + nameOfObject + " is clicked sucussfilly", "Pass",
							GlobalParameters.boolScreenShortForEachStep);
				} else {
					logReport("The " + nameOfObject + " should be clicked", "The " + nameOfObject + " is not clicked",
							"Fail", true);
				}
			} catch (Exception e) {
				logReport("The " + nameOfObject + " should be clicked",
						"The " + nameOfObject + " element was not found : " + locator, "Error", true);
			}
		}
	}

	// Send key to a test field
	public void sendKeyToElement(String nameOfObject, String locator, String value) {
		if (!value.equalsIgnoreCase("")) {
			try {
				WebElement element = getWebElement(locator);
				if (element.isDisplayed()) {
					element.clear();
					element.sendKeys(value);
					logReport("Set the value \"" + value + "\" in the text box " + nameOfObject,
							"The value \"" + value + "\" is set in the text box " + nameOfObject, "Pass",
							GlobalParameters.boolScreenShortForEachStep);
				} else {
					logReport("Displays the test box " + nameOfObject + " in the page " + GlobalParameters.strPageName,
							"The object " + nameOfObject + " is not presrnt in the page "
									+ GlobalParameters.strPageName,
							"Fail", true);
				}
			} catch (Exception e) {
				logReport(
						"The text box " + nameOfObject + " should be present in the page "
								+ GlobalParameters.strPageName,
						"The text box " + nameOfObject + " its locator : " + locator + " is not present in the page "
								+ GlobalParameters.strPageName,
						"Error", true);
			}
		}
	}

	// To log report steps
	protected static void logReport(String Expected, String Actual, String Status, boolean takeScreenshort) {
		try {
			if (takeScreenshort) {
				_reportLibInterface.logReportStepsStatus(Expected, Actual, Status, _reportLibInterface
						.getScreenShot(webDriver, GlobalParameters.strPageName + GlobalParameters.strModuleName));
			} else {
				_reportLibInterface.logReportStepsStatus(Expected, Actual, Status, "");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// end chrome driver
	public void endChromeDriver() {

		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void waitSeconds(int seconds) {
		try {
			if (seconds == 500)
				Thread.sleep(500);
			else
				Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// returns web driver
	public WebDriver getWebDriver() {
		return webDriver;
	}

	/** single **/
	WebLibrary() {
	}

	public static WebLibrary getWebLibrary() {
		if (webLibInstance == null)
			webLibInstance = new WebLibrary();
		return webLibInstance;
	}

	/** single **/
	public static void main(String[] args) {
		//System.out.println(GlobalParameters.BasePath());
		getWebLibrary().endChromeDriver();
	}

}
