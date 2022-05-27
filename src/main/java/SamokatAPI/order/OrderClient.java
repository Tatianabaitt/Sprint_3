package SamokatAPI.order;


import SamokatAPI.RestAssuredSamokat;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class OrderClient extends RestAssuredSamokat {

    private final String ORDER = "/orders";
    private final String SERACH = "/stations/search";
    private final String GET_ORDER = ORDER + "/track";
    private final String CANCEL_ORDER = ORDER + "/cancel";

    public void getNumberMetro(Order order) {
        List<String> numberMetro = given()
                .header("Content-type", "application/json")
                .baseUri(URL)
                .when()
                .queryParam("s", order.getMetroStation())
                .get(SERACH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("number");
        String number = numberMetro.get(0);
        order.setMetroStation(number);
    }

    public int createOrder(Order order) {
        return reqSpec
                .body(order)
                .when()
                .post(ORDER)
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }

    public void cancelOrder(int track) {
        reqSpec.when()
                .queryParam("track", track)
                .put(CANCEL_ORDER)
                .then()
                .assertThat()
                .statusCode(200);
    }

    public void getOrder(int track) {
        reqSpec.when()
                .queryParam("t", track)
                .get(GET_ORDER)
                .then()
                .assertThat()
                .statusCode(200)
                .body("order.track", equalTo(track));
    }

    public List<String> getOrdersList() {
        return reqSpec
                .when()
                .get(ORDER)
                .then()
                .assertThat()
                .statusCode(200)
                .body("orders", notNullValue())
                .extract()
                .path("orders");
    }
}
