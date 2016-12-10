package com.sim.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sim.dao.GradeDao;
import com.sim.model.Grade;
import com.sim.model.PageBean;
import com.sim.util.DbUtil;
import com.sim.util.JsonUtil;
import com.sim.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class GradeListServlet extends HttpServlet {
	DbUtil dbUtil=new DbUtil();
	GradeDao gradeDao=new GradeDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String gradeName=request.getParameter("gradeName"); 
		if (gradeName==null) {
			gradeName="";
		}
		Grade grade=new Grade();
		grade.setGradeName(gradeName);
		Connection con=null;
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			ResultSet resultSet=gradeDao.gradeList(con, pageBean, grade);
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(resultSet);
			int total=gradeDao.gradeCount(con,grade);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}

}
