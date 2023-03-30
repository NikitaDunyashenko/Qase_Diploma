package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class BaseProjectPage extends HomePage {

    private final static String MENU_BAR_ITEMS = "[title=%s]";

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }

    public void chooseMenuBarItem(String itemName) {
        log.info(String.format("choosing the menu bar value: %s", itemName));
        driver.findElement(By.cssSelector(String.format(MENU_BAR_ITEMS, itemName))).click();
    }
}
