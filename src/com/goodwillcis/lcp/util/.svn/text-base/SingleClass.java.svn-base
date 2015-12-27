package com.goodwillcis.lcp.util;

public class SingleClass 
{ 
    //定义一个私有的静态全局变量来保存该类的唯一实例 
    private static DBConnection dbc; 
    private SingleClass() 
    { 
    } 
    public static DBConnection GetInstance() 
    { 
        if (dbc == null) 
        { 
        	dbc = new DBConnection(); 
        } 
        return dbc; 
    } 
} 

