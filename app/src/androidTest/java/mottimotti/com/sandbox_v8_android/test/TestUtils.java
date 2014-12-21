package mottimotti.com.sandbox_v8_android.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class TestUtils {
    public static String getJson(String name) {
        InputStream is = TestUtils.getInstance().getClass().getResourceAsStream("/" + name + ".json");
        Scanner scanner = new Scanner(is).useDelimiter("\\A");

        try {
            return scanner.hasNext() ? scanner.next() : "";
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private static TestUtils getInstance() {
        return new TestUtils();
    }
}