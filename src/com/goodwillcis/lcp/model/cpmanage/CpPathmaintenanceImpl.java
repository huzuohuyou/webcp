/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CpDistributionImpl.java
// 文件功能描述：路径列表的实现类
// 创建人：康榕元
// 创建日期：2011/08/02
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model.cpmanage;

import com.goodwillcis.lcp.util.CommonUtil;

public class CpPathmaintenanceImpl implements CpPathmaintenance {

	@Override
	public String funGetCpTable(String sql, int start, int end) {
		// TODO Auto-generated method stub
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql,start,end);
		int row=dataSet.getRowNum();
		String table="";
		for(int i=0;i<row;i++){
			String cpId=dataSet.funGetFieldByCol(i, "CP_ID");
			String cpCode=dataSet.funGetFieldByCol(i, "CP_CODE");
			String cpName=dataSet.funGetFieldByCol(i, "CP_NAME");
			String cpVersion=dataSet.funGetFieldByCol(i, "CP_VERSION");
			String verify_data=dataSet.funGetFieldByCol(i, "VERIFY_DATE");
			String verify_name=dataSet.funGetFieldByCol(i, "VERIFY_NAME");
			String input_code_py=dataSet.funGetFieldByCol(i, "INPUT_CODE_PY");
			String input_code_wb=dataSet.funGetFieldByCol(i, "INPUT_CODE_WB");
			String shanchu = "";
			if(!"".equals(verify_data)){
				shanchu+="disabled='disabled'";
				verify_data = verify_data.substring(0,10);
			}
			
			table=table+"<tr height='20' bgcolor='#FFFFFF' style='cursor:pointer' onClick='NodeColor(this)' class='STYLE10' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)' id='"+cpId+"'>"+
				"<td  align='center' >"+cpCode+"</td>"+
		        "<td  align='left' >&nbsp;&nbsp;&nbsp;"+cpName+"</td>"+
		        "<td  align='left' >&nbsp;&nbsp;&nbsp;"+cpVersion+"</td>"+
		        "<td  align='left' >&nbsp;&nbsp;&nbsp;"+verify_data+"</td>"+
		        "<td  align='left'>&nbsp;&nbsp;&nbsp;"+verify_name+"</td>"+
		        "<td  align='left' >&nbsp;&nbsp;&nbsp;"+input_code_py+"</td>"+
		        "<td  align='left' >&nbsp;&nbsp;&nbsp;"+input_code_wb+"</td>"+
		        "<td  align='center'><input type='button'  value='执行单' ></td>" +
		        "<td  align='center'><input type='button'  value='路径图表'  onClick='tuxing("+cpId+")'></td>" +
		        "<td  align='center'><input name='"+cpName+"' type='button'  value='删除' id ='"+cpId+"' "+shanchu+"  onClick='shanchu(this)'></td>" +
		        "</tr>";
		}
		table=CommonUtil.replactCharacter(table);
		return table;
	}

	@Override
	public String funGetCpSql( String code, String name,
			String py, String wb) {
		// TODO Auto-generated method stub
		String sql="select t.cp_id,t.cp_code,t.cp_name,t.cp_version,t.verify_date,t.verify_name,t.input_code_py,t.input_code_wb from dcp_master t where 1=1";
		if(code!=""){
			sql+=" and t.cp_code like '%"+code+"%' ";
		}else if(name!=""){
			sql+=" and t.cp_name like '%"+name+"%' ";
		}else if(py!=""){
			sql=sql+" and (T.INPUT_CODE_PY like '%"+py.toLowerCase()+"%' or T.INPUT_CODE_PY like '%"+py.toUpperCase()+"%' or T.INPUT_CODE_PY like '%"+py+"%') ";
		}else if(wb!=""){
			sql=sql+" and (T.INPUT_CODE_WB like '%"+wb.toLowerCase()+"%' or T.INPUT_CODE_WB like '%"+wb.toUpperCase()+"%' or T.INPUT_CODE_WB like '%"+wb+"%' )";	
		}
		sql=sql+" order by t.cp_id ";
		return sql;
	}



}
