/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： MatchLocal.java
// 文件功能描述：查询本地数据库调用的各种方法
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.match.impl;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.LocalDBMatch;
import com.goodwillcis.lcp.service.match.MatchLocal;

public class MatchLocalImpl implements MatchLocal {

	@Override
	public String funGetTable(String sql, String code, String name, String py,
			String wb) {
		// TODO Auto-generated method stub
		String table="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql, 1, 10);
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String _code=dataSet.funGetFieldByCol(i, code.toUpperCase());
			String _name=dataSet.funGetFieldByCol(i, name.toUpperCase());
			String inputCodePy=dataSet.funGetFieldByCol(i, py.toUpperCase());
			String inputCodeWb=dataSet.funGetFieldByCol(i, wb.toUpperCase());
			_name=_name.replace("\\", "\\\\");
			table=table+"<tr name='youshangjiao'  style='cursor:hand'  id='"+_code+"'class='STYLE19'  onclick='onclickColor(this)' bgcolor='#FFFFFF'  onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
			"<td><div align='left'>"+_code+"</div></td>" +
			"<td><div align='left'>"+_name+"</div></td>" +
			"<td><div align='left'>"+inputCodePy+"</div></td>" +
			"<td><div align='left'>"+inputCodeWb+"</div></td>" +	
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
