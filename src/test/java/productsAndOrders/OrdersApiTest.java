package productsAndOrders;

import api.*;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.Listner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

import static common.Config.URL;
import static io.qameta.allure.SeverityLevel.*;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

@Test(enabled = true, priority = 2)
@Listeners({Listner.class})
public class OrdersApiTest {

    private final int testUserID = 123;
    private String orderID = "";
    private String productId = "";


    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        Log.printClassTitle(getClass().getSimpleName());
        Allure.step("Проверить наличие тестового пользователя", step2 -> {
            Properties props = new Properties();
            props.setProperty("user", "postgres_user");
            props.setProperty("password", "postgres_password");
            try {
                Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres_db", props).createStatement();
                ResultSet rs = statement.executeQuery("SELECT EXISTS (SELECT id FROM customer WHERE id = " + testUserID + ")");
                rs.next();
                if (rs.getString(1).equals("f")) {
                    statement.executeUpdate("INSERT INTO customer (id, login, email,is_active) VALUES (" + testUserID + ", 'bob', 'bob@mail.ru', 'true')");
                    Log.println("Тестовый пользователь не найден. Тестовый пользователь создан.");
                }
            } catch (SQLException e) {
                Log.println(e.getMessage());
                throw new RuntimeException(e);
            }
        });
        Allure.step("Создать тестовый продукт", step2 -> {
            CreateProductPost body = new CreateProductPost(
                    "TestProd",
                    UUID.randomUUID().toString(),
                    "VEGETABLES",
                    "TestProd",
                    10.1,
                    99999);
            CreateProductPostResp product = given()
                    .body(body)
                    .when()
                    .contentType(ContentType.JSON)
                    .post(URL + "products/products")
                    .then().log().all()
                    .extract().as(CreateProductPostResp.class);
            productId = product.getId();
        });

    }

    @Test(enabled = true, description = "Создать заказ", priority = 2002)
    @Description("Тест создает новый заказ")
    @Step("Создать заказ")
    @Severity(BLOCKER)
    @Feature("Smoke тесты")
    @Story("Контроллер создания заказов")
    @AllureId("TC_006")
    public void createOrder() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(productId, 2));
        CreateOrderPost body = new CreateOrderPost(
                "test text",
                products);
        String s = given()
                .body(body)
                .header("customer_id", 123)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "order")
                .then().log().all()
                .extract().response().asString();
        assertFalse(s.isEmpty());
        orderID = s.replace("\"", "");
    }


    @Test(enabled = true, description = "Изменить заказ", priority = 2003, dependsOnMethods = "createOrder")
    @Description("Тест изменяет заказ")
    @Step("Изменить заказ")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания заказов")
    @AllureId("TC_007")
    public void updateOrder() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(productId, 3));
        ChangeOrderPatch body = new ChangeOrderPatch(products);
        given()
                .body(body)
                .header("customer_id", 123)
                .when()
                .contentType(ContentType.JSON)
                .patch(URL + "order/" + orderID)
                .then().log().all().statusCode(204);
    }

    @Test(enabled = true, description = "Найти заказ по id", priority = 2004, dependsOnMethods = "createOrder")
    @Description("Тест ищет заказ")
    @Step("Найти заказ по id")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания заказов")
    @AllureId("TC_008")
    public void findOrder() {
        Response order = given()
                .header("customer_id", 123)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "order/" + orderID);
        order.then().log().all();
        assertNotNull(order);
    }

    @Test(enabled = true, description = "Удалить заказ", priority = 2005, dependsOnMethods = "createOrder")
    @Description("Тест удаляет заказ")
    @Step("Удалить заказ")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания заказов")
    @AllureId("TC_009")
    public void deleteOrder() {
        given()
                .header("customer_id", 123)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "order/" + orderID)
                .then()
                .statusCode(200);
    }

    @AfterClass(description = "Постусловия")
    public void afterClass() {
        Log.printClassTitle(getClass().getSimpleName());
        Allure.step("Удалить тестовый продукт", step2 -> {
            given()
                    .when()
                    .contentType(ContentType.JSON)
                    .delete(URL + "products/" + productId)
                    .then()
                    .statusCode(200);
        });
    }
}
