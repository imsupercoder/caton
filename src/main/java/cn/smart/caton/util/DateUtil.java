package cn.smart.caton.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateUtil {

    public static String[] allFormatStrings = new String[] { "yyyy-MM-dd HH:ss", "yyyyMMddHHss", "yyyy-MM-dd HHss",
            "yyyy-MM-dd", "yyyyMMddHHmm" };

    private static final Log logger = LogFactory.getLog(DateUtil.class);
    // 日期
    public static final String YEAR_TO_DAY = "yyyy-MM-dd";
    // 24小时制，精确到秒
    public static final String YEAR_TO_SEC = "yyyy-MM-dd HH:mm:ss";
    // 24小时制，精确到毫秒
    public static final String YEAR_TO_MS = "yyyy-MM-dd HH:mm:ss.SSS";
    // 24小时制，精确到分
    public static final String YEAR_TO_MINUTE = "yyyy-MM-dd HH:mm";

    public static final String MONTH_TO_MINUTE = "MM-dd HH:mm";

    public static final String HOUR_TO_SEC = "HH:mm:ss";

    public static final String YEAR_TO_MS_UN_LINE = "yyyyMMdd HHmmssSSS";

    public static final String YEAR_TO_SEC_UN_LINE = "yyyyMMdd HHmmss";

    public static final String YEAR_TO_MI_UN_LINE = "yyyyMMdd HHmm";

    public static final String YEAR_TO_DAY_UN_LINE = "yyyyMMdd";

    public static final String YEAR_TO_MS_NO_BLANK = "yyyyMMddHHmmssSSS";

    public static final String YEAR_TO_SEC_NO_BLANK = "yyyyMMddHHmmss";

    public static final String YEAR_TO_MI_NO_BLANK = "yyyyMMddHHmm";

    public static final String DAY_TO_MINUTE = "dd HH:mm";

    public static final String DAY = "dd";
    
    public static final String YEAR = "yyyy";

    // 系统和数据时间差
    private static long SYSTEM_DIFF_TIME = 0;

    private static final String YEAR_TO_DAY_SLASH = "yyyy/MM/dd";

    private static final String YEAR_TO_DAY_TIME_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";

    /**
     * 日期转换成 dd HH:mm 字符
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2014-7-8
     */
    public static String getDayToMin(final Date date) {
        if (null == date) {
            return "";
        }
        return format(date, DAY_TO_MINUTE);
    }

    /**
     * DD HH:MI
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2014-7-9
     */
    public static Date parseDayToMin(final String dateStr) {
        return parseDate(dateStr, DAY_TO_MINUTE);

    }

    /**
     * 将yyyyMMdd格式字符转换为日期
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-6-5
     */
    public static Date parseYearToDayUnLine(String dateStr) {
        return parseDate(dateStr, YEAR_TO_DAY_UN_LINE);
    }

    /**
     * 
     * yyyyMMdd格式字符转换到时间
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-5
     */
    public static String getYearToDayUnLine(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_DAY_UN_LINE);
    }

    /**
     * 将yyyyMMdd HHmm格式字符转换为日期
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-6-5
     */
    public static Date parseYearToMinUnLine(String dateStr) {
        return parseDate(dateStr, YEAR_TO_MI_UN_LINE);
    }

    /**
     * 将字符串HHmm转换为离参照时间最近的时间 如参照时间为9月30日14点，传入1150,则返回9月30日11点50分
     * 如参照时间为9月30日0点10分，传入2350,则返回9月29日23点50分
     * 如参照时间为9月29日23点50分，传入0010,则返回9月30日00点10分
     * 
     * @param refTm
     *            参照时间
     * @param hourMin
     *            'HHmm'格式
     * @return Date(返回值说明)
     * @author soarin 2014-3-5
     */
    public static Date getLatestDateFromHourMinUnLine(Date refTm, String hourMin) {

        String strCurrentDt = DateUtil.getYearToDayUnLine(refTm);
        strCurrentDt += " ";
        strCurrentDt += hourMin;

        Date newTm = DateUtil.parseYearToMinUnLine(strCurrentDt);
        Date newTmPre = DateUtil.addDate(newTm, -1); // 往前一天
        Date newTmAfter = DateUtil.addDate(newTm, 1); // 往后一天

        Date finalTm = null;
        // 取和参照时间最近的时间
        if (Math.abs(DateUtil.diffMinute(refTm, newTm)) > Math.abs(DateUtil.diffMinute(refTm, newTmPre))) {
            finalTm = newTmPre;
        } else {
            finalTm = newTm;
        }

        if (Math.abs(DateUtil.diffMinute(refTm, finalTm)) > Math.abs(DateUtil.diffMinute(refTm, newTmAfter))) {
            finalTm = newTmAfter;
        }

        return finalTm;
    }

    /**
     * 将字符串HHMM转换为离当前时刻最近的时间 如当前时间为9月30日14点，传入1150,则返回9月30日11点50分
     * 如当前时间为9月30日0点10分，传入2350,则返回9月29日23点50分
     * 如当前时间为9月29日23点50分，传入0010,则返回9月30日00点10分
     * 
     * @param hourMin
     *            'HHMM'格式
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-9-30
     */
    public static Date getLatestDateFromHourMinUnLine(String hourMin) {
        Date currentTm = DateUtil.getSystemTm();

        String strCurrentDt = DateUtil.getYearToDayUnLine(currentTm);
        strCurrentDt += " ";
        strCurrentDt += hourMin;

        Date newTm = DateUtil.parseYearToMinUnLine(strCurrentDt);
        Date newTmPre = DateUtil.addDate(newTm, -1); // 往前一天
        Date newTmAfter = DateUtil.addDate(newTm, 1); // 往后一天

        Date finalTm = null;
        // 取和当前时间最近的时间
        if (Math.abs(DateUtil.diffMinute(currentTm, newTm)) > Math.abs(DateUtil.diffMinute(currentTm, newTmPre))) {
            finalTm = newTmPre;
        } else {
            finalTm = newTm;
        }

        if (Math.abs(DateUtil.diffMinute(currentTm, finalTm)) > Math.abs(DateUtil.diffMinute(currentTm, newTmAfter))) {
            finalTm = newTmAfter;
        }

        return finalTm;
    }

    /**
     * 
     * yyyyMMdd HHmm格式字符转换到时间
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-5
     */
    public static String getYearToMinUnLine(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_MI_UN_LINE);
    }

    /**
     * 将yyyyMMdd HHmmss格式字符转换为日期
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-6-5
     */
    public static Date parseYearToSecUnLine(String dateStr) {
        return parseDate(dateStr, YEAR_TO_SEC_UN_LINE);
    }

    /**
     * 
     * yyyyMMdd HHmmss格式字符转换到时间
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-5
     */
    public static String getYearToSecUnLine(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_SEC_UN_LINE);
    }

    /**
     * 将yyyyMMdd HHmmssSSS格式字符转换为日期
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-6-5
     */
    public static Date parseYearToMsUnLine(String dateStr) {
        return parseDate(dateStr, YEAR_TO_MS_UN_LINE);
    }

    /**
     * 
     * yyyyMMdd HHmmssSSS格式字符转换到时间
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-5
     */
    public static String getYearToMsUnLine(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_MS_UN_LINE);
    }

    /**
     * 
     * 字符串日期转换到Date类型
     * 
     * Examples:
     * 
     * @param dateStr
     *            yyyy-MM-dd格式的字符串
     * @return 日期类型
     * 
     * @return: 返回值说明
     * @exception： 异常的说明
     * @author soarin 2013-1-12
     * 
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, YEAR_TO_DAY);
    }

    /**
     * 
     * 时间转换成yyyy-MM-dd字符 Examples:(列举一些调用的例子)
     * 
     * @param dt
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-7
     */
    public static String getYearToDay(Date dt) {
        if (null == dt) {
            return "";
        }
        return format(dt, YEAR_TO_DAY);
    }

    /**
     * 获取当前日期的简单形式,去掉时/分/秒(如：2012-07-04 00:00:00)
     * 
     * @return
     * @author 田红兵 2012-7-5
     */
    public static Date getSimpleDate() {
        return getSimpleDate(getSystemTm());
    }

    /**
     * 获取给定日期的简单形式,去掉时/分/秒(如：2012-07-04 00:00:00)
     * 
     * @return
     * @author 田红兵 2012-7-5
     */
    public static Date getSimpleDate(Date d) {
        if (null == d)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 返回字符型时间
     * 
     * @param date
     *            日期
     * @return 返回字符型时间
     */
    public static String getTime(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, HOUR_TO_SEC);
    }

    /**
     * 返回字符型日期时间
     * 
     * @param date
     *            日期
     * @return 返回字符型日期时间
     */
    public static String getDateTime(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_MINUTE);
    }

    /**
     * yyyy-MM-dd HH:mm格式字符转换为date
     * 
     * @param dateStr
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2014-6-11
     */
    public static Date parseDateTime(String dateStr) {
        return DateUtil.parseDate(dateStr, YEAR_TO_MINUTE);
    }

    public static String getDateTimeSec(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_SEC);
    }

    public static String getDateTimeSecTwo(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, "yyyy-MM-dd HHmm");
    }

    public static String getDateTimeSec(String date) {
        if (null == date) {
            return "";
        }
        Date dateTemp = stringToUtilDate(date, YEAR_TO_SEC);
        return format(dateTemp, YEAR_TO_SEC);
    }

    /**
     * 获取当前日期的简单形式,去掉时/分/秒(如：2012-07-04 00:00:00)
     * 
     * @return
     * @author 田红兵 2012-7-5
     */
    public static Date getSimpleDay() {
        return getSimpleDay(getSystemTm());
    }

    /**
     * 获取给定日期的简单形式,去掉时/分/秒(如：2012-07-04 00:00:00)
     * 
     * @return
     * @author 田红兵 2012-7-5
     */
    public static Date getSimpleDay(Date d) {
        if (null == d)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date parseDateTimeSec(String str) throws ParseException {
        return parseDate(str, YEAR_TO_SEC);
        // java.text.DateFormat df = new
        // java.text.SimpleDateFormat(YEAR_TO_SEC);
        // return df.parse(str);
    }

    /**
     * 
     * 方法作用说明
     * 
     * Examples: 列举一些调用的例子
     * 
     * @param dateStr
     * @param format
     * @return
     * 
     * @return: 返回值说明
     * @exception： 异常的说明
     * @author soarin 2013-1-12
     * 
     */
    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        try {
            java.text.DateFormat df = new SimpleDateFormat(format);

            return (Date) df.parse(dateStr);
        } catch (Exception e) {
            logger.error("parse Date error:" + dateStr + "|" + format);
        }
        return date;
    }

    /**
     *
     * 方法作用说明 按format格式将时间转换成字符串 Examples: 列举一些调用的例子
     *
     * @param date
     * @param format
     *            格式化形式
     * @return
     *
     * @return: 返回值说明
     * @exception： 异常的说明
     * @author soarin 2013-1-12
     *
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                java.text.DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 把包含日期值转换为字符串
     *
     * @param date日期
     *            （日期+时间）
     * @param type
     *            输出类型
     * @return 字符串
     */
    public static String dateTimeToString(Date date, String type) {
        String DateString = "";
        if (date == null) {
            DateString = "";
        } else {
            SimpleDateFormat formatDate = new SimpleDateFormat(type, Locale.getDefault());
            DateString = formatDate.format(date);
        }
        return DateString;
    }

    /**
     * 把包含日期值转换为字符串
     *
     * @param Object日期
     *            （日期+时间）
     * @param type
     *            输出类型
     * @return 字符串
     */
    public static String dateTimeToString(Object dateObj, String type) {
        String DateString = "";
        if (dateObj == null) {
            DateString = "";
        } else {
            SimpleDateFormat formatDate = new SimpleDateFormat(type, Locale.getDefault());
            DateString = formatDate.format(dateObj);
        }
        return DateString;
    }

    /**
     * 将指定格式的日期/时间字符串转换成Date格式
     *
     * @param strDate
     *            String类型，日期字符
     * @param strFormat
     *            String类型，格式
     * @return Date类型
     */
    public static Date stringToUtilDate(String strDate, String strFormat) {
        try {
            if (strDate == null || strDate.equals("")) {
                return null;
            } else {
                SimpleDateFormat formatdate = new SimpleDateFormat(strFormat, Locale.getDefault());
                Date date = new Date((formatdate.parse(strDate)).getTime());
                return date;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期转换成strFormat的转换
     *
     * @param startDate
     * @param endDate
     * @param strFormat
     * @return
     */
    public static Date dateToFormat(Date startDate, String strFormat) {
        try {
            if (startDate != null && !"".equals(strFormat)) {
                String date = dateTimeToString(startDate, strFormat);
                return stringToUtilDate(date, strFormat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return startDate;
    }

    public static Date dateToFormat(Object startDate, String strFormat) {
        try {
            if (startDate != null && !"".equals(strFormat)) {
                String date = dateTimeToString(startDate, strFormat);
                return stringToUtilDate(date, strFormat);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据日期Date计算星期
     *
     * @param date
     * @return 返回星期
     */
    public static String getWeek(Date date) {
        String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return weekDaysName[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 得到某天星期几 1 2 3 4 5 6 7
     *
     * @param date
     * @return(设定参数)
     * @return int(返回值说明)
     * @author soarin 2014-3-25
     */
    public static int getIntWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 返回年份
     *
     * @param date
     *            日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 返回月份
     *
     * @param date
     *            日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static String getMonth(int month) {
        String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
        return months[month - 1];
    }

    public static String getMonth(String month) {
        String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
        for (int i = 1; i <= months.length; i++) {
            if (months[i - 1].equals(month)) {
                if (i < 10) {
                    return "0" + i;
                } else {
                    return "" + i;
                }
            }
        }
        return null;
    }

    /**
     * 返回日份
     *
     * @param date
     *            日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 返回小时
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date
     *            日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 日期相加
     *
     * @param date
     *            日期
     * @param day
     *            天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 增加小时
     *
     * @param date
     *            原来的时间
     * @param hour
     *            小时
     * @return Date 新的时间
     * @author pengzh 2014-7-24
     */
    public static Date addHour(Date date, double hour) {
        if (date == null) {
            return null;
        }
        return addHour(date, (int) hour);
    }

    public static Date addHour(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
        return c.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minute);
        return c.getTime();
    }

    /**
     * 日期增加秒 方法作用说明
     *
     * Examples: 列举一些调用的例子
     *
     * @param date
     *            日期
     * @param hour
     *            小时
     * @return
     *
     * @return: 返回值说明
     * @exception： 异常的说明
     * @author luxinming 2013-9-12
     *
     */
    public static Date addSecond(Date date, double second) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, (int) (second));
        return c.getTime();
    }

    public static Date addSecond(Date date, int second) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, (second));
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date
     *            日期
     * @param day
     *            天数
     * @return 返回相减后的日期
     */
    public static Date diffDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date
     *            日期
     * @param date1
     *            日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 日期相减,返回相减后的毫秒数 Examples:(列举一些调用的例子)
     *
     * @param date
     * @param date1
     * @return(设定参数)
     * @return int相减后的毫秒数
     * @author John 2013-5-29
     */
    public static int diffMillis(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)));
    }

    /**
     * 取得指定月份的第一天
     *
     * @param strdate
     *            String
     * @return String
     */
    public static String getMonthBegin(String strdate) {
        Date date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }

    /**
     * 取得指定月份的最后一天
     *
     * @param strdate
     *            String
     * @return String
     */
    public static String getMonthEnd(String strdate) {
        Date date = parseDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 常用的格式化日期
     *
     * @param date
     *            Date
     * @return String
     */
    public static String formatDate(Date date) {
        // 添加判断
        if (null == date) {
            return "";
        }
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    public static String formatDateSimple(Date date) {
        // 添加判断
        if (null == date) {
            return "";
        }
        return formatDateByFormat(date, YEAR_TO_DAY_UN_LINE);
    }

    public static String formatDateTimeSimple(Date date) {
        // 添加判断
        if (null == date) {
            return "";
        }
        return formatDateByFormat(date, YEAR_TO_MI_UN_LINE);
    }

    public static Date parseSimpleDate(String dateStr) {
        return parseDate(dateStr, YEAR_TO_DAY_UN_LINE);
    }

    public static Date parseSimpleDateTime(String dateStr) {
        return parseDate(dateStr, YEAR_TO_MI_UN_LINE);
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date
     *            Date
     * @param format
     *            String
     * @return String
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 去掉Date里面的时分秒
     *
     * @param Date
     * @return 去掉时分秒后的Date，若参数为空则返回原日期
     * @author 王柳新
     */
    public static Date trimHmsForDate(Date date) {
        Date result = date;
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.clear(Calendar.MINUTE);
            cal.clear(Calendar.SECOND);
            cal.clear(Calendar.MILLISECOND);
            result = cal.getTime();
        }
        return result;
    }

    /**
     * 判断两个时间是否在同一天
     *
     * @param d1
     * @param d2
     * @return(设定参数)
     * @return boolean(返回值说明)
     * @author soarin 2013-9-30
     */
    public static boolean bSameDay(Date d1, Date d2) {
        if (d1 == null || d2 == null)
            return false;

        return DateUtil.formatDate(d1).equals(DateUtil.formatDate(d2));
    }

    /**
     * 以当前日期为参照，根据偏移量，获取日期
     *
     * @param dOffset
     *            日期偏移量(以天为单位)
     * @return
     * @author 田红兵 2012-6-12
     */
    public static Date getDateByOffset(int dOffset) {
        return getDateByOffset(getSystemTm(), dOffset);
    }

    /**
     * 根据偏移量，获取日期
     *
     * @param dOffset
     *            日期偏移量(以天为单位)
     * @return
     * @author 田红兵 2012-6-12
     */
    public static Date getDateByOffset(Date d, int dOffset) {
        if (null == d)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, dOffset);
        return c.getTime();
    }

    /**
     * 以当前日期为参照，根据d/h/m移量，获取日期
     *
     * @param dOffset
     *            日期偏移量(以天为单位)
     * @param hOffset
     *            小时偏移量
     * @param mOffset
     *            分钟偏移量
     * @return
     * @author 田红兵 2012-6-12
     */
    public static Date getDateByOffset(int dOffset, int hOffset, int mOffset) {
        return getDateByOffset(getSystemTm(), dOffset, hOffset, mOffset);
    }

    /**
     * 根据d/h/m移量，获取日期
     *
     * @param dOffset
     *            日期偏移量(以天为单位)
     * @param hOffset
     *            小时偏移量
     * @param mOffset
     *            分钟偏移量
     * @return
     * @author 田红兵 2012-6-12
     */
    public static Date getDateByOffset(Date d, int dOffset, int hOffset, int mOffset) {
        if (null == d)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, dOffset);
        c.add(Calendar.HOUR, hOffset);
        c.add(Calendar.MINUTE, mOffset);
        return c.getTime();
    }

    /**
     * 比较两个日期(第二个日期减去第一个日期)，返回以分钟为最小单位的，最大以小时显示的字符窜(如：36H28 表示36小时28分)
     *
     * @param firstDt
     *            第一个日期
     * @param secondDt
     *            第二个日期
     * @return
     * @author 田红兵
     */
    public static String compareDate(Date firstDt, Date secondDt) {

        if (null == firstDt || null == secondDt)
            return "";
        long diff = (secondDt.getTime() - firstDt.getTime()) / (60 * 1000);
        return formatHourAndMinByMin(diff);
    }

    /**
     * 根据分钟数格式化成小时和分钟字符窜(如：200 -> 3H20)
     *
     * @param minute
     * @return
     * @author 田红兵
     */
    public static String formatHourAndMinByMin(long minute) {
        StringBuffer sb = new StringBuffer();
        if (minute < 0)
            sb.append("-");
        long m = Math.abs(minute % 60);
        long h = Math.abs(minute / 60);
        if (h > 0) {
            sb.append(h);
            sb.append("H");
        }
        if (m != 0)
            sb.append(m);
        return sb.toString();
    }

    /**
     * 比较两个日期(第二个日期减去第一个日期)，间隔2小时以上以分钟为单位，间隔2小时以上以小时+分钟格式显示的字符窜(如：36H28
     * 表示36小时28分，91表示1小时31分钟)
     *
     * @param firstDt
     *            第一个日期
     * @param secondDt
     *            第二个日期
     * @return
     * @author 田红兵
     */
    public static String compareDateTransfer(Date firstDt, Date secondDt) {

        if (null == firstDt || null == secondDt)
            return "";
        long diff = (secondDt.getTime() - firstDt.getTime()) / (60 * 1000);
        return formatHourAndMinByMinTransfer(diff);
    }

    /**
     * 2小时及以下直接显示分钟数，2小时以上根据分钟数格式化成小时和分钟字符窜(如：200 -> 3H20,91 -> 91)
     *
     * @param minute
     * @return
     * @author 田红兵
     */
    public static String formatHourAndMinByMinTransfer(long minute) {
        StringBuffer sb = new StringBuffer();
        if (minute < 0)
            sb.append("-");
        if (minute >= 0 && minute <= 120) {
            sb.append(minute);
        } else {
            long m = Math.abs(minute % 60);
            long h = Math.abs(minute / 60);
            if (h > 0) {
                sb.append(h);
                sb.append("H");
            }
            if (m != 0)
                sb.append(m);
        }
        return sb.toString();
    }

    /**
     * 两个时间差距多少秒
     *
     * @return
     */
    public static long getDateMinus(Date d1, Date d2) {
        long v = d1.getTime() - d2.getTime();
        if (v < 0) {
            v = -v;
        }
        return v;
    }

    /**
     * 时间减去多少分
     *
     * @param date
     * @param min
     * @return
     */
    public static Date deffDate(Date date, String min) {
        if (date == null || min == null || "".equals(min))
            return null;
        try {
            int m = Integer.parseInt(min);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.setTimeInMillis(c.getTimeInMillis() - ((long) m) * 60 * 1000);
            return c.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期相减
     *
     * @param date
     *            日期
     * @param date1
     *            日期
     * @return 返回相减后的分钟数
     */
    public static int diffMinute(Date date, Date date1) {
        if (date == null || date1 == null)
            return 0;
        return (int) ((getMillis(date) - getMillis(date1)) / (60 * 1000));
    }

    /**
     * 将java.util.Date 转换成 java.sql.Timestamp 用于PreparedStatement
     *
     * @param date
     * @return
     */
    public static java.sql.Timestamp transDate(Date date) {
        if (date == null)
            return null;
        return new java.sql.Timestamp(date.getTime());
    }

    public static Integer judgeDate(Date s, Date e) {
        int mark = 0;
        Long ss = s.getTime();
        Long ee = e.getTime();
        if (ss - ee == 0) {
            mark = 0;
        } else if (ss - ee > 0) {
            mark = 1;
        } else if (ss - ee < 0) {
            mark = -1;
        }
        return mark;
    }

    /**
     * 转换2013-01-18 00:00:0 字符日期格式为2013-01-18字符日期格式 百年之后这个函数就不对了。 by soarin
     */
    public static String transDateStrToStr(String fltDtStr) {
        String fltDt = "";
        if (fltDtStr != null && !"".equals(fltDtStr)) {
            String fltDtArr[] = fltDtStr.split(" ");
            fltDt = fltDtArr[0];
        }
        return fltDt;
    }

    /**
     * 两个时间是否相等
     * 
     * @param d1
     * @param d2
     * @return(设定参数)
     * @return boolean(返回值说明)
     * @author soarin 2013-12-6
     */
    public static boolean bEqual(Date d1, Date d2) {
        if (null == d1) {
            return null == d2;
        } else {
            if (null == d2) {
                return false;
            }
            return d1.compareTo(d2) == 0;
        }
    }

    public static void setSystemDiffTime(long diffTime) {
        SYSTEM_DIFF_TIME = diffTime;
    }

    /**
     * 获取系统时间
     * 
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author soarin 2013-5-13
     */
    public static Date getSystemTm() {
        return new Date(new Date().getTime() + SYSTEM_DIFF_TIME);
    }

    /**
     * 以指定的格式把Date转换成String（Locale为English)
     * 
     * @param date
     *            日期
     * @param format
     *            格式
     * @return String
     * @author 王柳新
     * @since 2013-6-9
     */
    public static String formatDateByFormatWithEnglishLocale(Date date, String format) {
        String result = "";
        if (date != null && StringUtil.isNotBlank(format)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                result = sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 以指定的格式把String转换成Date（Locale为English)
     * 
     * @param dateStr
     *            日期字符串
     * @param format
     *            格式
     * @return Date
     * @author 王柳新
     * @since 2013-6-9
     */
    public static Date parseDateByFormatWithEnglishLocale(String dateStr, String format) {
        Date result = null;
        if (StringUtil.isNotBlank(dateStr) && StringUtil.isNotBlank(format)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                result = (Date) sdf.parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 日期验证,dateStr格式需要是:yyyyMMddHHmmss或者yyyyMMdd
     * 因为parseDate方法当dateStr不是日期格式时，也会返回一个错误的日期，不满足要求，所以新增日期验证方法
     * 
     * @description:
     * @param dateStr
     * @return 是日期，返回true
     * @author John 2013-12-17
     */
    public static boolean isValidDate(String dateStr) {
        // String eL =
        // "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        String eL = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))((((0?[0-9])|([1][0-9])|([2][0-3]))([0-5]?[0-9])((\\s)|(([0-5]?[0-9])))))?$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(dateStr);
        return m.matches();
    }

    /**
     * 设置日期偏移量
     * 
     * @description: 功能描述
     * @param d
     *            所要偏移的日期
     * @param field
     *            字段(例如:Calendar.MINUTE,Calendar.HOUR),field the calendar
     *            field.注意：此参数必须与Calendar类定义的常量对应，否则会有错误
     * @param amount
     *            偏移量 the amount of date or time to be added to the field.
     * @return Date(返回值说明)
     * @author luxinming 2013-11-20
     */
    public static Date setDateByOffset(Date d, int field, int amount) {
        if (null == d) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 
     * 获取2个时间的差值，并格式化成xxdxxmxxs 扣除整数天后，如果时间小于等于二小时以内，则格式化成xxdxxm
     * 
     * @param startDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     * @author liyongsen
     * @CreateDate 2014-07-29
     */
    public static String getConnectTimeStr(Date startDate, Date endDate) {
        if (null == startDate || null == endDate) {
            return null;
        }
        long diffTime = diffMinute(endDate, startDate);
        String connectTimeStr = timeFormart((diffTime * 60 * 1000));
        return connectTimeStr;
    }

    /**
     * 把时间转成字符串xxdxxmxxs 扣除整数天后，如果时间小于等于二小时以内，则格式化成xxdxxm
     * 
     * @param time
     *            时间
     * @return String 字符串形式的时间
     * @author liyongsen
     * @CreateDate 2014-07-29
     */
    private static String timeFormart(long time) {
        StringBuilder buffer = new StringBuilder();
        long day = time / (24 * 60 * 60 * 1000);// 计算天数
        long hour = 0;
        long min = 0;
        if (time < 0) {
            time = time * -1;
            buffer.append("-");// 负数
        }
        if (day > 0) {
            time = time - day * (24 * 60 * 60 * 1000);// 剩下多少小时
            buffer.append(day).append("d");
        }
        hour = time / (60 * 60 * 1000);// 计算小时
        if (hour >= 0) {
            if (time <= (2 * 60 * 60 * 1000)) {// 如果时间小于等于二小时以内
                long mins = time / (60 * 1000);
                buffer.append(mins).append("m");
            } else {
                time = time - hour * 60 * 60 * 1000;// 剩下多少小时
                buffer.append(hour).append("h");
                min = time / (60 * 1000);// 剩下多少分钟
                buffer.append(min).append("m");
            }
        }
        return buffer.toString();
    }

    /**
     * 
     * @description:输入11NOV日期格式，根据当前系统时间判断，返回最近的yyyy/MM/dd格式的日期
     * @param date
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author ljxu 2014-12-14
     */
    public static Date chgHostMothDay2Common(String date) {
        String hostDateUpper = date.toUpperCase();
        String monName = "";
        monName = hostDateUpper.substring(2, 5);
        String month = getMonth(monName);
        if (null == month) {
            logger.error("待转换日期错误： " + date);
            return null;
        }
        String day = hostDateUpper.substring(0, 2);
        String monthDay = "/" + month + "/" + day;

        Date currentTm = DateUtil.getSystemTm();
        String strCurrentDt = DateUtil.getYearToDayWithSlash(currentTm);

        Date currentDt = DateUtil.parseDateWithSlash(strCurrentDt);
        Calendar calendar = Calendar.getInstance();

        int currentYear = calendar.get(Calendar.YEAR);

        int nextYear = currentYear + 1;

        int preYear = currentYear - 1;

        Date preTm = DateUtil.parseDateWithSlash(String.valueOf(preYear) + monthDay);

        Date nextTm = DateUtil.parseDateWithSlash(String.valueOf(nextYear) + monthDay);

        Date nowTm = DateUtil.parseDateWithSlash(String.valueOf(currentYear) + monthDay);

        if (preTm == null || nextTm == null || nowTm == null) {
            logger.error("待转换日期错误： " + date);
            return null;
        }
        Date finalTm = null;

        // 取和当前时间最近的时间
        if (Math.abs(currentDt.getTime() - preTm.getTime()) > Math.abs(currentDt.getTime() - nextTm.getTime())) {
            finalTm = nextTm;
        } else {
            finalTm = preTm;
        }

        if (Math.abs(currentDt.getTime() - finalTm.getTime()) > (Math.abs(currentDt.getTime() - nowTm.getTime()))) {
            finalTm = nowTm;
        }
        return finalTm;
    }

    /**
     * 
     * @description:输入11NOV 0200日期格式，根据当前系统时间判断 ，返回最近的yyyy/MM/dd
     *                      HH:mm:SS.SSS格式的日期
     * @param date
     * @param Time
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author ljxu 2014-12-15
     */
    public static Date chgHostMothDay2CommonTime(String date, String time) {
        Date nowDate = chgHostMothDay2Common(date);

        String hour = time.substring(0, 2);
        String min = time.substring(2, 4);
        String sec = "00";
        String mills = "000";
        StringBuffer nowTm = new StringBuffer();
        nowTm.append(hour + ":");
        nowTm.append(min + ":");
        nowTm.append(sec + "." + mills);
        String nowTmStr = getYearToDayWithSlash(nowDate) + " " + nowTm.toString();

        return parseDateTimeWithSlash(nowTmStr);
    }

    /**
     * 
     * yyyy/MM/dd格式字符转换到时间
     * 
     * @param date
     * @return(设定参数)
     * @return String(返回值说明)
     * @author soarin 2013-6-5
     */
    public static String getYearToDayWithSlash(Date date) {
        if (null == date) {
            return "";
        }
        return format(date, YEAR_TO_DAY_SLASH);
    }

    /**
     * 
     * @description:将yyyy/MM/dd转换成日期格式
     * @param date
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author ljxu 2014-12-14
     */
    public static Date parseDateWithSlash(String date) {
        return parseDate(date, YEAR_TO_DAY_SLASH);
    }

    /**
     * 
     * @description:将:yyyy/MM/dd HH:mm:ss.SSS转换成日期格式
     * @param date
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author ljxu 2014-12-14
     */
    public static Date parseDateTimeWithSlash(String date) {
        return parseDate(date, YEAR_TO_DAY_TIME_SLASH);
    }

    /**
     * 
     * @description:输入11NOV0200日期格式，根据当前系统时间判断 ，返回最近的yyyy/MM/dd
     *                                         HH:mm:SS.SSS格式的日期
     * @param date
     * @param Time
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author ljxu 2014-12-15
     */
    public static Date chgMothDay2CommonTime(String date) {
        String currentMothDay = date.substring(0, 5);
        String currentTime = date.substring(5);
        String hour = currentTime.substring(0, 2);
        String min = currentTime.substring(2, 4);
        String sec = "00";
        String mills = "000";
        StringBuffer nowTm = new StringBuffer();
        nowTm.append(hour + ":");
        nowTm.append(min + ":");
        nowTm.append(sec + "." + mills);
        Date nowDate = chgHostMothDay2Common(currentMothDay);
        String nowTmStr = getYearToDayWithSlash(nowDate) + " " + nowTm;

        return parseDateTimeWithSlash(nowTmStr);
    }

    /**
     * 
     * @description:输入110200日期格式，根据当前系统时间判断 ，返回最近的yyyy/MM/dd HH:mm:SS.SSS格式的日期
     * @param date
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author yhli 2014-1-5
     */
    public static Date parseAloneDTime2CommonDTime(String date) {
        String currentDate = date.substring(0, 2);
        String currentTime = date.substring(2);
        String hour = currentTime.substring(0, 2);
        String min = currentTime.substring(2, 4);
        String sec = "00";
        String mills = "000";
        StringBuffer aloneTm = new StringBuffer();
        aloneTm.append(hour + ":");
        aloneTm.append(min + ":");
        aloneTm.append(sec + "." + mills);
        Date aloneDate = parseAloneDate2CommonDate(currentDate);
        String nowTmStr = getYearToDayWithSlash(aloneDate) + " " + aloneTm;

        return parseDateTimeWithSlash(nowTmStr);
    }

    /**
     * 
     * @description:输入11日格式，根据当前系统时间判断，返回最近的yyyy/MM/dd格式的日期，当日期相距一样时，取前者，如当天为2012/2/29，输入30，得到2012/1/30而不是2012/3/30
     * @param date
     * @return(设定参数)
     * @return Date(返回值说明)
     * @author yhli 2015-1-5
     */
    public static Date parseAloneDate2CommonDate(String date) {
        Calendar finalDate = null;
        int d = Integer.valueOf(date);
        Calendar currentDate = Calendar.getInstance();

        if (currentDate.get(Calendar.DATE) == d) {
            finalDate = currentDate;
        } else {
            Calendar basicDate = (Calendar) currentDate.clone();
            basicDate.set(Calendar.DATE, d);
            Calendar nextDateOfBasic = (Calendar) currentDate.clone();
            nextDateOfBasic.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) + 1, d);
            Calendar prevDateOfBasic = (Calendar) currentDate.clone();
            prevDateOfBasic.set(currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH) - 1, d);

            if (nextDateOfBasic.get(Calendar.DATE) == d && prevDateOfBasic.get(Calendar.DATE) == d) {
                if (Math.abs(currentDate.getTimeInMillis() - prevDateOfBasic.getTimeInMillis()) > Math.abs(currentDate
                        .getTimeInMillis() - nextDateOfBasic.getTimeInMillis())) {
                    finalDate = nextDateOfBasic;
                } else {
                    finalDate = prevDateOfBasic;
                }
                if (basicDate.get(Calendar.DATE) == d
                        && (Math.abs(currentDate.getTimeInMillis() - finalDate.getTimeInMillis()) > Math
                                .abs(currentDate.getTimeInMillis() - basicDate.getTimeInMillis()))) {
                    finalDate = basicDate;
                }
            } else if (nextDateOfBasic.get(Calendar.DATE) == d) {
                if (basicDate.get(Calendar.DATE) != d
                        || (Math.abs(currentDate.getTimeInMillis() - basicDate.getTimeInMillis()) > Math
                                .abs(currentDate.getTimeInMillis() - nextDateOfBasic.getTimeInMillis()))) {
                    finalDate = nextDateOfBasic;
                } else {
                    finalDate = basicDate;
                }
            } else {
                if (basicDate.get(Calendar.DATE) != d
                        || (Math.abs(currentDate.getTimeInMillis() - basicDate.getTimeInMillis()) > Math
                                .abs(currentDate.getTimeInMillis() - prevDateOfBasic.getTimeInMillis()))) {
                    finalDate = prevDateOfBasic;
                } else {
                    finalDate = basicDate;
                }
            }
        }

        return finalDate.getTime();
    }

    /**
     * @description:将给定的日dd，如02 转换成离当前系统时间最近的ddMMM，如 02JAN；当前后时间相等时取较前的时间
     * @param day
     *            固定日期格式dd
     * @return(设定参数)
     * @return String(返回值说明)
     * @author fucy 2015-1-6
     */
    public static String transDay2DayMonth(String day) {
        if (StringUtil.isBlank(day)) {
            return null;
        }

        Date date = parseAloneDate2CommonDate(day);
        String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
        int month = DateUtil.getMonth(date);
        return day + months[month - 1];
    }

    /**
     * @description:将给定的日期字符串 
     *                        (四种形式：dd/ddMMM/ddMMMyy/ddMMMyyyy，如05或者04MAY，或者04MAY15
     *                        ，或者04MAY2015) 转换成离当前日期最近的yyyyMMdd格式日期字符串
     * @param date
     *            待转换日期字符串
     * @return String yyyyMMdd格式日期字符串
     * @author fucy 2015-5-5
     */
    public static String transDate2YearMonthDay(String date) {
        if (StringUtil.isBlank(date)) {
            logger.error("待转换日期字符串错误：" + date);
            return "";
        }

        Date finalDate = null;
        if (date.length() == 2 && date.matches("\\d{2}")) {
            // dd格式
            finalDate = parseAloneDate2CommonDate(date);
        } else if (date.length() == 5 && date.matches("\\d{2}[A-Z]{3}")) {
            // ddMMM格式
            finalDate = chgHostMothDay2Common(date);
        } else if (date.length() == 7 && date.matches("\\d{2}[A-Z]{3}\\d{2}")) {
            // ddMMMyy格式
            String year = String.valueOf(getYear(new Date())).substring(0, 2) + date.substring(5);
            return year + getMonth(date.substring(2, 5)) + date.substring(0, 2);
        } else if (date.length() == 9 && date.matches("\\d{2}[A-Z]{3}\\d{4}")) {
            // ddMMMyyyy格式
            return date.substring(5) + getMonth(date.substring(2, 5)) + date.substring(0, 2);
        }
        return getYearToDayUnLine(finalDate);
    }
    
    public static int getYearOfDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.get(Calendar.YEAR);
    }
    
    public static int getMonthOfDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.get(Calendar.MONTH)+1;
    }

    public static int getDayOfDay(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    public static Date addMonth(Date date,int month) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.MONTH, month);
    	return calendar.getTime();
    }
    
    public static Date addDay(Date date,int day) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, day);
    	return calendar.getTime();
    }
    /*
     * public static void main(String[] args) { Date n =
     * DateUtil.parseDate("20141231 16:31", "yyyyMMdd HH:mm");
     * System.out.println(DateUtil.getLatestDateFromHourMinUnLine(n,"0130"));
     * System.out.println(chgHostMothDay2Common("12DEC"));
     * System.out.println(chgHostMothDay2CommonTime("12DEC","0900L")); Date t1 =
     * new Date(); Calendar cal = Calendar.getInstance(); cal.setTime(t1);
     * cal.set(Calendar, -1); System.out.println(cal.getTime());
     * cal.add(Calendar.MONTH, 1); System.out.println(cal.getTime()); }
     */
    /** 
     * 获取当前年份 
     */  
    public static Integer getCurrentYear() {  
            Calendar ca = Calendar.getInstance();  
            return ca.get(Calendar.YEAR);  
    }  
     
    /** 
     * 获取当前月份 
     */  
    public static Integer getCurrentMonth() {  
            Calendar ca = Calendar.getInstance();  
            return ca.get(Calendar.MONTH) + 1;  
    }  
    
    public static Integer getCurrentDay() {  
        Calendar ca = Calendar.getInstance();  
        return ca.get(Calendar.DAY_OF_MONTH);  
}
    public static String getCurrentStrYear() {  
        Date date = new Date();      
        return  format(date, YEAR);  
}
    public static String getCurrentStrDate(){
    	 Date date = new Date();
    	 return  format(date, YEAR_TO_DAY); 
    }
    /**  
     * @Description: TODO 传入日期加addmonth个月份
     * @Author:fanzebin
     * @date 2016年11月23日 上午10:43:26  		
     * @param datetime（2016-11-05）
     * @param addmonth
     * @return  2017-02-05
    */
     	
    public static String addMonth(String datetime,int addmonth) {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;  
        try {  
            date = sdf.parse(datetime);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        Calendar cl = Calendar.getInstance();  
        cl.setTime(date);  
        cl.add(Calendar.MONTH, addmonth);  
        date = cl.getTime();  
        return sdf.format(date);  
    }  
    /** 
     *  将出生日期与当前日期相减，获得年龄 
     * @param birthdayDate 
     * @return 
     */  
    public static int getAge(Date birthdayDate) {  
            String formatCurrent = new SimpleDateFormat("yyyy-MM-dd").format(new Date());  

            int firstCu = formatCurrent.indexOf("-");  
            int lastCu = formatCurrent.lastIndexOf("-");  
            String currentYearStr = formatCurrent.substring(0, firstCu);  
            String currentMonthStr = formatCurrent.substring(firstCu + 1, lastCu);  
            String currentDayStr = formatCurrent.substring(lastCu + 1);  
            int currentYear = Integer.valueOf(currentYearStr);  
            int currentMonth = Integer.valueOf(currentMonthStr);  
            int currentDay = Integer.valueOf(currentDayStr);  

            String formatBirthday = new SimpleDateFormat("yyyy-MM-dd").format(birthdayDate);  

            int first = formatBirthday.indexOf("-");  
            int last = formatBirthday.lastIndexOf("-");  
            String birthYearStr = formatBirthday.substring(0, first);  
            String birthMonthStr = formatBirthday.substring(first + 1, last);  
            String birthDayStr = formatBirthday.substring(last + 1);  

            int birthYear = Integer.valueOf(birthYearStr);  
            int birthMonth = Integer.valueOf(birthMonthStr);  
            int birthDay = Integer.valueOf(birthDayStr);  

            if (currentMonth > birthMonth) {  
                    return  currentYear-birthYear;  
            } else if (currentMonth == birthMonth) {  
                    if (currentDay >= birthDay) {  
                            return currentYear-birthYear;  
                    } else {  
                            return currentYear-birthYear - 1;  
                    }  
            } else {  
                    return currentYear-birthYear - 1;  
            }  
    }  

}
