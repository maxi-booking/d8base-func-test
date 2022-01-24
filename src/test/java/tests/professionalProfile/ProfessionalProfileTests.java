package tests.professionalProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.Registration.*;
import static api.ServicePublish.*;

public class ProfessionalProfileTests extends config.TestBase {

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Professional profile (preparations): account creation (Full Registration + Full Service Publication)")
    void t00000() {
        String accessToken = registration(firstNames[11], lastNames[11], emails[11], passwords[11], countries[11], phoneNumbers[11]);
        int locationsId = locations(accessToken, countries[11], cities[11]);
        changeAccountTypeToProfessional(accessToken);
        int professionalId = createProfessional(accessToken, master12MainCategory, master12MainSubcategory, specializations[11], master12MainLevel, master12MainDescription);
        int serviceId = servicePublish(accessToken, professionalId, serviceNames[11], serviceDescriptions[11], serviceDurations[11], professional, instantBooking);
        int serviceLocationId = professionalLocations(accessToken, professionalId, service12Country, service12City, service12Address);
        serviceLocations(accessToken, serviceId, serviceLocationId);
        setSchedule(accessToken, professionalId, 7);
        servicePrices(accessToken, serviceId, servicePrices[11], serviceCurrencyRandom, paymentCashOnline);
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: info verification")
    void t00001() {
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(firstNames[11], lastNames[11], countries[11], cities[11], user12Address, master12MainDescription, master12MainLevel);

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.mainClickBack();
        pp.qualificationVerificationEmpty();
        pp.educationVerificationEmpty();
        pp.certificatesVerificationEmpty();
        pp.clickEditMain();
        pp.editExperience(master12MainExperience);
        pp.mainClickSave();
        pp.verifyProfessionalProfileMain(countries[11], cities[11], user12Address, master12MainDescription, master12MainExperience, master12MainLevel);
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: functionality verification")
    void t00002() {
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
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
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.editSpecialization(master12MainSpecializationNew);
        pp.editDescription(master12MainDescriptionNew);
        pp.editCompany(master12MainCompanyNew);
        pp.editExperience(master12MainExperienceNew);
        pp.editExpertiseLevel(master12MainLevelNew);
        pp.editCategory(master12MainCategoryNew);
        pp.editSubcategory(master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescription, master12MainLevel, master12MainCategory, master12MainSubcategory);
        pp.editSpecialization(master12MainSpecializationNew);
        pp.editDescription(master12MainDescriptionNew);
        pp.editCompany(master12MainCompanyNew);
        pp.editExperience(master12MainExperienceNew);
        pp.editExpertiseLevel(master12MainLevelNew);
        pp.editCategory(master12MainCategoryNew);
        pp.editSubcategory(master12MainSubcategoryNew);
        pp.mainClickSave();
        pp.verifyProfessionalExp(master12MainExperienceNew);

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.verifyProfessionalProfileMain(countries[11], cities[11], user12Address, master12MainDescriptionNew, master12MainExperienceNew, master12MainLevelNew);
    }

    @Test
    @Feature("Professional Profile verification")
    @Owner("Egor Khlebnikov")
    @Story("Professional Profile")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Professional profile: qualification - change all the info and verify")
    void t00006() {
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
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
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
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
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
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
        log.openMainPage();
        log.popupSkip();
        log.account(11);
        log.forceEN();
        log.openMainPage();

        sideMenu.clickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(firstNames[11], lastNames[11], countries[11], cities[11], user12Address, master12MainDescriptionNew, master12MainLevelNew);

        pp.editProfessionalAddress();
        pp.addressClickBack();

        pp.clickAddNewAddress();
        pp.addressClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.editSpecialization(specializations[11]);
        pp.editDescription(master12MainDescription);
        pp.editCompany(master12MainCompany);
        pp.editExperience(master12MainExperience);
        pp.editExpertiseLevel(master12MainLevel);
        pp.editCategory(master12MainCategory);
        pp.editSubcategory(master12MainSubcategory);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(master12MainDescriptionNew, master12MainLevelNew, master12MainCategoryNew, master12MainSubcategoryNew);
        pp.editSpecialization(specializations[11]);
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

        pp.verifyProfessionalProfileMain(countries[11], cities[11], user12Address, master12MainDescription, master12MainExperience, master12MainLevel);
    }
}