package com.goodwillcis.lcp.servlet.pdca;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 路径版本间对比
 * @author wuhailong
 * 2015-12-26
 */
public class CompareCpVersions {

	/**
	 * 获取前四条临床路径
	 * 2015-12-22
	 * 吴海龙 
	 */
	public String GetTopFourCps(String p_strCpId){
		String _strCurrentCpId =p_strCpId;// request.getParameter("master_id");
		String _strSQL = "select * from (select cp_master_id,cp_id,cp_name,cp_code,cp_status,\r\n" + 
				"(case cp_status \r\n" + 
				"when 0 then '启用' \r\n" + 
				"when 1 then '停用' \r\n" + 
				"when 2 then '等待审核' \r\n" + 
				"when 3 then '已退回' \r\n" + 
				"when 4 then '隐藏' \r\n" + 
				"else '其他' end) cp_status_name ,\r\n" + 
				"(case cp_id \r\n" + 
				"when "+_strCurrentCpId+" then 0 \r\n" + 
				"else 1 end) cp_order ,\r\n" + 
				"round((sum(hzqk)*100/count(*)),2) cp_hzl,\r\n" + 
				"nvl(round(avg(zyr),2),0) cp_pjzyr,\r\n" + 
				"nvl(round(avg(zyf),2),0) cp_pjzyf ,cp_create_date\r\n" + 
				"from( select a.cp_master_id,a.cp_status,a.cp_code,a.cp_id,a.cp_name,c.patient_no,cp_create_date,\r\n" + 
				"(case b.TREAT_EFFECT when 1then 1 when 2 then 1 else 0 end)hzqk,\r\n" + 
				"round(B.DISCHARGE_DATE_TIME- B.ADMISSION_DATE_TIME,2) zyr,\r\n" + 
				"b.fee_total zyf\r\n" + 
				"from (select cp_master_id,cp_id, cp_status, cp_name, cp_code,cp_create_date\r\n" + 
				"from lcp_master\r\n" + 
				"where cp_master_id = (select cp_master_id from lcp_master where cp_id ='"+_strCurrentCpId+"')) a\r\n" + 
				"left outer join (select CP_ID, PATIENT_NO\r\n" + 
				"FROM LCP_PATIENT_NODE\r\n" + 
				"GROUP BY CP_ID, PATIENT_NO) c\r\n" + 
				"on a.cp_id = c.cp_id\r\n" + 
				"left outer join (select b1.TREAT_EFFECT,\r\n" + 
				"b1.PATIENT_NO,\r\n" + 
				"b1.DISCHARGE_DATE_TIME,\r\n" + 
				"b1.ADMISSION_DATE_TIME,\r\n" + 
				"b2.FEE_TOTAL\r\n" + 
				"from LCP_PATIENT_FIRSTPAGE b1, LCP_PATIENT_FEE b2\r\n" + 
				"where b1.patient_no = b2.patient_no) b\r\n" + 
				"on c.patient_NO = B.PATIENT_NO )  \r\n" + 
				"group by cp_master_id,cp_id,cp_name,cp_code,cp_status,cp_create_date \r\n" + 
				"order by cp_order asc,cp_status asc,cp_create_date desc\r\n" + 
				")where rownum <5";
		ResultSet _rsData = CommonFunction.ExecuteQuery(_strSQL);
		try {
			String _strJson = "{\"cp_cp\":[";
			while (_rsData.next()) {
				String _strCpId = _rsData.getString("cp_id");
				String _strCpName = _rsData.getString("cp_name");
				String _strCpCode = _rsData.getString("cp_code");
				Double _nCphzl = _rsData.getDouble("cp_hzl");
				Double _nCppjzyr = _rsData.getDouble("cp_pjzyr");
				Double _nCppjzyf = _rsData.getDouble("cp_pjzyf");
				String _strCpStatus = _rsData.getString("cp_status_name");
				_strJson += "{\"cp_id\":\"" + _strCpId + "\",\"cp_name\":\""
						+ _strCpName + "\",\"cp_code\":\"" + _strCpCode
						+ "\",\"cp_hzl\":" + _nCphzl + ",\"cp_pjzyr\":"
						+ _nCppjzyr + ",\"cp_pjzyf\":" + _nCppjzyf
						+ ",\"cp_status\":\"" + _strCpStatus + "\"},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			if("{\"cp_cp\":]}"==_strJson){
				_strJson="{\"cp_cp\":[{\"cp_id\":\"no record\",\"cp_name\":\"no record\",\"cp_code\":\"no record\",\"cp_hzl\":0.00,\"cp_pjzyr\":0.00,\"cp_pjzyf\":0.00,\"cp_status\":\"no record\"}]}";
			}		
			System.out.println(_strJson);
			return _strJson;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
		
	}
}
