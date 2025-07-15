package productsAndOrders;

import api.GetAllProducts;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import listeners.Listner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.util.List;

import static common.Config.URL;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.given;

import static org.testng.Assert.assertTrue;
import static utils.AllureAttachmentTools.attachDataTXT;

@Test(enabled = true, priority = 1)
@Listeners({Listner.class})
public class ApiTest {


    @Test(enabled = true, description = "Проверить продукты на невалидные категории", priority = 1001)
    @Description("Тест получает список всех продуктов и проверяет что их поля 'category' имеют значения 'FRUITS' или 'VEGETABLES'")
    @Step("Проверить продукты на невалидные категории")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("Контроллер создания товаров")
    public void checkProductsCategory() {
        List<GetAllProducts> products = given().
                when().
                contentType(ContentType.JSON)
                .get(URL + "products")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetAllProducts.class);
        for (GetAllProducts product : products) {
            if (product.getCategory().equals("VEGETABLES") || product.getCategory().equals("FRUITS")) {
                attachDataTXT(product.toString());
                assertTrue(false);
            }
        }
    }

}
