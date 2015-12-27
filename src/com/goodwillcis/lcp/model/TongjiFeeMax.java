/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：TongjiFeeMax.java
//文件功能描述：统计费用最大段
//用于自动签名使用
//创建人：刘植鑫 
//创建日期：2011/10/24
//
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.lcp.util.CommonUtil;

public class TongjiFeeMax {

	private String feeQuduan;
	
	private String bfb;
	
	private int quduanrenshu;
	public int getQuduanrenshu() {
		return quduanrenshu;
	}
	private void setQuduanrenshu(int quduanrenshu) {
		this.quduanrenshu = quduanrenshu;
	}
	public String getFeeQuduan() {
		return feeQuduan;
	}
	private void setFeeQuduan(String feeQuduan) {
		this.feeQuduan = feeQuduan;
	}
	public String getBfb() {
		return bfb;
	}
	private void setBfb(String bfb) {
		this.bfb = bfb;
	}
	/**
	 * 
	 * @param dataSet
	 * @param num 分成几段
	 * @param fieldName 按那个名字进行统计
	 */
	public void setDataSet(DataSet dataSet,int num,String fieldName){
		float maxValue=0f;
		float minValue=0f;
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			float linshi_value=Float.valueOf(dataSet.funGetFieldByCol(i, fieldName)).floatValue();
			if(maxValue==0){
				
				maxValue=linshi_value;
				
			}if(minValue==0){
				minValue=linshi_value;	
			}else{
				if(linshi_value>maxValue){
					maxValue=linshi_value;
				}
				if(linshi_value<minValue&&linshi_value>0){
					minValue=linshi_value;
				}
			}
		}
		ArrayList<TongjiFee> fees=new ArrayList<TongjiFee>();
		for(int j=0;j<num;j++){
			TongjiFee fee=new TongjiFee();
			fee.setQuduan(j+1);
			fee.setRenshu(0);
			fees.add(fee);
		}
		System.out.println(fees.size());
		float qujian_size=(maxValue-minValue+1)/num;
		System.out.println("区段大小值为= "+qujian_size);
		for(int i=0;i<row;i++){
			float linshi_value=Float.valueOf(dataSet.funGetFieldByCol(i, fieldName)).floatValue();
			if(linshi_value>0){
				int djg=(int)((linshi_value-minValue)/qujian_size);
				System.out.println(linshi_value+"  第"+djg+"个区段");
				int aa=fees.get(djg).getRenshu();
				fees.get(djg).setRenshu(aa+1);
			}
			
		}
		int maxQujianRenshu=0;
		//int maxQuduan=0;
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		for(int k=0;k<num;k++){
			System.out.print(fees.get(k).getQuduan()+"\t");
			int renshu=fees.get(k).getRenshu();
			System.out.println(fees.get(k).getRenshu());
			if(renshu>maxQujianRenshu){
				maxQujianRenshu=renshu;
				//maxQuduan=k;
				this.setFeeQuduan((minValue+qujian_size*k)+"≤￥＜"+(minValue+qujian_size*(k+1)));
				this.setQuduanrenshu(maxQujianRenshu);
				this.setBfb(fnum.format(maxQujianRenshu*100f/(row))+"");
			}
		}
		
		System.out.println("总人数为="+row);
		System.out.println("最大区间为="+this.getFeeQuduan());
		System.out.println("最大区间人数为="+this.getQuduanrenshu());
		System.out.println("最大区间百分比为="+this.getBfb());
	}
	/**
	 * 
	 * @param dataSet
	 * @param num 分成几段
	 * @param fieldName 按那个名字进行统计
	 */
	public void setDataSet2(DataSetClass dataSet,int num,String fieldName){
		float minValue=(dataSet.FunGetDataAsNumberByColName(0, fieldName)).floatValue();
		float maxValue=(dataSet.FunGetDataAsNumberByColName((dataSet.FunGetRowCount()-1), fieldName)).floatValue();
		int row=dataSet.FunGetRowCount();
//		for(int i=0;i<row;i++){
//			float linshi_value=(dataSet.FunGetDataAsNumberByColName(i, fieldName)).floatValue();
//			if(maxValue==0){
//				
//				maxValue=linshi_value;
//				
//			}if(minValue==0){
//				minValue=linshi_value;	
//			}else{
//				if(linshi_value>maxValue){
//					maxValue=linshi_value;
//				}
//				if(linshi_value<minValue&&linshi_value>0){
//					minValue=linshi_value;
//				}
//			}
//		}
		ArrayList<TongjiFee> fees=new ArrayList<TongjiFee>();
		for(int j=0;j<num;j++){
			TongjiFee fee=new TongjiFee();
			fee.setQuduan(j+1);
			fee.setRenshu(0);
			fees.add(fee);
		}
		//System.out.println(fees.size());
		float qujian_size=(maxValue-minValue+1)/num;
		//System.out.println("区段大小值为= "+qujian_size);
		for(int i=0;i<row;i++){
			float linshi_value=(dataSet.FunGetDataAsNumberByColName(i, fieldName)).floatValue();
			if(linshi_value>0){
				int djg=(int)((linshi_value-minValue)/qujian_size);
				//System.out.println(linshi_value+"  第"+djg+"个区段");
				int aa=fees.get(djg).getRenshu();
				fees.get(djg).setRenshu(aa+1);
			}
			
		}
		int maxQujianRenshu=0;
		int maxQuduan=0;
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		for(int k=0;k<num;k++){
			//System.out.print(fees.get(k).getQuduan()+"\t");
			int renshu=fees.get(k).getRenshu();
			//System.out.println(fees.get(k).getRenshu());
			if(renshu>maxQujianRenshu){
				maxQujianRenshu=renshu;
				maxQuduan=k;
				this.setFeeQuduan((minValue+qujian_size*k)+"≤￥＜"+(minValue+qujian_size*(k+1)));
				this.setQuduanrenshu(maxQujianRenshu);
				this.setBfb(fnum.format(maxQujianRenshu*100f/(row))+"");
			}
		}
		
		System.out.println("总人数为="+row);
		System.out.println("最大区间为="+this.getFeeQuduan());
		System.out.println("最大区间人数为="+this.getQuduanrenshu());
		System.out.println("最大区间百分比为="+this.getBfb());
	}
	
	public static String funGetFeeQujianSql(String maxValueStr,String minValueStr,int naru ,int num,String tablename,String fieldName ,int fieldIndex){
		System.out.println("tablename"+tablename);
		float minValue= Float.valueOf("0").floatValue();
		float maxValue= Float.valueOf(maxValueStr).floatValue();
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		DecimalFormat   fnum1   =   new   DecimalFormat("##0.000");  
		float qujian_size=(maxValue-minValue+1)/num;
		if(minValue==maxValue&&maxValue==0){
			qujian_size=0;
		}
		String sql="select * from (select * from (";
		for(int i=0;i<num;i++){
			String kaishi=fnum.format(minValue+qujian_size*i)+"";
			String jieshu=fnum.format(minValue+qujian_size*(i+1))+"";
			String kaishi1=fnum1.format(minValue+qujian_size*i-0.005)+"";
			String jieshu1=fnum1.format(minValue+qujian_size*(i+1)-0.005)+"";
			//System.out.println(kaishi1+"---------"+jieshu1);
			sql=sql+"select count(*) renshu ,'"+fieldName+"' type,"+fieldIndex+" fieldIndex, "+(i+1)+" qujian,'"+ kaishi+"≤￥＜"+jieshu+"' fanwei,"+naru+" naru from "+tablename+" t where t."+fieldName+">="+ kaishi1+" and t."+fieldName+"<"+ jieshu1+" ";
			if(i!=(num-1)){
				sql=sql+ " union " ;
			}
		}
		return sql+")order by renshu desc,qujian desc )where rownum=1";	
	}
	public static String funGetFeeQujianSql2(String maxValueStr,String minValueStr,int naru ,int num,String tablename,String fieldName ,int fieldIndex){
		System.out.println("tablename"+tablename);
		float minValue= Float.valueOf("0").floatValue();
		float maxValue= Float.valueOf(maxValueStr).floatValue();
		DecimalFormat   fnum   =   new   DecimalFormat("##0.00");  
		DecimalFormat   fnum1   =   new   DecimalFormat("##0.000");  
		float qujian_size=(maxValue-minValue+1)/num;
		if(minValue==maxValue&&maxValue==0){
			qujian_size=0;
		}
		String sql="select * from (";
		for(int i=0;i<num;i++){
			String kaishi=fnum.format(minValue+qujian_size*i)+"";
			String jieshu=fnum.format(minValue+qujian_size*(i+1))+"";
			String kaishi1=fnum1.format(minValue+qujian_size*i-0.005)+"";
			String jieshu1=fnum1.format(minValue+qujian_size*(i+1)-0.005)+"";
			//System.out.println(kaishi1+"---------"+jieshu1);
			sql=sql+"select count(*) renshu ,'"+fieldName+"' type,"+fieldIndex+" fieldIndex, "+(i+1)+" qujian,'"+ kaishi+"≤￥＜"+jieshu+"' fanwei,"+naru+" naru from "+tablename+" t where t."+fieldName+">="+ kaishi1+" and t."+fieldName+"<"+ jieshu1+" ";
			if(i!=(num-1)){
				sql=sql+ " union " ;
			}
		}
		return sql+") order by qujian";	
	}
	public static String funGetFeeSql(String cp_id){
		
		
		return null; 
	}
}



