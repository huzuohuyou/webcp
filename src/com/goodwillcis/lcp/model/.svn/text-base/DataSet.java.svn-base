/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：DataSet.java
// 文件功能描述：数据库常用操作各种方法汇总
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class DataSet {
	
	public DatabaseClass databaseTable=LcpUtil.getDatabaseClass();
	public DataSetClass dataSetTable=new DataSetClass();
	public ArrayList<String> fieldNames=new ArrayList<String>();
	ArrayList<String> fileNameNotSameListPos=new ArrayList<String>();
	public String fieldNamesUseInsert="";
	public String fieldValueUseInsert="";
	public int rowNum=0;
	public int colNum=0;
	private byte[] xmldataset;
	
	
	public byte[] getXmldataset() {
		return xmldataset;
	}
	public void setXmldataset(byte[] xmldataset) {
		this.xmldataset = xmldataset;
	}
	public ArrayList<String> getFieldNames() {
		return fieldNames;
	}
	public void setFieldNames(ArrayList<String> fieldNames) {
		this.fieldNames = fieldNames;
	}	
	public String getFieldNamesUseInsert() {
		return fieldNamesUseInsert;
	}
	public void setFieldNamesUseInsert(String fieldNamesUseInsert) {
		this.fieldNamesUseInsert = fieldNamesUseInsert;
	}
	public String getFieldValueUseInsert() {
		return fieldValueUseInsert;
	}
	public void setFieldValueUseInsert(String fieldValueUseInsert) {
		this.fieldValueUseInsert = fieldValueUseInsert;
	}
	public int getColNum() {
		return colNum;
	}
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * 对数据库进行添加或者删除操作
	 * 2011-07-28
	 * @param sql
	 * @return
	 */
	public int funRunSql(String sql){
		String key=databaseTable.FunGetSvrKey();
		return databaseTable.FunRunSQLCommand(key, sql);
	}
	/**
	 * 对数据库进行添加或者删除操作
	 * 2011-07-28
	 * @param sql
	 * @return
	 */
	public int FunRunSqlByFile(byte[] bb){
		return databaseTable.FunRunSqlByFile(bb);
	}
	/**
	 * 通过sql语句把数据撞到dataset里面
	 * 2011-04-26
	 * @param sql
	 */
	public void funSetDataSetBySql(String sql){
		dataSetTable=databaseTable.FunGetDataSetBySQL(sql);
		if(dataSetTable!=null){
			setXmldataset(databaseTable.FunGetDataByteSetBySQL(sql));
			colNum=dataSetTable.FunGetColCount();
			rowNum=dataSetTable.FunGetRowCount();
			ArrayList<String> fieldList=new ArrayList<String>();
			for(int i=0;i<colNum;i++){
				String fieldName=dataSetTable.FunGetColNameById(i);
				fieldList.add(fieldName);
			}
			setFieldNames(fieldList);
		}
		
	}

	/**
	 * 单条语句调用数据库是否成功
	 * @return
	 */
	public boolean isDiaoyongDantiaoSuc(){
		if(dataSetTable==null){
			return false;
		}
		return true;
	}
	/**
	 * 获得插入数据库是的值 字符串不带单引号 2011-05-30
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldSQlByCol(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="0";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.getOracleToDate(date);
		}
		return result;
	}
	
	/**
	 * 获得字段values的值的String类型不带单引号  格式化的时间不带有时分秒
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldByCol1(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="0";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\r\n", "<br/>");
			result=result.replace("\n", "<br/>");
			result=result.replace("\r", "<br/>");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate1(date);
		}
		return result;
	}
	
	/**
	 * 获得插入数据库是的值，带有单引号，例如varchar型的返回值是 'aa'
	 * @param i
	 * @param colName
	 * @return
	 */
	public String funGetFieldSQlWithDenoByCol(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="0";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
			result="'"+result+"'";
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.getOracleToDate(date);
		}
		return result;
	}
	/**
	 * 获得字段values的值的String类型不带单引号  2011-05-30
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldByCol(int i,String colName){
		colName=colName.toUpperCase();
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{

				result="0";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";

			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
			result=result.replace("\\", "\\\\");
			result=result.replace("\r\n", "").replace("\r", "").replace("\n", "");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate(date);
		}
		return result;
	}
	
	/**
	 * 获得字段values的值的String类型不带单引号，number类型的返回值为空  2011-05-30
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldByColPan(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
			result=result.replace("\\", "\\\\");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate(date);
		}
		return result;
	}
	
	/**
	 * 获得字段values的值的String类型不带单引号，number类型的返回值为空  2011-05-30
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldByCol2(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
			result=result.replace("\\", "\\\\");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate(date);
		}
		return result;
	}
	
	/**
	 * 对funGetFieldByCol方法做了一点修改，如果是int型为空的时候  返回"9"
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetNullFieldByCol(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			if(number!=null){
				result=number.toString();
			}else{
				result="9";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result=result.replace("\\r\\n", "<br/>");
			result=result.replace("\\n", "<br/>");
			result=result.replace("\\r", "<br/>");
			result=result.replace("\\", "\\\\");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate(date);
		}
		return result;
	}
	/**
	 * byte封装到dataset中
	 * @param dataByte
	 * @return
	 */
	public int funSetDataSetByByte(byte[] dataByte){
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		try {
			out.write(dataByte);
			dataSetTable.FunLoadFile(out);
			out.close();
		} catch (IOException e) {
			System.out.println("Byte封装到dataset出错");
			return -1;
		}
		dataSetTable.FunLoadFile(out);
		colNum=dataSetTable.FunGetColCount();
		rowNum=dataSetTable.FunGetRowCount();
		ArrayList<String> fieldList=new ArrayList<String>();
		for(int i=0;i<colNum;i++){
			String fieldName=dataSetTable.FunGetColNameById(i);
			fieldList.add(fieldName);
		}
		setFieldNames(fieldList);
		return 1;
	}
	/**
	 * 返回Insert语句中的字段字符串，最后一个字段后面没有逗号
	 * @return
	 */
	public String funGetFieldInsertSQL(){
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			aa=aa+arrayList.get(i)+",";
		}
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}
	/**
	 * 返回Insert语句中的字段字符串，最后一个字段后面没有逗号 如果tableFieldName有值，
	 * 那么把里面对应的值替换掉例如name1，name2 那么就dataset中有name1的名字用name2代替
	 * @param tableFieldName
	 * @return
	 */
	public String funGetFieldInsertSQL(HashMap<String, String> tableFieldName){
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			String name=arrayList.get(i);
			String _name="";
			if(tableFieldName!=null&&tableFieldName.size()>0){
				try {
					_name=tableFieldName.get(name);
				} catch (Exception e) {
					// TODO: handle exception
					_name="";
				}
				if(_name!=""&&_name!=null){
					name=_name;
				}
			}
			aa=aa+name+",";
		}
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}
	/**
	 * 返回Insert语句中的字段字符串，最后一个字段后面没有逗号
	 * （如果dataset中有fileNames这些字段，那么取dataset中的那些字段，如果没有那么取fileNames中的字段名称）
	 * @param fileNames 查看dataset中是否有这些字段
	 * @return
	 */
	public String funGetFieldInsertSQL(ArrayList<String> fileNames){
		CommonUtil util=new CommonUtil();
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			aa=aa+arrayList.get(i)+",";
		}
		fileNameNotSameListPos=util.funGetPosteriorNotSameListPos(arrayList, fileNames);
		for(int j=0;j<fileNameNotSameListPos.size();j++){
			aa=aa+fileNames.get(Integer.parseInt(fileNameNotSameListPos.get(j)))+",";
		}
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}
	/**
	 * 返回Insert语句中的字段对应值字符串，最后一个值后面没有逗号
	 * @return
	 */
	public String funGetFieldValueInsertSQL(int row){
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			String fieldName=arrayList.get(i);
			String fieldVal=this.funGetFieldSQlWithDenoByCol(row, fieldName);
			aa=aa+fieldVal+",";
		}
		
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}
	/**
	 * 返回Insert语句中的字段对应值字符串，最后一个值后面没有逗号
	 * @param row 第几行
	 * @param fileValues 新增值
	 * @return
	 */
	public String funGetFieldValueInsertSQL(int row,ArrayList<String> fileValues){
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			String fieldName=arrayList.get(i);
			String fieldVal=this.funGetFieldSQlWithDenoByCol(row, fieldName);
			aa=aa+fieldVal+",";
		}
		for(int j=0;j<fileNameNotSameListPos.size();j++){
			aa=aa+fileValues.get(Integer.parseInt(fileNameNotSameListPos.get(j)))+",";
		}
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}
	/**
	 * 取得一列数据的所有值	2011-06-21
	 * @param fieldName 对应列名称
	 * @return
	 */
	public ArrayList<String> funGetOneFieldListValues(String fieldName){
		ArrayList<String> list=new ArrayList<String>();
		int row=getRowNum();
		for(int i=0;i<row;i++){
			String value=funGetFieldByCol(i, fieldName);
			list.add(value);
		}
		if(row>0)
		return list;
		else
			return null;
	}
	/**
	 * 通过sql语句把数据撞到dataset里面
	 * 2011-04-26
	 * @param sql
	 */
	public void funSetDataSetBySql(String sql,int start,int end){
		dataSetTable=databaseTable.FunGetPageDataSetBySQL(sql, start, end);
		colNum=dataSetTable.FunGetColCount();
		rowNum=dataSetTable.FunGetRowCount();
		ArrayList<String> fieldList=new ArrayList<String>();
		for(int i=0;i<colNum;i++){
			String fieldName=dataSetTable.FunGetColNameById(i);
			fieldList.add(fieldName);
		}
		setFieldNames(fieldList);
	}
	/**
	 * 判断对应表名的某些值是否为空
	 * @param keyName 字段名称
	 * @return true 为空 false不为空
	 */
	public boolean funVerKeyNull(ArrayList<String > keyName){
		for(int j=0;j<keyName.size();j++){
		}
		int oldRow=this.getRowNum();
		if(oldRow==0){
			return false;
		}
		int keyNameSize=keyName.size();
		for(int k=0;k<oldRow;k++){
			for(int kk=0;kk<keyNameSize;kk++){
				String value=this.funGetFieldByCol(k, keyName.get(kk));
				if(value==""){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 获得字段values的值的String类型若查询的字段是字符型那么带有单引号  2011-05-30
	 * @param i 
	 * @param colName  
	 * @param type 
	 * @return
	 */
	public  String funGetFieldWithDYHByCol(int i,String colName){
		String result="";
		String type=dataSetTable.FunGetColTypeByColName(colName);
		if("xs:decimal".equals(type)){
			Number number=dataSetTable.FunGetDataAsNumberByColName(i, colName);
			int a;
			if(number!=null){
				a=number.intValue();
				result=Integer.toString(a);
			}else{
				result="0";
			}
		}else if("xs:string".equals(type)){
			result=dataSetTable.FunGetDataAsStringByColName(i, colName);
			if(result==null){
				result="";
			}
			result="'"+result+"'";
			result=result.replace("\r\n", "<br/>");
			result=result.replace("\n", "<br/>");
			result=result.replace("\r", "<br/>");
		}else if("xs:dateTime".equals(type)){
			Date date=dataSetTable.FunGetDataAsDateByColName(i, colName);			
			result=CommonUtil.traTimeStringByDate(date);
		}
		return result;
	}
	/**
	 * 取得一列数据的所有值字符串类型带有单引号	2011-07-06
	 * @param fieldName 对应列名称
	 * @return ArrayList
	 */
	public String funGetOneFieldStringValues(String fieldName){
		String value="";
		int row=getRowNum();
		for(int i=0;i<row;i++){
			value=value+funGetFieldWithDYHByCol(i, fieldName)+",";
		}
		if(row>0){
			value=value.substring(0, value.length()-1);
		}
		return value;
	}
	/**
	 * 取得某一行的数据总和
	 * @param row
	 * @param fields
	 * @return
	 */
	public int funGetSumByColAndFiieds(int row,ArrayList<String> fields){
		int result=0;
		if(fields!=null && fields.size()>0){
			for(int i=0;i<fields.size();i++){
				int a=Integer.parseInt(this.funGetFieldByCol(row, fields.get(i)));
				result=result+a;
			}
		}
		return result;
	}
	/**
	 * 生成update语句(暂时不用)
	 * @param row
	 * @return
	 */
	public String funGetUpdateField(int row){
		ArrayList<String> arrayList=this.getFieldNames();
		String aa="";
		for(int i=0;i<arrayList.size();i++){
			String fieldName=arrayList.get(i);
			String fieldVal=this.funGetFieldSQlWithDenoByCol(row, fieldName);
			aa=aa+fieldName+"="+fieldVal+",";
		}
		
		aa=aa.substring(0, aa.length()-1);
		return aa;
	}

}
