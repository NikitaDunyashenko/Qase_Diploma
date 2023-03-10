package tests;

import enums.ProjectAccessType;
import enums.StatusMilestone;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Milestone;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MilestoneTests extends BaseTest{

    @BeforeMethod(alwaysRun = true)
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to create new milestone")
    @Test(groups = {"smoke", "positive"}, retryAnalyzer = RetryAnalyzer.class)
    public void createNewMilestone() {
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        baseProjectPage.chooseMenuBarItem("Milestones");

        Milestone milestone = new Milestone.MilestoneBuilder()
                .setMilestoneName("Milestone name")
                .setDescription("Description")
                .setStatusMilestone(StatusMilestone.ACTIVE)
                .setDate("2023-03-15")
                .build();

        milestonePage.clickCreateMilestoneButton();
        createNewMilestone.fillForm(milestone);
        createNewMilestone.clickSaveButton();

        milestonePage.clickSpecificMilestone("Milestone name");
        editMilestonePage.waitForSaveButtonDisplays();

        Assert.assertEquals(editMilestonePage.getMilestoneDetails(), milestone);
    }
}
