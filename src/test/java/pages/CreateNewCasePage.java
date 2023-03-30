package pages;

import elements.DataPlaceholder;
import elements.DataPlaceholderForSteps;
import elements.DropdownForCase;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CreateNewCasePage extends BaseProjectPage {

    private final static By ADD_PARAMETER_BUTTON = By.xpath("//span[text()='Add parameter']/parent::button");
    private final static By ADD_STEP_BUTTON = By.xpath("//span[text()=' Add step']/parent::button");
    private final static By SAVE_BUTTON = By.id("save-case");

    public CreateNewCasePage(WebDriver driver) {
        super(driver);
    }

    public void clickAddParameterButton() {
        log.info("clicking add parameter button");
        scrollToElement(driver.findElement(ADD_PARAMETER_BUTTON));
        driver.findElement(ADD_PARAMETER_BUTTON).click();
    }

    public void clickAddStepButton() {
        log.info("clicking add step button");
        scrollToElement(driver.findElement(ADD_STEP_BUTTON));
        driver.findElement(ADD_STEP_BUTTON).click();
    }

    public void clickSaveCaseButton() {
        log.info("clicking save test case button");
        driver.findElement(SAVE_BUTTON).click();
    }

    public void fillForm(TestCase testCase) {
        log.info("filling values into the create new test case page");
        new Input(driver, "Title").setInputValue(testCase.getTitle());
        new Input(driver, "Parameter title").setInputValue(testCase.getParameterTitle());
        new Input(driver, "Parameter values").setInputValue(testCase.getParameterValues());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(testCase.getDescription());
        new DataPlaceholder(driver, "Pre-conditions").setDataPlaceholderValue(testCase.getPreConditions());
        new DataPlaceholder(driver, "Post-conditions").setDataPlaceholderValue(testCase.getPostConditions());
        new DropdownForCase(driver, "Status").chooseDropdownOption(testCase.getStatus().getName());
        new DropdownForCase(driver, "Suite").chooseDropdownOption(testCase.getSuite());
        new DropdownForCase(driver, "Severity").chooseDropdownOption(testCase.getSeverity().getName());
        new DropdownForCase(driver, "Priority").chooseDropdownOption(testCase.getPriority().getName());
        new DropdownForCase(driver, "Type").chooseDropdownOption(testCase.getType().getName());
        new DropdownForCase(driver, "Layer").chooseDropdownOption(testCase.getLayer().getName());
        new DropdownForCase(driver, "Is flaky").chooseDropdownOption(testCase.getIsFlaky().getName());
        new DropdownForCase(driver, "Behavior").chooseDropdownOption(testCase.getBehavior().getName());
        new DropdownForCase(driver, "Automation status").chooseDropdownOption(testCase.getAutomationStatus().getName());
        new DataPlaceholderForSteps(driver, 1, "Step Action").setDataPlaceholderForStepsValue(testCase.getStepAction());
        new DataPlaceholderForSteps(driver, 1, "Data").setDataPlaceholderForStepsValue(testCase.getData());
        new DataPlaceholderForSteps(driver, 1, "Expected result").setDataPlaceholderForStepsValue(testCase.getExpectedResult());

    }

}
