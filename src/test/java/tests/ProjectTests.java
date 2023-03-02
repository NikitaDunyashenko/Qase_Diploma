package tests;

import enums.ProjectAccessType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectTests extends BaseTest {

    @BeforeMethod
    public void login() {
        loginPage.setUserNameInput(USER_NAME);
        loginPage.setPasswordInput(PASSWORD);
        loginPage.clickLoginButton();
        projectsPage.waitForProjectIconDisplayed();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking the possibility to create a new project")
    @Test(groups = {"smoke", "positive"})
    public void createNewProject() {
        projectsPage.clickCreateNewProject();

        Project project = new Project.ProjectBuilder()
                .setProjectName(PROJECT_NAME + ID_NUMBER)
                .setProjectCode(PROJECT_ID + ID_NUMBER)
                .setProjectDescription(PROJECT_DESCRIPTION)
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .build();

        newProjectModal.fillForm(project);
        newProjectModal.clickSaveButton();
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        baseProjectPage.chooseMenuBarItem("Settings");

        Assert.assertEquals(projectSettingsPage.getProjectDetails(), project);

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("checking if a project that has been searched for is in the first row after entering text to the searcher line")
    @Test(groups = {"regression", "positive"})
    public void searchingForSpecificProject() {

        Assert.assertEquals(projectsPage.getSpecificProjectNameFromSearch("Qase_Diploma"), "Qase_Diploma");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("checking if a represented information is correct after entering text to the searcher line")
    @Test(groups = {"regression", "positive"})
    public void searchingForProjects() {

        Assert.assertEquals(projectsPage.getIndicatorIfFilteredProjectsDisplayCorrectly("Demo"), 0);

    }

}
