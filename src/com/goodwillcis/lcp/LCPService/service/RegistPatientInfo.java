/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： RegistPatientInfo .java
// 文件功能描述：ws调用函数接口
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.LCPService.service;

public interface RegistPatientInfo {

	/**
	 * 患者简单注册接口
	 * @param inSize
	 * @param inRegData
	 * @return 返回值大于0的值表示注册成功     小于0表示方法执行中出错
	 */
	public int funCPRegPatient(String patientNo,int inSize, byte[] inRegData);
	/**
	 * 返回值说明：
	 * 			-1：病人下诊断但是不匹配
	 * 			-2：病人没有下诊断
	 * 			-3：病人没有注册
	 * 			 0：未纳入
	 * 			 1：已纳入
	 * 			11：已完成
	 *			21：已退出
	 *			99：不纳入
	 */
	public int funCPGetStatus(String inPatiID);
	
	/**
	 * 上传诊断记录数据
	 * @param inPatiID
	 * @param inSize
	 * @param inDiagData
	 * @return 返回值大于0表示正确上传     返回值小于0表示没有执行成功
	 */
	public int funCPSetDiagnosisOrOperator(String inPatiID, int inSize, byte[] inDiagData,String income_type);
	/**
	 * 上传诊疗记录数据 
	 * @param inPatiID
	 * @param inSize
	 * @param inDoctData
	 * @return 返回值大于0表示正确上传     返回值小于0表示没有执行成功
	 */ 
    public int funCPSetDoctorItem(String inPatiID, int inSize, byte[] inDoctData);
    
    /**
	 * 上传护理记录数据  
	 * @param inPatiID
	 * @param inSize
	 * @param inNurData
	 * @return 返回值大于0表示正确上传     返回值小于0表示没有执行成功
	 */ 
    public int funCPSetNurseItem(String inPatiID, int inSize, byte[] inNurData);

    /**
	 * 上传医嘱记录数据     
	 * @param inPatiID
	 * @param inSize
	 * @param inOrderData
	 * @return 返回值大于0表示正确上传     返回值小于0表示没有执行成功
	 */ 
    public int funCPSetOrderItem(String inPatiID, int inSize, byte[] inOrderData);
    /**
     * 查找节点内容
     * @param CpID
     * @param inNodeID
     * @return
     */
    public byte[] funCpGetNodeInfo(String patientID, int inNodeID,int inItemType);
    /**
     * 通过patiID查找病人所处的节点号
     * @param patiID
     * @return
     */
    public int funCpGetIDByPatient(String patiID);
    /**
     * 注册首页
     * @param inPatiID
     * @param inSize
     * @param inPatiData
     * @return
     */
    public int funCPSetFirstPage(String inPatiID, int inSize, byte[] inPatiData);
    /**
     * 查询匹配
     * @param tableName
     * @param localCode
     * @return
     */
    public byte[] funCpGetMatchItem(String tableName,String localCode);
    /**
     * 取得查询的xml数据
     * @return
     */
    public byte[] funCpGetData();
    /**
     * 函数操作成功与否
     * @return 1:表示成功；-1表示失败
     */
    public int funCpIsFunOpSuc();
    /**
     * 查询返回的xmlbyte长度
     * @return
     */
    public int funCpGetDataLen();
    /**
	 * 更新出院信息
	 * @param size
	 * @param data
	 * @param patientNo
	 * @return
	 */
	public int funCPDischarge(int size, byte[] data, String patientNo);
	/**
	 * 获取医嘱变异字典数据包
	 * @param a （预留参数，无意义）
	 * @return
	 */
	public int funCPGetDictVariation(String a);
	/**
	 * 获取变异记录数据包
	 * @param patientNo
	 * @param data
	 * @param size
	 * @return 1:成功;
	 * 			-1:获取数据异常
	 * 			-2:size为0或者size！=data的长度
	 * 			-3:patientNo为空
	 * 			-4:传过来的流中行数为0
	 * 			-5:传过来的AUTO_ID为空
	 */
	public int funCPGetOrderVariation(String patientNo,int inNodeID);
	/**
	 * 向LCP提交患者费用信息
	 * @param size
	 * @param data
	 * @param patientNo
	 * @return
	 */
	public int funCPSetFee(int size, byte[] data, String patientNo);
	 /**
	  * 更新医嘱方案遴选状态
	  * @param patientNo
	  * @param inStateType
	  * @param size
	  * @param data
	  * @return
	  */
	 public int funCPSetItemSet(String patientNo, int inStateType, int size, byte[] data);
	 /**
	  * 向LCP更新患者检验结果信息
	  * @param patientNo
	  * @param data
	  * @param size
	  * @return
	  */
	 public int funCPSetLIS(String patientNo,byte[]data,int size);
	 /**
	 * 更新医嘱状态
	 * @param patientNo
	 * @param data
	 * @param size
	 * @return 	 1:成功;
	 * 			-1:插入数据异常
	 * 			-2:size为0或者size！=data的长度
	 * 			-3:patientNo为空
	 * 			-4:传过来的流中行数为0
	 * 			-5:传过来的主键值为空
	 */
	public int FunCPSetOrderState (String patientNo,byte[]data,int size);
	/**
	 * 向LCP记录患者医嘱变异信息
	 * @param patientNo
	 * @param data
	 * @param size
	 * @return	 1:成功;
	 * 			-1:插入数据异常
	 * 			-2:size为0或者size！=data的长度
	 * 			-3:patientNo为空
	 * 			-4:传过来的流中行数为0
	 * 			-5:传过来的主键值为空
	 */
	public int funCPSetOrderVariation(String patientNo,byte[]data,int size);
	/**
	 * 向LCP更新患者检查报告信息
	 * @param size
	 * @param data
	 * @param patientNo
	 * @return
	 */
	 public int funCPSetPACS(int size, byte[] data, String patientNo);
	 public int funCPGetStatus1(String inPatiID);
}
