/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： LCPService_ServiceLocator .java
// 文件功能描述：eclipse自动生成的
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
/**
 * LCPService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.goodwillcis.lcp.LCPService;

public class LCPService_ServiceLocator extends org.apache.axis.client.Service implements com.goodwillcis.lcp.LCPService.LCPService_Service {

    public LCPService_ServiceLocator() {
    }


    public LCPService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LCPService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LCPServiceSOAP
    private java.lang.String LCPServiceSOAP_address = "http://lcp.goodwillcis.com/";

    public java.lang.String getLCPServiceSOAPAddress() {
        return LCPServiceSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LCPServiceSOAPWSDDServiceName = "LCPServiceSOAP";

    public java.lang.String getLCPServiceSOAPWSDDServiceName() {
        return LCPServiceSOAPWSDDServiceName;
    }

    public void setLCPServiceSOAPWSDDServiceName(java.lang.String name) {
        LCPServiceSOAPWSDDServiceName = name;
    }

    public com.goodwillcis.lcp.LCPService.LCPService_PortType getLCPServiceSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LCPServiceSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLCPServiceSOAP(endpoint);
    }

    public com.goodwillcis.lcp.LCPService.LCPService_PortType getLCPServiceSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.goodwillcis.lcp.LCPService.LCPServiceSOAPStub _stub = new com.goodwillcis.lcp.LCPService.LCPServiceSOAPStub(portAddress, this);
            _stub.setPortName(getLCPServiceSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLCPServiceSOAPEndpointAddress(java.lang.String address) {
        LCPServiceSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.goodwillcis.lcp.LCPService.LCPService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.goodwillcis.lcp.LCPService.LCPServiceSOAPStub _stub = new com.goodwillcis.lcp.LCPService.LCPServiceSOAPStub(new java.net.URL(LCPServiceSOAP_address), this);
                _stub.setPortName(getLCPServiceSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LCPServiceSOAP".equals(inputPortName)) {
            return getLCPServiceSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "LCPService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "LCPServiceSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LCPServiceSOAP".equals(portName)) {
            setLCPServiceSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
