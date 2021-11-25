package config;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static helpers.MonthHelper.generateMonth;
import static helpers.MonthHelper.monthConvertToNumber;
import static helpers.SubcategoryGenerator.getRandomSubcategoryFromCategoryValue;
import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.fail;


public class TestData {
    public static String
            testUser1,
            testUser2,
            testUser3,
            testUser4,
            testUser5,
            testUser6,
            testUser7,
            testUser8,
            testUser9,
            testUser10,
            testUser10New,
            testPassword1,
            testPassword2,
            testPassword3,
            testPassword4,
            testPassword5,
            testPassword6,
            testPassword7,
            testPassword8,
            testPassword9,
            testPassword10,
            testPassword10New,
            user11Email,
            user11Password,
            user1FirstName,
            user1LastName,
            user1PhoneNumber,
            user1Country,
            user1City,
            user2FirstName,
            user2LastName,
            user2PhoneNumber,
            user2Country,
            user2City,
            user3FirstName,
            user3LastName,
            user3PhoneNumber,
            user3Country,
            user3City,
            user4FirstName,
            user4LastName,
            user4PhoneNumber,
            user4Country,
            user4City,
            user5FirstName,
            user5LastName,
            user5PhoneNumber,
            user5Country,
            user5City,
            user6FirstName,
            user6LastName,
            user6PhoneNumber,
            user6Country,
            user6City,
            user7FirstName,
            user7LastName,
            user7PhoneNumber,
            user7Country,
            user7City,
            user8FirstName,
            user8LastName,
            user8PhoneNumber,
            user8Country,
            user8City,
            user9FirstName,
            user9LastName,
            user9PhoneNumber,
            user9Country,
            user9City,
            user10FirstName,
            user10LastName,
            user10PhoneNumber,
            user10Country,
            user10City,
            user10FirstNameNew,
            user10LastNameNew,
            user10PatronymicNew,
            user10PhoneNumberNew,
            user10CountryNew,
            user10Region,
            user10RegionNew,
            user10SubregionNew,
            user10CityNew,
            user10DistrictNew,
            user10ZipCodeNew,
            user10AddressNew,
            user10Country2,
            user10Region2,
            user10Subregion2,
            user10City2,
            user10District2,
            user10ZipCode2,
            user10Address2,
            user10dateDD,
            user10dateMM,
            user10dateYYYY,
            user10Nationality,
            user10Language1,
            user10Language2,
            user11FirstName,
            user11LastName,
            user11PhoneNumber,
            user11Country,
            user11City,
            service1Name,
            service1Description,
            service1DurationDays,
            service1DurationHours,
            service1DurationMinutes,
            service1TotalDuration,
            service1Price,
            service1Specialization,
            service1Country,
            service1City,
            service1Address,
            service1Distance,
            service2Name,
            service2Description,
            service2DurationDays,
            service2DurationHours,
            service2DurationMinutes,
            service2TotalDuration,
            service2Price,
            service2Specialization,
            service2Country,
            service2City,
            service2Address,
            service2Distance,
            service3Name,
            service3Description,
            service3DurationDays,
            service3DurationHours,
            service3DurationMinutes,
            service3TotalDuration,
            service3Price,
            service3Specialization,
            service3Country,
            service3City,
            service3Address,
            service3Distance,
            service4Name,
            service4Description,
            service4DurationDays,
            service4DurationHours,
            service4DurationMinutes,
            service4TotalDuration,
            service4Price,
            service4Specialization,
            service4Country,
            service4City,
            service4Address,
            service4Distance,
            service7Name,
            service7Description,
            service7DurationDays,
            service7DurationHours,
            service7DurationMinutes,
            service7TotalDuration,
            service7Price,
            service7Specialization,
            service7Country,
            service7City,
            service7Address,
            service7Distance,
            reviewText1,
            reviewText2,
            reviewText3,
            reviewText4,
            masterComment,
            testMessage1,
            testMessage2,
            empty,
            today,
            tomorrow,
            nextDay,
            nextDayPlus,
            nextDayPlusPlus,
            user12Email,
            user12Password,
            user12FirstName,
            user12Patronymic,
            user12LastName,
            user12PhoneNumber,
            user12Country,
            user12City,
            user12Region,
            user12Subregion,
            user12District,
            user12ZipCode,
            user12Address,
            user12dateDD,
            user12dateMM,
            user12dateYYYY,
            user12Nationality,
            user12Language,
            service12Name,
            service12Description,
            service12DurationDays,
            service12DurationHours,
            service12DurationMinutes,
            service12TotalDuration,
            service12Price,
            master12MainDescription,
            master12MainDescriptionNew,
            master12MainSpecialization,
            master12MainSpecializationNew,
            master12MainCompany,
            master12MainCompanyNew,
            master12MainExperience,
            master12ExperienceNew,
            master12MainLevel,
            master12MainLevelNew,
            service12Country,
            service12City,
            service12Address,
            service12Distance,
            user12FirstName1,
            user12LastName1,
            master12QualificationJobTitle,
            master12QualificationCompany,
            master12QualificationFromMonth,
            master12QualificationFromYear,
            master12QualificationToMonth,
            master12QualificationToYear,
            master12QualificationDate,
            master12QualificationDescription,
            master12QualificationJobTitleNew,
            master12QualificationCompanyNew,
            master12QualificationFromMonthNew,
            master12QualificationFromYearNew,
            master12QualificationDateNew,
            master12QualificationDescriptionNew,
            master12EducationUniversity,
            master12EducationDegree,
            master12EducationAcademicField,
            master12EducationFromMonth,
            master12EducationFromYear,
            master12EducationToMonth,
            master12EducationToYear,
            master12EducationDate,
            master12EducationDescription,
            master12EducationUniversityNew,
            master12EducationDegreeNew,
            master12EducationAcademicFieldNew,
            master12EducationFromMonthNew,
            master12EducationFromYearNew,
            master12EducationDateNew,
            master12EducationDescriptionNew,
            master12CertificateName,
            master12CertificateOrganization,
            master12CertificateDate,
            master12CertificateDateDay,
            master12CertificateDateMonth,
            master12CertificateDateYear,
            master12CertificateID,
            master12CertificateLink,
            master12CertificatePhoto,
            master12CertificateNameNew,
            master12CertificateOrganizationNew,
            master12CertificateDateNew,
            master12CertificateDateDayNew,
            master12CertificateDateMonthNew,
            master12CertificateDateYearNew,
            master12CertificateIDNew,
            master12CertificateLinkNew,
            master12CertificatePhotoNew,
            random,
            randomFile,
            man,
            male,
            woman,
            female,
            person,
            company,
            junior,
            middle,
            senior;

    public static Integer
            cad,
            eur,
            rub,
            usd,
            online,
            client,
            master,
            nextDayInt,
            nextDayPlusInt,
            nextDayPlusPlusInt,
            randomServiceCategory,
            randomServiceSubcategory,
            master12MainCategory,
            master12MainSubcategory,
            master12MainCategoryNew,
            master12MainSubcategoryNew;

    public static void setTestData() {
        //currency
        cad = 0;
        eur = 1;
        rub = 2;
        usd = 3;

        //service location
        online = 0;
        client = 1;
        master = 2;

        //gender helpers
        man = "man";
        male = "man";
        woman = "woman";
        female = "woman";

        //select person or company
        person = "person";
        company = "company";

        //select level of the professional
        junior = "junior";
        middle = "middle";
        senior = "senior";

        //random confirmation
        random = "random";


        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int year = currentDate.getYear();
        today = Integer.toString(day);
        tomorrow = Integer.toString(day + 1);
        nextDayInt = day + 1;
        if (nextDayInt >= 28) {
            nextDayInt = 1;
        } else {
            nextDay = Integer.toString(nextDayInt);
        }
        nextDayPlusInt = nextDayInt + 1;
        nextDayPlusPlusInt = nextDayInt + 2;
        nextDayPlus = Integer.toString(nextDayPlusInt);
        nextDayPlusPlus = Integer.toString(nextDayPlusPlusInt);

        Faker generate = new Faker(new Locale("en-US"));
        empty = " ";
        randomServiceCategory = generate.number().numberBetween(0, 8);
        randomServiceSubcategory = getRandomSubcategoryFromCategoryValue(randomServiceCategory);
        randomFile = "src/test/resources/img/" + generate.number().numberBetween(1, 12) + ".png";
        testUser1 = generate.name().username() + "@uu.dd";
        testUser2 = generate.name().username() + "@uu.dd";
        testUser3 = generate.name().username() + "@uu.dd";
        testUser4 = generate.name().username() + "@uu.dd";
        testUser5 = generate.name().username() + "@uu.dd";
        testUser6 = generate.name().username() + "@cc.aa";
        testUser7 = generate.name().username() + "@tt.jj";
        testUser8 = generate.name().username() + "@cc.ff";
        testUser9 = generate.name().username() + "@ii.oo";
        testUser10 = generate.name().username() + "@ll.tt";
        testPassword1 = generate.internet().password();
        testPassword2 = generate.internet().password();
        testPassword3 = generate.internet().password();
        testPassword4 = generate.internet().password();
        testPassword5 = generate.internet().password();
        testPassword6 = generate.internet().password();
        testPassword7 = generate.internet().password();
        testPassword8 = generate.internet().password();
        testPassword9 = generate.internet().password();
        testPassword10 = generate.internet().password();

        user1FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user1LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user1PhoneNumber = "911" + generate.number().digits(7);
        user1Country = "Russia";
        user1City = "Moscow";

        user2FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user2LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user2PhoneNumber = "911" + generate.number().digits(7);
        user2Country = "Russia";
        user2City = "Moscow";

        user3FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user3LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user3PhoneNumber = "911" + generate.number().digits(7);
        user3Country = "Russia";
        user3City = "Moscow";

        user4FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user4LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user4PhoneNumber = "911" + generate.number().digits(7);
        user4Country = "Russia";
        user4City = "Moscow";

        user5FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user5LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user5PhoneNumber = "911" + generate.number().digits(7);
        user5Country = "Russia";
        user5City = "Moscow";

        user6FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user6LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user6PhoneNumber = "6135" + generate.number().digits(6);
        user6Country = "Canada";
        user6City = "Toronto";

        user7FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user7LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user7PhoneNumber = "4575" + generate.number().digits(6);
        user7Country = "Finland";
        user7City = "Helsinki";

        user8FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user8LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user8PhoneNumber = "5905" + generate.number().digits(6);
        user8Country = "France";
        user8City = "Paris";

        user9FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user9LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user9PhoneNumber = "1595" + generate.number().digits(6);
        user9Country = "Germany";
        user9City = "Berlin";

        user10FirstName = generate.name().firstName() + generate.name().suffix() + generate.name().suffix();
        user10LastName = generate.name().lastName() + generate.name().suffix() + generate.name().suffix();
        user10PhoneNumber = "911" + generate.number().digits(7);
        user10Country = "Russia";
        user10City = "Moscow";

        service1Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service1Description = generate.lorem().characters(20, 2000);
        service1DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service1DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service1DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service1Price = String.valueOf(generate.number().numberBetween(1, 9999999));
        service1Specialization = generate.job().title() + " " + generate.ancient().god();
        service1Country = "Russia";
        service1City = "Moscow";
        service1Address = generate.address().fullAddress();
        service1Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service2Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service2Description = generate.lorem().characters(20, 2000);
        service2DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service2DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service2DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service2Price = String.valueOf(generate.number().numberBetween(1, 2000));
        service2Specialization = generate.job().title() + " " + generate.ancient().hero();
        service2Country = "Russia";
        service2City = "Moscow";
        service2Address = generate.address().fullAddress();
        service2Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service3Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service3Description = generate.lorem().characters(20, 2000);
        service3DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service3DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service3DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service3Price = String.valueOf(generate.number().numberBetween(1, 70000));
        service3Specialization = generate.job().title() + " " + generate.ancient().titan();
        service3Country = "Russia";
        service3City = "Moscow";
        service3Address = generate.address().fullAddress();
        service3Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service4Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service4Description = generate.lorem().characters(20, 2000);
        service4DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service4DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service4DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service4Price = String.valueOf(generate.number().numberBetween(1, 999));
        service4Specialization = generate.job().title() + " " + generate.ancient().primordial();
        service4Country = "Russia";
        service4City = "Moscow";
        service4Address = generate.address().fullAddress();
        service4Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service7Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service7Description = generate.rickAndMorty().quote() + " " + generate.dune().quote();
        service7DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service7DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service7DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service7Price = String.valueOf(generate.number().numberBetween(1, 99));
        service7Specialization = generate.job().title() + " " + generate.name().suffix();
        service7Country = "Finland";
        service7City = "Helsinki";
        service7Address = generate.address().fullAddress();
        service7Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        reviewText1 = generate.lorem().sentence(10);
        reviewText2 = generate.lorem().characters(2000, 2500);
        reviewText3 = generate.rickAndMorty().quote();
        reviewText4 = generate.chuckNorris().fact();
        masterComment = generate.backToTheFuture().quote();

        testMessage1 = generate.dragonBall().character() + " > " + generate.dragonBall().character();
        testMessage2 = generate.friends().quote() + " (c) " + generate.dragonBall().character();

        user11Email = generate.name().username() + "@pp.nn";
        user11Password = generate.internet().password();
        user11FirstName = generate.name().firstName() + generate.name().suffix();
        user11LastName = generate.name().lastName() + generate.name().suffix();
        user11PhoneNumber = "923" + generate.number().digits(7);
        user11Country = "Россия";
        user11City = "Москва";

        testUser10New = generate.name().username() + "@new.kk";
        testPassword10New = generate.internet().password() + "New";
        user10FirstNameNew = generate.name().firstName() + "New";
        user10LastNameNew = generate.name().lastName() + "New";
        user10PatronymicNew = generate.name().username() + "New";
        user10PhoneNumberNew = "964" + generate.number().digits(7);

        user10CountryNew = "Russia";
        user10Region = "Moscow";
        user10RegionNew = "St.-Petersburg";
        user10SubregionNew = "Petrogradskiy Rayon";
        user10CityNew = "Saint Petersburg";
        user10DistrictNew = "Petrogradka";
        user10ZipCodeNew = "197101";
        user10AddressNew = "Каменноостровский пр., 38/96";

        user10Country2 = "Canada";
        user10Region2 = "Ontario";
        user10Subregion2 = "Toronto country";
        user10City2 = "Toronto";
        user10District2 = "Etobicoke";
        user10ZipCode2 = "M9A";
        user10Address2 = "8 Orkney Crescent";

        user10dateDD = Long.toString(generate.number().numberBetween(10, 28));
        user10dateMM = Long.toString(generate.number().numberBetween(10, 12));
        user10dateYYYY = Long.toString(generate.number().numberBetween(1950, 2005));
        user10Nationality = "Iceland";
        user10Language1 = "Arabic";
        user10Language2 = "Irish";

        long service1DurationDaysLong = parseLong(service1DurationDays),
                service1DurationHoursLong = parseLong(service1DurationHours),
                service1DurationMinutesLong = parseLong(service1DurationMinutes),
                service1TotalDurationLong = service1DurationDaysLong * 24 * 60 + service1DurationHoursLong * 60 + service1DurationMinutesLong;

        service1TotalDuration = Long.toString(service1TotalDurationLong);

        long service2DurationDaysLong = parseLong(service2DurationDays),
                service2DurationHoursLong = parseLong(service2DurationHours),
                service2DurationMinutesLong = parseLong(service2DurationMinutes),
                service2TotalDurationLong = service2DurationDaysLong * 24 * 60 + service2DurationHoursLong * 60 + service2DurationMinutesLong;

        service2TotalDuration = Long.toString(service2TotalDurationLong);

        long service3DurationDaysLong = parseLong(service3DurationDays),
                service3DurationHoursLong = parseLong(service3DurationHours),
                service3DurationMinutesLong = parseLong(service3DurationMinutes),
                service3TotalDurationLong = service3DurationDaysLong * 24 * 60 + service3DurationHoursLong * 60 + service3DurationMinutesLong;

        service3TotalDuration = Long.toString(service3TotalDurationLong);

        long service4DurationDaysLong = parseLong(service4DurationDays),
                service4DurationHoursLong = parseLong(service4DurationHours),
                service4DurationMinutesLong = parseLong(service4DurationMinutes),
                service4TotalDurationLong = service4DurationDaysLong * 24 * 60 + service4DurationHoursLong * 60 + service4DurationMinutesLong;

        service4TotalDuration = Long.toString(service4TotalDurationLong);

        long service7DurationDaysLong = parseLong(service7DurationDays),
                service7DurationHoursLong = parseLong(service7DurationHours),
                service7DurationMinutesLong = parseLong(service7DurationMinutes),
                service7TotalDurationLong = service7DurationDaysLong * 24 * 60 + service7DurationHoursLong * 60 + service7DurationMinutesLong;

        service7TotalDuration = Long.toString(service7TotalDurationLong);

        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        user12Email = generate.name().username() + "@" + generate.lorem().characters(2, 5) + "." + c + c;
        user12Password = generate.lorem().characters(8, 50);
        user12FirstName = generate.funnyName().name();
        user12Patronymic = generate.aviation().aircraft();
        user12LastName = generate.animal().name();
        user12LastName = user12LastName.substring(0, 1).toUpperCase() + user12LastName.substring(1); //1st letter to Upper case
        user12PhoneNumber = "903" + generate.number().digits(7);
        user12Country = "Russia";
        user12City = "Moscow";
        user12Region = "Moscow";
        user12Subregion = "Yugo-Vostochnyy Administrativnyy Okrug";
        user12District = "Lefortovo";
        user12ZipCode = "111033";
        user12Address = "Shosse Entuziastov, 3к1";
        user12dateDD = Long.toString(generate.number().numberBetween(01, 28));
        user12dateMM = Long.toString(generate.number().numberBetween(01, 12));
        user12dateYYYY = Long.toString(generate.number().numberBetween(1950, 2005));
        user12Nationality = "Russia";
        user12Language = "Russian";
        master12MainCategory = generate.number().numberBetween(0, 8);
        master12MainSubcategory = getRandomSubcategoryFromCategoryValue(master12MainCategory);
        master12MainCategoryNew = generate.number().numberBetween(0, 8);
        master12MainSubcategoryNew = getRandomSubcategoryFromCategoryValue(master12MainCategoryNew);
        service12Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service12Description = generate.backToTheFuture().quote() + " " + generate.hitchhikersGuideToTheGalaxy().quote();
        service12DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service12DurationHours = String.valueOf(generate.number().numberBetween(0, 2));
        service12DurationMinutes = String.valueOf(generate.number().numberBetween(15, 45));
        service12Price = String.valueOf(generate.number().numberBetween(1500, 90000));
        master12MainDescription = generate.rickAndMorty().quote() + " " + generate.twinPeaks().quote();
        master12MainDescriptionNew = generate.aquaTeenHungerForce().character() + " " + generate.princessBride().quote();
        master12MainSpecialization = generate.job().title() + " " + generate.business().creditCardType();
        master12MainSpecializationNew = generate.job().title() + " " + generate.app().name();
        master12MainCompany = generate.app().author() + " " + generate.ancient().titan();
        master12MainCompanyNew = generate.app().author() + " " + generate.ancient().god();
        master12MainLevel = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
        master12MainLevelNew = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
        while (true) {
            if (!master12MainLevelNew.equals(master12MainLevel)) {
                break;
            } else {
                master12MainLevelNew = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
            }
        }
        service12Country = "Russia";
        service12City = "Moscow";
        service12Address = "Shosse Entuziastov, 3к1";
        service12Distance = "3000";
        master12QualificationJobTitle = generate.job().title();
        master12QualificationCompany = generate.company().name();
        master12QualificationFromMonth = generateMonth();
        master12QualificationFromYear = String.valueOf(generate.number().numberBetween(1950, 2016));
        master12QualificationToMonth = generateMonth();
        master12QualificationToYear = String.valueOf(generate.number().numberBetween(2017, 2020));
        master12QualificationDescription = generate.shakespeare().asYouLikeItQuote();
        master12QualificationJobTitleNew = generate.job().position();
        master12QualificationCompanyNew = generate.company().profession();
        master12QualificationFromMonthNew = generateMonth();
        master12QualificationFromYearNew = String.valueOf(generate.number().numberBetween(1970, 2020));
        master12QualificationDescriptionNew = generate.shakespeare().hamletQuote();


        master12ExperienceNew = Integer.toString(year - Integer.parseInt(master12QualificationFromYearNew));

        master12QualificationDate = master12QualificationFromYear + "-" + monthConvertToNumber(master12QualificationFromMonth) + "-" + "01" + " - " + master12QualificationToYear + "-" + monthConvertToNumber(master12QualificationToMonth) + "-" + "01";
        master12QualificationDateNew = master12QualificationFromYearNew + "-" + monthConvertToNumber(master12QualificationFromMonthNew) + "-" + "01" + " - ";

        master12EducationUniversity = generate.university().name();
        master12EducationDegree = generate.educator().course();
        master12EducationAcademicField = generate.job().field();
        master12EducationFromMonth = generateMonth();
        master12EducationFromYear = String.valueOf(generate.number().numberBetween(1995, 2000));
        master12EducationToMonth = generateMonth();
        master12EducationToYear = String.valueOf(generate.number().numberBetween(2001, 2005));
        master12EducationDate = master12EducationFromYear + "-" + monthConvertToNumber(master12EducationFromMonth) + "-01 - " + master12EducationToYear + "-" + monthConvertToNumber(master12EducationToMonth) + "-01";
        master12EducationDescription = generate.job().keySkills();

        master12EducationUniversityNew = generate.educator().university();
        master12EducationDegreeNew = generate.educator().campus();
        master12EducationAcademicFieldNew = generate.job().field();
        master12EducationFromMonthNew = generateMonth();
        master12EducationFromYearNew = String.valueOf(generate.number().numberBetween(2006, 2012));
        master12EducationDateNew = master12EducationFromYearNew + "-" + monthConvertToNumber(master12EducationFromMonthNew) + "-01 - ";
        master12EducationDescriptionNew = generate.job().seniority();

        master12CertificateName = generate.name().title();
        master12CertificateOrganization = generate.address().state();
        master12CertificateDateDay = String.valueOf(generate.number().numberBetween(10, 28));
        master12CertificateDateMonth = generateMonth();
        master12CertificateDateYear = String.valueOf(generate.number().numberBetween(1980, 2020));
        master12CertificateDate = master12CertificateDateYear + "-" + monthConvertToNumber(master12CertificateDateMonth) + "-" + master12CertificateDateDay;
        master12CertificateID = String.valueOf(generate.number().numberBetween(100000000, 600000000));
        master12CertificateLink = "https://" + "www." + generate.color().name() + "." + c + c + "/".replaceAll("\\s+", "").toLowerCase();
        master12CertificateLink = master12CertificateLink.replaceAll("\\s+", "").toLowerCase();
        master12CertificatePhoto = "src/test/resources/img/" + generate.number().numberBetween(1, 6) + ".png";

        master12CertificateNameNew = generate.app().name();
        master12CertificateOrganizationNew = generate.company().name();
        master12CertificateDateDayNew = String.valueOf(generate.number().numberBetween(10, 28));
        master12CertificateDateMonthNew = generateMonth();
        master12CertificateDateYearNew = String.valueOf(generate.number().numberBetween(1980, 2020));
        master12CertificateDateNew = master12CertificateDateYearNew + "-" + monthConvertToNumber(master12CertificateDateMonthNew) + "-" + master12CertificateDateDayNew;
        master12CertificateIDNew = String.valueOf(generate.number().numberBetween(500000001, 999999999));
        master12CertificateLinkNew = "https://" + "www." + generate.app().name() + "." + c + c + "/";
        master12CertificateLinkNew = master12CertificateLinkNew.replaceAll("\\s+", "").toLowerCase();
        master12CertificatePhotoNew = "src/test/resources/img/" + generate.number().numberBetween(7, 12) + ".png";

        user12FirstName1 = generate.funnyName().name() + " 1";
        user12LastName1 = generate.animal().name() + " 1";

        long service12DurationDaysLong = parseLong(service12DurationDays),
                service12DurationHoursLong = parseLong(service12DurationHours),
                service12DurationMinutesLong = parseLong(service12DurationMinutes),
                service12TotalDurationLong = service12DurationDaysLong * 24 * 60 + service12DurationHoursLong * 60 + service12DurationMinutesLong;

        service12TotalDuration = Long.toString(service12TotalDurationLong);
    }

    public static String userCountry,
            userCity,
            userFirstName,
            userEmail,
            userPassword,
            serviceName,
            serviceDescription,
            serviceDuration,
            servicePrice,
            serviceSpecialization,
            masterEducationUniversity;

    public static void setRandomData() {

        Faker generate = new Faker(new Locale("en-US"));
        userCountry = "Russia";
        userCity = "Moscow";
        userFirstName = generate.name().firstName();
        userEmail = generate.lorem().characters(8, 12) + "@" + generate.lorem().characters(2, 3) + ".pp";
        userPassword = generate.internet().password();

        serviceName = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        serviceDescription = generate.lorem().characters(20, 200);
        serviceDuration = String.valueOf(generate.number().numberBetween(15, 45));
        servicePrice = String.valueOf(generate.number().numberBetween(1, 500));
        serviceSpecialization = generate.job().title() + " " + generate.ancient().god();

        masterEducationUniversity = generate.university().name();
    }
}