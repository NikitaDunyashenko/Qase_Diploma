package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class MilestonePage extends BaseProjectPage{

    private final static By CREATE_MILESTONE_BUTTON = By.id("createButton");
    private final static String CLICK_SPECIFIC_MILESTONE = "//a[text()='%s']";

    public MilestonePage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateMilestoneButton() {
        log.info("clicking create new milestone button");
        driver.findElement(CREATE_MILESTONE_BUTTON).click();
    }

    public void clickSpecificMilestone(String milestoneName) {
        log.info(String.format("clicking specific milestone with name: %s", milestoneName));
        driver.findElement(By.xpath(String.format(CLICK_SPECIFIC_MILESTONE, milestoneName))).click();
    }
}
