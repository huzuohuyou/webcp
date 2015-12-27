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
import com.goodwillcis.lcp.service.zhikong.ZhikongCp;
import com.goodwillcis.lcp.service.zhikong.impl.ZhikongCpImpl;


public class ZhikongCpIndexServlet extends HttpServlet {
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
		String queryFlag=request.getParameter("queryFlag");
		String queryValue=request.getParameter("queryValue");
		String pageNo=request.getParameter("pageNo");
		int page_no=1;
		try {
			page_no=Integer.parseInt(pageNo);
		} catch (Exception e) {
			page_no=1;
		}
		ZhikongCp cp=new ZhikongCpImpl();
		int count=0;
		if(queryFlag==null){
			count=cp.funGetCount("","", "", "");
		}else if(queryFlag.equals("0")){
			count=cp.funGetCount(queryValue, "", "","");
		}else if(queryFlag.equals("1")){
			count=cp.funGetCount("", queryValue, "","");
		}else if(queryFlag.equals("2")){
			count=cp.funGetCount("", "",queryValue, "");
		}
		else if(queryFlag.equals("3")){
			count=cp.funGetCount("", "", "",queryValue);
		}
		Page page=new Page(count, page_no, PAGESIZE);
		
		String zhikongCpIndexPage=page.funGetPageHtml1();
		ArrayList<ZhikongCpIndex> zhikongCpIndexs=cp.funGetZhikongByStartEndAndQuery(page.getStart(), page.getEnd());
		session.setAttribute("zhikongCpIndexPage", zhikongCpIndexPage);
		session.setAttribute("zhikongCpIndexs", zhikongCpIndexs);
		session.setAttribute("zhikongCpIndexRadioCheck", queryFlag);
		session.setAttribute("zhikongCpIndexRadioContext", queryValue);
		request.getRequestDispatcher("/zhikong/zhikong_cp_index.jsp").forward(request,response);
	}

}
