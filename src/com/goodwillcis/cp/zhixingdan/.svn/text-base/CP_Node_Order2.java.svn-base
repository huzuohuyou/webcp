/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：MyDataSetParam.java
// 文件功能描述：皕杰报表自定义数据集示例代码
// 创建人：潘状
// 创建日期：2011/09/03
// 
//----------------------------------------------------------------*/

package com.goodwillcis.cp.zhixingdan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;


public class CP_Node_Order2 implements CustomDataSet {

    private String[] metaData;

    private List<Object[]> data;

    // 参数列表,必须与设计器端的参数数量匹配
    private String pairentId;

  
   
    @Override
	public String[] getMetaData() {

	return metaData;
    }
   
   
    @Override
	public int getRowCount() {
	return data.size();
    }

    
   
    @Override
	public Object[] getRowData(int rowIndex) {
	return data.get(rowIndex);
    }
    
    @Override
	public void applyParams(Object[] params) {
        pairentId = (String) params[0];
    	metaData = new String[] { "CP_NODE_ID","CP_NODE_ORDER_TEXT","ORDER_KIND" };
    	data = new ArrayList<Object[]>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		DBConnection dbread =SingleClass.GetInstance();
		conn=dbread.getConnection();
	    System.out.println("conection is ok");
	    try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call ZXD.CP_NODE_ORDER2(?,?)}");
			stmt.registerOutParameter(1, OracleTypes.VARCHAR);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.setString(1,pairentId);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(2);
			String order_kind="";
			while(rs.next()){
	    	    int cp_node_id = rs.getInt(1);
	    	    //int cp_node_order_id = rs.getInt(2);
	    	    String leixing = rs.getString(3);
	    	    String cp_node_order_text = rs.getString(4);
	    	    String exe_state = rs.getString(5);
	    	    exe_state=(exe_state==null?"0":exe_state);
	    	    if(exe_state.equals("1")){
	    	    	cp_node_order_text = "■"+cp_node_order_text;
	    	    }else{
	    	    	cp_node_order_text = "□"+cp_node_order_text;
	    	    }
	    	    
	    	    if("0".equals(leixing)){
	    	    	order_kind = "临时医嘱";
	    	    }
	    	    data.add(new Object[] { cp_node_id,cp_node_order_text,order_kind});
			}
		} catch (SQLException e) {
			System.out.println("存储过程出错!");
			e.printStackTrace();
		}finally{
			  if(stmt!=null){
				  try {
					  stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt=null;
			  }
			  if(rs!=null){
				  try {
					  rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs=null;
			  }
			  if(statement!=null){
				  try {
					  statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement=null;
			  }
			  if(conn!=null){
				  try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				  conn=null;
			  }
		  }
		
//    	String sFuns = "";
//        String con_id="";
//        String sqlfuns="";
//        String sql ="";
 //   	DatabaseClass db = LcpUtil.getDatabaseClass();
    	//查询con_id是否存在
//    	String con_idsql = "select t.continue_order_id from lcp_patient_order_point t where t.cp_id in (select distinct(cp_id) from lcp_patient_node where patient_no = '"+pairentId+"' )";
//    	con_id=db.FunGetDataSetBySQL(con_idsql).FunGetDataAsStringById(0, 0);
//      	if(con_id==""){//con_id不存在
 //   	   sqlfuns = "select t.cp_node_id,t.cp_node_order_id from lcp_patient_order_point t where t.patient_no = '"+pairentId+"' and t.exe_state = 1" ;
 //     	}else{//存在
 //    	   sql = "select t.cp_node_id, t.cp_node_order_id,t.ORDER_KIND,t.CP_NODE_ORDER_TEXT,t.exe_state from lcp_patient_order_point t where t.patient_no = '"+pairentId+"' and t.cp_node_order_id != t.continue_order_id order by t.continue_order_id" ;
 //       }
//     	DataSetClass funs = db.FunGetDataSetBySQL(sqlfuns);
//    	int cfuns = funs.FunGetRowCount();
//    	for(int i = 0;i<cfuns;i++){
//    		int cp_node_id = funs.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
//    	    int cp_node_order_id = funs.FunGetDataAsNumberByColName(i, "CP_NODE_ORDER_ID").intValue();
//    	    sFuns += cp_node_id+","+cp_node_order_id+"||";
//    	}
//    	//如果con_id存在
//    	if(con_id==""){
//    	    sql = "select t.cp_node_id,t.cp_node_order_id,t.cp_node_order_text,t.order_kind from lcp_node_order_point t where t.cp_id = (select distinct t.cp_id from lcp_patient_node t where t.patient_no = '"+pairentId+"') order by t.cp_node_id,t.order_kind desc,t.cp_node_class_id,t.cp_node_order_id" ;
//    	}else{//不存在
//    	    sql = "select t.cp_node_id, t.cp_node_order_id, t.cp_node_order_text, t.order_kind from lcp_node_order_point t where t.cp_id = (select distinct t.cp_id from lcp_patient_node t where t.patient_no = '"+pairentId+"') and t.cp_node_order_id != t.continue_order_id order by t.cp_node_id, t.order_kind desc,t.cp_node_class_id, t.cp_node_order_id"; 
//    	}
//    	DataSetClass sd = db.FunGetDataSetBySQL(sql);
//    	int count = sd.FunGetRowCount();
//    	String order_kind = "";
//    	for (int i = 0; i < count; i++) {
//    	    int cp_node_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ID").intValue();
//    	    int cp_node_order_id = sd.FunGetDataAsNumberByColName(i, "CP_NODE_ORDER_ID").intValue();
//    	    String leixing = sd.FunGetDataAsStringByColName(i, "ORDER_KIND");
//    	    String cp_node_order_text = sd.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
//    	    String exe_state = sd.FunGetDataAsStringByColName(i, "EXE_STATE");
//    	    //System.out.println(leixing);
//    	    //System.out.println(cp_node_order_text);
//    	    if(exe_state.equals("1")){
//    	    	cp_node_order_text = "■"+cp_node_order_text;
//    	    }else{
//    	    	cp_node_order_text = "□"+cp_node_order_text;
//    	    }
//    	    
//    	    if("0".equals(leixing)){
//    	    	order_kind = "临时医嘱";
//    	    }else if("1".equals(leixing)){
//    	    	order_kind = "长期医嘱";
//    	    }else{
//    	    	order_kind = "出院医嘱";
//    	    }
//    	    
//    	    data.add(new Object[] { cp_node_id,cp_node_order_text,order_kind});
//    	}
//	   	 for(int j=0;j<data.size();j++){
//	 		//System.out.println(data.get(j)[1]);
//	 	 }
        }

}
