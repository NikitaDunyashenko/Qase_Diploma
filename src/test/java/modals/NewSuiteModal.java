package modals;

import elements.DataPlaceholder;
import elements.Dropdown;
import elements.Input;
import lombok.extern.log4j.Log4j2;
import models.Suite;
import org.openqa.selenium.WebDriver;

@Log4j2
public class NewSuiteModal extends BaseModal{

    public NewSuiteModal(WebDriver driver) {
        super(driver);
    }

    public void fillForm(Suite suite) {
        log.info("filling values into the suite modal");
        new Input(driver, "Suite name").setInputValue(suite.getSuiteName());
        new DataPlaceholder(driver, "Description").setDataPlaceholderValue(suite.getDescription());
        new DataPlaceholder(driver, "Preconditions").setDataPlaceholderValue(suite.getPreconditions());
        new Dropdown(driver, "Parent suite").chooseDropdownValue(suite.getParentSuite().getName());
    }
}
