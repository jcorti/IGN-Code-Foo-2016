package com.ign.corti;

import java.text.SimpleDateFormat;
import java.util.*;

public class Date {
	private HashMap<String,String> monthMap = new HashMap<>();
	
	private SimpleDateFormat iForm = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXX");
	
	private int year;   private int day;  private int minute;
	private int month;  private int hour; private int second;
	
	private boolean isWeek = false, isMonth = false;
	
	public void mapSet()
	{
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
	
	public void slashDate(String ds, Calendar cal)
	{
		int first = ds.indexOf("/");
		int last = ds.lastIndexOf("/");

		String newDay = ds.substring(0, first);
		String newMonth = ds.substring(first + 1, last);
		String newYear = ds.substring(last + 1, ds.length());
	}
	
	public void dotDate(String ds, Calendar cal)
	{
		int dotOne = ds.indexOf(".");
		int last = ds.lastIndexOf(".");
		
		String newYear = ds.substring(0, dotOne);
		String newMonth =ds.substring(dotOne + 1,last);
		String newDay  = ds.substring(last + 1, ds.length());
		
		cal.set(year, month, day);
		
	}
}
