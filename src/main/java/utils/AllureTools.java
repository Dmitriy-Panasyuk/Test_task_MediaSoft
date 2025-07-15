package utils;

import java.io.*;

import static common.Config.*;

public class AllureTools {
    private static final String BAT_NAME = "RunAllure.bat";

    public static void writeAllureRunBAT() throws IOException {
        File file = createFile(WIN_ALLURE_RESULTS_PATH, BAT_NAME);
        FileOutputStream fos = new FileOutputStream(file);
        String s = "allure generate --single-file " + WIN_ALLURE_RESULTS_PATH + " --report-dir " + WIN_ALLURE_HTML_PATH;
        byte[] buffer = s.getBytes();
        fos.write(buffer, 0, buffer.length);
        fos.close();
        Log.println("HTML path " + WIN_ALLURE_HTML_PATH, "c");
    }

    public static void writeTempFile(String path) throws IOException {
        InputStream inputStream = AllureTools.class.getResourceAsStream("/" + path);
        File file = createFile(WIN_ALLURE_RESULTS_PATH + "/temp", path);
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = inputStream.readAllBytes();
        fos.write(buffer, 0, buffer.length);
        fos.close();
    }

    public static File createFile(String path, String fName) {
        File fLog = new File(path, fName);
        return fLog;
    }

    public static void createResultDir() {
        File file = new File(WIN_ALLURE_RESULTS_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(WIN_ALLURE_HTML_PATH);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void createTemptDir() {
        File file = new File(WIN_ALLURE_RESULTS_PATH + "/temp");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static void deleteResultDir() {
        deleteDirectory(WIN_ALLURE_RESULTS_PATH);
    }

    public static void deleteTemptDir() {
        deleteDirectory(WIN_ALLURE_RESULTS_PATH + "/temp");
    }

    public static void deleteDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        File[] contents = directory.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file.getPath());
            }
        }
        directory.delete();
    }

    public static void createHTML() throws IOException {
        Log.println("==== Create HTML report", "c");
        writeAllureRunBAT();
        Runtime.getRuntime().exec("cmd /c " + WIN_ALLURE_RESULTS_PATH + "\\" + BAT_NAME);
        Log.println("==== Create HTML report Success\n", "c");
    }
}
