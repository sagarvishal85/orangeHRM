package web.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;


public class RecruitmentPage {

	    @FindBy(xpath = "//span[text()='Recruitment']")
	    private WebElement candidatesMenu;

	    @FindBy(xpath = "//button[normalize-space()='Add']")
	    private WebElement addCandidateButton;

	    @FindBy(xpath = "//input[@placeholder='First Name']")
	    private WebElement firstNameField;
	    
	    @FindBy(xpath = "//input[@placeholder='Middle Name']")
	    private WebElement middleNameField;

	    @FindBy(xpath = "//input[@placeholder='Last Name']")
	    private WebElement lastNameField;
	    
	    @FindBy(xpath = "//div[@class='oxd-select-text-input']")
	    private WebElement roleDropDownField;
	    
	    @FindBy(xpath = "//span[text()='Senior QA Lead']")
	    private WebElement jobRoleField;

	    @FindBy(xpath = "(//input[@placeholder='Type here'])[1]")
	    private WebElement emailField;

	    @FindBy(xpath = "(//input[@placeholder='Type here'])[2]")
	    private WebElement contactNoField;
	    
	    @FindBy(xpath = "//div[@class='oxd-file-button']")
	    private WebElement resumeUploadField;

	    @FindBy(xpath = "//button[normalize-space()='Save']")
	    private WebElement saveButton;

	    public RecruitmentPage(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	    }

	    public void navigateToCandidates() {
	        candidatesMenu.click();
	    }

	    public void addCandidate(String firstName,String middleName, String lastName, String email, String contactNo, String resumePath) throws AWTException {
	        addCandidateButton.click();
	        firstNameField.sendKeys(firstName);
	        middleNameField.sendKeys(middleName);
	        lastNameField.sendKeys(lastName);
	        roleDropDownField.click();
	        jobRoleField.click();
	        emailField.sendKeys(email);
	        contactNoField.sendKeys(contactNo);
	        resumeUploadField.click();
	        uploadFileUsingRobot(resumePath);
	        saveButton.click();
	    }
	    
	    public void uploadFileUsingRobot(String filePath) throws AWTException {
		    // Copy file path to clipboard
		    StringSelection selection = new StringSelection(filePath);
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		    Robot robot = new Robot();
		    robot.delay(1000);

		    // Press CTRL + V
		    robot.keyPress(KeyEvent.VK_CONTROL);
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_CONTROL);

		    robot.delay(500);

		    // Press ENTER
		    robot.keyPress(KeyEvent.VK_ENTER);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		}
	

}
