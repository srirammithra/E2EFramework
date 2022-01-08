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
import pageobjects.UsersPage;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class TC04CreateNewUserTest extends TestBase {

	public static WebDriver driver;

	@Test
	public void TC04CreateNewUser() throws FileNotFoundException, IOException, InterruptedException {
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
		objHomePage.ClickUsers();
		Thread.sleep(5000);
		UsersPage objUsersPage = PageFactory.initElements(driver, UsersPage.class);
		objExtentTestSync.get().log(Status.INFO, "Users Page opened");
		Thread.sleep(5000);
		objExtentTestSync.get().log(Status.INFO, "Adding new user");
		objUsersPage.AddNewUser(objTestDataProperties.getProperty("FirstName"),
				objTestDataProperties.getProperty("MiddleName"), objTestDataProperties.getProperty("LastName"),
				objTestDataProperties.getProperty("Email"), objTestDataProperties.getProperty("UserName"),
				objTestDataProperties.getProperty("Password"));
	}

}
