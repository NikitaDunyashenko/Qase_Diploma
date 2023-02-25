package pages;

import enums.ProjectAccessType;
import lombok.extern.log4j.Log4j2;
import models.Project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectSettingsPage extends BaseProjectPage {

    private final static By PROJECT_NAME = By.id("project-name");
    private final static By PROJECT_CODE = By.id("project-code");
    private final static By PROJECT_DESCRIPTION = By.id("description-area");
    private final static By PRIVATE_ACCESS_TYPE = By.cssSelector("[value=private");
    private final static By PUBLIC_ACCESS_TYPE = By.cssSelector("[value=public]");

    public Project getProjectDetails() {
        log.info("getting projects details from settings page");
        Project.ProjectBuilder project = new Project.ProjectBuilder();
        project.setProjectName(new ProjectSettingsPage(driver).getProjectName());
        project.setProjectCode(new ProjectSettingsPage(driver).getProjectCode());
        project.setProjectDescription(new ProjectSettingsPage(driver).getProjectDescription());
        project.setProjectAccessType(new ProjectSettingsPage(driver).getAccessTypeSelected());
        return project.build();
    }

    public ProjectSettingsPage(WebDriver driver) {
        super(driver);
    }

    public String getProjectName() {
        log.info("getting project name");
        return driver.findElement(PROJECT_NAME).getAttribute("value");
    }

    public String getProjectCode() {
        log.info("getting project code");
        return driver.findElement(PROJECT_CODE).getAttribute("value");
    }

    public String getProjectDescription() {
        log.info("getting project description");
        return driver.findElement(PROJECT_DESCRIPTION).getText();
    }

    public ProjectAccessType getAccessTypeSelected() {
        log.info("getting access type");
        if (driver.findElement(PUBLIC_ACCESS_TYPE).isSelected()) {
            return ProjectAccessType.fromString("public");
        } else if (driver.findElement(PRIVATE_ACCESS_TYPE).isSelected()) {
            return ProjectAccessType.fromString("private");
        } else {
            return null;
        }
    }
}
