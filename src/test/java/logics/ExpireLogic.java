package logics;

import org.testng.annotations.Test;

import dataProvider.DataSupplier;
import scripts.MultiExecution_T_C;

public class ExpireLogic extends MultiExecution_T_C{

	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void qualityhirings(String URL) {
		sf.assertTrue(networkStatus(URL)==200,URL);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void spotopenings(String URL) {
		sf.assertTrue(networkStatus(URL)==404,URL);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void nearbynurses(String URL) {
		sf.assertTrue(networkStatus(URL)==404,URL);
		sf.assertAll();
	}
	
	@Test(dataProvider="supplier",dataProviderClass=DataSupplier.class)
	public void recruitzme(String URL) {
		sf.assertTrue(networkStatus(URL)==410,URL);
		sf.assertAll();
	}
	
	
}
