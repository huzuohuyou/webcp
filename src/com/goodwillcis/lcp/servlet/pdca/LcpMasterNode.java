package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LcpMasterNode implements ICloneCp {
	private int HOSPITAL_ID;
	private int CP_ID;
	private int CP_NODE_ID;
	private int CP_STD_NODE_ID;
	private int CP_NODE_PARENT_ID;
	private String CP_NODE_NAME;
	private int CP_NODE_DAYS_MIN;
	private int CP_NODE_DAYS_MAX;
	private int CP_NODE_DAYS;
	private int CP_NODE_TYPE;
	private Date VERIFY_DATE;
	private String VERIFY_CODE;
	private String VERIFY_NAME;
	private int SYS_IS_DEL;
	private Date SYS_LAST_UPDATE;

	public int getHOSPITAL_ID() {
		return HOSPITAL_ID;
	}

	public void setHOSPITAL_ID(int hOSPITAL_ID) {
		HOSPITAL_ID = hOSPITAL_ID;
	}

	public int getCP_ID() {
		return CP_ID;
	}

	public void setCP_ID(int cP_ID) {
		CP_ID = cP_ID;
	}

	public int getCP_NODE_ID() {
		return CP_NODE_ID;
	}

	public void setCP_NODE_ID(int cP_NODE_ID) {
		CP_NODE_ID = cP_NODE_ID;
	}

	public int getCP_STD_NODE_ID() {
		return CP_STD_NODE_ID;
	}

	public void setCP_STD_NODE_ID(int cP_STD_NODE_ID) {
		CP_STD_NODE_ID = cP_STD_NODE_ID;
	}

	public int getCP_NODE_PARENT_ID() {
		return CP_NODE_PARENT_ID;
	}

	public void setCP_NODE_PARENT_ID(int cP_NODE_PARENT_ID) {
		CP_NODE_PARENT_ID = cP_NODE_PARENT_ID;
	}

	public String getCP_NODE_NAME() {
		return CommonFunction.FixNull(CP_NODE_NAME);
	}

	public void setCP_NODE_NAME(String cP_NODE_NAME) {
		CP_NODE_NAME = cP_NODE_NAME;
	}

	public int getCP_NODE_DAYS_MIN() {
		return CP_NODE_DAYS_MIN;
	}

	public void setCP_NODE_DAYS_MIN(int cP_NODE_DAYS_MIN) {
		CP_NODE_DAYS_MIN = cP_NODE_DAYS_MIN;
	}

	public int getCP_NODE_DAYS_MAX() {
		return CP_NODE_DAYS_MAX;
	}

	public void setCP_NODE_DAYS_MAX(int cP_NODE_DAYS_MAX) {
		CP_NODE_DAYS_MAX = cP_NODE_DAYS_MAX;
	}

	public int getCP_NODE_DAYS() {
		return CP_NODE_DAYS;
	}

	public void setCP_NODE_DAYS(int cP_NODE_DAYS) {
		CP_NODE_DAYS = cP_NODE_DAYS;
	}

	public int getCP_NODE_TYPE() {
		return CP_NODE_TYPE;
	}

	public void setCP_NODE_TYPE(int cP_NODE_TYPE) {
		CP_NODE_TYPE = cP_NODE_TYPE;
	}

	public Date getVERIFY_DATE() {
		return VERIFY_DATE;
	}

	public void setVERIFY_DATE(Date vERIFY_DATE) {
		VERIFY_DATE = vERIFY_DATE;
	}

	public String getVERIFY_CODE() {
		return CommonFunction.FixNull(VERIFY_CODE);
	}

	public void setVERIFY_CODE(String vERIFY_CODE) {
		VERIFY_CODE = vERIFY_CODE;
	}

	public String getVERIFY_NAME() {
		return CommonFunction.FixNull(VERIFY_NAME);
	}

	public void setVERIFY_NAME(String vERIFY_NAME) {
		VERIFY_NAME = vERIFY_NAME;
	}

	public int getSYS_IS_DEL() {
		return SYS_IS_DEL;
	}

	public void setSYS_IS_DEL(int sYS_IS_DEL) {
		SYS_IS_DEL = sYS_IS_DEL;
	}

	public Date getSYS_LAST_UPDATE() {
		return SYS_LAST_UPDATE;
	}

	public void setSYS_LAST_UPDATE(Date sYS_LAST_UPDATE) {
		SYS_LAST_UPDATE = sYS_LAST_UPDATE;
	}

	public LcpMasterNode() {
	}

	private String m_strCpId;

	public LcpMasterNode(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	
	LcpMaster m_strLcpMaster =null;
	public LcpMasterNode(LcpMaster p_strObj) {
		m_strLcpMaster=p_strObj;
	}

	@Override
	public int CloneCpObject(Object _obj) throws SQLException {
		LcpMasterNode _objLcpMasterNode = (LcpMasterNode) _obj;
		String _strSQL = "insert into lcp_master_node\r\n"
				+ "(hospital_id,\r\n" 
				+ "cp_id,\r\n" 
				+ "cp_node_id,\r\n"
				+ "cp_std_node_id,\r\n"
				+ "cp_node_parent_id,\r\n"
				+ "cp_node_name,\r\n" 
				+ "cp_node_days_min,\r\n"
				+ "cp_node_days_max,\r\n" 
				+ "cp_node_days,\r\n"
				+ "cp_node_type,\r\n" 
				+ "verify_date,\r\n" 
				+ "verify_code,\r\n"
				+ "verify_name,\r\n"
				+ "sys_is_del,\r\n"
				+ "sys_last_update)\r\n" + "values\r\n"
				+ "(%d,%d,%d,%d,%d,'%s',%d,%d,%d,%d,sysdate,'%s','%s',%d,sysdate)";
		try {
			_strSQL = String.format(
					_strSQL,
					_objLcpMasterNode.getHOSPITAL_ID(),
					LcpMaster.m_strTargetCpId,
					_objLcpMasterNode.getCP_NODE_ID(),
					// --
					_objLcpMasterNode.getCP_STD_NODE_ID(),
					_objLcpMasterNode.getCP_NODE_PARENT_ID(),
					_objLcpMasterNode.getCP_NODE_NAME(),
					_objLcpMasterNode.getCP_NODE_DAYS_MIN(),
					// --
					_objLcpMasterNode.getCP_NODE_DAYS_MAX(),
					_objLcpMasterNode.getCP_NODE_DAYS(),
					// --
					_objLcpMasterNode.getCP_NODE_TYPE(),
					_objLcpMasterNode.getVERIFY_CODE(),
					_objLcpMasterNode.getVERIFY_NAME(),
					_objLcpMasterNode.getSYS_IS_DEL());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println(_strSQL);
		int _n = CommonFunction.ExecuteNonQuery(_strSQL);
		return _n;
	}

	@Override
	public List<Object> GetCpObjectList() {
		List<Object> _listLacpMasterNode = new ArrayList();
		String _strSQL = " select * from lcp_master_node where cp_id = '"
				+ m_strCpId + "'";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			while (_rsData.next()) {
				LcpMasterNode _lcpmasternode = new LcpMasterNode();
				_lcpmasternode.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
				_lcpmasternode.setCP_ID(_rsData.getInt("CP_ID"));
				_lcpmasternode.setCP_NODE_ID(_rsData.getInt("CP_NODE_ID"));
				_lcpmasternode.setCP_STD_NODE_ID(_rsData
						.getInt("CP_STD_NODE_ID"));
				_lcpmasternode.setCP_NODE_PARENT_ID(_rsData
						.getInt("CP_NODE_PARENT_ID"));
				_lcpmasternode.setCP_NODE_NAME(_rsData
						.getString("CP_NODE_NAME"));
				_lcpmasternode.setCP_NODE_DAYS_MIN(_rsData
						.getInt("CP_NODE_DAYS_MIN"));
				_lcpmasternode.setCP_NODE_DAYS_MAX(_rsData
						.getInt("CP_NODE_DAYS_MAX"));
				_lcpmasternode.setCP_NODE_DAYS(_rsData.getInt("CP_NODE_DAYS"));
				_lcpmasternode.setCP_NODE_TYPE(_rsData.getInt("CP_NODE_TYPE"));
				_lcpmasternode.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
				_lcpmasternode.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
				_lcpmasternode.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
				_lcpmasternode.setSYS_IS_DEL(_rsData.getInt("SYS_IS_DEL"));
				_lcpmasternode.setSYS_LAST_UPDATE(_rsData
						.getDate("SYS_LAST_UPDATE"));
				_listLacpMasterNode.add(_lcpmasternode);
			}
			return _listLacpMasterNode;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void CloneCpObjectList(List<Object> _listobj) throws SQLException {
		for (Object object : _listobj) {
			CloneCpObject(object);
		}

	}

}
