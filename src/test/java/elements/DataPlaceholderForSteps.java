package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DataPlaceholderForSteps extends BaseElement {

    private final static String DATA_PLACEHOLDER_FOR_STEPS_LOCATOR = "//div[@title='%d']//parent::div/parent::div/following-sibling::div//p[@data-placeholder='%s']";
    private int title;
    private String itemName;

    public DataPlaceholderForSteps(WebDriver driver, int title, String itemName) {
        super(driver);
        this.title = title;
        this.itemName = itemName;
    }

    public void setDataPlaceholderForStepsValue(String value) {
        scrollToElement(driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_FOR_STEPS_LOCATOR, this.title, this.itemName))));
        log.info(String.format("sending the following value: %s", value));
        driver.findElement(By.xpath(String.format(DATA_PLACEHOLDER_FOR_STEPS_LOCATOR, this.title, this.itemName))).sendKeys(value);
    }
}
