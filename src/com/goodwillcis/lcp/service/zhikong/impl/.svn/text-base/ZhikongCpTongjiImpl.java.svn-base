package com.goodwillcis.lcp.service.zhikong.impl;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import com.goodwillcis.cp.util.LcpUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.model.TongjiFeeMax;
import com.goodwillcis.lcp.service.zhikong.ZhikongCpTongji;
import com.goodwillcis.lcp.util.CommonUtil;

public class ZhikongCpTongjiImpl implements ZhikongCpTongji {
	DatabaseClass db=LcpUtil.getDatabaseClass();
	String startDate=" admission_date >=to_date('2011-10-01','YYYY-MM-DD')  ";

	@Override
	public int funGetCpCount(String sql) {
		// TODO Auto-generated method stub
		DataSet dataSet = new DataSet();
		sql = "select count(*) hang from(" + sql + ")";
		dataSet.funSetDataSetBySql(sql);
		int row = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));
		return row;
	}

	@Override
	public String funGetCpContextUseXML(String sql, int start, int end) {
		// TODO Auto-generated method stub
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql, start, end);
		int row = dataSet.getRowNum();
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?><DATASET>";
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				result = result + "<JILU>";
				String cp_code = dataSet.funGetFieldByCol(i, "CP_CODE");
				String cp_name = dataSet.funGetFieldByCol(i, "CP_NAME");
				String cp_id = dataSet.funGetFieldByCol(i, "CP_ID");
				String cp_start_date = dataSet.funGetFieldByCol1(i,
						"CP_START_DATE");
				String cp_status = dataSet.funGetFieldByCol(i, "CP_STATUS");
				if (cp_status.equals("1")) {
					cp_status = "●";
				} else {
					cp_status = "";
				}
				
				
//				String fhbls = dataSet.funGetFieldByCol(i, "FHBLS");
//				String nrbls = dataSet.funGetFieldByCol(i, "NRBLS");
//				String nrbfb = dataSet.funGetFieldByCol(i, "NRBFB");
//				String wcbls = dataSet.funGetFieldByCol(i, "WCBLS");
//				String wcbfb = dataSet.funGetFieldByCol(i, "WCBFB");
//				String wcdby = dataSet.funGetFieldByCol(i, "WCDBY");
//				String bybfb = dataSet.funGetFieldByCol(i, "BYBFB");

				
				
				String fhbls = dataSet.funGetFieldByCol(i, "FHRS");
				String nrbls = dataSet.funGetFieldByCol(i, "NRRS");
				String nrbfb = dataSet.funGetFieldByCol(i, "NRBFB");
				String wcbls = dataSet.funGetFieldByCol(i, "WCRS");
				String wcbfb = dataSet.funGetFieldByCol(i, "WCBFB");
				String wcdby = dataSet.funGetFieldByCol(i, "WCBYRS");
				String bybfb = dataSet.funGetFieldByCol(i, "WCBYBFB");

				
				result = result + "" + "<CP_CODE>" + cp_code + "</CP_CODE>"
						+ "<CP_NAME>" + cp_name + "</CP_NAME>" + "<CP_ID>"
						+ cp_id + "</CP_ID>" + "<CP_START_DATE>"
						+ cp_start_date + "</CP_START_DATE>" + "<CP_STATUS>"
						+ cp_status + "</CP_STATUS>" + "<FHBLS>" + fhbls
						+ "</FHBLS>" + "<NRBLS>" + nrbls + "</NRBLS>"
						+ "<NRBFB>" + nrbfb + "</NRBFB>" + "<WCBLS>" + wcbls
						+ "</WCBLS>" + "<WCBFB>" + wcbfb + "</WCBFB>"
						+ "<WCDBY>" + wcdby + "</WCDBY>" + "<BYBFB>" + bybfb
						+ "</BYBFB>" + "</JILU>";
			}
		}
		result = result + "</DATASET>";
		return result;
	}

	@Override
	public String funGetTopNaruUseXML(boolean isDesc, int top,String starttime,String endtime) {
		// TODO Auto-generated method stub
		String endDate=endtime+" 23:59:59";
		String sql = "";
		String title = "最高纳入率的" + top + " 条路径";
		
		//String sql_1=CommonSQL.funGetCpInfoTongji(-1, -1, "");
		if (isDesc) {
			sql="select cp_master_id,nvl((select cp_name from lcp_master where cp_id = b.cp_master_id),(select cp_name from dcp_master where cp_id=b.cp_master_id)) cp_name, nrrs, fhrs, decode(fhrs,0,0,trunc((nrrs / fhrs), 4)*100) nrl "+
				"  from (select cp_master_id, count(patient_no) nrrs                                                                              "+
				"          from lcp_patient_visit                                                                                                 "+
				"         where conform_master_id>0 and cp_master_id > 0 and cp_state not in(0,99) and admission_date>=to_date('"+starttime+"','YYYY-MM-DD') and admission_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
				"         group by cp_master_id) b,                                                                                               "+
				"       (select a.conform_master_id, count(a.patient_no) fhrs                                                                     "+
				"          from lcp_patient_visit a                                                                                               "+
				"         where a.conform_master_id>0 and a.cp_master_id > 0 and a.cp_state not in(0,99) and a.conform_master_id in(select cp_id from dcp_master) and admission_date>=to_date('"+starttime+"','YYYY-MM-DD') and admission_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
				"         group by a.conform_master_id) c                                                                                         "+
				" where b.cp_master_id = c.conform_master_id                                                                   "+
				" order by nrl desc                                                                                                               ";

		} else {
			sql = "select cp_master_id,nvl((select cp_name from lcp_master where cp_id = b.cp_master_id),(select cp_name from dcp_master where cp_id=b.cp_master_id)) cp_name, nrrs, fhrs, decode(fhrs,0,0,trunc((nrrs / fhrs), 4)*100) nrl "+
			"  from (select cp_master_id, count(patient_no) nrrs                                                                              "+
			"          from lcp_patient_visit                                                                                                 "+
			"         where conform_master_id>0 and cp_master_id > 0 and cp_state not in(0,99) and admission_date>=to_date('"+starttime+"','YYYY-MM-DD') and admission_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
			"         group by cp_master_id) b,                                                                                               "+
			"       (select a.conform_master_id, count(a.patient_no) fhrs                                                                     "+
			"          from lcp_patient_visit a                                                                                               "+
			"         where a.conform_master_id>0 and a.cp_master_id > 0 and a.cp_state not in(0,99)  and a.conform_master_id in(select cp_id from dcp_master) and admission_date>=to_date('"+starttime+"','YYYY-MM-DD') and admission_date<=to_date('"+endDate+"','YYYY-MM-DD HH24:MI:SS') "+
			"         group by a.conform_master_id) c                                                                                         "+
			" where b.cp_master_id = c.conform_master_id                                                                   "+
			" order by nrrs desc                                                                                                               ";
			title = "最高纳入人数的" + top + " 条路径";
		}
		//System.out.println("topSql====:"+sql);
		DataSetClass dataSet=db.FunGetDataSetBySQL(sql,top);
		//DataSet dataSet = new DataSet();
		//dataSet.funSetDataSetBySql(sql);
		int row = dataSet.FunGetRowCount();
		if (row > 0) {
			String result="";
			if(isDesc){
				result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' "
					+ " caption='" + title
					+ "' xAxisName='路径名称' yAxisName='纳入率' numberSuffix='%' "
					+ " formatNumberScale='0' useRoundEdges='1'>";
			}else{
				result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' "
					+ " caption='" + title
					+ "' xAxisName='路径名称' yAxisName='纳入人数' numberSuffix='' "
					+ " formatNumberScale='0' useRoundEdges='1'>";
			}
			
			float totalNaru = 0;
			int totalNRRS=0;

			for (int i = 0; i < row; i++) {
				String cp_id = dataSet.FunGetDataAsStringByColName(i, "CP_MASTER_ID");
				String cp_name = dataSet.FunGetDataAsStringByColName(i, "CP_NAME");
				String nrbfb = dataSet.FunGetDataAsStringByColName(i, "NRL");
				String nrrs = dataSet.FunGetDataAsStringByColName(i, "NRRS");
				totalNaru = Float.parseFloat(nrbfb) + totalNaru;
				totalNRRS=Integer.parseInt(nrrs)+totalNRRS;
				if(isDesc){
					result = result + "<set label='" + cp_name + "' value='"
					+ nrbfb + "' link='zhikong_cp_tongji_danyi.jsp?cp_id="+cp_id+"&s="+starttime+"&e="+endtime+"'/>";
			
					
				}else{
					result = result + "<set label='" + cp_name + "' value='"
					+ nrrs + "' link='zhikong_cp_tongji_danyi.jsp?cp_id="+cp_id+"&s="+starttime+"&e="+endtime+"'/>";
				}
				
			}
			float aveNaru = totalNaru / top;
			int avgNrrs=totalNRRS/top;
			aveNaru = Math.round(aveNaru * 100) / 100f;
			if(isDesc){
				result = result
				+ "<trendLines><line startValue='"
				+ aveNaru
				+ "' color='359A35' displayvalue='平均纳入率' valueOnRight='1'/></trendLines></chart>";
			}else{
				result = result
				+ "<trendLines><line startValue='"
				+ avgNrrs
				+ "' color='359A35' displayvalue='平均纳入人数' valueOnRight='1'/></trendLines></chart>";
			}
			
			return result;
		} else {
			String result="";
			if(isDesc){
				result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' "
					+ " caption='" + title
					+ "' xAxisName='路径名称' yAxisName='纳入率' numberSuffix='%' "
					+ " formatNumberScale='0' useRoundEdges='1'>";
			}else{
				result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' "
					+ " caption='" + title
					+ "' xAxisName='路径名称' yAxisName='纳入人数' numberSuffix='' "
					+ " formatNumberScale='0' useRoundEdges='1'>";
			}
			
			float totalNaru = 0;
			int totalNRRS=0;

			result = result + "<set label='' value='0'/>";

			float aveNaru = totalNaru / top;
			int avgNrrs=totalNRRS/top;
			aveNaru = Math.round(aveNaru * 100) / 100f;
			if(isDesc){
				result = result
				+ "<trendLines><line startValue='0' color='359A35' displayvalue='平均纳入率' valueOnRight='1'/></trendLines></chart>";
			}else{
				result = result
				+ "<trendLines><line startValue='0' color='359A35' displayvalue='平均纳入人数' valueOnRight='1'/></trendLines></chart>";
			}
				return result;
		}
	}

	@Override
	public String funGetTianshuByCId(String cp_id) {
		// TODO Auto-generated method stub
		String sql = "select * "
				+ "  from (select max(zyts) wcmaxzyts, "
				+ "               min(zyts) wcminzyts, "
				+ "               ceil(avg(zyts)) wcpjzyts "
				+ "          from (select aa.cp_id, "
				+ "                       bb.*, "
				+ "                       trunc( trunc(discharged_date, 'DD') - trunc(admission_date, 'DD')) + 1 zyts "
				+ "                  from (select distinct * "
				+ "                          from (select a.hospital_id, a.patient_no, a.cp_id "
				+ "                                  from lcp_patient_node a "
				+ "                                 where a.cp_id = "
				+ cp_id
				+ ")) aa, "
				+ "                      (select b.hospital_id, "
				+ "                              b.patient_no, "
				+ "                             b.patient_name, "
				+ "                          b.admission_date, "
				+ "                         b.discharged_date "
				+ "                         from  lcp_patient_visit b "
				+ "                        where b.cp_state in (11,21)) bb, "
				+ "                          lcp_master cc             "
				+ "                where aa.hospital_id = bb.hospital_id "
				+ "                  and aa.patient_no = bb.patient_no "
				+ "                  and bb.admission_date is not null "
				+ "                  and bb.discharged_date is not null "
				+ "                  and cc.cp_id="
				+ cp_id
				+ " "
				+ "                  and bb.admission_date >= cc.cp_start_date "
				+ "  and bb.discharged_date <= (case when cc.cp_stop_date is null then sysdate  else cc.cp_stop_date end) "
				+ "                  )), "
				+ "      (select max(zyts) bymaxzyts, "
				+ "              min(zyts) byminzyts, "
				+ "              ceil(avg(zyts)) bypjzyts "
				+ "         from (select aa.cp_id, "
				+ "                      bb.*, "
				+ "                       trunc( trunc(discharged_date, 'DD') - trunc(admission_date, 'DD')) + 1 zyts "
				+ "                 from (select distinct * "
				+ "                         from (select a.hospital_id, a.patient_no, a.cp_id "
				+ "                                 from lcp_patient_node a "
				+ "                                where a.cp_id = "
				+ cp_id
				+ ")) aa, "
				+ "                      (select b.hospital_id, "
				+ "                              b.patient_no, "
				+ "                              b.patient_name, "
				+ "                               b.admission_date, "
				+ "                             b.discharged_date "
				+ "                         from lcp_patient_visit b "
				+ "                        where  b.cp_state in (21)) bb "
				+ "                        ,  "
				+ "                          lcp_master cc            "
				+ "                where aa.hospital_id = bb.hospital_id "
				+ "                  and aa.patient_no = bb.patient_no "
				+ "                  and bb.admission_date is not null "
				+ "                  and bb.discharged_date is not null "
				+ "               and cc.cp_id="
				+ cp_id
				+ " "
				+ "                  and bb.admission_date >= cc.cp_start_date "
				+ "  and bb.discharged_date <= (case when cc.cp_stop_date is null then sysdate  else cc.cp_stop_date end) "
				+

				"  )), "
				+ "      (select max(zyts) bnrmaxzyts, "
				+ "              min(zyts) bnrminzyts, "
				+ "              ceil(avg(zyts)) bnrpjzyts "
				+ "         from (select cc.*, "
				+ "                       trunc( trunc(discharged_date, 'DD') - trunc(admission_date, 'DD')) + 1 zyts "
				+ "                 from (select aa.* "
				+ "                         from (select b.hospital_id, "
				+ "                                      b.patient_no, "
				+ "                                      b.patient_name, "
				+ "                                      b.discharged_date, "
				+ "                                      b.admission_date, "
				+ "                                      b.cp_state "
				+ "                                 from "
				+ "                                      lcp_patient_visit     b, "
				+ "                                      lcp_master c "
				+ "                                where b.cp_state in (99) "
				+ "                                  and b.discharged_date is not null "
				+ "                                  and b.admission_date is not null "
				+ "                                    and c.cp_id="
				+ cp_id
				+ " "
				+ "                  and b.admission_date >= c.cp_start_date "
				+ "  and b.discharged_date <= (case when c.cp_stop_date is null then sysdate  else c.cp_stop_date end) "
				+ "                                  ) aa, "
				+ "                              (select b.* "
				+ "                                 from lcp_master_income      a, "
				+ "                                      lcp_patient_log_income b "
				+ "                                where a.cp_id = "
				+ cp_id
				+ " "
				+ "                                  and a.hospital_id = b.hospital_id "
				+ "                                  and a.cp_income_type = b.income_type "
				+ "                                  and a.cp_income_code = b.income_code) bb "
				+ "                        where aa.hospital_id = bb.hospital_id "
				+ "                          and aa.patient_no = bb.patient_no) cc)), "
				+ "      (select t.cp_days_min, t.cp_days_max "
				+ "         from lcp_master t "
				+ "       where t.cp_id = (select max(cp_id) from lcp_master where cp_master_id="+cp_id+"))";
		//System.out.println("-_--------"+sql);
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='效率统计' baseFont='宋体' baseFontSize='12' "
				+ " useRoundEdges='1' legendBorderAlpha='0' numberSuffix='天'>"
				+ "<categories font='宋体' fontSize='12'>"
				+ "<category label='平均住院日'/>"
				+ "<category label='最小住院日' />"
				+ "<category label='最大住院日'/>"
				+ "</categories>"
				+ "<dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "WCPJZYTS")
				+ "' />"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "WCMINZYTS")
				+ "' link='t3111.htm'/>"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "WCMAXZYTS")
				+ "' />"
				+ "</dataset>"
				+
				// "<dataset seriesname='变异退出病例' color='F6BD0F' showValues='1' alpha='90'>"+
				// "<set value='"+dataSet.funGetFieldByCol(0,"BYPJZYTS")+"' />"+
				// "<set value='"+dataSet.funGetFieldByCol(0,"BYMINZYTS")+"' />"+
				// "<set value='"+dataSet.funGetFieldByCol(0,"BYMAXZYTS")+"' />"+
				// "</dataset>"+
				"<dataset seriesname='符合条件但不纳入病例' color='F6BD0F' showValues='1' alpha='90'>"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "BNRPJZYTS")
				+ "' />"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "BNRMINZYTS")
				+ "' />"
				+ "<set value='"
				+ dataSet.funGetFieldByCol(0, "BNRMAXZYTS")
				+ "' />"
				+ "</dataset>"
				+ "<trendLines>"
				+ "<line startValue='10' endValue='14' color='8BBA00' thickness='1' alpha='20' showOnTop='0' valueOnRight='1' "
				+ " displayValue='路径定义天数范围（"
				+ dataSet.funGetFieldByCol(0, "CP_DAYS_MIN")
				+ "~"
				+ dataSet.funGetFieldByCol(0, "CP_DAYS_MAX")
				+ "天）' isTrendZone='1'/>" + "</trendLines>" + "</chart>";
		return result;
	}

	@Override
	public String funGetZhuYuanFenblByCpIdUseXML(String cp_id) {
		// TODO Auto-generated method stub
		String tianshu_sql = "select max(bnrmaxzyts) maxitanshu, min(bnrminzyts) mintianshu"
				+ "  from (select max(zyts) bnrmaxzyts,"
				+ "              min(zyts) bnrminzyts,"
				+ "              round(avg(zyts), 2) bnrpjzyts"
				+ "         from (select cc.*,"
				+ "                      trunc( trunc(discharged_date, 'DD') - trunc(admission_date, 'DD')+1) zyts"
				+ "                 from (select aa.*,bb.cp_id"
				+ "                         from (select b.hospital_id,"
				+ "                                      b.patient_no,"
				+ "                                      b.admission_date,"
				+ "                                      b.discharged_date,"
				+ "                                      b.cp_state"
				+ "                                 from"
				+ "                                      lcp_patient_visit     b"
				+ "                                where  b.cp_state in (99)"
				+ "                                  and b.admission_date is not null"
				+ "                                  and b.discharged_date is not null) aa,"
				+ "                              (select b.*,a.cp_id"
				+ "                                 from lcp_master_income      a,"
				+ "                                      lcp_patient_log_income b"
				+ "                                where a.cp_id = "
				+ cp_id
				+ ""
				+ "                                  and a.hospital_id = b.hospital_id"
				+ "                                  and a.cp_income_type = b.income_type"
				+ "                                  and a.cp_income_code = b.income_code) bb,"
				+ "                                  lcp_master cc"
				+ ""
				+ ""
				+ "                        where aa.hospital_id = bb.hospital_id"
				+ "                          and aa.patient_no = bb.patient_no"
				+ "                          and bb.cp_id=cc.cp_id"
				+ "                          and aa.admission_date >= cc.cp_start_date"
				+ "  and aa.discharged_date <= (case when cc.cp_stop_date is null then sysdate  else cc.cp_stop_date end)"
				+ "                          ) cc)"
				+ "       union"
				+ "       select max(zyts) wcmaxzyts,"
				+ "              min(zyts) wcminzyts,"
				+ "              round(avg(zyts), 2) wcpjzyts"
				+ "         from (select aa.cp_id,"
				+ "                      bb.*,"
				+ "                      trunc( trunc(discharged_date, 'DD') - trunc(admission_date, 'DD')+1) zyts"
				+ "                 from (select distinct *"
				+ "                         from (select a.hospital_id, a.patient_no, a.cp_id"
				+ "                                 from lcp_patient_node a"
				+ "                                where a.cp_id = "
				+ cp_id
				+ ")) aa,"
				+ "                      (select b.hospital_id,"
				+ "                                      b.patient_no,"
				+ "                                      b.admission_date,"
				+ "                                      b.discharged_date"
				+ "                         from  lcp_patient_visit b"
				+ "                        where  b.cp_state in (11,21)) bb,"
				+ "                        lcp_master cc"
				+ "                where aa.hospital_id = bb.hospital_id"
				+ "                  and aa.patient_no = bb.patient_no"
				+ "                  and bb.discharged_date is not null"
				+ "                  and bb.admission_date is not null"
				+ "                  and aa.cp_id=cc.cp_id"
				+ "                          and bb.admission_date >= cc.cp_start_date"
				+ "  and bb.discharged_date <= (case when cc.cp_stop_date is null then sysdate  else cc.cp_stop_date end)"
				+ "                  ))";
		// //////System.out.println("tianshu_sql="+tianshu_sql);
		String resultXML = "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(tianshu_sql);
		String maxDay = dataSet.funGetFieldByCol(0, "MAXITANSHU");
		String minDay = dataSet.funGetFieldByCol(0, "MINTIANSHU");
		// //////System.out.println(maxDay+"==="+minDay);
		if (maxDay.equals("0") && minDay.equals("0")) {
			resultXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='各住院日分布率' baseFont='宋体' baseFontSize='12' "
					+ "       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>";
			String zhuyuanbiaoti = "";
			String narulujingbilv = "";
			String bnarulujingbilv = "";
			zhuyuanbiaoti = zhuyuanbiaoti + "<category label='住院0日的比率'/>";
			narulujingbilv = narulujingbilv + "<set value='0' />";
			bnarulujingbilv = bnarulujingbilv + "<set value='0' />";
			resultXML = resultXML + "<categories font='宋体' fontSize='12'>";
			resultXML = resultXML + zhuyuanbiaoti;

			resultXML = resultXML
					+ "</categories><dataset seriesname='纳入路径病例' color='9ACCF6' alpha='90' showValues='1' dashed='1'>";
			resultXML = resultXML + narulujingbilv;

			resultXML = resultXML
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>";
			resultXML = resultXML + bnarulujingbilv + "</dataset></chart>";

			return resultXML;
		} else {
			String fenbu_sql = "select *"
					+ "  from (select *"
					+ "          from (select rownum tianshu from dual connect by rownum <= "
					+ maxDay
					+ ")"
					+ "         where tianshu >= "
					+ minDay
					+ ") aaa,"
					+ "       (select zyts,"
					+ "               renshu,"
					+ "               sum(renshu) over() zrs,"
					+ "               100 * round(renshu / sum(renshu) over(), 4) renshubfb"
					+ "          from (select count(*) renshu, zyts"
					+ "                  from (select aa.cp_id,"
					+ "                               bb.*,"
					+ "                               trunc(trunc(discharged_date, 'DD') -"
					+ "                                     trunc(admission_date, 'DD') + 1) zyts"
					+ "                          from (select distinct *"
					+ "                                  from (select a.hospital_id,"
					+ "                                               a.patient_no,"
					+ "                                               a.cp_id"
					+ "                                          from lcp_patient_node a"
					+ "                                         where a.cp_id = "
					+ cp_id
					+ ")) aa,"
					+ "                               (select b.hospital_id,"
					+ "                                       b.patient_no,"
					+ "                                       b.admission_date,"
					+ "                                       b.discharged_date,"
					+ "                                       b.cp_state"
					+ "                                  from lcp_patient_visit b"
					+ "                                 where b.cp_state in (11, 21)) bb,"
					+ "                               lcp_master cc"
					+ "                         where aa.hospital_id = bb.hospital_id"
					+ "                           and aa.patient_no = bb.patient_no"
					+ "                           and bb.admission_date is not null"
					+ "                           and bb.discharged_date is not null"
					+ "                           and aa.cp_id = cc.cp_id"
					+ "                           and bb.admission_date >= cc.cp_start_date"
					+ "                           and bb.discharged_date <="
					+ "                               (case when cc.cp_stop_date is null then sysdate else"
					+ "                                cc.cp_stop_date end))"
					+ "                 group by zyts)) bbb,"
					+ "       (select zyts zytsbnr,"
					+ "               renshu renshubnr,"
					+ "               sum(renshu) over() zrsbnr,"
					+ "               100 * round(renshu / sum(renshu) over(), 4) renshubfbbnr"
					+ "          from (select count(*) renshu, zyts"
					+ "                  from (select cc.*,"
					+ "                               trunc(trunc(discharged_date, 'DD') -"
					+ "                                     trunc(admission_date, 'DD') + 1) zyts"
					+ "                          from (select aa.*"
					+ "                                  from (select b.hospital_id,"
					+ "                                               b.patient_no,"
					+ "                                               b.admission_date,"
					+ "                                               b.discharged_date,"
					+ "                                               b.cp_state"
					+ "                                          from lcp_patient_visit b"
					+ "                                         where b.cp_state in (99)"
					+ "                                           and b.admission_date is not null"
					+ "                                           and b.discharged_date is not null) aa,"
					+ "                                       (select b.*, a.cp_id"
					+ "                                          from lcp_master_income      a,"
					+ "                                               lcp_patient_log_income b"
					+ "                                         where a.cp_id = "
					+ cp_id
					+ ""
					+ "                                           and a.hospital_id = b.hospital_id"
					+ "                                           and a.cp_income_type = b.income_type"
					+ "                                           and a.cp_income_code = b.income_code) bb,"
					+ "                                       lcp_master cc"
					+ "                                       "
					+ "                                 where aa.hospital_id = bb.hospital_id"
					+ "                                   and aa.patient_no = bb.patient_no"
					+ "                                   and aa.admission_date is not null"
					+ "                                   and bb.cp_id = cc.cp_id"
					+ "                                   and aa.discharged_date is not null"
					+ "                                   and aa.admission_date >= cc.cp_start_date"
					+ "                                   and aa.discharged_date <="
					+ "                                       (case when cc.cp_stop_date is null then"
					+ "                                        sysdate else cc.cp_stop_date end)) cc)"
					+ "                 group by zyts)) ccc"
					+ " where aaa.tianshu = bbb.zyts(+)"
					+ "   and aaa.tianshu = ccc.zytsbnr(+)"
					+ " order by tianshu";
			//////System.out.println("fenbu_sql= " + fenbu_sql);
			dataSet.funSetDataSetBySql(fenbu_sql);
			// //////System.out.println(new String(dataSet.getXmldataset()));
			resultXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='各住院日分布率' baseFont='宋体' baseFontSize='12' "
					+ "       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>";
			String zhuyuanbiaoti = "";
			String narulujingbilv = "";
			String bnarulujingbilv = "";
			int row = dataSet.getRowNum();
			for (int i = 0; i < row; i++) {
				zhuyuanbiaoti = zhuyuanbiaoti + "<category label='住院"
						+ dataSet.funGetFieldByCol(i, "TIANSHU") + "日的比率'/>";
				narulujingbilv = narulujingbilv + "<set value='"
						+ dataSet.funGetFieldByCol(i, "RENSHUBFB") + "' />";
				bnarulujingbilv = bnarulujingbilv + "<set value='"
						+ dataSet.funGetFieldByCol(i, "RENSHUBFBBNR") + "' />";
			}
			resultXML = resultXML + "<categories font='宋体' fontSize='12'>";
			resultXML = resultXML + zhuyuanbiaoti;
			// <category label='住院10日的比率'/>
			// <category label='住院11日的比率'/>
			// <category label='住院12日的比率'/>
			// <category label='住院13日的比率'/>
			// <category label='住院14日的比率'/>
			// <category label='住院15日的比率'/>
			// <category label='住院16日的比率'/>
			// <category label='住院17日的比率'/>
			resultXML = resultXML
					+ "</categories><dataset seriesname='纳入路径病例' color='9ACCF6' alpha='90' showValues='1' dashed='1'>";
			resultXML = resultXML + narulujingbilv;
			// <set value='5.80' />
			// <set value='22.20' />
			// <set value='39.50' />
			// <set value='35.00' />
			// <set value='5.20' />
			// <set value='2.30' />
			// <set value='0' />
			// <set value='0' />
			resultXML = resultXML
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>";
			resultXML = resultXML + bnarulujingbilv + "</dataset></chart>";
			// <set value='0' />
			// <set value='0' />
			// <set value='0' />
			// <set value='13.60' />
			// <set value='21.40' />
			// <set value='33.45' />
			// <set value='22.20' />
			// <set value='9.35' />
			// </dataset>
			// </chart>";
			return resultXML;
		}
		// //////System.out.println(resultXML);

	}

	@Override
	public String funGetXiaoguoByCpIdUseXML(String cp_id) {
		// TODO Auto-generated method stub
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String sql_narubingli = "select tt.*,sum(hang) over()zongshu  from ( "
				+ "	select bb.*,aa.* from "
				+ "	(select TREAT_EFFECT,count(*)hang from("
				+ "	select aaa.TREAT_EFFECT"
				+ "	  from lcp_patient_firstpage aaa,"
				+ "	       (select distinct *"
				+ "	          from ("
				+ "         "
				+ "         select a.hospital_id, a.cp_id, a.patient_no"
				+ "	                  from lcp_patient_node a, lcp_patient_visit b"
				+ "	                 where a.hospital_id = b.hospital_id"
				+ "	                   and a.patient_no = b.patient_no"
				+ "	                 and a.cp_id = "
				+ cp_id
				+ ""
				+ "	                   and b.cp_state in (11, 21)"
				+ ""
				+ ""
				+ ""
				+ "))bbb"
				+ "	                   where aaa.hospital_id=bbb.hospital_id"
				+ "	                   and aaa.patient_no=bbb.patient_no)group by TREAT_EFFECT)aa,   "
				+ "	                   (select * from ("
				+ "	select 0 flag,'死亡' name from dual" + "	union"
				+ "	select 1 flag,'治愈' name from dual" + "	union"
				+ "	select 2 flag,'好转' name from dual" + "	union"
				+ "	select 3 flag,'其他' name from dual))bb"
				+ "	where aa.TREAT_EFFECT(+)=bb.flag)tt order by tt.flag";

		String sql_bunarubingli = "select tt.*,sum(hang) over()zongshu  from ( "
				+ "	select bb.*,aa.* from "
				+ "	(select TREAT_EFFECT,count(*)hang from("
				+ "	select aaa.TREAT_EFFECT"
				+ "	  from lcp_patient_firstpage aaa,"
				+ "	       (select distinct *"
				+ "	          from ("
				+ "         "
				+ "        select aa.*  "
				+ "	  from (select distinct * from (select a.hospital_id, a.patient_no, b.cp_id "
				+ "	          from lcp_patient_log_income a, lcp_master_income b "
				+ "	         where a.hospital_id = b.hospital_id "
				+ "	           and a.income_type = b.cp_income_type "
				+ "	           and a.income_code = b.cp_income_code "
				+ "	           and b.cp_id = "
				+ cp_id
				+ ")) aa, "
				+ "	       lcp_patient_visit bb "
				+ "	 where aa.hospital_id = bb.hospital_id "
				+ "	   and aa.patient_no = bb.patient_no "
				+ "	   and bb.cp_state in (0, 99)"
				+ ""
				+ ""
				+ ""
				+ "))bbb"
				+ "	                   where aaa.hospital_id=bbb.hospital_id"
				+ "	                   and aaa.patient_no=bbb.patient_no)group by TREAT_EFFECT)aa,   "
				+ "	                   (select * from ("
				+ "	select 0 flag,'死亡' name from dual"
				+ "	union"
				+ "	select 1 flag,'治愈' name from dual"
				+ "	union"
				+ "	select 2 flag,'好转' name from dual"
				+ "	union"
				+ "	select 3 flag,'其他' name from dual))bb"
				+ "	where aa.TREAT_EFFECT(+)=bb.flag)tt order by tt.flag";

		//////System.out.println("sql_bunarubingli" + sql_bunarubingli);
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql_narubingli);
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='效果统计' baseFont='宋体' baseFontSize='12' "
				+ "      useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>"
				+ "<categories font='宋体' fontSize='12'>"
				+ "<category label='死亡率'/>"
				+ "<category label='治愈率' />"
				+ "<category label='好转率'/>"
				+ "<category label='其它比率'/>"
				+ "</categories>"
				+ "<dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>";
		int row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			int fzs = Integer.parseInt(dataSet.funGetFieldByCol(i, "HANG"));// 分总数
			int zs = Integer.parseInt(dataSet.funGetFieldByCol(i, "ZONGSHU"));// 分总数
			if (zs == 0) {
				result = result + "<set value='0' />";
			} else {
				float bfb_nr = fzs * 100 / (float) zs;
				result = result + "<set value='" + fnum.format(bfb_nr) + "' />";
			}
		}
		result = result
				+ " </dataset><dataset seriesname='符合条件但不纳入病例' color='F6BD0F' showValues='1' alpha='90'>";
		dataSet.funSetDataSetBySql(sql_bunarubingli);
		row = dataSet.getRowNum();
		for (int i = 0; i < row; i++) {
			int fzs = Integer.parseInt(dataSet.funGetFieldByCol(i, "HANG"));// 分总数
			int zs = Integer.parseInt(dataSet.funGetFieldByCol(i, "ZONGSHU"));// 分总数
			if (zs == 0) {
				result = result + "<set value='0' />";
			} else {
				float bfb_nr = fzs * 100 / (float) zs;
				result = result + "<set value='" + fnum.format(bfb_nr) + "' />";
			}
		}
		result = result + "</dataset></chart>";

		return result;
	}

	@Override
	public String funGetJingjixueCpIdUseXML1(String cp_id) {
		// TODO Auto-generated method stub
		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";
		String fee_Str = tianshu.funGetFieldByCol(0, "CP_FEE");
		String fee_sql = "select * from (select trunc(avg(fee_total),2)nrpjfy,min(fee_total)nrzxfy,max(fee_total)nrzdfy from (select aaa.* from lcp_patient_fee aaa,(select aa.* from lcp_patient_visit aa,(select distinct * from(select cp_id,hospital_id ,patient_no from lcp_patient_node a where a.cp_id="
				+ cp_id
				+ " ))bb where aa.hospital_id=bb.hospital_id and aa.patient_no=bb.patient_no and aa.cp_state in(1,11,21) "
				+ " and aa.ADMISSION_DATE>="
				+ date_start
				+ "     and aa.discharged_date<= "
				+ date_end
				+ ")bbb where aaa.hospital_id=bbb.hospital_id and aaa.patient_no=bbb.patient_no)),(select trunc(avg(fee_total),2)bnrpjfy,min(fee_total)bnrzxfy,max(fee_total)bnrzdfy from (select aaa.* from lcp_patient_fee aaa,(select aa.* from lcp_patient_visit aa,( select distinct * from (select a.hospital_id, a.cp_id, b.patient_no from lcp_master_income a,lcp_patient_log_income b where a.cp_income_type = b.income_type  and a.cp_income_code = b.income_code and a.hospital_id = b.hospital_id and a.cp_id="
				+ cp_id
				+ "))cc where aa.hospital_id=cc.hospital_id and aa.patient_no=cc.patient_no and aa.cp_state in(99)"
				+ " and aa.ADMISSION_DATE>="
				+ date_start
				+ "     and aa.discharged_date<= "
				+ date_end
				+ ")bbb where aaa.hospital_id=bbb.hospital_id and aaa.patient_no=bbb.patient_no))";
		DataSet fee_dataset = new DataSet();
		fee_dataset.funSetDataSetBySql(fee_sql);
		// //////System.out.println("fee_sql= "+fee_sql);
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "	<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='卫生经济学统计' baseFont='宋体' baseFontSize='12' "
				+ "	       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='元' showPercentageValues='0' formatNumberScale='0'> "
				+ "	  <categories font='宋体' fontSize='12'> "
				+ "	    <category label='平均总费用'/> "
				+ "	    <category label='最小总费用' /> "
				+ "	    <category label='最大总费用'/> "
				+ "	  </categories> "
				+ "	  <dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "NRPJFY")
				+ "' /> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "NRZXFY")
				+ "' /> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "NRZDFY")
				+ "' /> "
				+ "	  </dataset> "
				+ "	  <dataset seriesname='符合条件但不纳入病例' color='F6BD0F' showValues='1' alpha='90'> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "BNRPJFY")
				+ "' /> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "BNRZXFY")
				+ "' /> "
				+ "	    <set value='"
				+ fee_dataset.funGetFieldByCol(0, "BNRZDFY")
				+ "' /> "
				+ "	  </dataset> "
				+ "	  <trendLines> "
				+ "	    <line startValue='"
				+ fee_Str
				+ "' color='359A35' displayvalue='路径定义平均总费用（"
				+ fee_Str
				+ "元）' valueOnRight='1'/> " + "	  </trendLines> " + "	</chart>";
		return result;
	}

	@Override
	public String funGetJingjixueCpIdUseXML2(String cp_id) {
		// TODO Auto-generated method stub
		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";

		String sql_naru_patient = "( select concat(concat(qq.hospital_id,'_'),qq.patient_no) from lcp_patient_fee qq,( "
				+ "	  select aa.* "
				+ "	    from lcp_patient_visit aa, "
				+ "	         (select distinct * "
				+ "	            from (select cp_id, hospital_id, patient_no "
				+ "	                    from lcp_patient_node a "
				+ "	                   where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "	   where aa.hospital_id = bb.hospital_id "
				+ "	     and aa.patient_no = bb.patient_no "
				+ "	     and aa.cp_state in ( 11, 21) "
				+ "	     and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	     and aa.discharged_date <= "
				+ date_end
				+ ")pp"
				+ " where qq.hospital_id=pp.hospital_id "
				+ "	     and qq.patient_no=pp.patient_no )";
		// //////System.out.println(sql_naru_patient);
		String sql_bu_naru_patient = "( select concat(concat(qq.hospital_id,'_'),qq.patient_no) from lcp_patient_fee qq,( "
				+ "	 select aa.* "
				+ "				  from lcp_patient_visit aa, "
				+ "				       (select distinct * "
				+ "				          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "				                  from lcp_master_income a, lcp_patient_log_income b "
				+ "				                 where a.cp_income_type = b.income_type "
				+ "				                   and a.cp_income_code = b.income_code "
				+ "				                   and a.hospital_id = b.hospital_id "
				+ "				                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "				 where aa.hospital_id = cc.hospital_id "
				+ "				   and aa.patient_no = cc.patient_no "
				+ "				   and aa.cp_state in (99)"
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ " )pp"
				+ " where qq.hospital_id=pp.hospital_id "
				+ "	     and qq.patient_no=pp.patient_no )";
		String renshu_sql = "select * from (select count(*) narurenshu, 2 naru from (select aa.* "
				+ "				  from lcp_patient_visit aa, "
				+ "				       (select distinct * "
				+ "				          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "				                  from lcp_master_income a, lcp_patient_log_income b "
				+ "				                 where a.cp_income_type = b.income_type "
				+ "				                   and a.cp_income_code = b.income_code "
				+ "				                   and a.hospital_id = b.hospital_id "
				+ "				                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "				 where aa.hospital_id = cc.hospital_id "
				+ "				   and aa.patient_no = cc.patient_no "
				+ "				   and aa.cp_state in (99)"
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ ") union  "
				+ "   			select count(*) narurenshu, 1 naru from (select aa.* "
				+ "	          from lcp_patient_visit aa, "
				+ "	               (select distinct * "
				+ "	                  from (select cp_id, hospital_id, patient_no "
				+ "	                          from lcp_patient_node a "
				+ "	                         where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "	         where aa.hospital_id = bb.hospital_id "
				+ "	           and aa.patient_no = bb.patient_no "
				+ "	           and aa.cp_state in ( 11, 21) "
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ ")) order by naru asc ";
		// out.print(renshu_sql);
		DataSet renshu_dataSet = new DataSet();
		renshu_dataSet.funSetDataSetBySql(renshu_sql);
		int naru_renshu_int = Integer.parseInt(renshu_dataSet.funGetFieldByCol(
				0, "NARURENSHU"));
		if (naru_renshu_int == 0)
			naru_renshu_int = 1;
		int bunaru_renshu_int = Integer.parseInt(renshu_dataSet
				.funGetFieldByCol(1, "NARURENSHU"));
		if (bunaru_renshu_int == 0)
			bunaru_renshu_int = 1;

		if (sql_naru_patient == "") {

			sql_naru_patient = "''";

		}
		String maix_sql = "select max(qq.fee_total)max_total,min(qq.fee_total)min_total from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_naru_patient + ")";
		DataSet maix = new DataSet();// 最大值最小值
		maix.funSetDataSetBySql(maix_sql);
		String max = maix.funGetFieldByCol(0, "MAX_TOTAL");
		String min = maix.funGetFieldByCol(0, "MIN_TOTAL");
		String naru_sql1 = "(select * from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_naru_patient + "))";
		String naru_sql_1 = TongjiFeeMax.funGetFeeQujianSql2(max, min, 1, 5,
				naru_sql1, "fee_total", 1);
		String naru_sql2 = "(select * from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_bu_naru_patient + "))";
		String naru_sql_2 = TongjiFeeMax.funGetFeeQujianSql2(max, min, 1, 5,
				naru_sql2, "fee_total", 1);
		//////System.out.println(naru_sql_2);
		DataSet naru_dataset = new DataSet();
		naru_dataset.funSetDataSetBySql(naru_sql_1);
		DataSet bu_naru_dataset = new DataSet();
		bu_naru_dataset.funSetDataSetBySql(naru_sql_2);
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "	<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='纳入路径病例中总费用出现率与不纳入病例的对比' baseFont='宋体' "
				+ "	       baseFontSize='12' useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'> "
				+ "	  <categories font='宋体' fontSize='12'> ";
		for (int i = 0; i < 5; i++) {
			result = result + "	<category label='"
					+ naru_dataset.funGetFieldByCol(i, "FANWEI") + "'/> ";
		}

		result = result
				+ "	  </categories> "
				+ "	  <dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'> ";
		for (int i = 0; i < 5; i++) {
			result = result
					+ "	    <set value='"
					+ fnum.format(Integer.parseInt(naru_dataset
							.funGetFieldByCol(i, "RENSHU"))
							* 100f
							/ naru_renshu_int) + "' /> ";
		}
		result = result
				+ "	  </dataset> "
				+ "	  <dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'> ";
		for (int i = 0; i < 5; i++) {
			result = result
					+ "	    <set value='"
					+ fnum.format(Integer.parseInt(bu_naru_dataset
							.funGetFieldByCol(i, "RENSHU"))
							* 100f
							/ bunaru_renshu_int) + "' /> ";
		}
		result = result + "	  </dataset> " + "	</chart>";
		return result;
	}

	@Override
	public String funGetJingjixueCpIdUseXML3(String cp_id) {
		// TODO Auto-generated method stub
		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";

		String sql_naru_patient = "( select concat(concat(qq.hospital_id,'_'),qq.patient_no) from lcp_patient_fee qq,( "
				+ "	  select aa.* "
				+ "	    from lcp_patient_visit aa, "
				+ "	         (select distinct * "
				+ "	            from (select cp_id, hospital_id, patient_no "
				+ "	                    from lcp_patient_node a "
				+ "	                   where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "	   where aa.hospital_id = bb.hospital_id "
				+ "	     and aa.patient_no = bb.patient_no "
				+ "	     and aa.cp_state in ( 11, 21) "
				+ "	     and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	     and aa.discharged_date <= "
				+ date_end
				+ ")pp"
				+ " where qq.hospital_id=pp.hospital_id "
				+ "	     and qq.patient_no=pp.patient_no )";
		// //////System.out.println(sql_naru_patient);
		String sql_bu_naru_patient = "( select concat(concat(qq.hospital_id,'_'),qq.patient_no) from lcp_patient_fee qq,( "
				+ "	 select aa.* "
				+ "				  from lcp_patient_visit aa, "
				+ "				       (select distinct * "
				+ "				          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "				                  from lcp_master_income a, lcp_patient_log_income b "
				+ "				                 where a.cp_income_type = b.income_type "
				+ "				                   and a.cp_income_code = b.income_code "
				+ "				                   and a.hospital_id = b.hospital_id "
				+ "				                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "				 where aa.hospital_id = cc.hospital_id "
				+ "				   and aa.patient_no = cc.patient_no "
				+ "				   and aa.cp_state in (99)"
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ " )pp"
				+ " where qq.hospital_id=pp.hospital_id "
				+ "	     and qq.patient_no=pp.patient_no )";
		String renshu_sql = "select * from (select count(*) narurenshu, 2 naru from (select aa.* "
				+ "				  from lcp_patient_visit aa, "
				+ "				       (select distinct * "
				+ "				          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "				                  from lcp_master_income a, lcp_patient_log_income b "
				+ "				                 where a.cp_income_type = b.income_type "
				+ "				                   and a.cp_income_code = b.income_code "
				+ "				                   and a.hospital_id = b.hospital_id "
				+ "				                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "				 where aa.hospital_id = cc.hospital_id "
				+ "				   and aa.patient_no = cc.patient_no "
				+ "				   and aa.cp_state in (99)"
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ ") union  "
				+ "   			select count(*) narurenshu, 1 naru from (select aa.* "
				+ "	          from lcp_patient_visit aa, "
				+ "	               (select distinct * "
				+ "	                  from (select cp_id, hospital_id, patient_no "
				+ "	                          from lcp_patient_node a "
				+ "	                         where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "	         where aa.hospital_id = bb.hospital_id "
				+ "	           and aa.patient_no = bb.patient_no "
				+ "	           and aa.cp_state in ( 11, 21) "
				+ "	           and aa.ADMISSION_DATE >= "
				+ date_start
				+ " "
				+ "	           and aa.discharged_date <= "
				+ date_end
				+ ")) order by naru asc ";
		// out.print(renshu_sql);
		DataSet renshu_dataSet = new DataSet();
		renshu_dataSet.funSetDataSetBySql(renshu_sql);
		int naru_renshu_int = Integer.parseInt(renshu_dataSet.funGetFieldByCol(
				0, "NARURENSHU"));
		if (naru_renshu_int == 0)
			naru_renshu_int = 1;
		int bunaru_renshu_int = Integer.parseInt(renshu_dataSet
				.funGetFieldByCol(1, "NARURENSHU"));
		if (bunaru_renshu_int == 0)
			bunaru_renshu_int = 1;

		if (sql_naru_patient == "") {

			sql_naru_patient = "''";

		}
		String maix_sql = "select max(qq.fee_total)max_total,min(qq.fee_total)min_total from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_bu_naru_patient + ")";
		DataSet maix = new DataSet();// 最大值最小值
		maix.funSetDataSetBySql(maix_sql);
		String max = maix.funGetFieldByCol(0, "MAX_TOTAL");
		String min = maix.funGetFieldByCol(0, "MIN_TOTAL");
		String naru_sql1 = "(select * from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_naru_patient + "))";
		String naru_sql_1 = TongjiFeeMax.funGetFeeQujianSql2(max, min, 1, 5,
				naru_sql1, "fee_total", 1);
		String naru_sql2 = "(select * from lcp_patient_fee qq  where concat(concat(qq.hospital_id,'_'),qq.patient_no) in("
				+ sql_bu_naru_patient + "))";
		String naru_sql_2 = TongjiFeeMax.funGetFeeQujianSql2(max, min, 1, 5,
				naru_sql2, "fee_total", 1);
		//////System.out.println(naru_sql_2);
		DataSet naru_dataset = new DataSet();
		naru_dataset.funSetDataSetBySql(naru_sql_1);
		DataSet bu_naru_dataset = new DataSet();
		bu_naru_dataset.funSetDataSetBySql(naru_sql_2);
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "	<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='不纳入路径病例中总费用出现率与不纳入病例的对比' baseFont='宋体' "
				+ "	       baseFontSize='12' useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'> "
				+ "	  <categories font='宋体' fontSize='12'> ";
		for (int i = 0; i < 5; i++) {
			result = result + "	<category label='"
					+ naru_dataset.funGetFieldByCol(i, "FANWEI") + "'/> ";
		}

		result = result
				+ "	  </categories> "
				+ "	  <dataset seriesname='符合条件但不纳入病例' color='A66EDD' alpha='90' showValues='1' dashed='1'> ";
		for (int i = 0; i < 5; i++) {
			result = result
					+ "	    <set value='"
					+ fnum.format(Integer.parseInt(bu_naru_dataset
							.funGetFieldByCol(i, "RENSHU"))
							* 100f
							/ bunaru_renshu_int) + "' /> ";
		}
		result = result
				+ "	  </dataset> "
				+ "	  <dataset seriesname='纳入路径病例' color='82CF27' showValues='1' alpha='90'> ";
		for (int i = 0; i < 5; i++) {
			result = result
					+ "	    <set value='"
					+ fnum.format(Integer.parseInt(naru_dataset
							.funGetFieldByCol(i, "RENSHU"))
							* 100f
							/ naru_renshu_int) + "' /> ";
		}
		result = result + "	  </dataset> " + "	</chart>";
		return result;
	}

	@Override
	public String funGetXiaoguoByCpIdUseXML2(String cp_id) {
		// TODO Auto-generated method stub
		String sql = "select * from (select * from ( "
				+ "select ll.*,kk.diagnosis_name from dcp_dict_diagnosis kk,("
				+ "select count(income_code) gbfzsl, income_code,zrs"
				+ "  from (select *"
				+ "          from (select aaa.cp_income_code, bbb.*"
				+ "                  from lcp_master_income aaa,"
				+ "                       (select distinct *"
				+ "                          from ("
				+ "                          select bb.hospital_id,"
				+ "                                       bb.patient_no,"
				+ "                                       bb.income_code,"
				+ "                                       aa.zrs"
				+ "                                  from ("
				+ "                                  select gg.*,sum(ren) over() zrs from"
				+ "                                  (select distinct *"
				+ "                                          from (select a.hospital_id,"
				+ "                                                       a.cp_id,"
				+ "                                                       a.patient_no,"
				+ "                                                       1 ren"
				+ "                                                  from lcp_patient_node  a,"
				+ "                                                       lcp_patient_visit b"
				+ "                                                 where a.hospital_id ="
				+ "                                                       b.hospital_id"
				+ "                                                   and a.patient_no ="
				+ "                                                       b.patient_no"
				+ "                                                   and a.cp_id = "
				+ cp_id
				+ ""
				+ "                                                   and b.cp_state in (11, 21)))gg"
				+ "                                                   ) aa,"
				+ "                                       lcp_patient_log_income bb"
				+ "                                 where aa.hospital_id = bb.hospital_id"
				+ "                                   and bb.patient_no = aa.patient_no"
				+ "                                   )"
				+ "                         where income_code is not null) bbb"
				+ "                 where aaa.hospital_id(+) = bbb.hospital_id"
				+ "                   and aaa.cp_income_code(+) = bbb.income_code)"
				+ "         where cp_income_code is null)"
				+ " group by income_code,zrs)ll where kk.diagnosis_code=ll.income_code"
				+ " ) ) mm,"
				+ " (select gbfzsl bnr_gbfzsl,income_code bnr_code,zrs bnr_zrs,diagnosis_name bnr_name from ("
				+ "select ll.*,kk.diagnosis_name from dcp_dict_diagnosis kk,("
				+ "select count(income_code) gbfzsl, income_code,zrs"
				+ "  from (select *"
				+ "          from (select aaa.cp_income_code, bbb.*"
				+ "                  from lcp_master_income aaa,"
				+ "                       (select distinct *"
				+ "                          from ("
				+ "                          select bb.hospital_id,"
				+ "                                       bb.patient_no,"
				+ "                                       bb.income_code,"
				+ "                                       aa.zrs"
				+ "                                  from ("
				+ "                                  select gg.*,sum(ren) over() zrs from"
				+ "                                  (select distinct *"
				+ "                                          from ("
				+ "                                        		  "
				+ "                                          select aa.*,1 ren"
				+ "	  from (select distinct *"
				+ "	          from (select a.hospital_id, a.patient_no, b.cp_id"
				+ "	                  from lcp_patient_log_income a, lcp_master_income b"
				+ "	                 where a.hospital_id = b.hospital_id"
				+ "	                   and a.income_type = b.cp_income_type"
				+ "	                   and a.income_code = b.cp_income_code"
				+ "	                   and b.cp_id = "
				+ cp_id
				+ ")) aa,"
				+ "	       lcp_patient_visit bb"
				+ "	 where aa.hospital_id = bb.hospital_id"
				+ "	   and aa.patient_no = bb.patient_no"
				+ "	   and bb.cp_state in (0, 99)))gg  "
				+ "	                                                   ) aa,"
				+ "	                                       lcp_patient_log_income bb"
				+ "	                                 where aa.hospital_id = bb.hospital_id"
				+ "	                                   and bb.patient_no = aa.patient_no"
				+ "	                                   )"
				+ "	                         where income_code is not null) bbb"
				+ "	                 where aaa.hospital_id(+) = bbb.hospital_id"
				+ "	                   and aaa.cp_income_code(+) = bbb.income_code)"
				+ "	         where cp_income_code is null)"
				+ "	 group by income_code,zrs)ll where kk.diagnosis_code=ll.income_code"
				+ "	 ) order by gbfzsl desc)nn"
				+ "	 where mm.income_code=nn.bnr_code(+) order by gbfzsl desc";
		//////System.out.println(sql);
		DataSet dataset = new DataSet();
		dataset.funSetDataSetBySql(sql, 1, 5);
		int row = dataset.getRowNum();
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='纳入路径病例中前5位并发症出现率与不纳入病例的对比'"
				+ " baseFont='宋体' baseFontSize='12' useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>"
				+ "<categories font='宋体' fontSize='12'>";
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				result = result + "<category label='"
						+ dataset.funGetFieldByCol(i, "DIAGNOSIS_NAME") + "'/>";
			}

			result = result
					+ " </categories><dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>";
			for (int i = 0; i < row; i++) {
				//////System.out.println(dataset.funGetFieldByCol(i, "DIAGNOSIS_NAME"));
				int gbfzsl = Integer.parseInt(dataset.funGetFieldByCol(i,
						"GBFZSL"));
				int zrs = Integer.parseInt(dataset.funGetFieldByCol(i, "ZRS"));
				float bfb = gbfzsl * 100 / (float) zrs;
				result = result + "<set value='" + fnum.format(bfb) + "' />";
			}

			result = result
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>";
			for (int i = 0; i < row; i++) {
				int gbfzsl = Integer.parseInt(dataset.funGetFieldByCol(i,
						"BNR_GBFZSL"));
				int zrs = Integer.parseInt(dataset.funGetFieldByCol(i,
						"BNR_ZRS"));
				//////System.out.println(gbfzsl + "-------------------------" + zrs);
				if (zrs == 0) {
					result = result + "<set value='0' />";
				} else {
					float bfb = gbfzsl * 100 / (float) zrs;
					result = result + "<set value='" + fnum.format(bfb)
							+ "' />";
				}

			}
		} else {
			result = result
					+ "<category/>"
					+ "</categories><dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>"
					+ "<set value='0' />"
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>"
					+ "<set value='0' />";
		}
		result = result + "</dataset></chart>";
		return result;
	}

	@Override
	public String funGetXiaoguoByCpIdUseXML3(String cp_id) {
		// TODO Auto-generated method stub
		String sql = "select * from (select * from ( "
				+ "select ll.*,kk.diagnosis_name from dcp_dict_diagnosis kk,("
				+ "select count(income_code) gbfzsl, income_code,zrs"
				+ "  from (select *"
				+ "          from (select aaa.cp_income_code, bbb.*"
				+ "                  from lcp_master_income aaa,"
				+ "                       (select distinct *"
				+ "                          from ("
				+ "                          select bb.hospital_id,"
				+ "                                       bb.patient_no,"
				+ "                                       bb.income_code,"
				+ "                                       aa.zrs"
				+ "                                  from ("
				+ "                                  select gg.*,sum(ren) over() zrs from"
				+ "                                  (select distinct *"
				+ "                                          from (select a.hospital_id,"
				+ "                                                       a.cp_id,"
				+ "                                                       a.patient_no,"
				+ "                                                       1 ren"
				+ "                                                  from lcp_patient_node  a,"
				+ "                                                       lcp_patient_visit b"
				+ "                                                 where a.hospital_id ="
				+ "                                                       b.hospital_id"
				+ "                                                   and a.patient_no ="
				+ "                                                       b.patient_no"
				+ "                                                   and a.cp_id = "
				+ cp_id
				+ ""
				+ "                                                   and b.cp_state in (11, 21)))gg"
				+ "                                                   ) aa,"
				+ "                                       lcp_patient_log_income bb"
				+ "                                 where aa.hospital_id = bb.hospital_id"
				+ "                                   and bb.patient_no = aa.patient_no"
				+ "                                   )"
				+ "                         where income_code is not null) bbb"
				+ "                 where aaa.hospital_id(+) = bbb.hospital_id"
				+ "                   and aaa.cp_income_code(+) = bbb.income_code)"
				+ "         where cp_income_code is null)"
				+ " group by income_code,zrs)ll where kk.diagnosis_code=ll.income_code"
				+ " ) ) mm,"
				+ " (select gbfzsl bnr_gbfzsl,income_code bnr_code,zrs bnr_zrs,diagnosis_name bnr_name from ("
				+ "select ll.*,kk.diagnosis_name from dcp_dict_diagnosis kk,("
				+ "select count(income_code) gbfzsl, income_code,zrs"
				+ "  from (select *"
				+ "          from (select aaa.cp_income_code, bbb.*"
				+ "                  from lcp_master_income aaa,"
				+ "                       (select distinct *"
				+ "                          from ("
				+ "                          select bb.hospital_id,"
				+ "                                       bb.patient_no,"
				+ "                                       bb.income_code,"
				+ "                                       aa.zrs"
				+ "                                  from ("
				+ "                                  select gg.*,sum(ren) over() zrs from"
				+ "                                  (select distinct *"
				+ "                                          from ("
				+ "                                        		  "
				+ "                                          select aa.*,1 ren"
				+ "	  from (select distinct *"
				+ "	          from (select a.hospital_id, a.patient_no, b.cp_id"
				+ "	                  from lcp_patient_log_income a, lcp_master_income b"
				+ "	                 where a.hospital_id = b.hospital_id"
				+ "	                   and a.income_type = b.cp_income_type"
				+ "	                   and a.income_code = b.cp_income_code"
				+ "	                   and b.cp_id = "
				+ cp_id
				+ ")) aa,"
				+ "	       lcp_patient_visit bb"
				+ "	 where aa.hospital_id = bb.hospital_id"
				+ "	   and aa.patient_no = bb.patient_no"
				+ "	   and bb.cp_state in (0, 99)))gg  "
				+ "	                                                   ) aa,"
				+ "	                                       lcp_patient_log_income bb"
				+ "	                                 where aa.hospital_id = bb.hospital_id"
				+ "	                                   and bb.patient_no = aa.patient_no"
				+ "	                                   )"
				+ "	                         where income_code is not null) bbb"
				+ "	                 where aaa.hospital_id(+) = bbb.hospital_id"
				+ "	                   and aaa.cp_income_code(+) = bbb.income_code)"
				+ "	         where cp_income_code is null)"
				+ "	 group by income_code,zrs)ll where kk.diagnosis_code=ll.income_code"
				+ "	 ) order by gbfzsl desc)nn"
				+ "	 where mm.income_code(+)=nn.bnr_code order by bnr_gbfzsl desc";
		//////System.out.println("------=" + sql);
		DataSet dataset = new DataSet();
		dataset.funSetDataSetBySql(sql, 1, 5);
		int row = dataset.getRowNum();
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='纳入路径病例中前5位并发症出现率与不纳入病例的对比'"
				+ " baseFont='宋体' baseFontSize='12' useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>"
				+ "<categories font='宋体' fontSize='12'>";

		if (row > 0) {
			for (int i = 0; i < row; i++) {
				result = result + "<category label='"
						+ dataset.funGetFieldByCol(i, "BNR_NAME") + "'/>";
			}

			result = result
					+ " </categories><dataset seriesname='符合条件但不纳入病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>";

			for (int i = 0; i < row; i++) {
				int gbfzsl = Integer.parseInt(dataset.funGetFieldByCol(i,
						"BNR_GBFZSL"));
				int zrs = Integer.parseInt(dataset.funGetFieldByCol(i,
						"BNR_ZRS"));
				//////System.out.println(gbfzsl + "-------------------------" + zrs);
				if (zrs == 0) {
					result = result + "<set value='0' />";
				} else {
					float bfb = gbfzsl * 100 / (float) zrs;
					result = result + "<set value='" + fnum.format(bfb)
							+ "' />";
				}

			}

			result = result
					+ "</dataset><dataset seriesname='纳入路径病例' color='82CF27' showValues='1' alpha='90'>";
			for (int i = 0; i < row; i++) {
				//////System.out.println(dataset.funGetFieldByCol(i, "DIAGNOSIS_NAME"));
				int gbfzsl = Integer.parseInt(dataset.funGetFieldByCol(i,
						"GBFZSL"));
				int zrs = Integer.parseInt(dataset.funGetFieldByCol(i, "ZRS"));
				float bfb = gbfzsl * 100 / (float) zrs;
				result = result + "<set value='" + fnum.format(bfb) + "' />";
			}
		} else {
			result = result
					+ "<category/>"
					+ "</categories><dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'>"
					+ "<set value='0' />"
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>"
					+ "<set value='0' />";
		}
		result = result + "</dataset></chart>";
		return result;
	}

	@Override
	public String funGetBianyiByCpIdUseXML1(String cp_id) {
		// TODO Auto-generated method stub
		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";
		DecimalFormat fnum = new DecimalFormat("##0.00");
		String fhrs_sql = "select count(*) hang "
				+ " from lcp_patient_visit aa, "
				+ " (select distinct * "
				+ "    from (select a.hospital_id, a.patient_no "
				+ "            from lcp_patient_log_income a, lcp_master_income b "
				+ "           where a.income_type = b.cp_income_type "
				+ "           and a.income_code = b.cp_income_code "
				+ "           and a.hospital_id = b.hospital_id "
				+ "            and b.cp_id = " + cp_id + ")) b "
				+ "where aa.hospital_id = b.hospital_id "
				+ "  and aa.patient_no = b.patient_no and  aa.ADMISSION_DATE>="
				+ date_start + " " + "         and aa.discharged_date<= "
				+ date_end + " ";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(fhrs_sql);
		int fhrs_int = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));

		String bunaru_sql = "select count(*) hang "
				+ " from lcp_patient_visit aa, "
				+ " (select distinct * "
				+ "    from (select a.hospital_id, a.patient_no "
				+ "            from lcp_patient_log_income a, lcp_master_income b "
				+ "           where a.income_type = b.cp_income_type "
				+ "           and a.income_code = b.cp_income_code "
				+ "           and a.hospital_id = b.hospital_id "
				+ "            and b.cp_id = " + cp_id + ")) b "
				+ "where aa.hospital_id = b.hospital_id   and aa.cp_state=99 "
				+ "  and aa.patient_no = b.patient_no and  aa.ADMISSION_DATE>="
				+ date_start + " " + "         and aa.discharged_date<= "
				+ date_end + " ";

		dataSet.funSetDataSetBySql(bunaru_sql);
		int bunaru_int = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));

		String naru_sql = "select count(*) hang "
				+ " from lcp_patient_visit aa, "
				+ " (select distinct * "
				+ "    from (select a.hospital_id, a.patient_no "
				+ "            from lcp_patient_log_income a, lcp_master_income b "
				+ "           where a.income_type = b.cp_income_type "
				+ "           and a.income_code = b.cp_income_code "
				+ "           and a.hospital_id = b.hospital_id "
				+ "            and b.cp_id = "
				+ cp_id
				+ ")) b "
				+ "where aa.hospital_id = b.hospital_id   and aa.cp_state in(1,11,21) "
				+ "  and aa.patient_no = b.patient_no and  aa.ADMISSION_DATE>="
				+ date_start + " " + "         and aa.discharged_date<= "
				+ date_end + " ";

		dataSet.funSetDataSetBySql(naru_sql);
		int naru_int = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));

		String tuichu_sql = "select count(*) hang "
				+ " from lcp_patient_visit aa, "
				+ " (select distinct * "
				+ "    from (select a.hospital_id, a.patient_no "
				+ "            from lcp_patient_log_income a, lcp_master_income b "
				+ "           where a.income_type = b.cp_income_type "
				+ "           and a.income_code = b.cp_income_code "
				+ "           and a.hospital_id = b.hospital_id "
				+ "            and b.cp_id = "
				+ cp_id
				+ ")) b "
				+ "where aa.hospital_id = b.hospital_id   and aa.cp_state in(21) "
				+ "  and aa.patient_no = b.patient_no and  aa.ADMISSION_DATE>="
				+ date_start + " " + "         and aa.discharged_date<= "
				+ date_end + " ";

		dataSet.funSetDataSetBySql(tuichu_sql);
		int tuichu_int = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANG"));

		String wanchengbianyi_sql = "select count(distinct(aaa.patient_no) )hang from lcp_patient_log_order_varia aaa,(select aa.* "
				+ " from lcp_patient_visit aa, "
				+ " (select distinct * "
				+ "    from (select a.hospital_id, a.patient_no "
				+ "            from lcp_patient_log_income a, lcp_master_income b "
				+ "           where a.income_type = b.cp_income_type "
				+ "           and a.income_code = b.cp_income_code "
				+ "           and a.hospital_id = b.hospital_id "
				+ "            and b.cp_id = "
				+ cp_id
				+ ")) b "
				+ "where aa.hospital_id = b.hospital_id   and aa.cp_state in(11) "
				+ "  and aa.patient_no = b.patient_no and  aa.ADMISSION_DATE>="
				+ date_start
				+ " "
				+ "         and aa.discharged_date<= "
				+ date_end
				+ " )bbb "
				+ "   where aaa.hospital_id=bbb.hospital_id "
				+ "   and aaa.patient_no=bbb.patient_no";

		dataSet.funSetDataSetBySql(wanchengbianyi_sql);
		int wanchegnbianyi_int = Integer.parseInt(dataSet.funGetFieldByCol(0,
				"HANG"));

		String yzby_sql = "select hh.*,sum(hangshu) over ()zongshu  from ( "
				+ "  select l.*,k. variation_type_name from dcp_dict_variation j,DCP_DICT_VARIA_TYPE k,( "
				+ "			  select count(variation_code) hangshu,variation_code from (select q.hospital_id, q.variation_code"
				+ "			    from lcp_patient_log_order_varia q,"
				+ "			         (select distinct *"
				+ "			            from (select aaa.patient_no, aaa.hospital_id"
				+ "			                    from lcp_patient_log_order_varia aaa,"
				+ "			                         (select aa.*"
				+ "			                            from lcp_patient_visit aa,"
				+ "			                                 (select distinct *"
				+ "			                                    from (select a.hospital_id, a.patient_no"
				+ "			                                            from lcp_patient_log_income a,"
				+ "			                                                 lcp_master_income      b"
				+ "			                                           where a.income_type = b.cp_income_type"
				+ "			                                             and a.income_code = b.cp_income_code"
				+ "			                                             and a.hospital_id = b.hospital_id"
				+ "			                                             and b.cp_id = "
				+ cp_id
				+ ")) b"
				+ "			                           where aa.hospital_id = b.hospital_id"
				+ "			                             and aa.cp_state in (1,11,21)"
				+ "			                             and aa.patient_no = b.patient_no"
				+ "			                             and aa.ADMISSION_DATE >="
				+ "			                                 to_date('2011-09-01', 'yyyy-mm-dd')"
				+ "			                             and aa.discharged_date <="
				+ "			                                 to_date('2011-10-17', 'yyyy-mm-dd')) bbb"
				+ "			                   where aaa.hospital_id = bbb.hospital_id"
				+ "			                     and aaa.patient_no = bbb.patient_no)) p"
				+ "			   where q.hospital_id = p.hospital_id"
				+ "			     and q.patient_no = p.patient_no)group by variation_code)l"
				+ "			     where l.variation_code=j.variation_code"
				+ "			     and j.variation_type_sub=k.variation_type_code)hh order by hangshu desc";
		dataSet.funSetDataSetBySql(yzby_sql);
		int byyyzs = Integer.parseInt(dataSet.funGetFieldByCol(0, "ZONGSHU"));
		int byyyzds = Integer.parseInt(dataSet.funGetFieldByCol(0, "HANGSHU"));
		float bunaru_bfb = 0;
		if (fhrs_int != 0) {
			bunaru_bfb = (bunaru_int * 100 / (float) fhrs_int);
		}

		String bunaru_bfb_str = fnum.format(bunaru_bfb);

		float byyybfb = 0;
		if (byyyzs != 0) {
			byyybfb = (byyyzds * 100 / (float) byyyzs);
		}

		String byyybfb_str = fnum.format(byyybfb);

		float ljtc_bfb = 0;// 路径退出百分比
		if (naru_int != 0) {
			ljtc_bfb = (tuichu_int * 100 / (float) naru_int);
		}

		String ljtc_bfb_str = fnum.format(ljtc_bfb);

		float wcby_bfb = 0;// 完成变异百分比
		if (naru_int != 0) {
			wcby_bfb = (wanchegnbianyi_int * 100 / (float) naru_int);
		}

		String wcby_bfb_str = fnum.format(wcby_bfb);
		// 开始构建xml
		StringBuffer dataXml = new StringBuffer();
		// 定义bom类型的xml文件
		byte[] utf8Bom = new byte[] { (byte) 0xef, (byte) 0xbb, (byte) 0xbf };
		String utf8BomStr = "";
		try {
			utf8BomStr = new String(utf8Bom, "UTF-8");// 定义BOM标记
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String result = utf8BomStr
				+ "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='' xAxisName='变异类型' yAxisName='比率' numberSuffix='%' formatNumberScale='0' useRoundEdges='1'> "
				+ "  <set label='不纳入率（符合条件但不纳入）' value='" + bunaru_bfb_str
				+ "' />" + "   <set label='路径退出率（纳入后变异退出）' value='"
				+ ljtc_bfb_str + "' />"
				+ "   <set label='完成路径的变异出现率（完成路径但执行过程中有变异）' value='"
				+ wcby_bfb_str + "' />" + " </chart>";

		//////System.out.println("----" + result);
		return result;
	}

	@Override
	public String funGetBianyiByCpIdUseXML4(String cp_id) {
		// TODO Auto-generated method stub
		String zrs_sql = "select count(distinct(t.patient_no)) zongrenshu from lcp_patient_log_order_varia t where t.cp_id="
				+ cp_id + "";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(zrs_sql);
		int zrs_int = Integer.parseInt(dataSet
				.funGetFieldByCol(0, "ZONGRENSHU"));
		String gby_sql = "select count(name) gbysl,name from( "
				+ // 各变异语句
				"	select variation_type_name name from("
				+ "	select  distinct *  from ("
				+ "	select   cc.patient_no ,  bb.variation_type_name  from DCP_DICT_VARIATION aa,DCP_DICT_VARIA_TYPE bb,"
				+ "	("
				+ "	select distinct * from (select t.hospital_id,t.patient_no,t.variation_code,t.cp_id from lcp_patient_log_order_varia t where t.cp_id="
				+ cp_id
				+ ")"
				+ "	)cc"
				+ "	where aa.variation_code=cc.variation_code and aa.variation_type_sub=bb.variation_type_code)))group by name";
		dataSet.funSetDataSetBySql(gby_sql);
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "	<chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='完成路径中医嘱变异原因分类出现率统计' xAxisName='变异原因类型' yAxisName='比率'"
				+ "	       numberSuffix='%' formatNumberScale='0' useRoundEdges='1'>";
		int row = dataSet.getRowNum();
		DecimalFormat fnum = new DecimalFormat("##0.00");
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				int gby_int = Integer.parseInt(dataSet.funGetFieldByCol(i,
						"GBYSL"));
				String name = dataSet.funGetFieldByCol(i, "NAME");
				float gbybfb = gby_int * 100 / (float) zrs_int;
				result = result + "<set label='" + name + "' value='"
						+ fnum.format(gbybfb) + "' />";
			}
		} else {
			result = result + "<set value='0' />";
		}
		result = result + "</chart>";
		return result;
	}

	@Override
	public String funGetBianyiByCpIdUseXML2(String cp_id) {
		// TODO Auto-generated method stub

		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";

		sql = "select name, trunc(gyy*100/zongshu,2)bfb from("
				+ "select count(name) gyy,name,zongshu from ("
				+ "select variation_name name, sum(geshu) over() zongshu from ("
				+ "select aaa.variation_code,aaa.variation_name,1 geshu from dcp_dict_variation aaa,lcp_patient_visit bbb,"
				+ "(select aa.*  from (select distinct *          from (select a.hospital_id, a.patient_no, b.cp_id  "
				+ "                from lcp_patient_log_income a, lcp_master_income b              "
				+ "   where a.hospital_id = b.hospital_id                   and a.income_type = b.cp_income_type "
				+ "                  and a.income_code = b.cp_income_code                   and b.cp_id = "
				+ cp_id
				+ ")) aa,  "
				+ "     lcp_patient_visit bb where aa.hospital_id = bb.hospital_id   and aa.patient_no = bb.patient_no "
				+ "  and bb.cp_state in (0, 99)"
				+ " and bb.ADMISSION_DATE>"
				+ date_start
				+ " and bb.DISCHARGED_DATE<"
				+ date_end
				+ ""
				+ ")ccc   where aaa.variation_code=bbb.cp_exclude_code   "
				+ "and bbb.hospital_id=ccc.hospital_id   and bbb.patient_no=ccc.patient_no))group by name,zongshu)";
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int row = dataSet.getRowNum();
		//////System.out.println("bianyi2" + sql);
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ " <chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='临床路径不纳入原因出现率统计' xAxisName='不纳入原因类型' yAxisName='比率'"
				+ " numberSuffix='%' formatNumberScale='0' useRoundEdges='1'>";
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String name = dataSet.funGetFieldByCol(i, "NAME");
				String bfb = dataSet.funGetFieldByCol(i, "BFB");
				result = result + "<set label='" + name + "' value='" + bfb
						+ "' />";
			}
		} else {
			result = result + "<set value='0' />";
		}

		result = result + "</chart>";
		return result;
	}

	@Override
	public String funGetBianyiByCpIdUseXML3(String cp_id) {
		// TODO Auto-generated method stub

		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";

		sql = "select frs, name,trunc(frs*100/zrs,2) bfb from(select count(name) frs ,name,zrs from("
				+ "	select name,sum(geshu) over () zrs from ("
				+ "	select aa.variation_name name,1 geshu from dcp_dict_variation aa,"
				+ "	(select * from lcp_patient_variation t,lcp_patient_visit u"
				+ "                         where t.cp_id = "
				+ cp_id
				+ ""
				+ "                          and t.cp_node_id ="
				+ "                             (select cp_node_id"
				+ "                               from lcp_master_node b"
				+ "                             where b.cp_id = "
				+ cp_id
				+ ""
				+ "                              and b.cp_node_type = 4)"
				+ "                            and t.hospital_id=u.hospital_id"
				+ "                           and t.patient_no=u.patient_no"
				+ "                          and  u.ADMISSION_DATE>"
				+ date_start
				+ " and u.DISCHARGED_DATE<"
				+ date_end
				+ " )bb where aa.variation_code=bb.CP_NODE_VARIATION_CODE))group by name ,zrs)";

		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		//////System.out.println(sql);
		int row = dataSet.getRowNum();

		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ " <chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='临床路径退出原因出现率统计' xAxisName='退出原因类型' yAxisName='比率'"
				+ " numberSuffix='%' formatNumberScale='0' useRoundEdges='1'>";
		if (row > 0) {
			for (int i = 0; i < row; i++) {
				String name = dataSet.funGetFieldByCol(i, "NAME");
				String bfb = dataSet.funGetFieldByCol(i, "BFB");
				result = result + "<set label='" + name + "' value='" + bfb
						+ "' />";
			}
		} else {
			result = result + "<set value='0' />";
		}

		result = result + "</chart>";
		return result;
	}

	@Override
	public String funGetKangshengsuCpIdUseXML1(String cp_id) {
		// TODO Auto-generated method stub

		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";
		String ksssyts_naru_sql = "select trunc(avg(ksssyts), 0) pj_ksssyts,trunc(min(ksssyts), 0)zx_ksssyts,trunc(max(ksssyts), 0)zd_ksssyts from ( "
				+ "	select patient_no,sum(ksssyts) ksssyts from ( "
				+ "			select aa.*, "
				+ "			       trunc(trunc(end_time, 'DD') - trunc(start_time, 'DD')) + 1 ksssyts "
				+ "			  from (select t.hospital_id, "
				+ "			               t.patient_no, "
				+ "			               t.enter_date_time start_time, "
				+ "			               t.STOP_DATE_TIME end_time "
				+ "			          from (select w.* "
				+ "			                 from lcp_patient_log_order w, "
				+ "			                      (select aa.* "
				+ "			                         from lcp_patient_visit aa, "
				+ "			                              (select distinct * "
				+ "			                                 from (select cp_id, hospital_id, patient_no "
				+ "			                                         from lcp_patient_node a "
				+ "			                                        where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "			                        where aa.hospital_id = bb.hospital_id "
				+ "			                          and aa.patient_no = bb.patient_no "
				+ "			                          and aa.cp_state in ( 11, 21) "
				+ "			                          and aa.ADMISSION_DATE >= "
				+ "			                              "
				+ date_start
				+ " "
				+ "			                          and aa.discharged_date <= "
				+ "			                              "
				+ date_end
				+ ") e "
				+ "			                where w.hospital_id = e.hospital_id "
				+ "			                  and w.patient_no = e.patient_no) t "
				+ "			                where t.IS_ANTIBIOTIC = 1 "
				+ "			        ) aa)group by patient_no)";

		String ksssyts_bunaru_sql = "select trunc(avg(ksssyts), 0) pj_ksssyts,trunc(min(ksssyts), 0)zx_ksssyts,trunc(max(ksssyts), 0)zd_ksssyts from ( "
				+ "	select patient_no,sum(ksssyts) ksssyts from ( "
				+ "			select aa.*, "
				+ "			       trunc(trunc(end_time, 'DD') - trunc(start_time, 'DD')) + 1 ksssyts "
				+ "			  from (select t.hospital_id, "
				+ "			               t.patient_no, "
				+ "			               t.enter_date_time start_time, "
				+ "			               t.STOP_DATE_TIME end_time "
				+ "			          from (select w.* "
				+ "			                 from lcp_patient_log_order w, "
				+ " "
				+ "			                      (select aa.* "
				+ "									  from lcp_patient_visit aa, "
				+ "									       (select distinct * "
				+ "									          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "									                  from lcp_master_income a, lcp_patient_log_income b "
				+ "									                 where a.cp_income_type = b.income_type "
				+ "									                   and a.cp_income_code = b.income_code "
				+ "									                   and a.hospital_id = b.hospital_id "
				+ "									                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "									 where aa.hospital_id = cc.hospital_id "
				+ "									   and aa.patient_no = cc.patient_no "
				+ "									   and aa.cp_state in (99) "
				+ "			                          and aa.ADMISSION_DATE >= "
				+ "			                              "
				+ date_start
				+ " "
				+ "			                          and aa.discharged_date <= "
				+ "			                              "
				+ date_end
				+ ") e "
				+ "			                where w.hospital_id = e.hospital_id "
				+ "			                  and w.patient_no = e.patient_no) t "
				+ "			                where t.IS_ANTIBIOTIC = 1 "
				+ "			        ) aa)group by patient_no)";
		//////System.out.println(ksssyts_bunaru_sql);
		DataSet ksssyts_naru_dataset = new DataSet();
		ksssyts_naru_dataset.funSetDataSetBySql(ksssyts_naru_sql);
		DataSet ksssyts_bunaru_dataset = new DataSet();
		ksssyts_bunaru_dataset.funSetDataSetBySql(ksssyts_bunaru_sql);

		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "		<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='抗菌药物使用统计' baseFont='宋体' baseFontSize='12' "
				+ "		       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='天'> "
				+ "		  <categories font='宋体' fontSize='12'> "
				+ "		    <category label='平均使用天数'/> "
				+ "		    <category label='最小使用天数' /> "
				+ "		    <category label='最大使用天数'/> "
				+ "		  </categories> "
				+ "		  <dataset seriesname='纳入路径病例' color='A66EDD' alpha='90' showValues='1' dashed='1'> "
				+ "		    <set value='"
				+ ksssyts_naru_dataset.funGetFieldByCol(0, "PJ_KSSSYTS")
				+ "' /> "
				+ "		    <set value='"
				+ ksssyts_naru_dataset.funGetFieldByCol(0, "ZX_KSSSYTS")
				+ "' /> "
				+ "		    <set value='"
				+ ksssyts_naru_dataset.funGetFieldByCol(0, "ZD_KSSSYTS")
				+ "' /> "
				+ "		  </dataset> "
				+ "		  <dataset seriesname='符合条件但不纳入病例' color='F6BD0F' showValues='1' alpha='90'> "
				+ "		    <set value='"
				+ ksssyts_bunaru_dataset.funGetFieldByCol(0, "PJ_KSSSYTS")
				+ "' /> " + "		    <set value='"
				+ ksssyts_bunaru_dataset.funGetFieldByCol(0, "ZX_KSSSYTS")
				+ "' /> " + "		    <set value='"
				+ ksssyts_bunaru_dataset.funGetFieldByCol(0, "ZD_KSSSYTS")
				+ "' /> " + "		  </dataset> " + "		</chart>";
		return result;
	}

	@Override
	public String funGetKangshengsuCpIdUseXML2(String cp_id) {
		// TODO Auto-generated method stub
		DataSet tianshu = new DataSet();
		String sql = "select t.cp_days, t.cp_name, t.cp_start_date, t.cp_fee,t.CP_STATUS, t.cp_stop_date from lcp_master t  where t.cp_id = "
				+ cp_id + "";

		tianshu.funSetDataSetBySql(sql);
		String cp_start_time = tianshu.funGetFieldByCol1(0, "CP_START_DATE");
		String cp_stop_time = (tianshu.funGetFieldByCol1(0, "CP_STOP_DATE"))
				.equals("") ? CommonUtil.getDBTimeString1() : tianshu
				.funGetFieldByCol1(0, "CP_STOP_DATE");
		String cp_state = tianshu.funGetFieldByCol(0, "CP_STATUS");
		if (cp_state.equals("0")) {
			cp_stop_time = CommonUtil.getDBTimeString1();
		}
		String date_start = "to_date('" + cp_start_time + "','yyyy-mm-dd')";
		String date_end = "to_date('" + cp_stop_time + "','yyyy-mm-dd')";
		String ksssyts_naru_sql = "select min(zx_ksssyts)zx_ksssyts,max(zd_ksssyts)zd_ksssyts from(select trunc(avg(ksssyts), 0) pj_ksssyts,trunc(min(ksssyts), 0)zx_ksssyts,trunc(max(ksssyts), 0)zd_ksssyts from ( "
				+ "	select patient_no,sum(ksssyts) ksssyts from ( "
				+ "			select aa.*, "
				+ "			       trunc(trunc(end_time, 'DD') - trunc(start_time, 'DD')) + 1 ksssyts "
				+ "			  from (select t.hospital_id, "
				+ "			               t.patient_no, "
				+ "			               t.enter_date_time start_time, "
				+ "			               t.STOP_DATE_TIME end_time "
				+ "			          from (select w.* "
				+ "			                 from lcp_patient_log_order w, "
				+ "			                      (select aa.* "
				+ "			                         from lcp_patient_visit aa, "
				+ "			                              (select distinct * "
				+ "			                                 from (select cp_id, hospital_id, patient_no "
				+ "			                                         from lcp_patient_node a "
				+ "			                                        where a.cp_id = "
				+ cp_id
				+ ")) bb "
				+ "			                        where aa.hospital_id = bb.hospital_id "
				+ "			                          and aa.patient_no = bb.patient_no "
				+ "			                          and aa.cp_state in ( 11, 21) "
				+ "			                          and aa.ADMISSION_DATE >= "
				+ "			                              "
				+ date_start
				+ " "
				+ "			                          and aa.discharged_date <= "
				+ "			                              "
				+ date_end
				+ ") e "
				+ "			                where w.hospital_id = e.hospital_id "
				+ "			                  and w.patient_no = e.patient_no) t "
				+ "			                where t.IS_ANTIBIOTIC = 1 "
				+ "			        ) aa)group by patient_no)"
				+ ""
				+ "union "
				+ "select trunc(avg(ksssyts), 0) pj_ksssyts,trunc(min(ksssyts), 0)zx_ksssyts,trunc(max(ksssyts), 0)zd_ksssyts from ( "
				+ "	select patient_no,sum(ksssyts) ksssyts from ( "
				+ "			select aa.*, "
				+ "			       trunc(trunc(end_time, 'DD') - trunc(start_time, 'DD')) + 1 ksssyts "
				+ "			  from (select t.hospital_id, "
				+ "			               t.patient_no, "
				+ "			               t.enter_date_time start_time, "
				+ "			               t.STOP_DATE_TIME end_time "
				+ "			          from (select w.* "
				+ "			                 from lcp_patient_log_order w, "
				+ " "
				+ "			                      (select aa.* "
				+ "									  from lcp_patient_visit aa, "
				+ "									       (select distinct * "
				+ "									          from (select a.hospital_id, a.cp_id, b.patient_no "
				+ "									                  from lcp_master_income a, lcp_patient_log_income b "
				+ "									                 where a.cp_income_type = b.income_type "
				+ "									                   and a.cp_income_code = b.income_code "
				+ "									                   and a.hospital_id = b.hospital_id "
				+ "									                   and a.cp_id = "
				+ cp_id
				+ ")) cc "
				+ "									 where aa.hospital_id = cc.hospital_id "
				+ "									   and aa.patient_no = cc.patient_no "
				+ "									   and aa.cp_state in (99) "
				+ "			                          and aa.ADMISSION_DATE >= "
				+ "			                              "
				+ date_start
				+ " "
				+ "			                          and aa.discharged_date <= "
				+ "			                              "
				+ date_end
				+ ") e "
				+ "			                where w.hospital_id = e.hospital_id "
				+ "			                  and w.patient_no = e.patient_no) t "
				+ "			                where t.IS_ANTIBIOTIC = 1 "
				+ "			        ) aa)group by patient_no))";
		DataSet tianshu_dataset = new DataSet();
		tianshu_dataset.funSetDataSetBySql(ksssyts_naru_sql);

		String maxDay = tianshu_dataset.funGetFieldByCol(0, "ZD_KSSSYTS");
		String minDay = tianshu_dataset.funGetFieldByCol(0, "ZX_KSSSYTS");
		if (maxDay.equals("0") && minDay.equals("0")) {
			String resultXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='抗菌药物使用各级分布率' baseFont='宋体' baseFontSize='12' "
					+ "       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>";
			String zhuyuanbiaoti = "";
			String narulujingbilv = "";
			String bnarulujingbilv = "";
			zhuyuanbiaoti = zhuyuanbiaoti + "<category label='使用0日的比率'/>";
			narulujingbilv = narulujingbilv + "<set value='0' />";
			bnarulujingbilv = bnarulujingbilv + "<set value='0' />";
			resultXML = resultXML + "<categories font='宋体' fontSize='12'>";
			resultXML = resultXML + zhuyuanbiaoti;
			resultXML = resultXML
					+ "</categories><dataset seriesname='纳入路径病例' color='9ACCF6' alpha='90' showValues='1' dashed='1'>";
			resultXML = resultXML + narulujingbilv;
			resultXML = resultXML
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>";
			resultXML = resultXML + bnarulujingbilv + "</dataset></chart>";

			return resultXML;
		} else {

			String fbl_sql = "select * "
					+ "	  from (select * "
					+ "	          from (select rownum tianshu from dual connect by rownum <= "
					+ maxDay
					+ ") "
					+ "	         where tianshu >=  "
					+ minDay
					+ ") aaa, "
					+ "	         "
					+ "	       (select ksssyts ksssyts, "
					+ "	               100 * round(renshu / sum(renshu) over(), 4) renshubfb_naru "
					+ "	          from ( "
					+ "	                select sum(ren) renshu, ksssyts "
					+ "	                  from (select patient_no, sum(ksssyts) ksssyts, 1 ren "
					+ "	                           from ( "
					+ "	                        		   "
					+ "	                                 select aa.*, "
					+ "	                                         trunc(trunc(end_time, 'DD') - "
					+ "	                                               trunc(start_time, 'DD')) + 1 ksssyts "
					+ "	                                   from (select t.hospital_id, "
					+ "	                                                 t.patient_no, "
					+ "	                                                 t.enter_date_time start_time, "
					+ "	                                                 t.STOP_DATE_TIME end_time "
					+ "	                                            from (select w.* "
					+ "	                                                    from lcp_patient_log_order w, "
					+ "	                                                         (select aa.* "
					+ "	                                                            from lcp_patient_visit aa, "
					+ "	                                                                 (select distinct * "
					+ "	                                                                    from (select cp_id, "
					+ "	                                                                                 hospital_id, "
					+ "	                                                                                 patient_no "
					+ "	                                                                            from lcp_patient_node a "
					+ "	                                                                           where a.cp_id = "
					+ "	                                                                                 "
					+ cp_id
					+ ")) bb "
					+ "	                                                           where aa.hospital_id = "
					+ "	                                                                 bb.hospital_id "
					+ "	                                                             and aa.patient_no = "
					+ "	                                                                 bb.patient_no "
					+ "	                                                             and aa.cp_state in "
					+ "	                                                                 (11, 21) "
					+ "	                                                             and aa.ADMISSION_DATE >= "
					+ "			                              "
					+ date_start
					+ " "
					+ "			                          and aa.discharged_date <= "
					+ "			                              "
					+ date_end
					+ ") e "
					+ "	                                                   where w.hospital_id = "
					+ "	                                                         e.hospital_id "
					+ "	                                                     and w.patient_no = e.patient_no) t "
					+ "	                                           where t.IS_ANTIBIOTIC = 1) aa "
					+ "	                                           "
					+ "	                                 ) "
					+ "	                          group by patient_no) "
					+ "	                 group by ksssyts) "
					+ "	       "
					+ "	        ) bbb, "
					+ "	       (select ksssyts ksssyts, "
					+ "	               100 * round(renshu / sum(renshu) over(), 4) renshubfb_bunaru "
					+ "	          from ( "
					+ "	                select sum(ren) renshu, ksssyts "
					+ "	                  from (select patient_no, sum(ksssyts) ksssyts, 1 ren "
					+ "	                           from ( "
					+ "	                        		   "
					+ "	                                 select aa.*, "
					+ "	                                         trunc(trunc(end_time, 'DD') - "
					+ "	                                               trunc(start_time, 'DD')) + 1 ksssyts "
					+ "	                                   from (select t.hospital_id, "
					+ "	                                                 t.patient_no, "
					+ "	                                                 t.enter_date_time start_time, "
					+ "	                                                 t.STOP_DATE_TIME end_time "
					+ "	                                            from (select w.* "
					+ "	                                                    from lcp_patient_log_order w, "
					+ "	                                                         (select aa.* "
					+ "	                                                            from lcp_patient_visit aa, "
					+ "	                                                                 (select distinct * "
					+ "	                                                                    from (select a.hospital_id, "
					+ "	                                                                                 a.cp_id, "
					+ "	                                                                                 b.patient_no "
					+ "	                                                                            from lcp_master_income      a, "
					+ "	                                                                                 lcp_patient_log_income b "
					+ "	                                                                           where a.cp_income_type = "
					+ "	                                                                                 b.income_type "
					+ "	                                                                             and a.cp_income_code = "
					+ "	                                                                                 b.income_code "
					+ "	                                                                             and a.hospital_id = "
					+ "	                                                                                 b.hospital_id "
					+ "	                                                                             and a.cp_id = "
					+ "	                                                                                 "
					+ cp_id
					+ ")) cc "
					+ "	                                                           where aa.hospital_id = "
					+ "	                                                                 cc.hospital_id "
					+ "	                                                             and aa.patient_no = "
					+ "	                                                                 cc.patient_no "
					+ "	                                                             and aa.cp_state in (99) "
					+ "	                                                             and aa.ADMISSION_DATE >= "
					+ "			                              "
					+ date_start
					+ " "
					+ "			                          and aa.discharged_date <= "
					+ "			                              "
					+ date_end
					+ ") e "
					+ "	                                                   where w.hospital_id = "
					+ "	                                                         e.hospital_id "
					+ "	                                                     and w.patient_no = e.patient_no) t "
					+ "	                                           where t.IS_ANTIBIOTIC = 1) aa "
					+ "	                                           "
					+ "	                                 ) "
					+ "	                          group by patient_no) "
					+ "	                 group by ksssyts)) ccc "
					+ "	 where aaa.tianshu = bbb.ksssyts(+) "
					+ "	   and aaa.tianshu = ccc.ksssyts(+)"
					+ "order by tianshu";

			DataSet dataSet = new DataSet();
			dataSet.funSetDataSetBySql(fbl_sql);
			String resultXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+ "<chart palette='2' rotateNames='0' animation='1' numdivlines='4' caption='菌药物使用各级分布率' baseFont='宋体' baseFontSize='12' "
					+ "       useRoundEdges='1' legendBorderAlpha='0' numberSuffix='%'>";
			String zhuyuanbiaoti = "";
			String narulujingbilv = "";
			String bnarulujingbilv = "";
			int row = dataSet.getRowNum();
			for (int i = 0; i < row; i++) {
				zhuyuanbiaoti = zhuyuanbiaoti + "<category label='使用"
						+ dataSet.funGetFieldByCol(i, "TIANSHU") + "日的比率'/>";
				narulujingbilv = narulujingbilv + "<set value='"
						+ dataSet.funGetFieldByCol(i, "RENSHUBFB_NARU")
						+ "' />";
				bnarulujingbilv = bnarulujingbilv + "<set value='"
						+ dataSet.funGetFieldByCol(i, "RENSHUBFB_BUNARU")
						+ "' />";
			}
			resultXML = resultXML + "<categories font='宋体' fontSize='12'>";
			resultXML = resultXML + zhuyuanbiaoti;
			// <category label='住院10日的比率'/>
			// <category label='住院11日的比率'/>
			// <category label='住院12日的比率'/>
			// <category label='住院13日的比率'/>
			// <category label='住院14日的比率'/>
			// <category label='住院15日的比率'/>
			// <category label='住院16日的比率'/>
			// <category label='住院17日的比率'/>
			resultXML = resultXML
					+ "</categories><dataset seriesname='纳入路径病例' color='9ACCF6' alpha='90' showValues='1' dashed='1'>";
			resultXML = resultXML + narulujingbilv;
			// <set value='5.80' />
			// <set value='22.20' />
			// <set value='39.50' />
			// <set value='35.00' />
			// <set value='5.20' />
			// <set value='2.30' />
			// <set value='0' />
			// <set value='0' />
			resultXML = resultXML
					+ "</dataset><dataset seriesname='符合条件但不纳入病例' color='82CF27' showValues='1' alpha='90'>";
			resultXML = resultXML + bnarulujingbilv + "</dataset></chart>";

			return resultXML;
		}

	}

	@Override
	public String funGetCpSQL(String value, String flag,String start_time,String end_time) {
		String sql="";

			sql="select hospital_id,cp_code,     cp_name,     cp_id,  cp_start_date,     cp_status,     input_code_py,     input_code_wb,     fhrs,  nrrs,     TRUNC((case             when fhrs = 0 then              0 else nrrs /  fhrs * 100           end),           2) nrbfb, zxrs,     wcrs, TRUNC((case when  nrrs = 0 then               0 else wcrs / nrrs * 100           end), 2) wcbfb ,  tcrs,     wcbyrs, TRUNC((case             when wcrs = 0 then 0 else wcbyrs /  wcrs * 100           end),           2) wcbybfb , fhbnrrs, fhrs- nrrs- fhbnrrs  bnrrs, fhrs-nrrs zbnrrs   from ( select  a.*,b.fhrs,c.nrrs,d.zxrs,e.wcrs,f.tcrs,g.wcbyrs,h.fhbnrrs from (select * from  lcp_master)a,(select hospital_id,cp_id,cp_name,fuherenshu fhrs from (select  NVL(b.fuherenshu,0) fuherenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select NVL (count(patient_no),0) fuherenshu,  cp_id,hospital_id from (select b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)group by  cp_id,hospital_id)b where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+)  ))b,(select hospital_id,cp_id,cp_name,narurenshu nrrs from (select  NVL(b.fuherenshu,0) narurenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select * from (select a.* from (select b.*,a.admission_date,a.visit_status from  (select a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)a,(select distinct *  from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where  1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id ) where visit_status  in(1,11,21))group by hospital_id,cp_id)b where a.hospital_id=b.hospital_id(+)  and a.cp_id=b.cp_id(+) ))c,(select hospital_id,cp_id,cp_name,zhixingrenshu zxrs  from (select NVL(b.fuherenshu,0) zhixingrenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select * from (select * from (select a.* from (select  b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)a,(select distinct *  from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where  1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id ) where visit_status in(1,11,21))  where visit_status in(1))group by hospital_id,cp_id)b where  a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ))d,(select  hospital_id,cp_id,cp_name,wanchengrenshu wcrs from (select NVL(b.fuherenshu,0)  wanchengrenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select * from (select * from (select a.* from (select  b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)a,(select distinct *  from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where  1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id ) where visit_status in(1,11,21))  where visit_status in(11))group by hospital_id,cp_id)b where  a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ))e,(select  hospital_id,cp_id,cp_name,tuichurenshu tcrs from (select NVL(b.fuherenshu,0)  tuichurenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select * from (select * from (select a.* from (select  b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)a,(select distinct *  from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where  1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id ) where visit_status in(1,11,21))  where visit_status in(21))group by hospital_id,cp_id)b where  a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ))f,(select  hospital_id,cp_id,cp_name,wanchengbianyirenshu wcbyrs from (select  NVL(b.fuherenshu,0) wanchengbianyirenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select a.* from (select * from (select * from (select a.* from (select  b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no)a,(select distinct *  from (select t.hospital_id,t.patient_no,t.cp_id from lcp_patient_node t where  1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id ) where visit_status in(1,11,21))  where visit_status in(11))a,(select distinct * from (select  t.hospital_id,t.patient_no,t.cp_id from lcp_patient_log_order_varia t where 1=1  and hospital_id=1 )) b where a.hospital_id=b.hospital_id and  a.patient_no=b.patient_no and a.cp_id=b.cp_id )group by hospital_id,cp_id)b  where a.hospital_id=b.hospital_id(+) and a.cp_id=b.cp_id(+) ))g,(select  hospital_id,cp_id,cp_name,fuhenonarurenshu fhbnrrs from (select  NVL(b.fuherenshu,0) fuhenonarurenshu ,a.* from (select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 ) a ,(select count(patient_no) fuherenshu,hospital_id, cp_id from  (select * from (select b.*,a.admission_date,a.visit_status from (select  a.hospital_id,a.patient_no,a.admission_date,a.cp_state visit_status from  lcp_patient_visit a where   a.sys_is_del=0  and a.admission_date>=to_date('"+start_time+"','yyyy-mm-dd') and a.admission_date<= to_date('"+end_time+"','yyyy-mm-dd')  )a,( select distinct * from (select  b.*,a.patient_no from  (select * from (select distinct * from (select  a.hospital_id,a.patient_no,b.cp_id,a.exe_date from lcp_patient_log_income  a,lcp_master_income b where a.hospital_id=b.hospital_id and  a.income_type=b.cp_income_type and a.income_code=b.cp_income_code)) where 1=1  and hospital_id=1 )a,(select * from (select  t.hospital_id,t.cp_id,t.cp_name,t.cp_start_date kaishi,case when t.cp_status=0  then sysdate  else t.cp_stop_date  end jieshu from lcp_master t ) where 1=1  and  hospital_id=1 )b where a.hospital_id=b.hospital_id and a.cp_id=b.cp_id and (  a.exe_date>=b.kaishi and  a.exe_date<=b.jieshu)))b where  a.hospital_id=b.hospital_id and a.patient_no=b.patient_no) where visit_status  in(0))group by hospital_id,cp_id)b where a.hospital_id=b.hospital_id(+) and  a.cp_id=b.cp_id(+) ))h where a.hospital_id=b.hospital_id and  a.hospital_id=c.hospital_id and a.hospital_id=d.hospital_id and  a.hospital_id=e.hospital_id and a.hospital_id=f.hospital_id and  a.hospital_id=g.hospital_id and a.hospital_id=h.hospital_id and a.cp_id=b.cp_id  and a.cp_id=c.cp_id and a.cp_id=d.cp_id and a.cp_id=e.cp_id and a.cp_id=f.cp_id  and a.cp_id=g.cp_id and a.cp_id=h.cp_id) ";
	    ////////System.out.println("<<<<<<分医院查询= "+sql+">>>>>>>>");
		//////System.out.println("<<<<<<"+start_time+">>>>>>>>"+end_time);
		if (value != "" && value != null) {
			sql = "select * from(" + sql + ") where   ";
			if (flag.equals("0")) {
				sql = sql + " cp_code like '%" + value
						+ "%' order by length(cp_code)";
			}
			if (flag.equals("1")) {
				sql = sql + " input_code_py like '%" + value.toUpperCase()
						+ "%' order by length(input_code_py)";
			}
			if (flag.equals("2")) {
				sql = sql + " input_code_wb like '%" + value.toUpperCase()
						+ "%' order by length(input_code_wb)";
			}
		}
		//////System.out.println(sql);
		return sql;
	}

	@Override
	public String funGetFPCpInfo(String year, String month) {
		// TODO Auto-generated method stub
		String sql_1 = "select count(*) jbljs from dcp_master where sys_is_del=0";//基本路径数
		// 符合人数
		String sql_2 = "";
		if (year == "" && month == "")
			//sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+"";
			sql_2="select count(*) fhrs from v_cp_dept t,(select conform_master_id,execute_dept " +
					"from lcp_patient_visit  where conform_master_id <> 0 and "+startDate+"" +
					") a where t.cp_master_id=a.conform_master_id and t.dept_name=a.execute_dept";

		if (year != "")
			//"select count(*) fhrs from (select distinct(a.patient_no) from lcp_patient_log_income a,lcp_master_income b where a.income_type=b.cp_income_type and a.income_code=b.cp_income_code and to_char(a.sys_last_update,'yyyy')='"
			//sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy')='"
			//	+ CommonUtil.funGetYear() + "'";
			sql_2="select count(*) fhrs from v_cp_dept t,(select conform_master_id,execute_dept " +
					"from lcp_patient_visit  where conform_master_id <> 0 and "+startDate+"" +
					" and to_char(admission_date, 'yyyy') = '"+CommonUtil.funGetYear()+"') a " +
					"where t.cp_master_id=a.conform_master_id and t.dept_name=a.execute_dept";
		if (month != "")
			//"select count(*) fhrs from (select distinct(a.patient_no) from lcp_patient_log_income a,lcp_master_income b where a.income_type=b.cp_income_type and a.income_code=b.cp_income_code and to_char(a.sys_last_update,'yyyy-mm')='"
			//sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"	
			//	+ CommonUtil.funGetYearAndMonth() + "'";
			sql_2="select count(*) fhrs from v_cp_dept t,(select conform_master_id,execute_dept " +
			"from lcp_patient_visit  where conform_master_id <> 0 and "+startDate+"" +
			" and to_char(admission_date, 'yyyy-mm') = '"+CommonUtil.funGetYearAndMonth()+"') a " +
			"where t.cp_master_id=a.conform_master_id and t.dept_name=a.execute_dept";

		// 纳入人数
		String sql_3 = "";
		String sql_34 = "";//完成人数
		// "select count(*) narurenshu from lcp_patient_visit t where t.cp_state not in(0,99)";
		if (year == "" && month == ""){
			//sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+"";
			sql_3="select count(*) narurenshu from v_cp_dept t,(select cp_master_id,execute_dept " +
					"from lcp_patient_visit  where cp_master_id <> 0 and "+startDate+"" +
					") a where t.cp_master_id=a.cp_master_id and t.dept_name=a.execute_dept";
		}
		if (year != ""){
			//"select count(*) fhrs from (select distinct(a.patient_no) from lcp_patient_log_income a,lcp_master_income b where a.income_type=b.cp_income_type and a.income_code=b.cp_income_code and to_char(a.sys_last_update,'yyyy')='"
//			sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy')='"
//				+ CommonUtil.funGetYear() + "'";
//			sql_34 = "select count(*) wcrenshu from lcp_patient_visit where cp_master_id<>0 and cp_state=11 and "+startDate+" and to_char(admission_date,'yyyy')='"
//				+ CommonUtil.funGetYear() + "'";
			sql_3="select count(*) narurenshu from v_cp_dept t,(select cp_master_id,execute_dept " +
					"from lcp_patient_visit  where cp_master_id <> 0 and "+startDate+"" +
					" and to_char(admission_date, 'yyyy') = '"+CommonUtil.funGetYear()+"') a " +
					"where t.cp_master_id=a.cp_master_id and t.dept_name=a.execute_dept";
			sql_34="select count(*) wcrenshu from v_cp_dept t,(select cp_master_id,execute_dept " +
					"from lcp_patient_visit  where cp_master_id <> 0 and cp_state=11 and "+startDate+"" +
					" and to_char(admission_date, 'yyyy') = '"+CommonUtil.funGetYear()+"') a " +
					"where t.cp_master_id=a.cp_master_id and t.dept_name=a.execute_dept";
			//System.out.println("sql_3ee===:"+sql_3);
			//System.out.println("sql_34=ee==:"+sql_34);
		}
		if (month != ""){
			//"select count(*) fhrs from (select distinct(a.patient_no) from lcp_patient_log_income a,lcp_master_income b where a.income_type=b.cp_income_type and a.income_code=b.cp_income_code and to_char(a.sys_last_update,'yyyy-mm')='"
//			sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"	
//				+ CommonUtil.funGetYearAndMonth() + "'";
//			sql_34 = "select count(*) wcrenshu from lcp_patient_visit where cp_master_id<>0 and cp_state=11 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"	
//				+ CommonUtil.funGetYearAndMonth() + "'";
			sql_3="select count(*) narurenshu from v_cp_dept t,(select cp_master_id,execute_dept " +
					"from lcp_patient_visit  where cp_master_id <> 0 and "+startDate+"" +
					" and to_char(admission_date, 'yyyy-mm') = '"+CommonUtil.funGetYearAndMonth()+"') a " +
					"where t.cp_master_id=a.cp_master_id and t.dept_name=a.execute_dept";
			sql_34="select count(*) wcrenshu from v_cp_dept t,(select cp_master_id,execute_dept " +
					"from lcp_patient_visit  where cp_master_id <> 0 and cp_state=11 and "+startDate+"" +
					" and to_char(admission_date, 'yyyy-mm') = '"+CommonUtil.funGetYearAndMonth()+"') a " +
					"where t.cp_master_id=a.cp_master_id and t.dept_name=a.execute_dept";
			//System.out.println("sql_3===:"+sql_3);
			//System.out.println("sql_34===:"+sql_34);
		}
		DataSet dataSet = new DataSet();

		dataSet.funSetDataSetBySql(sql_1);
		String jbljs = dataSet.funGetFieldByCol(0, "JBLJS");
		//启用中的路径数和自定义的路径数
		String sql_4="select (select count(*) from lcp_master t where t.cp_master_id=cp_id and t.cp_start_date is not null) zdy," +
				" (select count(*) from lcp_master a where a.cp_status=0) qy from dual";
		//System.out.println("sql_4==:"+sql_4);
		dataSet.funSetDataSetBySql(sql_4);
		String z=dataSet.funGetFieldByCol(0, "ZDY");
		String q=dataSet.funGetFieldByCol(0, "QY");
		if(z=="")z="0";
		if(q=="")q="0";
		int zdy=Integer.parseInt(z);
		int qy=Integer.parseInt(q);

		dataSet.funSetDataSetBySql(sql_2);
		String fh=dataSet.funGetFieldByCol(0, "FHRS");
		if(fh=="")fh="0";
		double fhrs = Double.parseDouble(fh);
		//System.out.println("fhrs====:"+fhrs);

		dataSet.funSetDataSetBySql(sql_3);
		String nr=dataSet.funGetFieldByCol(0, "NARURENSHU");
		if(nr=="")nr="0";
		double narurenshu = Double.parseDouble(nr);
		//完成人数
		dataSet.funSetDataSetBySql(sql_34);
		String wc=dataSet.funGetFieldByCol(0, "WCRENSHU");
		if(wc=="")wc="0";
		double wcrenshu = Double.parseDouble(wc);
		
		DecimalFormat fnum = new DecimalFormat("##0");
		String bfb = "0";
		String wcl = "0";
		try {
			bfb = fnum.format(Math.round(narurenshu * 100 / fhrs));
			wcl = fnum.format(Math.round(wcrenshu * 100 / narurenshu));
		} catch (Exception e) {
			// TODO: handle exception
			bfb = "0";
			wcl = "0";
		}
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?> "
				+ "<chart showShadow='true' baseFont='宋体' baseFontSize='20' caption='路径启用情况' xAxisName='情况' yAxisName='数'> "
				+ "	<set label='基本路径数：' value='"+jbljs+"' /> "
				+ "	<set label='启用中的路径数：' value='" + qy + "' /> "
				+ "	<set label='自定义的路径数：' value='" + zdy + "' /> "
				+ " <set label='全院平均纳入率（%）：' value='" + bfb + "' /> "
				+ " <set label='全院平均完成率（%）：' value='" + wcl + "' /> "
				+ "</chart>";
		return result;
	}

	@Override
	public String funGetFPCpTongji(String year, String month) {
		// TODO Auto-generated method stub
		// 符合人数
		String sql_2 = "";
		if (year == "" && month == "")
			sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+"";
		if (year != "")
			sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy')='"
				+ CommonUtil.funGetYear() + "'";
		if (month != "")
			sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"	
				+ CommonUtil.funGetYearAndMonth() + "'";

		// 纳入人数
		String sql_3 = "";
		if (year == "" && month == "")
			sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+"";
		if (year != "")
			sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy')='"
				+ CommonUtil.funGetYear() + "'";
		if (month != "")
			sql_3 = "select count(*) narurenshu from lcp_patient_visit where cp_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"	
				+ CommonUtil.funGetYearAndMonth() + "'";

		DataSet dataSet = new DataSet();

		dataSet.funSetDataSetBySql(sql_2);
		int fhrs = Integer.parseInt(dataSet.funGetFieldByCol(0, "FHRS"));

		dataSet.funSetDataSetBySql(sql_3);
		int narurenshu = Integer.parseInt(dataSet.funGetFieldByCol(0,
				"NARURENSHU"));

		int wnrrenshu = fhrs - narurenshu;

		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='全院路径纳入率' bgColor='#FFFFFF,CCCCCC' " 
				+" showPercentageValues='0' plotBorderColor='FFFFFF' formatNumberScale='0' numberSuffix='例' isSmartLineSlanted='1' showValues='1' showLabels='0' showLegend='1' xAxisName='Week' yAxisName='Sales'>"
				+ "<set label='纳入病例' value='" + narurenshu
				+ "' color='99CC00' alpha='60'/>"
				+ "<set label='未纳入病例' value='" + wnrrenshu
				+ "' color='333333' alpha='60'/>" + "</chart>";

		return result;
	}

	@Override
	public String funGetFPWC(String year, String month) {
		// TODO Auto-generated method stub
		String sql = "";
		if (year == "" && month == "")
			sql = "select * from   "
					+ "		(select count(*)zxz from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=1 and "+startDate+"),  "
					+ "		(select count(*)tc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=21 and "+startDate+"),  "
					+ "		(select count(*)wc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=11 and "+startDate+")";

		if (year != "")
			sql = "select * from   "
					+ "		(select count(*)zxz from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=1 and "+startDate+" and to_char(a.admission_date,'yyyy')='"
					+ CommonUtil.funGetYear()
					+ "') , "
					+ "		(select count(*)tc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=21 and "+startDate+" and to_char(a.admission_date,'yyyy')='"
					+ CommonUtil.funGetYear()
					+ "') ,  "
					+ "		(select count(*)wc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=11 and "+startDate+"  and to_char(a.admission_date,'yyyy')='"
					+ CommonUtil.funGetYear() + "') ";
		if (month != "")
			sql = "select * from   "
					+ "		(select count(*)zxz from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=1 and "+startDate+"  and to_char(a.admission_date,'yyyy-mm')='"
					+ CommonUtil.funGetYearAndMonth()
					+ "'),  "
					+ "		(select count(*)tc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=21 and "+startDate+" and to_char(a.admission_date,'yyyy-mm')='"
					+ CommonUtil.funGetYearAndMonth()
					+ "'),  "
					+ "		(select count(*)wc from lcp_patient_visit a where a.cp_master_id<>0 and a.cp_state=11 and "+startDate+" and to_char(a.admission_date,'yyyy-mm')='"
					+ CommonUtil.funGetYearAndMonth() + "')";
        //System.out.println("sql=:"+sql);
		DataSet dataSet = new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String zxz = dataSet.funGetFieldByCol(0, "zxz");
		String tc = dataSet.funGetFieldByCol(0, "tc");
		String wc = dataSet.funGetFieldByCol(0, "wc");

		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart showShadow='true' baseFont='宋体' baseFontSize='14' caption='全院路径完成情况' xAxisName='路径状态' yAxisName='病例数' numberSuffix='例' formatNumberScale='0' useRoundEdges='1'>"
				+ "<set label='路径完成' value='" + wc + "' />"
				+ "<set label='路径退出' value='" + tc + "' />"
				+ "<set label='路径执行中' value='" + zxz + "' /></chart>";

		return result;
	}

	@Override
	public String funGetFPPC(String year, String month) {
		// TODO Auto-generated method stub
		// 总共人数
		String sql_1 = "";
		String sql_2 = "";
		String sql_3 = "";
		String sql_4 = "";
		if (year == "" && month == "")
			sql_1 = "select count(*)total from lcp_patient_visit a where "+startDate+"";
		// 符合人数
		sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+"";

		// 符合不纳入人数
		sql_3 = "select count(*)fhbnr from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state=99 and "+startDate+"";

		// 符合，并且处理的人数
		sql_4 = "select count(*)fhycl from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state in(1,11,21,99) and "+startDate+"";

		if (year != ""){
		sql_1 = "select count(*)total from lcp_patient_visit a  where "+startDate+" and to_char(admission_date,'yyyy')='"
					+ CommonUtil.funGetYear() + "'";
		// 符合人数
		sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0  and "+startDate+" and to_char(admission_date,'yyyy')='"
				+ CommonUtil.funGetYear() + "'";

		// 符合不纳入人数
		sql_3 = "select count(*)fhbnr from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state=99 and "+startDate+" and to_char(a.admission_date,'yyyy')='"
				+ CommonUtil.funGetYear() + "'";

		// 符合，并且处理的人数
		sql_4 = "select count(*)fhycl from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state in(1,11,21,99) and "+startDate+" and to_char(a.admission_date,'yyyy')='"
				+ CommonUtil.funGetYear() + "'";
		}
		if (month != ""){
			sql_1 = "select count(*)total from lcp_patient_visit a where "+startDate+" and to_char(a.admission_date,'yyyy-mm')='"
					+ CommonUtil.funGetYearAndMonth() + "'";
		// 符合人数
		sql_2 = "select count(*) fhrs from lcp_patient_visit where conform_master_id<>0 and "+startDate+" and to_char(admission_date,'yyyy-mm')='"
				+ CommonUtil.funGetYearAndMonth() + "'";

		//////System.out.println("sql_2=:"+sql_2);
		// 符合不纳入人数
		sql_3 = "select count(*)fhbnr from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state=99 and "+startDate+" and to_char(a.admission_date,'yyyy-mm')='"
				+ CommonUtil.funGetYearAndMonth() + "'";

		// 符合，并且处理的人数
		sql_4 = "select count(*)fhycl from lcp_patient_visit a where a.conform_master_id<>0 and a.cp_state in(1,11,21,99) and "+startDate+" and to_char(a.admission_date,'yyyy-mm')='"
				+ CommonUtil.funGetYearAndMonth() + "'";
		}
		DataSet dataSet = new DataSet();

		dataSet.funSetDataSetBySql(sql_1);
		int total = Integer.parseInt(dataSet.funGetFieldByCol(0, "total"));

		dataSet.funSetDataSetBySql(sql_2);
		int fhrs = Integer.parseInt(dataSet.funGetFieldByCol(0, "fhrs"));

		dataSet.funSetDataSetBySql(sql_3);
		int fhbnr = Integer.parseInt(dataSet.funGetFieldByCol(0, "fhbnr"));

		dataSet.funSetDataSetBySql(sql_4);
		int fhycl = Integer.parseInt(dataSet.funGetFieldByCol(0, "fhycl"));

		int fhwcl = fhrs - fhycl;
		if (fhwcl<0){
			fhwcl=0;
		}

		int bfh = total - fhrs;

		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<chart showShadow='true' baseFont='宋体' baseFontSize='12' caption='全院路径排除情况' xAxisName='纳入条件合符情况' yAxisName='病例数' numberSuffix='例' formatNumberScale='0' useRoundEdges='1'>"
				+ "<set label='不符合条件' value='" + bfh + "' />"
				+ "<set label='符合但不纳入' value='" + fhbnr + "' />"
				+ "<set label='符合未处理' value='" + fhwcl + "' />" + "</chart>";
		return result;
	}
}
