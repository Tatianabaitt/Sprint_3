package ordersCreate;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import SamokatAPI.order.Order;
import SamokatAPI.order.OrderClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> color;
    private OrderClient orderCLient;
    private int orderTrack;

    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getOrder() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {null},
        };
    }

    @Before
    public void setup() {
        orderCLient = new OrderClient();
    }

    @After
    public void teardown() {
        orderCLient.cancelOrder(orderTrack);
    }

    @Test
    @DisplayName("Проверка успешного создания заказа со всеми полями")
    public void orderCreateSuccess() {
        String message = "При попытке создания заказа со всеми параметрами не получен корретный трек номер заказа";
        Order order = Order.getRandomWithChooseColor(color);
        orderCLient.getNumberMetro(order);
        orderTrack = orderCLient.createOrder(order);
        orderCLient.getOrder(orderTrack);
        assertNotEquals(message, 0, orderTrack);
    }
}
