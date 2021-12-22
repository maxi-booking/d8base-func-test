package pages;

import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Search {
    @Step("Search page: Search {searchQuery}")
    public void search(String searchQuery) {
        $("app-search form input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("app-search form input").pressEnter();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Search page: Search without quotes {searchQuery}")
    public void searchNoQuotes(String searchQuery) {
        $("app-search form input").setValue(searchQuery);
        sleep(1000);
        $("app-search form input").pressEnter();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Main page: Search {searchQuery}")
    public void searchMain(String searchQuery) {
        $("ion-searchbar input").setValue("\"" + searchQuery + "\"");
        sleep(1000);
        $("ion-searchbar input").pressEnter();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Main page: Search without quotes {searchQuery}")
    public void searchMainNoQuotes(String searchQuery) {
        $("ion-searchbar input").setValue(searchQuery);
        sleep(1000);
        $("ion-searchbar input").pressEnter();
        Attach.screenshotAs("Screenshot");
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
        $("app-search app-search-result app-professional-card div.title a").click();
    }

    @Step("Verify that 'Best works' field exists")
    public void verifyBestWorksExists() {
        $("app-search-result section ion-label").shouldHave(text("Best works"));
    }

    @Step("Verify that 'Best works' field doesn't exists")
    public void verifyBestWorksNotExists() {
        $("app-search-result section ion-label").shouldNot(exist);
    }
}