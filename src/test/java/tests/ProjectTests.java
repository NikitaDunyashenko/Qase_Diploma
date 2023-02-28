package tests;

import com.github.javafaker.Faker;
import enums.ProjectAccessType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectTests extends BaseTest {

    Faker faker = new Faker();
    private final int ID_NUMBER = faker.number().numberBetween(1, 1000);
    private final static String PROJECT_NAME = "Qase_Diploma_";
    private final  static String PROJECT_ID = "QD";
    private final static String PROJECT_DESCRIPTION = "The project is designed to track test activities of qase.io";


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

        Assert.assertEquals(projectsPage.getListOfProjectsAfterSearching("Demo"), 0);

    }

}
