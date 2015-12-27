package com.goodwillcis.lcp.servlet.pdca;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

/**
 * 获取路径下医嘱执行情况
 * @author wuhailong
 * 2015-12-26
 */
public class CpOrdersExcuteStatus {

	public CpOrdersExcuteStatus() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 获取临床路径下的医嘱执行次数
	 * 2015-12-11
	 * 吴海龙 
	 * @throws ServletException 
	 */
	public String GetOrderSeqsByCPId(String p_strCpId) throws ServletException{
		String _strCpId = p_strCpId;
		String _strSQL = "select tom.*,\r\n" + 
				"(case\r\n" + 
				"when round((case tom.sumcount\r\n" + 
				"when 0 then\r\n" + 
				"0\r\n" + 
				"else\r\n" + 
				"mycount * 100 / sumcount\r\n" + 
				"end),\r\n" + 
				"2) > 100 then\r\n" + 
				"100\r\n" + 
				"else\r\n" + 
				"round((case tom.sumcount\r\n" + 
				"when 0 then\r\n" + 
				"0\r\n" + 
				"else\r\n" + 
				"mycount * 100 / sumcount\r\n" + 
				"end),\r\n" + 
				"2)\r\n" + 
				"end) lv\r\n" + 
				"from (select a.hospital_id,\r\n" + 
				"a.cp_id,\r\n" + 
				"b.cp_node_id,\r\n" + 
				"b.cp_node_name node_name,\r\n" + 
				"c.cp_node_order_id order_group_id,\r\n" + 
				"c.CP_NODE_ORDER_TEXT order_group,\r\n" + 
				"d.cp_node_order_item_id,\r\n" + 
				"d.ORDER_ITEM_SET_ID sub_group_id,\r\n" + 
				"d.cp_node_order_text order_text,\r\n" + 
				"d.order_no,\r\n" + 
				"(select count(*)\r\n" + 
				"from LCP_PATIENT_LOG_ORDER\r\n" + 
				"where cp_id = '"+_strCpId+"'\r\n" + 
				"and cp_node_id = d.cp_node_id\r\n" + 
				"and cp_node_order_id = d.cp_node_order_id\r\n" + 
				"and cp_node_order_item_id = d.cp_node_order_item_id) mycount,\r\n" + 
				"(select count(*)\r\n" + 
				"from (select patient_no\r\n" + 
				"from LCP_PATIENT_LOG_ORDER\r\n" + 
				"where cp_id = '"+_strCpId+"'\r\n" + 
				"group by patient_no)) sumcount\r\n" + 
				"from lcp_master           a,\r\n" + 
				"lcp_master_node      b,\r\n" + 
				"LCP_NODE_ORDER_POINT c,\r\n" + 
				"LCP_NODE_ORDER_ITEM  d        \r\n" + 
				"where a.hospital_id = b.hospital_id\r\n" + 
				"and a.cp_id = b.cp_id\r\n" + 
				"and b.hospital_id = c.hospital_id\r\n" + 
				"and b.cp_id = c.cp_id\r\n" + 
				"and b.cp_node_id = c.cp_node_id\r\n" + 
				"and c.hospital_id = d.hospital_id\r\n" + 
				"and c.cp_id = d.cp_id\r\n" + 
				"and c.cp_node_id = d.cp_node_id\r\n" + 
				"and c.cp_node_order_id = d.cp_node_order_id\r\n" + 
				"and a.cp_id = '"+_strCpId+"'\r\n" + 
				"order by cp_node_id asc, mycount desc, order_group_id, sub_group_id) tom";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			String _strJson = "{\"cp_orders\":[";
			while (_rsData.next()) {
				int _nHosId = _rsData.getInt("hospital_id");
				int _nCpId = _rsData.getInt("cp_id");
				int _nNodeId = _rsData.getInt("cp_node_id");
				String _strCpName = _rsData.getString("node_name");
				String _strOrderGroup = _rsData.getString("order_group");
				String _strOrderGroupId = _rsData.getString("order_group_id");//cp_node_order_item_id
				int _nOrderItemId = _rsData.getInt("cp_node_order_item_id");
				int _strSubGroupId = _rsData.getInt("sub_group_id");
				String _strOrderText = _rsData.getString("order_text");
				String _nOrdrNo = _rsData.getString("order_no");
				int _nOrderCount = _rsData.getInt("mycount");
				int _nGroupCount =0;// _rsData.getInt("groupcount");
				int _nsumCount =_rsData.getInt("sumcount");
				double _dLv = _rsData.getDouble("lv");
				_strJson += "{\"hospital_id\":"+_nHosId+"," +
						"\"cp_id\":"+_nCpId+"," +
						"\"node_id\":"+_nNodeId+"," +
						"\"node_name\":\"" + _strCpName + 
						"\",\"order_group\":\""+ _strOrderGroup + 
						"\",\"order_group_id\":"+ _strOrderGroupId+ 
						",\"order_item_id\":"+ _nOrderItemId+ 
						",\"sub_group_id\":"+ _strSubGroupId + 
						",\"order_text\":\""+ _strOrderText + 
						"\",\"order_no\":\"" + _nOrdrNo+ 
						"\",\"mycount\":" + _nOrderCount + 
						",\"sumcount\":" + _nsumCount + 
						",\"lv\":" + _dLv + 
						"},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			if("{\"cp_orders\":]}"==_strJson){}
			System.out.println(_strJson);
			return _strJson;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
