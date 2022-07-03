package tests.regressionTests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static helpers.RegressionTestsHelpers.*;
import static io.qameta.allure.Allure.step;

@Feature("Professional Profile")
@Owner("Egor Khlebnikov")
public class ProfessionalProfileTests extends config.TestBase {

    @Test
    @Link(name = "https://redmine.maxi-booking.ru/issues/4848", url = "https://redmine.maxi-booking.ru/issues/4848")
    @DisplayName("Professional Profile: delete education")
    @Severity(SeverityLevel.NORMAL)
    void t00000() {
        serviceReadyAPI(data);
        log.openMainPage();
        data.locale = language.select(defaultLanguage);

        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(masterEducationUniversity);

        pp.educationClickSave();
        pp.educationVerificationBasic(0, masterEducationUniversity);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();
    }

    @Test
    @Link(name = "https://redmine.maxi-booking.ru/issues/4909", url = "https://redmine.maxi-booking.ru/issues/4909")
    @DisplayName("Professional Profile: social share button should be clickable (another person)")
    @Severity(SeverityLevel.NORMAL)
    void masterProfileSocialShareClickableForAnotherPerson() {
        serviceReadyAPI(data);
        log.openMainPage();
        data.locale = language.select(defaultLanguage);
        sideMenu.clickLogOut();

        sideMenu.clickSearch();
        search.closeAllChips();
        search.search(serviceName);
        search.clickProfessionalsName();
        pp.clickSocialShare();
        log.toastVisible();
    }

    @Test
    @Link(name = "https://redmine.maxi-booking.ru/issues/4598", url = "https://redmine.maxi-booking.ru/issues/4598")
    @DisplayName("Professional Profile: professional expertise level shouldn't be in English with Russian locale chosen")
    @Severity(SeverityLevel.NORMAL)
    void masterProfileLevelIsAlwaysInEnglish() {
        serviceReadyAPIEnglish(data);
        sideMenu.clickProfessionalProfile();

        pp.clickEditMain();
        pp.mainClickExpertiseLevel();
        step("Verify English expertise levels", () -> {
            $("ion-popover ion-radio-group[role='radiogroup']").shouldHave(text("Junior"), text("Middle"), text("Senior"));
            $("ion-backdrop").click();
            $("ion-backdrop").shouldNotBe(visible, Duration.ofSeconds(10));
        });
        language.select(russian);
        pp.mainClickExpertiseLevel();
        step("Verify no English expertise levels", () -> {
            $("ion-popover ion-radio-group[role='radiogroup']").shouldNotHave(text("Junior"), text("Middle"), text("Senior"));
            $("ion-backdrop").click();
            $("ion-backdrop").shouldNotBe(visible, Duration.ofSeconds(10));
        });
    }
}