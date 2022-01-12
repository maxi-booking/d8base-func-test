package config;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static helpers.DayConverter.*;
import static helpers.MonthConverter.generateMonth;
import static helpers.MonthConverter.monthConvertToNumber;
import static helpers.SubcategoryGenerator.getRandomSubcategoryFromCategoryValue;
import static java.lang.Long.parseLong;
import static org.junit.jupiter.api.Assertions.fail;

public class TestData {

    public static class UserEmails {
        Faker generate = new Faker(new Locale("en-US"));
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        String[] userEmail = {
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().word() + "." + generate.lorem().word() + c,
                generate.name().username() + "@" + generate.lorem().characters(2, 5) + "." + c + c
        };

        public String[] userEmail() {
            return userEmail;
        }
    }

    public static class UserPasswords {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userPassword = {
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.internet().password(),
                generate.lorem().characters(8, 50)
        };

        public String[] userPassword() {
            return userPassword;
        }
    }

    public static class UserFirstNames {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userFirstName = {
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().firstName(),
                generate.name().firstName() + generate.name().suffix(),
                generate.funnyName().name()
        };

        public String[] userFirstName() {
            return userFirstName;
        }
    }

    public static class UserLastNames {
        Faker generate = new Faker(new Locale("en-US"));
        String animalName = generate.animal().name();
        String[] userLastName = {
                generate.name().lastName() + generate.name().lastName(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix() + generate.name().suffix(),
                generate.name().lastName() + generate.name().suffix(),
                animalName.substring(0, 1).toUpperCase() + animalName.substring(1)
        };

        public String[] userLastName() {
            return userLastName;
        }
    }

    public static class UserPhoneNumbers {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userPhoneNumber = {
                "911" + generate.number().digits(7),
                "911" + generate.number().digits(7),
                "911" + generate.number().digits(7),
                "911" + generate.number().digits(7),
                "911" + generate.number().digits(7),
                "6135" + generate.number().digits(6),
                "4575" + generate.number().digits(6),
                "5905" + generate.number().digits(6),
                "1595" + generate.number().digits(6),
                "911" + generate.number().digits(7),
                "923" + generate.number().digits(7),
                "903" + generate.number().digits(7)
        };

        public String[] userPhoneNumber() {
            return userPhoneNumber;
        }
    }

    public static class UserCountries {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userCountry = {
                "Russia",
                "Russia",
                "Russia",
                "Russia",
                "Russia",
                "Canada",
                "Finland",
                "France",
                "Germany",
                "Russia",
                "Россия",
                "Russia"
        };

        public String[] userCountry() {
            return userCountry;
        }
    }

    public static class UserCities {
        Faker generate = new Faker(new Locale("en-US"));
        String[] userCity = {
                "Moscow",
                "Moscow",
                "Moscow",
                "Moscow",
                "Moscow",
                "Toronto",
                "Helsinki",
                "Paris",
                "Berlin",
                "Moscow",
                "Москва",
                "Moscow"
        };

        public String[] userCity() {
            return userCity;
        }
    }

    public static class ServiceNames {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceName = {
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                "",
                "",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")",
                "",
                "",
                "",
                "",
                generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")"
        };

        public String[] serviceName() {
            return serviceName;
        }
    }

    public static class ServiceDescriptions {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceDescription = {
                generate.lorem().characters(20, 2000),
                generate.lorem().characters(20, 2000),
                generate.lorem().characters(20, 2000),
                generate.lorem().characters(20, 2000),
                "",
                "",
                generate.rickAndMorty().quote() + " " + generate.dune().quote(),
                "",
                "",
                "",
                "",
                generate.backToTheFuture().quote() + " " + generate.hitchhikersGuideToTheGalaxy().quote()
        };

        public String[] serviceDescription() {
            return serviceDescription;
        }
    }

    public static class ServiceDurations {
        Faker generate = new Faker(new Locale("en-US"));
        String[] serviceDuration = {
                String.valueOf(generate.number().numberBetween(15, 105)),
                String.valueOf(generate.number().numberBetween(15, 105)),
                String.valueOf(generate.number().numberBetween(15, 105)),
                String.valueOf(generate.number().numberBetween(15, 105)),
                "",
                "",
                String.valueOf(generate.number().numberBetween(15, 105)),
                "",
                "",
                "",
                "",
                String.valueOf(generate.number().numberBetween(15, 165))
        };

        public String[] serviceDuration() {
            return serviceDuration;
        }
    }

    public static class ServicePrices {
        Faker generate = new Faker(new Locale("en-US"));
        String[] servicePrice = {
                String.valueOf(generate.number().numberBetween(1, 9999999)),
                String.valueOf(generate.number().numberBetween(1, 2000)),
                String.valueOf(generate.number().numberBetween(1, 70000)),
                String.valueOf(generate.number().numberBetween(1, 999)),
                "",
                "",
                String.valueOf(generate.number().numberBetween(1, 99)),
                "",
                "",
                "",
                "",
                String.valueOf(generate.number().numberBetween(1500, 90000))
        };

        public String[] servicePrice() {
            return servicePrice;
        }
    }

    public static class Specializations {
        Faker generate = new Faker(new Locale("en-US"));
        String[] specialization = {
                generate.job().title() + " " + generate.ancient().god(),
                generate.job().title() + " " + generate.ancient().hero(),
                generate.job().title() + " " + generate.ancient().titan(),
                generate.job().title() + " " + generate.ancient().primordial(),
                "",
                "",
                generate.job().title() + " " + generate.name().suffix(),
                "",
                "",
                "",
                "",
                generate.job().title() + " " + generate.business().creditCardType()
        };

        public String[] specialization() {
            return specialization;
        }
    }

    public static String
            testUser10New,
            testPassword10New,
            userFirstName9,
            userLastName9,
            user10PatronymicNew,
            userPhoneNumber9,
            userCountry9,
            user10Region,
            user10RegionNew,
            user10SubregionNew,
            userCity9,
            user10DistrictNew,
            user10ZipCodeNew,
            user10AddressNew,
            userCountry9_2,
            user10Region2,
            user10Subregion2,
            userCity9_2,
            user10District2,
            user10ZipCode2,
            user10Address2,
            user10dateDD,
            user10dateMM,
            user10dateYYYY,
            user10Nationality,
            user10Language1,
            user10Language2,
            service1Country,
            service1City,
            service1Address,
            service1Distance,
            service2Country,
            service2City,
            service2Address,
            service2Distance,
            service3Country,
            service3City,
            service3Address,
            service3Distance,
            service4Country,
            service4City,
            service4Address,
            service4Distance,
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
            user12Patronymic,
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
            master12MainDescription,
            master12MainDescriptionNew,
            master12MainSpecializationNew,
            master12MainCompany,
            master12MainCompanyNew,
            master12MainExperience,
            master12MainExperienceNew,
            master12MainLevel,
            master12MainLevelNew,
            service12Country,
            service12City,
            service12Address,
            service12Distance,
            userFirstName11,
            userLastName11,
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
            empty,
            today,
            tomorrow,
            next1Day,
            next2Days,
            next3Days,
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
            randomServiceCategory,
            randomServiceSubcategory,
            randomRating,
            randomCurrency,
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
        tomorrow = getDayXDaysForward(1);
        next1Day = getDayXDaysForward(1);
        next2Days = getDayXDaysForward(2);
        next3Days = getDayXDaysForward(3);

        Faker generate = new Faker(new Locale("en-US"));
        empty = " ";
        randomServiceCategory = generate.number().numberBetween(0, 8);
        randomServiceSubcategory = getRandomSubcategoryFromCategoryValue(randomServiceCategory);
        randomFile = "src/test/resources/img/" + generate.number().numberBetween(1, 12) + ".png";
        randomRating = generate.number().numberBetween(1, 5);
        randomCurrency = generate.number().numberBetween(0, 3);

        service1Country = "Russia";
        service1City = "Moscow";
        service1Address = generate.address().fullAddress();
        service1Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service2Country = "Russia";
        service2City = "Moscow";
        service2Address = generate.address().fullAddress();
        service2Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service3Country = "Russia";
        service3City = "Moscow";
        service3Address = generate.address().fullAddress();
        service3Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service4Country = "Russia";
        service4City = "Moscow";
        service4Address = generate.address().fullAddress();
        service4Distance = String.valueOf(generate.number().numberBetween(0, 9999));

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

        testUser10New = generate.name().username() + "@new.kk";
        testPassword10New = generate.internet().password() + "New";
        userFirstName9 = generate.name().firstName() + "New";
        userLastName9 = generate.name().lastName() + "New";
        user10PatronymicNew = generate.name().username() + "New";
        userPhoneNumber9 = "964" + generate.number().digits(7);

        userCountry9 = "Russia";
        user10Region = "Moscow";
        user10RegionNew = "St.-Petersburg";
        user10SubregionNew = "Petrogradskiy Rayon";
        userCity9 = "Saint Petersburg";
        user10DistrictNew = "Petrogradka";
        user10ZipCodeNew = "197101";
        user10AddressNew = "Каменноостровский пр., 38/96";

        userCountry9_2 = "Canada";
        user10Region2 = "Ontario";
        user10Subregion2 = "Toronto country";
        userCity9_2 = "Toronto";
        user10District2 = "Etobicoke";
        user10ZipCode2 = "M9A";
        user10Address2 = "8 Orkney Crescent";

        user10dateDD = Long.toString(generate.number().numberBetween(10, 28));
        user10dateMM = Long.toString(generate.number().numberBetween(10, 12));
        user10dateYYYY = Long.toString(generate.number().numberBetween(1950, 2005));
        user10Nationality = "Iceland";
        user10Language1 = "Arabic";
        user10Language2 = "Irish";

        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        user12Patronymic = generate.aviation().aircraft();
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
        master12MainDescription = generate.rickAndMorty().quote() + " " + generate.twinPeaks().quote();
        master12MainDescriptionNew = generate.aquaTeenHungerForce().character() + " " + generate.princessBride().quote();
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


        master12MainExperience = Integer.toString(year - Integer.parseInt(master12QualificationFromYear));
        master12MainExperienceNew = Integer.toString(year - Integer.parseInt(master12QualificationFromYearNew));

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

        userFirstName11 = generate.funnyName().name() + " 1";
        userLastName11 = generate.animal().name() + " 1";
    }

    public static String userCountry,
            userCity,
            userFirstName,
            userLastName,
            userEmailRandom,
            userEmailUppercase,
            userEmailLowercase,
            userEmailMixedCase,
            userPasswordRandom,
            userPhoneNumber,
            serviceNameRandom,
            serviceDescriptionRandom,
            serviceDurationRandom,
            servicePriceRandom,
            servicePriceMin,
            servicePriceMax,
            serviceSpecialization,
            serviceAddress,
            serviceDistance,
            masterEducationUniversity;

    public static void setRandomData() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');

        Faker generate = new Faker(new Locale("en-US"));
        userCountry = "Russia";
        userCity = "Moscow";
        userFirstName = generate.name().firstName();
        userLastName = generate.name().lastName();
        userEmailRandom = generate.lorem().characters(8, 12) + "@" + generate.lorem().characters(2, 3) + ".pp";
        userPasswordRandom = generate.internet().password();
        userPhoneNumber = "911" + generate.number().digits(7);

        serviceNameRandom = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        serviceDescriptionRandom = generate.lorem().characters(20, 200);
        serviceDurationRandom = String.valueOf(generate.number().numberBetween(15, 45));
        servicePriceRandom = String.valueOf(generate.number().numberBetween(1, 500));
        servicePriceMin = String.valueOf(generate.number().numberBetween(1, 40000));
        servicePriceMax = String.valueOf(generate.number().numberBetween(1, 40000) + Integer.parseInt(servicePriceMin));
        serviceSpecialization = generate.job().title() + " " + generate.ancient().god();
        serviceAddress = generate.address().fullAddress();
        serviceDistance = String.valueOf(generate.number().numberBetween(0, 9999));

        masterEducationUniversity = generate.university().name();

        userEmailMixedCase = (generate.lorem().characters(2, 4)).toUpperCase() + (generate.lorem().characters(2, 4)).toLowerCase() + (generate.lorem().characters(2, 4)).toUpperCase() + "@" + (generate.lorem().characters(1, 2)).toUpperCase() + (generate.lorem().characters(1, 2)).toLowerCase() + "." + Character.toUpperCase(c) + Character.toLowerCase(c);
        userEmailUppercase = userEmailMixedCase.toUpperCase();
        userEmailLowercase = userEmailMixedCase.toLowerCase();

    }
}