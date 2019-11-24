package dataGenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import data.ProfileClient;

import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

public class DataGenerator {

    private static final Faker fakerRU = new Faker(new Locale("ru"));
    private static final Faker fakerEN = new Faker(new Locale("en"));
    private static final FakeValuesService fakeValuesServiceRU = new FakeValuesService(new Locale("RU"), new RandomService());
    private static final FakeValuesService fakeValuesServiceEU = new FakeValuesService(new Locale("en"), new RandomService());


    private DataGenerator() {}

    public static Stream<HashMap<String, String>> generateCity() {

        HashMap<String, String> value1 = new HashMap<String, String>(){{
            put("value", fakeValuesServiceEU.bothify(""));
            put("message", "Поле обязательно для заполнения");
        }};
        HashMap<String, String> value2 = new HashMap<String, String>(){{
            put("value", fakeValuesServiceEU.bothify("!@#$%^&"));
            put("message", "Доставка в выбранный город недоступна");
        }};
        HashMap<String, String> value3 = new HashMap<String, String>(){{
            put("value", fakeValuesServiceEU.letterify("????"));
            put("message", "Доставка в выбранный город недоступна");
        }};
        HashMap<String, String> value4 = new HashMap<String, String>(){{
            put("value", fakerRU.numerify("####"));
            put("message", "Доставка в выбранный город недоступна");
        }};
        HashMap<String, String> value5 = new HashMap<String, String>(){{
            put("value", fakerRU.regexify("[а-я]{4}"));
            put("message", "Доставка в выбранный город недоступна");
        }};
        HashMap<String, String> value6 = new HashMap<String, String>(){{
            put("value", fakerRU.regexify("[а-я][0-9][а-я][0-9]"));
            put("message", "Доставка в выбранный город недоступна");
        }};
        HashMap<String, String> value7 = new HashMap<String, String>(){{
            put("value", fakerRU.address().cityName());
            put("message", "Выберите ваш город");
        }};


        return Stream.of(value1, value2, value3, value4, value5, value6, value7);
    }

    public static Stream<HashMap<String, String>> generateDateNegative() {
        HashMap<String, String> value1 = new HashMap<String, String>(){{
            put("value", "");
            put("message", "Неверно введена дата");
        }};
        HashMap<String, String> value2 = new HashMap<String, String>(){{
            put("value", fakerRU.numerify("####"));
            put("message", "Неверно введена дата");
        }};
        HashMap<String, String> value3 = new HashMap<String, String>(){{
            put("value", fakeValuesServiceEU.letterify("????"));
            put("message", "Неверно введена дата");
        }};
        HashMap<String, String> value4 = new HashMap<String, String>(){{
            put("value",  fakeValuesServiceEU.bothify("!@#$%^&"));
            put("message", "Неверно введена дата");
        }};
        return Stream.of(value1, value2, value3, value4);
    }

    public static String getCity() {
        return fakerRU.address().cityName();
    }

    public static String getFullName() {
        return fakerRU.name().fullName();
    }

    public static String getPhoneNumber() {
        return fakerRU.phoneNumber().phoneNumber();
    }

    public static Stream<HashMap<String, String>> generateName() {
        HashMap<String, String> value1 = new HashMap<String, String>(){{
            put("value", "");
            put("message", "Поле обязательно для заполнения");
        }};
        HashMap<String, String> value2 = new HashMap<String, String>(){{
            put("value", fakerRU.numerify("####"));
            put("message", "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");
        }};
        HashMap<String, String> value3 = new HashMap<String, String>(){{
            put("value", fakeValuesServiceEU.bothify("!@#$%^&"));
            put("message", "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");
        }};
        HashMap<String, String> value4 = new HashMap<String, String>(){{
            put("value", fakerEN.name().firstName() );
            put("message", "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.");
        }};
        HashMap<String, String> value5 = new HashMap<String, String>(){{
            put("value", fakerRU.regexify("[а-я]{4}-[а-я]{4}") );
            put("message", "Укажите точно как в паспорте");
        }};
        HashMap<String, String> value6 = new HashMap<String, String>(){{
            put("value", fakerRU.regexify("[а-я]{4} [а-я]{4}"));
            put("message", "Укажите точно как в паспорте");
        }};
        HashMap<String, String> value7 = new HashMap<String, String>(){{
            put("value",  fakerRU.regexify("[а-я]{4} [а-я]{4}-[а-я]{4}"));
            put("message", "Укажите точно как в паспорте");
        }};
        HashMap<String, String> value8 = new HashMap<String, String>(){{
            put("value",  fakerRU.name().firstName());
            put("message", "Укажите точно как в паспорте");
        }};
        return Stream.of(value1, value2, value3, value4, value5, value6, value7, value8);
    }

    public static Stream<HashMap<String, String>> generateNumberPhone() {
        HashMap<String, String> value1 = new HashMap<String, String>(){{
            put("value", "");
            put("message", "Поле обязательно для заполнения");
        }};
        HashMap<String, String> value2 = new HashMap<String, String>(){{
            put("value", fakerRU.regexify("+[а-я]{11}"));
            put("message", "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.");
        }};
        HashMap<String, String> value3 = new HashMap<String, String>(){{
            put("value", "!@#$%^&");
            put("message", "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.");
        }};
        HashMap<String, String> value4 = new HashMap<String, String>(){{
            put("value", fakerRU.phoneNumber().phoneNumber());
            put("message", "На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно.");
        }};
        return Stream.of(value1, value2, value3, value4);
    }

    public static Stream<ProfileClient> generateClient() {
        return Stream.of(new ProfileClient(getCity(), getFullName(), getPhoneNumber()));
    }
}
