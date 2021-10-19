package config;

import com.github.javafaker.Faker;

import java.util.Locale;

import static java.lang.Long.parseLong;

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
            testMessage2;

    public static void setTestData() {
        Faker generate = new Faker(new Locale("en-US"));
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

        user1FirstName = generate.name().firstName() + generate.name().suffix();
        user1LastName = generate.name().lastName() + generate.name().suffix();
        user1PhoneNumber = "911" + generate.number().digits(7);
        user1Country = "Russia";
        user1City = "Moscow";

        user2FirstName = generate.name().firstName() + generate.name().suffix();
        user2LastName = generate.name().lastName() + generate.name().suffix();
        user2PhoneNumber = "911" + generate.number().digits(7);
        user2Country = "Russia";
        user2City = "Moscow";

        user3FirstName = generate.name().firstName() + generate.name().suffix();
        user3LastName = generate.name().lastName() + generate.name().suffix();
        user3PhoneNumber = "911" + generate.number().digits(7);
        user3Country = "Russia";
        user3City = "Moscow";

        user4FirstName = generate.name().firstName() + generate.name().suffix();
        user4LastName = generate.name().lastName() + generate.name().suffix();
        user4PhoneNumber = "911" + generate.number().digits(7);
        user4Country = "Russia";
        user4City = "Moscow";

        user5FirstName = generate.name().firstName() + generate.name().suffix();
        user5LastName = generate.name().lastName() + generate.name().suffix();
        user5PhoneNumber = "911" + generate.number().digits(7);
        user5Country = "Russia";
        user5City = "Moscow";

        user6FirstName = generate.name().firstName() + generate.name().suffix();
        user6LastName = generate.name().lastName() + generate.name().suffix();
        user6PhoneNumber = "6135" + generate.number().digits(6);
        user6Country = "Canada";
        user6City = "Toronto";

        user7FirstName = generate.name().firstName() + generate.name().suffix();
        user7LastName = generate.name().lastName() + generate.name().suffix();
        user7PhoneNumber = "4575" + generate.number().digits(6);
        user7Country = "Finland";
        user7City = "Helsinki";

        user8FirstName = generate.name().firstName() + generate.name().suffix();
        user8LastName = generate.name().lastName() + generate.name().suffix();
        user8PhoneNumber = "5905" + generate.number().digits(6);
        user8Country = "France";
        user8City = "Paris";

        user9FirstName = generate.name().firstName() + generate.name().suffix();
        user9LastName = generate.name().lastName() + generate.name().suffix();
        user9PhoneNumber = "1595" + generate.number().digits(6);
        user9Country = "Germany";
        user9City = "Berlin";

        user10FirstName = generate.name().firstName() + generate.name().suffix();
        user10LastName = generate.name().lastName() + generate.name().suffix();
        user10PhoneNumber = "911" + generate.number().digits(7);
        user10Country = "Russia";
        user10City = "Moscow";

        service1Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service1Description = generate.lorem().characters(20, 2000);
        service1DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service1DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service1DurationMinutes = String.valueOf(generate.number().numberBetween(0, 59));
        service1Price = String.valueOf(generate.number().numberBetween(1, 999));
        service1Specialization = generate.job().title() + " " + generate.ancient().god();
        service1Country = "Russia";
        service1City = "Moscow";
        service1Address = generate.address().fullAddress();
        service1Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service2Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service2Description = generate.lorem().characters(20, 2000);
        service2DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service2DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service2DurationMinutes = String.valueOf(generate.number().numberBetween(0, 59));
        service2Price = String.valueOf(generate.number().numberBetween(1, 999));
        service2Specialization = generate.job().title() + " " + generate.ancient().hero();
        service2Country = "Russia";
        service2City = "Moscow";
        service2Address = generate.address().fullAddress();
        service2Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service3Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service3Description = generate.lorem().characters(20, 2000);
        service3DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service3DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service3DurationMinutes = String.valueOf(generate.number().numberBetween(0, 59));
        service3Price = String.valueOf(generate.number().numberBetween(1, 999));
        service3Specialization = generate.job().title() + " " + generate.ancient().titan();
        service3Country = "Russia";
        service3City = "Moscow";
        service3Address = generate.address().fullAddress();
        service3Distance = String.valueOf(generate.number().numberBetween(0, 9999));

        service4Name = generate.name().title() + " (" + generate.lorem().characters(6, 12) + ")";
        service4Description = generate.lorem().characters(20, 2000);
        service4DurationDays = String.valueOf(generate.number().numberBetween(0, 0));
        service4DurationHours = String.valueOf(generate.number().numberBetween(0, 1));
        service4DurationMinutes = String.valueOf(generate.number().numberBetween(0, 59));
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
        service7DurationMinutes = String.valueOf(generate.number().numberBetween(0, 59));
        service7Price = String.valueOf(generate.number().numberBetween(1, 999));
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
    }
}
