/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchCPLocal.java
// 文件功能描述：查询本地数据库调用的各种方法
// 创建人：张昆
// 创建日期：2012/05/10
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match.impl;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.LocalDBMatch;
import com.goodwillcis.lcp.service.match.MatchCPLocal;

public class MatchCPLocalImpl implements MatchCPLocal {

	@Override
	public String funGetTable(String sql, String code, String name, String py,
			String wb) {
		String table="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql, 1, 10);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cpID=dataSet.funGetFieldByCol(i, code.toUpperCase());
			String cpName=dataSet.funGetFieldByCol(i, name.toUpperCase());
			String cpVersion=dataSet.funGetFieldByCol(i, py.toUpperCase());
			String cpMasterID=dataSet.funGetFieldByCol(i, wb.toUpperCase());
			if(cpMasterID.equals("0")){
					cpMasterID="";
				}else{
					cpMasterID="√";
			}
			cpName=cpName.replace("\\", "\\\\");
			table=table+"<tr name='youshangjiao'  style='cursor:hand'  mingcheng='"+cpName+"' id='"+cpID+"'class='STYLE19'  onclick='onclickColor(this)' bgcolor='#FFFFFF'  onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			"<td><div align='center'>"+cpID+"</div></td>" +
			"<td><div align='left'>"+cpName+"</div></td>" +
			"<td><div align='center'>"+cpVersion+"</div></td>" +
			"<td><div align='center'>"+cpMasterID+"</div></td>" +	
			"</tr>";
		}
		return table.replace("\"", "&#34;");
	}

	@Override
	public ArrayList<LocalDBMatch> funGetCoreList(String sql, String code,
			String name, String py, String wb) {
		// TODO Auto-generated method stub
		ArrayList<LocalDBMatch> coreList=new ArrayList<LocalDBMatch>();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String _code=dataSet.funGetFieldByCol(i, code.toUpperCase());
			String _name=dataSet.funGetFieldByCol(i, name.toUpperCase());
			String inputCodePy=dataSet.funGetFieldByCol(i, py.toUpperCase());
			String inputCodeWb=dataSet.funGetFieldByCol(i, wb.toUpperCase());
			LocalDBMatch match=new LocalDBMatch(_code, _name, inputCodePy, inputCodeWb);
			coreList.add(match);
		}
		return coreList;
	}

}
