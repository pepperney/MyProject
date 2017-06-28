package cn.com.learn.jdk8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 测试java8 新的时间api
 * @Description
 * @author niepei
 * @date 2016年12月10日 下午2:40:17
 * @version V1.3.1
 */
public class LearnNewDateTimeAPI {

	public static void main(String args[]) {
		// 获取当天的时间
		LocalDate today = LocalDate.now();

		// 获取制定日期中的年月日
		int year = today.getYear();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		System.out.printf("今天是 %d 年  %d 月  %d 日\n", year, month, day);

		// 根据指定的年月日生成LocalDate实例
		LocalDate now = LocalDate.of(2016, 12, 10);

		// 日期的比较
		if (now.equals(today)) {
			System.out.println(now + " and " + today + " are same date ");
		}
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		if (tomorrow.isAfter(today)) {
			System.out.println("today is : " + today + " , tomorrow comes after today");
		}
		LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
		if (yesterday.isBefore(today)) {
			System.out.println("today is : " + today + " , yesterday is day before today");
		}

		// 日期的增加--ChronoUnit.WEEKS指定增加的时间单位
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("today is : " + today + " , after 1 week is : " + nextWeek);
		LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
		System.out.println("today is : " + today + " , before 1 year is : " + previousYear);

		// 判断日期是否节日，可以使用MonthDay类，这个类由月份和日期组成，可以用来检查每一年重复出现的日期
		MonthDay birthday = MonthDay.of(8, 6);
		LocalDate specialDay = LocalDate.of(2016, 8, 6);
		MonthDay specialDate = MonthDay.from(specialDay);
		if (specialDate.equals(birthday)) {
			System.out.println("nulo's birthday is : " + birthday + " ,Let's say happy birthday to her");
		} else {
			System.out.println("It is not her bitthday");
		}

		// 判断日期是否为固定的年月，例如信用卡还款时间
		YearMonth currentYearMonth = YearMonth.now();
		YearMonth creditCardExpiry = YearMonth.of(2018, Month.DECEMBER);
		System.out.println("current year and month is : " + currentYearMonth + " and your credit card expiry is : "	+ creditCardExpiry);

		// 判断当前年份是否闰年
		System.out.println(LocalDate.now().isLeapYear());

		// 获取当前时间
		LocalTime time = LocalTime.now();
		System.out.println("current time is : " + time);

		// 计算当前时间之后的时间
		LocalTime newTime = time.plusHours(2);
		System.out.println("current time is : " + time + " , after 2 hours is : " + newTime);
		LocalTime newTime2 = time.plusHours(-2);
		System.out.println("current time is : " + time + " , before 2 hours is : " + newTime2);
		LocalTime newTime3 = time.plusMinutes(10);
		System.out.println("current time is : " + time + " , after 10 minutes is : " + newTime3);

		// 获取时间戳
		Instant timestamp = Instant.now();
		System.out.println("the timestamp of current time is " + timestamp);

		// 日期时间格式化 -- 使用默认的格式
		String date = "19930806";
		LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println("the formattedDate is " + formattedDate);
		// 日期时间格式化 -- 指定格式
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd  hh:mm:ss");
		String currentTimeStr = currentTime.format(format);
		System.out.println("currentTime is : " + currentTimeStr);

		// 计算两个日期之间包含多少年，多少月，多少天
		Period periods = Period.between( LocalDate.of(1993, Month.AUGUST, 6),today);
		System.out.println("age is " + periods.getYears());
		
	}

}
