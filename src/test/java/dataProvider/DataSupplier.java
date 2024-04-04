package dataProvider;

import org.testng.annotations.DataProvider;

import utilities.DataFileHandler;

public class DataSupplier {
	
	DataFileHandler dfh = new DataFileHandler();

	@DataProvider(name="supplier")
	public Object[][] dataSupplier() {return dfh.readDataFromExcel(dfh.readDataFromPropFile("sheetname"));}
	

}
