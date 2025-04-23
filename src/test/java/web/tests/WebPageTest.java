package web.tests;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.*;


import web.pages.*;
import web.utils.EnvReader;
import web.utils.WebDriverManagerUtil;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class WebPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverManagerUtil.getDriver();
        driver.get(EnvReader.get("BASE_URL"));
        loginPage = new LoginPage(driver);
        recruitmentPage = new RecruitmentPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Test(priority = 0)
    public void testLoginNegative() {
        loginPage.login("wronguser", "wrongpass");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement error = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
        Assert.assertTrue(error.isDisplayed(), "Error message not displayed for invalid login.");
    }


    @Test(priority = 1)
    public void testLoginPositive() {
        String username = EnvReader.get("HRM_USERNAME");
        String password = EnvReader.get("HRM_PASSWORD");
        loginPage.login(username, password);
//        System.out.println(driver.getTitle());
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test(priority = 2, dependsOnMethods = "testLoginPositive")
    public void testCandidateNavigation() {
    	recruitmentPage.navigateToCandidates();
    	Assert.assertTrue(driver.getCurrentUrl().contains("recruitment"));
    }
    
    
    @Test(priority = 3 , dependsOnMethods = "testCandidateNavigation")
    public void testAddCandidate() throws InterruptedException, AWTException, IOException {
    	
    	String firstName = "John";
    	String middleName = "Jacob";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String contact = "1234567890";
        String resumePath = EnvReader.get("RESUME_PATH");
        recruitmentPage.addCandidate(firstName, middleName, lastName, email, contact, resumePath);        
//        Thread.sleep(2000); 
        verifyFormSubmission();
        takeScreenshot("CandidateProfile_" + firstName + middleName + lastName);
//        verifyResumeUploaded();
        
    }
    
    private void verifyFormSubmission() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='oxd-toast-content oxd-toast-content--success']")
        ));
        Assert.assertTrue(toast.getText().contains("Successfully Saved"), "Candidate not saved successfully.");
    }

    private void verifyResumeUploaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resumeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@class='orangehrm-file-preview']")
        ));
        Assert.assertTrue(resumeLink.isDisplayed(), "Resume is not properly uploaded or linked.");
    }

    private void takeScreenshot(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File("screenshots/" + fileName + ".png");
        FileUtils.copyFile(screenshot, targetFile);
        System.out.println("Screenshot saved: " + targetFile.getAbsolutePath());
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
