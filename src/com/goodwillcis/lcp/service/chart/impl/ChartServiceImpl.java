/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：ChartServiceImpl .java
// 文件功能描述：fusionCharts生成院内路径图表方法接口的实现
// 创建人：段英华 
// 创建日期：2011/07/29
// 
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.service.chart.impl;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.service.chart.ChartService;

public class ChartServiceImpl implements ChartService {

	@Override
	public String getCpDataXML(String dpId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
		DataSetClass dsc1 = dbc.FunGetDataSetBySQL("select cp_name from lcp_master where cp_id = "+ dpId);

		DataSetClass dsc2 = dbc.FunGetDataSetBySQL("select cp_node_id, cp_node_name, cp_node_type from lcp_master_node t where t.cp_id = "+ dpId+" order by cp_node_type,cp_node_id");
		

		DataSetClass dsc3 = dbc.FunGetDataSetBySQL("select cp_node_id,cp_next_node_id from lcp_node_relate  where cp_id = "+ dpId);

		//开始构建xml
		StringBuffer dataXml = new StringBuffer();
//      定义bom类型的xml文件  		
		byte[] utf8Bom = new byte[]{(byte) 0xef, (byte) 0xbb, (byte) 0xbf};    
		String utf8BomStr="";    
		try {    
		utf8BomStr = new String(utf8Bom,"UTF-8");//定义BOM标记    
		} catch (UnsupportedEncodingException e) {    
		   e.printStackTrace();    
		}  
//		定义xml文件头
		dataXml.append("<chart baseFontSize='18' xAxisMinValue='0' xAxisMaxValue='600' yAxisMinValue='0' yAxisMaxValue='500' is3D='1' showAboutMenuItem='0' showFormBtn='0' viewMode='1' bgcolor='FFFFFF' canvasbordercolor='FFFFFF'>");
		dataXml.append("<styles><definition><style name='MyFirstFontStyle' type='font' font='宋体' size='12' /></definition><application><apply toObject='DATALABELS' styles='MyFirstFontStyle' /></application></styles>");
		//定义变异退出节点id
		String varId = "";
		//定义节点名字 类型
		int xValue = 0;
		int yValue = 350;
		if(dsc2.FunGetRowCount()>0){
			dataXml.append("<dataset seriesName=\"DS1\">");
			for(int i=0; i<dsc2.FunGetRowCount();i++){
				//确定x轴坐标
				String cpNodeId = dsc2.FunGetDataAsStringByColName(i, "CP_NODE_ID");
				
				if(i==6){
					yValue = 200;
					xValue = 920;
				}else if(i==7){
					yValue = 200;
					xValue = 780;
				}else if(i==8){
					yValue = 200;
					xValue = 640;
				}else if(i==9){
					yValue = 200;
					xValue = 500;
				}else if(i==10){
					yValue = 200;
					xValue = 360;
				}else if(i==11){
					yValue = 200;
					xValue = 220;
				}else{
					xValue+=140;
				}
//				int xValue = 0;
//				int yValue = 350;
//				if(cpNodeId.equals("2")){
//					xValue = 220;
//				}else if(cpNodeId.equals("3")){
//					xValue = 360;
//				}else if(cpNodeId.equals("4")){
//					xValue = 500;
//				}else if(cpNodeId.equals("5")){
//					xValue = 640;
//				}else if(cpNodeId.equals("6")){
//					xValue = 780;
//				}else if(cpNodeId.equals("7")){
//					xValue = 920;
//				}else if(cpNodeId.equals("8")){
//					yValue = 200;
//					xValue = 920;
//				}else if(cpNodeId.equals("9")){
//					yValue = 200;
//					xValue = 780;
//				}else if(cpNodeId.equals("10")){
//					yValue = 200;
//					xValue = 640;
//				}else if(cpNodeId.equals("11")){
//					yValue = 200;
//					xValue = 500;
//				}else if(cpNodeId.equals("12")){
//					yValue = 200;
//					xValue = 360;
//				}else if(cpNodeId.equals("13")){
//					yValue = 200;
//					xValue = 220;
//				}
				
				switch(dsc2.FunGetDataAsNumberByColName(i, "CP_NODE_TYPE").intValue()){
				//准入
				case 0:
					dataXml.append("<set x=\"50\" y=\"350\" width=\"70\" link=\"as\" height=\"56\" name=\"");
					dataXml.append(dsc2.FunGetDataAsStringByColName(i, "CP_NODE_NAME"));
					dataXml.append("\" color=\"35AE00\" id=\"");
					dataXml.append(cpNodeId + "\" />");					
				break;
				
				//完成节点
				case 2:
					dataXml.append("<set x=\"520\" y=\"80\" width=\"70\" height=\"56\" name=\"");
					dataXml.append(dsc2.FunGetDataAsStringByColName(i, "CP_NODE_NAME"));
					dataXml.append("\" color=\"35AE00\" id=\"");
					dataXml.append(cpNodeId + "\" />");
					
				break;
				
				//变异节点
				case 3:
					dataXml.append("<set x=\"120\" y=\"80\"  radius=\"39\" shape=\"circle\" name=\" ");
					dataXml.append(dsc2.FunGetDataAsStringByColName(i, "CP_NODE_NAME"));
					dataXml.append("\" color=\"FE3233\" id=\"");
					dataXml.append(cpNodeId + "\" />");
					
				break;
				
				//退出节点
				case 4:
					dataXml.append("<set x=\"320\" y=\"80\" width=\"70\" height=\"56\" name=\"");
					dataXml.append(dsc2.FunGetDataAsStringByColName(i, "CP_NODE_NAME"));
					dataXml.append("\" color=\"FE3233\" id=\"");
					dataXml.append(cpNodeId + "\" />");
					varId = cpNodeId;
				break;
				//正常节点
				default:
					dataXml.append(" <set x=\""+ xValue +"\" y=\""+yValue+"\" radius=\"39\" shape=\"circle\" name=\" ");
					dataXml.append(dsc2.FunGetDataAsStringByColName(i, "CP_NODE_NAME"));
					dataXml.append("\" color=\"62D0FE\" id=\"");
					dataXml.append(cpNodeId + "\" />");
				
				break;
				}
			}
			dataXml.append("</dataset>");
		}
		//定义节点之间关系
		if(dsc3.FunGetRowCount()>0){
			dataXml.append("<connectors color=\"FF0000\" stdThickness=\"5\">");
			for(int j=0; j<dsc3.FunGetRowCount();j++){
				//定义起始节点跟结束节点
				String startNo = dsc3.FunGetDataAsStringByColName(j,"CP_NODE_ID");
				String endNo = dsc3.FunGetDataAsStringByColName(j,"CP_NEXT_NODE_ID");
				//正常指向颜色
				String color1 = "BBBB00";
				//变异退出颜色
				String color2 = "FF5904";
				if(endNo.equals(varId)){
					dataXml.append("<connector strength=\"0.6\" label=\"\" from=\""+startNo+"\" to=\""+endNo+"\" color=\""+ color2 +"\" arrowAtStart=\"0\" arrowAtEnd=\"1\" />");
				}else{
					dataXml.append("<connector strength=\"0.6\" label=\"\" from=\""+startNo+"\" to=\""+endNo+"\" color=\""+ color1 +"\" arrowAtStart=\"0\" arrowAtEnd=\"1\" />");
				}
				
			}
			dataXml.append("</connectors>");
		}
		
		//定义路径名
		if(dsc1.FunGetRowCount()>0){
			dataXml.append("<labels>");
			dataXml.append("<label text=\""+ dsc1.FunGetDataAsStringByColName(0, "CP_NAME") +"\" x=\"300\" y=\"480\" color=\"EFEFEF\" borderColor=\"0F6A00\" fontsize=\"16\" bgColor=\"35AE00\" />");
			dataXml.append("</labels>");
		}
		dataXml.append("</chart>");
		
		String dataXml1 = utf8BomStr+"<?xml version='1.0' encoding='UTF-8'?>" + dataXml;
		
		return dataXml1.toString();
	}
	

}
