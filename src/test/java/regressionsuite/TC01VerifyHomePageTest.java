package regressionsuite;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import library.TestBase;
import pageobjects.HomePage;
import pageobjects.LoginPage;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class TC01VerifyHomePageTest extends TestBase {

	public static WebDriver driver;

	@Test
	public void TC01VerifyHomePage() throws FileNotFoundException, IOException, InterruptedException {
		driver = LaunchBrowser();
		GetTestData("TestData.properties");
		OpenApp();
		Thread.sleep(5000);
		LoginPage objLoginPage = PageFactory.initElements(driver, LoginPage.class);
		objExtentTestSync.get().log(Status.INFO, "Login Page opened");
		objLoginPage.AppLogin(objTestDataProperties.getProperty("Uname"), objTestDataProperties.getProperty("Pwd"));
		Thread.sleep(5000);
		HomePage objHomePage = PageFactory.initElements(driver, HomePage.class);
		objExtentTestSync.get().log(Status.INFO, "Home Page opened");
		objHomePage.ValidateHomePage(objTestDataProperties.getProperty("HomePageTitle"));
	}

}
