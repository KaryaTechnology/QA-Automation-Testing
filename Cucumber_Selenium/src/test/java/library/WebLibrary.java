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

import seleniumgluecode.test;

public class WebLibrary {
	//ST
	private WebLibrary() {
	}
	// ST
	public static WebLibrary webLibaray = new WebLibrary();
	// ST
	public static WebLibrary getWebLibrary() {
		return webLibaray;
	}

	public static boolean booHighliteFlag = true;
	public static WebDriver webDriver;

	@SuppressWarnings("unused")
	private String BasePath()  {
		
		String strBasepath = null;
		try {
			File Directory = new File(".");
			strBasepath = Directory.getAbsolutePath();
			test.strBasepath = strBasepath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strBasepath;
	}

	@SuppressWarnings("unused")
	private static void launchBrowser(String URL) {
		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		System.setProperty("webdriver.chrome.driver", "D:/Automation/Driver/chromedriver.exe");
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(URL);
	}

	private static WebElement getWebElement(String... Locator) throws Exception {

		WebElement element = null;

		WebDriverWait wait = new WebDriverWait(webDriver, 15);
		try {

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
			// reportResults("Web element should be exists", "Web element is not
			// exists", "Fail");
			// e.printStackTrace();
			String msg = "";
			if (Locator.length > 1) {
				msg = Locator[1];
			}
		}

		HighlightMyElement(element);

		return element;
	}

	private static void HighlightMyElement(WebElement element) throws InterruptedException {
		if (booHighliteFlag) {
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

	private static void waitForStartPageloade() throws InterruptedException {

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
		}

	}

	private static void waitForPageLoad() throws InterruptedException {

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
		}

	}
}
