package tests;


import pageObjectModel.LoginPage;

import org.testng.Assert;
import org.testng.annotations.*;

import baseTest.BaseTest;


public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testLoginButtonDisabledWhenFieldsAreEmpty() {
        Assert.assertFalse(loginPage.isLoginButtonEnabled(), "Login button should be disabled");
    }

    @Test
    public void testPasswordMaskedButton() {
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked initially");
        loginPage.togglePasswordVisibility();
        Assert.assertFalse(loginPage.isPasswordMasked(), "Password should be visible after toggle");
    }

    @Test(priority = 1)
    public void testInvalidLoginShowErrorMsg() throws InterruptedException {
    	 
    	loginPage.clickLogin();
   
        loginPage.enterUserId("User23366");
        loginPage.enterPassword("Pass@123");
        loginPage.clickLogin();
        
        Thread.sleep(2000); 
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid"), "Invalid Credentials");
    }

    @Test
    public void testElementsPresence() {
        Assert.assertTrue(loginPage.isAllElementsPresent(), "All elements should be visible");
    }
}
