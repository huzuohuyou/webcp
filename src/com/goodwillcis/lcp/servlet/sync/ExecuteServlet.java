/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：ExecuteServlet.java    
// 文件功能描述：重新执行指定的上传或下载的应用程序
//
// 创建人：赵蓬
// 创建日期：2011-08-12
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.sync;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.lcp.util.PropertiesUtil;
import com.goodwillcis.lcp.util.LcpUtil;

/**
 * Servlet implementation class ExecuteServlet
 */
public class ExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try
		{
			String type = request.getParameter("type");
			int id = Integer.parseInt(request.getParameter("id"));
			if ("upload".equals(type)) upload(id);
			else if ("download".equals(type)) download(id);
			response.getWriter().print("complete");
		}
		catch(Exception ex)
		{
			response.getWriter().print(ex.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try
		{
			String type = request.getParameter("type");
			int id = Integer.parseInt(request.getParameter("id"));
			if ("upload".equals(type)) upload(id);
			else if ("download".equals(type)) download(id);
			response.getWriter().print("complete");
		}
		catch(Exception ex)
		{
			response.getWriter().print(ex.getMessage());
		}
	}
	
	private void upload(int id)
	{
		try
		{
			String url = LcpUtil.getConfigValue(PropertiesUtil.get(PropertiesUtil.UPLOAD_EXE_KEY));
			Runtime.getRuntime().exec("cmd /c start \" \" \"" + url + "\" " + id);
		} 
		catch(Exception ex){}
	}
	
	private void download(int id)
	{
		try
		{
			String url = LcpUtil.getConfigValue(PropertiesUtil.get(PropertiesUtil.DOWNLOAD_EXE_KEY));
			Runtime.getRuntime().exec("cmd /c start \" \" \"" + url + "\" " + id);
		}
		catch(IOException ex){}
	}

}
