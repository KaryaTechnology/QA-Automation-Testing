package Library;

import java.io.File;
import java.util.HashMap;

public class GlobalParameters {

	public static HashMap<String, String> globalValueDump = new HashMap<String, String>();
	public static String strSetReportLocation = "DESK";
	public static boolean booHighliteFlag = true;
	public static boolean boolScreenShortForEachStep = false;
	public static String strChromeDriverLocation = "";
	public static String strBasePath = "";
	public static String strTestCaseId = "TestCaseID";
	public static String strPageName = "";
	public static String strModuleName = "";

	public static String BasePath() {

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
