package SamokatAPI.courier;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }

    public CourierCredentials CourierCredentialsWithoutLogin(CourierCredentials creds) {
        creds.login = "";
        return creds;
    }

    public CourierCredentials CourierCredentialsWithoutPassword(CourierCredentials creds) {
        creds.password = "";
        return creds;
    }

    public CourierCredentials CourierCredentialsWrongLogin(CourierCredentials creds) {
        creds.login = RandomStringUtils.randomAlphabetic(10);
        return creds;
    }

    public CourierCredentials CourierCredentialsWrongPassword(CourierCredentials creds) {
        creds.password = RandomStringUtils.randomAlphabetic(10);
        return creds;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier);
    }
}
