/**
 * 路径统计专用接口
 */
package com.goodwillcis.lcp.service.zhikong;


public interface ZhikongCpTongji {

	/**
	 * 生成查询的sql语句（拆分路径版本）
	 * @param value 查询的参数值
	 * @param flag 按什么查询  0：编码；1：拼音：2；五笔
	 * @return
	 */
	public String funGetCpSQL(String value,String flag,String starttime,String endtime);
	/**
	 * 取得总行数（拆分路径版本）
	 * @param sql
	 * @return
	 */
	public int funGetCpCount(String sql);
	/**
	 * 取得各路径情况，生成xml流（拆分路径版本）
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public String funGetCpContextUseXML(String sql,int start,int end);
	/**
	 * 取得最大或者最小的路径纳入率（拆分路径版本）（报表专用）
	 * @param isDesc true:最大项，false：最小项
	 * @param top 前多少条数据
	 * @return
	 */
	public String funGetTopNaruUseXML(boolean isDesc ,int top,String starttime,String endtime);
	/**
	 * 取得路径的各种天数（报表专用）
	 * @param cp_id
	 * @return
	 */
	public String funGetTianshuByCId(String cp_id);
	/**
	 * 住院分布率
	 * @param cp_id
	 * @return
	 */
	public String funGetZhuYuanFenblByCpIdUseXML(String cp_id);
	
	/**
	 * 效果统计，第一个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetXiaoguoByCpIdUseXML(String cp_id);
	
	/**
	 * 效果统计，第二个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetXiaoguoByCpIdUseXML2(String cp_id);
	
	/**
	 * 效果统计，第三个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetXiaoguoByCpIdUseXML3(String cp_id);
	
	/**
	 * 变异统计第一个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetBianyiByCpIdUseXML1(String cp_id);
	
	/**
	 * 变异统计第2个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetBianyiByCpIdUseXML2(String cp_id);
	
	/**
	 * 变异统计第三个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetBianyiByCpIdUseXML3(String cp_id);
	
	/**
	 * 变异统计第四个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetBianyiByCpIdUseXML4(String cp_id);
	
	/**
	 * 经济学统计第1个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetJingjixueCpIdUseXML1(String cp_id);
	
	/**
	 * 经济学统计第2个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetJingjixueCpIdUseXML2(String cp_id);
	
	/**
	 * 经济学统计第3个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetJingjixueCpIdUseXML3(String cp_id);
	
	/**
	 * 抗生素统计第1个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetKangshengsuCpIdUseXML1(String cp_id);
	
	/**
	 * 抗生素统计第2个图表
	 * @param cp_id
	 * @return
	 */
	public String funGetKangshengsuCpIdUseXML2(String cp_id);
	
	/**
	 * 登陆首页中的路径概况
	 * @return
	 */	
	public String funGetFPCpInfo(String year,String month);
	
	/**
	 * 登陆首页中的全院路径统计
	 * @return
	 */	
	public String funGetFPCpTongji(String year,String month);
	/**
	 * 登陆首页中的全院路径完成情况
	 * @return
	 */	
	public String funGetFPWC(String year,String month);
	/**
	 * 登陆首页中的全院路径排除
	 * @return
	 */	
	public String funGetFPPC(String year,String month);
}
