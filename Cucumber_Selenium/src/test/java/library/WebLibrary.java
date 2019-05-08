package library;

import java.io.File;
import java.io.IOException;
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
	protected static void launchBrowser(String URL) {
		try {
			_reportLibInterface.createReport();
			_reportLibInterface.startTest("Launch Application");
			
			File file = new File(".");
			
			System.setProperty("webdriver.chrome.driver",
					file.getCanonicalPath() + "\\DependentFiles\\chromedriver.exe");
			
			webDriver = new ChromeDriver();
			webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
	protected static WebElement getWebElement(String... Locator) throws Exception {

		WebElement element = null;

		try {
			WebDriverWait wait = new WebDriverWait(webDriver, 15);
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
					"Error",
					_reportLibInterface.getScreenShot(webDriver, "Objectnot Found in " + GlobalParameters.strPageName));
		}

		HighlightMyElement(element);

		return element;
	}

	// Highlight the element
	protected static void HighlightMyElement(WebElement element) throws InterruptedException {
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
			Thread.sleep(500);
		}
		Thread.sleep(500);

	}

	// Waits for start page to load
	protected static void waitForStartPageloade() throws InterruptedException {

		try {
			Thread.sleep(2000);
			for (int i = 0; i <= 400; i++) {
				boolean loadingStatus = webDriver
						.findElement(By.xpath("//div[@class='busy active']//div[@class='element-loading-icon']"))
						.isDisplayed();
				if (loadingStatus) {
					Thread.sleep(2000);

				} else if (loadingStatus) {
					break;
				}
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println(
					"Page Loading waitForStartPageloade() is complete or might be an exception on Page Loading");
			logReport("waitForStartPageloade() method called the page is loading",
					"Error while loading page : " + e.toString(), "Error", true);
		}

	}

	// Waits for page to load
	protected static void waitForPageLoad() throws InterruptedException {

		try {
			Thread.sleep(2000);
			for (int i = 0; i <= 400; i++) {
				boolean loadingStatus = webDriver.findElement(By.xpath("//div[@class='loading-icon']")).isDisplayed();

				if (loadingStatus) {
					Thread.sleep(15000);
					System.out.println("Page refresh in waitForStartPageLoading times" + i * 15 + "seconds");
				} else if (webDriver.findElement(By.xpath("//div[@class='cal-day-panel clearfix']")).isDisplayed()) {
					break;
				}
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			System.out.println(
					"Page Loading waitForStartPageloade() is complete or might be an exception on Page Loading");
			logReport("waitForPageLoad() method called the page is loading",
					"Error while loading page : " + e.toString(), "Error", true);
		}

	}

	// Click on the object
	protected static void clickElement(String nameOfObject, String locator, String action) {
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
	protected static void sendKeyToElement(String nameOfObject, String locator, String value) {
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

	// close chrome driver
	protected static void endChromeDriver() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}

	protected static void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private WebLibrary() {
	}

	public static WebLibrary getWebLibrary() {
		if (webLibInstance == null)
			webLibInstance = new WebLibrary();
		return webLibInstance;
	}

	public static void main(String[] args) throws InterruptedException {
		launchBrowser("https://www.google.com/");

		waitSeconds(2);

		sendKeyToElement("Google search text box", "xpath=//input[@class='gLFyf gsfi']", "extent report");

		waitSeconds(2);

		clickElement("Click on Google Search", "xpath=//div[@class='FPdoLc VlcLAe']//input[@value='Google Search']",
				"yes");
		waitSeconds(10);
		try {

			endChromeDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// webDriver.quit();
	}

}
