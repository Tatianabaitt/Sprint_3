package SamokatAPI.courier;

import SamokatAPI.RestAssuredSamokat;

public class CourierClient extends RestAssuredSamokat {

    private final String ROOT = "/courier";
    private final String LOGIN_URL = ROOT + "/login/";
    private final String COURIER = ROOT + "/{courierId}/";

    public boolean create(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    public int login(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    public void delete(int courierId) {
        reqSpec.pathParams("courierId", courierId)
                .when()
                .delete(COURIER)
                .then()
                .assertThat()
                .statusCode(200);
    }

    public String createDublicateCourier(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    public String createCourierWithoutRequiredParameter(Courier courier) {
        return reqSpec
                .body(courier)
                .when()
                .post(ROOT)
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public String loginWithoutLogin(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL)
                .then()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    public String loginWithWrongCreds(CourierCredentials creds) {
        return reqSpec
                .body(creds)
                .when()
                .post(LOGIN_URL)
                .then()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");
    }
}
