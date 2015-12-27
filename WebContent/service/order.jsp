<%
/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
//
// 文件名：order.jsp  
// 文件功能描述：院内医嘱下发页面
// 接收参数: 病人IDpatient_no   住院号admissTimes  医生工号doctorNo  病区patientArea
// 创建人：段英华
// 创建日期：2011-8-25
// 修改日期：2011-8-25
// 完成日期：
//----------------------------------------------------------------*/ 
%>

<%@page import="com.goodwillcis.lcp.util.LcpUtil"%>
<%@page import="com.goodwillcis.general.DataSetClass"%>
<%@page import="com.goodwillcis.general.DatabaseClass"%>
<%@page import="com.goodwillcis.lcp.util.*"%>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.net.URLEncoder"%>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%	final int HOSPITALID=LcpUtil.getHospitalID();
	DatabaseClass db=LcpUtil.getDatabaseClass(); 
	String nullOrderAddress1="";
	String orderAddressLN="";
	String nullOrderAddress2="";
	String orderAddressGB="";
	String nullOrderAddress3="";
	String swOrderURLAddress2="";
	String swOrderURLAddress="";
	String swOrderURLAddress2xhnk="";
	String swOrderURLAddressxhnk="";
	String fcOrderLink2="";
	String fcOrderLink="";
	String gkOrderLink2="";
	String gkOrderLink="";
	String ykOrderLink2="";
	String ykOrderLink="";
	String mnwkOrderLink2="";
	String mnwkOrderLink="";
	String ekOrderLink2="";
	String ekOrderLink="";
	String ebhkOrderLink2="";
	String ebhkOrderLink="";
	String gdwkOrderLink2="";
	String gdwkOrderLink="";
	String hxnkOrderLink2="";
	String hxnkOrderLink="";
	String kqkOrderLink2="";
	String kqkOrderLink="";
	String pwewbqOrderLink2="";
	String pwewbqOrderLink="";
	String rxwkbqOrderLink2="";
	String rxwkbqOrderLink="";
	String sjnkOrderLink2="";
	String sjnkOrderLink="";
	String snkOrderLink2="";
	String snkOrderLink="";
	String wcwkOrderLink2="";
	String wcwkOrderLink="";
	String xewkbqOrderLink2="";
	String xewkbqOrderLink="";
	String xgwkbqOrderLink2="";
	String xgwkbqOrderLink="";
	String ptwkebqOrderLink2="";
	String ptwkebqOrderLink="";
	String fskOrderLink2="";
	String fskOrderLink="";
	String nfmkOrderLink2="";
	String nfmkOrderLink="";
	String zlflkOrderLink2="";
	String zlflkOrderLink="";
	String zykOrderLink2="";
	String zykOrderLink="";
	String smkOrderLink="";
	String zlkOrderLink2="";
	String zlkOrderLink="";
	String zzyxkOrderLink2="";
	String zzyxkOrderLink="";
	String xykOrderLink2="";
	String xykOrderLink="";
	String xwkOrderLink2="";
	String xwkOrderLink="";
	String jzOrderLink2="";
	String jzOrderLink="";
	String zxmrkOrderLink2="";
	String zxmrkOrderLink="";
	//病人ID
	String patientID=request.getParameter("patient_no");
	//住院次数
	String admissTimes = request.getParameter("admissTimes");
	//路径表内查询ID
	String patientId1 = patientID + "_" + admissTimes;	

	//System.out.println("eeeeeeeeee====:"+patientId1);
	//医生工号
	String doctorNo = request.getParameter("doctorNo");
	//病区
	String patientArea = request.getParameter("patientArea");
	///婴儿标志
	String babyStatus = request.getParameter("babyStatus");
	//纳入医嘱调用地址
	String order = PropertiesUtil.get(PropertiesUtil.ORDER_URL);
	String orderAddress = LcpUtil.getConfigValue(order);
	//不纳入医嘱调用地址
	String nullOrder = PropertiesUtil.get(PropertiesUtil.NULL_ORDER_URL);
	String nullOrderAddress = LcpUtil.getConfigValue(nullOrder);
	//肿瘤科不纳入医嘱地址
	if(patientArea.equals("1200200")){
		String tumourOrder = PropertiesUtil.get(PropertiesUtil.TUMOUR_ORDER_URL);
		nullOrderAddress1 = LcpUtil.getConfigValue(tumourOrder);
	}
	//老年科跳转到金榜服务器的医嘱系统
	if(patientArea.equals("1060000") || patientArea.equals("1060002") || patientArea.equals("1060001") || patientArea.equals("1060003")){
		String oldOrder = PropertiesUtil.get(PropertiesUtil.OLD_ORDER_URL);
		orderAddressLN = LcpUtil.getConfigValue(oldOrder);
		String nullOldOrder = PropertiesUtil.get(PropertiesUtil.OLD_ORDER_URL_NULL);
		nullOrderAddress2=LcpUtil.getConfigValue(nullOldOrder);
	}
	//干部保健科医嘱下发页面
	if(patientArea.equals("1120001") || patientArea.equals("1120002")){
		String gbOrder = PropertiesUtil.get(PropertiesUtil.GB_ORDER_URL);
		orderAddressGB = LcpUtil.getConfigValue(gbOrder);
		String nullGBOrder = PropertiesUtil.get(PropertiesUtil.GB_ORDER_URL_NULL);
		nullOrderAddress3=LcpUtil.getConfigValue(nullGBOrder);
	}
	//神经外科调用医嘱地址
	if(patientArea.equals("1150000")){
		String swOrder = PropertiesUtil.get(PropertiesUtil.SW_ORDER_URL);
		swOrderURLAddress2 = LcpUtil.getConfigValue(swOrder);
		String nullSWOrder = PropertiesUtil.get(PropertiesUtil.SW_ORDER_URL_NULL);
		swOrderURLAddress=LcpUtil.getConfigValue(nullSWOrder);
	}
	//消化内科调用医嘱地址
	if(patientArea.equals("1010100") || patientArea.equals("1010103")){
		String xhOrder = PropertiesUtil.get(PropertiesUtil.XH_ORDER_URL);
		swOrderURLAddress2xhnk = LcpUtil.getConfigValue(xhOrder);
		String nullXHOrder = PropertiesUtil.get(PropertiesUtil.XH_ORDER_URL_NULL);
		swOrderURLAddressxhnk=LcpUtil.getConfigValue(nullXHOrder);
	}
	//产科vip 妇产科， 妇科一病区，妇科二病区
	if(patientArea.equals("1040010") || patientArea.equals("1040011") || patientArea.equals("1040000") || patientArea.equals("1040012")){
		String fcOrder = PropertiesUtil.get(PropertiesUtil.FC_ORDER_URL);
		fcOrderLink2 = LcpUtil.getConfigValue(fcOrder);
		String nullFCOrder = PropertiesUtil.get(PropertiesUtil.FC_ORDER_URL_NULL);
		fcOrderLink=LcpUtil.getConfigValue(nullFCOrder);
	}
	//关节外科病区  脊柱外科病区 创伤骨科病区
	if(patientArea.equals("1031001") || patientArea.equals("1032001") || patientArea.equals("1033001")){
		String gkOrder = PropertiesUtil.get(PropertiesUtil.GK_ORDER_URL);
		gkOrderLink2 = LcpUtil.getConfigValue(gkOrder);
		String nullGKOrder = PropertiesUtil.get(PropertiesUtil.GK_ORDER_URL_NULL);
		gkOrderLink=LcpUtil.getConfigValue(nullGKOrder);
	}
	
	//眼科病区
	if(patientArea.equals("1090001")){
		String ykOrder = PropertiesUtil.get(PropertiesUtil.YK_ORDER_URL);
		ykOrderLink2 = LcpUtil.getConfigValue(ykOrder);
		String nullYKOrder = PropertiesUtil.get(PropertiesUtil.YK_ORDER_URL_NULL);
		ykOrderLink=LcpUtil.getConfigValue(nullYKOrder);
	}
	//泌尿外科
	if(patientArea.equals("1020300")){
		String mnwkOrder = PropertiesUtil.get(PropertiesUtil.MNWK_ORDER_URL);
		mnwkOrderLink2 = LcpUtil.getConfigValue(mnwkOrder);
		String nullMNWKOrder = PropertiesUtil.get(PropertiesUtil.MNWK_ORDER_URL_NULL);
		mnwkOrderLink=LcpUtil.getConfigValue(nullMNWKOrder);
	}
	//儿科
	if(patientArea.equals("1050000")){
		String ekOrder = PropertiesUtil.get(PropertiesUtil.EK_ORDER_URL);
		ekOrderLink2 = LcpUtil.getConfigValue(ekOrder);
		String nullEKOrder = PropertiesUtil.get(PropertiesUtil.EK_ORDER_URL_NULL);
		ekOrderLink=LcpUtil.getConfigValue(nullEKOrder);
	}
	//耳鼻喉科
	if(patientArea.equals("1100000")){
		String ebhkOrder = PropertiesUtil.get(PropertiesUtil.EBHK_ORDER_URL);
		ebhkOrderLink2 = LcpUtil.getConfigValue(ebhkOrder);
		String nullEBHKOrder = PropertiesUtil.get(PropertiesUtil.EBHK_ORDER_URL_NULL);
		ebhkOrderLink=LcpUtil.getConfigValue(nullEBHKOrder);
	}
	//肝胆外科   1020202/1020200
	if(patientArea.equals("1020202") || patientArea.equals("1020200")){
		String gdwkOrder = PropertiesUtil.get(PropertiesUtil.GDWK_ORDER_URL);
		gdwkOrderLink2 = LcpUtil.getConfigValue(gdwkOrder);
		String nullGDWKOrder = PropertiesUtil.get(PropertiesUtil.GDWK_ORDER_URL_NULL);
		gdwkOrderLink=LcpUtil.getConfigValue(nullGDWKOrder);
	}
	//呼吸内科  1010200/1010202/1010203
	if(patientArea.equals("1010200") || patientArea.equals("1010202") || patientArea.equals("1010203")){
		String hxnkOrder = PropertiesUtil.get(PropertiesUtil.HXNK_ORDER_URL);
		hxnkOrderLink2 = LcpUtil.getConfigValue(hxnkOrder);
		String nullHXNKOrder = PropertiesUtil.get(PropertiesUtil.HXNK_ORDER_URL_NULL);
		hxnkOrderLink=LcpUtil.getConfigValue(nullHXNKOrder);
	}
	//口腔科
	if(patientArea.equals("1110100")){
		String kqkOrder = PropertiesUtil.get(PropertiesUtil.KQK_ORDER_URL);
		kqkOrderLink2 = LcpUtil.getConfigValue(kqkOrder);
		String nullKQKOrder = PropertiesUtil.get(PropertiesUtil.KQK_ORDER_URL_NULL);
		kqkOrderLink=LcpUtil.getConfigValue(nullKQKOrder);
	}
	//普外儿外病区
	if(patientArea.equals("1020103")){
		String pwewbqOrder = PropertiesUtil.get(PropertiesUtil.PWEWBQ_ORDER_URL);
		pwewbqOrderLink2 = LcpUtil.getConfigValue(pwewbqOrder);
		String nullPWEWBQOrder = PropertiesUtil.get(PropertiesUtil.PWEWBQ_ORDER_URL_NULL);
		pwewbqOrderLink=LcpUtil.getConfigValue(nullPWEWBQOrder);
	}
	//乳腺外科病区
	if(patientArea.equals("1520001")){
		String rxwkbqOrder = PropertiesUtil.get(PropertiesUtil.RXWKBQ_ORDER_URL);
		rxwkbqOrderLink2 = LcpUtil.getConfigValue(rxwkbqOrder);
		String nullRXWKBQOrder = PropertiesUtil.get(PropertiesUtil.RXWKBQ_ORDER_URL_NULL);
		rxwkbqOrderLink=LcpUtil.getConfigValue(nullRXWKBQOrder);
	}
	//神经内科
	if(patientArea.equals("1140001")){
		String sjnkOrder = PropertiesUtil.get(PropertiesUtil.SJNK_ORDER_URL);
		sjnkOrderLink2 = LcpUtil.getConfigValue(sjnkOrder);
		String nullSJNKOrder = PropertiesUtil.get(PropertiesUtil.SJNK_ORDER_URL_NULL);
		sjnkOrderLink=LcpUtil.getConfigValue(nullSJNKOrder);
	}
	//肾内科
	if(patientArea.equals("1010300")){
		String snkOrder = PropertiesUtil.get(PropertiesUtil.SNK_ORDER_URL);
		snkOrderLink2 = LcpUtil.getConfigValue(snkOrder);
		String nullSNKOrder = PropertiesUtil.get(PropertiesUtil.SNK_ORDER_URL_NULL);
		snkOrderLink=LcpUtil.getConfigValue(nullSNKOrder);
	}
	//胃肠外科
	if(patientArea.equals("1260001")){
		String wcwkOrder = PropertiesUtil.get(PropertiesUtil.WCWK_ORDER_URL);
		wcwkOrderLink2 = LcpUtil.getConfigValue(wcwkOrder);
		String nullWCWKOrder = PropertiesUtil.get(PropertiesUtil.WCWK_ORDER_URL_NULL);
		wcwkOrderLink=LcpUtil.getConfigValue(nullWCWKOrder);
	}
	//小儿外科病区
	if(patientArea.equals("1510000")){
		String xewkbqOrder = PropertiesUtil.get(PropertiesUtil.XEWKBQ_ORDER_URL);
		xewkbqOrderLink2 = LcpUtil.getConfigValue(xewkbqOrder);
		String nullXEWKBQOrder = PropertiesUtil.get(PropertiesUtil.XEWKBQ_ORDER_URL_NULL);
		xewkbqOrderLink=LcpUtil.getConfigValue(nullXEWKBQOrder);
	}
	//血管外科病区
	if(patientArea.equals("1250010")){
		String xgwkbqOrder = PropertiesUtil.get(PropertiesUtil.XGWKBQ_ORDER_URL);
		xgwkbqOrderLink2 = LcpUtil.getConfigValue(xgwkbqOrder);
		String nullXGWKBQOrder = PropertiesUtil.get(PropertiesUtil.XGWKBQ_ORDER_URL_NULL);
		xgwkbqOrderLink=LcpUtil.getConfigValue(nullXGWKBQOrder);
	}
	//普通外科二病区
	if(patientArea.equals("1020102")){
		String ptwkebqOrder = PropertiesUtil.get(PropertiesUtil.PTWKEBQ_ORDER_URL);
		ptwkebqOrderLink2 = LcpUtil.getConfigValue(ptwkebqOrder);
		String nullPTWKEBQOrder = PropertiesUtil.get(PropertiesUtil.PTWKEBQ_ORDER_URL_NULL);
		ptwkebqOrderLink=LcpUtil.getConfigValue(nullPTWKEBQOrder);
	}
	//放射科
	if(patientArea.equals("2040000")){
		String fskOrder = PropertiesUtil.get(PropertiesUtil.FSK_ORDER_URL);
		fskOrderLink2 = LcpUtil.getConfigValue(fskOrder);
		String nullFSKOrder = PropertiesUtil.get(PropertiesUtil.FSK_ORDER_URL_NULL);
		fskOrderLink=LcpUtil.getConfigValue(nullFSKOrder);
	}
	//内分泌科
	if(patientArea.equals("1220000")){
		String nfmkOrder = PropertiesUtil.get(PropertiesUtil.NFMK_ORDER_URL);
		nfmkOrderLink2 = LcpUtil.getConfigValue(nfmkOrder);
		String nullNFMKOrder = PropertiesUtil.get(PropertiesUtil.NFMK_ORDER_URL_NULL);
		nfmkOrderLink=LcpUtil.getConfigValue(nullNFMKOrder);
		/* nfmkOrderLink2="http://192.1.33.144:8080/EMRClinicPath/clinicPathAdvice.do";
		nfmkOrderLink="http://192.1.33.144:8080/EMRClinicPath/loadAdvice.do"; */
	}
	//肿瘤放疗科
	if(patientArea.equals("1240000")){
		String zlflkOrder = PropertiesUtil.get(PropertiesUtil.ZLFLK_ORDER_URL);
		zlflkOrderLink2 = LcpUtil.getConfigValue(zlflkOrder);
		String nullZLFLKOrder = PropertiesUtil.get(PropertiesUtil.ZLFLK_ORDER_URL_NULL);
		zlflkOrderLink=LcpUtil.getConfigValue(nullZLFLKOrder);
	}
	//中医科
	if(patientArea.equals("1080000")){
		String zykOrder = PropertiesUtil.get(PropertiesUtil.ZYK_ORDER_URL);
		zykOrderLink2 = LcpUtil.getConfigValue(zykOrder);
		String nullZYKOrder = PropertiesUtil.get(PropertiesUtil.ZYK_ORDER_URL_NULL);
		zykOrderLink=LcpUtil.getConfigValue(nullZYKOrder);
	}
	//手术麻醉科
	if(patientArea.equals("1130100")){
		String nullSMKOrder = PropertiesUtil.get(PropertiesUtil.SMK_ORDER_URL_NULL);
		smkOrderLink=LcpUtil.getConfigValue(nullSMKOrder);
	}
	//肿瘤科
	if(patientArea.equals("1200000")){
		String zlkOrder = PropertiesUtil.get(PropertiesUtil.ZLK_ORDER_URL);
		zlkOrderLink2 = LcpUtil.getConfigValue(zlkOrder);
		String nullZLKOrder = PropertiesUtil.get(PropertiesUtil.ZLK_ORDER_URL_NULL);
		zlkOrderLink=LcpUtil.getConfigValue(nullZLKOrder);
	}
	//重症医学科
	if(patientArea.equals("1400400") || patientArea.equals("1400401")){
		String zzyxkOrder = PropertiesUtil.get(PropertiesUtil.ZZYXK_ORDER_URL);
		zzyxkOrderLink2 = LcpUtil.getConfigValue(zzyxkOrder);
		String nullZZYXKOrder = PropertiesUtil.get(PropertiesUtil.ZZYXK_ORDER_URL_NULL);
		zzyxkOrderLink=LcpUtil.getConfigValue(nullZZYXKOrder);
	}
	//血液科
	if(patientArea.equals("1210000")){
		String xykOrder = PropertiesUtil.get(PropertiesUtil.XYK_ORDER_URL);
		xykOrderLink2 = LcpUtil.getConfigValue(xykOrder);
		String nullXYKOrder = PropertiesUtil.get(PropertiesUtil.XYK_ORDER_URL_NULL);
		xykOrderLink=LcpUtil.getConfigValue(nullXYKOrder);
	}
	//胸外科
	if(patientArea.equals("1020400")){
		/* xwkOrderLink2="http://192.1.33.144:8080/EMRClinicPath/clinicPathAdvice.do";
		xwkOrderLink="http://192.1.33.144:8080/EMRClinicPath/loadAdvice.do"; */
		String xwkOrder = PropertiesUtil.get(PropertiesUtil.XWK_ORDER_URL);
		xwkOrderLink2 = LcpUtil.getConfigValue(xwkOrder);
		String nullXWKOrder = PropertiesUtil.get(PropertiesUtil.XWK_ORDER_URL_NULL);
		xwkOrderLink=LcpUtil.getConfigValue(nullXWKOrder);
	}
	//急诊ICU
	if(patientArea.equals("1190004") || patientArea.equals("1190005") || patientArea.equals("1190000")){
		String jzOrder = PropertiesUtil.get(PropertiesUtil.JZ_ORDER_URL);
		jzOrderLink2 = LcpUtil.getConfigValue(jzOrder);
		String nullJZOrder = PropertiesUtil.get(PropertiesUtil.JZ_ORDER_URL_NULL);
		jzOrderLink=LcpUtil.getConfigValue(nullJZOrder);
	}
	//整形美容科
	if(patientArea.equals("1230000")){
		String zxmrkOrder = PropertiesUtil.get(PropertiesUtil.ZXMRK_ORDER_URL);
		zxmrkOrderLink2 = LcpUtil.getConfigValue(zxmrkOrder);
		String nullZXMRKOrder = PropertiesUtil.get(PropertiesUtil.ZXMRK_ORDER_URL_NULL);
		zxmrkOrderLink=LcpUtil.getConfigValue(nullZXMRKOrder);
	}
	//医生级别
	String doctorQX = request.getParameter("doctorQX");
	String alertMsg = "";
	//如果没取到值，则先给他最大权限
	if(doctorQX == null || doctorQX == ""){
		doctorQX = "2";
	}
	if(doctorQX.equals("0")){
		alertMsg = "请注意：您所在的医生组不可以下抗生素相关医嘱！";
	}else if(doctorQX.equals("1")){
		alertMsg = "请注意：您所在的医生组不可以下高级抗生素相关医嘱！";
	}
	
	
	DataSetClass ds = null;	
	DataSetClass ds7 = null;

	String cpNodeID="";
	String cpID="";
	String cpName = "";
	String cpNodeName="";
	String json = "";
	String nextNodeType = "";
	String nextNodeId = "";
	List list = new ArrayList();
	
	UpdateUtil uu = new UpdateUtil(); 
	String deptNo = uu.findCurrDeptNo(patientId1);
	
	if(patientID==null||"".equals(patientID)){
		response.sendRedirect("../route/error.html"); 
	}	
	//System.out.println("-----"+patientArea);
	//这个是为肿瘤科室特别准备的地址		
	if(patientArea.equals("1200200")){
		String orderAddress1 = nullOrderAddress1 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
				);
	//老年病科室
	}/* else if(patientArea.equals("1060000") || patientArea.equals("1060002")){
		String orderAddress2 = nullOrderAddress2 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1";
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress2+"';     </script>"
				); 
	} */else if(deptNo.equals("1130100")){	
		//如果是手术麻醉科，则直接跳转到医嘱系统。
		String orderAddress1 = smkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
				); 
	}else if(patientArea.equals("1130100")){	
		//如果是手术麻醉科，则直接跳转到医嘱系统。
		String orderAddress1 = smkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
		response.getWriter().print(
				" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
				); 
	}
		//查当前患者所在路径的最大节点，判断节点类型
		String sql = "select a.CP_NODE_TYPE,a.CP_ID from LCP_PATIENT_NODE a where a.CP_NODE_ID in (select CP_NODE_ID from LCP_PATIENT_NODE t where t.PATIENT_NO = '"+patientId1+"') and a.PATIENT_NO = '"+patientId1+"' and a.HOSPITAL_ID = "+HOSPITALID;
		//System.out.println("============="+sql);
		String currNodeType="";
		String nodeType="";
		for(int n=0;n<db.FunGetRowCountBySql(sql);n++){
			 nodeType = db.FunGetDataSetBySQL(sql).FunGetDataAsStringByColName(n,"CP_NODE_TYPE").toString().trim();
			 cpID = db.FunGetDataSetBySQL(sql).FunGetDataAsStringByColName(0,"CP_ID").toString().trim();
			 currNodeType+=nodeType;
		}
		//cpID=db.FunGetDataSetBySQL("select distinct(tp.cp_id) from LCP_PATIENT_NODE tp where tp.patient_no='"+patientId1+"'").FunGetDataAsStringById(0,0);
		//System.out.println("=====:"+cpID);
		if(cpID!= ""){
			int cpID1 = Integer.valueOf(cpID).intValue();
		}
		//if(cpID1 < 10037){
			//response.sendRedirect("../route/error1.html"); 
		//}
		//192.1.33.144:8081   1150000
		//如果医嘱类型是0  准入节点  则自动跳转到第一天，如果是2,3,4 (完成 变异 退出) 跳到医嘱系统界面
		if(currNodeType.equals("0")){
			String sql2 = "select t.cp_next_node_id from lcp_node_relate t where t.cp_id = (select distinct(tp.cp_id) from LCP_PATIENT_NODE tp where tp.patient_no='"+patientId1+"') and t.cp_node_id = (select t.cp_node_id from lcp_master_node t where t.cp_id = (select distinct(tp.cp_id) from LCP_PATIENT_NODE tp where tp.patient_no='"+patientId1+"') and t.cp_node_type = 0) and t.cp_next_node_id <> (select t.cp_node_id from lcp_master_node t where t.cp_id = (select distinct(tp.cp_id) from LCP_PATIENT_NODE tp where tp.patient_no='"+patientId1+"') and t.cp_node_type = 4)";
			cpNodeID=db.FunGetDataSetBySQL(sql2).FunGetDataAsStringById(0,0);
			//cpNodeID = "2";	
		//如果在node表查不到值，表示这个人没在路径内，则直接跳到医嘱页面
		}else if(currNodeType.equals("") || currNodeType.contains("2") || currNodeType.contains("3") ||currNodeType.contains("4")){	
			//如果类型是完成或者变异退出状态，则跳转到医嘱系统。
			if(patientArea.equals("1060000") || patientArea.equals("1060002")|| patientArea.equals("1060001") || patientArea.equals("1060003")){
				String orderAddress2 = nullOrderAddress2 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress2+"';     </script>"
						); 
			} else if(patientArea.equals("1120001") || patientArea.equals("1120002")) {
				String orderAddress3 = nullOrderAddress3 + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress3+"';     </script>"
						); 
				
			}else if(patientArea.equals("1150000")) {
				String swOrderAddress = swOrderURLAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+swOrderAddress+"';     </script>"
						); 
				
			}else if(patientArea.equals("1010100") || patientArea.equals("1010103")) {
				String swOrderAddress1 = swOrderURLAddressxhnk + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+swOrderAddress1+"';     </script>"
						); 
			}else if(patientArea.equals("1040010") || patientArea.equals("1040011")|| patientArea.equals("1040000")|| patientArea.equals("1040012")) {
				String fcOrderLink1 = fcOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+fcOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1031001") || patientArea.equals("1032001")|| patientArea.equals("1033001")) {
				String gkOrderLink1 = gkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+gkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1090001")) {
				String ykOrderLink1 = ykOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+ykOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1020300")) {
				String mnwkOrderLink1 = mnwkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+mnwkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1050000")) {//儿科
				String ekOrderLink1 = ekOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+ekOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1100000")) {//耳鼻喉科
				String ebhkOrderLink1 = ebhkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+ebhkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1020202") || patientArea.equals("1020200")) {//肝胆外科
				String gdwkOrderLink1 = gdwkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+gdwkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1010200") || patientArea.equals("1010202") || patientArea.equals("1010203")) {//呼吸内科
				String hxnkOrderLink1 = hxnkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+hxnkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1110100")) {//口腔科
				String kqkOrderLink1 = kqkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+kqkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1020103")) {//普外儿外病区
				String pwewbqOrderLink1 = pwewbqOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+pwewbqOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1520001")) {//乳腺外科病区
				String rxwkbqOrderLink1 = rxwkbqOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+rxwkbqOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1140001")) {//神经内科
				String sjnkOrderLink1 = sjnkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+sjnkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1010300")) {//肾内科
				String snkOrderLink1 = snkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+snkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1260001")) {//胃肠外科
				String wcwkOrderLink1 = wcwkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+wcwkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1510000")) {//小儿外科病区
				String xewkbqOrderLink1 = xewkbqOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+xewkbqOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1250010")) {//血管外科病区
				String xgwkbqOrderLink1 = xgwkbqOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+xgwkbqOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1020102")) {//普通外科二病区
				String ptwkebqOrderLink1 = ptwkebqOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+ptwkebqOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("2040000")) {//放射科
				String fskOrderLink1 = fskOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+fskOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1220000")) {//内分泌科科
				String nfmkOrderLink1 = nfmkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+nfmkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1240000")) {//肿瘤放疗科
				String zlflkOrderLink1 = zlflkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+zlflkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1080000")) {//中医科
				String zykOrderLink1 = zykOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+zykOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1200000")) {//肿瘤科
				String zlkOrderLink1 = zlkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+zlkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1400400") || patientArea.equals("1400401")) {//重症医学科
				String zzyxkOrderLink1 = zzyxkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+zzyxkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1210000")) {//血液科
				String xykOrderLink1 = xykOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+xykOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1020400")) {//胸外科
				String xwkOrderLink1 = xwkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+xwkOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1190004") || patientArea.equals("1190005") || patientArea.equals("1190000")) {//急诊
				String jzOrderLink1 = jzOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+jzOrderLink1+"';     </script>"
						); 
				
			}else if(patientArea.equals("1230000")) {//整形美容科
				String zxmrkOrderLink1 = zxmrkOrderLink + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+zxmrkOrderLink1+"';     </script>"
						); 
				
			}else{
				String orderAddress1 = nullOrderAddress + "?inpatientNo="+patientID+"&admissTimes="+admissTimes+"&physicianCode="+doctorNo+"&deptCode="+patientArea+"&doctorQX="+doctorQX+"&status=1"+"&babyStatus="+babyStatus;
				response.getWriter().print(
						" <script language='javascript' type='text/javascript'>           window.location.href='"+orderAddress1+"';     </script>"
						); 
			}
			
		}
		else{
		
		//查出当前医嘱处在的最大节点
			String sql1 = "select max(cp_node_id) as cpNodeId from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"'";
			
			cpNodeID = db.FunGetDataSetBySQL(sql1).FunGetDataAsStringById(0,0).toString();
			
			if(cpNodeID == ""){
				String oneNodeSql = "select min(t.cp_node_id) as minNodeId from lcp_master_node t where t.cp_id = "+cpID+" and t.cp_node_type = 1 order by t.cp_node_id";
				
				cpNodeID = db.FunGetDataSetBySQL(oneNodeSql).FunGetDataAsStringById(0,0).trim();

				//lcp_node_order_point的数据同步到lcp_patient_order_point里
				String synSql2 = "insert into lcp_patient_order_point (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_text,continue_item,continue_cp_node_id,continue_order_id,need_item,auto_item,order_kind,sys_is_del,sys_last_update) "+
				"(select t.hospital_id,'"+patientId1+"',t.cp_id,t.cp_node_id,t.cp_node_order_id,t.cp_node_order_text,t.continue_item,t.continue_cp_node_id,t.continue_order_id,t.need_item,t.auto_item,t.order_kind,t.sys_is_del,t.sys_last_update from lcp_node_order_point t" +
				" where t.hospital_id = "+HOSPITALID+" and t.cp_id='"+cpID+"' and t.cp_node_id='"+cpNodeID+"')\r\n";

				//lcp_node_order_item的数据同步到lcp_patient_order_item里
				String synSql3 = "insert into lcp_patient_order_item (hospital_id,patient_no,cp_id,cp_node_id,cp_node_order_id,cp_node_order_item_id,cp_node_order_text,order_no,order_type,order_type_name,order_kind,frequency,way,order_item_set_id,ADMINISTRATION,SPECIFICATION,measure,MEASURE_UNITS,DOSAGE,DOSAGE_UNITS,UNIT_ID,MARK,need_item,auto_item,cp_node_class_id,IS_ANTIBIOTIC,sys_is_del,sys_last_update,effect_flag,default_item,drug_id)"+
				 " (select a.hospital_id,'"+patientId1+"',a.cp_id,a.cp_node_id, a.cp_node_order_id,a.cp_node_order_item_id,a.CP_NODE_ORDER_TEXT,a.ORDER_NO,a.ORDER_TYPE,a.ORDER_TYPE_NAME,a.ORDER_KIND,a.frequency,a.way,a.order_item_set_id,a.ADMINISTRATION,a.SPECIFICATION,a.measure,a.MEASURE_UNITS,a.DOSAGE,a.DOSAGE_UNITS,a.UNIT_ID,a.MARK,a.need_item,a.auto_item,a.cp_node_class_id,a.IS_ANTIBIOTIC,sys_is_del,sys_last_update,a.effect_flag,a.default_item,a.drug_id from lcp_node_order_item a" +
				 " where a.hospital_id = "+HOSPITALID+" and a.cp_id='"+cpID+"' and a.cp_node_id='"+cpNodeID+"')\r\n";

				String synSql = synSql2 + synSql3;

try {
	 int result = db.FunRunSqlByFile(synSql.getBytes("GBK"));
	 
			 
	 
	 
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("../route/error.html"); 
				}
			}
			
		//查出下个节点医嘱节点	
			String sql4 = "select min(t.cp_node_id) as minNodeId from lcp_master_node t where t.cp_id = "+cpID+" and t.cp_node_type = 1 and t.cp_node_id > "+cpNodeID+" order by t.cp_node_id" ;

			//System.out.println(sql4);
			DataSetClass ds6 = db.FunGetDataSetBySQL(sql4);
			if(ds6.FunGetRowCount()<=0){
				//一般不应该出现这种情况，但也有可能顺序定义时搞错的，先让他定义为最后一个节点
			}else{
				nextNodeId = ds6.FunGetDataAsStringByColName(0,"MINNODEID").toString();

				//如果是空值  则不处理
				if(cpNodeID == null||"".equals(cpNodeID)){
					response.sendRedirect("../route/error.html"); 
				}
				
			}
			
			String sql2 = "select * from lcp_master_node a where a.cp_node_id = (select max(cp_node_id) from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"') "+
			"and a.cp_id = (select distinct cp_id from lcp_patient_order_point t where t.patient_no = '"+patientId1+"' and t.hospital_id = '"+HOSPITALID+"')";
			
			//查出对应的node表的数据，取到路径ID，医嘱对应节点号，名字
			DataSetClass ds5=db.FunGetDataSetBySQL(sql2);
	   	   
				//取出路径名字
				cpName = db.FunGetDataSetBySQL("select CP_NAME from lcp_master  where CP_ID = "+cpID).FunGetDataAsStringById(0,0).toString();
				cpNodeID=ds5.FunGetDataAsStringByColName(0,"CP_NODE_ID");
				//取出路径节点名字
				cpNodeName=ds5.FunGetDataAsStringByColName(0,"CP_NODE_NAME");
				
				//把point表内容从这取出
/* 				 String sql3="select * from lcp_patient_order_point t "
						+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = t.cp_node_order_id order by CP_NODE_ORDER_ID"; */
						String sql3="select t.*,t1.cp_node_class_id  from lcp_patient_order_point t,lcp_node_order_point t1 where t.SYS_IS_DEL = 0   and t.cp_id = "+cpID+"   and t.cp_node_id = "+cpNodeID+"   and t.HOSPITAL_ID = "+HOSPITALID+"   and t.PATIENT_NO = '"+patientId1+"'   and t.continue_order_id = t.cp_node_order_id   and t1.cp_id="+cpID+"   and t1.cp_node_id="+cpNodeID+"   and t1.cp_node_order_id=t.continue_order_id  order by t1.cp_node_class_id";
						ds=db.FunGetDataSetBySQL(sql3);//取出一级菜单医嘱项
					
				       //组出当前节点下所有二级的ID 放入list中备用
					//把point表内容从这取出
					 String conSql="select * from lcp_patient_order_point t "
							+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";
						DataSetClass dsc8=db.FunGetDataSetBySQL(conSql);
						int rowTwo = dsc8.FunGetRowCount();
						for(int x=0; x<rowTwo; x++){
							list.add(dsc8.FunGetDataAsStringByColName(x,"CP_NODE_ORDER_ID").trim());
						}
				
			}
			
		
		
			
			
			
			
			
			
		
	

	    
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<link rel="stylesheet" href="../public/plugins/jquery/themes/cupertino/jquery-ui-1.8.11.custom.css">
	<link rel="stylesheet"
	href="../public/plugins/jquery/themes/base/jquery.ui.tabs.css">
	<script src="../public/plugins/jquery/jquery-1.5.1.min.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.core.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.tabs.js"></script>
	<script src="../public/plugins/jquery/external/jquery.bgiframe-2.1.2.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.widget.js"></script>

	<script src="../public/plugins/jquery/ui/jquery.ui.mouse.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.button.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.draggable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.position.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.resizable.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.ui.dialog.js"></script>
	<script src="../public/plugins/jquery/ui/jquery.effects.core.js"></script>

<title>临床路径医嘱下发页面</title>
<script type="text/javascript">
var highlightcolor='#d5f4fe';
var clickcolor='#51b2f6';
var recoveryColor='#FFFFFF';
var bgcolor="#51b2f6";
var tempColor="";
var orderIds = "";
var schemaOperation = "";
var replaceStatus = "close";
var oldComments = "";
var arrList = new Array();

function changeColor(event){
	tempColor=event.bgColor;
	event.bgColor=highlightcolor;
	}

function recoverColor(event){
	event.bgColor=tempColor;
	tempColor=recoveryColor;
	}

//双击模板医嘱
//修改：新增一级菜单显示功能
function ondblclickLoad(event){
	$("#div1").dialog("close");
	var tr=$(event);
	var data = tr.find("input#data").val();
	var   strArray=new   Array();
	strArray=data.split("/");
	//清空之前所选
 	$("#item").find("tr").each(function(){
 		var src = $(this).children().eq(7).html();
 		if(src != null && src !='选择' && src !=""){
 			$(this).children().eq(7).html("");
 			this.bgColor="#FFFFFF";
 		}
	});
 	//打开所选一二三级菜单
	for(var i =0; i<strArray.length; i++){
		strArray1 = strArray[i].split(",");
		$("#item").find("tr").each(function(){
			var str = strArray1[0]; 
			var orderId = $(this).children().eq(0).attr("orderid");
			var html = $(this).children().eq(0).html();
			if(str == orderId && html == '+'){
				lap(this);
			}else if(strArray1[1] == orderId && html == '+'){
				lap2(this,false);
				}
		});
	}
 	//确定所选三级菜单行数
	for(var i =0; i<strArray.length; i++){
		strArray1 = strArray[i].split(",");
		$("#item").find("tr").each(function(){
			var orderId = $(this).children().eq(0).attr("name");
			var orderItemId = $(this).children().eq(0).attr("id");
			if(strArray1[1] == orderId && strArray1[2] == orderItemId){
				changeSign(this);
				}
		});
	} 
}

//打开已经下发的医嘱列表
function openRecord(){
	window.open('record.jsp?patientNo=<%=patientId1%>');
}


//全选
function selectAll(event){
	
	$("#item").find("tr").each(function(){
		var nodeCode = $(this).children().eq(0).attr("id");
		
		if(nodeCode != ""){
		changeSign(this);
		}
	});	
}


//选中组
//修改人 林建喜  修改日期 2013-8-13 完成日期 2013-8-14 
//修改内容：成组医嘱选择处理，解决替代药品成组问题
function selectSet(event){
    var x = $(event).children().eq(0).html();
    var setIdnow = $(event).children().eq(8).attr("orderItemSetNo");
    var cpNodeOrderId = event.fristid;
    if(x == "◇"){
    	$(event).children().eq(0).html("◆");
    	changeSign(event);
    }else if(x == "◆"){
    	$(event).children().eq(0).html("◇");
    	changeSignNull(event);
    }
	var setIdOld;
	$("#item").find("tr[fristid = '"+cpNodeOrderId+"']").each(function(){
		var setId = $(this).children().eq(8).attr("orderItemSetNo");
		var itemId = $(this).children().eq(0).attr("id");
		var orderId = $(this).children().eq(0).attr("name");
        var y = $(this).children().eq(0).html();
		
        var setNo =  orderId + itemId ;
		setIdOld = setId;
		if(setId !=undefined){
			if(setId == setIdOld && x=="◇" && setId==setIdnow && y == "├"){
		           changeSign(this);
		     }else if(setId == setIdOld && x=="◆" && setId==setIdnow){
		    	 if(y == "◆"){
		    		$(this).children().eq(0).html("◇");
		    	 }
		    	 changeSignNull(this);
	       }
			
		}
	});	
}
//选中非组
//修改人 林建喜  修改日期 2013-8-13 完成日期 2013-8-14 
//修改内容：成组医嘱选择处理，解决替代药品成组问题
  function selectSet1(event){
	  var setId = $(this).children().eq(8).attr("orderItemSetNo");
	    var x = $(event).children().eq(0).html();
	    var y = $(event).children().eq(7).html();
        if(x=="&nbsp;"){
    	    if(y!=""){
    	    	if(event.replaceFlag =='1'){
    	    		event.bgColor='#ffff00';//替代药品反选颜色变化	
    	    	}else{
    	    		event.bgColor='#ffffff';	
    	    	}
   	 		    $($(event).children().eq(7)).html("");
          
            }else if(setId==null || "".equals(setId)){
				event.bgColor=clickcolor;
        	    var html="<img src='../public/images/success.png' width='18' height='18'/>";
                $($(event).children().eq(7)).html(html);
            }
		}
	
  }

//选中所有未执行的项
function selectOthers(event){
	
	$("#item").find("tr").each(function(){
		var exestate1 = $(this).attr("exestate1");
		
		if(exestate1 == ""){
		changeSign(this);
		}
	});	
}

//全不选
function unSelectAll(event){

	$("#item").find("tr").each(function(){
		changeSignNull(this);
	});	
}

//跳转到医嘱系统时，只去掉打勾，而保留变色
function unSelectAll1(event){

	$("#item").find("tr").each(function(){
		changeSignNull1(this);
	});	
}
//默认医嘱按钮
function orderDefault(event){
	var op = event;
	$("#wait").css("display","");
	$.ajax({
		url : "../servlet/OrderItemServlet",
		type : 'POST',
		data : {
			op: op,
			patient_no:"<%=patientId1%>",
			cp_id : "<%=cpID%>",
			cp_node_id : "<%=cpNodeID%>"
		},
		dataType : "json", 
		complete : show_result,
		async:true,
		success : function(data, textStatus, XMLHttpRequest) { 
			$("#wait").css("display","none");
			data = eval(data);
			data = data[0];
			var rows = data.rows;
			if(rows == 0){
				alert(data.msg);
			}else if(rows > 0){
			orderIds = data.result;
			var strArray=new   Array();
			strArray=orderIds.split("/");
			//清空之前所选
		 	$("#item").find("tr").each(function(){
		 		var src = $(this).children().eq(7).html();
		 		if(src != null && src !='选择' && src !=""){
		 			$(this).children().eq(7).html("");
		 			this.bgColor="#FFFFFF";
		 		}
		 		var x = $(this).children().eq(0).html();
		 		var y = $(this).children().eq(0).attr("orderid");
		 		if(x == "-"){
		 			lap(this);
		 			}
			});
		 	//打开所选一二三级菜单
			for(var i =0; i<strArray.length; i++){
				strArray1 = strArray[i].split(",");
				$("#item").find("tr").each(function(){
					var str = strArray1[0]; 
					var orderId = $(this).children().eq(0).attr("orderid");
					var html = $(this).children().eq(0).html();
					if(str == orderId && html == '+'){
						lap(this);
					}else if(strArray1[1] == orderId && html == '+'){
						lap2(this,false);
						}
				});
			}
		 	//确定所选三级菜单项目
			for(var i =0; i<strArray.length; i++){
				strArray1 = strArray[i].split(",");
				$("#item").find("tr").each(function(){
					var orderId = $(this).children().eq(0).attr("name");
					var orderItemId = $(this).children().eq(0).attr("id");
					if(strArray1[1] == orderId && strArray1[2] == orderItemId){
						changeSign(this);
						}
				});
			} 
          }
		}
	});
}
//保存为模板按钮
function saveSchema(){
	orderIds = "";
	$("#item").find("tr").each(function(){
		var html=$(this).children().eq(7).html();
		if(html !="" && html != "选择" && html != null){
			orderIds +=  $(this).children().eq(0).attr("name") + "," ; 	
			orderIds +=  $(this).children().eq(0).attr("id") + "/";
			
		}
		
	});
	orderIds=orderIds.substr(0,orderIds.length-1);
	if(orderIds === "" || orderIds == null){
		alert("模板数据为空，请重新选择...");
	}else{
		$("#div").dialog("open");
	}
	

}

//更新模板按钮
function saveAsSchema(){
	if(schemaOperation === ""){
		alert("您未选择编辑医嘱模板，点击此按钮无效");
	}else if(schemaOperation === "editSchema"){
		orderIds = "";
		$("#item").find("tr").each(function(){
			var html=$(this).children().eq(7).html();
			if(html !="" && html != "选择" && html != null){
				orderIds +=  $(this).children().eq(0).attr("name") + "," ; 	
				orderIds +=  $(this).children().eq(0).attr("id") + "/";
				
			}
			
		});
		orderIds=orderIds.substr(0,orderIds.length-1);
		if(orderIds === "" || orderIds == null){
			alert("模板数据为空，请重新选择...");
		}else{
			$("#saveAs").dialog("open");
		}
    }
}

//选择方案按钮
function selectSchema(){
	$("#tablePersonalSchema").load("../servlet/NodeUpdate",{op:"getSchema",flag:"0",cpId:"<%=cpID%>",cpNodeId:"<%=cpNodeID%>",doctorNo:"<%=doctorNo%>"}); 
	$("#tableOthersSchema").load("../servlet/NodeUpdate",{op:"getSchema",flag:"1",cpId:"<%=cpID%>",cpNodeId:"<%=cpNodeID%>",doctorNo:"<%=doctorNo%>"}); 
	$("#div1").tabs();
	$("#div1").dialog("open");
}
//展开/关闭全部二级菜单
function lap1(){
	$("tr[id^='node-']").each(function(){
		var x = $(this).children().eq(0).html();
		var y = $(this).children().eq(0).attr("orderid");
		var trs2=$("tr[name^='Con_"+y+"']");
		if(x === "+"){		
			trs2.show();
			$(this).children().eq(0).html("-");
		}else if(x === "-"){
			trs2.hide();
			$(this).children().eq(0).html("+");
		}
	});
    
}

//展开替代药品菜单
function lapReplace(event){
	var cpNodeOrderId = $(event).attr("name");
	var cpNodeOrderItemId = $(event).attr("id");
	var trs3 = $("tr[name = '"+cpNodeOrderId+"_"+cpNodeOrderItemId+"'][replaceFlag = '1']");
	if(replaceStatus === "close"){
		trs3.show();
		replaceStatus = "open";
	}else if(replaceStatus === "open"){
		trs3.hide();
		replaceStatus = "close";
	}
}

//折叠操作(第一级菜单专用)
var cc=0;
var dd=0;
var ll=new Array();
var temp=0;
var ss=new Array();
function lap(event){
	document.all.item.style.cursor="pointer";
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");
	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs2=$("tr[name^='Con_"+y+"']");

		trs2.hide();
		for(var n in ll){
			if(ll[n].indexOf(y)==0){
				$(trs2.children().eq(0)).html("+");
				temp=ll[n].substr(ll[n].indexOf("_")+1,ll[n].length-((ll[n].substr(0,ll[n].indexOf("_"))).length+1));
				ss.push(temp);
				//alert("========================:"+temp);
				$("#"+temp).hide();
				//trs1.hide();
				//alert("0000000000::===:"+ll[n].substring(3,2));
			}
		}
	}else{
		cc=y;
		$($(event).children().eq(0)).html("-");
		var trs2=$("tr[name^='Con_"+y+"']");
		trs2.show();
	}	
}

//折叠子项(第二级菜单用)
var temp=0;
var b=0;
var list=new Array();
function lap2(event,isTrue){
	var x = $(event).children().eq(0).html();
	var y = $(event).children().eq(0).attr("orderid");
	if(x == "-"){
		$($(event).children().eq(0)).html("+");
		var trs1=$("tr[name^='"+ y +"_']");
		trs1.hide();
	}else{
		document.all.item.style.cursor="wait";
		var i;
		for(i=0;i<list.length;i++){
			if(list[i]==y){
				b=10000;
				break;
			}else{
				b=0;
			}
		}
		for(var m in ss){
			if(ss[m]==y){
				var trs1=$("tr[name^='"+ y +"_']");
				$("#"+y).show();
			}
		}
		if(b!=10000){
			var op ="getLapItems";
			$.ajax({
				url : "../servlet/OrderItemServlet",
				type : 'POST',
				data : {
					op: op,
					patient_no:"<%=patientId1%>",
					cp_id : "<%=cpID%>",
					cp_node_id : "<%=cpNodeID%>",
					cp_node_order_id : y
				},
				dataType : "json",
				complete : show_result,
				async: isTrue,
				success : function(data, textStatus, XMLHttpRequest) {
					data = eval(data);
					data = data[0];
					$("#"+y).html(data.result);
					$($(event).children().eq(0)).html("-");
					var trs1=$("tr[name^='"+ y +"_']");
					var trs3=$("tr[bgcolor='#FFFF00']");
					trs1.show();
					trs3.hide();
					//style="font-size:14px; cursor:pointer;"
					document.all.item.style.cursor="pointer";
				}
			});
			list.push(y);
		}else if(b==10000){
			$($(event).children().eq(0)).html("-");
			var trs1=$("tr[name^='"+ y +"_']");
			var trs3=$("tr[bgcolor='#FFFF00']");
			trs1.show();
			trs3.hide();
			document.all.item.style.cursor="pointer";
		}
		dd=cc+"_"+y;
		ll.push(dd);
	}
}


//把焦点转移到底部
function move(){	 
	commit.focus();
 }


//保存医嘱模板
function save1(type){
	var comments = "";	
	msg=$("#comment").val();
    if(msg == null || msg === ""){
    	alert("模板名称为空，请重新输入...");
    }else{
    	$.ajax({
	   url: "../servlet/NodeUpdate",
	   type: "POST",
	   async: false,
	   data: {op: "saveSchema",
		   patientNo:"<%=patientId1%>",
		   cpId:"<%=cpID%>",
		   cpNodeId:"<%=cpNodeID%>",
		   doctorNo:"<%=doctorNo%>",
		   orderIds: orderIds,
		   comments: msg,	
		   typeName: type
 	      },
	   dataType: "json",
	   complete: show_result,
	   success: function(data, textStatus, XMLHttpRequest){
		   data = eval(data);
		   data = data[0];
     	   if(data.flag === "1"){
			   alert("保存成功");
			   schemaOperation = "";
			   $("#comment").val("");
			   $("#div").dialog( "close" );
			   $("#updateButton").attr("disabled","disabled");
		   }else if(data.flag === "0"){
			   alert("保存失败，请重新保存...");
		   }else if(data.flag === "2"){
			   alert("您已经保存有一个同名的模板，请重新命名");
		   }
	   }
     });
  }
	
}
//更新模板
function saveAs(type){
	var comments = "";
	msg=$("#commentSaveAs").val();
	   if(msg == null || msg === ""){
	    	alert("模板名称为空，请重新输入...");
	   }else{
	    $.ajax({
		  url: "../servlet/NodeUpdate",
		  type: "POST",
		  async: false,
		  data: {op: "editSchema",
			   patientNo:"<%=patientId1%>",
			   cpId:"<%=cpID%>",
			   cpNodeId:"<%=cpNodeID%>",
			   doctorNo:"<%=doctorNo%>",
			   orderIds: orderIds,
			   comments: msg,	
			   typeName: type,
			   oldComments: oldComments
	 	      },
		  dataType: "json",
		  complete: show_result,
		  success: function(data, textStatus, XMLHttpRequest){
			   data = eval(data);
			   data = data[0];
	     	   if(data.flag === "1"){
				   alert("保存成功");
				   schemaOperation = "";
				   $("#saveAs").dialog( "close" );
				   $("#updateButton").attr("disabled","disabled");
			   }else if(data.flag === "0"){
				   alert("保存失败，请重新保存...");
			   }else if(data.flag === "2"){
				   alert("您已经保存有一个同名的模板，请重新命名");
			   }
		   }
	     });
	  }
	
}
//编辑医嘱模板
function editSchema(){
	var countSchema = 0;
	var schemaId = "";
	var belongs = "";
	$("#div1").find("tr").each(function(){
		var isSelect =  $(this).children().eq(5).html();
		if (isSelect != "" && isSelect != null && isSelect !="选择"){
			countSchema++;
			var belong = $(this).children().eq(0).attr("belong");
			belongs = belongs + belong;
			schemaId += $(this).children().eq(0).attr("id");
		}
	});
	if(countSchema === 0){
		alert("您未选择任何模板进行编辑，请重新选择...");
	}else if(belongs.indexOf("othersSchema")>= 0){
		alert("您选择编辑的模板包含他人模板，请重新选择...");
	}else if(belongs.indexOf("othersSchema") < 0){
		if(countSchema >1){
			alert("同一时刻您只能选择一个模板进行编辑，请重新选择...");
		}else if(countSchema === 1){
		 if(confirm("确定要编辑吗？")){
	      $("#personalSchema").find("tr#"+schemaId+"").each(function(){
		   var isSelect =  $(this).children().eq(5).html();
			if (isSelect != "" && isSelect != null && isSelect !="选择"){
                   var data = $(this).find("input#data").val();
				   oldComments = $(this).children().eq(1).attr("comments");
				   var   strArray=new   Array();
				   strArray=data.split("/");
				   $("#item").find("tr").each(function(){
				 		var src = $(this).children().eq(7).html();
				 		if(src != null && src !='选择' && src !=""){
				 			$(this).children().eq(7).html("");
				 			this.bgColor="#FFFFFF";
				 		}
					});
				 	//打开所选一二三级菜单
					for(var i =0; i<strArray.length; i++){
						strArray1 = strArray[i].split(",");
						$("#item").find("tr").each(function(){
							var str = strArray1[0]; 
							var orderId = $(this).children().eq(0).attr("orderid");
							var html = $(this).children().eq(0).html();
							if(str == orderId && html == '+'){
								lap(this);
							}else if(strArray1[1] == orderId && html == '+'){
								lap2(this,false);
								}
						});
					}
				 	//确定所选三级菜单行数
					for(var i =0; i<strArray.length; i++){
						strArray1 = strArray[i].split(",");
						$("#item").find("tr").each(function(){
							var orderId = $(this).children().eq(0).attr("name");
							var orderItemId = $(this).children().eq(0).attr("id");
							if(strArray1[1] == orderId && strArray1[2] == orderItemId){
								changeSign(this);
								}
						});
					
			  }
		   }
	    });
	     schemaOperation = "editSchema";
	     $("#updateButton").removeAttr("disabled");
	     $("#div1").dialog("close");
	     $("#commentSaveAs").val(oldComments);
	   }
	 }
		
	}
	
	
}
//删除医嘱模板
function delSchema(){
	var schema_ids = "0";
	var belongs = "";
	$("#div1").find("tr").each(function(){
		var isSelect =  $(this).children().eq(5).html();
		var belong = $(this).children().eq(0).attr("belong");
		
	 if (isSelect != "" && isSelect != null && isSelect !="选择"){
		    var belong = $(this).children().eq(0).attr("belong");
		    var schema_id = $(this).children().eq(0).attr("id");
			belongs = belongs + "," + belong;
		    schema_ids = schema_ids + "," +schema_id;
		}
	});
    if(belongs.indexOf("othersSchema")>0){
    	alert("您要删除的模板中包含他人模板，请重新选择...");
    }else{
    	if(schema_ids === "0"){
    		alert("您未选择要删除的模板，请重新选择...");
    	}else{
    	if(confirm("您确定要删除吗？")){
    	$.ajax({
    	   url: "../servlet/NodeUpdate",
    	   type: "POST",
    	   async: false,
    	   data: {op:"delSchema",
    		   patientNo:"<%=patientId1%>",
    		   cpId:"<%=cpID%>",
    		   cpNodeId:"<%=cpNodeID%>",
    		   doctorNo:"<%=doctorNo%>",
    		   schemaIds: schema_ids
     	},
    	   dataType: "json",
    	   complete: show_result,
    	   success: function(data, textStatus, XMLHttpRequest){
    		   data = eval(data);
    		   data = data[0];
    		   if(data.flag === "1"){
    			   alert("已成功删除"+data.count+"条模板数据");
    			   $("#tablePersonalSchema").html(data.table);
    		   }else if(flag === "0"){
    			   alert("删除失败，请重新删除...");
    		   }
    	   }
        });
      }
     }
    }
	
}

//执行到下一节点
<%-- function toNext(){
	$.ajax({
	   url: "../servlet/NextOrder",
	   type: "POST",
	   async: false,
	   data: {op:"toNextOrder",
		   cpId:"<%=cpID%>",
		   cpNodeId:"<%=nextNodeId%>",
		   patientId:"<%=patientId1%>"		   
		   
 	},
	   dataType: "json",
	   complete: show_result1,
	   success: function(data, textStatus, XMLHttpRequest){
		   if(data[0].result === "OK"){
			   //window.location.href = "http://www.baidu.com";
			   var url = "../service/order.jsp?patient_no="+<%=patientID%>+"&admissTimes="+<%=admissTimes%>+"&doctorNo="+<%=doctorNo%>+"&patientArea="+<%=patientArea%>+"&doctorQX="+<%=doctorQX%>;
			   window.location.href=url;

		   }
		   if(data[0] === "fail"){
			   alert("执行失败，请与管理员联系！");
		   }		   
	   }
 });
} --%>

//点击下一节点时候的操作
/* function gotoNext(){
	if(confirm("确定要执行到下一节点的医嘱吗?")) 
	{ 
	    toNext();
	} 
	 
}*/






function NodeColor(event){
	var a=event.bgColor;
	event.bgColor=(tempColor==='#51b2f6'?recoveryColor:clickcolor);
	tempColor=event.bgColor;
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	
	var check=(tempColor==='#51b2f6'?html:"");
	$($(event).children().eq(7)).html(check);
	
}

function NodeColor1(event){
	var a=event.bgColor;
	event.bgColor=(tempColor==='#51b2f6'?recoveryColor:clickcolor);
	tempColor=event.bgColor;
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	
	var check=(tempColor==='#51b2f6'?html:"");
	$($(event).children().eq(5)).html(check);
	
}

function changeSign(event){
	if(event.bgColor == '#aaaaaa'){
		
	}else{
	 event.bgColor=clickcolor;
	 var htmlHead = $(event).children().eq(0).html();
	 if(htmlHead == '◇'){
		 $(event).children().eq(0).html("◆");
	 }
	 var html="<img src='../public/images/success.png' width='18' height='18'/>";
     $($(event).children().eq(7)).html(html);
	}
}

function changeSignNull(event){

	var x = $(event).children().eq(0).html();
	if(x == "-" || x == "+"){		
		
	}else if(event.bgColor == '#aaaaaa'){
	}else{
	if(event.replaceFlag === '1'){
		event.bgColor='#ffff00';//替代药品反选颜色变化
	}else if(event.bgColor == clickcolor){
		event.bgColor="#ffffff";
	}
		$($(event).children().eq(7)).html("");	
	}
}

function changeSignNull1(event){

	var x = $(event).children().eq(0).html();
	
	if(x == "-" || x == "+"){		
		
	}else if(event.bgColor == '#aaaaaa'){
	}else{
		$($(event).children().eq(7)).html("");	
	}
}

function NodeColorOne(event){
	var html="<img src='../public/images/success.png' width='18' height='18'/>";
	var check=(tempColor===clickcolor?"":html);
	var name=event.id.split("_")[0];
	var trs=$("tr[id^="+name+"]");
	var trsleng=trs.length;
	for(var i=0;i<trsleng;i++){
		var tr=trs[i];
		tr.bgColor=recoveryColor;
		$($(tr).children().eq(7)).html("");
	}
	$($(event).children().eq(7)).html(check);
	event.bgColor=(tempColor===clickcolor?recoveryColor:clickcolor);
	tempColor=event.bgColor;
}

jQuery.fn.Scrollable = function(tableHeight, tableWidth) {
	this.each(function(){
		if (jQuery.browser.msie || jQuery.browser.mozilla) {
			// var table = new ScrollableTable(this, tableHeight, tableWidth);
		}
	});
};



var cpID = "";

var lastState = "";
$(document).ready(function(){
	
	arrList = "<%=list%>".replace('[','').replace(']','').split(', ');
	for (var x in arrList){
        var trs1=$("tr[name^='"+arrList[x]+"_']");
		trs1.hide();
		
		var trs2=$("tr[name^='Con_']");
		trs2.hide();
        
    }





	jQuery('table').Scrollable(550, 900);
	//保存方案时的DIV
	$("#div").dialog({
		title:"请填写模板名称",
		autoOpen: false,
		height: 280,
		width: 370,
		modal: true,
		buttons: {
			"保存其他": function() {
				save1("其他");
			},
			"保存检验": function() {
				save1("检验");
			},
			"保存检查": function() {
				save1("检查");
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
		}
	});
   //更新方案时的DIV
   $("#saveAs").dialog({
		title:"请填写新模板名称",
		autoOpen: false,
		height: 280,
		width: 370,
		modal: true,
		buttons: {
			"更新其他": function() {
				saveAs("其他");
			},
			"更新检验": function() {
				saveAs("检验");
			},
			"更新检查": function() {
				saveAs("检查");
			},
			"取消":function(){
				("#saveAs").dialog("close");
			}
		},
		close: function() {
		}
	});
	//选择方案时的DIV
$("#div1").dialog({
		
		title:"方案列表",
		autoOpen: false,
		modal: true,
		height:400,
		width:600,
		buttons: {
			"删除": function() {
				delSchema();
			},
			"编辑": function() {
				editSchema();
			}
		}
	});
	
	$("#sub").click(function(){
	checkTrs="";
	var checkTrs1 = "";
	var checkTrs2 = "";
	var checkTrs3 = "";
	var order = "";
	var jianyan = "";
	var jiancha = "";
	var other = "";
	var jsons = "";
	$("#item").find("tr").each(function(){
		var html=$(this).children().eq(7).html();
		var typeName = $(this).children().eq(3).html();
		
		//判断本次操作里发生变化的所有值
		if(html !="" && html != null){
			var headhtml = $(this).children().eq(0).html();//标示获取
			var orderName=$(this).children().eq(4).html().replace( /^\s*/, '');
			var mark = $(this).children().eq(12).html().replace( /^\s*/, '');
			var orderKind = $(this).children().eq(2).html();
			var orderNo = $(this).children().eq(5).attr("orderNo");
			var discription;
			if(orderNo == "O4476" || orderNo == "O0996" || orderNo == "O0997" || orderNo == "O0998" || orderNo == "O0999" 
					|| orderNo == "O3823" || orderNo == "O3824" || orderNo == "O3825" || orderNo == "O3826" || orderNo == "O3827" 
					|| orderNo == "O3828" || orderNo == "O3829" || orderNo == "O3830" || orderNo == "O3831" || orderNo == "O3832" 
					|| orderNo == "O3833"){
				discription = mark;
			}else{
				discription = "";
			}
			if(headhtml == "◆" || headhtml == "├" || headhtml == "&nbsp;"){
			if(typeName == "检验" ){
				if(orderKind == "长期+临时"){
					checkTrs3+="{";
					checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
					checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
					checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
					checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
					checkTrs3+="'mark':'" +mark+"',";
					checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype1")+"',";
					checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
					checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
					checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
					checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
					checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency1")+"',";
					checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
					checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
					checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
					checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype1")+"',";
					checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
					checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
					checkTrs3+="{";
					checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
					checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
					checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
					checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
					checkTrs3+="'mark':'" +mark+"',";
					checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype2")+"',";
					checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
					checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
					checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
					checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
					checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency2")+"',";
					checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
					checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
					checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
					checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype2")+"',";
					checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
					checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
			}else{
				checkTrs1+="{";
				checkTrs1+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs1+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs1+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
			    checkTrs1+="'orderName':'"+orderName.replace('$','')+"',";
				checkTrs1+="'mark':'" +mark+"',";
				checkTrs1+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
				checkTrs1+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs1+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
				checkTrs1+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
				checkTrs+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs1+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs1+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
				checkTrs1+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs1+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs1+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs1+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
				
			}

			}else if(typeName == "检查"){
				if(orderKind == "长期+临时"){
					checkTrs3+="{";
					checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
					checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
					checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
					checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
					checkTrs3+="'mark':'" +mark+"',";
					checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype1")+"',";
					checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
					checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
					checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
					checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
					checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency1")+"',";
					checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
					checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
					checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
					checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype1")+"',";
					checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
					checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
					checkTrs3+="{";
					checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
					checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
					checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
					checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
					checkTrs3+="'mark':'" +mark+"',";
					checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype2")+"',";
					checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
					checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
					checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
					checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
					checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency2")+"',";
					checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
					checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
					checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
					checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype2")+"',";
					checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
					checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
			}else{
				checkTrs2+="{";
				checkTrs2+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
				checkTrs2+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
				checkTrs2+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
				checkTrs2+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
				checkTrs2+="'mark':'" +mark+"',";
				checkTrs2+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
				checkTrs2+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
				checkTrs2+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
				checkTrs2+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
				checkTrs2+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
				checkTrs2+="'way':'" +$(this).children().eq(10).attr("way")+"',";
				checkTrs2+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
				checkTrs2+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
				checkTrs2+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
				checkTrs2+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
				checkTrs2+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
			}

			}else if(typeName != "类别"){
					if(orderKind == "长期+临时"){
							checkTrs3+="{";
							checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
							checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
							checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
							checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
							checkTrs3+="'mark':'" +mark+"',";
							checkTrs3+="'discription':'" +discription+"',";//加入描述
							checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype1")+"',";
							checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
							checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
							checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
							checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
							checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency1")+"',";
							checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
							checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
							checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
							checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype1")+"',";
							checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
							checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
							checkTrs3+="{";
							checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
							checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
							checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
							checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
							checkTrs3+="'mark':'" +mark+"',";
							checkTrs3+="'discription':'" +discription+"',";//加入描述
							checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype2")+"',";
							checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
							checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
							checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
							checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
							checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency2")+"',";
							checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
							checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
							checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
							checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype2")+"',";
							checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
							checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
					}else{
						checkTrs3+="{";
						checkTrs3+="'cpNodeOrderId':'"+$(this).children().eq(0).attr("name")+"',";
						checkTrs3+="'cpNodeItemId':'"+$(this).children().eq(0).attr("id")+"',";
						checkTrs3+="'orderTypeName':'"+$(this).children().eq(2).html()+"',";
						checkTrs3+="'orderName':'"+$(this).children().eq(4).html().replace('$','')+"',";
						checkTrs3+="'mark':'" +mark+"',";
						checkTrs3+="'discription':'" +discription+"',";//加入描述
						checkTrs3+="'orderKind':'"+$(this).children().eq(10).attr("advicetype")+"',";
						checkTrs3+="'stateItem':'"+$(this).children().eq(5).attr("orderNo")+"',";
						checkTrs3+="'needItem':'"+$(this).children().eq(6).attr("need")+"',";
						checkTrs3+="'measure':'"+$(this).children().eq(8).attr("measure")+"',";
						checkTrs3+="'measureUnits':'"+$(this).children().eq(8).attr("measureUnits")+"',";
						checkTrs3+="'frequency':'"+$(this).children().eq(9).attr("frequency")+"',";
						checkTrs3+="'way':'" +$(this).children().eq(10).attr("way")+"',";
						checkTrs3+="'dosage':'" +$(this).children().eq(11).attr("dosage")+"',";
						checkTrs3+="'dosageUnits':'" +$(this).children().eq(12).attr("dosageUnits")+"',";
						checkTrs3+="'advicetype':'"+$(this).children().eq(10).attr("advicetype")+"',";
						checkTrs3+="'unitId':'" +$(this).children().eq(11).attr("unitId")+"',";
						checkTrs3+="'orderItemSetNo':'" +$(this).children().eq(8).attr("orderItemSetNo")+"'},";
					}
					
				}
			}
		}	
		
	});	
	
	checkTrs1=checkTrs1.substr(0,checkTrs1.length-1);

	checkTrs2=checkTrs2.substr(0,checkTrs2.length-1);

	checkTrs3=checkTrs3.substr(0,checkTrs3.length-1);

	if(checkTrs1 != ""){
		jianyan = "'inspect':["+checkTrs1+"],";
	}
	if(checkTrs2 != ""){
		jiancha = "'check':["+checkTrs2+"],";
	}
	if(checkTrs3 != ""){
		other = "'advice':["+checkTrs3+"],";
	}
	order = jianyan + jiancha + other;
	
	order = order.substr(0,order.length-1); 
	if(order == ""){
		jsons = "{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>','babyStatus':'<%=babyStatus%>'}";
	}else{
		jsons="{'inpatientNo':'<%=patientID%>','cpId':'<%=cpID%>','cpNodeId':'<%=cpNodeID%>','admissTimes':'<%=admissTimes%>','doctorNo':'<%=doctorNo%>','patientArea':'<%=patientArea%>','doctorQX':'<%=doctorQX%>','babyStatus':'<%=babyStatus%>',"+ order +"}";
	}
	json = jsons;
	//alert(json);
	<%
	 java.net.URLEncoder.encode(json,"GBK");
	%>
	//往后台提交选中状态
//	$.ajax({
//		   url: "../servlet/NodeUpdate",
//		   type: "POST",
//		   async: false,
//		   data: {op:"updateOrder",
//			   cpId:"<%=cpID%>",
//			   nodeId:"<%=cpNodeID%>",
//			   orderIds: orderIds,
//			   patientNo:"<%=patientId1%>"
//	    	},
//		   dataType: "json",
//		   complete: show_result,
//		   success: function(data, textStatus, XMLHttpRequest){
//		   }
//	 });


//中山医院json格式[{患者编号:123},{cpID:1},{cpNodeID:2},{项目:[{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置},{项目编号:aaaa,频次:bbbb,计量:cccc,项目类型:检查|检验|护理|处置}]}]
  	//document.write("<form id='post1' name='post1' action='<%=orderAddress%>' method='POST'>"); 
 	//document.write('<input type="hidden" name="order" value="'+json+'" />');
 	//document.write('</form>');  
 	//document.getElementById('post1').submit();
	var patientArea="<%=patientArea%>";
	//老年科跳转到金榜服务器的医嘱系统http://192.1.33.62:8081/EMRClinicPath/clinicPathAdvice.do
	if(patientArea == "1060000" || patientArea =="1060002"|| patientArea =="1060001" || patientArea =="1060003"){
		openPostWindow('<%=orderAddressLN%>',json,'order');
	}
	//干部保健一病区和二病区需跳转到的服务器的医嘱系统
	else if(patientArea == "1120001" || patientArea =="1120002"){
		openPostWindow('<%=orderAddressGB%>',json,'order');
	}//干部保健一病区和二病区需跳转到的服务器的医嘱系统
	else if(patientArea == "1150000"){
		openPostWindow('<%=swOrderURLAddress2%>',json,'order');
	}
	else if(patientArea == "1010100" || patientArea =="1010103"){
		openPostWindow('<%=swOrderURLAddress2xhnk%>',json,'order');
	}//产科vip,妇产科，妇产科一病区，妇产科二病区
	else if(patientArea=="1040010" || patientArea=="1040011"|| patientArea=="1040000"|| patientArea=="1040012"){
		openPostWindow('<%=fcOrderLink2%>',json,'order');
	}
	else if(patientArea=="1031001" || patientArea=="1032001"|| patientArea=="1033001"){
		openPostWindow('<%=gkOrderLink2%>',json,'order');
	}else if(patientArea=="1090001"){
		openPostWindow('<%=ykOrderLink2%>',json,'order');
	}else if(patientArea=="1020300"){
		openPostWindow('<%=mnwkOrderLink2%>',json,'order');
	}else if(patientArea=="1050000"){
		openPostWindow('<%=ekOrderLink2%>',json,'order');
	}else if(patientArea=="1100000"){
		openPostWindow('<%=ebhkOrderLink2%>',json,'order');
	}else if(patientArea=="1020202" || patientArea=="1020200"){//1020202/1020200
		openPostWindow('<%=gdwkOrderLink2%>',json,'order');
	}else if(patientArea=="1010200" || patientArea=="1010202" || patientArea=="1010203"){//1010200/1010202/1010203
		openPostWindow('<%=hxnkOrderLink2%>',json,'order');
	}else if(patientArea=="1110100"){
		openPostWindow('<%=kqkOrderLink2%>',json,'order');
	}else if(patientArea=="1020103"){
		openPostWindow('<%=pwewbqOrderLink2%>',json,'order');
	}else if(patientArea=="1520001"){
		openPostWindow('<%=rxwkbqOrderLink2%>',json,'order');
	}else if(patientArea=="1140001"){
		openPostWindow('<%=sjnkOrderLink2%>',json,'order');
	}else if(patientArea=="1010300"){
		openPostWindow('<%=snkOrderLink2%>',json,'order');
	}else if(patientArea=="1260001"){
		openPostWindow('<%=wcwkOrderLink2%>',json,'order');
	}else if(patientArea=="1510000"){
		openPostWindow('<%=xewkbqOrderLink2%>',json,'order');
	}else if(patientArea=="1250010"){
		openPostWindow('<%=xgwkbqOrderLink2%>',json,'order');
	}else if(patientArea=="1020102"){
		openPostWindow('<%=ptwkebqOrderLink2%>',json,'order');
	}else if(patientArea=="2040000"){
		openPostWindow('<%=fskOrderLink2%>',json,'order');
	}else if(patientArea=="1220000"){
		openPostWindow('<%=nfmkOrderLink2%>',json,'order');
	}else if(patientArea=="1240000"){
		openPostWindow('<%=zlflkOrderLink2%>',json,'order');
	}else if(patientArea=="1080000"){
		openPostWindow('<%=zykOrderLink2%>',json,'order');
	}else if(patientArea=="1200000"){
		openPostWindow('<%=zlkOrderLink2%>',json,'order');
	}else if(patientArea=="1400400" || patientArea=="1400401"){
		openPostWindow('<%=zzyxkOrderLink2%>',json,'order');
	}else if(patientArea=="1210000"){
		openPostWindow('<%=xykOrderLink2%>',json,'order');
	}else if(patientArea=="1020400"){
		openPostWindow('<%=xwkOrderLink2%>',json,'order');
	}else if(patientArea=="1190004" || patientArea=="1190005" || patientArea=="1190000"){
		openPostWindow('<%=jzOrderLink2%>',json,'order');
	}else if(patientArea=="1230000"){
		openPostWindow('<%=zxmrkOrderLink2%>',json,'order');
	}else{
		openPostWindow('<%=orderAddress%>',json,'order'); 
		
	}

	unSelectAll1(this);
	
	});
});

function openPostWindow(url, data, name){  	
	
	var tempForm = document.createElement("form");  
	tempForm.id="tempForm1";  
	tempForm.method="post";  
	tempForm.action=url;  
	tempForm.target=name;  
	    
	var hideInput = document.createElement("input");  
	hideInput.type="hidden";  
	hideInput.name= "order";
	hideInput.value= data;
	tempForm.appendChild(hideInput);   
	tempForm.attachEvent("onsubmit",function(){ openWindow(name); });
	document.body.appendChild(tempForm);  
	    
	tempForm.fireEvent("onsubmit");
	tempForm.submit();
	document.body.removeChild(tempForm);
}


function openWindow(name)  
      {  
         window.open('about:blank',name,'height=768, width=1024, top=0, left=0, toolbar=yes, menubar=yes, scrollbars=yes, resizable=yes,location=yes, status=yes');   
      } 

jQuery.fn.Scrollable = function(tableHeight, tableWidth) {
	this.each(function(){
		if (jQuery.browser.msie || jQuery.browser.mozilla) {
			// var table = new ScrollableTable(this, tableHeight, tableWidth);
		}
	});
};


var show_result = function(XMLHttpRequest, textStatus){
 	var msg = "";
 	if(textStatus == "error"){
 	 	msg = "请求出错！";
 	}
 	else if(textStatus == "timeout"){
 	 msg = "请求超时！";
 	}
 	else if(textStatus == "parsererror"){
 	 msg = "JSON数据格式有误！";
 	}
 	if (textStatus != "success"){
 		ajax=false;

 	}
   
};

var show_result1 = function(XMLHttpRequest, textStatus){
 	var msg = "";
 	if(textStatus == "error"){
 	 	msg = "请求出错！";
 	}
 	else if(textStatus == "timeout"){
 	 msg = "请求超时！";
 	}
 	else if(textStatus == "parsererror"){
 	 msg = "JSON数据格式有误！";
 	}
 	if (textStatus != "success"){
 		ajax=false;

 	}
   
};

</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	
	}
#tabs { height: 200px; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 650px; overflow: auto;} 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0em 0em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -0px !important; margin-bottom: 0px !important; border-top: none; border-bottom-width: 0px; }
	.ui-tabs-selected { margin-top: -3px !important; }
.STYLE10 {color: #000000; font-size: 12px;font-weight:normal; }
.shubiao {color: #000000; font-size: 12px;font-weight:normal; }
-->
</style>

</head>

<body >
<h1 align="center" style="font-size:20px;"><%=cpName %>临床路径医嘱方案(<%=cpNodeName %>)</h1>
 <h2 align="center" style="font-size:18px; color:red;"><%=alertMsg %></h2>
<!--  <h4 align="center" style="font-size:14px; color:red;">说明：  如有颜色是 <font size="10" color="#AAAAAA">■</font> 的医嘱项是不能下发的，因为在医嘱系统里这些项是作废的或者库存为0，请大家总结并选择替代这些的医嘱项，整理好发给信息科，谢谢！</h4> -->
<!--  <h4 align="center" style="font-size:14px; color:red;">2. 医嘱下发时请不要把检验，检查，其他分类一起提交，医嘱系统中检验检查其他这三个分类是在三个不同界面里，混在一起提交的话会只能看到一个类别的医嘱而引起误会，希望大家认真按照规定执行。</h4> -->
<div style="font-size:14px;" align="center">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;患者编码:<strong><%=patientID%></strong>
 &nbsp;&nbsp;&nbsp;
 状态说明: 
 
 <img src="../public/images/detail_s3.png" height="16" width="16"/>已执行

&nbsp;&nbsp;&nbsp; 
 必选说明:
 <img src="../public/images/detail_s4.png" height="16" width="16"/>单项选一
  &nbsp;&nbsp;&nbsp; 
 <img src="../public/images/detail_s5.png" height="16" width="16"/>此项目必选,否则填变异
  &nbsp;&nbsp;&nbsp; 
  ★&nbsp;必选未下达的医嘱
  &nbsp;&nbsp;&nbsp;
  <font color="#AAAAAA">■</font>&nbsp;灰色不可用&nbsp;&nbsp;&nbsp;
  <font color="#FFFF00">■</font>&nbsp;黄色背景为替代药品项，多选一
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; 
<!--    <input type="button" id="sub" value="提交"/> -->
<br/><br/>

<input type="button"  onclick="selectSchema()"; value="选择模板"/> 
<input type="button"  onclick="openRecord()"; value="查看已下发医嘱"/> 
<input type="button"  onclick="move()"; value="跳至底部"/>  
<input type="button"  onclick="selectAll(this)"; value="全选"/>  
<input type="button"  onclick="unSelectAll()"; value="全不选"/>
<input type="button"  onclick="selectOthers()"; value="选中未执行项"/>
<input type="button"  onclick="lap1()"; value="展开/关闭二级菜单"/>
<br/>
</div>
<!-- style=" height:550px; width:80%;OVERFLOW-x:hide; overflow-y:auto"  -->
<div align="center" id="item">

    <table width="1120" id="main" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" class="frag-3" >
       <thead>
		<tr height="30" style=" font-size:14px;" bgcolor="d3eaef" >
		  <th width="20"  >&nbsp;</th>
		  <th width="40"  >状态</th>
		  <th width="80"  >类型</th>
		  <th width="80"  >类别</th>
		  <th width="240" >项目名称</th>
		  <th width="70"  >规格</th>
		  <th width="50"  >必选</th>
		  <th width="50" >选择</th>
		  <th width="100" >用量</th>
		  <th width="80" >频次</th>
		  <th width="80" >途径</th>
		  <th width="100" >药品一次使用剂量</th>
		  <th width="80" >医生嘱托</th>
		  <th width="80" >替代选择</th>		  
		  </tr>
        </thead>
        <tbody id="lcp_node_order_tbody">
       
    <% 
         
		 
          //String sql3 = "select distinct (t.cp_node_class_id) as AA,  (select a.order_type_name from lcp_local_order_type a where a.order_type_code = (t.cp_node_class_id)) as BB" +
                     //   " from lcp_patient_order_item t where t.patient_no = '"+patientId1+"' and t.cp_id = "+cpID+" and t.cp_node_id = "+cpNodeID+" order by t.cp_node_class_id";
    
 		  String itemSql="select * from lcp_patient_order_item "
 						+"where sys_is_del=0 and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and CP_NODE_ORDER_ID =";
 		  
 		 String itemSql2="select * from lcp_patient_order_item "
				+"where sys_is_del=0 and cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and CP_NODE_ORDER_ID in";

				try{ 
					if(ds.FunGetDataAsStringById(0,0)!=""){
						for (int i = 0; i < ds.FunGetRowCount(); i++) {
							String cpNodeOrderId=ds.FunGetDataAsStringByColName(i,"CP_NODE_ORDER_ID");
							String conId=ds.FunGetDataAsStringByColName(i,"CONTINUE_ORDER_ID");

							String cpNodeOrderText = ds.FunGetDataAsStringByColName(i, "CP_NODE_ORDER_TEXT");
							//String autoItem = ds.FunGetDataAsStringByColName(i, "AUTO_ITEM");
							String kind="";
							//String state=ds.FunGetDataAsStringByColName(i,"STATE_ITEM");
							//String exe_State=ds.FunGetDataAsStringByColName(i,"EXE_STATE");
							
							String _needItem=("");
							String checkType=("NodeColor(this)");
							String trName=("tr");
							String orderType="";
							 String needSql ="select cp_node_order_id from lcp_patient_order_point t "
									+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id"; 
							int needAndexeState1=db.FunGetRowCountBySql(itemSql2+"("+needSql+")"+" and need_item=1 and (exe_state is null or exe_state=0)");
							//医嘱细项必选未执行,一级菜单提示
 	     %>
 	     <%
         if(needAndexeState1 != 0){
             %>
         <tr id="node-<%= cpNodeOrderId%>"  height="25" bgcolor="#FFE4B5"    onclick="lap(this);">
           <td align="center" orderid="<%= cpNodeOrderId%>">+</td>
           <td align="center">★</td>
           <%}else{ %>
           <tr id="node-<%= cpNodeOrderId%>"  height="25" bgcolor="#FFE4B5"    onclick="lap(this);">
           <td align="center" orderid="<%= cpNodeOrderId%>">+</td>
           <td align="center"></td>
           <%} %>
           <td align="center"><span class="STYLE10"><%=orderType %></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;&nbsp;<%=cpNodeOrderText %></td>
           	<td align="left" class="STYLE10" bgcolor="#FFE4B5">&nbsp;</td>
           	<td align="center" class="STYLE10"><%=_needItem %></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
         <%
 			
         String continueSql ="select * from lcp_patient_order_point t "
				+  " where SYS_IS_DEL=0 and  cp_id="+cpID+" and cp_node_id="+cpNodeID+" and HOSPITAL_ID="+HOSPITALID+" and PATIENT_NO='"+patientId1+"' and t.continue_order_id = "+ conId+" and t.continue_order_id != t.cp_node_order_id order by CP_NODE_ORDER_ID";    
      	ds7 = db.FunGetDataSetBySQL(continueSql);
         	if(ds7.FunGetDataAsStringById(0,0)!=""){
				for (int k = 0; k < ds7.FunGetRowCount(); k++) {
					String cpNodeOrderId1=ds7.FunGetDataAsStringByColName(k,"CP_NODE_ORDER_ID");

					String cpNodeOrderText1 = ds7.FunGetDataAsStringByColName(k, "CP_NODE_ORDER_TEXT");
					int needAndexeState2=db.FunGetRowCountBySql(itemSql+cpNodeOrderId1+" and need_item=1 and (exe_state is null or exe_state=0)");      
					//医嘱细项未执行，二级菜单提示
         %>
         <%
         if(needAndexeState2 != 0){
         %>
         <tr  height="25" bgcolor="#7FFFD4"   name="<%="Con_"+cpNodeOrderId%>"  onclick="lap2(this,true);">
           <td align="center" orderid="<%= cpNodeOrderId1%>">+</td>
           <td align="center">★</td>
           <%}else if(needAndexeState2==0){ %>
            <tr  height="25" bgcolor="#7FFFD4"   name="<%="Con_"+cpNodeOrderId%>"  onclick="lap2(this,true);">
           <td align="center" orderid="<%= cpNodeOrderId1%>">+</td>
           <td align="center"></td>
           <%} %>
           <td align="center"><span class="STYLE10"><%=orderType %></span></td>
			<td align="center"></td>
           	<td align="left" class="STYLE10" bgcolor="#7FFFD4">&nbsp;&nbsp;<%=cpNodeOrderText1 %></td>
           	<td align="left" class="STYLE10" bgcolor="#7FFFD4">&nbsp;</td>
           	<td align="center" class="STYLE10"><%=_needItem %></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
          	<td  align="center" class="STYLE10"></td>
       	  </tr>
       	  <tbody id="<%=cpNodeOrderId1 %>"></tbody>
       	   <%}}}}}catch(Exception e){ System.out.println(e);
          	out.print("<script  type='text/javascript'> alert('该路径病人处于准人节点,请执行下一节点才可下医嘱')</script>");
          } %>
        </tbody>
      </table>
</div>
  <div id="commit" style="height:20px" align="center">
    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
  <input type="button" style="width: auto; height: auto; font-size: 15px" id="sub" value="提交"/>
  <input type="button" style="width: auto; height: auto; font-size: 15px" onclick="saveSchema()" value="保存模板"  />
  <input type="button" id="updateButton" style="width: auto; height: auto; font-size: 15px" onclick="saveAsSchema()" value="更新模板" disabled="disabled"/>
  <input type="button" style="width: auto; height: auto; font-size: 15px" onclick="orderDefault('getDefaultOrderItems')" value="默认医嘱项目"/>
  <input type="button" style="width: auto; height: auto; font-size: 15px" onclick="orderDefault('getDefaultInspectItems')" value="默认检验项目"/>
  <input type="button" style="width: auto; height: auto; font-size: 15px" onclick="orderDefault('getDefaultCheckItems')" value="默认检查项目"/>
<!--    <input type="button" style="width: auto; height: auto; font-size: 20px" onclick="gotoNext()" value="执行下一节点医嘱"  /> --> 
  
<br/>
</div>
<div id="div">
	<textarea id="comment" rows="10" cols="50"></textarea>

</div>
<div id="saveAs">
	<textarea id="commentSaveAs" rows="10" cols="50"></textarea>

</div>
<div id="div1">
    <table width="100%" style="margin-top: 0em;padding-top: 0em">
    	<tr>
    	  <td>
    	     <ul>
				 <li><a href="#personalSchema"><span style="font-size: 12px">个人模板</span></a></li>
				 <li><a href="#othersSchema"><span style="font-size: 12px">科室他人模板</span></a></li>
			 </ul>
			 
			 <div id="personalSchema"  style="height:auto;padding-top: 1.0em;padding-left:0.1em;padding-right: 0.1em " >
			     <table width="100%" id="users" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		    		<tr bgcolor="d3eaef" class="STYLE10" align="center">
				        <td width="10%" height="20" >模板编号</td>
				        <td width="40%" height="20" >模板名称</td>
				        <td width="10%" height="20" >模板类型</td>
				        <td width="10%" height="20" >所属医生</td>
				        <td width="15%" height="20" >模板更新时间</td>
				        <td width="10%" height="20" >选择</td>
		      		</tr>
		      		<tbody id="tablePersonalSchema">
		      		</tbody>
    	         </table>
    	     </div>
			 <div id="othersSchema"  style="height:auto;padding-top: 1.0em;padding-left:0.1em;padding-right: 0.1em " >
			     <table width="100%" id="users" class="users" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
		    		<tr bgcolor="d3eaef" class="STYLE10" align="center">
				        <td width="10%" height="20" >模板编号</td>
				        <td width="40%" height="20" >模板名称</td>
				        <td width="10%" height="20" >模板类型</td>
				        <td width="10%" height="20" >所属医生</td>
				        <td width="15%" height="20" >模板更新时间</td>
				        <td width="10%" height="20" >选择</td>
		      		</tr>
		      		<tbody id="tableOthersSchema">
		      		</tbody>
    	         </table>
		 	 </div>
		  </td>
	   </tr>
    </table>
</div> 

<div id="wait" style="display:none;width:295px;height:40px;position:absolute;top:50%;left:35%;padding:2px;overflow:hidden;">
	<span style="background:#FFFFFF;">数据正在加载中,请等待 ...</span>
	<img src='../public/images/loading.gif' width="300" height="15" /> 
	
</div>


</body>
</html>