package Runner;

import java.io.File;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.ExtentCucumberFormatter;
import com.cucumber.listener.Reporter;
import java.io.*;
import org.junit.AfterClass;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import managers.FileReaderManager;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/Test.feature", glue = "seleniumgluecode", plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true)

public class testrunner {
	@AfterClass
	public static void writeExtentReport() {
/*		File file = new File(".");
		System.out.println(file.getAbsolutePath());
		Reporter.loadXMLConfig(file.getAbsolutePath() + "\\Config\\extent-config.xml");*/
		Reporter.loadXMLConfig(new File("config/report.xml"));
	}

	

}
