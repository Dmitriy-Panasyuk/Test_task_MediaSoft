package common;

import java.io.File;
import java.time.Duration;

public class Config {
    public static String URL = "http://localhost:8080/";

    public static String WIN_ALLURE_RESULTS_PATH = (new File(CommonActions.class.getProtectionDomain().getCodeSource().getLocation().getFile())).getParent().replace("\\target","")+ "\\allure-results";
    public static String WIN_ALLURE_HTML_PATH = WIN_ALLURE_RESULTS_PATH + "\\ReportHTML";
    public static String WIN_ALLURE_REPORT_PATH = "C:\\Program Files\\allure-2.30.0\\bin\\allure";
}
