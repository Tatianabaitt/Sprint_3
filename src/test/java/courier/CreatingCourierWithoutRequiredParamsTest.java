package courier;

import SamokatAPI.courier.Courier;
import SamokatAPI.courier.CourierClient;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import static org.junit.Assert.assertEquals;

public class CreatingCourierWithoutRequiredParamsTest {

    private CourierClient courierClient;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без логина")
    public void courierCreateWithoutLoginRejection() {
        String message = "При попытке создании курьера без обязательного поля логин в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Недостаточно данных для создания учетной записи";
        Courier courier = Courier.getRandomWithoutLogin("");
        String messegeError = courierClient.createCourierWithoutRequiredParameter(courier);
        assertEquals(message, expected, messegeError);
    }

    @Test
    @DisplayName("Проверка невозможности создания курьера без пароля")
    public void courierCreateWithoutPasswordRejection() {
        String message = "При попытке создании курьера без обязательного поля логин в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Недостаточно данных для создания учетной записи";
        Courier courier = Courier.getRandomWithoutPassword("");
        String messegeError = courierClient.createCourierWithoutRequiredParameter(courier);
        assertEquals(message, expected, messegeError);
    }
}
