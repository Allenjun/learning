package com.allen.java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateTime8 {
    @Test
    public void test1() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("当前年:" + ldt.getYear());
        System.out.println("当前年份天数:" + ldt.getDayOfYear());
        System.out.println("当前月:" + ldt.getMonthValue());
        System.out.println("当前时:" + ldt.getHour());
        System.out.println("当前分:" + ldt.getMinute());
        System.out.println("当前时间:" + ldt.toString());
    }

    @Test
    public void test2() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("格式化时间: " + ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }

    @Test
    public void test3() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("后5天时间:" + ldt.plusDays(5));
        System.out.println("前5天时间并格式化:" + ldt.minusDays(5).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))); //2018-06-16
        System.out.println("前一个月的时间:" + ldt.minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMM"))); //2018-06-16
        System.out.println("后一个月的时间:" + ldt.plusMonths(1)); //2018-06-16
        System.out.println("指定2099年的当前时间:" + ldt.withYear(2099)); //2099-06-21T15:07:39.506
    }

    @Test
    public void test4() {
        LocalDate ld3 = LocalDate.of(2017, Month.NOVEMBER, 17);
        LocalDate ld4 = LocalDate.of(2018, 02, 11);
    }

    @Test
    public void test5() {
        LocalDate ld = LocalDate.parse("2017-11-17");
        LocalDate ld2 = LocalDate.parse("2018-01-05");
        Period p = Period.between(ld, ld2);
        System.out.println("相差年: " + p.getYears() + " 相差月 :" + p.getMonths() + " 相差天:" + p.getDays());
    }

    @Test
    public void test6() {
        LocalDateTime ldt4 = LocalDateTime.now();
        LocalDateTime ldt5 = ldt4.plusMinutes(10);
        System.out.println("当前时间是否大于:" + ldt4.isAfter(ldt5));
        System.out.println("当前时间是否小于" + ldt4.isBefore(ldt5));
    }

    @Test
    public void test7() {
        Clock clock = Clock.systemUTC();
        System.out.println("当前时间戳 : " + clock.millis());
        Clock clock2 = Clock.system(ZoneId.of("Asia/Shanghai"));
        System.out.println("亚洲上海此时的时间戳:" + clock2.millis());
        Clock clock3 = Clock.system(ZoneId.of("America/New_York"));
        System.out.println("美国纽约此时的时间戳:" + clock3.millis());
    }

    @Test
    public void test8() {
        ZoneId zoneId = ZoneId.of("America/New_York");
        ZonedDateTime dateTime = ZonedDateTime.now(zoneId);
        System.out.println("美国纽约此时的时间 : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        System.out.println("美国纽约此时的时间 和时区: " + dateTime);

    }

}
