package org.dromara.common.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgeRangeCalculator {

    // 静态常量：从20岁开始，每5年一个段的年龄段列表
    public static final List<AgeRange> FIVE_YEAR_RANGES_FROM_20;
    private static final String BELOW_MIN_LABEL = "20岁以下";

    static {
        List<AgeRange> ranges = new ArrayList<>();
        int startAge = 20;
        int interval = 5;
        int maxRegularAge = 70;

        for (int i = startAge; i <= maxRegularAge; i += interval) {
            ranges.add(new AgeRange(i, i + interval - 1, i + "-" + (i + interval - 1) + "岁"));
        }

        ranges.add(new AgeRange(maxRegularAge + interval, Integer.MAX_VALUE, "75岁以上"));
        FIVE_YEAR_RANGES_FROM_20 = List.copyOf(ranges); // 不可变列表
    }

    /**
     * 年龄段定义类
     */
    public static class AgeRange {
        private final int minAge;
        private final int maxAge;
        private final String label;

        public AgeRange(int minAge, int maxAge, String label) {
            this.minAge = minAge;
            this.maxAge = maxAge;
            this.label = label;
        }

        public boolean isInRange(int age) {
            return age >= minAge && age <= maxAge;
        }

        public String getLabel() {
            return label;
        }
    }

    // ==================== 日期类型支持 ====================

    /**
     * 根据 Date 类型的出生日期计算年龄段
     */
    public static String calculateAgeRange(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("出生日期不能为空");
        }
        LocalDate localDate = convertToLocalDate(birthDate);
        return calculateAgeRange(localDate);
    }

    /**
     * 根据 LocalDate 计算年龄段
     */
    public static String calculateAgeRange(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("出生日期不能为空");
        }
        int age = calculateAge(birthDate);

        if (age < 20) {
            return BELOW_MIN_LABEL;
        }

        // 直接使用静态常量列表，无需重新生成
        for (AgeRange range : FIVE_YEAR_RANGES_FROM_20) {
            if (range.isInRange(age)) {
                return range.getLabel();
            }
        }

        return FIVE_YEAR_RANGES_FROM_20.get(FIVE_YEAR_RANGES_FROM_20.size() - 1).getLabel();
    }

    /**
     * 根据日期字符串计算年龄段
     */
    public static String calculateAgeRange(String birthDateStr) {
        return calculateAgeRange(parseDate(birthDateStr));
    }

    // ==================== 辅助方法 ====================

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
    }

    private static int calculateAge(LocalDate birthDate) {
        LocalDate now = LocalDate.now();
        if (birthDate.isAfter(now)) {
            throw new IllegalArgumentException("出生日期不能晚于当前日期");
        }
        return Period.between(birthDate, now).getYears();
    }

    private static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("日期格式错误，应使用yyyy-MM-dd格式", e);
        }
    }
}
