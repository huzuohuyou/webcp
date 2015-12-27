package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 院内预防性抗菌药物选择与使用时机
 * @author wuhailong
 * 2015-12-25
 */
public class LcpMasterAntibiotics implements ICloneCp {

	private int HOSPITAL_ID;
	public int getHOSPITAL_ID() {
		return HOSPITAL_ID;
	}

	public void setHOSPITAL_ID(int hOSPITAL_ID) {
		HOSPITAL_ID = hOSPITAL_ID;
	}
	private int CP_ID;
	private String CP_ANTIBIOTICS;
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



	public String getCP_ANTIBIOTICS() {
		return CommonFunction.FixNull(CP_ANTIBIOTICS);
	}



	public void setCP_ANTIBIOTICS(String cP_ANTIBIOTICS) {
		CP_ANTIBIOTICS = cP_ANTIBIOTICS;
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

	

	
	public LcpMasterAntibiotics() {
		// TODO Auto-generated constructor stub
	}

	private String m_strCpId;

	public LcpMasterAntibiotics(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	
	@Override
	public List<Object> GetCpObjectList() {
		String _strSQL = " select * from LCP_MASTER_ANTIBIOTICS where cp_id = '"
			+ m_strCpId + "'";
		List<Object> _listLcpClone = new ArrayList();
	ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
	try {
		
		while (_rsData.next()) {
			LcpMasterAntibiotics _lcpmaster = new LcpMasterAntibiotics();
			_lcpmaster.setCP_ID(_rsData.getInt("CP_ID"));
			_lcpmaster.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
			_lcpmaster.setCP_ANTIBIOTICS(_rsData.getString("CP_ANTIBIOTICS"));
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
		LcpMasterAntibiotics _objLcp = (LcpMasterAntibiotics) _obj;
		String _strSQL = "insert into LCP_MASTER_ANTIBIOTICS\r\n"
				+ "(CP_ID,\r\n"
				+ "HOSPITAL_ID,\r\n"
				+ "CP_ANTIBIOTICS,\r\n"
				+ "VERIFY_DATE,\r\n"
				+ "VERIFY_CODE,\r\n"
				+ "VERIFY_NAME,\r\n"
				+ "SYS_IS_DEL,\r\n"
				+ "SYS_LAST_UPDATE)\r\n"
				+ "values\r\n"
				+ "(" +
				+LcpMaster.m_strTargetCpId+","+_objLcp.getHOSPITAL_ID()+",'"+_objLcp.getCP_ANTIBIOTICS()+"',"
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
		// TODO Auto-generated method stub
		for (Object object : _listobj) {
			CloneCpObject(object);
		}
	}

}
