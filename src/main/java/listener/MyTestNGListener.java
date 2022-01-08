package listener;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import library.TestBase;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class MyTestNGListener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult objITestResult) {

	}

	public void onTestFailure(ITestResult objITestResult) {
		WebDriver driver = null;
		ExtentTest objExtentTest = (ExtentTest) objITestResult.getAttribute("Extent Test Object");
		objExtentTest.log(Status.FAIL, objITestResult.getThrowable().getMessage());
		Object objTestObject = objITestResult.getInstance();
		Class objClass = objITestResult.getTestClass().getRealClass();
		try {
			driver = (WebDriver) objClass.getDeclaredField("driver").get(objTestObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date objDate = new Date();
		String varScreenshotname = objITestResult.getMethod().getMethodName() + objDate.toString().replaceAll(":", "-");
		TakeScreenshot(driver, varScreenshotname);
		try {
			objExtentTest.addScreenCaptureFromPath(
					System.getProperty("user.dir") + "\\screenshots\\" + varScreenshotname + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult objITestResult) {
		ExtentTest objExtentTest = (ExtentTest) objITestResult.getAttribute("Extent Test Object");
		objExtentTest.log(Status.PASS, "TEST " + objITestResult.getMethod().getMethodName().toUpperCase() + " PASSED");
	}

}
