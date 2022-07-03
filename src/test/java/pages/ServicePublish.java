package pages;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import helpers.PriceFormatter;
import helpers.ServiceDuration;
import io.qameta.allure.Step;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Locale;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.SelectableModal.selectModal;
import static helpers.ServiceDuration.getDuration;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class ServicePublish extends config.TestBase {

    @Step("Open the page")
    public void openPageUrl(String urlServicePublish) {
        open(urlServicePublish);
    }

    @Step("Choose a category: {value}")
    //value 0-8
    public void chooseCategory(Integer value) {
        $("app-category-selector").$("ionic-selectable").shouldBe(visible, Duration.ofSeconds(10));
        $("app-category-selector").$("ionic-selectable").scrollIntoView(true).click();
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", value).shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", value).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Choose a subcategory: {value}")
    //value 0-8
    public void chooseSubcategory(Integer value) {
        $("app-subcategory-selector").$("ionic-selectable").shouldBe(visible, Duration.ofSeconds(10));
        $("app-subcategory-selector").$("ionic-selectable").scrollIntoView(true).click();
        $("ionic-selectable-modal").$("ion-content").$("ion-item", value).shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable-modal").$("ion-content").$("ion-item", value).scrollIntoView(true).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void clickFirstStep() {
        step("Click first step confirmation", () -> {
            $("app-service-publish-step-one").$("ion-button[type='submit']").scrollIntoView(true).click();
        });
    }

    @Step("Enter a service name")
//    public void enterServiceName(String serviceName) {
//        $(".native-input[type='text']").val(serviceName);
//    }
    public void enterServiceName(String serviceName) {
        $("app-service-publish-wrapper").$("app-service-publish-step-two").$("input").scrollIntoView(true).val(serviceName);
    }

    @Step("Enter a service description")
    public void enterServiceDescription(String serviceDescription) {
        $(".native-textarea").scrollIntoView(true).val(serviceDescription);
    }

    @Step("Enter a service duration")
    public void setDuration(String serviceDurationTotal) {
        ServiceDuration duration = getDuration(serviceDurationTotal);
        $("app-duration-editor").$("input").scrollIntoView(true).setValue(duration.days);
        $("app-duration-editor").$("input", 1).scrollIntoView(true).setValue(duration.hours);
        $("app-duration-editor").$("input", 2).scrollIntoView(true).setValue(duration.minutes);
    }

    @Step("Enter a service duration")
    public void setDuration(int serviceDurationTotal) {
        ServiceDuration duration = getDuration(serviceDurationTotal);
        $("app-duration-editor").$("input").scrollIntoView(true).setValue(duration.days);
        $("app-duration-editor").$("input", 1).scrollIntoView(true).setValue(duration.hours);
        $("app-duration-editor").$("input", 2).scrollIntoView(true).setValue(duration.minutes);
    }

    @Step("Enter a fixed price")
    public void setPriceFixed(String servicePrice, Integer currency) {
        $("app-price-editor").$("input").scrollIntoView(true).setValue(servicePrice);
        $("app-price-editor").$("ionic-selectable").scrollIntoView(true).click();
        $("ionic-selectable-modal").$("ion-label", currency).scrollIntoView(true).click();
    }

    @Step("Enter a price range")
    public void setPriceRange(String serviceMinPrice, String serviceMaxPrice, Integer currency) {
        $("app-price-editor").$("ion-checkbox").click();
        sleep(200);
        $("app-price-editor").$("input", 0).scrollIntoView(true).setValue(serviceMinPrice);
        $("app-price-editor").$("input", 1).scrollIntoView(true).setValue(serviceMaxPrice);
        $("app-price-editor").$("ionic-selectable").scrollIntoView(true).click();
        $("ionic-selectable-modal").$("ion-label", currency).scrollIntoView(true).click();
    }

    @Step("Select a service location")
    public void selectServiceLocation(Integer location) {
        $("ion-radio-group").$("ion-item", location).scrollIntoView(true).click();
    }

    public void clickSecondStep() {
        step("Click second step confirmation", () -> {
            $("app-service-publish-step-two").$("ion-button[type='submit']").scrollIntoView(true).click();
        });
    }

    @Step("Add service photos")
    public void addServicePhotos(String picture) {
        if (picture.equals("random")) {
            Faker generate = new Faker(new Locale("en-US"));
            picture = generate.number().numberBetween(1, 12) + ".png";
        }
        $("app-service-publish-step-three app-image-carousel input[type='file']").shouldBe(visible, Duration.ofSeconds(10));
        $("app-service-publish-step-three app-image-carousel input[type='file']").uploadFile(new File("src/test/resources/img/" + picture));
        sleep(2000);
    }

    @Step("Verify the amount of photos")
    public void verifyPictureUpload(Integer quantity) {
        $("app-service-publish-step-three").$("app-image-carousel").$("ion-slide", quantity).scrollIntoView(true).shouldBe(visible);
    }

    public void clickThirdStep() {
        step("Click third step confirmation", () -> {
            $("app-service-publish-step-three").$("ion-button[type='submit']").scrollIntoView(true).click();
            sleep(500);
        });
    }

    @Step("Fill email")
    public void fillEmail(String serviceEmail) {
        $("app-service-publish-step-four").$("input", 0).scrollIntoView(true).val(serviceEmail);
    }

    @Step("Fill user info")
    public void fillUserInfo(String userFirstName, String userLastName, String userPassword, String userCountry, String userCity) {
        $("app-service-publish-step-four").$("input", 1).shouldBe(visible, Duration.ofSeconds(10));
        $("app-service-publish-step-four").$("input", 1).scrollIntoView(true).val(userFirstName);
        $("app-service-publish-step-four").$("input", 2).scrollIntoView(true).val(userLastName);
        $("app-service-publish-step-four").$("input", 3).scrollIntoView(true).val(userPassword);
        $("app-service-publish-step-four").$("input", 4).scrollIntoView(true).val(userPassword);

        $("app-service-publish-step-four").$("app-country-selector").$("button").scrollIntoView(true).click();
        selectModal(userCountry);
        $("app-service-publish-step-four").$("app-city-selector").$("button").scrollIntoView(true).click();
        selectModal(userCity);
    }

    public void clickFourthStep() {
        step("Click fourth step confirmation", () -> {
            $("app-service-publish-step-four").$("ion-button[type='submit']").scrollIntoView(true).click();
            $("ion-loading ion-spinner[role='progressbar']").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void clickFourthStepNoDelay() {
        step("Confirm fourth step", () -> {
            $("app-service-publish-step-four").$("ion-button[type='submit']").scrollIntoView(true).click();
        });
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
            avatar = generate.number().numberBetween(1, 12) + ".png";
        }
        $("app-service-publish-step-five app-picture-selector input[type='file']").shouldBe(visible, Duration.ofSeconds(10));
        $("app-service-publish-step-five app-picture-selector input[type='file']").uploadFile(new File("src/test/resources/img/" + avatar));
        sleep(1000);
    }

    public void clickFifthStep() {
        step("Click fifth step confirmation", () -> {
            $("app-service-publish-step-five").$("ion-button[type='submit']").scrollIntoView(true).click();
            sleep(200);
        });
    }

    @Step("Select private person or company: {value}")
    public void selectPersonOrCompany(String value) {
        $("app-service-publish-step-six").$("form").$("ion-segment-button[value='" + value + "']").scrollIntoView(true).click();
    }

    @Step("Enter company name: {value}")
    public void companyName(String value) {
        $("app-service-publish-step-six").$("form").$("input[name='company-name']").scrollIntoView(true).setValue(value);
    }

    @Step("Fill 'About' text")
    public void fillAbout(String value) {
        $("app-service-publish-step-six").$("form").$("textarea[name='description']").scrollIntoView(true).setValue(value);
    }

    @Step("Fill the specialization form")
    public void fillSpecialization(String serviceSpecialization) {
        $("app-service-publish-step-six").$("form").$("input").scrollIntoView(true).setValue(serviceSpecialization);
    }

    @Step("Select level: {value}")
    public void selectLevel(String value) {
        $("app-service-publish-step-six").$("form").$("ionic-selectable").scrollIntoView(true).click();
        $("ion-modal ionic-selectable-modal ion-list ion-item").shouldBe(visible, Duration.ofSeconds(10));
        switch (value) {
            case "random":
                Faker generate = new Faker(new Locale("en-US"));
                int level = generate.number().numberBetween(0, 2);
                $("ion-modal ionic-selectable-modal ion-list ion-item", level).click();
                break;
            case "junior":
                $("ion-modal ionic-selectable-modal ion-list ion-item", 0).click();
                break;
            case "middle":
                $("ion-modal ionic-selectable-modal ion-list ion-item", 1).click();
                break;
            case "senior":
                $("ion-modal ionic-selectable-modal ion-list ion-item", 2).click();
                break;
            default:
                fail();
                break;
        }
    }

    public void clickSixthStep() {
        step("Click sixth step confirmation", () -> {
            $("app-service-publish-step-six").$("ion-button[type='submit']").scrollIntoView(true).click();
            sleep(500);
        });
    }

    public void step7ClickEditSchedule() {
        step("Step seven: edit schedule click", () -> {
            $("app-service-publish-step-seven ion-item[routerlink='timetable']").scrollIntoView(true).click();
            $("app-timetable app-schedule-editor ion-checkbox").shouldBe(visible, Duration.ofSeconds(10));
        });
    }

    @Step("Set a schedule")
    public void fillSchedule() {
        $("app-service-publish-step-seven").$("form").$("ion-icon").scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 0).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 1).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 2).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 3).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 4).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 5).scrollIntoView(true).click();
        $("app-add-button").$("ion-label").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").$("ion-label", 6).scrollIntoView(true).click();
        $("app-timetable").$("app-content-wrapper").$("ion-button[type='submit']").scrollIntoView(true).click();
    }

    public void fillScheduleLite() {
        step("Set a schedule", () -> {
            $("app-service-publish-step-seven ion-item[routerlink='timetable']").scrollIntoView(true).click();
            $("app-timetable app-schedule-editor ion-checkbox").shouldBe(visible, Duration.ofSeconds(10));
            $("app-timetable app-column-header ion-buttons ion-button", 1).click();
            $("app-timetable-add-time-popover").shouldBe(visible, Duration.ofSeconds(10));
            sleep(200);
            $("app-timetable-add-time-popover ion-label", 5).click();
            $("app-timetable-add-time-popover").shouldNotBe(visible, Duration.ofSeconds(10));
            $("app-timetable app-column-header ion-buttons ion-button", 1).click();
            $("app-timetable-add-time-popover").shouldBe(visible, Duration.ofSeconds(10));
            sleep(200);
            $("app-timetable-add-time-popover ion-label", 6).click();
            $("app-timetable-add-time-popover").shouldNotBe(visible, Duration.ofSeconds(10));
            $("app-timetable ion-button[type='submit']").click();
            $("app-timetable").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    /* old method
    @Step("Set a schedule")
    public void fillScheduleLite() {
        sleep(300);
        $("app-service-publish-step-seven").$("form").$("ion-icon").scrollIntoView(true).click();
        $("app-timetable app-add-button ion-item").shouldBe(visible, Duration.ofSeconds(10));
        $("app-timetable app-add-button ion-item").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").shouldBe(visible, Duration.ofSeconds(10));
        $("app-timetable-add-time-popover").$("ion-label", 5).scrollIntoView(true).click();
        $("app-timetable-add-time-popover").shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-timetable app-add-button ion-item").scrollIntoView(true).click();
        $("app-timetable-add-time-popover").shouldBe(visible, Duration.ofSeconds(10));
        $("app-timetable-add-time-popover").$("ion-label", 6).scrollIntoView(true).click();
        $("app-timetable-add-time-popover").shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-timetable ion-button[type='submit']").scrollIntoView(true).click();
        $("app-timetable").shouldNotBe(visible, Duration.ofSeconds(10));
    }
     */

    @Step("Verify that schedule can not be edited")
    public void verifyNoScheduleEditButton() {
        $("app-service-publish-step-seven form ion-item[routerlink='timetable'] ion-icon").shouldNotBe(visible);
    }

    @Step("Verify that schedule can not be edited")
    public void verifyScheduleInfiniteTime() {
        int i = 0;
        while ($("app-service-publish-step-seven app-schedule-viewer ion-text", i).exists()) {
            $("app-service-publish-step-seven app-schedule-viewer ion-text", i).shouldHave(text("00:00"), text("23:59"));
            i++;
        }
    }

    @Step("Confirm Instant Booking")
    public void instantBooking(Boolean value) {
        if (value.equals(true)) {
            $("app-service-publish-step-seven form ion-item.item-label").scrollIntoView(false).click();
        }
    }

    @Step("Fill service geography")
    public void fillServiceGeo(String serviceCountry, String serviceCity, String serviceAddress) {
        $("app-country-selector button").scrollIntoView(false).click();
        selectModal(serviceCountry);
        $("app-city-selector button").scrollIntoView(false).click();
        selectModal(serviceCity);
        $("app-service-publish-step-seven").$("form").$("textarea").scrollIntoView(false).setValue(serviceAddress);
    }

    @Step("Fill a service distance")
    public void fillServiceDistance(String serviceDistance) {
        $("app-service-publish-step-seven").$("form").$("ion-input").$("input").scrollIntoView(false).setValue(serviceDistance);
    }

    public void PaymentOptions(Boolean cash, Boolean online, Data data) {
        step("Selected payment options: by cash - " + cash + ", online - " + online, () -> {
            if (cash.equals(true)) {
                $("app-service-publish-step-seven").$("form").$("ion-list").$("ion-item", 0).scrollIntoView(false).click();
            }
            if (online.equals(true)) {
                $("app-service-publish-step-seven").$("form").$("ion-list").$("ion-item", 1).scrollIntoView(false).click();
            }

            if (cash.equals(true)) {
                if (online.equals(true)) {
                    data.payment = paymentCashOnline;
                } else {
                    data.payment = paymentCash;
                }
            } else if (online.equals(true)) {
                data.payment = paymentOnline;
            }
        });
    }

    public void addTag(String... tags) {
        step("Add tags: " + Arrays.toString(tags), () -> {
            for (String tag : tags) {
                $("app-tags-step-component input[name='teg']").scrollIntoView(false).setValue(tag).pressEnter();

            }
        });
    }

    public void clickTagStep() {
        step("Click tag step confirmation", () -> {
            $("app-tags-step-component ion-button[type='submit']").scrollIntoView(false).click();
        });
    }

    public void clickSeventhStep() {
        step("Click seventh step confirmation", () -> {
            $("app-service-publish-step-seven ion-button[type='submit']").scrollIntoView(false).click();
        });
    }

    public void verifySeventhStepVisible() {
        step("Step seven (with schedule) should be visible", () -> {
            $("app-service-publish-step-seven").shouldBe(visible);
        });
    }

    public void verifySeventhStepContinueIsNotClickable() {
        step("Seventh step, 'Continue' should not be clickable", () -> {
            $("app-service-publish-step-seven ion-button[type='submit']").shouldHave(cssClass("button-disabled"));
            $("app-service-publish-step-seven ion-button[type='submit'][aria-disabled='true']").should(exist);
        });
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

        if (serviceTotalDurationLong >= 1440) {
            $("app-duration-viewer").shouldHave(text(serviceDurationDays));
            if (serviceDurationHoursLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationHours));
            }
            if (serviceDurationMinutesLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
            }
        } else if (serviceTotalDurationLong >= 60) {
            $("app-duration-viewer").shouldHave(text(serviceDurationHours));
            if (serviceDurationMinutesLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
            }
        } else {
            $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
        }

        $("app-service-preview ion-content app-service-widget ion-item", 1).$("div").shouldHave(Condition.textCaseSensitive(serviceDescription));
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

        if (serviceTotalDurationLong >= 1440) {
            $("app-duration-viewer").shouldHave(text(serviceDurationDays));
            if (serviceDurationHoursLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationHours));
            }
            if (serviceDurationMinutesLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
            }
        } else if (serviceTotalDurationLong >= 60) {
            $("app-duration-viewer").shouldHave(text(serviceDurationHours));
            if (serviceDurationMinutesLong > 0) {
                $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
            }
        } else {
            $("app-duration-viewer").shouldHave(text(serviceDurationMinutes));
        }

        $("app-service-preview ion-content app-service-widget ion-item", 1).shouldHave(Condition.textCaseSensitive(serviceDescription));
        $("app-service-preview app-service-location app-location-viewer").shouldHave(Condition.text(serviceCountry + ", " + serviceCity + ", " + serviceAddress));
    }

    @Step("Check that actual service price equals to expected ({servicePrice})")
    public void checkPrice(String servicePrice) {
        String servicePriceActual = $("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("[,.]", "");
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(servicePrice)) {
            System.out.println(servicePriceActual + " (actual) not equal to " + servicePrice + " (expected)");
            fail();
        }
    }

    public void checkMinMaxPrice(String serviceMinPrice, String serviceMaxPrice) {
        String servicePriceActual = $("app-price").getText();
        servicePriceActual = servicePriceActual.replaceAll("\\s+", "");
        if (!servicePriceActual.contains(serviceMinPrice) || !servicePriceActual.contains(serviceMaxPrice)) {
            System.out.println(servicePriceActual + " (actual) not equal to " + serviceMaxPrice + " (expected max) \n or " + serviceMinPrice + " (expected min)");
            fail();
        }
    }

    public void checkPreviewSearch(String firstName, String lastName, String specialization, String serviceName) {
        step("Verify service parameters", () -> {
            $("app-service-search-preview app-professional-card div.title").shouldHave(text(firstName + " " + lastName));
            $("app-service-search-preview app-professional-card div.subtitle").shouldHave(text(specialization));
            /*
            String descriptionValue = $("app-professional-page app-shorten div").getText();
            if ($("app-service-search-preview app-shorten div.ext").exists() && !descriptionValue.equals(description)) {
                $("app-service-search-preview app-shorten div.ext").click();
            }
            $("app-service-search-preview-page app-shorten").shouldHave(text(description));
            if ($("app-service-search-preview app-shorten div.ext").exists() && descriptionValue.equals(description)) {
                $("app-service-search-preview app-shorten div.ext").click();
            }
            */
            $("app-service-search-preview app-service-link-search-card div.title").shouldHave(text(serviceName));
        });
    }

    public void checkPreviewSearchFavorite(boolean favorite) {
        step("A user in favorites? " + favorite, () -> {
            if (favorite) {
                $("app-service-search-preview app-professional-card ion-icon[name='heart']").shouldHave(cssClass("ion-color-danger"));
            } else {
                $("app-service-search-preview app-professional-card ion-icon[name='heart-outline']").shouldHave(cssClass("ion-color-medium"));
            }
        });
    }

    public void checkRating(int rating) {
        step("Verify that service rating is " + rating, () -> {
            $$("app-rating").filter(visible).get(0).shouldHave(text(String.valueOf(rating)));
        });
    }

    public void checkRating(int rating, int index) {
        step("Verify that service rating is " + rating, () -> {
            $$("app-rating").filter(visible).get(index).shouldHave(text(String.valueOf(rating)));
        });
    }

    public void checkPreviewServiceNameCard(String serviceName) {
        step("Verify that service name is " + serviceName, () -> {
            $("app-service-preview ion-card-subtitle").shouldHave(text(serviceName));
        });
    }

    public void checkDurationOnThePage(String serviceDuration, int index) {
        step("Service duration should be: " + serviceDuration + ", index-" + index, () -> {

            long serviceTotalDurationLong = Long.parseLong(serviceDuration),
                    serviceDurationDaysLong = serviceTotalDurationLong / 24 / 60,
                    serviceDurationHoursLong = serviceTotalDurationLong / 60 % 24,
                    serviceDurationMinutesLong = serviceTotalDurationLong % 60;

            String serviceDurationDays = Long.toString(serviceDurationDaysLong),
                    serviceDurationHours = Long.toString(serviceDurationHoursLong),
                    serviceDurationMinutes = Long.toString(serviceDurationMinutesLong);

            if (serviceTotalDurationLong >= 1440) {
                $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationDays));
                if (serviceDurationHoursLong > 0) {
                    $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationHours));
                }
                if (serviceDurationMinutesLong > 0) {
                    $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationMinutes));
                }
            } else if (serviceTotalDurationLong >= 60) {
                $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationHours));
                if (serviceDurationMinutesLong > 0) {
                    $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationMinutes));
                }
            } else {
                $$("app-duration-viewer").filter(visible).get(index).shouldHave(text(serviceDurationMinutes));
            }
        });
    }

    public void checkFavorite(boolean favorite, int index) {
        step("A user in favorites? " + favorite, () -> {
            if (favorite) {
                $$("app-saved-professional-toggle ion-icon[ng-reflect-name='heart']").filter(visible).get(index).shouldHave(cssClass("ion-color-danger"));
            } else {
                $$("app-saved-professional-toggle ion-icon[ng-reflect-name='heart-outline']").filter(visible).get(index).shouldHave(cssClass("ion-color-medium"));
            }
        });
    }

    public void checkPreviewServiceProfessionalParameters(String serviceName, String firstName, String lastName, String specialization) {
        step("Verify professional parameters", () -> {
            $("app-service-widget app-service-title h2").shouldHave(text(serviceName));
            $("app-service-widget div.info").shouldHave(text(firstName + " " + lastName));
            $("app-service-widget div.subtitle").shouldHave(text(specialization));
        });
    }

    public void checkServiceLocation(int location) {
        step("Verify correct service location", () -> {
            switch (location) {
                case 0:
                    $("app-service-location ion-item#online-service").shouldBe(visible);
                    break;
                case 1:
                    $("app-service-location ion-item#client-service").shouldBe(visible);
                    break;
                case 2:
                    $("app-service-location ion-item#professional-service").shouldBe(visible);
                    break;
                default:
                    System.out.println("Unknown location ID: " + location);
                    throw  new IllegalArgumentException();
            }
        });
    }

    public void verifyTags(String... tags) {
        step("Verify tag(s) are correct", () -> {
            for (String tag : tags) {
                $$("app-service-tags-viewer").filter(visible).get(0).shouldHave(text(tag));
            }
        });
    }

    public void verifyNoTags() {
        step("Verify no visible tags for preview", () -> {
                $("app-service-preview app-service-tags-viewer ion-chip").shouldNotBe(visible, Duration.ofSeconds(2));
        });
    }

    public void verifyAddress(String serviceAddress) {
        step("Verify service address: " + serviceAddress, () -> {
            $$("app-location-viewer").filter(visible).get(0).shouldHave(text(serviceAddress));
        });
    }

    public void verifyServicePaymentCash(boolean value) {
        step("Check if payment by cash", () -> {
            if (value) {
                $("app-service-widget app-payment-method-viewer ion-icon[name='cash-outline']").scrollIntoView(true).shouldBe(visible);
            } else {
                $("app-service-widget app-payment-method-viewer ion-icon[name='cash-outline']").scrollIntoView(true).shouldNotBe(visible);
            }
        });
    }

    public void verifyServicePaymentOnline(boolean value) {
        step("Check if payment online", () -> {
            if (value) {
                $("app-service-widget app-payment-method-viewer ion-icon[name='card-outline']").scrollIntoView(true).shouldBe(visible);
            } else {
                $("app-service-widget app-payment-method-viewer ion-icon[name='card-outline']").scrollIntoView(true).shouldNotBe(visible);
            }
        });
    }

    public void verifyServiceInstantBooking(boolean value) {
        step("Check if instant booking active", () -> {
            if (value) {
                $("ion-icon[name='checkbox-outline']").scrollIntoView(true).shouldBe(visible);
            } else {
                $("ion-icon[name='checkbox-outline']").scrollIntoView(true).shouldNotBe(visible);
            }
        });
    }

    public void stepFinalClickBack() {
        step("Final step: click back", () -> {
            $("app-service-publish-final-step ion-button[color='light']").click();
        });
    }

    @Step("Publish a service")
    public void publishService() {
        $("app-service-publish-final-step ion-col.pub-btn ion-button[color='primary']").shouldBe(visible, Duration.ofSeconds(10));
        $("app-service-publish-final-step ion-col.pub-btn ion-button[color='primary']").shouldHave(cssClass("ion-activatable"));
        sleep(400);
        $("app-service-publish-final-step ion-col.pub-btn ion-button[color='primary']").click();
        $("app-service-publish-final-step").shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-service-created-page").shouldBe(visible, Duration.ofSeconds(10));
        sleep(5000);
    }

    @Step("Publish a service")
    public void publishServiceFromPreview() {
        $("app-service-preview app-bottom-btn ion-button.footer__btn").shouldBe(visible, Duration.ofSeconds(10));
        $("app-service-preview app-bottom-btn ion-button.footer__btn").shouldHave(cssClass("ion-activatable"));
        sleep(400);
        $("app-service-preview app-bottom-btn ion-button.footer__btn").click();
        $("app-service-preview").shouldNotBe(visible, Duration.ofSeconds(10));
        $("app-service-created-page").shouldBe(visible, Duration.ofSeconds(10));
        sleep(5000);
    }

    @Step("Verify that 'Field is required.' error appeared")
    public void fieldIsRequiredPresent() {
        $("app-service-publish-step-two app-price-editor app-form-control-error div.error-desc").shouldBe(visible);
    }

    @Step("Verify publishing step: step two")
    public void isStepTwo() {
        $("app-service-publish-step-two").$("ion-button").shouldBe(visible);
    }

    @Step("Verify that category/subcategory title language is in English")
    public void verifyCategorySubcategoryTitleLanguageEng() {
        String category = $("app-service-publish-step-one").$("app-category-selector").$("ion-label").getText();
        String subcategory = $("app-service-publish-step-one").$("app-subcategory-selector").$("ion-label").getText();
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
        if (category.equals("Select category")) {
            fail();
        }
        if (subcategory.equals("Select subcategory")) {
            fail();
        }
    }

    @Step("Verify that category list language is in English")
    public void verifyCategoryListLanguageEng() {
        $("ionic-selectable").shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable").click();
        $("ionic-selectable-modal ion-virtual-scroll ion-item ion-label").shouldBe(visible, Duration.ofSeconds(10));
        sleep(500);
        String value = $("ionic-selectable-modal ion-virtual-scroll ion-item ion-label").getText();
        if (!value.equals("Tutors")) {
            System.out.println("Expected: Tutors\nActual value: " + value);
            fail();
        }
        $("ionic-selectable-modal ion-virtual-scroll ion-item").scrollIntoView(true).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify that subcategory list language is in English")
    public void verifySubcategoryListLanguageEng() {
        $("ionic-selectable", 1).shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable", 1).scrollIntoView(true).click();
        $("ionic-selectable-modal ion-content ion-item ion-label").shouldBe(visible, Duration.ofSeconds(10));
        String value = $("ionic-selectable-modal ion-content ion-item ion-label").getText();
        if (!value.equals("English language")) {
            fail();
        }
        $("ionic-selectable-modal ion-content ion-item", 0).scrollIntoView(true).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify that category list language is not in English")
    public void verifyCategoryListLanguageNotEng() {
        $("ionic-selectable").shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable").click();
        $("ionic-selectable-modal ion-virtual-scroll ion-item ion-label").shouldBe(visible, Duration.ofSeconds(10));
        String value = $("ionic-selectable-modal ion-virtual-scroll ion-item ion-label").getText();
        if (value.equals("Tutors")) {
            fail();
        }
        $("ionic-selectable-modal").$("ion-virtual-scroll").$("ion-item", 0).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify that subcategory list language is not in English")
    public void verifySubcategoryListLanguageNotEng() {
        $("ionic-selectable", 1).shouldBe(visible, Duration.ofSeconds(10));
        $("ionic-selectable", 1).scrollIntoView(true).click();
        $("ionic-selectable-modal ion-content ion-item ion-label").shouldBe(visible, Duration.ofSeconds(10));
        String value = $("ionic-selectable-modal ion-content ion-item ion-label").getText();
        if (value.equals("English language")) {
            System.out.println("Language should not be English.");
            throw new IllegalArgumentException();
        }
        $("ionic-selectable-modal").$("ion-content").$("ion-item", 0).scrollIntoView(true).click();
        $("ionic-selectable-modal").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    @Step("Verify price currency is {currency}")
    public void verifyPriceCurrency(String minPrice, String maxPrice, int currency) {
//        String value = $("app-service-preview app-service-widget app-price").getText();
        String value = $$("app-price").filter(visible).get(0).getText();
        minPrice = PriceFormatter.addSpaces(minPrice);
        maxPrice = PriceFormatter.addSpaces(maxPrice);
        if (currency == cad && value.contains("$")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(minPrice + " — " + maxPrice + " $"));
            return;
        }
        if (currency == eur && value.contains("€")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(minPrice + " — " + maxPrice + " €"));
            return;
        }
        if (currency == rub && value.contains("₽")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(minPrice + " — " + maxPrice + " ₽"));
            return;
        }
        if (currency == usd && value.contains("$")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(minPrice + " — " + maxPrice + " $"));
            return;
        }
        System.out.println("Unknown currency.");
        throw new IllegalArgumentException();
    }

    @Step("Verify price currency is {currency}")
    public void verifyPriceCurrency(String price, int currency) {
        String value = $$("app-price").filter(visible).get(0).getText();
        price = PriceFormatter.addSpaces(price);
        if (currency == cad && value.contains("$")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(price + " $"));
            return;
        }
        if (currency == eur && value.contains("€")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(price + " €"));
            return;
        }
        if (currency == rub && value.contains("₽")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(price + " ₽"));
            return;
        }
        if (currency == usd && value.contains("$")) {
            $$("app-price").filter(visible).get(0).shouldHave(text(price + " $"));
            return;
        }
        System.out.println("Unknown currency.");
        throw new IllegalArgumentException();
    }

    @Step("Verify price currency is {currency}")
    public void verifyPriceCurrency(String price, int currency, int index) {
        String value = $$("app-price").filter(visible).get(index).getText();
        price = PriceFormatter.addSpaces(price);
        if (currency == cad && value.contains("$")) {
            $$("app-price").filter(visible).get(index).shouldHave(text(price + " $"));
            return;
        }
        if (currency == eur && value.contains("€")) {
            $$("app-price").filter(visible).get(index).shouldHave(text(price + " €"));
            return;
        }
        if (currency == rub && value.contains("₽")) {
            $$("app-price").filter(visible).get(index).shouldHave(text(price + " ₽"));
            return;
        }
        if (currency == usd && value.contains("$")) {
            $$("app-price").filter(visible).get(index).shouldHave(text(price + " $"));
            return;
        }
        System.out.println("Unknown currency.");
        throw new IllegalArgumentException();
    }

    public void verifySpinnerWorks() {
        $("ion-loading ion-spinner[role='progressbar']").shouldBe(visible);
        $("ion-loading ion-spinner[role='progressbar']").shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void clickPreview() {
        step("Click Preview button", () -> {
            $("app-service-publish-final-step ion-content ion-button[fill='outline']").click();
            $("app-service-publish-final-step ion-content ion-button[fill='outline']").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }

    public void previewClickSearch() {
        step("Click Preview tab: Search", () -> {
            $("app-service-preview ion-content ion-segment-button[value='search']").click();
            $("app-service-preview ion-content ion-segment-button[value='search']").shouldHave(cssClass("segment-button-checked"));
        });
    }

    public void previewClickService() {
        step("Click Preview tab: Service", () -> {
            $("app-service-preview ion-content ion-segment-button[value='service']").click();
            $("app-service-preview ion-content ion-segment-button[value='service']").shouldHave(cssClass("segment-button-checked"));
        });
    }
}