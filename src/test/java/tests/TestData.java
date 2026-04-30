package tests;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.Locale;

public class TestData {

    static Faker faker = new Faker();

    public static final String
            FIRST_NAME = faker.name().firstName(),
            SECOND_NAME = faker.name().lastName(),
            FULL_NAME = FIRST_NAME + " " + SECOND_NAME,
            EMAIL = faker.internet().emailAddress(),
            MOBILE_NUMBER = faker.numerify("##########"),
            CURRENT_ADDRESS = faker.address().fullAddress(),
            PERMANENT_ADDRESS = CURRENT_ADDRESS;

    public static final File PICTURE_FILE = new File("src/test/resources/picture.jpeg");
}
