package com.goodwillcis.lcp.servlet.sync;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DatabaseClass;

/**
 * Servlet implementation class PrintingTimeServlet
 */
@WebServlet("/PrintingTimeServlet")
public class PrintingTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintingTimeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		response.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		if(op.equals("formPrintingTime")){
			formPrintingTime(request,response);
		}
	}

	private void formPrintingTime(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String patient_no = request.getParameter("patientID");
		String sql = "update lcp_patient_visit t set t.form_printing_time=t.form_printing_time+1 where t.patient_no='"+patient_no+"'";
		DatabaseClass db = LcpUtil.getDatabaseClass();
		db.FunRunSqlByFile(sql.getBytes());
	    response.getWriter().print("success");
	}

}
