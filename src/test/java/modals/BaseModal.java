package modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

@Log4j2
public abstract class BaseModal extends BasePage {

    private final static By SAVE_BUTTON = By.cssSelector("[type=submit]");

    public BaseModal(WebDriver driver) {
        super(driver);
    }

    public void clickSaveButton() {
        log.info("clicking Save Button");
        driver.findElement(SAVE_BUTTON).click();
    }
}
