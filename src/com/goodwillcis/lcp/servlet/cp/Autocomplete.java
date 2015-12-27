package com.goodwillcis.lcp.servlet.cp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;

/**
 * @author boanerges
 *
 */
public class Autocomplete extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String ops=request.getParameter("ops");
		////System.out.println(ops);
		String callBack="";
			if("code".equals(ops)){
				
				callBack= selectOrderCode( request,  response);
			}else if("orderPC".equals(ops)){
				 callBack	= selectOrderPC( request,  response);
			}else if("orderWay".equals(ops)){
				 callBack	= selectOrderWay( request,  response);
			
			}else{
			   callBack	= selectOther( request,  response);
			}
			
			//System.out.println(callBack); 
			response.getWriter().println(callBack);
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		doGet(request,response);
		
	}
		
	
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * 查询医嘱途径
	 */
	private String selectOrderWay(HttpServletRequest request, HttpServletResponse response){
		String select=request.getParameter("q").toUpperCase();
		
			DatabaseClass db=LcpUtil.getDatabaseClass();
			String temp="[";
		
		String selectSQL = "select supply_code,supply_name,py_code from lcp_local_order_way   " +
				" where py_code like '%" + select + "%' order by length(py_code)" ;
		//System.out.println(selectSQL);
		try {
			DataSetClass ds = db.FunGetDataSetBySQL(selectSQL, 15);
			if (ds.FunGetDataAsStringById(0, 0) != "") {
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String end = ",";
					if (i == (ds.FunGetRowCount() - 1)) {
						end = "]";
					}
					String code = ds.FunGetDataAsStringByColName(i, "SUPPLY_CODE");
					String name = ds.FunGetDataAsStringByColName(i, "SUPPLY_NAME");
					String py = ds.FunGetDataAsStringByColName(i, "PY_CODE");
					
						temp = temp + "{\"code\":\"" + code + "\","
								+ "\"name\":\"" + name + "\","
								+ "\"input\":\"" + py + "\"}" + end;
					
				}
			} else {
				temp = "[{\"code\":\"-\"," + "\"name\":\"无对应项,请重新输入\","
						+ "\"input\":\"\"}]";
			}
			return temp;
		} catch (Exception e) {
			//System.out.println("DB ERROR!");
			temp = "[{\"code\":\"-\"," + "\"name\":\"无法连接服务器,请稍后重新尝试\","
					+ "\"input\":\"\"}]";
			return temp;
		}
	}
  
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * 查询医嘱频次
	 */
	private String selectOrderPC(HttpServletRequest request, HttpServletResponse response){
		String select=request.getParameter("q").toUpperCase();
		
			DatabaseClass db=LcpUtil.getDatabaseClass();
			String temp="[";
		
		String selectSQL = "select trim(code) as CODE,COMM from lcp_local_order_frequency  " +
				" where code like '%" + select + "%' order by code" ;
		//System.out.println(selectSQL);
		try {
			DataSetClass ds = db.FunGetDataSetBySQL(selectSQL, 10);
			if (ds.FunGetDataAsStringById(0, 0) != "") {
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String end = ",";
					if (i == (ds.FunGetRowCount() - 1)) {
						end = "]";
					}
					String code = ds.FunGetDataAsStringByColName(i, "CODE");
					String name = ds.FunGetDataAsStringByColName(i, "COMM");
					
						temp = temp + "{\"code\":\"" + code + "\","
								+ "\"name\":\"" + name + "\","
								+ "\"input\":\"" + code + "\"}" + end;
					
				}
			} else {
				temp = "[{\"code\":\"-\"," + "\"name\":\"无对应项,请重新输入\","
						+ "\"input\":\"\"}]";
			}
			return temp;
		} catch (Exception e) {
			//System.out.println("DB ERROR!");
			temp = "[{\"code\":\"-\"," + "\"name\":\"无法连接服务器,请稍后重新尝试\","
					+ "\"input\":\"\"}]";
			return temp;
		}
	}
	
	
	/**
	 * 根据编码查询医嘱
	 * @param request
	 * @param response
	 * @return
	 */
	private String selectOrderCode(HttpServletRequest request, HttpServletResponse response){
		String select=request.getParameter("q").toUpperCase();
		
			DatabaseClass db=LcpUtil.getDatabaseClass();
			String temp="[";
		
		String selectSQL = "select order_item_code,order_item_name,specification,input_code_py,input_code_wb " +
				"from dcp_dict_order_item where order_item_code like '%" + select + "%' order by order_item_code" ;
		//System.out.println(selectSQL);
		try {
			DataSetClass ds = db.FunGetDataSetBySQL(selectSQL, 10);
			if (ds.FunGetDataAsStringById(0, 0) != "") {
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String end = ",";
					if (i == (ds.FunGetRowCount() - 1)) {
						end = "]";
					}
					String dept_code = ds.FunGetDataAsStringByColName(i, "ORDER_ITEM_CODE");
					String dept_name = ds.FunGetDataAsStringByColName(i, "ORDER_ITEM_NAME");
					String specification = ds.FunGetDataAsStringByColName(i, "SPECIFICATION");
					
						temp = temp + "{\"code\":\"" + dept_code + "\","
								+ "\"name\":\"" + dept_name + "\","
								+ "\"spec\":\"" + specification + "\","
								+ "\"input\":\"" + dept_code + "\"}" + end;
					
				}
			} else {
				temp = "[{\"code\":\"-\"," + "\"name\":\"-\","+"\"spec\":\"无对应项,请重新输入\","
						+ "\"input\":\"\"}]";
			}
			return temp;
		} catch (Exception e) {
			//System.out.println("DB ERROR!");
			temp = "[{\"code\":\"-\"," + "\"name\":\"-\","+"\"spec\":\"无法连接服务器,请稍后重新尝试\","
					+ "\"input\":\"\"}]";
			return temp;
		}
	}

	
	private String selectOther(HttpServletRequest request, HttpServletResponse response){
		String select=request.getParameter("q").toUpperCase();
		String ops=request.getParameter("ops");
		String op=request.getParameter("op");
		String option=request.getParameter("option");
		
			DatabaseClass db=LcpUtil.getDatabaseClass();
			String temp="[";
			String code="";
			String name="";
			String spec="";
			String dosunits="";
			String tableName="";
			String selectSQL="";
			String input="input_code_py";
			if("wb".equals(ops)){
				input="input_code_wb";
				//System.out.println(input);
			}
			
			if("diagnosis".equals(op)){//查询诊断
				code="DIAGNOSIS_CODE";
				name="DIAGNOSIS_NAME";
				tableName="dcp_dict_diagnosis";
				
			}else if("operation".equals(op)){//查询手术
				code="OPERATION_CODE";
				name="OPERATION_NAME";
				tableName="DCP_DICT_OPERATION";
			}else if("dept".equals(op)){//查询科室
				code="DEPT_CODE";
				name="DEPT_NAME";
				tableName="LCP_LOCAL_DEPT";
			}else if("doctor".equals(op)){
				code="DOCTOR_CODE";
				name="DOCTOR_NAME";
				tableName="DCP_DICT_DOCTOR";
			}else if("nurse".equals(op)){//查询护士工作
				code="NURSE_CODE";
				name="NURSE_NAME";
				tableName="DCP_DICT_NURSE";
			
			}else if("lcpmaster".equals(op)){
				code="CP_ID";
				name="CP_NAME";
				tableName="DCP_MASTER";
			}else if("diagnosisCode".equals(op)){
				code="DIAGNOSIS_CODE";
				name="DIAGNOSIS_NAME";
				tableName="dcp_dict_diagnosis";
				input="DIAGNOSIS_CODE";
			}else if("operationCode".equals(op)){
				code="OPERATION_CODE";
				name="OPERATION_NAME";
				tableName="DCP_DICT_OPERATION";
				input="OPERATION_CODE";
			}else if("order".equals(op)){//录入医嘱
				code="ORDER_ITEM_CODE";
				name="ORDER_ITEM_NAME";
				tableName="DCP_DICT_ORDER_ITEM";
			}else if("selectUnit".equals(op)){//领量
				code="CODE";
				name="UNIT";
				tableName="LCP_LOCAL_ORDER_DOSAGEUNITS";
			}else if("codePY".equals(op)){
				code="ORDER_ITEM_CODE";
				name="ORDER_ITEM_NAME";
				spec="SPECIFICATION";
				dosunits="UNIT";
				tableName="DCP_DICT_ORDER_ITEM";
			}else if("codeBM".equals(op)){
				code="ORDER_ITEM_CODE";
				name="ORDER_ITEM_NAME";
				spec="SPECIFICATION";
				dosunits="UNIT";
				tableName="DCP_DICT_ORDER_ITEM";
			}
		
		if(("codePY").equals(op) || ("codeBM").equals(op)){
			if("dim".equals(option)){
				selectSQL = "select " + name + "," + code
				+ ","+spec+",INPUT_CODE_PY,INPUT_CODE_WB,CHARGE_AMOUNT from " + tableName + " where effect_flag = 0 and "
				+ input + " like '%" + select + "%'";
			}else{
				selectSQL = "select " + name + "," + code
				+ ","+spec+",INPUT_CODE_PY,INPUT_CODE_WB,CHARGE_AMOUNT from " + tableName + " where effect_flag = 0 and "
				+ input + " like '" + select + "%' order by length(trim(ORDER_ITEM_NAME))";
			}
//			selectSQL = "select b.UNIT as UNIT,a.ORDER_ITEM_CODE as ORDER_ITEM_CODE,a.ORDER_ITEM_NAME as ORDER_ITEM_NAME," +
//					" a.SPECIFICATION as SPECIFICATION,a.INPUT_CODE_PY as INPUT_CODE_PY " +
//					" from dcp_dict_order_item a,lcp_local_order_dosageunits b " +
//					" where a.dosage_units=b.code and a.INPUT_CODE_PY like '%" + select + "%' order by length(a.INPUT_CODE_PY)";
			
			
//			System.out.println("============:"+selectSQL);

		}else if("diagnosis".equals(op) || "diagnosisCode".equals(op)){
			selectSQL = "select " + name + "," + code
			+ ",INPUT_CODE_PY,INPUT_CODE_WB from " + tableName + " where "
			+ input + " like '" + select + "%' order by length(trim(DIAGNOSIS_NAME))";
		}
		else{
			selectSQL = "select " + name + "," + code
				+ ",INPUT_CODE_PY,INPUT_CODE_WB from " + tableName + " where "
				+ input + " like '" + select + "%'";

		}
		////System.out.println(selectSQL);
		try {
			DataSetClass ds = db.FunGetDataSetBySQL(selectSQL, 80);
//			System.out.println("=--------------:"+ds.FunGetRowCount());
			if (ds.FunGetDataAsStringById(0, 0) != "") {
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					String end = ",";
					if (i == (ds.FunGetRowCount() - 1)) {
						end = "]";
					}
					String dept_code = ds.FunGetDataAsStringByColName(i, code);
					String dept_name = ds.FunGetDataAsStringByColName(i, name).replaceAll("\"", "^");
					String input_code_py = ds.FunGetDataAsStringByColName(i,
							"INPUT_CODE_PY");
					String input_code_wb = ds.FunGetDataAsStringByColName(i,
							"INPUT_CODE_WB");
					String specification=ds.FunGetDataAsStringByColName(i, "SPECIFICATION");
					String dosage_units=ds.FunGetDataAsStringByColName(i, dosunits);
					Number charge_amount = ds.FunGetDataAsNumberByColName(i, "CHARGE_AMOUNT");
					if ("py".equals(ops)) {
						temp = temp + "{\"code\":\"" + dept_code + "\","
								+ "\"name\":\"" + dept_name + "\","
								+ "\"input\":\"" + input_code_py + "\"}" + end;
					} else if ("wb".equals(ops)) {
						temp = temp + "{\"code\":\"" + dept_code + "\","
								+ "\"name\":\"" + dept_name + "\","
								+ "\"input\":\"" + input_code_wb + "\"}" + end;
					} else if ("codes".equals(ops)) {
						temp = temp + "{\"code\":\"" + dept_code + "\","
								+ "\"name\":\"" + dept_name + "\"" + "}" + end;
					}else if("jl".equals(ops)){
						temp = temp + "{\"code\":\"" + dept_code + "\","
						+ "\"name\":\"" + dept_name + "\","
						+ "\"input\":\"" + input_code_py + "\"}" + end;
					}else if("cd".equals(ops)){
						temp = temp + "{\"code\":\"" + dept_code + "\","
						+ "\"name\":\"" + dept_name + "\","
						+ "\"spec\":\"" + specification + "\","
						+ "\"charge_amount\":\"￥" + charge_amount + "\","
						//+ "\"input\":\"" + input_code_py + "\","
						//+ "\"dosunits\":\"" + dosage_units + "\"}" + end;
						+ "\"input\":\"" + input_code_py + "\"}" + end;
					}else if("bm".equals(ops)){
						temp = temp + "{\"code\":\"" + dept_code + "\","
						+ "\"name\":\"" + dept_name + "\","
						+ "\"spec\":\"" + specification + "\","
						//+ "\"input\":\"" + dept_code + "\","
						//+ "\"dosunits\":\"" + dosage_units + "\"}" + end;
						+ "\"input\":\"" + dept_code + "\"}" + end;
					}
				}
			} else {
				temp = "[{\"code\":\"-\"," + "\"name\":\"无对应项,请重新输入\","
						+ "\"input\":\"\"}]";
			}
			return temp;
		} catch (Exception e) {
			//System.out.println("DB ERROR!");
			temp = "[{\"code\":\"-\"," + "\"name\":\"无法连接服务器,请稍后重新尝试\","
					+ "\"input\":\"\"}]";
			return temp;
		}

	}
}
