package pages;

import helpers.Attach;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class Search {
    @Step("Search page: Search without 'quotes'")
    public void search(String searchQuery) {
        $("app-search").$("form").$("input").setValue(searchQuery);
        sleep(1000);
        $("app-search").$("form").$("input").pressEnter();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Main page: Search without 'quotes'")
    public void searchMain(String searchQuery) {
        $("ion-searchbar").$("input").setValue(searchQuery);
        sleep(1000);
        $("ion-searchbar").$("input").pressEnter();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Click on the first name in search results")
    public void clickProfessionalsName() {
        $("app-search").$("app-search-result").$("app-professional-card").$("div.title").$("a").click();
    }
}