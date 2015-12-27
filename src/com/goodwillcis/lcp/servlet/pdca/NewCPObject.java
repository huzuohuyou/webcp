package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据基准路径生成新路径
 * @author wuhailong
 * 2015-12-24
 */
public class NewCPObject {
	
	private String m_strCpId = "";
	private String m_strOrderGroupId = "";
	private String m_strSubOrderGroupId = "";
	private String m_strOrderNo = "";
	
	/**
	 * 传参 cp_id group_id sub_group_id order_no
	 * @param p_strCpId
	 * @param p_strOrderGroupId
	 * @param p_strSubOrderGroupId
	 * @param p_strOrderNo
	 */
	public NewCPObject(String p_strCpId,String p_strOrderGroupId,String p_strSubOrderGroupId,String p_strOrderNo){
		m_strCpId = p_strCpId;
		m_strOrderGroupId=p_strOrderGroupId;
		m_strSubOrderGroupId=p_strSubOrderGroupId;
		m_strOrderNo=p_strOrderNo;
	}
	
	public NewCPObject(String p_strCpId){
		m_strCpId = p_strCpId;
	}
	
	
	/**
	 * 克隆基准路径
	 * 2015-12-24
	 * 吴海龙 
	 * @throws SQLException 
	 */
	public void CloneCpPath() throws SQLException{
		//LcpMaster
		LcpMaster _objLcpMaster = new LcpMaster(m_strCpId);
		List<Object> _listlcpmaster =_objLcpMaster.GetCpObjectList();
		_objLcpMaster.CloneCpObjectList(_listlcpmaster);
		//_objLcpMasterNode
		LcpMasterNode _objLcpMasterNode = new LcpMasterNode(m_strCpId);
		List<Object> _listlcpmasternode =_objLcpMasterNode.GetCpObjectList();
		_objLcpMasterNode.CloneCpObjectList(_listlcpmasternode);
		//_objLcpNodeOrderPoint
		LcpNodeOrderPoint _objLcpNodeOrderPoint = new LcpNodeOrderPoint(m_strCpId);
		List<Object> _listlcpnodeorderpoint =_objLcpNodeOrderPoint.GetCpObjectList();
		_objLcpNodeOrderPoint.CloneCpObjectList(_listlcpnodeorderpoint);
		//_objLcpNodeOrderItem
		LcpNodeOrderItem _objLcpNodeOrderItem = new LcpNodeOrderItem(m_strCpId);
		List<Object> _listlcpnodeorderitem =_objLcpNodeOrderItem.GetCpObjectList();
		_objLcpNodeOrderItem.CloneCpObjectList(_listlcpnodeorderitem);
		//--
		//LcpMasterExclude
		LcpMasterExclude _objLcpMasterExclude = new LcpMasterExclude(m_strCpId);
		List<Object> _listLcpMasterExclude =_objLcpMasterExclude.GetCpObjectList();
		_objLcpMasterExclude.CloneCpObjectList(_listLcpMasterExclude);
		//LcpMasterIncome
		LcpMasterIncome _objLcpMasterIncome = new LcpMasterIncome(m_strCpId);
		List<Object> _listLcpMasterIncome =_objLcpMasterIncome.GetCpObjectList();
		_objLcpMasterIncome.CloneCpObjectList(_listLcpMasterIncome);
		//LCP_MASTER_BASED
		LcpMasterBased _objLcpMasterBased = new LcpMasterBased(m_strCpId);
		List<Object> _listLcpMasterBased =_objLcpMasterBased.GetCpObjectList();
		_objLcpMasterBased.CloneCpObjectList(_listLcpMasterBased);
		//LCP_MASTER_ANTIBIOTICS
		LcpMasterAntibiotics _objLcpMasterAntibiotics = new LcpMasterAntibiotics(m_strCpId);
		List<Object> _listLcpMasterAntibiotics =_objLcpMasterAntibiotics.GetCpObjectList();
		_objLcpMasterAntibiotics.CloneCpObjectList(_listLcpMasterAntibiotics);
		//LCP_MASTER_DISCHARGE
		LcpMasterDischarge _objLcpMasterDischarge = new LcpMasterDischarge(m_strCpId);
		List<Object> _listLcpMasterDischarge =_objLcpMasterDischarge.GetCpObjectList();
		_objLcpMasterDischarge.CloneCpObjectList(_listLcpMasterDischarge);
		//LCP_NODE_RELATE
		LcpNodeRelate _objLcpNodeRelate = new LcpNodeRelate(m_strCpId);
		List<Object> _listLcpNodeRelate =_objLcpNodeRelate.GetCpObjectList();
		_objLcpNodeRelate.CloneCpObjectList(_listLcpNodeRelate);
	}
	
	/**
	 * 清理医嘱
	 * 2015-12-25
	 * 吴海龙 
	 * @throws SQLException 
	 */
	public int ClearLcpNodeOrderItem(String p_strHosId,String p_strCpId,String p_strCpNodeId,String p_strCpNodeOrderId,String p_strCpNodeOrderItemId) throws SQLException{
		String _strSQL = "delete LCP_NODE_ORDER_ITEM t\r\n" + 
				" where t.hospital_id = "+p_strHosId+"\r\n" + 
				"   and t.cp_id = "+LcpMaster.m_strTargetCpId+"\r\n" + 
				"   and t.cp_node_id="+p_strCpNodeId+"\r\n" + 
				"   and t.cp_node_order_id = "+p_strCpNodeOrderId+"\r\n" + 
				"   and t.cp_node_order_item_id = "+p_strCpNodeOrderItemId+"";
		int _n = CommonFunction.ExecuteNonQuery(_strSQL);
		return _n;
	}
	
	
	/**
	 * 返回精简后的路径简明情况 2015-12-25 吴海龙
	 */
	public String GetClearedCp() {
		String _strSQL = "select \r\n" + 
				"d.cp_code,\r\n" + 
				"a.cp_id,\r\n" + 
				"a.order_no,\r\n" + 
				"a.cp_node_id,\r\n" + 
				"c.cp_node_name,\r\n" + 
				"a.cp_node_order_id,\r\n" + 
				"a.cp_node_order_item_id,\r\n" + 
				"b.cp_node_order_text group_name,\r\n" + 
				"a.cp_node_order_text,\r\n" + 
				"a.SPECIFICATION, --规格\r\n" + 
				"a.MEASURE, --领量\r\n" + 
				"a.FREQUENCY, --频次\r\n" + 
				"(case a.ORDER_KIND\r\n" + 
				"when '0' then\r\n" + 
				"'临时'\r\n" + 
				"when '1' then\r\n" + 
				"'长期'\r\n" + 
				"when '2' then\r\n" + 
				"'出院'\r\n" + 
				"else\r\n" + 
				"''\r\n" + 
				"end) as ORDER_KIND --类型\r\n" + 
				"from lcp_master d,lcp_master_node c, LCP_NODE_ORDER_ITEM a, lcp_node_order_point b\r\n" + 
				"where \r\n" + 
				"d.hospital_id = c.hospital_id\r\n" + 
				"and d.cp_id = c.cp_id\r\n" + 
				"and c.hospital_id = a.hospital_id\r\n" + 
				"and c.cp_id = a.cp_id\r\n" + 
				"and c.cp_node_id = a.cp_node_id\r\n" + 
				"and a.hospital_id = b.hospital_id\r\n" + 
				"and a.cp_id = b.cp_id\r\n" + 
				"and a.cp_node_id = b.cp_node_id\r\n" + 
				"and a.cp_node_order_id = b.cp_node_order_id\r\n" + 
				"and a.cp_id = '"+LcpMaster.m_strTargetCpId+"'\r\n" + 
				"order by cp_node_id asc, cp_node_order_id, cp_node_order_item_id";
		List<Object> _listLcpClone = new ArrayList();
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			String _strJson = "{\"cp_orders\":[";
			while (_rsData.next()) {
				String _strCpCode = _rsData.getString("cp_code");
				String _strOrderNo = _rsData.getString("order_no");
				int _nCpId = _rsData.getInt("cp_id");
				int _nNodeId = _rsData.getInt("cp_node_id");
				String _strNodeName = _rsData.getString("cp_node_name");
				int _nNodeOrderId = _rsData.getInt("cp_node_order_id");
				int _nNodeOrderItemId = _rsData.getInt("cp_node_order_item_id");// cp_node_order_item_id
				String _strGroupName = _rsData.getString("group_name");
				String _strOrderText = _rsData.getString("cp_node_order_text");
				String _strSpecification = CommonFunction.FixNull(_rsData.getString("SPECIFICATION"));
				String _strMeasure = CommonFunction.FixNull(_rsData.getString("MEASURE"));
				String _strFrequency = CommonFunction.FixNull(_rsData.getString("FREQUENCY"));
				String _strOrderKind = _rsData.getString("ORDER_KIND");
				
				// double _dLv = _rsData.getDouble("lv");
				_strJson += "{\"cp_code\":\""+_strCpCode+"\",\"cp_id\":" + _nCpId + "," + "\"node_id\":"
						+ _nNodeId + "," + "\"node_name\":\"" + _strNodeName
						+ "\"," + "\"order_id\":" + _nNodeOrderId + ","
						+ "\"order_item_id\":" + _nNodeOrderItemId + ","
						+ "\"group_name\":\"" + _strGroupName + "\","
						+ "\"order_text\":\"" + _strOrderText + "\","
						+ "\"order_no\":\"" + _strOrderNo + "\","
						+ "\"SPECIFICATION\":\"" + _strSpecification + "\","
						+ "\"MEASURE\":\"" + _strMeasure + "\","
						+ "\"FREQUENCY\":\"" + _strFrequency + "\","
						+ "\"ORDER_KIND\":\"" + _strOrderKind + "\"" + "},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			System.out.println(_strJson);
			return _strJson;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

}
