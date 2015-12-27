package com.goodwillcis.lcp.servlet.pdca;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

public class myServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response){
		System.out.println("123");
		
	}
	public void doPost(HttpServletRequest request,
			HttpServletResponse response){
		this.doGet(request, response);
		
	}

}
