package com.goodwillcis.lcp.servlet.zhikong;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.model.ZhikongCpIndex;
import com.goodwillcis.lcp.model.ZhikongCpPatientIndex;
import com.goodwillcis.lcp.service.zhikong.ZhikongCp;
import com.goodwillcis.lcp.service.zhikong.ZhikongCpPatient;
import com.goodwillcis.lcp.service.zhikong.impl.ZhikongCpImpl;
import com.goodwillcis.lcp.service.zhikong.impl.ZhikongCpPatientImpl;


public class ZhikongCpPatientIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static int PAGESIZE=20;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session=request.getSession();
		String cp_id=request.getParameter("cp_id");
		String patient_name=request.getParameter("patient_name");
		if(patient_name==null){
			patient_name="";
		}
		String ruyuantime=request.getParameter("ruyuan_time");

		if(ruyuantime==null){
			ruyuantime="";
		}
		System.out.println("---"+patient_name+"++"+ruyuantime);
		String pageNo=request.getParameter("page_no");
		System.out.println("pageNo="+pageNo);
		int page_no=1;
		try {
			page_no=Integer.parseInt(pageNo);
		} catch (Exception e) {
			page_no=1;
		}
		ZhikongCpPatient cp=new ZhikongCpPatientImpl();
		int count=0;
		count=cp.funGetCount(cp_id, patient_name, ruyuantime, "");

		Page page=new Page(count, page_no, PAGESIZE);
		
		String zhikongCpPatientIndexPage=page.funGetPageHtml1();
		ArrayList<ZhikongCpPatientIndex> zhikongCpPatientIndexs=cp.funGetZhikongByStartEndAndQuery(page.getStart(), page.getEnd());
		session.setAttribute("cp_id", cp_id);
		session.setAttribute("patient_name", patient_name);
		session.setAttribute("ruyuantime", ruyuantime);
		session.setAttribute("zhikongCpPatientIndexs", zhikongCpPatientIndexs);
		session.setAttribute("zhikongCpPatientIndexPage", zhikongCpPatientIndexPage);
		request.getRequestDispatcher("/zhikong/zhikong_patient_cp_index.jsp").forward(request,response);
	}

}
