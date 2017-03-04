/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author iNET
 */
public class DatePro {

    public static List<String> getListDateDDMMYYYY(String dayOfWeek, int numDay) {
        List<String> list = new ArrayList<String>();
        Today today = new Today();
        String ddmmyyyy = "";
        if (today.getDay() > 10) {
            ddmmyyyy = today.getDay() + "/";
        } else {
            ddmmyyyy = "0" + today.getDay() + "/";
        }

        if (today.getMonth() > 10) {
            ddmmyyyy = ddmmyyyy + today.getMonth() + "/";
        } else {
            ddmmyyyy = ddmmyyyy + "0" + today.getMonth() + "/";
        }
        ddmmyyyy = ddmmyyyy + today.getYear();

        ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);

        for (int i = 0; i < numDay; i++) {
            list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * i)));
        }

        return list;
    }

    public static String getDateOfWeek(int d, int m, int y) {
        Today today = new Today(d, m, y);
        String strThu = today.getDayOfWeek().toLowerCase().replace("thu", "");
        strThu = strThu.replace("chu nhat", "8");
        return strThu.trim().toUpperCase();
    }

    public static String getDateOfWeekDDMMYYYY(String date) {
        String[] arrDate = date.split("/");
        int day = Integer.valueOf(arrDate[0]);
        int month = Integer.valueOf(arrDate[1]);
        int year = Integer.valueOf(arrDate[2]);

        return getDateOfWeek(day, month, year);
    }

    //startdate dd/mm/yyyy
    //enddate dd/mm/yyyy
    public static long getNumberDay(String startdate, String enddate) {
        long result = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startdate);
            d2 = format.parse(enddate);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

//        long diffSeconds = diff / 1000 % 60;
//        long diffMinutes = diff / (60 * 1000) % 60;
//        long diffHours = diff / (60 * 60 * 1000) % 24;
            result = diff / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //dd/mm/yyyy
    //dayofweek
    //dd/mm/yyyy
    public static String getDateDDMMYYYY(String ddmmyyyy, String dayofweek) {
        String dd = ddmmyyyy;
        String dow = getDateOfWeekDDMMYYYY(dd);
        while (!dayofweek.contains(dow.toUpperCase())) {
            dd = DateProc.getDateString(DateProc.getPreviousDate(DateProc.String2Timestamp(dd)));
            //System.out.println("dd--"+dd);
            dow = getDateOfWeekDDMMYYYY(dd);
            //System.out.println("dow--"+dow);
        }

        return dd;
    }

    public static List<String> getListDateDDMMYYYY(String dayOfWeek) {
        List<String> list = new ArrayList<String>();
        Today today = new Today();
        String ddmmyyyy = today.getDay() + "/" + today.getMonth() + "/" + today.getYear();
        ddmmyyyy = getDateDDMMYYYY(ddmmyyyy, dayOfWeek);
        list.add(ddmmyyyy);
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 1)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 2)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 3)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 4)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 5)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 6)));
        list.add(DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -(7 * 7)));
        return list;
    }

    public static void main(String[] agr) {
        //System.out.println("day "+getDateDDMMYYYY("20/01/2015", "CN"));    
        long dow = getNumberDay("21/12/2015", "26/12/2015");
        System.out.println(dow);
//        for(int i=0;i<list.size();i++){
//            System.out.println("ddmmyyyy---"+list.get(i));
//        }
    }
}
