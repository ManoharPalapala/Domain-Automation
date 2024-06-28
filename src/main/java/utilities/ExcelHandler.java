package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {

	PropHandler ph = new PropHandler();

//	public static void main(String args[]) {
//		DataFileHandler dh = new DataFileHandler();
//		for(Object[] x:dh.readDataFromExcel("list")) {
//			for (Object y : x) {
//				System.out.println(y);
//			}
//		}
//	}


	private File excelFile;
	private FileInputStream fis;
	private FileOutputStream fos;
	private XSSFWorkbook wb = null;
	private XSSFSheet sheet;

	public Object[][] readDataFromExcel(String sheetName) {

		excelFile = new File(ph.readDataFromPropFile("domainUrlSheetPath"));

		try{
			fis = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum();// index
		int colCount = sheet.getRow(0).getLastCellNum();// physical
		Object[][] data = new Object[rowCount][colCount];
		for (int r = 1; r <= rowCount; r++) {

			XSSFRow row = sheet.getRow(r);
			for (int c = 0; c < colCount; c++) {
				XSSFCell cell = row.getCell(c);
				CellType cellType = cell.getCellType();

				switch (cellType) {
				case STRING:
					data[r - 1][c] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[r - 1][c] = cell.getNumericCellValue();
					break;
				case BLANK:
					data[r - 1][c] = null;
					break;
				default:
					break;
				}
			}
		}

		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;

	}

//
//
//	public void writeDataToExcel(String sheetname, Object[][] data){
//
//		wb = new XSSFWorkbook();
//		sheet = wb.createSheet(sheetname);
//
//		Row row = sheet.createRow(0);
//		Cell cell = row.createCell(0);
//
//
//	}

		}





































