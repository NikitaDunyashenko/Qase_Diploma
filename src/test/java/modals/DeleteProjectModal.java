package modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DeleteProjectModal extends BaseModal {

    private final static By DELETE_PROJECT = By.xpath("//*[text()='Delete project']/parent::button");

    public DeleteProjectModal(WebDriver driver) {
        super(driver);
    }

    public void waitForDeleteButtonDisplays() {
        log.info("waiting for button delete project appears");
        waitForElementDisplayed(DELETE_PROJECT);
    }

    public void clickDeleteProject() {
        log.info("clicking button delete project");
        driver.findElement(DELETE_PROJECT).click();
    }
}
