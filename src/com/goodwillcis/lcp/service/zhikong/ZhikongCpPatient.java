package com.goodwillcis.lcp.service.zhikong;

import java.util.ArrayList;

import com.goodwillcis.lcp.model.ZhikongCpPatientIndex;

public interface ZhikongCpPatient {
	/**
	 * 取得总数据行数
	 * @return
	 */
	public int funGetCount(String cp_id,String name,String inTime,String outTime);
	/**
	 * 取得符合的查询内容
	 * @param code
	 * @param pinyin
	 * @param wubi
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<ZhikongCpPatientIndex> funGetZhikongByStartEndAndQuery(int start,int end);
}
