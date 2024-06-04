package com.oatss.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class ValidUtils {
    public static final String DATA_TIME_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_TIME_PATTERN2 = "yyyy-M-d HH:mm:ss";
    private ValidUtils() {

    }
    public static boolean isValidText(String... arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null || arr[i].isEmpty() == true || arr[i].isBlank() == true) {
                return false;
            }
        }
        return true;
    }
    public static boolean isValidDateTime(String str, String pattern) {
        try {
            LocalDateTime.parse(str, DateTimeFormatter.ofPattern(pattern));
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }
    public static boolean isValidPrice(String str) {
        try {
            Integer.parseInt(str);
            Double.parseDouble(str);
        }
        catch (Exception exc) {
            return false;
        }
        return true;
    }
}