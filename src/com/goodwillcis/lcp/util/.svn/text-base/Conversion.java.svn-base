package com.goodwillcis.lcp.util;

import java.util.ArrayList;
import java.util.HashMap;

public class Conversion {


	/**
	 * 取得需要签名的ID值和对应ID值是否执行
	 * @param editAbles ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param blues ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param executes ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param split 分割标记
	 * @return HashMap<String, String> 第一个String 代表对应的nodeid下的id值(不管是否执行，全部是需要签名的)，第二个String代表是否执行，0表示未执行，1表示执行
	 */
	public static HashMap<String, String> funGetSign(String editAbles,String blues,String executes,String split){
		HashMap<String, String> map=new HashMap<String,String>();
		ArrayList<String> editAblesList=funGetListIDsByString(editAbles,split);
		ArrayList<String> bluesList=funGetListIDsByString(blues,split);
		ArrayList<String> executesList=funGetListIDsByString(executes,split);
		map=funGetSign(editAblesList, bluesList, executesList);
		return map;
	}
	/**
	 * 取得需要签名的ID值和对应ID值是否执行
	 * @param editAbles ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param blues ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param executes ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param split 分割标记
	 * @return ArrayList<String> 代表对应的nodeid下的id值
	 */
	public  static  ArrayList<String> funGetSign(String editAbles,String blues,String split){
		ArrayList<String> arrayList=new ArrayList<String>();
		ArrayList<String> editAblesList=funGetListIDsByString(editAbles,split);
		ArrayList<String> bluesList=funGetListIDsByString(blues,split);
		arrayList=funGetSign(editAblesList, bluesList);
		return arrayList;
	}
	/**
	 * 取得需要签名的ID值和对应ID值是否执行(内部使用)
	 * @param editAbles 可编辑的行id值
	 * @param blues 所有变蓝色的行id值
	 * @param executes 所有打钩的行ID值
	 * @return HashMap<String, String> 第一个String 代表对应的nodeid下的id值(不管是否执行，全部是需要签名的)，第二个String代表是否执行，0表示未执行，1表示执行
	 */
	private  static  HashMap<String, String> funGetSign(ArrayList<String> editAbles,ArrayList<String> blues,ArrayList<String> executes){
		HashMap<String, String> map=new HashMap<String,String>();
		ArrayList<String> signs=funGetSign(editAbles, blues);
		int signsLen=signs.size();
		int executesLen=executes.size();
		for(int i=0;i<signsLen;i++){
			String sign=signs.get(i);
			boolean isExt=false;
			for(int j=0;j<executesLen;j++){
				String execute=executes.get(j);
				if(sign.equals(execute)){
					isExt=true;
				}
			}
			if(isExt){
				map.put(sign, "1");
			}else{
				map.put(sign, "0");
			}
		}
		return map;
	}
	/**
	 * 取得需要签名的id值(内部使用)
	 * @param editAbles 可编辑的行id值
	 * @param blues 所有变蓝色的行id值
	 * @return
	 */
	private  static  ArrayList<String> funGetSign(ArrayList<String> editAbles,ArrayList<String> blues){
		ArrayList<String> arrayList=new ArrayList<String>();
		int editLen=editAbles.size();
		int blueLen=blues.size();
		for(int i=0;i<editLen;i++){
			String edit=editAbles.get(i);
			for(int j=0;j<blueLen;j++){
				String blue=blues.get(j);
				if(edit.equals(blue)){
					arrayList.add(edit);
				}
			}
		}
		return arrayList;
	}
	/**
	 * 取得ID值(内部使用)
	 * @param ids 字符串ID值，例如 1,2,3,4,5,6这样的
	 * @param split 分割标记
	 * @return 返回List数组
	 */
	public  static  ArrayList<String> funGetListIDsByString(String ids,String split){
		ArrayList<String>idList=new ArrayList<String>();
		if(!ids.isEmpty()){
			String [] idArr=ids.split(split);
			for(int i=0;i<idArr.length;i++){
				idList.add(idArr[i]);
			}
		}
		return idList;
	}
	
}
