/**---------------------------------------------------------------- 
 * // Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。
 *  // 文件名：PropertiesUtil.java
 *  // 文件功能描述：读取配置文件里属性的类
 *  // 创建人：段英华 
 *  // 创建日期：2011/07/26 
 *  // ----------------------------------------------------------------*/

package com.goodwillcis.lcp.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropertiesUtil {
	
	private static HashMap<String, String> property;
	
	/**
	 * Web服务DatabaseProject的地址常量
	 */
	public static final String DATABASE_URL = "database.url";
	/**
	 * 配置表中，当前医院编号的关键字常量
	 */
	public static final String HOSPITAL_ID_KEY = "hospital.id.key";
	/**
	 * 配置表中，上传应用程序地址的关键字常量
	 */
	public static final String UPLOAD_EXE_KEY = "upload.exe.key";
	/**
	 * 配置表中，下载应用程序地址的关键字常量
	 */
	public static final String DOWNLOAD_EXE_KEY = "download.exe.key";
	
	/**
	 * 配置表中，其他科室纳入路径的医嘱调用地址
	 */
	public static final String ORDER_URL = "order.nr.address";
	/**
	 * 配置表中，其他科室不纳入路径的医嘱调用地址
	 */
	public static final String NULL_ORDER_URL = "order.bnr.address";
	/**
	 * 配置表中，如果病区为肿瘤科室（1200200）,医嘱下发对应的URL地址常量
	 */
	public static final String TUMOUR_ORDER_URL = "order.tumour.address";
	/**
	 * 配置表中，老年科，老年病一病区和二病区，医嘱跳转地
	 */
	public static final String OLD_ORDER_URL = "order.old.address";
	public static final String OLD_ORDER_URL_NULL = "order.old.address.null";
	/**
	 * 配置表中，干部保健科，干部保健一病区和二病区调用医嘱地址
	 */
	public static final String GB_ORDER_URL = "order.cadre.address";
	public static final String GB_ORDER_URL_NULL = "order.cadre.address.null";
	/**
	 * 配置表中，神经外科
	 */
	public static final String SW_ORDER_URL = "order.nerve.address";
	public static final String SW_ORDER_URL_NULL = "order.nerve.address.null";
	/**
	 * 配置表中，消化内科
	 */
	public static final String XH_ORDER_URL = "order.digest.address";
	public static final String XH_ORDER_URL_NULL = "order.digest.address.null";
	
	/**
	 * 配置表中，妇产科
	 */
	public static final String FC_ORDER_URL = "order.maternity.address";
	public static final String FC_ORDER_URL_NULL = "order.maternity.address.null";
	
	/**
	 * 骨科
	 */
	public static final String GK_ORDER_URL = "order.address";
	public static final String GK_ORDER_URL_NULL = "order.address.null";
	/**
	 * 眼科
	 */
	public static final String YK_ORDER_URL = "order.address.yk";
	public static final String YK_ORDER_URL_NULL = "order.address.yk.null";
	
	/**
	 * 泌尿外科
	 */
	public static final String MNWK_ORDER_URL = "order.address.mnwk";
	public static final String MNWK_ORDER_URL_NULL = "order.address.mnwk.null";
	/**
	 * 儿科
	 */
	public static final String EK_ORDER_URL = "order.address.ek";
	public static final String EK_ORDER_URL_NULL = "order.address.ek.null";
	/**
	 * 耳鼻喉科
	 */
	public static final String EBHK_ORDER_URL = "order.address.ebhk";
	public static final String EBHK_ORDER_URL_NULL = "order.address.ebhk.null";
	/**
	 * 肝胆外科
	 */
	public static final String GDWK_ORDER_URL = "order.address.gdwk";
	public static final String GDWK_ORDER_URL_NULL = "order.address.gdwk.null";
	/**
	 * 呼吸内科
	 */
	public static final String HXNK_ORDER_URL = "order.address.hxnk";
	public static final String HXNK_ORDER_URL_NULL = "order.address.hxnk.null";
	/**
	 * 口腔科
	 */
	public static final String KQK_ORDER_URL = "order.address.kqk";
	public static final String KQK_ORDER_URL_NULL = "order.address.kqk.null";
	/**
	 * 普外儿外病区
	 */
	public static final String PWEWBQ_ORDER_URL = "order.address.pwewbq";
	public static final String PWEWBQ_ORDER_URL_NULL = "order.address.pwewbq.null";
	/**
	 * 乳腺外科病区
	 */
	public static final String RXWKBQ_ORDER_URL = "order.address.rxwkbq";
	public static final String RXWKBQ_ORDER_URL_NULL = "order.address.rxwkbq.null";
	/**
	 * 神经内科
	 */
	public static final String SJNK_ORDER_URL = "order.address.sjnk";
	public static final String SJNK_ORDER_URL_NULL = "order.address.sjnk.null";
	/**
	 * 肾内科
	 */
	public static final String SNK_ORDER_URL = "order.address.snk";
	public static final String SNK_ORDER_URL_NULL = "order.address.snk.null";
	/**
	 * 胃肠外科
	 */
	public static final String WCWK_ORDER_URL = "order.address.wcwk";
	public static final String WCWK_ORDER_URL_NULL = "order.address.wcwk.null";
	/**
	 * 小儿外科病区
	 */
	public static final String XEWKBQ_ORDER_URL = "order.address.xewkbq";
	public static final String XEWKBQ_ORDER_URL_NULL = "order.address.xewkbq.null";
	/**
	 * 血管外科病区
	 */
	public static final String XGWKBQ_ORDER_URL = "order.address.xgwkbq";
	public static final String XGWKBQ_ORDER_URL_NULL = "order.address.xgwkbq.null";	
	/**
	 * 普通外科二病区
	 */
	public static final String PTWKEBQ_ORDER_URL = "order.address.ptwkebq";
	public static final String PTWKEBQ_ORDER_URL_NULL = "order.address.ptwkebq.null";	
	/**
	 * 放射科
	 */
	public static final String FSK_ORDER_URL = "order.address.fsk";
	public static final String FSK_ORDER_URL_NULL = "order.address.fsk.null";
	/**
	 * 内分泌科
	 */
	public static final String NFMK_ORDER_URL = "order.address.nfmk";
	public static final String NFMK_ORDER_URL_NULL = "order.address.nfmk.null";
	/**
	 * 肿瘤放疗科
	 */
	public static final String ZLFLK_ORDER_URL = "order.address.zlflk";
	public static final String ZLFLK_ORDER_URL_NULL = "order.address.zlflk.null";
	
	/**
	 * 中医科
	 */
	public static final String ZYK_ORDER_URL = "order.address.zyk";
	public static final String ZYK_ORDER_URL_NULL = "order.address.zyk.null";
	
	/**
	 * 手术麻醉科
	 */
	public static final String SMK_ORDER_URL_NULL = "order.address.smk.null";
	/**
	 * 肿瘤科
	 */
	public static final String ZLK_ORDER_URL = "order.address.zlk";
	public static final String ZLK_ORDER_URL_NULL = "order.address.zlk.null";
	/**
	 * 重症医学科
	 */
	public static final String ZZYXK_ORDER_URL = "order.address.zzyxk";
	public static final String ZZYXK_ORDER_URL_NULL = "order.address.zzyxk.null";
	/**
	 * 血液科
	 */
	public static final String XYK_ORDER_URL = "order.address.xyk";
	public static final String XYK_ORDER_URL_NULL = "order.address.xyk.null";
	/**
	 * 胸外科
	 */
	public static final String XWK_ORDER_URL = "order.address.xwk";
	public static final String XWK_ORDER_URL_NULL = "order.address.xwk.null";
	/**
	 * 急诊
	 */
	public static final String JZ_ORDER_URL = "order.address.jz";
	public static final String JZ_ORDER_URL_NULL = "order.address.jz.null";
	/**
	 * 整形美容科
	 */
	public static final String ZXMRK_ORDER_URL = "order.address.zxmrk";
	public static final String ZXMRK_ORDER_URL_NULL = "order.address.zxmrk.null";
	/**
	 * his库的连接串
	 */
	public static final String HIS_DB = "order.his.db";
	
	
	/**
	 * 把变异页面地址发给医嘱端，让他调用打开的地址
	 */
	public static final String ORDER_VAIRA = "order.vaira";
	
	
	/**
	 * 配置文件里报表所在tomcat服务器ip
	 */
	public static final String REPORT = "report.url";

//2011.09.22新加   修改jhDatabase为JDBC方式，以下为连接参数
	
	/**
	 * 配置文件里数据库类型
	 */
	public static final String DB_TYPE = "Db_Type";
	
	/**
	 * 配置文件里数据库驱动名
	 */
	public static final String DB_DRIVER = "Db_Driver";
	
	/**
	 * 配置文件里连接串
	 */
	public static final String DB_CONNSTRING = "Db_ConnString";
	
	/**
	 * 配置文件里用户名
	 */
	public static final String DB_USER = "Db_User";
	
	/**
	 * 配置文件里用户密码
	 */
	public static final String DB_PASSWORD = "Db_Password";
	

	
	

	public PropertiesUtil() {
		property = new HashMap<String, String>();
	}
	
	static {
		try{
			reloadAll();
		} catch(Exception e) {	
			throw new RuntimeException(e.toString());
		}
    }
    
	/**
	 * get 根据关键字，获取配置文件中相关的值
	 * @param key 关键字
	 * @return 配置值
	 */
	public static String get(String key) {
		String str = "";
		if(property != null && property.containsKey(key))
			str = (String)property.get(key);            	
		return str;
	}
	
	private static void reloadAll() {
    	if(property == null)property = new HashMap<String, String>();
    	strageMsg();
    }

	private static void strageMsg() {
    	HashMap<String, String> map = new HashMap<String, String>();

    	Properties p = new Properties();
        try {
            p.load((PropertiesUtil.class).getClassLoader().getResourceAsStream("config.properties"));
            
            String key;
            String value;
            for(Enumeration<?> enu = p.propertyNames(); enu.hasMoreElements(); map.put(key, value))
            {
                key = (String)enu.nextElement();
                value = p.getProperty(key);
            }
        } catch(FileNotFoundException e) {
        	throw new RuntimeException(e.toString());
        } catch(IOException io){
        	throw new RuntimeException(io.toString());
        }
    	property = map;
    }
}
