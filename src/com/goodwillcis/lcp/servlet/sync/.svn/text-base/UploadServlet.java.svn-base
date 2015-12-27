package com.goodwillcis.lcp.servlet.sync;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.Page;
import com.goodwillcis.lcp.model.PageTable;
import com.goodwillcis.lcp.service.sync.UploadList;
import com.goodwillcis.lcp.service.sync.impl.UploadListImpl;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.PropertiesUtil;
import com.goodwillcis.lcp.util.StateUtil;

public class UploadServlet extends HttpServlet {
	
	private int pageSize=20;
	
	//总标识
	String biao = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UploadList uploadList=new UploadListImpl();
	PageTable pageTable=new PageTable();
	DatabaseClass db = LcpUtil.getDatabaseClass();
	String sqlid = "select * from dcp_sys_config  where config_id = '20110516ZP01'";
	DataSetClass dataSet = db.FunGetDataSetBySQL(sqlid);
	int hospitalId = Integer.parseInt(dataSet.FunGetDataAsStringByColName(0, "CONFIG_VALUE"));
	
	
	String download_name;
	StateUtil state;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String op = request.getParameter("op");
		if("getAllTable".equals(op)){
//			String cpUnPublish="";
			String updateList="";
			String updateListPageHtml="";
			try {
				/***************数据更新列表******************/
				int row=uploadList.getTotalNum();
				Page page=new Page(row, 1, pageSize);	
				int start=page.getStart();
				int end=page.getEnd();
				int nowPage=page.getNowPage();
				int totalPage=page.getTotolPage();
				updateList=uploadList.getUploadList(start, end);
				updateListPageHtml=pageTable.funGetPageHtml(row, nowPage, totalPage);
				response.getWriter().println("[{\"result\":\"OK\", \"method\":\"getAllTable\", " +
						" \"updateListPageHtml\":\""+updateListPageHtml+"\", " +
						"\"updateList\":\""+updateList+"\",\"flag\":\"1\"}]");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				response.getWriter().println("[{\"result\":\"ERROR\", \"method\":\"getAllTable\", \"flag\":\"0\"}]");
			}					
		} else if ("upload".equals(op)) {
			upload(request, response);
		} else if ("unzip".equals(biao)) {
			unzip(request, response);
		} else if ("loadcsv".equals(biao)) {
			loadcsv(request, response);
		} else if ("insertxml".equals(biao)) {
			insertxml(request, response);
		} else if ("del".equals(biao)) {
			del(request, response);
		}
		
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
	try {
		DatabaseClass db = LcpUtil.getDatabaseClass();
		//System.out.println("————————————————upload——————————————————————————————————————");
		state = new StateUtil();
		String sql = "select download_name,download_file from dcp_syn_download where sys_is_del = 0 order by download_name";
		
		state.setDataSet(db.FunGetDataSetBySQL(sql));
		state.setI(0);
		state.setJ(0);
		
		if (state.getDataSet().FunGetRowCount() > 0) {
			System.out.println(state.getDataSet().FunGetRowCount());
			biao = "unzip";
			response.getWriter().println("[{\"res\":true},{\"result1\":\"加载成功，共"+state.getDataSet().FunGetRowCount()+"个版本包可以更新<br/>\"}]");
		}else{
			response.getWriter().println("[{\"res\":false}]");
		}
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		//System.out.println(e);
		e.printStackTrace();
		response.getWriter().println("e");
	}
	
	}
	
	private void unzip(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	
	try {
		//System.out.println("————————————————unzip——————————————————————————————————————");
		DataSetClass dataSet = state.getDataSet();
		download_name = dataSet.FunGetDataAsStringById(state.getI(), 0);
		String download_file = dataSet.FunGetDataAsStringById(state.getI(), 1);
		download_file = download_file.replace("\\", "/");
		download_file = download_file.replace("//", "/");
		state.setPath(download_file);
		
		int res = uploadList.unZipFile(state.getPath(),hospitalId, download_name);
		if (res == 1) {
			//state.setI(state.getI()+1);
			biao = "loadcsv";
			response.getWriter().println("[{\"res\":\"b\"},{\"result1\":\"—————————开始更新"+download_name+"  版本包—————————<br/>"+download_name+"版本包解压完成<br/>正在加载配置文件......<br/>\"},{\"result2\":\"当前更新更新版本:"+download_name+"\"}]");
		} else {
			response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"—————————开始更新"+download_name+"  版本包—————————<br/><span style=\'color:#F00\'>"+download_name+" 版本包解压失败！</span><br/><span style=\'color:#F00\'>请检查版本包物理文件是否被删除！然后重新操作！</span><br/>\"}]");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
			System.out.println(e);				
			response.getWriter().println("[{\"res\":\"e\"},{\"result\":\"<span style=\"color:#F00\">"+download_name+"版本包解压失败！</span><br/><span style=\"color:#F00\">请检查版本包物理文件是否被删除！然后重新操作！</span><br/>\"}]");
	}
	
	}
	
	private void loadcsv(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	try {
		//System.out.println("————————————————loadcsv——————————————————————————————————————");
		state.setAlist(uploadList.loadcsv(hospitalId,state.getPath()));
		if (state.getAlist().size() > 0) {
			biao = "insertxml";
			response.getWriter().println("[{\"res\":\"a\"},{\"result1\":\"加载配置文件完毕!<br/>开始更新表数据......<br/>\"}]");
			
		}else{
			response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 读取配置文件失败!</span><br/><span style=\'color:#F00\'> 请检查配置文件是否被删除！然后重新操作！</span><br/>\"}]");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 加载配置文件时出错!已停止更新！</span><br/>\"}]");
	}
	
	}
	
	private void insertxml(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	
	try {
	//	System.out.println("—————————总JJJ———————————:"+state.getAlist().size());
	//	System.out.println("—————————JJJJJ———————————:"+state.getJ());
	//	System.out.println("—————————总III———————————:"+state.getDataSet().FunGetRowCount());
	//	System.out.println("—————————IIIII———————————:"+state.getI());
		if(state.getJ()<state.getAlist().size()){
			String[] ss = state.getAlist().get(state.getJ()).split(",");
			int res = uploadList.updateData(hospitalId,state.getPath(), ss[0], ss[1]);
			//System.out.println("————————————————insertxml——————————————————————————————————————");
			//System.out.println(res);
			if (res == 1) {
				
				response.getWriter().println("[{\"res\":\"a\"},{\"result1\":\"表"+ss[0]+"更新完毕<br/>\"}]");
			} else if (res == -1) {
				response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 加载"+ss[0]+".xml文件失败！已停止更新！</span><br/><span style=\'color:#F00\'> 请检查xml文件是否已被删除！然后重新操作！</span><br/>\"}]");
			} else {
				response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 更新表"+ss[0]+"时出错！已停止更新！</span><br/>\"}]");
			}
			state.setJ(state.getJ()+1);
		}else{
			
			if(state.getI()==state.getDataSet().FunGetRowCount()-1){
				biao = "del";
			}else{
				state.setJ(0);
				state.setI(state.getI()+1);
				biao = "unzip";
				
			}
			response.getWriter().println("[{\"res\":\"a\"},{\"result1\":\"—————————"+download_name+"版本更新完毕—————————<br/>\"}]");
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 更新当前表时出错！已停止更新！</span><br/>\"}]");
	}
	
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	try {
		//System.out.println("————————————————del——————————————————————————————————————");
		String sql = "update dcp_syn_download t set t.sys_is_del = 1 where t.sys_is_del = 0";
		int res = db.FunRunSQLCommand(db.FunGetSvrKey(), sql);
		if (res > 0) {
			response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"＝＝＝＝＝＝＝＝ 所有版本更新完毕 ＝＝＝＝＝＝＝＝<br/>\"}]");
		} else {
			response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 更新状态写入出错！</span><br/>\"}]");
		}
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e);
		response.getWriter().println("[{\"res\":\"e\"},{\"result1\":\"<span style=\'color:#F00\'> 更新状态写入出错！</span><br/>\"}]");
	}

}


}
