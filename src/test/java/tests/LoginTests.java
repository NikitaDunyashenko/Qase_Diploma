package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Severity(SeverityLevel.BLOCKER)
    @Description("checking the possibility to login after entering the correct email and password")
    @Test(groups = {"smoke", "positive"}, retryAnalyzer = RetryAnalyzer.class)
    public void positiveLoginTest() {
        loginPage.setUserNameInput(USER_NAME)
                .setPasswordInput(PASSWORD)
                .clickLoginButton()
                .waitForProjectIconDisplayed();
        Assert.assertTrue(projectsPage.isProjectIconDisplayed());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("checking if the error message appears after entering the incorrect values")
    @Test(groups = {"smoke", "negative"}, dataProvider = "negativeLoginData", retryAnalyzer = RetryAnalyzer.class)
    public void negativeLoginTestOneFailed(String userName, String password, String errorMessage) {
        loginPage.setUserNameInput(userName)
                .setPasswordInput(password)
                .clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }

    @DataProvider()
    public Object[][] negativeLoginData() {
        return new Object[][] {
                {"nik12345@mail.com", "12345678", "These credentials do not match our records."},
                {"nik12345@mail.com", "12345678", "These credentials do not match."},
        };
    }
}
