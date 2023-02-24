package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class HomePage extends BasePage{

    private final static By PROJECTS_TAB = By.xpath("//a[text()='Projects']");
    private final static By WORKSPACE_TAB = By.xpath("//a[text()='Workspace']");
    private final static By USER_ICON = By.cssSelector(".Eb2vGG");
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

    public void clickOnUserIcon() {
        log.info("clicking on user icon");
        driver.findElement(USER_ICON).click();
    }
}
