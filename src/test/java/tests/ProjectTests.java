package tests;

import enums.ProjectAccessType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ProjectTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginPage.setUserNameInput(USER_NAME);
        loginPage.setPasswordInput(PASSWORD);
        loginPage.clickLoginButton();
        projectsPage.waitForProjectIconDisplayed();
    }

    @BeforeMethod(alwaysRun = true, onlyForGroups = "needsProject")
    public void newProject() {
        projectsPage.clickCreateNewProject();

        Project project = new Project.ProjectBuilder()
                .setProjectName(PROJECT_NAME_OTHER)
                .setProjectCode(PROJECT_ID)
                .setProjectDescription(PROJECT_DESCRIPTION)
                .setProjectAccessType(ProjectAccessType.PUBLIC)
                .build();

        newProjectModal.fillForm(project);
        newProjectModal.clickSaveButton();
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        homePage.clickOnProjectsTab();
        projectsPage.waitForProjectIconDisplayed();
    }

    @AfterMethod(alwaysRun = true, onlyForGroups = "needsProject")
    public void deleteProject() {
        projectsPage.clickOnSpecificProject(PROJECT_NAME_OTHER);
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        baseProjectPage.chooseMenuBarItem("Settings");
        projectSettingsPage.clickDeleteProject();
        deleteProjectModal.waitForDeleteButtonDisplays();
        deleteProjectModal.clickDeleteProject();
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        homePage.clickSignOutButton();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking the possibility to create a new project")
    @Test(groups = {"smoke", "positive"}, dataProvider = "projectData", retryAnalyzer = RetryAnalyzer.class)
    public void createNewProject(String projectName, String projectId, String projectDescription, ProjectAccessType projectAccessType) {
        projectsPage.clickCreateNewProject();

        Project project = new Project.ProjectBuilder()
                .setProjectName(projectName + ID_NUMBER)
                .setProjectCode(projectId + ID_NUMBER)
                .setProjectDescription(projectDescription)
                .setProjectAccessType(projectAccessType)
                .build();

        newProjectModal.fillForm(project);
        newProjectModal.clickSaveButton();
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        baseProjectPage.chooseMenuBarItem("Settings");

        Assert.assertEquals(projectSettingsPage.getProjectDetails(), project);

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("checking if a project that has been searched for is in the first row after entering text to the searcher line")
    @Test(groups = {"regression", "positive", "needsProject"}, retryAnalyzer = RetryAnalyzer.class)
    public void searchingForSpecificProject() {

        Assert.assertEquals(projectsPage.getSpecificProjectNameFromSearch(PROJECT_NAME_OTHER), "Test_Project");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("checking if a represented information is correct after entering text to the searcher line")
    @Test(groups = {"regression", "positive"}, retryAnalyzer = RetryAnalyzer.class)
    public void searchingForProjects() {

        Assert.assertEquals(projectsPage.getIndicatorIfFilteredProjectsDisplayCorrectly("Demo"), 0);

    }

    @DataProvider()
    public Object[][] projectData() {
        return new Object[][] {
                {"Salesforce_", "SF", "The project is designed to track test activities of Salesforce", ProjectAccessType.PUBLIC},
                {"SauceDemo_", "SD", "The project is designed to track test activities of SauceDemo", ProjectAccessType.PRIVATE},
        };
    }

}
