package com.goodwillcis.lcp.servlet.zhikong;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.cp.util.ExecuteProcedure;
import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.service.zhikong.ZhikongCpTongji;
import com.goodwillcis.lcp.service.zhikong.impl.ZhikongCpTongjiImpl;

public class ZhikongTongjiServlet extends HttpServlet {

	private static final int PAGESIZE=16;
	DatabaseClass db = LcpUtil.getDatabaseClass();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int total=0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("UTF-8");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String op=request.getParameter("op");
		
		if(op.equals("getZhiKongCPTongJidan")){
			getZhiKongCPTongJidan(request,response);
		}
		if(op.equals("getTable")){
			getTable(request, response);
		}
		if(op.equals("getPage")){
			getPage(request, response);
		}
		if(op.equals("getPage1")){
			getPage1(request, response);
		}
		if(op.equals("topnaru")){
			getTopNr(request, response);
		}
		if(op.equals("tianshuTongji")){
			tianshuTongji(request, response);
		}
		/***
		 * 住院天数分布率统计
		 */
		if(op.equals("tianshufblTongji")){
			tianshufblTongji(request, response);
		}
		/***
		 * 效果第一个图标统计
		 */
		if(op.equals("xiaoguoTongji1")){
			xiaoguoTongji1(request, response);
		}
		
		/***
		 * 效果第一个图标统计
		 */
		if(op.equals("xiaoguoTongji2")){
			xiaoguoTongji2(request, response);
		}
		/***
		 * 效果第一个图标统计
		 */
		if(op.equals("xiaoguoTongji3")){
			xiaoguoTongji3(request, response);
		}
		
		/***
		 * 效果第一个图标统计
		 */
		if(op.equals("bianyi1")){
			bianyi1(request, response);
		}
		
		/***
		 * 效果第3个图标统计
		 */
		if(op.equals("bianyi3")){
			bianyi3(request, response);
		}
		/***
		 * 效果第2个图标统计
		 */
		if(op.equals("bianyi2")){
			bianyi2(request, response);
		}
		/***
		 * 效果第4个图标统计
		 */
		if(op.equals("bianyi4")){
			bianyi4(request, response);
		}
		/***
		 * 经济学第1个图标统计
		 */
		if(op.equals("jingjixue1")){
			jingjixue1(request, response);
		}
		
		/***
		 * 经济学第2个图标统计
		 */
		if(op.equals("jingjixue2")){
			jingjixue2(request, response);
		}
		
		/***
		 * 经济学第3个图标统计
		 */
		if(op.equals("jingjixue3")){
			jingjixue3(request, response);
		}
		/***
		 * 效果第1个图标统计
		 */
		if(op.equals("kangshengsu1")){
			kangshengsu1(request, response);
		}
		
		/***
		 * 效果第1个图标统计
		 */
		if(op.equals("kangshengsu2")){
			kangshengsu2(request, response);
		}
		
		if(op.equals("fg1")){
			fg1(request, response);
		}
		
		if(op.equals("fg2")){
			fg2(request, response);
		}
		
		if(op.equals("fg3")){
			fg3(request, response);
		}
		if(op.equals("fg4")){
			fg4(request, response);
		}
		if(op.equals("dljtj")){
			dljtj(request,response);
		}
	}
	
	private void dljtj(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String s=request.getParameter("s");
		String e=request.getParameter("e");
		s=" and trunc(admission_date)>= to_date('"+s+"','yyyy-MM-dd') "; 
		e=" and trunc(admission_date)<= to_date('"+e+" 23:59:59','yyyy-MM-dd HH24:MI:SS') "; 
		String addSql=s+e;
//		String sql="select (select count(cp_id) dcp from dcp_master where sys_is_del=0) a,                  "+
//			"(select count(cp_master_id) from lcp_master where cp_status=0) b,     "+
//			"(select count(cp_id) from lcp_master where cp_master_id=cp_id and cp_start_date is not null) c,    "+
//			"(select count(patient_no) from lcp_patient_visit where conform_master_id>0 and cp_master_id > 0 and cp_state not in(0,99) "+addSql+" ) d, "+
//			"(select count( patient_no) from lcp_patient_visit where conform_master_id <>0  "+addSql+" ) e,                       "+
//			"(select count(*) from lcp_patient_visit  where 1=1 "+addSql+" ) f,(select count(*) from lcp_patient_visit where conform_master_id > 0 and cp_master_id > 0 and cp_state=11   "+addSql+") g "+
//			"from dual ";
		String sql="select (select count(cp_id) dcp from dcp_master where sys_is_del = 0) a,"+
				"(select count(cp_master_id) from lcp_master where cp_status = 0) b, "+
				"(select count(cp_id) from lcp_master where cp_master_id = cp_id and cp_start_date is not null) c, "+
				"(select count(*) narurenshu from v_cp_dept t, (select cp_master_id, execute_dept from " +
				"lcp_patient_visit where cp_master_id <> 0 "+addSql+") a " +
				"where t.cp_master_id = a.cp_master_id  and t.dept_name = a.execute_dept) d, "+
				"(select count(*) fhrs from v_cp_dept t,(select conform_master_id, execute_dept " +
				"from lcp_patient_visit where conform_master_id <> 0 "+addSql+") a where t.cp_master_id = " +
				"a.conform_master_id and t.dept_name = a.execute_dept) e, "+
				"(select count(*) from lcp_patient_visit where 1 = 1 "+addSql+") f, "+
				"(select count(*) wcrs from v_cp_dept t, (select cp_master_id, execute_dept " +
				"from lcp_patient_visit where cp_master_id <> 0 and cp_state=11 "+addSql+") a where t.cp_master_id = " +
				"a.cp_master_id and t.dept_name = a.execute_dept) g "+
				"from dual";

		System.out.println("====sql==:"+sql);
		DataSetClass dataSet=db.FunGetDataSetBySQL(sql);
		String dcpCount=dataSet.FunGetDataAsStringByColName(0,"A");//基本路径数
		String startCP=dataSet.FunGetDataAsStringByColName(0,"B");//启用路径数
		int selfCP=dataSet.FunGetDataAsNumberByColName(0,"C").intValue();//院内自定义路径数
		double nrrs=dataSet.FunGetDataAsNumberByColName(0,"D").doubleValue();
		double fhrs=dataSet.FunGetDataAsNumberByColName(0,"E").doubleValue();
		String totalRS=dataSet.FunGetDataAsStringByColName(0,"F");//住院总人数
		double wcrs=dataSet.FunGetDataAsNumberByColName(0,"G").doubleValue();
		DecimalFormat rs = new DecimalFormat("##0");
		String nrcount=rs.format(nrrs);//纳入人数
		String wccount=rs.format(wcrs);//完成人数
		String nrl = rs.format(Math.round(nrrs * 100 / fhrs));
		String wcl=  rs.format(Math.round(wcrs * 100 / nrrs));
		response.getWriter().println("[{\"dcpCount\":\""+dcpCount+"\"," +
				"\"startCP\":\""+startCP+"\",\"selfCP\":\""+selfCP+"\",\"nrrs\":\""+nrcount+"\"," +
						"\"total\":\""+totalRS+"\",\"nrl\":\""+nrl+"\",\"wc\":\""+wcl+"\"}]");
	}
	protected void fg4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String year=request.getParameter("year");
		if(year==null||"null".equals(year)){
			year="";
		}
		String month=request.getParameter("month");
		if(month==null||"null".equals(month)){
			month="";
		}
		String xml=tongji.funGetFPPC(year,month);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	protected void fg3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String year=request.getParameter("year");
		if(year==null||"null".equals(year)){
			year="";
		}
		String month=request.getParameter("month");
		if(month==null||"null".equals(month)){
			month="";
		}
		String xml=tongji.funGetFPWC(year,month);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}

	
	protected void fg2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String year=request.getParameter("year");
		if(year==null||"null".equals(year)){
			year="";
		}
		String month=request.getParameter("month");
		if(month==null||"null".equals(month)){
			month="";
		}
		String xml=tongji.funGetFPCpTongji(year,month);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void fg1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String year=request.getParameter("year");
		if(year==null||"null".equals(year)){
			year="";
		}
		String month=request.getParameter("month");
		if(month==null||"null".equals(month)){
			month="";
		}
		////////////System.out.println(year+"---"+month);
		String xml=tongji.funGetFPCpInfo(year,month);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}

	
	protected void bianyi1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetBianyiByCpIdUseXML1(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
	}

	protected void kangshengsu1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetKangshengsuCpIdUseXML1(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	protected void kangshengsu2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetKangshengsuCpIdUseXML2(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void bianyi4(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetBianyiByCpIdUseXML4(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void bianyi3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetBianyiByCpIdUseXML3(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void bianyi2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetBianyiByCpIdUseXML2(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	protected void getTable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//DatabaseClass db=LcpUtil.getDatabaseClass();
		response.setCharacterEncoding("UTF-8");
		String value=request.getParameter("searchString");
		//String value=URLDecoder.decode(s, "UTF-8");//查询的内容
		String flag=request.getParameter("searchField");//查询的列
		String start=request.getParameter("s");//开始时间
		String end = request.getParameter("e");//结束时间
		int pageRows = Integer.valueOf(request.getParameter("rows")).intValue();// 每页行数
		int nowPage = Integer.valueOf(request.getParameter("page")).intValue();// 当前页数
		boolean search=Boolean.valueOf(request.getParameter("_search"));
		String searchOper=request.getParameter("searchOper");//查询方式
		
		String sidx = request.getParameter("sidx");//要排序的行
		sidx=(sidx==null||sidx==""?"cp_id":sidx);
		String sord = request.getParameter("sord");//排序方式
		
		String opp=request.getParameter("cpType");//1 全部路径  2 局发路径  3 自定义路径
		int startNum = pageRows * (nowPage - 1) + 1;//开始查询页数
		int endNum = pageRows * nowPage;//结束查询页数
		ExecuteProcedure proc=ExecuteProcedure.getInstance();
		proc.executeProc(start, end);
		JSONObject jsonObj = new JSONObject();
		JSONArray rows=new JSONArray();
		String searchStr="";
		String orderbySql="";
		String addSql="";
		String sql="select t.*,decode(fhrs,0,0,round(nvl(trunc(nrrs /fhrs, 3),0) * 100)) nrl,decode(nrrs,0,0,round(nvl(trunc(wcrs /nrrs, 3), 0) * 100)) wcl from ( select cp_id,max(h_id) hid,max(cp_name) cp_name,sum(num_fuhe) fhrs,sum(num_naru) nrrs,sum(num_wancheng) wcrs,max(cp_start_date) cp_start_date,max(input_code_py) input_code_py from report_tj";
		String endSql= "  group by cp_id ) t  ";
		orderbySql=" order by "+sidx+" "+sord+"";
		if(search){
				if("cn".equals(searchOper)){
	        		addSql=" where "+flag+" like '%"+value+"%'";
	        	}
	        	if("eq".equals(searchOper)){
	        		if("cpType".equals(flag)){
	        			if("1".equals(value)){
	        				searchStr="  where cp_id<>0 " ;
	        			}else if("2".equals(value)){
	        				searchStr="  where cp_id<10000 " ;
	        			}else if("3".equals(value)){
	        				searchStr="  where cp_id>10000 " ;
	        			}
	        		}else{
	        			addSql=" where "+flag+" = '"+value+"'";
	        		}
	        		
	        	}
	        	sql=sql+searchStr+addSql+endSql+orderbySql;
		}else{
			searchStr="  where cp_id<10000 " ;
			sql=sql+searchStr+endSql+orderbySql;
		}
		
		//System.out.println("sql==:"+sql);
		int rowCount = db.FunGetRowCountBySql(sql);
		int countPage=rowCount / pageRows;
		if(rowCount%pageRows!=0){
			countPage++;
		}
		jsonObj.put("page", nowPage); // 当前页
		jsonObj.put("total", countPage); // 总页数
		jsonObj.put("records", rowCount); // 总记录数
		////System.out.println("sql===:"+sql);
		DataSetClass dataSet = db.FunGetPageDataSetBySQL(sql, startNum, endNum);
		String cpType="";
		int CPID=0;
		String cpName="";
		String startDate="";
		String fhrs="0";
		String nrrs="0";
		String wcrs="";
		String nrl="0";
		String wcl="0";
		int counts=dataSet.FunGetRowCount();
		for(int i=0;i<counts;i++){
			CPID=dataSet.FunGetDataAsNumberByColName(i, "CP_ID").intValue();
			cpName=dataSet.FunGetDataAsStringByColName(i, "CP_NAME");
			startDate=dataSet.FunGetDataAsStringByColName(i, "CP_START_DATE");
			if(startDate!=""){
				startDate=startDate.substring(0,10);
			}
			fhrs=dataSet.FunGetDataAsStringByColName(i, "FHRS");
			nrrs=dataSet.FunGetDataAsStringByColName(i, "NRRS");
			wcrs=dataSet.FunGetDataAsStringByColName(i, "WCRS");	
			if(!fhrs.equals("0")){
				nrl=dataSet.FunGetDataAsStringByColName(i, "NRL");
				wcl=dataSet.FunGetDataAsStringByColName(i,"WCL");
			}else{
				nrl="0";
				wcl="0";
			}
			
			cpType="局发路径";
			if(CPID>10000){
				cpType="自定义路径";
			}
			JSONObject cell= new JSONObject();
			cell.put("cpType", cpType);
			cell.put("CPID", CPID);
			cell.put("cpName", cpName);
			cell.put("startDate", startDate);
			cell.put("fhrs", fhrs);
			cell.put("nrrs", nrrs);
			cell.put("wcrs", wcrs);
			cell.put("nrl", nrl);
			cell.put("wcl", wcl);
			rows.add(cell);
		}
		jsonObj.put("rows", rows);
		request.setCharacterEncoding("UTF-8");
		response.getWriter().println(jsonObj.toString());
		
}
	protected void getTopNr(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String top=request.getParameter("top");
		String num=request.getParameter("num");
		String starttime=request.getParameter("startdate").replace("\'", "");
		String endtime=request.getParameter("enddate").replace("\'", "");
		String xml="";
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		if(top.equals("max")){
			xml=tongji.funGetTopNaruUseXML(true, Integer.parseInt(num),starttime,endtime);
		}else{
			xml=tongji.funGetTopNaruUseXML(false, Integer.parseInt(num),starttime,endtime);

		}
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void tianshuTongji(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetTianshuByCId(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}

	/**
	 * 住院天数分布率统计
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void tianshufblTongji(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetZhuYuanFenblByCpIdUseXML(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	/**
	 * 经济学第一个图标统计
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void jingjixue1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetJingjixueCpIdUseXML1(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void jingjixue2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetJingjixueCpIdUseXML2(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}

	protected void jingjixue3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetJingjixueCpIdUseXML3(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}

	/**
	 * 效果第一个图标统计
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void xiaoguoTongji1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetXiaoguoByCpIdUseXML(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	/**
	 * 效果第一个图标统计
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void xiaoguoTongji2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetXiaoguoByCpIdUseXML2(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	
	/**
	 * 效果第一个图标统计
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void xiaoguoTongji3(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cp_id=request.getParameter("cp_id");
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String xml=tongji.funGetXiaoguoByCpIdUseXML3(cp_id);
		PrintWriter out = response.getWriter();
		out.write(xml);
		out.close();
		}
	
	protected void getPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value=request.getParameter("value");
		String flag=request.getParameter("flag");
		String pageNo=request.getParameter("pageNo");
		String start=request.getParameter("startvalue");
		String end = request.getParameter("endvalue");
		int page_no=1;
		try {
			page_no=Integer.parseInt(pageNo);
		} catch (Exception e) {
			// TODO: handle exception
			page_no=1;
		}
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String sql=tongji.funGetCpSQL(value, flag,start,end);
		int record=tongji.funGetCpCount(sql);
		Page page=new Page(record, page_no, PAGESIZE);
		String pageHtml=page.funGetPageHtml();
		PrintWriter out = response.getWriter();
		out.println(pageHtml);
		out.close();
		}
	
	protected void getPage1(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value=request.getParameter("value");
		String flag=request.getParameter("flag");
		String pageNo=request.getParameter("pageNo");
		String hospital_id=request.getParameter("hos");
		String start=request.getParameter("startvalue");
		String end = request.getParameter("endvalue");
		int page_no=1;
		try {
			page_no=Integer.parseInt(pageNo);
		} catch (Exception e) {
			// TODO: handle exception
			page_no=1;
		}
		ZhikongCpTongji tongji=new ZhikongCpTongjiImpl();
		String sql=tongji.funGetCpSQL(value, flag,start,end);
		int record=tongji.funGetCpCount(sql);
		Page page=new Page(record, page_no, PAGESIZE);
		String pageHtml=page.funGetPageHtml();
		PrintWriter out = response.getWriter();
		out.println(pageHtml);
		out.close();
		}
	
	protected void getZhiKongCPTongJidan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String one = request.getParameter("one");
		String cp_id = request.getParameter("cp_id");
		int cpid=Integer.valueOf(cp_id);
	
		if ("1".equals(one)) {
			String s = request.getParameter("s");// 开始时间
			String e = request.getParameter("e");// 结束时间
		
			String date_start = "to_date('" + s + "','yyyy-mm-dd')";
			String date_end = "to_date('" + e + "','yyyy-mm-dd')";
			DataSet tianshu = new DataSet();
			String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
					+ cp_id ;

			if (cpid<10000) {
				sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from dcp_master t  where t.cp_id = "
						+ cp_id ;

			}
			JSONObject rows = new JSONObject();
			//DatabaseClass db = LcpUtil.getDatabaseClass();
			tianshu.funSetDataSetBySql(sql);
			String cp_name = tianshu.funGetFieldByCol(0, "CP_NAME");
			String cp_days = tianshu.funGetFieldByCol(0, "CP_DAYS");
			String cp_fee = tianshu.funGetFieldByCol1(0, "CP_FEE");

			rows.put("cp_name", cp_name);
			rows.put("cp_days", cp_days);
			rows.put("cp_fee", cp_fee);
			// 平均住院日统计
			// 纳入病例的平均住院日 纳入病例的最小住院日 纳入病例的最大住院日
			// 不纳入路径的平均住院日 不纳入路径的最小住院日 不纳入路径的最大住院日

			String tianshusql = " select  round(avg(d),1) a, round(min(d),1) b , round( max(d),1) c "
					+ "   from (select (t.discharged_date - t.admission_date) d            "
					+ "           from lcp_patient_visit t       where t.cp_master_id =          "
					+ cp_id + "            and trunc(t.admission_date)>= " + date_start + "            and trunc(t.admission_date)<= "
					+ date_end + "            and t.discharged_date is not null)     where d>0                  "
					+ " union all                         "
					+ " select  round(avg(d),1) a, round(min(d),1) b, round( max(d),1) c   "
					+ "   from (select (t.discharged_date - t.admission_date) d            "
					+ "           from lcp_patient_visit t"
					+ "          where t.conform_master_id =       " + cp_id
					+ "            and t.cp_master_id = 0 "
					+ "              and trunc(t.admission_date)>= " + date_start + "            and trunc(t.admission_date)<= " + date_end
					+ "            and t.discharged_date is not null)      where d>0                 ";
	//		//////////System.out.println("tsssssss" + tianshusql);

			DataSetClass dc = db.FunGetDataSetBySQL(tianshusql);
			int count = 0;

			for (int i = 0; i < dc.FunGetRowCount(); i++) {
				for (int j = 0; j < 3; j++) {
					Number temp = dc.FunGetDataAsNumberById(i, j);
					temp = (temp == null ? 0 : temp);
					rows.put("ts" + count, temp);
					count++;
				}
			}
			// ////////////System.out.println("mmm"+start+"--"+end);

			// 卫生经济学统计
			// 纳入病例的平均总费用 纳入病例的最大总费用 纳入病例的最小总费用
			// 不纳入路径的平均总费用 不纳入路径的最大总费用 不纳入路径的最小总费用
			String fee_sql = " select   nvl(round(avg(d), 2),0) a,nvl(max(d),0) b, nvl(min(d),0) c"
					+ "   from (select l.fee_total d  from lcp_patient_visit t,lcp_patient_fee l    "
					+ "   where  t.hospital_id=l.hospital_id   and t.patient_no=l.patient_no and t.cp_master_id ="
					+ cp_id
					+ "   and trunc(t.admission_date)>= "
					+ date_start
					+ "   and trunc(t.admission_date)<= "
					+ date_end
					+ "   and t.discharged_date is not null )                      "
					+ "    union all                         "
					+ "   select  nvl(round(avg(d), 2),0) a,nvl(max(d),0) b, nvl(min(d),0) c "
					+ "   from (select l.fee_total d  from lcp_patient_visit t,lcp_patient_fee l            "
					+ "   where t.conform_master_id =                            "
					+ cp_id
					+ "   and t.cp_master_id = 0         and t.hospital_id=l.hospital_id   and t.patient_no=l.patient_no  "
					+ "   and trunc(t.admission_date)>= " + date_start + "            and trunc(t.admission_date)<= " + date_end
					+ "   and t.discharged_date is not null)                      ";
			DataSetClass dcc = db.FunGetDataSetBySQL(fee_sql);
			count = 0;
			String[] ts = new String[6];
			for (int i = 0; i < dcc.FunGetRowCount(); i++) {
				for (int j = 0; j < 3; j++) {
					String temp = dcc.FunGetDataAsStringById(i, j);
					ts[count] = (temp == null || temp == "" ? "0" : temp);
					rows.put("wsjj" + count, temp);
					count++;
				}
			}

			PrintWriter out = response.getWriter();
			out.println(rows.toString());

			out.close();
		} else
			getZhiKongCPTongJidan2(request, response);
	}

	protected void getZhiKongCPTongJidan2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cp_id = request.getParameter("cp_id");
		String s = request.getParameter("s");// 开始时间
		String e = request.getParameter("e");// 结束时间
		String date_start = "to_date('" + s + "','yyyy-mm-dd')";
		String date_end = "to_date('" + e + "','yyyy-mm-dd')";
		JSONObject rows = new JSONObject();
		// 工作量统计
		// 入院总病例数 符合纳入条件病例数 纳入路径病例数
		// 路径执行中病例数 完成路径病例数 变异退出路径病例数

		String gzl_sql = "select ryzs,fhbl,nrbl,zxzbl,wcbl,bytcbl  from"
				+ "       (select count(*) ryzs from lcp_patient_visit where  to_char(ADMISSION_DATE,'yyyy-mm-dd')>='"
				+ s
				+ "' "
				+ " and to_char(discharged_date,'yyyy-mm-dd')<= '"
				+ e
				+ "'),"
				+ " 		(select count(*) fhbl from lcp_patient_visit t where t.conform_master_id="
				+ cp_id
				+ " and  trunc(ADMISSION_DATE)>="
				+ date_start
				+ " and trunc(ADMISSION_DATE)<="
				+ date_end+"),"
				+ " 		(select count(*) nrbl from lcp_patient_visit t where t.cp_master_id="
				+ cp_id
				+ " and trunc(ADMISSION_DATE)>="
				+ date_start
				+ " and trunc(ADMISSION_DATE)<="
				+ date_end+"),"
				+ " 		(select count(*) zxzbl from lcp_patient_visit t where t.cp_master_id="
				+ cp_id
				+ " and cp_state=1 and  trunc(ADMISSION_DATE)>="
				+ date_start
				+ " and trunc(ADMISSION_DATE)<="
				+ date_end+"),"
				+ " 		(select count(*) wcbl from lcp_patient_visit t where t.cp_master_id="
				+ cp_id
				+ " and cp_state=11 and  trunc(ADMISSION_DATE)>="
				+ date_start
				+ " and trunc(ADMISSION_DATE)<="
				+ date_end+"),"
				+ " 		(select count(*) bytcbl from lcp_patient_visit t where t.cp_master_id="
				+ cp_id
				+ " and cp_state=21 and  trunc(ADMISSION_DATE)>=" + date_start + " and trunc(ADMISSION_DATE)<=" + date_end+")";
		DataSet gzl_dataset = new DataSet();
		gzl_dataset.funSetDataSetBySql(gzl_sql);

		int ryzs = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "RYZS"));
		int fhbl = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "FHBL"));
		int nrbl = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "NRBL"));

		int zxzbl = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "ZXZBL"));
		int wcbl = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "WCBL"));
		int bytcbl = Integer.valueOf(gzl_dataset.funGetFieldByCol(0, "BYTCBL"));
		rows.put("ryzs", ryzs);
		rows.put("fhbl", fhbl);
		rows.put("nrbl", nrbl);

		rows.put("zxzbl", zxzbl);
		rows.put("wcbl", wcbl);
		rows.put("bytcbl", bytcbl);

		// 抗菌药物使用统计 -->
		// 纳入病例中平均使用抗菌药物的比率 纳入病例中平均使用抗菌药物的天数
		// 不纳入路径中平均使用抗菌药物的比率 不纳入路径中平均使用抗菌药物的天数

		String ksssyts_naru_sql = " select count(a)  aa           "
				+ "   from (select max(a.is_antibiotic) a                        "
				+ "   from lcp_patient_log_order a, lcp_patient_visit t  "
				+ "   where t.hospital_id = a.hospital_id   "
				+ "   and t.patient_no = a.patient_no     "
				+ "    and t.cp_state not in (0,99)   and t.cp_master_id =     "
				+ cp_id
				+ "   and trunc(t.admission_date)>= "
				+ date_start
				+ "            and trunc(t.admission_date)<= "
				+ date_end
				+ "   and t.discharged_date is not null      "
				+ "   group by t.patient_no)  where a > 0    "
				+ " union      all   "
				+ " select count(a)   aa   from (select max(a.is_antibiotic) a     from lcp_patient_log_order a, lcp_patient_visit t  "
				+ "    where t.hospital_id = a.hospital_id  and t.patient_no = a.patient_no   and t.cp_state=0      and t.cp_state in(0,99) and t.conform_master_id ="
				+ cp_id
				+ "  and trunc(t.admission_date)>= "
				+ date_start
				+ "  and trunc(t.admission_date)<= "
				+ date_end
				+ "  and t.discharged_date is not null "       
				+ "   group by t.patient_no)   where a > 0   ";
		DataSetClass dcs = db.FunGetDataSetBySQL(ksssyts_naru_sql);
		double kjywrs = 0;
		double bsykj = 0;
		for (int i = 0; i < dcs.FunGetRowCount(); i++) {
			if (i == 0) {
				kjywrs = dcs.FunGetDataAsNumberById(i, 0).doubleValue();

			} else {
				bsykj = dcs.FunGetDataAsNumberById(i, 0).doubleValue();
			}
		}
		if (nrbl != 0 && fhbl - nrbl != 0) {
			kjywrs = this.covertDouble2Dec(kjywrs / nrbl * 100);//纳入病人抗菌药物
			bsykj = this.covertDouble2Dec(bsykj / (fhbl - nrbl) * 100);// 不纳入抗菌
		}

		rows.put("kjywrs", kjywrs);
		rows.put("bsykj", bsykj);

		// 变异统计
		PrintWriter out = response.getWriter();
		out.println(rows.toString());

		out.close();
	}

	public double covertDouble2Dec(double val) {

		Double ret = null;
		int precision = 2;
		try {
			double factor = Math.pow(10, precision);
			ret = new Double(Math.floor(val * factor + 0.5) / factor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String tmp = String.valueOf(ret);
		if (tmp.substring(tmp.indexOf('.') + 1).length() < 2) {
			tmp = tmp + "0";
		}
		return Double.parseDouble(tmp);
	}

}
