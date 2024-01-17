package com.domainAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

//	public static void main(String[] args) {
//		readMetaContentFromExcel("metaContent","Vocationize.com","about");
//	}


	
	@SuppressWarnings("unused")
	public static String readMetaContentFromExcel(String sheetName, String domainUrl, String page, String typeOfContent) {
		
		String metaValue=null;
		
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\test\\resources\\metaPractise.xlsx");
		
		FileInputStream fis;
		XSSFWorkbook wb=null;
		
		int startIndex=0;
		int lastIndex=0;
		
		XSSFCell cell=null;
		XSSFCell pageName=null;
		XSSFCell metaContentCell=null;

		
		List<String> metaContentValuesList = new ArrayList<String>(); 
		List<String> pageNamesList=new ArrayList<String>();
		List<List<String>> metaContent_T_D_Values = new ArrayList<List<String>>();
		Map<String,List<String>> pageToContentMapping = new HashMap<String, List<String>>();
		
		try {
			fis = new FileInputStream(excelFile);
			wb = new XSSFWorkbook(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum();
		int colCount = sheet.getRow(0).getLastCellNum();
		

		for(int r=1;r<=rowCount;r++) {
			XSSFRow row = sheet.getRow(r);
			if(row != null) {
				
				for(int c=0;c<colCount;c++) {
					cell=row.getCell(c,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					CellType cellType = cell.getCellType();
					
					if(domainUrl.equals(cell == null ? "Cell is null" : cell.toString())) 
						startIndex = r+1;
				}}}
		
		
		for(int domainRow = startIndex-1; domainRow<=rowCount; domainRow++) {
			XSSFRow row1 = sheet.getRow(domainRow);
			if(row1!=null) {
				for(int domainCell=1;domainCell<=1;domainCell++) {
					pageName = row1.getCell(domainCell,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					pageNamesList.add(pageName.toString());}

				for(int domainCell=2;domainCell<colCount;domainCell++) {
					metaContentCell = row1.getCell(domainCell,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					metaContentValuesList.add(metaContentCell.toString());
					
					if(metaContentValuesList.size()==2) {
						metaContent_T_D_Values.add(metaContentValuesList);
						metaContentValuesList = new ArrayList<String>();
					}}
			}else{
				lastIndex=domainRow;
				break;}
			

		for(List<String> e:metaContent_T_D_Values) {
				pageToContentMapping.put(pageName.toString().toLowerCase().trim(), e);
			}
		}
			
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(typeOfContent.toLowerCase()=="title") {
			return pageToContentMapping.get(page.toString().toLowerCase().trim()).get(0);
		}else if(typeOfContent.toLowerCase()=="description") {
			return pageToContentMapping.get(page.toString().toLowerCase().trim()).get(1);
		}else {
			return "Please provide valid details";
		}

	}
			}

	
	
