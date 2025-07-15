package demoqa;

import io.qameta.allure.*;
import io.restassured.specification.RequestSpecification;
import listeners.Listner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import static io.qameta.allure.SeverityLevel.NORMAL;
import static io.restassured.RestAssured.given;


@Test(enabled = true, priority = 1)
@Listeners({Listner.class})
public class HomePageTest {


    @Test(enabled = true, description = "", priority = 2003)
    @Description("")
    @Step("")
    @Severity(NORMAL)
    @Feature("Smoke тесты")
    @Story("")
    public void login() {
        RequestSpecification request = given();
    }


}
