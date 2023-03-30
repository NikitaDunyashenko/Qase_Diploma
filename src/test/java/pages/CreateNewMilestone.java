package pages;

import elements.DataPlaceholder;
import elements.Dropdown;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CreateNewMilestone extends BaseProjectPage {

    private final static By SAVE_BUTTON = By.cssSelector("[type=submit]");

    public CreateNewMilestone(WebDriver driver) {
        super(driver);
    }

    public void fillForm(Milestone milestone) {
        log.info("filling milestone fields with values");
        new Input(driver, "Milestone name").setInputValue(milestone.getMilestoneName());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(milestone.getDescription());
        new Dropdown(driver, "Status").chooseDropdownValue(milestone.getStatusMilestone().getName());
        new Input(driver, "Due date").setInputValue(milestone.getDate());
        new Input(driver, "Due date").clickEnterAfterInput();
    }

    public void clickSaveButton() {
        log.info("clicking save button");
        driver.findElement(SAVE_BUTTON).click();
    }
}
