/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：NodeNurse.java
//文件功能描述：在页面显示护理第一层跟第二层信息
//创建人：段英华 
//修改人：周伟彬
//创建日期：2011/08/10
//修改日期:2013/08/06
 * 修改内容:计量或频次如果为0则不显示
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model.cpmanage;

import java.io.UnsupportedEncodingException;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.util.LcpUtil;

public class NodeOrder {
	private final int HOSPITALID=LcpUtil.getHospitalID();
	public String funGetPointTable(String cp_id,String cp_node_id,String orderID)
	{
		//String sql="SELECT * FROM LCP_NODE_ORDER_POINT T WHERE  CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+" AND HOSPITAL_ID="+HOSPITALID +" order by CP_NODE_ORDER_ID";
		String sql = "SELECT T.*  FROM LCP_NODE_ORDER_POINT T"
				+ " WHERE t.cp_id=" + cp_id + " AND CP_NODE_ID="
				+ cp_node_id+" AND HOSPITAL_ID="+HOSPITALID 
				+ " ORDER BY CP_NODE_ID,  CONTINUE_ORDER_ID,CP_NODE_ORDER_ID";
		System.out.println(sql);
		/*ORDER_KIND	VARCHAR2(20)	Y			医嘱类型（0临时医嘱、1长期医嘱、2出院医嘱、3长期+临时）*/
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_order_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID");
			String cp_node_order_text=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT");
			String need_item=dataSet.funGetFieldByCol(i, "NEED_ITEM");
			String order_kind=dataSet.funGetFieldByCol(i, "ORDER_KIND");
			String c_id=dataSet.funGetFieldByCol(i, "CONTINUE_ORDER_ID");//所属的上级医嘱的ID(第二级)
			if("0".equals(order_kind)){
				order_kind="临时";
			}else if("1".equals(order_kind)){
				order_kind="长期";
			}else if("2".equals(order_kind)){
				order_kind="出院";
			}else if("3".equals(order_kind)){
				order_kind="长期+临时";
			}
			need_item=(need_item.equals("0"))?"":"&radic;";
			String display="none";
			String color="#ffffff";
			
			if(orderID!=null&&orderID.equals(c_id)){
				 display="";
				 color="#51b2f6";
			}
			
			String selectSql = "select cp_node_order_id from lcp_node_order_item where hospital_id="+HOSPITALID+" and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and cp_node_order_id="+cp_node_order_id+" and effect_flag=1";
			//System.out.println("selectSql===:" + selectSql);
			DatabaseClass db = LcpUtil.getDatabaseClass();
			DataSetClass dc = db.FunGetDataSetBySQL(selectSql);
			if(dc.FunGetRowCount() > 0){
				if(c_id.equals(cp_node_order_id)){
					table=table+"<tr style='cursor:pointer' height='20' name='trpan'  class='STYLE10' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"' bgcolor='"+color+"' onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
					"onclick='showpan("+c_id+","+cp_node_id+","+cp_node_order_id+",this);'>"+
					"<td align='center' ><span onclick='showpan("+c_id+","+cp_node_id+","+cp_node_order_id+",this);'><span style='color: #FF0000'>＋</span></span></td>"+
					"<td align='center' >"+order_kind+"</td>"+
					"<td align='left' ><span  id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+
					"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</span></td>"+
					"<td align='center' >"+need_item+"</td>"+
					"</tr>";
				}else if(!c_id.equals(cp_node_order_id)){
						table=table+"<tr style='cursor:pointer;display:"+display+"' height='20'  name='pan"+c_id+"' class='STYLE10' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+
						"' bgcolor='#DFE6E6' onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
						"  onclick='showorderitem("+cp_id+","+cp_node_id+","+cp_node_order_id+",this);'>"+
						"<td colspan=2 align='right' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: #FF0000'>├</span>&nbsp;&nbsp;&nbsp;"+order_kind+"&nbsp;&nbsp;&nbsp;</td>"+
						"<td align='left' ><span  id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"'  name='myspan'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</span></td>"+
						"<td align='center' >"+need_item+"</td>"+
						"</tr>";
					}
			}else{
				if(c_id.equals(cp_node_order_id)){
					table=table+"<tr style='cursor:pointer' height='20' name='trpan'  class='STYLE10' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"' bgcolor='"+color+"' onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
					"onclick='showpan("+c_id+","+cp_node_id+","+cp_node_order_id+",this);'>"+
					"<td align='center' ><span onclick='showpan("+c_id+","+cp_node_id+","+cp_node_order_id+",this);'>＋</span></td>"+
					"<td align='center' >"+order_kind+"</td>"+
					"<td align='left' ><span  id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+
					"'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</span></td>"+
					"<td align='center' >"+need_item+"</td>"+
					"</tr>";
				}else if(!c_id.equals(cp_node_order_id)){
						table=table+"<tr style='cursor:pointer;display:"+display+"' height='20'  name='pan"+c_id+"' class='STYLE10' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+
						"' bgcolor='#DFE6E6' onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' "+
						"  onclick='showorderitem("+cp_id+","+cp_node_id+","+cp_node_order_id+",this);'>"+
						"<td colspan=2 align='right' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├&nbsp;&nbsp;&nbsp;"+order_kind+"&nbsp;&nbsp;&nbsp;</td>"+
						"<td align='left' ><span  id='2_1_"+cp_id+"_"+cp_node_id+"_"+cp_node_order_id+"'  name='myspan'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</span></td>"+
						"<td align='center' >"+need_item+"</td>"+
						"</tr>";
					}
			}
			}
		//System.out.println(table+".............");
		return table;
	}
	
	public String funGetItemTable(String cp_id,String cp_node_id,String cp_node_nurse_id)
	{
		String sql = "SELECT t.*,l.unit FROM LCP_NODE_ORDER_ITEM T, lcp_local_order_dosageunits l WHERE  t.measure_units=l.code(+) and CP_ID="
				+ cp_id + " AND CP_NODE_ID=" + cp_node_id
				+ " AND CP_NODE_ORDER_ID=" + cp_node_nurse_id
				+ " AND HOSPITAL_ID=" + HOSPITALID
				+ " order by CP_NODE_ORDER_ITEM_ID";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		DatabaseClass db = LcpUtil.getDatabaseClass();
		
		String table="";
		int row=dataSet.getRowNum();
		
		for(int i=0;i<row;i++){
			String cp_node_order_item_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ITEM_ID");
			String cp_node_order_text=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT");
			String order_no=dataSet.funGetFieldByCol(i, "ORDER_NO");
			String need_item=dataSet.funGetFieldByCol(i, "NEED_ITEM");
			String default_item=dataSet.funGetFieldByCol(i, "DEFAULT_ITEM");
			String order_kind=dataSet.funGetFieldByCol(i, "ORDER_KIND");//医嘱类型（0临时医嘱、1长期医嘱、2出院医嘱）
			//String order_type=dataSet.funGetFieldByCol(i, "ORDER_TYPE");//检查--检验
			String ORDER_TYPE_NAME=dataSet.funGetFieldByCol(i, "ORDER_TYPE_NAME");//检查--检验
			String MEASURE=dataSet.funGetFieldByCol(i, "MEASURE");//药品一次使用剂量
			String MEASURE_UNITS=dataSet.funGetFieldByCol(i, "UNIT");//剂量单位：规范描述，本系统定义，见4.20剂量单位字典
	//		String DURATION=dataSet.funGetFieldByCol(i, "DURATION");//持续时间：一次执行的持续时间
	//		String DURATION_UNITS=dataSet.funGetFieldByCol(i, "DURATION_UNITS");//持续时间单位：使用规范描述，本系统定义，见4.31时间单位字典
			String WAY=dataSet.funGetFieldByCol(i, "WAY");//给药途径和方法：规范描述，是判断生成何种治疗单的依据，本系统定义，见4.17给药途径字典
			String FREQUENCY=dataSet.funGetFieldByCol(i, "FREQUENCY");//	执行频率描述：使用固定或固定格式的描述，如：3/日、TID，每xx分xx次，用户定义 ，见4.21医嘱执行频率字
			String groupID=dataSet.funGetFieldByCol(i, "ORDER_ITEM_SET_ID");//分组ID
			String specification=dataSet.funGetFieldByCol(i, "SPECIFICATION");//规格
			String SUPPLYNAME="";
			if(WAY != ""){
				SUPPLYNAME=db.FunGetDataSetBySQL("select supply_name from lcp_local_order_way where supply_code="+WAY+"").FunGetDataAsStringById(0, 0);
			}
				
			if("0".equals(order_kind)){
				order_kind="临时";
			}else if("1".equals(order_kind)){
				order_kind="长期";
			}else if("2".equals(order_kind)){
				order_kind="出院";
			}else if("3".equals(order_kind)){
				order_kind="长期+临时";
			}
			
			if("0".equals(groupID)){
				groupID="无";
			}
			String jiliang=MEASURE+MEASURE_UNITS;
		//	System.out.println("jiliang--------"+jiliang);
			if(MEASURE==null||MEASURE.equals("0")||MEASURE==""){
			    jiliang="";
			}
				
		//	String chiXuShiJian=DURATION+DURATION_UNITS;
		//	System.out.println("chiXuShiJian--------"+chiXuShiJian);
//			if(DURATION==null||DURATION.equals("0")||DURATION==""){
//			    chiXuShiJian="";
//			}
			
			need_item=(need_item.equals("0"))?"":"&radic;";
			default_item=(default_item.equals("0"))?"":"&radic;";
			String effectFlagSql = "select effect_flag from lcp_node_order_item where hospital_id="+HOSPITALID+" and cp_id="+cp_id+" and cp_node_id="+cp_node_id+" and cp_node_order_id="+cp_node_nurse_id+" and cp_node_order_item_id="+cp_node_order_item_id;

			String effectFlag = db.FunGetDataSetBySQL(effectFlagSql).FunGetDataAsStringById(0, 0);
			if(effectFlag=="") effectFlag="0";
			if(effectFlag.equals("1")){
				table=table+"<tr  class='STYLE10' height='20' name='mytb'  id='"+cp_id+"_"+cp_node_id+"_"+cp_node_nurse_id+"_"+cp_node_order_item_id+"' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='lineclick(this);'>"+
				"<td  align='center' ><input type='checkbox' name='chekcbox_orderitem' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_nurse_id+"_"+cp_node_order_item_id+"'></td>"+
				//"<td  align='center' >"+cp_node_order_item_id+"</td>"+//序号
				"<td  align='center'>"+cp_node_order_item_id+"</td>"+//序号
				"<td  align='center' >"+groupID+"</td>"+//组
				"<td  align='center' >"+order_kind+"</td>"+//类别  长期-临时-出院
				"<td  align='center' >"+ORDER_TYPE_NAME+"</td>"+//------------>
				"<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"<span style='color: #FF0000'>(不可用)</span></td>"+//医嘱内弄
				"<td  align='center' >"+specification+"</td>"+//规格
				"<td  align='center' >"+need_item+"</td>"+//必做
				"<td  align='center' >"+default_item+"</td>"+//默认
				"<td  align='center' >"+jiliang+"</td>"+//计量
				"<td  align='center' >"+FREQUENCY+"</td>"+//频次
				//"<td  align='center' >"+WAY+"</td>"+//途径
				"<td  align='center' >"+SUPPLYNAME+"</td>"+//途径
				"</tr>";
			}else{
				table=table+"<tr  class='STYLE10' height='20' name='mytb'  id='"+cp_id+"_"+cp_node_id+"_"+cp_node_nurse_id+"_"+cp_node_order_item_id+"' bgcolor='#FFFFFF'onmouseover='changeColor(this)'  onMouseOut='recoverColor(this)' onclick='lineclick(this);'>"+
				"<td  align='center' ><input type='checkbox' name='chekcbox_orderitem' id='"+cp_id+"_"+cp_node_id+"_"+cp_node_nurse_id+"_"+cp_node_order_item_id+"'></td>"+
				//"<td  align='center' >"+cp_node_order_item_id+"</td>"+//序号
				"<td  align='center'>"+cp_node_order_item_id+"</td>"+//序号
				"<td  align='center' >"+groupID+"</td>"+//组
				"<td  align='center' >"+order_kind+"</td>"+//类别  长期-临时-出院
				"<td  align='center' >"+ORDER_TYPE_NAME+"</td>"+//------------>
				"<td  align='left' >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+cp_node_order_text+"</td>"+//医嘱内弄
				"<td  align='center' >"+specification+"</td>"+//规格
				"<td  align='center' >"+need_item+"</td>"+//必做
				"<td  align='center' >"+default_item+"</td>"+//默认
				"<td  align='center' >"+jiliang+"</td>"+//计量
				"<td  align='center' >"+FREQUENCY+"</td>"+//频次
				//"<td  align='center' >"+WAY+"</td>"+//途径
				"<td  align='center' >"+SUPPLYNAME+"</td>"+//途径
				"</tr>";
			}	
		}
		return table;
	}

	public boolean funDelPoint(String cpID, String cpNodeID,
			String cpNodeOrderID, String type) {
		String delSql = "";
		DataSet dataSet = new DataSet();
		if ("1".equals(type)) {
			String sql = "SELECT CP_NODE_ORDER_ID  FROM LCP_NODE_ORDER_POINT T "
					+ "where CP_ID="
					+ cpID
					+ " AND CP_NODE_ID="
					+ cpNodeID
					+ " AND CONTINUE_ORDER_ID="
					+ cpNodeOrderID
					+ " AND HOSPITAL_ID=" + HOSPITALID;
			dataSet.funSetDataSetBySql(sql);
			int row = dataSet.getRowNum();
			for (int i = 0; i < row; i++) {
				String cp_node_order_id = dataSet.funGetFieldByCol(i,
						"CP_NODE_ORDER_ID");
				delSql = delSql + "delete LCP_NODE_ORDER_POINT where CP_ID="
						+ cpID + " AND CP_NODE_ID=" + cpNodeID
						+ " AND CP_NODE_ORDER_ID=" + cp_node_order_id
						+ " AND HOSPITAL_ID=" + HOSPITALID + "\r\n";
				delSql = delSql + "delete LCP_NODE_ORDER_ITEM where CP_ID="
						+ cpID + " AND CP_NODE_ID=" + cpNodeID
						+ " AND CP_NODE_ORDER_ID=" + cp_node_order_id
						+ " AND HOSPITAL_ID=" + HOSPITALID + "\r\n";
			}
		} else if ("2".equals(type)) {// 删除第二层point及item
			delSql = "delete LCP_NODE_ORDER_POINT where CP_ID=" + cpID
					+ " AND CP_NODE_ID=" + cpNodeID + " AND CP_NODE_ORDER_ID="
					+ cpNodeOrderID + " AND HOSPITAL_ID=" + HOSPITALID + "\r\n";
			delSql = delSql + "delete LCP_NODE_ORDER_ITEM where CP_ID=" + cpID
					+ " AND CP_NODE_ID=" + cpNodeID + " AND CP_NODE_ORDER_ID="
					+ cpNodeOrderID + " AND HOSPITAL_ID=" + HOSPITALID + "\r\n";

		}
		int row = -1;
		try {
			row = dataSet.FunRunSqlByFile(delSql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		 
		if (row > 0)
			return true;
		return false;
	}
	
	public boolean funDelItem(String ids){
		String []_ids=ids.split(",");
		String sql="";
		DatabaseClass db = LcpUtil.getDatabaseClass();
		for(int i=0;i<_ids.length;i++){
			String []id=_ids[i].split("_");
			String setItemSql="select order_item_set_id from lcp_node_order_item WHERE CP_ID="+id[0]+" AND CP_NODE_ID="+id[1]+" AND CP_NODE_ORDER_ID="+id[2]+" AND CP_NODE_ORDER_ITEM_ID="+id[3]+" AND HOSPITAL_ID="+HOSPITALID;
			String orderSetItemId = db.FunGetDataSetBySQL(setItemSql).FunGetDataAsStringById(0, 0);
			if("".equals(orderSetItemId) || orderSetItemId == null){
				sql=sql+"DELETE LCP_NODE_ORDER_ITEM WHERE CP_ID="+id[0]+" AND CP_NODE_ID="+id[1]+" AND CP_NODE_ORDER_ID="+id[2]+" AND CP_NODE_ORDER_ITEM_ID="+id[3]+" AND HOSPITAL_ID="+HOSPITALID+"\r\n";
			}else{
				return false;
			}
		}
		//System.out.println(sql);
		DataSet dataSet=new DataSet();
		int row=-1;
		try {
			row=dataSet.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<_ids.length;i++){
			String []id=_ids[i].split("_");
			String selectSql="select CP_NODE_ORDER_ITEM_ID from LCP_NODE_ORDER_ITEM WHERE CP_ID="+id[0]+" AND CP_NODE_ID="+id[1]+" AND CP_NODE_ORDER_ID="+id[2]+" AND HOSPITAL_ID="+HOSPITALID;
			int current=db.FunGetDataSetBySQL(selectSql).FunGetRowCount();
			if(current==0){
				System.out.println("不用更新!");
			}else{
				DataSet dataSet1=new DataSet();
				String updateSql="";
				int cpNodeItemID=0;
				for(int j=1;j<=current;j++){
					cpNodeItemID=db.FunGetDataSetBySQL(selectSql).FunGetDataAsNumberByColName((j-1), "CP_NODE_ORDER_ITEM_ID").intValue();
						
					try {
						updateSql="update LCP_NODE_ORDER_ITEM set CP_NODE_ORDER_ITEM_ID="+j+" WHERE CP_ID="+id[0]+" AND CP_NODE_ID="+id[1]+" AND CP_NODE_ORDER_ID="+id[2]+" AND CP_NODE_ORDER_ITEM_ID="+cpNodeItemID+" AND HOSPITAL_ID="+HOSPITALID;
						dataSet1.FunRunSqlByFile(updateSql.getBytes("GB2312"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					//System.out.println(cpNodeItemID);
				}
				//System.out.println("updateSql=:" + updateSql);
			}
		}
		if(row>0) return true;
		return false;
	}
	
	
	public boolean funUpItemGroup(String ids){
		String itemIds=funUpItemGroups(ids);
		DataSet dataSet=new DataSet();
		String []_ids=ids.split(",");
		String minNum="";
		
		{

			String[] id = _ids[0].split("_");
			String selMinId = "select min(CP_NODE_ORDER_ITEM_ID) min_num from lcp_node_order_item  where cp_id = "
					+ id[0]
					+ " and cp_node_id = "
					+ id[1]
					+ " and cp_node_order_id ="
					+ id[2]
					+ " and CP_NODE_ORDER_ITEM_ID in(" + itemIds + ")";
			//System.out.println(selMinId);
			dataSet.funSetDataSetBySql(selMinId);
			int row = dataSet.getRowNum();

			for (int i = 0; i < row; i++) {
				minNum = dataSet.funGetFieldByCol(i, "MIN_NUM");
			}

		}
		
		
		String sql="";
		for(int i=0;i<_ids.length;i++){
			String []id=_ids[i].split("_");
			 sql=sql+"UPDATE lcp_node_order_item t SET t.order_item_set_id= "+minNum+
			"where cp_id = "+id[0] +
			"and cp_node_id = "+id[1]+
			"and cp_node_order_id ="+id[2]+
			" AND CP_NODE_ORDER_ITEM_ID="+id[3]+" AND HOSPITAL_ID="+HOSPITALID+"\r\n";

		}
		
		int row=-1;
		try {
			row=dataSet.FunRunSqlByFile(sql.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(row>0) return true;
		return false;
	}
	
	
	public String funUpItemGroups(String ids){
		String []_ids=ids.split(",");
		String sql="";
		for(int i=0;i<_ids.length;i++){
			String []id=_ids[i].split("_");
			sql=sql+id[3]+",";
		}
		sql=sql.substring(0, sql.length()-1);
		return sql;
	}
	
	public String funGetOrderBelong(String cp_id,String cp_node_id){
		String sql="SELECT * FROM LCP_NODE_ORDER_POINT T WHERE  CP_ID="+cp_id+" AND CP_NODE_ID="+cp_node_id+" AND HOSPITAL_ID="+HOSPITALID+" order by cp_node_order_id";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		String table="";
		int row=dataSet.getRowNum();
		for(int i=0;i<row;i++){
			String cp_node_order_id=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_ID");
			String cp_node_order_text=dataSet.funGetFieldByCol(i, "CP_NODE_ORDER_TEXT");
				table=table+"<option value='"+cp_node_order_id+"'>"+cp_node_order_text+"</option>";
			}
		return table;
	}
}
