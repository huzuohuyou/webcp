/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：OrderTable.java    
// 文件功能描述：医嘱的相关操作(未完成)
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-28
 * 
 * 业务需求修改程序item显示问题，不管是不是自动，都不允许勾选下达框、
 * 修改point的复选框问题，不管是不是自动项都不允许签名
 * 修改时间：2011-08-29
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class OrderTable {
	/**
	 * 取得LCP_PATIENT_ORDER 表格内容
	 * 2011-06-01
	 * @return 返回table表格中的行
	 */
	public String createTable(RouteExecuteInfo info){
		String tableSql="SELECT *  FROM LCP_PATIENT_ORDER_POINT T  " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
		" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+info.getCpNodeID()+" AND nvl(T.CONTINUE_ORDER_ID,0) <> T.CP_NODE_ORDER_ID ORDER BY CP_NODE_ORDER_ID";
		
		//System.out.println("4444444444444444tableSql=:"+tableSql);
		String tableHTML="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(tableSql);
		int row=dataSet.getRowNum();
		if(info.isExecute()){
			for(int i=0;i<row;i++){	
				String isChecked="";
				String bgColor="#FFFFFF";
				String CheckAble="";
				boolean isExe=dataSet.funGetFieldByCol(i,"EXE_STATE").equals("1")?true:false;
				String isMust=dataSet.funGetFieldByCol(i,"NEED_ITEM").equals("1")?"是":"否";
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				//String isContinue=dataSet.funGetFieldByCol(i, "CONTINUE_ITEM").equals("0")?"不延续":"延续";
				String isContinue="";
				String trName="";
				if(isSign){
					bgColor="#51b2f6";
					CheckAble="disabled='disabled'";
					trName="unChangeColorOrderPoint";
				}else{
					trName="changeColorOrderPoint";//没有签名
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				trName="unChangeColorOrderPoint";
				CheckAble="disabled='disabled'";
				String checkID="checkboxOrderPoint"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID");
				tableHTML=tableHTML+"<tr jibie='sc' bianse='order_"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'  name='"+trName+"' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td width='4%'><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"</div></td>" +
				"<td width='37%'><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT")+"</div></td>" +
				"<td  width='6%'><div align='center'>"+isMust+"</div></td>" +
				"<td width='12%'><div align='center'>"+isContinue+"</div></td>" +
				"<td width='6%'><div align='center'><input type='checkbox' name='checkboxOrderPoint' id='"+checkID+"'  onclick='changeColorByCheckbox(&quot;"+checkID+"&quot;)' "+isChecked+CheckAble+"/></div></td>" +
				"<td width='7%'><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td width='24%'><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
				
				
			}
		}else{
			for(int i=0;i<row;i++){	
				String isChecked="";
				String bgColor="#FFFFFF";
				String CheckAble="disabled='disabled'";
				boolean isExe=dataSet.funGetFieldByCol(i,"EXE_STATE").equals("1")?true:false;
				String isMust=dataSet.funGetFieldByCol(i,"NEED_ITEM").equals("1")?"必做项":"可选项";
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				String isContinue=dataSet.funGetFieldByCol(i, "CONTINUE_ITEM").equals("0")?"不延续":"延续";
				if(isSign){
					bgColor="#51b2f6";
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				
				tableHTML=tableHTML+"<tr name='doctorTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"</div></td>" +
				"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT")+"</div></td>" +
				"<td><div align='center'>"+isMust+"</div></td>" +
				"<td><div align='center'>"+isContinue+"</div></td>" +
				"<td><div align='center'><input type='checkbox' name='checkboxOrderPoint' id='checkbox"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'  onclick='changeColorByCheckbox(this)' "+isChecked+CheckAble+"/></div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
			}
			
		}
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}
	
	/**
	 * 从LCP_NODE_ORDER表中查出输入插入到LCP_PATIENT_ORDER中
	 * @param info
	 * @param cpNodeID
	 * @return 负数：错误，整数：插入的行数
	 */
	public int InsertOrderTable(RouteExecuteInfo info,int cpNodeID){
//		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_ORDER_ID," +
//				"T.CP_NODE_ORDER_TEXT,T.NEED_ITEM,T.AUTO_ITEM,T.CONTINUE_ITEM,T.CONTINUE_CP_NODE_ID,T.CONTINUE_ORDER_ID," +
//				"T.ORDER_KIND FROM LCP_NODE_ORDER_POINT T " +
//			"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
//		String sqlItem="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_ORDER_ID,T.CP_NODE_ORDER_ITEM_ID,T.LOCAL_ORDER_NO, T.LOCAL_ORDER_TEXT," +
//		"T.CP_NODE_ORDER_TEXT,T.NEED_ITEM,T.AUTO_ITEM,T.ORDER_NO,ORDER_TYPE_NAME," +
//		"T.ORDER_TYPE,ORDER_KIND FROM LCP_NODE_ORDER_ITEM T " +
//	"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
//
//		DataSet dataSet=new DataSet();
//		dataSet.funSetDataSetBySql(sql);
//		int row=dataSet.getRowNum();
//		String insertSql="";
//		String time=CommonUtil.getOracleToDate();
//		for(int i=0;i<row;i++){
//			String names=dataSet.funGetFieldInsertSQL();
//			String values=dataSet.funGetFieldValueInsertSQL(i);
//			insertSql=insertSql+"" +
//					"INSERT INTO LCP_PATIENT_ORDER_POINT("+names+",PATIENT_NO," +
//					"EXE_STATE,STATE_ITEM,SYS_IS_DEL,SYS_LAST_UPDATE)" +
//					"VALUES("+values+",'"+info.getPatientNo()+"',0,1,0,"+time+")\r\n";
//		}
//		
//		DataSet dataSetItem=new DataSet();
//		dataSetItem.funSetDataSetBySql(sqlItem);
//		int rowItem=dataSetItem.getRowNum();
//		String insertSqlItem="";
//		for(int j=0;j<rowItem;j++){
//			String names=dataSetItem.funGetFieldInsertSQL();
//			String values=dataSetItem.funGetFieldValueInsertSQL(j);
//			insertSqlItem=insertSqlItem+""+
//						"INSERT INTO LCP_PATIENT_ORDER_ITEM("+names+",PATIENT_NO," +
//						"EXE_STATE,STATE_ITEM,SYS_IS_DEL,SYS_LAST_UPDATE)" +
//						"VALUES("+values+",'"+info.getPatientNo()+"',0,0,0,"+time+")\r\n";
//			
//		}
//		//System.out.println(insertSqlItem);
//		
//		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
//		int aa=0;
//		if(row>0){
//			try {
//				aa = databaseClass.FunRunSqlByFile((insertSql+insertSqlItem).getBytes("GB2312"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();aa=-1;
//				TableException.rollBackAll(info, cpNodeID);
//			}
//		}
//		return aa;
		
		int result = 0;
		//lcp_node_order的数据同步到lcp_patient_order里(作废)
//		String synSql1 = "insert into lcp_patient_order (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_std_order_id,cp_node_order_text,need_item,auto_item,order_no,order_type,sys_is_del,sys_last_update) "+
//						"(select t.hospital_id,'"+patientId+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_std_order_id,t.cp_node_order_text,t.need_item,t.auto_item,t.order_no,t.order_type,t.sys_is_del,t.sys_last_update from lcp_node_order t" +
//						" where t.hospital_id = "+hospitalId+" and t.cp_id='"+cpId+"' and t.cp_node_id='"+cpNodeId+"')\r\n";
		
		//lcp_node_order_point的数据同步到lcp_patient_order_point里
	    String synSql2 = "insert into lcp_patient_order_point (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_text,continue_item,continue_cp_node_id,continue_order_id,need_item,auto_item,order_kind,sys_is_del,sys_last_update) "+
						"(select t.hospital_id,'"+info.getPatientNo()+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_node_order_text,t.continue_item,t.continue_cp_node_id,t.continue_order_id,t.need_item,t.auto_item,t.order_kind,t.sys_is_del,t.sys_last_update from lcp_node_order_point t" +
						" where t.hospital_id = "+info.getHostipalID()+" and t.cp_id='"+info.getCpID()+"' and t.cp_node_id='"+cpNodeID+"')\r\n";
		
		//lcp_node_order_item的数据同步到lcp_patient_order_item里
	    String synSql3 = "insert into lcp_patient_order_item (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_item_id,cp_node_order_text,order_no,order_type,order_type_name,order_kind,frequency,way,order_item_set_id,SPECIFICATION,ADMINISTRATION,measure,MEASURE_UNITS,DOSAGE,DOSAGE_UNITS,UNIT_ID,MARK,need_item,auto_item,cp_node_class_id,IS_ANTIBIOTIC,effect_flag,sys_is_del,sys_last_update)"+
	    				 " (select a.hospital_id,'"+info.getPatientNo()+"',a.cp_id,a.cp_node_id, a.cp_node_order_id,a.cp_node_order_item_id,a.CP_NODE_ORDER_TEXT,a.ORDER_NO,a.ORDER_TYPE,a.ORDER_TYPE_NAME,a.ORDER_KIND,a.frequency,a.way,a.order_item_set_id,a.SPECIFICATION,a.ADMINISTRATION,a.measure,a.MEASURE_UNITS,a.DOSAGE,a.DOSAGE_UNITS,a.UNIT_ID,a.MARK,a.need_item,a.auto_item,a.cp_node_class_id,a.IS_ANTIBIOTIC,a.effect_flag,sys_is_del,sys_last_update from lcp_node_order_item a" +
	    				 " where a.hospital_id = "+info.getHostipalID()+" and a.cp_id='"+info.getCpID()+"' and a.cp_node_id='"+cpNodeID+"')\r\n";
	    
	    String synSql = synSql2 + synSql3;
	    
	    //System.out.println(synSql);
	    
	    DatabaseClass dbc = LcpUtil.getDatabaseClass();
	    try {
			result = dbc.FunRunSqlByFile(synSql.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return result;
	}
	/**
	 * 签名功能Point
	 * @param info
	 * @param map String CP_NODE_ORDER_ID,String EXE_STATE
	 * @param varTime 对应签名字段的时间
	 * @param laseUpdateTime 对应最后更新时间
	 * @return
	 */
	public int signTable(RouteExecuteInfo info,HashMap<String,String> map,String varTime,String laseUpdateTime){
		String sql="";
		Set<String> set=map.keySet();
		Iterator<String>iterator=set.iterator();
		while(iterator.hasNext()){
			String id=(String)iterator.next();
			String exeFlag=map.get(id);
			sql=sql+"UPDATE LCP_PATIENT_ORDER_POINT SET" +
						" USER_ID='"+info.getVerifyCode()+"' ," +
						" USER_NAME='"+info.getVerifyName()+"' ," +
						" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
						" EXE_STATE="+exeFlag+" ," +
						" EXE_DATE="+varTime+" WHERE " +
						" HOSPITAL_ID="+info.getHostipalID()+" " +
						" AND PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND CP_ID="+info.getCpID()+"" +
						" AND CP_NODE_ID="+info.getCpNodeID()+"" +
						" AND CP_NODE_ORDER_ID = "+id+"\r\n";
		}
		int a=0;
		if(!sql.isEmpty()){
			try {
				DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
				a=databaseClass.FunRunSqlByFile(sql.getBytes("GB2312"));
			} catch (Exception e) {
			}
		}
		return a;
	}
	/**
	 * 签名功能Item
	 * @param info
	 * @param map String CP_NODE_ORDER_ID,String EXE_STATE
	 * @param varTime 对应签名字段的时间
	 * @param laseUpdateTime 对应最后更新时间
	 * @return
	 */
	public int signTableItem(RouteExecuteInfo info,HashMap<String,String> map,String varTime,String laseUpdateTime){
		String sql="";
		Set<String> set=map.keySet();
		Iterator<String>iterator=set.iterator();
		while(iterator.hasNext()){
			String id=(String)iterator.next();
			String[] ids=id.split("_p_i_");
			String exeFlag=map.get(id);
			sql=sql+"UPDATE LCP_PATIENT_ORDER_ITEM SET" +
						" USER_ID='"+info.getVerifyCode()+"' ," +
						" USER_NAME='"+info.getVerifyName()+"' ," +
						" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
						" STATE_ITEM="+exeFlag+" ," +
						" EXE_STATE="+exeFlag+" ," +
						" EXE_DATE="+varTime+" WHERE " +
						" HOSPITAL_ID="+info.getHostipalID()+" " +
						" AND PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND CP_ID="+info.getCpID()+"" +
						" AND CP_NODE_ID="+info.getCpNodeID()+"" +
						" AND CP_NODE_ORDER_ID = "+ids[0]+"" +
						" AND CP_NODE_ORDER_ITEM_ID = "+ids[1]+"" +
						"\r\n";
			sql=sql+"UPDATE LCP_PATIENT_ORDER_POINT SET" +
					" USER_ID='"+info.getVerifyCode()+"' ," +
					" USER_NAME='"+info.getVerifyName()+"' ," +
					" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
					" EXE_STATE="+exeFlag+" ," +
					" EXE_DATE="+varTime+" WHERE " +
					" HOSPITAL_ID="+info.getHostipalID()+" " +
					" AND PATIENT_NO='"+info.getPatientNo()+"'" +
					" AND CP_ID="+info.getCpID()+"" +
					" AND CP_NODE_ID="+info.getCpNodeID()+"" +
					" AND CP_NODE_ORDER_ID = "+ids[0]+"" +
					"\r\n";
		}
		int a=0;
		if(!sql.isEmpty()){
			try {
				DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
				a=databaseClass.FunRunSqlByFile(sql.getBytes("GB2312"));
			} catch (Exception e) {
			}
		}
		return a;
	}
	/**
	 * 查看此节点是否已经签名
	 * @param info
	 * @return true 已经签名，false 未全部签名
	 */
	public boolean checkSign(RouteExecuteInfo info){
		String sql="SELECT T.PATIENT_NO FROM LCP_PATIENT_ORDER_POINT T " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+info.getCpID()+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+info.getMaxCpNodeID();
		sql=sql+" UNION ALL SELECT T.PATIENT_NO  FROM LCP_PATIENT_ORDER_ITEM T " +
				"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+info.getCpID()+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+info.getMaxCpNodeID();
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int a=dataSet.getRowNum();//一共多少行
		if(a>0){
			return false;
		}
		return true;
	}
	
	public String funGetItem(RouteExecuteInfo info){
		String tableSql="SELECT *  FROM LCP_PATIENT_ORDER_ITEM T  " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
		" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+info.getCpNodeID()+" ORDER BY CP_NODE_ORDER_ID";
		String tableHTML="";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(tableSql);
		int row=dataSet.getRowNum();
		if(info.isExecute()){
			for(int i=0;i<row;i++){	
				String isChecked="";
				String bgColor="#FFFFFF";
				String CheckAble="";
				boolean isExe=dataSet.funGetFieldByCol(i,"EXE_STATE").equals("1")?true:false;
				String order_kind=dataSet.funGetFieldByCol(i,"ORDER_KIND");
				if(order_kind.equals("0")){
					order_kind="临时";
				}
				if(order_kind.equals("1")){
					order_kind="长期";
				}
				if(order_kind.equals("2")){
					order_kind="出院";
				}
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				String state_item=dataSet.funGetFieldByCol(i, "STATE_ITEM");
				String state_item_context="";
				if(state_item.equals("0")){
					state_item_context="未下达";
				}
				if(state_item.equals("1")){
					state_item_context="已下达";
				}
				if(state_item.equals("2")){
					state_item_context="不下达";
				}
				String trName="";
				if(isSign){
					bgColor="#51b2f6";
					CheckAble="disabled='disabled'";
					trName="unChangeColorOrderItem";
				}else{
					trName="changeColorOrderItem";//没有签名
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				String order_type_name= dataSet.funGetFieldByCol(i, "ORDER_TYPE_NAME");
				if(order_type_name.equals("1")){
					order_type_name = "其他";
					}else if(order_type_name.equals("2")){
					order_type_name = "检查";
					}else if(order_type_name.equals("0")){
					order_type_name = "检验";
					}

				CheckAble="disabled='disabled'";
				String cp_node_table_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID");
				String cp_node_table_item_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ITEM_ID");
				String ids=cp_node_table_id+"_p_i_"+cp_node_table_item_id;
				tableHTML=tableHTML+"<tr name='"+trName+"' jibie='xc' bianse='order_"+cp_node_table_id+"' id='tr"+ids+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td width='4%'><div align='center'>" +
				"<input type='checkbox' name='checkboxOrderItem' id='checkboxOrderItem"+ids+"'  onclick='changeColorByCheckbox(&quot;checkboxOrderItem"+ids+"&quot;)' "+isChecked+CheckAble+"/>" +
				"</div></td>" +
				"<td width='60%'><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT")+"</div></td>" +
				"<td  width='14%'><div align='center'>"+order_kind+"</div></td>" +
				"<td width='*'><div align='center'>"+order_type_name+"</div></td>" +
				//"<td width='*'><div align='center'>"+state_item_context+"</div></td>" +
				"</tr>";			
			}
		}else{
			for(int i=0;i<row;i++){	
				String isChecked="";
				String bgColor="#FFFFFF";
				String CheckAble="disabled='disabled'";
				boolean isExe=dataSet.funGetFieldByCol(i,"EXE_STATE").equals("1")?true:false;
				String isMust=dataSet.funGetFieldByCol(i,"NEED_ITEM").equals("1")?"必做项":"可选项";
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				String isContinue=dataSet.funGetFieldByCol(i, "CONTINUE_ITEM").equals("0")?"不延续":"延续";
				if(isSign){
					bgColor="#51b2f6";
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				
				tableHTML=tableHTML+"<tr name='doctorTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td width='4%'><div align='center'>"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"</div></td>" +
				"<td width='4%'><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT")+"</div></td>" +
				"<td width='4%'><div align='center'>"+isMust+"</div></td>" +
				"<td width='4%'><div align='center'>"+isContinue+"</div></td>" +
				"<td width='4%'><div align='center'><input type='checkbox' name='checkboxOrdeItem' id='checkbox"+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"'  onclick='changeColorByCheckbox(this)' "+isChecked+CheckAble+"/></div></td>" +
				"<td width='4%'><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td width='4%'><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
			}
			
		}
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}

}
