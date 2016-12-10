package com.sim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sim.model.Grade;
import com.sim.model.PageBean;
/* select * from table limit size*/
import com.sim.util.StringUtil;

public class GradeDao {
	public ResultSet gradeList(Connection conn, PageBean pageBean, Grade grade) throws SQLException {
		StringBuffer stringBuffer = new StringBuffer("select * from t_grade");
		//System.out.println(grade.getGradeName());
		if (StringUtil.isNotEmpty(grade.getGradeName())) {
			stringBuffer.append(" and gradeName like '%" + grade.getGradeName() + "%'");

		}
		if (pageBean != null) {
			stringBuffer.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());

		}

		PreparedStatement pstmt = conn.prepareStatement(stringBuffer.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();

	}

	public int gradeCount(Connection con, Grade grade) throws SQLException {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
		if (StringUtil.isNotEmpty(grade.getGradeName())) {
			sb.append(" and gradeName like '%" + grade.getGradeName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet resultSet = pstmt.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("total");

		} else {
			return 0;
		}
	}

	public int gradeDelete(Connection con, String delids) throws SQLException {
		String sql = "delete from t_grade where id in (" + delids + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	public int gradeAdd(Connection con,Grade grade) throws SQLException{
		String sql="insert into t_grade values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
	
		return pstmt.executeUpdate();
		
	}
	public int gradeModify(Connection connection,Grade grade)throws SQLException{
		String sql="update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement pstmt=connection.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();
	}

}
