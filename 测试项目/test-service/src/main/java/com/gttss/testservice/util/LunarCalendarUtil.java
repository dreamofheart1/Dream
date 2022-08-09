package com.gttss.testservice.util;

import java.util.*;

public final class LunarCalendarUtil {
    //农历（阴历）年，月，日
    private static int yearCyl;
    private static int monCyl;
    private static int dayCyl;
    //公历（阳历）年，月，日
    private static int year;
    private static int month;
    private static int day;
    private static boolean isLeap;
    private static int[] lunarInfo = {0x04bd8, 0x04ae0, 0x0a570, 0x054d5,
            0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
            0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2,
            0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40,
            0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0,
            0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7,
            0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0,
            0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355,
            0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263,
            0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0,
            0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6, 0x095b0,
            0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
            0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50,
            0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954,
            0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0,
            0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0,
            0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 0x06aa0, 0x0ad50,
            0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6,
            0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    private static int[] solarMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
            30, 31};
    private static String[] Gan = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛",
            "壬", "癸"};
    private static String[] Zhi = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未",
            "申", "酉", "戌", "亥"};
    private static String[] Animals = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
            "猴", "鸡", "狗", "猪"};
    private static int[] sTermInfo = {0, 21208, 42467, 63836, 85337, 107014,
            128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,
            308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224,
            483532, 504758};
    private static String[] nStr1 = {"日", "一", "二", "三", "四", "五", "六", "七",
            "八", "九", "十"};
    private static String[] nStr2 = {"初", "十", "廿", "卅", "　"};
    private static String[] monthNong = {"", "正", "二", "三", "四", "五", "六", "七",
            "八", "九", "十", "冬", "腊"};
    private static String[] yearName = {"零", "壹", "贰", "叁", "肆", "伍", "陆",
            "柒", "捌", "玖"};
    private Object LogFactory;


    /**
     * 传回农历 y年的总天数
     * @param y
     * @return
     */
    private static int lYearDays(int y) {
        int i;
        int sum = 348; //29*12
        for (i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunarInfo[y - 1900] & i) == 0 ? 0 : 1; //大月+1天
        }
        return (sum + leapDays(y)); //+闰月的天数
    }

    /**
     * 传回农历 y年闰月的天数
     * @param y
     * @return
     */
    private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            return ((lunarInfo[y - 1900] & 0x10000) == 0 ? 29 : 30);
        } else {
            return (0);
        }
    }

    /**
     * 传回农历 y年闰哪个月 1-12 , 没闰传回 0
     * @param y
     * @return
     */
    private static int leapMonth(int y) {
        return (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 传回农历 y年m月的总天数
     * @param y
     * @param m
     * @return
     */
    private static int monthDays(int y, int m) {
        return ((lunarInfo[y - 1900] & (0x10000 >> m)) == 0 ? 29 : 30);
    }

    /**
     * 算出农历, 传入日期物件, 传回农历日期物件
     * 该物件属性有 .year .month .day .isLeap .yearCyl .dayCyl .monCyl
     * @param objDate
     */
    private static void initLunar(Date objDate) {
        int i, leap = 0, temp = 0;
        Calendar cl = Calendar.getInstance();
        cl.set(1900, 0, 31); //1900-01-31是农历1900年正月初一
        Date baseDate = cl.getTime();
        //1900-01-31是农历1900年正月初一
        int offset = (int) ((objDate.getTime() - baseDate.getTime()) / 86400000); //天数(86400000=24*60*60*1000)
        //1899-12-21是农历1899年腊月甲子日
        dayCyl = offset + 40;
        //1898-10-01是农历甲子月
        monCyl = 14;
        //得到年数
        for (i = 1900; i < 2050 && offset > 0; i++) {
            //农历每年天数
            temp = lYearDays(i);
            offset -= temp;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += temp;
            i--;
            monCyl -= 12;
        }
        year = i; //农历年份
        yearCyl = i - 1864; //1864年是甲子年
        leap = leapMonth(i); //闰哪个月
        isLeap = false;
        for (i = 1; i < 13 && offset > 0; i++) {
            //闰月
            if (leap > 0 && i == (leap + 1) && isLeap == false) {
                --i;
                isLeap = true;
                temp = leapDays(year);
            } else {
                temp = monthDays(year, i);
            }
            //解除闰月
            if (isLeap == true && i == (leap + 1)) {
                isLeap = false;
            }
            offset -= temp;
            if (isLeap == false) {
                monCyl++;
            }
        }
        if (offset == 0 && leap > 0 && i == leap + 1) {
            if (isLeap) {
                isLeap = false;
            } else {
                isLeap = true;
                --i;
                --monCyl;
            }
        }
        if (offset < 0) {
            offset += temp;
            --i;
            --monCyl;
        }
        month = i; //农历月份
        day = offset + 1; //农历天份
    }

    /**
     * 传入 offset 传回干支, 0=甲子
     * @param num
     * @return
     */
    private static String cyclical(int num) {
        return (Gan[num % 10] + Zhi[num % 12]);
    }

    /*
    * 返回天干、地支序数 从1开始
    * */
    private static Integer[] getTianGanOrdinary(int num) {
    	return new Integer[]{num % 10+1, num % 12+1};
    }

    /**
     * 中文日期
     * @param d
     * @return
     */
    private static String cDay(int d) {
        String s;
        switch (d) {
            case 10:
                s = "初十";
                break;
            case 20:
                s = "二十";
                break;
            case 30:
                s = "三十";
                break;
            default:
                s = nStr2[(int) (d / 10)];//取商
                s += nStr1[d % 10];//取余
        }
        return (s);
    }
    private static String cYear(int y) {
        String s = " ";
        int d;
        while (y > 0) {
            d = y % 10;
            y = (y - d) / 10;
            s = yearName[d] + s;
        }
        return (s);
    }

    private static int getYear() {
        return (year);
    }
    private static int getMonth() {
        return (month);
    }
    private static int getDay() {
        return (day);
    }
    private static int getMonCyl() {
        return (monCyl);
    }
    private static int getYearCyl() {
        return (yearCyl);
    }
    private static int getDayCyl() {
        return (dayCyl);
    }
    private static boolean getIsLeap() {
        return (isLeap);
    }

    /**
     * 获取-天干、地支序数
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static List<Integer> getLunarYMDOrdinary(String year, String month, String day) {
        List list = new ArrayList();
        Date sDObj;
        String s;
        int SY, SM, SD;
        int sy;
        SY = Integer.parseInt(year);
        SM = Integer.parseInt(month);
        SD = Integer.parseInt(day);
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        sDObj = cl.getTime();
        //日期
        initLunar(sDObj); //农历

        list.addAll(Arrays.asList(getTianGanOrdinary(getYearCyl())));
        list.addAll(Arrays.asList(getTianGanOrdinary(getMonCyl())));
        list.addAll(Arrays.asList(getTianGanOrdinary(getDayCyl())));

        return list;
    }

    /**
     * 获取-农历详细日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getLunarDetails(String year, String month, String day) {
        Date sDObj;
        String s;
        int SY, SM, SD;
        int sy;
        SY = Integer.parseInt(year);
        SM = Integer.parseInt(month);
        SD = Integer.parseInt(day);
        sy = (SY - 4) % 12;
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        sDObj = cl.getTime();
        //日期
        initLunar(sDObj); //农历
        StringBuffer lMDBuffer = new StringBuffer();
        lMDBuffer.append("农历");
        lMDBuffer.append("【");
        lMDBuffer.append(Animals[sy]);
        lMDBuffer.append("】");
        lMDBuffer.append(cYear(getYear()));
        lMDBuffer.append("年 ");
        lMDBuffer.append((getIsLeap() ? "闰" : ""));
        lMDBuffer.append(monthNong[getMonth()]);
        lMDBuffer.append("月");
        lMDBuffer.append((monthDays(getYear(), getMonth()) == 29 ? "小" : "大"));
        lMDBuffer.append(cDay(getDay()));
        lMDBuffer.append(" ");
        int yearCyl = getYearCyl();
        System.out.println("yearCyl=" + yearCyl);
        lMDBuffer.append(cyclical(getYearCyl()));
        lMDBuffer.append("年");
        lMDBuffer.append(cyclical(getMonCyl()));
        lMDBuffer.append("月");
        lMDBuffer.append(cyclical(getDayCyl()));
        lMDBuffer.append("日");
        return lMDBuffer.toString();
    }

    /**
     * 获取-农历年月日
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getLunarYearMonthDay(String year, String month, String day) {
        Date sDObj;
        int SY, SM, SD;
        int sy;
        SY = Integer.parseInt(year);
        SM = Integer.parseInt(month);
        SD = Integer.parseInt(day);
        sy = (SY - 4) % 12;
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        sDObj = cl.getTime();
        //日期
        initLunar(sDObj); //农历
        StringBuffer lMDBuffer = new StringBuffer();
        lMDBuffer.append("农历");
        lMDBuffer.append(cyclical(getYearCyl()));
        lMDBuffer.append("(");
        lMDBuffer.append(Animals[sy]);
        lMDBuffer.append(")年");
        lMDBuffer.append(monthNong[getMonth()]);
        lMDBuffer.append("月");
        lMDBuffer.append(cDay(getDay()));
        return lMDBuffer.toString();
    }

    /**
     * 获取-农历月日
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getLunarMonthDay(String year, String month, String day) {
        Date sDObj;
        String s;
        int SY, SM, SD;
        SY = Integer.parseInt(year);
        SM = Integer.parseInt(month);
        SD = Integer.parseInt(day);
        Calendar cl = Calendar.getInstance();
        cl.set(SY, SM - 1, SD);
        sDObj = cl.getTime();
        //日期
        initLunar(sDObj); //农历
        StringBuffer lMDBuffer = new StringBuffer();
        lMDBuffer.append(monthNong[getMonth()]);
        lMDBuffer.append("月");
        lMDBuffer.append(cDay(getDay()));
        return lMDBuffer.toString();
    }
    public static void main(String[] args) {
        System.out.println(getLunarDetails("1990", "12", "22"));
        System.out.println(getLunarDetails("2019", "1", "22"));
        System.out.println(getLunarDetails("2019", "2", "10"));
        System.out.println(getLunarDetails("2020", "03", "03"));
        System.out.println(getLunarDetails("2022", "08", "06"));
        System.out.println("----------------------------------------------------");

        System.out.println(getLunarYearMonthDay("1990", "12", "22"));
        System.out.println(getLunarYearMonthDay("2019", "1", "22"));
        System.out.println(getLunarYearMonthDay("2019", "2", "10"));
        System.out.println(getLunarYearMonthDay("2020", "03", "03"));
        System.out.println(getLunarYearMonthDay("2022", "08", "06"));
        System.out.println("----------------------------------------------------");

        System.out.println(getLunarMonthDay("1990", "12", "22"));
        System.out.println(getLunarMonthDay("2019", "1", "22"));
        System.out.println(getLunarMonthDay("2019", "2", "10"));
        System.out.println(getLunarMonthDay("2020", "03", "03"));
        System.out.println(getLunarMonthDay("2022", "08", "06"));
        System.out.println("----------------------------------------------------");

        List<Integer> list = getLunarYMDOrdinary("2022", "08", "06");
        System.out.println(list);

    }
}

