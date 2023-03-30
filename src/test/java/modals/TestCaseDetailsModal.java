package modals;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TestCaseDetailsModal extends BaseModal {

    private final static By EDIT_TEST_CASE_BUTTON = By.xpath("//span[text()=' Edit']/parent::a");

    public TestCaseDetailsModal(WebDriver driver) {
        super(driver);
    }

    public void clickOnEditTestCase() {
        log.info("clicking on edit test case");
        driver.findElement(EDIT_TEST_CASE_BUTTON).click();
    }
}
