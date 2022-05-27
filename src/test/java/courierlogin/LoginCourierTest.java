package courierlogin;

import io.qameta.allure.junit4.DisplayName;
import SamokatAPI.courier.Courier;
import SamokatAPI.courier.CourierClient;
import SamokatAPI.courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginCourierTest {

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
    @DisplayName("Проверка успешной авторизации курьера с корректным логином и паролем")
    public void courierLoginSuccess() {
        String messegeId = "При попытке логине курьера в теле ответа получен некорретный id";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        assertNotEquals(messegeId, 0, courierId);
    }

    @Test
    @DisplayName("Проверка не успешной авторизации курьера без логина")
    public void courierLoginWithoutLoginRejection() {
        String messege = "При попытке логина курьера без логина в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Недостаточно данных для входа";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        CourierCredentials credsWithoutLogin = creds.CourierCredentialsWithoutLogin(creds);
        String messegeError = courierClient.loginWithoutLogin(credsWithoutLogin);
        assertEquals(messege, expected, messegeError);
    }

    @Test
    @DisplayName("Проверка не успешной авторизации курьера без пароля")
    public void courierLoginWithoutPasswordRejection() {
        String messege = "При попытке лоигна курьера без пароля в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Недостаточно данных для входа";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        CourierCredentials credsWithoutLogin = creds.CourierCredentialsWithoutPassword(creds);
        String messegeError = courierClient.loginWithoutLogin(credsWithoutLogin);
        assertEquals(messege, expected, messegeError);
    }

    @Test
    @DisplayName("Проверка не успешной авторизации курьера с неверным логином")
    public void courierLoginWithWrongLogindRejection() {
        String message = "При попытке лоигна курьера с ошибочным логином в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Учетная запись не найдена";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        CourierCredentials wrongCreds = creds.CourierCredentialsWrongLogin(creds);
        String messegeError = courierClient.loginWithWrongCreds(wrongCreds);
        assertEquals(message, expected, messegeError);
    }

    @Test
    @DisplayName("Проверка не успешной авторизации курьера с ошибочным паролем")
    public void courierLoginWithWrongPasswordRejection() {
        String message = "При попытке лоигна курьера с ошибочным паролем в теле ответа вернулось сообщение отлчиное от ожидаемого";
        String expected = "Учетная запись не найдена";
        Courier courier = Courier.getRandom();
        courierClient.create(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds);
        CourierCredentials wrongCreds = creds.CourierCredentialsWrongPassword(creds);
        String messegeError = courierClient.loginWithWrongCreds(wrongCreds);
        assertEquals(message, expected, messegeError);
    }
}
