package ar.com.leogaray.hiring.common.utils.converters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ISODateConverter {
    private ISODateConverter() {
    }

    public static LocalDate toLocalDate(String isoString) {
        return LocalDate.parse(isoString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static LocalDateTime toLocalDateTime(String isoString) {
        return LocalDateTime.parse(isoString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static String toString(LocalDate localDate) {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
    }

    public static String toString(LocalDateTime localDateTime) {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
    }

    public static LocalTime toLocalTime(String startTime) {
        return LocalTime.parse(startTime, DateTimeFormatter.ISO_LOCAL_TIME);

    }
}
