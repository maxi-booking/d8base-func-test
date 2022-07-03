package pages;

import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class Search {
    public void search(String searchQuery) {
        step("Search page: Search " + searchQuery, () -> {
        $("app-search form input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("app-search form input").pressEnter();
        $("app-search app-applied-filters ion-chip").shouldHave(text(searchQuery), Duration.ofSeconds(10));
        });
    }

    @Step("Search page: Search without quotes {searchQuery}")
    public void searchNoQuotes(String searchQuery) {
        $("app-search form input").setValue(searchQuery);
        sleep(1000);
        $("app-search form input").pressEnter();
        sleep(500);
    }

    @Step("Main page: Search {searchQuery}")
    public void searchMain(String searchQuery) {
        $("ion-searchbar input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("ion-searchbar input").pressEnter();
    }

    @Step("Main page: Search without quotes {searchQuery}")
    public void searchMainNoQuotes(String searchQuery) {
        $("ion-searchbar input").setValue(searchQuery);
        sleep(1000);
        $("ion-searchbar input").pressEnter();
    }

    @Step("Close default filters")
    public void closeAllChips() {
        sleep(1000);
        while ($("app-applied-filters ion-chip").exists()) {
            $("app-applied-filters ion-chip").scrollIntoView(false).click();
            sleep(500);
        }
    }

    @Step("Click on the first name in search results")
    public void clickProfessionalsName() {
//        $("app-search-result ion-thumbnail.avatar",1).shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-search-result ion-thumbnail.avatar",0).shouldBe(visible, Duration.ofSeconds(10));
        $$("app-search-result ion-thumbnail.avatar").filter(visible).get(0).click();
        $("app-professional-page").shouldBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify that 'Best works' field exists")
    public void verifyBestWorksExists() {
        $("app-search-result ion-card section ion-label.photos-title b").shouldBe(visible);
        $("app-search-result ion-card section app-image-carousel div.photo-slider-container ion-slides").shouldBe(visible);
    }

    @Step("Verify that 'Best works' field doesn't exists")
    public void verifyBestWorksNotExists(String serviceName) {
        $("app-search-result ion-card").shouldBe(visible, Duration.ofSeconds(10));
        $("app-search-result ion-card app-service-link-search-card a").shouldHave(text(serviceName));
        $("app-search-result ion-card section ion-label").shouldNot(exist);
        $("app-search-result ion-card section app-image-carousel").shouldNot(exist);
    }
}