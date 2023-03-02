package tests;

import enums.ParentSuite;
import enums.ProjectAccessType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import models.Suite;
import org.testng.Assert;
import org.testng.annotations.*;

public class SuiteTests extends BaseTest{

    @BeforeMethod(onlyForGroups = {"smoke", "positive"})
    public void createProject() {
        loginPage.setUserNameInput(USER_NAME);
        loginPage.setPasswordInput(PASSWORD);
        loginPage.clickLoginButton();
        projectsPage.waitForProjectIconDisplayed();

        projectsPage.clickCreateNewProject();

        Project project = new Project.ProjectBuilder()
                .setProjectName(PROJECT_NAME + ID_NUMBER)
                .setProjectCode(PROJECT_ID + ID_NUMBER)
                .setProjectDescription(PROJECT_DESCRIPTION)
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .build();

        newProjectModal.fillForm(project);
        newProjectModal.clickSaveButton();
    }

    @BeforeMethod(onlyForGroups = {"regression"})
    public void createSuite() {
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        projectRepositoryPage.clickCreateNewSuiteButton();

        Suite suite = new Suite.SuiteBuilder()
                .setSuiteName(SUITE_NAME)
                .setDescription(SUITE_DESCRIPTION)
                .setPreconditions(SUITE_PRECONDITIONS)
                .setParentSuite(ParentSuite.PROJECT_ROOT)
                .build();

        newSuiteModal.fillForm(suite);
        newSuiteModal.clickSaveButton();
    }

    @AfterMethod(onlyForGroups = {"smoke", "regression"})
    public void deleteProject() {
        suiteDetailsModal.clickCloseModalSign();
        baseProjectPage.chooseMenuBarItem("Settings");
        projectSettingsPage.clickDeleteProject();
        deleteProjectModal.waitForDeleteButtonDisplays();
        deleteProjectModal.clickDeleteProject();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it is possible to create a new suite")
    @Test(groups = {"smoke", "positive"})
    public void createNewSuite() {
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        projectRepositoryPage.clickCreateNewSuiteButton();

        Suite suite = new Suite.SuiteBuilder()
                .setSuiteName(SUITE_NAME)
                .setDescription(SUITE_DESCRIPTION)
                .setPreconditions(SUITE_PRECONDITIONS)
                .setParentSuite(ParentSuite.PROJECT_ROOT)
                .build();

        newSuiteModal.fillForm(suite);
        newSuiteModal.clickSaveButton();

        projectRepositoryPage.clickToEditSuiteName(SUITE_NAME);

        suiteDetailsModal.waitForParentSuiteDisplays();

        Assert.assertEquals(suiteDetailsModal.getSuiteDetails(), suite);

    }

    @Test(groups = {"regression", "positive"})
    public void editSuite() {
        projectRepositoryPage.clickToEditSuiteName(SUITE_NAME);

        Suite suite = new Suite.SuiteBuilder()
                .setSuiteName("Regression")
                .setDescription("Suite for regression tests only")
                .setPreconditions("Precondition")
                .setParentSuite(ParentSuite.PROJECT_ROOT)
                .build();

        suiteDetailsModal.fillEditForm(suite);
        newSuiteModal.clickSaveButton();

        projectRepositoryPage.clickToEditSuiteName("Regression");

        suiteDetailsModal.waitForParentSuiteDisplays();

        Assert.assertEquals(suiteDetailsModal.getSuiteDetails(), suite);
    }

}
