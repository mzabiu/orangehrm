package com.orangehrm.framework.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;
	private ExtentTest extentTest;

	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String dir = System.getProperty("user.dir") + "/reports/";

		new File(dir).mkdirs();
		String path = dir + fileName;
		htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setDocumentTitle("Automation Reports for OrangeHRM");
		htmlReporter.config().setReportName("Automation Reports");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Organisation", "Selenium Training");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		return extent;
	}

	public static String getReportName() {

		Date date = new Date();
		return "Automation Reports " + date.toString().replace(":", "_").replace(",", "_") + ".html";

	}

}
