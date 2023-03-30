package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class HomePage extends BasePage {

    private final static By PROJECTS_TAB = By.xpath("//a[text()='Projects']");
    private final static By WORKSPACE_TAB = By.xpath("//a[text()='Workspace']");
    private final static By USER_ICON = By.xpath("//span[@aria-label='Chat']/following-sibling::span/img");
    private final static By SIGN_OUT_BUTTON = By.xpath("//span[text()='Sign out']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProjectsTab() {
        log.info("clicking on projects tab");
        driver.findElement(PROJECTS_TAB).click();
    }

    public void clickOnWorkspaceTab() {
        log.info("clicking on workspace tab");
        driver.findElement(WORKSPACE_TAB).click();
    }

    private void clickOnUserIcon() {
        log.info("clicking on user icon");
        driver.findElement(USER_ICON).click();
    }

    public void clickSignOutButton() {
        clickOnUserIcon();
        log.info("clicking on sign out button");
        driver.findElement(SIGN_OUT_BUTTON).click();
    }
}
