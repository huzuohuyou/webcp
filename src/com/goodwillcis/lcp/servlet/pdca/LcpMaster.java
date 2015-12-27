package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LcpMaster implements ICloneCp {
	private int HOSPITAL_ID;

	private String CP_CODE;
	private int CP_ID;
	private String CP_NAME;
	private Date CP_START_DATE;
	private int CP_MASTER_ID;
	private int CP_VERSION;
	private int CP_STATUS;
	private Date CP_VERSION_DATE;
	private Date CP_CREATE_DATE;
	private String CP_CREATE_ID;
	private String CP_CREATE_NAME;
	private Date CP_STOP_DATE;
	private int CP_DAYS_MIN;
	private int CP_DAYS_MAX;
	private int CP_DAYS;
	private double CP_FEE;
	private String CP_START_USER;
	private String CP_STOP_USER;
	private String DEPT_CODE;
	private String DEPT_NAME;
	private Date VERIFY_DATE;
	private String VERIFY_CODE;
	private String VERIFY_NAME;
	private int SYS_IS_DEL;
	private Date SYS_LAST_UPDATE;
	private String INPUT_CODE_PY;
	private String INPUT_CODE_WB;
	private double HEALTH_CARE_QUOTA;

	private String m_strCpId;

	public LcpMaster(String p_strCpId) {
		m_strCpId = p_strCpId;
	}

	public LcpMaster() {
	}

	public int getHOSPITAL_ID() {
		return HOSPITAL_ID;
	}

	public void setHOSPITAL_ID(int hOSPITAL_ID) {
		HOSPITAL_ID = hOSPITAL_ID;
	}

	public String getCP_CODE() {
		return CommonFunction.FixNull(CP_CODE);
	}

	public void setCP_CODE(String cP_CODE) {
		CP_CODE = cP_CODE;
	}

	public int getCP_ID() {
		return CP_ID;
	}

	public void setCP_ID(int cP_ID) {
		CP_ID = cP_ID;
	}

	public String getCP_NAME() {
		return CommonFunction.FixNull(CP_NAME);
	}

	public void setCP_NAME(String cP_NAME) {
		CP_NAME = cP_NAME;
	}

	public int getCP_MASTER_ID() {
		return CP_MASTER_ID;
	}

	public void setCP_MASTER_ID(int cP_MASTER_ID) {
		CP_MASTER_ID = cP_MASTER_ID;
	}

	public int getCP_VERSION() {
		return CP_VERSION;
	}

	public void setCP_VERSION(int cP_VERSION) {
		CP_VERSION = cP_VERSION;
	}

	public int getCP_STATUS() {
		return CP_STATUS;
	}

	public void setCP_STATUS(int cP_STATUS) {
		CP_STATUS = cP_STATUS;
	}

	public Date getCP_VERSION_DATE() {
		return CP_VERSION_DATE;
	}

	public void setCP_VERSION_DATE(Date cP_VERSION_DATE) {
		CP_VERSION_DATE = cP_VERSION_DATE;
	}

	public Date getCP_CREATE_DATE() {
		return CP_CREATE_DATE;
	}

	public void setCP_CREATE_DATE(Date cP_CREATE_DATE) {
		CP_CREATE_DATE = cP_CREATE_DATE;
	}

	public String getCP_CREATE_ID() {
		return CommonFunction.FixNull(CP_CREATE_ID);
	}

	public void setCP_CREATE_ID(String cP_CREATE_ID) {
		CP_CREATE_ID = cP_CREATE_ID;
	}

	public String getCP_CREATE_NAME() {
		return CommonFunction.FixNull(CP_CREATE_NAME);
	}

	public void setCP_CREATE_NAME(String cP_CREATE_NAME) {
		CP_CREATE_NAME = cP_CREATE_NAME;
	}

	public Date getCP_STOP_DATE() {
		return CP_STOP_DATE;
	}

	public void setCP_STOP_DATE(Date cP_STOP_DATE) {
		CP_STOP_DATE = cP_STOP_DATE;
	}

	public int getCP_DAYS_MIN() {
		return CP_DAYS_MIN;
	}

	public void setCP_DAYS_MIN(int cP_DAYS_MIN) {
		CP_DAYS_MIN = cP_DAYS_MIN;
	}

	public int getCP_DAYS_MAX() {
		return CP_DAYS_MAX;
	}

	public void setCP_DAYS_MAX(int cP_DAYS_MAX) {
		CP_DAYS_MAX = cP_DAYS_MAX;
	}

	public int getCP_DAYS() {
		return CP_DAYS;
	}

	public void setCP_DAYS(int cP_DAYS) {
		CP_DAYS = cP_DAYS;
	}

	public double getCP_FEE() {
		return CP_FEE;
	}

	public void setCP_FEE(double cP_FEE) {
		CP_FEE = cP_FEE;
	}

	public String getCP_START_USER() {
		return CommonFunction.FixNull(CP_START_USER);
	}

	public void setCP_START_USER(String cP_START_USER) {
		CP_START_USER = cP_START_USER;
	}

	public String getCP_STOP_USER() {
		return CommonFunction.FixNull(CP_STOP_USER);
	}

	public void setCP_STOP_USER(String cP_STOP_USER) {
		CP_STOP_USER = cP_STOP_USER;
	}

	public String getDEPT_CODE() {
		return CommonFunction.FixNull(DEPT_CODE);
	}

	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}

	public String getDEPT_NAME() {
		return CommonFunction.FixNull(DEPT_NAME);
	}

	public void setDEPT_NAME(String dEPT_NAME) {
		DEPT_NAME = dEPT_NAME;
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

	public String getINPUT_CODE_PY() {
		return CommonFunction.FixNull(INPUT_CODE_PY);
	}

	public void setINPUT_CODE_PY(String iNPUT_CODE_PY) {
		INPUT_CODE_PY = iNPUT_CODE_PY;
	}

	public String getINPUT_CODE_WB() {
		return CommonFunction.FixNull(INPUT_CODE_WB);
	}

	public void setINPUT_CODE_WB(String iNPUT_CODE_WB) {
		INPUT_CODE_WB = iNPUT_CODE_WB;
	}

	public double getHEALTH_CARE_QUOTA() {
		return HEALTH_CARE_QUOTA;
	}

	public void setHEALTH_CARE_QUOTA(double hEALTH_CARE_QUOTA) {
		HEALTH_CARE_QUOTA = hEALTH_CARE_QUOTA;
	}

	public Date getCP_START_DATE() {
		return CP_START_DATE;
	}

	public void setCP_START_DATE(Date cP_START_DATE) {
		CP_START_DATE = cP_START_DATE;
	}

	public static int m_strTargetCpId;

	/**
	 * 生成路径版本号 算法为最大版本号+1 2015-12-24 吴海龙
	 */
	public int GetNewVersion() {
		String _strSQL = "select max(cp_version)+1 new_cp_version\r\n"
				+ "from lcp_master\r\n" + "where cp_master_id =\r\n"
				+ "(select cp_master_id from lcp_master where cp_id = '"
				+ CP_ID + "')\r\n" + "";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			while (_rsData.next()) {
				int _nMaxVersion = _rsData.getInt("new_cp_version");

				return _nMaxVersion;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 生成新的cp_code 算法 cp_master_id + 版本号 2015-12-24 吴海龙
	 */
	public String GetNewCpCode() {
		return CP_MASTER_ID + "-" + GetNewVersion();
	}

	/**
	 * 获取新的cp_id 2015-12-24 吴海龙
	 */
	public int GetNewCpId() {
		String _strSQL = "select max(cp_id) + 1 new_cp_id from lcp_master";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			while (_rsData.next()) {
				int _nNewCpId = _rsData.getInt("new_cp_id");
				m_strTargetCpId = _nNewCpId;
				return _nNewCpId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 克隆lcp_master 数据 2015-12-24 吴海龙
	 */
	public LcpMaster GetLcpMaster() {
		String _strSQL = " select * from lcp_master where cp_id = '"
				+ m_strCpId + "'";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			LcpMaster _lcpmaster = new LcpMaster();
			while (_rsData.next()) {
				_lcpmaster.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
				_lcpmaster.setCP_CODE(_rsData.getString("CP_CODE"));
				_lcpmaster.setCP_ID(_rsData.getInt("CP_ID"));
				_lcpmaster.setCP_NAME(_rsData.getString("CP_NAME"));
				_lcpmaster.setCP_START_DATE(_rsData.getDate("CP_START_DATE"));
				_lcpmaster.setCP_MASTER_ID(_rsData.getInt("CP_MASTER_ID"));
				_lcpmaster.setCP_VERSION(_rsData.getInt("CP_VERSION"));
				_lcpmaster.setCP_STATUS(_rsData.getInt("CP_STATUS"));
				_lcpmaster.setCP_VERSION_DATE(_rsData
						.getDate("CP_VERSION_DATE"));
				_lcpmaster.setCP_CREATE_DATE(_rsData.getDate("CP_CREATE_DATE"));
				_lcpmaster.setCP_CREATE_ID(_rsData.getString("CP_CREATE_ID"));
				_lcpmaster.setCP_CREATE_NAME(_rsData
						.getString("CP_CREATE_NAME"));
				_lcpmaster.setCP_STOP_DATE(_rsData.getDate("CP_STOP_DATE"));
				_lcpmaster.setCP_DAYS_MIN(_rsData.getInt("CP_DAYS_MIN"));
				_lcpmaster.setCP_DAYS_MAX(_rsData.getInt("CP_DAYS_MAX"));
				_lcpmaster.setCP_DAYS(_rsData.getInt("CP_DAYS"));
				_lcpmaster.setCP_FEE(_rsData.getDouble("CP_FEE"));
				_lcpmaster.setCP_START_USER(_rsData.getString("CP_START_USER"));
				_lcpmaster.setCP_STOP_USER(_rsData.getString("CP_STOP_USER"));
				_lcpmaster.setDEPT_CODE(_rsData.getString("DEPT_CODE"));
				_lcpmaster.setDEPT_NAME(_rsData.getString("DEPT_NAME"));
				_lcpmaster.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
				_lcpmaster.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
				_lcpmaster.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
				_lcpmaster.setSYS_IS_DEL(_rsData.getInt("SYS_IS_DEL"));
				_lcpmaster.setSYS_LAST_UPDATE(_rsData
						.getDate("SYS_LAST_UPDATE"));
				_lcpmaster.setINPUT_CODE_PY(_rsData.getString("INPUT_CODE_PY"));
				_lcpmaster.setINPUT_CODE_WB(_rsData.getString("INPUT_CODE_WB"));
				_lcpmaster.setHEALTH_CARE_QUOTA(_rsData
						.getDouble("HEALTH_CARE_QUOTA"));
			}
			return _lcpmaster;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成新cp 2015-12-24 吴海龙
	 */
	public int CloneLcpMaster(LcpMaster p_objLcpMaster) throws SQLException {
		LcpMaster _objLcpMaster = p_objLcpMaster;
		String _strSQL = "insert into lcp_master\r\n"
				+ "(cp_code,\r\n"
				+ "hospital_id,\r\n"
				+ "cp_id,\r\n"
				+ "cp_name,\r\n"
				+ "cp_start_date,\r\n"
				+ "cp_master_id,\r\n"
				+ "cp_version,\r\n"
				+ "cp_status,\r\n"
				+ "cp_version_date,\r\n"
				+ "cp_create_date,\r\n"
				+ "cp_create_id,\r\n"
				+ "cp_create_name,\r\n"
				+ "cp_stop_date,\r\n"
				+ "cp_days_min,\r\n"
				+ "cp_days_max,\r\n"
				+ "cp_days,\r\n"
				+ "cp_fee,\r\n"
				+ "cp_start_user,\r\n"
				+ "cp_stop_user,\r\n"
				+ "dept_code,\r\n"
				+ "dept_name,\r\n"
				+ "verify_date,\r\n"
				+ "verify_code,\r\n"
				+ "verify_name,\r\n"
				+ "sys_is_del,\r\n"
				+ "sys_last_update,\r\n"
				+ "input_code_py,\r\n"
				+ "input_code_wb,\r\n"
				+ "health_care_quota)\r\n"
				+ "values\r\n"
				+ "('%s',%d,%d,'%s',sysdate,%d,%d,%d,sysdate,sysdate,%d,'%s',sysdate,%d,%d,%d,%f,'%s','%s','%s','%s',sysdate,%s,'%s',%d,sysdate,'%s','%s','%s')";
		_strSQL = String.format(
				_strSQL,
				_objLcpMaster.GetNewCpCode(),
				_objLcpMaster.getHOSPITAL_ID(),
				_objLcpMaster.GetNewCpId(),
				_objLcpMaster.getCP_NAME(),
				// --
				_objLcpMaster.getCP_MASTER_ID(),
				_objLcpMaster.GetNewVersion(),
				_objLcpMaster.getCP_STATUS(),
				// --
				_objLcpMaster.getCP_CREATE_ID(),
				_objLcpMaster.getCP_CREATE_NAME(),
				// --
				_objLcpMaster.getCP_DAYS_MIN(),
				_objLcpMaster.getCP_DAYS_MAX(),
				_objLcpMaster.getCP_DAYS(),
				_objLcpMaster.getCP_FEE(),
				// --
				_objLcpMaster.getCP_START_USER(),
				_objLcpMaster.getCP_STOP_USER(),
				_objLcpMaster.getDEPT_CODE(),
				_objLcpMaster.getDEPT_NAME(),
				// --
				_objLcpMaster.getVERIFY_CODE(),
				_objLcpMaster.getVERIFY_NAME(),
				_objLcpMaster.getSYS_IS_DEL(),
				// --
				_objLcpMaster.getINPUT_CODE_PY(),
				_objLcpMaster.getINPUT_CODE_WB(),
				_objLcpMaster.getHEALTH_CARE_QUOTA());
		System.out.println(_strSQL);
		int _n;
		_n = CommonFunction.ExecuteNonQuery(_strSQL);
		return _n;
	}

	@Override
	public List<Object> GetCpObjectList() {
		String _strSQL = " select * from lcp_master where cp_id = '"
				+ m_strCpId + "'";
		List<Object> _listLcpMaster = new ArrayList();
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {

			while (_rsData.next()) {
				LcpMaster _lcpmaster = new LcpMaster();
				_lcpmaster.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
				_lcpmaster.setCP_CODE(_rsData.getString("CP_CODE"));
				_lcpmaster.setCP_ID(_rsData.getInt("CP_ID"));
				_lcpmaster.setCP_NAME(_rsData.getString("CP_NAME"));
				_lcpmaster.setCP_START_DATE(_rsData.getDate("CP_START_DATE"));
				_lcpmaster.setCP_MASTER_ID(_rsData.getInt("CP_MASTER_ID"));
				_lcpmaster.setCP_VERSION(_rsData.getInt("CP_VERSION"));
				_lcpmaster.setCP_STATUS(_rsData.getInt("CP_STATUS"));
				_lcpmaster.setCP_VERSION_DATE(_rsData
						.getDate("CP_VERSION_DATE"));
				_lcpmaster.setCP_CREATE_DATE(_rsData.getDate("CP_CREATE_DATE"));
				_lcpmaster.setCP_CREATE_ID(_rsData.getString("CP_CREATE_ID"));
				_lcpmaster.setCP_CREATE_NAME(_rsData
						.getString("CP_CREATE_NAME"));
				_lcpmaster.setCP_STOP_DATE(_rsData.getDate("CP_STOP_DATE"));
				_lcpmaster.setCP_DAYS_MIN(_rsData.getInt("CP_DAYS_MIN"));
				_lcpmaster.setCP_DAYS_MAX(_rsData.getInt("CP_DAYS_MAX"));
				_lcpmaster.setCP_DAYS(_rsData.getInt("CP_DAYS"));
				_lcpmaster.setCP_FEE(_rsData.getDouble("CP_FEE"));
				_lcpmaster.setCP_START_USER(_rsData.getString("CP_START_USER"));
				_lcpmaster.setCP_STOP_USER(_rsData.getString("CP_STOP_USER"));
				_lcpmaster.setDEPT_CODE(_rsData.getString("DEPT_CODE"));
				_lcpmaster.setDEPT_NAME(_rsData.getString("DEPT_NAME"));
				_lcpmaster.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
				_lcpmaster.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
				_lcpmaster.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
				_lcpmaster.setSYS_IS_DEL(_rsData.getInt("SYS_IS_DEL"));
				_lcpmaster.setSYS_LAST_UPDATE(_rsData
						.getDate("SYS_LAST_UPDATE"));
				_lcpmaster.setINPUT_CODE_PY(_rsData.getString("INPUT_CODE_PY"));
				_lcpmaster.setINPUT_CODE_WB(_rsData.getString("INPUT_CODE_WB"));
				_lcpmaster.setHEALTH_CARE_QUOTA(_rsData
						.getDouble("HEALTH_CARE_QUOTA"));
				_listLcpMaster.add(_lcpmaster);
			}
			return _listLcpMaster;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int CloneCpObject(Object _obj) {
		LcpMaster _objLcpMaster = (LcpMaster) _obj;
		String _strSQL = "insert into lcp_master\r\n" + "(cp_code,\r\n"
				+ "hospital_id,\r\n" + "cp_id,\r\n" + "cp_name,\r\n"
				+ "cp_start_date,\r\n" + "cp_master_id,\r\n"
				+ "cp_version,\r\n" + "cp_status,\r\n" + "cp_version_date,\r\n"
				+ "cp_create_date,\r\n" + "cp_create_id,\r\n"
				+ "cp_create_name,\r\n" + "cp_stop_date,\r\n"
				+ "cp_days_min,\r\n" + "cp_days_max,\r\n" + "cp_days,\r\n"
				+ "cp_fee,\r\n" + "cp_start_user,\r\n" + "cp_stop_user,\r\n"
				+ "dept_code,\r\n" + "dept_name,\r\n" + "verify_date,\r\n"
				+ "verify_code,\r\n" + "verify_name,\r\n" + "sys_is_del,\r\n"
				+ "sys_last_update,\r\n" + "input_code_py,\r\n"
				+ "input_code_wb,\r\n" + "health_care_quota)\r\n"
				+ "values\r\n" + "('"
				+ _objLcpMaster.GetNewCpCode()
				+ "',"
				+ _objLcpMaster.getHOSPITAL_ID()
				+ ","
				+ _objLcpMaster.GetNewCpId()
				+ ","
				+ "'"
				+ _objLcpMaster.getCP_NAME()
				+ "',sysdate,"
				+ _objLcpMaster.getCP_MASTER_ID()
				+ ","
				+ _objLcpMaster.GetNewVersion()
				+ ","
				+ _objLcpMaster.getCP_STATUS()
				+ ",sysdate,sysdate,'"
				+ _objLcpMaster.getCP_CREATE_ID()
				+ "','"
				+ _objLcpMaster.getCP_CREATE_NAME()
				+ "',"
				+ "sysdate,"
				+ _objLcpMaster.getCP_DAYS_MIN()
				+ ","
				+ _objLcpMaster.getCP_DAYS_MAX()
				+ ","
				+ _objLcpMaster.getCP_DAYS()
				+ ","
				+ _objLcpMaster.getCP_FEE()
				+ ",'"
				+ _objLcpMaster.getCP_START_USER()
				+ "',"
				+ "'"
				+ _objLcpMaster.getCP_STOP_USER()
				+ "','"
				+ _objLcpMaster.getDEPT_CODE()
				+ "','"
				+ _objLcpMaster.getDEPT_NAME()
				+ "',"
				+ "sysdate,'"
				+ _objLcpMaster.getVERIFY_CODE()
				+ "','"
				+ _objLcpMaster.getVERIFY_NAME()
				+ "',"
				+ _objLcpMaster.getSYS_IS_DEL()
				+ ",sysdate,'"
				+ _objLcpMaster.getINPUT_CODE_PY()
				+ "',"
				+ "'"
				+ _objLcpMaster.getINPUT_CODE_WB()
				+ "','"
				+ _objLcpMaster.getHEALTH_CARE_QUOTA() + "')";

		System.out.println(_strSQL);
		int _n = CommonFunction.ExecuteNonQuery(_strSQL);
		return _n;

	}

	@Override
	public void CloneCpObjectList(List<Object> _listobj) {
		for (Object object : _listobj) {
			CloneCpObject(object);
		}

	}

}
