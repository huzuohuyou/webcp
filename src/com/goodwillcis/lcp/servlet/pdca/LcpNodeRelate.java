package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 路径制定输入输出节点
 * @author wuhailong
 * 2015-12-25
 */
public class LcpNodeRelate implements ICloneCp {

	private int HOSPITAL_ID;
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

	public int getCP_NEXT_NODE_ID() {
		return CP_NEXT_NODE_ID;
	}

	public void setCP_NEXT_NODE_ID(int cP_NEXT_NODE_ID) {
		CP_NEXT_NODE_ID = cP_NEXT_NODE_ID;
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

	private int CP_ID;
	private int CP_NODE_ID;
	private int CP_NEXT_NODE_ID;
	 private Date	VERIFY_DATE;
    private String	VERIFY_CODE;
    private String	VERIFY_NAME;
    private int	SYS_IS_DEL;
    private Date	SYS_LAST_UPDATE;

	
	public LcpNodeRelate() {
		// TODO Auto-generated constructor stub
	}
	private String m_strCpId;

	public LcpNodeRelate(String p_strCpId) {
		m_strCpId = p_strCpId;
	}

	@Override
	public List<Object> GetCpObjectList() {
		String _strSQL = " select * from LCP_NODE_RELATE where cp_id = '"
			+ m_strCpId + "'";
		List<Object> _listLcpobj = new ArrayList();
	ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
	try {
		
		while (_rsData.next()) {
			LcpNodeRelate _lcpObj = new LcpNodeRelate();
			_lcpObj.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
			_lcpObj.setCP_ID(_rsData.getInt("CP_ID"));
			_lcpObj.setCP_NODE_ID(_rsData.getInt("CP_NODE_ID"));
			_lcpObj.setCP_NEXT_NODE_ID(_rsData.getInt("CP_NEXT_NODE_ID"));
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
		LcpNodeRelate _objLcp = (LcpNodeRelate) _obj;
		String _strSQL = "insert into LCP_NODE_RELATE\r\n"
				+ "(CP_ID,\r\n"
				+ "HOSPITAL_ID,\r\n"
				+ "CP_NODE_ID,\r\n"
				+ "CP_NEXT_NODE_ID,\r\n"
				+ "VERIFY_DATE,\r\n"
				+ "VERIFY_CODE,\r\n"
				+ "VERIFY_NAME,\r\n"
				+ "SYS_IS_DEL,\r\n"
				+ "SYS_LAST_UPDATE)\r\n"
				+ "values\r\n"
				+ "(" +
				+LcpMaster.m_strTargetCpId+","+_objLcp.getHOSPITAL_ID()+","+_objLcp.getCP_NODE_ID()+","
				+_objLcp.getCP_NEXT_NODE_ID()+",sysdate,'"
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
