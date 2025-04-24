package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//11.11.2001.20:23
public class DateHelper {
	
    public static DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
    public static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
     
    public static String dateToString(LocalDate date) {
        String formated = date.format(formatDate);
        return formated;
    }
     
    public static LocalDate stringToDate(String datumIzdavanja) {
        LocalDate formated = LocalDate.parse(datumIzdavanja, formatDate);    
        return formated;
    }
    
    
    public static String dateTimeToString(LocalDateTime dateTime) {
        String formated = dateTime.format(formatDateTime);
        return formated;
    }
    
    public static LocalDateTime stringToDateTime(String dateTime) {
        LocalDateTime formated = LocalDateTime.parse(dateTime, formatDateTime);
        return formated;
    }
    
}
