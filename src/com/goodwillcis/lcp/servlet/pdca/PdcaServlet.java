package com.goodwillcis.lcp.servlet.pdca;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @author wuhailong
 * 
 */
public class PdcaServlet extends HttpServlet {
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:DCPLOCAL";
	public static final String DBUSER = "jhdcp";
	public static final String DBPASS = "jhdcp";
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	HttpServletResponse response;

	public PdcaServlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		char _strOP = request.getParameter("op").charAt(0);
		try {
			GeneralOperation(_strOP);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	public void GeneralOperation(char p_strOP) throws SQLException, ServletException, IOException {
		String _strJson = "";
		String _strCurrentCpId ="";
		switch (p_strOP) {
		case '0':
			GetVaNodeCount();
			break;
		case '1':
			GetCpVaInfo();
			break;
		case '2':
			GetCpcpInfo();
			break;
		case '3':
			GetCpVersions();		
			break;
		case '4':
			GetOrderSeqs();
			break;
		case '5':
			GetDiOrders();
			break;
		case '6':
		    _strCurrentCpId = request.getParameter("cp_id");
			InAndOutCompare iaoc = new InAndOutCompare();
			_strJson = iaoc.GetUsingCpInfo(_strCurrentCpId);
			break;
		case '7':
			 _strCurrentCpId = request.getParameter("cp_id");
			 CompareCpVersions ccv = new CompareCpVersions();
			 _strJson =ccv.GetTopFourCps(_strCurrentCpId);
			break;
		case '8':
			_strCurrentCpId = request.getParameter("cp_id");
			CpOrdersExcuteStatus coes = new CpOrdersExcuteStatus();
			_strJson =coes.GetOrderSeqsByCPId(_strCurrentCpId);
			break;
		case '9':
			_strCurrentCpId = request.getParameter("cp_id");
			CpNodeVaGraph cnvg = new CpNodeVaGraph();
			_strJson =cnvg.GetVaNodeCountByCpId(_strCurrentCpId);
			break;
		case 'A':
			GetSelectOrders();
			break;
		default:
		}
		ReturnJson(_strJson);
	}

	
	
	/**
	 * 获取需要删除的医嘱，然后进行下一步操作
	 * 生成新路径的流程是
	 * 1.克隆基准路径
	 * 2.删除标注医嘱
	 * 3.添加标注额外医嘱-未实现
	 * 2015-12-24
	 * 吴海龙 
	 * @throws SQLException 
	 */
	private void GetSelectOrders() throws SQLException {
		try {
			String _strSelectedOrders = request.getParameter("ordersstring");
			String _strCpId = request.getParameter("cp_id");
			if (null == _strCpId || "".equals(_strCpId)) {
				return;
			}
			NewCPObject np = new NewCPObject(_strCpId);
			np.CloneCpPath();
			System.out.println(_strSelectedOrders);
			String[] _arrayOrders = _strSelectedOrders.split("#");
			System.out.println("删除医嘱" + _arrayOrders.length + "条");
			for (int i = 0; i < _arrayOrders.length; i++) {
				String[] _arrayOrderItem = _arrayOrders[i].split(",");
				System.out.println(_arrayOrders[i]);
				if (_arrayOrderItem.length == 5) {
					np.ClearLcpNodeOrderItem(_arrayOrderItem[0],
							_arrayOrderItem[1], _arrayOrderItem[2],
							_arrayOrderItem[3], _arrayOrderItem[4]);
				}
			}
			String _strJson = np.GetClearedCp();
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * 跳转到pdca操作界面
	 * 2015-12-22
	 * 吴海龙 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void ForwardPdca() throws ServletException, IOException{
		String _strCpId = request.getParameter("cp_id");
		RequestDispatcher rd = request.getRequestDispatcher("/cppdca/index.jsp");
        String cp_id = _strCpId;
        request.setAttribute("cp_id",cp_id);//存值
        rd.forward(request,response);		
	}
	
	/**
	 * 跳转到临床路径医嘱执行统计界面
	 * 2015-12-12
	 * 吴海龙 
	 */
	public void GotoOrders() throws ServletException, IOException{
		request.getRequestDispatcher("/ordercp.jsp").forward(request,response);
	}
	
	
	/**
	 * 获取第一条对比第二条不同的医嘱
	 * 2015-12-17
	 * 吴海龙 
	 */
	public String GetDiOrdersByFlag(String p_strFlag,String p_strCpIdOne,String p_strCpIdTwo) {
		String _strCpIdOne = p_strCpIdOne;
		String _strCpIdTwo = p_strCpIdTwo;
		String _strFlag = p_strFlag;
		String _strJson ="";
		String _strSQL = " select a.cp_id,\r\n" + "        a.cp_node_id,\r\n"
				+ "        a.cp_node_name       node_name,\r\n"
				+ "        b.order_no,\r\n"
				+ "        b.cp_node_order_text order_text\r\n"
				+ "   from (select *\r\n"
				+ "           from LCP_MASTER_NODE\r\n"
				+ "          where cp_id = '" + _strCpIdOne + "'\r\n"
				+ "            and cp_node_type = '1') a,\r\n"
				+ "        (select * from LCP_NODE_ORDER_ITEM where cp_id = '"
				+ _strCpIdOne + "') b\r\n"
				+ "  where a.cp_node_id = b.cp_node_id\r\n"
				+ "    and a.cp_node_id || cp_node_order_text not in\r\n"//用order_text过滤比较符合实际情况
				+ "        (select cp_node_id || cp_node_order_text\r\n"
				+ "           from LCP_NODE_ORDER_ITEM\r\n"
				+ "          where cp_id = '" + _strCpIdTwo
				+ "') order by cp_node_id\r\n" + "";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		try {
			String _strCp = "";
			if (_strFlag.equals("one")) {
				_strCp = "cp_disorderone";
			} else {
				_strCp = "cp_disordertwo";
			}
			Boolean _hasData = false;
		    _strJson = "\""+_strCp + "\":[";
			while (_rsData.next()) {
				_hasData = true;
				String _strCpName = _rsData.getString("node_name");
				String _strOrderText = _rsData.getString("order_text");
				String _strOrderNo = _rsData.getString("order_no");
				_strJson += "{" 
						+ "\"node_name\":\"" + _strCpName + "\","
						+ "\"order_no\":\"" + _strOrderNo + "\","
						+ "\"order_text\":\"" + _strOrderText + "\"" 
						+ "},";
			}
			if (_hasData) {
				_strJson = _strJson.substring(0, _strJson.length() - 1);
			} else {
				_strJson += "{" + "\"node_name\":\"无不同条目\","
				+ "\"order_text\":\"无不同条目\"" + "}";
			}
			_strJson += "]";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _strJson;
	}
	
	
	
	/**
	 * 获取两临床路径下不同的医嘱
	 * 2015-12-15
	 * 吴海龙 
	 */
	public void GetDiOrders() {
		String _strCpIdOne = request.getParameter("cpone");
		String _strCpIdTwo = request.getParameter("cptwo");
		String _strFlag = request.getParameter("flag");
		String _strJson1 = GetDiOrdersByFlag("one", _strCpIdOne, _strCpIdTwo);
		String _strJson2 = GetDiOrdersByFlag("two", _strCpIdTwo, _strCpIdOne);
		String _strJson = "{" + _strJson1 + "," + _strJson2 + "}";
		System.out.print(_strJson);
		try {
			this.response.setContentType("text/html;charset=UTF-8");
			this.response.getWriter().print(_strJson);
			this.response.getWriter().flush();
			this.response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过路径主id获取，启用路径id，再获取其医嘱选取情况
	 * 2015-12-22
	 * 吴海龙 
	 * @throws ServletException 
	 */
	public void GetUsingCPOrderSeqsByMasterId() throws ServletException{
		String _strMasterId = request.getParameter("master_id");
		String _strCpId = GetUsingCPIdByMasterId(_strMasterId);
		GetOrderSeqsByCPId(_strCpId);
	}
	
	/**
	 * 通过路径主ID获取启用路径id
	 * 2015-12-22
	 * 吴海龙 
	 */
	public String GetUsingCPIdByMasterId(String p_strMasterId) {
		String _strMasterId =p_strMasterId;
		String _strSQL = " select cp_id\r\n" + 
				" from lcp_master\r\n" + 
				" where cp_master_id = '"+_strMasterId+"'\r\n" + 
				" and cp_status = '0'\r\n" + 
				"";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		String _strCpId = "";
		try {
			while (_rsData.next()) {
				_strCpId = _rsData.getString("cp_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return _strCpId;
	}
	
	/**
	 * 获取临床路径下的医嘱执行次数
	 * 2015-12-11
	 * 吴海龙 
	 * @throws ServletException 
	 */
	public void GetOrderSeqsByCPId(String p_strCpId) throws ServletException{
		String _strCpId = p_strCpId;
		String _strSQL = "select tom.*,\r\n" + 
				"       (case when round((case tom.sumcount\r\n" + 
				"        when 0 then 0 else mycount * 100 / sumcount\r\n" + 
				"        end),2) > 100 then 100 else\r\n" + 
				"        round((case tom.sumcount when 0 then 0\r\n" + 
				"        else mycount * 100 / sumcount end), 2) end) lv\r\n" + 
				"  from (select  a.hospital_id,a.cp_id,\r\n" + 
				"               (select cp_node_name\r\n" + 
				"                  from lcp_master_node\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id) node_name,\r\n" + 
				"               a.cp_node_id,\r\n" + 
				"               b.cp_node_order_id order_group_id,\r\n" + 
				"               b.cp_node_order_text order_group,c.cp_node_order_item_id,\r\n" + 
				"               nvl(c.ORDER_ITEM_SET_ID,-1) sub_group_id,\r\n" + 
				"               c.cp_node_order_text order_text,\r\n" + 
				"               c.order_no,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id\r\n" + 
				"                   and order_no = c.order_no\r\n" + 
				"                   and cp_node_order_id = b.cp_node_order_id\r\n" + 
				"                   and ORDER_ITEM_SET_ID=c.ORDER_ITEM_SET_ID) mycount,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id\r\n" + 
				"                   and cp_node_order_id = b.cp_node_order_id) groupcount,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from (select patient_no\r\n" + 
				"                          from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                         where cp_id = '"+_strCpId+"'\r\n" + 
				"                         group by patient_no)) sumcount\r\n" + 
				"          from (select *\r\n" + 
				"                  from LCP_MASTER_NODE\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_type = '1') a,\r\n" + 
				"               (select * from LCP_NODE_ORDER_POINT where cp_id = '"+_strCpId+"') b,\r\n" + 
				"               (select * from LCP_NODE_ORDER_ITEM where cp_id = '"+_strCpId+"') c\r\n" + 
				"         where a.cp_node_id = b.cp_node_id\r\n" + 
				"           and a.cp_node_id = c.cp_node_id\r\n" + 
				"           and b.cp_node_order_id = c.cp_node_order_id\r\n" + 
				"         order by cp_node_id     asc,\r\n" + 
				" mycount        desc,\r\n"+
				//"                  groupcount     desc,\r\n" + 
				"                  order_group_id,\r\n" + 
				"                  sub_group_id" + 
				"                  ) tom";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		try {
			String _strJson = "{\"cp_orders\":[";
			while (_rsData.next()) {
				int _nHosId = _rsData.getInt("hospital_id");
				int _nCpId = _rsData.getInt("cp_id");
				int _nNodeId = _rsData.getInt("cp_node_id");
				String _strCpName = _rsData.getString("node_name");
				String _strOrderGroup = _rsData.getString("order_group");
				String _strOrderGroupId = _rsData.getString("order_group_id");//cp_node_order_item_id
				int _nOrderItemId = _rsData.getInt("cp_node_order_item_id");
				int _strSubGroupId = _rsData.getInt("sub_group_id");
				String _strOrderText = _rsData.getString("order_text");
				String _nOrdrNo = _rsData.getString("order_no");
				int _nOrderCount = _rsData.getInt("mycount");
				int _nGroupCount = _rsData.getInt("groupcount");
				double _dLv = _rsData.getDouble("lv");
				_strJson += "{\"hospital_id\":"+_nHosId+"," +
						"\"cp_id\":"+_nCpId+"," +
						"\"node_id\":"+_nNodeId+"," +
						"\"node_name\":\"" + _strCpName + 
						"\",\"order_group\":\""+ _strOrderGroup + 
						"\",\"order_group_id\":"+ _strOrderGroupId+ 
						",\"order_item_id\":"+ _nOrderItemId+ 
						",\"sub_group_id\":"+ _strSubGroupId + 
						",\"order_text\":\""+ _strOrderText + 
						"\",\"order_no\":\"" + _nOrdrNo+ 
						"\",\"mycount\":" + _nOrderCount + 
						",\"groupcount\":" + _nGroupCount + 
						",\"lv\":" + _dLv + 
						"},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			if("{\"cp_orders\":]}"==_strJson){}
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * 获取临床路径下的医嘱执行次数
	 * 2015-12-11
	 * 吴海龙 
	 * @throws ServletException 
	 */
	public void GetOrderSeqs() throws ServletException{
		String _strCpId = request.getParameter("cp_id");
		String _strSQL = "select tom.*,\r\n" + 
				"       (case when round((case tom.sumcount\r\n" + 
				"        when 0 then 0 else mycount * 100 / sumcount\r\n" + 
				"        end),2) > 100 then 100 else\r\n" + 
				"        round((case tom.sumcount when 0 then 0\r\n" + 
				"        else mycount * 100 / sumcount end), 2) end) lv\r\n" + 
				"  from (select a.cp_id,\r\n" + 
				"               (select cp_node_name\r\n" + 
				"                  from lcp_master_node\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id) node_name,\r\n" + 
				"               a.cp_node_id,\r\n" + 
				"               b.cp_node_order_id order_group_id,\r\n" + 
				"               b.cp_node_order_text order_group,\r\n" + 
				"               nvl(c.ORDER_ITEM_SET_ID,-1) sub_group_id,\r\n" + 
				"               c.cp_node_order_text order_text,\r\n" + 
				"               c.order_no,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id\r\n" + 
				"                   and order_no = c.order_no\r\n" + 
				"                   and cp_node_order_id = b.cp_node_order_id\r\n" + 
				"                   and ORDER_ITEM_SET_ID=c.ORDER_ITEM_SET_ID) mycount,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_id = a.cp_node_id\r\n" + 
				"                   and cp_node_order_id = b.cp_node_order_id) groupcount,\r\n" + 
				"               (select count(*)\r\n" + 
				"                  from (select patient_no\r\n" + 
				"                          from LCP_PATIENT_ORDER_ITEM\r\n" + 
				"                         where cp_id = '"+_strCpId+"'\r\n" + 
				"                         group by patient_no)) sumcount\r\n" + 
				"          from (select *\r\n" + 
				"                  from LCP_MASTER_NODE\r\n" + 
				"                 where cp_id = '"+_strCpId+"'\r\n" + 
				"                   and cp_node_type = '1') a,\r\n" + 
				"               (select * from LCP_NODE_ORDER_POINT where cp_id = '"+_strCpId+"') b,\r\n" + 
				"               (select * from LCP_NODE_ORDER_ITEM where cp_id = '"+_strCpId+"') c\r\n" + 
				"         where a.cp_node_id = b.cp_node_id\r\n" + 
				"           and a.cp_node_id = c.cp_node_id\r\n" + 
				"           and b.cp_node_order_id = c.cp_node_order_id\r\n" + 
				"         order by cp_node_id     asc,\r\n" + 
				"                  groupcount     desc,\r\n" + 
				"                  order_group_id,\r\n" + 
				"                  sub_group_id,\r\n" + 
				"                  mycount        desc) tom";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		try {
			String _strJson = "{\"cp_orders\":[";
			while (_rsData.next()) {
				String _strCpName = _rsData.getString("node_name");
				String _strOrderGroup = _rsData.getString("order_group");
				int _strSubGroupId = _rsData.getInt("sub_group_id");
				String _strOrderText = _rsData.getString("order_text");
				String _nOrdrNo = _rsData.getString("order_no");
				int _nOrderCount = _rsData.getInt("mycount");
				int _nGroupCount = _rsData.getInt("groupcount");
				double _dLv = _rsData.getDouble("lv");
				_strJson += "{\"node_name\":\"" + _strCpName 
				+ "\",\"order_group\":\""+ _strOrderGroup 
				+ "\",\"sub_group_id\":"+ _strSubGroupId 
				+ ",\"order_text\":\""+ _strOrderText 
				+ "\",\"order_no\":\"" + _nOrdrNo
				+ "\",\"mycount\":" + _nOrderCount 
				+ ",\"groupcount\":" + _nGroupCount 
				+ ",\"lv\":" + _dLv + 
				"},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			if("{\"cp_orders\":]}"==_strJson){}
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	
	/**
	 * 获取主路径的不同版本路径
	 * 2015-12-10
	 * 吴海龙
	 */
	public void GetCpVersions(){

		String _strCpMaterId = request.getParameter("master_id");
		String _strSQL = "   select cp_id,cp_name,cp_code,\r\n" + 
				"   (case cp_status \r\n" + 
				"   when 0 then '启用' \r\n" + 
				"     when 1 then '停用' \r\n" + 
				"       when 2 then '等待审核' \r\n" + 
				"         when 3 then '已退回' \r\n" + 
				"           when 4 then '隐藏' \r\n" + 
				"             else '其他' end) cp_status ,\r\n" + 
				"round((sum(hzqk)*100/count(*)),2) cp_hzl,\r\n" + 
				"nvl(round(avg(zyr),2),0) cp_pjzyr,\r\n" + 
				"nvl(round(avg(zyf),2),0) cp_pjzyf \r\n" + 
				"from( select a.cp_status,a.cp_code,a.cp_id,a.cp_name,c.patient_no,\r\n" + 
				"       (case b.TREAT_EFFECT when 1then 1 when 2 then 1 else 0 end)hzqk,\r\n" + 
				"        round(B.DISCHARGE_DATE_TIME- B.ADMISSION_DATE_TIME,2) zyr,\r\n" + 
				"        b.fee_total zyf\r\n" + 
				"    from (select cp_id, cp_status, cp_name, cp_code\r\n" + 
				"            from lcp_master\r\n" + 
				"           where cp_master_id = '"+_strCpMaterId+"') a\r\n" + 
				"    left outer join (select CP_ID, PATIENT_NO\r\n" + 
				"                       FROM LCP_PATIENT_NODE\r\n" + 
				"                      GROUP BY CP_ID, PATIENT_NO) c\r\n" + 
				"      on a.cp_id = c.cp_id\r\n" + 
				"    left outer join (select b1.TREAT_EFFECT,\r\n" + 
				"                            b1.PATIENT_NO,\r\n" + 
				"                            b1.DISCHARGE_DATE_TIME,\r\n" + 
				"                            b1.ADMISSION_DATE_TIME,\r\n" + 
				"                            b2.FEE_TOTAL\r\n" + 
				"                       from LCP_PATIENT_FIRSTPAGE b1, LCP_PATIENT_FEE b2\r\n" + 
				"                      where b1.patient_no = b2.patient_no) b\r\n" + 
				"      on c.patient_NO = B.PATIENT_NO ) group by cp_id,cp_name,cp_code,cp_status";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		try {
			String _strJson = "{\"cp_cp\":[";
			while (_rsData.next()) {
				String _strCpId = _rsData.getString("cp_id");
				String _strCpName = _rsData.getString("cp_name");
				String _strCpCode = _rsData.getString("cp_code");
				Double _nCphzl = _rsData.getDouble("cp_hzl");
				Double _nCppjzyr = _rsData.getDouble("cp_pjzyr");
				Double _nCppjzyf = _rsData.getDouble("cp_pjzyf");
				String _strCpStatus = _rsData.getString("cp_status");
				_strJson += "{\"cp_id\":\"" + _strCpId + "\",\"cp_name\":\""
						+ _strCpName + "\",\"cp_code\":\"" + _strCpCode
						+ "\",\"cp_hzl\":" + _nCphzl + ",\"cp_pjzyr\":"
						+ _nCppjzyr + ",\"cp_pjzyf\":" + _nCppjzyf
						+ ",\"cp_status\":\"" + _strCpStatus + "\"},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			if("{\"cp_cp\":]}"==_strJson){
				_strJson="{\"cp_cp\":[{\"cp_id\":\"no record\",\"cp_name\":\"no record\",\"cp_code\":\"no record\",\"cp_hzl\":0.00,\"cp_pjzyr\":0.00,\"cp_pjzyf\":0.00,\"cp_status\":\"no record\"}]}";
			}		
			
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	/**
	 * 获取使用中临床路径的参数&此路径对应未纳入病人统计情况
	 * 2015-12-21
	 * 吴海龙 
	 */
	public void ReturnJson(String p_strJson) {
		try {
			this.response.setContentType("text/html;charset=UTF-8");
			this.response.getWriter().print(p_strJson);
			this.response.getWriter().flush();
			this.response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
		
	
	
	/**
	 *  获取所有主路径
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	 */
	public void GetCpcpInfo() throws UnsupportedEncodingException, SQLException {
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session=request.getSession();//返回与当前request相关联的session，如果没有则在服务器端创建一个;        
		String _strUserIdFilter = session.getAttribute("userid").toString();
		String _strDeptNameFilter = GetUserDept(_strUserIdFilter);	
		if (null==_strDeptNameFilter) {
			_strDeptNameFilter = "%";
		}
		String _strSQL = " select t.cp_master_id, max(cp_name) cp_name, dept_name\r\n" + 
				"            from lcp_master t\r\n" + 
				"           where t.dept_name is not null and dept_name like'%"+_strDeptNameFilter+"%' \r\n" + 
				"           group by cp_master_id, dept_name\r\n" + 
				"           order by dept_name";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		try {
			String _strJson = "{\"cp_cp\":[";
			while (_rsData.next()) {
				String _strCpId ="-";// _rsData.getString("cp_id");
				String _strCpName = _rsData.getString("cp_name");
				String _strCpCode ="-";// _rsData.getString("cp_code");
				String _strCpMasterId = _rsData.getString("cp_master_id");
				String _strDeptName= _rsData.getString("dept_name");
				int _nCpVersion =-1;// _rsData.getInt("cp_version");
				_strJson += "{" +
						"\"cp_id\":\"" + _strCpId + "\"," +
						"\"cp_name\":\""+ _strCpName + "\"," +
						"\"dept_name\":\""+ _strDeptName + "\"," +
						"\"cp_code\":\"" + _strCpCode+ "\"," +
						"\"cp_master_id\":\"" + _strCpMasterId
						+ "\",\"cp_version\":" + _nCpVersion 
						+ "},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过模糊查询查找路径 条件为科室名称
	 * 2015-12-18
	 * 吴海龙 
	 */
	public ResultSet GetCpVaInfoByParam(String p_strParam) {
		String _strSQL = "select k.cp_master_id,\r\n" + 
				"k.cp_id,\r\n" + 
				"k.cp_status,\r\n" + 
				"(case cp_status\r\n" + 
				"when 0 then\r\n" + 
				"'启用'\r\n" + 
				"when 1 then\r\n" + 
				"'停用'\r\n" + 
				"when 2 then\r\n" + 
				"'等待审核'\r\n" + 
				"when 3 then\r\n" + 
				"'已退回'\r\n" + 
				"when 4 then\r\n" + 
				"'隐藏'\r\n" + 
				"else\r\n" + 
				"''\r\n" + 
				"end) as cp_status_name,\r\n" + 
				"k.cp_name,\r\n" + 
				"k.cp_code,\r\n" + 
				"nvl(m.fcount,0)fcount,\r\n" + 
				"nvl(n.tcount,0)tcount,\r\n" + 
				"nvl(round(m.fcount * 100 / (m.fcount + n.tcount), 2),0) va,\r\n" + 
				"k.dept_code,\r\n" + 
				"k.dept_name,\r\n" + 
				"k.input_code_py\r\n" + 
				"from lcp_master k\r\n" + 
				"left outer join (select cp_id, count(*) fcount\r\n" + 
				"from LCP_PATIENT_NODE t\r\n" + 
				"where cp_node_type = '4'\r\n" + 
				"group by cp_id) m\r\n" + 
				"on k.cp_id = m.cp_id\r\n" + 
				"left outer join (select cp_id, count(*) tcount\r\n" + 
				"from LCP_PATIENT_NODE t\r\n" + 
				"where cp_node_type = '2'\r\n" + 
				"group by cp_id) n\r\n" + 
				"on k.cp_id = n.cp_id\r\n" + 
				"where k.dept_name like '%"+p_strParam+"%'\r\n" + 
				"order by k.dept_name,cp_status asc,cp_master_id, va desc";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		return _rsData;
	}
	
	/**
	 * 获取用户科室
	 * 2015-12-18
	 * 吴海龙 
	 * @throws SQLException 
	 */
	public String GetUserDept(String p_strUserId) throws SQLException {
		String _strDeptName = "-";
		String _strSQL = "select USER_ID, USER_NAME, USER_STATE, DEPT_CODE, DEPT_NAME\r\n"
				+ "  from DCP_SYS_USER\r\n"
				+ " where user_id = '"
				+ p_strUserId + "'\r\n";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		while (_rsData.next()) {
			_strDeptName = _rsData.getString("DEPT_NAME");
		}
		return _strDeptName;
	}
	
	/**
	 * 获取启用的临床路径
	 * 2015-12-18
	 * 吴海龙 
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	 */
	public void GetCpVaInfo() throws UnsupportedEncodingException, SQLException {
		request.setCharacterEncoding("UTF-8"); 
		HttpSession session=request.getSession();//返回与当前request相关联的session，如果没有则在服务器端创建一个;        
		String _strUserIdFilter = session.getAttribute("userid").toString();
		String _strDeptNameFilter = GetUserDept(_strUserIdFilter);	
		if (null==_strDeptNameFilter) {
			_strDeptNameFilter = "%";
		}
		ResultSet _rsData = GetCpVaInfoByParam(_strDeptNameFilter);		
		try {
			String _strJson = "{\"cp_va\":[";
			while (_rsData.next()) {
				String _strMasterId = _rsData.getString("cp_master_id");
				String _strCpId = _rsData.getString("cp_id");
				String _strCpName = _rsData.getString("cp_name");
				String _strCpCode = _rsData.getString("cp_code");
				String _strDeptName = _rsData.getString("dept_name");
				int _nFcount = _rsData.getInt("fcount");
				int _nTcount = _rsData.getInt("tcount");
				int _nVa = _rsData.getInt("va");//cp_status_name
				String _strCpStatus = _rsData.getString("cp_status_name");
				_strJson += "{\"master_id\":\""+_strMasterId+"\"," +
							 "\"cp_id\":\"" + _strCpId + "\"," +
							 "\"cp_name\":\""+ _strCpName + "\"," +
							 "\"cp_code\":\"" + _strCpCode+ "\"," +
							 "\"fcount\":" + _nFcount + "," +
							 "\"tcount\":"+ _nTcount + "," +
							 "\"va\":" + _nVa + "," +
							 "\"dept_name\":\""+_strDeptName+"\"," +
							 "\"cp_status\":\""+_strCpStatus+"\"" +
							 "},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "]}";
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取节点个数
	 * 2015-12-18
	 * 吴海龙 
	 */
	public int GetCpNodeCount(String _strCpId) throws SQLException {
		String _strSQL = "select count(*) mycount from LCP_MASTER_NODE where cp_id='"
				+ _strCpId + "' and cp_node_type ='1'";
		ResultSet _rsCpNodeCount = this.ExcuteBySQL(_strSQL);
		int _nCount = 0;
		while (_rsCpNodeCount.next()) {
			_nCount = _rsCpNodeCount.getInt("mycount");
		}
		return _nCount;
	}

	
	/**
	 * 获取节点名称
	 * 2015-12-18
	 * 吴海龙 
	 */
	public String GetNodeName(String p_strCpId, String p_strNodeId)
			throws SQLException {
		String _strSQL = "select cp_node_name  from LCP_MASTER_NODE "
				+ " where cp_id = '" + p_strCpId + "' and cp_node_id='"
				+ p_strNodeId + "'";
		ResultSet _rsCpNodeCount = this.ExcuteBySQL(_strSQL);
		while (_rsCpNodeCount.next()) {
			return _rsCpNodeCount.getString("cp_node_name");
		}
		return "";

	}

	
	
	
	
	/**
	 * 获取变异节点
	 * 2015-12-18
	 * 吴海龙 
	 */
	public void GetVaNodeCount() throws SQLException {
		String _strCpId = request.getParameter("cp_id");
		System.out.println("cp_id" + _strCpId);
		String _strSQL = " select mm.node_no,mm.cp_node_name byjd, mm.cp_node_id, nvl(nn.mycount, 0) mycount"
				+ " from (select rownum node_no, m.cp_node_name, cp_node_id"
				+ " from LCP_MASTER_NODE m"
				+ " where cp_id = '"
				+ _strCpId
				+ "'"
				+ " and cp_node_type = '1') mm"
				+ " left outer join (select rownum node_no, x.*"
				+ " from (select m.byjd cp_node_id, count(*) mycount"
				+ " from (select m.patient_no, count(*) byjd"
				+ " from LCP_PATIENT_NODE m,"
				+ " (select tt.patient_no"
				+ " from LCP_PATIENT_NODE tt"
				+ " where cp_id = '"
				+ _strCpId
				+ "'"
				+ " and cp_node_type = '4') n"
				+ " where m.patient_no = n.patient_no"
				+ " group by m.patient_no) m"
				+ " group by byjd) x) nn"
				+ " on mm.node_no = nn.node_no" + " order by cp_node_id asc";
		ResultSet _rsData = this.ExcuteBySQL(_strSQL);
		int _nCount = GetCpNodeCount(_strCpId);
		try {
			int _nMaxCount = 0;
			String _strJson = "{\"cp\":[";
			while (_rsData.next()) {
				String _strNodeName = _rsData.getString("byjd");
				String _strNodeId = _rsData.getString("cp_node_id");
				int _nMyCount = _rsData.getInt("mycount");
				int _nNodeNo = _rsData.getInt("node_no");
				_nMaxCount = _nMaxCount < _nMyCount ? _nMyCount : _nMaxCount;
				System.out.println(_strNodeName + "---" + _nMyCount);
				_strJson += " {\"node_name\":\"" + _strNodeName
						+ "\",\"node_no\":" + _nNodeNo + ",\"node_id\":"
						+ _strNodeId + ",\"vacount\":" + _nMyCount + "},";
			}
			_strJson = _strJson.substring(0, _strJson.length() - 1);
			_strJson += "],\"numberx\":" + _nCount;
			_strJson += ",\"endy\":" + ((_nMaxCount / 10) + 1) * 10;
			_strJson += "}";
			System.out.println(_strJson);
			try {
				this.response.setContentType("text/html;charset=UTF-8");
				this.response.getWriter().print(_strJson);
				this.response.getWriter().flush();
				this.response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int GetNodeVaCount(String p_strNodeId) {
		return 0;

	}

	/**
	 * 
	 * 2015-12-8 �⺣��
	 */
	public ResultSet ExcuteBySQL(String p_strSQL) {
		try {
			Connection conn = null;
			Statement stmt = null;
			String sql = p_strSQL;
			System.out.println(sql);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


/**
 * 将Json对象转换成Map
 * 
 * @param jsonObject
 *            json对象
 * @return Map对象
 * @throws JSONException
 */
public Map toMap(String jsonString) throws JSONException {
    JSONObject jsonObject = JSONObject.fromObject(jsonString);
    Map result = new HashMap();
    Iterator iterator = jsonObject.keys();
    String key = null;
    String value = null; 
    while (iterator.hasNext()) {
        key = (String) iterator.next();
        value = jsonObject.getString(key);
        result.put(key, value);
    }
    return result;
}
}