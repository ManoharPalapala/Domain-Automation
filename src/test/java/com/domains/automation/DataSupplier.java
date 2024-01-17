package com.domains.automation;

import org.testng.annotations.DataProvider;

import com.domainAutomation.utils.ExcelUtils;
import com.domainAutomation.utils.Utils;

public class DataSupplier {

	@DataProvider(name="supplier")
	public Object[][] dataSupplier() {
//		Object[][] excelSheets = {Utils.readDataFromExcel("list"), Utils.readDataFromExcel("metaContent")};

		return Utils.readDataFromExcel("list");
	}
	
}
