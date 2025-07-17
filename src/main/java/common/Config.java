package common;

import java.io.File;
import java.time.Duration;

public class Config {
    public static String URL = "http://localhost:8080/";

    public static String WIN_ALLURE_RESULTS_PATH = (new File(Config.class.getProtectionDomain().getCodeSource().getLocation().getFile())).getParent().replace("\\target","")+ "\\allure-results";
    public static String WIN_ALLURE_HTML_PATH = WIN_ALLURE_RESULTS_PATH + "\\ReportHTML";
}
