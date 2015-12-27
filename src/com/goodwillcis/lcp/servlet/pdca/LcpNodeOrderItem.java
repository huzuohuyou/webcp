package com.goodwillcis.lcp.servlet.pdca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LcpNodeOrderItem implements ICloneCp {
	private int HOSPITAL_ID;
	private int CP_ID;
	private int CP_NODE_ID;
	private int CP_NODE_ORDER_ID;
	private String CP_NODE_ORDER_ITEM_ID;
	private String CP_NODE_ORDER_TEXT;
	private String ORDER_NO;
	private String ORDER_TYPE;
	private int NEED_ITEM;
	private Date VERIFY_DATE;
	private String VERIFY_CODE;
	private String VERIFY_NAME;
	private int SYS_IS_DEL;
	private Date SYS_LAST_UPDATE;

	private int AUTO_ITEM;
	private String ORDER_TYPE_NAME;
	private String ORDER_KIND;
	private String MEASURE;
	private String FREQUENCY;
	private String WAY;
	private double DOSAGE;
	private String DOSAGE_UNITS;
	private String ADMINISTRATION;
	private int DURATION;
	private String DURATION_UNITS;
	private int FREQ_COUNTER;
	private int FREQ_INTERVAL;
	private String FREQ_INTERVAL_UNIT;
	private String FREQ_DETAIL;
	private String ORDERING_DEPT;
	private String DOCTOR;
	private String NURSE;
	private String ORDER_STATUS;
	private Date PROCESSING_DATE_TIME;
	private String BILLING_ATTR;
	private String ORDER_PRINT_INDICATOR;
	private Date START_DATE_TIME;
	private String DRUG_BILLING_ATTR;
	private String ORDER_INSURANCE_TYPE;
	private String LOCAL_ORDER_NO;
	private String LOCAL_ORDER_TEXT;
	private int ORDER_ITEM_SET_ID;
	private String ORDER_CLASS;
	private int REPEAT_INDICATOR;
	private int IS_ANTIBIOTIC;
	private String MEASURE_UNITS;
	private int CP_NODE_CLASS_ID;
	private int EFFECT_FLAG;
	private String SPECIFICATION;
	private int UNIT_ID;
	private String MARK;
	private int DEFAULT_ITEM;
	private String DRUG_ID;

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

	public String getCP_NODE_ORDER_ITEM_ID() {
		return CommonFunction.FixNull(CP_NODE_ORDER_ITEM_ID);
	}

	public void setCP_NODE_ORDER_ITEM_ID(String cP_NODE_ORDER_ITEM_ID) {
		CP_NODE_ORDER_ITEM_ID = cP_NODE_ORDER_ITEM_ID;
	}

	public String getCP_NODE_ORDER_TEXT() {
		return CommonFunction.FixNull(CP_NODE_ORDER_TEXT);
	}

	public void setCP_NODE_ORDER_TEXT(String cP_NODE_ORDER_TEXT) {
		CP_NODE_ORDER_TEXT = cP_NODE_ORDER_TEXT;
	}

	public String getORDER_NO() {
		return CommonFunction.FixNull(ORDER_NO);
	}

	public void setORDER_NO(String oRDER_NO) {
		ORDER_NO = oRDER_NO;
	}

	public String getORDER_TYPE() {
		return CommonFunction.FixNull(ORDER_TYPE);
	}

	public void setORDER_TYPE(String oRDER_TYPE) {
		ORDER_TYPE = oRDER_TYPE;
	}

	public int getNEED_ITEM() {
		return NEED_ITEM;
	}

	public void setNEED_ITEM(int nEED_ITEM) {
		NEED_ITEM = nEED_ITEM;
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

	public int getAUTO_ITEM() {
		return AUTO_ITEM;
	}

	public void setAUTO_ITEM(int aUTO_ITEM) {
		AUTO_ITEM = aUTO_ITEM;
	}

	public String getORDER_TYPE_NAME() {
		return CommonFunction.FixNull(ORDER_TYPE_NAME);
	}

	public void setORDER_TYPE_NAME(String oRDER_TYPE_NAME) {
		ORDER_TYPE_NAME = oRDER_TYPE_NAME;
	}

	public String getORDER_KIND() {
		return CommonFunction.FixNull(ORDER_KIND);
	}

	public void setORDER_KIND(String oRDER_KIND) {
		ORDER_KIND = oRDER_KIND;
	}

	public String getMEASURE() {
		return CommonFunction.FixNull(MEASURE);
	}

	public void setMEASURE(String mEASURE) {
		MEASURE = mEASURE;
	}

	public String getFREQUENCY() {
		return CommonFunction.FixNull(FREQUENCY);
	}

	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}

	public String getWAY() {
		return CommonFunction.FixNull(WAY);
	}

	public void setWAY(String wAY) {
		WAY = wAY;
	}

	public double getDOSAGE() {
		return DOSAGE;
	}

	public void setDOSAGE(double dOSAGE) {
		DOSAGE = dOSAGE;
	}

	public String getDOSAGE_UNITS() {
		return CommonFunction.FixNull(DOSAGE_UNITS);
	}

	public void setDOSAGE_UNITS(String dOSAGE_UNITS) {
		DOSAGE_UNITS = dOSAGE_UNITS;
	}

	public String getADMINISTRATION() {
		return CommonFunction.FixNull(ADMINISTRATION);
	}

	public void setADMINISTRATION(String aDMINISTRATION) {
		ADMINISTRATION = aDMINISTRATION;
	}

	public int getDURATION() {
		return DURATION;
	}

	public void setDURATION(int dURATION) {
		DURATION = dURATION;
	}

	public String getDURATION_UNITS() {
		return CommonFunction.FixNull(DURATION_UNITS);
	}

	public void setDURATION_UNITS(String dURATION_UNITS) {
		DURATION_UNITS = dURATION_UNITS;
	}

	public int getFREQ_COUNTER() {
		return FREQ_COUNTER;
	}

	public void setFREQ_COUNTER(int fREQ_COUNTER) {
		FREQ_COUNTER = fREQ_COUNTER;
	}

	public int getFREQ_INTERVAL() {
		return FREQ_INTERVAL;
	}

	public void setFREQ_INTERVAL(int fREQ_INTERVAL) {
		FREQ_INTERVAL = fREQ_INTERVAL;
	}

	public String getFREQ_INTERVAL_UNIT() {
		return CommonFunction.FixNull(FREQ_INTERVAL_UNIT);
	}

	public void setFREQ_INTERVAL_UNIT(String fREQ_INTERVAL_UNIT) {
		FREQ_INTERVAL_UNIT = fREQ_INTERVAL_UNIT;
	}

	public String getFREQ_DETAIL() {
		return CommonFunction.FixNull(FREQ_DETAIL);
	}

	public void setFREQ_DETAIL(String fREQ_DETAIL) {
		FREQ_DETAIL = fREQ_DETAIL;
	}

	public String getORDERING_DEPT() {
		return CommonFunction.FixNull(ORDERING_DEPT);
	}

	public void setORDERING_DEPT(String oRDERING_DEPT) {
		ORDERING_DEPT = oRDERING_DEPT;
	}

	public String getDOCTOR() {
		return CommonFunction.FixNull(DOCTOR);
	}

	public void setDOCTOR(String dOCTOR) {
		DOCTOR = dOCTOR;
	}

	public String getNURSE() {
		return CommonFunction.FixNull(NURSE);
	}

	public void setNURSE(String nURSE) {
		NURSE = nURSE;
	}

	public String getORDER_STATUS() {
		return CommonFunction.FixNull(ORDER_STATUS);
	}

	public void setORDER_STATUS(String oRDER_STATUS) {
		ORDER_STATUS = oRDER_STATUS;
	}

	public Date getPROCESSING_DATE_TIME() {
		return PROCESSING_DATE_TIME;
	}

	public void setPROCESSING_DATE_TIME(Date pROCESSING_DATE_TIME) {
		PROCESSING_DATE_TIME = pROCESSING_DATE_TIME;
	}

	public String getBILLING_ATTR() {
		return CommonFunction.FixNull(BILLING_ATTR);
	}

	public void setBILLING_ATTR(String bILLING_ATTR) {
		BILLING_ATTR = bILLING_ATTR;
	}

	public String getORDER_PRINT_INDICATOR() {
		return CommonFunction.FixNull(ORDER_PRINT_INDICATOR);
	}

	public void setORDER_PRINT_INDICATOR(String oRDER_PRINT_INDICATOR) {
		ORDER_PRINT_INDICATOR = oRDER_PRINT_INDICATOR;
	}

	public Date getSTART_DATE_TIME() {
		return START_DATE_TIME;
	}

	public void setSTART_DATE_TIME(Date sTART_DATE_TIME) {
		START_DATE_TIME = sTART_DATE_TIME;
	}

	public String getDRUG_BILLING_ATTR() {
		return CommonFunction.FixNull(DRUG_BILLING_ATTR);
	}

	public void setDRUG_BILLING_ATTR(String dRUG_BILLING_ATTR) {
		DRUG_BILLING_ATTR = dRUG_BILLING_ATTR;
	}

	public String getORDER_INSURANCE_TYPE() {
		return CommonFunction.FixNull(ORDER_INSURANCE_TYPE);
	}

	public void setORDER_INSURANCE_TYPE(String oRDER_INSURANCE_TYPE) {
		ORDER_INSURANCE_TYPE = oRDER_INSURANCE_TYPE;
	}

	public String getLOCAL_ORDER_NO() {
		return CommonFunction.FixNull(LOCAL_ORDER_NO);
	}

	public void setLOCAL_ORDER_NO(String lOCAL_ORDER_NO) {
		LOCAL_ORDER_NO = lOCAL_ORDER_NO;
	}

	public String getLOCAL_ORDER_TEXT() {
		return CommonFunction.FixNull(LOCAL_ORDER_TEXT);
	}

	public void setLOCAL_ORDER_TEXT(String lOCAL_ORDER_TEXT) {
		LOCAL_ORDER_TEXT = lOCAL_ORDER_TEXT;
	}

	public int getORDER_ITEM_SET_ID() {
		return ORDER_ITEM_SET_ID;
	}

	public void setORDER_ITEM_SET_ID(int oRDER_ITEM_SET_ID) {
		ORDER_ITEM_SET_ID = oRDER_ITEM_SET_ID;
	}

	public String getORDER_CLASS() {
		return CommonFunction.FixNull(ORDER_CLASS);
	}

	public void setORDER_CLASS(String oRDER_CLASS) {
		ORDER_CLASS = oRDER_CLASS;
	}

	public int getREPEAT_INDICATOR() {
		return REPEAT_INDICATOR;
	}

	public void setREPEAT_INDICATOR(int rEPEAT_INDICATOR) {
		REPEAT_INDICATOR = rEPEAT_INDICATOR;
	}

	public int getIS_ANTIBIOTIC() {
		return IS_ANTIBIOTIC;
	}

	public void setIS_ANTIBIOTIC(int iS_ANTIBIOTIC) {
		IS_ANTIBIOTIC = iS_ANTIBIOTIC;
	}

	public String getMEASURE_UNITS() {
		return CommonFunction.FixNull(MEASURE_UNITS);
	}

	public void setMEASURE_UNITS(String mEASURE_UNITS) {
		MEASURE_UNITS = mEASURE_UNITS;
	}

	public int getCP_NODE_CLASS_ID() {
		return CP_NODE_CLASS_ID;
	}

	public void setCP_NODE_CLASS_ID(int cP_NODE_CLASS_ID) {
		CP_NODE_CLASS_ID = cP_NODE_CLASS_ID;
	}

	public int getEFFECT_FLAG() {
		return EFFECT_FLAG;
	}

	public void setEFFECT_FLAG(int eFFECT_FLAG) {
		EFFECT_FLAG = eFFECT_FLAG;
	}

	public String getSPECIFICATION() {
		return CommonFunction.FixNull(SPECIFICATION);
	}

	public void setSPECIFICATION(String sPECIFICATION) {
		SPECIFICATION = sPECIFICATION;
	}

	public int getUNIT_ID() {
		return UNIT_ID;
	}

	public void setUNIT_ID(int uNIT_ID) {
		UNIT_ID = uNIT_ID;
	}

	public String getMARK() {
		return CommonFunction.FixNull(MARK);
	}

	public void setMARK(String mARK) {
		MARK = mARK;
	}

	public int getDEFAULT_ITEM() {
		return DEFAULT_ITEM;
	}

	public void setDEFAULT_ITEM(int dEFAULT_ITEM) {
		DEFAULT_ITEM = dEFAULT_ITEM;
	}

	public String getDRUG_ID() {
		return CommonFunction.FixNull(DRUG_ID);
	}

	public void setDRUG_ID(String dRUG_ID) {
		DRUG_ID = dRUG_ID;
	}

	public LcpNodeOrderItem() {
	}

	private String m_strCpId;

	public LcpNodeOrderItem(String p_strCpId) {
		m_strCpId = p_strCpId;
	}
	
	LcpMaster m_strLcpMaster =null;
	public LcpNodeOrderItem(LcpMaster p_strObj) {
		m_strLcpMaster=p_strObj;
	}

	@Override
	public List<Object> GetCpObjectList() {
		List<Object> _listObj = new ArrayList();
		String _strSQL = " select * from LCP_NODE_ORDER_ITEM where cp_id = '"
				+ m_strCpId + "'";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			while (_rsData.next()) {
				LcpNodeOrderItem _lcpobj = new LcpNodeOrderItem();
				_lcpobj.setHOSPITAL_ID(_rsData.getInt("HOSPITAL_ID"));
				_lcpobj.setCP_ID(_rsData.getInt("CP_ID"));
				_lcpobj.setCP_NODE_ID(_rsData.getInt("CP_NODE_ID"));
				_lcpobj.setCP_NODE_ORDER_ID(_rsData.getInt("CP_NODE_ORDER_ID"));
				_lcpobj.setCP_NODE_ORDER_ITEM_ID(_rsData
						.getString("CP_NODE_ORDER_ITEM_ID"));
				_lcpobj.setCP_NODE_ORDER_TEXT(_rsData
						.getString("CP_NODE_ORDER_TEXT"));
				_lcpobj.setORDER_NO(_rsData.getString("ORDER_NO"));
				_lcpobj.setORDER_TYPE(_rsData.getString("ORDER_TYPE"));
				_lcpobj.setNEED_ITEM(_rsData.getInt("NEED_ITEM"));
				_lcpobj.setVERIFY_DATE(_rsData.getDate("VERIFY_DATE"));
				_lcpobj.setVERIFY_CODE(_rsData.getString("VERIFY_CODE"));
				_lcpobj.setVERIFY_NAME(_rsData.getString("VERIFY_NAME"));
				_lcpobj.setSYS_IS_DEL(_rsData.getInt("SYS_IS_DEL"));
				_lcpobj.setSYS_LAST_UPDATE(_rsData.getDate("SYS_LAST_UPDATE"));

				_lcpobj.setAUTO_ITEM(_rsData.getInt("AUTO_ITEM"));
				_lcpobj.setORDER_TYPE_NAME(_rsData.getString("ORDER_TYPE_NAME"));
				_lcpobj.setORDER_KIND(_rsData.getString("ORDER_KIND"));
				_lcpobj.setMEASURE(_rsData.getString("MEASURE"));
				_lcpobj.setFREQUENCY(_rsData.getString("FREQUENCY"));
				_lcpobj.setWAY(_rsData.getString("WAY"));
				_lcpobj.setDOSAGE(_rsData.getInt("DOSAGE"));
				_lcpobj.setDOSAGE_UNITS(_rsData.getString("DOSAGE_UNITS"));
				_lcpobj.setADMINISTRATION(_rsData.getString("ADMINISTRATION"));
				_lcpobj.setDURATION(_rsData.getInt("DURATION"));
				_lcpobj.setDURATION_UNITS(_rsData.getString("DURATION_UNITS"));
				_lcpobj.setFREQ_COUNTER(_rsData.getInt("FREQ_COUNTER"));
				_lcpobj.setFREQ_INTERVAL(_rsData.getInt("FREQ_INTERVAL"));
				_lcpobj.setFREQ_INTERVAL_UNIT(_rsData
						.getString("FREQ_INTERVAL_UNIT"));
				_lcpobj.setFREQ_DETAIL(_rsData.getString("FREQ_DETAIL"));
				_lcpobj.setORDERING_DEPT(_rsData.getString("ORDERING_DEPT"));
				_lcpobj.setDOCTOR(_rsData.getString("DOCTOR"));
				_lcpobj.setNURSE(_rsData.getString("NURSE"));
				_lcpobj.setORDER_STATUS(_rsData.getString("ORDER_STATUS"));
				_lcpobj.setPROCESSING_DATE_TIME(_rsData
						.getDate("PROCESSING_DATE_TIME"));
				_lcpobj.setBILLING_ATTR(_rsData.getString("BILLING_ATTR"));
				_lcpobj.setORDER_PRINT_INDICATOR(_rsData
						.getString("ORDER_PRINT_INDICATOR"));
				_lcpobj.setSTART_DATE_TIME(_rsData.getDate("START_DATE_TIME"));
				_lcpobj.setDRUG_BILLING_ATTR(_rsData
						.getString("DRUG_BILLING_ATTR"));
				_lcpobj.setORDER_INSURANCE_TYPE(_rsData
						.getString("ORDER_INSURANCE_TYPE"));
				_lcpobj.setLOCAL_ORDER_NO(_rsData.getString("LOCAL_ORDER_NO"));
				_lcpobj.setLOCAL_ORDER_TEXT(_rsData
						.getString("LOCAL_ORDER_TEXT"));
				_lcpobj.setORDER_ITEM_SET_ID(_rsData
						.getInt("ORDER_ITEM_SET_ID"));
				_lcpobj.setORDER_CLASS(_rsData.getString("ORDER_CLASS"));
				_lcpobj.setREPEAT_INDICATOR(_rsData.getInt("REPEAT_INDICATOR"));
				_lcpobj.setIS_ANTIBIOTIC(_rsData.getInt("IS_ANTIBIOTIC"));
				_lcpobj.setMEASURE_UNITS(_rsData.getString("MEASURE_UNITS"));
				_lcpobj.setCP_NODE_CLASS_ID(_rsData.getInt("CP_NODE_CLASS_ID"));
				_lcpobj.setEFFECT_FLAG(_rsData.getInt("EFFECT_FLAG"));
				_lcpobj.setSPECIFICATION(_rsData.getString("SPECIFICATION"));
				_lcpobj.setUNIT_ID(_rsData.getInt("UNIT_ID"));
				_lcpobj.setMARK(_rsData.getString("MARK"));
				_lcpobj.setDEFAULT_ITEM(_rsData.getInt("DEFAULT_ITEM"));
				_lcpobj.setDRUG_ID(_rsData.getString("DRUG_ID"));
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
		LcpNodeOrderItem _lcpobj = (LcpNodeOrderItem) _obj;
		String _strSQL = "insert into LCP_NODE_ORDER_ITEM\r\n"
				+ "(hospital_id,\r\n"+ "cp_id,\r\n"+ "cp_node_id,\r\n"+ "cp_node_order_id,\r\n"
				+ "cp_node_order_item_id,\r\n"+ "cp_node_order_text,\r\n"+ "order_no,\r\n"+ "order_type,\r\n"
				+ "need_item,\r\n"+ "VERIFY_DATE,\r\n"+ "verify_code,\r\n"+ "verify_name,\r\n"
				+ "sys_is_del,\r\n"+ "SYS_LAST_UPDATE,\r\n"+ "auto_item,\r\n"+ "order_type_name,\r\n"
				+ "order_kind,\r\n"+ "measure,\r\n"+ "frequency,\r\n"+ "way,\r\n"
				+ "dosage,\r\n"+ "dosage_units,\r\n"+ "administration,\r\n"+ "duration,\r\n"
				+ "duration_units,\r\n"+ "freq_counter,\r\n"+ "freq_interval,\r\n"+ "freq_interval_unit,\r\n"
				+ "freq_detail,\r\n"+ "ordering_dept,\r\n"+ "doctor,\r\n"+ "nurse,\r\n"
				+ "order_status,\r\n"+ "PROCESSING_DATE_TIME,\r\n"+ "billing_attr,\r\n"+ "order_print_indicator,\r\n"
				+ "START_DATE_TIME,\r\n"+ "drug_billing_attr,\r\n"+ "order_insurance_type,\r\n"+ "local_order_no,\r\n"
				+ "local_order_text,\r\n"+ "order_item_set_id,\r\n"+ "order_class,\r\n"+ "repeat_indicator,\r\n"
				+ "is_antibiotic,\r\n"+ "measure_units,\r\n"+ "cp_node_class_id,\r\n"+ "effect_flag,\r\n"
				+ "specification,\r\n"+ "unit_id,\r\n"+ "mark,\r\n"+ "default_item,\r\n"+ "drug_id)\r\n"
				+ "values(\r\n"
				+_lcpobj.getHOSPITAL_ID()+","+LcpMaster.m_strTargetCpId+","+_lcpobj.getCP_NODE_ID()+","+_lcpobj.getCP_NODE_ORDER_ID()+","+
				_lcpobj.getCP_NODE_ORDER_ITEM_ID()+",'"+_lcpobj.getCP_NODE_ORDER_TEXT()+"','"+_lcpobj.getORDER_NO()+"','"+_lcpobj.getORDER_TYPE()+"',"+
				_lcpobj.getNEED_ITEM()+",sysdate,'"+_lcpobj.getVERIFY_CODE()+"','"+_lcpobj.getVERIFY_NAME()+"',"+
				_lcpobj.getSYS_IS_DEL()+",sysdate,"+_lcpobj.getAUTO_ITEM()+",'"+_lcpobj.getORDER_TYPE_NAME()+"','"+
				_lcpobj.getORDER_KIND()+"','"+_lcpobj.getMEASURE()+"','"+_lcpobj.getFREQUENCY()+"','"+_lcpobj.getWAY()+"',"+
				_lcpobj.getDOSAGE()+",'"+_lcpobj.getDOSAGE_UNITS()+"','"+_lcpobj.getADMINISTRATION()+"',"+_lcpobj.getDURATION()+",'"+
				_lcpobj.getDURATION_UNITS()+"',"+_lcpobj.getFREQ_COUNTER()+","+_lcpobj.getFREQ_INTERVAL()+",'"+_lcpobj.getFREQ_INTERVAL_UNIT()+"','"+
				_lcpobj.getFREQ_DETAIL()+"','"+_lcpobj.getORDERING_DEPT()+"','"+_lcpobj.getDOCTOR()+"','"+_lcpobj.getNURSE()+"','"+
				_lcpobj.getORDER_STATUS()+"',sysdate,'"+_lcpobj.getBILLING_ATTR()+"','"+_lcpobj.getORDER_PRINT_INDICATOR()+"',"+
				"sysdate,'"+_lcpobj.getDRUG_BILLING_ATTR()+"','"+_lcpobj.getORDER_INSURANCE_TYPE()+"','"+ _lcpobj.getLOCAL_ORDER_NO()+"','"+
				_lcpobj.getLOCAL_ORDER_TEXT()+"',"+_lcpobj.getORDER_ITEM_SET_ID()+",'"+_lcpobj.getORDER_CLASS()+"',"+ _lcpobj.getREPEAT_INDICATOR()+","+
				_lcpobj.getIS_ANTIBIOTIC()+",'"+_lcpobj.getMEASURE_UNITS()+"',"+_lcpobj.getCP_NODE_CLASS_ID()+","+ _lcpobj.getEFFECT_FLAG()+",'"+
				_lcpobj.getSPECIFICATION()+"',"+_lcpobj.getUNIT_ID()+",'"+_lcpobj.getMARK()+"',"+ _lcpobj.getDEFAULT_ITEM()+",'"+_lcpobj.getDRUG_ID()+"')";
		System.out.println(_strSQL);
		try {
			int _n = CommonFunction.ExecuteNonQuery(_strSQL);
			return _n;
		} catch (Exception e) {
			System.out.print(e.toString());
			return -1;
		}
	}

	@Override
	public void CloneCpObjectList(List<Object> _listobj) throws SQLException {
		for (Object object : _listobj) {
			CloneCpObject(object);
		}

	}

}
