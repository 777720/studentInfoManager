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


public class GradeDeleteServlet extends HttpServlet {
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
		String delids=request.getParameter("delids");
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int deleNum=gradeDao.gradeDelete(con, delids);
			if (deleNum>0) {
				result.put("success", "删除成功！");
				result.put("deleNum", deleNum);
			}else{
				result.put("errorMsg", "删除失败！");
			}
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
