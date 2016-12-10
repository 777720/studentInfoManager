package com.sim.util;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static JSONArray formatRsToJsonArray(ResultSet rs) throws SQLException{
		 
		ResultSetMetaData metaData=rs.getMetaData();
		int num=metaData.getColumnCount();
		JSONArray array=new JSONArray();
		while(rs.next()){                 
			JSONObject mapOfColValue=new JSONObject();
			for (int i = 1; i <= num; i++) {	
				Object o=rs.getObject(i);
				if (o instanceof Date) {
					mapOfColValue.put(metaData.getColumnName(i),DateUtil.formatDate((Date)o, "yyyy-MM-dd"));
				}else{
					mapOfColValue.put(metaData.getColumnName(i), rs.getObject(i));
				}
				
			}
			array.add(mapOfColValue);
		}
		return array;
	}

}
