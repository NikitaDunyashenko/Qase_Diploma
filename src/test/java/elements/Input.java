package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Input extends BaseElement {
    private String labelName;
    private final static String INPUT_LOCATOR = "//label[text()='%s']/parent::div//following-sibling::div//input";

    public Input(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    private void clearInputValue() {
        log.info("clearing the input value");
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, this.labelName))).clear();
    }

    public void setInputValue(String inputValue) {
        scrollToElement(driver.findElement(By.xpath(String.format(INPUT_LOCATOR, this.labelName))));
        clearInputValue();
        log.info(String.format("entering the input value: %s", inputValue));
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, this.labelName))).sendKeys(inputValue);
    }

    public void clickEnterAfterInput() {
        log.info("clicking enter after input");
        driver.findElement(By.xpath(String.format(INPUT_LOCATOR, this.labelName))).sendKeys(Keys.ENTER);
    }
}
