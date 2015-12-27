<%@page import="com.goodwillcis.lcp.model.DataSet"%>
<%@page import="com.goodwillcis.lcp.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String cp_id=request.getParameter("cp_id");
	String cp_name="";//request.getParameter("cp_name");
	cp_name=new String(cp_name.getBytes("GBK"),"UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>路径统计</title>
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
                <span id="xiaolv">2型糖尿病临床路径效率统计</span>
                
                
                            <script type="text/javascript"><!--
                            
                            
                            $.post("../ZhikongTongjiServlet?op=tianshuTongji&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("xiaolv");	 
                          	        }
                               });
                                									  
                                // -->
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
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#FFFFFF"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="left">
                <span id="rifenbu"><%=cp_name%>住院分布率</span>
                            <script type="text/javascript">
                                  
                            $.post("../ZhikongTongjiServlet?op=tianshufblTongji&cp_id=<%=cp_id%>",function(data){
                          	     if(data!=""){
                          	           /* 创建 FusionCharts 对象 有4个参数 1： swf 文件URL 2：图形ID 随便取名 3：图片宽度 4：图片高度 */
                          	    	var MarChart = new FusionCharts("../public/plugins/FusionCharts/MSColumn2D.swf", "MarChartId", "1060", "300", "0", "1");
                                    MarChart.setDataXML(data);
                                    MarChart.render("rifenbu");	 
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
  <!-- 各个节点的数据统计 -->
  <tr>
  	<td width="3">&nbsp;</td>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td height="23"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="24" height="19" valign="bottom"><div align="center"><img src="../public/images/tb.gif" width="14" height="14" /></div></td>
                <td valign="bottom"><span class="STYLE1"> 各个节点效率统计</span></td>
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
        <td width="20%" height="48" bgcolor="d3eaef" rowspan="2" class="STYLE10"><div align="center"><span class="STYLE10">节点名称</span></div></td>
        <td width="30%" bgcolor="d3eaef" height="24" colspan="3" class="STYLE10"><div align="center"><span class="STYLE10">节点定义</span></div></td>
        <td width="30%" bgcolor="d3eaef" height="24" colspan="3" class="STYLE10"><div align="center"><span class="STYLE10">实际执行</span></div></td>
        <td width="10%" bgcolor="d3eaef" height="48" rowspan="2" class="STYLE10"><div align="center"><span class="STYLE10">住院日最高分布率</span></div></td>
        <td width="10%" bgcolor="d3eaef" height="48" rowspan="2" class="STYLE10"><div align="center"><span class="STYLE10">住院日分布率最高的住院日</span></div></td>
      </tr>
      <tr>
        <td width="10%" height="24" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最小住院日</span></div></td>
        <td width="10%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最大住院日</span></div></td>
        <td width="10%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">平均住院日</span></div></td>
        <td width="10%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最小住院日</span></div></td>
        <td width="10%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">最大住院日</span></div></td>
        <td width="10%" bgcolor="d3eaef" class="STYLE10"><div align="center"><span class="STYLE10">平均住院日</span></div></td>
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
			String tianchu_fbl="select * from ( "+
				"select ddd.*,aaa.zyts zdzyrts,aaa.gtrs zdzyrrs,ccc.sjzxzxts,ccc.sjzdzyts,ccc.sjzypjts,bbb.zyrszh,trunc(aaa.gtrs/bbb.zyrszh*100,2) zdzyfbl from "+ 
				"(select hospital_id,cp_id,cp_node_id,gtrs,max(zyts)zyts from( select hospital_id,cp_id,cp_node_id,gtrs,zyts from (SELECT aa.*, RANK() OVER(PARTITION BY cp_node_id ORDER BY gtrs desc) Rank FROM (select hospital_id,cp_id, cp_node_id, zyts, gtrs from (select hospital_id, cp_id, cp_node_id, zyts, sum(renshu) gtrs   from (select a.hospital_id,  a.patient_no, a.cp_id,  a.cp_node_id,   1 renshu,  trunc(a.cp_node_end_time,'DD') - trunc(a.cp_node_start_time,'DD') + 1 zyts   from lcp_patient_node a,lcp_patient_visit b    where a.cp_id = "+cp_id+" and a.cp_node_end_time is not null   and a.hospital_id=b.hospital_id and a.patient_no=b.patient_no and b.admission_date>= "+date_start+"  and b.discharged_date<= "+date_end+"  order by a.cp_node_id, a.cp_node_id,       a.patient_no) group by hospital_id, cp_id, cp_node_id, zyts)order by hospital_id, cp_id, cp_node_id, zyts, gtrs desc) aa) where Rank = 1 )group by hospital_id,cp_id,cp_node_id,gtrs)aaa, "+
				"(select hospital_id, cp_id, cp_node_id, sum(gtrs)zyrszh from (select hospital_id, cp_id, cp_node_id, zyts, sum(renshu) gtrs from (select a.hospital_id, a.patient_no,a.cp_id,a.cp_node_id, 1 renshu,trunc(a.cp_node_end_time, 'DD') - trunc(a.cp_node_start_time, 'DD') + 1 zyts from lcp_patient_node a,lcp_patient_visit b where a.cp_id = "+cp_id+" and a.cp_node_end_time is not null   and a.hospital_id=b.hospital_id and a.patient_no=b.patient_no and b.admission_date>= "+date_start+"  and b.discharged_date<= "+date_end+"  order by a.cp_node_id, a.cp_node_id, a.patient_no) group by hospital_id, cp_id, cp_node_id, zyts)group by hospital_id, cp_id, cp_node_id)bbb, "+
				"(select hospital_id, cp_id, cp_node_id, max(zyts) sjzdzyts,  min(zyts) sjzxzxts,  ceil(avg(zyts)) sjzypjts from (select a.hospital_id, a.patient_no, a.cp_id, a.cp_node_id,trunc(a.cp_node_end_time, 'DD') - trunc(a.cp_node_start_time, 'DD') + 1 zyts from lcp_patient_node a,lcp_patient_visit b where a.cp_id = "+cp_id+" and a.cp_node_end_time is not null  and a.hospital_id=b.hospital_id and a.patient_no=b.patient_no and b.admission_date>= "+date_start+"  and b.discharged_date<= "+date_end+" order by a.cp_node_id, a.cp_node_id, a.patient_no) group by hospital_id, cp_id, cp_node_id)ccc, "+
				"(select t.hospital_id,t.cp_id,t.cp_node_id, t.cp_node_name, t.cp_node_days_min,t.cp_node_days_max,t.cp_node_days from lcp_master_node t where t.cp_id="+cp_id+" and t.cp_node_type=1)ddd "+
				"where aaa.hospital_id=bbb.hospital_id and aaa.hospital_id=ccc.hospital_id and aaa.hospital_id=ddd.hospital_id and aaa.cp_id=bbb.cp_id and aaa.cp_id=ccc.cp_id and aaa.cp_id=ddd.cp_id and aaa.cp_node_id=bbb.cp_node_id and aaa.cp_node_id=ccc.cp_node_id and aaa.cp_node_id=ddd.cp_node_id "+
				") order by cp_node_id";
         System.out.println("tianchu_fbl= "+tianchu_fbl);
        DataSet tianchu_fbl_DataSet=new DataSet();
        tianchu_fbl_DataSet.funSetDataSetBySql(tianchu_fbl);
      	
        int tianchu_fbl_row=tianchu_fbl_DataSet.getRowNum();
        for(int i=0;i<tianchu_fbl_row;i++){
        	
       

%>
<tr bgcolor="#FFFFFF" class="STYLE19">
		<td height="24"><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"CP_NODE_NAME")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"CP_NODE_DAYS_MIN")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"CP_NODE_DAYS_MAX")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"CP_NODE_DAYS")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"SJZXZXTS")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"SJZDZYTS")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"SJZYPJTS")%></div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"ZDZYFBL")%>%</div></td>
		<td><div align="center"><%=tianchu_fbl_DataSet.funGetFieldByCol(i,"ZDZYRTS")%>天</div></td>
		</tr>
<%
        }
%>
		

				
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