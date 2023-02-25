package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectsPage extends HomePage{

    private final static By PROJECT_ICON = By.cssSelector(".text-center.project-icon");
    private final static By CREATE_NEW_PROJECT_BUTTON = By.id("createButton");
    private final static By SEARCH_FOR_PROJECTS = By.cssSelector("[name=title]");
    private final static String PROJECT_RESULTS = "//a[text()='%s']";

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

    public void clickCreateNewProject() {
        log.info("creating new project");
        driver.findElement(CREATE_NEW_PROJECT_BUTTON).click();
    }

    private void searchForProject(String projectName) {
        log.info(String.format("entering the value: %s in search line", projectName));
        driver.findElement(SEARCH_FOR_PROJECTS).sendKeys(projectName);
    }

    public void chooseProjectFromSearch(String projectName) {
        searchForProject(projectName);
        log.info("clicking at project name after searching it");
        driver.findElement(By.xpath(String.format(PROJECT_RESULTS, projectName))).click();
    }
}
