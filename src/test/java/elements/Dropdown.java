package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Dropdown extends BaseElement {
    private String labelName;
    private final static String DROPDOWN_LOCATOR = "//label[text()='%s']//parent::div/following-sibling::div//span";
    private final static String DROPDOWN_VALUE = "//div[text()='%s']";

    public Dropdown(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void chooseDropdownValue(String value) {
        log.debug(String.format("searching for the dropdown with label: %s", this.labelName));
        driver.findElement(By.xpath(String.format(DROPDOWN_LOCATOR, this.labelName))).click();
        log.debug(String.format("choosing the value: %s in dropdown", value));
        driver.findElement(By.xpath(String.format(DROPDOWN_VALUE, value))).click();
    }


}
