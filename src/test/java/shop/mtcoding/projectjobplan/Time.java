package shop.mtcoding.projectjobplan;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    @Test
    public void time(){
        String time = "2024-03-04 00:00:00";

        String dateTime = LocalDateTime
                .parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("dateTime: " + dateTime);
    }
}
