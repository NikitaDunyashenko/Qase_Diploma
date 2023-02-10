package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

@Log4j2
public abstract class BaseTest {

    protected final static String BASE_URL = "https://app.qase.io/login";
    protected final static String USER_NAME = "nik123@mailinator.com";
    protected final static String PASSWORD = "Password@_1";

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeClass(alwaysRun = true, description = "Setting up the driver")
    public void setUp() {

        log.info("Setting up the chrome driver");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        log.info("Maximizing browser window");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
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
