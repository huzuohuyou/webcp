package com.goodwillcis.cp.util;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.cp.util.LcpUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String cp_id = "10017";
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String sqlFirstpage = "select round(t1.discharge_date_time-t1.admission_date_time,0) zhutianshu,t1.treat_effect zhuangtai,t2.fee_total zongfei,t2.fee_drug+t2.fee_cn_drug+t2.fee_cn_herb yaofei from lcp_patient_firstpage t1,lcp_patient_fee t2 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') and t1.patient_no = t2.patient_no";
		String sqlBingfa = "select t.local_name,t.income_code , count(t.income_code)a  from lcp_patient_log_income t where t.patient_no in (select distinct t.patient_no  from lcp_patient_node t where cp_id = '"+cp_id+"') and t.income_code not in (select t.cp_income_code from lcp_master_income t where t.cp_id = '"+cp_id+"') group by t.local_name,t.income_code order by  a desc";
		String sql = "select round(avg(t1.discharge_date_time - t1.admission_date_time),2)a,1 b from lcp_patient_firstpage t1 where t1.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') union select count(distinct t2.patient_no)a,2 b from lcp_patient_node t2 where cp_id = '"+cp_id+"' union select count(*)a,3 b from lcp_patient_visit union select count(*)a,4 b from lcp_patient_visit t4 where t4.cp_state = 11 and  t4.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') union select count(*)a,5 b from lcp_patient_visit t5 where t5.cp_state = 21 and  t5.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') union select round(sum(t6.fee_other)/sum(t6.fee_total)*100,2) a,6 b from LCP_PATIENT_FEE t6 where t6.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') union select round(sum(t7.fee_examination)/sum(t7.fee_total)*100,2)a,7 b from LCP_PATIENT_FEE t7 where t7.patient_no in (select distinct t.patient_no from lcp_patient_node t where cp_id = '"+cp_id+"') order by b";
		DataSetClass dsfirst = db.FunGetDataSetBySQL(sqlFirstpage);
		DataSetClass dsbingfa = db.FunGetDataSetBySQL(sqlBingfa);
		DataSetClass ds = db.FunGetDataSetBySQL(sql);
		double pingjuzhu = 0;// 平均住院日
		double countZong = 0;// 进入路径的总人数
		//int countWantui;// 完成路径和退出的总人数
		double wancheng = 0;// 完成路径的总人数
		int siwang = 0;// 死亡总人数
		int zhiyu = 0;// 治愈总人数
		int haozhuan = 0;// 好转总人数
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
		//单病种次均费用总费用,单病种次均费用总药费
		//单病种日均费用总费用,单病种日均费用总药费
				double cjzhong = 0;//总费总和
				double cjyzhong = 0;//药费总和
				double rjzhong = 0;//每日总费总和
				double rjyzhong = 0;//每日总费总和
				int zongcishu = dsfirst.FunGetRowCount();//
				for (int i = 0; i < zongcishu; i++) {
					int ZHUTIANSHU = dsfirst.FunGetDataAsNumberByColName(i,
							"ZHUTIANSHU").intValue();// 住院天数
					double ZONGFEI = dsfirst.FunGetDataAsNumberByColName(i,
							"ZONGFEI").doubleValue(); // 总费用
					double ZHUANGTAI = dsfirst.FunGetDataAsNumberByColName(i,
							"ZHUANGTAI").doubleValue();// 治疗效果，0死亡，1治愈，2好转，3其它
					double YAOFEI = dsfirst.FunGetDataAsNumberByColName(i,
							"YAOFEI").doubleValue(); // 总药费
					cjzhong += ZONGFEI;
					cjyzhong += YAOFEI;
					rjzhong += ZONGFEI/ZHUTIANSHU;
					rjyzhong += YAOFEI/ZHUTIANSHU;
					if(ZHUANGTAI == 0){
						siwang++;
					}else if(ZHUANGTAI == 1){
						zhiyu++;
					}else if(ZHUANGTAI == 2){
						haozhuan++;
					}
				}
				danbicj = Math.round(cjzhong/zongcishu*100)/100.0;// 次均
				danbicjy = Math.round(cjyzhong/zongcishu*100)/100.0;// 次均药
				danbirj = Math.round(rjzhong/zongcishu*100)/100.0;// 日均
				danbirjy = Math.round(rjyzhong/zongcishu*100)/100.0;// 日均药
				
				
				for (int i = 0; i < dsbingfa.FunGetRowCount()&&i<3; i++) {
					if(i==0){
						bingfa1 = dsbingfa.FunGetDataAsStringByColName(i, "LOCAL_NAME");
						bingfaren1 = dsbingfa.FunGetDataAsNumberByColName(i, "A").intValue();
					}else if(i==1){
						bingfa2 = dsbingfa.FunGetDataAsStringByColName(i, "LOCAL_NAME");
						bingfaren2 = dsbingfa.FunGetDataAsNumberByColName(i, "A").intValue();
					}else if(i==2){
						bingfa3 = dsbingfa.FunGetDataAsStringByColName(i, "LOCAL_NAME");
						bingfaren3 = dsbingfa.FunGetDataAsNumberByColName(i, "A").intValue();
					}
				}
				
				for (int i = 0; i < ds.FunGetRowCount(); i++) {
					double data = ds.FunGetDataAsNumberByColName(i, "A")==null?0:ds.FunGetDataAsNumberByColName(i, "A").doubleValue();
					System.out.println(data);
					switch(i){
					case 0:
						pingjuzhu = data;
						break;
					case 1:
						countZong = data;
						break;
					case 2:
						zhuyuanzong = data;
						break;
					case 3:
						wancheng = data;
						break;
					case 4:
						bianyizong = data;
						break;
					case 5:
						danbihc = data;
						break;
					case 6:
						danbijc = data;
						break;
						}
				}
				
System.out.println("kaishi-----------------------");
				System.out.println("平均住院日:"+pingjuzhu);
				 System.out.println("假"+3);
				 System.out.println("病种死亡率:"+Math.round(siwang/zhuyuanzong*100)/100.0);
				 System.out.println("治愈率:"+Math.round(zhiyu/zhuyuanzong*100)/100.0);
				 System.out.println("好转率:"+Math.round(haozhuan/zhuyuanzong*100)/100.0);
				 System.out.println("假"+1.21);
				 System.out.println("假"+4);
				 System.out.println("假"+5);
				 System.out.println("假"+2);
				 System.out.println("前三位常见并发症1:"+bingfa1);
				 System.out.println("前三位常见并发症1:"+Math.round(bingfaren1/countZong*100)/100.0);
				 System.out.println("前三位常见并发症2:"+bingfa2);
				 System.out.println("前三位常见并发症2:"+Math.round(bingfaren2/countZong*100)/100.0);
				 System.out.println("前三位常见并发症3:"+bingfa3);
				 System.out.println("前三位常见并发症3:"+Math.round(bingfaren3/countZong*100)/100.0);
				 System.out.println("住院患者总人数:"+zhuyuanzong);
				 System.out.println("进入路径的患者总人次数:"+countZong);
				 System.out.println("完成路径的人次数:"+wancheng);
				 System.out.println("出现变异的患者数 :"+bianyizong);
				 System.out.println("假"+41);
				 System.out.println("假"+8);
				 System.out.println("单病种次均费用总费用:"+danbicj);
				 System.out.println("单病种次均费用总药费:"+danbicjy);
				 System.out.println("单病种日均费用总费费:"+danbirj);
				 System.out.println("单病种日均费用总药费:"+danbirjy);
				 System.out.println("假:"+123);
				 System.out.println("单病种耗材费用比例:"+danbihc);
				 System.out.println("单病种检查费用比例:"+danbijc);
				 
				 
	}

}
