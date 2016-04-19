package test2;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Manikandan Sundararajan on 4/17/16.
 */
public class iso {
    private static ArrayList<String> weekValue = new ArrayList<>(Arrays.asList("mon", "tue", "wed", "thu", "fri", "sat", "sun"));
    private static ArrayList<String> monthValue = new ArrayList<>(Arrays.asList("jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"));

    private static HashMap<String, String> monthMap = new HashMap<>();

    //Variables that handles converting dates to the ISO 8601 format
    private static String ISO_FORMAT  = "yyyy-MM-dd'T'HH:mm:ss.SSSXX";
    private static SimpleDateFormat isoFormat = new SimpleDateFormat(ISO_FORMAT);

    private static int year;
    private static int month;
    private static int day;
    private static int hour;
    private static int minute;
    private static int second;

    private static boolean isWeek = false;
    private static boolean isMonth = false;

    //Initializes a hash map of month names to their corresponding month numbers
    public static void init(HashMap<String , String> monthMap){
        monthMap.put("jan", "01");
        monthMap.put("feb", "02");
        monthMap.put("mar", "03");
        monthMap.put("apr", "04");
        monthMap.put("may", "05");
        monthMap.put("jun", "06");
        monthMap.put("jul", "07");
        monthMap.put("aug", "08");
        monthMap.put("sep", "09");
        monthMap.put("oct", "10");
        monthMap.put("nov", "11");
        monthMap.put("dec", "12");
    }

    //Parses date strings that contain dots
    public static void dateWithDot(String input, Calendar calendar){
        int firstDot = input.indexOf(".");
        int lastDot = input.lastIndexOf(".");
        String strYear = input.substring(0, firstDot);
        String strMonth = input.substring(firstDot + 1, lastDot);
        String strDay = input.substring(lastDot + 1, input.length());

        year = Integer.parseInt(strYear);
        month = Integer.parseInt(strMonth) - 1;
        day = Integer.parseInt(strDay);

        calendar.set(year, month, day);
        //System.out.println(year + " " + month + " " + day);
        //System.out.println(calendar.getTime());
        return;
    }

    //Parses dates with backslashes in them
    public static void dateWithSlash(String input, Calendar calendar){
        int firstSlash = input.indexOf("/");
        int lastSlash = input.lastIndexOf("/");

        String strDay = input.substring(0, firstSlash);
        //System.out.println(strDay);
        String strMonth = input.substring(firstSlash + 1, lastSlash);
        //System.out.println(strMonth);
        //String strYear = input.substring(lastSlash + 1, input.length());
        //System.out.println(strYear);

        //year = Integer.parseInt(strYear);
        if (Integer.parseInt(strDay) > 12) {
            month = Integer.parseInt(strMonth) - 1;
            day = Integer.parseInt(strDay);
        }
        else{
            month = Integer.parseInt(strDay) - 1;
            //System.out.println(month);
            day = Integer.parseInt(strMonth);
            //System.out.println(day);
        }
        if (input.contains(" ")){
            int firstSpace = input.indexOf(" ");
            int lastSpace = input.lastIndexOf(" ");

            year = Integer.parseInt(input.substring(lastSlash + 1, firstSpace));
            String time = input.substring(firstSpace + 1, lastSpace);
            String mornorEve = input.substring(lastSpace + 1, input.length());
            if (input.indexOf(":") == input.lastIndexOf(":")){
                int firstColon = input.indexOf(":");
                String strMinute = input.substring(firstColon + 1, lastSpace);
                String strHour = input.substring(firstSpace + 1, firstColon);
                minute = Integer.parseInt(strMinute);
                hour = Integer.parseInt(strHour);
                if (mornorEve == "PM")
                    hour += 12;
                calendar.set(year, month, day, hour, minute);
                //System.out.println(calendar.getTime());
                return;
            }
            else {
                int firstColon = input.indexOf(":");
                int lastColon = input.lastIndexOf(":");
                String strMinute = input.substring(firstColon + 1, lastColon);
                String strHour = input.substring(firstSpace + 1, firstColon);
                String strSec = input.substring(lastColon + 1, lastSpace);
                minute = Integer.parseInt(strMinute);
                hour = Integer.parseInt(strHour);
                second = Integer.parseInt(strSec);
                if (mornorEve == "PM")
                    hour += 12;
                calendar.set(year, month, day, hour, minute, second);
                //System.out.println(calendar.getTime());
                return;
            }
        }
        else{
            year = Integer.parseInt(input.substring(lastSlash + 1, input.length()));
            calendar.set(year, month, day);
            //System.out.println(year + " " + month + " " + day);
            //System.out.println(calendar.getTime());
            return;
        }
    }

    //Parses dates with commas in them
    public static void dateWithComma(String input, Calendar calendar){
        int firstComma = input.indexOf(",");
        String weekOrMonth = input.substring(0, firstComma);
        if (weekOrMonth.contains(" ")){
            String[] dayMonth = weekOrMonth.split(" ");
            month = Integer.parseInt(monthMap.get(dayMonth[0].substring(0,3)));
            day = Integer.parseInt(dayMonth[1]);
            year = Integer.parseInt(input.substring(firstComma + 2, input.length()));
            calendar.set(year, month-1, day);
            //System.out.println(calendar.getTime());
            return;
        }
        else{
            if (weekOrMonth.length() > 3)
                weekOrMonth = weekOrMonth.substring(0,3);
            //System.out.println(monthMap.get(weekOrMonth));
            if (weekValue.contains(weekOrMonth))
                isWeek = true;
            else
                isMonth = true;
            if (isMonth == true){
                String strYear = input.substring(input.length()-4, input.length());
                //System.out.println(strYear);
                year = Integer.parseInt(strYear);
                calendar.set(year, Integer.parseInt(monthMap.get(weekOrMonth)) - 1, Calendar.DAY_OF_MONTH);
                //System.out.println(calendar.getTime());
                return;
            }
            else {
                String rest = input.substring(firstComma + 2, input.length());
                if (rest.contains(",")){
                    int secondComma = rest.indexOf(",");
                    String[] dayMonth = rest.substring(0, secondComma).split(" ");
                    month = Integer.parseInt(monthMap.get(dayMonth[0].substring(0,3)));
                    day = Integer.parseInt(dayMonth[1]);
                    String remainder = rest.substring(secondComma + 2, rest.length());
                    String[] yearTime = remainder.split(" ");
                    if (yearTime.length == 1){
                        year = Integer.parseInt(yearTime[0]);
                        calendar.set(year, month-1, day);
                        //System.out.println(calendar.getTime());
                        return;
                    }
                    else{
                        year = Integer.parseInt(yearTime[0]);
                        String[] time = yearTime[1].split(":");
                        if (time.length == 2){
                            hour = Integer.parseInt(time[0]);
                            minute = Integer.parseInt(time[1]);
                            calendar.set(year, month-1, day, hour, minute);
                            //System.out.println(calendar.getTime());
                            return;
                        }
                        else{
                            hour = Integer.parseInt(time[0]);
                            minute = Integer.parseInt(time[1]);
                            second = Integer.parseInt(time[2]);
                            calendar.set(year, month-1, day, hour, minute, second);
                            //System.out.println(calendar.getTime());
                            return;
                        }
                    }
                }
                else{
                    String[] yearDate = rest.split(" ");
                    day = Integer.parseInt(yearDate[0]);
                    month = Integer.parseInt(monthMap.get(yearDate[1]));
                    year = Integer.parseInt(yearDate[2]);
                    String[] time = yearDate[3].split(":");
                    hour = Integer.parseInt(time[0]);
                    minute = Integer.parseInt(time[1]);
                    second = Integer.parseInt(time[2]);
                    calendar.set(year, month-1, day, hour, minute, second);
                    if (!yearDate[4].matches(".*\\d+.*")){
                        calendar.setTimeZone(TimeZone.getTimeZone(yearDate[4]));
                    }
                    else{
                        if (yearDate[4].contains("-")){
                            String strHour = yearDate[4].substring(1,3);
                            String strMin = yearDate[4].substring(3, 5);
                            hour -= Integer.parseInt(strHour);
                            minute -= Integer.parseInt(strMin);
                        }
                        else{
                            String strHour = yearDate[4].substring(0,2);
                            String strMin = yearDate[4].substring(2, 4);
                            hour += Integer.parseInt(strHour);
                            minute += Integer.parseInt(strMin);
                        }
                    }
                    //System.out.println(calendar.getTime());
                    return;
                }
            }
        }
    }
    //Parses dates with other requirements
    public static void otherDate(String input, Calendar calendar){
        String[] isoDate = input.split(" ");
        if (isoDate.length == 1){
            if (input.contains("t")){
                year = Integer.parseInt(input.substring(0,4));
                month = Integer.parseInt(input.substring(5, 7));
                day = Integer.parseInt(input.substring(8,10));
                String t = input.substring(input.indexOf("t")+1, input.length());
                String[] time = t.substring(0,8).split(":");
                String timezone = t.substring(8, t.length());
                String plusOrMinus = timezone.substring(0,1);
                String strHour = timezone.substring(1,3);
                String strMin = timezone.substring(4, 6);
                if (plusOrMinus == "+") {
                    hour = Integer.parseInt(time[0]) + Integer.parseInt(strHour);
                    minute = Integer.parseInt(time[1]) + Integer.parseInt(strMin);
                }
                else {
                    hour = Integer.parseInt(time[0]) - Integer.parseInt(strHour);
                    minute = Integer.parseInt(time[1]) - Integer.parseInt(strMin);
                }
                second = Integer.parseInt(time[2]);
                calendar.set(year, month, day, hour, minute, second);
                return;
            }
            else {
                year = Integer.parseInt(input.substring(0, 4));
                month = Integer.parseInt(input.substring(4, 6));
                day = Integer.parseInt(input.substring(6, 8));
                calendar.set(year, month - 1, day);
                //System.out.println(calendar.getTime());
                return;
            }
        }
        else if (isoDate.length == 2){
            if (isoDate[1].contains("PM") || isoDate[1].contains("AM")){
                String[] time = isoDate[0].split(":");
                hour = Integer.parseInt(time[0]);
                minute = Integer.parseInt(time[1]);
                second = Integer.parseInt(time[2]);
                calendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DATE, hour, minute, second);
                //System.out.println(calendar.getTime());
                return;
            }
            else if (isoDate[0].matches(".*\\d+.*") && isoDate[1].matches(".*\\d+.*")){
                year = Integer.parseInt(isoDate[0].substring(0,4));
                month = Integer.parseInt(isoDate[0].substring(4,6));
                day = Integer.parseInt(isoDate[0].substring(6,8));
                String[] time = isoDate[1].split(":");
                hour = Integer.parseInt(time[0]);
                minute = Integer.parseInt(time[1]);
                second = Integer.parseInt(time[2]);
                calendar.set(year, month-1, day, hour, minute, second);
                //System.out.println(calendar.getTime());
                return;
            }
            else if (!isoDate[0].matches(".*\\d+.*")){
                String strMonth = isoDate[0].substring(0,3);
                month = Integer.parseInt(monthMap.get(strMonth));
                day = Integer.parseInt(isoDate[1]);
                calendar.set(Calendar.YEAR, month-1, day);
                //System.out.println(calendar.getTime());
                return;
            }
        }
    }

    //Function that handles different date functions
    public static void parse(String input1, Calendar calendar){
        String input = input1.toLowerCase();
        if (input.contains(".")){
            dateWithDot(input, calendar);
        }

        else if (input.contains("/")){
            dateWithSlash(input, calendar);
        }

        else if (input.contains(",")){
            dateWithComma(input, calendar);
        }

        else{
            otherDate(input, calendar);
        }
    }

    public static void main(String[] args){
        Calendar cal = Calendar.getInstance();
        //System.out.println(cal.getTime());
        init(monthMap);
        
        //Various input string configurations
        /*
        3/20/2016
        4:05:07 PM
        Sunday, March 20, 2016
        Sunday, March 20, 2016 4:05 PM
        Sunday, March 20, 2016 4:05:07 PM
        Sunday 20th of March 2016 04:05:07 PM
        Sunday, MAR 20, 2016
        3/20/2016 4:05 PM
        3/20/2016 4:05:07 PM
        March 20, 2016
        March 20
        March, 2016
        Sun, 20 Mar 2016 16:05:07 GMT
        Sun, 20 Mar 2016 16:05:07 -0800
        20160320 16:05:07
        20160320
        2016.03.20
        20/03/2016
        20 March 2016
        2016-20-03T16:05:07-08:00
        */
        //Modify this input parameter to see the ISO 8601 string
        parse("3/20/2016", cal);

        Date date = cal.getTime();
        System.out.println(isoFormat.format(date));
    }
}