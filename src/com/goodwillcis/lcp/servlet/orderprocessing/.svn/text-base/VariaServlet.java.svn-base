/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名：VariaServlet.java
 *  // 文件功能描述：录入变异的servlet类 
 *  // 创建人：康榕元 
 *  // 创建日期：2011/08/25 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.orderprocessing;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;

import com.goodwillcis.general.DatabaseClass;

public class VariaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");
		//request.setCharacterEncoding("gbk");
		request.setCharacterEncoding("utf-8");
		String op = request.getParameter("op");
		if ("varia".equals(op)) {
			try {
				varia(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void varia(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException, JSONException {
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String jsonStr = request.getParameter("reStr");
		System.out.println("传来的："+jsonStr);
		String time = CommonUtil.getOracleToDate();
		JSONObject json = new JSONObject(jsonStr);
		String pa_id = json.getString("pa_id");
		JSONArray jArray = json.getJSONArray("list");
		
		String sql = "";
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject data = (JSONObject) jArray.get(i);
			String id = data.getString("id");
			String yuanyinid = data.getString("yuanyinid");
			String shuoming = data.getString("shuoming");
			sql +="update lcp_patient_log_order_varia t set t.variation_code = '"+yuanyinid+"',t.variation_content = '"+shuoming+"',t.sys_last_update = "+time+" where t.auto_id = "+id+" and t.patient_no='"+pa_id+"'\n";
		}
		System.out.println("添变异sql:"+sql);
		int res = db.FunRunSqlByFile(sql.getBytes());
		System.out.println("提交成功行数res:"+res);
		if(res>0){
			response.getWriter().println(true);
		}else{
			response.getWriter().println(false);
		}
	}
	
}
