<%@page import="com.goodwillcis.lcp.model.TongjiFeeMax"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<%@page import="com.goodwillcis.lcp.model.DataSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cp_id=request.getParameter("cp_id");
	String cp_name="";
	cp_name=new String(cp_name.getBytes("GBK"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>临床路径效果统计</title>
    <script type="text/javascript" src="../public/plugins/FusionCharts/FusionCharts.js"></script>
	<script src="../public/plugins/jquery/jquery-1.5.1.js"></script>
<link rel="stylesheet" href="../public/plugins/FusionCharts/style.css" type="text/css" />
		<link rel="stylesheet" href="../public/styles/demos.css">
		<style type="text/css"> 
		<!--
		a:link{
		text-decoration:none;
		color: #000000;
		font-size: 12px;
		}
		a:visited{
		text-decoration:none;
		color: #000000;
		font-size: 12px;
		}
		-->
		</style>
</head>
<body>
<%
DataSet tianshu=new DataSet();
String sql="select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "+cp_id+"";

tianshu.funSetDataSetBySql(sql);

%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="24" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
                <td valign="bottom"><span class="STYLE1"> <%=tianshu.funGetFieldByCol(0,"CP_NAME") %></span></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="jingji">2型糖尿病临床路径卫生经济学统计</span>
                            <script type="text/javascript">
                                 $.post("../ZhikongTongjiServlet?op=jingjixue1&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("jingji");	 
                         	        }
                              });
                            </script>                
                </div></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 纳入路径中费用段最集中的对比 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="narujjduibi">纳入路径病例中前5段总费用出现率与不纳入病例的对比</span>
                            <script type="text/javascript">
                                 $.post("../ZhikongTongjiServlet?op=jingjixue2&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("narujjduibi");	 
                         	        }
                              });
                            </script>                
                </div></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 不纳入中费用段最集中的对比 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="bunajjduibi">不纳入病例中前5段总费用出现率与纳入病例的对比</span>
                            <script type="text/javascript">
                                 $.post("../ZhikongTongjiServlet?op=jingjixue3&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("bunajjduibi");	 
                         	        }
                              });
                            </script>                
                </div></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <!-- 各类费用数据统计 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="24" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
                <td valign="bottom"><span class="STYLE1"> 各类费用统计</span></td>
              </tr>
            </table></td>
            <td>&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td><table width="100%" id="tcp" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
    <tbody id="tbcp">    
      <tr>
        <td width="10%" height="48" bgcolor="d3eaef" rowspan="2" class="STYLE10"><div align="center"><span class="STYLE10">费用类型</span></div></td>
        <td width="45%" bgcolor="d3eaef" height="24" colspan="5" class="STYLE10"><div align="center"><span class="STYLE10">纳入病例</span></div></td>
        <td width="45%" bgcolor="d3eaef" height="24" colspan="5" class="STYLE10"><div align="center"><span class="STYLE10">不纳入病例</span></div></td>        
      </tr>
      <tr>
        <td width="9%" height="48" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最小费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最大费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">平均费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">出现率最高的费用段</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">出现率最高的费用段对应出现率</span></div></td>        
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最小费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最大费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">平均费用</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">出现率最高的费用段</span></div></td>
        <td width="9%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">出现率最高的费用段对应出现率</span></div></td>
      </tr>
      <%
	  	String cp_start_time=tianshu.funGetFieldByCol1(0,"CP_START_DATE");
	  	String cp_stop_time=(tianshu.funGetFieldByCol1(0,"CP_STOP_DATE")).equals("")?CommonUtil.getDBTimeString1():tianshu.funGetFieldByCol1(0,"CP_STOP_DATE");
	  	String cp_state=tianshu.funGetFieldByCol(0, "CP_STATUS");
	  	if(cp_state.equals("0")){
	  		  cp_stop_time= CommonUtil.getDBTimeString1();
	  	}
	  	String date_start="to_date('"+cp_start_time+"','yyyy-mm-dd')";
	  	String date_end="to_date('"+cp_stop_time+"','yyyy-mm-dd')";
      	DataSet dataSet_naru=new DataSet();
      	String sql_naru="select  "+
	      	"	max(aa.fee_bed) zd_fee_bed,min(aa.fee_bed)zx_fee_bed,trunc(avg(aa.fee_bed),2) pj_fee_bed, "+
	      	"	max(aa.FEE_CARE) zd_FEE_CARE,min(aa.FEE_CARE)zx_FEE_CARE,trunc(avg(aa.FEE_CARE),2) pj_FEE_CARE, "+
	      	"	max(aa.FEE_DRUG) zd_FEE_DRUG,min(aa.FEE_DRUG)zx_FEE_DRUG,trunc(avg(aa.FEE_DRUG),2) pj_FEE_DRUG, "+
	      	"	max(aa.FEE_CN_DRUG) zd_FEE_CN_DRUG,min(aa.FEE_CN_DRUG)zx_FEE_CN_DRUG,trunc(avg(aa.FEE_CN_DRUG),2) pj_FEE_CN_DRUG, "+
	      	"	max(aa.FEE_CN_HERB) zd_FEE_CN_HERB,min(aa.FEE_CN_HERB)zx_FEE_CN_HERB,trunc(avg(aa.FEE_CN_HERB),2) pj_FEE_CN_HERB, "+
	      	"	max(aa.FEE_RADIATION) zd_FEE_RADIATION,min(aa.FEE_RADIATION)zx_FEE_RADIATION,trunc(avg(aa.FEE_RADIATION),2) pj_FEE_RADIATION, "+
	      	"	max(aa.FEE_TEST) zd_FEE_TEST,min(aa.FEE_TEST)zx_FEE_TEST,trunc(avg(aa.FEE_TEST),2) pj_FEE_TEST, "+
	      	"	max(aa.FEE_OXYGEN) zd_FEE_OXYGEN,min(aa.FEE_OXYGEN)zx_FEE_OXYGEN,trunc(avg(aa.FEE_OXYGEN),2) pj_FEE_OXYGEN, "+
	      	"	max(aa.FEE_BLOOD) zd_FEE_BLOOD,min(aa.FEE_BLOOD)zx_FEE_BLOOD,trunc(avg(aa.FEE_BLOOD),2) pj_FEE_BLOOD, "+
	      	"	max(aa.FEE_TREATMENT) zd_FEE_TREATMENT,min(aa.FEE_TREATMENT)zx_FEE_TREATMENT,trunc(avg(aa.FEE_TREATMENT),2) pj_FEE_TREATMENT, "+
	      	"	max(aa.FEE_OPERATION) zd_FEE_OPERATION,min(aa.FEE_OPERATION)zx_FEE_OPERATION,trunc(avg(aa.FEE_OPERATION),2) pj_FEE_OPERATION, "+
	      	"	max(aa.FEE_DELIVERY) zd_FEE_DELIVERY,min(aa.FEE_DELIVERY)zx_FEE_DELIVERY,trunc(avg(aa.FEE_DELIVERY),2) pj_FEE_DELIVERY, "+
	      	"	max(aa.FEE_EXAMINATION) zd_FEE_EXAMINATION,min(aa.FEE_EXAMINATION)zx_FEE_EXAMINATION,trunc(avg(aa.FEE_EXAMINATION),2) pj_FEE_EXAMINATION, "+
	      	"	max(aa.FEE_NARCOSIS) zd_FEE_NARCOSIS,min(aa.FEE_NARCOSIS)zx_FEE_NARCOSIS,trunc(avg(aa.FEE_NARCOSIS),2) pj_FEE_NARCOSIS, "+
	      	"	max(aa.FEE_BABY) zd_FEE_BABY,min(aa.FEE_BABY)zx_FEE_BABY,trunc(avg(aa.FEE_BABY),2) pj_FEE_BABY, "+
	      	"	max(aa.FEE_ACCOMPANY) zd_FEE_ACCOMPANY,min(aa.FEE_ACCOMPANY)zx_FEE_ACCOMPANY,trunc(avg(aa.FEE_ACCOMPANY),2) pj_FEE_ACCOMPANY, "+
	      	"	max(aa.FEE_OTHER) zd_FEE_OTHER,min(aa.FEE_OTHER)zx_FEE_OTHER,trunc(avg(aa.FEE_OTHER),2) pj_FEE_OTHER "+
	      	"	  from lcp_patient_fee aa, "+
	      	"	       (select aa.* "+
	      	"	          from lcp_patient_visit aa, "+
	      	"	               (select distinct * "+
	      	"	                  from (select cp_id, hospital_id, patient_no "+
	      	"	                          from lcp_patient_node a "+
	      	"	                         where a.cp_id = "+cp_id+")) bb "+
	      	"	         where aa.hospital_id = bb.hospital_id "+
	      	"	           and aa.patient_no = bb.patient_no "+
	      	"	           and aa.cp_state in ( 11, 21) "+
	      	"	           and aa.ADMISSION_DATE >= "+date_start+" "+
	      	"	           and aa.discharged_date <= "+date_end+" ) bb "+
	      	"	 where aa.hospital_id = bb.hospital_id "+
	      	"	   and aa.patient_no = bb.patient_no";
	      System.out.println(sql_naru);
	      dataSet_naru.funSetDataSetBySql(sql_naru);
	      
	      String sql_bunaru="select  "+
	      	"	max(aa.fee_bed) zd_fee_bed,min(aa.fee_bed)zx_fee_bed,trunc(avg(aa.fee_bed),2) pj_fee_bed, "+
	      	"	max(aa.FEE_CARE) zd_FEE_CARE,min(aa.FEE_CARE)zx_FEE_CARE,trunc(avg(aa.FEE_CARE),2) pj_FEE_CARE, "+
	      	"	max(aa.FEE_DRUG) zd_FEE_DRUG,min(aa.FEE_DRUG)zx_FEE_DRUG,trunc(avg(aa.FEE_DRUG),2) pj_FEE_DRUG, "+
	      	"	max(aa.FEE_CN_DRUG) zd_FEE_CN_DRUG,min(aa.FEE_CN_DRUG)zx_FEE_CN_DRUG,trunc(avg(aa.FEE_CN_DRUG),2) pj_FEE_CN_DRUG, "+
	      	"	max(aa.FEE_CN_HERB) zd_FEE_CN_HERB,min(aa.FEE_CN_HERB)zx_FEE_CN_HERB,trunc(avg(aa.FEE_CN_HERB),2) pj_FEE_CN_HERB, "+
	      	"	max(aa.FEE_RADIATION) zd_FEE_RADIATION,min(aa.FEE_RADIATION)zx_FEE_RADIATION,trunc(avg(aa.FEE_RADIATION),2) pj_FEE_RADIATION, "+
	      	"	max(aa.FEE_TEST) zd_FEE_TEST,min(aa.FEE_TEST)zx_FEE_TEST,trunc(avg(aa.FEE_TEST),2) pj_FEE_TEST, "+
	      	"	max(aa.FEE_OXYGEN) zd_FEE_OXYGEN,min(aa.FEE_OXYGEN)zx_FEE_OXYGEN,trunc(avg(aa.FEE_OXYGEN),2) pj_FEE_OXYGEN, "+
	      	"	max(aa.FEE_BLOOD) zd_FEE_BLOOD,min(aa.FEE_BLOOD)zx_FEE_BLOOD,trunc(avg(aa.FEE_BLOOD),2) pj_FEE_BLOOD, "+
	      	"	max(aa.FEE_TREATMENT) zd_FEE_TREATMENT,min(aa.FEE_TREATMENT)zx_FEE_TREATMENT,trunc(avg(aa.FEE_TREATMENT),2) pj_FEE_TREATMENT, "+
	      	"	max(aa.FEE_OPERATION) zd_FEE_OPERATION,min(aa.FEE_OPERATION)zx_FEE_OPERATION,trunc(avg(aa.FEE_OPERATION),2) pj_FEE_OPERATION, "+
	      	"	max(aa.FEE_DELIVERY) zd_FEE_DELIVERY,min(aa.FEE_DELIVERY)zx_FEE_DELIVERY,trunc(avg(aa.FEE_DELIVERY),2) pj_FEE_DELIVERY, "+
	      	"	max(aa.FEE_EXAMINATION) zd_FEE_EXAMINATION,min(aa.FEE_EXAMINATION)zx_FEE_EXAMINATION,trunc(avg(aa.FEE_EXAMINATION),2) pj_FEE_EXAMINATION, "+
	      	"	max(aa.FEE_NARCOSIS) zd_FEE_NARCOSIS,min(aa.FEE_NARCOSIS)zx_FEE_NARCOSIS,trunc(avg(aa.FEE_NARCOSIS),2) pj_FEE_NARCOSIS, "+
	      	"	max(aa.FEE_BABY) zd_FEE_BABY,min(aa.FEE_BABY)zx_FEE_BABY,trunc(avg(aa.FEE_BABY),2) pj_FEE_BABY, "+
	      	"	max(aa.FEE_ACCOMPANY) zd_FEE_ACCOMPANY,min(aa.FEE_ACCOMPANY)zx_FEE_ACCOMPANY,trunc(avg(aa.FEE_ACCOMPANY),2) pj_FEE_ACCOMPANY, "+
	      	"	max(aa.FEE_OTHER) zd_FEE_OTHER,min(aa.FEE_OTHER)zx_FEE_OTHER,trunc(avg(aa.FEE_OTHER),2) pj_FEE_OTHER "+
	      	"	  from lcp_patient_fee aa, "+
	      	"	       ( select aa.* "+
			"				  from lcp_patient_visit aa, "+
			"				       (select distinct * "+
			"				          from (select a.hospital_id, a.cp_id, b.patient_no "+
			"				                  from lcp_master_income a, lcp_patient_log_income b "+
			"				                 where a.cp_income_type = b.income_type "+
			"				                   and a.cp_income_code = b.income_code "+
			"				                   and a.hospital_id = b.hospital_id "+
			"				                   and a.cp_id = "+cp_id+")) cc "+
			"				 where aa.hospital_id = cc.hospital_id "+
			"				   and aa.patient_no = cc.patient_no "+
			"				   and aa.cp_state in (99)"+
	      	"	           and aa.ADMISSION_DATE >= "+date_start+" "+
	      	"	           and aa.discharged_date <= "+date_end+" ) bb "+
	      	"	 where aa.hospital_id = bb.hospital_id "+
	      	"	   and aa.patient_no = bb.patient_no";
	      
	     // System.out.print(sql_bunaru);
	     DataSet dataSet_bunaru=new DataSet();
	     dataSet_bunaru.funSetDataSetBySql(sql_bunaru);
	     
	   String renshu_sql="select * from (select count(*) narurenshu, 2 naru from (select aa.* "+
		"				  from lcp_patient_visit aa, "+
		"				       (select distinct * "+
		"				          from (select a.hospital_id, a.cp_id, b.patient_no "+
		"				                  from lcp_master_income a, lcp_patient_log_income b "+
		"				                 where a.cp_income_type = b.income_type "+
		"				                   and a.cp_income_code = b.income_code "+
		"				                   and a.hospital_id = b.hospital_id "+
		"				                   and a.cp_id = "+cp_id+")) cc "+
		"				 where aa.hospital_id = cc.hospital_id "+
		"				   and aa.patient_no = cc.patient_no "+
		"				   and aa.cp_state in (99)"+
      	"	           and aa.ADMISSION_DATE >= "+date_start+" "+
      	"	           and aa.discharged_date <= "+date_end+") union  "+
	  	"   			select count(*) narurenshu, 1 naru from (select aa.* "+
     	"	          from lcp_patient_visit aa, "+
     	"	               (select distinct * "+
     	"	                  from (select cp_id, hospital_id, patient_no "+
     	"	                          from lcp_patient_node a "+
     	"	                         where a.cp_id = "+cp_id+")) bb "+
     	"	         where aa.hospital_id = bb.hospital_id "+
     	"	           and aa.patient_no = bb.patient_no "+
     	"	           and aa.cp_state in ( 11, 21) "+
     	"	           and aa.ADMISSION_DATE >= "+date_start+" "+
     	"	           and aa.discharged_date <= "+date_end+")) order by naru asc ";
     	//out.print(renshu_sql);
     	DataSet renshu_dataSet=new DataSet();
     	renshu_dataSet.funSetDataSetBySql(renshu_sql);
     	int naru_renshu_int=Integer.parseInt(renshu_dataSet.funGetFieldByCol(0,"NARURENSHU"));
     	if(naru_renshu_int==0)naru_renshu_int=1;
     	int bunaru_renshu_int=Integer.parseInt(renshu_dataSet.funGetFieldByCol(1,"NARURENSHU"));
     	if(bunaru_renshu_int==0)bunaru_renshu_int=1;
     
	      String sql_naru_patient="( select qq.* from lcp_patient_fee qq,( "+
	    	"	  select aa.* "+
	    	"	    from lcp_patient_visit aa, "+
	    	"	         (select distinct * "+
	    	"	            from (select cp_id, hospital_id, patient_no "+
	    	"	                    from lcp_patient_node a "+
	    	"	                   where a.cp_id = "+cp_id+")) bb "+
	    	"	   where aa.hospital_id = bb.hospital_id "+
	    	"	     and aa.patient_no = bb.patient_no "+
	    	"	     and aa.cp_state in ( 11, 21) "+
	    	"	     and aa.ADMISSION_DATE >= "+date_start+" "+
	    	"	     and aa.discharged_date <= "+date_end+")pp" +
	    			" where qq.hospital_id=pp.hospital_id "+
	    	"	     and qq.patient_no=pp.patient_no )";
			//System.out.println(sql_naru_patient);
	      String sql_bu_naru_patient="( select qq.* from lcp_patient_fee qq,( "+
	    	"	 select aa.* "+
			"				  from lcp_patient_visit aa, "+
			"				       (select distinct * "+
			"				          from (select a.hospital_id, a.cp_id, b.patient_no "+
			"				                  from lcp_master_income a, lcp_patient_log_income b "+
			"				                 where a.cp_income_type = b.income_type "+
			"				                   and a.cp_income_code = b.income_code "+
			"				                   and a.hospital_id = b.hospital_id "+
			"				                   and a.cp_id = "+cp_id+")) cc "+
			"				 where aa.hospital_id = cc.hospital_id "+
			"				   and aa.patient_no = cc.patient_no "+
			"				   and aa.cp_state in (99)"+
	      	"	           and aa.ADMISSION_DATE >= "+date_start+" "+
	      	"	           and aa.discharged_date <= "+date_end+" )pp" +
	    			" where qq.hospital_id=pp.hospital_id "+
	    	"	     and qq.patient_no=pp.patient_no )";
	      System.out.print(sql_bu_naru_patient);
	  
	  	
	  	
	    	String naru_fee="select * from (" ;
	    	naru_fee=naru_fee+ TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"PJ_FEE_BED"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BED"), 1, 10, sql_naru_patient, "FEE_BED", 1);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CARE"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CARE"), 1, 10, sql_naru_patient, "FEE_CARE", 2);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_DRUG"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_DRUG"), 1, 10, sql_naru_patient, "FEE_DRUG", 3);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CN_DRUG"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CN_DRUG"), 1, 10, sql_naru_patient, "FEE_CN_DRUG", 4);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CN_HERB"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CN_HERB"), 1, 10, sql_naru_patient, "FEE_CN_HERB", 5);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_RADIATION"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_RADIATION"), 1, 10, sql_naru_patient, "FEE_RADIATION", 6);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_TEST"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_TEST"), 1, 10, sql_naru_patient, "FEE_TEST", 7);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OXYGEN"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OXYGEN"), 1, 10, sql_naru_patient, "FEE_OXYGEN", 8);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_BLOOD"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BLOOD"), 1, 10, sql_naru_patient, "FEE_BLOOD", 9);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_TREATMENT"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_TREATMENT"), 1, 10, sql_naru_patient, "FEE_TREATMENT", 10);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OPERATION"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OPERATION"), 1, 10, sql_naru_patient, "FEE_OPERATION", 11);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_DELIVERY"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_DELIVERY"), 1, 10, sql_naru_patient, "FEE_DELIVERY", 12);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_EXAMINATION"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_EXAMINATION"), 1, 10, sql_naru_patient, "FEE_EXAMINATION", 13);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_NARCOSIS"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_NARCOSIS"), 1, 10, sql_naru_patient, "FEE_NARCOSIS", 14);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_BABY"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BABY"), 1, 10, sql_naru_patient, "FEE_BABY", 15);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_ACCOMPANY"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_ACCOMPANY"), 1, 10, sql_naru_patient, "FEE_ACCOMPANY", 16);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OTHER"), dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OTHER"), 1, 10, sql_naru_patient, "FEE_OTHER", 17);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_BED"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BED"), 2, 10, sql_bu_naru_patient, "FEE_BED", 1);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CARE"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CARE"), 2, 10, sql_bu_naru_patient, "FEE_CARE", 2);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_DRUG"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_DRUG"), 2, 10, sql_bu_naru_patient, "FEE_DRUG", 3);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CN_DRUG"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CN_DRUG"), 2, 10, sql_bu_naru_patient, "FEE_CN_DRUG", 4);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CN_HERB"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CN_HERB"), 2, 10, sql_bu_naru_patient, "FEE_CN_HERB", 5);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_RADIATION"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_RADIATION"), 2, 10, sql_bu_naru_patient, "FEE_RADIATION", 6);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_TEST"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_TEST"), 2, 10, sql_bu_naru_patient, "FEE_TEST", 7);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OXYGEN"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OXYGEN"), 2, 10, sql_bu_naru_patient, "FEE_OXYGEN", 8);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_BLOOD"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BLOOD"), 2, 10, sql_bu_naru_patient, "FEE_BLOOD", 9);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_TREATMENT"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_TREATMENT"), 2, 10, sql_bu_naru_patient, "FEE_TREATMENT", 10);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OPERATION"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OPERATION"), 2, 10, sql_bu_naru_patient, "FEE_OPERATION", 11);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_DELIVERY"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_DELIVERY"), 2, 10, sql_bu_naru_patient, "FEE_DELIVERY", 12);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_EXAMINATION"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_EXAMINATION"), 2, 10, sql_bu_naru_patient, "FEE_EXAMINATION", 13);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_NARCOSIS"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_NARCOSIS"), 2, 10, sql_bu_naru_patient, "FEE_NARCOSIS", 14);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_BABY"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BABY"), 2, 10, sql_bu_naru_patient, "FEE_BABY", 15);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_ACCOMPANY"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_ACCOMPANY"), 2, 10, sql_bu_naru_patient, "FEE_ACCOMPANY", 16);
	    	naru_fee=naru_fee+" union " +TongjiFeeMax.funGetFeeQujianSql(dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OTHER"), dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OTHER"), 2, 10, sql_bu_naru_patient, "FEE_OTHER", 17);
	    	naru_fee=naru_fee+ ") order by naru asc, fieldIndex asc";
	    	//System.out.println(naru_fee);
	    	DataSet fee_quduan=new DataSet();
	    	fee_quduan.funSetDataSetBySql(naru_fee);
	    	DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
	    	//fnum1.
	    	
      %>
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">床位费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BED")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_BED")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_BED")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(0,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(0,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BED")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_BED")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_BED")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(0,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(0,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">护理费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CARE")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CARE")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_CARE")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(1,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(1,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CARE")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CARE")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_CARE")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(18,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(18,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">西药费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_DRUG")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(2,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(2,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_DRUG")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(19,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(19,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">中成药费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(3,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(3,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_CN_DRUG")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(20,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(20,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">中草药费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_CN_HERB")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_CN_HERB")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_CN_HERB")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(4,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(4,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_CN_HERB")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_CN_HERB")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_CN_HERB")%></div></td>
				<td><div align="center"><%=fee_quduan.funGetFieldByCol(21,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(21,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">放射费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(5,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(5,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_RADIATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(22,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(22,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">化验费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_TEST")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_TEST")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_TEST")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(6,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(6,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_TEST")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_TEST")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_TEST")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(23,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(23,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">输氧费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(7,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(7,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_OXYGEN")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(24,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(24,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">输血费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(8,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(8,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_BLOOD")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(25,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(25,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">诊疗费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(9,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(9,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_TREATMENT")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(26,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(26,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">手术费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(10,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(10,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_OPERATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(27,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(27,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">接生费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(11,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(11,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_DELIVERY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(28,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(28,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">检查费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(12,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(12,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_EXAMINATION")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(29,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(29,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">麻醉费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(13,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(12,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_NARCOSIS")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(30,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(30,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">婴儿费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_BABY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_BABY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_BABY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(14,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(14,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_BABY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_BABY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_BABY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(31,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(31,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">陪床费</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(15,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(15,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_ACCOMPANY")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(32,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(32,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
			
		<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center">其他费用</div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZX_FEE_OTHER")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"ZD_FEE_OTHER")%></div></td>
		<td><div align="center"><%=dataSet_naru.funGetFieldByCol(0,"PJ_FEE_OTHER")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(16,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(16,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZX_FEE_OTHER")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"ZD_FEE_OTHER")%></div></td>
		<td><div align="center"><%=dataSet_bunaru.funGetFieldByCol(0,"PJ_FEE_OTHER")%></div></td>
		<td><div align="center"><%=fee_quduan.funGetFieldByCol(33,"FANWEI")%></div></td>
		<td><div align="center"><%=fnum.format(Integer.parseInt(fee_quduan.funGetFieldByCol(33,"RENSHU"))*100f/naru_renshu_int)%>%</div></td>

		</tr>		
		
		
			
						
      </tbody>
    </table></td>
  	<td width="3">&nbsp;</td>
  </tr>
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="1"></td>
  	<td width="3">&nbsp;</td>
  </tr>
  
</table>

</body>

</html>