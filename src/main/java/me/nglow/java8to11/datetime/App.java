package me.nglow.java8to11.datetime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 기존 날짜를 사용하는 객체는 사용하는데 불편했다
         */
        var date = new Date();
        var calendar = new GregorianCalendar();
        var dateFormat = new SimpleDateFormat();

        // 이름 작명이 제대로 되어있지 않음 -> Date가 실질적으로는 timestamp 임
        System.out.println(date);
        System.out.println(date.getTime()); // 에폭타임 기준

        // Mutable한 객체임 -> Multi Thread환경에서 안전하게 쓰기 어려움 -> Thread Safe하지 않음
//        Thread.sleep(1000 * 3);
        var after3Seconds = new Date();
        System.out.println(after3Seconds);
        after3Seconds.setTime(date.getTime());
        System.out.println(after3Seconds);

        var birthDay = new GregorianCalendar(1990, Calendar.MARCH, 17);
        System.out.println(birthDay.getTime());
        birthDay.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(birthDay.getTime());

        /**
         * 새로운 Date/Time API
         */
        // 기계용 시간
        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT
        System.out.println(instant.atZone(ZoneId.of("UTC")));

        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);
        var zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        // 인류용 시간
        var now = LocalDateTime.now();
        System.out.println(now);
        var of = LocalDateTime.of(1990, Month.MARCH, 17, 0, 0, 0);
        System.out.println(of);
        var nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        // Period (인류용 시간을 이용해 비교)
        var today = LocalDate.now();
        var thisYearBirthday = LocalDate.of(2021, Month.MARCH, 17);
        var period = Period.between(today, thisYearBirthday);
        System.out.println(period);
        System.out.println(period.getDays()); // 날짜로변환안됨...

        // Duration (기계용 시간을 이용해 비교)
        var now1 = Instant.now();
        var plus = now.plus(10, ChronoUnit.SECONDS);
        var plus1 = now.plus(22, ChronoUnit.DAYS);
        var between = Duration.between(now, plus);
        var between1 = Duration.between(now, plus1);
        System.out.println(between);
        System.out.println(between1);

        // formatting, parsing
        var now2 = LocalDateTime.now();
        var dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        var datetimeString = now.format(dateTimeFormatter);
        System.out.println(datetimeString);

        var parsed = LocalDate.parse("07/15/1982", dateTimeFormatter);
        System.out.println(parsed);

        // 예전 API와의 변환
        var date1 = new Date();
        Instant instant1 = date1.toInstant();
        var newDate1 = Date.from(instant1);

        var gregorianCalendar = new GregorianCalendar();
        var localDateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);

        var zoneId = TimeZone.getTimeZone("PST").toZoneId();
        var timeZone = TimeZone.getTimeZone(zoneId);
    }
}
