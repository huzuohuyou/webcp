package com.goodwillcis.lcp.servlet.pdca;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InAndOutCompare {

	public InAndOutCompare() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * 获取串入参数的路径信息
	 * 2015-12-25
	 * 吴海龙 
	 */
	public String GetUsingCpInfo(String p_strCurrentCpId) {
		String _strSQL = "select '路径内' kind, cp_master_id,cp_id,cp_name,cp_code,\r\n" + 
				"round((sum(hzqk)*100/count(*)),2) cp_hzl,\r\n" + 
				"nvl(round(avg(zyr),2),0) cp_pjzyr,\r\n" + 
				"nvl(round(avg(zyf),2),0) cp_pjzyf \r\n" + 
				"from( select cp_master_id,a.cp_status,a.cp_code,a.cp_id,a.cp_name,c.patient_no,\r\n" + 
				"(case b.TREAT_EFFECT when 1then 1 when 2 then 1 else 0 end)hzqk,\r\n" + 
				"round(B.DISCHARGE_DATE_TIME- B.ADMISSION_DATE_TIME,2) zyr,\r\n" + 
				"b.fee_total zyf\r\n" + 
				"from (select cp_master_id,cp_id, cp_status, cp_name, cp_code\r\n" + 
				"from lcp_master\r\n" + 
				"where cp_id = '"+p_strCurrentCpId+"') a\r\n" + 
				"left outer join (select patient_no, cp_id\r\n" + 
				"from LCP_PATIENT_VISIT t\r\n" + 
				"where t.cp_state like '%1'\r\n" + 
				"group by CP_ID, PATIENT_NO) c\r\n" + 
				"on a.cp_id = c.cp_id\r\n" + 
				"left outer join (select b1.TREAT_EFFECT,\r\n" + 
				"b1.PATIENT_NO,\r\n" + 
				"b1.DISCHARGE_DATE_TIME,\r\n" + 
				"b1.ADMISSION_DATE_TIME,\r\n" + 
				"b2.FEE_TOTAL\r\n" + 
				"from LCP_PATIENT_FIRSTPAGE b1, LCP_PATIENT_FEE b2\r\n" + 
				"where b1.patient_no = b2.patient_no) b\r\n" + 
				"on c.patient_NO = B.PATIENT_NO ) group by cp_master_id,cp_id,cp_name,cp_code,cp_status \r\n" + 
				"union \r\n" + 
				"select '非路径' kind, cp_master_id,cp_id,cp_name,cp_code,\r\n" + 
				"round((sum(hzqk)*100/count(*)),2) cp_hzl,\r\n" + 
				"nvl(round(avg(zyr),2),0) cp_pjzyr,\r\n" + 
				"nvl(round(avg(zyf),2),0) cp_pjzyf \r\n" + 
				"from( select cp_master_id,a.cp_status,a.cp_code,a.cp_id,a.cp_name,c.patient_no,\r\n" + 
				"(case b.TREAT_EFFECT when 1then 1 when 2 then 1 else 0 end)hzqk,\r\n" + 
				"round(B.DISCHARGE_DATE_TIME- B.ADMISSION_DATE_TIME,2) zyr,\r\n" + 
				"b.fee_total zyf\r\n" + 
				"from (select cp_master_id,cp_id, cp_status, cp_name, cp_code\r\n" + 
				"from lcp_master\r\n" + 
				"where cp_id = '"+p_strCurrentCpId+"') a\r\n" + 
				"left outer join (select patient_no, CONFORM_CP_ID cp_id\r\n" + 
				"from LCP_PATIENT_VISIT t\r\n" + 
				"where t.cp_state = 0\r\n" + 
				"and CONFORM_CP_ID != 0\r\n" + 
				"group by CONFORM_CP_ID, PATIENT_NO) c\r\n" + 
				"on a.cp_id = c.cp_id\r\n" + 
				"left outer join (select b1.TREAT_EFFECT,\r\n" + 
				"b1.PATIENT_NO,\r\n" + 
				"b1.DISCHARGE_DATE_TIME,\r\n" + 
				"b1.ADMISSION_DATE_TIME,\r\n" + 
				"b2.FEE_TOTAL\r\n" + 
				"from LCP_PATIENT_FIRSTPAGE b1, LCP_PATIENT_FEE b2\r\n" + 
				"where b1.patient_no = b2.patient_no) b\r\n" + 
				"on c.patient_NO = B.PATIENT_NO ) group by cp_master_id,cp_id,cp_name,cp_code,cp_status ";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			boolean _boolHasData = false;
			String _strJson = "{\"cp_cp\":[";
			while (_rsData.next()) {
				_boolHasData=true;
				String _strKind = _rsData.getString("kind");
				String _strCpId = _rsData.getString("cp_id");
				String _strCpName = _rsData.getString("cp_name");
				String _strCpCode = _rsData.getString("cp_code");
				Double _nCphzl = _rsData.getDouble("cp_hzl");
				Double _nCppjzyr = _rsData.getDouble("cp_pjzyr");
				Double _nCppjzyf = _rsData.getDouble("cp_pjzyf");
				String _strCpStatus = "0";
				_strJson += "{" +
							 "\"kind\":\"" + _strKind + "\"," +
							 "\"cp_id\":\"" + _strCpId + "\"," +
							 "\"cp_name\":\""+ _strCpName + "\"," +
							 "\"cp_code\":\"" + _strCpCode+ "\"," +
							 "\"cp_hzl\":" + _nCphzl + "," +
							 "\"cp_pjzyr\":"+ _nCppjzyr + "," +
							 "\"cp_pjzyf\":" + _nCppjzyf+ "," +
							 "\"cp_status\":\"" + _strCpStatus + "\"" +
							 "},";
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
