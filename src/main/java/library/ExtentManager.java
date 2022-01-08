package library;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class ExtentManager {

	public static ExtentReports objExtentReports;
	public static ExtentSparkReporter objExtentSparkReporter;

	public static ExtentReports GetExtentReports() {
		if (objExtentReports == null) {
			objExtentReports = new ExtentReports();
			Date objDate = new Date();
			String varReportFolder = objDate.toString().replaceAll(":", "-");
			String varReportPath = System.getProperty("user.dir") + "\\reports\\";
			File objFile = new File(varReportPath + varReportFolder);
			objFile.mkdir();
			objExtentSparkReporter = new ExtentSparkReporter(varReportPath + varReportFolder);
			objExtentSparkReporter.config().setReportName("Selenium Framework Report");
			objExtentSparkReporter.config().setDocumentTitle("Selenium Tests");
			objExtentSparkReporter.config().setTheme(Theme.DARK);
			objExtentSparkReporter.config().setEncoding("utf-8");
			objExtentReports.attachReporter(objExtentSparkReporter);
		}
		return objExtentReports;
	}

}
