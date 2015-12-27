/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： LCPServiceSOAPImpl .java
// 文件功能描述：ws调用的方法的接口实现类
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
/**
 * LCPServiceSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.goodwillcis.lcp.LCPService;

import java.util.Date;

import org.apache.log4j.Logger;

import com.goodwillcis.lcp.LCPService.service.RegistPatientInfo;
import com.goodwillcis.lcp.LCPService.service.impl.RegistPatientInfoImpl;
import com.goodwillcis.lcp.model.DataSet;

public class LCPServiceSOAPImpl implements com.goodwillcis.lcp.LCPService.LCPService_PortType{
	private static Logger logger = Logger.getLogger ( LCPServiceSOAPImpl.class.getName () ) ;

	/**
	 * 患者简单注册接口    inRegData 见schema inSize为数据大小
	 * 
	 * 返回值大于0的值表示注册成功  
     * 
     *       小于0表示方法执行中出错
	 */
	public int funCPRegPatient(int inSize, byte[] inRegData, java.lang.String inPatiID) throws java.rmi.RemoteException {
		logger.debug("-----------------------------funCPRegPatient---------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
		RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPRegPatient(inPatiID,inSize, inRegData);
    	long end=new Date().getTime();
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	logger.debug("funCPRegPatient()耗时="+(end-start));
    	return a;
    }
	 /**
     * 查询所在路径的状态   inPatiID为患者ID   
     * 
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
    public int funCPGetStatus(java.lang.String inPatiID) throws java.rmi.RemoteException {
		logger.debug("--------------------------funCPGetStatus------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa=info.funCPGetStatus(inPatiID);
    	long end=new Date().getTime();
    	logger.debug("funCPGetStatus()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
       	return aa;
    }
    /**
     * 记录患者首页信息  inPatiID是患者ID   inSize是上传数据的大小   inPatiData是数据的内容
     * 
     * 返回值大于0表示记录数据成功
     * 
     *       小于0表示方法执行中出错
     */
    public int funCPSetFirstPage(java.lang.String inPatiID, int inSize, byte[] inPatiData) throws java.rmi.RemoteException {
		logger.debug("-------------------------funCPSetFirstPage-------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa= info.funCPSetFirstPage(inPatiID, inSize, inPatiData);
    	long end=new Date().getTime();
    	logger.debug("funCPSetFirstPage()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return aa;
    }
    /**
     * 上传诊断记录数据   inPatiID是患者ID   inSize是上传数据的大小   inDiagData是数据的内容
     * 
     * 返回值大于0表示正确上传
     * 
     * 返回值小于0表示没有执行成功
     */
    public int funCPSetDiagnosis(java.lang.String inPatiID, int inSize, byte[] inDiagData) throws java.rmi.RemoteException {
		logger.debug("---------------------------funCPSetDiagnosis-----------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPSetDiagnosisOrOperator(inPatiID, inSize, inDiagData, "诊断");
    	long end=new Date().getTime();
    	logger.debug("funCPSetFirstPage()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return a;
    }
    /**
     * 上传手术记录数据   inPatiID是患者ID   inSize是上传数据的大小   inOperData是数据的内容
     * 
     * 返回值大于0表示正确上传
     * 
     * 返回值小于0表示没有执行成功
     */
    public int funCPSetOperation(java.lang.String inPatiID, int inSize, byte[] inOperData) throws java.rmi.RemoteException {
		logger.debug("--------------------------funCPSetOperation------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPSetDiagnosisOrOperator(inPatiID, inSize, inOperData, "手术");
    	long end=new Date().getTime();
    	logger.debug("funCPSetOperation()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return a;
    }
    /**
     * 上传诊疗记录数据   inPatiID是患者ID   inSize是上传数据的大小   inDoctData是数据的内容
     * 
     * 返回值大于0表示正确上传
     * 
     * 返回值小于0表示没有执行成功
     */
    public int funCPSetDoctorItem(java.lang.String inPatiID, int inSize, byte[] inDoctData) throws java.rmi.RemoteException {
		logger.debug("-------------------------funCPSetDoctorItem-------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPSetDoctorItem(inPatiID, inSize, inDoctData);
    	long end=new Date().getTime();
    	logger.debug("funCPSetDoctorItem()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return a;
    }
    /**
     * 上传护理记录数据   inPatiID是患者ID   inSize是上传数据的大小   inNurData是数据的内容
     * 
     * 返回值大于0表示正确上传
     * 
     * 返回值小于0表示没有执行成功
     */
    public int funCPSetNurseItem(java.lang.String inPatiID, int inSize, byte[] inNurData) throws java.rmi.RemoteException {
		logger.debug("---------------------------funCPSetNurseItem----------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPSetNurseItem(inPatiID, inSize, inNurData);
    	long end=new Date().getTime();
    	logger.debug("funCPSetNurseItem()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return a;
    }
    /**
     * 上传医嘱记录数据   inPatiID是患者ID   inSize是上传数据的大小   inOrderData是数据的内容
     * 
     * 返回值大于0表示正确上传
     * 
     * 返回值小于0表示没有执行成功
     * 
     */
    public int funCPSetOrderItem(java.lang.String inPatiID, int inSize, byte[] inOrderData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int a=info.funCPSetOrderItem(inPatiID, inSize, inOrderData);
    	long end=new Date().getTime();
    	logger.debug("funCPSetOrderItem()耗时="+(end-start));
    	return a;
    }
    /**
     * 接口名称	FunCPGetItemSet	向LCP请求获取方案信息数据包
		返回值名称	返回类型	返回说明
		outRes	int	接口执行结果，大于零为调用成功，否则失败
		outSize	int	返回数据包大小
		outItemData	hexBinary	返回数据包（XML）内容
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inNodeID	int	路径节点序号，0为获得当前节点的数据包，其他为相应的节点数据包
		inItemType	int	要获取方案数据包的类型
		11、诊疗工作方案节点第一层数据包
		12、诊疗工作方案节点第二层数据包
		13、诊疗根据病人ID和节点号查对应工作项的编码，由此查出match表里对应本地编号，以dataset格式返回
		21、护理工作方案节点第一层数据包
		22、护理工作方案节点第二层数据包
		23、护理根据病人ID和节点号查对应工作项的编码，由此查出match表里对应本地编号，以dataset格式返回
		31、医嘱工作方案节点第一层数据包
		32、医嘱工作方案节点第二层数据包
		33、医嘱根据病人ID和节点号查对应工作项的编码，由此查出match表里对应本地编号，以dataset格式返回

     */
    public void funCPGetItemSet(java.lang.String inPatiID, int inNodeID, int inItemType, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.IntHolder outSize, javax.xml.rpc.holders.ByteArrayHolder outItemData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	byte[]nextNode=info.funCpGetNodeInfo(inPatiID, inNodeID,inItemType);
    	int nextNodeLen=info.funCpGetDataLen();
    	int nextNodeFlag=info.funCpIsFunOpSuc();
    	outRes.value = nextNodeFlag;
        outSize.value = nextNodeLen;
        outItemData.value = nextNode;
        long end=new Date().getTime();
    	logger.debug("funCPGetItemSet()耗时="+(end-start));
    }
    /**
     * 	接口名称	FunGetMatchItem	从lcp对照表中查对应的工作编码
		返回值名称	返回类型	返回说明
		outRes	int	接口执行结果，大于零为调用成功，否则失败
		outMatchData	hexBinary	返回查到的对应match表里的工作编码（xml）
		参数名称	参数类型	参数说明
		localCode	string	本院编码
		inMatchType	string	传所查match表的名字

     */
    public void funGetMatchItem(java.lang.String inMatchType, java.lang.String localCode, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.ByteArrayHolder outMatchData) throws java.rmi.RemoteException {
		logger.debug("------------------------funGetMatchItem--------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	byte[]aa=info.funCpGetMatchItem(inMatchType, localCode);
    	outRes.value = info.funCpIsFunOpSuc();
        outMatchData.value = aa;
        long end=new Date().getTime();
    	logger.debug("funGetMatchItem()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    }
   
    public int funCPSetEmrDoc(java.lang.String inPatiID, int inSize, byte[] inEmrDocData) throws java.rmi.RemoteException {
        return -3;
    }
    /**
     * 	接口名称	FunCPDischarge	患者出院登记
		返回值类型	int	返回大于零表示更新成功否则失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	int	患者信息数据包的大小
		inDisData	hexBinary	患者出院信息数据包内容（XML）

     */
    public int funCPDischarge(int inSize, byte[] inDisData, java.lang.String inPatiID) throws java.rmi.RemoteException {
		logger.debug("-----------------------funCPDischarge---------------------------------------------");
		String sql_lianjieshu="select count(*)ljs from v$session";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用前连接数"+dataSet.funGetFieldByCol(0, "LJS"));
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa= info.funCPDischarge(inSize, inDisData, inPatiID);
    	long end=new Date().getTime();
    	logger.debug("funCPDischarge()耗时="+(end-start));
    	dataSet.funSetDataSetBySql(sql_lianjieshu);
		logger.debug("调用后连接数"+dataSet.funGetFieldByCol(0, "LJS"));
    	return aa;
    }
    /**
     * ·接口名称	FunCPSetOrderState	更新医嘱状态
		返回值类型	int	返回成功记录的条目数，小于零表示记录失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	Int	医嘱状态数据包大小
		inOrderStateData	hexBinary	医嘱状态数据包的内容（xml）

     */
    public int funCPSetOrderState(java.lang.String inPatiID, int inSize, byte[] inOrderStateData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa= info.FunCPSetOrderState(inPatiID, inOrderStateData, inSize);
    	long end=new Date().getTime();
    	logger.debug("funCPSetOrderState()耗时="+(end-start));
    	return aa;
    }
    /**
     * 	接口名称	FunCPGetOrderVariation	获取变异记录数据包
		返回值名称	返回类型	返回说明
		outRes	int	接口执行结果，大于零为调用成功，否则失败
		outVarBagData	hexBinary	返回变异记录数据包内容
		outSize	int	返回数据包大小
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inOrderVarData	hexBinary	患者所对应的变异数据的autoId集合（xml，可以批量接收）
		inSize	Int	医嘱状态数据包大小

     */
    public void funCPGetOrderVariation(java.lang.String inPatiID, int inNodeID, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.ByteArrayHolder outVarBagData, javax.xml.rpc.holders.IntHolder outSize) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	outRes.value = info.funCPGetOrderVariation(inPatiID, inNodeID);
        outVarBagData.value = info.funCpGetData();
        outSize.value = info.funCpGetDataLen();
        long end=new Date().getTime();
    	logger.debug("funCPGetOrderVariation()耗时="+(end-start));
    }
    /**
     * 	接口名称	FunCPSetOrderVariation	向LCP记录患者医嘱变异信息
		返回值类型	int	返回成功记录的条目数，小于零表示记录失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	int	患者信息数据包的大小
		inOrderVarData	hexBinary	患者医嘱变异信息数据包内容（XML）

     */
    public int funCPSetOrderVariation(java.lang.String inPatiID, byte[] inOrderVarData, int inSize) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa= info.funCPSetOrderVariation(inPatiID, inOrderVarData, inSize);
    	long end=new Date().getTime();
     	logger.debug("funCPSetOrderVariation()耗时="+(end-start));
     	return aa;
    }
    /**
     * 	接口名称	FunCPSetItemSet	更新医嘱方案遴选状态
		返回值类型	int	返回成功更新的条目数，小于零表示更新失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inStateType	int	表示更新哪一层数据，1代表第一层 2代表第二层
		inSize	int	遴选状态数据包大小
		stateData	hexBinary	患者所对应的遴选数据包（xml）

     */
    public int funCPSetItemSet(java.lang.String inPatiID, int inStateType, int inSize, byte[] stateData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	
    	int aa= info.funCPSetItemSet(inPatiID,inStateType,inSize,stateData);
    	long end=new Date().getTime();
     	logger.debug("funCPSetItemSet()耗时="+(end-start));
     	return aa;
    }
    /**
     * 	接口名称	FunCPSetLIS	向LCP更新患者检验结果信息
		返回值类型	int	返回成功记录的条目数，小于零表示记录失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	int	患者信息数据包的大小
		inLISData	hexBinary	患者检验结果信息数据包内容（XML）

     */
    public int funCPSetLIS(java.lang.String inPatiID, int inSize, byte[] inLISData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa= info.funCPSetLIS(inPatiID, inLISData, inSize);
    	long end=new Date().getTime();
     	logger.debug("funCPSetLIS()耗时="+(end-start));
     	return aa;
    }
    /**
     * 	接口名称	FunCPSetPACS	向LCP更新患者检查报告信息
		返回值类型	int	返回成功记录的条目数，小于零表示记录失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	int	患者信息数据包的大小
		inPACSData	hexBinary	患者检查报告信息数据包内容（XML）

     */
    public int funCPSetPACS(java.lang.String inPatiID, int inSize, byte[] inPACSData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa=  info.funCPSetPACS(inSize, inPACSData, inPatiID);
    	long end=new Date().getTime();
     	logger.debug("funCPSetPACS()耗时="+(end-start));
    	return aa;
    }
    /**
     * 	接口名称	FunCPSetFee	向LCP提交患者费用信息
		返回值类型	int	返回成功记录的条目数，小于零表示记录失败
		参数名称	参数类型	参数说明
		inPatiID	string	患者唯一编码
		inSize	int	患者信息数据包的大小
		inFeeData	hexBinary	患者费用信息数据包内容（XML）

     */
    public int funCPSetFee(java.lang.String inPatiID, int inSize, byte[] inFeeData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	int aa=   info.funCPSetFee(inSize, inFeeData, inPatiID);  	
    	long end=new Date().getTime();
     	logger.debug("funCPSetFee()耗时="+(end-start));
     	return aa;
    }

    public void funCPGetDictVariation(java.lang.String in, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.IntHolder outSize, javax.xml.rpc.holders.ByteArrayHolder outDictData) throws java.rmi.RemoteException {
		logger.debug("--------------------------------------------------------------------");
		long start=new Date().getTime();
    	RegistPatientInfo info=new RegistPatientInfoImpl();
    	outRes.value = info.funCPGetDictVariation(in);
        outSize.value = info.funCpGetDataLen();
        outDictData.value = info.funCpGetData();
        long end=new Date().getTime();
     	logger.debug("funCPGetDictVariation()耗时="+(end-start));
    }

}
