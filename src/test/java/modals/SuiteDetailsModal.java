package modals;

import elements.DataPlaceholder;
import elements.Dropdown;
import elements.Input;
import enums.ParentSuite;
import lombok.extern.log4j.Log4j2;
import models.Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class SuiteDetailsModal extends BaseModal{

    private final static String LABEL_LOCATOR = "//label[text()='%s']";
    private final static String VALUE_LOCATOR = ".//parent::div//following-sibling::div//%s";
    private final static By PARENT_SUITE = By.xpath("//label[text()='Parent suite']/parent::div//following-sibling::div//div");
    private final static By CLOSE_MODAL_SIGN = By.xpath("//*[@data-icon='xmark']/parent::button");

    public SuiteDetailsModal(WebDriver driver) {
        super(driver);
    }

    public void waitForParentSuiteDisplays() {
        log.info("waiting for text Project root displays");
        wait.until(ExpectedConditions.textToBe(PARENT_SUITE, "Project root"));
    }

    public Suite getSuiteDetails() {
        log.info("getting suite values");
        Suite.SuiteBuilder suite = new Suite.SuiteBuilder();
        suite.setParentSuite(ParentSuite.fromString(getDataPlaceholderAndDropdownValue("Parent suite", "div")));
        suite.setSuiteName(new SuiteDetailsModal(driver).getInputValue("Suite name", "input"));
        suite.setDescription(new SuiteDetailsModal(driver).getDataPlaceholderAndDropdownValue("Description", "p[@class]"));
        suite.setPreconditions(new SuiteDetailsModal(driver).getDataPlaceholderAndDropdownValue("Preconditions", "p[@class]"));
        return suite.build();
    }

    public String getDataPlaceholderAndDropdownValue(String labelName, String tagName) {
        log.debug(String.format("getting text data from the field with the following values: %s, %s", labelName, tagName));
        return driver.findElement(getLabelLocator(labelName)).findElement(getValueLocator(tagName)).getText();
    }

    public String getInputValue(String labelName, String tagName) {
        log.debug(String.format("getting text data from the field with the following values: %s, %s", labelName, tagName));
        return driver.findElement(getLabelLocator(labelName)).findElement(getValueLocator(tagName)).getAttribute("value");
    }

    private By getLabelLocator(String labelName) {
        log.info(String.format("getting locator of the following field: %s", labelName));
        return By.xpath(String.format(LABEL_LOCATOR, labelName));
    }

    private By getValueLocator(String tagName) {
        log.info(String.format("getting locator of the following tagname: %s", tagName));
        return By.xpath(String.format(VALUE_LOCATOR, tagName));
    }

    public void fillEditForm(Suite suite) {
        log.info("filling the edit form with values");
        new Dropdown(driver, "Parent suite").chooseDropdownValue(suite.getParentSuite().getName());
        new Input(driver, "Suite name").setInputValue(suite.getSuiteName());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(suite.getDescription());
        new DataPlaceholder(driver, "Preconditions").setDataPlaceholderValue(suite.getPreconditions());
    }

    public void clickCloseModalSign() {
        log.info("clicking close modal button");
        driver.findElement(CLOSE_MODAL_SIGN).click();
    }

}
