package dataProvider;

import org.testng.annotations.DataProvider;

import utilities.ExcelHandler;
import utilities.PropHandler;

public class DataSupplier {
	
	ExcelHandler dfh = new ExcelHandler();
	PropHandler ph = new PropHandler();

	@DataProvider(name="supplier")
	public Object[][] dataSupplier() {return dfh.readDataFromExcel(ph.readDataFromPropFile("sheetname"));}
	

}
