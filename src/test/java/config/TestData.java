package config;

import com.github.javafaker.Faker;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.restassured.response.Response;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static api.Localization.*;
import static com.codeborne.selenide.Selenide.sleep;
import static helpers.CountryConverter.getCountryCode;
import static helpers.CountryConverter.getCountryId;
import static helpers.Currency.getCurrencyById;
import static helpers.DateTimeFormatter.*;
import static helpers.LanguageConverter.getLocale;
import static helpers.SubcategoryGenerator.*;
import static java.lang.String.valueOf;

public class TestData {

    public static final String
            xTimeZone = "Europe/Moscow";
    public static final int
            timeZone = 3;

    public static String[] dateTimes() { //get 3 unique DateTimes for booking

        String dateTime1 = getDateTime();
        String date1 = dateTime1.substring(0, 10);

        String dateTime2 = getDateTime();
        String date2 = dateTime2.substring(0, 10);
        while (date1.equals(date2)) {
            dateTime2 = getDateTime();
            date2 = dateTime2.substring(0, 10);
        }

        String dateTime3 = getDateTime();
        String date3 = dateTime3.substring(0, 10);
        while (date3.equals(date1) || date3.equals(date2)) {
            dateTime3 = getDateTime();
            date3 = dateTime3.substring(0, 10);
        }

        return new String[]{dateTime1, dateTime2, dateTime3};
    }

    public static String
            english,
            russian,
            german,
            french,
            spanish,
            arabic,
            greek,
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
            user10Nationality,
            user10Language1,
            user10Language2,
            reviewText1,
            reviewText2,
            reviewText3,
            reviewText4,
            masterComment,
            emptySpace,
            today,
            tomorrow,
            next1Day,
            next2Days,
            next3Days,
            random,
            man,
            male,
            woman,
            female,
            person,
            company,
            junior,
            middle,
            senior,
            onlineLocation,
            clientLocation,
            professionalLocation;

    public static Integer
            cad,
            eur,
            rub,
            usd,
            online,
            client,
            professional,
            unitsKilometers,
            unitsMiles,
            monday,
            tuesday,
            wednesday,
            thursday,
            friday,
            saturday,
            sunday;


    public static final String
            englishLanguage = "English",
            russianLanguage = "Russian",
            germanLanguage = "German",
            frenchLanguage = "French",
            spanishLanguage = "Spanish",
            arabicLanguage = "Arabic",
            greekLanguage = "Greek";

    public static final Integer

            // days of the week ID's
            dayOfTheWeek = dayStringToId(LocalDate.now().getDayOfWeek().name()),
            dayIdNext1Day = getNextDayId(dayOfTheWeek),
            dayIdNext2Days = getNextDayId(dayIdNext1Day),
            dayUdNext3Days = getNextDayId(dayIdNext2Days);

    public static Object
            emptyNull;

    public static Boolean
            yes,
            no,
            on,
            off,
            instantBooking,
            noInstantBooking,
            forAnotherPerson,
            forMyself,
            active,
            inactive;

    public static final String[]
            paymentCashOnline = {"cash", "online"},
            paymentCash = {"cash"},
            paymentOnline = {"online"};

    public static void setTestData() {

        //languages
        english = getLanguageNameByLocale(localeAPI, getLocale("english"));
        russian = getLanguageNameByLocale(localeAPI, getLocale("russian"));
        german = getLanguageNameByLocale(localeAPI, getLocale("german"));
        french = getLanguageNameByLocale(localeAPI, getLocale("french"));
        spanish = getLanguageNameByLocale(localeAPI, getLocale("spanish"));
        arabic = getLanguageNameByLocale(localeAPI, getLocale("arabic"));
        greek = getLanguageNameByLocale(localeAPI, getLocale("greek"));

        //currency
        cad = 0;
        eur = 1;
        rub = 2;
        usd = 3;

        //service location
        online = 0;
        client = 1;
        professional = 2;

        //service location strings
        onlineLocation = "Online";
        clientLocation = "Client's place";
        professionalLocation = "Professional's place";

        //gender vars
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

        //units of distance
        unitsKilometers = 0;
        unitsMiles = 1;

        //random confirmation
        random = "random";

        //days of the week
        monday = 0;
        tuesday = 1;
        wednesday = 2;
        thursday = 3;
        friday = 4;
        saturday = 5;
        sunday = 6;

        yes = true;
        no = false;
        on = true;
        off = false;
        instantBooking = true;
        noInstantBooking = false;
        active = true;
        inactive = false;
        forAnotherPerson = true;
        forMyself = false;

        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int year = currentDate.getYear();
        today = Integer.toString(day);
        tomorrow = getDayXDaysForward(1);
        next1Day = getDayXDaysForward(1);
        next2Days = getDayXDaysForward(2);
        next3Days = getDayXDaysForward(3);

        Faker generate = new Faker(new Locale("en-US"));
        emptyNull = null;
        emptySpace = " ";

        reviewText1 = generate.lorem().sentence(10);
        reviewText2 = generate.lorem().characters(2000, 2500);
        reviewText3 = generate.rickAndMorty().quote();
        reviewText4 = generate.chuckNorris().fact();
        masterComment = generate.backToTheFuture().quote();

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
        user10AddressNew = "?????????????????????????????????? ????., 38/96";

        userCountry9_2 = "Canada";
        user10Region2 = "Ontario";
        user10Subregion2 = "Toronto country";
        userCity9_2 = "Toronto";
        user10District2 = "Etobicoke";
        user10ZipCode2 = "M9A";
        user10Address2 = "8 Orkney Crescent";

        user10Nationality = "Iceland";
        user10Language1 = "Arabic";
        user10Language2 = "Irish";
    }

    public String
            randomSpaces,
            randomFile,
            reviewText,
            userCountry,
            userCity,
            userFirstName,
            userLastName,
            userPatronymic,
            userEmail,
            userEmailUppercase,
            userEmailLowercase,
            userEmailMixedCase,
            userPassword,
            userPhoneNumber,
            userBirthDay,
            userBirthMonth,
            userBirthYear,
            clientCountry,
            clientCity,
            clientFirstName,
            clientLastName,
            clientPatronymic,
            clientEmail,
            clientPassword,
            clientPhoneNumber,
            serviceName,
            serviceDescription,
            serviceDescriptionAlt,
            serviceDuration,
            serviceDurationLong,
            masterLevel,
            masterLevelAlt,
            masterQualificationDate,
            masterQualificationDateAlt,
            masterQualificationJobTitle,
            masterQualificationJobTitleAlt,
            masterQualificationCompany,
            masterQualificationCompanyAlt,
            masterQualificationFromMonth,
            masterQualificationFromMonthAlt,
            masterQualificationFromYear,
            masterQualificationFromYearAlt,
            masterQualificationToMonth,
            masterQualificationToMonthAlt,
            masterQualificationToYear,
            masterQualificationToYearAlt,
            masterQualificationDescription,
            masterQualificationDescriptionAlt,
            masterEducationUniversity,
            masterEducationUniversityAlt,
            masterEducationDegree,
            masterEducationDegreeAlt,
            masterEducationAcademicField,
            masterEducationAcademicFieldAlt,
            masterEducationFromMonth,
            masterEducationFromMonthAlt,
            masterEducationFromYear,
            masterEducationFromYearAlt,
            masterEducationToMonth,
            masterEducationToMonthAlt,
            masterEducationToYear,
            masterEducationToYearAlt,
            masterEducationDate,
            masterEducationDateAlt,
            masterEducationDescription,
            masterEducationDescriptionAlt,
            masterExperience,
            masterExperienceAlt,
            masterCertificateName,
            masterCertificateNameAlt,
            masterCertificateOrganization,
            masterCertificateOrganizationAlt,
            masterCertificateDateDay,
            masterCertificateDateDayAlt,
            masterCertificateDateMonth,
            masterCertificateDateMonthAlt,
            masterCertificateDateYear,
            masterCertificateDateYearAlt,
            masterCertificateDate,
            masterCertificateDateAlt,
            masterCertificateID,
            masterCertificateIDAlt,
            masterCertificateLink,
            masterCertificateLinkAlt,
            masterCertificatePhoto,
            masterCertificatePhotoAlt,
            masterCompany,
            masterCompanyAlt,
            servicePrice,
            servicePriceMin,
            servicePriceMax,
            serviceCurrency,
            userSpecialization,
            userSpecializationAlt,
            serviceAddress,
            serviceDistance,
            bookingDateTime,
            userChatMessage,
            clientChatMessage,
            defStartTime,
            defEndTime;

    public int
            randomNumber,
            serviceLocationDistance,
            distanceUnits,
            serviceCategory,
            serviceCategoryAlt,
            serviceSubcategoryId,
            serviceSubcategoryIdAlt,
            serviceSubcategoryList,
            serviceSubcategoryListAlt,
            serviceSubcategory,
            serviceSubcategoryAlt,
            randomRating,
            serviceCurrencyId,
            randomStartTime,
            randomEndTime,
            seed;

    public String[] tags;

    public Data data;

    public void setRandomData() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int year = currentDate.getYear();
        seed = (int) System.currentTimeMillis();

        Faker generate = new Faker(new Locale(localeAPI));

        randomNumber = generate.number().numberBetween(1, 7);
        for (int i = 0; i < randomNumber; i++) {
            if (randomSpaces != null) {
                randomSpaces = " " + randomSpaces;
            } else {
                randomSpaces = " ";
            }
        }

        // generate schedule for a service
        randomStartTime = generate.number().numberBetween(0, 24) * 100 + generate.number().numberBetween(0, 4) * 15;
        while (true) {
            if (randomStartTime < 2345 && randomStartTime / 100 != 9) {
                break;
            } else {
                randomStartTime = generate.number().numberBetween(0, 24) * 100 + generate.number().numberBetween(0, 4) * 15;
            }
        }
        if (randomStartTime % 100 == 45) {
            randomEndTime = (randomStartTime / 100 + generate.number().numberBetween(0, (23 - randomStartTime / 100)) + 1) * 100;
            while (true) {
                if (randomEndTime / 100 != 17) {
                    break;
                } else {
                    randomEndTime = (randomStartTime / 100 + generate.number().numberBetween(0, (23 - randomStartTime / 100)) + 1) * 100;
                }
            }
        } else {
            randomEndTime = (randomStartTime / 100 + generate.number().numberBetween(0, (24 - randomStartTime / 100))) * 100;
            while (true) {
                if (randomEndTime / 100 != 17) {
                    break;
                } else {
                    randomEndTime = (randomStartTime / 100 + generate.number().numberBetween(0, (24 - randomStartTime / 100))) * 100;
                }
            }
        }
        if (randomEndTime / 100 == randomStartTime / 100) {
            randomEndTime = randomEndTime + ((((randomStartTime % 100) / 15) + generate.number().numberBetween(0, 4 - ((randomStartTime % 100) / 15))) * 15);
        } else {
            randomEndTime = randomEndTime + generate.number().numberBetween(0, 4) * 15;
        }

        defStartTime = "9:00";
        defEndTime = "17:00";

        serviceSubcategoryList = generate.number().numberBetween(0, (subcategoryCount));
        serviceSubcategoryListAlt = generate.number().numberBetween(0, (subcategoryCount));

        serviceSubcategory = getSubcategoryOrderWithinCategory(subcategories, serviceSubcategoryList);
        serviceSubcategoryAlt = getSubcategoryOrderWithinCategory(subcategories, serviceSubcategoryListAlt);

        serviceSubcategoryId = getSubcategoryId(subcategories, serviceSubcategoryList);
        serviceSubcategoryIdAlt = getSubcategoryId(subcategories, serviceSubcategoryListAlt);

        serviceCategory = getCategoryOrderById(categories, getCategoryIdBySubcategoryId(subcategories, serviceSubcategoryId));
        serviceCategoryAlt = getCategoryOrderById(categories, getCategoryIdBySubcategoryId(subcategories, serviceSubcategoryIdAlt));

        randomFile = "src/test/resources/img/" + generate.number().numberBetween(1, 12) + ".png";
        randomRating = generate.number().numberBetween(1, 6);
        reviewText = generate.rickAndMorty().quote() + " " + generate.chuckNorris().fact();

        String[] userCountryList = countryList(localeAPI);
        int userCountryRandomIndex = generate.number().numberBetween(0, userCountryList.length);
        String userCountrySlug = userCountryList[userCountryRandomIndex];
        userCountry = "Russia"; //countrySlugToName(localeAPI, userCountrySlug);

        String[] userCityList = cityList(localeAPI, getCountryId(localeAPI, userCountry));
        int userCityRandomIndex = generate.number().numberBetween(0, userCityList.length);
        userCity = "Moscow"; //userCityList[userCityRandomIndex];

        userFirstName = generate.name().firstName();
        userLastName = generate.name().lastName();
        userPatronymic = generate.name().nameWithMiddle();
        if (userPatronymic.length() > 30) {
            userPatronymic = userPatronymic.substring(0, 30);
        }
        userEmail = generate.lorem().characters(13, 16) + c + "@" + generate.lorem().word() + ".us" + c;
        userPassword = generate.internet().password();

        getCountryCode(localeAPI, userCountry);
        new Locale(english, userCountry);
        userPhoneNumber = Long.toString(PhoneNumberUtil.getInstance().getExampleNumber(
                PhoneNumberUtil.getInstance().getRegionCodeForCountryCode(
                        Integer.parseInt(
                                getCountryCode(localeAPI, userCountry).split("-")[0].replaceAll("[-]", "")
                        )
                )
        ).getNationalNumber());
        userBirthDay = Integer.toString(generate.number().numberBetween(10, 28));
        userBirthMonth = Integer.toString(generate.number().numberBetween(10, 12));
        userBirthYear = Integer.toString(generate.number().numberBetween(1950, 2005));

        String[] clientCountryList = countryList(localeAPI);
        int clientCountryRandomIndex = generate.number().numberBetween(0, clientCountryList.length);
        String clientCountrySlug = clientCountryList[clientCountryRandomIndex];
        clientCountry = "Russia";//countrySlugToName(localeAPI, clientCountrySlug);

        String[] clientCityList = cityList(localeAPI, getCountryId(localeAPI, clientCountry));
        int clientCityRandomIndex = generate.number().numberBetween(0, clientCityList.length);
        clientCity = "Moscow";// clientCityList[clientCityRandomIndex];

        clientFirstName = generate.name().firstName();
        clientLastName = generate.name().lastName();
        clientPatronymic = generate.name().nameWithMiddle();
        if (clientPatronymic.length() > 30) {
            clientPatronymic = clientPatronymic.substring(0, 30);
        }
        clientEmail = generate.lorem().characters(13, 16) + c + "@" + generate.lorem().word() + ".cl" + c;
        clientPassword = generate.internet().password();

        clientPhoneNumber = Long.toString(PhoneNumberUtil.getInstance().getExampleNumber(
                PhoneNumberUtil.getInstance().getRegionCodeForCountryCode(
                        Integer.parseInt(
                                getCountryCode(localeAPI, clientCountry).split("-")[0].replaceAll("[-]", "")
                        )
                )
        ).getNationalNumber());

        serviceName = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        serviceDescription = generate.rickAndMorty().quote() + " " + generate.hobbit().quote();
        serviceDescriptionAlt = generate.rickAndMorty().quote() + " " + generate.hobbit().quote();
        while (true) {
            if (!serviceDescriptionAlt.equals(serviceDescription)) {
                break;
            } else {
                serviceDescriptionAlt = generate.rickAndMorty().quote();
            }
        }
        serviceDuration = new String[]{"15", "30", "45"}[(int) (Math.random() * 3)];
        serviceDurationLong = valueOf(generate.number().numberBetween(96, 250) * 15); // 1440 minutes (day) / 3750 minutes (2,6 days)
        masterLevel = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
        masterLevelAlt = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
        while (true) {
            if (!masterLevelAlt.equals(masterLevel)) {
                break;
            } else {
                masterLevelAlt = new String[]{"junior", "middle", "senior"}[(int) (Math.random() * 3)];
            }
        }

        masterCompany = generate.app().author() + " " + generate.ancient().titan();
        masterCompanyAlt = generate.app().author() + " " + generate.ancient().titan();
        while (true) {
            if (!masterCompanyAlt.equals(masterCompany)) {
                break;
            } else {
                masterCompanyAlt = generate.app().author() + " " + generate.ancient().titan();
            }
        }
        servicePrice = valueOf(generate.number().numberBetween(1, 500));
        servicePriceMin = valueOf(generate.number().numberBetween(1, 40000));
        servicePriceMax = valueOf(generate.number().numberBetween(1, 40000) + Integer.parseInt(servicePriceMin));
        serviceCurrencyId = generate.number().numberBetween(0, 4);
        serviceCurrency = getCurrencyById(serviceCurrencyId);
        userSpecialization = generate.job().title() + " " + generate.ancient().god();
        userSpecializationAlt = generate.job().title() + " " + generate.ancient().god();
        while (true) {
            if (!userSpecializationAlt.equals(userSpecialization)) {
                break;
            } else {
                userSpecializationAlt = generate.job().title() + " " + generate.ancient().god();
            }
        }
        serviceAddress = generate.address().fullAddress();
        serviceDistance = valueOf(generate.number().numberBetween(0, 9999));
        serviceLocationDistance = generate.number().numberBetween(1, 9999);
        distanceUnits = generate.number().numberBetween(0, 2);

        bookingDateTime = getDateTime();

        userEmailMixedCase = (generate.lorem().characters(2, 4)).toUpperCase() + (generate.lorem().characters(2, 4)).toLowerCase() + (generate.lorem().characters(2, 4)).toUpperCase() + "@" + (generate.lorem().characters(1, 2)).toUpperCase() + (generate.lorem().characters(1, 2)).toLowerCase() + "." + Character.toUpperCase(c) + Character.toLowerCase(c);
        userEmailUppercase = userEmailMixedCase.toUpperCase();
        userEmailLowercase = userEmailMixedCase.toLowerCase();

        //Professional's profile qualification tab

        masterQualificationJobTitle = generate.job().title();
        masterQualificationJobTitleAlt = generate.job().title();
        while (true) {
            if (!masterQualificationJobTitleAlt.equals(masterQualificationJobTitle)) {
                break;
            } else {
                masterQualificationJobTitleAlt = generate.job().title();
            }
        }
        masterQualificationCompany = generate.company().name();
        masterQualificationCompanyAlt = generate.company().name();
        while (true) {
            if (!masterQualificationCompanyAlt.equals(masterQualificationCompany)) {
                break;
            } else {
                masterQualificationCompanyAlt = generate.company().name();
            }
        }
        masterQualificationFromMonth = generateMonth();
        masterQualificationFromMonthAlt = generateMonth();
        masterQualificationFromYear = valueOf(generate.number().numberBetween(1950, 2016));
        masterQualificationFromYearAlt = valueOf(generate.number().numberBetween(1950, 2016));
        while (true) {
            if (!masterQualificationFromYearAlt.equals(masterQualificationFromYear)) {
                break;
            } else {
                masterQualificationFromYearAlt = valueOf(generate.number().numberBetween(1950, 2016));
            }
        }
        masterQualificationToMonth = generateMonth();
        masterQualificationToMonthAlt = generateMonth();
        while (true) {
            if (!masterQualificationToMonthAlt.equals(masterQualificationToMonth)) {
                break;
            } else {
                masterQualificationToMonthAlt = generateMonth();
            }
        }
        masterQualificationToYear = valueOf(generate.number().numberBetween(2017, 2020));
        masterQualificationToYearAlt = valueOf(generate.number().numberBetween(2017, 2020));
        while (true) {
            if (!masterQualificationToYearAlt.equals(masterQualificationToYear)) {
                break;
            } else {
                masterQualificationToYearAlt = valueOf(generate.number().numberBetween(2017, 2020));
            }
        }
        masterQualificationDescription = generate.shakespeare().asYouLikeItQuote();
        masterQualificationDescriptionAlt = generate.shakespeare().asYouLikeItQuote();
        while (true) {
            if (!masterQualificationDescriptionAlt.equals(masterQualificationDescription)) {
                break;
            } else {
                masterQualificationDescriptionAlt = generate.shakespeare().asYouLikeItQuote();
            }
        }
        masterQualificationDate = masterQualificationFromYear + "-" + monthConvertToNumber(masterQualificationFromMonth) + "-" + "01" + " - " + masterQualificationToYear + "-" + monthConvertToNumber(masterQualificationToMonth) + "-" + "01";
        masterQualificationDateAlt = masterQualificationFromYearAlt + "-" + monthConvertToNumber(masterQualificationFromMonthAlt) + "-" + "01" + " - ";

        masterExperience = Integer.toString(year - Integer.parseInt(masterQualificationFromYear));
        masterExperienceAlt = Integer.toString(year - Integer.parseInt(masterQualificationFromYearAlt));
        while (true) {
            if (!masterExperienceAlt.equals(masterExperience)) {
                break;
            } else {
                masterExperienceAlt = Integer.toString(year - Integer.parseInt(masterQualificationDateAlt));
            }
        }

        //Professional's profile education tab

        masterEducationUniversity = generate.university().name();
        masterEducationUniversityAlt = generate.educator().university();
        masterEducationDegree = generate.educator().course();
        masterEducationDegreeAlt = generate.educator().campus();
        masterEducationAcademicField = generate.job().field();
        masterEducationAcademicFieldAlt = generate.job().field();
        masterEducationFromMonth = generateMonth();
        masterEducationFromMonthAlt = generateMonth();
        masterEducationFromYear = valueOf(generate.number().numberBetween(1995, 2000));
        masterEducationFromYearAlt = valueOf(generate.number().numberBetween(2006, 2012));
        masterEducationToMonth = generateMonth();
        masterEducationToMonthAlt = generateMonth();
        masterEducationToYear = valueOf(generate.number().numberBetween(2001, 2005));
        masterEducationToYearAlt = valueOf(generate.number().numberBetween(2013, 2018));
        masterEducationDate = masterEducationFromYear + "-" + monthConvertToNumber(masterEducationFromMonth) + "-01 - " + masterEducationToYear + "-" + monthConvertToNumber(masterEducationToMonth) + "-01";
        masterEducationDateAlt = masterEducationFromYearAlt + "-" + monthConvertToNumber(masterEducationFromMonthAlt) + "-01 - ";
        masterEducationDescription = generate.job().keySkills();
        masterEducationDescriptionAlt = generate.job().seniority();

        //Professional's profile certificates tab

        masterCertificateName = generate.name().title();
        masterCertificateNameAlt = generate.app().name();
        masterCertificateOrganization = generate.address().state();
        masterCertificateOrganizationAlt = generate.company().name();
        masterCertificateDateDay = valueOf(generate.number().numberBetween(10, 28));
        masterCertificateDateDayAlt = valueOf(generate.number().numberBetween(10, 28));
        masterCertificateDateMonth = generateMonth();
        masterCertificateDateMonthAlt = generateMonth();
        masterCertificateDateYear = valueOf(generate.number().numberBetween(1980, 2020));
        masterCertificateDateYearAlt = valueOf(generate.number().numberBetween(1980, 2020));
        masterCertificateDate = masterCertificateDateYear + "-" + monthConvertToNumber(masterCertificateDateMonth) + "-" + masterCertificateDateDay;
        masterCertificateDateAlt = masterCertificateDateYearAlt + "-" + monthConvertToNumber(masterCertificateDateMonthAlt) + "-" + masterCertificateDateDayAlt;
        masterCertificateID = valueOf(generate.number().numberBetween(100000000, 600000000));
        masterCertificateIDAlt = valueOf(generate.number().numberBetween(500000001, 999999999));
        masterCertificateLink = "https://" + "www." + generate.color().name() + "." + c + c + "/".replaceAll("\\s+", "").toLowerCase();
        masterCertificateLink = masterCertificateLink.replaceAll("\\s+", "").toLowerCase();
        masterCertificateLinkAlt = "https://" + "www." + generate.app().name() + "." + c + c + "/";
        masterCertificateLinkAlt = masterCertificateLinkAlt.replaceAll("\\s+", "").toLowerCase();
        masterCertificatePhoto = "src/test/resources/img/" + generate.number().numberBetween(1, 6) + ".png";
        masterCertificatePhotoAlt = "src/test/resources/img/" + generate.number().numberBetween(7, 12) + ".png";

        userChatMessage = generate.dragonBall().character() + " > " + generate.dragonBall().character();
        clientChatMessage = generate.friends().quote() + " (c) " + generate.dragonBall().character();

        tags = new String[]{
                seed + "-" + generate.color().name() + "-0",
                seed + "-" + generate.color().name() + "-1",
                seed + "-" + generate.color().name() + "-2",
                seed + "-" + generate.color().name() + "-3",
                seed + "-" + generate.color().name() + "-4",
                seed + "-" + generate.color().name() + "-5",
                seed + "-" + generate.color().name() + "-6",
                seed + "-" + generate.color().name() + "-7",
                seed + "-" + generate.color().name() + "-8",
                seed + "-" + generate.color().name() + "-9",
                seed + "-" + generate.color().name() + "-10"
        };

        data = new Data();
    }

    /**
     * Class with random data
     * <br />
     * <br />
     * <p>
     * [0] - user/professional
     * <br />
     * [1] - client
     */
    public class Data {
        public String locale = localeAPI;
        public String english = TestData.this.english;
        public String russian = TestData.this.russian;
        public String german = TestData.this.german;
        public String french = TestData.this.french;
        public String spanish = TestData.this.spanish;
        public String arabic = TestData.this.arabic;
        public String greek = TestData.this.greek;
        public String[] firstName = new String[]{userFirstName, clientFirstName};
        public String[] lastName = new String[]{userLastName, clientLastName};
        public String[] patronymic = new String[]{userPatronymic, clientPatronymic};
        public String[] email = new String[]{userEmail, clientEmail};
        public String[] password = new String[]{userPassword, clientPassword};
        public String[] phoneNumber = new String[]{userPhoneNumber, clientPhoneNumber};
        public String[] country = new String[]{userCountry, clientCountry};
        public String[] city = new String[]{userCity, clientCity};
        public String[] birthDay = new String[]{userBirthDay};
        public String[] birthMonth = new String[]{userBirthMonth};
        public String[] birthYear = new String[]{userBirthYear};
        public String[] specialization = new String[]{userSpecialization, userSpecializationAlt};
        public String name = serviceName;
        public String[] description = new String[]{serviceDescription, serviceDescriptionAlt};
        public String duration = serviceDuration;
        public String durationLong = serviceDurationLong;
        public String[] level = new String[]{masterLevel, masterLevelAlt};
        public String[] experience = new String[]{masterExperience, masterExperienceAlt};
        public String[] company = new String[]{masterCompany, masterCompanyAlt};
        public String[] qualificationDate = new String[]{masterQualificationDate, masterQualificationDateAlt};
        public String[] qualificationJobTitle = new String[]{masterQualificationJobTitle, masterQualificationJobTitleAlt};
        public String[] qualificationCompany = new String[]{masterQualificationCompany, masterQualificationCompanyAlt};
        public String[] qualificationFromMonth = new String[]{masterQualificationFromMonth, masterQualificationFromMonthAlt};
        public String[] qualificationFromYear = new String[]{masterQualificationFromYear, masterQualificationFromYearAlt};
        public String[] qualificationToMonth = new String[]{masterQualificationToMonth, masterQualificationToMonthAlt};
        public String[] qualificationToYear = new String[]{masterQualificationToYear, masterQualificationToYearAlt};
        public String[] qualificationDescription = new String[]{masterQualificationDescription, masterQualificationDescriptionAlt};
        public String[] educationUniversity = new String[]{masterEducationUniversity, masterEducationUniversityAlt};
        public String[] educationDegree = new String[]{masterEducationDegree, masterEducationDegreeAlt};
        public String[] educationAcademicField = new String[]{masterEducationAcademicField, masterEducationAcademicFieldAlt};
        public String[] educationFromMonth = new String[]{masterEducationFromMonth, masterEducationFromMonthAlt};
        public String[] educationFromYear = new String[]{masterEducationFromYear, masterEducationFromYearAlt};
        public String[] educationToMonth = new String[]{masterEducationToMonth, masterEducationToMonthAlt};
        public String[] educationToYear = new String[]{masterEducationToYear, masterEducationToYearAlt};
        public String[] educationDate = new String[]{masterEducationDate, masterEducationDateAlt};
        public String[] educationDescription = new String[]{masterEducationDescription, masterEducationDescriptionAlt};
        public String[] certificateName = new String[]{masterCertificateName, masterCertificateNameAlt};
        public String[] certificateOrganization = new String[]{masterCertificateOrganization, masterCertificateOrganizationAlt};
        public String[] certificateDateDay = new String[]{masterCertificateDateDay, masterCertificateDateDayAlt};
        public String[] certificateDateMonth = new String[]{masterCertificateDateMonth, masterCertificateDateMonthAlt};
        public String[] certificateDateYear = new String[]{masterCertificateDateYear, masterCertificateDateYearAlt};
        public String[] certificateDate = new String[]{masterCertificateDate, masterCertificateDateAlt};
        public String[] certificateID = new String[]{masterCertificateID, masterCertificateIDAlt};
        public String[] certificateLink = new String[]{masterCertificateLink, masterCertificateLinkAlt};
        public String[] certificatePhoto = new String[]{masterCertificatePhoto, masterCertificatePhotoAlt};
        public String price = servicePrice;
        public String priceMin = servicePriceMin;
        public String priceMax = servicePriceMax;
        public String currency = serviceCurrency;
        public String address = serviceAddress;
        public String distance = serviceDistance;
        public int locationDistance = serviceLocationDistance;
        public String dateTime = bookingDateTime;
        public int[] category = new int[]{serviceCategory, serviceCategoryAlt};
        public int[] subcategory = new int[]{serviceSubcategory, serviceSubcategoryAlt};
        public int[] subcategoryId = new int[]{serviceSubcategoryId, serviceSubcategoryIdAlt};
        public String review = reviewText;
        public int rating = randomRating;
        public int currencyId = serviceCurrencyId;
        public int units = distanceUnits; // random units km/mil
        public String file = randomFile;
        public String[] message = new String[]{userChatMessage, clientChatMessage};
        public String[] tag = new String[]{tags[0], tags[1], tags[2], tags[3], tags[4], tags[5], tags[6], tags[7], tags[8], tags[9], tags[10]};

        public int days = 7;
        public String[] startTime = {defStartTime, defStartTime, defStartTime, defStartTime, defStartTime, defStartTime, defStartTime};
        public String[] endTime = {defEndTime, defEndTime, defEndTime, defEndTime, defEndTime, defEndTime, defEndTime};
        public int sType = online; // service type - online / client / master
        public boolean iBooking = on; // instant booking on/off (true/false)
        public String[] payment = paymentCashOnline; // payment options: cash / online / cashOnline

        public String[] accessToken = new String[]{null, null};
        public int[] locationsId = new int[]{0, 0};
        public int professionalId;
        public int serviceId;
        public int professionalLocationId;
        public int serviceLocationId;
        public int orderId;
    }
}