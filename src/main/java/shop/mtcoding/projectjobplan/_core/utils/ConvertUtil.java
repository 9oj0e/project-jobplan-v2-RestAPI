package shop.mtcoding.projectjobplan._core.utils;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ConvertUtil {
    public static Timestamp timestampConverter(String date) {
        LocalDate localDate = LocalDate.parse(date);

        return Timestamp.valueOf(localDate.atStartOfDay());
    }
}
