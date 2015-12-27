/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：UploadListImpl.java
// 文件功能描述：院内更新页面接口的实现类
// 创建人：段英华
// 创建日期：2011/08/16
// 
//----------------------------------------------------------------*/
package com.goodwillcis.lcp.service.sync.impl;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.rpc.holders.ByteArrayHolder;
import javax.xml.rpc.holders.IntHolder;
import com.goodwillcis.general.DataSetClass;
import com.goodwillcis.general.DatabaseClass;
import com.goodwillcis.lcp.model.DataSet;
import com.goodwillcis.lcp.util.CommonUtil;
import com.goodwillcis.lcp.util.LcpUtil;
import com.goodwillcis.lcp.util.UpdateUtil;
import com.goodwillcis.lcp.service.sync.UploadList;

public class UploadListImpl implements UploadList {

	//用来放置主键ID 名字 类型的
	private static DataSetClass dsc2 = new DataSetClass();
	//
	private static DataSetClass dsc3 = new DataSetClass();
	
	private static String hosId = null;
	
	UpdateUtil uu = new UpdateUtil();
	
	@Override
	public String getUploadList(int start, int end) {
		// TODO Auto-generated method stub
		DataSet dataSet=new DataSet();

		String sql="select t.download_id, "+
			       "t.download_name, "+
			       "t.download_file, "+
			       "(select update_state "+
			         "from dcp_log_update s "+
			        "where t.download_id = s.update_id) as state "+
			        "from dcp_syn_download t "+
			         "where t.sys_is_del = 0 "+
			         "order by t.download_id";
		dataSet.funSetDataSetBySql(sql,start,end);
		int row=dataSet.getRowNum();
		
		String table="";
		for(int i=0;i<row;i++){
			String state = dataSet.funGetNullFieldByCol(i, "STATE");
			if(state.equals("9")){
				state = "未更新";
			}else if(state.equals("0")){
				state = "更新失败";
			}else if(state.equals("1")){
				state = "更新成功";
			}
			table=table+"<tr height='20' bgcolor='#FFFFFF'  onmouseout='recoverColor(this);' onmouseover='changeColor(this);'>"+
			"<td><div align='center'><span class='STYLE10'>"+dataSet.funGetFieldByCol(i, "DOWNLOAD_NAME")+"</span></div></td>" +
			"<td><div align='center'><span class='STYLE10'>"+dataSet.funGetFieldByCol(i, "DOWNLOAD_FILE")+"</span></div></td>" +
			"<td><div align='center'><span class='STYLE10'>"+state+"</span></div></td>" +
			"<td><div align='center'><span class='STYLE10'><input name='showInfoButton' type='button' value='查看' onclick='showDetail("+dataSet.funGetFieldByCol(i, "DOWNLOAD_ID")+");'/></span></div></td>" +
			"</tr>";
		}
		table=CommonUtil.replactCharacter(table);
		return table;
	}

	@Override
	public int getTotalNum() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(*)RECORDNUM FROM DCP_SYN_DOWNLOAD T WHERE T.SYS_IS_DEL=0";
		DataSet dataSet=new DataSet();
		dataSet.funSetDataSetBySql(sql);
		int recordNum=Integer.parseInt(dataSet.funGetFieldByCol(0, "RECORDNUM"));
		return  recordNum;
	}

	@Override
	public ArrayList<String> loadcsv(int hospitalId, String pathName) {
		// TODO Auto-generated method stub

    	//String ip = dt.getClientIp();
			//解压后文件夹所在的目录
				String folderPath = pathName.substring(0, pathName.length()-4);
			     String rootFolderName = uu.getRootFolder(folderPath);
	            File csv = new File(folderPath+ "/" + rootFolderName +"/index.csv"); // CSV文件
	            BufferedReader br;
	            ArrayList<String> alist=new ArrayList<String>();
				
				try {
					br = new BufferedReader(new FileReader(csv));
					String line;
		            	while((line = br.readLine())!=null){
		            		
							//取CSV每一行的值
							System.out.println(line);
							alist.add(line);
							
						
		            }
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					//dt.logInfo(2, "Update", "["+hospitalId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "file未找到"+e, "医院号是"+hospitalId);
				
				}
				 catch (IOException e) {
						// TODO Auto-generated catch block
					 System.out.println(e);
						//dt.logInfo(2, "Update", "["+hospitalId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "IO错误"+e, "医院号是"+hospitalId);

					}
				System.out.println(alist.size());

					 //dt.logUpload(hospitalId, 1, endTime, null);
					return alist;
	}

	@Override
	public int updateData(int hospitalId, String pathName, String tableName,
			String uprow) {
		// TODO Auto-generated method stub
DatabaseClass dbc = LcpUtil.getDatabaseClass();
    	
    	String hId = hosId;
    	if(hId == null){
    		//用来查医院Id
        	DataSetClass dsc1 = dbc.FunGetDataSetBySQL("select config_value from dcp_sys_config where config_id = '20110516ZP01'");
        	
        	hId = dsc1.FunGetDataAsStringById(0, 0);
        	hosId = hId;
    	}

    	if(dsc2.FunGetRowCount()<=0){
		dsc2 = dbc.FunGetDataSetBySQL("select TABLE_SET_ID, KEY_ID,KEY_NAME,KEY_TYPE from DCP_SYS_TABLE_KEY");
    	}
    	
    	if(dsc3.FunGetRowCount()<=0){
    		dsc3 = dbc.FunGetDataSetBySQL("select TABLE_SET_ID,TABLE_NAME from DCP_SYS_TABLE_SET");
    	}
    	
    	//解压后文件夹所在的目录
//		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa:"+dsc2.FunGetDataAsStringById(0,0));
		
		String folderPath = pathName.substring(0, pathName.length()-4);
		     String rootFolderName = uu.getRootFolder(folderPath);
		int res = 2;
    	try {
    		  
			//取到表名
			//加载xml到DataSetClass
			DataSetClass dsc = new DataSetClass();
			System.out.println("当前执行表是" + tableName);
			int i =0;
			ByteArrayOutputStream out= new ByteArrayOutputStream();
			FileInputStream in = null;
			try {
				in = new FileInputStream(folderPath + "/"+ rootFolderName + "/" + tableName + ".xml");
				while((i=in.read()) != -1 ){
					out.write(i);

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String dString = out.toString();
			System.out.println(dString);
			ByteArrayOutputStream out1 = new ByteArrayOutputStream();
			out1.write(dString.getBytes());
			//int m = dsc.FunLoadFile(folderPath + "/"+ rootFolderName + "/" + tableName + ".xml");
			int m = dsc.FunLoadFile(out1);
				if(m>0){
					//查出每个xml的row数
//					dt.logInfo(3, "Update", "["+hId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "加载xml文件成功"+tableName, "医院号是"+hId);
					int row = dsc.FunGetRowCount();
					String allSql = "";
					String sql = "DECLARE var VARCHAR2(10); BEGIN SELECT 'X' INTO var FROM " + tableName + " where ";
					String lcpSql = null;
					//循环xml里所有row
					for(int z=0; z<row;z++){
						//根据主键的数目循环
						String condition = "";
							//得到对应表的ID
							//String tableId = dbc.FunGetDataSetBySQL("select TABLE_SET_ID from DCP_SYS_TABLE_SET where TABLE_NAME = '"+tableName+"'").FunGetDataAsStringById(0, 0);
							String tableId = uu.FunGetDataByValue1(dsc3, "TABLE_NAME", tableName, "TABLE_SET_ID").get(0);
							ArrayList<String> tableKey = uu.FunGetDataByValue(dsc2, "TABLE_SET_ID", tableId, "KEY_NAME", "KEY_TYPE");
							for(int n=0; n<tableKey.size(); n++){
								//把每行的主键值循环查出  拼成sql
								String ss1[] = tableKey.get(n).split("\\|");
								//根据主键里边的type(I,D,S)把数据转换成对应的样子 加入到condition中
								if(n==0){
									condition += ss1[0] + "=" + uu.getDataWithType(ss1[1], dsc.FunGetDataAsStringByColName(z, ss1[0]));
								}else{
									condition += " and " + ss1[0] + "=" + uu.getDataWithType(ss1[1], dsc.FunGetDataAsStringByColName(z, ss1[0]));
								}
							}
							condition = condition +"; ";
						//定义标志位来确定是否存在lcp数据
						boolean isLcp = false;
						//lcp对应的master跟node表名
						String lcpTableName = null;
						String updateLcpSql = null;
						String insertLcpSql = null;

						String ex = "EXCEPTION WHEN no_data_found THEN ";
						if(uu.getLcpTableName(tableName) != null){
							isLcp = true;
							lcpTableName = uu.getLcpTableName(tableName);
							lcpSql = "DECLARE var VARCHAR2(10); BEGIN SELECT 'X' INTO var FROM " + lcpTableName + " where ";
							updateLcpSql = uu.upDate(dsc, lcpTableName, condition, z, hId, true);
							insertLcpSql = uu.insert(dsc, lcpTableName, condition, z, hId, true);
							
						}
						String dcpUpdateSql = uu.upDate(dsc, tableName, condition, z, hId, false);
						String dcpInsertSql = uu.insert(dsc, tableName, condition, z,  hId, false);
						
						//根据isLcp来判断是否加上对应lcp表的sql  
						if(isLcp == true){
							allSql = allSql + sql + condition + dcpUpdateSql + ex + dcpInsertSql + "END;" +"\r\n"+ lcpSql + condition + updateLcpSql + ex + insertLcpSql + "END;"+"\r\n";
						}else{
							allSql = allSql + sql + condition + dcpUpdateSql + ex + dcpInsertSql + "END;" +"\r\n";
						}
					}
					System.out.print(allSql);
					
					
					byte[] bbb = allSql.getBytes();
					int x = dbc.FunRunSqlByFile(bbb);
					System.out.println(x);
					if(x>0){
//						dt.logInfo(2, "Update", "["+hId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "插入数据成功"+x, "医院号是"+hId);
						res = 1;
					}else{
//						dt.logInfo(2, "Update", "["+hId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "插入数据失败"+x, "医院号是"+hId);
						res = -1;
					}
					String tableSetId = uu.FunGetDataByValue1(dsc3, "TABLE_NAME", tableName, "TABLE_SET_ID").get(0);
					String ulTableSql = "INSERT INTO DCP_SYN_DL_TABLE(DOWNLOAD_ID,TABLE_SET_ID,ROW_COUNT,SYS_IS_DEL,SYS_LAST_UPDATE) VALUES ('" +rootFolderName+ "','"+tableSetId+"','"+uprow+"','0',to_date('"+new SimpleDateFormat("yyyy-MM-d kk:mm:ss").format(new Date())+"', 'YYYY-MM-dd HH24:MI:SS'))";
					dbc.FunRunSQLCommand(dbc.FunGetSvrKey(), ulTableSql);
					return res;
				}else{
					//这个地需要加入记录到数据库	
//					dt.logInfo(3, "Update", "["+hId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "加载xml文件失败"+tableName, "医院号是"+hId);
					return -2;
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			dt.logInfo(2, "Update", "["+hId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "加载xml文件出错："+e + tableName, "医院号是"+hId);
			return 0;
		}
	}

	@Override
	public void getVersionList(String lastVerNo, int hospitalId,
			IntHolder outIntKey, ByteArrayHolder versionNos) {
		// TODO Auto-generated method stub
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
//    	String ip = dt.getClientIp();
    	if(lastVerNo ==null){
    		try {
				byte[] bb = dbc
						.FunGetDataByteSetBySQL("select DOWNLOAD_ID from DCP_SYN_DOWNLOAD t");
				versionNos.value = bb;
				ByteArrayOutputStream out=new ByteArrayOutputStream();
				out.write(bb);
				System.out.println(out.toString());
				outIntKey.value = 1;
//				Singleton.getInstance1().setHospitalId(hospitalId);
//				dt.logInfo(1, "DownLoad", "["+hospitalId+"][All]_"+new Exception().getStackTrace()[0].getMethodName(), "获取要更新的版本列表成功", "所有版本");
			} catch (Exception e) {
				// TODO: handle exception
				outIntKey.value = -1;
//				dt.logInfo(1, "DownLoad", "["+hospitalId+"][All]_"+new Exception().getStackTrace()[0].getMethodName(), "获取要更新的版本列表失败", "所有版本，异常："+e.getMessage());
//				String nowTime = dt.getNowTime();
//				dt.logDownload(hospitalId, 0, nowTime, ip, lastVerNo);
			}
    	}else{
	    	try {
				byte[] bb = dbc
						.FunGetDataByteSetBySQL("select DOWNLOAD_ID from DCP_SYN_DOWNLOAD t where DOWNLOAD_ID >"
								+ lastVerNo);
				versionNos.value = bb;
				System.out.println(bb.toString());
				outIntKey.value = 1;
//				Singleton.getInstance1().setHospitalId(hospitalId);
//				dt.logInfo(1, "DownLoad", "["+hospitalId+"][>"+lastVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取要更新的版本列表成功", "大于版本号"+lastVerNo+"的版本");
			} catch (Exception e) {
				// TODO: handle exception
				outIntKey.value = -1;
//				dt.logInfo(1, "DownLoad", "["+hospitalId+"][>"+lastVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取要更新的版本列表失败", "大于版本号"+lastVerNo+"的版本，异常："+e.getMessage());
//				String nowTime = dt.getNowTime();
//				dt.logDownload(hospitalId, 0, nowTime, ip, lastVerNo);
				
			}
    	}
		
	}

	@Override
	public int buildDataByVer(String curVerNo) {
		// TODO Auto-generated method stub
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
//    	int hospitalId = Singleton.getInstance1().getHospitalId();
//    	String ip = dt.getClientIp();
    	int result = -1;
		try {
			DataSetClass dsc = new DataSetClass();
			dsc = dbc.FunGetDataSetBySQL("select DOWNLOAD_NAME,DOWNLOAD_FILE from DCP_SYN_DOWNLOAD t where DOWNLOAD_ID = "
					+ curVerNo);
			if (dsc.FunGetRowCount() <= 0) {
				result = -2;
//				dt.logInfo(2, "DownLoad", "["+hospitalId+"]["+curVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "未找到版本包"+curVerNo+"的数据", String.valueOf(result));
//				String nowTime = dt.getNowTime();
//				dt.logDownload(hospitalId, 0, nowTime, ip, curVerNo);
			}
				String name = dsc.FunGetDataAsStringByColName(0, "DOWNLOAD_NAME");
				String path = dsc.FunGetDataAsStringByColName(0, "DOWNLOAD_FILE");
				File in = new File(path + "/" + name);
				result = (int) in.length();
//				dt.logInfo(2, "DownLoad", "["+hospitalId+"]["+curVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取版本包"+curVerNo+"的数据长度成功", String.valueOf(result));

				return result;
				

		} catch (Exception e) {
			// TODO: handle exception
			result = -1;
			
//			dt.logInfo(2, "DownLoad", "["+hospitalId+"]["+curVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取版本包"+curVerNo+"的数据长度出现异常", e.getMessage());
//			String nowTime = dt.getNowTime();
//			dt.logDownload(hospitalId, 0, nowTime, ip, curVerNo);
		}
		return result;
	}

	@Override
	public void getVersionBagByPack(String curVerNo, int curPageNo,
			int totalPageSize, int hospitalId, IntHolder outIntKey,
			ByteArrayHolder curVersionBagData) {
		// TODO Auto-generated method stub
		DatabaseClass dbc = LcpUtil.getDatabaseClass();
//    	String ip = dt.getClientIp();
    	
		int pageSize = 10000; //每页大小
		int countPage = totalPageSize/pageSize; //总页数
		System.out.print(countPage);
		
			
			DataSetClass dsc = new DataSetClass();
			dsc = dbc.FunGetDataSetBySQL("select DOWNLOAD_NAME,DOWNLOAD_FILE from DCP_SYN_DOWNLOAD where DOWNLOAD_ID = "
					+ curVerNo);
			if (dsc.FunGetRowCount() > 0) {
				String name = dsc.FunGetDataAsStringByColName(0, "DOWNLOAD_NAME");
				String path = dsc.FunGetDataAsStringByColName(0, "DOWNLOAD_FILE");
				String filePath = path + "/" + name;

				System.out.print("||"+totalPageSize);
				
				
				curVersionBagData.value = uu.FunGetFileByteByPage(curPageNo, totalPageSize, filePath);
				outIntKey.value = curVersionBagData.value.length;
//				dt.logInfo(3, "DownLoad", "["+hospitalId+"]["+curVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取"+curPageNo+"页的数据长度成功", String.valueOf(outIntKey));
//				String nowTime = dt.getNowTime();
//				dt.logDownload(hospitalId, 1, nowTime, ip, curVerNo);
				
				
			}else{
				curVersionBagData.value = null;
				outIntKey.value = -1;
//				dt.logInfo(3, "DownLoad", "["+hospitalId+"]["+curVerNo+"]_"+new Exception().getStackTrace()[0].getMethodName(), "获取"+curPageNo+"页的数据长度失败", String.valueOf(outIntKey));
//				String nowTime = dt.getNowTime();
//				dt.logDownload(hospitalId, 0, nowTime, ip, curVerNo);
			}
		
	}

	@Override
	public int unZipFile(String filePath, int hospitalId, String loadName) {
		// TODO Auto-generated method stub
		final int BUFFER = 2048;

		String zipPath = filePath.substring(0, filePath.length() - 4) + "/";
		try {
			ZipFile zipFile = new ZipFile(filePath);
			@SuppressWarnings("rawtypes")
			Enumeration emu = zipFile.entries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
				System.out.println(entry);
				if (entry.isDirectory()) {
					new File(zipPath + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(
						zipFile.getInputStream(entry));
				File file = new File(zipPath + entry.getName()+"\\");
				// 加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
				// 而这个文件所在的目录还没有出现过，所以要建出目录来。
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
				
				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
					
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
			
//			dt.logInfo(1, "Update", "["+hospitalId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "压缩包解压成功", "医院号是"+hospitalId);
			return 1;
		} catch (Exception e) {
			System.out.println("闹挺啊"+e);
//			dt.logInfo(1, "Update", "["+hospitalId+"]["+downloadName+"]_"+new Exception().getStackTrace()[0].getMethodName(), "压缩包解压失败", "医院号是"+hospitalId +e);
//    		dt.logUpload(hospitalId, 0, endTime, ip);
            return -1;
		}
	}

}
