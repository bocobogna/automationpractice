package intern.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import intern.pageObject.Pages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static intern.utils.DataResources.*;

public abstract class BaseTest {

    Pages pages = new Pages();

    @BeforeAll
    public static void setup() {
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.addArguments("disable-web-security");
            WebDriverManager.chromedriver().version("77.0.3865.40").setup();
            WebDriver webDriver = new ChromeDriver(options);
            setWebDriver(webDriver);
        }
        Configuration.timeout = 8000;
        Configuration.baseUrl = "http://automationpractice.com";
    }

    @AfterAll
    public static void teardown() {
        WebDriverRunner.driver().getWebDriver().close();
        WebDriverRunner.driver().getWebDriver().quit();
    }

    protected void loginUser(){
        pages.mainPage
                .open();

        pages.mainHeader
                .isAt(signInButtonText)
                .goToSignInPage();

        pages.loginPage
                .isAt(loginHeaderText.toUpperCase())
                .loginUser(UserEmail, UserPassword);

        pages.mainHeader
                .isAt(signOutButtonText);

        pages.myAccountPage
                .isAt(myAccountHeaderText.toUpperCase());
    }

    protected void logoutUser(){
        pages.mainHeader
                .signOut()
                .isAt(signInButtonText);
    }
}
