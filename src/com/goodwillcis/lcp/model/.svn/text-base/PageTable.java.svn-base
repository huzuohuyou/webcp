/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：PageTable.java
//文件功能描述：分页导航栏工具类
//创建人：刘植鑫 
//创建日期：2011/07/26
 * 
 * 分页导航空格问题导致json格式错误
 * 修改人:刘植鑫
 * 修改时间：2011-08-26
//
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model;

public class PageTable {

	/**
	 * 生成分页导航
	 * @param totalRow 总记录数
	 * @param nowPage 当前页号
	 * @param totalPage 总页号
	 * @return
	 */
	public String funGetPageHtml(int totalRow,int nowPage,int totalPage) {
		// TODO Auto-generated method stub
		String table=""+
	      				"<tr>"+
	        				"<td width='300'><div align='left'><span class='STYLE22'>&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> "+totalRow+"</strong> 条记录，当前第<strong> "+nowPage+"</strong> 页，共 <strong>"+totalPage+"</strong> 页</span></div></td>"+
	        				"<td width='*' align='right'>"+
	        					"<table border='0' align='right' cellpadding='0' cellspacing='0'>"+
	          					"<tr>";
			if(nowPage!=1){				    
	          			
		table=table+		
	          					"<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_54.gif' width='40' height='15'  onclick='tiaozhuan("+1+");'  border='0'/></a></div></td>"+
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_56.gif' width='45' height='15' onclick='tiaozhuan("+(nowPage-1)+");'  border='0'/></a></div></td>";
							     
			}        
			if(nowPage!=totalPage)	{			        
		table=table+					        
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_58.gif' width='45' height='15'  onclick='tiaozhuan("+(nowPage+1)+");'  border='0'/></a></div></td>"+
							      "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_60.gif' width='40' height='15'  onclick='tiaozhuan("+totalPage+");'  border='0'/></a></div></td>";
							    
			}
							      
			table=table+			      "<td width='37' class='STYLE22'><div align='center'>转到</div></td>"+
							      "<td width='22'><div align='center'><input type='text' name='tiaozhuan_daohang' id='tiaozhuan_daohang'  style='width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;'/></div></td>"+
	            				"<td width='22' class='STYLE22'><div align='center'>页</div></td>"+
	            				"<td width='35'><a  href='#'><img src='../public/images/main_62.gif' width='26' height='15'  border='0' onclick='tiaozhuanzhiding();' /></a></td>"+
	          				"</tr>"+
	        				"</table>"+
	        				"</td>" +
	        				"<td width='1%'></td>"+
	      			"</tr>";
		return table;
	}
	
	/**
	 * 生成分页导航 -- 字典审核
	 * @param totalRow 总记录数
	 * @param nowPage 当前页号
	 * @param totalPage 总页号
	 * @param flag 区分不同表的标记
	 * @return
	 */
	public String funGetPageHtml(int totalRow,int nowPage,int totalPage,String flag) {
		// TODO Auto-generated method stub
		String table=""+
	      				"<tr>"+
	        				"<td width='300'><div align='left'><span class='STYLE22'>&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> "+totalRow+"</strong> 条记录，当前第<strong> "+nowPage+"</strong> 页，共 <strong>"+totalPage+"</strong> 页</span></div></td>"+
	        				"<td width='*' align='right'>"+
	        					"<table border='0' align='right' cellpadding='0' cellspacing='0'>"+
	          					"<tr>";
			if(nowPage!=1){				    
	          			
		table=table+		
	          					"<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_54.gif' width='40' height='15'  onclick='tiaozhuan("+1+");'  border='0'/></a></div></td>"+
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_56.gif' width='45' height='15' onclick='tiaozhuan("+(nowPage-1)+");'  border='0'/></a></div></td>";
							     
			}        
			if(nowPage!=totalPage)	{			        
		table=table+					        
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_58.gif' width='45' height='15'  onclick='tiaozhuan("+(nowPage+1)+");'  border='0'/></a></div></td>"+
							      "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_60.gif' width='40' height='15'  onclick='tiaozhuan("+totalPage+");'  border='0'/></a></div></td>";
							    
			}
							      
			table=table+			      "<td width='37' class='STYLE22'><div align='center'>转到</div></td>"+
							      "<td width='22'><div align='center'><input type='text' name='tiaozhuan_daohang' id='tiaozhuan_daohang"+flag+"'  style='width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;'/></div></td>"+
	            				"<td width='22' class='STYLE22'><div align='center'>页</div></td>"+
	            				"<td width='35'><a  href='#'><img src='../public/images/main_62.gif' width='26' height='15'  border='0' onclick='tiaozhuanzhiding();' /></a></td>"+
	          				"</tr>"+
	        				"</table>"+
	        				"</td>" +
	        				"<td width='1%'></td>"+
	      			"</tr>";
		return table;
	}
	
	/**
	 * 生成分页导航 --路径配置
	 * @param totalRow 总记录数
	 * @param nowPage 当前页号
	 * @param totalPage 总页号
	 * @param flag 区分不同表的标记
	 * @return
	 */
	public String funGetPageHtmlToCp(int totalRow,int nowPage,int totalPage,String flag) {
		// TODO Auto-generated method stub
		String table=""+
	      				"<tr>"+
	        				"<td width='300'><div align='left'><span class='STYLE22'>&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> "+totalRow+"</strong> 条记录，当前第<strong> "+nowPage+"</strong> 页，共 <strong>"+totalPage+"</strong> 页</span></div></td>"+
	        				"<td width='*' align='right'>"+
	        					"<table border='0' align='right' cellpadding='0' cellspacing='0'>"+
	          					"<tr>";
			if(nowPage!=1){				    
	          			
		table=table+		
	          					"<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_54.gif' width='40' height='15'  onclick='tiaozhuan("+1+","+flag+");'  border='0'/></a></div></td>"+
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_56.gif' width='45' height='15' onclick='tiaozhuan("+(nowPage-1)+","+flag+");'  border='0'/></a></div></td>";
							     
			}        
			if(nowPage!=totalPage)	{			        
		table=table+					        
							        "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_58.gif' width='45' height='15'  onclick='tiaozhuan("+(nowPage+1)+","+flag+");'  border='0'/></a></div></td>"+
							      "<td width='49'><div align='center'><a  href='#'><img src='../public/images/main_60.gif' width='40' height='15'  onclick='tiaozhuan("+totalPage+","+flag+");'  border='0'/></a></div></td>";
							    
			}
							      
			table=table+			      "<td width='37' class='STYLE22'><div align='center'>转到</div></td>"+
							      "<td width='22'><div align='center'><input type='text' name='tiaozhuan_daohang' id='tiaozhuan_daohang"+flag+"'  style='width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;'/></div></td>"+
	            				"<td width='22' class='STYLE22'><div align='center'>页</div></td>"+
	            				"<td width='35'><a  href='#'><img src='../public/images/main_62.gif' width='26' height='15'  border='0' onclick='tiaozhuanzhiding("+flag+");' /></a></td>"+
	          				"</tr>"+
	        				"</table>"+
	        				"</td>" +
	        				"<td width='1%'></td>"+
	      			"</tr>";
		return table;
	}
	
	

}
