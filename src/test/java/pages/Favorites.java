package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Favorites {

    @Step("Open side menu button")
    public void clickMenuMain() {
        sleep(300);
        $("app-main").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click side menu button")
    public void clickMenuProfile() {
        sleep(300);
        $("app-profile").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click side menu button")
    public void clickMenuSearch() {
        sleep(300);
        $("app-search").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click side menu button")
    public void clickMenuMasterProfile() {
        sleep(300);
        $("app-professional-page").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click side menu button")
    public void clickMenuServiceInfo() {
        sleep(300);
        $("app-service-viewer-page").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Open bookmarks from the menu")
    public void openBookmarksMenuEN() {
        $("app-main-menu").$(byText("Bookmarks")).click();
    }

    @Step("Verify bookmarks")
    public void verifyBookmark(String masterName, String masterCity) {
        $("app-bookmarks-list-page").$("main").shouldHave(
                text(masterName),
                text(masterCity)
        );
    }

    @Step("Verify online bookmarks")
    public void verifyBookmarkOnline(String masterName) {
        $("app-bookmarks-list-page").$("main").shouldHave(
                text(masterName)
        );
    }

    @Step("Verify bookmark deletion")
    public void verifyDelBookmark(String masterName) {
        $("app-bookmarks-list-page").$("main").shouldNotHave(text(masterName));
    }

    @Step("Remove bookmark")
    public void removeBookmark() {
        $("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Remove first bookmark")
    public void removeBookmark1() {
        $("app-professional-card", 0).$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Remove second bookmark")
    public void removeBookmark2() {
        $("app-professional-card", 1).$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Remove third bookmark")
    public void removeBookmark3() {
        $("app-professional-card", 2).$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Click Search button")
    public void clickSearchEN() {
        $("app-main-menu").$(byText("Search")).click();
        sleep(300);
    }

    @Step("Close default filters")
    public void closeFilters() {
        $("app-applied-filters").$("ion-chip").click();
        sleep(1000);
    }

    @Step("Input search text and press Enter")
    public void findService(String serviceName) {
        $("app-search").$("form").$("input").setValue("\"" + serviceName + "\"");
        sleep(1000);
        $("app-search").$("form").$("input").pressEnter();
    }

    @Step("Verify that the search result is correct")
    public void verifyServiceSearch(
            String FirstName,
            String LastName,
            String ServiceName,
            String ServicePrice) {
        $("app-search-result").$("ion-card-content").$("app-professional-card")
                .shouldHave(text(FirstName), text(LastName));
        $("app-search-result").$("ion-card-content").$("app-service-link").shouldHave(text(ServiceName));
        $("app-search-result").$("ion-card-content").$("app-price").shouldHave(text(ServicePrice));
    }

    @Step("Click booking button from the search results")
    public void clickFavSearch() {
        $("app-search").$("app-search-result").$("app-professional-card").$("ion-button").click();
    }

    @Step("Click on master's name in the search results")
    public void selectMasterSearch() {
        $("app-search").$("app-search-result").$("app-professional-card").$("a").click();
    }

    @Step("Click on service name in the search results")
    public void selectServiceSearch() {
        $("app-search").$("app-search-result").$("app-service-link").$("a").click();
    }

    @Step("Add master to favorites from master's profile page")
    public void clickFavMasterProfile() {
        $("app-professional-page").$("app-saved-professional-toggle").$("ion-button").click();
    }

    @Step("Add master to favorites from service info page")
    public void clickFavServiceInfo() {
        $("app-service-viewer-page").$("app-saved-professional-toggle").$("ion-button").click();
    }
}
