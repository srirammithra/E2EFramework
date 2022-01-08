package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * 
 * @author SRIRAM MITHRA
 *
 */

public class TasksPage {

	private final WebDriver driver;

	public TasksPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@id='createTasksDiv']//span[text()='Create Tasks']")
	WebElement createTasksButton;

	@FindBy(xpath = "//button[contains(text(), 'Select Customer')]")
	WebElement selectCustomerButton;

	@FindBy(xpath = "//li/a[text()='Boston Chocolate']")
	WebElement customerOption;

	@FindBy(xpath = "//button[contains(text(), 'Select Project')]")
	WebElement selectProjectButton;

	@FindBy(xpath = "//li/a[text()='Web site creation']")
	WebElement projectOption;

	@FindBy(xpath = "(//input[@placeholder='Enter task name'])[1]")
	WebElement taskNameTextbox;

	@FindBy(xpath = "(//td[@class='billingTypeCell']//button[text()='Non-Billable'])[1]")
	WebElement billingTypeButton;

	@FindBy(xpath = "//li/a[text()='testing']")
	WebElement testingTaskOption;

	@FindBy(xpath = "//div[@class='buttonIcon']/span[text()='Create Tasks']")
	WebElement addNewTask;

	public boolean ValidateTasksPage(String varTitle) {
		if (driver.getTitle().toLowerCase().equalsIgnoreCase(varTitle.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}

	public void AddNewTask(String varTaskName) throws InterruptedException {

		createTasksButton.click();
		Thread.sleep(3000);
		selectCustomerButton.click();
		Thread.sleep(2000);
		customerOption.click();
		Thread.sleep(2000);
		selectProjectButton.click();
		Thread.sleep(2000);
		projectOption.click();
		Thread.sleep(2000);
		taskNameTextbox.sendKeys(varTaskName);
		billingTypeButton.click();
		Thread.sleep(2000);
		testingTaskOption.click();
		Thread.sleep(2000);
		addNewTask.click();
		Thread.sleep(2000);
	}
}
