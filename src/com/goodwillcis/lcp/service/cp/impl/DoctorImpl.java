// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：DoctorImpl .java
// 文件功能描述：诊疗接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/02
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import com.goodwillcis.lcp.model.CpNodeTableMaint;
import com.goodwillcis.lcp.model.DataSet;
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：DoctorImpl .java
//文件功能描述：诊疗接口实现类
//创建人：刘植鑫 
//创建日期：2011/08/03
//
//----------------------------------------------------------------*/
import com.goodwillcis.lcp.service.cp.Doctor;
import com.goodwillcis.lcp.util.CommonUtil;

public class DoctorImpl implements Doctor {

	@Override
	public String funGetTable(int hostipalID, int cp_id, int cp_node_id, boolean isUse) {
		// TODO Auto-generated method stub
		String sql="SELECT *  FROM (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_NODE_ID, A.CP_NODE_DOCTOR_ID," +
				" 0 CP_NODE_DOCTOR_ITEM_ID, A.CP_NODE_DOCTOR_TEXT, A.NEED_ITEM, A.AUTO_ITEM," +
				" '' DOCTOR_NO, 1 JIBIE, FLAG, LOCAL_CODE   FROM (SELECT A.*,  CASE    WHEN B.CP_ID IS NULL THEN" +
				"     0    ELSE     1  END AS FLAG,  '' LOCAL_CODE    FROM LCP_NODE_DOCTOR_POINT A, LCP_NODE_DOCTOR_ITEM B " +
				"  WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+)     AND A.CP_ID = B.CP_ID(+)     AND A.CP_NODE_ID = B.CP_NODE_ID(+)" +
				"     AND A.CP_NODE_DOCTOR_ID = B.CP_NODE_DOCTOR_ID(+)) A UNION SELECT B.HOSPITAL_ID, B.CP_ID, B.CP_NODE_ID," +
				" B.CP_NODE_DOCTOR_ID, B.CP_NODE_DOCTOR_ITEM_ID, B.CP_NODE_DOCTOR_TEXT, B.NEED_ITEM, B.AUTO_ITEM, " +
				"B.DOCTOR_NO, 2 JIBIE, 0 FLAG, LOCAL_CODE   FROM LCP_NODE_DOCTOR_ITEM B, (SELECT DOCTOR_CODE CODE,  " +
				"WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE    FROM LCP_MATCH_DOCTOR A  " +
				" where a.DOCTOR_CODE in(  select doctor_no from lcp_node_doctor_item b   WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID = "+cp_id+" AND CP_NODE_ID = "+cp_node_id+")" +
				" GROUP BY A.DOCTOR_CODE) C " +
				" WHERE B.DOCTOR_NO = C.CODE(+)  ) " +
				" WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_doctor_id=dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_ID");
			String cp_node_doctor_item_id=dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_ITEM_ID");
			String cp_node_doctor_text=dataSet.funGetFieldByCol(i, "CP_NODE_DOCTOR_TEXT");
			String auto_item=dataSet.funGetFieldByCol(i, "AUTO_ITEM");
			String doctor_no=dataSet.funGetFieldByCol(i, "DOCTOR_NO");
			String code=dataSet.funGetFieldByCol(i, "LOCAL_CODE");
			code=code.replace(",", " 、  ");
			String flag=dataSet.funGetFieldByCol(i, "FLAG");
			String jibie=dataSet.funGetFieldByCol(i, "JIBIE");
			
			if(jibie.equals("1")){
				if(flag.equals("1")){//第一级别，有孩子
					table=table+"<tr bgcolor='#FFFFFF'>"+
					"<td height='20'width='3%'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'><a  href='#'><img src='../public/plugins/jquery/images/bullet_toggle_minus.png' width='15' height='15'  border='0'/></a></span></div></td>"+
					"<td height='20' colspan='4'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+cp_node_doctor_text+"</span></div></td>"+
					"</tr>";
				}else{//第一级别，没孩子
					table=table+"<tr bgcolor='#FFFFFF'>"+
					"<td height='20' width='3%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'></span></div></td>"+
					"<td height='20' colspan='4'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+cp_node_doctor_text+"</span></div></td>"+
					"</tr>";
				}
			}else{
				if(isUse){
					if(auto_item.equals("0")){
						auto_item="<select name='select_2_1' id='select_2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_doctor_id+"_"+cp_node_doctor_item_id+"' disabled='disabled'><option value='0' selected='selected'>自动</option><option value='1'>手动</option></select>";
					}else{
						auto_item="<select name='select_2_1' id='select_2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_doctor_id+"_"+cp_node_doctor_item_id+"' disabled='disabled'><option value='0' >自动</option><option value='1' selected='selected'>手动</option></select>";
					}
				}
				else{
					if(auto_item.equals("0")){
						auto_item="<select name='select_2_1' id='select_2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_doctor_id+"_"+cp_node_doctor_item_id+"' disabled='disabled'><option value='0' selected='selected'>自动</option><option value='1'>手动</option></select>";
					}else{
						auto_item="<select  name='select_2_1' id='select_2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_doctor_id+"_"+cp_node_doctor_item_id+"' disabled='disabled'><option value='0' >自动</option><option value='1' selected='selected'>手动</option></select>";
					}
				}
				table=table+"<tr bgcolor='#FFFFFF'>"+
				"<td height='20'width='3%'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'></span></div></td>"+
				"<td height='20' width='30%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_doctor_text+"</span></div></td>"+
				"<td height='20' width='*' align='left' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;&nbsp;"+doctor_no+"</span></div></td>"+
				"<td height='20' width='15%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+auto_item+"</span></div></td>"+
				"<td height='20' width='30%' align='left' class='STYLE6'><div><span align='left' style='font-size: 12px;' id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_doctor_id+"_"+cp_node_doctor_item_id+"'>"+code+"</span>" ;
				if(isUse){
					table=table+"<span><input name='duizhao_2_1' type='button' style='font-size: 12px;'  code='"+doctor_no+"' value='对照' onclick='duizhaoButton(2,1,"+cp_id+","+cp_node_id+","+cp_node_doctor_id+","+cp_node_doctor_item_id+",3,this);'/></span>";
				}
				table=table+"</div></td>"+
				"</tr>";
			}
		}
		//System.out.println(table);
		return table;
	}

	@Override
	public String funGetNewCode(int hostipalID, int cp_id,
			int cp_node_id, int cp_node_doctor_id, int CP_NODE_DOCTOR_ITEM_ID) {
		// TODO Auto-generated method stub
		String sql="SELECT *  FROM (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_NODE_ID, A.CP_NODE_DOCTOR_ID," +
				" 0 CP_NODE_DOCTOR_ITEM_ID, A.CP_NODE_DOCTOR_TEXT, A.NEED_ITEM, A.AUTO_ITEM," +
				" '' DOCTOR_NO, 1 JIBIE, FLAG, LOCAL_CODE   FROM (SELECT A.*,  CASE    WHEN B.CP_ID IS NULL THEN" +
				"     0    ELSE     1  END AS FLAG,  '' LOCAL_CODE    FROM LCP_NODE_DOCTOR_POINT A, LCP_NODE_DOCTOR_ITEM B " +
				"  WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+)     AND A.CP_ID = B.CP_ID(+)     AND A.CP_NODE_ID = B.CP_NODE_ID(+)" +
				"     AND A.CP_NODE_DOCTOR_ID = B.CP_NODE_DOCTOR_ID(+)) A UNION SELECT B.HOSPITAL_ID, B.CP_ID, B.CP_NODE_ID," +
				" B.CP_NODE_DOCTOR_ID, B.CP_NODE_DOCTOR_ITEM_ID, B.CP_NODE_DOCTOR_TEXT, B.NEED_ITEM, B.AUTO_ITEM, " +
				"B.DOCTOR_NO, 2 JIBIE, 0 FLAG, LOCAL_CODE   FROM LCP_NODE_DOCTOR_ITEM B, (SELECT DOCTOR_CODE CODE,  " +
				"WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE    FROM LCP_MATCH_DOCTOR A   GROUP BY A.DOCTOR_CODE) C " +
				" WHERE B.DOCTOR_NO = C.CODE(+)  ) " +
				" WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+"  AND CP_NODE_DOCTOR_ID="+cp_node_doctor_id+" AND CP_NODE_DOCTOR_ITEM_ID="+CP_NODE_DOCTOR_ITEM_ID+"";
		//System.out.println(sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String code=dataSet.funGetFieldByCol(0, "LOCAL_CODE");
		return code.replace(",", "、");
	}


	@Override
	public boolean funSetNodeItem(int hostipalID, String IDS) {
		// TODO Auto-generated method stub
		ArrayList<CpNodeTableMaint> list_item=CommonUtil.funSetCpNodeTableMaintItemByStr(IDS);
		ArrayList<CpNodeTableMaint> list_point=CommonUtil.funSetCpNodeTableMaintPointByStr(IDS);
		int len_item=list_item.size();
	//	System.out.println(len_item);
		int len_point=list_point.size();
	//	System.out.println(len_point);
		String sql="";
		String time=CommonUtil.getOracleToDate();
		for(int i=0;i<len_item;i++){
			String cp_id=list_item.get(i).getCp_id();
			String cp_node_id=list_item.get(i).getCp_node_id();
			String cp_node_doctor_id=list_item.get(i).getCp_node_table_id();
			String cp_node_doctor_item_id=list_item.get(i).getCp_node_table_item_id();
			String auto_item=list_item.get(i).getAuto_item();
			sql=sql+"UPDATE LCP_NODE_DOCTOR_ITEM  SET AUTO_ITEM="+auto_item+",  SYS_LAST_UPDATE="+time+" WHERE HOSPITAL_ID="+hostipalID+"" +
					" AND CP_ID="+cp_id+" AND CP_NODE_ID = "+cp_node_id+" AND CP_NODE_DOCTOR_ID ="+cp_node_doctor_id+" AND CP_NODE_DOCTOR_ITEM_ID" +
					"="+cp_node_doctor_item_id+"\r\n";
		}
		for(int i=0;i<len_point;i++){
			String cp_id=list_point.get(i).getCp_id();
			String cp_node_id=list_point.get(i).getCp_node_id();
			String cp_node_doctor_id=list_point.get(i).getCp_node_table_id();
			String auto_item=list_point.get(i).getAuto_item();
			sql=sql+"UPDATE LCP_NODE_DOCTOR_POINT  SET AUTO_ITEM="+auto_item+",  SYS_LAST_UPDATE="+time+" WHERE HOSPITAL_ID="+hostipalID+"" +
					" AND CP_ID="+cp_id+" AND CP_NODE_ID = "+cp_node_id+" AND CP_NODE_DOCTOR_ID ="+cp_node_doctor_id+"\r\n";
		}
		//System.out.println(sql);
		DataSet dataSet=new DataSet();
		int aa=-1;
		try {
			aa=dataSet.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(aa>0)
			return true;
		return false;
	}

}
