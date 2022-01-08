package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class HomePage {

	private final WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//td/a/div[text()='USERS']")
	WebElement usersLink;

	@FindBy(xpath = "//td/a/div[text()='TASKS']")
	WebElement tasksLink;

	public boolean ValidateHomePage(String varTitle) {
		if (driver.getTitle().toLowerCase().equalsIgnoreCase(varTitle.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	public void ClickUsers() {
		usersLink.click();
	}

	public void ClickTasks() {
		tasksLink.click();
	}
}
