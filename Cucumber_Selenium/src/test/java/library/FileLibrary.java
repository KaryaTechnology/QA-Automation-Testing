package library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class FileLibrary {

	static FileLibrary fileLibInstance = null;

	public String BasePath() throws IOException {
		File Directory = new File(".");
		String StrBasepath = Directory.getCanonicalPath();
		return StrBasepath;
	}

	public LinkedHashMap<String, String> getExcelData(String strExcelName, String strSheetName, String strTestCaseID) {

		File DriverFile = null;
		Workbook dataWorkbook = null;

		try {
			DriverFile = new File(BasePath() + "\\TestData\\" + strExcelName + ".xlsx");
			dataWorkbook = new XSSFWorkbook(DriverFile);
		} catch (Exception e1) {
			try {
				DriverFile = new File(BasePath() + "\\TestData\\" + strExcelName + ".xls");
				InputStream ExcelFileToRead = new FileInputStream(DriverFile);
				;
				dataWorkbook = new HSSFWorkbook(ExcelFileToRead);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Sheet dataExcelSheet = dataWorkbook.getSheet(strSheetName);
		Row dataRow;
		// System.out.println(strExcelName + " : " + strSheetName);

		LinkedHashMap<String, String> testCaseHedderData = new LinkedHashMap<String, String>();

		for (int i = 0; i <= dataExcelSheet.getLastRowNum(); i++) {
			dataRow = dataExcelSheet.getRow(i);
			String strDataTestcaseIDCol = "";
			try {
				strDataTestcaseIDCol = dataRow.getCell(0).getStringCellValue().trim();
			} catch (Exception e) {
				// TODO: handle exception
				strDataTestcaseIDCol = "";
				System.out.println("Excel Sheet " + strExcelName + " in sheet " + strSheetName
						+ " contains empty row in row no " + i);
			}
			if (strDataTestcaseIDCol.trim().equals(strTestCaseID)) {
				int cellCount = dataExcelSheet.getRow(0).getLastCellNum();
				for (int j = 0; j < cellCount; j++) {
					String dataHeadder = dataExcelSheet.getRow(0).getCell(j).getStringCellValue().trim();
					String dataValue = "";
					try {
						dataValue = dataExcelSheet.getRow(i).getCell(j).getStringCellValue().trim();
					} catch (Exception e) {
						dataValue = "";
					}
					testCaseHedderData.put(dataHeadder, dataValue);
				}
				break;
			}
		}
		try {
			dataWorkbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("data sheet value"+testCaseHedderData);
		return testCaseHedderData;
	}

	/** single **/
	FileLibrary() {
	}

	public static FileLibrary getFileLibrary() {
		if (fileLibInstance == null)
			fileLibInstance = new FileLibrary();
		return fileLibInstance;
	}
	/** single **/
}
