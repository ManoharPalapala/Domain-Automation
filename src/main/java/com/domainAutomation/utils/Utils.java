package com.domainAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class Utils {
	
	
	WebDriver driver;
	
	public static void main(String args[]) {
		
		readDataFromExcel("list");
	}

	
	public static Object[][] readDataFromExcel(String sheetName) {
		
		File excelFile = new File(System.getProperty("user.dir"),Utils.readDataFromPropFile("path"));
		
		FileInputStream fis;
		XSSFWorkbook wb=null;
		XSSFSheet sheet = null;
		
		
		try {
			fis = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		if("list".equals(sheetName)) {
			sheet = wb.getSheet(sheetName);
		}else if("metaContent".equals(sheetName)) {
			sheet = wb.getSheet(sheetName);
		}else {
            throw new IllegalArgumentException("Invalid sheet name: " + sheetName);
        }
		
		
		int rowCount = sheet.getLastRowNum();//index
		int colCount = sheet.getRow(0).getLastCellNum();//physical
		
		Object[][] data = new Object[rowCount][colCount];

		for(int r=1;r<=rowCount;r++) {	
			
			XSSFRow row = sheet.getRow(r);
			for(int c=0;c<colCount;c++) {
				XSSFCell cell = row.getCell(c);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				case STRING:
					data[r-1][c] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[r-1][c] = cell.getNumericCellValue();
					break; 
				case BLANK:
					System.out.println("Cell is blank");
				default:
					System.out.println(cell.getCellType());
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
		
		
		
		public static String readDataFromPropFile(String propName) {
			
			File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\resources\\config.properties");
			Properties prop = new Properties();
			FileInputStream fis=null;
			try {
				fis = new FileInputStream(propFile);
				prop.load(fis);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		
			return prop.getProperty(propName);
			
		}	
			
	}
		
	

	



