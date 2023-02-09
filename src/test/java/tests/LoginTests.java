package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Severity(SeverityLevel.BLOCKER)
    @Description("checking the possibility to login after entering the correct email and password")
    @Test(groups = {"smoke", "positive"})
    public void positiveLoginTest() {
        loginPage.setUserNameInput(USER_NAME)
                .setPasswordInput(PASSWORD)
                .clickLoginButton()
                .waitForProjectIconDisplayed();
        Assert.assertTrue(homePage.isProjectIconDisplayed());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("checking if the error message appears after entering the incorrect values")
    @Test(groups = {"smoke", "negative"})
    public void negativeLoginTest() {
        loginPage.setUserNameInput("nik12345@mail.com")
                .setPasswordInput("12345678")
                .clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(), "These credentials do not match our records.");
    }
}
