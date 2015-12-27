/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：CommonUtil.java
// 文件功能描述：工具类
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.CpNodeTableMaint;

public class CommonUtil {
	
	/**
	 * 获取String数组
	 * @param ids
	 * @param split
	 * @return
	 */
	public static String[]getArrByString(String sour,String split){
		String[]idsArr=new String[0];
		String[]newidsArr={"","",""};
		if(!sour.isEmpty()){
			idsArr=sour.split(split);
		}
		if(idsArr.length<3){
			for(int i=0;i<idsArr.length;i++){
				newidsArr[i]=idsArr[i];
			}
		}else{
			newidsArr=idsArr;
		}
		return newidsArr;
	}

	/**
	 * 取得数据库当前时间
	 * @return 返回String类型的时间 例如 2011-05-30 00:00:00
	 */
	public static String getDBTimeString(){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		Date dateNow=databaseClass.FunGetDbNow(true);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr=sdf.format(dateNow);
		return dateNowStr;
	}
	
	/**
	 * 取得数据库当前时间
	 * @return 返回String类型的时间 例如 2011-05-30
	 */
	public static String getDBTimeString1(){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		Date dateNow=databaseClass.FunGetDbNow(true);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr=sdf.format(dateNow);
		return dateNowStr;
	}
	/**
	 * 替换字符串
	 * @param sour 源语句
	 * @param old 被替换的字符
	 * @param newStr 替换的字符
	 * @return
	 */
	public static String replace(String sour,String old,String newStr){
		if(sour.indexOf(old)>=0){
			sour=sour.replace(old, newStr);
			sour=sour.substring(newStr.length());
		}
		return sour;
	}
	/**
	 * 替换插入数据库中的单引号为两个单引号
	 * @param sour
	 * @return
	 */
	public static String replactInsertDBApostrophe(String sour){
		return sour.replace("'","''");
	}
	/**
	 * 替换表格中的特殊字符，替换"为&quot;;
	 * @param sour
	 * @return
	 */
	public static String replactCharacter(String sour){
		sour=sour.replace("\"","&quot;");
		//sour=sour.replace("\\","&#92;");
		return sour;
	}
	/**
	 * 格式化时间
	 * @param date
	 * @return String 例如  2011-05-30 00:00:00
	 */
	public static String traTimeStringByDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr="";
		if(date!=null){
		try {
			dateNowStr=sdf.format(date);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("格式化时间错误traTimeStringByDate()");
			dateNowStr="";
		}}
		return dateNowStr;
	}
	/**
	 * 返回Oracle插入数据库sql语句的时间函数
	 * @return to_date('2011-04-25 12:00:00','yyyy-mm-dd hh24:mi:ss')
	 */
	public static String getOracleToDate(){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		if(databaseClass==null){
			return "";
		}
		Date dateNow=databaseClass.FunGetDbNow(true);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr=sdf.format(dateNow);		
		dateNowStr="to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss')";
		return dateNowStr;
	}
	/**
	 * 返回Oracle插入数据库sql语句的时间函数
	 * @date 
	 * @return to_date('2011-04-25 12:00:00','yyyy-mm-dd hh24:mi:ss')
	 */
	public static String getOracleToDate(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr=null;
		if(date!=null){
		try {
			dateNowStr=sdf.format(date);		
			dateNowStr="to_date('"+dateNowStr+"','yyyy-mm-dd hh24:mi:ss')";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("格式化时间错误getOracleToDate()");
			dateNowStr=null;
		}}
		return dateNowStr;
	}
	/**
	 * 取得两个list中的相同值		2011-06-22
	 * @param list1
	 * @param list2
	 * @return
	 */
	public ArrayList<String> funGetSameValues(ArrayList<String>list1,ArrayList<String>list2){
		ArrayList<String> arrayList=new ArrayList<String>();
		if(list1==null||list2==null)
			return null;
		int list1Len=list1.size();
		int list2Len=list2.size();
		for(int i=0;i<list1Len;i++){
			String list_1=list1.get(i);
			for(int j=0;j<list2Len;j++){
				String list_2=list2.get(j);
				if(list_1.equals(list_2)){
					arrayList.add(list_2);
				}
			}
		}
		return arrayList;
	}
	/**
	 * 把数组变成字符串，用split参数值隔开
	 * @param sour
	 * @param split
	 * @return
	 */
	public static String getStringByArr(String[]sour,String split){
		String result="";
		int size=sour.length;
		if(size>0){
			for(int i=0;i<size;i++){
				result=result+sour[i]+split;
			}
			return result.substring(0, result.length()-split.length());
		}
		return "";
	}
	/**
	 * 取得后者有的但是前者没有的值
	 * @param list1 前者
	 * @param list2 后者
	 * @return list2中有的，但是list1中没有的值
	 */
	public ArrayList<String> funGetPosteriorNotSameList(ArrayList<String>list1,ArrayList<String>list2){
		ArrayList<String> sameList=funGetSameValues(list1, list2);
		if(sameList==null){
			return null;
		}
		int lastSize=list2.size();
		int sameSize=sameList.size();
		for(int i=0;i<lastSize;i++){
			String fileName=list2.get(i);
			for(int j=0;j<sameSize;j++){
				if(fileName.equals("sameName")){
					list2.remove(i);
				}
			}
		}
		return list2;
	}
	/**
	 * 取得后者有的但是前者没有的值的位置
	 * @param list1 前者
	 * @param list2 后者
	 * @return list2中有的，但是list1中没有的值的位置
	 */
	public ArrayList<String> funGetPosteriorNotSameListPos(ArrayList<String>list1,ArrayList<String>list2){
		ArrayList<String> sameList=funGetSameValues(list1, list2);
		if(sameList==null){
			return null;
		}
		ArrayList<String> pos=new ArrayList<String>();
		int lastSize=list2.size();
		int sameSize=sameList.size();
		boolean same=false;
		for(int i=0;i<lastSize;i++){
			String fileName=list2.get(i);
			for(int j=0;j<sameSize;j++){
				String sameName=sameList.get(j);
				if(fileName.equals(sameName)){
					same=true;
					break;
				}
			}
			if(!same){//不重复
				pos.add(Integer.toString(i));
				same=false;
			}else{//重复
				same=false;
			}
		}
		return pos;
	}
	/**
	 * 将list编程字符串，并用逗号隔开
	 * @param list
	 * @param flag 是否带有单引号，true带有，false不带有
	 * @return
	 */
	public static String funGetStringByArraylist(ArrayList<String> list,boolean flag){
		String aa="";
		int row=list.size();
		for(int i=0;i<row;i++){
			if(flag){
				aa=aa+"'";
			}
			aa=aa+list.get(i);
			if(flag){
				aa=aa+"'";
			}
			aa=aa+",";
		}
		if(row>0){
			aa=aa.substring(0, aa.length()-1);
		}
		return aa;
	}
	/**
	 * 路径维护专用函数，生成需要保存的对象 对应从表
	 * @param selectIDS
	 * @return
	 */
	public static ArrayList<CpNodeTableMaint> funSetCpNodeTableMaintItemByStr(String selectIDS){
		ArrayList<CpNodeTableMaint> list=new ArrayList<CpNodeTableMaint>();
		selectIDS=selectIDS.substring(0, selectIDS.length()-2);
		String [] ids=selectIDS.split(":;");
		int ids_len=ids.length;
		for(int i=0;i<ids_len;i++){
			String selectid=ids[i];
			String [] _select_id=selectid.split("_");
			CpNodeTableMaint maint=new CpNodeTableMaint( _select_id[2], _select_id[3], _select_id[4], _select_id[5], _select_id[6],_select_id[7]);
			list.add(maint);
		}
		return list;
	}
	/**
	 * 路径维护专用函数，生成需要保存的对象 对应主表
	 * @param selectIDS
	 * @return
	 */
	public static ArrayList<CpNodeTableMaint> funSetCpNodeTableMaintPointByStr(String selectIDS){
		ArrayList<CpNodeTableMaint> list=new ArrayList<CpNodeTableMaint>();
		selectIDS=selectIDS.substring(0, selectIDS.length()-2);
		String [] ids=selectIDS.split(":;");
		int ids_len=ids.length;
		for(int i=0;i<ids_len;i++){
			String selectid=ids[i];
			String [] _select_id=selectid.split("_");
			CpNodeTableMaint maint=new CpNodeTableMaint( _select_id[2], _select_id[3], _select_id[4], _select_id[5],_select_id[7]);
			boolean isRepeat=isRepeat(list, maint);
			if(!isRepeat)
			list.add(maint);
		}
		list=funSetCpNodeTableMaintPointByStr1(list);
//		for(int j=0;j<list.size();j++){
//			System.out.print(list.get(j).getCp_id()+"\t");
//			System.out.print(list.get(j).getCp_node_id()+"\t");
//			System.out.print(list.get(j).getCp_node_table_id()+"\t");
//			System.out.print(list.get(j).getAuto_item()+"\t");
//			System.out.println();
//		}
		return list;
	}
	 //路径维护专用函数，查看书否有重复数据
	private static boolean  isRepeat(ArrayList<CpNodeTableMaint> list,CpNodeTableMaint main){
		int list_len=list.size();
		for(int i=0;i<list_len;i++){
			CpNodeTableMaint new_main=list.get(i);
			if(new_main.getCp_id().equals(main.getCp_id())){
				if(new_main.getCp_node_id().equals(main.getCp_node_id())){
					if(new_main.getCp_node_table_id().equals(main.getCp_node_table_id())){
						if(new_main.getAuto_item().equals(main.getAuto_item())){
						//	System.out.println("重复数据");
							return true;					
						}	
					}
				}
			}
		}
		return false;
	}
	//既有自动也有手动，那么取手动，反之去自动
	private static ArrayList<CpNodeTableMaint> funSetCpNodeTableMaintPointByStr1(ArrayList<CpNodeTableMaint> list){
		ArrayList<CpNodeTableMaint> list1=new ArrayList<CpNodeTableMaint>();
		ArrayList<CpNodeTableMaint> list2=new ArrayList<CpNodeTableMaint>();
		ArrayList<CpNodeTableMaint> list3=new ArrayList<CpNodeTableMaint>();
		for(int k=0;k<list.size();k++){
			list3.add(list.get(k));
		}
		list1=list;
		list2=list;
		int ii=0;
		int list1_len=list1.size();
		int list2_len=list2.size();
		for(int i=0;i<list1_len-1;i++){
			CpNodeTableMaint main_1=list1.get(i);
			for(int j=i+1;j<list2_len;j++){
				CpNodeTableMaint main_2=list1.get(j);
				if(main_1.getCp_id().equals(main_2.getCp_id())){
					if(main_1.getCp_node_id().equals(main_2.getCp_node_id())){
						if(main_1.getCp_node_table_id().equals(main_2.getCp_node_table_id())){
							if(!main_1.getAuto_item().equals(main_2.getAuto_item())){
								if (main_2.getAuto_item().equals("1")) {
									list3.remove(i);
								}else{
									list3.remove(j-ii);
								}
								ii++;
							//	System.out.println("需要删除");				
							}	
						}
					}
				}
			}		
		}
		return list3;
	}
	
	
	/**
	 * 根据字段特定的值来获取对应的字段的值  以ArrayList<String>方式取出
	 * @param args
	 */
	public static String FunGetDataByValue(DataSetClass dsc, String colName, String value, String purName1){
		//得到行数
		if(value == ""){
			return "";
		}else{
		int rows = dsc.FunGetRowCount();
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0; i<rows; i++){
			String s = dsc.FunGetDataAsStringByColName(i, colName);
			if(s.equals(value)){
				String purValue1 = dsc.FunGetDataAsStringByColName(i, purName1);
				list.add(purValue1);
			}
		}
		if(!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	}
	/**
	 * 格式化时间
	 * @param date
	 * @return String 例如  2011-05-30
	 */
	public static String traTimeStringByDate1(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr="";
		if(date!=null){
		try {
			dateNowStr=sdf.format(date);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("格式化时间错误traTimeStringByDate()");
			dateNowStr="";
		}}
		return dateNowStr;
	}
	/**
	 * 时间转化函数String->Date String格式要求 yyyy-mm-dd
	 * @param time  yyyy-mm-dd
	 * @return
	 */
	public static Date traStringToDate(String time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
			if(time!=null){
			try {
				date=sdf.parse(time);			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("格式化时间错误traStringToDate()");
				date=null;
			}}
		
		return date;
	}
	/**
	 * 时间转化函数String->Date String格式要求 yyyy-MM-dd HH:mm:ss
	 * @param time yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date traStringToDate1(String time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		if(time!=null){
			try {
				date=sdf.parse(time);			
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("格式化时间错误traStringToDate1()");
				date=null;
			}
		}
		return date;
	}
	/**
	 * 取得年份
	 * @return
	 */
	public static String funGetYear(){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		Date dateNow=databaseClass.FunGetDbNow(true);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		String dateNowStr=sdf.format(dateNow);
		return dateNowStr;
	}
	/**
	 * 取得年月
	 * @return
	 */
	public static String funGetYearAndMonth(){
		DatabaseClass databaseClass=LcpUtil.getDatabaseClass();
		Date dateNow=databaseClass.FunGetDbNow(true);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		String dateNowStr=sdf.format(dateNow);
		return dateNowStr;
	}
	
}
