package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Reviews {

    @Step("Click Outbox button")
    public void clickOutboxEN() {
        $("app-main-menu").$(byText("Sent orders")).click();
        sleep(300);
    }

    @Step("Select current orders")
    public void tabCurrentOrdersOutbox() {
        $("app-outbox-page").$("ion-segment-button", 0).click();
    }

    @Step("Select archived orders")
    public void tabArchivedOrdersOutbox() {
        $("app-outbox-page").$("ion-segment-button", 1).click();
    }

    @Step("Click master")
    public void clickMaster1() {
        $("app-outbox").$("app-professional-card", 0).$("a").click();
    }

    @Step("Click master")
    public void clickMaster2() {
        $("app-outbox").$("app-professional-card", 1).$("a").click();
    }

    @Step("Click master")
    public void clickMaster3() {
        $("app-outbox").$("app-professional-card", 2).$("a").click();
    }

    @Step("Open reviews tab")
    public void clickReviewTab() {
        $("app-professional-page").$(byText("Reviews")).click();
    }

    @Step("Click all reviews link")
    public void clickAllReviewsLink() {
        $("app-professional-page").$(byText("All reviews")).click();
    }

    @Step("Click review/rating button")
    public void clickReviews() {
        $("app-outbox-page").$("app-rating").$("a").click();
    }

    @Step("Click send review button")
    public void clickSendReviewButton() {
        $("app-reviews-list").$(byText("Send review")).click();
    }

    @Step("Choose rating 1 stars")
    public void choseRating1() {
        sleep(200);
        $("app-edit-review").$("app-rating-picker").$("ion-item", 0).click();
        sleep(200);
    }

    @Step("Choose rating 4 stars")
    public void choseRating4() {
        sleep(200);
        $("app-edit-review").$("app-rating-picker").$("ion-item", 3).click();
        sleep(200);
    }

    @Step("Choose rating 5 stars")
    public void choseRating5() {
        sleep(200);
        $("app-edit-review").$("app-rating-picker").$("ion-item", 4).click();
        sleep(200);
    }

    @Step("Type review text")
    public void sendReviewText(String reviewText) {
        $("app-edit-review").$("textarea").sendKeys(reviewText);
        sleep(200);
    }

    @Step("Add to favorite")
    public void addToFavorite() {
        sleep(200);
        $("app-edit-review").$("app-saved-professional-toggle").$("ion-button").click();
        sleep(200);
    }

    public void scrollDown() {
        $("app-edit-review").$("ion-row").$("ion-button", 1).scrollIntoView(false);
    }

    public void scrollDown2() {
        $("app-professional-page").$("main").$(byText("Reviews")).scrollIntoView(true);
    }

    @Step("Send review")
    public void pressSend() {
        sleep(200);
        $("app-edit-review").$("ion-row").$("ion-button", 1).click();
        sleep(3000);
    }

    @Step("Verify review")
    public void verifyReview(String masterName, String userName, String reviewText) {
        $("app-reviews-list").$("app-professional-card").shouldHave(text(masterName));
        $("app-reviews-list").$("ion-card").shouldHave(text(userName));
        $("app-reviews-list").$("ion-card").shouldHave(text(reviewText));
    }

    @Step("Open side menu")
    public void clickMenuMain() {
        sleep(300);
        $("app-reviews-list").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Open bookmarks from the menu")
    public void openBookmarksMenu() {
        $("app-main-menu").$(byText("Bookmarks")).click();
    }

    @Step("Verify bookmarks")
    public void verifyBookmark(String masterName) {
        $("app-bookmarks-list-page").$("app-professional-card").shouldHave(text(masterName));
        $("app-bookmarks-list-page").$("app-rating").shouldHave(text("5.00"));
    }

    @Step("Click menu button")
    public void clickMenuProfile() {
        sleep(300);
        $("app-profile").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Click menu button")
    public void clickMenu() {
        sleep(300);
        $("app-main").$("ion-menu-toggle").$("ion-button").click();
        sleep(300);
    }

    @Step("Open master's profile")
    public void openMasterProfile() {
        $("app-main-menu").$(byText("Professional profile")).click();
    }

    @Step("Click master's reviews")
    public void clickMasterReviews() {
        $("app-professional-page").$(".review-count").click();
    }

    @Step("Post master's comment")
    public void postMasterComment(String masterComment) {
        $("app-reviews-list").$("app-review-card").$("ion-button").scrollIntoView(true);
        $("app-reviews-list").$("app-review-card").$("ion-button").click();
        sleep(500);
        $("app-edit-review-comment").$("textarea").scrollIntoView(true);
        $("app-edit-review-comment").$("textarea").sendKeys(masterComment);
        sleep(500);
        $x("//ion-button[@class='md button button-block button-solid ion-activatable ion-focusable hydrated']").scrollIntoView(true);
        $x("//ion-button[@class='md button button-block button-solid ion-activatable ion-focusable hydrated']").click();
        sleep(2000);
    }

    @Step("Verify master's comment")
    public void verifyMasterComment(String masterComment) {
        $("app-reviews-list").$("app-review-card").shouldHave(text(masterComment));
    }
}
