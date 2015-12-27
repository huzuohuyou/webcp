package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 院内出院标准
 * @author wuhailong
 * 2015-12-25
 */
public class LcpMasterDischarge implements ICloneCp{

	private int HOSPITAL_ID;
	public int getHOSPITAL_ID() {
		return HOSPITAL_ID;
	}
	public void setHOSPITAL_ID(int hOSPITAL_ID) {
		HOSPITAL_ID = hOSPITAL_ID;
	}
	private int CP_ID;
	private int CP_DISCHARGE_ID;
	private String CP_DISCHARGE_NAME;
	private int DISPLAY_ORDER;
	private Date VERIFY_DATE;
	private String VERIFY_CODE;
	private String VERIFY_NAME;
	private int SYS_IS_DEL;
	private Date SYS_LAST_UPDATE;
	public int getCP_ID() {
		return CP_ID;
	}
	public void setCP_ID(int cP_ID) {
		CP_ID = cP_ID;
	}
	public int getCP_DISCHARGE_ID() {
		return CP_DISCHARGE_ID;
	}
	public void setCP_DISCHARGE_ID(int cP_DISCHARGE_ID) {
		CP_DISCHARGE_ID = cP_DISCHARGE_ID;
	}
	public String getCP_DISCHARGE_NAME() {
		return CommonFunction.FixNull(CP_DISCHARGE_NAME);
	}
	public void setCP_DISCHARGE_NAME(String cP_DISCHARGE_NAME) {
		CP_DISCHARGE_NAME = cP_DISCHARGE_NAME;
	}
	public int getDISPLAY_ORDER() {
		return DISPLAY_ORDER;
	}
	public void setDISPLAY_ORDER(int dISPLAY_ORDER) {
		DISPLAY_ORDER = dISPLAY_ORDER;
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
	public String getM_strCpId() {
		return CommonFunction.FixNull(m_strCpId);
	}
	public void setM_strCpId(String m_strCpId) {
		this.m_strCpId = m_strCpId;
	}


	
	public LcpMasterDischarge(){}
	private String m_strCpId;

	public LcpMasterDischarge(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	@Override
	public List<Object> GetCpObjectList() {
		String _strSQL = " select * from LCP_MASTER_DISCHARGE where cp_id = '"
			+ m_strCpId + "'";
		List<Object> _listLcpClone = new ArrayList();
	ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
	try {
		
		while (_rsData.next()) {
			LcpMasterDischarge _lcpmaster = new LcpMasterDischarge();
			_lcpmaster.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
			_lcpmaster.setCP_ID(_rsData.getInt("CP_ID"));
			_lcpmaster.setCP_DISCHARGE_ID(_rsData.getInt("CP_DISCHARGE_ID"));
			_lcpmaster.setCP_DISCHARGE_NAME(_rsData.getString("CP_DISCHARGE_NAME"));//DISPLAY_ORDER
			_lcpmaster.setDISPLAY_ORDER(_rsData.getInt("DISPLAY_ORDER"));
			_lcpmaster.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
			_lcpmaster.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
			_lcpmaster.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
			_lcpmaster.setSYS_IS_DEL(_rsData
					.getInt("SYS_IS_DEL"));
			_lcpmaster.setSYS_LAST_UPDATE(_rsData.getDate("SYS_LAST_UPDATE"));
			_listLcpClone.add(_lcpmaster);
		}
		return _listLcpClone;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

	@Override
	public int CloneCpObject(Object _obj) throws SQLException {
		LcpMasterDischarge _objLcp = (LcpMasterDischarge) _obj;
		String _strSQL = "insert into LCP_MASTER_DISCHARGE\r\n"
				+ "(CP_ID,\r\n"
				+ "HOSPITAL_ID,\r\n"
				+ "CP_DISCHARGE_ID,\r\n"
				+ "CP_DISCHARGE_NAME,\r\n"
				+ "DISPLAY_ORDER,\r\n"
				+ "VERIFY_DATE,\r\n"
				+ "VERIFY_CODE,\r\n"
				+ "VERIFY_NAME,\r\n"
				+ "SYS_IS_DEL,\r\n"
				+ "SYS_LAST_UPDATE)\r\n"
				+ "values\r\n"
				+ "(" +
				+LcpMaster.m_strTargetCpId+","+_objLcp.getHOSPITAL_ID()+","+_objLcp.getCP_DISCHARGE_ID()+",'"
				+_objLcp.getCP_DISCHARGE_NAME()+"',"+_objLcp.getDISPLAY_ORDER()+","
				+"sysdate,'"
				+_objLcp.getVERIFY_CODE()+"','"+_objLcp.getVERIFY_NAME()+"',"
				+_objLcp.getSYS_IS_DEL()+",sysdate"
						+")";
		System.out.println(_strSQL);
		int _n = CommonFunction.ExecuteNonQuery(_strSQL);
		return _n;
	
	}

	@Override
	public void CloneCpObjectList(List<Object> _listobj) throws SQLException {
		for (Object object : _listobj) {
			CloneCpObject(object);
		}
	}
	
	

}
