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
import com.goodwillcis.lcp.util.DBConnection;
import com.goodwillcis.lcp.util.SingleClass;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import bios.report.engine.api.CustomDataSet;

public class Danbingzhong_dcp implements CustomDataSet {

	private String[] metaData;

	private List<Object[]> data;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String cpMasterId = "";
	
	private String  start_time="";
	
	private String  end_time="";
	

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
		start_time=(String) params[0];
		end_time=(String) params[1];
		cpMasterId=(String) params[2];
		end_time = end_time + " 23:59:59";
//		start_time="2012-09-01";
//		end_time="2012-09-24 23:59:59";
//		cpMasterId="10079";
		int cp_id=Integer.parseInt(cpMasterId);

		DatabaseClass db = LcpUtil.getDatabaseClass();
		String sqlming = "select t3.*  from (select nvl(t2.cp_name, t1.cp_name) cp_name,               t1.cp_start_date,               t1.hospital_id          from lcp_master t1, dcp_master t2         where t1.cp_master_id = t2.cp_id(+)           and t1.cp_master_id = "+cp_id+") t3 where rowid = (select max(rowid)                  from (select nvl(t2.cp_name, t1.cp_name) cp_name,                               t1.cp_start_date,                               t1.hospital_id                          from lcp_master t1, dcp_master t2                         where t1.cp_master_id = t2.cp_id(+)                           and t1.cp_master_id = "+cp_id+"))";
		
		DataSetClass dsming = db.FunGetDataSetBySQL(sqlming);
		String start_date = dsming.FunGetDataAsStringByColName(0,"CP_START_DATE");
		if(!"".equals(start_date)){
		start_date = start_date.replace("T"," ");
		start_date = start_date.substring(0,19);
		}
		  Connection conn = null;
		  Statement statement = null;
		  ResultSet rs = null; 
		  CallableStatement stmt = null;
		  /* String driver;
		  String url;
		  String user;
		  String pwd; */
			double pingjuzhu = 0;// 平均住院日
			double shoushu = 0;//手术病人术前平均住院日
			double countZong = 0;// 进入路径的总人数
			double naruZong = 0;// 纳入过路径的人数（单人多次算一人）
			//int countWantui;// 完成路径和退出的总人数
			double wancheng = 0;// 完成路径的总人数
			int siwang = 0;// 死亡总人数
			int zhiyu = 0;// 治愈总人数
			int haozhuan = 0;// 好转总人数
			double yyganran = 0;//医院感染人数
			double ssganran = 0;//手术病人手术部位感人数
			
			int zaizhuyuan14 = 0;//14日再入院人数
			int zaizhuyuan31 = 0;//31日再入院人数
			double zaizhuyuanlv14 = 0;//14日再入院率
			double zaizhuyuanlv31 = 0;//31日再入院率
			
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
			String mingcheng = "";
			//单病种次均费用总费用,单病种次均费用总药费
			//单病种日均费用总费用,单病种日均费用总药费
					double cjzhong = 0;//总费总和
					double cjyzhong = 0;//药费总和
					double rjzhong = 0;//每日总费总和
					double rjyzhong = 0;//每日总费总和
					int zongcishu=0;
					//求抗生素比
					double kangsheng1 = 0;
					double kangsheng2 = 0;
		  try{
		  DBConnection dbread =SingleClass.GetInstance();
		  conn=dbread.getConnection();
	      System.out.println("conection is ok");
	      statement = conn.createStatement();
	     //conn.setAutoCommit(false);
	     
	     //调用函数
	     stmt = conn.prepareCall("{call GETDUIBI_DATA.DBZBB_PRO (?,?,?,?,?)}");
	    // stmt.registerOutParameter(1, java.sql.Types.FLOAT);
	    // stmt.registerOutParameter(2, java.sql.Types.CHAR);
	     stmt.registerOutParameter(1, OracleTypes.VARCHAR);
	     stmt.registerOutParameter(2, OracleTypes.VARCHAR);
	     stmt.registerOutParameter(3, OracleTypes.NUMBER);
	     stmt.registerOutParameter(4, OracleTypes.NUMBER);
	     stmt.registerOutParameter(5, OracleTypes.CURSOR);
	     stmt.setString(1,start_time);
	     stmt.setString(2,end_time);
	     stmt.setInt(3, cp_id);
	     for(int i=0;i<5;i++){
	    	 if(i==0){
		    	 stmt.setInt(4,0);
			     stmt.execute();
			     rs=(ResultSet) stmt.getObject(5);
			 while(rs.next()){
				 zongcishu++;
				double ZHUTIANSHUs = rs.getDouble(1);// 住院天数
				int ZHUTIANSHU=(int) Math.round(ZHUTIANSHUs);
				double ZONGFEI = rs.getDouble(3); // 总费用
				int ZHUANGTAI = rs.getInt(2);// 治疗效果，0死亡，1治愈，2好转，3其它
				double YAOFEI = rs.getDouble(4);// 总药费
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
		     }else if(i==1){
		    	 stmt.setInt(4,1);
		         stmt.execute();
			     rs=(ResultSet) stmt.getObject(5);
			     int j=0;
				  while(rs.next()){
					  //并发症
					  
						if(j==0){
							bingfa1 = rs.getString(1);
							bingfaren1 = rs.getInt(2);
							 j++;
						}else if(j==1){
							bingfa2 =  rs.getString(1);
							bingfaren2 = rs.getInt(2);
							 j++;
						}else if(j==2){
							bingfa3 =  rs.getString(1);
							bingfaren3 = rs.getInt(2);
							 j++;
						}else{
							
						}
						
					     }
		     }else if(i==2){
		    	 stmt.setInt(4,2);
		         stmt.execute();
			     rs=(ResultSet) stmt.getObject(5);
				  while(rs.next()){
						String is_antibiotic = rs.getString(1);
						double cou = rs.getDouble(2);
						if("1".equals(is_antibiotic)||"2".equals(is_antibiotic)||"3".equals(is_antibiotic)){
							kangsheng1+=cou;
						}
							kangsheng2+=cou;
					     }
		     }else if(i==3){
		    	 stmt.setInt(4,3);
		         stmt.execute();
			     rs=(ResultSet) stmt.getObject(5);
			     int j=0;
				  while(rs.next()){
						switch(j){
						case 0:
							//平均住院日
							pingjuzhu = rs.getDouble(1);
							j++;
							break;
						case 1:
							//进入路径总人数
							countZong =rs.getDouble(1);
							j++;
							break;
						case 2:
							//总入院人数
							zhuyuanzong = rs.getDouble(1);
							 j++;
							break;
						case 3:
							//完成路径总人数
							wancheng = rs.getDouble(1);
							  j++;
							break;
						case 4:
							//出现变异总人数
							bianyizong = rs.getDouble(1);
							  j++;
							break;
						case 5:
							//单病种耗材费用比例
							danbihc = rs.getDouble(1);
							  j++;
							break;
						case 6:
							//单病种检查费用比例
							danbijc =rs.getDouble(1);
							  j++;
							break;
						case 7:
							//抗生素使用的平均天数 
							kangshengtian = rs.getDouble(1);
							  j++;
							break;
						case 8:
							//纳入过路径的人数（单人多次算一人）
							naruZong =rs.getDouble(1);
							  j++;
							break;
						case 9:
							//术前平均住院日 
							shoushu =rs.getDouble(1);
							  j++;
							break;
						case 10:
							//医院感染人数
							yyganran = rs.getDouble(1);
							  j++;
							break;
						case 11:
							//抗菌药物比
							ksfeiyongbi = rs.getDouble(1);
							  j++;
							break;
						}
						  
					     }
		     }
	    	
	     }
			danbicj = Math.round(cjzhong/zongcishu*100)/100.0;// 次均
			danbicjy = Math.round(cjyzhong/zongcishu*100)/100.0;// 次均药
			danbirj = Math.round(rjzhong/zongcishu*100)/100.0;// 日均
			danbirjy = Math.round(rjyzhong/zongcishu*100)/100.0;// 日均药
			
			kangshengbi = Math.round(kangsheng1/kangsheng2*1000)/1000.0;
			mingcheng = dsming.FunGetDataAsStringByColName(0, "CP_NAME");
			if(bingfaren1>countZong ||bingfaren2>countZong ||bingfaren3>countZong){
				bingfaren1=(int) countZong;
				bingfaren2=(int) countZong;
				bingfaren3=(int) countZong;
				
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
					"病种名称",
					"起始时间"//
			};
			data = new ArrayList<Object[]>();
			if(siwang>countZong || zhiyu>countZong || haozhuan>countZong||yyganran>countZong){
				siwang=(int) countZong;
				zhiyu=(int) countZong;
				haozhuan=(int) countZong;
				yyganran=countZong;
				
			}
			data.add(new Object[] { pingjuzhu, shoushu,
					Math.round(siwang / countZong * 1000) / 1000.0,
					Math.round(zhiyu / countZong * 1000) / 1000.0,
					Math.round(haozhuan / countZong * 1000) / 1000.0, Math.round(yyganran/countZong*1000)/1000.0, 0,
					0,
					0, 0,
					bingfa1, Math.round(bingfaren1 / countZong * 1000) / 1000.0,
					bingfa2, Math.round(bingfaren2 / countZong * 1000) / 1000.0,
					bingfa3, Math.round(bingfaren3 / countZong * 1000) / 1000.0,
					zhuyuanzong, countZong, wancheng, bianyizong, kangshengbi,
					kangshengtian, danbicj, danbicjy, danbirj, danbirjy, ksfeiyongbi,
					danbihc, danbijc, mingcheng, start_date });
		  }catch(Exception e){
			  System.out.println("存储过程出错!");
		  }finally{
			  if(stmt!=null){
				  try {
					  stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stmt=null;
			  }
			  if(rs!=null){
				  try {
					  rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				rs=null;
			  }
			  if(statement!=null){
				  try {
					  statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				statement=null;
			  }
			  if(conn!=null){
				  try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				  conn=null;
			  }
		  }
	}
}

