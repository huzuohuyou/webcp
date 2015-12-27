package com.goodwillcis.lcp.util;

public class CommonSQL {

	/**
	 * 查询所有匹配的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetAccordantPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// visit表中的人
		if(start_date==null||start_date=="")
		start_date="2011-01-01";
		String visit_sql = "select a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from lcp_patient_visit a where   a.sys_is_del=0 ";
		if (start_date != "" && start_date != null&&start_date.length()==10) {
			visit_sql = visit_sql + " and a.admission_date>=to_date('"
					+ start_date + "','yyyy-mm-dd') ";
		}
		
		if (start_date != "" && start_date != null&&start_date.length()==7) {
			visit_sql = visit_sql + " and to_char(a.admission_date,'yyyy-mm') ='"+start_date+"' ";
		}
		
		if (start_date != "" && start_date != null&&start_date.length()==4) {
			visit_sql = visit_sql + " and to_char(a.admission_date,'yyyy') ='"+start_date+"'";
		}
		

		//System.out.println("visit_sql="+visit_sql);
		// 符合的人，
		String fuhe_sql = "select distinct * from (select a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income a,lcp_master_income b where a.hospital_id=b.hospital_id and a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)";
		fuhe_sql = "select * from (" + fuhe_sql + ") where 1=1 ";
		if (hospital_id > 0) {
			fuhe_sql = fuhe_sql + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			fuhe_sql = fuhe_sql + " and cp_id=" + cp_id + " ";
		}
		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		// 符合的人下的诊断或者手术必须在路径开启的时间内才有效！！！
		fuhe_sql = " select distinct * from (select  b.*,a.patient_no from ("
				+ fuhe_sql
				+ ")a,("
				+ cp_status
				+ ")b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and ( a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu))";

		// 符合 的人必须在是visit有的前提下
		String sql_1 = "select b.*,a.admission_date,a.visit_status from ("
				+ visit_sql
				+ ")a,("
				+ fuhe_sql
				+ ")b where a.hospital_id=b.hospital_id and a.patient_no=b.patient_no";

		// 符合的人必须在路径的时间范围内
		// String
		// sql="select a.patient_no,a.visit_status,b.* from ("+sql_1+")a,("+cp_status+")b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and( a.admission_date>=b.kaishi and  a.admission_date<=b.jieshu)";

		return sql_1;
	}

	/**
	 * 获得各个路径的状态，起始时间等
	 * 
	 * @return
	 */
	public static String funGetCpStatus() {
		return "select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0 then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ";
	}

	/**
	 * 查询匹配的人的总数，按照医院和cp_id进行分类
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return
	 */
	public static String funGetAccordantTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		String fenlei_sql = funGetAccordantPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);
		String sql_1 = "select NVL (count(patient_no),0) fuherenshu, cp_id,hospital_id from ("
				+ fenlei_sql + ")group by cp_id,hospital_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) fuherenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	public static String funGetNodePatientByHosAndCpId(int hospital_id,
			int cp_id) {

		String sql = "select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where 1=1 ";
		if (hospital_id > 0) {
			sql = sql + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			sql = sql + " and cp_id=" + cp_id + " ";
		}

		return "select distinct * from (" + sql + ")";
	}

	/**
	 * 查询所有符合未纳入的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetFuheNoNaruPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有状态的病人信息
		String total_sql = funGetAccordantPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql = "select * from (" + total_sql
				+ ") where visit_status in(0)";

		return sql;
	}

	/**
	 * 查询所有符合未纳入的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetFuheNoNaruTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetFuheNoNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) fuhenonarurenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有纳入的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetNaruPatientPartByHosAndCpId(int hospital_id,
			int cp_id, String start_date) {
		// 所有状态的病人信息
		String total_sql = funGetAccordantPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// node表中纳入的人

		String node_sql = funGetNodePatientByHosAndCpId(hospital_id, cp_id);

		String sql_1 = "select a.* from ("
				+ total_sql
				+ ")a,("
				+ node_sql
				+ ") b where a.hospital_id=b.hospital_id and a.patient_no=b.patient_no and a.cp_id=b.cp_id ";

		// System.out.println("符合并且弄得表中有数据的人为 "+sql_1);

		// 查询状态是1,11,21 的人

		String sql = "select * from (" + sql_1
				+ ") where visit_status in(1,11,21)";

		return sql;
	}

	/**
	 * 查询所有纳入的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetNaruTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) narurenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有执行的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetZhixingPatientPartByHosAndCpId(int hospital_id,
			int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1的人

		String sql = "select * from (" + total_sql
				+ ") where visit_status in(1)";

		return sql;
	}

	/**
	 * 查询所有执行的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetZhixingTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetZhixingPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) zhixingrenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有完成的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetWanchengPatientPartByHosAndCpId(int hospital_id,
			int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1的人

		String sql = "select * from (" + total_sql
				+ ") where visit_status in(11)";

		return sql;
	}

	/**
	 * 查询所有完成的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetWanchengTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetWanchengPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) wanchengrenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有退出的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetTuichuPatientPartByHosAndCpId(int hospital_id,
			int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1的人

		String sql = "select * from (" + total_sql
				+ ") where visit_status in(21)";

		return sql;
	}

	/**
	 * 查询所有退出的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetTuichuTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetTuichuPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) tuichurenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有完成带有变异的人，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetWanchengWithBianyiPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetNaruPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);

		// 查询状态是1的人

		String sql_1 = "select * from (" + total_sql
				+ ") where visit_status in(11)";

		// 查询变异表中的人

		String bianyi = "select distinct * from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_log_order_varia t where 1=1 ";

		if (hospital_id > 0) {
			bianyi = bianyi + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			bianyi = bianyi + " and cp_id=" + cp_id + " ";
		}
		bianyi = bianyi + ")";

		String sql = "select a.* from ("
				+ sql_1
				+ ")a,("
				+ bianyi
				+ ") b where a.hospital_id=b.hospital_id and a.patient_no=b.patient_no and a.cp_id=b.cp_id ";

		return sql;
	}

	/**
	 * 查询所有完成带有变异的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetWanchengWithBianyiTotalPatientPartByHosAndCpId(
			int hospital_id, int cp_id, String start_date) {
		// 所有纳入的病人信息
		String total_sql = funGetWanchengWithBianyiPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);

		// 查询状态是1,11,21 的人

		String sql_1 = "select count(patient_no) fuherenshu,hospital_id, cp_id from ("
				+ total_sql + ")group by hospital_id,cp_id";

		// 路径的状态
		String cp_status = "select * from (" + funGetCpStatus()
				+ ") where 1=1 ";
		if (hospital_id > 0) {
			cp_status = cp_status + " and hospital_id=" + hospital_id + " ";
		}
		if (cp_id > 0) {
			cp_status = cp_status + " and cp_id=" + cp_id + " ";
		}

		String sql = "select NVL(b.fuherenshu,0) wanchengbianyirenshu ,a.* from ("
				+ cp_status
				+ ") a ,("
				+ sql_1
				+ ")b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ";

		return sql;
	}

	/**
	 * 查询所有情况的人总数，按医院和cpId进行分类查询(所有人的列表，都属于哪个医院和哪个CpID)
	 * 
	 * @param hospital_id
	 *            负数表示所有的医院，正数就是统计这个医院的
	 * @param cp_id
	 *            负数表示所有的路径，正数就是统计这个路径的
	 * @param start_date
	 *            统计开始时间 必须是2011-11-21这种格式数据，否则出错！！！不填表示统计所有日期的数据
	 * @return hospital_id,patient_no,cp_id
	 */
	public static String funGetCpInfoTongji(int hospital_id, int cp_id,
			String start_date) {

		String cp_info_sql = "select * from lcp_master lc where lc.cp_status=0";
		//System.out.println("路径状态查询=  " + cp_info_sql);

		String fuhe_sql = funGetAccordantTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		fuhe_sql = "select hospital_id,cp_id,cp_name,fuherenshu fhrs from ("
				+ fuhe_sql + ")";
		//System.out.println("所有符合的路径查询=  " + fuhe_sql);

		String naru_sql = funGetNaruTotalPatientPartByHosAndCpId(hospital_id,
				cp_id, start_date);
		naru_sql = "select hospital_id,cp_id,cp_name,narurenshu nrrs from ("
				+ naru_sql + ")";
		//System.out.println("所有纳入的路径查询=  " + naru_sql);

		String zhixing_sql = funGetZhixingTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		zhixing_sql = "select hospital_id,cp_id,cp_name,zhixingrenshu zxrs from ("
				+ zhixing_sql + ")";
		//System.out.println("所有执行的路径查询=  " + zhixing_sql);

		String wancheng_sql = funGetWanchengTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		wancheng_sql = "select hospital_id,cp_id,cp_name,wanchengrenshu wcrs from ("
				+ wancheng_sql + ")";
		//System.out.println("所有完成的路径查询=  " + wancheng_sql);

		String tuichu_sql = funGetTuichuTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		tuichu_sql = "select hospital_id,cp_id,cp_name,tuichurenshu tcrs from ("
				+ tuichu_sql + ")";
		//System.out.println("所有退出的路径查询=  " + tuichu_sql);

		String wanchengbianyi_sql = funGetWanchengWithBianyiTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		wanchengbianyi_sql = "select hospital_id,cp_id,cp_name,wanchengbianyirenshu wcbyrs from ("
				+ wanchengbianyi_sql + ")";
		//System.out.println("所有完成带有变异的路径查询=  " + wanchengbianyi_sql);

		String fuheNoNaru_sql = funGetFuheNoNaruTotalPatientPartByHosAndCpId(
				hospital_id, cp_id, start_date);
		fuheNoNaru_sql = "select hospital_id,cp_id,cp_name,fuhenonarurenshu fhbnrrs from ("
				+ fuheNoNaru_sql + ")";
		//System.out.println("所有符合不纳入的路径查询=  " + fuheNoNaru_sql);

		String sql = "select a.*,b.fhrs,c.nrrs,d.zxrs,e.wcrs,f.tcrs,g.wcbyrs,h.fhbnrrs from (" + cp_info_sql + ")a," + "("
				+ fuhe_sql + ")b," + "(" + naru_sql + ")c," + "("
				+ zhixing_sql + ")d," + "(" + wancheng_sql + ")e," + "("
				+ tuichu_sql + ")f," + "(" + wanchengbianyi_sql + ")g," + "("
				+ fuheNoNaru_sql + ")h" +
						" where a.hospital_id=b.hospital_id" +
						" and a.hospital_id=c.hospital_id" +
						" and a.hospital_id=d.hospital_id" +
						" and a.hospital_id=e.hospital_id" +
						" and a.hospital_id=f.hospital_id" +
						" and a.hospital_id=g.hospital_id" +
						" and a.hospital_id=h.hospital_id" +
						" and a.cp_id=b.cp_id" +
						" and a.cp_id=c.cp_id" +
						" and a.cp_id=d.cp_id" +
						" and a.cp_id=e.cp_id" +
						" and a.cp_id=f.cp_id" +
						" and a.cp_id=g.cp_id" +
						" and a.cp_id=h.cp_id" +
						"";
		sql="select " +
				"hospital_id," +
				"cp_code, "+
           "    cp_name, "+
           "    cp_id, "+
           "    cp_start_date, "+
           "    cp_status, "+
           "    input_code_py, "+
           "    input_code_wb, "+
           "    fhrs, "+
          
           
           "    nrrs, "+
           "    TRUNC((case "+
           "            when fhrs = 0 then "+
           "             0 "+
           "            else "+
           "             nrrs / fhrs * 100 "+
           "          end), "+
           "          2) nrbfb, "+
           
           
           "    zxrs, "+
           
           
           "    wcrs, "+     
           "    TRUNC((case "+
           "            when nrrs = 0 then  "+
           "             0 "+
           "            else "+
           "             wcrs / nrrs * 100 "+
           "          end), "+
           "          2) wcbfb ," +
           
           
           
           "    tcrs, "+
           "    wcbyrs, "+
           "    TRUNC((case "+
           "            when wcrs = 0 then  "+
           "             0 "+
           "            else "+
           "             wcbyrs / wcrs * 100 "+
           "          end), "+
           "          2) wcbybfb ," +
           " fhbnrrs," +
           " fhrs-nrrs-fhbnrrs bnrrs," +
           " fhrs-nrrs zbnrrs " + //总的不纳入人数，包括符合不纳入和就是不纳入
           "" +
           "" +
           "  from ( "+sql +")";
		return sql;
	}
	/**
	 * 所有医院的各自的纳入百分比
	 * @return
	 */
	public static String funGetAllHosNRBFB(int hospital_id,String date){
		//符合总数
		String sql_1=funGetCpInfoTongji(-1, -1, date);
		sql_1="select * from ( "+sql_1+") where nrrs>0 ";
		
		sql_1="select avg(nrbfb)gybfb," +
				"sum(nrrs)nrrs," +
				"sum(zbnrrs) zbnrrs," +
				"sum(zxrs) zxrs," +
				"sum(wcrs) wcrs," +
				"sum(tcrs) tcrs," +
				"sum(fhbnrrs) fhbnrrs," +
				"sum(bnrrs) bnrrs," +
				"sum(fhrs) fhrs," +
				" hospital_id from ("+sql_1+") group by hospital_id";
		
		//System.out.println("sql_1=" +sql_1);
		
	
		
		String sql_3="select * from dcp_sys_hospital t";
		
		String sql="select trunc(a.gybfb,2) gybfb,a.nrrs,a.zbnrrs,a.zxrs,a.wcrs,a.tcrs,a.fhbnrrs,a.bnrrs,a.fhrs,c.* from ("+sql_1+")a,("+sql_3+")c where  a.hospital_id(+)=c.hospital_id ";
		
		if(hospital_id>0){
			sql="select * from ("+sql+") where hospital_id= "+hospital_id;
		}
		return sql;
	}
	
	/**
	 * 
	 * 所有医院的纳入百分比，及各种综合统计
	 * @ date如果是2011-10-01这种格式，那么查询从这天开始的所有数据，如果是2011这种格式，那么查询2011年的所有数据，如果是2011-10那么查询2011-01的数据或者""默认从2011-10-01开始统计
	 * @return
	 */
	public static String funGetAllHosTotalNRBFB(String date){
		//符合总数
		String sql_1=funGetAllHosNRBFB(-1,date);
		//sql_1="select * from ( "+sql_1+") where nrrs>0 ";
		System.out.println(sql_1);
		sql_1="select avg(gybfb)nrbfb," +
				"sum(nrrs)nrrs," +
				"sum(zbnrrs)zbnrrs," +
				"sum(zxrs)zxrs," +
				"sum(wcrs)wcrs," +
				"sum(tcrs)tcrs," +
				"sum(fhbnrrs)fhbnrrs," +
				"sum(bnrrs)bnrrs," +
				"sum(fhrs)fhrs" +
				" from ("+sql_1+")";
		
		//System.out.println("sql_1=" +sql_1);
			
		String sql="select trunc(nrbfb,2) nrbfb,nrrs,zbnrrs,zxrs,wcrs,tcrs,bnrrs,fhbnrrs,fhrs from ("+sql_1+")a ";
		
		return sql;
	}
}
