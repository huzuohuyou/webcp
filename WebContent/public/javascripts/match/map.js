/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名：Map.js
// 文件功能描述：仿java的Map对象，用于存储数据
// 创建人：刘植鑫 
// 创建日期：2011/07/26
// 
//----------------------------------------------------------------*/

function Map(){
	this.elements=new Array();
	/**
	 * 获取map个数
	 */
	this.size=function(){
		return this.elements.length;
	};
	/**
	 * 判断是否为空
	 */
	this.isEmpty=function(){
		return (this.elements.length<1);
	};
	/**
	 * 清空所有数据
	 */
	this.clear=function(){
		this.elements=new Array();
	};
	/**
	 * 压入数据
	 */
	this.put=function(_key,_value){
		this.elements.push({
			key:_key,
			value:_value
		});
	};
	/**
	 * 根据key移除数据
	 */
	this.remove=function(_key){
		var bln=false;
		try {
			for(i=0;i<this.elements.length;i++){
				if(this.elements[i].key==_key){
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			// TODO: handle exception
			bln=false;
		}
		return bln;
	};
	/**
	 * 根据key取得对应的value
	 */
	this.get=function(_key){
		try {
			for(i=0;i<elements.length;i++){
				if(this.elements[i].key==_key){
					return elements[i].value;
				}
			}
		} catch (e) {
			// TODO: handle exception
			return null;
		}
	};
	/**
	 * 取得第几个map值，包括key和value
	 */
	this.element=function(_index){
		if(_index<0||_index>=this.elements.length){
			return null;
		}
		return this.elements[_index];
	};
	/**
	 * 判断MAP中是否含有指定KEY的元素
	 */
	 this.containsKey = function(_key) {
		 var bln = false;
		 try {
		     for (i = 0; i < this.elements.length; i++) {
	            if (this.elements[i].key == _key) {
		            bln = true;
		            }
		        }
		    } catch (e) {
		        bln = false;
		    }
		 return bln;
	};
	/**
	 * 判断MAP中是否含有指定value的元素
	 */
	this.containsValue=function(_value){
		var bln=false;
		try {
			for(i=0;i<this.elements.length;i++){
				if(this.elements[i].value==_value){
					bln=true;
				}
			}
		} catch (e) {
			// TODO: handle exception
			bln=false;
		}
		return bln;
	};
	/**
	 * 取得所有的value值
	 */
	this.values=function(){
		var arr=new Array;
		for(i=0;i<this.elements.length;i++){
			arr.push(this.elements[i].value);
		}
		return arr;
	};
	/**
	 * 取得所有的key值
	 */
	this.keys=function(){
		var arr=new Array;
		for(i=0;i<this.elements.length;i++){
			arr.push(this.elements[i].key);
		}
		return arr;
	};
}