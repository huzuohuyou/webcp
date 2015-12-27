package com.goodwillcis.lcp.servlet.zhikong;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjgoodwill.emr.service.method.EmrMethod;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.service.zhikong.ZhikongCpTongji;
import com.goodwillcis.lcp.service.zhikong.impl.ZhikongCpTongjiImpl;
import com.goodwillcis.lcp.util.LcpUtil;

public class ZhikongPatientDoctorServlet extends HttpServlet {

	private static final int PAGESIZE=16;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("GBK");
		String id=request.getParameter("id");
		String localkey=request.getParameter("local_key");
		
//		if(null==id){
//			id="0";
//		}
		
		EmrMethod method=new EmrMethod();
		String html="";
		//html=method.getHtmlContentByNo(localkey);
		
		DatabaseClass class1=LcpUtil.getDatabaseClass();
		if(id != null && id != ""){
			html=class1.FunGetClobDataBySql("select * from lcp_patient_log_emr t where t.id="+id+" ", "CONTEXT");
		}else{
			html=null;
		}
		
		if(html==null||"".equals(html)){
			html="<html><head><title>厦门大学附属中山医院</title></head><body bgcolor='#005757'>" +
					"<center>暂无数据</center>" +
					"</body></html>";
		}
		PrintWriter pw=response.getWriter();
		pw.println(html);

		
		}
}
