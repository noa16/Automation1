package com.w2a.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent==null) {
			
			extent = new ExtentReports("C:\\Users\\noa\\eclipse-workspace\\DataDrivenFramework\\target\\surefire-reports\\html\\extent.html",true,DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File("C:\\Users\\noa\\eclipse-workspace\\DataDrivenFramework\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}

}
