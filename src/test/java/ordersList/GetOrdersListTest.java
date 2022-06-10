package ordersList;

import SamokatAPI.order.Order;
import SamokatAPI.order.OrderClient;
import lombok.SneakyThrows;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;

import java.util.List;

import static org.junit.Assert.*;


public class GetOrdersListTest {

    private OrderClient orderCLient;

    private int orderTrack1;
    private int orderTrack2;

    @Before
    public void setup() {
        orderCLient = new OrderClient();
    }

    @After
    public void teardown() {
        orderCLient.cancelOrder(orderTrack1);
        orderCLient.cancelOrder(orderTrack2);
    }

    @Test
    @DisplayName("Проверка успешного получения списка заказов")
    public void getOrdersListSuccess() {
        String message = "При получении списка заказов получен пустой список";
        Order order = Order.getRandomWithChooseColor(List.of("BLACK"));
        System.out.println(order);
        orderCLient.getNumberMetro(order);
        System.out.println(order);
        orderTrack1 = orderCLient.createOrder(order);
        System.out.println(orderTrack1);
        orderTrack2 = orderCLient.createOrder(order);
        System.out.println(orderTrack2);
        List<String> orderList = orderCLient.getOrdersList();
        assertTrue(message, orderList.size() > 1);
    }
}
