package shop.mtcoding.projectjobplan._core.utils;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class FormatUtil {
    public static String timeFormatter(Timestamp timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String stringFormatter(String input) {
        int maxLength = 8; // 최대 길이

        String output;
        if (input.length() > maxLength) {
            output = input.substring(0, maxLength) + "...";
        } else {
            output = input;
        }
        return output;
    }

    public static Double numberFormatter(Double num) {
        // 소수점 한 자리 rounded down to the nearest tenth
        final int DECIMAL_PLACE = 10;

        return (double) Math.round(num * DECIMAL_PLACE) / DECIMAL_PLACE;
    }
}
