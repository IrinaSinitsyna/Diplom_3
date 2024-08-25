package data_providers;

import com.github.javafaker.Faker;

public class DataGenerator {

    private static final Faker FAKER = new Faker();

    public static String generateName() {
        return FAKER.name().fullName();
    }

    public static String generateEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String generatePassword(int length) {
        return FAKER.internet().password(
                length,
                length + 1,
                true,
                true,
                true
        );
    }
}