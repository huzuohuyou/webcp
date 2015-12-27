/*----------------------------------------------------------------
//Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
//文件名：Page.java
//文件功能描述：分页model类
//创建人：刘植鑫 
//创建日期：2011/07/26
//
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.model.cpmanage;

public class Page {

	private int totalRecord;//总共多少条记录
	private int nowPage;//当前页号
	private int totolPage;//一共多少页
	private int pageSize;//每一页显示的大小
	private int nowPageSize;//当前页面大小
	private int lastPageSize;//最后页面大小
	private int start;//开始位置
	/**
	 * 开始位置
	 * @return
	 */
	public int getStart() {
		return start;
	}
	private void setStart(int start) {
		this.start = start;
	}
	/**
	 * 结束位置
	 * @return
	 */
	public int getEnd() {
		return end;
	}
	private void setEnd(int end) {
		this.end = end;
	}
	private int end;//结束位置
	public int getNowPageSize() {
		return nowPageSize;
	}
	public void setNowPageSize(int nowPageSize) {
		this.nowPageSize = nowPageSize;
	}
	/**
	 * 计算分页情况
	 * @param totalRecord 总共多少条记录
	 * @param nowPage 当前查询的页数
	 * @param pageSize 分配的页面大小
	 */
	public Page(int totalRecord,int nowPage,int pageSize){
		int _totalPage=totalRecord/pageSize;
		int _lastPageSize=totalRecord%pageSize;
		int _nowPageSize=-1;
		if(_lastPageSize>0){
			_totalPage++;
		}
		if(_lastPageSize==0){
			_lastPageSize=pageSize;
		}
		if(nowPage>=_totalPage){
			nowPage=_totalPage;
			_nowPageSize=_lastPageSize;
		}else if(nowPage<=0) {
			nowPage=1;
			_nowPageSize=pageSize;
		}else{
			_nowPageSize=pageSize;
		}
		if(_totalPage==1){
			nowPage=1;
			_nowPageSize=_lastPageSize;
		}
		int _start=(nowPage-1)*pageSize+1;
		int _end=(nowPage-1)*pageSize+_nowPageSize;
		if(totalRecord==0){
			setStart(0);
			setEnd(0);
			setTotalRecord(0);
			setTotolPage(1);
			setLastPageSize(0);
			setNowPage(1);
			setNowPageSize(0);
			setPageSize(0);
		}else{
			setStart(_start);
			setEnd(_end);
			setTotalRecord(totalRecord);
			setTotolPage(_totalPage);
			setLastPageSize(_lastPageSize);
			setNowPage(nowPage);
			setNowPageSize(_nowPageSize);
			setPageSize(pageSize);
		}
	}
	/**
	 * 最后一页大小
	 * @return
	 */
	public int getLastPageSize() {
		return lastPageSize;
	}
	private void setLastPageSize(int lastPageSize) {
		this.lastPageSize = lastPageSize;
	}
	/**
	 * 总记录数
	 * @return
	 */
	public int getTotalRecord() {
		return totalRecord;
	}
	private void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	/**
	 * 当前页号
	 * @return
	 */
	public int getNowPage() {
		return nowPage;
	}
	private void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	/**
	 * 总页数
	 * @return
	 */
	public int getTotolPage() {
		return totolPage;
	}

	private void setTotolPage(int totolPage) {
		this.totolPage = totolPage;
	}
	/**
	 * 设置的页大小
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}
	private void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
