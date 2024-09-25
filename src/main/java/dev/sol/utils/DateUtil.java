package dev.sol.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateUtil {
    private static final String DATE_PATTERN = "MMMM dd, yyyy";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static LocalDate randomize(
            LocalDate startRange,
            LocalDate endRange) {
        Random random = new Random();
        int min = (int) startRange.toEpochDay();
        int max = (int) endRange.toEpochDay();
        long randomDay = min + random.nextInt(max - min);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static String format(LocalDate date) {
        return date == null ? null : DTF.format(date);
    }

    public static LocalDate parse(String dateString) {
        return DTF.parse(dateString, LocalDate::from);
    }

    public static boolean validDate(String dateString) {
        return DateUtil.parse(dateString) != null;
    }
}
