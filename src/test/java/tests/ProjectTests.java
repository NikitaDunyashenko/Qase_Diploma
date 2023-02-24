package tests;

import com.github.javafaker.Faker;
import enums.ProjectAccessType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTests extends BaseTest {

    Faker faker = new Faker();
    private final int idNumber = faker.number().numberBetween(1, 1000);
    private final static String PROJECT_NAME = "Qase_Diploma_";
    private final  static String PROJECT_ID = "QD";
    private final static String PROJECT_DESCRIPTION = "The project is designed to track test activities of qase.io";

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking the possibility to create a new project")
    @Test(groups = {"smoke", "positive"})
    public void createNewProject() {
        loginPage.setUserNameInput(USER_NAME);
        loginPage.setPasswordInput(PASSWORD);
        loginPage.clickLoginButton();
        projectsPage.waitForProjectIconDisplayed();

        projectsPage.clickCreateNewProject();

        Project project = new Project.ProjectBuilder()
                .setProjectName(PROJECT_NAME + idNumber)
                .setProjectCode(PROJECT_ID + idNumber)
                .setProjectDescription(PROJECT_DESCRIPTION)
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .build();

        newProjectModal.fillForm(project);
        newProjectModal.clickSaveButton();
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        baseProjectPage.chooseMenuBarItem("Settings");

        Assert.assertEquals(projectSettingsPage.getProjectDetails(), project);

    }
}
