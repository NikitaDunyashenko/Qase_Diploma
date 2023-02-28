package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DataPlaceholder extends BaseElement{

    private final static String DATA_PLACEHOLDER_LOCATOR = "//label[text()='%s']//parent::div//following-sibling::div//p[@class]";
    private String labelName;
    public DataPlaceholder(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    private void clearDataPlaceholderValue() {
        log.info("clearing the data placeholder value");
        driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_LOCATOR, this.labelName))).clear();
    }

    public void setDataPlaceholderValue(String value) {
        clearDataPlaceholderValue();
        log.info(String.format("setting data placeholder value: %s", value));
        driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_LOCATOR, this.labelName))).sendKeys(value);
    }

}
