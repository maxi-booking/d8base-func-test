package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.*;

import static com.codeborne.selenide.Selenide.*;

public class TestBase extends TestData {

    private static final WebDriverConfig config =
            ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    public static String
            urlBase = config.getBaseUrl(),
            urlLogin = urlBase + "/auth/login",
            urlLogOut = urlBase + "/auth/login?logout=",
            urlServicePublish = urlBase + "/service/publish",
            urlOrders = urlBase + "/my-orders/inbox",
            urlUserRegistration = urlBase + "/auth/registration",
            urlProfile = urlBase + "/profile",
            urlClientDetails = "/client-details",
            urlProfessionalProfile = urlBase + "/professional",
            urlMyProfessionalProfile = urlBase + "/professional/my-profile",
            urlForPerformers = urlBase + "/for-performers";

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
    public static Footer ftr = new Footer();

    public static UserEmails getEmail = new UserEmails();
    public static String[] emails = getEmail.userEmail();

    public static UserPasswords getPassword = new UserPasswords();
    public static String[] passwords = getPassword.userPassword();

    public static UserFirstNames getFirstName = new UserFirstNames();
    public static String[] firstNames = getFirstName.userFirstName();

    public static UserLastNames getLastName = new UserLastNames();
    public static String[] lastNames = getLastName.userLastName();

    public static UserPhoneNumbers getPhoneNumber = new UserPhoneNumbers();
    public static String[] phoneNumbers = getPhoneNumber.userPhoneNumber();

    public static UserCountries getCountry = new UserCountries();
    public static String[] countries = getCountry.userCountry();

    public static UserCities getCity = new UserCities();
    public static String[] cities = getCity.userCity();

    public static ServiceNames getServiceName = new ServiceNames();
    public static String[] serviceNames = getServiceName.serviceName();

    public static ServiceDescriptions getServiceDescription = new ServiceDescriptions();
    public static String[] serviceDescriptions = getServiceDescription.serviceDescription();

    public static ServiceDurations getServiceDuration = new ServiceDurations();
    public static String[] serviceDurations = getServiceDuration.serviceDuration();

    public static ServicePrices getServicePrice = new ServicePrices();
    public static String[] servicePrices = getServicePrice.servicePrice();

    public static Specializations getSpecialization = new Specializations();
    public static String[] specializations = getSpecialization.specialization();

    public static String [] dateTime = dateTimes();

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
        System.setProperty("chromeoptions.prefs","intl.accept_languages=en");
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
            closeWebDriver();
        }
    }
}