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

import static org.testng.Assert.*;
import static utils.AllureAttachmentTools.attachDataTXT;

@Test(enabled = true, priority = 1)
@Listeners({Listner.class})
public class WarehouseApiTest {

    private static String productId = "";

    @BeforeClass(description = "Предусловия")
    public void beforeClass() {
        Log.printClassTitle(getClass().getSimpleName());
    }

    @Test(enabled = true, description = "Создать продукт", priority = 1001)
    @Description("Тест создает новый продукт")
    @Step("Создать продукт")
    @Severity(CRITICAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void createProduct() {
        CreateProductPost body = new CreateProductPost(
                "Potato",
                UUID.randomUUID().toString(),
                "VEGETABLES",
                "test dict",
                9000.0,
                // TODO После изменения qty товара не изменяется время last_qty_changed
                11);
        CreateProductPostResp product = given()
                .body(body)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "products/products")
                .then().log().all()
                .extract().as(CreateProductPostResp.class);
        productId = product.getId();
        assertEquals(body.getName(),product.getName());
        assertEquals(body.getArticle(),product.getArticle());
        assertEquals(body.getCategory(),product.getCategory());
        assertEquals(body.getPrice(),product.getPrice());
        assertEquals(body.getQty(),product.getQty());
    }

    @Test(enabled = true, description = "Проверить продукты на невалидные категории", priority = 1002)
    @Description("Тест получает список всех продуктов и проверяет что их поля 'category' имеют значения 'FRUITS' или 'VEGETABLES'")
    @Step("Проверить продукты на невалидные категории")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void checkProductsCategory() {
        List<AllProductsGet> products = given().
                when().
                contentType(ContentType.JSON)
                .get(URL + "products")
                .then().log().all()
                .extract().body().jsonPath().getList(".", AllProductsGet.class);
        for (AllProductsGet product : products) {
            if (!product.getCategory().equals("VEGETABLES") && !product.getCategory().equals("FRUITS")) {
                attachDataTXT(product.toString());
                assertTrue(false);
            }
        }
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
                // TODO Уточнить необходимость параметра dictionary
                "New dict",
                0.01,
                22);
        ChangeProductPatchResp product = given()
                .body(body)
                .when()
                .contentType(ContentType.JSON)
                .patch(URL + "products")
                .then().log().all()
                .extract().as(ChangeProductPatchResp.class);
        assertEquals(body.getName(),product.getName());
        assertEquals(body.getCategory(),product.getCategory());
        assertEquals(body.getPrice(),product.getPrice());
        assertEquals(body.getQty(),product.getQty());
    }

    @Test(enabled = true, description = "Найти продукт по id", priority = 1004, dependsOnMethods = "createProduct")
    @Description("Тест ищет продукт")
    @Step("Найти продукт по id")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void findProduct() {
        Response product = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "products/" + productId);
        product.then().log().all();
        assertNotNull(product);
    }

    @Test(enabled = true, description = "Удалить продукт", priority = 1005, dependsOnMethods = "createProduct")
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
