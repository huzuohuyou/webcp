package com.goodwillcis.cp.patientmanager;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

import bios.report.engine.api.CustomDataSet;

public class ByTongji implements CustomDataSet{
	 private String[] metaData;

	 private List<Object[]> data;
	 	 
	 private String start_time = "";
	 
	 private String end_time = "";

	 private String deptcodevalue="";

	@Override
	public String[] getMetaData() {
		// TODO Auto-generated method stub
		return metaData;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object[] getRowData(int rowIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex);
	}
	@Override
	public void applyParams(Object[] params) {
		// TODO Auto-generated method stub
		//hospital_id = (Integer) params[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		start_time = (String) params[0];
		end_time = (String) params[1];
		deptcodevalue = (String) params[2];
		if (start_time == null||"".equals(start_time)) {
			start_time = "2012-03-01";
		}
		if (end_time == null||"".equals(end_time)) {
			end_time = "2012-04-01";
		}
	    
		end_time = end_time + " 23:59:59";
	metaData = new String[] {
         "患者编码",
         "患者姓名",
         "入院日期",
         "所属科室",
         "主治医生",
         "所属路径名",
         "变异编码",
         "变异类型",
         "变异原因",
         "变异记录医生",
         "变异记录时间"
		};
	data = new ArrayList<Object[]>();
	String bysql="";
	DatabaseClass db = LcpUtil.getDatabaseClass();
	if(!"".equals(deptcodevalue) && Integer.parseInt(deptcodevalue)>0 ){
	String ss = "select dept_name from lcp_local_dept where dept_code='"+deptcodevalue+"'";
	DatabaseClass db1 = LcpUtil.getDatabaseClass();
	String deptName=db1.FunGetDataSetBySQL(ss).FunGetDataAsStringById(0,0);
	//查询符合纳入路径病例数
		//bysql="select distinct (t1.patient_no),                t1.patient_name,                t1.admission_date,                t1.dept_admission_to,                t1.attending_doctor,                (select x.cp_name from lcp_master x where x.cp_id=t.cp_id) cp_name,                t2.cp_node_variation_code,                t2.cp_node_variation_text,                t2.user_name,                t2.exe_date  from lcp_patient_visit t1, lcp_patient_node t, lcp_patient_variation t2 where t.patient_no = t1.patient_no   and t1.cp_state = 21   and t2.patient_no = t1.patient_no   and t1.admission_date >= to_date('"+start_time+"', 'yyyy-mm-dd')   and t1.admission_date <=       to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') and t1.dept_admission_to like '%"+deptName+"%' order by t1.dept_admission_to";
	bysql="select distinct t.patient_no,                                                       "+ 
				"                t.patient_name,                                                    "+
				"                t.admission_date,                                                  "+
				"                t.dept_admission_to,  " +
				"                t.execute_dept,                                          "+
				"                t.attending_doctor,                                                "+
				"                t.conform_master_id,                                               "+
				"                    nvl((select max(cp_name)                                       "+
				"                from dcp_master                                                    "+
				"                where cp_id = t.conform_master_id),                                "+
				"               (select max(cp_name)                                                "+
				"                from lcp_master                                                    "+
				"                where cp_master_id = t.conform_master_id and cp_status=0)) cp_name, " +
				"                t1.cp_node_variation_code, "+
				"                t1.cp_node_variation_text,                                         "+
				"                t1.user_name,                                                      "+
				"                t1.exe_date                                                        "+
				"       from lcp_patient_visit t,                                                   "+
				"       (select v.patient_no, v.cp_node_variation_code,v.cp_node_variation_text,v.exe_date,v.user_name       "+
				"          from lcp_patient_variation v                                             "+
				"         where v.rowid = (select max(v_1.rowid)                                    "+
				"                            from lcp_patient_variation v_1                         "+
				"                           where v.patient_no = v_1.patient_no)) t1                "+
				"   where t.conform_master_id > 0                                                   "+
				"   and t.cp_master_id > 0                                                          "+
				"   and t.cp_state = 21                                                             "+
				"   and t.patient_no = t1.patient_no(+)                                             "+
				"   and t.admission_date >= to_date('"+start_time+"', 'yyyy-mm-dd')                     "+
				"   and t.admission_date <= to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') "+
				"   and t.execute_dept like '%"+deptName+"%' order by t.execute_dept";
	}else{
		//bysql="select distinct (t1.patient_no),                t1.patient_name,                t1.admission_date,                t1.dept_admission_to,                t1.attending_doctor,                (select x.cp_name from lcp_master x where x.cp_id=t.cp_id) cp_name,                t2.cp_node_variation_code,                t2.cp_node_variation_text,                t2.user_name,                t2.exe_date  from lcp_patient_visit t1, lcp_patient_node t, lcp_patient_variation t2 where t.patient_no = t1.patient_no   and t1.cp_state = 21   and t2.patient_no = t1.patient_no   and t1.admission_date >= to_date('"+start_time+"', 'yyyy-mm-dd')   and t1.admission_date <=       to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') order by t1.dept_admission_to";
         bysql="select distinct t.patient_no,                                                       "+ 
				"                t.patient_name,                                                    "+
				"                t.admission_date,                                                  "+
				"                t.dept_admission_to," +
				"                t.execute_dept,             "+
				"                t.attending_doctor,                                                "+
				"                t.conform_master_id,                                               "+
				"                    nvl((select max(cp_name)                                       "+
				"                from dcp_master                                                    "+
				"                where cp_id = t.conform_master_id),                                "+
				"               (select max(cp_name)                                                "+
				"                from lcp_master                                                    "+
				"                where cp_master_id = t.conform_master_id)) cp_name,                " +
				"				 t1.cp_node_variation_code,  "+
				"                t1.cp_node_variation_text,                                         "+
				"                t1.user_name,                                                      "+
				"                t1.exe_date                                                        "+
				"       from lcp_patient_visit t,                                                   "+
				"       (select v.patient_no, v.cp_node_variation_code,v.cp_node_variation_text,v.exe_date,v.user_name       "+
				"          from lcp_patient_variation v                                             "+
				"         where v.rowid = (select max(v_1.rowid)                                    "+
				"                            from lcp_patient_variation v_1                         "+
				"                           where v.patient_no = v_1.patient_no)) t1                "+
				"   where t.conform_master_id > 0                                                   "+
				"   and t.cp_master_id > 0                                                          "+
				"   and t.cp_state = 21                                                             "+
				"   and t.patient_no = t1.patient_no(+)                                             "+
				"   and t.admission_date >= to_date('"+start_time+"', 'yyyy-mm-dd')                     "+
				"   and t.admission_date <= to_date('"+end_time+"', 'yyyy-mm-dd hh24:mi:ss') "+
				"   order by t.execute_dept";
	}
	//System.out.println("bysql===:"+bysql);
	DataSetClass ds = db.FunGetDataSetBySQL(bysql);
	int count= ds.FunGetRowCount();
	for (int i = 0; i < count; i++) {
		String patient_no=ds.FunGetDataAsStringByColName(i, "PATIENT_NO");
		String patient_name=ds.FunGetDataAsStringByColName(i, "PATIENT_NAME");
		String admission_date1=ds.FunGetDataAsStringByColName(i, "ADMISSION_DATE");
		String dept_admission_to=ds.FunGetDataAsStringByColName(i, "DEPT_ADMISSION_TO");
		String attending_doctor=ds.FunGetDataAsStringByColName(i, "ATTENDING_DOCTOR");
		String cp_name=ds.FunGetDataAsStringByColName(i, "CP_NAME");
		String cp_node_variation_code=ds.FunGetDataAsStringByColName(i, "CP_NODE_VARIATION_CODE");
		String cp_node_variation_text=ds.FunGetDataAsStringByColName(i, "CP_NODE_VARIATION_TEXT");
		String user_name=ds.FunGetDataAsStringByColName(i, "USER_NAME");
		String exe_date1=ds.FunGetDataAsStringByColName(i, "EXE_DATE");
		String 	admission_date = admission_date1.replace("T", " ").replace("+08:00", "");
		String	exe_date=exe_date1.replace("T", " ").replace("+08:00", "");
		String variation_name=db.FunGetDataSetBySQL("select variation_name from dcp_dict_variation where variation_code='"+cp_node_variation_code+"'").FunGetDataAsStringById(0, 0);
	    data.add(new Object[] {
	    		patient_no,
	    		patient_name,
	    		admission_date,
	    		dept_admission_to,
	    		attending_doctor,
	    		cp_name,
	    		cp_node_variation_code,
	    		variation_name,
	    		cp_node_variation_text,
	    		user_name,
	    		exe_date
	    });
	}
  }
}
