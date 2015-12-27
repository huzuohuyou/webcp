// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：OrderImpl .java
// 文件功能描述：医嘱接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/08/03
//
//	修改排版问题
//修改时间：2011-08-25
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.cp.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import com.goodwillcis.lcp.model.CpNodeTableMaint;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.service.cp.Order;
import com.goodwillcis.lcp.util.CommonUtil;

public class OrderImpl implements Order {

	@Override
	public String funGetTable(int hostipalID, int cp_id, int cp_node_id, boolean isUse) {
		// TODO Auto-generated method stub
		long start=new Date().getTime();
		String sql="SELECT *  FROM (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_NODE_ID, A.CP_NODE_ORDER_ID, 0 CP_NODE_ORDER_ITEM_ID, " +
				"A.CP_NODE_ORDER_TEXT, A.NEED_ITEM, A.AUTO_ITEM, A.ORDER_KIND ORDER_TYPE, '' ORDER_NO, 1 JIBIE, FLAG, LOCAL_CODE " +
				" FROM (SELECT A.*, CASE   WHEN B.CP_ID IS NULL THEN    0   ELSE    1 END AS FLAG, '' LOCAL_CODE    " +
				"FROM LCP_NODE_ORDER_POINT A, LCP_NODE_ORDER_ITEM B   WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+)     " +
				"AND A.CP_ID = B.CP_ID(+)     AND A.CP_NODE_ID = B.CP_NODE_ID(+)     AND A.CP_NODE_ORDER_ID = B.CP_NODE_ORDER_ID(+) AND A.CONTINUE_ORDER_ID <> A.CP_NODE_ORDER_ID) " +
				" A UNION SELECT B.HOSPITAL_ID, B.CP_ID, B.CP_NODE_ID, B.CP_NODE_ORDER_ID, B.CP_NODE_ORDER_ITEM_ID, B.CP_NODE_ORDER_TEXT," +
				" B.NEED_ITEM, B.AUTO_ITEM, '' ORDER_TYPE, B.ORDER_NO, 2 JIBIE, 0 FLAG, LOCAL_CODE  FROM LCP_NODE_ORDER_ITEM B," +
				" (SELECT ORDER_CODE CODE, WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE    FROM LCP_MATCH_ORDER A " +
				"where a.order_code in(  select order_no from lcp_node_order_item b   WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID = "+cp_id+" AND CP_NODE_ID = "+cp_node_id+")" +
				"" +
				"  GROUP BY A.ORDER_CODE)" +
				" C WHERE B.ORDER_NO = C.CODE(+)) WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+"";
		//System.out.println("------------=======:"+sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		long end=new Date().getTime();
		//System.out.println("==="+(end-start));
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_order_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID");
			String cp_node_order_item_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ITEM_ID");
			String cp_node_order_text=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT");
			String auto_item=dataSet.funGetFieldByCol(i, "AUTO_ITEM");
			String order_no=dataSet.funGetFieldByCol(i, "ORDER_NO");
			String code=dataSet.funGetFieldByCol(i, "LOCAL_CODE");
			code=code.replace(",", "<br/>");
			String flag=dataSet.funGetFieldByCol(i, "FLAG");
			String jibie=dataSet.funGetFieldByCol(i, "JIBIE");
			String need_item=dataSet.funGetFieldByCol(i, "NEED_ITEM");
			if(need_item.equals("0")){
				need_item="可做";
			}else{
				need_item="必做";
			}
			String order_type=dataSet.funGetFieldByCol(i, "ORDER_TYPE");
			if(order_type.equals("0")){
				order_type="临时";
			}else if(order_type.equals("1")){
				order_type="长期";
			}else if(order_type.equals("2")){
				order_type="出院";
			}else if(order_type.equals("3")){
				order_type="长期+临时";
			}
			if(jibie.equals("1")){
				if(flag.equals("1")){//第一级别，有孩子
					table=table+"<tr bgcolor='#FFFFFF'>"+
					"<td height='20' width='3%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'><a  href='#'><img src='../public/plugins/jquery/images/bullet_toggle_minus.png' width='15' height='15'  border='0'/></a></span></div></td>"+
					"<td height='20' width='50%'  colspan='2'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+cp_node_order_text+"</span></div></td>"+
					"<td height='20' width='5%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+need_item+"</span></div></td>"+
					"<td height='20' width='5%'  align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+order_type+"</span></div></td>"+
					"<td height='20' width='37%'   align='center' colspan='2' class='STYLE6'></td>"+
					"</tr>";
				}else{//第一级别，没孩子
					table=table+"<tr bgcolor='#FFFFFF'>"+
					"<td height='20' width='3%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'></span></div></td>"+
					"<td height='20' width='50%' colspan='2'  align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+cp_node_order_text+"</span></div></td>"+
					"<td height='20' width='5%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+need_item+"</span></div></td>"+
					"<td height='20' width='5%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+order_type+"</span></div></td>"+
					"<td height='20' width='37%' align='center' colspan='2' class='STYLE6'></td>"+
					"</tr>";
				}
			}else{
				if(isUse){
					if(auto_item.equals("0")){
						auto_item="<select name='select_2_3' id='select_2_3_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"_"+cp_node_order_item_id+"'   disabled='disabled'><option value='0' selected='selected'>自动</option><option value='1'>手动</option></select>";
					}else{
						auto_item="<select name='select_2_3' id='select_2_3_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"_"+cp_node_order_item_id+"'    disabled='disabled'><option value='0' >自动</option><option value='1' selected='selected'>手动</option></select>";
					}
				}
				else{
					if(auto_item.equals("0")){
						auto_item="<select name='select_2_3' id='select_2_3_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"_"+cp_node_order_item_id+"'    disabled='disabled'><option value='0' selected='selected'>自动</option><option value='1'>手动</option></select>";
					}else{
						auto_item="<select  name='select_2_3' id='select_2_3_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"_"+cp_node_order_item_id+"'    disabled='disabled'><option value='0' >自动</option><option value='1' selected='selected'>手动</option></select>";
					}
				}
				table=table+"<tr bgcolor='#FFFFFF'>"+
				"<td width='3%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'></span></div></td>"+
				"<td width='35%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</span></div></td>"+
				"<td width='15%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+order_no+"</span></div></td>"+
				"<td width='5%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>"+need_item+"</span></div></td>"+
				"<td width='5%' align='center' class='STYLE6'><div align='center'><span class='STYLE10'>-----</span></div></td>"+
				"<td width='15%' align='left' class='STYLE6'><div align='left'><span class='STYLE10'>"+auto_item+"</span></div></td>"+
				"<td width='22%' align='left' class='STYLE6'><div><span align='left' style='font-size: 12px;'  id='2_3_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"_"+cp_node_order_item_id+"'>"+code+"</span>" ;
				if(isUse){
					table=table+"<span><input name='duizhao_2_3' type='button'  style='font-size: 12px;'  code='"+order_no+"' value='对照' onclick='duizhaoButton(2,3,"+cp_id+","+cp_node_id+","+cp_node_order_id+","+cp_node_order_item_id+",5,this);'/></span>";
				}
				table=table+"</div></td>"+
				"</tr>";
			}
		}
		//System.out.println(table);
		return table.replace("\r\n", "").replace("\r", "").replace("\n", "");
	}

	@Override
	public String funGetNewCode(int hostipalID, int cp_id,
			int cp_node_id, int cp_node_doctor_id, int cp_node_doctor_item_id) {
		// TODO Auto-generated method stub
		String sql="SELECT *  FROM (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_NODE_ID, A.CP_NODE_ORDER_ID, 0 CP_NODE_ORDER_ITEM_ID, " +
				"A.CP_NODE_ORDER_TEXT, A.NEED_ITEM, A.AUTO_ITEM, A.ORDER_KIND ORDER_TYPE, '' ORDER_NO, 1 JIBIE, FLAG, LOCAL_CODE " +
				" FROM (SELECT A.*, CASE   WHEN B.CP_ID IS NULL THEN    0   ELSE    1 END AS FLAG, '' LOCAL_CODE    " +
				"FROM LCP_NODE_ORDER_POINT A, LCP_NODE_ORDER_ITEM B   WHERE A.HOSPITAL_ID = B.HOSPITAL_ID(+)     " +
				"AND A.CP_ID = B.CP_ID(+)     AND A.CP_NODE_ID = B.CP_NODE_ID(+)     AND A.CP_NODE_ORDER_ID = B.CP_NODE_ORDER_ID(+))" +
				" A UNION SELECT B.HOSPITAL_ID, B.CP_ID, B.CP_NODE_ID, B.CP_NODE_ORDER_ID, B.CP_NODE_ORDER_ITEM_ID, B.CP_NODE_ORDER_TEXT," +
				" B.NEED_ITEM, B.AUTO_ITEM, '' ORDER_TYPE, B.ORDER_NO, 2 JIBIE, 0 FLAG, LOCAL_CODE  FROM LCP_NODE_ORDER_ITEM B," +
				" (SELECT ORDER_CODE CODE, WMSYS.WM_CONCAT(A.LOCAL_CODE) LOCAL_CODE    FROM LCP_MATCH_ORDER A   GROUP BY A.ORDER_CODE)" +
				" C WHERE B.ORDER_NO = C.CODE(+)) WHERE HOSPITAL_ID = "+hostipalID+" AND CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+"  AND CP_NODE_ORDER_ID="+cp_node_doctor_id+" AND CP_NODE_ORDER_ITEM_ID="+cp_node_doctor_item_id+"";
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
			sql=sql+"UPDATE LCP_NODE_ORDER_ITEM  SET AUTO_ITEM="+auto_item+", SYS_LAST_UPDATE="+time+" WHERE HOSPITAL_ID="+hostipalID+"" +
					" AND CP_ID="+cp_id+" AND CP_NODE_ID = "+cp_node_id+" AND CP_NODE_ORDER_ID ="+cp_node_doctor_id+" AND CP_NODE_ORDER_ITEM_ID" +
					"="+cp_node_doctor_item_id+"\r\n";
		}
		for(int i=0;i<len_point;i++){
			String cp_id=list_point.get(i).getCp_id();
			String cp_node_id=list_point.get(i).getCp_node_id();
			String cp_node_doctor_id=list_point.get(i).getCp_node_table_id();
			String auto_item=list_point.get(i).getAuto_item();
			sql=sql+"UPDATE LCP_NODE_ORDER_POINT  SET AUTO_ITEM="+auto_item+", SYS_LAST_UPDATE="+time+" WHERE HOSPITAL_ID="+hostipalID+"" +
					" AND CP_ID="+cp_id+" AND CP_NODE_ID = "+cp_node_id+" AND CP_NODE_ORDER_ID ="+cp_node_doctor_id+"\r\n";
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
