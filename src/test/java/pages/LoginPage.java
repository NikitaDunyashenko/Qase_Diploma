package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage{

    private final static By USER_NAME_INPUT = By.id("inputEmail");
    private final static By PASSWORD_INPUT = By.id("inputPassword");
    private final static By LOGIN_BUTTON = By.id("btnLogin");
    private final static By ERROR_MESSAGE = By.xpath("//*[@class='form-control-feedback']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setUserNameInput(String USER_NAME) {
        log.info(String.format("Entering the user name to input: %s", USER_NAME));
        driver.findElement(USER_NAME_INPUT).sendKeys(USER_NAME);
        return this;
    }

    public LoginPage setPasswordInput(String PASSWORD) {
        log.info(String.format("Entering the password to input: %s", PASSWORD));
        driver.findElement(PASSWORD_INPUT).sendKeys(PASSWORD);
        return this;
    }

    public ProjectsPage clickLoginButton() {
        log.info("Clicking the login button");
        driver.findElement(LOGIN_BUTTON).click();
        return new ProjectsPage(driver);
    }

    public String getErrorMessage() {
        log.info("Getting the error message when the user name or password were entered incorrectly");
        return driver.findElement(ERROR_MESSAGE).getText();
    }

}
