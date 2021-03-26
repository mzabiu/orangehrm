package com.orangehrm.framework.listeners;

import java.io.File;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orangehrm.framework.reports.ExtentManager;

public class TestListeners implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance();
	private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + ">>" + result.getMethod().getMethodName());
		extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {

		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " successfull </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get()
				.fail("<details><summary><b><font color=red>" + "Exception Occured, click here to see the details: "
						+ "</font></b></summary>" + exceptionMessage.replace(",", "<br>") + "</details>\n");
		EventFiringWebDriver driver = (EventFiringWebDriver) result.getTestContext().getAttribute("WebDriver");

		String path = takeScreenshot(driver, result.getMethod().getMethodName());

		try {
			extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		} catch (Exception e) {
			extentTest.get().fail("Test Failed, cannot attach screen shot");
		}

		String logText = "<b>Test Method " + methodName + " Failed <b>";

		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);

		extentTest.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		if (extent != null) {
			extent.flush();
		}

	}

	private String takeScreenshot(EventFiringWebDriver driver, String methodName) {

		String fileName = getScreenShotName(methodName);

		String dir = "./src/main/resources/com/orangehrm/screenshots/";
		new File(dir).mkdirs();

		String path = dir + fileName;
		try {
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	private String getScreenShotName(String methodName) {
		Date date = new Date();
		return methodName + date.toString().replace(":", "_").replace(",", "_") + ".png";

	}

}
