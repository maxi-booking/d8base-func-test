package pages;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.Step;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class ServicePublish {

    @Step("Open the page")
    public void openPageUrl(String urlServicePublish) {
        open(urlServicePublish);
    }

    @Step("Choose a category: {value}")
    //value 0-8
    public void chooseCategory(Integer value) {
        sleep(500);
        $("ionic-selectable").click();
        sleep(500);
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", value).click();
        sleep(500);
        Attach.screenshotAs("Screenshot");

    }

    @Step("Choose a subcategory: {value}")
    //value 0-8
    public void chooseSubcategory(Integer value) {
        sleep(500);
        $("ionic-selectable", 1).scrollIntoView(false).click();
        sleep(500);
        $("ionic-selectable-modal").$("ion-content").$("ion-item", value).scrollIntoView(false).click();
        sleep(500);
        Attach.screenshotAs("Screenshot");

    }

    public void clickFirstStep() {
        $("app-service-publish-step-one").$("ion-button[type='submit']").scrollIntoView(false).click();
    }

    @Step("Enter a service name")
//    public void enterServiceName(String serviceName) {
//        $(".native-input[type='text']").val(serviceName);
//    }
    public void enterServiceName(String serviceName) {
        $("app-service-publish-wrapper").$("app-service-publish-step-two").$("input").scrollIntoView(false).val(serviceName);
    }

    @Step("Enter a service description")
    public void enterServiceDescription(String serviceDescription) {
        $(".native-textarea").scrollIntoView(false).val(serviceDescription);
    }

    @Step("Enter a service duration")
    public void setDuration(String serviceDurationDays, String serviceDurationHours, String serviceDurationMinutes) {
        $("app-duration-editor").$("input").scrollIntoView(false).setValue(serviceDurationDays);
        $("app-duration-editor").$("input", 1).scrollIntoView(false).setValue(serviceDurationHours);
        $("app-duration-editor").$("input", 2).scrollIntoView(false).setValue(serviceDurationMinutes);
    }

    @Step("Enter a fixed price")
    public void setPriceFixed(String servicePrice, Integer currency) {
        $("app-price-editor").$("input").scrollIntoView(false).setValue(servicePrice);
        $("app-price-editor").$("ionic-selectable").scrollIntoView(false).click();
        $("ionic-selectable-modal").$("ion-label", currency).scrollIntoView(false).click();
    }

    @Step("Enter a price range")
    public void setPriceRange(String serviceMinPrice, String serviceMaxPrice, Integer currency) {
        $("app-price-editor").$("ion-checkbox").click();
        sleep(200);
        $("app-price-editor").$("input", 0).scrollIntoView(false).setValue(serviceMinPrice);
        $("app-price-editor").$("input", 1).scrollIntoView(false).setValue(serviceMaxPrice);
        $("app-price-editor").$("ionic-selectable").scrollIntoView(false).click();
        $("ionic-selectable-modal").$("ion-label", currency).scrollIntoView(false).click();
    }

    @Step("Select a service location")
    public void selectServiceLocation(Integer location) {
        $("ion-radio-group").$("ion-item", location).scrollIntoView(false).click();
    }

    public void clickSecondStep() {
        $("app-service-publish-step-two").$("ion-button[type='submit']").scrollIntoView(false).click();
    }

    @Step("Add service photos")
    public void addServicePhotos(String picture) {
        if (picture.equals("random")) {
            Faker generate = new Faker(new Locale("en-US"));
            picture = String.valueOf(generate.number().numberBetween(1, 12)) + ".png";
        }
        sleep(2000);
        $("app-service-publish-step-three").$("app-image-carousel").$("input[type='file']").uploadFile(new File("src/test/resources/img/" + picture));
        sleep(2000);
    }

    @Step("Verify the amount of photos")
    public void verifyPictureUpload(Integer quantity) {
        $("app-service-publish-step-three").$("app-image-carousel").$("ion-slide", quantity).scrollIntoView(false).shouldBe(visible);
    }

    public void clickThirdStep() {
        $("app-service-publish-step-three").$("ion-button[type='submit']").scrollIntoView(false).click();
        sleep(500);
    }

    @Step("Fill email")
    public void fillEmail(String serviceEmail) {
        $("app-service-publish-step-four").$("input", 0).scrollIntoView(false).val(serviceEmail);
    }

    @Step("Fill user info")
    public void fillUserInfo(String userFirstName, String userLastName, String userPassword, String userCountry, String userCity) {
        sleep(200);
        $("app-service-publish-step-four").$("input", 1).scrollIntoView(false).val(userFirstName);
        $("app-service-publish-step-four").$("input", 2).scrollIntoView(false).val(userLastName);
        $("app-service-publish-step-four").$("input", 3).scrollIntoView(false).val(userPassword);
        $("app-service-publish-step-four").$("input", 4).scrollIntoView(false).val(userPassword);

        $("app-service-publish-step-four").$("app-country-selector").$("button").scrollIntoView(false).click();
        sleep(300);
        $("ionic-selectable-modal").$("input").scrollIntoView(false).sendKeys(userCountry);
        sleep(300);
        $("ionic-selectable-modal").$("ion-label", 0).scrollIntoView(false).click();
        sleep(300);
        $("app-service-publish-step-four").$("app-city-selector").$("button").scrollIntoView(false).click();
        sleep(300);
        $("ionic-selectable-modal").$("input").scrollIntoView(false).sendKeys(userCity);
        sleep(300);
        $("ionic-selectable-modal").$("ion-item", 0).scrollIntoView(false).click();
        sleep(300);
    }

    public void clickFourthStep() {
        $("app-service-publish-step-four").$("ion-button[type='submit']").scrollIntoView(false).click();
        sleep(3000);
    }

    @Step("Enter first name: {firstName}")
    public void enterFirstName(String firstName) {
        $("app-service-publish-step-five").$("form").$("input[name='name']").sendKeys(firstName);
    }

    @Step("Enter last name: {lastName}")
    public void enterLastName(String lastName) {
        $("app-service-publish-step-five").$("form").$("input[name='last-name']").sendKeys(lastName);
    }

    @Step("Select gender: {gender} ")
    public void selectGender(String gender) {
        if (gender.equals("male") || gender.equals("man")) {
            String value = "man";
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-icon[name='" + value + "-outline']").parent().click();
            sleep(200);
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-segment-button", 0).should(cssClass("segment-button-checked"));
        } else if (gender.equals("female") || gender.equals("woman")) {
            String value = "woman";
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-icon[name='" + value + "-outline']").parent().click();
            sleep(200);
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-segment-button", 1).should(cssClass("segment-button-checked"));
        } else {
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-segment-button", 2).parent().click();
            sleep(200);
            $("app-service-publish-step-five").$("app-gender-selector").$("ion-segment-button", 2).should(cssClass("segment-button-checked"));
        }
    }

    @Step("Upload avatar")
    public void uploadAvatar(String avatar) {
        if (avatar.equals("random")) {
            Faker generate = new Faker(new Locale("en-US"));
            avatar = String.valueOf(generate.number().numberBetween(1, 12)) + ".png";
        }
        sleep(500);
        $("app-service-publish-step-five").$("app-picture-selector").$("input[type='file']").uploadFile(new File("src/test/resources/img/" + avatar));
        sleep(1000);
    }

    public void clickFifthStep() {
        $("app-service-publish-step-five").$("ion-button[type='submit']").scrollIntoView(false).click();
        sleep(200);
    }

    @Step("Select private person or company: {value}")
    public void selectPersonOrCompany(String value) {
        $("app-service-publish-step-six").$("form").$("ion-segment-button[value='" + value + "']").scrollIntoView(false).click();
    }

    @Step("Enter company name: {value}")
    public void companyName(String value) {
        $("app-service-publish-step-six").$("form").$("input[name='company-name']").scrollIntoView(false).setValue(value);
    }

    @Step("Fill 'About' text")
    public void fillAbout(String value) {
        $("app-service-publish-step-six").$("form").$("textarea[name='description']").scrollIntoView(false).setValue(value);
    }

    @Step("Fill the specialization form")
    public void fillSpecialization(String serviceSpecialization) {
        $("app-service-publish-step-six").$("form").$("input").scrollIntoView(false).setValue(serviceSpecialization);
    }

    @Step("Select level: {value}")
    public void selectLevel(String value) {
        $("app-service-publish-step-six").$("form").$("ionic-selectable").scrollIntoView(false).click();
        sleep(500);
        switch (value) {
            case "random":
                Faker generate = new Faker(new Locale("en-US"));
                Integer level = generate.number().numberBetween(0, 2);
                $("ion-modal").$("ionic-selectable-modal").$("ion-list").$("ion-item", level).click();
                break;
            case "junior":
                $("ion-modal").$("ionic-selectable-modal").$("ion-list").$("ion-item", 0).click();
                break;
            case "middle":
                $("ion-modal").$("ionic-selectable-modal").$("ion-list").$("ion-item", 1).click();
                break;
            case "senior":
                $("ion-modal").$("ionic-selectable-modal").$("ion-list").$("ion-item", 2).click();
                break;
            default:
                fail();
                break;
        }
    }

    public void clickSixthStep() {
        $("app-service-publish-step-six").$("ion-button[type='submit']").scrollIntoView(false).click();
        sleep(500);
    }

    @Step("Set a schedule")
    public void fillSchedule() {
        $("app-service-publish-step-seven").$("form").$("ion-icon").scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 0).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 1).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 2).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 3).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 4).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 5).scrollIntoView(false).click();
        $("app-add-button").$("ion-label").scrollIntoView(false).click();
        $("app-timetable-add-time-popover").$("ion-label", 6).scrollIntoView(false).click();
        $("app-timetable").$("app-content-wrapper").$("ion-button[type='submit']").scrollIntoView(false).click();
    }

    @Step("Set a schedule")
    public void fillScheduleLite() {
        sleep(300);
        $("app-service-publish-step-seven").$("form").$("ion-icon").scrollIntoView(false).click();
        sleep(1000);
        $("app-timetable").$("app-add-button").$("ion-item").scrollIntoView(false).click();
        sleep(300);
        $("app-timetable-add-time-popover").$("ion-label", 5).scrollIntoView(false).click();
        sleep(300);
        $("app-timetable").$("app-add-button").$("ion-item").scrollIntoView(false).click();
        sleep(300);
        $("app-timetable-add-time-popover").$("ion-label", 6).scrollIntoView(false).click();
        sleep(1000);
        $("app-timetable").$("ion-button[type='submit']").scrollIntoView(false).click();
        sleep(300);
    }

    @Step("Confirm Instant Booking")
    public void confirmInstantBooking() {
        sleep(2000);
        $("app-service-publish-step-seven").$("form").$("ion-item", 9).scrollIntoView(false).click();
        Attach.screenshotAs("Screenshot");
    }

    @Step("Fill a service geography")
    public void fillServiceGeo(String serviceCountry, String serviceCity, String serviceAddress) {
        $("app-country-selector").$("button").scrollIntoView(false).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").scrollIntoView(false).sendKeys(serviceCountry);
        sleep(500);
        $("ionic-selectable-modal").$("ion-label", 0).scrollIntoView(false).click();

        $("app-city-selector").$("button").scrollIntoView(false).click();
        sleep(1000);
        $("ionic-selectable-modal").$("input").scrollIntoView(false).sendKeys(serviceCity);
        sleep(500);
        $("ionic-selectable-modal").$("ion-item", 0).scrollIntoView(false).click();

        $("app-service-publish-step-seven").$("form").$("textarea").scrollIntoView(false).setValue(serviceAddress);
    }

    @Step("Fill a service distance")
    public void fillServiceDistance(String serviceDistance) {
        $("app-service-publish-step-seven").$("form").$("ion-input").$("input").scrollIntoView(false).setValue(serviceDistance);
    }

    @Step("Select payment by cash")
    public void selectPaymentByCash() {
        $("app-service-publish-step-seven").$("form").$("ion-list").$("ion-item", 0).scrollIntoView(false).click();
    }

    @Step("Select online payment")
    public void selectOnlinePayment() {
        $("app-service-publish-step-seven").$("form").$("ion-list").$("ion-item", 1).scrollIntoView(false).click();
        Attach.screenshotAs("Screenshot");
    }

    public void clickSeventhStep() {
        $("app-service-publish-step-seven").$("ion-button[type='submit']").scrollIntoView(false).click();
    }

    @Step("Verify data")
    public void checkPublishFormOnline(String serviceName, String serviceTotalDuration, String serviceDescription) {
        $("h2").shouldHave(Condition.textCaseSensitive(serviceName));

        long serviceTotalDurationLong = Long.parseLong(serviceTotalDuration),
                serviceDurationDaysLong = serviceTotalDurationLong / 24 / 60,
                serviceDurationHoursLong = serviceTotalDurationLong / 60 % 24,
                serviceDurationMinutesLong = serviceTotalDurationLong % 60;

        String serviceDurationDays = Long.toString(serviceDurationDaysLong),
                serviceDurationHours = Long.toString(serviceDurationHoursLong),
                serviceDurationMinutes = Long.toString(serviceDurationMinutesLong);

        if (serviceTotalDurationLong > 1440) {
            $("app-duration-viewer").shouldHave(text(serviceDurationDays), text(serviceDurationHours), text(serviceDurationMinutes));
        } else if (serviceTotalDurationLong > 60) {
            $("app-duration-viewer").shouldHave(text(serviceDurationHours), text(serviceDurationMinutes));
        } else {
            $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
        }

        $("app-service-publish-final-step").$("ion-content").$("ion-item", 2).shouldHave(Condition.textCaseSensitive(serviceDescription));
    }

    public void checkPublishFormWithAddress(String serviceName, String serviceTotalDuration, String serviceDescription, String serviceCountry, String serviceCity, String serviceAddress) {
        $("h2").shouldHave(Condition.textCaseSensitive(serviceName));

        long serviceTotalDurationLong = Long.parseLong(serviceTotalDuration),
                serviceDurationDaysLong = serviceTotalDurationLong / 24 / 60,
                serviceDurationHoursLong = serviceTotalDurationLong / 60 % 24,
                serviceDurationMinutesLong = serviceTotalDurationLong % 60;

        String serviceDurationDays = Long.toString(serviceDurationDaysLong),
                serviceDurationHours = Long.toString(serviceDurationHoursLong),
                serviceDurationMinutes = Long.toString(serviceDurationMinutesLong);

        System.out.println(serviceTotalDuration + " total");
        System.out.println(serviceDurationDays + " days");
        System.out.println(serviceDurationHours + " hours");
        System.out.println(serviceDurationMinutes + " minutes");

        if (serviceTotalDurationLong > 1440) {
            $("app-duration-viewer").shouldHave(text(serviceDurationDays), text(serviceDurationHours), text(serviceDurationMinutes));
        } else if (serviceTotalDurationLong > 60) {
            $("app-duration-viewer").shouldHave(text(serviceDurationHours), text(serviceDurationMinutes));
        } else {
            $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
        }

        $("app-service-publish-final-step").$("ion-content").$("ion-item", 2).shouldHave(Condition.textCaseSensitive(serviceDescription));
        $("app-service-location").shouldHave(Condition.text(serviceCountry + ", " + serviceCity + ", " + serviceAddress));
    }

    public void checkPrice(String servicePrice) {
        String servicePriceActual = $("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            fail();
        }
    }

    public void checkMinMaxPrice(String serviceMinPrice, String serviceMaxPrice) {
        String servicePriceActual = $("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(serviceMinPrice) || !servicePriceActual.contains(serviceMaxPrice)) {
            fail();
        }
    }

    @Step("Publish a service")
    public void publishService() {
        $("app-service-publish-final-step").$("ion-content").$("ion-button", 1).scrollIntoView(false).click();
        sleep(5000);
    }

    @Step("Verify that 'Field is required.' error appeared")
    public void fieldIsRequiredPresent() {
        $(byText("Field is required.")).shouldBe(visible);
    }

    @Step("Verify publishing step: step two")
    public void isStepTwo() {
        $("app-service-publish-step-two").$("ion-button").shouldBe(visible);
    }

    @Step("Verify that category/subcategory title language is in English")
    public void verifyCategorySubcategoryTitleLanguageEng() {
        String category = $("app-service-publish-step-one").$("app-category-selector").$("ion-label").getText();
        String subcategory = $("app-service-publish-step-one").$("app-subcategory-selector").$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (!category.equals("Select category")) {
            fail();
        }
        if (!subcategory.equals("Select subcategory")) {
            fail();
        }
    }

    @Step("Verify that category/subcategory title language is not in English")
    public void verifyCategorySubcategoryTitleLanguageNotEng() {
        String category = $("app-service-publish-step-one").$("app-category-selector").$("ion-label").getText();
        String subcategory = $("app-service-publish-step-one").$("app-subcategory-selector").$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (category.equals("Select category")) {
            fail();
        }
        if (subcategory.equals("Select subcategory")) {
            fail();
        }
    }

    @Step("Verify that category list language is in English")
    public void verifyCategoryListLanguageEng() {
        sleep(500);
        $("ionic-selectable").click();
        sleep(500);
        String value = $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", 0).$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (!value.equals("Tutors")) {
            fail();
        }
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", 0).scrollIntoView(false).click();
        sleep(500);
    }

    @Step("Verify that subcategory list language is in English")
    public void verifySubcategoryListLanguageEng() {
        sleep(500);
        $("ionic-selectable", 1).scrollIntoView(false).click();
        sleep(500);
        String value = $("ionic-selectable-modal").$("ion-content").$("ion-item", 0).$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (!value.equals("English language")) {
            fail();
        }
        $("ionic-selectable-modal").$("ion-content").$("ion-item", 0).scrollIntoView(false).click();
        sleep(500);
    }

    @Step("Verify that category list language is not in English")
    public void verifyCategoryListLanguageNotEng() {
        sleep(500);
        $("ionic-selectable").click();
        sleep(500);
        String value = $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", 0).$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (value.equals("Tutors")) {
            fail();
        }
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", 0).click();
        sleep(500);
    }

    @Step("Verify that subcategory list language is not in English")
    public void verifySubcategoryListLanguageNotEng() {
        sleep(500);
        $("ionic-selectable", 1).scrollIntoView(false).click();
        sleep(500);
        String value = $("ionic-selectable-modal").$("ion-content").$("ion-item", 0).$("ion-label").getText();
        Attach.screenshotAs("Screenshot");
        if (value.equals("English language")) {
            fail();
        }
        $("ionic-selectable-modal").$("ion-content").$("ion-item", 0).scrollIntoView(false).click();
        sleep(500);
    }
}