package courier;

import SamokatAPI.courier.Courier;
import SamokatAPI.courier.CourierClient;
import SamokatAPI.courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static org.junit.Assert.*;

public class CreatingCourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @After
    public void teardown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Проверка корректного создания курьера со всеми полями")
    public void courierCreateSuccess() {
        String messageId = "При создании курьера при попытке логина в ид получен 0";
        String messageCreated = "При создании курьера со всеми параметрами вернулся false";
        Courier courier = Courier.getRandom();
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        assertTrue(messageCreated, created);
        assertNotEquals(messageId, 0, courierId);
    }

    @Test
    @DisplayName("Проверка корректного создания курьера без имени")
    public void courierCreateWithoutFirstnameSuccess() {
        String messageId = "При создании курьера без имени при попытке логина в ид получен 0";
        String messageCreated = "При создании курьера без имени со всеми обязательными параметрами вернулся false";
        Courier courier = Courier.getRandomWithoutFirstname("");
        boolean created = courierClient.create(courier);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        assertTrue(messageCreated, created);
        assertNotEquals(messageId, 0, courierId);
    }

    @Test
    @DisplayName("Проверка невозможности создания идентичных курьеров")
    public void courierCreateDuplicateCourierRejection() {
        String message = "При попытке создании дубликата курьера в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Этот логин уже используется. Попробуйте другой.";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        String messegeError = courierClient.createDublicateCourier(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        assertEquals(message, expected, messegeError);
    }

    @Test
    @DisplayName("Проверка корректного создания курьеров с одинаковыми логинами")
    public void courierCreateDuplicateLoginRejection() {
        String message = "При попытке создании курьера c логином, который уже используется, в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Этот логин уже используется. Попробуйте другой.";
        Courier courier = Courier.getRandom();
        Courier courierSameLogin = Courier.getRandomWithoutLogin(courier.getLogin());
        courierClient.create(courier);
        String messegeError = courierClient.createDublicateCourier(courierSameLogin);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        assertEquals(message, expected, messegeError);
    }
}
