package drivers;

import com.codeborne.selenide.Browser;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.FIRST)
@Config.Sources({
        "classpath:config/${environment}.properties",
        "classpath:config/local_chrome.properties"
})
public interface WebDriverConfig extends Config {

    @Config.Key("threads")
    @Config.DefaultValue("4")
    int getThreads();

    @Config.Key("browserName")
    @Config.DefaultValue("chrome")
    String getBrowserName();

    @Config.Key("browserVersion")
    @Config.DefaultValue("")
    String getBrowserVersion();

    @Config.Key("browserSize")
    @Config.DefaultValue("1920x1080")
    String getBrowserSize();

    @Config.Key("headless")
    @Config.DefaultValue("false")
    Boolean getHeadless();

    @Config.Key("timeout")
    @Config.DefaultValue("10000")
    long getTimeout();

    @Config.Key("frontendUrl")
    @Config.DefaultValue("https://app.maxibooking.ru")
    String getFrontendUrl();

    @Config.Key("backendUrl")
    @Config.DefaultValue("https://app.maxibooking.ru")
    String getBackendUrl();

    @Config.Key("remote")
    String getRemote();

    @Config.Key("enableVNC")
    @Config.DefaultValue("true")
    Boolean getVNC();

    @Config.Key("enableVideo")
    @Config.DefaultValue("true")
    Boolean getVideo();
}
