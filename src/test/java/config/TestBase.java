package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase extends TestData {

    public static SideMenu sideMenu = new SideMenu();
    public static TopBar topBar = new TopBar();
    public static Registration reg = new Registration();
    public static ServicePublish pbl = new ServicePublish();
    public static Booking bkn = new Booking();
    public static Orders ord = new Orders();
    public static Messages msg = new Messages();
    public static Favorites fav = new Favorites();
    public static Reviews rev = new Reviews();
    public static Search sch = new Search();
    public static LogIn log = new LogIn();
    public static UserProfile up = new UserProfile();
    public static ProfessionalProfile pp = new ProfessionalProfile();

    public static UserEmails getEmail = new UserEmails();
    public static String[] emails = getEmail.userEmail();
    public static UserPasswords getPassword = new UserPasswords();
    public static String[] passwords = getPassword.userPassword();

    public String
            urlBase = System.getProperty("url", "https://app.maxibooking.ru/"),
            urlLogin = urlBase + "auth/login",
            urlLogOut = urlBase + "auth/login?logout=",
            urlServicePublish = urlBase + "service/publish",
            urlOrders = urlBase + "my-orders/inbox",
            urlUserRegistration = urlBase + "auth/registration",
            urlProfile = urlBase + "profile",
            urlClientDetails = "client-details",
            urlProfessionalProfile = urlBase + "professional";

    @BeforeAll
    public static void init() {
        Configuration.browser = System.getProperty("browser", "chrome");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
//        Configuration.remote = "http://test:test@localhost:4445/wd/hub/";
//        Configuration.remote = "http://localhost:8080/wd/hub/";

        Configuration.timeout = 10000;

//        Configuration.clickViaJs = true;

//        Configuration.startMaximized = true;
        Configuration.headless = true;

        setTestData();
    }


    @BeforeEach
    public void setupConfig() {
        setRandomData();
        open(urlBase);
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}