package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProjectsPage extends HomePage{

    private final static By PROJECT_ICON = By.cssSelector(".text-center.project-icon");
    private final static By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private final static By SEARCH_FOR_PROJECTS = By.cssSelector("[name=title]");
    private final static By PROJECT_RESULTS = By.cssSelector("a[class=defect-title]");
    private final static String SPECIFIC_PROJECT_LOCATOR = "//a[text()='%s']";

    public ProjectsPage(WebDriver driver) {
        super(driver);
    }

    public void waitForProjectIconDisplayed() {
        log.info("Waiting for project icon will be uploaded");
        waitForElementDisplayed(PROJECT_ICON);
    }

    public boolean isProjectIconDisplayed() {
        log.info("Checking if project icon is loaded and displayed");
        return driver.findElement(PROJECT_ICON).isDisplayed();
    }

    public void clickOnSpecificProject(String projectName) {
        searchForProject(projectName);
        log.info(String.format("clicking on specific project name: %s", projectName));
        driver.findElement(By.xpath(String.format(SPECIFIC_PROJECT_LOCATOR, projectName))).click();
    }

    public void clickCreateNewProject() {
        log.info("creating new project");
        driver.findElement(CREATE_NEW_PROJECT_BUTTON).click();
    }

    private void searchForProject(String projectName) {
        log.info(String.format("entering the value: %s in search line", projectName));
        driver.findElement(SEARCH_FOR_PROJECTS).sendKeys(projectName);
    }

    public String getSpecificProjectNameFromSearch(String projectName) {
        searchForProject(projectName);
        refreshThePage();
        log.info("getting the results after entering project name");
        List<WebElement> projectResults = driver.findElements(PROJECT_RESULTS);
        String firstProject = projectResults.get(0).getText();
        return firstProject;
    }

    public int getListOfProjectsAfterSearching(String projectName) {
        int num = 0;
        searchForProject(projectName);
        refreshThePage();
        log.info("getting the results after entering project name");
        List<WebElement> projectResults = driver.findElements(PROJECT_RESULTS);
        List<String> projectNames = new ArrayList<>();
        for(int i = 0; i < projectResults.size(); i++) {
            String text = projectResults.get(i).getText();
            projectNames.add(text);
            if(projectNames.get(i).contains("Demo") == false){
                num++;
            }
        }
        return num;
    }

}
