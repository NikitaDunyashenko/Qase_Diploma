package tests;

import enums.*;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import models.Project;
import models.Suite;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCaseTests extends BaseTest {

    @BeforeMethod(alwaysRun = true, onlyForGroups = {"smoke"})
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

    @BeforeMethod(alwaysRun = true, onlyForGroups = {"smoke"})
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

    @Severity(SeverityLevel.CRITICAL)
    @Description("checking if it's possible to create new case")
    @Test(groups = {"smoke", "positive"}, retryAnalyzer = RetryAnalyzer.class)
    public void createNewCase() {
        projectRepositoryPage.waitForCreateNewSuiteIsDisplayed();
        projectRepositoryPage.clickToAddNewItem("Smoke");
        projectRepositoryPage.createNewItem("Create case");

        TestCase testCase = new TestCase.TestCaseBuilder()
                .setTitle("Authorization")
                .setDescription("We can authorize on page qase.io")
                .setPreConditions("Pre-conditions")
                .setPostConditions("Post-conditions")
                .setStatus(Status.ACTUAL)
                .setSuite("Smoke")
                .setSeverity(enums.Severity.BLOCKER)
                .setPriority(Priority.HIGH)
                .setType(Type.FUNCTIONAL)
                .setLayer(Layer.E2E)
                .setIsFlaky(IsFlaky.NO)
                .setBehavior(Behavior.POSITIVE)
                .setAutomationStatus(AutomationStatus.AUTOMATED)
                .setParameterTitle("Title")
                .setParameterValues("Values")
                .setStepAction("Step Action")
                .setData("Data")
                .setExpectedResult("Expected result")
                .build();

        createNewCasePage.clickAddParameterButton();
        createNewCasePage.clickAddStepButton();
        createNewCasePage.fillForm(testCase);
        createNewCasePage.clickSaveCaseButton();

        projectRepositoryPage.clickOnSpecificTestCase("Authorization");
        testCaseDetailsModal.clickOnEditTestCase();

        editCasePage.waitForLoadingElementDisplays();
        editCasePage.waitForLoadingElementDisappears();

        Assert.assertEquals(editCasePage.getTestCaseDetails(), testCase);

    }

}
