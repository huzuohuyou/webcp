/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：NurseTable.java    
// 文件功能描述：NurseTable(护理工作)相关操作相关操作
//
// 创建人：刘植鑫
// 创建日期：2011-06-01
// 修改人:潘状
// 修改原因:需求更改,数据库表结构改变
// 修改日期:2011-7-28
// 完成日期:2011-7-28
//
// 修改人:韩金杰
// 修改原因:增加需求，签名可取消
// 修改日期:2013-03-01
// 完成日期:2013-03-01
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;
public class NurseTable {

	/**
	 * 取得LCP_PATIENT_NURSE 表格内容
	 * 2011-06-01
	 * @return 返回table表格中的行
	 */
	/**
	 * 程序修改
	 */
	/**
	 * 程序修改
	 * 修改内容：让已签名选项可选，以增加取消功能。
	 * 修改人：韩金杰
	 * 修改日期：2013-03-01
	 */
	public String createTable(RouteExecuteInfo info){
		String tableSql="SELECT * FROM LCP_PATIENT_NURSE_POINT T " +
				"WHERE T.HOSPITAL_ID = "+info.getHostipalID()+" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND T.CP_NODE_ID="+info.getCpNodeID()+" AND T.CP_ID="+info.getCpID()+" AND SYS_IS_DEL=0 ORDER BY CP_NODE_NURSE_ID";
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
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				boolean isAuto=dataSet.funGetFieldByCol(i,"AUTO_ITEM").equals("0")?true:false;
				boolean is_execute=dataSet.funGetFieldByCol(i,"IS_EXECUTE").equals("1")?true:false;
				String trName="";
				if(isSign){
					bgColor="#51b2f6";
					CheckAble="disabled='disabled'";
					trName="unChangeColorNurse";
				}else{
					trName="changeColorNurse";//没有签名
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				String title="";
				if(isAuto){
					CheckAble="disabled='disabled'";
					title="title='该项为自动项目必须通过电子病历下护理文档'";
					trName="unChangeColorNurse";
				}
//				if(is_execute){
//					trName="unChangeColorDoctor";
//				}
				String checkID="checkboxNurse"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_ID");
				tableHTML=tableHTML+"<tr name='"+trName+"' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_ID")+"' height='20' bgcolor='"+bgColor+"'  "+title+"  class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_TEXT")+"</div></td>" +
				"<td><div align='center'><input type='checkbox' name='checkboxNurse' id='"+checkID+"'  onclick='changeColorByCheckbox(&quot;"+checkID+"&quot;)' "+isChecked+"/></div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
			}
		}else{
			for(int i=0;i<row;i++){	
				String isChecked="";
				String bgColor="#FFFFFF";
				String CheckAble="disabled='disabled'";
				boolean isExe=dataSet.funGetFieldByCol(i,"EXE_STATE").equals("1")?true:false;
				boolean isSign=dataSet.funGetFieldByCol(i,"USER_NAME").length()>0?true:false;
				if(isSign){
					bgColor="#51b2f6";
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				tableHTML=tableHTML+"<tr name='doctorTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_TEXT")+"</div></td>" +
				"<td><div align='center'><input type='checkbox' name='nurseCheckbox' id='checkbox"+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_ID")+"'  onclick='changeColorByCheckbox(this)' "+isChecked+"/></div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
			}
			
		}
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}
	/**
	 * 签名功能
	 * @param info
	 * @param map String CP_NODE_NURSE_ID,String EXE_STATE
	 * @param varTime 对应签名字段的时间
	 * @param laseUpdateTime 对应最后更新时间
	 * @return
	 * 
	 * 
	 */
	/*
	 * 程序错误修改
	 * 原来没有同步更新item表的签名，现已修改
	 * 修改人：刘植鑫 2011-08-12
	 * 
	 * 
	 */
	public int signTable(RouteExecuteInfo info,HashMap<String,String> map,String varTime,String laseUpdateTime){
		String sql="";
		Set<String> set=map.keySet();
		Iterator<String>iterator=set.iterator();
		while(iterator.hasNext()){
			String id=(String)iterator.next();
			String exeFlag=map.get(id);
			sql=sql+"UPDATE LCP_PATIENT_NURSE_POINT SET USER_ID='"+info.getVerifyCode()+"' ," +
						" USER_NAME='"+info.getVerifyName()+"' ," +
						" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
						" EXE_STATE="+exeFlag+" ," +
						" EXE_DATE="+varTime+" WHERE " +
						" HOSPITAL_ID="+info.getHostipalID()+" " +
						" AND PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND CP_ID="+info.getCpID()+"" +
						" AND CP_NODE_ID="+info.getCpNodeID()+"" +
						" AND CP_NODE_NURSE_ID = "+id+"\r\n";
			sql=sql+"UPDATE LCP_PATIENT_NURSE_ITEM SET USER_ID='"+info.getVerifyCode()+"' ," +
				" USER_NAME='"+info.getVerifyName()+"' ," +
				" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
				" EXE_STATE="+exeFlag+" ," +
				" EXE_DATE="+varTime+" WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND CP_NODE_NURSE_ID = "+id+"\r\n";
		}
//		System.out.println("sql==="+sql);
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
	 * 
	 * 取消签名功能
	 * @param info
	 * @param ids  CP_NODE_NURSE_ID
	 * @param varTime  对应签名字段的时间
	 * @param laseUpdateTime  对应最后更新时间
	 * @return
	 * 创建人：韩金杰
	 * 创建日期：2013-03-01
	 * 
	 */
	public int cancelSignTable(RouteExecuteInfo info,String[] ids,int idslength,String varTime,String laseUpdateTime){
		String sql="";
		for(int i=1;i<idslength;i++){
			String id=(String)ids[i];
			String exeFlag="0";
			sql=sql+"UPDATE LCP_PATIENT_NURSE_POINT SET USER_ID=null ," +
						" USER_NAME=null ," +
						" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
						" EXE_STATE="+exeFlag+" ," +
						" EXE_DATE=null WHERE " +
						" HOSPITAL_ID="+info.getHostipalID()+" " +
						" AND PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND CP_ID="+info.getCpID()+"" +
						" AND CP_NODE_ID="+info.getCpNodeID()+"" +
						" AND CP_NODE_NURSE_ID = "+id+"\r\n";
			sql=sql+"UPDATE LCP_PATIENT_NURSE_ITEM SET USER_ID=null ," +
				" USER_NAME=null ," +
				" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
				" EXE_STATE="+exeFlag+" ," +
				" EXE_DATE=null WHERE " +
				" HOSPITAL_ID="+info.getHostipalID()+" " +
				" AND PATIENT_NO='"+info.getPatientNo()+"'" +
				" AND CP_ID="+info.getCpID()+"" +
				" AND CP_NODE_ID="+info.getCpNodeID()+"" +
				" AND CP_NODE_NURSE_ID = "+id+"\r\n";
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
	 * 从LCP_NODE_NURSE表中查出输入插入到LCP_PATIENT_NURSE中
	 * @param info
	 * @param cpNodeID
	 * @return 负数：错误，整数：插入的行数
	 */
	/*
	 * 程序错误修改
	 * 向item表中插入数据，原来没有此代码，先已修改
	 * 修改人：刘植鑫 2011-08-12
	 * 
	 */
	public int InsertNurseTable(RouteExecuteInfo info,int cpNodeID){
		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_NURSE_ID," +
				"T.CP_NODE_NURSE_TEXT,T.NEED_ITEM,T.AUTO_ITEM,IS_EXECUTE" +
				" FROM LCP_NODE_NURSE_POINT T " +
			"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		String insertSql="";
		String time=CommonUtil.getOracleToDate();
		for(int i=0;i<row;i++){
			String names=dataSet.funGetFieldInsertSQL();
			String values=dataSet.funGetFieldValueInsertSQL(i);
			insertSql=insertSql+"" +
					"INSERT INTO LCP_PATIENT_NURSE_POINT("+names+",PATIENT_NO," +
					"EXE_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',0,0,"+time+")\r\n";
		}
		String sqlItem="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_NURSE_ID,T.CP_NODE_NURSE_ITEM_ID,T.NURSE_NO," +
				"T.CP_NODE_NURSE_TEXT,T.NEED_ITEM,T.AUTO_ITEM" +
				" FROM LCP_NODE_NURSE_ITEM T " +
			"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
		dataSet.funSetDataSetBySql(sqlItem);
		int row_item=dataSet.getRowNum();
		for(int i=0;i<row_item;i++){
			String names=dataSet.funGetFieldInsertSQL();
			String values=dataSet.funGetFieldValueInsertSQL(i);
			insertSql=insertSql+"" +
					"INSERT INTO LCP_PATIENT_NURSE_ITEM("+names+",PATIENT_NO," +
					"EXE_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',0,0,"+time+")\r\n";
		}
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		int aa=0;
		if(row>0){
			try {
				aa = databaseClass.FunRunSqlByFile(insertSql.getBytes("GB2312"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();aa=-1;
				TableException.rollBackAll(info, cpNodeID);
			}
		}
		
		
		///以下为新加功能，如果log表中已经下达了日志了，那么执行下一节点有匹配项，那么就签名
		///修改人：刘植鑫
		///修改时间：2011-10-24
		DataSet log=new DataSet();
		String log_sql="select * from lcp_patient_log_nurse t where t.patient_no='"+info.getPatientNo()+"' and t.nurse_no is not null and t.cp_id=0 order by sys_last_update";
		log.funSetDataSetBySql(log_sql);
		int row_log=log.getRowNum();
		if(row_log>0){
			ArrayList<NextNodeLog> logs=new ArrayList<NextNodeLog>();
			for(int i=0;i<row_log;i++){
				String code=log.funGetFieldByCol(i, "nurse_NO");
				String local_key=log.funGetFieldByCol(i, "LOCAL_KEY");
				NextNodeLog log2=new NextNodeLog();
				log2.setCode(code);
				log2.setDuizhao(false);
				log2.setLocal_key(local_key);
				logs.add(log2);
			}
			DataSet dataSet2=new DataSet();
			String sql_patient="select * from lcp_patient_nurse_item t where t.patient_no='"+info.getPatientNo()+"' and t.hospital_id="+info.getHostipalID()+"";
			dataSet2.funSetDataSetBySql(sql_patient);
			int row_patient=dataSet2.getRowNum();
			String next_node_execute_sql="";
			for(int i=0;i<row_patient;i++){
				
				String cp_node_table_id=dataSet2.funGetFieldByCol(i, "CP_NODE_nurse_ID");
				String cp_node_table_item_id=dataSet2.funGetFieldByCol(i, "CP_NODE_nurse_ITEM_ID");
				String code=dataSet2.funGetFieldByCol(i, "nurse_NO");
				for(int j=0;j<logs.size();j++){
					boolean duizhao=logs.get(j).isDuizhao();
					if(!duizhao){
						String code_1=logs.get(j).getCode();
						if(code.equals(code_1)){
							next_node_execute_sql=next_node_execute_sql+"update lcp_patient_nurse_point set user_id='"+info.getVerifyCode()+"',user_name='"+info.getVerifyName()+"' , " +
									"exe_date="+time+",EXE_state=1 where auto_item=0 and hospital_id="+info.getHostipalID()+" and patient_no='"+info.getPatientNo()+"' and cp_id="+info.getCpID()+" and cp_node_id="+cpNodeID+"" +
											" and cp_node_nurse_id="+cp_node_table_id+"\r\n";
							next_node_execute_sql=next_node_execute_sql+"update lcp_patient_log_nurse set CP_ID="+info.getCpID()+",sys_last_update="+time+" ,CP_NODE_ID="+cpNodeID+",CP_NODE_nurse_ID="+cp_node_table_id+",CP_NODE_nurse_ITEM_ID="+cp_node_table_item_id+"" +
									" where hospital_id="+info.getHostipalID()+" and patient_no='"+info.getPatientNo()+"' and LOCAL_KEY='"+logs.get(j).getLocal_key()+"' \r\n";
							logs.get(j).setDuizhao(true);
							break;
						}
					}				
				}
				
			}
			//System.out.println(next_node_execute_sql);
			DatabaseClass databaseClass1=LcpUtil.getDatabaseClass();
			if(next_node_execute_sql!=""){
				try {
					databaseClass1.FunRunSqlByFile((next_node_execute_sql).getBytes("GB2312"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();//aa=-1;
					//TableException.rollBackAll(info, cpNodeID);
				}
			}
		}
		
		
		return aa;
	}
	
	
	/*
	 * 程序错误修改
	 * 向item表中插入数据，原来没有此代码，先已修改
	 * 修改人：刘植鑫 2011-08-12
	 * 
	 */
	public String InsertNurseTableSQL(RouteExecuteInfo info,int cpNodeID){
		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_NURSE_ID," +
				"T.CP_NODE_NURSE_TEXT,T.NEED_ITEM,T.AUTO_ITEM,IS_EXECUTE" +
				" FROM LCP_NODE_NURSE_POINT T " +
			"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row=dataSet.getRowNum();
		String insertSql="";
		String time=CommonUtil.getOracleToDate();
		for(int i=0;i<row;i++){
			String names=dataSet.funGetFieldInsertSQL();
			String values=dataSet.funGetFieldValueInsertSQL(i);
			insertSql=insertSql+"" +
					"INSERT INTO LCP_PATIENT_NURSE_POINT("+names+",PATIENT_NO," +
					"EXE_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',0,0,"+time+")\r\n";
		}
		String sqlItem="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_NURSE_ID,T.CP_NODE_NURSE_ITEM_ID,T.NURSE_NO," +
				"T.CP_NODE_NURSE_TEXT,T.NEED_ITEM,T.AUTO_ITEM" +
				" FROM LCP_NODE_NURSE_ITEM T " +
			"WHERE T.HOSPITAL_ID="+info.getHostipalID()+" AND T.CP_ID="+info.getCpID()+" AND T.CP_NODE_ID="+cpNodeID+"";
		dataSet.funSetDataSetBySql(sqlItem);
		int row_item=dataSet.getRowNum();
		for(int i=0;i<row_item;i++){
			String names=dataSet.funGetFieldInsertSQL();
			String values=dataSet.funGetFieldValueInsertSQL(i);
			insertSql=insertSql+"" +
					"INSERT INTO LCP_PATIENT_NURSE_ITEM("+names+",PATIENT_NO," +
					"EXE_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					"VALUES("+values+",'"+info.getPatientNo()+"',0,0,"+time+")\r\n";
		}
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		int aa=0;
		if(row>0){
			try {
				aa = databaseClass.FunRunSqlByFile(insertSql.getBytes("GB2312"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();aa=-1;
				TableException.rollBackAll(info, cpNodeID);
			}
		}
		return "";
	}
	/**
	 * 查看此节点是否已经签名
	 * @param info
	 * @return true 已经签名，false 未全部签名
	 */
	public boolean checkSign(RouteExecuteInfo info){
		String sql="SELECT T.*  FROM LCP_PATIENT_NURSE_POINT T " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+info.getCpID()+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+info.getMaxCpNodeID()+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		
		int a=dataSet.getRowNum();//一共多少行
		if(a>0){
			return false;
		}
		return true;
	}
	/**
	 * 查看此节点是否已经勾选执行
	 * @param info
	 * 修改日期:2011-10-14 
	 */
	public String checkExecuteSign(RouteExecuteInfo info){
		String sql="SELECT T.CP_NODE_NURSE_TEXT  FROM LCP_PATIENT_NURSE_POINT T " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.EXE_STATE=0 AND T.IS_EXECUTE=1 AND T.CP_ID="+info.getCpID()+" AND CP_NODE_ID="+info.getMaxCpNodeID()+"";
//		System.out.println(sql);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int a=dataSet.getRowNum();//一共多少行
		if(a==0){
			return "";
		}else{
			String result="护理工作中的";
			for(int i=0;i<a;i++){
				result=result+dataSet.funGetFieldByCol(i, "CP_NODE_NURSE_TEXT")+"项、";
			}
			//result=result.substring(0,result.length()-1);
			
			return result;
		}
	}
}
