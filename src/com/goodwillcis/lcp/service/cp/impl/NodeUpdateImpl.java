package com.goodwillcis.lcp.service.cp.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.service.cp.NodeUpdate;
import com.goodwillcis.lcp.util.LcpUtil;

public class NodeUpdateImpl implements NodeUpdate {

	@Override
	public String getSchemaData(String cpMasterId, String cpNodeId, int hospitalNo, String doctorNo,String flag){
		// TODO Auto-generated method stub
		String schemaSql = "select * from lcp_patient_order_schema where hospital_id = "+hospitalNo+"  and cp_master_id = "+cpMasterId+" and cp_node_id ="+cpNodeId+" ";
		String belong = "";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(flag.equals("0") ){
			schemaSql = schemaSql + "and doctor_no = "+doctorNo+" order by  order_type_name desc,sys_last_update asc";
			belong = "personalSchema";
		}else if(flag.equals("1")){
			schemaSql = schemaSql + "and doctor_no <> "+doctorNo+" order by order_type_name desc,sys_last_update asc";
			belong = "othersSchema";
		}
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
		DataSetClass dsc = dbc.FunGetDataSetBySQL(schemaSql);
		int row = dsc.FunGetRowCount();
		//组串
		String table="";
		if(row>0){
			for(int i=0;i<row; i++){
				String schema_id = dsc.FunGetDataAsStringByColName(i, "SCHEMA_ID");
				String comments = dsc.FunGetDataAsStringByColName(i, "COMMENTS");
				String content = dsc.FunGetDataAsStringByColName(i, "SCHEMA_DATA");
				String typeName = dsc.FunGetDataAsStringByColName(i, "ORDER_TYPE_NAME");
				String doctorName = dsc.FunGetDataAsStringByColName(i, "DOCTOR_NAME");
				String sysLastUpdate = formatter.format(dsc.FunGetDataAsDateByColName(i, "SYS_LAST_UPDATE"));
				table=table+"<tr id='"+schema_id+"' height='20' bgcolor='#FFFFFF'  onmouseout='recoverColor(this);' onmouseover='changeColor(this);' style='cursor:pointer'; " +
						"ondblclick='ondblclickLoad(this)' onclick='NodeColor1(this)'>"+
				"<td id='"+schema_id+"' belong='"+belong+"' hospitalNo='"+hospitalNo+"' doctorNo='"+doctorNo+"' cpMasterId='"+cpMasterId+"' " +
						"cpNodeId='"+cpNodeId+"' align='center' class='STYLE0'>"+(i+1)+"</td>" +
				"<td align='center' class='STYLE10' comments='"+comments+"'>"+comments+"" +
						"<input type='hidden' name= 'content' id='data'  value='"+content+"'></td>" +
				"<td align='center' class='STYLE10'>"+typeName+"</td>" +
				"<td align='center' class='STYLE10'>"+doctorName+"</td>" +
				"<td align='center' class='STYLE10'>"+sysLastUpdate+"</td>" +
				"<td align='center'></td>" +
				"</tr>";
			}
			return table;
		}
		return null;  
	}

}
