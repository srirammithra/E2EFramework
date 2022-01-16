package library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * 
 * @author SRIRAM MITHRA
 *         https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html
 *         java -jar jenkins.war --httpPort=9090
 *         https://confluence.atlassian.com/bitbucketserver/basic-git-commands-776639767.html
 *         http://www.javabyexamples.com/set-system-property-for-tests
 *
 */

public class TestBase {

	public static ExtentReports objExtentReports;
	public static ExtentTest objExtentTest;
	public static String varBrowser;
	// public static WebDriver driver;
	public static Properties objGlobalProperties;
	public static Properties objTestDataProperties;
	public static ThreadLocal<ExtentTest> objExtentTestSync = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	// Add alwaysRun=true to avoid nullpointerexception
	@BeforeMethod(alwaysRun = true)
	public void Init(ITestResult objITestResult) {
		// Initialize Extent report
		objExtentReports = ExtentManager.GetExtentReports();
		// Create new test in the initialized report
		objExtentTest = objExtentReports.createTest(objITestResult.getMethod().getMethodName().toUpperCase());
		objExtentTestSync.set(objExtentTest);
		// Pass the objExtentTest to MyTestNGListener class
		objITestResult.setAttribute("Extent Test Object", objExtentTestSync.get());
	}

	// Add alwaysRun=true to avoid nullpointerexception
	@AfterMethod(alwaysRun = true)
	public void Quit() {
		objExtentReports.flush();
	}

	public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}

	public void GetGlobalProperties() throws IOException {
		if (objGlobalProperties == null) {
			objGlobalProperties = new Properties();
			FileInputStream objFIS = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\Global.properties");
			objGlobalProperties.load(objFIS);
		}
	}

	public void GetTestData(String varFileName) throws IOException {
		if (objTestDataProperties == null) {
			objTestDataProperties = new Properties();
			FileInputStream objFIS = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\" + varFileName);
			objTestDataProperties.load(objFIS);
		}
	}

	public void TakeScreenshot(WebDriver driver, String varName) {
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).withName(varName)
				.save(System.getProperty("user.dir") + "\\screenshots");
	}

	public WebDriver LaunchBrowser() throws FileNotFoundException, IOException {
		GetGlobalProperties();
		// Read the browser param from surefire plugin
		if (System.getProperty("executionBrowser") != null) {
			varBrowser = System.getProperty("executionBrowser");
		} else {
			varBrowser = objGlobalProperties.getProperty("browser").toUpperCase();
		}
		System.out.println("BROWSER: " + varBrowser);
		switch (varBrowser) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.addArguments("--disable-notifications");
			objChromeOptions.addArguments("--start-maximized");
			objChromeOptions.addArguments("ignore-certificate-errors");
			driver.set(new ChromeDriver(objChromeOptions));
			// driver = new ChromeDriver(objChromeOptions);
			break;

		case "EDGE":
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
			EdgeOptions objEdgeOptions = new EdgeOptions();
			objEdgeOptions.addArguments("--disable-notifications");
			objEdgeOptions.addArguments("--start-maximized");
			objEdgeOptions.addArguments("ignore-certificate-errors");
			driver.set(new EdgeDriver(objEdgeOptions));
			// driver = new EdgeDriver(objEdgeOptions);
			break;

		case "FIREFOX":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			FirefoxOptions objFirefoxOptions = new FirefoxOptions();
			FirefoxProfile objFirefoxProfile = new FirefoxProfile();
			objFirefoxProfile.setPreference("dom.webnotifications.enabled", false);
			objFirefoxProfile.setAcceptUntrustedCertificates(true);
			objFirefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			objFirefoxOptions.setProfile(objFirefoxProfile);
			driver.set(new FirefoxDriver(objFirefoxOptions));
			// driver = new FirefoxDriver(objFirefoxOptions);
			break;
		}

		return getDriver();
	}

	public void OpenApp() {
		getDriver().get(objGlobalProperties.getProperty("url"));
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	}

}
