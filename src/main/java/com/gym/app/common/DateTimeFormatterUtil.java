package com.gym.app.common;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;

/**
 * Created by Manjunath Reddy
 */
public class DateTimeFormatterUtil {

    private DateTimeFormatterUtil() {
    }

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd"); //Default Date Time Formatter

    public static Timestamp getCurrentTimestampInUTC() {
        return new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis());
    }

    public static Timestamp getOneHourBackTimestampFromNowInUTC() {
        return new Timestamp(DateTime.now(DateTimeZone.UTC).minusHours(Constants.EXPIRY_HOURS_PACK_CALL).getMillis());
    }

    public static Timestamp convertDatTimeToTimestamp(DateTime dateTime) {
        return new Timestamp(dateTime.getMillis());
    }

    public static DateTime convertTimestampToDatTime(Timestamp timestamp) {
        if(timestamp != null) {
            return new DateTime(timestamp);
        }
        return null;
    }

    public static DateTime getCurrentDateTimeInUTC() {
        return DateTime.now(DateTimeZone.UTC);
    }

    public static DateTime getLastXMonthsUTCDateTime(int lastXMonths) {
        return DateTime.now(DateTimeZone.UTC).minusMonths(lastXMonths).withTimeAtStartOfDay();
    }

    public static Timestamp getLastXMonthsUTCTimeStamp(int lastXMonths) {
        return new Timestamp(getLastXMonthsUTCDateTime(lastXMonths).getMillis());
    }

    public static String calculateDateDifferenceInMonthsByNow(Timestamp createdTs) {
        int monthsInDifference = Months.monthsBetween(new DateTime(createdTs), DateTimeFormatterUtil.getCurrentDateTimeInUTC()).getMonths();
        return monthsInDifference + Constants.DELIMITER_SPACE + Constants.UNITS_MONTHS;
    }

    public static int calculateDateDifferenceInHoursByNow(Timestamp createdTs) {
        int hoursInDifference = Hours.hoursBetween(new DateTime(createdTs), DateTimeFormatterUtil.getCurrentDateTimeInUTC()).getHours();
        return hoursInDifference;
    }

    public static int calculateDateDifferenceInDaysByNow(Timestamp createdTs) {
        int daysInDifference = Days.daysBetween(new DateTime(createdTs), DateTimeFormatterUtil.getCurrentDateTimeInUTC()).getDays();
        return daysInDifference;
    }

    public static int calculateDateDifferenceInWeeksByNow(Timestamp createdTs) {
        int weeksInDifference = Weeks.weeksBetween(new DateTime(createdTs), DateTimeFormatterUtil.getCurrentDateTimeInUTC()).getWeeks();
        return weeksInDifference;
    }
}
