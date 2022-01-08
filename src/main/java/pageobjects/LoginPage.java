package pageobjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class LoginPage {

	private final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@name='username']")
	WebElement UsernameTextbox;

	@FindBy(xpath = "//input[@name='pwd']")
	WebElement PasswordTextbox;

	@FindBy(xpath = "//td[@id='loginButtonContainer']//div[contains(text(),'Login')]")
	WebElement LoginButton;

	public void AppLogin(String varUsername, String varPassword) {
		try {
			UsernameTextbox.sendKeys(varUsername);
			PasswordTextbox.sendKeys(varPassword);
			LoginButton.click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
