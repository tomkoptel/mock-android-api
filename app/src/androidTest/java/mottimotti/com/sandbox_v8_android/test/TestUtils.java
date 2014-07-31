package mottimotti.com.sandbox_v8_android.test;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class TestUtils {
    public static String getJson(String name) {
        try {
            return IOUtils.toString(
                    TestUtils.getInstance().getClass().getResourceAsStream("/" + name + ".json"),
                    "UTF-8"
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static TestUtils getInstance() {
        return new TestUtils();
    }
}