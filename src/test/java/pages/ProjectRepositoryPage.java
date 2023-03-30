package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Log4j2
public class ProjectRepositoryPage extends BaseProjectPage {

    private final static By CREATE_NEW_SUITE_BUTTON = By.id("create-suite-button");
    private final static By CREATE_NEW_CASE_BUTTON = By.id("create-case-button");
    private final static String SUITE_NAME_LOCATOR = "//span[text()='%s']";
    private final static By EDIT_SUITE_BUTTON = By.xpath("//i[@class='far fa-pencil']/ancestor::button");
    private final static By ADD_NEW_CASE_ITEM = By.xpath("//i[@class='fas fa-plus']/ancestor::button");
    private final static String CREATE_NEW_ITEM = "//*[text()='%s']/descendant::i";
    private final static String TEST_CASE_BUTTON = "//div[text()='%s']";

    public ProjectRepositoryPage(WebDriver driver) {
        super(driver);
    }

    public void waitForCreateNewSuiteIsDisplayed() {
        log.info("waiting for create new suite button will be displayed");
        waitForElementDisplayed(CREATE_NEW_SUITE_BUTTON);
    }

    public void clickCreateNewSuiteButton() {
        log.info("clicking create new suite button");
        driver.findElement(CREATE_NEW_SUITE_BUTTON).click();
    }

    public void clickToEditSuiteName(String suiteName) {
        Actions actions = new Actions(driver);
        WebElement suiteNameElement = driver.findElement(By.xpath(String.format(SUITE_NAME_LOCATOR, suiteName)));
        log.info(String.format("moving mouse to the following element: %s", suiteNameElement));
        actions.moveToElement(suiteNameElement)
                .build()
                .perform();
        log.info("clicking edit suite button");
        driver.findElement(EDIT_SUITE_BUTTON).click();
    }

    public void clickToAddNewItem(String suiteName) {
        Actions actions = new Actions(driver);
        WebElement suiteNameElement = driver.findElement(By.xpath(String.format(SUITE_NAME_LOCATOR, suiteName)));
        log.info(String.format("moving mouse to the following element: %s", suiteNameElement));
        actions.moveToElement(suiteNameElement)
                .build()
                .perform();
        log.info("clicking add new case button");
        driver.findElement(ADD_NEW_CASE_ITEM).click();
    }

    public void createNewItem(String itemName) {
        log.info(String.format("clicking to create new %s", itemName));
        driver.findElement(By.xpath(String.format(CREATE_NEW_ITEM, itemName))).click();
    }

    public void clickOnSpecificTestCase(String value) {
        log.info(String.format("clicking on specific test case: %s", value));
        driver.findElement(By.xpath(String.format(TEST_CASE_BUTTON, value))).click();
    }
}
