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

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.sim.dao.GradeDao;
import com.sim.model.Grade;
import com.sim.model.PageBean;
import com.sim.util.DbUtil;
import com.sim.util.JsonUtil;
import com.sim.util.ResponseUtil;
import com.sim.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class GradeSaveServlet extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		String gradeName=request.getParameter("gradeName");
		String gradeDesc=request.getParameter("gradeDesc");
		String gradeID=request.getParameter("id");
		Grade grade=new Grade(gradeName,gradeDesc);
		Connection con=null;
		if (StringUtil.isNotEmpty(gradeID)) {
			grade.setId(Integer.parseInt(gradeID));
		}
		try {
			con=dbUtil.getCon();
			int saveResult=0;
			JSONObject result=new JSONObject();
			if (StringUtil.isNotEmpty(gradeID)) {
				saveResult=gradeDao.gradeModify(con, grade);
			}else{
				saveResult=gradeDao.gradeAdd(con, grade);
			}
			if (saveResult>0) {
				result.put("success", "true");
				result.put("successMsg", "保存成功！");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败！");
			}
			
			
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
