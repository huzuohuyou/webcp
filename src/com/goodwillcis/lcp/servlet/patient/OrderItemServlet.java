/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：DelVariation.java    
// 文件功能描述：删除未签名的变异信息
//
// 创建人：潘状
// 创建日期：2011-08-01
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.servlet.patient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;

public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final int HOSPITALID = LcpUtil.getHospitalID();
	DatabaseClass db = LcpUtil.getDatabaseClass();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String op = request.getParameter("op");
		if ("getLapItems".equals(op)) {
			getLapItems(request, response);
		}else if("getDefaultOrderItems".equals(op)){
			getDefaultOrderItems(request, response,"getDefaultOrderItems");
		}else if("getDefaultInspectItems".equals(op)){
			getDefaultOrderItems(request, response,"getDefaultInspectItems");
		}else if("getDefaultCheckItems".equals(op)){
			getDefaultOrderItems(request, response,"getDefaultCheckItems");
		}
	}
	
	/**
	 * @author 林建喜
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @editContent 修改药品医嘱不可用处理逻辑，添加替代药品功能
	 */
	protected void getLapItems(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String _needItem = ("");
		String checkType = ("NodeColor(this)");
		String trName = ("tr");
		String table="";

		String patientNo = request.getParameter("patient_no");
		String cpID = request.getParameter("cp_id");
		String cpNodeID = request.getParameter("cp_node_id");
		String cpNodeOrderID = request.getParameter("cp_node_order_id");

		String dosageSql = "select code, unit from lcp_local_order_dosageunits";// 查找药品单位
		DataSetClass ds2 = db.FunGetDataSetBySQL(dosageSql);
		String freSql = "select code,comm from lcp_local_order_frequency";// 查找频次
		DataSetClass ds3 = db.FunGetDataSetBySQL(freSql);
		String waySql = "select supply_code,supply_name from lcp_local_order_way";// 查找途径
		DataSetClass ds4 = db.FunGetDataSetBySQL(waySql);

		String itemSql = "select * from lcp_patient_order_item "
				+ "where sys_is_del=0 and cp_id=" + cpID + " and cp_node_id="
				+ cpNodeID + " and HOSPITAL_ID=" + HOSPITALID
				+ " and PATIENT_NO='" + patientNo + "' and CP_NODE_ORDER_ID ="
				+ cpNodeOrderID + "";
		DataSetClass ds1 = db.FunGetDataSetBySQL(itemSql);
		JSONObject jsonObj = new JSONObject();
		JSONArray rows = new JSONArray();
		if (ds1.FunGetDataAsStringById(0, 0) != "") {
			for (int j = 0; j < ds1.FunGetRowCount(); j++) {
				boolean x = false;
				String cpNodeOrderOrderId = ds1.FunGetDataAsStringByColName(j,
						"CP_NODE_ORDER_ID");
				String cpNodeOrderItemId = ds1.FunGetDataAsStringByColName(j,
						"CP_NODE_ORDER_ITEM_ID");
				String orderText = ds1.FunGetDataAsStringByColName(j,
						"CP_NODE_ORDER_TEXT");

				String need = ds1.FunGetDataAsStringByColName(j, "NEED_ITEM");
				String need1 = need;
				//String auto = ds1.FunGetDataAsStringByColName(j, "AUTO_ITEM");
				// 现在指定的院内医嘱编码
				String orderNo = ds1.FunGetDataAsStringByColName(j, "ORDER_NO");
				String exeState = ds1.FunGetDataAsStringByColName(j,
						"EXE_STATE");

				String type = ds1.FunGetDataAsStringByColName(j,
						"ORDER_TYPE_NAME");
				// if(type.equals("1")){
				// type = "其他";
				// }else if(type.equals("2")){
				// type = "检查";
				// }else if(type.equals("0")){
				// type = "检验";
				// }
				String specification = ds1.FunGetDataAsStringByColName(j,
						"SPECIFICATION");
				String orderKind = ds1.FunGetDataAsStringByColName(j,
						"ORDER_KIND");

				String stateItem = ds1.FunGetDataAsStringByColName(j,
						"STATE_ITEM");
				String measure = ds1.FunGetDataAsStringByColName(j, "MEASURE");
				String measureUnits = "";
				String measureUnit_name = "";
				String measure1 = "0";
				String dosage1 = "0";
				if (measure != "") {
					measureUnits = ds1.FunGetDataAsStringByColName(j,
							"MEASURE_UNITS");
					measureUnit_name = CommonUtil.FunGetDataByValue(ds2,
							"CODE", measureUnits, "UNIT");
					measure1 = measure;
				}

				String frequency = ds1.FunGetDataAsStringByColName(j,
						"FREQUENCY");
				if (orderKind.equals("0") && frequency == "") {
					x = true;
					frequency = "ONCE";
				}

				String way = ds1.FunGetDataAsStringByColName(j, "WAY");

				String dosage = ds1.FunGetDataAsStringByColName(j, "DOSAGE");
				String dosageUnits = "";
				String dosageUnits_name = "";
				if (dosage != "") {
					dosageUnits = ds1.FunGetDataAsStringByColName(j,
							"DOSAGE_UNITS");
					dosageUnits_name = CommonUtil.FunGetDataByValue(ds2,
							"CODE", dosageUnits, "UNIT");
					dosage1 = dosage;
				}

				//String administration = ds1.FunGetDataAsStringByColName(j,"ADMINISTRATION");

				String way_name = CommonUtil.FunGetDataByValue(ds4,
						"SUPPLY_CODE", way, "SUPPLY_NAME");

				String frequency_name = null;
				if (x == true) {
					frequency_name = "";
				} else {
					frequency_name = CommonUtil.FunGetDataByValue(ds3, "CODE",
							frequency, "COMM");
				}

				String mark = ds1.FunGetDataAsStringByColName(j, "MARK");
				String orderItemSetNo = ds1.FunGetDataAsStringByColName(j,
						"ORDER_ITEM_SET_ID");
				// 如果组号跟子项的医嘱号一致 则为这一组的开端

				// 分组标志
				String setFlag = "&nbsp;";
				if (orderItemSetNo.trim().equals(cpNodeOrderItemId.trim())) {
					setFlag = "◇";
					checkType = ("selectSet(this)");
				} else {
					if (orderItemSetNo != null && !"".equals(orderItemSetNo)) {
						setFlag = "├";
						checkType = ("");
					} else {
						checkType = ("selectSet1(this)");
					}
				}

				// 加入新的项，该医嘱是否可用。
				String orderSql = "";
				String updateSql = "";
				String dictItemSql = "select t.effect_flag from dcp_dict_order_item t where t.order_item_name='"
						+ orderText
						+ "' and t.order_item_code='"
						+ orderNo
						+ "'";
				if (!specification.equals("")){
					dictItemSql = dictItemSql + " and t.specification='"+specification+"'";
				}
				
				String effectFlag = db.FunGetDataSetBySQL(dictItemSql)
						.FunGetDataAsStringById(0, 0);
				String visFlag = "#FFFFFF";
				if (effectFlag.equals("")) {//路径执行医嘱细项编码同医嘱字典编码不一致
					// 查找名字和规格正确但Code不正确
					if (!specification.equals("")) {//规格不为空，属于药品医嘱
						orderSql = "select t.order_item_code,t.drug_id from dcp_dict_order_item t where t.order_item_name='"
								+ orderText
								+ "' and t.specification ='"
								+ specification + "'";

					} else {
						orderSql = "select t.order_item_code from dcp_dict_order_item t where t.order_item_name='"
								+ orderText + "' and t.specification is null";
					}
					String dictOrderCode = db.FunGetDataSetBySQL(orderSql)
							.FunGetDataAsStringByColName(0, "ORDER_ITEM_CODE");
					if (!dictOrderCode.equals("")
							&& !(orderNo.trim()).equals(dictOrderCode.trim())) {//路径制定和路径执行医嘱细项格式和医嘱字典保持一致
						if (!specification.equals("")) {
							String drug_id1 = db.FunGetDataSetBySQL(orderSql)
							.FunGetDataAsStringByColName(0, "DRUG_ID");
							updateSql = "update lcp_node_order_item set order_no='"
									+ dictOrderCode
									+ "',effect_flag=0,drug_id='"+drug_id1+"' where cp_id="
									+ cpID
									+ " and hospital_id="
									+ HOSPITALID
									+ " and cp_node_order_text='" 
									+ orderText
									+ "' and specification='"
									+ specification
									+ "' \n"
									+ "update lcp_patient_order_item set order_no='"
									+ dictOrderCode
									+ "',effect_flag=0,drug_id='"+drug_id1+"' where cp_id="
									+ cpID
									+ " and hospital_id="
									+ HOSPITALID
									+ " and cp_node_order_text='"
									+ orderText
									+ "' and specification='"
									+ specification
									+ "' and patient_no='" + patientNo + "'";
						} else {
							updateSql = "update lcp_node_order_item set order_no='"
									+ dictOrderCode
									+ "',effect_flag=0 where cp_id="
									+ cpID
									+ " and hospital_id="
									+ HOSPITALID
									+ " and cp_node_order_text='"
									+ orderText
									+ "' and specification is null \n"
									+ "update lcp_patient_order_item set order_no='"
									+ dictOrderCode
									+ "',effect_flag=0 where cp_id="
									+ cpID
									+ " and hospital_id="
									+ HOSPITALID
									+ " and cp_node_order_text='"
									+ orderText
									+ "' and specification is null and patient_no='"
									+ patientNo + "'";

						}
						int res = db.FunRunSqlByFile(updateSql.getBytes());
						if (res > 0){
							visFlag = "#FFFFFF";
//							System.out.println("修改成功");
						// System.out.println("res====:"+res);
					}else {
						visFlag = "#AAAAAA";
						checkType = "";
//						System.out.println("不成功");
						}
					
				  }else if(dictOrderCode.equals("")){
					  visFlag = "#AAAAAA";
					  checkType = "";
				  }
			} 
				String _exeState=("1".equals(exeState)?"<img src='../public/images/detail_s3.png' width='18' height='18'/>":"");
				String executeFlag=("1".equals(exeState)?"success":"");
				if ("1".equals(need) && _needItem == "") {
					need = "<img src='../public/images/detail_s5.png' width='18' height='18'/>";
				} else {
					need = "";
				}
				String orderTypeItem = "";
				if ("0".equals(orderKind)) {
					orderTypeItem = "临时";
				} else if ("1".equals(orderKind)) {
					orderTypeItem = "长期";
				} else if ("2".equals(orderKind)) {
					orderTypeItem = "出院";
				} else if ("3".equals(orderKind)) {
					orderTypeItem = "长期+临时";
				}
				String s=""+"";
				String check = "";
				if("1".equals(stateItem)){
					check="<img src='../public/images/success.png' width='18' height='18'/>";
				}
				if(visFlag.equals("#AAAAAA") && !specification.equals("")){
					String drug_id = ds1.FunGetDataAsStringByColName(j, "DRUG_ID");
					String drugSql = "select order_item_code,order_item_name,specification from dcp_dict_order_item where drug_id='"+drug_id+"'";
					DataSetClass dsDrug = db.FunGetDataSetBySQL(drugSql);
					int drugNum = dsDrug.FunGetRowCount();
					if(drugNum > 0 ){
						table+="<tr align='center' bgcolor='"+visFlag+"' onClick='"+checkType+"' exestate1='"+executeFlag+"' selectId='"+orderNo+"' fristid='"+cpNodeOrderID+"' id='"+trName+"_"+cpNodeOrderItemId+"' name='"+cpNodeOrderID+"_"+cpNodeOrderItemId+"' replaceFlag='0' style='cursor:pointer;'>"+
						 "<td id='"+cpNodeOrderItemId+"' name='"+cpNodeOrderOrderId+"'>"+setFlag+"</td>"+
						 "<td>"+_exeState+"</td>"+
						 "<td class='STYLE10'>"+orderTypeItem+"</td>"+
						 "<td class='STYLE10'>"+type+"</td>"+
						 "<td align='center' class='STYLE10' >"+orderText+"</td>"+
						 "<td class='STYLE10' orderNo='"+orderNo+"'>"+specification+"</td>"+
						 "<td class='STYLE10' need='"+need1+"' exestate1='"+executeFlag+"'>"+need+"</td>"+
						 "<td class='STYLE10' stateItem="+stateItem+">"+check+"</td>";
						 if("0".equals(orderItemSetNo) || "".equals(orderItemSetNo)){  
						 table+="<td class='STYLE10' orderItemSetNo='"+orderItemSetNo+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
						 }else{                                                   
						 table+="<td class='STYLE10' orderItemSetNo='"+(cpNodeOrderID+orderItemSetNo)+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
						 }                                                     
						 if("3".equals(orderKind)){                               
						 table+="<td class='STYLE10' frequency1='"+frequency+"' frequency2=''> "+frequency_name+"</td>"+
						 "<td class='STYLE10' way='"+way+"' advicetype1='0' advicetype2='1'>"+way_name+"</td>";
						 }else{
						 table+="<td class='STYLE10' frequency='"+frequency+"'>"+frequency_name+"</td>"+
						 "<td class='STYLE10' way='"+way+"' advicetype='"+orderKind+"'>"+way_name+"</td>";
						 }
						 table+="<td class='STYLE10' unitId='"+cpNodeOrderID+cpNodeOrderItemId+"' dosage='"+dosage1+"'>"+dosage1+""+dosageUnits_name+"</td>"+
						 "<td class='STYLE10' dosageUnits='"+dosageUnits+"'>"+mark+"</td>"+
						 "<td class='STYLE10'><input type='button' id='"+cpNodeOrderItemId+"' name='"+cpNodeOrderOrderId+"' value='替代药品' onclick='lapReplace(this);'/></td>"+
						 "</tr>";
						
						 String orderItemSetId = cpNodeOrderID + orderItemSetNo;
						 if(setFlag.equals("├")){
								setFlag = "&nbsp;";
								checkType = ("selectSet1(this)");
							}else if(setFlag.equals("◇")){
								checkType = ("selectSet(this)");
							}else{
								checkType = ("selectSet1(this)");
							}
						for (int k = 0; k < drugNum; k++) {
							
							String order_item_code = dsDrug.FunGetDataAsStringByColName(k, "ORDER_ITEM_CODE");
							String order_item_name = dsDrug.FunGetDataAsStringByColName(k, "ORDER_ITEM_NAME");
							String specification1 = dsDrug.FunGetDataAsStringByColName(k, "SPECIFICATION");
							
							table+="<tr align='center' bgcolor='#FFFF00' onClick='"+checkType+"' exestate1='"+executeFlag+"' selectId='"+order_item_code+"' fristid='"+cpNodeOrderID+"' " +
									"id='"+trName+"_"+cpNodeOrderItemId+"' name='"+cpNodeOrderID+"_"+cpNodeOrderItemId+"' replaceFlag='1' style='cursor:pointer;display:none'>"+
							 "<td id='"+cpNodeOrderItemId+"' name='"+cpNodeOrderOrderId+"'>"+setFlag+"</td>"+
							 "<td>"+_exeState+"</td>"+
							 "<td class='STYLE10'>"+orderTypeItem+"</td>"+
							 "<td class='STYLE10'>"+type+"</td>"+
							 "<td align='center' class='STYLE10' >"+order_item_name+"</td>"+
							 "<td class='STYLE10' orderNo='"+order_item_code+"'>"+specification1+"</td>"+
							 "<td class='STYLE10' need ='"+need1+"' exestate1='"+executeFlag+"'>"+need+"</td>"+
							 "<td class='STYLE10' stateItem="+stateItem+">"+check+"</td>";
							 if("0".equals(orderItemSetNo) || "".equals(orderItemSetNo)){  
							 table+="<td class='STYLE10' orderItemSetNo='"+orderItemSetNo+"' measureUnits = '' measure=''></td>";
							 }else{                                                   
							 table+="<td class='STYLE10' orderItemSetNo='"+orderItemSetId+"' measureUnits = '' measure=''></td>";
							 }                                                     
							 if("3".equals(orderKind)){                               
							 table+="<td class='STYLE10' frequency1='' frequency2=''> </td>"+
							 "<td class='STYLE10' way='' advicetype1='0' advicetype2='1'></td>";
							 }else{
							 table+="<td class='STYLE10' frequency=''></td>"+
							 "<td class='STYLE10' way='' advicetype='"+orderKind+"'></td>";
							 }
							 table+="<td class='STYLE10' unitId='"+cpNodeOrderID+cpNodeOrderItemId+"' dosage=''></td>"+
							 "<td class='STYLE10' dosageUnits=''></td>"+
							 "<td class='STYLE10' dosageUnits=''></td>"+
							 "</tr>";
							 
							 
						}
					}else{
						 if(setFlag.equals("◇")){ 
							checkType = ("selectSet(this)");
						   }
						table+="<tr align='center' bgcolor='"+visFlag+"' onClick='"+checkType+"' exestate1='"+executeFlag+"' selectId='"+orderNo+"' fristid='"+cpNodeOrderID+"' id='"+trName+"_"+cpNodeOrderItemId+"' name='"+cpNodeOrderID+"_"+cpNodeOrderItemId+"' replaceFlag='0' style='cursor:pointer'>"+
						 "<td id='"+cpNodeOrderItemId+"' name='"+cpNodeOrderOrderId+"'>"+setFlag+"</td>"+
						 "<td>"+_exeState+"</td>"+
						 "<td class='STYLE10'>"+orderTypeItem+"</td>"+
						 "<td class='STYLE10'>"+type+"</td>"+
						 "<td align='center' class='STYLE10' >"+orderText+"</td>"+
						 "<td class='STYLE10' orderNo='"+orderNo+"'>"+specification+"</td>"+
						 "<td class='STYLE10' need='"+need1+"' exestate1='"+executeFlag+"'>"+need+"</td>"+
						 "<td class='STYLE10' stateItem="+stateItem+">"+check+"</td>";
						 if("0".equals(orderItemSetNo) || "".equals(orderItemSetNo)){  
						 table+="<td class='STYLE10' orderItemSetNo='"+orderItemSetNo+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
						 }else{                                                   
						 table+="<td class='STYLE10' orderItemSetNo='"+(cpNodeOrderID+orderItemSetNo)+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
						 }                                                     
						 if("3".equals(orderKind)){                               
						 table+="<td class='STYLE10' frequency1='"+frequency+"' frequency2=''> "+frequency_name+"</td>"+
						 "<td class='STYLE10' way='"+way+"' advicetype1='0' advicetype2='1'>"+way_name+"</td>";
						 }else{
						 table+="<td class='STYLE10' frequency='"+frequency+"'>"+frequency_name+"</td>"+
						 "<td class='STYLE10' way='"+way+"' advicetype='"+orderKind+"'>"+way_name+"</td>";
						 }
						 table+="<td class='STYLE10' unitId='"+cpNodeOrderID+cpNodeOrderItemId+"' dosage='"+dosage1+"'>"+dosage1+""+dosageUnits_name+"</td>"+
						 "<td class='STYLE10' dosageUnits='"+dosageUnits+"'>"+mark+"</td>"+
						 "<td class='STYLE10'><font size=2 color='red'>无替代药品</font></td>"+
						 "</tr>";
					}
				}else{
					  table+="<tr align='center' bgcolor='"+visFlag+"' onClick='"+checkType+"' exestate1='"+executeFlag+"' selectId='"+orderNo+"' fristid='"+cpNodeOrderID+"' id='"+trName+"_"+cpNodeOrderItemId+"' name='"+cpNodeOrderID+"_"+cpNodeOrderItemId+"' replaceFlag='0' style='cursor:pointer'>"+
							 "<td id='"+cpNodeOrderItemId+"' name='"+cpNodeOrderOrderId+"'>"+setFlag+"</td>"+
							 "<td>"+_exeState+"</td>"+
							 "<td class='STYLE10'>"+orderTypeItem+"</td>"+
							 "<td class='STYLE10'>"+type+"</td>"+
							 "<td align='center' class='STYLE10' >"+orderText+"</td>"+
							 "<td class='STYLE10' orderNo='"+orderNo+"'>"+specification+"</td>"+
							 "<td class='STYLE10' need='"+need1+"' exestate1='"+executeFlag+"'>"+need+"</td>"+
							 "<td class='STYLE10' stateItem="+stateItem+">"+check+"</td>";
							 if("0".equals(orderItemSetNo) || "".equals(orderItemSetNo)){  
							 table+="<td class='STYLE10' orderItemSetNo='"+orderItemSetNo+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
							 }else{                                                   
							 table+="<td class='STYLE10' orderItemSetNo='"+(cpNodeOrderID+orderItemSetNo)+"' measureUnits = '"+measureUnits+"' measure='"+measure1+"'>"+measure+""+measureUnit_name+"</td>";
							 }                                                     
							 if("3".equals(orderKind)){                               
							 table+="<td class='STYLE10' frequency1='"+frequency+"' frequency2=''> "+frequency_name+"</td>"+
							 "<td class='STYLE10' way='"+way+"' advicetype1='0' advicetype2='1'>"+way_name+"</td>";
							 }else{
							 table+="<td class='STYLE10' frequency='"+frequency+"'>"+frequency_name+"</td>"+
							 "<td class='STYLE10' way='"+way+"' advicetype='"+orderKind+"'>"+way_name+"</td>";
							 }
							 table+="<td class='STYLE10' unitId='"+cpNodeOrderID+cpNodeOrderItemId+"' dosage='"+dosage1+"'>"+dosage1+""+dosageUnits_name+"</td>"+
							 "<td class='STYLE10' dosageUnits='"+dosageUnits+"'>"+mark+"</td>"+
							 "<td class='STYLE10' dosageUnits='"+dosageUnits+"'></td>"+
							 "</tr>";
				}
			}
//			System.out.println("table==" + table);
			response.getWriter().println("[{\"result\":\""+table+"\"}]");
		}		
	}
	
    /**
     * @author 林建喜
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * function 获取默认医嘱项目
     */
	protected void getDefaultOrderItems(HttpServletRequest request,
			HttpServletResponse response,String orderItems) throws ServletException, IOException {
		
		String patientNo = request.getParameter("patient_no");
		String cpID = request.getParameter("cp_id");
		String cpNodeID = request.getParameter("cp_node_id");
		
		String defaultOrderSql = "select cp_node_order_id,cp_node_order_item_id from lcp_patient_order_item where patient_no='"+patientNo+"' " +
		"and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and default_item=1 and (exe_state<>1 or exe_state is null) and order_type_name like ";
		if(orderItems.equals("getDefaultOrderItems")){
				defaultOrderSql = defaultOrderSql + "'%其他%'";
			}else if(orderItems.equals("getDefaultInspectItems")){
				defaultOrderSql = defaultOrderSql + "'%检验%'";
			}else if(orderItems.equals("getDefaultCheckItems")){
				defaultOrderSql = defaultOrderSql + "'%检查%'";
			}

		DataSetClass defaultOrderSet = db.FunGetDataSetBySQL(defaultOrderSql);
		int rows = defaultOrderSet.FunGetRowCount();
		if(rows > 0){
			String orderIds = "";
			for (int i = 0; i < rows; i++) {
				String cpNodeOrderId = defaultOrderSet.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_ID");
				String cpNodeOrderItemId = defaultOrderSet.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_ITEM_ID");
				String continueOrderIdSql = "select t.continue_order_id from lcp_patient_order_point t where t.patient_no='"+patientNo+"' " +
						"and t.cp_id="+cpID+" and t.cp_node_id="+cpNodeID+" and t.cp_node_order_id="+cpNodeOrderId+"";
				DataSetClass continueOrderIdSet = db.FunGetDataSetBySQL(continueOrderIdSql);
				String continueOrderId = continueOrderIdSet.FunGetDataAsStringByColName(0, "CONTINUE_ORDER_ID");
				orderIds += continueOrderId+","+cpNodeOrderId+","+cpNodeOrderItemId+"/";
				}
			response.getWriter().println("[{\"result\":\""+orderIds+"\",\"rows\":\""+rows+"\"}]");
		}else if(rows == 0){
			String msg ="";
			if(orderItems.equals("getDefaultOrderItems")){
				msg = "没有默认医嘱项目"; 
			}else if(orderItems.equals("getDefaultInspectItems")){
				msg = "没有默认检验项目";
			}else if(orderItems.equals("getDefaultCheckItems")){
				msg = "没有默认检查项目";
			}
			response.getWriter().println("[{\"rows\":\""+rows+"\",\"msg\":\""+msg+"\"}]");
		}
	}
	
}
