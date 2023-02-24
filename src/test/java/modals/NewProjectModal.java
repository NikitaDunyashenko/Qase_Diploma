package modals;

import elements.Input;
import elements.Select;
import elements.TextArea;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.WebDriver;

@Log4j2
public class NewProjectModal extends BaseModal{
    public NewProjectModal(WebDriver driver) {
        super(driver);
    }

    public void fillForm(Project project) {
        log.info(String.format("filling the modal form with the following values: %s", project));
        new Input(driver, "Project name").setInputValue(project.getProjectName());
        new Input(driver, "Project code").setInputValue(project.getProjectCode());
        new TextArea(driver, "Description").setTextAreaValue(project.getProjectDescription());
        new Select(driver).chooseAccessType(project.getProjectAccessType().getName());
    }
}
