package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.TestBase;

//common methods
public class TestUtil extends TestBase{
	
	public static String screenShotPath ;
	public static String screenshotName;
	public static void captureScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date d = new Date();//the name of the screen shot will be the data and the time
		screenshotName=  d.toString().replace(":", "_").replace(" ", "_");;
		//copy the screenshot to a specific path:
		FileUtils.copyFile(scrFile,new File("C:\\Users\\noa\\eclipse-workspace\\DataDrivenFramework\\target\\surefire-reports\\html\\"+screenshotName+".jpg"));
	
	}
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;
		
	}
	
	public static boolean isTestRunnable(String testName,ExcelReader excel) {
		
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		for(int rNum=2;rNum<=rows;rNum++) {
			String testCase = excel.getCellData(sheetName,"TCID" , rNum);
			if(testCase.equalsIgnoreCase(testName)) {//if the data from the excel is equel the data we provide to out func
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
			    if(runmode.equalsIgnoreCase("Y"))
			    	return(true);
			    else
			    	return false;
			
			}
		}
		return false;
	}
	

}
