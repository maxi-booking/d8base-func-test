package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

import static helpers.LanguageConverter.getLocale;
import static helpers.SubcategoryGenerator.*;
import static helpers.SubcategoryGenerator.getCategoryCount;
import static io.qameta.allure.Allure.step;

public class TestBase extends TestData {

    private static final WebDriverConfig config =
            ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static final String
            defaultLanguage = config.getLanguage(),
            localeAPI = getLocale(defaultLanguage),

            urlFrontend = config.getFrontendUrl(),
            urlBackend = config.getBackendUrl() + ":8000",
            urlLogin = urlFrontend + "/auth/login",
            urlLogOut = urlFrontend + "/auth/login?logout=",
            urlServicePublish = urlFrontend + "/service/publish",
            urlOrders = urlFrontend + "/my-orders/inbox",
            urlUserRegistration = urlFrontend + "/auth/registration",
            urlProfile = urlFrontend + "/profile",
            urlClientDetails = "/client-details",
            urlProfessionalProfile = urlFrontend + "/professional",
            urlMyProfessionalProfile = urlFrontend + "/professional/my-profile",
            urlForPerformers = urlFrontend + "/for-performers";

    public static final Response
            subcategories = getSubcategories(localeAPI),
            categories = getCategories(localeAPI);
    public static final int
            subcategoryCount = getSubcategoryCount(subcategories),
            categoryCount = getCategoryCount(categories);

    public static Language language = new Language();
    public static SideMenu sideMenu = new SideMenu();
    public static TopBar topBar = new TopBar();
    public static Registration reg = new Registration();
    public static ServicePublish pbl = new ServicePublish();
    public static Booking bkn = new Booking();
    public static Orders ord = new Orders();
    public static Messages msg = new Messages();
    public static Favorites fav = new Favorites();
    public static Reviews rev = new Reviews();
    public static Search search = new Search();
    public static LogIn log = new LogIn();
    public static UserProfile up = new UserProfile();
    public static ProfessionalProfile pp = new ProfessionalProfile();
    public static Schedule sch = new Schedule();
    public static Footer ftr = new Footer();

    public static String[] dateTime = dateTimes();

    @BeforeAll
    public static void init() {
        Configuration.browser = config.getBrowserName();
        if (!config.getBrowserVersion().equals("")) {
            Configuration.browserVersion = config.getBrowserVersion();
        }
        Configuration.browserSize = config.getBrowserSize();
        if (!config.getRemote().equals("")) {
            Configuration.remote = config.getRemote();
        }
        Configuration.timeout = config.getTimeout();
        Configuration.headless = config.getHeadless();
        Configuration.browserCapabilities.setCapability("enableVNC", config.getVNC());
        Configuration.browserCapabilities.setCapability("enableVideo", config.getVideo());
        System.setProperty("chromeoptions.prefs", "intl.accept_languages=en");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        setTestData();
    }

    @BeforeEach
    public void setupConfig() {
        setRandomData();
    }

    @AfterEach
    public void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
//            Attach.addVideo(); //todo
            step("Close web driver", Selenide::closeWebDriver);
        }
    }
}