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
import com.sim.dao.StudentDao;
import com.sim.model.Grade;
import com.sim.model.PageBean;
import com.sim.model.Student;
import com.sim.util.DbUtil;
import com.sim.util.JsonUtil;
import com.sim.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class StudentListServlet extends HttpServlet {
	DbUtil dbUtil=new DbUtil();
	StudentDao stuDao=new StudentDao();
	
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
		
		Student student=new Student();
		Connection con=null;
		PageBean pageBean=new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			ResultSet resultSet=stuDao.studentList(con, pageBean, student);
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(resultSet);
			int total=stuDao.studentCount(con, student);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	
	}

}
