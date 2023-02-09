package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class HomePage extends BasePage{

    private final static By PROJECT_ICON = By.cssSelector(".text-center.project-icon");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void waitForProjectIconDisplayed() {
        log.info("Wating for project icon will be loaded");
        waitForElementDisplayed(PROJECT_ICON);
    }

    public boolean isProjectIconDisplayed() {
        log.info("Checking if project icon is loaded and displayed");
        return driver.findElement(PROJECT_ICON).isDisplayed();
    }
}
