/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：MatchOperaServlet.java
// 文件功能描述： 手术匹配表servlet
// 创建人：刘植鑫 
// 创建日期：2011/07/26
//修改sql语句，表结构发生变化
//修改人：刘植鑫
//修改时间：2011-08-25
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.servlet.macth;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.model.PageTable;
import com.goodwillcis.lcp.service.match.MatchLocal;
import com.goodwillcis.lcp.service.match.MatchOpera;
import com.goodwillcis.lcp.service.match.impl.MatchLocalImpl;
import com.goodwillcis.lcp.service.match.impl.MatchOperaImpl;


public class MatchOperaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MatchOpera match=new MatchOperaImpl();
	private static int PAGESIZE=22;
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
		response.setCharacterEncoding("UTF-8");
		String operate=request.getParameter("op");
		if("getAllTable".equals(operate)){
			String pageNo_js=request.getParameter("pageNo");
			int pageNo=1;
			try {
				pageNo=Integer.parseInt(pageNo_js);
			} catch (Exception e) {
				// TODO: handle exception
				pageNo=1;
			}
			String sql="    SELECT A.* " +
				"   FROM DCP_DICT_OPERATION A " +
				"  ORDER BY A.OPERATION_CODE";
			int totalRecord=match.funGetCount(sql);
			Page page=new Page(totalRecord, pageNo, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			String tableHtml=match.funCreateTable(sql,start, end);
			PageTable pageTable=new PageTable();
			String pageHtml=pageTable.funGetPageHtml(totalRecord, nowPage, totalPage);
			MatchLocal matchlocalTable=new MatchLocalImpl();
			String localTable=matchlocalTable.funGetTable("select * from lcp_local_operation t","OPERATION_CODE","OPERATION_name","input_code_py","input_code_wb");
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"getAllTable\", " +
					"\"pageHtml\":\""+pageHtml+"\", " +
					"\"localTable\":\""+localTable+"\", " +
					"\"tableHtml\":\""+tableHtml+"\"}]");
		}
		if("pipeiutil".equals(operate)){
			String youxiajiaoHtml="";
			String localTable="";
			String zcl_ajax=URLDecoder.decode(request.getParameter("zcl_ajax"),"UTF-8").replace("%2B", "+");
			String _mingcheng="";
			String _pinyin="";
			String _wubi="";
			String _code="";
			if(zcl_ajax!=""){
				MatchLocal matchlocalTable=new MatchLocalImpl();
				localTable=matchlocalTable.funGetTable("select * from lcp_local_operation t","OPERATION_CODE","OPERATION_name","input_code_py","input_code_wb");
				youxiajiaoHtml=match.funCreateMatchTable(zcl_ajax);
				String sql="SELECT * FROM DCP_DICT_OPERATION T WHERE T.OPERATION_CODE='"+zcl_ajax+"'";
				DataSet _dataset=new DataSet(); 
				_dataset.funSetDataSetBySql(sql);
				_code=_dataset.funGetFieldByCol(0, "OPERATION_CODE");
				_mingcheng=_dataset.funGetFieldByCol(0, "OPERATION_NAME");
				_pinyin=_dataset.funGetFieldByCol(0, "INPUT_CODE_PY");
				_wubi=_dataset.funGetFieldByCol(0, "INPUT_CODE_WB");
			}
			
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"pipeiutil\", " +
					"\"_mingcheng\":\""+_mingcheng+"\", " +
					"\"_pinyin\":\""+_pinyin+"\", " +
					"\"_wubi\":\""+_wubi+"\", " +
					"\"_code\":\""+_code+"\", " +
					"\"localTable\":\""+localTable+"\", " +
					"\"youxiajiaoHtml\":\""+youxiajiaoHtml+"\"}]");
		}
		if("changeTable".equals(operate)){
			String zxksbm_ajax=URLDecoder.decode(request.getParameter("zxksbm_ajax"),"UTF-8").replace("%2B", "+");
			String zxksmc_ajax=URLDecoder.decode(request.getParameter("zxksmc_ajax"),"UTF-8").replace("%2B", "+");
			String zxkspy_ajax=URLDecoder.decode(request.getParameter("zxkspy_ajax"),"UTF-8").replace("%2B", "+");
			String zxkswb_ajax=URLDecoder.decode(request.getParameter("zxkswb_ajax"),"UTF-8").replace("%2B", "+");
			zxksbm_ajax=zxksbm_ajax.replace("'", "''");
			zxksmc_ajax=zxksmc_ajax.replace("'", "''");
			zxkspy_ajax=zxkspy_ajax.replace("'", "''");
			zxkswb_ajax=zxkswb_ajax.replace("'", "''");
			//System.out.println(zxksbm_ajax+"  "+zxksmc_ajax+"  "+zxkspy_ajax+"  "+zxkswb_ajax);			
			String sql="    SELECT A.* " +
				"   FROM DCP_DICT_OPERATION A " +
				" WHERE 1=1 ";
			if(zxksbm_ajax!=""&&zxksbm_ajax!=null){
				sql=sql+" and (A.OPERATION_CODE like '%"+zxksbm_ajax+"%' or A.OPERATION_CODE like '%"+zxksbm_ajax.toUpperCase()+"%' or A.OPERATION_CODE like '%"+zxksbm_ajax.toLowerCase()+"%')";
			}
			if(zxksmc_ajax!=""&&zxksmc_ajax!=null){
				if(zxksmc_ajax.indexOf("_")>=0||zxksmc_ajax.indexOf("%")>=0){
					zxksmc_ajax=zxksmc_ajax.replace("_", "/_");
					zxksmc_ajax=zxksmc_ajax.replace("%", "/%");
					zxksmc_ajax=zxksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.OPERATION_name like '%"+zxksmc_ajax+" ";
				}else{
					sql=sql+" and  A.OPERATION_name like '%"+zxksmc_ajax+"%' ";
				}
			}
			if(zxkspy_ajax!=""&&zxkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+zxkspy_ajax+"%' or input_code_py like '%"+zxkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+zxkspy_ajax.toLowerCase()+"%')";
			}
			if(zxkswb_ajax!=""&&zxkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+zxkswb_ajax+"%' or input_code_wb like '%"+zxkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+zxkswb_ajax.toLowerCase()+"%')";
			}
			sql=sql+"  ORDER BY A.OPERATION_CODE";
			//System.out.println(sql);
			String pageNo_js=URLDecoder.decode(request.getParameter("pageNo"),"UTF-8").replace("%2B", "+");
			int pageNo=1;
			try {
				pageNo=Integer.parseInt(pageNo_js);
			} catch (Exception e) {
				// TODO: handle exception
				pageNo=1;
			}
			int totalRecord=match.funGetCount(sql);
			Page page=new Page(totalRecord, pageNo, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			String tableHtml=match.funCreateTable(sql,start, end);
			PageTable pageTable=new PageTable();
			String pageHtml=pageTable.funGetPageHtml(totalRecord, nowPage, totalPage);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"changeTable\", " +
					"\"pageHtml\":\""+pageHtml+"\", " +
					"\"tableHtml\":\""+tableHtml+"\"}]");
		}
		if("zcchaxun".equals(operate)){//左侧查询
			String zxksbm_ajax=URLDecoder.decode(request.getParameter("zxksbm_ajax"),"UTF-8").replace("%2B", "+");
			String zxksmc_ajax=URLDecoder.decode(request.getParameter("zxksmc_ajax"),"UTF-8").replace("%2B", "+");
			String zxkspy_ajax=URLDecoder.decode(request.getParameter("zxkspy_ajax"),"UTF-8").replace("%2B", "+");
			String zxkswb_ajax=URLDecoder.decode(request.getParameter("zxkswb_ajax"),"UTF-8").replace("%2B", "+");
			zxksbm_ajax=zxksbm_ajax.replace("'", "''");
			zxksmc_ajax=zxksmc_ajax.replace("'", "''");
			zxkspy_ajax=zxkspy_ajax.replace("'", "''");
			zxkswb_ajax=zxkswb_ajax.replace("'", "''");
			//System.out.println(zxksbm_ajax+"  "+zxksmc_ajax+"  "+zxkspy_ajax+"  "+zxkswb_ajax);			
			String sql="    SELECT A.* " +
				"   FROM DCP_DICT_OPERATION A " +
				" WHERE 1=1 ";
			if(zxksbm_ajax!=""&&zxksbm_ajax!=null){
				sql=sql+" and (A.OPERATION_CODE like '%"+zxksbm_ajax+"%' or A.OPERATION_CODE like '%"+zxksbm_ajax.toUpperCase()+"%' or A.OPERATION_CODE like '%"+zxksbm_ajax.toLowerCase()+"%')";
			}
			if(zxksmc_ajax!=""&&zxksmc_ajax!=null){
				if(zxksmc_ajax.indexOf("_")>=0||zxksmc_ajax.indexOf("%")>=0){
					zxksmc_ajax=zxksmc_ajax.replace("_", "/_");
					zxksmc_ajax=zxksmc_ajax.replace("%", "/%");
					zxksmc_ajax=zxksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.OPERATION_name like '%"+zxksmc_ajax+" ";
				}else{
					sql=sql+" and  A.OPERATION_name like '%"+zxksmc_ajax+"%' ";
				}
			}
			if(zxkspy_ajax!=""&&zxkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+zxkspy_ajax+"%' or input_code_py like '%"+zxkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+zxkspy_ajax.toLowerCase()+"%')";
			}
			if(zxkswb_ajax!=""&&zxkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+zxkswb_ajax+"%' or input_code_wb like '%"+zxkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+zxkswb_ajax.toLowerCase()+"%')";
			}
			sql=sql+"  ORDER BY A.OPERATION_CODE";
			//System.out.println(sql);
			int totalRecord=match.funGetCount(sql);
			Page page=new Page(totalRecord, 1, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			String tableHtml=match.funCreateTable(sql,start, end);
			PageTable pageTable=new PageTable();
			String pageHtml=pageTable.funGetPageHtml(totalRecord, nowPage, totalPage);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"zcchaxun\", " +
					"\"tableHtml\":\""+tableHtml+"\", " +
					"\"pageHtml\":\""+pageHtml+"\"}]");
		}
		if("ycchaxun".equals(operate)){//右侧查询
			String bdksbm_ajax=URLDecoder.decode(request.getParameter("bdksbm_ajax"),"UTF-8").replace("%2B", "+");
			String bdksmc_ajax=URLDecoder.decode(request.getParameter("bdksmc_ajax"),"UTF-8").replace("%2B", "+");
			String bdkspy_ajax=URLDecoder.decode(request.getParameter("bdkspy_ajax"),"UTF-8").replace("%2B", "+");
			String bdkswb_ajax=URLDecoder.decode(request.getParameter("bdkswb_ajax"),"UTF-8").replace("%2B", "+");
			bdksmc_ajax=bdksmc_ajax.replace("'", "''");
			bdksbm_ajax=bdksbm_ajax.replace("'", "''");
			bdkspy_ajax=bdkspy_ajax.replace("'", "''");
			bdkswb_ajax=bdkswb_ajax.replace("'", "''");
			//System.out.println(bdksbm_ajax+"  "+bdksmc_ajax+"  "+bdkspy_ajax+"  "+bdkswb_ajax);
			String sql="select * from lcp_local_operation a where 1=1 ";
			if(bdksbm_ajax!=""&&bdksbm_ajax!=null){
				sql=sql+" and (A.OPERATION_CODE like '%"+bdksbm_ajax+"%' or A.OPERATION_CODE like '%"+bdksbm_ajax.toUpperCase()+"%' or A.OPERATION_CODE like '%"+bdksbm_ajax.toLowerCase()+"%')";
			}
			if(bdksmc_ajax!=""&&bdksmc_ajax!=null){
				if(bdksmc_ajax.indexOf("_")>=0||bdksmc_ajax.indexOf("%")>=0){
					bdksmc_ajax=bdksmc_ajax.replace("_", "/_");
						bdksmc_ajax=bdksmc_ajax.replace("%", "/%");
						bdksmc_ajax=bdksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.OPERATION_name like '%"+bdksmc_ajax+" ";
				}else{
					sql=sql+" and  A.OPERATION_name like '%"+bdksmc_ajax+"%' ";
				}
			}
			if(bdkspy_ajax!=""&&bdkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+bdkspy_ajax+"%' or input_code_py like '%"+bdkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+bdkspy_ajax.toLowerCase()+"%')";
			}
			if(bdkswb_ajax!=""&&bdkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+bdkswb_ajax+"%' or input_code_wb like '%"+bdkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+bdkswb_ajax.toLowerCase()+"%')";
			}
			//System.out.println(sql);
			MatchLocal matchlocalTable=new MatchLocalImpl();
			String localTable=matchlocalTable.funGetTable(sql,"OPERATION_CODE","OPERATION_name","input_code_py","input_code_wb");
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"ycchaxun\", " +
					"\"localTable\":\""+localTable+"\"}]");
		}
		if("xuanzhongzcl".equals(operate)){
			String youxiajiaoHtml="";
			String zcl_ajax=URLDecoder.decode(request.getParameter("zcl_ajax"),"UTF-8").replace("%2B", "+");
			//System.out.println("zcl_ajax="+zcl_ajax);
			if(zcl_ajax!="")
			youxiajiaoHtml=match.funCreateMatchTable(zcl_ajax);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"xuanzhongzcl\", " +
					"\"youxiajiaoHtml\":\""+youxiajiaoHtml+"\"}]");
		}
		if("tijiao".equals(operate)){
			String yxj_add_ajax=URLDecoder.decode(request.getParameter("yxj_add_ajax"),"UTF-8").replace("%2B", "+");
			String yxj_del_ajax=URLDecoder.decode(request.getParameter("yxj_del_ajax"),"UTF-8").replace("%2B", "+");
			//System.out.println(yxj_add_ajax+"==============="+yxj_del_ajax);
			String code="";
			boolean isSuc=true;
			if(yxj_add_ajax==""){
				code=yxj_del_ajax.split("_and_")[0];
			}else{
				code=yxj_add_ajax.split("_and_")[0];
			}
			if(yxj_add_ajax!=""){
				int insertRow=match.funPiPeiInsert(yxj_add_ajax);
				if(insertRow<0){
					isSuc=false;
				}
			}
			if(yxj_del_ajax!=""){
				int delRow=match.funDelMatch(yxj_del_ajax);
				if(delRow<0){
					isSuc=false;
				}
			}
			match.funUpdateYDD(code);
			int row=match.funGetCountByCode(code);
			String localCode=match.funGetLocalCodeMatched();
			if(isSuc){
				response.getWriter().println("[{\"result\":\"OK\"," +
						"\"row\":\""+row+"\", " +
						"\"method\":\"tijiao\"}]");
			}else{
				response.getWriter().println("[{\"result\":\"ERROR\"," +
						"\"row\":\""+row+"\", " +
						"\"localCode\":\""+localCode+"\", " +
						"\"method\":\"tijiao\"}]");
			}

		}
		if("befordDel".equals(operate)){
			String delStr=URLDecoder.decode(request.getParameter("delStr"),"UTF-8").replace("%2B", "+");
			int row=match.funFindIsUsedBeforeDel(delStr);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"row\":\""+row+"\", " +
					"\"method\":\"befordDel\"}]");
		}
	}

}