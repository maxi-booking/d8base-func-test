package professionalProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfessionalProfileTests extends config.TestBase {

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Professional profile (preparations): account creation (Full Registration + Full Service Publication)")
    void t00000() {
        log.popupSelect(user12Country, user12City);
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

        pbl.chooseCategory(master12MainCategory);
        pbl.chooseSubcategory(master12MainSubcategory);
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
        pbl.fillAbout(master12MainDescription);
        pbl.fillSpecialization(master12MainSpecialization);
        pbl.selectLevel(master12MainLevel);
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
    @DisplayName("Professional profile: info verification")
    void t00001() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(user12FirstName1, user12LastName1, user12Country, user12City, user12Address, master12MainDescription, master12MainLevel);

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.mainClickBack();
        pp.qualificationVerificationEmpty();
        pp.educationVerificationEmpty();
        pp.certificatesVerificationEmpty();
        pp.verifyProfessionalProfileMain(user12Country, user12City, user12Address, master12MainDescription, master12MainExperience, master12MainLevel);
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: functionality verification")
    void t00002() {
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
        pp.certificatesClickBack();

        pp.collapseItems();
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: main - change all the info and verify")
    void t00004() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.editSpecialization(master12MainSpecializationNew);
        pp.editDescription(master12MainDescriptionNew);
        pp.editCompany(master12MainCompanyNew);
        pp.editExperience(master12ExperienceNew);
        pp.editExpertiseLevel(master12MainLevelNew);
        pp.editCategory(master12MainCategoryNew);
        pp.editSubcategory(master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.editSpecialization(master12MainSpecializationNew);
        pp.editDescription(master12MainDescriptionNew);
        pp.editCompany(master12MainCompanyNew);
        pp.editExperience(master12ExperienceNew);
        pp.editExpertiseLevel(master12MainLevelNew);
        pp.editCategory(master12MainCategoryNew);
        pp.editSubcategory(master12MainSubcategoryNew);
        pp.mainClickSave();
        pp.verifyProfessionalExp(master12ExperienceNew);

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.verifyProfessionalProfileMain(user12Country, user12City, user12Address, master12MainDescriptionNew, master12ExperienceNew, master12MainLevelNew);
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: qualification - change all the info and verify")
    void t00006() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();

        pp.qualificationVerificationEmpty();
        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(master12QualificationJobTitle);
        pp.qualificationEditCompany(master12QualificationCompany);
        pp.qualificationEditFrom(master12QualificationFromMonth, master12QualificationFromYear);
        pp.qualificationEditTo(master12QualificationToMonth, master12QualificationToYear);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(master12QualificationDescription);
        pp.qualificationClickBack();
        pp.qualificationVerificationEmpty();

        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(master12QualificationJobTitle);
        pp.qualificationEditCompany(master12QualificationCompany);
        pp.qualificationEditFrom(master12QualificationFromMonth, master12QualificationFromYear);
        pp.qualificationEditTo(master12QualificationToMonth, master12QualificationToYear);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(master12QualificationDescription);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(master12QualificationJobTitleNew);
        pp.qualificationEditCompany(master12QualificationCompanyNew);
        pp.qualificationEditFrom(master12QualificationFromMonthNew, master12QualificationFromYearNew);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(master12QualificationDescriptionNew);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(master12QualificationJobTitleNew);
        pp.qualificationEditCompany(master12QualificationCompanyNew);
        pp.qualificationEditFrom(master12QualificationFromMonthNew, master12QualificationFromYearNew);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(master12QualificationDescriptionNew);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, master12QualificationDateNew, master12QualificationJobTitleNew, master12QualificationCompanyNew, master12QualificationDescriptionNew);

        pp.clickEditQualification(0);
        pp.qualificationClickRemove();
        pp.qualificationVerificationEmpty();
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: education - change all the info and verify")
    void t00007() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(master12EducationUniversity);
        pp.educationDegree(master12EducationDegree);
        pp.educationAcademicField(master12EducationAcademicField);
        pp.educationEditFrom(master12EducationFromMonth, master12EducationFromYear);
        pp.educationEditTo(master12EducationToMonth, master12EducationToYear);
        pp.educationOngoingNo();
        pp.educationEditDescription(master12EducationDescription);
        pp.educationClickBack();
        pp.educationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(master12EducationUniversity);
        pp.educationDegree(master12EducationDegree);
        pp.educationAcademicField(master12EducationAcademicField);
        pp.educationEditFrom(master12EducationFromMonth, master12EducationFromYear);
        pp.educationEditTo(master12EducationToMonth, master12EducationToYear);
        pp.educationOngoingNo();
        pp.educationEditDescription(master12EducationDescription);
        pp.educationClickSave();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationClickBack();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationUniversity(master12EducationUniversityNew);
        pp.educationDegree(master12EducationDegreeNew);
        pp.educationAcademicField(master12EducationAcademicFieldNew);
        pp.educationEditFrom(master12EducationFromMonthNew, master12EducationFromYearNew);
        pp.educationOngoingYes();
        pp.educationEditDescription(master12EducationDescriptionNew);
        pp.educationClickBack();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationUniversity(master12EducationUniversityNew);
        pp.educationDegree(master12EducationDegreeNew);
        pp.educationAcademicField(master12EducationAcademicFieldNew);
        pp.educationEditFrom(master12EducationFromMonthNew, master12EducationFromYearNew);
        pp.educationOngoingYes();
        pp.educationEditDescription(master12EducationDescriptionNew);
        pp.educationClickSave();
        pp.educationVerificationFull(0, master12EducationDateNew, master12EducationUniversityNew, master12EducationDegreeNew, master12EducationDescriptionNew);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: certificates - change all the info and verify")
    void t00008() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();

        pp.certificatesVerificationEmpty();
        pp.clickAddNewCertificate();
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(master12CertificateName);
        pp.certificatesEditOrganization(master12CertificateOrganization);
        pp.certificatesEditDate(master12CertificateDateDay, master12CertificateDateMonth, master12CertificateDateYear);
        pp.certificatesEditID(master12CertificateID);
        pp.certificatesEditLink(master12CertificateLink);
        pp.certificatesUploadPic(master12CertificatePhoto);
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(master12CertificateName);
        pp.certificatesEditOrganization(master12CertificateOrganization);
        pp.certificatesEditDate(master12CertificateDateDay, master12CertificateDateMonth, master12CertificateDateYear);
        pp.certificatesEditID(master12CertificateID);
        pp.certificatesEditLink(master12CertificateLink);
        pp.certificatesUploadPic(master12CertificatePhoto);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, master12CertificateName, master12CertificateDate, master12CertificateID, master12CertificateLink);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(master12CertificateNameNew);
        pp.certificatesEditOrganization(master12CertificateOrganizationNew);
        pp.certificatesEditDate(master12CertificateDateDayNew, master12CertificateDateMonthNew, master12CertificateDateYearNew);
        pp.certificatesEditID(master12CertificateIDNew);
        pp.certificatesEditLink(master12CertificateLinkNew);
        pp.certificatesUploadPic(master12CertificatePhotoNew);
        pp.certificatesClickBack();
        pp.certificatesVerificationFull(0, master12CertificateName, master12CertificateDate, master12CertificateID, master12CertificateLink);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(master12CertificateNameNew);
        pp.certificatesEditOrganization(master12CertificateOrganizationNew);
        pp.certificatesEditDate(master12CertificateDateDayNew, master12CertificateDateMonthNew, master12CertificateDateYearNew);
        pp.certificatesEditID(master12CertificateIDNew);
        pp.certificatesEditLink(master12CertificateLinkNew);
        pp.certificatesUploadPic(master12CertificatePhotoNew);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, master12CertificateNameNew, master12CertificateDateNew, master12CertificateIDNew, master12CertificateLinkNew);

        pp.clickEditCertificate(0);
        pp.certificatesClickRemove();
        pp.certificatesVerificationEmpty();
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: full test")
    void t00100() {
        log.popupSkip();
        log.account12();
        log.forceEN();

        menu.menuClickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(user12FirstName1, user12LastName1, user12Country, user12City, user12Address, master12MainDescriptionNew, master12MainLevelNew);

        pp.editProfessionalAddress();
        pp.addressClickBack();

        pp.clickAddNewAddress();
        pp.addressClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.editSpecialization(master12MainSpecialization);
        pp.editDescription(master12MainDescription);
        pp.editCompany(master12MainCompany);
        pp.editExperience(master12MainExperience);
        pp.editExpertiseLevel(master12MainLevel);
        pp.editCategory(master12MainCategory);
        pp.editSubcategory(master12MainSubcategory);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.editSpecialization(master12MainSpecialization);
        pp.editDescription(master12MainDescription);
        pp.editCompany(master12MainCompany);
        pp.editExperience(master12MainExperience);
        pp.editExpertiseLevel(master12MainLevel);
        pp.editCategory(master12MainCategory);
        pp.editSubcategory(master12MainSubcategory);
        pp.mainClickSave();
        pp.verifyProfessionalExp(master12MainExperience);

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.mainClickBack();

        pp.clickEditAbout();
        pp.aboutClickBack();

        pp.qualificationVerificationEmpty();
        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(master12QualificationJobTitle);
        pp.qualificationEditCompany(master12QualificationCompany);
        pp.qualificationEditFrom(master12QualificationFromMonth, master12QualificationFromYear);
        pp.qualificationEditTo(master12QualificationToMonth, master12QualificationToYear);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(master12QualificationDescription);
        pp.qualificationClickBack();
        pp.qualificationVerificationEmpty();

        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(master12QualificationJobTitle);
        pp.qualificationEditCompany(master12QualificationCompany);
        pp.qualificationEditFrom(master12QualificationFromMonth, master12QualificationFromYear);
        pp.qualificationEditTo(master12QualificationToMonth, master12QualificationToYear);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(master12QualificationDescription);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(master12QualificationJobTitleNew);
        pp.qualificationEditCompany(master12QualificationCompanyNew);
        pp.qualificationEditFrom(master12QualificationFromMonthNew, master12QualificationFromYearNew);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(master12QualificationDescriptionNew);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, master12QualificationDate, master12QualificationJobTitle, master12QualificationCompany, master12QualificationDescription);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(master12QualificationJobTitleNew);
        pp.qualificationEditCompany(master12QualificationCompanyNew);
        pp.qualificationEditFrom(master12QualificationFromMonthNew, master12QualificationFromYearNew);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(master12QualificationDescriptionNew);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, master12QualificationDateNew, master12QualificationJobTitleNew, master12QualificationCompanyNew, master12QualificationDescriptionNew);

        pp.clickEditQualification(0);
        pp.qualificationClickRemove();
        pp.qualificationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(master12EducationUniversity);
        pp.educationDegree(master12EducationDegree);
        pp.educationAcademicField(master12EducationAcademicField);
        pp.educationEditFrom(master12EducationFromMonth, master12EducationFromYear);
        pp.educationEditTo(master12EducationToMonth, master12EducationToYear);
        pp.educationOngoingNo();
        pp.educationEditDescription(master12EducationDescription);
        pp.educationClickBack();
        pp.educationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(master12EducationUniversity);
        pp.educationDegree(master12EducationDegree);
        pp.educationAcademicField(master12EducationAcademicField);
        pp.educationEditFrom(master12EducationFromMonth, master12EducationFromYear);
        pp.educationEditTo(master12EducationToMonth, master12EducationToYear);
        pp.educationOngoingNo();
        pp.educationEditDescription(master12EducationDescription);
        pp.educationClickSave();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationClickBack();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationUniversity(master12EducationUniversityNew);
        pp.educationDegree(master12EducationDegreeNew);
        pp.educationAcademicField(master12EducationAcademicFieldNew);
        pp.educationEditFrom(master12EducationFromMonthNew, master12EducationFromYearNew);
        pp.educationOngoingYes();
        pp.educationEditDescription(master12EducationDescriptionNew);
        pp.educationClickBack();
        pp.educationVerificationFull(0, master12EducationDate, master12EducationUniversity, master12EducationDegree, master12EducationDescription);

        pp.clickEditEducation(0);
        pp.educationUniversity(master12EducationUniversityNew);
        pp.educationDegree(master12EducationDegreeNew);
        pp.educationAcademicField(master12EducationAcademicFieldNew);
        pp.educationEditFrom(master12EducationFromMonthNew, master12EducationFromYearNew);
        pp.educationOngoingYes();
        pp.educationEditDescription(master12EducationDescriptionNew);
        pp.educationClickSave();
        pp.educationVerificationFull(0, master12EducationDateNew, master12EducationUniversityNew, master12EducationDegreeNew, master12EducationDescriptionNew);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();

        pp.certificatesVerificationEmpty();
        pp.clickAddNewCertificate();
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(master12CertificateName);
        pp.certificatesEditOrganization(master12CertificateOrganization);
        pp.certificatesEditDate(master12CertificateDateDay, master12CertificateDateMonth, master12CertificateDateYear);
        pp.certificatesEditID(master12CertificateID);
        pp.certificatesEditLink(master12CertificateLink);
        pp.certificatesUploadPic(master12CertificatePhoto);
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(master12CertificateName);
        pp.certificatesEditOrganization(master12CertificateOrganization);
        pp.certificatesEditDate(master12CertificateDateDay, master12CertificateDateMonth, master12CertificateDateYear);
        pp.certificatesEditID(master12CertificateID);
        pp.certificatesEditLink(master12CertificateLink);
        pp.certificatesUploadPic(master12CertificatePhoto);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, master12CertificateName, master12CertificateDate, master12CertificateID, master12CertificateLink);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(master12CertificateNameNew);
        pp.certificatesEditOrganization(master12CertificateOrganizationNew);
        pp.certificatesEditDate(master12CertificateDateDayNew, master12CertificateDateMonthNew, master12CertificateDateYearNew);
        pp.certificatesEditID(master12CertificateIDNew);
        pp.certificatesEditLink(master12CertificateLinkNew);
        pp.certificatesUploadPic(master12CertificatePhotoNew);
        pp.certificatesClickBack();
        pp.certificatesVerificationFull(0, master12CertificateName, master12CertificateDate, master12CertificateID, master12CertificateLink);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(master12CertificateNameNew);
        pp.certificatesEditOrganization(master12CertificateOrganizationNew);
        pp.certificatesEditDate(master12CertificateDateDayNew, master12CertificateDateMonthNew, master12CertificateDateYearNew);
        pp.certificatesEditID(master12CertificateIDNew);
        pp.certificatesEditLink(master12CertificateLinkNew);
        pp.certificatesUploadPic(master12CertificatePhotoNew);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, master12CertificateNameNew, master12CertificateDateNew, master12CertificateIDNew, master12CertificateLinkNew);

        pp.clickEditCertificate(0);
        pp.certificatesClickRemove();
        pp.certificatesVerificationEmpty();

        pp.verifyProfessionalProfileMain(user12Country, user12City, user12Address, master12MainDescription, master12MainExperience, master12MainLevel);
    }
}
