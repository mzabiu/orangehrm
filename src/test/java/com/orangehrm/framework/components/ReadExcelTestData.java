package com.orangehrm.framework.components;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * Data reading utility
 * 
 * @author Zabiulla_Pro
 *
 */

public class ReadExcelTestData {

	private static final String excelDir = System.getProperty("user.dir")
			+ "\\src\\main\\resources\\com\\orangehrm\\data\\";

	private static Map<String, HashMap<String, String>> suiteData;
	private static Workbook workbook;
	private static FileInputStream fis;
	private static Sheet excelSheet;

	/**
	 * 
	 * This will return the Suite Data
	 * 
	 * @param workBookName
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */

	public static Map<String, HashMap<String, String>> getSuiteData(String workBookName, String sheetName)
			throws Exception {

		String excelFilePath = excelDir + workBookName + ".xlsx";
		fis = new FileInputStream(new File(excelFilePath));

		LinkedList<String> row1 = new LinkedList<String>();
		suiteData = new HashMap<String, HashMap<String, String>>();

		workbook = new XSSFWorkbook(fis);
		excelSheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = excelSheet.iterator();

		// loading the Row 0 which is a header
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();

		Cell cell = cellIterator.next();
		while (cellIterator.hasNext()) {
			cell = cellIterator.next();
			switch (cell.getCellType()) {
			case STRING:
				System.out.print(cell.getStringCellValue());
				row1.add(cell.getStringCellValue());
				break;
			case BOOLEAN:
				System.out.print(cell.getBooleanCellValue());
				row1.add(cell.getStringCellValue());
				break;
			case NUMERIC:
				System.out.print(cell.getNumericCellValue());
				row1.add("" + cell.getNumericCellValue());
				break;
			default:
				break;
			}
		}
		System.out.println(row1);
		System.out.println(excelSheet.getPhysicalNumberOfRows());

		while (iterator.hasNext()) {
			HashMap<String, String> data1 = new HashMap<String, String>();
			nextRow = iterator.next();
			cellIterator = nextRow.cellIterator();
			String cellTestCase = "";

			for (int i = 0; i < nextRow.getLastCellNum(); i++) {
				cell = cellIterator.next();
				String cellData = "";
				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue());
					cellData = cell.getStringCellValue();
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					cellData = cell.getStringCellValue();
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue());
					cellData = "" + cell.getNumericCellValue();
					break;

				default:
					cellData = "" + cell.getStringCellValue();
					break;
				}

				if (i == 0) {
					cellTestCase = cellData;
				} else {
					data1.put(row1.get(i - 1), cellData);
					System.out.println(data1);
				}
			}
			suiteData.put(cellTestCase, data1);
		}
		workbook.close();
		fis.close();
		return suiteData;
	}
}
