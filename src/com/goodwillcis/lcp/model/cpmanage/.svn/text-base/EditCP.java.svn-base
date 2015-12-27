package com.goodwillcis.lcp.model.cpmanage;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class EditCP  {

	public static void main(String[] args) {
		/*	EditCP ed=new EditCP();
			DatabaseClass db = new DatabaseClass(PropertiesUtil.get(PropertiesUtil.DATABASE_URL));
				 int isUpdateSuc=888;
				
				 String batSQL=ed.copyCPInfo("1");
				 while("error".equals(batSQL)){
					 batSQL=ed.copyCPInfo("1");
				 }
			
				 System.out.println(batSQL);
				try {
					isUpdateSuc = db.FunRunSqlByFile(batSQL.getBytes("GBK"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			System.out.println(isUpdateSuc);*/
		DatabaseClass db = LcpUtil.getDatabaseClass();
		String maxIdSql="SELECT MAX(cp_id) MAXID from DCP_MASTER ";
		DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
		Number maxid=dc.FunGetDataAsNumberById(0, 0);
		System.out.println(maxid);
	}

	public String copyCPInfo(String cp_id){
		String batSQL="";
		try {
			String nowDate=CommonUtil.getOracleToDate();
			DatabaseClass db = LcpUtil.getDatabaseClass();
			String maxIdSql="SELECT MAX(cp_id) MAXID from DCP_MASTER ";
			DataSetClass dc=db.FunGetDataSetBySQL(maxIdSql);
			Number maxid=dc.FunGetDataAsNumberById(0, 0);
			System.out.println(maxid);
			
			String selectSQL="select CP_ANTIBIOTICS from dcp_master_antibiotics  where cp_id="+cp_id;
		
			 dc=db.FunGetDataSetBySQL(selectSQL);
			if(dc.FunGetDataAsStringById(0, 0)!=""){
				batSQL = "delete from dcp_master_antibiotics  where cp_id="+maxid+"\r\n";
				 batSQL=batSQL+"insert into dcp_master_antibiotics (CP_ID,CP_ANTIBIOTICS,SYS_IS_DEL,SYS_LAST_UPDATE)"+
				" values ("+maxid+",'"+dc.FunGetDataAsStringById(0,0)+"',0,"+nowDate+")"+"\r\n";
			}


				
			
			selectSQL="select CP_DIAGNOSIS_BASED,CP_TREATMENT from dcp_master_based where cp_id="+cp_id;
			 dc=db.FunGetDataSetBySQL(selectSQL);
			 if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from dcp_master_based  where cp_id="+maxid+"\r\n";
					 String cp_diagnosis_based=dc.FunGetDataAsStringById(0, 0);
					 String cp_treatment=dc.FunGetDataAsStringById(0, 1);
					 batSQL=batSQL+"insert into dcp_master_based (CP_ID,CP_DIAGNOSIS_BASED,CP_TREATMENT,SYS_IS_DEL,SYS_LAST_UPDATE)"
					 		+" values ("+maxid+",'"+cp_diagnosis_based+"','"+cp_treatment+"',0,"+nowDate+")"+"\r\n";
			 }
			
			
			 selectSQL="select cp_discharge_id,cp_discharge_name,DISPLAY_ORDER from dcp_master_discharge where cp_id="+cp_id;
			 dc=db.FunGetDataSetBySQL(selectSQL);
			 if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from dcp_master_discharge  where cp_id="+maxid+"\r\n";
				 for(int i=0;i<dc.FunGetRowCount();i++){
					 Number cp_discharge_id=dc.FunGetDataAsNumberById(i, 0);
					 String cp_discharge_name=dc.FunGetDataAsStringById(i, 1);
					 Number dispay_order=dc.FunGetDataAsNumberById(i, 2);
					 batSQL=batSQL+"insert into dcp_master_discharge (CP_ID,CP_DISCHARGE_ID,CP_DISCHARGE_NAME,DISPLAY_ORDER,SYS_IS_DEL,SYS_LAST_UPDATE)"+
					 " values ("+maxid+","+cp_discharge_id+",'"+cp_discharge_name+"',"+dispay_order+",0,"+nowDate+")"+"\r\n";
				 }
			 }
			 
			
			 selectSQL="select CP_EXCLUDE_ID,CP_EXCLUDE_NAME,CP_EXCLUDE_VALUE, DISPLAY_ORDER from DCP_MASTER_EXCLUDE where cp_id="+cp_id;
			 dc=db.FunGetDataSetBySQL(selectSQL);
			 if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from DCP_MASTER_EXCLUDE  where cp_id="+maxid+"\r\n";
				 for(int i=0;i<dc.FunGetRowCount();i++){
					 Number CP_EXCLUDE_ID=dc.FunGetDataAsNumberById(i, 0);
					 String CP_EXCLUDE_NAME=dc.FunGetDataAsStringById(i, 1);
					 String CP_EXCLUDE_VALUE=dc.FunGetDataAsStringById(i, 2);
					 Number DISPLAY_ORDER=dc.FunGetDataAsNumberById(i, 3);
					 batSQL=batSQL+"insert into dcp_master_exclude (CP_ID,CP_EXCLUDE_ID,CP_EXCLUDE_NAME,CP_EXCLUDE_VALUE,DISPLAY_ORDER,SYS_IS_DEL,SYS_LAST_UPDATE)"+
					 " values ("+maxid+","+CP_EXCLUDE_ID+",'"+CP_EXCLUDE_NAME+"',"+CP_EXCLUDE_VALUE+","+DISPLAY_ORDER+",0,"+nowDate+")"+"\r\n";
				 }
			 }
			
			
			 selectSQL="select CP_INCOME_ID,CP_INCOME_TYPE,CP_INCOME_NAME,CP_INCOME_CODE,DISPLAY_ORDER from DCP_MASTER_INCOME where cp_id="+cp_id;
			 dc=db.FunGetDataSetBySQL(selectSQL);
			 if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from DCP_MASTER_INCOME  where cp_id="+maxid+"\r\n";
				 for(int i=0;i<dc.FunGetRowCount();i++){
					 Number CP_INCOME_ID=dc.FunGetDataAsNumberById(i, 0);
					 String CP_INCOME_TYPE=dc.FunGetDataAsStringById(i, 1);
					 String CP_INCOME_NAME=dc.FunGetDataAsStringById(i, 2);
					 String CP_INCOME_CODE=dc.FunGetDataAsStringById(i, 3);
					 Number DISPLAY_ORDER=dc.FunGetDataAsNumberById(i, 4);
					 batSQL=batSQL+"insert into dcp_master_income (CP_ID,CP_INCOME_ID,CP_INCOME_TYPE,CP_INCOME_NAME,CP_INCOME_CODE,DISPLAY_ORDER, SYS_IS_DEL, SYS_LAST_UPDATE)"+
					 " values ("+maxid+","+CP_INCOME_ID+",'"+CP_INCOME_TYPE+"','"+CP_INCOME_NAME+"','"+CP_INCOME_CODE+"',"+DISPLAY_ORDER+",0,"+nowDate+")"+"\r\n";
				 }
			 }
			 
			
			 selectSQL="select CP_NODE_ID,CP_NODE_PARENT_ID,CP_NODE_NAME, CP_NODE_DAYS_MIN,CP_NODE_DAYS_MAX,CP_NODE_DAYS,CP_NODE_TYPE" +
			 				" from dcp_master_node where cp_id="+cp_id;
			 dc=db.FunGetDataSetBySQL(selectSQL);
			 if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from dcp_master_node  where cp_id="+maxid+"\r\n";
				 for(int i=0;i<dc.FunGetRowCount();i++){
					 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
					 Number CP_NODE_PARENT_ID=dc.FunGetDataAsNumberById(i, 1);
					 String CP_NODE_NAME=dc.FunGetDataAsStringById(i, 2);
					 Number CP_NODE_DAYS_MIN=dc.FunGetDataAsNumberById(i, 3);
					 Number CP_NODE_DAYS_MAX=dc.FunGetDataAsNumberById(i, 4);
					 Number CP_NODE_DAYS=dc.FunGetDataAsNumberById(i, 5);
					 Number CP_NODE_TYPE=dc.FunGetDataAsNumberById(i, 6);
					 batSQL=batSQL+"insert into dcp_master_node (CP_ID, CP_NODE_ID,CP_NODE_PARENT_ID,CP_NODE_NAME,CP_NODE_DAYS_MIN," +
					 				" CP_NODE_DAYS_MAX,CP_NODE_DAYS,CP_NODE_TYPE,SYS_IS_DEL,SYS_LAST_UPDATE)"+
					 				" values ("+maxid+","+CP_NODE_ID+","+CP_NODE_PARENT_ID+",'"+CP_NODE_NAME+"',"+CP_NODE_DAYS_MIN+","+
					 				CP_NODE_DAYS_MAX+","+CP_NODE_DAYS+","+CP_NODE_TYPE+",0,"+nowDate+")"+"\r\n";
				 }
			 }

			
			 selectSQL="select CP_VARIATION_ID,CP_VARIATION_NAME,DISPLAY_ORDER from dcp_master_variation where cp_id="+cp_id;
			dc=db.FunGetDataSetBySQL(selectSQL);
			if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from dcp_master_variation  where cp_id="+maxid+"\r\n";
			for(int i=0;i<dc.FunGetRowCount();i++){
				 Number CP_VARIATION_ID=dc.FunGetDataAsNumberById(i, 0);
				 String CP_VARIATION_NAME=dc.FunGetDataAsStringById(i, 1);
				 Number DISPLAY_ORDER=dc.FunGetDataAsNumberById(i, 2);
				 batSQL=batSQL+"insert into dcp_master_variation (CP_ID,CP_VARIATION_ID,CP_VARIATION_NAME,DISPLAY_ORDER,SYS_IS_DEL,SYS_LAST_UPDATE)" +
				 				" values ("+maxid+","+CP_VARIATION_ID+",'"+CP_VARIATION_NAME+"',"+DISPLAY_ORDER+",0,"+nowDate+")"+"\r\n";
			}
			}
			
			
			
			 selectSQL="select CP_NODE_ID,CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_TEXT,NEED_ITEM,DOCTOR_NO from DCP_NODE_DOCTOR where cp_id="+cp_id;
				dc=db.FunGetDataSetBySQL(selectSQL);
				if(dc.FunGetDataAsStringById(0,0)!=""){
					 batSQL=batSQL+"delete from DCP_NODE_DOCTOR  where cp_id="+maxid+"\r\n";
				for(int i=0;i<dc.FunGetRowCount();i++){
					 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
					 Number CP_NODE_DOCTOR_ID=dc.FunGetDataAsNumberById(i, 1);
					 String CP_NODE_DOCTOR_TEXT=dc.FunGetDataAsStringById(i, 2);
					 Number NEED_ITEM=dc.FunGetDataAsNumberById(i, 3);
					 String DOCTOR_NO=dc.FunGetDataAsStringById(i, 4);
					 batSQL=batSQL+"insert into dcp_node_doctor (CP_ID,CP_NODE_ID,CP_NODE_DOCTOR_ID,CP_NODE_DOCTOR_TEXT,NEED_ITEM,DOCTOR_NO,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					 				" values ("+maxid+","+CP_NODE_ID+","+CP_NODE_DOCTOR_ID+",'"+CP_NODE_DOCTOR_TEXT+"',"+NEED_ITEM+",'"+DOCTOR_NO+"',0,"+nowDate+")"+"\r\n";
				}
				}
			
			
				
			 selectSQL="select CP_NODE_ID,CP_NODE_NURSE_ID,CP_NODE_NURSE_TEXT,NEED_ITEM,NURSE_NO from DCP_NODE_NURSE where cp_id="+cp_id;
				dc=db.FunGetDataSetBySQL(selectSQL);
				if(dc.FunGetDataAsStringById(0,0)!=""){
					 batSQL=batSQL+"delete from DCP_NODE_NURSE  where cp_id="+maxid+"\r\n";
				for(int i=0;i<dc.FunGetRowCount();i++){
					 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
					 Number CP_NODE_NURSE_ID=dc.FunGetDataAsNumberById(i, 1);
					 String CP_NODE_NURSE_TEXT=dc.FunGetDataAsStringById(i, 2);
					 Number NEED_ITEM=dc.FunGetDataAsNumberById(i, 3);
					 String NURSE_NO=dc.FunGetDataAsStringById(i, 4);
					 batSQL=batSQL+"insert into DCP_NODE_NURSE (CP_ID,CP_NODE_ID,CP_NODE_NURSE_ID,CP_NODE_NURSE_TEXT,NEED_ITEM, NURSE_NO,SYS_IS_DEL,SYS_LAST_UPDATE)" +
					 				" values ("+maxid+","+CP_NODE_ID+","+CP_NODE_NURSE_ID+",'"+CP_NODE_NURSE_TEXT+"',"+NEED_ITEM+","+NURSE_NO+",0,"+nowDate+")"+"\r\n";
				}
				}
				
				
				
			 selectSQL="select CP_NODE_ID,CP_NODE_ORDER_ID,CP_NODE_ORDER_TEXT,NEED_ITEM,ORDER_NO,ORDER_TYPE from DCP_NODE_ORDER where cp_id="+cp_id;
			dc=db.FunGetDataSetBySQL(selectSQL);
			if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from DCP_NODE_ORDER  where cp_id="+maxid+"\r\n";
			for(int i=0;i<dc.FunGetRowCount();i++){
				 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
				 Number CP_NODE_ORDER_ID=dc.FunGetDataAsNumberById(i, 1);
				 String CP_NODE_ORDER_TEXT=dc.FunGetDataAsStringById(i, 2);
				 Number NEED_ITEM=dc.FunGetDataAsNumberById(i, 3);
				 String ORDER_NO=dc.FunGetDataAsStringById(i, 4);
				 String ORDER_TYPE=dc.FunGetDataAsStringById(i, 5);
				 batSQL=batSQL+"insert into dcp_node_order (CP_ID,CP_NODE_ID,CP_NODE_ORDER_ID,CP_NODE_ORDER_TEXT,NEED_ITEM,ORDER_NO,ORDER_TYPE,SYS_IS_DEL,SYS_LAST_UPDATE)" +
				 				" values ("+maxid+","+CP_NODE_ID+","+CP_NODE_ORDER_ID+",'"+CP_NODE_ORDER_TEXT+"',"+NEED_ITEM+",'"+ORDER_NO+"','"+ORDER_TYPE+"',0,"+nowDate+")"+"\r\n";
			}
			}


			
			
			 selectSQL="select CP_NODE_ID,CP_NEXT_NODE_ID from DCP_NODE_RELATE where cp_id="+cp_id;
			dc=db.FunGetDataSetBySQL(selectSQL);
			if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from DCP_NODE_RELATE  where cp_id="+maxid+"\r\n";
			for(int i=0;i<dc.FunGetRowCount();i++){
				 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
				 Number CP_NEXT_NODE_ID=dc.FunGetDataAsNumberById(i, 1);
				 batSQL=batSQL+"insert into DCP_NODE_RELATE (CP_ID,CP_NODE_ID,CP_NEXT_NODE_ID,SYS_IS_DEL,SYS_LAST_UPDATE)" +
				 				" values ("+maxid+","+CP_NODE_ID+","+CP_NEXT_NODE_ID+",0,"+nowDate+")"+"\r\n";
			}
			}
				

			
			selectSQL="select CP_NODE_ID,CP_NODE_VARIATION_ID,CP_NODE_VARIATION_TEXT from DCP_NODE_VARIATION where cp_id="+cp_id;
			dc=db.FunGetDataSetBySQL(selectSQL);
			
			if(dc.FunGetDataAsStringById(0,0)!=""){
				 batSQL=batSQL+"delete from DCP_NODE_VARIATION  where cp_id="+maxid+"\r\n";
			for(int i=0;i<dc.FunGetRowCount();i++){
				 Number CP_NODE_ID=dc.FunGetDataAsNumberById(i, 0);
				 Number CP_NODE_VARIATION_ID=dc.FunGetDataAsNumberById(i, 1);
				 String  CP_NODE_VARIATION_TEXT=dc.FunGetDataAsStringById(i, 2);
				 batSQL=batSQL+"insert into DCP_NODE_VARIATION (CP_ID,CP_NODE_ID,CP_NODE_VARIATION_ID,CP_NODE_VARIATION_TEXT,SYS_IS_DEL,SYS_LAST_UPDATE)" +
				 				" values ("+maxid+","+CP_NODE_ID+","+CP_NODE_VARIATION_ID+",'"+CP_NODE_VARIATION_TEXT+"',0,"+nowDate+")"+"\r\n";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			batSQL="error";
		}
		return batSQL;
		
	}
}
