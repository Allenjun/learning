package com.allen.learningbasicsjava8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import org.junit.Test;

public class TimeTests {
    
    /**
     * Instant：时间戳
     * Duration：持续时间，时间差
     * LocalDate：只包含日期，比如：2016-10-20
     * LocalTime：只包含时间，比如：23:12:10
     * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
     * Period：时间段
     * ZoneOffset：时区偏移量，比如：+8:00
     * ZonedDateTime：带时区的时间
     * Clock：时钟，比如获取目前美国纽约的时间
     * DateTimeFormatter：时间格式化
     *
     */
    
    @Test
    public void test1() {
        /* 获取当前日期 */
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        /* 自定义日期 */
        LocalDate of = LocalDate.of(2019, 6, 23);
        System.out.println(of);
        /* 日期解析 */
        LocalDate parse = LocalDate.parse("2019-07-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(parse);
        /* TODO */
        LocalDate from = LocalDate.from(of);
        System.out.println(from);
        /* 格式化日期 */
        String format = localDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(format);
        /* 获取今天是今年的第几天 */
        int i = localDate.get(ChronoField.DAY_OF_YEAR);
        System.out.println(i);
        /* 日期结合时间 ->  LocalDateTime */
        LocalDateTime localDateTime = localDate.atTime(LocalTime.now());
        System.out.println(localDateTime);
        /* 获取年表 */
        IsoChronology chronology = localDate.getChronology();
        System.out.println(chronology);
        /* 日期的平移 */
        LocalDate plus = localDate.plus(1, ChronoUnit.DAYS);
        System.out.println(plus);
        /* 返回TemporalQuery接口中的结果 */
        Boolean query = localDate.query(temporal -> temporal.get(ChronoField.DAY_OF_YEAR) == 200);
        System.out.println(query);
        /* 比较两个日期之间的周期 */
        Period until = localDate.until(of);
        System.out.println(until.getMonths());
        /* 获取当期日期所在的时间范围 */
        ValueRange range = localDate.range(ChronoField.DAY_OF_YEAR);
        System.out.println(range);
        /* 修改字段的值 */
        LocalDate with = localDate.with(ChronoField.DAY_OF_MONTH, 15);
        System.out.println(with);
        
    }
}
