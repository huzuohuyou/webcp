package com.goodwillcis.lcp.servlet.pdca;

import java.sql.SQLException;
import java.util.List;

public interface ICloneCp {

	
	/**
	 * 获取对象list
	 * 2015-12-24
	 * 吴海龙 
	 */
	List<Object> GetCpObjectList();

	/**
	 * 克隆单个对象
	 * 2015-12-24
	 * 吴海龙 
	 */
	int CloneCpObject(Object _obj) throws SQLException;

	/**
	 * 克隆对象list
	 * 内部调用GetCpObjectList
	 * 2015-12-24
	 * 吴海龙 
	 */
	void CloneCpObjectList(List<Object> _listobj) throws SQLException;
}
