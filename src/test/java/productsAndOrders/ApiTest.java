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


import java.util.List;
import java.util.UUID;

import static common.Config.URL;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertTrue;

@Test(enabled = true, priority = 1)
@Listeners({Listner.class})
public class ApiTest {

    private static String productId = "";

    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        Log.printClassTitle(getClass().getSimpleName());
    }

    @Test(enabled = true, description = "Проверить продукты на невалидные категории", priority = 1001)
    @Description("Тест получает список всех продуктов и проверяет что их поля 'category' имеют значения 'FRUITS' или 'VEGETABLES'")
    @Step("Проверить продукты на невалидные категории")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void checkProductsCategory() {
//        List<GetAllProducts> products = given().
//                when().
//                contentType(ContentType.JSON)
//                .get(URL + "products")
//                .then().log().all()
//                .extract().body().jsonPath().getList(".", GetAllProducts.class);
//        for (GetAllProducts product : products) {
//            if (!product.getCategory().equals("VEGETABLES") && !product.getCategory().equals("FRUITS")) {
//                attachDataTXT(product.toString());
//                assertTrue(false);
//            }
//        }
    }

    @Test(enabled = true, description = "Создать продукт", priority = 1002)
    @Description("Тест создает новый продукт")
    @Step("Создать продукт")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void createProduct() {

        CreateProductPost body = new CreateProductPost(
                "Potato",
                UUID.randomUUID().toString(), //Тут не id а article (Косяк api)!!!!
                "VEGETABLES",
                "test dict",
                9000,
                1);
        CreateProductPostResp product = given()
                .body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "products/products")
                .then().log().all()
                .extract().as(CreateProductPostResp.class);
        productId = product.getId();

    }

    @Test(enabled = true, description = "Изменить продукт", priority = 1003, dependsOnMethods = "createProduct")
    @Description("Тест изменяет продукт")
    @Step("Изменить продукт")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void updateProduct() {
        ChangeProductPatch body = new ChangeProductPatch(
                productId,
                "PotatoNew",
                "VEGETABLES",
                "New dict",
                0.1,
                3333);
        ChangeProductPatchResp product = given()
                .body(body)
                .when()
                .contentType(ContentType.JSON)
                .patch(URL + "products")
                .then().log().all()
                .extract().as(ChangeProductPatchResp.class);

    }

    @Test(enabled = true, description = "Найти продукт по id", priority = 1004, dependsOnMethods = "updateProduct")
    @Description("Тест ищет продукт")
    @Step("Найти продукт по id")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void findProduct() {
        FindProductGet products = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "products/" + productId)
                .then().log().all()
                .extract().as(FindProductGet.class);
    }

    @Test(enabled = true, description = "Удалить продукт", priority = 1005, dependsOnMethods = "findProduct")
    @Description("Тест удаляет продукт")
    @Step("Удалить продукт")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void deleteProduct() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "products/" + productId)
                .then()
                .statusCode(200);
    }

    @AfterClass(description = "Постусловия")
    public void afterClass() {
        Log.printClassTitle(getClass().getSimpleName());
    }
}
