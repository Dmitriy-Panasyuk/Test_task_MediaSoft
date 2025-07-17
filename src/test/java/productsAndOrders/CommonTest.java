package productsAndOrders;

import io.qameta.allure.Step;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.Log;

import java.io.IOException;

import static utils.AllureTools.*;

@Test(priority = 0)
public class CommonTest {
    @BeforeSuite
    @Step("До выполнения тестов")
    public void beforeSuite() {
        deleteResultDir();
        createResultDir();
        deleteTemptDir();
        createTemptDir();
    }

    @AfterSuite
    @Step("После выполнения тестов")
    public void afterSuite() throws IOException {
        Log.printFinalTestLog();
        createHTML();
    }
}
