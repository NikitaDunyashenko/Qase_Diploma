package pages;

import enums.StatusMilestone;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class EditMilestonePage extends BaseProjectPage{

    private final static String INPUT_LOCATOR = "//label[text()='%s']/parent::div//following-sibling::div//input";
    private final static String DATA_PLACEHOLDER_LOCATOR = "//label[text()='%s']//parent::div//following-sibling::div//div[contains(@class,'ww-container')]//p";
    private final static String DROPDOWN_LOCATOR = "//label[text()='%s']//parent::div//following-sibling::div//div[@class='RZYgph']";
    private final static By SAVE_BUTTON = By.cssSelector("[type=submit]");

    public EditMilestonePage(WebDriver driver) {
        super(driver);
    }

    public Milestone getMilestoneDetails() {
        log.info("getting fields' values from milestone edit page");
        Milestone.MilestoneBuilder milestone = new Milestone.MilestoneBuilder();
        milestone.setMilestoneName(new EditMilestonePage(driver).getInputValue("Milestone name"));
        milestone.setDescription(new EditMilestonePage(driver).getDataPlaceholderValue("Description"));
        milestone.setStatusMilestone(StatusMilestone.fromString(getDropdownValue("Status")));
        milestone.setDate(new EditMilestonePage(driver).getInputValue("Due date"));
        return milestone.build();
    }

    public String getInputValue(String labelName) {
        log.info(String.format("getting data from the field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(INPUT_LOCATOR, labelName))).getAttribute("value");
    }

    public String getDataPlaceholderValue(String labelName) {
        log.info(String.format("getting data from the field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_LOCATOR, labelName))).getText();
    }

    public String getDropdownValue(String labelName) {
        log.info(String.format("getting data from the field with label name: %s", labelName));
        return driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, labelName))).getText();
    }

    public void waitForSaveButtonDisplays() {
        log.info("waiting for save button appears");
        waitForElementDisplayed(SAVE_BUTTON);
    }
}
