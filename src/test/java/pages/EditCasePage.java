package pages;

import enums.*;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class EditCasePage extends BaseProjectPage{

    private final static String INPUT_LOCATOR = "//label[text()='%s']/parent::div//following-sibling::div//input";
    private final static String DATA_PLACEHOLDER_LOCATOR = "//label[text()='%s']//parent::div//following-sibling::div//p[@class]";
    private final static String DROPDOWN_OPTION_LOCATOR = "//label[text()='%s']//parent::div//div[@class='RZYgph']";
    private final static String DATA_PLACEHOLDER_FOR_STEPS_LOCATOR = "//div[@title='%d']//parent::div/parent::div/following-sibling::div//p[text()='%s']//parent::div/parent::div/parent::div/following-sibling::div//p";
    private final static By LOADING_ELEMENT = By.xpath("//span[contains(text(),'Loading')]");

    public EditCasePage(WebDriver driver) {
        super(driver);
    }

    public void waitForLoadingElementDisplays() {
        log.info("waiting for loading element will be loaded");
        waitForElementDisplayed(LOADING_ELEMENT);
    }

    public void waitForLoadingElementDisappears() {
        log.info("waiting for loading element will be disappeared");
        waitForElementDisappears(LOADING_ELEMENT);
    }

    public String getInputValue(String labelName) {
        log.info(String.format("getting value from input field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(INPUT_LOCATOR, labelName))).getAttribute("value");
    }

    public String getDataPlaceholderValue(String labelName) {
        log.info(String.format("getting value from data placeholder field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_LOCATOR, labelName))).getText();
    }

    public String getDropdownOptionValue(String labelName) {
        log.info(String.format("getting value from dropdown field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DROPDOWN_OPTION_LOCATOR, labelName))).getText();
    }

    public String getDataPlaceholderForStepsValue(int numberOfSteps, String labelName) {
        log.info(String.format("getting value from data placeholder for steps field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_FOR_STEPS_LOCATOR, numberOfSteps, labelName))).getText();
    }

    public TestCase getTestCaseDetails() {
        log.info("getting fields' values from test case edit page");
        TestCase.TestCaseBuilder testCase = new TestCase.TestCaseBuilder();
        testCase.setTitle(new EditCasePage(driver).getInputValue("Title"));
        testCase.setParameterTitle(new EditCasePage(driver).getInputValue("Parameter title"));
        testCase.setParameterValues(new EditCasePage(driver).getInputValue("Parameter values"));
        testCase.setDescription(new EditCasePage(driver).getDataPlaceholderValue("Description"));
        testCase.setPreConditions(new EditCasePage(driver).getDataPlaceholderValue("Pre-conditions"));
        testCase.setPostConditions(new EditCasePage(driver).getDataPlaceholderValue("Post-conditions"));
        testCase.setStatus(Status.fromString(getDropdownOptionValue("Status")));
        testCase.setSuite(new EditCasePage(driver).getDropdownOptionValue("Suite"));
        testCase.setSeverity(Severity.fromString(getDropdownOptionValue("Severity")));
        testCase.setPriority(Priority.fromString(getDropdownOptionValue("Priority")));
        testCase.setType(Type.fromString(getDropdownOptionValue("Type")));
        testCase.setLayer(Layer.fromString(getDropdownOptionValue("Layer")));
        testCase.setIsFlaky(IsFlaky.fromString(getDropdownOptionValue("Is flaky")));
        testCase.setBehavior(Behavior.fromString(getDropdownOptionValue("Behavior")));
        testCase.setAutomationStatus(AutomationStatus.fromString(getDropdownOptionValue("Automation status")));
        testCase.setStepAction(new EditCasePage(driver).getDataPlaceholderForStepsValue(1, "Step Action"));
        testCase.setData(new EditCasePage(driver).getDataPlaceholderForStepsValue(1, "Data"));
        testCase.setExpectedResult(new EditCasePage(driver).getDataPlaceholderForStepsValue(1, "Expected result"));
        return testCase.build();
    }
}
