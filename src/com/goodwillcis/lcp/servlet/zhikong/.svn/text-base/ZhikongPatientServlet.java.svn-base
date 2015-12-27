/*---------------------------------------------------------------- 
 * // Copyright (C) 2012 北京嘉和美康信息技术有限公司版权所有。
 * // 文件名： ZhikongPatientServlet.java
 * // 文件功能描述：病例查看具体信息
 * // 创建人：张昆 
 * // 创建日期：2012/07/30
 * ----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.zhikong;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
@WebServlet("/ZhikongPatientServlet")
public class ZhikongPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseClass db=LcpUtil.getDatabaseClass();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		//System.out.println("op=====:"+op);
		if(op.equals("diaAndOperaInfo")){
			diaAndOperaInfo(request,response);
		}
		if(op.equals("doctor")){
			doctor(request,response);
		}if(op.equals("order")){
			order(request,response);
		}if(op.equals("nurse")){
			nurse(request,response);
		}
		if(op.equals("logEmr")){
			logEmr(request,response);
		}
	}
	/**
	 * 诊疗与手术记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void diaAndOperaInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=request.getParameter("patientNo");
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		String diaAndOperaInfoSql="select * from lcp_patient_log_income a where a.patient_no = '"+patient_no+"'";
		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		int rowCount = db.FunGetRowCountBySql(diaAndOperaInfoSql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}
		DataSetClass dataSet=db.FunGetPageDataSetBySQL(diaAndOperaInfoSql,startNum,endNum);
		int counts=dataSet.FunGetRowCount();
		   String LOCAL_KEY="";
		   String INCOME_CODE="";
		   String LOCAL_NAME="";
		   String INCOME_TYPE="";
		   String USER_NAME="";
		   String EXE_DATE="";
		   String LOCAL_CODE="";
	   JSONObject jsonObj=new JSONObject();
	   JSONArray rows=new JSONArray();
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<counts;i++){
			LOCAL_KEY=dataSet.FunGetDataAsStringByColName(i, "LOCAL_KEY");
			INCOME_CODE=dataSet.FunGetDataAsStringByColName(i, "INCOME_CODE");
			LOCAL_NAME=dataSet.FunGetDataAsStringByColName(i, "LOCAL_NAME");
			INCOME_TYPE=dataSet.FunGetDataAsStringByColName(i, "INCOME_TYPE");
			USER_NAME=dataSet.FunGetDataAsStringByColName(i, "USER_NAME");
			Date date= dataSet.FunGetDataAsDateByColName(i, "EXE_DATE");
			if(date!=null){
				EXE_DATE=sdf.format(date);
			}
			LOCAL_CODE=dataSet.FunGetDataAsStringByColName(i, "LOCAL_CODE");
			JSONObject cell=new JSONObject();
			cell.put("serialNo", i+1);
			cell.put("LOCAL_KEY", LOCAL_KEY);
			cell.put("INCOME_CODE", INCOME_CODE);
			cell.put("LOCAL_NAME", LOCAL_NAME);
			cell.put("INCOME_TYPE", INCOME_TYPE);
			cell.put("USER_NAME", USER_NAME);
			cell.put("EXE_DATE", EXE_DATE);
			cell.put("LOCAL_CODE", LOCAL_CODE);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(jsonObj.toString());
	}
	
	/**
	 * 治疗记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void doctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=request.getParameter("patientNo");
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		String doctorInfoSql="select a.*,b.cp_id belong   from lcp_patient_log_doctor a, lcp_patient_doctor_item b  where a.doctor_no = b.doctor_no(+)    and a.hospital_id = b.hospital_id(+)    and a.patient_no = '"+patient_no+"'    and a.patient_no=b.patient_no(+)  ";

		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		int rowCount = db.FunGetRowCountBySql(doctorInfoSql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}
		DataSetClass dataSet=db.FunGetPageDataSetBySQL(doctorInfoSql,startNum,endNum);
		int counts=dataSet.FunGetRowCount();
		   String LOCAL_KEY="";
		   String LOCAL_NAME="";
		   String INCOME_TYPE="";
		   String USER_NAME="";
		   String EXE_DATE="";
		   String LOCAL_CODE="";
		   String isBelongCp="";//是否路径项
		   String DOCTOR_NO="";
	   JSONObject jsonObj=new JSONObject();
	   JSONArray rows=new JSONArray();
	   SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<counts;i++){
			LOCAL_KEY=dataSet.FunGetDataAsStringByColName(i, "LOCAL_KEY");
			LOCAL_CODE=dataSet.FunGetDataAsStringByColName(i, "LOCAL_CODE");
			LOCAL_NAME=dataSet.FunGetDataAsStringByColName(i, "LOCAL_NAME");
			isBelongCp=dataSet.FunGetDataAsStringByColName(i, "BELONG");
			if(isBelongCp!="0"){
        		isBelongCp="●";
        	}else{
        		isBelongCp="";
        	}
			INCOME_TYPE=dataSet.FunGetDataAsStringByColName(i, "INCOME_TYPE");
			USER_NAME=dataSet.FunGetDataAsStringByColName(i, "USER_NAME");
			Date date= dataSet.FunGetDataAsDateByColName(i, "EXE_DATE");
			if(date!=null){
				EXE_DATE=sdf.format(date);
			}
			DOCTOR_NO=dataSet.FunGetDataAsStringByColName(i, "DOCTOR_NO");
			JSONObject cell=new JSONObject();
			cell.put("serialNo", i+1);
			cell.put("LOCAL_KEY", LOCAL_KEY);
			cell.put("LOCAL_CODE", LOCAL_CODE);
			cell.put("LOCAL_NAME", LOCAL_NAME);
			cell.put("isBelongCp", isBelongCp);
			cell.put("INCOME_TYPE", INCOME_TYPE);
			cell.put("USER_NAME", USER_NAME);
			cell.put("EXE_DATE", EXE_DATE);
			cell.put("DOCTOR_NO", DOCTOR_NO);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		//System.out.println("jsonObj==:"+jsonObj);
		response.getWriter().println(jsonObj.toString());
	}
	
	/**
	 * 医嘱记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=request.getParameter("patientNo");
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		String orderInfoSql="select a.*, b.cp_id BELONG  from lcp_patient_log_order a, lcp_patient_order_item b where a.hospital_id = b.hospital_id(+)   and a.order_no = b.order_no(+)   and a.patient_no=b.patient_no(+)  and a.patient_no='"+patient_no+"'";
		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		int rowCount = db.FunGetRowCountBySql(orderInfoSql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}
		DataSetClass dataSet=db.FunGetPageDataSetBySQL(orderInfoSql,startNum,endNum);
		int counts=dataSet.FunGetRowCount();
		   String LOCAL_KEY="";
		   String EXE_DATE="";
		   String LOCAL_CODE="";
		   String isBelongCp="";//是否路径项
		   String REPEAT_INDICATOR="";
		   String ORDER_CLASS="";
		   String LOCAL_ORDER_TEXT="";
		   String DOSAGE="";
		   String DOSAGE_UNITS="";
		   String ADMINISTRATION="";
		   String FREQUENCY="";
		   String DURATION="";
		   String DURATION_UNITS="";
		   String DOCTOR="";
		   String NURSE="";
		   String FREQ_COUNTER="";
		   String FREQ_INTERVAL_UNIT="";
	   JSONObject jsonObj=new JSONObject();
	   JSONArray rows=new JSONArray();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<counts;i++){
			isBelongCp=dataSet.FunGetDataAsStringByColName(i, "BELONG");
			if(isBelongCp!="0"){
        		isBelongCp="●";
        	}else{
        		isBelongCp="";
        	}
			LOCAL_KEY=dataSet.FunGetDataAsStringByColName(i, "LOCAL_KEY");
			LOCAL_CODE=dataSet.FunGetDataAsStringByColName(i, "LOCAL_CODE");
			REPEAT_INDICATOR=dataSet.FunGetDataAsStringByColName(i, "REPEAT_INDICATOR");
			if(REPEAT_INDICATOR.equals("1")){
				REPEAT_INDICATOR="长期";
        	}else{
        		REPEAT_INDICATOR="临时";
        	}
			ORDER_CLASS=dataSet.FunGetDataAsStringByColName(i, "ORDER_CLASS");
			LOCAL_ORDER_TEXT=dataSet.FunGetDataAsStringByColName(i, "LOCAL_ORDER_TEXT");
			DOSAGE=dataSet.FunGetDataAsStringByColName(i, "DOSAGE");
			DOSAGE_UNITS=dataSet.FunGetDataAsStringByColName(i, "DOSAGE_UNITS");
			ADMINISTRATION=dataSet.FunGetDataAsStringByColName(i, "ADMINISTRATION");
			FREQUENCY=dataSet.FunGetDataAsStringByColName(i, "FREQUENCY");
			DURATION=dataSet.FunGetDataAsStringByColName(i, "DURATION");
			DURATION_UNITS=dataSet.FunGetDataAsStringByColName(i, "DURATION_UNITS");
			DOCTOR=dataSet.FunGetDataAsStringByColName(i, "DOCTOR");
			Date date= dataSet.FunGetDataAsDateByColName(i, "EXE_DATE");
			if(date!=null){
				EXE_DATE=sdf.format(date);
			}
			
			NURSE=dataSet.FunGetDataAsStringByColName(i, "NURSE");
			FREQ_COUNTER=dataSet.FunGetDataAsStringByColName(i, "FREQ_COUNTER");
			FREQ_INTERVAL_UNIT=dataSet.FunGetDataAsStringByColName(i, "FREQ_INTERVAL_UNIT");
			
			JSONObject cell=new JSONObject();
			cell.put("isBelongCp", isBelongCp);
			cell.put("LOCAL_KEY", LOCAL_KEY);
			cell.put("LOCAL_CODE", LOCAL_CODE);
			cell.put("REPEAT_INDICATOR", REPEAT_INDICATOR);
			cell.put("ORDER_CLASS", ORDER_CLASS);
			cell.put("LOCAL_ORDER_TEXT", LOCAL_ORDER_TEXT);
			cell.put("DOSAGE", DOSAGE);
			cell.put("DOSAGE_UNITS", DOSAGE_UNITS);
			cell.put("ADMINISTRATION", ADMINISTRATION);
			cell.put("FREQUENCY", FREQUENCY);
			cell.put("DURATION", DURATION);
			cell.put("DURATION_UNITS", DURATION_UNITS);
			cell.put("DOCTOR", DOCTOR);
			cell.put("EXE_DATE", EXE_DATE);
			cell.put("NURSE", NURSE);
			cell.put("FREQ_COUNTER", FREQ_COUNTER);
			cell.put("FLAG", "/");
			cell.put("FREQ_INTERVAL_UNIT", FREQ_INTERVAL_UNIT);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().println(jsonObj.toString());
	}
	
	/**
	 * 护理记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void nurse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=request.getParameter("patientNo");
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		String nurseInfoSql="select a.*,b.cp_id belong   from lcp_patient_log_nurse a, lcp_patient_nurse_item b  where a.nurse_no = b.nurse_no(+)    and a.hospital_id = b.hospital_id(+)    and a.patient_no = '"+patient_no+"'    and a.patient_no=b.patient_no(+) ";
		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		int rowCount = db.FunGetRowCountBySql(nurseInfoSql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}
		DataSetClass dataSet=db.FunGetPageDataSetBySQL(nurseInfoSql,startNum,endNum);
		int counts=dataSet.FunGetRowCount();
		   String LOCAL_KEY="";
		   String EXE_DATE="";
		   String LOCAL_CODE="";
		   String isBelongCp="";//是否路径项
		   String LOCAL_NAME="";
		   String USER_NAME="";
		   String NURSE_NO="";
	   JSONObject jsonObj=new JSONObject();
	   JSONArray rows=new JSONArray();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<counts;i++){
			
			LOCAL_KEY=dataSet.FunGetDataAsStringByColName(i, "LOCAL_KEY");
			LOCAL_CODE=dataSet.FunGetDataAsStringByColName(i, "LOCAL_CODE");
			LOCAL_NAME=dataSet.FunGetDataAsStringByColName(i, "LOCAL_NAME");
			isBelongCp=dataSet.FunGetDataAsStringByColName(i, "BELONG");
			if(isBelongCp!="0"){
        		isBelongCp="●";
        	}else{
        		isBelongCp="";
        	}
			USER_NAME=dataSet.FunGetDataAsStringByColName(i, "USER_NAME");
			Date date= dataSet.FunGetDataAsDateByColName(i, "EXE_DATE");
			if(date!=null){
				EXE_DATE=sdf.format(date);
			}
			
			NURSE_NO=dataSet.FunGetDataAsStringByColName(i, "NURSE_NO");
			
			JSONObject cell=new JSONObject();
			cell.put("serialNo", (i+1));
			cell.put("LOCAL_KEY", LOCAL_KEY);
			cell.put("LOCAL_CODE", LOCAL_CODE);
			cell.put("LOCAL_NAME", LOCAL_NAME);
			cell.put("isBelongCp", isBelongCp);
			cell.put("USER_NAME", USER_NAME);
			cell.put("EXE_DATE", EXE_DATE);
			cell.put("NURSE_NO", NURSE_NO);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().println(jsonObj.toString());
	}
	
	/**
	 * 相关病例
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logEmr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String patient_no=request.getParameter("patientNo");
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		String doctorInfoSql="select a.*,b.cp_id belong   from lcp_patient_log_doctor a, lcp_patient_doctor_item b  where a.doctor_no = b.doctor_no(+)    and a.hospital_id = b.hospital_id(+)    and a.patient_no = '"+patient_no+"'    and a.patient_no=b.patient_no(+)  ";
		int startNum = pageRows * (nowPage - 1) + 1;// 开始查询页数
		int endNum = pageRows * nowPage;// 结束查询页数
		int rowCount = db.FunGetRowCountBySql(doctorInfoSql);
		int countPage = rowCount / pageRows;
		if (rowCount % pageRows != 0) {
			countPage++;
		}
		DataSetClass dataSet=db.FunGetPageDataSetBySQL(doctorInfoSql,startNum,endNum);
		int counts=dataSet.FunGetRowCount();
		   String LOCAL_KEY="";
		   String EXE_DATE="";
		   String LOCAL_CODE="";
		   String checkFlag="";
		   String LOCAL_NAME="";
		   String DOCTOR_CONTEXT_ID="";
	   JSONObject jsonObj=new JSONObject();
	   JSONArray rows=new JSONArray();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<counts;i++){
			
			LOCAL_KEY=dataSet.FunGetDataAsStringByColName(i, "LOCAL_KEY");
			LOCAL_CODE=dataSet.FunGetDataAsStringByColName(i, "LOCAL_CODE");
			LOCAL_NAME=dataSet.FunGetDataAsStringByColName(i, "LOCAL_NAME");
			DOCTOR_CONTEXT_ID=dataSet.FunGetDataAsStringByColName(i, "DOCTOR_CONTEXT_ID");
			if(!DOCTOR_CONTEXT_ID.equals("0") && DOCTOR_CONTEXT_ID!=null){
				checkFlag="<span class='STYLE10'><a href='../ZhikongPatientDoctorServlet?local_key="+LOCAL_KEY+"&id="+DOCTOR_CONTEXT_ID+"'>查看</a></span>";
			}
			Date date= dataSet.FunGetDataAsDateByColName(i, "EXE_DATE");
			if(date!=null){
				EXE_DATE=sdf.format(date);
			}
			
			JSONObject cell=new JSONObject();
			cell.put("LOCAL_KEY", LOCAL_KEY);
			cell.put("LOCAL_CODE", LOCAL_CODE);
			cell.put("LOCAL_NAME", LOCAL_NAME);
			cell.put("EXE_DATE", EXE_DATE);
			cell.put("checkFlag", checkFlag);
			rows.add(cell);
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("UTF-8");
		
		response.getWriter().println(jsonObj.toString());
	}

}
