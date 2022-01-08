package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class UsersPage {

	private final WebDriver driver;

	public UsersPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='buttonText' and text()='Add User']")
	WebElement addUserButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@name='middleName']")
	WebElement middleNameTextbox;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@name='email']")
	WebElement emailTextbox;

	@FindBy(xpath = "//input[@name='username']")
	WebElement usernameTextbox;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordTextbox;

	@FindBy(xpath = "//input[@name='passwordCopy']")
	WebElement passwordretypeTextbox;

	@FindBy(xpath = "//div/span[@class='buttonTitle' and text()='Create User']")
	WebElement createUserButton;

	public boolean ValidateUsersPage(String varTitle) {
		if (driver.getTitle().toLowerCase().equalsIgnoreCase(varTitle.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	public void AddNewUser(String varFirstName, String varMiddleName, String varLastName, String varEmail,
			String varUserName, String varPassword) throws InterruptedException {

		addUserButton.click();
		Thread.sleep(3000);
		firstNameTextbox.sendKeys(varFirstName);
		middleNameTextbox.sendKeys(varMiddleName);
		lastNameTextbox.sendKeys(varLastName);
		emailTextbox.sendKeys(varEmail);
		usernameTextbox.sendKeys(varUserName);
		passwordTextbox.sendKeys(varPassword);
		passwordretypeTextbox.sendKeys(varPassword);
		createUserButton.click();
		Thread.sleep(3000);
	}

}
