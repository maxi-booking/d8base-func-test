package professionalProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class ProfessionalProfileTests extends config.TestBase {

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Full Registration + Full Service Publication")
    void t00000() {
        log.popupSkip();
        log.forceEN();
        log.clickSideMenu();
        reg.openPageEN();
        reg.fillUserFirstName(user12FirstName);
        reg.fillUserLastName(user12LastName);
        reg.fillEmail(user12Email);
        reg.choosePassword(user12Password);
        reg.fillPhoneNumber(user12PhoneNumber, user12Country);
        reg.selectCountry(user12Country);
        reg.selectCity(user12City);
        reg.confirm();
        reg.verifyRegistrationDataFull(user12FirstName, user12LastName, user12Email, user12PhoneNumber, user12Country, user12City);

        log.forceEN();

        log.clickSideMenu();
        pbl.clickPublishNewService();

        pbl.chooseCategory(service12Category);
        pbl.chooseSubcategory(service12Subcategory);
        pbl.clickFirstStep();

        pbl.enterServiceName(service12Name);
        pbl.enterServiceDescription(service12Description);
        pbl.setDuration(service12DurationDays, service12DurationHours, service12DurationMinutes);
        pbl.setPriceFixed(service12Price, rub);
        pbl.selectServiceLocation(master);
        pbl.clickSecondStep();

//        pbl.addServicePhotos(random);
//        pbl.verifyPictureUpload(1);
        pbl.clickThirdStep();

        pbl.enterFirstName(user12FirstName1);
        pbl.enterLastName(user12LastName1);
        pbl.selectGender(female);
        pbl.uploadAvatar(random);
        pbl.clickFifthStep();

        pbl.selectPersonOrCompany(person);
        pbl.fillAbout(service12About);
        pbl.fillSpecialization(service12Specialization);
        pbl.selectLevel(middle);
        pbl.clickSixthStep();

        pbl.fillScheduleLite();
        pbl.confirmInstantBooking();
        pbl.fillServiceGeo(service12Country, service12City, service12Address);
        pbl.selectPaymentByCash();
        pbl.selectOnlinePayment();
        pbl.clickSeventhStep();

        pbl.checkPublishFormOnline(service12Name, service12TotalDuration, service12Description);
        pbl.checkPrice(service12Price);
        pbl.publishService();
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile functionality verification")
    void t00001() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();

        pp.editProfessionalAddress();
        pp.addressClickBack();

        pp.clickAddNewAddress();
        pp.addressClickBack();

        pp.clickEditMain();
        pp.mainClickBack();

        pp.clickEditAbout();
        pp.aboutClickBack();

        pp.clickAddNewQualification();
        pp.qualificationClickBack();

        pp.clickAddNewEducation();
        pp.educationClickBack();

        pp.clickAddNewCertificate();
        pp.certificateClickBack();
    }
}
