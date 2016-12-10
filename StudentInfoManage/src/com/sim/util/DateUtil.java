package com.sim.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String formatDate(Date date,String formate){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(formate);
		if (date!=null) {
			result=sdf.format(date);
		}
		return result;
	}

}
