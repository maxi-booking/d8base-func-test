package tests.professionalProfile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.RegressionTestsHelpers.serviceReadyAPI;

@Feature("Professional Profile")
@Owner("Egor Khlebnikov")
public class ProfessionalProfileSmokeTests extends config.TestBase {

    @Test
    @DisplayName("Professional profile: info verification")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileInfoVerification() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(data.firstName[0], data.lastName[0], data.country[0], data.city[0], data.address, data.description[0], data.level[0]);

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.mainClickBack();
        pp.qualificationVerificationEmpty();
        pp.educationVerificationEmpty();
        pp.certificatesVerificationEmpty();
        pp.clickEditMain();
        pp.editExperience(data.experience[0]);
        pp.mainClickSave();
        pp.verifyProfessionalProfileMain(data.country[0], data.city[0], data.address, data.description[0], data.experience[0], data.level[0]);
    }

    @Test
    @DisplayName("Professional profile: functionality verification")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileFunctionalityVerification() {
        serviceReadyAPI(data);
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
    @DisplayName("Professional profile: main - change all the info and verify")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileInfoChangeTest() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.editSpecialization(data.specialization[1]);
        pp.editDescription(data.description[1]);
        pp.editCompany(data.company[0]);
        pp.editExperience(data.experience[1]);
        pp.editExpertiseLevel(data.level[1]);
        pp.editCategory(data.category[1]);
        pp.editSubcategory(data.subcategory[1]);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.editSpecialization(data.specialization[1]);
        pp.editDescription(data.description[1]);
        pp.editCompany(data.company[0]);
        pp.editExperience(data.experience[1]);
        pp.editExpertiseLevel(data.level[1]);
        pp.editCategory(data.category[1]);
        pp.editSubcategory(data.subcategory[1]);
        pp.mainClickSave();
        pp.verifyProfessionalExp(data.experience[1]);

        pp.clickEditMain();
        pp.mainVerify(data.description[1], data.level[1], data.category[1], data.subcategory[1]);
        pp.mainClickBack();

        pp.verifyProfessionalProfileMain(data.country[0], data.city[0], data.address, data.description[1], data.experience[1], data.level[1]);
    }

    @Test
    @DisplayName("Professional profile: qualification - change all the info and verify")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileQualificationChangeTest() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.qualificationVerificationEmpty();
        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(data.qualificationJobTitle[0]);
        pp.qualificationEditCompany(data.qualificationCompany[0]);
        pp.qualificationEditFrom(data.qualificationFromMonth[0], data.qualificationFromYear[0]);
        pp.qualificationEditTo(data.qualificationToMonth[0], data.qualificationToYear[0]);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(data.qualificationDescription[0]);
        pp.qualificationClickBack();
        pp.qualificationVerificationEmpty();

        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(data.qualificationJobTitle[0]);
        pp.qualificationEditCompany(data.qualificationCompany[0]);
        pp.qualificationEditFrom(data.qualificationFromMonth[0], data.qualificationFromYear[0]);
        pp.qualificationEditTo(data.qualificationToMonth[0], data.qualificationToYear[0]);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(data.qualificationDescription[0]);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(data.qualificationJobTitle[1]);
        pp.qualificationEditCompany(data.qualificationCompany[1]);
        pp.qualificationEditFrom(data.qualificationFromMonth[1], data.qualificationFromYear[1]);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(data.qualificationDescription[1]);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(data.qualificationJobTitle[1]);
        pp.qualificationEditCompany(data.qualificationCompany[1]);
        pp.qualificationEditFrom(data.qualificationFromMonth[1], data.qualificationFromYear[1]);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(data.qualificationDescription[1]);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, data.qualificationDate[1], data.qualificationJobTitle[1], data.qualificationCompany[1], data.qualificationDescription[1]);

        pp.clickEditQualification(0);
        pp.qualificationClickRemove();
        pp.qualificationVerificationEmpty();
    }

    @Test
    @DisplayName("Professional profile: education - change all the info and verify")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileEducationChangeTest() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(data.educationUniversity[0]);
        pp.educationDegree(data.educationDegree[0]);
        pp.educationAcademicField(data.educationAcademicField[0]);
        pp.educationEditFrom(data.educationFromMonth[0], data.educationFromYear[0]);
        pp.educationEditTo(data.educationToMonth[0], data.educationToYear[0]);
        pp.educationOngoingNo();
        pp.educationEditDescription(data.educationDescription[0]);
        pp.educationClickBack();
        pp.educationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(data.educationUniversity[0]);
        pp.educationDegree(data.educationDegree[0]);
        pp.educationAcademicField(data.educationAcademicField[0]);
        pp.educationEditFrom(data.educationFromMonth[0], data.educationFromYear[0]);
        pp.educationEditTo(data.educationToMonth[0], data.educationToYear[0]);
        pp.educationOngoingNo();
        pp.educationEditDescription(data.educationDescription[0]);
        pp.educationClickSave();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationClickBack();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationUniversity(data.educationUniversity[1]);
        pp.educationDegree(data.educationDegree[1]);
        pp.educationAcademicField(data.educationAcademicField[1]);
        pp.educationEditFrom(data.educationFromMonth[1], data.educationFromYear[1]);
        pp.educationOngoingYes();
        pp.educationEditDescription(data.educationDescription[1]);
        pp.educationClickBack();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationUniversity(data.educationUniversity[1]);
        pp.educationDegree(data.educationDegree[1]);
        pp.educationAcademicField(data.educationAcademicField[1]);
        pp.educationEditFrom(data.educationFromMonth[1], data.educationFromYear[1]);
        pp.educationOngoingYes();
        pp.educationEditDescription(data.educationDescription[1]);
        pp.educationClickSave();
        pp.educationVerificationFull(0, data.educationDate[1], data.educationUniversity[1], data.educationDegree[1], data.educationDescription[1]);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();
    }

    @Test
    @DisplayName("Professional profile: certificates - change all the info and verify")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileCertificateChangeTest() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();

        pp.certificatesVerificationEmpty();
        pp.clickAddNewCertificate();
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(data.certificateName[0]);
        pp.certificatesEditOrganization(data.certificateOrganization[0]);
        pp.certificatesEditDate(data.certificateDateDay[0], data.certificateDateMonth[0], data.certificateDateYear[0]);
        pp.certificatesEditID(data.certificateID[0]);
        pp.certificatesEditLink(data.certificateLink[0]);
        pp.certificatesUploadPic(data.certificatePhoto[0]);
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(data.certificateName[0]);
        pp.certificatesEditOrganization(data.certificateOrganization[0]);
        pp.certificatesEditDate(data.certificateDateDay[0], data.certificateDateMonth[0], data.certificateDateYear[0]);
        pp.certificatesEditID(data.certificateID[0]);
        pp.certificatesEditLink(data.certificateLink[0]);
        pp.certificatesUploadPic(data.certificatePhoto[0]);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, data.certificateName[0], data.certificateDate[0], data.certificateID[0], data.certificateLink[0]);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(data.certificateName[1]);
        pp.certificatesEditOrganization(data.certificateOrganization[1]);
        pp.certificatesEditDateWaitToLoad();
        pp.certificatesEditDate(data.certificateDateDay[1], data.certificateDateMonth[1], data.certificateDateYear[1]);
        pp.certificatesEditID(data.certificateID[1]);
        pp.certificatesEditLink(data.certificateLink[1]);
        pp.certificatesUploadPic(data.certificatePhoto[1]);
        pp.certificatesClickBack();
        pp.certificatesVerificationFull(0, data.certificateName[0], data.certificateDate[0], data.certificateID[0], data.certificateLink[0]);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(data.certificateName[1]);
        pp.certificatesEditOrganization(data.certificateOrganization[1]);
        pp.certificatesEditDateWaitToLoad();
        pp.certificatesEditDate(data.certificateDateDay[1], data.certificateDateMonth[1], data.certificateDateYear[1]);
        pp.certificatesEditID(data.certificateID[1]);
        pp.certificatesEditLink(data.certificateLink[1]);
        pp.certificatesUploadPic(data.certificatePhoto[1]);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, data.certificateName[1], data.certificateDate[1], data.certificateID[1], data.certificateLink[1]);

        pp.clickEditCertificate(0);
        pp.certificatesClickRemove();
        pp.certificatesVerificationEmpty();
    }

    @Test
    @DisplayName("Professional profile: full test")
    @Severity(SeverityLevel.CRITICAL)
    void masterProfileFullTest() {
        serviceReadyAPI(data);
        sideMenu.clickProfessionalProfile();
        pp.expandItems();
        pp.verifyProfessionalProfileBasic(data.firstName[0], data.lastName[0], data.country[0], data.city[0], data.address, data.description[0], data.level[0]);

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.editSpecialization(data.specialization[1]);
        pp.editDescription(data.description[1]);
        pp.editCompany(data.company[0]);
        pp.editExperience(data.experience[1]);
        pp.editExpertiseLevel(data.level[1]);
        pp.editCategory(data.category[1]);
        pp.editSubcategory(data.subcategory[1]);
        pp.mainClickSave();
        pp.verifyProfessionalExp(data.experience[1]);

        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(data.qualificationJobTitle[1]);
        pp.qualificationEditCompany(data.qualificationCompany[1]);
        pp.qualificationEditFrom(data.qualificationFromMonth[1], data.qualificationFromYear[1]);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(data.qualificationDescription[1]);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, data.qualificationDate[1], data.qualificationJobTitle[1], data.qualificationCompany[1], data.qualificationDescription[1]);

        pp.clickAddNewEducation();
        pp.educationUniversity(data.educationUniversity[1]);
        pp.educationDegree(data.educationDegree[1]);
        pp.educationAcademicField(data.educationAcademicField[1]);
        pp.educationEditFrom(data.educationFromMonth[1], data.educationFromYear[1]);
        pp.educationOngoingYes();
        pp.educationEditDescription(data.educationDescription[1]);
        pp.educationClickSave();
        pp.educationVerificationFull(0, data.educationDate[1], data.educationUniversity[1], data.educationDegree[1], data.educationDescription[1]);

        pp.clickAddNewCertificate();
        pp.certificatesEditName(data.certificateName[1]);
        pp.certificatesEditOrganization(data.certificateOrganization[1]);
        pp.certificatesEditDate(data.certificateDateDay[1], data.certificateDateMonth[1], data.certificateDateYear[1]);
        pp.certificatesEditID(data.certificateID[1]);
        pp.certificatesEditLink(data.certificateLink[1]);
        pp.certificatesUploadPic(data.certificatePhoto[1]);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, data.certificateName[1], data.certificateDate[1], data.certificateID[1], data.certificateLink[1]);

        pp.editProfessionalAddress();
        pp.addressClickBack();

        pp.clickAddNewAddress();
        pp.addressClickBack();

        pp.clickEditMain();
        pp.mainVerify(data.description[1], data.level[1], data.category[1], data.subcategory[1]);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(data.description[1], data.level[1], data.category[1], data.subcategory[1]);
        pp.editSpecialization(data.specialization[0]);
        pp.editDescription(data.description[0]);
        pp.editCompany(data.company[0]);
        pp.editExperience(data.experience[0]);
        pp.editExpertiseLevel(data.level[0]);
        pp.editCategory(data.category[0]);
        pp.editSubcategory(data.subcategory[0]);
        pp.mainClickBack();

        pp.clickEditMain();
        pp.mainVerify(data.description[1], data.level[1], data.category[1], data.subcategory[1]);
        pp.editSpecialization(data.specialization[0]);
        pp.editDescription(data.description[0]);
        pp.editCompany(data.company[0]);
        pp.editExperience(data.experience[0]);
        pp.editExpertiseLevel(data.level[0]);
        pp.editCategory(data.category[0]);
        pp.editSubcategory(data.subcategory[0]);
        pp.mainClickSave();
        pp.verifyProfessionalExp(data.experience[0]);

        pp.clickEditMain();
        pp.mainVerify(data.description[0], data.level[0], data.category[0], data.subcategory[0]);
        pp.mainClickBack();

        pp.clickEditAbout();
        pp.aboutClickBack();

        pp.qualificationVerificationEmpty();
        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(data.qualificationJobTitle[0]);
        pp.qualificationEditCompany(data.qualificationCompany[0]);
        pp.qualificationEditFrom(data.qualificationFromMonth[0], data.qualificationFromYear[0]);
        pp.qualificationEditTo(data.qualificationToMonth[0], data.qualificationToYear[0]);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(data.qualificationDescription[0]);
        pp.qualificationClickBack();
        pp.qualificationVerificationEmpty();

        pp.clickAddNewQualification();
        pp.qualificationEditJobTitle(data.qualificationJobTitle[0]);
        pp.qualificationEditCompany(data.qualificationCompany[0]);
        pp.qualificationEditFrom(data.qualificationFromMonth[0], data.qualificationFromYear[0]);
        pp.qualificationEditTo(data.qualificationToMonth[0], data.qualificationToYear[0]);
        pp.qualificationOngoingNo();
        pp.qualificationEditDescription(data.qualificationDescription[0]);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(data.qualificationJobTitle[1]);
        pp.qualificationEditCompany(data.qualificationCompany[1]);
        pp.qualificationEditFrom(data.qualificationFromMonth[1], data.qualificationFromYear[1]);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(data.qualificationDescription[1]);
        pp.qualificationClickBack();
        pp.qualificationVerificationFull(0, data.qualificationDate[0], data.qualificationJobTitle[0], data.qualificationCompany[0], data.qualificationDescription[0]);

        pp.clickEditQualification(0);
        pp.qualificationEditJobTitle(data.qualificationJobTitle[1]);
        pp.qualificationEditCompany(data.qualificationCompany[1]);
        pp.qualificationEditFrom(data.qualificationFromMonth[1], data.qualificationFromYear[1]);
        pp.qualificationOngoingYes();
        pp.qualificationEditDescription(data.qualificationDescription[1]);
        pp.qualificationClickSave();
        pp.qualificationVerificationFull(0, data.qualificationDate[1], data.qualificationJobTitle[1], data.qualificationCompany[1], data.qualificationDescription[1]);

        pp.clickEditQualification(0);
        pp.qualificationClickRemove();
        pp.qualificationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(data.educationUniversity[0]);
        pp.educationDegree(data.educationDegree[0]);
        pp.educationAcademicField(data.educationAcademicField[0]);
        pp.educationEditFrom(data.educationFromMonth[0], data.educationFromYear[0]);
        pp.educationEditTo(data.educationToMonth[0], data.educationToYear[0]);
        pp.educationOngoingNo();
        pp.educationEditDescription(data.educationDescription[0]);
        pp.educationClickBack();
        pp.educationVerificationEmpty();

        pp.educationVerificationEmpty();
        pp.clickAddNewEducation();
        pp.educationUniversity(data.educationUniversity[0]);
        pp.educationDegree(data.educationDegree[0]);
        pp.educationAcademicField(data.educationAcademicField[0]);
        pp.educationEditFrom(data.educationFromMonth[0], data.educationFromYear[0]);
        pp.educationEditTo(data.educationToMonth[0], data.educationToYear[0]);
        pp.educationOngoingNo();
        pp.educationEditDescription(data.educationDescription[0]);
        pp.educationClickSave();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationClickBack();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationUniversity(data.educationUniversity[1]);
        pp.educationDegree(data.educationDegree[1]);
        pp.educationAcademicField(data.educationAcademicField[1]);
        pp.educationEditFrom(data.educationFromMonth[1], data.educationFromYear[1]);
        pp.educationOngoingYes();
        pp.educationEditDescription(data.educationDescription[1]);
        pp.educationClickBack();
        pp.educationVerificationFull(0, data.educationDate[0], data.educationUniversity[0], data.educationDegree[0], data.educationDescription[0]);

        pp.clickEditEducation(0);
        pp.educationUniversity(data.educationUniversity[1]);
        pp.educationDegree(data.educationDegree[1]);
        pp.educationAcademicField(data.educationAcademicField[1]);
        pp.educationEditFrom(data.educationFromMonth[1], data.educationFromYear[1]);
        pp.educationOngoingYes();
        pp.educationEditDescription(data.educationDescription[1]);
        pp.educationClickSave();
        pp.educationVerificationFull(0, data.educationDate[1], data.educationUniversity[1], data.educationDegree[1], data.educationDescription[1]);

        pp.clickEditEducation(0);
        pp.educationClickRemove();
        pp.educationVerificationEmpty();

        pp.certificatesVerificationEmpty();
        pp.clickAddNewCertificate();
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(data.certificateName[0]);
        pp.certificatesEditOrganization(data.certificateOrganization[0]);
        pp.certificatesEditDate(data.certificateDateDay[0], data.certificateDateMonth[0], data.certificateDateYear[0]);
        pp.certificatesEditID(data.certificateID[0]);
        pp.certificatesEditLink(data.certificateLink[0]);
        pp.certificatesUploadPic(data.certificatePhoto[0]);
        pp.certificatesClickBack();
        pp.certificatesVerificationEmpty();

        pp.clickAddNewCertificate();
        pp.certificatesEditName(data.certificateName[0]);
        pp.certificatesEditOrganization(data.certificateOrganization[0]);
        pp.certificatesEditDate(data.certificateDateDay[0], data.certificateDateMonth[0], data.certificateDateYear[0]);
        pp.certificatesEditID(data.certificateID[0]);
        pp.certificatesEditLink(data.certificateLink[0]);
        pp.certificatesUploadPic(data.certificatePhoto[0]);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, data.certificateName[0], data.certificateDate[0], data.certificateID[0], data.certificateLink[0]);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(data.certificateName[1]);
        pp.certificatesEditOrganization(data.certificateOrganization[1]);
        pp.certificatesEditDate(data.certificateDateDay[1], data.certificateDateMonth[1], data.certificateDateYear[1]);
        pp.certificatesEditID(data.certificateID[1]);
        pp.certificatesEditLink(data.certificateLink[1]);
        pp.certificatesUploadPic(data.certificatePhoto[1]);
        pp.certificatesClickBack();
        pp.certificatesVerificationFull(0, data.certificateName[0], data.certificateDate[0], data.certificateID[0], data.certificateLink[0]);

        pp.clickEditCertificate(0);
        pp.certificatesEditName(data.certificateName[1]);
        pp.certificatesEditOrganization(data.certificateOrganization[1]);
        pp.certificatesEditDate(data.certificateDateDay[1], data.certificateDateMonth[1], data.certificateDateYear[1]);
        pp.certificatesEditID(data.certificateID[1]);
        pp.certificatesEditLink(data.certificateLink[1]);
        pp.certificatesUploadPic(data.certificatePhoto[1]);
        pp.certificatesClickSave();
        pp.certificatesVerificationFull(0, data.certificateName[1], data.certificateDate[1], data.certificateID[1], data.certificateLink[1]);

        pp.clickEditCertificate(0);
        pp.certificatesClickRemove();
        pp.certificatesVerificationEmpty();

        pp.verifyProfessionalProfileMain(data.country[0], data.city[0], data.address, data.description[0], data.experience[0], data.level[0]);
    }
}