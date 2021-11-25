package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.webdriver.WebDriverFactory;
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

    public static Menu menu = new Menu();
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

    public String
            urlBase = System.getProperty("url", "https://app.maxibooking.ru/"),
            urlLogin = urlBase + "auth/login",
            urlLogOut = urlBase + "auth/login?logout=",
            urlServicePublish = urlBase + "service/publish",
            urlOrders = urlBase + "my-orders/inbox",
            urlUserRegistration = urlBase + "auth/registration",
            urlProfile = urlBase + "profile";

    @BeforeAll
    public static void init() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";

        Configuration.timeout = 10000;

//        Configuration.startMaximized = true;
        Configuration.headless = true;

        Configuration.browser = System.setProperty("browser", "chrome");
        System.setProperty("chromeoptions.prefs", "intl.accept_languages=en");


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