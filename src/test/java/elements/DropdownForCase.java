package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DropdownForCase extends BaseElement{

    private final static String DROPDOWN_LOCATOR = "//label[text()='%s']//parent::div//span";
    private final static String DROPDOWN_VALUE = "//div[text()='%s']";
    private String labelName;
    public DropdownForCase(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void chooseDropdownOption(String value) {
        scrollToElement(driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, this.labelName))));
        log.info(String.format("clicking dropdown button with label: %s", this.labelName));
        driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, this.labelName))).click();
        log.info(String.format("clicking dropdown option: %s", value));
        driver.findElement(By.xpath(String.format(DROPDOWN_VALUE, value))).click();
    }
}
