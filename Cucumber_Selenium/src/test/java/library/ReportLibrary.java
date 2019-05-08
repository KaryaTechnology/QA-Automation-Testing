package library;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportLibrary {

	public static String reportLocation = reportLocation(GlobalParameters.strSetReportLocation);
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest logger;

	static ReportLibrary reportLibInstance = null;

	/********************************************
	 * Report Initialization
	 ********************************************/
	protected void createReport() {

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String strTimeStamp = dateFormat.format(date).toString();

		String report_location = reportLocation + "\\Vertex-Report_" + GlobalParameters.strTestCaseId + "_"
				+ strTimeStamp + ".html";

		System.out.println("Report File Location : " + report_location);

		htmlReporter = new ExtentHtmlReporter(report_location);

		// We Can Add extra parameters if needed
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Environment", "Production");
		report.setSystemInfo("User Name", System.getProperty("user.name"));

		// Should look into before any changes
		htmlReporter.config().setDocumentTitle("Vertex Automation");
		htmlReporter.config().setReportName("Vertex");
		htmlReporter.config().setTheme(Theme.DARK);

		endReport();
	}

	/********************************************
	 * Module Start
	 ********************************************/
	protected void startTest(String moduletName) {
		logger = report.createTest(moduletName);
	}

	/********************************************
	 * Log steps to report Info Pass Fail
	 ********************************************/
	protected void logReportStepsStatus(String Expected, String Actual, String status, String screenshotPath) {

		try {
			if (!screenshotPath.equalsIgnoreCase("")) {

				switch (status.toUpperCase()) {
				case "INFO":
					logger.info(Actual, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				case "PASS":
					logger.pass(Actual, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				case "SKIP":
					logger.skip(Actual, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				case "FAIL":
					logger.fail("Expected : " + Expected + " \nActual : " + Actual,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				case "ERROR":
					logger.error("Expected : " + Expected + " \nActual : " + Actual,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				case "WARNING":
					logger.warning("Expected : " + Expected + " \nActual : " + Actual,
							MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
					break;
				default:
					logger.log(Status.INFO, MarkupHelper.createLabel(Actual, ExtentColor.BLUE));
					break;
				}

				/*
				 * logger.log(Status.FAIL, "\nExpected : " + Expected + " \nActual : " +
				 * Actual); logger.pass("\nExpected : " + Expected + " \nActual : " + Actual)
				 * .addScreenCaptureFromPath(screenshotPath);
				 */
			} else {
				switch (status.toUpperCase()) {
				case "INFO":
					logger.info(Actual);
					break;
				case "PASS":
					logger.pass(Actual);
					break;
				case "SKIP":
					logger.skip(Actual);
					break;
				case "FAIL":
					logger.fail("Expected : " + Expected + " \nActual : " + Actual);
					break;
				case "ERROR":
					logger.error("Expected : " + Expected + " \nActual : " + Actual);
					break;
				case "WARNING":
					logger.warning("Expected : " + Expected + " \nActual : " + Actual);
					break;
				default:
					logger.log(Status.INFO, MarkupHelper.createLabel(Actual, ExtentColor.BLUE));
					break;
				}

			}
			endReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/********************************************
	 * Take screenshot returns the location
	 ********************************************/
	protected String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = reportLocation + "\\Screenshots\\" + screenshotName + "_" + dateName + ".png";
		File FileResultFolder = new File(reportLocation + "\\Screenshots\\");
		if (!FileResultFolder.exists()) {
			FileResultFolder.mkdirs();
		}
		System.out.println("Screenshorts Location : " + destination);
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	/********************************************
	 * Report flush to right in the report
	 ********************************************/
	protected static void endReport() {
		report.flush();
	}

	/********************************************
	 * Report location to be set
	 ********************************************/
	protected static String reportLocation(String where_ProjDesk) {

		String reportLocation;
		switch (where_ProjDesk.toUpperCase().trim()) {
		case "PROJ":
			reportLocation = GlobalParameters.BasePath();
			break;
		case "DESK":
			reportLocation = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
			break;
		default:
			reportLocation = System.getProperty("user.dir");
			break;
		}
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strTimeStamp = dateFormat.format(date).toString();
		reportLocation = reportLocation + "\\Test-Automation-Reports\\Report-Generated-On-" + strTimeStamp;

		System.out.println("Report generated Location : " + reportLocation);

		// Check file location exist or not and creates file
		File FileResultFolder = new File(reportLocation);
		if (!FileResultFolder.exists()) {
			FileResultFolder.mkdirs();
		}

		return reportLocation;
	}

	private ReportLibrary() {
	}

	public static ReportLibrary getReportLibrary() {
		if (reportLibInstance == null)
			reportLibInstance = new ReportLibrary();
		return reportLibInstance;
	}

}
