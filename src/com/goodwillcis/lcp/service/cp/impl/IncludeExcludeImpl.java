// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：IncludeExcludeImpl .java
// 文件功能描述：纳入排除条件接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/02
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.IncludeExclude;

public class IncludeExcludeImpl implements IncludeExclude {

	@Override
	public String funGetIncludeTable(int hostipalID,int cp_id,boolean isUse) {
		// TODO Auto-generated method stub
		String sql="SELECT AA.CP_ID,AA.CP_INCOME_ID,AA.CP_INCOME_TYPE,AA.CP_INCOME_NAME,AA.CP_INCOME_CODE," +
				" CASE WHEN AA.CP_INCOME_TYPE='手术' THEN 2 ELSE  1 END AS FLAG, " +
				"BB.LOCAL_CODE FROM LCP_MASTER_INCOME AA, (" +
				"SELECT '手术' TYPE,OPERATION_CODE CODE," +
				"WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE FROM LCP_MATCH_OPERATION A " +
				"where a.OPERATION_CODE in(select t.cp_income_code from lcp_master_income t where t.cp_income_type='手术' AND t.CP_ID="+cp_id+" AND t.HOSPITAL_ID="+hostipalID+")" +
				" GROUP BY A.OPERATION_CODE " +
				"UNION " +
				"SELECT '诊断' TYPE,DIAGNOSIS_CODE CODE,WMSYS.WM_CONCAT(B.LOCAL_CODE) LOCAL_CODE" +
				" FROM LCP_MATCH_DIAGNOSIS B " +
				"where b.DIAGNOSIS_CODE in(select t.cp_income_code from lcp_master_income t where t.cp_income_type='诊断' AND t.CP_ID="+cp_id+" AND t.HOSPITAL_ID="+hostipalID+")" +
				" GROUP BY B.DIAGNOSIS_CODE)BB WHERE AA.CP_INCOME_TYPE=BB.TYPE(+)" +
				" AND AA.CP_INCOME_CODE=BB.CODE(+) AND AA.CP_ID="+cp_id+" AND AA.HOSPITAL_ID="+hostipalID+" ";
			sql=sql+" ORDER BY AA.CP_INCOME_ID";
			//System.out.println("纳入排除"+sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_income_type=dataSet.funGetFieldByCol(i, "CP_INCOME_TYPE");
			String cp_income_name=dataSet.funGetFieldByCol(i, "CP_INCOME_NAME");
			String cp_income_code=dataSet.funGetFieldByCol(i, "CP_INCOME_CODE");
			String local_code=dataSet.funGetFieldByCol(i, "LOCAL_CODE");
			local_code=local_code.replace(",", " 、  ");
			String flag=dataSet.funGetFieldByCol(i, "FLAG");
			String _cp_income_id=dataSet.funGetFieldByCol(i, "CP_INCOME_ID");
			table=table+"<tr bgcolor='#FFFFFF'>"+
				"<td height='20'  class='STYLE6'><div align='center'><span class='STYLE10'>"+cp_income_type+"</span></div></td>"+
		        "<td height='20'  class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_income_name+"</span></div></td>"+
		        "<td height='20'  class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_income_code+"</span>";
			if(isUse){
				table=table+"<td height='20' align='left' class='STYLE6'><div align='left' ><span class='STYLE10' id='1_1_"+cp_id+"_0_"+_cp_income_id+"_0'>&nbsp;&nbsp; "+local_code+"</span></div></td>" +
		       	"<td width='30' align='right'  class='STYLE6'>" +
		       	"<div align='right'>" +
		       	"<input code='"+cp_income_code+"' name='duizhao_1_1' style='font-size: 12px;'  type='button' value='对照'  onclick='duizhaoButton(1,1,"+cp_id+",0,"+_cp_income_id+",0,"+flag+",this);'/>" +
		       	"</div>" +
		       	"</td>";
				
			}else{
				table=table+"<td colspan='2' height='20' align='left' class='STYLE6'><div align='left' ><span class='STYLE10' id='include_exclude_local_"+(i)+"'>&nbsp;&nbsp; "+local_code+"</span></div></td>";
			}
		       
				table=table+"</tr>";
		}
		//System.out.println(table);
		return table;
	}

	@Override
	public String funGetExcludeTable(int hostipalID,int cp_id) {
		// TODO Auto-generated method stub
		String sql="SELECT T.CP_EXCLUDE_NAME FROM LCP_MASTER_EXCLUDE T WHERE T.CP_ID="+cp_id+" AND T.HOSPITAL_ID="+hostipalID+" ORDER BY T.DISPLAY_ORDER ASC";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_exclude_name=dataSet.funGetFieldByCol(i, "CP_EXCLUDE_NAME");
			table=table+"<tr bgcolor='#FFFFFF'>"+
			"<td height='20'  align='right' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;"+cp_exclude_name+"</span></div></td>"+
			"</tr>";
		}
		return table;
	}

	@Override
	public String funGetNewLocal(int hostipalID, int cp_id, int cp_income_id) {
		// TODO Auto-generated method stub
		String sql="SELECT AA.CP_ID,AA.CP_INCOME_ID,AA.CP_INCOME_TYPE,AA.CP_INCOME_NAME,AA.CP_INCOME_CODE," +
				" CASE WHEN AA.CP_INCOME_TYPE='手术' THEN 2 ELSE  1 END AS FLAG, " +
				"BB.LOCAL_CODE FROM LCP_MASTER_INCOME AA, (SELECT '手术' TYPE,OPERATION_CODE CODE," +
				"WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE FROM LCP_MATCH_OPERATION A GROUP BY " +
				"A.OPERATION_CODE UNION SELECT '诊断' TYPE,DIAGNOSIS_CODE CODE,WMSYS.WM_CONCAT(B.LOCAL_CODE) LOCAL_CODE" +
				" FROM LCP_MATCH_DIAGNOSIS B GROUP BY B.DIAGNOSIS_CODE)BB WHERE AA.CP_INCOME_TYPE=BB.TYPE(+)" +
				" AND AA.CP_INCOME_CODE=BB.CODE(+) AND AA.CP_ID="+cp_id+" AND AA.HOSPITAL_ID="+hostipalID+" AND AA.CP_INCOME_ID="+cp_income_id+" ";
			sql=sql+" ORDER BY AA.CP_INCOME_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String local_code=dataSet.funGetFieldByCol(i, "LOCAL_CODE");
			local_code=local_code.replace(",", " 、  ");
			table=table+"&nbsp;&nbsp; "+local_code+"";
		}
		return table;
	}

}
