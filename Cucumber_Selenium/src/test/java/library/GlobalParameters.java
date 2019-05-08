package library;

import java.io.File;

public class GlobalParameters {

	public static String strSetReportLocation = "DESK";
	public static String strChromeDriverLocation = "";
	public static String strBasePath = "";
	public static String strTestCaseId = "TestCaseID";

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
