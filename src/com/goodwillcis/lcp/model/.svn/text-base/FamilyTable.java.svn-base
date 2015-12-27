/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：FamilyTable.java    
// 文件功能描述：FamilyTable(家属工作)相关操作
//
// 创建人：潘状
// 创建日期：2011-07-29
// 完成日期:2011-7-29
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.model;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class FamilyTable
{
	/**
	 * 取得LCP_PATIENT_FAMILY_POINT 表格内容
	 * @return 返回table表格中的行
	 */
	public String createTable(RouteExecuteInfo info){
		String tableSql="SELECT *  FROM LCP_PATIENT_FAMILY_POINT T  " +
				"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0  AND T.CP_ID="+info.getCpID()+"  AND CP_NODE_ID="+info.getCpNodeID()+" ORDER BY CP_NODE_FAMILY_ID";
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
				String trName="";
				if(isSign){
					bgColor="#51b2f6";
					CheckAble="disabled='disabled'";
					trName="unChangeColorFamily";
				}else{
					trName="changeColorFamily";//没有签名
				}
				if(isExe){
					isChecked="checked='checked'"; 
				}
				if(isAuto){
					CheckAble="disabled='disabled'";
					trName="unChangeColorFamily";
				}
			
				String checkID="checkboxFamily"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_ID");
				tableHTML=tableHTML+"<tr name='"+trName+"' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td width='38%'><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_TEXT")+"</div></td>" +
				"<td width='5%'><div align='center'><input type='checkbox' name='checkboxFamily' id='"+checkID+"'  onclick='changeColorByCheckbox(&quot;"+checkID+"&quot;)' "+isChecked+CheckAble+"/></div></td>" +
				"<td width='8%'><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td width='21%'><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
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

				tableHTML=tableHTML+"<tr name='familyTR' id='tr"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_ID")+"'   height='20' bgcolor='"+bgColor+"' class='STYLE19' onmouseover='changeColor(this)'   onmouseout='recoverColor(this)'>" +
				"<td><div align='left'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_TEXT")+"</div></td>" +
				"<td><div align='center'><input type='checkbox' name='familyCheckbox' id='checkbox"+dataSet.funGetFieldByCol(i, "CP_NODE_FAMILY_ID")+"'  onclick='changeColorByCheckbox(this)' "+isChecked+CheckAble+"/></div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "USER_NAME")+"</div></td>" +
				"<td><div align='center'>"+dataSet.funGetFieldByCol(i, "EXE_DATE")+"</div></td>" +
				"</tr>";
			}
			
		}
		tableHTML=CommonUtil.replactCharacter(tableHTML);
		return tableHTML;
	}
	
	/**
	 * 从LCP_NODE_FAMILY_POINT表中查出输入插入到LCP_PATIENT_FAMILY_POINT中
	 * @param info
	 * @param cpNodeID
	 * @return 负数：错误，整数：插入的行数
	 */
	public int InsertFamilyTable(RouteExecuteInfo info,int cpNodeID){
		String sql="SELECT T.HOSPITAL_ID,T.CP_ID,T.CP_NODE_ID,T.CP_NODE_FAMILY_ID," +
				"T.CP_NODE_FAMILY_TEXT,T.NEED_ITEM,T.AUTO_ITEM" +
				" FROM LCP_NODE_FAMILY_POINT T " +
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
					"INSERT INTO LCP_PATIENT_FAMILY_POINT("+names+",PATIENT_NO," +
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
		return aa;
	}
	/**
	 * 签名功能
	 * @param info
	 * @param map String CP_NODE_FAMILY_ID,String EXE_STATE
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
			sql=sql+"UPDATE LCP_PATIENT_FAMILY_POINT SET" +
						" USER_ID='"+info.getVerifyCode()+"' ," +
						" USER_NAME='"+info.getVerifyName()+"' ," +
						" SYS_LAST_UPDATE="+laseUpdateTime+" ," +
						" EXE_STATE="+exeFlag+" ," +
						" EXE_DATE="+varTime+" WHERE " +
						" HOSPITAL_ID="+info.getHostipalID()+" " +
						" AND PATIENT_NO='"+info.getPatientNo()+"'" +
						" AND CP_ID="+info.getCpID()+"" +
						" AND CP_NODE_ID="+info.getCpNodeID()+"" +
						" AND CP_NODE_FAMILY_ID = "+id+"\r\n";
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
		String sql="SELECT T.*  FROM LCP_PATIENT_FAMILY_POINT T " +
		"WHERE T.PATIENT_NO = '"+info.getPatientNo()+"'   AND T.HOSPITAL_ID = "+info.getHostipalID()+"" +
				" AND T.SYS_IS_DEL=0 AND T.CP_ID="+info.getCpID()+" AND T.USER_NAME IS NULL AND CP_NODE_ID="+info.getCpNodeID()+"";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int a=dataSet.getRowNum();//一共多少行
		if(a>0){
			return false;
		}
		return true;
	}
}
