<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%


if(session.getAttribute("userid") == null)
	response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"../login.jsp\";</script>");

String funs = (String)session.getAttribute("funs");

//String url = request.getRequestURI().replaceAll(request.getContextPath(), "");

if(funs == null || "".equals(funs))
	response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"../login.jsp\";</script>");
//else if(funs.indexOf(url)<0)
//	response.getWriter().println("<script type=\"text/javascript\">top.location.href=\"../login.jsp\";</script>");
%>