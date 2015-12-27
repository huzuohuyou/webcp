package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LcpNodeOrderPoint implements ICloneCp {

	private int HOSPITAL_ID;
	private int CP_ID;
	private int CP_NODE_ID;
	private int CP_NODE_ORDER_ID;
	private String CP_NODE_ORDER_TEXT;
	private int CONTINUE_ITEM;
	private int CONTINUE_CP_NODE_ID;
	private int CONTINUE_ORDER_ID;
	private int NEED_ITEM;
	private int AUTO_ITEM;
	private String ORDER_KIND;
	private Date VERIFY_DATE;
	private String VERIFY_CODE;
	private String VERIFY_NAME;
	private int SYS_IS_DEL;
	private Date SYS_LAST_UPDATE;
	private int CP_NODE_CLASS_ID;

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

	public int getCP_NODE_ORDER_ID() {
		return CP_NODE_ORDER_ID;
	}

	public void setCP_NODE_ORDER_ID(int cP_NODE_ORDER_ID) {
		CP_NODE_ORDER_ID = cP_NODE_ORDER_ID;
	}

	public String getCP_NODE_ORDER_TEXT() {
		return CommonFunction.FixNull(CP_NODE_ORDER_TEXT);
	}

	public void setCP_NODE_ORDER_TEXT(String cP_NODE_ORDER_TEXT) {
		CP_NODE_ORDER_TEXT = cP_NODE_ORDER_TEXT;
	}

	public int getCONTINUE_ITEM() {
		return CONTINUE_ITEM;
	}

	public void setCONTINUE_ITEM(int cONTINUE_ITEM) {
		CONTINUE_ITEM = cONTINUE_ITEM;
	}

	public int getCONTINUE_CP_NODE_ID() {
		return CONTINUE_CP_NODE_ID;
	}

	public void setCONTINUE_CP_NODE_ID(int cONTINUE_CP_NODE_ID) {
		CONTINUE_CP_NODE_ID = cONTINUE_CP_NODE_ID;
	}

	public int getCONTINUE_ORDER_ID() {
		return CONTINUE_ORDER_ID;
	}

	public void setCONTINUE_ORDER_ID(int cONTINUE_ORDER_ID) {
		CONTINUE_ORDER_ID = cONTINUE_ORDER_ID;
	}

	public int getNEED_ITEM() {
		return NEED_ITEM;
	}

	public void setNEED_ITEM(int nEED_ITEM) {
		NEED_ITEM = nEED_ITEM;
	}

	public int getAUTO_ITEM() {
		return AUTO_ITEM;
	}

	public void setAUTO_ITEM(int aUTO_ITEM) {
		AUTO_ITEM = aUTO_ITEM;
	}

	public String getORDER_KIND() {
		return CommonFunction.FixNull(ORDER_KIND);
	}

	public void setORDER_KIND(String oRDER_KIND) {
		ORDER_KIND = oRDER_KIND;
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

	public int getCP_NODE_CLASS_ID() {
		return CP_NODE_CLASS_ID;
	}

	public void setCP_NODE_CLASS_ID(int cP_NODE_CLASS_ID) {
		CP_NODE_CLASS_ID = cP_NODE_CLASS_ID;
	}

	public LcpNodeOrderPoint(){}
	private String m_strCpId;

	public LcpNodeOrderPoint(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	
	LcpMaster m_strLcpMaster =null;
	public LcpNodeOrderPoint(LcpMaster p_strObj) {
		m_strLcpMaster=p_strObj;
	}
	@Override
	public List<Object> GetCpObjectList() {
		List<Object> _listObj = new ArrayList();
		String _strSQL = " select * from LCP_NODE_ORDER_POINT where cp_id = '"
				+ m_strCpId + "'";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			while (_rsData.next()) {
				LcpNodeOrderPoint _lcpobj = new LcpNodeOrderPoint();
				_lcpobj.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
				_lcpobj.setCP_ID(_rsData.getInt("CP_ID"));
				_lcpobj.setCP_NODE_ID(_rsData.getInt("CP_NODE_ID"));
				_lcpobj.setCP_NODE_ORDER_ID(_rsData
						.getInt("CP_NODE_ORDER_ID"));
				_lcpobj.setCP_NODE_ORDER_TEXT(_rsData
						.getString("CP_NODE_ORDER_TEXT"));
				_lcpobj.setCONTINUE_ITEM(_rsData
						.getInt("CONTINUE_ITEM"));
				_lcpobj.setCONTINUE_CP_NODE_ID(_rsData
						.getInt("CONTINUE_CP_NODE_ID"));
				_lcpobj.setCONTINUE_ORDER_ID(_rsData
						.getInt("CONTINUE_ORDER_ID"));
				_lcpobj.setNEED_ITEM(_rsData.getInt("NEED_ITEM"));
				_lcpobj.setAUTO_ITEM(_rsData.getInt("AUTO_ITEM"));
				_lcpobj.setORDER_KIND(_rsData.getString("ORDER_KIND"));
				_lcpobj.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
				_lcpobj.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
				_lcpobj.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
				_lcpobj.setSYS_IS_DEL(_rsData.getInt("SYS_IS_DEL"));
				_lcpobj.setSYS_LAST_UPDATE(_rsData.getDate("SYS_LAST_UPDATE"));
				_lcpobj.setCP_NODE_CLASS_ID(_rsData.getInt("CP_NODE_CLASS_ID"));
				_listObj.add(_lcpobj);
			}
			return _listObj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int CloneCpObject(Object _obj) throws SQLException {
		LcpNodeOrderPoint _lcpobj = (LcpNodeOrderPoint) _obj;
		String _strSQL = "insert into LCP_NODE_ORDER_POINT\r\n" + 
				"(hospital_id,\r\n" + 
				"CP_ID,\r\n" + 
				"CP_NODE_ID,\r\n" + 
				"CP_NODE_ORDER_ID,\r\n" + 
				"CP_NODE_ORDER_TEXT,\r\n" + 
				"CONTINUE_ITEM,\r\n" + 
				"CONTINUE_CP_NODE_ID,\r\n" + 
				"CONTINUE_ORDER_ID,\r\n" + 
				"NEED_ITEM,\r\n" + 
				"AUTO_ITEM,\r\n" + 
				"ORDER_KIND,\r\n" + 
				"VERIFY_DATE,\r\n" + 
				"VERIFY_CODE,\r\n" + 
				"VERIFY_NAME,\r\n" + 
				"SYS_IS_DEL,\r\n" + 
				"SYS_LAST_UPDATE,\r\n" + 
				"CP_NODE_CLASS_ID)\r\n" + 
				"values\r\n" 
				+ "(%d,%d,%d,%d,'%s',%d,%d,%d,%d,%d,'%s',sysdate,'%s','%s',%d,sysdate,%d)";
		_strSQL = String.format(
				_strSQL,
				_lcpobj.getHOSPITAL_ID(),
				LcpMaster.m_strTargetCpId,
				_lcpobj.getCP_NODE_ID(),
				// --
				_lcpobj.getCP_NODE_ORDER_ID(),
				_lcpobj.getCP_NODE_ORDER_TEXT(),
				_lcpobj.getCONTINUE_ITEM(),
				// --
				_lcpobj.getCONTINUE_CP_NODE_ID(),
				_lcpobj.getCONTINUE_ORDER_ID(),
				// --
				_lcpobj.getNEED_ITEM(),
				_lcpobj.getAUTO_ITEM(),
				_lcpobj.getORDER_KIND(),
				_lcpobj.getVERIFY_CODE(),
				_lcpobj.getVERIFY_NAME(),
				_lcpobj.getSYS_IS_DEL(),
				_lcpobj.getCP_NODE_CLASS_ID());
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
