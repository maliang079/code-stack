package com.test.lambda;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;

public class TestTime {

    /**
     * 常用类的概述与功能介绍
     * 1. Instant
     *    Instant类对时间轴上的单一瞬时点建模（时间戳），可以用于记录应用程序中的事件时间，在之后学习的类型转换中，均可以使用Instant作为中间类完成转换
     * 2.
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        // 快速补充变量的快捷键ctrl+alt+v
        Instant instant = Instant.now();
        System.out.println(instant);

        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId);

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);


        // 对时间进行加puls、减minus
        // 使用TemporalAmount该当前时间加三个小时
        // TemporalAmount是一个接口，具体实现类是Duration，因此通过Duration来实现
        LocalDateTime plus3Hours = now.plus(Duration.ofHours(3));
        System.out.println("使用TemporalAmount该当前时间加三个小时的时间: " + plus3Hours);


        // 使用TemporalUnit给当前时间加半天的时间
        // TemporalUnit是一个接口，具体的实现类是ChronoUnit，因此通过ChronoUnit来实现
        LocalDateTime plusHalfDays = now.plus(1, ChronoUnit.HALF_DAYS);
        System.out.println("使用TemporalUnit给当前时间加半天的时间: " + plusHalfDays);

        // with方法用于修改时间
        // 把当前时间直接修改为13时
        LocalDateTime now13Hour = now.withHour(13);
        System.out.println("把当前时间直接修改为13时: " + now13Hour);

        // TemporalField的使用
        // 把当前时间修改到10分
        // TemporalField是一个接口，具体的实现类是ChronoField，因此使用ChronoField来实现
        LocalDateTime with10Minute = now.with(ChronoField.MINUTE_OF_HOUR, 10);
        System.out.println("把当前时间直接修改为10分钟:" + with10Minute);

        // TemporalAdjuster的使用
        // 使用TemporalAdjuster来设置时间，由于TemporalAdjuster是一个接口，TemporalAdjusters中给我们提供了常用的一些实现
        // eg. TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY) 把当前时间修改为当月的第一个星期三
        LocalDateTime withAdjusterFirstInMonth = now.with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));
        System.out.println("把当前时间修改为当月的第一个星期三: " + withAdjusterFirstInMonth);
//        now.with();

        // 使用rang方法和TemporalField获取当前时间，当月的日期范围
        ValueRange rangeDayOfMonth = now.range(ChronoField.DAY_OF_MONTH);
        System.out.println("获取当前时间所在月的取值范围: " + rangeDayOfMonth);

        // TemporalAdjuster的使用
        // 由于TemporalAdjuster是一个接口，因此可以自定义实现
        // 例如我们要实现一个如下的功能，根据传入的日式算发薪日期，规定的发薪日期是每月10日，10日如果正好是周六日的情况下
        // 把日期修改为周一
        LocalDateTime payDay = now.with(temporal -> {
            LocalDateTime day = LocalDateTime.from(temporal);
            // 把时间设置为当月的10日
            LocalDateTime day10 = day.with(ChronoField.DAY_OF_MONTH, 10);
            // 如果10正好是周六或者周日，则把发薪日设置为下周一
            if (day10.getDayOfWeek() == DayOfWeek.SATURDAY || day10.getDayOfWeek() == DayOfWeek.SATURDAY)
                day10 = day10.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            return day10;
        });
        System.out.println("发薪日期：" + payDay);

        // TemporalQuery的使用
        // 假如某人的生日为6月23日，根据当前日期求距离下个生日还有多少天时间
        long days = now.query(temporal -> {
            LocalDateTime day = LocalDateTime.from(temporal);
            LocalDateTime birthday = day.withMonth(6).withDayOfMonth(23);
            if (day.isAfter(birthday)) {
                birthday = birthday.plusYears(1);
            }
            return Duration.between(day, birthday).toDays();
        });

        System.out.println("距离下次生日还有: " + days + "天");

    }



}
