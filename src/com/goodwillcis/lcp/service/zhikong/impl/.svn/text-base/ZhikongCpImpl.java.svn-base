package com.goodwillcis.lcp.service.zhikong.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.ZhikongCpIndex;
import com.goodwillcis.lcp.service.zhikong.ZhikongCp;
import com.goodwillcis.lcp.util.CommonSQL;

public class ZhikongCpImpl implements ZhikongCp{

	private Logger logger=Logger.getLogger(ZhikongCpImpl.class);
	private String sql_quanju;
	@Override
	public int funGetCount(String code, String name,String pinyin, String wubi) {
		// TODO Auto-generated method stub
		String sql="select * from ("+CommonSQL.funGetCpInfoTongji(-1, -1, "")+") where 1=1 ";
		if(code!=""){
			sql=sql+" and cp_code like '%"+code+"%'";
		}
		if(name!=""){
			sql=sql+" and cp_name like '%"+name+"%'";
		}
		if(pinyin!=""){
			sql=sql+"  and (input_code_py like '%"+pinyin+"%' or input_code_py like '%"+pinyin.toUpperCase()+"%' or input_code_py like '%"+pinyin.toLowerCase()+"%')";
		}
		if(wubi!=""){
			sql=sql+"  and (INPUT_CODE_WB like '%"+wubi+"%' or INPUT_CODE_WB like '%"+wubi.toUpperCase()+"%' or INPUT_CODE_WB like '%"+wubi.toLowerCase()+"%')";
		}
		sql_quanju=sql+" ORDER BY CP_CODE";
		System.out.println(sql_quanju);
		sql="select count(*) hang from ("+sql+")";
		logger.info("funGetCount()查询的语句="+sql);
		DataSet dataSet=new DataSet();
		int row=0;
		try {
			dataSet.funSetDataSetBySql(sql);
			row=Integer.parseInt(dataSet.funGetFieldByCol(0 ,"HANG"));
			logger.info("funGetCount()查询的行数="+row);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("出错，查询异常，sql="+sql);
			row=0;
		}	
		return row;
	}

	@Override
	public ArrayList<ZhikongCpIndex> funGetZhikongByStartEndAndQuery(int start, int end) {
		// TODO Auto-generated method stub
		ArrayList<ZhikongCpIndex> zhikongCpList=null;
		String sql=sql_quanju;
		logger.info("funGetZhikongByStartEndAndQuery()查询的语句为"+sql);
		DataSet dataSet=new DataSet();
		try {
			dataSet.funSetDataSetBySql(sql,start,end);
			int row=dataSet.getRowNum();
			if(row>0){
				zhikongCpList=new ArrayList<ZhikongCpIndex>();
				for(int i=0;i<row;i++){
					ZhikongCpIndex cpIndex=new ZhikongCpIndex();
					String cp_code=dataSet.funGetFieldByCol(i,"CP_CODE");
					String cp_id=dataSet.funGetFieldByCol(i,"CP_ID");
					String cp_name=dataSet.funGetFieldByCol(i,"CP_NAME");
					String cp_start_date=dataSet.funGetFieldByCol1(i,"CP_START_DATE");
					String cp_status=dataSet.funGetFieldByCol(i,"CP_STATUS");
					
					
//					String fhrs=dataSet.funGetFieldByCol(i,"FHRS");
//					String ynrbl=dataSet.funGetFieldByCol(i,"YNRLJ");
//					String zxbl=dataSet.funGetFieldByCol(i,"ZXZLJ");//执行中路径
//					String wcbl=dataSet.funGetFieldByCol(i,"WCBL");
//					String tcbl=dataSet.funGetFieldByCol(i,"BYTC");
//					String bnrbl=dataSet.funGetFieldByCol(i,"BNRRS");
//					String wcdby=dataSet.funGetFieldByCol(i,"WCDBY");
//					String wnrbl=dataSet.funGetFieldByCol(i,"WNRRS");
					
					
					String fhrs=dataSet.funGetFieldByCol(i,"FHRS");
					String ynrbl=dataSet.funGetFieldByCol(i,"NRRS");
					String zxbl=dataSet.funGetFieldByCol(i,"ZXRS");//执行中路径
					String wcbl=dataSet.funGetFieldByCol(i,"WCRS");
					String tcbl=dataSet.funGetFieldByCol(i,"TCRS");
					String bnrbl=dataSet.funGetFieldByCol(i,"BNRRS");
					String wcdby=dataSet.funGetFieldByCol(i,"WCBYRS");
					String wnrbl=dataSet.funGetFieldByCol(i,"FHBNRRS");
					
					
					
					if(cp_status.equals("1")){
						cp_status="●";
					}else{
						cp_status="";

					}
					cpIndex.setCp_code(cp_code);
					cpIndex.setCp_id(cp_id);
					cpIndex.setCp_name(cp_name);
					cpIndex.setStart_time(cp_start_date);
					cpIndex.setIsStop(cp_status);
					cpIndex.setMatchIncomeCase(fhrs);
					cpIndex.setIncomeCase(ynrbl);
					cpIndex.setExecuteCase(zxbl);
					cpIndex.setCompleteCase(wcbl);
					cpIndex.setVariaExistCase(tcbl);
					cpIndex.setAntiIncomeCase(bnrbl);
					cpIndex.setCompleteCaseWithVaria(wcdby);
					cpIndex.setNotIncomeCase(wnrbl);
					zhikongCpList.add(cpIndex);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("出错，查询异常，funGetZhikongByStartEndAndQuery()的查询sql="+sql);
		}
		return zhikongCpList;
	}


}
