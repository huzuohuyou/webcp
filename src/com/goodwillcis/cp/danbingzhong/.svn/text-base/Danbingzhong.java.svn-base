/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：Danbingzhong.java
//文件功能描述：单病种相关非特异性指标评估表 自定义数据集
//创建人：潘状
//创建日期：2011/09/08
//
//----------------------------------------------------------------*/

package com.goodwillcis.cp.danbingzhong;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.cp.util.LcpUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bios.report.engine.api.CustomDataSet;

public class Danbingzhong implements CustomDataSet {

	private String[] metaData;

	private List<Object[]> data;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String cp_id = "";
	private int hospital_id = 0;

	 private String start_time = "";
	 
	 private String end_time = "";
	// 参数列表,必须与设计器端的参数数量匹配

	@Override
	public String[] getMetaData() {

		return metaData;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object[] getRowData(int rowIndex) {
		return data.get(rowIndex);
	}

	@Override
	public void applyParams(Object[] params) {
		cp_id = (String) params[0];
		hospital_id = (Integer) params[1];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		start_time = (String) params[2];
		end_time = (String) params[3];
		if (start_time == null||"".equals(start_time)) {
			start_time = "2011-10-01";
		}
		if (end_time == null||"".equals(end_time)) {
			end_time = sdf.format(new Date());
		}
		end_time = end_time + " 23:59:59";
		
		
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String sqlming = "select t1.cp_name,t1.dept_name,t1.cp_start_date,t2.hospital_name from lcp_master t1,dcp_sys_hospital t2 where t1.cp_id = "
				+ cp_id + " and t2.hospital_id = " + hospital_id;
		DataSetClass dsming = db.FunGetDataSetBySQL(sqlming);
		Date cp_start_date = dsming.FunGetDataAsDateByColName(0,
				"CP_START_DATE");
		String start_date = sdf.format(cp_start_date);

		// 查询数据

		String sqlFirstpage = "select round(t3.discharged_date - t3.admission_date,1) zhutianshu,t1.treat_effect zhuangtai,t2.fee_total zongfei,(t2.fee_drug + t2.fee_cn_drug + t2.fee_cn_herb) yaofei,dis_diag_type zhenduan from lcp_patient_firstpage t1,lcp_patient_fee t2,lcp_patient_visit t3 where t3.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t3.patient_no = t2.patient_no(+) and t3.patient_no=t1.patient_no(+) and t3.admission_date > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss') and t3.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss') and t3.cp_state not in (0, 99) and t3.discharged_date is not null and t1.hospital_id = "
				+ hospital_id;

		String sqlBingfa = "select t2.diagnosis_name,count(income_code) a from (select t.income_code  from lcp_patient_log_income t,lcp_patient_visit t1 where t1.patient_no = t.patient_no  and t1.patient_no  in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t.income_code not in (select t.cp_income_code from lcp_master_income t  where t.cp_id = "
				+ cp_id
				+ ") and t.hospital_id = "
				+ hospital_id
				+ " and t1.admission_date>to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss') and t1.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  group by t.patient_no,t.income_code)t1,dcp_dict_diagnosis t2 where t1.income_code = t2.diagnosis_code group by  diagnosis_name order by a desc";

		String sqlkangsheng = "select t.is_antibiotic,count(distinct t.patient_no)cou from lcp_patient_log_order t where hospital_id = "
				+ hospital_id
				+ " and cp_id = "
				+ cp_id
				+ " and t.enter_date_time > to_date('"
				+ start_date
				+ "', 'yyyy-mm-dd hh24:mi:ss')  and t.enter_date_time between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  group by t.is_antibiotic";
		
		String sqlzairuyuan = "select t1.inpatient_no,to_number(substr(t1.patient_no,8)) ci,t1.admission_date,t1.discharged_date from lcp_patient_visit t1 where t1.admission_date is not null and t1.discharged_date is not null and t1.inpatient_no in(select tt.pai from (select t.inpatient_no pai,count(*) cou from lcp_patient_visit t where t.admission_date is not null and t.discharged_date is not null and t.admission_date > to_date('"+start_date+"','yyyy-mm-dd hh24:mi:ss')  and t.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t.patient_no in (select t.patient_no from lcp_patient_node t where t.cp_id = "+cp_id+" group by patient_no) group by inpatient_no )tt  where cou >1) order by inpatient_no,to_number(substr(t1.patient_no, 8))";

		

		String sql = "select round(avg(t1.discharged_date - t1.admission_date), 2) a, 1 b from lcp_patient_visit t1 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t1.admission_date >to_date('"
				+ start_date
				+ "', 'yyyy-mm-dd hh24:mi:ss') and t1.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t1.hospital_id = "
				+ hospital_id
				+ " union select count(t2.patient_no) a, 2 b from lcp_patient_visit t2 where t2.patient_no in (select distinct patient_no  from lcp_patient_node  where cp_id = "
				+ cp_id
				+ ") and t2.cp_state in(1,11,21) and t2.admission_date > to_date('"
				+ start_date
				+ "', 'yyyy-mm-dd hh24:mi:ss')  and t2.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t2.hospital_id = "
				+ hospital_id
				+ " union select count(*) a, 3 b from lcp_patient_visit t3 where t3.hospital_id = "+hospital_id+" and  t3.admission_date > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss') and  t3.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t3.hospital_id = "
				+ hospital_id
				+ " union select count(*) a, 4 b from lcp_patient_visit t4 where t4.cp_state = 11 and t4.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t4.admission_date > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss')  and t4.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t4.hospital_id = "
				+ hospital_id
				+ " union select count(*) a, 5 b from lcp_patient_visit t5 where t5.cp_state = 21 and t5.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t5.admission_date > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss')  and t5.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t5.hospital_id = "
				+ hospital_id
				+ " union select round(sum(t6.fee_other) / sum(t6.fee_total), 3) a, 6 b from LCP_PATIENT_FEE t6 where t6.patient_no in (select distinct t.patient_no from lcp_patient_node t  where cp_id = "
				+ cp_id
				+ " and t.cp_node_start_time  > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss') and t.cp_node_start_time between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss') ) and t6.hospital_id = "
				+ hospital_id
				+ " union select round(sum(t7.fee_examination) / sum(t7.fee_total), 3) a, 7 b from LCP_PATIENT_FEE t7 where t7.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ " and t.cp_node_start_time  > to_date('"
				+ start_date
				+ "','yyyy-mm-dd hh24:mi:ss') and t.cp_node_start_time between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss') ) and t7.hospital_id = "
				+ hospital_id
				+ " union select round(avg(t.stop_date_time-t.enter_date_time),3)a,8 b from lcp_patient_log_order t where t.is_antibiotic in (1,2,3) and hospital_id = "
				+ hospital_id
				+ " and cp_id = "
				+ cp_id
				+ " and t.enter_date_time > to_date('"
				+ start_date
				+ "', 'yyyy-mm-dd hh24:mi:ss')and t.enter_date_time between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  " 
				+" union select count(distinct t9.inpatient_no) a,9 b from lcp_patient_visit t9 where t9.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "
				+ cp_id
				+ ") and t9.admission_date > to_date('"
				+ start_date
				+ "', 'yyyy-mm-dd hh24:mi:ss') and t9.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  " 
				+ "union select round(avg(t1.exe_date-t2.admission_date),2) a,10 b from lcp_patient_log_income t1,lcp_patient_visit t2 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where t.cp_id = "+cp_id+") and t2.admission_date>to_date('"+start_date+"', 'yyyy-mm-dd hh24:mi:ss')  and t2.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t1.income_type='手术' and t1.patient_no=t2.patient_no "
				+ "union select count(*) a ,11 b from lcp_patient_firstpage t where t.dis_diag_type = '4' and t.patient_no in (select distinct t1.patient_no from lcp_patient_node t,lcp_patient_visit t1 where t1.patient_no = t.patient_no and t1.admission_date>to_date('"+start_date+"', 'yyyy-mm-dd hh24:mi:ss') and t1.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss')  and t.cp_id = "+cp_id+") " 
				+ "union select round(oo.kang/ff.zong,4) a,12 b from (select sum(f.fee_total)zong from lcp_patient_fee f where f.patient_no in( select t1.patient_no from lcp_patient_visit t1 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "+cp_id+") and t1.admission_date >to_date('"+start_date+"','yyyy-mm-dd hh24:mi:ss') and t1.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss') ))ff,(select sum(o.orders_cost)kang from lcp_patient_log_order o where o.patient_no in( select t1.patient_no from lcp_patient_visit t1 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = "+cp_id+") and t1.admission_date >to_date('"+start_date+"','yyyy-mm-dd hh24:mi:ss') and t1.admission_date between to_date('"+start_time+"','yyyy-mm-dd') and to_date('"+end_time+"','yyyy-mm-dd hh24:mi:ss') ) and o.is_antibiotic is not null and o.is_antibiotic != 0)oo " 
				+ "order by b";
		DataSetClass dsfirst = db.FunGetDataSetBySQL(sqlFirstpage);
		DataSetClass dsbingfa = db.FunGetDataSetBySQL(sqlBingfa);
		DataSetClass dskangsheng = db.FunGetDataSetBySQL(sqlkangsheng);
		DataSetClass dszairuyuan = db.FunGetDataSetBySQL(sqlzairuyuan);
		DataSetClass ds = db.FunGetDataSetBySQL(sql);

		double pingjuzhu = 0;// 平均住院日
		double shoushu = 0;//手术病人术前平均住院日
		double countZong = 0;// 进入路径的总人数
		double naruZong = 0;// 纳入过路径的人数（单人多次算一人）
		// int countWantui;// 完成路径和退出的总人数
		double wancheng = 0;// 完成路径的总人数
		int siwang = 0;// 死亡总人数
		int zhiyu = 0;// 治愈总人数
		int haozhuan = 0;// 好转总人数
		double yyganran = 0;//医院感染人数
		double ssganran = 0;//手术病人手术部位感人数
		
		int zaizhuyuan14 = 0;//14日再入院人数
		int zaizhuyuan31 = 0;//31日再入院人数
		
		
		String bingfa1 = "";// 并发症1
		int bingfaren1 = 0;// 并发症人数1
		String bingfa2 = "";// 并发症2
		int bingfaren2 = 0;// 并发症人数2
		String bingfa3 = "";// 并发症3
		int bingfaren3 = 0;// 并发症人数3
		double zhuyuanzong = 0;// 住院总人数
		double bianyizong = 0;// 出现变异的患者数
		double danbicj = 0;// 次均
		double danbicjy = 0;// 次均药
		double danbirj = 0;// 日均
		double danbirjy = 0;// 日均药
		double danbihc = 0;// 耗材费比率
		double danbijc = 0;// 检查费比率
		double kangshengbi = 0;// 使用三线抗菌药物的患者比例
		double kangshengtian = 0;// 抗生素使用平均天数
		double ksfeiyongbi = 0;//抗生素费用比
		String keshi = "";//科室名称
		String mingcheng = "";//路径名称
		String yiyuan = "";//医院名称
		// 单病种次均费用总费用,单病种次均费用总药费
		// 单病种日均费用总费用,单病种日均费用总药费
		double cjzhong = 0;// 总费总和
		double cjyzhong = 0;// 药费总和
		double rjzhong = 0;// 每日总费总和
		double rjyzhong = 0;// 每日总费总和
		int zongcishu = dsfirst.FunGetRowCount();//
		for (int i = 0; i < zongcishu; i++) {
			int ZHUTIANSHU = dsfirst.FunGetDataAsNumberByColName(i,
					"ZHUTIANSHU").intValue() + 1;// 住院天数
			double ZONGFEI = dsfirst.FunGetDataAsNumberByColName(i, "ZONGFEI") == null ? 0
					: dsfirst.FunGetDataAsNumberByColName(i, "ZONGFEI")
							.doubleValue(); // 总费用
			int ZHUANGTAI = dsfirst.FunGetDataAsNumberByColName(i, "ZHUANGTAI") == null ? 3
					: dsfirst.FunGetDataAsNumberByColName(i, "ZHUANGTAI")
							.intValue();// 治疗效果，0死亡，1治愈，2好转，3其它
			double YAOFEI = dsfirst.FunGetDataAsNumberByColName(i, "YAOFEI") == null ? 0
					: dsfirst.FunGetDataAsNumberByColName(i, "YAOFEI")
							.doubleValue(); // 总药费
			cjzhong += ZONGFEI;
			cjyzhong += YAOFEI;
			rjzhong += ZONGFEI / ZHUTIANSHU;
			rjyzhong += YAOFEI / ZHUTIANSHU;
			if (ZHUANGTAI == 0) {
				siwang++;
			} else if (ZHUANGTAI == 1) {
				zhiyu++;
			} else if (ZHUANGTAI == 2) {
				haozhuan++;
			}
		}
		danbicj = Math.round(cjzhong / zongcishu * 100) / 100.0;// 次均
		danbicjy = Math.round(cjyzhong / zongcishu * 100) / 100.0;// 次均药
		danbirj = Math.round(rjzhong / zongcishu * 100) / 100.0;// 日均
		danbirjy = Math.round(rjyzhong / zongcishu * 100) / 100.0;// 日均药

		for (int i = 0; i < dsbingfa.FunGetRowCount() && i < 3; i++) {
			if (i == 0) {
				bingfa1 = dsbingfa.FunGetDataAsStringByColName(i,
						"DIAGNOSIS_NAME");
				bingfaren1 = dsbingfa.FunGetDataAsNumberByColName(i, "A")
						.intValue();
			} else if (i == 1) {
				bingfa2 = dsbingfa.FunGetDataAsStringByColName(i,
						"DIAGNOSIS_NAME");
				bingfaren2 = dsbingfa.FunGetDataAsNumberByColName(i, "A")
						.intValue();
			} else if (i == 2) {
				bingfa3 = dsbingfa.FunGetDataAsStringByColName(i,
						"DIAGNOSIS_NAME");
				bingfaren3 = dsbingfa.FunGetDataAsNumberByColName(i, "A")
						.intValue();
			}
		}

		double kangsheng1 = 0;
		double kangsheng2 = 0;
		for (int i = 0; i < dskangsheng.FunGetRowCount(); i++) {
			String is_antibiotic = dskangsheng.FunGetDataAsStringByColName(i,
					"IS_ANTIBIOTIC");
			double cou = dskangsheng.FunGetDataAsNumberByColName(i, "COU")
					.doubleValue();
			if (is_antibiotic != null && !"".equals(is_antibiotic)) {
				kangsheng1 += cou;
			}
			kangsheng2 += cou;
		}
		kangshengbi = Math.round(kangsheng1 / kangsheng2 * 1000) / 1000.0;

		
		//求再住院率部分
		if("1".equals(hospital_id)){
			int resruyuan = dszairuyuan.FunGetRowCount();

			int jishuqi14 = 0;
			int jishuqi31 = 0;
			for(int i = 0;i<resruyuan;i++){
				String pais= dszairuyuan.FunGetDataAsStringByColName(i, "INPATIENT_NO");
				String pai = dszairuyuan.FunGetDataAsStringByColName(i+1, "INPATIENT_NO");
				Date admission_date = dszairuyuan.FunGetDataAsDateByColName(i+1,"ADMISSION_DATE");
				Date discharged_date = dszairuyuan.FunGetDataAsDateByColName(i,"DISCHARGED_DATE");
				
				if(pai.equals(pais)){
					long res = (admission_date.getTime()-discharged_date.getTime())/(3600*24*1000);
						if(res<14){
							jishuqi14++;
						}
						if(res<31){
							jishuqi31++;
						}
				}else{
					if(jishuqi14>1){
						zaizhuyuan14++;
					}
					if(jishuqi31>1){
						zaizhuyuan31++;
					}
					jishuqi14 = 0;
					jishuqi31 = 0;
				}
				
				
			
			
			}
			
			
		}else if("2".equals(hospital_id)){
			
		}
		
		
		for (int i = 0; i < ds.FunGetRowCount(); i++) {
			switch (i) {
			case 0:
				pingjuzhu = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 1:
				countZong = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 2:
				zhuyuanzong = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 3:
				wancheng = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 4:
				bianyizong = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 5:
				danbihc = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 6:
				danbijc = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 7:
				kangshengtian = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
				//纳入过路径的人数（单人多次算一人）
			case 8:
				naruZong = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
				
				//术前平均住院日 
			case 9:
				shoushu = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			case 10:
				yyganran = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
				
				//抗菌药物比
			case 11:
				ksfeiyongbi = ds.FunGetDataAsNumberByColName(i, "A") == null ? 0
						: ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
				break;
			}

			keshi = dsming.FunGetDataAsStringByColName(0, "DEPT_NAME");
			mingcheng = dsming.FunGetDataAsStringByColName(0, "CP_NAME");
			yiyuan = dsming.FunGetDataAsStringByColName(0, "HOSPITAL_NAME");
		}
		// 填入数据
		metaData = new String[] { "平均住院日", //
				"手术病人术前平均住院日", // 1
				"病种死亡率", //
				"治愈率", //
				"好转率", //
				"医院感染发生率", // 1
				"手术病人手术部位感染率", // 1
				"14日再住院率", // 1
				"31日再住院率", // 1
				"手术病人非计划重返手术室发生率", // 1
				"前三位常见并发症1", //
				"其发生率1", //
				"前三位常见并发症2", //
				"其发生率2", //
				"前三位常见并发症3", //
				"其发生率3", //
				"住院患者总人数", //
				"进入路径的患者总人次数", //
				"完成路径的人次数", //
				"出现变异的患者数", // 变异退出
				"使用三线抗菌药物的患者比例", // 1
				"抗生素使用的平均天数", // 1
				"单病种次均费用总费用", //
				"单病种次均费用总药费", //
				"单病种日均费用总费用", //
				"单病种日均费用总药费", //
				"单病种抗菌药物费用比例", // 1
				"单病种耗材费用比例", //
				"单病种检查费用比例",//
				"科室名称",//
				"病种名称", "医院名称", "开启时间","起始时间","结束时间"//
		};
		data = new ArrayList<Object[]>();
		data.add(new Object[] { pingjuzhu, shoushu,
				Math.round(siwang / countZong * 1000) / 1000.0,
				Math.round(zhiyu / countZong * 1000) / 1000.0,
				Math.round(haozhuan / countZong * 1000) / 1000.0, Math.round(yyganran/countZong*1000)/1000.0, null,
				Math.round(zaizhuyuan14 / naruZong * 1000) / 1000.0,
				Math.round(zaizhuyuan31 / naruZong * 1000) / 1000.0, null,
				bingfa1, Math.round(bingfaren1 / countZong * 1000) / 1000.0,
				bingfa2, Math.round(bingfaren2 / countZong * 1000) / 1000.0,
				bingfa3, Math.round(bingfaren3 / countZong * 1000) / 1000.0,
				zhuyuanzong, countZong, wancheng, bianyizong, kangshengbi,
				kangshengtian, danbicj, danbicjy, danbirj, danbirjy, ksfeiyongbi,
				danbihc, danbijc, keshi, mingcheng, yiyuan, start_date,start_time,end_time });

	}

}
