package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Select extends BaseElement{

    private final static String ACCESS_TYPE = "[value=%s]";
    public Select(WebDriver driver) {
        super(driver);
    }

    public void chooseAccessType(String type) {
        scrollToElement(driver.findElement(By.cssSelector(String.format(ACCESS_TYPE, type))));
        log.info(String.format("choosing the select value: %s", type));
        driver.findElement(By.cssSelector(String.format(ACCESS_TYPE, type))).click();
    }
}
