package pageObjectModel;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath="//input[@placeholder='Enter your User ID']")
    private WebElement userIdInput;

    @FindBy(xpath = "//input[@placeholder='Enter your Password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Log In']")
    private WebElement loginButton;

    @FindBy(css = "img.passowrd-visible") 
    private WebElement passwordEyeIcon;

    @FindBy(xpath="//p[text()='Invalid Credentials']") 
    private WebElement errorMessage;

    // Actions
    public void enterUserId(String userId) {
    	 
        userIdInput.clear();
        userIdInput.sendKeys(userId);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
        
    }
    

    public boolean isLoginButtonEnabled() {
        return loginButton.isEnabled();
    }

    public boolean isPasswordMasked() {
        return passwordInput.getAttribute("type").equals("password");
    }

    public void togglePasswordVisibility() {
        passwordEyeIcon.click();
    }

    public String getErrorMessage() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

    public boolean isAllElementsPresent() {
        return userIdInput.isDisplayed() &&
               passwordInput.isDisplayed() &&
               loginButton.isDisplayed() &&
               passwordEyeIcon.isDisplayed();
    }
}

