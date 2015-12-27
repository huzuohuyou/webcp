/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： DoctorLcpService .java
// 文件功能描述：ws调用函数接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.LCPService.service.DoctorLcpService;
import com.goodwillcis.lcp.LCPService.service.NurseLcpService;
import com.goodwillcis.lcp.LCPService.service.OrderLcpService;
import com.goodwillcis.lcp.LCPService.service.RegistPatientInfo;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.JiekouModel;
import com.goodwillcis.lcp.model.PatientInfo;
import com.goodwillcis.lcp.model.RouteExecuteInfo;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.JiekouUtils;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.UpdateUtil;

public class RegistPatientInfoImpl implements RegistPatientInfo {
	
	private RouteExecuteInfo info;
		
	private byte[] data=null;//查询信息
	
	private int isFunOpSuc=-1;//函数操作是否成功；1表示成功，-1表示失败
	
	private void setIsFunOpSuc(int isFunOpSuc) {
		this.isFunOpSuc = isFunOpSuc;
	}

	private int dataLen=-1;;//查询的数据长度

	private void setData(byte[] data) {
		this.data = data;
	}

	private static Logger logger = Logger.getLogger ( RegistPatientInfoImpl.class.getName () ) ; 


	/**
	 * 通过病人编号取得病人当前节点信息	2011-06-21
	 * @param PATIENT_NO
	 */
	private void funSetInfo(String PATIENT_NO){
		PatientInfo patientInfo=new PatientInfo();
		info=patientInfo.funGetPatientInfo1(PATIENT_NO);
	}

	@Override
	public int funCPRegPatient(String patientNo,int inSize, byte[] inRegData) {	
		logger.info("患者注册接口  funCPRegPatient()开始");
		logger.debug("接收到的xml内容为");
		logger.info(new String(inRegData));	
		if(inSize!=inRegData.length){
			logger.error("患者注册接口  funCPRegPatient()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inRegData));
			logger.error("出错！！！，无法继续，byte流大小不一致 返回-1");
			return -1;
		}
		if(inSize==0){
			logger.error("患者注册接口  funCPRegPatient()函数正在被调用");	
			logger.error("接收到的xml内容为");
			logger.error(new String(inRegData));
			logger.error("出错！！！，无法继续，inSize大小为0 返回值为-4");
			return -4;
		}
	//	long start=new Date().getTime();
		String dateTime=CommonUtil.getOracleToDate();
	//	long end=new Date().getTime();
	//	System.out.println("chaxunshijian="+(end-start));
	//	start=new Date().getTime();
		//funSetInfo(patientNo);
		//int hostipalID=info.getHostipalID();
		int hostipalID=LcpUtil.getHospitalID();
	//	end=new Date().getTime();
	//	System.out.println("chaxunbingren="+(end-start));
	//	start=new Date().getTime();
		
		
		
		DataSet dataSet=new DataSet();
		
		DatabaseClass database=LcpUtil.getDatabaseClass();
	
		
		int aa=dataSet.funSetDataSetByByte(inRegData);
	//	end=new Date().getTime();
	//	System.out.println("加载耗时="+(end-start));
	//	start=new Date().getTime();
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		if(iskeynull){
			logger.error("患者注册接口  funCPRegPatient()函数正在被调用");		
			logger.error("接收到的xml内容为");
			logger.error(new String(inRegData));
			logger.error("出错！！！，无法继续，PATIENT_NO必须有值才行，返回值为-5");
			return -5;
		}
		if(aa<0){
			logger.error("患者注册接口  funCPRegPatient()函数正在被调用");		
			logger.error("接收到的xml内容为");
			logger.error(new String(inRegData));
			logger.error("出错！！！，无法继续，dataset封装出错，返回值为-2");
			return -2;
		}else{
//			String sql_1="select * from lcp_patient_visit t where t.hospital_id='"+patientNo+"'";
//			DataSet dataSet2=new DataSet();
//			
//			dataSet2.funRunSql(sql_1);
//			int row_dataset2=dataSet2.getRowNum();
//			if(row_dataset2==0){
				HashMap<String, String> tableFieldName=null;
				String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
				String fieldValues=dataSet.funGetFieldValueInsertSQL(0);
				String sql="insert into LCP_PATIENT_VISIT (" +
						"HOSPITAL_ID,"+fieldNames+",CP_STATE,SYS_IS_DEL,SYS_LAST_UPDATE)values(" +
						""+hostipalID+","+fieldValues+",0,0,"+dateTime+")";
				String key=database.FunGetSvrKey();
				logger.info("sql语句为"+sql.replace("\r\n", ";"));
				int insertRow=0;
				try {
					insertRow=database.FunRunSQLCommand(key, sql);
					if(insertRow<=0){
						logger.error("患者注册接口  funCPRegPatient()函数正在被调用");				
						logger.error("接收到的xml内容为");
						logger.error(new String(inRegData));
						logger.error("出错！！！，无法继续，插入数据异常，插入数据库的语句为"+sql.replace("\r\n", ";"));
						logger.error("返回值为="+insertRow);
					}else{
						logger.info("正常插入，插入行数="+insertRow);
					}
				} catch (Exception e) {
					logger.fatal("患者注册接口  funCPRegPatient()函数正在被调用");	
					logger.fatal("接收到的xml内容为");
					logger.fatal(new String(inRegData));
					logger.fatal("出错！！！，无法继续，插入数据异常，插入数据库的语句为"+sql.replace("\r\n", ";"));
					insertRow=-1;
				}
//				end=new Date().getTime();
//				System.out.println("插入耗时="+(end-start));
				return insertRow;
//			}else{
//				String update="";
//			}
			
			
		}
	}

	/**
	 * 返回值说明：
	 * 			-1：病人下诊断但是不匹配
	 * 			-2：病人既没有下诊断也没有下手术
	 * 			-3：病人没有注册
	 * 			 0：未纳入
	 * 			 1：已纳入
	 * 			11：已完成
	 *			21：已退出
	 *			99：不纳入
	 */
	@Override
	public int funCPGetStatus1(String inPatiID) {
		//逻辑出现问题，修改的查询的顺序
		//修改人:刘植鑫；修改时间：2011-09-05
		logger.info("查询患者状态  funCPGetStatus()开始");
		logger.info("接收的患者号="+inPatiID);
		if(inPatiID==""||inPatiID==null){
			logger.error("查询患者状态  funCPGetStatus()函数正在被调用");		
			logger.error("无法继续，inPatiID必须有值才行，返回值为-4");
			return -4;
		}
		
		int result=-1;
		int row=-1;
		String sql="";
		//funSetInfo(inPatiID);
		try {
			DataSet dataSet=new DataSet();
			/**************************病人没有注册************************************/
			sql="select  t.patient_no  from lcp_patient_visit t where t.patient_no='"+inPatiID+"'";
			dataSet.funSetDataSetBySql(sql);
			row=dataSet.getRowNum();
			if(row<=0){
				logger.debug("查询患者状态  funCPGetStatus()函数正在被调用");
				logger.debug("查询的语句为"+sql.replace("\r\n", ";"));
				logger.debug("查询出的xml内容为");
				logger.debug(new String(dataSet.getXmldataset()));
				logger.debug("无法继续，病人没有注册，返回值为-3");
				logger.debug("查询患者状态  funCPGetStatus()函数结束");
				return -3;
			}
			/**************************查询病人状态************************************/
			

			
			long start=new Date().getTime();
			sql="SELECT CP_STATE FROM LCP_PATIENT_VISIT T WHERE T.PATIENT_NO='"+inPatiID+"'";
			dataSet.funSetDataSetBySql(sql);
			long end=new Date().getTime();
			logger.info("LCP_PATIENT_VISIThaoshi"+(end-start));
			result=Integer.parseInt(dataSet.funGetFieldByCol(0, "CP_STATE")); 
			if(result!=0){
				/*****************1已纳入，11已完成，21已退出，99不纳入**********************/
				logger.debug("查询的语句为"+sql.replace("\r\n", ";"));
				logger.debug("查询出来的值为"+result+"   *1已纳入，11已完成，21已退出，99不纳入*");
				return result;
			}else{
			
			
				logger.debug("病人没有纳入路径");
				/**************************病人既没有下诊断也没有下手术************************************/
				start=new Date().getTime();
				sql="select distinct(t.patient_no)patient_no from lcp_patient_log_income t where t.patient_no='"+inPatiID+"'";
				dataSet.funSetDataSetBySql(sql);
				end=new Date().getTime();
				System.out.println("lcp_patient_log_incomehaoshi"+(end-start));
				row=dataSet.getRowNum();
				if(row<=0){
					logger.debug("查询患者状态  funCPGetStatus()函数正在被调用");
					logger.debug("查询的语句为"+sql.replace("\r\n", ";"));
					logger.debug("查询出的xml内容为");
					logger.debug(new String(dataSet.getXmldataset()));
					logger.debug("无法继续，病人没有下诊断，返回值为-2");
					logger.debug("查询患者状态  funCPGetStatus()函数结束");
					return -2;
				}
				
				
				/**************************病人下诊断但是不匹配************************************/
				start=new Date().getTime();
				sql="SELECT DISTINCT (A.PATIENT_NO) PATIENT_NO FROM LCP_PATIENT_LOG_INCOME A, (SELECT AA.* FROM LCP_MASTER_INCOME AA, LCP_MASTER BB, (SELECT A.HOSPITAL_ID,A.CP_ID,A.CP_STATUS FROM LCP_HOSPITAL_VS_CP A UNION SELECT B.HOSPITAL_ID,B.CP_ID,1 CP_STATUS FROM LCP_MASTER B WHERE B.CP_ID>=10000) CC WHERE AA.HOSPITAL_ID = BB.HOSPITAL_ID AND BB.HOSPITAL_ID = CC.HOSPITAL_ID AND AA.CP_ID = BB.CP_ID AND BB.CP_ID = CC.CP_ID AND BB.CP_STATUS = 0 AND CC.CP_STATUS = 1) B WHERE A.INCOME_CODE = B.CP_INCOME_CODE AND A.INCOME_TYPE = B.CP_INCOME_TYPE AND A.PATIENT_NO='"+inPatiID+"'";
				dataSet.funSetDataSetBySql(sql);
				end=new Date().getTime();
				System.out.println("lcp_patient_log_incomehaoshi"+(end-start));
				logger.info("查询是否匹配的语句是"+sql);
				row=dataSet.getRowNum();
				if(row<=0){
					logger.debug("查询患者状态  funCPGetStatus()函数正在被调用");
					logger.debug("查询的语句为"+sql.replace("\r\n", ";"));
					logger.debug("查询出的xml内容为");
					logger.debug(new String(dataSet.getXmldataset()));
					logger.debug("无法继续，病人下诊断但是不匹配，返回值为-1");
					logger.debug("查询患者状态  funCPGetStatus()函数结束");
					return -1;
				}else{
					logger.debug("匹配，已经下了诊断，返回0");
					return 0;
				}
			}
		}catch (Exception e) {
			logger.fatal("查询患者状态  funCPGetStatus()函数正在被调用");
			logger.fatal("查询的语句为"+sql.replace("\r\n", ";"));
			logger.fatal("出错！！！，无法继续，sql语句异常，返回值为-5");
			logger.fatal("查询患者状态  funCPGetStatus()函数结束");
			result=-5;
		}
		return result;
	}
	
	@Override
	public int funCPGetStatus(String inPatiID) {
		//逻辑出现问题，修改的查询的顺序
		//修改人:刘植鑫；修改时间：2011-09-05
		logger.info("查询患者状态  funCPGetStatus()开始");
		logger.info("接收的患者号="+inPatiID);
		if(inPatiID==""||inPatiID==null){
			logger.error("查询患者状态  funCPGetStatus()函数正在被调用");		
			logger.error("无法继续，inPatiID必须有值才行，返回值为-4");
			return -4;
		}
		
		/**
		 * 新增    如果患者是在手术麻醉科室（根据emr中mr_on_line表里最新科室的状态来查询） 则返回当前病人未纳入路径（状态0）
		 */
		UpdateUtil uu = new UpdateUtil(); 
		String deptNo = uu.findCurrDeptNo(inPatiID);
		if(deptNo.equals("1130100")){
			return 99;
		}
		
		int result=-1;
//		int row=-1;
		String sql="";
		//funSetInfo(inPatiID);
		try {
			DataSet dataSet=new DataSet();
			
			String sql_1="select zhuce,cp_state,xiazhenduan,pipei from " +
					"(select count(*)zhuce,hospital_id  from lcp_patient_visit t " +
					"where t.patient_no='"+inPatiID+"' group by hospital_id)aa, " +
					"(SELECT CP_STATE,hospital_id FROM LCP_PATIENT_VISIT T " +
					"WHERE T.PATIENT_NO='"+inPatiID+"')bb," +
					"(select count(*)xiazhenduan ,hospital_id from lcp_patient_log_income t where " +
					"t.patient_no='"+inPatiID+"'  group by hospital_id)cc, " +
					"(select count(*)pipei  ,hospital_id from(SELECT A.PATIENT_NO ,a.hospital_id,B.cp_id FROM LCP_PATIENT_LOG_INCOME A," +
					"       (SELECT AA.*          FROM LCP_MASTER_INCOME AA,    " +
					"           LCP_MASTER BB,               (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_STATUS    " +
					"              FROM LCP_HOSPITAL_VS_CP A             " +
					"   UNION                SELECT B.HOSPITAL_ID, B.CP_ID, 1 CP_STATUS         " +
					"         FROM LCP_MASTER B                 WHERE B.CP_ID >= 10000) CC         " +
					"WHERE AA.HOSPITAL_ID = BB.HOSPITAL_ID           AND BB.HOSPITAL_ID = CC.HOSPITAL_ID      " +
					"     AND AA.CP_ID = BB.CP_ID           AND BB.CP_ID = CC.CP_ID         " +
					"  AND BB.CP_STATUS = 0           AND CC.CP_STATUS = 1) B WHERE A.INCOME_CODE = B.CP_INCOME_CODE  " +
					" AND A.INCOME_TYPE = B.CP_INCOME_TYPE   AND A.PATIENT_NO = '"+inPatiID+"' and (A.local_key='2_1_0_初步诊断' or A.income_type='手术'))  group by hospital_id)dd " +
					"where aa.hospital_id=bb.hospital_id(+)and aa.hospital_id=cc.hospital_id(+)and aa.hospital_id=dd.hospital_id(+)";

			dataSet.funSetDataSetBySql(sql_1);
			int row_1=dataSet.getRowNum();
			if(row_1<=0){
				logger.info("查询患者状态  funCPGetStatus()函数正在被调用");
				logger.info("查询的语句为"+sql.replace("\r\n", ";"));
				logger.info("查询出的xml内容为");
				logger.info(new String(dataSet.getXmldataset()));
				logger.info("无法继续，病人没有注册，返回值为-3");
				logger.info("查询患者状态  funCPGetStatus()函数结束");
				return -3;
			}else{
				String zhuce=dataSet.funGetFieldByCol2(0, "ZHUCE");
				String cp_state=dataSet.funGetFieldByCol2(0, "CP_STATE");
				String xiazhenduan=dataSet.funGetFieldByCol2(0, "XIAZHENDUAN");
				String pipei=dataSet.funGetFieldByCol2(0, "PIPEI");
				System.out.println(zhuce+"==="+cp_state+"==="+xiazhenduan+"==="+pipei);
				if(!cp_state.equals("0")){
					logger.info("查询的语句为"+sql.replace("\r\n", ";"));
					logger.info("查询出来的值为"+cp_state+"   *1已纳入，11已完成，21已退出，99不纳入*");
					return Integer.parseInt(cp_state);
				}else{
					logger.info("病人没有纳入路径");
					if(xiazhenduan==""){
						logger.info("查询患者状态  funCPGetStatus()函数正在被调用");
						logger.info("查询的语句为"+sql.replace("\r\n", ";"));
						logger.info("查询出的xml内容为");
						logger.info(new String(dataSet.getXmldataset()));
						logger.info("无法继续，病人没有下诊断，返回值为-2");
						logger.info("查询患者状态  funCPGetStatus()函数结束");
						return -2;
					}else{
						if(pipei==""){
							logger.info("查询患者状态  funCPGetStatus()函数正在被调用");
							logger.info("查询的语句为"+sql.replace("\r\n", ";"));
							logger.info("查询出的xml内容为");
							logger.info(new String(dataSet.getXmldataset()));
							logger.info("无法继续，病人下诊断但是不匹配，返回值为-1");
							logger.info("查询患者状态  funCPGetStatus()函数结束");
							return -1;
						}else{
							logger.info("匹配，已经下了诊断，返回0");
							return 0;
						}
					}
				}
			}
		}catch (Exception e) {
			logger.fatal("查询患者状态  funCPGetStatus()函数正在被调用");
			logger.fatal("查询的语句为"+sql.replace("\r\n", ";"));
			logger.fatal("出错！！！，无法继续，sql语句异常，返回值为-5");
			logger.fatal("查询患者状态  funCPGetStatus()函数结束");
			result=-5;
		}
		return result;
	}

	@Override
	public int funCPSetDiagnosisOrOperator(String inPatiID, int inSize, byte[] inDiagData,String income_type) {
		logger.info("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");			
		logger.info("接收到的xml内容为");
		logger.info(new String(inDiagData));
		if(inSize!=inDiagData.length){
			logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDiagData));
			logger.error("无法继续，byte流大小不一致 返回-1");
			return -1;
		}
		if(inPatiID==""||inPatiID==null){
			logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDiagData));
			logger.error("无法继续，inPatiID为空 ， 返回-3");
			return -3;
		}
		if(inSize==0){
			logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDiagData));
			logger.error("无法继续，inSize大小为0 ， 返回-4");
			return -4;
		}
		funSetInfo(inPatiID);
		int hostipalID=info.getHostipalID();
		DataSet dataSet=new DataSet();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		
	//	boolean isSuc=dataSet3.isDiaoyongDantiaoSuc();
		if(database==null){
			logger.error("无法继续，调用数据库异常");
		}

		String dateTime=CommonUtil.getOracleToDate();
		info.setHostipalID(hostipalID);
		String sql="";
		int insertRow=-1;
		int aa=dataSet.funSetDataSetByByte(inDiagData);
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		keyname.add("LOCAL_KEY");
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		if(iskeynull){
			logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");		
			logger.error("接收到的xml内容为");
			logger.error(new String(inDiagData));
			logger.error("无法继续，PATIENT_NO,LOCAL_KEY必须有值才行，返回值为-5");
			return -5;
		}
		if(aa<0){
			logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");		
			logger.error("接收到的xml内容为");
			logger.error(new String(inDiagData));
			logger.error("无法继续，dataset封装xml byte流，返回值为-6");
			return -6;
		}else{
			int row=dataSet.getRowNum();
			String table_name="operation";
			if(income_type.equals("诊断")){
				table_name="diagnosis";
			}
//			HashMap<String,JiekouModel > map=null;
			String local_code=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
			HashMap<String,String > map3=null;
			if(!local_code.equals("")){
				
				String sql_3="select t.local_code,t."+table_name+"_code core_code from lcp_match_"+table_name+" t where t.local_code in("+local_code+")";
				DataSet dataSet3=new DataSet();
				dataSet3.funSetDataSetBySql(sql_3);
				
				boolean isSuc=dataSet3.isDiaoyongDantiaoSuc();
				if(!isSuc){
					logger.error("无法继续，调用数据库异常");
				}

				map3=JiekouUtils.fungetCode1(dataSet3);
			}
			for(int i=0;i<row;i++){
				String local_code3=dataSet.funGetFieldByCol (i, "LOCAL_CODE");
				String core_code3="";
				if(map3!=null){
					boolean isExits3=map3.containsKey(local_code3);
					
					if(isExits3){
						core_code3=map3.get(local_code3);
					}
				}
				HashMap<String, String> tableFieldName=null;
				String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
				String fieldValues=dataSet.funGetFieldValueInsertSQL(i);
				String tempSql="insert into LCP_PATIENT_LOG_INCOME (" +
					"HOSPITAL_ID,"+fieldNames+",INCOME_TYPE,SYS_IS_DEL,SYS_LAST_UPDATE,INCOME_CODE" +
					")values(" +
					""+hostipalID+","+fieldValues+"" +
					",'"+income_type+"',0,"+dateTime+",'"+core_code3+"')";
				sql=sql+tempSql+"\r\n";
			}
			try {
				logger.info("sql语句为"+sql.replace("\r\n", ";"));
				String selectSql="";
				insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
				if(insertRow>0){
					logger.info("手术或者诊断接口  funCPSetDiagnosisOrOperator()正常插入");
					//诊断下成功后，查询诊断是否符合路径
					try {
						DataSet ds=new DataSet();
						
						selectSql="select zhuce,cp_state,xiazhenduan,pipei,cpid from " +
								"(select count(*)zhuce,hospital_id  from lcp_patient_visit t " +
								"where t.patient_no='"+inPatiID+"' group by hospital_id)aa, " +
								"(SELECT CP_STATE,hospital_id FROM LCP_PATIENT_VISIT T " +
								"WHERE T.PATIENT_NO='"+inPatiID+"')bb," +
								"(select count(*)xiazhenduan ,hospital_id from lcp_patient_log_income t where " +
								"t.patient_no='"+inPatiID+"'  group by hospital_id)cc, " +
								"(select count(*)pipei  ,max(hospital_id) hospital_id,cp_id cpid from(SELECT A.PATIENT_NO ,a.hospital_id,B.cp_id FROM LCP_PATIENT_LOG_INCOME A," +
								"       (SELECT AA.*          FROM LCP_MASTER_INCOME AA,    " +
								"           LCP_MASTER BB,               (SELECT A.HOSPITAL_ID, A.CP_ID, A.CP_STATUS    " +
								"              FROM LCP_HOSPITAL_VS_CP A             " +
								"   UNION                SELECT B.HOSPITAL_ID, B.CP_ID, 1 CP_STATUS         " +
								"         FROM LCP_MASTER B                 WHERE B.CP_ID >= 10000) CC         " +
								"WHERE AA.HOSPITAL_ID = BB.HOSPITAL_ID           AND BB.HOSPITAL_ID = CC.HOSPITAL_ID      " +
								"     AND AA.CP_ID = BB.CP_ID           AND BB.CP_ID = CC.CP_ID         " +
								"  AND BB.CP_STATUS = 0           AND CC.CP_STATUS = 1) B WHERE A.INCOME_CODE = B.CP_INCOME_CODE  " +
								" AND A.INCOME_TYPE = B.CP_INCOME_TYPE   AND A.PATIENT_NO = '"+inPatiID+"' and (trim(A.local_key) in ('2_1_0_初步诊断','3_1_0_最后诊断') or income_type='手术'))group by cp_id)dd " +
								"where aa.hospital_id=bb.hospital_id(+)and aa.hospital_id=cc.hospital_id(+)and aa.hospital_id=dd.hospital_id(+)";

						ds.funSetDataSetBySql(selectSql);
						int count=ds.getRowNum();
						if(count>0){
							String zhuce=ds.funGetFieldByCol2(0, "ZHUCE");
							String cp_state=ds.funGetFieldByCol2(0, "CP_STATE");
							String xiazhenduan=ds.funGetFieldByCol2(0, "XIAZHENDUAN");
							String pipei=ds.funGetFieldByCol2(0, "PIPEI");
							System.out.println(zhuce+"==="+cp_state+"==="+xiazhenduan+"==="+pipei);
							String cpid="";
							for(int i=0;i<count;i++){
								String cp_id=ds.funGetFieldByCol2(i,"CPID");
								cpid+=cp_id+",";
							}
							cpid=cpid.substring(0, cpid.length()-1);
							if(cp_state.equals("0")){
								logger.info("病人没有纳入路径");
								if(xiazhenduan!=""){
									if(pipei!=""){
										System.out.println("符合路径**********************");
										logger.info("匹配，已经下了诊断，返回0");
										String updateSql="update lcp_patient_visit set conform_cp_id=nvl((select max(cp_id) from lcp_master where cp_id in("+cpid+") and cp_start_date=(select max(cp_start_date) from lcp_master where cp_id in("+cpid+"))),0)," +
												" conform_master_id=nvl((select max(cp_master_id) from lcp_master where cp_id in("+cpid+") and cp_start_date=(select max(cp_start_date) from lcp_master where cp_id in("+cpid+"))),0),sys_last_update="+CommonUtil.getOracleToDate()+" where patient_no='"+inPatiID+"'";
										int res=database.FunRunSQLCommand(database.FunGetSvrKey(), updateSql);
										if(res>0){
											logger.info("修改病人路径信息正常..."+updateSql);
										}else{
											logger.info("修改病人路径信息出错..."+updateSql);
										}
									}
								}
							}
						}
					}catch (Exception e) {
						logger.fatal("查询患者状态 ");
						logger.fatal("查询的语句为"+selectSql);
					}
				}else{
					logger.error("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");		
					logger.error("接收到的xml内容为");
					logger.error(new String(inDiagData));
					logger.error("无法继续，数据库插入异常，sql语句为"+sql.replace("\r\n", ";"));
					logger.error("返回值为="+insertRow);
				}
			} catch (Exception e) {
				logger.fatal("手术或者诊断接口  funCPSetDiagnosisOrOperator()函数正在被调用");		
				logger.fatal("接收到的xml内容为");
				logger.fatal(new String(inDiagData));
				logger.fatal("无法继续，SQL语句异常，sql语句为"+sql.replace("\r\n", ";"));
			}
			return insertRow;
		}
	}

	@Override
	public int funCPSetDoctorItem(String inPatiID, int inSize, byte[] inDoctData) {
		logger.info("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
		logger.info("接收到的xml内容为");
		logger.info(new String(inDoctData));
		if(inSize!=inDoctData.length){
			logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDoctData));
			logger.error("出错！！！，无法继续，byte流大小不一致 返回-1");
			return -1;
		}
		if(inPatiID==""||inPatiID==null){
			logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDoctData));
			logger.error("出错！！！，无法继续，inPatiID为空 返回-3");
			return -3;
		}
		if(inSize==0){
			logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDoctData));
			logger.error("出错！！！，无法继续，inSize为0 返回-4");
			return -4;
		}
		long start=new Date().getTime();
		funSetInfo(inPatiID);
		long end=new Date().getTime();
		System.out.println("查询病人信息耗时"+(end-start));
		DataSet dataSet=new DataSet();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		start=new Date().getTime();
		String dateTime=CommonUtil.getOracleToDate();
		end=new Date().getTime();
		System.out.println("查询数据库时间耗时"+(end-start));
		int hostipalID=info.getHostipalID();
		int insertRow=-1;
		String sql="";
		start=new Date().getTime();
		int aa=dataSet.funSetDataSetByByte(inDoctData);
		end=new Date().getTime();
		System.out.println("加载耗时"+(end-start));
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		keyname.add("LOCAL_KEY");
		keyname.add("USER_ID");//自动签名使用，必须传送
		keyname.add("USER_NAME");
		start=new Date().getTime();
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		end=new Date().getTime();
		System.out.println("判断非空耗时"+(end-start));
		if(iskeynull){
			logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDoctData));
			logger.error("出错！！！，无法继续，PATIENT_NO，LOCAL_KEY，USER_ID，USER_NAME必须传送， 返回-5");
			return -5;
		}
		if(aa<0){
			logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inDoctData));
			logger.error("出错！！！，dataset封装出错， 返回-6");
			return -6;
		}else{
			DoctorLcpService doctor=new DoctorLcpServiceImpl();
			int row=dataSet.getRowNum();
			System.out.println("接受数据行数"+row);
			DataSet dataSet3=new DataSet();
			if(row>0){
				HashMap<String,JiekouModel > map=null;
				String local_code=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
				HashMap<String,String > map3=null;
				String doctor_nos="";
				if(!local_code.equals("")&&local_code!=""){
					String sql_3="select t.local_code,t.doctor_code core_code from lcp_match_doctor t where t.local_code in("+local_code+")";
					
					dataSet3.funSetDataSetBySql(sql_3);
					doctor_nos=dataSet3.funGetOneFieldStringValues("CORE_CODE");
					map3=JiekouUtils.fungetCode1(dataSet3);
					System.out.println("==="+dataSet3.getRowNum());
				}
				
				
				if(doctor_nos!=""&&(!doctor_nos.equals(""))&&info.getCpID()!=0){	
					DataSet dataSet2=new DataSet();
					String sql_2="select t.cp_node_doctor_id TABLE_ID,t.cp_node_doctor_item_id TABLE_ITEM_ID,t.doctor_no CODE from lcp_node_doctor_item t " +
							"where t.cp_id="+info.getCpID()+" and t.cp_node_id="+info.getCpNodeID()+" and t.doctor_no in("+doctor_nos+")";
					System.out.println(sql_2);
					dataSet2.funSetDataSetBySql(sql_2);	
					System.out.println("==="+dataSet2.getRowNum());
					map=JiekouUtils.fungetCode(dataSet2);
				}
				
				start=new Date().getTime();
				for(int i=0;i<row;i++){
					start=new Date().getTime();
					String local_code3=dataSet.funGetFieldByCol (i, "LOCAL_CODE");
					
					String core_code3="";
					if(map3!=null){
						boolean isExits3=map3.containsKey(local_code3);
						if(isExits3){
							core_code3=map3.get(local_code3);
						}
					}
				
					String table_id="0";
					String table_item_id="0";
					if(map!=null){
						boolean isExits=map.containsKey(core_code3);
						System.out.println(core_code3+isExits);
						if(isExits){
							table_id=map.get(core_code3).getCp_node_table_id();
							table_item_id=map.get(core_code3).getCp_node_table_item_id();
						}
					}
					HashMap<String, String> tableFieldName=null;
					long start1=new Date().getTime();
					String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
					long end1=new Date().getTime();
					System.out.println("查询名称耗时"+(end1-start1));
					long start2=new Date().getTime();
					String fieldValues=dataSet.funGetFieldValueInsertSQL(i);
					long end2=new Date().getTime();
					System.out.println("查询值耗时"+(end2-start2));
					String tempSql="insert into LCP_PATIENT_LOG_DOCTOR (" +
						"HOSPITAL_ID,"+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE," +
						"CP_ID,CP_NODE_ID,CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_ITEM_ID,DOCTOR_NO" +
						")values(" +
						""+hostipalID+","+fieldValues+"" +
						",0,"+dateTime+"," +
						""+info.getCpID()+","+info.getCpNodeID()+","+table_id+","+table_item_id+",'"+core_code3+"')";
					sql=sql+tempSql+"\r\n";
					end=new Date().getTime();
					System.out.println("组装单一sql耗时"+(end-start));
				}
				end=new Date().getTime();
				System.out.println("组装sql耗时"+(end-start));
				try {
					start=new Date().getTime();
					insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
					if(insertRow<1){
						logger.error("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
						logger.error("接收到的xml内容为");
						logger.error(new String(inDoctData));
						logger.error("出错！！！，sql语句插入失败， sql="+sql.replace("\r\n", ";"));
					}else{
						logger.info("正常插入，sql="+sql.replace("\r\n", ";"));
					}
					end=new Date().getTime();
					System.out.println("插入数据库耗时"+(end-start));
				} catch (Exception e) {
					logger.fatal("诊疗记录  funCPSetDoctorItem()函数正在被调用");			
					logger.fatal("接收到的xml内容为");
					logger.fatal(new String(inDoctData));
					logger.fatal("出错！！！，sql语句插入失败， sql="+sql.replace("\r\n", ";"));
					return insertRow;
					
				}
				if(insertRow<0){
					return insertRow;
				}
				//这里更新patientdoctor表
				System.out.println("cp_id"+info.getCpID());
				start=new Date().getTime();
				if(info.getCpID()!=0){
					ArrayList<String>doctorNos=dataSet3.funGetOneFieldListValues("CORE_CODE");
					if(doctorNos!=null){
						String userID=dataSet.funGetFieldByCol(0, "USER_ID");
						String userName=dataSet.funGetFieldByCol(0, "USER_NAME");
						try {
							doctor.funSignPatientDoctor(userID, userName, info, doctorNos,dateTime);
						} catch (Exception e) {
							logger.fatal("出错！！！，自动签名异常");
						}
						
					}
				}
				end=new Date().getTime();
				System.out.println("自动签名耗时"+(end-start));
				}
			return insertRow;
		}
	}

	@Override
	public int funCPSetNurseItem(String inPatiID, int inSize, byte[] inNurData) {
		logger.info("护理记录  funCPSetNurseItem()函数正在被调用");			
		logger.info("接收到的xml内容为");
		logger.info(new String(inNurData));
		if(inSize!=inNurData.length){
			logger.error("护理记录  funCPSetNurseItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inNurData));
			logger.error("出错！！！，无法继续，byte流大小不一致 返回-1");
			return -1;
		}
		if(inPatiID==""||inPatiID==null){
			logger.error("护理记录  funCPSetNurseItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inNurData));
			logger.error("出错！！！，无法继续，inPatiID为空 返回-3");
			return -3;
		}
		if(inSize==0){
			logger.error("护理记录  funCPSetNurseItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inNurData));
			logger.error("出错！！！，无法继续，inSize为0  返回-4");
			return -4;
		}
		funSetInfo(inPatiID);
		DataSet dataSet=new DataSet();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String dateTime=CommonUtil.getOracleToDate();
		int hostipalID=info.getHostipalID();
		String sql="";
		int insertRow=-1;
		int aa=dataSet.funSetDataSetByByte(inNurData);
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		keyname.add("LOCAL_KEY");
		keyname.add("USER_ID");//自动签名使用，必须传送
		keyname.add("USER_NAME");
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		if(iskeynull){
			logger.error("护理记录  funCPSetNurseItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inNurData));
			logger.error("出错！！！，无法继续，PATIENT_NO，LOCAL_KEY，USER_ID，USER_NAME必须传送， 返回-5");
			return -5;
		}
		if(aa<0){
			return insertRow;
		}else{
			NurseLcpService nurse=new NurseLcpServiceImpl();
			int row=dataSet.getRowNum();
			if(row>0){
				DataSet dataSet3=new DataSet();
				HashMap<String,JiekouModel > map=null;
				String local_code=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
				HashMap<String,String > map3=null;
				String nurse_nos="";
				if(!local_code.equals("")&&local_code!=""){
					String sql_3="select t.local_code,t.nurse_code core_code from lcp_match_nurse t where t.local_code in("+local_code+")";
					
					dataSet3.funSetDataSetBySql(sql_3);
					
					map3=JiekouUtils.fungetCode1(dataSet3);
					System.out.println(sql_3);
					System.out.println("==="+dataSet3.getRowNum());
					nurse_nos=dataSet3.funGetOneFieldStringValues("CORE_CODE");
				}

				if(nurse_nos!=""&&!nurse_nos.equals("")&&info.getCpID()!=0){	
					DataSet dataSet2=new DataSet();
					String sql_2="select t.cp_node_nurse_id TABLE_ID,t.cp_node_nurse_item_id TABLE_ITEM_ID,t.nurse_no CODE from lcp_node_nurse_item t " +
							"where t.cp_id="+info.getCpID()+" and t.cp_node_id="+info.getCpNodeID()+" and t.nurse_no in("+nurse_nos+")";
					System.out.println(sql_2);
					dataSet2.funSetDataSetBySql(sql_2);	
					System.out.println("==="+dataSet2.getRowNum());
					map=JiekouUtils.fungetCode(dataSet2);
				}
				
				for(int i=0;i<row;i++){
					
					
	//				long start=new Date().getTime();
					String local_code3=dataSet.funGetFieldByCol (i, "LOCAL_CODE");
					
					String core_code3="";
					if(map3!=null){
						boolean isExits3=map3.containsKey(local_code3);
						if(isExits3){
							core_code3=map3.get(local_code3);
						}
					}
					
					String table_id="0";
					String table_item_id="0";
					if(map!=null){
						boolean isExits=map.containsKey(core_code3);
						System.out.println(core_code3+isExits);
						if(isExits){
							table_id=map.get(core_code3).getCp_node_table_id();
							table_item_id=map.get(core_code3).getCp_node_table_item_id();
						}
					}
					
					
					
					
					
					HashMap<String, String> tableFieldName=null;
					String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
					String fieldValues=dataSet.funGetFieldValueInsertSQL(i);
					String tempSql="insert into LCP_PATIENT_LOG_NURSE (" +
						"HOSPITAL_ID,"+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE," +
						"CP_ID,CP_NODE_ID,CP_NODE_NURSE_ID,CP_NODE_NURSE_ITEM_ID,NURSE_NO" +
						")values(" +
						""+hostipalID+","+fieldValues+"" +
						",0,"+dateTime+"," +
						""+info.getCpID()+","+info.getCpNodeID()+","+table_id+",'"+table_item_id+"','"+core_code3+"')";
					sql=sql+tempSql+"\r\n";
				}
				try {
					insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
					if(insertRow<0){
						logger.error("护理记录  funCPSetNurseItem()函数正在被调用");			
						logger.error("接收到的xml内容为");
						logger.error(new String(inNurData));
						logger.error("出错！！！，sql语句插入失败， sql="+sql.replace("\r\n", ";"));
					}else{
						logger.info("正常插入，sql="+sql.replace("\r\n", ";"));
					}
				} catch (Exception e) {
					logger.fatal("护理记录  funCPSetNurseItem()函数正在被调用");			
					logger.fatal("接收到的xml内容为");
					logger.fatal(new String(inNurData));
					logger.fatal("出错！！！，sql语句异常， sql="+sql.replace("\r\n", ";"));
					return insertRow;
				}
				if(insertRow<0){
					return insertRow;
				}
				if(info.getCpID()!=0){
					ArrayList<String>NURSENos=dataSet3.funGetOneFieldListValues("CORE_CODE");
					if(NURSENos!=null){
						String userID=dataSet.funGetFieldByCol(0, "USER_ID");
						String userName=dataSet.funGetFieldByCol(0, "USER_NAME");
						try {
							nurse.funSignPatientNURSE(userID, userName, info, NURSENos,dateTime);
						} catch (Exception e) {
							logger.fatal("出错！！！，自动签名异常");
						}
						
					}
				}
			}
			return insertRow;
		}
	}

	@Override
	public int funCPSetOrderItem(String inPatiID, int inSize, byte[] inOrderData) {
		logger.info("医嘱记录  funCPSetOrderItem()函数正在被调用");			
		logger.info("接收到的xml内容为");
		logger.info(new String(inOrderData));
		if(inSize!=inOrderData.length){
			logger.error("医嘱记录  funCPSetOrderItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inOrderData));
			logger.error("出错！！！，无法继续，byte流大小不一致 返回-1");
			return -1;
		}
		if(inSize==0){
			logger.error("医嘱记录  funCPSetOrderItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inOrderData));
			logger.error("出错！！！，无法继续，inSize为0  返回-4");
			return -4;
		}
		if(inPatiID==""||inPatiID==null){
			logger.error("医嘱记录  funCPSetOrderItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inOrderData));
			logger.error("出错！！！，无法继续，inPatiID为空 返回-3");
			return -3;
		}
		funSetInfo(inPatiID);
		DataSet dataSet=new DataSet();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String dateTime=CommonUtil.getOracleToDate();
		int hostipalID=info.getHostipalID();
		String sql="";
		int insertRow=-1;
		int aa=dataSet.funSetDataSetByByte(inOrderData);
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		keyname.add("LOCAL_KEY");
		keyname.add("USER_ID");//自动签名使用，必须传送
		keyname.add("USER_NAME");
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		if(iskeynull){
			logger.error("医嘱记录  funCPSetOrderItem()函数正在被调用");			
			logger.error("接收到的xml内容为");
			logger.error(new String(inOrderData));
			logger.error("出错！！！，无法继续，PATIENT_NO，LOCAL_KEY，USER_ID，USER_NAME必须传送， 返回-5");
			return -5;
		}
		if(aa<0){
			return insertRow;
		}else{
			int row=dataSet.getRowNum();
			if(row>0){
				OrderLcpService order=new OrderLcpServiceImpl();
				
				
				HashMap<String,JiekouModel > map=null;
				String local_code=dataSet.funGetOneFieldStringValues("LOCAL_CODE");
				HashMap<String,String > map3=null;
				String order_nos="";
				if(!local_code.equals("")&&local_code!=""){
					String sql_3="select t.local_code,t.order_code core_code from lcp_match_order t where t.local_code in("+local_code+")";
					DataSet dataSet3=new DataSet();
					dataSet3.funSetDataSetBySql(sql_3);
					
					map3=JiekouUtils.fungetCode1(dataSet3);
					System.out.println(sql_3);
					System.out.println("==="+dataSet3.getRowNum());
					order_nos=dataSet3.funGetOneFieldStringValues("CORE_CODE");
				}
				
				
				if(order_nos!=""&&!local_code.equals("")&&info.getCpID()!=0){	
					DataSet dataSet2=new DataSet();
					String sql_2="select t.cp_node_order_id TABLE_ID,t.cp_node_order_item_id TABLE_ITEM_ID,t.order_no CODE from lcp_node_order_item t " +
							"where t.cp_id="+info.getCpID()+" and t.cp_node_id="+info.getCpNodeID()+" and t.order_no in("+order_nos+")";
					System.out.println(sql_2);
					dataSet2.funSetDataSetBySql(sql_2);	
					System.out.println("==="+dataSet2.getRowNum());
					map=JiekouUtils.fungetCode(dataSet2);
				}
				
				
				
				
				
				for(int i=0;i<row;i++){
					
//					long start=new Date().getTime();
					String local_code3=dataSet.funGetFieldByCol (i, "LOCAL_CODE");
					String core_code3="";
					if(map3!=null){
						boolean isExits3=map3.containsKey(local_code3);
						
						if(isExits3){
							core_code3=map3.get(local_code3);
						}
					}
					
					String table_id="0";
					String table_item_id="0";
					if(map!=null){
						boolean isExits=map.containsKey(core_code3);
						System.out.println(core_code3+isExits);
						if(isExits){
							table_id=map.get(core_code3).getCp_node_table_id();
							table_item_id=map.get(core_code3).getCp_node_table_item_id();
						}
					}
					
					
					
					
//					String orderNo=dataSet.funGetFieldByCol(i, "ORDER_NO");
//					String orderID=order.funGetCpNodeOrderID(orderNo, info);
					HashMap<String, String> tableFieldName=null;
					String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
					String fieldValues=dataSet.funGetFieldValueInsertSQL(i);
					String tempSql="insert into LCP_PATIENT_LOG_ORDER (" +
						"HOSPITAL_ID,"+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE," +
						"CP_ID,CP_NODE_ID,CP_NODE_ORDER_ID,CP_NODE_ORDER_ITEM_ID,ORDER_NO" +
						")values(" +
						""+hostipalID+","+fieldValues+"" +
						",0,"+dateTime+"," +
						""+info.getCpID()+","+info.getCpNodeID()+","+table_id+",'"+table_item_id+"','"+core_code3+"')";
					sql=sql+tempSql+"\r\n";
				}
				try {
					insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
					if(insertRow<0){
						logger.error("医嘱记录  funCPSetOrderItem()函数正在被调用");			
						logger.error("接收到的xml内容为");
						logger.error(new String(inOrderData));
						logger.error("出错！！！，无法继续，sql插入异常，sql="+sql.replace("\r\n", ";"));
					}else{
						logger.info("正常插入，sql="+sql.replace("\r\n", ";"));
					}
				} catch (Exception e) {

					logger.fatal("医嘱记录  funCPSetOrderItem()函数正在被调用");			
					logger.fatal("接收到的xml内容为");
					logger.fatal(new String(inOrderData));
					logger.fatal("出错！！！，无法继续，sql插入异常，sql="+sql.replace("\r\n", ";"));
					return insertRow;
				}
				if(insertRow<0){
					return insertRow;
				}
				if(info.getCpID()!=0){
					ArrayList<String>ORDERNos=dataSet.funGetOneFieldListValues("ORDER_NO");
					if(ORDERNos!=null){
					String userID=dataSet.funGetFieldByCol(0, "USER_ID");
					String userName=dataSet.funGetFieldByCol(0, "USER_NAME");
					try {
						order.funSignPatientOrder(userID, userName, info, ORDERNos,dateTime);
	
					} catch (Exception e) {
						logger.fatal("出错！！！，无法继续，自动签名异常");
					}
					}
				}
			}
			return insertRow;
		}
	}

	@Override
	public byte[] funCpGetNodeInfo(String inPatiID, int inNodeID,int inItemType) {
		logger.info("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
		if(inPatiID==""||inPatiID==null){
			logger.error("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
			logger.error("出错！！！，无法继续，inPatiID为空 ");
			setIsFunOpSuc(-1);
			setDataLen(-1);
			return null;
		}
		String table="";
		if(inItemType==11){
			table="LCP_PATIENT_DOCTOR_POINT";
		}else if(inItemType==12){
			table="LCP_PATIENT_DOCTOR_ITEM";
		}else if(inItemType==13){
			table="(SELECT C.HOSPITAL_ID,       C.PATIENT_NO,       C.CP_ID,       C.CP_NODE_ID,       B.DOCTOR_CODE,       B.LOCAL_CODE  FROM LCP_PATIENT_DOCTOR_POINT C, LCP_MATCH_DOCTOR B WHERE C.CP_NODE_DOCTOR_ID = B.LOCAL_CODE   AND C.HOSPITAL_ID = B.HOSPITAL_ID)";
		}else if(inItemType==21){
			table="LCP_PATIENT_NURSE_POINT";
		}else if(inItemType==22){
			table="LCP_PATIENT_NURSE_ITEM";
		}else if(inItemType==23){
			table="(SELECT C.HOSPITAL_ID,       C.PATIENT_NO,       C.CP_ID,       C.CP_NODE_ID,       B.NURSE_CODE,       B.LOCAL_CODE  FROM LCP_PATIENT_NURSE_POINT C, LCP_MATCH_NURSE B WHERE C.CP_NODE_NURSE_ID = B.LOCAL_CODE   AND C.HOSPITAL_ID = B.HOSPITAL_ID)";
		}else if(inItemType==31){
			table="LCP_PATIENT_ORDER_POINT";
		}else if(inItemType==32){
			table="LCP_PATIENT_ORDER_ITEM";
		}else if(inItemType==33){
			table="(SELECT C.HOSPITAL_ID,       C.PATIENT_NO,       C.CP_ID,       C.CP_NODE_ID,       B.ORDER_CODE,       B.LOCAL_CODE  FROM LCP_PATIENT_ORDER_POINT C, LCP_MATCH_ORDER B WHERE C.CP_NODE_ORDER_ID = B.LOCAL_CODE   AND C.HOSPITAL_ID = B.HOSPITAL_ID)";
		}else{
			logger.error("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
			logger.error("出错！！！，无法继续，接收的参数不符合规定inNodeID="+inNodeID+" inItemType="+inItemType);
			setIsFunOpSuc(-1);
			setDataLen(-1);
			return null;
		}
		funSetInfo(inPatiID);
		String sql="SELECT * FROM "+table+" A WHERE A.CP_ID="+info.getCpID()+" AND A.PATIENT_NO='"+inPatiID+"' AND A.HOSPITAL_ID="+info.getHostipalID();
		if(inNodeID<=-1){
			logger.error("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
			logger.error("出错！！！，无法继续，接收的参数不符合规定inNodeID="+inNodeID+" inItemType="+inItemType);
			return null;
		}else
		if(inNodeID==-1){
			
		}else
		if(inNodeID==0){
			sql=sql+" AND A.CP_NODE_ID="+info.getCpNodeID();
		}else {
			int maxNodeID=info.getMaxCpNodeID();
			if(maxNodeID<inNodeID){
				logger.error("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
				logger.error("出错！！！，无法继续，接收的参数节点>当前路径的最大节点号");
				return null;
			}
				
			sql=sql+" AND A.CP_NODE_ID="+inNodeID;

		}
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		byte[]aa=null;
		try {
			logger.info("查询sql="+sql.replace("\r\n", ";"));
			aa=databaseClass.FunGetDataByteSetBySQL(sql);
			if(aa!=null){
				setIsFunOpSuc(1);
				setData(aa);
				setDataLen(aa.length);
			}else{
				setIsFunOpSuc(-1);
				setData(aa);
				setDataLen(-1);
			}
		} catch (Exception e) {
			logger.fatal("查找节点内容  funCpGetNodeInfo()函数正在被调用");			
			logger.fatal("出错！！！，无法继续，sql语句异常"+sql.replace("\r\n", ";"));
			setIsFunOpSuc(-1);
			setData(aa);
			setDataLen(-1);
		}
		
		return aa;
	}

	private void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}

	@Override
	public int funCpGetIDByPatient(String patiID) {
		if(patiID==""||patiID==null){
			logger.error("查找节点  funCpGetIDByPatient()函数正在被调用");			
			logger.error("出错！！！，无法继续，inPatiID为空 ");
			return -3;
		}
		String sql="SELECT CP_ID FROM LCP_PATIENT_NODE T WHERE T.PATIENT_NO='"+patiID+"'";
		DataSet dataSet=new DataSet();
		try {
			dataSet.funSetDataSetBySql(sql);
		} catch (Exception e) {
			logger.fatal("查找节点  funCpGetIDByPatient()函数正在被调用");			
			logger.fatal("出错！！！，查询失败，sql="+sql.replace("\r\n", ";"));
		}
		
		int row=dataSet.getRowNum();
		if(row>0){
			String cpidStr=dataSet.funGetFieldByCol(0, "CP_ID");
			int cpidInt=Integer.parseInt(cpidStr);
			return cpidInt;
		}else{
			return 0;
		}
	}

	@Override
	public int funCPSetFirstPage(String inPatiID, int inSize, byte[] inPatiData) {
		logger.info("funCPSetFirstPage()开始");
		logger.info("接收到的xml内容为");
		logger.info(new String(inPatiData));
		if(inSize!=inPatiData.length){
			logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(inPatiData));
			logger.error("byte流大小和传递的大小值不一致返回-1值");
			return -1;
		}
		if(inPatiID==""||inPatiID==null){
			logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(inPatiData));
			logger.error("inPatiID为空 返回-3值");
			return -3;
		}
		if(inSize==0){
			logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(inPatiData));
			logger.error("inSize大小为0， 返回-4值");
			return -4;
		}
		funSetInfo(inPatiID);
		DataSet dataSet=new DataSet();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String dateTime=CommonUtil.getOracleToDate();
		int hostipalID=info.getHostipalID();
		String sql="";
		int insertRow=-1;
		int aa=dataSet.funSetDataSetByByte(inPatiData);
		ArrayList<String> keyname=new ArrayList<String>();
		keyname.add("PATIENT_NO");
		boolean iskeynull=dataSet.funVerKeyNull(keyname);
		if(iskeynull){
			logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(inPatiData));
			logger.info("PATIENT_NO必须有值， 返回-5值");
			return -5;
		}
		if(aa<0){
			logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(inPatiData));
			logger.info("封装dataset出错");
			return insertRow;
		}else{
			int row=dataSet.getRowNum();
			if(row>0){
				for(int i=0;i<row;i++){
					HashMap<String, String> tableFieldName=null;
					String fieldNames=dataSet.funGetFieldInsertSQL(tableFieldName);
					String fieldVals=dataSet.funGetFieldValueInsertSQL(i);
					fieldNames=fieldNames+",HOSPITAL_ID,SYS_IS_DEL,SYS_LAST_UPDATE";
					fieldVals=fieldVals+","+hostipalID+",0,"+dateTime;
					String tempSql="INSERT INTO LCP_PATIENT_FIRSTPAGE ("+fieldNames+")VALUES("+fieldVals+")\r\n";
					sql=sql+tempSql;
				}
				try {
					insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
					if(insertRow<0){
						logger.error("患者首页  funCPSetFirstPage()函数正在被调用");
						logger.error("接收到的xml内容为");
						logger.error(new String(inPatiData));
						logger.error("插入数据失败，插入数据库中的语句为"+sql.replace("\r\n", ";"));
					}else{
						logger.info("插入数据库中的语句为"+sql.replace("\r\n", ";"));
						logger.info("患者首页注册成功！");
					}
				} catch (Exception e) {
					logger.fatal("患者首页  funCPSetFirstPage()函数正在被调用");
					logger.fatal("接收到的xml内容为");
					logger.fatal(new String(inPatiData));
					logger.fatal("插入数据失败，插入数据库中的语句为"+sql.replace("\r\n", ";"));
					insertRow=-1;
				}
			}
			
			return insertRow;
		}
	}

	@Override
	public byte[] funCpGetMatchItem(String tableName, String localCode) {
		logger.info("funCpGetMatchItem()开始");
		byte[] aa=new byte[0];
		int row=-1;
		String sql="";
		if(tableName.equals("LCP_MATCH_DEPT")){
			sql="SELECT DEPT_CODE  FROM LCP_MATCH_DEPT T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else if(tableName.equals("LCP_MATCH_DIAGNOSIS")){
			sql="SELECT DIAGNOSIS_CODE FROM LCP_MATCH_DIAGNOSIS T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else if(tableName.equals("LCP_MATCH_DOCTOR")){
			sql="SELECT DOCTOR_CODE FROM LCP_MATCH_DOCTOR T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else if(tableName.equals("LCP_MATCH_NURSE")){
			sql="SELECT NURSE_CODE FROM LCP_MATCH_NURSE T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else if(tableName.equals("LCP_MATCH_OPERATION")){
			sql="SELECT OPERATION_CODE FROM LCP_MATCH_OPERATION T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else if(tableName.equals("LCP_MATCH_ORDER")){
			sql="SELECT ORDER_CODE FROM LCP_MATCH_ORDER T WHERE T.LOCAL_CODE='"+localCode+"'";
		}else{
			logger.error("查询匹配  funCpGetMatchItem()函数正在被调用");
			logger.error("接收到的参数不正常表名为"+tableName);
			setIsFunOpSuc(-1);
			return null;
		}
		logger.info("查询的语句为"+sql.replace("\r\n", ";"));
		try {
			DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
			aa=databaseClass.FunGetOneDataByteSetBySQL(sql);
			logger.info("查询出的结果为"+new String(aa));
			row=databaseClass.FunGetRowCountBySql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			row=-1;
			logger.fatal("查询匹配  funCpGetMatchItem()函数正在被调用");
			logger.fatal("查询异常，查询的语句为"+sql.replace("\r\n", ";"));
		}
		if(row>0){
			setIsFunOpSuc(1);
		}else{
			setIsFunOpSuc(-1);

		}
		return aa;
	}

	@Override
	public byte[] funCpGetData() {
		return this.data;
	}

	@Override
	public int funCPDischarge(int size, byte[] data, String patientNo) {
		logger.info("更新患者出院信息  funCPDischarge()开始");
		if(patientNo==""||patientNo==null){
			logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，长度不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("DEPT_DISCHARGE_FROM");
		keyName.add("DISCHARGE_DATE_TIME");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，出院科室和出院时间是必填项，返回值为-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，接收到的xml行数为0，返回值为-4");
			return -4;
		}
		String sql="";
		String time=CommonUtil.getOracleToDate();
		for(int i=0;i<row;i++){
			String ks=dataSet.funGetFieldSQlWithDenoByCol(i, "DEPT_DISCHARGE_FROM");
			String cyrq=dataSet.funGetFieldSQlWithDenoByCol(i, "DISCHARGE_DATE_TIME");
			sql=sql+"update lcp_patient_visit set DISCHARGED_DATE="+cyrq+" ,SYS_LAST_UPDATE="+time+" where PATIENT_NO='"+patientNo+"'\r\n";
			sql=sql+"update LCP_PATIENT_FIRSTPAGE set DISCHARGE_DATE_TIME="+cyrq+" ,DEPT_DISCHARGE_FROM="+ks+" ,SYS_LAST_UPDATE="+time+"  where PATIENT_NO='"+patientNo+"'\r\n";
		}
		
		DatabaseClass class1=LcpUtil.getDatabaseClass();
		logger.info("sql语句为"+sql.replace("\r\n", ";"));
		int updateRow=-1;
		try {
			updateRow=class1.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(updateRow<0){
				logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("funCPDischarge(),更能语句出错，生成的sql语句是"+sql.replace("\r\n", ";"));
			}else if (updateRow==0){
				logger.error(" funCPDischarge()函数更新行数为0,可能原因是数据库中没有此患者，不过概率为0,需要检查数据库情况");
			}else{
			}
		} catch (Exception e) {
			logger.error("更新患者出院信息  funCPDischarge()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("funCPDischarge(),更能语句出错，生成的sql语句是"+sql.replace("\r\n", ";"));
		}
		return updateRow;
	}

	@Override
	public int funCPGetDictVariation(String a) {
		logger.info("获取变异字典数据包，funCPGetDictVariation()开始");
		String sql="select * from dcp_dict_variation t";
		DatabaseClass class1=LcpUtil.getDatabaseClass();
		byte[]aa=null;
		try {
			aa=class1.FunGetDataByteSetBySQL(sql);
		} catch (Exception e) {
			return -1;
		}
		setData(aa);
		setDataLen(aa.length);
		logger.info("获取变异字典数据包，funCPGetDictVariation()结束");
		return 1;
	}

	@Override
	public int funCPGetOrderVariation(String patientNo, int inNodeID) {
		logger.info("取得医嘱变异数据包  funCPGetOrderVariation()开始");
		if(patientNo==""||patientNo==null){
			logger.error("funCPGetOrderVariation()函数无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		funSetInfo(patientNo);
		String sql="SELECT * FROM LCP_PATIENT_LOG_ORDER_VARIA A WHERE A.CP_ID="+info.getCpID()+" AND A.PATIENT_NO='"+patientNo+"' AND A.HOSPITAL_ID="+info.getHostipalID();
		if(inNodeID<=-1){
			logger.error("funCPGetOrderVariation()函数无法继续，输入的inNodeID为负值，返回值为-1");
			return -1;
		}else
		if(inNodeID==-1){
			
		}else
		if(inNodeID==0){
			sql=sql+" AND A.CP_NODE_ID="+info.getCpNodeID();
		}else {
			int maxNodeID=info.getMaxCpNodeID();
			if(maxNodeID<inNodeID)
				return -1;
			sql=sql+" AND A.CP_NODE_ID="+inNodeID;

		}
		logger.info("生成的sql语句为"+sql.replace("\r\n", ";"));
		DatabaseClass database=LcpUtil.getDatabaseClass();
		byte[] aa=null;
		try {
			aa=database.FunGetDataByteSetBySQL(sql);
			logger.info("查询出的xml数据为");
			logger.info(new String(aa));
		} catch (Exception e) {
			logger.error("取得医嘱变异数据包  funCPGetOrderVariation()开始");
			logger.error("查询数据库异常 sql语句为"+sql.replace("\r\n", ";"));
			return -1;
		}
		setData(aa);
		setDataLen(aa.length);
		return 1;		
	}

	@Override
	public int funCPSetFee(int size, byte[] data, String patientNo) {
		logger.info("患者费用信息  funCPSetFee()开始");
		if(patientNo==""||patientNo==null){
			logger.error("患者费用信息  funCPSetFee()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("患者费用信息  funCPSetFee()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，byte流为空或者大小不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("PATIENT_NO");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("患者费用信息  funCPSetFee()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，PATIENT_NO为必填项，返回值为-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("患者费用信息  funCPSetFee()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，dataset封装异常，返回值为-4");
			return -4;
		}
		String dateTime=CommonUtil.getOracleToDate();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		
		
		ArrayList<String > newName=new ArrayList<String>();
		newName.add("HOSPITAL_ID");
		ArrayList<String > newValues=new ArrayList<String>();
		newValues.add(""+info.getHostipalID()+"");		
		
		
		
		String sql="";
		for(int i=0;i<row;i++){
			String fieldNames=dataSet.funGetFieldInsertSQL(newName);
			String fieldValues=dataSet.funGetFieldValueInsertSQL(i,newValues);
			String sql1="insert into LCP_PATIENT_FEE (" +
			""+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE" +
			")values(" +
			""+fieldValues+"" +
			",0,"+dateTime+")\r\n";
			sql=sql+sql1;
		}
		logger.info("sql"+sql.replace("\r\n", ";"));
		int insertRow=-1;
		try {
			insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(insertRow<0){
				logger.error("患者费用信息  funCPSetFee()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，插入数据库异常，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			logger.fatal("患者费用信息  funCPSetFee()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，插入数据库异常，sql="+sql.replace("\r\n", ";"));
		}
		return insertRow;
	}

	@Override
	public int funCPSetItemSet(String patientNo, int inStateType, int size,
			byte[] data) {
		logger.info("更新医嘱内容  funCPSetItemSet()开始");
	 	if(patientNo==""||patientNo==null){
	 		logger.error("更新医嘱内容  funCPSetItemSet()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
	 		return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新医嘱内容  funCPSetItemSet()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入byte流大小不一致，返回值为-2");
			return -2;
		}
		String table="";
		if(inStateType<1||inStateType>2){
			logger.error("更新医嘱内容  funCPSetItemSet()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，inStateType应该为1或或者2，而接受到的为"+inStateType);
			return -4;
		}else if(inStateType==1){
			table=" LCP_PATIENT_ORDER_POINT ";
		}else if(inStateType==2){
			table=" LCP_PATIENT_ORDER_ITEM ";
		}
		funSetInfo(patientNo);
		String dateTime=CommonUtil.getOracleToDate();
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("CP_NODE_ID");
		keyName.add("CP_NODE_ORDER_ID");
		keyName.add("CP_NODE_ORDER_ITEM_ID");
		keyName.add("STATE_ITEM");
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新医嘱内容  funCPSetItemSet()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，CP_NODE_ID，CP_NODE_ORDER_ID，CP_NODE_ORDER_ITEM_ID STATE_ITEM必须有值返回-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		String sql="";
		for(int i=0;i<row;i++){
			sql=sql+"update "+table+" t set STATE_ITEM="+dataSet.funGetFieldSQlWithDenoByCol(i, "STATE_ITEM")+"" +
					",SYS_LAST_UPDATE="+dateTime+"" +
					" where  t.hospital_id="+info.getHostipalID()+" and t.patient_no='"+patientNo+"' and t.cp_id="+info.getCpID()+" and " +
					"t.cp_node_id="+dataSet.funGetFieldByCol(i, "CP_NODE_ID")+" and " +
					"t.cp_node_order_id= "+dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID")+"\r\n";
		}
		int insertRow=-1;
		DatabaseClass database=LcpUtil.getDatabaseClass();
		logger.info("sql"+sql.replace("\r\n", ";"));
		try {
			insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(insertRow<0){
				logger.error("更新医嘱内容  funCPSetItemSet()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，更新数据库异常，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			logger.fatal("更新医嘱内容  funCPSetItemSet()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，更新数据库异常，sql="+sql.replace("\r\n", ";"));
		}
		return insertRow;
	}

	@Override
	public int funCPSetLIS(String patientNo, byte[] data, int size) {
		logger.info("更新患者检验检查信息  funCPSetLIS()开始");
		if(patientNo==""||patientNo==null){
			logger.error("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入byte流大小不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("PATIENT_NO");
		keyName.add("LOCAL_KEY");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，PATIENT_NO，LOCAL_KEY必须有值返回-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，dataset封装异常，返回值为-4");
			return -4;
		}
		String dateTime=CommonUtil.getOracleToDate();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		
		
		ArrayList<String > newName=new ArrayList<String>();
		newName.add("HOSPITAL_ID");
		newName.add("CP_ID");
		newName.add("CP_NODE_ID");
		ArrayList<String > newValues=new ArrayList<String>();
		newValues.add(""+info.getHostipalID()+"");
		newValues.add(""+info.getCpID()+"");
		newValues.add(""+info.getCpNodeID()+"");
		
		
		
		
		String sql="";
		for(int i=0;i<row;i++){
			//HashMap<String, String> tableFieldName=null;用于替换字段名使用
			String fieldNames=dataSet.funGetFieldInsertSQL(newName);
			String fieldValues=dataSet.funGetFieldValueInsertSQL(i,newValues);
			String sql1="insert into LCP_PATIENT_LOG_LIS (" +
			""+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE" +
			")values(" +
			""+fieldValues+"" +
			",0,"+dateTime+")\r\n";
			sql=sql+sql1;
		}
		int insertRow=-1;
		logger.info("sql="+sql.replace("\r\n", ";"));
		try {
			insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(insertRow<0){
				logger.error("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，数据库操作失败，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			logger.fatal("更新患者检验检查信息  funCPSetLIS()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，数据库操作失败，sql="+sql.replace("\r\n", ";"));
		}
		return insertRow;
	}

	@Override
	public int FunCPSetOrderState(String patientNo, byte[] data, int size) {
		logger.info("更新医嘱状态  FunCPSetOrderState()开始");
		if(patientNo==""||patientNo==null){
			logger.error("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入byte流大小不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("LOCAL_KEY");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，LOCAL_KEY必须有值返回-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，dataset封装异常，返回值为-4");
			return -4;
		}
		String sql="";
		for(int i=0;i<row;i++){
			sql=sql+"update LCP_PATIENT_LOG_ORDER set ORDER_STATUS="+dataSet.funGetFieldSQlWithDenoByCol(i, "ORDER_STATUS")+"" +
					" where HOSPITAL_ID="+info.getHostipalID()+" and PATIENT_NO='"+patientNo+"' and LOCAL_KEY="+dataSet.funGetFieldSQlWithDenoByCol(i, "LOCAL_KEY")+"\r\n";
		}
		DatabaseClass database=LcpUtil.getDatabaseClass();
		String key=database.FunGetSvrKey();
		int updateRow=-1;
		try {
			updateRow=database.FunRunSQLCommand(key, sql);
			if(updateRow<0){
				logger.error("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，数据库操作失败，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			logger.fatal("更新医嘱状态  FunCPSetOrderState()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，数据库操作失败，sql="+sql.replace("\r\n", ";"));		}
		return updateRow;
	}

	@Override
	public int funCPSetOrderVariation(String patientNo, byte[] data, int size) {
		logger.info("更新医嘱变异funCPSetOrderVariation()开始");
		if(patientNo==""||patientNo==null){
			logger.error("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入byte流大小不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("PATIENT_NO");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，LOCAL_KEY必须有值返回-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，dataset封装异常，返回值为-4");
			return -4;
		}
		String dateTime=CommonUtil.getOracleToDate();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		
		
		ArrayList<String > newName=new ArrayList<String>();
		newName.add("HOSPITAL_ID");
		newName.add("CP_ID");
		newName.add("CP_NODE_ID");
		ArrayList<String > newValues=new ArrayList<String>();
		newValues.add(""+info.getHostipalID()+"");
		newValues.add(""+info.getCpID()+"");
		newValues.add(""+info.getCpNodeID()+"");
		
		
		
		
		String sql="";
		for(int i=0;i<row;i++){
			String fieldNames=dataSet.funGetFieldInsertSQL(newName);
			String fieldValues=dataSet.funGetFieldValueInsertSQL(i,newValues);
			String sql1="insert into LCP_PATIENT_LOG_ORDER_VARIA (" +
			""+fieldNames+",AUTO_ID,HOSPITAL_ID,SYS_IS_DEL,SYS_LAST_UPDATE" +
			")values(" +
			""+fieldValues+",((SELECT MAX(AUTO_ID)+1 AUTO_ID FROM(SELECT AUTO_ID" +
					" FROM LCP_PATIENT_LOG_ORDER_VARIA UNION SELECT 0 AUTO_ID FROM DUAL))),"+info.getHostipalID()+"" +
			",0,"+dateTime+")\r\n";
			sql=sql+sql1;
		}
		int insertRow=-1;
		try {
			insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(insertRow<0){
				logger.error("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，数据库操作异常，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			logger.fatal("更新医嘱变异funCPSetOrderVariation()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，数据库操作异常，sql="+sql.replace("\r\n", ";"));
		}
		return insertRow;
	}

	@Override
	public int funCPSetPACS(int size, byte[] data, String patientNo) {
		logger.info("更新检查报告信息   funCPSetPACS()开始");
		if(patientNo==""||patientNo==null){
			logger.error("更新检查报告信息   funCPSetPACS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入的病人为空，返回值为-3");
			return -3;
		}
		if(data.length!=size||size==0){
			logger.error("更新检查报告信息   funCPSetPACS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，输入byte流大小不一致，返回值为-2");
			return -2;
		}
		funSetInfo(patientNo);
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetByByte(data);
		ArrayList<String > keyName=new ArrayList<String>();
		keyName.add("PATIENT_NO");
		keyName.add("LOCAL_KEY");
		boolean iskeyNUll=dataSet.funVerKeyNull(keyName);
		if(iskeyNUll){
			logger.error("更新检查报告信息   funCPSetPACS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，LOCAL_KEY必须有值返回-5");
			return -5;
		}
		int row=dataSet.getRowNum();
		if(row==0){
			logger.error("更新检查报告信息   funCPSetPACS()函数正在被调用");
			logger.error("接收到的xml内容为");
			logger.error(new String(data));
			logger.error("无法继续，dataset封装异常，返回值为-4");
			return -4;
		}
		String dateTime=CommonUtil.getOracleToDate();
		DatabaseClass database=LcpUtil.getDatabaseClass();
		
		
		ArrayList<String > newName=new ArrayList<String>();
		newName.add("HOSPITAL_ID");
		newName.add("CP_ID");
		newName.add("CP_NODE_ID");
		ArrayList<String > newValues=new ArrayList<String>();
		newValues.add(""+info.getHostipalID()+"");
		newValues.add(""+info.getCpID()+"");
		newValues.add(""+info.getCpNodeID()+"");
		
		
		
		String sql="";
		for(int i=0;i<row;i++){
			String fieldNames=dataSet.funGetFieldInsertSQL(newName);
			String fieldValues=dataSet.funGetFieldValueInsertSQL(i,newValues);
			String sql1="insert into LCP_PATIENT_LOG_PACS (" +
			""+fieldNames+",SYS_IS_DEL,SYS_LAST_UPDATE" +
			")values(" +
			""+fieldValues+"" +
			",0,"+dateTime+")\r\n";
			sql=sql+sql1;
		}
		int insertRow=-1;
		try {
			insertRow=database.FunRunSqlByFile(sql.getBytes("GB2312"));
			if(insertRow<0){
				logger.error("更新检查报告信息   funCPSetPACS()函数正在被调用");
				logger.error("接收到的xml内容为");
				logger.error(new String(data));
				logger.error("无法继续，数据库操作异常，sql="+sql.replace("\r\n", ";"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.fatal("更新检查报告信息   funCPSetPACS()函数正在被调用");
			logger.fatal("接收到的xml内容为");
			logger.fatal(new String(data));
			logger.fatal("无法继续，数据库操作异常，sql="+sql.replace("\r\n", ";"));
		}
		return insertRow;
	}

	@Override
	public int funCpIsFunOpSuc() {
		// TODO Auto-generated method stub
		return this.isFunOpSuc;
	}

	@Override
	public int funCpGetDataLen() {
		// TODO Auto-generated method stub
		return this.dataLen;
	}
	

}
