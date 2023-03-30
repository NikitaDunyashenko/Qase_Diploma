package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TextArea extends BaseElement {

    private String labelName;
    private final static String TEXTAREA_LOCATOR = "//label[text()='%s']/parent::div/parent::div/textarea";

    public TextArea(WebDriver driver, String labelName) {
        super(driver);
        this.labelName = labelName;
    }

    public void setTextAreaValue(String textAreaValue) {
        log.info(String.format("entering the textarea value: %s", textAreaValue));
        driver.findElement(By.xpath(String.format(TEXTAREA_LOCATOR, this.labelName))).sendKeys(textAreaValue);
    }
}
