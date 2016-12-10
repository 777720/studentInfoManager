 package com.sim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sim.model.PageBean;
import com.sim.model.Student;

public class StudentDao {
	public ResultSet studentList(Connection con,PageBean pageBean,Student student) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from t_student s,t_grade g where s.gradeId=g.id");
		if (pageBean!=null) {
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());	
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
		
	}
	
	public int studentCount(Connection con,Student student) throws SQLException{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_student s,t_grade g where s.gradeId=g.id");
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet resultSet=pstmt.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("total");
		}else{
			return 0;
		}
	}
}
