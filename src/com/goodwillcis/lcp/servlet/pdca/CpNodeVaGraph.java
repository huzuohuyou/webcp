package com.goodwillcis.lcp.servlet.pdca;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 路径节点变异图
 * @author wuhailong
 * 2015-12-26
 */
public class CpNodeVaGraph {

	public CpNodeVaGraph() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取变异节点
	 * 2015-12-18
	 * 吴海龙 
	 */
	public String GetVaNodeCountByCpId(String p_strCpId) throws SQLException {
		String _strCpId = p_strCpId;
		System.out.println("cp_id" + _strCpId);
		String _strSQL = " select mm.node_no,mm.cp_node_name byjd, mm.cp_node_id, nvl(nn.mycount, 0) mycount"
				+ " from (select rownum node_no, m.cp_node_name, cp_node_id"
				+ " from LCP_MASTER_NODE m"
				+ " where cp_id = '"
				+ _strCpId
				+ "'"
				+ " and cp_node_type = '1') mm"
				+ " left outer join (select rownum node_no, x.*"
				+ " from (select m.byjd cp_node_id, count(*) mycount"
				+ " from (select m.patient_no, count(*) byjd"
				+ " from LCP_PATIENT_NODE m,"
				+ " (select tt.patient_no"
				+ " from LCP_PATIENT_NODE tt"
				+ " where cp_id = '"
				+ _strCpId
				+ "'"
				+ " and cp_node_type = '4') n"
				+ " where m.patient_no = n.patient_no"
				+ " group by m.patient_no) m"
				+ " group by byjd) x) nn"
				+ " on mm.node_no = nn.node_no" + " order by cp_node_id asc";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		int _nCount = GetCpNodeCount(_strCpId);
		try {
			int _nMaxCount = 0;
			String _strJson = "{\"cp\":[";
			while (_rsData.next()) {
				String _strNodeName = _rsData.getString("byjd");
				String _strNodeId = _rsData.getString("cp_node_id");
				int _nMyCount = _rsData.getInt("mycount");
				int _nNodeNo = _rsData.getInt("node_no");
				_nMaxCount = _nMaxCount < _nMyCount ? _nMyCount : _nMaxCount;
				System.out.println(_strNodeName + "---" + _nMyCount);
				_strJson += " {\"node_name\":\"" + _strNodeName
						+ "\",\"node_no\":" + _nNodeNo + ",\"node_id\":"
						+ _strNodeId + ",\"vacount\":" + _nMyCount + "},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "],\"numberx\":" + _nCount;
			_strJson += ",\"endy\":" + ((_nMaxCount / 10) + 1) * 10;
			_strJson += "}";
			System.out.println(_strJson);
			return _strJson;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 获取节点个数
	 * 2015-12-18
	 * 吴海龙 
	 */
	public int GetCpNodeCount(String _strCpId) throws SQLException {
		String _strSQL = "select count(*) mycount from LCP_MASTER_NODE where cp_id='"
				+ _strCpId + "' and cp_node_type ='1'";
		ResultSet _rsCpNodeCount = CommonFunction.ExecuteQuery(_strSQL);
		int _nCount = 0;
		while (_rsCpNodeCount.next()) {
			_nCount = _rsCpNodeCount.getInt("mycount");
		}
		return _nCount;
	}
}
