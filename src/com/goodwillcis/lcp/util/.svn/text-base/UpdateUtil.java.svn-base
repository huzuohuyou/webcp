/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：UpdateUtil .java
// 文件功能描述：更新管理页面所需要的方法
// 创建人：段英华 
// 创建日期：2011/08/18
// 
//----------------------------------------------------------------*/

package com.goodwillcis.lcp.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;

public class UpdateUtil {
	
static final int BUFFER = 2048;
	
	DatabaseClass dbc = LcpUtil.getDatabaseClass();

	/**
	 * ����ݸ��ÿҳ���ֽ���� ������ļ�
	 * @param strFileName
	 * @param iPageId
	 * @param iFileSize
	 * @param iByteSize
	 * @param bData
	 * @return
	 */
	public int FunSetFileByteByPage(String strFileName, int iByteSize, int iFileSize, int iPageId, byte bData[])
    {
        if(strFileName.isEmpty())
        {
            return -1;
        }
        if(iPageId < 0)
        {
            return -1;
        }
        if(iFileSize < 0)
        {
            return -1;
        }
        if(iByteSize < 0)
        {
            return -1;
        }
        if(bData == null)
        {
            return -1;
        }
        if(iByteSize > iFileSize)
        {
            return -1;
        }
        if(bData.length < 1)
        {
            return -1;
        }
//        if(bData.length < iByteSize*10000)
//        {
//        	System.out.println(bData.length);
//            return -1;
//        }
        File ff = new File(strFileName);
        if(ff.exists())
        {
            if(iPageId == 0 && !ff.delete())
            {
                return -5;
            }
        } else
        if(iPageId > 0)
        {
            return -2;
        }
        
        
        int iPosStart = iPageId * 10000;
        int iPreWriteSize = 10000;
        int iPageCount = iFileSize / 10000;
        if(iFileSize % 10000 > 0)
        {
            iPageCount++;
        }
        if(iPageId + 1 == iPageCount)
        {
            iPreWriteSize = iFileSize % 10000;
        }
        if(iByteSize != iPreWriteSize)
        {
            return -3;
        }
        if(iPageId >= iPageCount)
        {
            return -4;
        }
        int iRes = 0;
        try
        {
            RandomAccessFile raf = new RandomAccessFile(ff, "rw");
            raf.seek(iPosStart);
            raf.write(bData);
            raf.close();
            iRes = iByteSize;
        }
        catch(FileNotFoundException e)
        {
            iRes = -11;
        }
        catch(IOException e1)
        {
            iRes = -12;
        }
        return iRes;
    }
	
	
	/**
	 * ȡ�ĵ�ǰϵͳʱ��
	 * @return
	 */
	public String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-d kk:mm:ss");//�������ڸ�ʽ
	    return df.format(new Date());// new Date()
	}
	
	public String getNowTime1(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_kkmmss");//�������ڸ�ʽ
	    return df.format(new Date());// new Date()
	}
	
	/**
	 * ���һ����ַȥ���ض�Ӧ��xml�ļ�
	 */
	public byte[] getLoadXml(String xmlPath){
		int i =0;
		ByteArrayOutputStream out= new ByteArrayOutputStream();
		FileInputStream in = null;
		try {
			in = new FileInputStream(xmlPath);
			while((i=in.read()) != -1 ){
				out.write(i);
			}
			byte[] bb = out.toByteArray();
			return bb;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * ȡ����ǰ�ϴ�zip��Ĵ��·��
	 * @param hospitalId
	 * @return
	 */
	public String getDataPath(int hospitalId){
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
		String sql = "select UPLOAD_FILE from DCP_SYN_UPLOAD where HOSPITAL_ID = "+ hospitalId + " order by CREATE_TIME desc";
		String dataPath = dbc.FunGetDataSetBySQL(sql).FunGetDataAsStringById(0, 0);
		return dataPath;
	}
	
	
	/**
	 * ȡ�õ�ǰZIP������
	 * @param hospitalId
	 * @return
	 */
	public String getDataBagName(int hospitalId){
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
		String sql = "select UPLOAD_NAME from DCP_SYN_UPLOAD where HOSPITAL_ID = "+ hospitalId + " order by CREATE_TIME desc";
		String bagName = dbc.FunGetDataSetBySQL(sql).FunGetDataAsStringById(0, 0);
		return bagName;
	}
	
	
	/**//*
	    * inputFileName ����һ���ļ���
	    * zipFileName ���һ��ѹ���ļ���
	    */
	     public void zipFolder(String srcFolder, String destZipFile) throws Exception {
	        java.util.zip.ZipOutputStream zip = null;
	        FileOutputStream fileWriter = null;

	        fileWriter = new FileOutputStream(destZipFile);
	        zip = new java.util.zip.ZipOutputStream(fileWriter);

	        addFolderToZip("", srcFolder, zip);
	        zip.flush();
	        zip.close();
	      }

	    private void addFolderToZip(String path, String srcFolder, ZipOutputStream zip)
	    throws Exception {
		  File folder = new File(srcFolder);
		
		  for (String fileName : folder.list()) {
		    if (path.equals("")) {
		      addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
		    } else {
		      addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
		    }
		  }
	    }
	    
		    private void addFileToZip(String path, String srcFile, ZipOutputStream zip)
		    throws Exception {
		
			  File folder = new File(srcFile);
			  if (folder.isDirectory()) {
			    addFolderToZip(path, srcFile, zip);
			  } else {
			    byte[] buf = new byte[1024];
			    int len;
			    System.out.println(srcFile);
			    FileInputStream in = new FileInputStream(srcFile);
			    zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			    while ((len = in.read(buf)) > 0) {
			      zip.write(buf, 0, len);
			    }
			  }
		}
		    
//	private String formatDate(String oldDate){
//		if(oldDate != null){
//		
//		String tmp = oldDate.replace("T", " ");
//		String tmp1 = tmp.replaceAll("-", "/");
//		String s1 = tmp1.substring(0, 19);
//		System.out.println(s1);
//		return s1;		
//		}
//		return null;
//	}
	
	public byte[] FunGetFileByteByPage(int iPageId, int iFileSize, String strFileName)
    {
        if(iPageId < 0)
        {
            return null;
        }
        if(strFileName.isEmpty())
        {
            return null;
        }
        int iMaxBufSize = 10000;
        File ff = new File(strFileName);
        int iLen = (int)ff.length();
        if(iLen != iFileSize)
        {
            return null;
        }
        int iPageCount = iLen / iMaxBufSize;
        int iPosRead = iLen % iMaxBufSize;
        if(iPosRead > 0)
        {
            iPageCount++;
        }
        if(iPageId >= iPageCount)
        {
            return null;
        }
        int iPosStart = iPageId * iMaxBufSize;
        if(iPageId + 1 != iPageCount)
        {
            iPosRead = iMaxBufSize;
        }
        byte bRes[] = (byte[])null;
        try
        {
            DataInputStream infile = new DataInputStream(new BufferedInputStream(new FileInputStream(ff)));
            int ii = infile.skipBytes(iPosStart);
            if(ii == iPosStart)
            {
                bRes = new byte[iPosRead];
                int jj = infile.read(bRes, 0, iPosRead);
                if(jj != iPosRead)
                {
                    bRes = (byte[])null;
                }
            } else
            {
                bRes = (byte[])null;
            }
            infile.close();
        }
        catch(FileNotFoundException e)
        {
            bRes = (byte[])null;
        }
        catch(IOException e)
        {
            bRes = (byte[])null;
        }
        return bRes;
    }

	/**
	 *  �ѵ�ǰ����Ϣд��info��־��    DCP_LOG_INFO
	 *
	 */
	public int logInfo(int logId,String logType, String logScr, String logContent1,String logContent2){

		if(logType==null){
			return -1;
		}
		if(logScr==null){
			return -1;
		}
		if(logContent1==null){
			return -1;
		}
		if(logContent2==null){
			return -1;
		}
		String date = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date());
		String infoSql = "INSERT INTO DCP_LOG_INFO(LOG_DATE,LOG_ID,LOG_TYPE,LOG_SCR,LOG_CONTENT1,LOG_CONTENT2,SYS_IS_DEL,SYS_LAST_UPDATE) VALUES (to_date('"+date+"', 'YYYY-MM-dd HH24:MI:SS'),'"+logId+"','"+logType+"','"+logScr+"','"+logContent1+"','"+logContent2+"','0',to_date('"+new SimpleDateFormat("yyyy-MM-d kk:mm:ss").format(new Date())+"', 'YYYY-MM-dd HH24:MI:SS'))";
		int x = dbc.FunRunSQLCommand(dbc.FunGetSvrKey(), infoSql);
		if(x<=0){
			return -1;
		}
		
		return 1;
	}
	
	/**
	 * �ѵ�ǰ��Ϣд��download��־��  DCP_LOG_DOWNLOAD
	 * �����������°������־��д��  ��ͬ�������۰汾���سɹ����   ����������İ汾�ŷ��ڱ�ע��
	 * @param args
	 */
	public int logDownload(int hospitalId,int downloadState,String downloadTime,String downloadIp,String versionId){
		DataSetClass dsc = new DataSetClass();
		int downloadId = 0;

		dsc = dbc.FunGetDataSetBySQL("select max(UPLOAD_ID) as uploadId from DCP_LOG_UPLOAD where HOSPITAL_ID = '"+hospitalId+"'");
		if(dsc.FunGetDataAsStringById(0, 0).isEmpty()){
			downloadId = 1;
		}else{
		
			downloadId = dsc.FunGetDataAsNumberById(0, 0).intValue();
		}
		
		String remark = null;
		if(versionId == null){
		}else{
			remark = "ҽԺ���Ϊ"+hospitalId+"�������صİ汾�����" + versionId;
		}
		
		String downloadSql = "INSERT INTO DCP_LOG_DOWNLOAD(DOWNLOAD_ID,HOSPITAL_ID,DOWNLOAD_TIME,DOWNLOAD_STATE,DOWNLOAD_IP,REMARK,SYS_IS_DEL,SYS_LAST_UPDATE) VALUES ('" +(downloadId+1)+ "','"+hospitalId+"',to_date('"+downloadTime+"', 'YYYY-MM-dd HH24:MI:SS'),'"+downloadState+"','"+downloadIp+"','"+remark+"','0',to_date('"+new SimpleDateFormat("yyyy-MM-d kk:mm:ss").format(new Date())+"', 'YYYY-MM-dd HH24:MI:SS'))";
		int x = dbc.FunRunSQLCommand(dbc.FunGetSvrKey(), downloadSql);
		if(x<=0){
			return -1;
		}
		return 1;
	}
	
	/**
	 * �ѵ�ǰ��Ϣд��upload��־��  DCP_LOG_UPLOAD
	 * �ڶ������   
	 * ��Ϊ�ñ���ҽԺID���ϴ�ID����ֵ��Ϊ���� ���Ը�ݲ�ͬ��ҽԺ��1��ʼ�����汾
	 * @param args
	 */
	public int logUpload(int hospitalId,int updateState,String endData,String updateIp){
		DataSetClass dsc = new DataSetClass();
		int uploadId = 0;
		String remark = null;
		dsc = dbc.FunGetDataSetBySQL("select max(UPLOAD_ID) as uploadId from DCP_LOG_UPLOAD where HOSPITAL_ID = '"+hospitalId+"'");
		if(dsc.FunGetDataAsStringById(0, 0).isEmpty()){
			uploadId = 1;
		}else{
		
		uploadId = dsc.FunGetDataAsNumberById(0, 0).intValue();
		}
		//����ϴθ�����ʧ�ܵģ���Ҫ���ϴ�uploadId���ڱ��θ��µı�ע����
		boolean b = dbc.FunGetDataSetBySQL("select UPDATE_STATE from DCP_LOG_UPLOAD t where t.upload_id = '"+(uploadId-1)+"'").FunGetDataAsStringById(0, 0).isEmpty();
		 if(b == false && dbc.FunGetDataSetBySQL("select UPDATE_STATE from DCP_LOG_UPLOAD t where t.upload_id = '"+(uploadId-1)+"'").FunGetDataAsNumberById(0, 0).intValue() == 0){
				remark = "���θ��µİ����� "+(uploadId-1);
		}
		

		String uploadSql = "INSERT INTO DCP_LOG_UPLOAD(UPLOAD_ID,HOSPITAL_ID,UPDATE_TIME,UPDATE_STATE,UPDATE_IP,REMARK,SYS_IS_DEL,SYS_LAST_UPDATE) VALUES ('" +(uploadId+1)+ "','"+hospitalId+"',to_date('"+endData+"', 'YYYY-MM-dd HH24:MI:SS'),'"+updateState+"','"+updateIp+"','"+remark+"','0',to_date('"+new SimpleDateFormat("yyyy-MM-d kk:mm:ss").format(new Date())+"', 'YYYY-MM-dd HH24:MI:SS'))";
		int x = dbc.FunRunSQLCommand(dbc.FunGetSvrKey(), uploadSql);
		if(x<=0){
			return -1;
		}
		
		return 1;
	}
	
	
	/**
	 * ����ֶ��ض���ֵ����ȡ��Ӧ���ֶε�ֵ  ��ArrayList<String>��ʽȡ��
	 * @param args
	 */
	public ArrayList<String> FunGetDataByValue(DataSetClass dsc, String colName, String value, String purName1, String purName2){
		//�õ�����
		int rows = dsc.FunGetRowCount();
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0; i<rows; i++){
			String s = dsc.FunGetDataAsStringByColName(i, colName);
			if(s.equals(value)){
				String purValue1 = dsc.FunGetDataAsStringByColName(i, purName1);
				String purValue2 = dsc.FunGetDataAsStringByColName(i, purName2);
				list.add(purValue1 +"|"+purValue2);
			}
		}
		return list;
	}
	
	/**
	 * �ٿͻ��˵��÷���ʱ��ȡ���IP
	 * @param args
	 */
    public String getClientIp(){
        MessageContext  mc  =  MessageContext.getCurrentContext();
        HttpServletRequest  request  =  (HttpServletRequest)  mc.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
        return request.getRemoteAddr();
      }
	
	public static void main(String[] args){
		String filePath = System.getProperty("user.dir");
		System.out.println(filePath);
	}
	
	public ArrayList<String> FunGetDataByValue1(DataSetClass dsc, String colName, String value, String purName){
		//�õ�����
		int rows = dsc.FunGetRowCount();
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0; i<rows; i++){
			String s = dsc.FunGetDataAsStringByColName(i, colName);
			if(s.equals(value)){
				String purValue = dsc.FunGetDataAsStringByColName(i, purName);
				list.add(purValue);
			}
		}
		return list;
	}
	
	public String getDataWithType(String dType, String value){
		if(!value.equals("")){
			if(dType.equals("3")){
				String result = "'"+value+"'";
				return result;
			}else if(dType.equals("1")){
				return value;
			}else if(dType.equals("2")){
				return "to_date("+ value+ ", 'YYYY/MM/DD HH24:MI:SS')";
			}
		}
		return null;
	}
	
	/**
	 * 根据从数据库ws取到的dataset取到的所有数据类型拼出对应的数据样子
	 * @param dataType
	 * @param dValue
	 * @return
	 */
	public String getDataWithType1(String dataType, String dValue){
		dValue = dValue.replace("\n", "\\n");
		if(!dValue.equals("")){
			if(dataType.equals("xs:string")){
				String result = "'"+dValue+"'";
				return result;
			}else if(dataType.equals("xs:decimal")){
				return dValue;
			}else if(dataType.equals("xs:dateTime")){
				String date = this.formatDate(dValue);
				return "to_date('"+ date + "', 'YYYY/MM/DD HH24:MI:SS')";
			}
		}
		return null;
	}
	
	/**
	 * 拿到解压缩文件夹里的文件上一层目录
	 * @param args
	 */
	public String getRootFolder(String folderPath){
		String rootPath = folderPath + "/";
		File file = new File(rootPath);      
        File[] array = file.listFiles(); 
        return array[0].getName();
	}
	
	public String formatDate(String oldDate){
		if(oldDate != null){
		
		String tmp = oldDate.replace("T", " ");
		String tmp1 = tmp.replaceAll("-", "/");
		String s1 = tmp1.substring(0, 19);
		return s1;		
		}
		return null;
	}
	
	public String getLcpTableName(String tableName){
		String s1 = "MASTER";
		String s2 = "NODE";
		String result = tableName.replaceAll("DCP", "LCP");
		if(tableName.lastIndexOf(s1)>0){
			return result;
		}else if(tableName.lastIndexOf(s2)>0){
			return result;
		}else 
			return null;
		
	}
	
    //院内跟中心update的sql
	public String upDate(DataSetClass dsc, String tableName,String condition, int z, String hId,boolean isLcp){
		//如果得到的行数大于0(数据库有值)
			//update数据
			int col = dsc.FunGetColCount();
			//得到该XML的列数
			ArrayList<String> aa = new ArrayList<String>();
			ArrayList<String> bb = new ArrayList<String>();
			ArrayList<String> vv = new ArrayList<String>();
			//取到每一列的字段名，字段类型，对应行的数据
			String cc = "set ";
			if(isLcp == true){
			cc = "set HOSPITAL_ID=1,";
			}
			
			for(int l=0; l<col; l++){
				aa.add(dsc.FunGetColTypeById(l));
				bb.add(dsc.FunGetColNameById(l));
				vv.add(dsc.FunGetDataAsStringByColName(z, bb.get(l)));
				
				String b = null;
					if(l < col-1){
						b = ", ";
					}else{
						b = " ";
					}
					cc += bb.get(l) + "=" + this.getDataWithType1(aa.get(l), vv.get(l)) + b;
			}
			String updateSql = "update " + tableName + " " + cc + " where " + condition;
			System.out.println("测试sql结果是  = "+updateSql);
			return updateSql;
		}
	  
    //院内跟中心insert的sql
	public String insert(DataSetClass dsc, String tableName,String condition, int z,String hId,boolean isLcp){
		//insert数据
		int col = dsc.FunGetColCount();
		//得到该XML的列数
		ArrayList<String> aa = new ArrayList<String>();
		ArrayList<String> bb = new ArrayList<String>();
		ArrayList<String> vv = new ArrayList<String>();
		//取到每一列的字段名，字段类型，对应行的数据
		String sql1 = "insert into " + tableName;
		String b = null;
		String cc = "( ";
		String dd = "( ";
			if(isLcp == true){
				cc = "( HOSPITAL_ID ,";
				dd = "( "+hId +",";
			}
		for(int l=0; l<col; l++){
			aa.add(dsc.FunGetColTypeById(l));
			bb.add(dsc.FunGetColNameById(l));
			vv.add(dsc.FunGetDataAsStringByColName(z, bb.get(l)));
	
	
				if(l < col-1){
					b = ", ";
				}else{
					b = " )";
				}
				cc += "" + bb.get(l) + b;
				dd += this.getDataWithType1(aa.get(l), vv.get(l)) + b;
			}
		String insertSql = sql1 + cc + " values " + dd + "; ";
		return insertSql;
	}
	
	public String findCurrDeptNo(String patient_id){
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			try {
				conn = OracleJdbcUtils.getConnection();
				st = conn.createStatement();
				String findSql = "select max(t.dept_code) as DEPTNO from mr_on_line t where t.patient_id = '"+patient_id.trim().split("_")[0]+"' order by t.request_date_time desc";
				rs = st.executeQuery(findSql);
				String deptNo = "";
				
				if(rs.next() == false){
					return "";
				}
				
				while(rs.next()){
					deptNo = rs.getString("DEPTNO").trim();				
				}
				return deptNo;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "";
			}
			
		} finally{
			OracleJdbcUtils.free1(rs, st, conn);
		}
		
	}

}
