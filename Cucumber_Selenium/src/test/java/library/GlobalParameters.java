package library;

import java.io.File;

public class GlobalParameters {

	protected static String strSetReportLocation = "DEXK";
	protected static boolean booHighliteFlag = true;
	protected static boolean boolScreenShortForEachStep = false;
	protected static String strChromeDriverLocation = "";
	protected static String strBasePath = "";
	protected static String strTestCaseId = "TestCaseID";
	protected static String strPageName = "";
	protected static String strModuleName = "";

	protected static String BasePath() {

		String strBasepath = null;
		try {
			File Directory = new File(".");
			strBasepath = Directory.getCanonicalPath();
			if (strBasePath.trim().equalsIgnoreCase(""))
				strBasePath = strBasepath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strBasepath;
	}

}
