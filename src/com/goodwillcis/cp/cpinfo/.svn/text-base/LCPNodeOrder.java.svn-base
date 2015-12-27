package com.goodwillcis.cp.cpinfo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import bios.report.engine.api.CustomDataSet;

import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;

public class LCPNodeOrder implements CustomDataSet{
    private String[] metaData;

    private List<Object[]> data;

    // 参数列表,必须与设计器端的参数数量匹配
    private String cpID;

	@Override
	public String[] getMetaData() {
		// TODO Auto-generated method stub
		return metaData;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object[] getRowData(int rowIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex);
	}
	
	@Override
	public void applyParams(Object[] params) {
		cpID = (String) params[0];
		metaData = new String[] {
				 "节点名称",
				 "一级菜单",
				 "二级菜单",
				 "类型",
		         "类别",
		         "项目名称",
		         "规格",
		         "用量",
		         "频次",
		         "途径",
		         "药品一次用量",
		         "医生嘱托"
				};
		data = new ArrayList<Object[]>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		CallableStatement stmt = null;
		DBConnection dbread = SingleClass.GetInstance();
		conn = dbread.getConnection();
		try {
			statement = conn.createStatement();
			stmt = conn.prepareCall("{call GET_DATA.ORDER_INFO (?,?)}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			int cp_id=Integer.parseInt(cpID);
			stmt.setInt(1, cp_id);
			stmt.execute();
			rs=(ResultSet) stmt.getObject(2);
			while(rs.next()){
				String cp_node_order_text1=rs.getString(4);
				cp_node_order_text1=(cp_node_order_text1==null||cp_node_order_text1==""?"":cp_node_order_text1.trim());
				String cp_node_order_text2=rs.getString(5);
				cp_node_order_text2=(cp_node_order_text2==null||cp_node_order_text2==""?"":cp_node_order_text2.trim());
				String cp_node_name =rs.getString(1);
				cp_node_name=(cp_node_name==null||cp_node_name==""?"":cp_node_name.trim());
				String order_kind= rs.getString(6);
				order_kind=(order_kind==null||order_kind==""?"":order_kind.trim());
				if(order_kind.equals("0")){
					order_kind="临时";
				}else if(order_kind.equals("1")){		
					order_kind="长期";
				}else if(order_kind.equals("2")){
					order_kind="出院";
				}else if(order_kind.equals("3")){
					order_kind="临时+长期";
				}
				String order_type_name=rs.getString(7);
				order_type_name=(order_type_name==null||order_type_name==""?"":order_type_name.trim());
				String cp_node_order_text=rs.getString(8);
				cp_node_order_text=(cp_node_order_text==null||cp_node_order_text==""?"":cp_node_order_text.trim());
				String specification=rs.getString(9);
				specification=(specification==null||specification==""?"":specification.trim());
				String measure=rs.getString(10);
				measure=(measure==null||measure==""?"":measure.trim());
				String measure_units=rs.getString(11);
				measure_units=(measure_units==null||measure_units==""?"":measure_units.trim());
				String frequency=rs.getString(12);
				frequency=(frequency==null||frequency==""?"":frequency.trim());
				String way=rs.getString(13);
				way=(way==null||way==""?"":way.trim());
				String dosage=rs.getString(14);
				dosage=(dosage==null||dosage==""?"":dosage.trim());
				String dosage_units=rs.getString(15);
				dosage_units=(dosage_units==null||dosage_units==""?"":dosage_units.trim());
				String mark=rs.getString(16);
				mark=(mark==null||mark==""?"":mark.trim());
//				System.out.println("measure=:"+measure+"measure_units=:"+measure_units);
//				System.out.println("dosage=:"+dosage+"dosage_units=:"+dosage_units);
				String mea="";
				String dos="";
				if(measure != "" && measure_units != ""){
					mea=measure+measure_units;
				}
				if(dosage != "" && dosage_units != ""){
					dos=dosage+dosage_units;
				}
				
			    data.add(new Object[] {
			    		cp_node_name,
			    		cp_node_order_text1,
			    		cp_node_order_text2,
			    		order_kind,
			    		order_type_name,
			    		cp_node_order_text,
			    		specification,
			    		mea,
			    		frequency,
			    		way,
			    		dos,
			    		mark
			    });
			}
		} catch (SQLException e) {
			System.out.println("数据库连接异常！");
			e.printStackTrace();
		}
	}
}
