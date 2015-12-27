/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchCPServlet.java
// 文件功能描述： 路径匹配表servlet
// 创建人：张昆
// 创建日期：2012/05/10
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
import com.goodwillcis.lcp.service.match.MatchCP;
import com.goodwillcis.lcp.service.match.MatchCPLocal;
import com.goodwillcis.lcp.service.match.impl.MatchCPImpl;
import com.goodwillcis.lcp.service.match.impl.MatchCPLocalImpl;


public class MatchCPServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MatchCP match=new MatchCPImpl();
	private static int PAGESIZE=20;
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
			String sql="select t.cp_id,t.cp_name,t.cp_create_date,t.cp_master_id from dcp_master t order by t.cp_id,t.cp_master_id";
			int totalRecord=match.funGetCount(sql);
			Page page=new Page(totalRecord, pageNo, PAGESIZE);
			int nowPage=page.getNowPage();
			int start=page.getStart();
			int end=page.getEnd();
			int totalPage=page.getTotolPage();
			String tableHtml=match.funCreateTable(sql,start, end);
			PageTable pageTable=new PageTable();
			String pageHtml=pageTable.funGetPageHtml(totalRecord, nowPage, totalPage);
			MatchCPLocal matchlocalTable=new MatchCPLocalImpl();
			String localTable=matchlocalTable.funGetTable("select t.cp_id,t.cp_name,t.cp_version,t.cp_master_id from lcp_master t where trim(t.dept_code) <> '0' order by t.dept_code,t.cp_master_id asc","CP_ID","cp_name","cp_version","cp_master_id");
			//System.out.println("localTable=:"+localTable);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"method\":\"getAllTable\", " +
					"\"pageHtml\":\""+pageHtml+"\", " +
					"\"localTable\":\""+localTable+"\", " +
					"\"tableHtml\":\""+tableHtml+"\"}]");
			
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
			String sql="    SELECT A.* " +
				"   FROM DCP_MASTER A " +
				" WHERE  1=1 ";
			if(zxksbm_ajax!=""&&zxksbm_ajax!=null){
				sql=sql+" and (A.CP_ID like '%"+zxksbm_ajax+"%' or A.CP_ID like '%"+zxksbm_ajax.toUpperCase()+"%' or A.CP_ID like '%"+zxksbm_ajax.toLowerCase()+"%')";
			}
			if(zxksmc_ajax!=""&&zxksmc_ajax!=null){
				if(zxksmc_ajax.indexOf("_")>=0||zxksmc_ajax.indexOf("%")>=0){
					zxksmc_ajax=zxksmc_ajax.replace("_", "/_");
					zxksmc_ajax=zxksmc_ajax.replace("%", "/%");
					zxksmc_ajax=zxksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.cp_name like '%"+zxksmc_ajax+" ";
				}else{
					sql=sql+" and  A.cp_name like '%"+zxksmc_ajax+"%' ";
				}
			}
			if(zxkspy_ajax!=""&&zxkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+zxkspy_ajax+"%' or input_code_py like '%"+zxkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+zxkspy_ajax.toLowerCase()+"%')";
			}
			if(zxkswb_ajax!=""&&zxkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+zxkswb_ajax+"%' or input_code_wb like '%"+zxkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+zxkswb_ajax.toLowerCase()+"%')";
			}
			sql=sql+"  ORDER BY A.CP_ID";
			String pageNo_js=request.getParameter("pageNo");
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
			String zxksbm_ajax=request.getParameter("zxksbm_ajax");
			
			
			zxksbm_ajax=URLDecoder.decode(zxksbm_ajax,"UTF-8").replace("%2B", "+");
			
			//System.out.println(zxksbm_ajax);
			String zxksmc_ajax=URLDecoder.decode(request.getParameter("zxksmc_ajax"),"UTF-8").replace("%2B", "+");
			String zxkspy_ajax=URLDecoder.decode(request.getParameter("zxkspy_ajax"),"UTF-8").replace("%2B", "+");
			String zxkswb_ajax=URLDecoder.decode(request.getParameter("zxkswb_ajax"),"UTF-8").replace("%2B", "+");
			zxksbm_ajax=zxksbm_ajax.replace("'", "''");
			zxksmc_ajax=zxksmc_ajax.replace("'", "''");
			zxkspy_ajax=zxkspy_ajax.replace("'", "''");
			zxkswb_ajax=zxkswb_ajax.replace("'", "''");
			String sql="    SELECT A.* " +
				"   FROM DCP_MASTER A" +
				" WHERE  1=1 ";
			if(zxksbm_ajax!=""&&zxksbm_ajax!=null){
				sql=sql+" and (A.CP_ID like '%"+zxksbm_ajax+"%' or A.CP_ID like '%"+zxksbm_ajax.toUpperCase()+"%' or A.CP_ID like '%"+zxksbm_ajax.toLowerCase()+"%')";
			}
			if(zxksmc_ajax!=""&&zxksmc_ajax!=null){
				if(zxksmc_ajax.indexOf("_")>=0||zxksmc_ajax.indexOf("%")>=0){
					zxksmc_ajax=zxksmc_ajax.replace("_", "/_");
					zxksmc_ajax=zxksmc_ajax.replace("%", "/%");
					zxksmc_ajax=zxksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.cp_name like '%"+zxksmc_ajax+" ";
				}else{
					sql=sql+" and  A.cp_name like '%"+zxksmc_ajax+"%' ";
				}
			}
			if(zxkspy_ajax!=""&&zxkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+zxkspy_ajax+"%' or input_code_py like '%"+zxkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+zxkspy_ajax.toLowerCase()+"%')";
			}
			if(zxkswb_ajax!=""&&zxkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+zxkswb_ajax+"%' or input_code_wb like '%"+zxkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+zxkswb_ajax.toLowerCase()+"%')";
			}
			sql=sql+"  ORDER BY A.CP_ID";

			int totalRecord=match.funGetCount(sql);
			//System.out.println(sql);
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
			String sql="select a.cp_id,a.cp_name,a.cp_version,a.cp_master_id from lcp_master a where 1=1";
			if(bdksbm_ajax!=""&&bdksbm_ajax!=null){
				sql=sql+" and (A.CP_ID like '%"+bdksbm_ajax+"%' or A.CP_ID like '%"+bdksbm_ajax.toUpperCase()+"%' or A.CP_ID like '%"+bdksbm_ajax.toLowerCase()+"%')";
			}
			if(bdksmc_ajax!=""&&bdksmc_ajax!=null){
				if(bdksmc_ajax.indexOf("_")>=0||bdksmc_ajax.indexOf("%")>=0){
					bdksmc_ajax=bdksmc_ajax.replace("_", "/_");
						bdksmc_ajax=bdksmc_ajax.replace("%", "/%");
						bdksmc_ajax=bdksmc_ajax+"%' escape '/'";
					sql=sql+" and  A.cp_name like '%"+bdksmc_ajax+" ";
				}else{
					sql=sql+" and  A.cp_name like '%"+bdksmc_ajax+"%' ";
				}
			}
			if(bdkspy_ajax!=""&&bdkspy_ajax!=null){
				sql=sql+" and (input_code_py like '%"+bdkspy_ajax+"%' or input_code_py like '%"+bdkspy_ajax.toUpperCase()+"%' or input_code_py like '%"+bdkspy_ajax.toLowerCase()+"%')";
			}
			if(bdkswb_ajax!=""&&bdkswb_ajax!=null){
				sql=sql+" and (input_code_wb like '%"+bdkswb_ajax+"%' or input_code_wb like '%"+bdkswb_ajax.toUpperCase()+"%' or input_code_wb like '%"+bdkswb_ajax.toLowerCase()+"%')";
			}
			MatchCPLocal matchlocalTable=new MatchCPLocalImpl();
			String localTable=matchlocalTable.funGetTable(sql,"CP_ID","cp_name","cp_version","cp_master_id");
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
		if("pipeiutil".equals(operate)){
			String youxiajiaoHtml="";
			String localTable="";
			String zcl_ajax=URLDecoder.decode(request.getParameter("zcl_ajax"),"UTF-8").replace("%2B", "+");
			String _mingcheng="";
			String _pinyin="";
			String _wubi="";
			String _code="";
			if(zcl_ajax!=""){
				MatchCPLocal matchlocalTable=new MatchCPLocalImpl();
				localTable=matchlocalTable.funGetTable("select t.cp_id,t.cp_name,t.cp_version,t.cp_master_id from lcp_master t order by t.cp_master_id","CP_ID","cp_name","cp_version","cp_master_id");
				youxiajiaoHtml=match.funCreateMatchTable(zcl_ajax);
				String sql="SELECT * FROM DCP_MASTER T WHERE T.CP_ID='"+zcl_ajax+"'";
				DataSet _dataset=new DataSet(); 
				_dataset.funSetDataSetBySql(sql);
				_code=_dataset.funGetFieldByCol(0, "CP_ID");
				_mingcheng=_dataset.funGetFieldByCol(0, "CP_NAME");
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
		if("tijiao".equals(operate)){
			String yxj_add_ajax=URLDecoder.decode(request.getParameter("yxj_add_ajax"),"UTF-8").replace("%2B", "+");
			String yxj_del_ajax=URLDecoder.decode(request.getParameter("yxj_del_ajax"),"UTF-8").replace("%2B", "+");
			//System.out.println("yxj_add_ajax=:"+yxj_add_ajax);
			//System.out.println("yxj_del_ajax=:"+yxj_del_ajax);
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
		if("delMatchCP".equals(operate)){
			String delStr=URLDecoder.decode(request.getParameter("delStr"),"UTF-8").replace("%2B", "+");
			int row=match.funFindIsUsedBeforeDel(delStr);
			response.getWriter().println("[{\"result\":\"OK\"," +
					"\"row\":\""+row+"\", " +
					"\"method\":\"befordDel\"}]");
		}
	}

}
