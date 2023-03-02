package tests;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import modals.DeleteProjectModal;
import modals.NewProjectModal;
import modals.NewSuiteModal;
import modals.SuiteDetailsModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.util.concurrent.TimeUnit;

@Log4j2
public abstract class BaseTest {

    protected final static String BASE_URL = "https://app.qase.io/login";
    protected final static String USER_NAME = "nik123@mailinator.com";
    protected final static String PASSWORD = "Password@_1";
    Faker faker = new Faker();
    protected final int ID_NUMBER = faker.number().numberBetween(1, 1000);
    protected final static String PROJECT_NAME = "Qase_Diploma_";
    protected final  static String PROJECT_ID = "QD";
    protected final static String PROJECT_DESCRIPTION = "The project is designed to track test activities of qase.io";
    protected final static String SUITE_NAME = "Smoke";
    protected final static String SUITE_DESCRIPTION = "Suite for smoke tests only";
    protected final static String SUITE_PRECONDITIONS = "Preconditions";

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProjectsPage projectsPage;
    protected BaseProjectPage baseProjectPage;
    protected HomePage homePage;
    protected ProjectRepositoryPage projectRepositoryPage;
    protected ProjectSettingsPage projectSettingsPage;
    protected NewProjectModal newProjectModal;
    protected NewSuiteModal newSuiteModal;
    protected SuiteDetailsModal suiteDetailsModal;
    protected DeleteProjectModal deleteProjectModal;

    @BeforeClass(alwaysRun = true, description = "Setting up the driver")
    public void setUp() {

        log.info("Setting up the chrome driver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        log.info("Maximizing browser window");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        projectsPage = new ProjectsPage(driver);
        baseProjectPage = new BaseProjectPage(driver);
        homePage = new HomePage(driver);
        projectRepositoryPage = new ProjectRepositoryPage(driver);
        projectSettingsPage = new ProjectSettingsPage(driver);
        newProjectModal = new NewProjectModal(driver);
        newSuiteModal = new NewSuiteModal(driver);
        suiteDetailsModal = new SuiteDetailsModal(driver);
        deleteProjectModal = new DeleteProjectModal(driver);
    }

    @BeforeMethod(alwaysRun = true, description = "Website URL")
    public void navigate() {
        log.info(String.format("Redirecting to the website: %s", BASE_URL));
        driver.get(BASE_URL);
    }

    @AfterClass(alwaysRun = true, description = "Quiting the browser")
    public void tearDown() {
        log.info("Quiting the browser");
        driver.quit();
    }

}
