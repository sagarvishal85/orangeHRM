package web.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {


	    @FindBy(xpath = "//input[@placeholder='Username']")
	    private WebElement usernameField;

	    @FindBy(xpath = "//input[@placeholder='Password']")
	    private WebElement passwordField;

	    @FindBy(xpath = "//button[normalize-space()='Login']")
	    private WebElement loginButton;

	    public LoginPage(WebDriver driver) {
	        PageFactory.initElements(driver, this);
	    }

	    public void login(String username, String password) {
	        usernameField.sendKeys(username);
	        passwordField.sendKeys(password);
	        loginButton.click();
	    }
	

}
