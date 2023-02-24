package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectRepositoryPage extends BaseProjectPage{

    private final static By CREATE_NEW_SUITE_BUTTON = By.id("create-suite-button");
    private final static By CREATE_NEW_CASE_BUTTON = By.id("create-case-button");
    public ProjectRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void waitForCreateNewSuiteIsDisplayed() {
        log.info("waiting for create new suite button will be displayed");
        waitForElementDisplayed(CREATE_NEW_SUITE_BUTTON);
    }
}
