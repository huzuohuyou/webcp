package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 院内临床路径制定纳入条件
 * @author wuhailong
 * 2015-12-25
 */
public class LcpMasterIncome implements ICloneCp {
	private int HOSPITAL_ID;
	public int getHOSPITAL_ID() {
		return HOSPITAL_ID;
	}

	public void setHOSPITAL_ID(int hOSPITAL_ID) {
		HOSPITAL_ID = hOSPITAL_ID;
	}

	private int	CP_ID;
    public int getCP_ID() {
		return CP_ID;
	}

	public void setCP_ID(int cP_ID) {
		CP_ID = cP_ID;
	}

	public int getCP_INCOME_ID() {
		return CP_INCOME_ID;
	}

	public void setCP_INCOME_ID(int cP_INCOME_ID) {
		CP_INCOME_ID = cP_INCOME_ID;
	}

	public String getCP_INCOME_TYPE() {
		return CommonFunction.FixNull(CP_INCOME_TYPE);
	}

	public void setCP_INCOME_TYPE(String cP_INCOME_TYPE) {
		CP_INCOME_TYPE = cP_INCOME_TYPE;
	}

	public String getCP_INCOME_NAME() {
		return CommonFunction.FixNull(CP_INCOME_NAME);
	}

	public void setCP_INCOME_NAME(String cP_INCOME_NAME) {
		CP_INCOME_NAME = cP_INCOME_NAME;
	}

	public String getCP_INCOME_CODE() {
		return CommonFunction.FixNull(CP_INCOME_CODE);
	}

	public void setCP_INCOME_CODE(String cP_INCOME_CODE) {
		CP_INCOME_CODE = cP_INCOME_CODE;
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

	private int	CP_INCOME_ID;
    private String	CP_INCOME_TYPE;
    private String	CP_INCOME_NAME;
    private String	CP_INCOME_CODE;
    private int	DISPLAY_ORDER;
    private Date	VERIFY_DATE;
    private String	VERIFY_CODE;
    private String	VERIFY_NAME;
    private int	SYS_IS_DEL;
    private Date	SYS_LAST_UPDATE;
   

	private String m_strCpId;

	public LcpMasterIncome(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	
	public LcpMasterIncome() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Object> GetCpObjectList() {
		String _strSQL = " select * from LCP_MASTER_INCOME where cp_id = '"
			+ m_strCpId + "'";
		List<Object> _listLcpobj = new ArrayList();
	ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
	try {
		
		while (_rsData.next()) {
			LcpMasterIncome _lcpObj = new LcpMasterIncome();
			_lcpObj.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
			_lcpObj.setCP_ID(_rsData.getInt("CP_ID"));
			_lcpObj.setCP_INCOME_ID(_rsData.getInt("CP_INCOME_ID"));
			_lcpObj.setCP_INCOME_TYPE(_rsData.getString("CP_INCOME_TYPE"));
			_lcpObj.setCP_INCOME_NAME(_rsData.getString("CP_INCOME_NAME"));
			_lcpObj.setCP_INCOME_CODE(_rsData.getString("CP_INCOME_CODE"));
			_lcpObj.setDISPLAY_ORDER(_rsData.getInt("DISPLAY_ORDER"));
			_lcpObj.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
			_lcpObj.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
			_lcpObj.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
			_lcpObj.setSYS_IS_DEL(_rsData
					.getInt("SYS_IS_DEL"));
			_lcpObj.setSYS_LAST_UPDATE(_rsData.getDate("SYS_LAST_UPDATE"));
			
			_listLcpobj.add(_lcpObj);
		}
		return _listLcpobj;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}

	@Override
	public int CloneCpObject(Object _obj) throws SQLException {
		LcpMasterIncome _objLcp = (LcpMasterIncome) _obj;
		String _strSQL = "insert into LCP_MASTER_INCOME\r\n"
				+ "(CP_ID,\r\n"
				+ "HOSPITAL_ID,\r\n"
				+ "CP_INCOME_ID,\r\n"
				+ "CP_INCOME_TYPE,\r\n"
				+ "CP_INCOME_NAME,\r\n"
				+ "CP_INCOME_CODE,\r\n"
				+ "DISPLAY_ORDER,\r\n"
				+ "VERIFY_DATE,\r\n"
				+ "VERIFY_CODE,\r\n"
				+ "VERIFY_NAME,\r\n"
				+ "SYS_IS_DEL,\r\n"
				+ "SYS_LAST_UPDATE)\r\n"
				+ "values\r\n"
				+ "(" +
				+LcpMaster.m_strTargetCpId+","+_objLcp.getHOSPITAL_ID()+","+_objLcp.getCP_INCOME_ID()+",'"
				+_objLcp.getCP_INCOME_TYPE()+"','"+_objLcp.getCP_INCOME_NAME()+"','"
				+_objLcp.getCP_INCOME_CODE()+"',"+_objLcp.getDISPLAY_ORDER()+",sysdate,'"
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
