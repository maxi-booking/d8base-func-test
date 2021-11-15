package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ProfessionalProfile {

// other person's professional profile methods

    @Step("Open the chat")
    public void clickChat() {
        $x("//ion-button[@class='book-btn ion-color ion-color-primary md button button-outline ion-activatable ion-focusable hydrated']").click();
    }
// your own professional profile methods

    @Step("Expand all collapse items")
    public void expandItems() {
        while ($("app-professional-page").$("app-collapse-item ion-item.item-lines-full").exists()) {
            $("app-professional-page").$("app-collapse-item ion-item.item-lines-full").scrollIntoView(false).click();
        }
    }

    @Step("Collapse all collapse items")
    public void collapseItems() {
        while ($("app-professional-page").$("app-collapse-item ion-item.item-lines-none").exists()) {
            $("app-professional-page").$("app-collapse-item ion-item.item-lines-none").scrollIntoView(false).click();
        }
    }

    @Step("Open reviews")
    public void clickReviews() {
        $("app-professional-page").$("a.review-count").scrollIntoView(false).click();
    }

    @Step("Click avatar edit button")
    public void clickAvatarEdit() {
        $("app-professional-page").$("a[routerlink='/profile'] img").scrollIntoView(false).click();
    }

    @Step("Click create reservation button")
    public void clickCreateReservation() {
        $("app-professional-page").$("ion-button.book-btn").scrollIntoView(false).click();
    }

   //address

    @Step("Address: click edit professional address")
    public void editProfessionalAddress() {
        $("app-professional-page").$("app-location-viewer").scrollIntoView(false).click();
    }

    @Step("Address: click add new address")
    public void clickAddNewAddress() {
        $("app-professional-page").$("app-add-button").$("ion-item").scrollIntoView(false).click();
    }

    @Step("Address: click back")
    public void addressClickBack() {
        $("app-master-location-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //main

    @Step("Main: click edit data")
    public void clickEditMain() {
        $("app-professional-page").$("ion-item[routerlink='edit']").scrollIntoView(false).click();
    }

    @Step("Main: click back")
    public void mainClickBack() {
        $("app-master-edit-page").$("main").$("ion-button").click();
        sleep(500);
    }

    //about

    @Step("About: click edit about")
    public void clickEditAbout() {
        $("app-professional-page").$("ion-button[routerlink='/profile/about']").scrollIntoView(false).click();
    }

    @Step("About: click back")
    public void aboutClickBack() {
        $("app-about-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //qualification

    @Step("Qualification: click add new qualification")
    public void clickAddNewQualification() {
        $("app-professional-page").$("app-add-button[routerlink='/profile/about'] ion-button").scrollIntoView(false).click();
    }

    @Step("Qualification: click back")
    public void qualificationClickBack() {
        $("app-master-experience-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //education

    @Step("Education: click add new education")
    public void clickAddNewEducation() {
        $("app-professional-page").$("app-add-button[routerlink='education-add/'] ion-button").scrollIntoView(false).click();
    }

    @Step("Education: click back")
    public void educationClickBack() {
        $("app-master-education-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //certificate

    @Step("Certificates: click add new certificate")
    public void clickAddNewCertificate() {
        $("app-professional-page").$("app-add-button[routerlink='certificate-add/'] ion-button").scrollIntoView(false).click();
    }

    @Step("Certificates: click back")
    public void certificateClickBack() {
        $("app-master-certificate-edit").$("main").$("ion-button").click();
        sleep(500);
    }

    //reviews

    @Step("Reviews: click 'all reviews'")
    public void clickAllReviews() {
        $("app-professional-page").$("app-review-cards").parent().$("ion-item a").scrollIntoView(false).click();
    }
}