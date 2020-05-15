package com.w2a.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = rep.startTest(result.getName().toUpperCase());//generate test by his name
		//runmodes
		//if(!TestUtil.isTestRunnable(result.getName(), excel)) {
			//false
		//	throw new SkipException("Skipping the test"+result.getName().toUpperCase()+"as the run mode is NO");
	//	}
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(LogStatus.PASS, result.getName().toLowerCase()+"PASS");
		rep.endTest(test);
		rep.flush();//terminate report
	}

	public void onTestFailure(ITestResult result) {
		
		//in a time of failure we tale a screenshot 
		
		
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reporting.escape-output", "false");
		Reporter.log("test failed!");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
		test.log(LogStatus.FAIL, result.getName().toLowerCase()+"Failed with exception:"+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		
		Reporter.log("Click to see Screenshot");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");//fix this!!
		Reporter.log("<br>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></img></a>");
		rep.endTest(test);
		rep.flush();//terminate report
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(LogStatus.SKIP, result.getInstanceName().toUpperCase()+"Skipped the test as the rum mode is NO");
		rep.endTest(test);
		rep.flush();//terminate report
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
