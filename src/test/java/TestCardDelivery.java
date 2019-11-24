
import data.ProfileClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import page.ActionsCardDelivery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static dataGenerator.DataGenerator.getCity;
import static dataGenerator.DataGenerator.getFullName;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardDelivery {

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateCity")
    public void testInputCity(HashMap<String, String> value) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(value.get("value"));
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputCity.shouldHave(exactText(value.get("message")));
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateDateNegative")
    public void testInputDateNegative(HashMap<String, String> value) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(getCity());
        actionsCardDelivery.setDate(value.get("value"));
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText(value.get("message")));
    }

    @Test
    public void testInputDatePositive() {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(getCity());
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        actionsCardDelivery.setDate(date);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText("Выберите дату встречи с представителем банка"));
    }

    @Test
    public void testInputDateInvalidDate() {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(getCity());
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        actionsCardDelivery.setDate(date);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputDate.shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateName")
    public void testInputName(HashMap<String, String> value){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(getCity());
        actionsCardDelivery.setName(value.get("value"));
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputName.shouldHave(exactText(value.get("message")));
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateNumberPhone")
    public void testInputPhone(HashMap<String, String> value){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(getCity());
        actionsCardDelivery.setName(getFullName());
        actionsCardDelivery.setPhone(value.get("value"));
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.subInputPhone.shouldHave(exactText(value.get("message")));
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateClient")
    public void testAgreementNegative(ProfileClient profileClient){
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(profileClient.getCity());
        actionsCardDelivery.setName(profileClient.getFullName());
        actionsCardDelivery.setPhone(profileClient.getPhoneNumber());
        actionsCardDelivery.clickButtonSubmit();
        assertTrue(actionsCardDelivery.fieldAgreement.getAttribute("class").contains("input_invalid"));
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateClient")
    void testCardDeliverySend(ProfileClient profileClient) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(profileClient.getCity());
        actionsCardDelivery.setName(profileClient.getFullName());
        actionsCardDelivery.setPhone(profileClient.getPhoneNumber());
        actionsCardDelivery.clickAgreement();
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.blockSuccsess.waitUntil(visible, 15000);
    }

    @ParameterizedTest
    @MethodSource("dataGenerator.DataGenerator#generateClient")
    void testRescheduleCardDelivery(ProfileClient profileClient) {
        ActionsCardDelivery actionsCardDelivery = new ActionsCardDelivery();
        actionsCardDelivery.openCardDelivery();
        actionsCardDelivery.setCity(profileClient.getCity());
        actionsCardDelivery.setName(profileClient.getFullName());
        actionsCardDelivery.setPhone(profileClient.getPhoneNumber());
        actionsCardDelivery.clickAgreement();
        actionsCardDelivery.clickButtonSubmit();
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
        actionsCardDelivery.setDate(date);
        actionsCardDelivery.clickButtonSubmit();
        actionsCardDelivery.clickReplan();
        actionsCardDelivery.blockSuccsess.waitUntil(visible, 15000);
    }
}


