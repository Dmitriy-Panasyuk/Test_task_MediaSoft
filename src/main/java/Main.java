import utils.Log;

import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

public class Main {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("user", "postgres_user");
        props.setProperty("password", "postgres_password");

        try {
            Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres_db", props).createStatement();
            printDBTab(statement, "Select * FROM customer");
            initDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres_db", props).createStatement();
                statement.executeQuery("DELETE FROM customer WHERE id=12345");
                Log.println("Delete test user", "y");
            } catch (SQLException e) {
            }
        }

    }

    private static void initDB() {
        Properties props = new Properties();
        props.setProperty("user", "postgres_user");
        props.setProperty("password", "postgres_password");
        try {
            Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres_db", props).createStatement();
            Log.println("create", "g");
            statement.executeUpdate("INSERT INTO customer (id, login, email, is_active) VALUES (12345, 'bob', 'bob@mail.ru', 'true')");
            Log.println("---------", "g");
            ResultSet rs = statement.executeQuery("Select * FROM customer");
            if (rs.isLast()) Log.println("last", "g");
        } catch (SQLException e) {
            Log.println("Create fail", "r");
            Log.println(e.getMessage(), "r");

            try {
                Statement statement = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres_db", props).createStatement();
                printDBTab(statement, "Select * FROM customer ");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void printDBTab(Statement statement, String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int column_count = rsmd.getColumnCount();
        int[] colLenghts = new int[column_count];
        ArrayList<String[]> table = new ArrayList<>();
        String[] row = new String[column_count];
        for (int i = 1; i <= column_count; i++) {
            row[i - 1] = rs.getMetaData().getColumnName(i);
            if (row[i - 1].length() > colLenghts[i - 1]) {
                colLenghts[i - 1] = row[i - 1].length();
            }
        }
        table.add(row);
        while (rs.next()) {
            row = new String[column_count];
            for (int i = 1; i <= column_count; i++) {
                row[i - 1] = rs.getString(i);
                if (row[i - 1] != null) {
                    if (row[i - 1].length() > colLenghts[i - 1]) {
                        colLenghts[i - 1] = row[i - 1].length();
                    }
                }

            }
            table.add(row);
        }
        int sum = 0;
        for (int i : colLenghts) {
            sum += i;
        }
        Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
        for (int i = 0; i < table.size(); i++) {
            for (int j = 0; j < table.get(i).length; j++) {
                if (table.get(i)[j] != null) {
                    Log.print("| " + table.get(i)[j] + " ".repeat(colLenghts[j] - table.get(i)[j].length() + 1));
                } else {
                    Log.print("| " + " ".repeat(colLenghts[j] + 1));
                }
            }
            Log.print("|\n");
            if (i == 0) {
                Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
            }
        }
        Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
        rs.close();
    }
}