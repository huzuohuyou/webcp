/*----------------------------------------------------------------
// Copyright (C) 2011 北京嘉和美康信息技术有限公司版权所有。 
// 文件名： LCPServiceSOAPSkeleton .java
// 文件功能描述：eclipse自动生成的
// 创建人：刘植鑫 
// 创建日期：2011/07/27
// 
//----------------------------------------------------------------*/
/**

 * LCPServiceSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.goodwillcis.lcp.LCPService;

public class LCPServiceSOAPSkeleton implements com.goodwillcis.lcp.LCPService.LCPService_PortType, org.apache.axis.wsdl.Skeleton {
    private com.goodwillcis.lcp.LCPService.LCPService_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inRegData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPRegPatient", _params, new javax.xml.namespace.QName("", "outReg"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPRegPatient"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPRegPatient");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPRegPatient") == null) {
            _myOperations.put("funCPRegPatient", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPRegPatient")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPGetStatus", _params, new javax.xml.namespace.QName("", "outStatus"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPGetStatus"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPGetStatus");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPGetStatus") == null) {
            _myOperations.put("funCPGetStatus", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPGetStatus")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetFirstPage", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetFirstPage"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetFirstPage");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetFirstPage") == null) {
            _myOperations.put("funCPSetFirstPage", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetFirstPage")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inDiagData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetDiagnosis", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetDiagnosis"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetDiagnosis");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetDiagnosis") == null) {
            _myOperations.put("funCPSetDiagnosis", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetDiagnosis")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inOperData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetOperation", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetOperation"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetOperation");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetOperation") == null) {
            _myOperations.put("funCPSetOperation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetOperation")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inDoctData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetDoctorItem", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetDoctorItem"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetDoctorItem");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetDoctorItem") == null) {
            _myOperations.put("funCPSetDoctorItem", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetDoctorItem")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inNurData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetNurseItem", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetNurseItem"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetNurseItem");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetNurseItem") == null) {
            _myOperations.put("funCPSetNurseItem", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetNurseItem")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inOrderData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetOrderItem", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetOrderItem"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetOrderItem");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetOrderItem") == null) {
            _myOperations.put("funCPSetOrderItem", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetOrderItem")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inNodeID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inItemType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outRes"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outSize"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outItemData"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPGetItemSet", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPGetItemSet"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPGetItemSet");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPGetItemSet") == null) {
            _myOperations.put("funCPGetItemSet", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPGetItemSet")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inMatchType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "localCode"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outRes"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outMatchData"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funGetMatchItem", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunGetMatchItem"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunGetMatchItem");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funGetMatchItem") == null) {
            _myOperations.put("funGetMatchItem", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funGetMatchItem")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inEmrDocData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetEmrDoc", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetEmrDoc"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetEmrDoc");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetEmrDoc") == null) {
            _myOperations.put("funCPSetEmrDoc", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetEmrDoc")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inDisData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPDischarge", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPDischarge"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPDischarge");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPDischarge") == null) {
            _myOperations.put("funCPDischarge", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPDischarge")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inOrderStateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetOrderState", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetOrderState"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetOrderState");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetOrderState") == null) {
            _myOperations.put("funCPSetOrderState", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetOrderState")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inNodeID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outRes"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outVarBagData"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outSize"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPGetOrderVariation", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPGetOrderVariation"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPGetOrderVariation");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPGetOrderVariation") == null) {
            _myOperations.put("funCPGetOrderVariation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPGetOrderVariation")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inOrderVarData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetOrderVariation", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetOrderVariation"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetOrderVariation");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetOrderVariation") == null) {
            _myOperations.put("funCPSetOrderVariation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetOrderVariation")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inStateType"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "stateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetItemSet", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetItemSet"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetItemSet");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetItemSet") == null) {
            _myOperations.put("funCPSetItemSet", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetItemSet")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inLISData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetLIS", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetLIS"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetLIS");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetLIS") == null) {
            _myOperations.put("funCPSetLIS", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetLIS")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPACSData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetPACS", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetPACS"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetPACS");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetPACS") == null) {
            _myOperations.put("funCPSetPACS", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetPACS")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inPatiID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inSize"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inFeeData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPSetFee", _params, new javax.xml.namespace.QName("", "outRes"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPSetFee"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPSetFee");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPSetFee") == null) {
            _myOperations.put("funCPSetFee", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPSetFee")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "in"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outRes"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outSize"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "outDictData"), org.apache.axis.description.ParameterDesc.OUT, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "hexBinary"), byte[].class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("funCPGetDictVariation", _params, null);
        _oper.setElementQName(new javax.xml.namespace.QName("http://lcp.goodwillcis.com/LCPService/", "FunCPGetDictVariation"));
        _oper.setSoapAction("http://lcp.goodwillcis.com/LCPService/FunCPGetDictVariation");
        _myOperationsList.add(_oper);
        if (_myOperations.get("funCPGetDictVariation") == null) {
            _myOperations.put("funCPGetDictVariation", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("funCPGetDictVariation")).add(_oper);
    }

    public LCPServiceSOAPSkeleton() {
        this.impl = new com.goodwillcis.lcp.LCPService.LCPServiceSOAPImpl();
    }

    public LCPServiceSOAPSkeleton(com.goodwillcis.lcp.LCPService.LCPService_PortType impl) {
        this.impl = impl;
    }
    public int funCPRegPatient(int inSize, byte[] inRegData, java.lang.String inPatiID) throws java.rmi.RemoteException
    {
        int ret = impl.funCPRegPatient(inSize, inRegData, inPatiID);
        return ret;
    }

    public int funCPGetStatus(java.lang.String inPatiID) throws java.rmi.RemoteException
    {
        int ret = impl.funCPGetStatus(inPatiID);
        return ret;
    }

    public int funCPSetFirstPage(java.lang.String inPatiID, int inSize, byte[] inPatiData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetFirstPage(inPatiID, inSize, inPatiData);
        return ret;
    }

    public int funCPSetDiagnosis(java.lang.String inPatiID, int inSize, byte[] inDiagData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetDiagnosis(inPatiID, inSize, inDiagData);
        return ret;
    }

    public int funCPSetOperation(java.lang.String inPatiID, int inSize, byte[] inOperData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetOperation(inPatiID, inSize, inOperData);
        return ret;
    }

    public int funCPSetDoctorItem(java.lang.String inPatiID, int inSize, byte[] inDoctData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetDoctorItem(inPatiID, inSize, inDoctData);
        return ret;
    }

    public int funCPSetNurseItem(java.lang.String inPatiID, int inSize, byte[] inNurData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetNurseItem(inPatiID, inSize, inNurData);
        return ret;
    }

    public int funCPSetOrderItem(java.lang.String inPatiID, int inSize, byte[] inOrderData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetOrderItem(inPatiID, inSize, inOrderData);
        return ret;
    }

    public void funCPGetItemSet(java.lang.String inPatiID, int inNodeID, int inItemType, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.IntHolder outSize, javax.xml.rpc.holders.ByteArrayHolder outItemData) throws java.rmi.RemoteException
    {
        impl.funCPGetItemSet(inPatiID, inNodeID, inItemType, outRes, outSize, outItemData);
    }

    public void funGetMatchItem(java.lang.String inMatchType, java.lang.String localCode, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.ByteArrayHolder outMatchData) throws java.rmi.RemoteException
    {
        impl.funGetMatchItem(inMatchType, localCode, outRes, outMatchData);
    }

    public int funCPSetEmrDoc(java.lang.String inPatiID, int inSize, byte[] inEmrDocData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetEmrDoc(inPatiID, inSize, inEmrDocData);
        return ret;
    }

    public int funCPDischarge(int inSize, byte[] inDisData, java.lang.String inPatiID) throws java.rmi.RemoteException
    {
        int ret = impl.funCPDischarge(inSize, inDisData, inPatiID);
        return ret;
    }

    public int funCPSetOrderState(java.lang.String inPatiID, int inSize, byte[] inOrderStateData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetOrderState(inPatiID, inSize, inOrderStateData);
        return ret;
    }

    public void funCPGetOrderVariation(java.lang.String inPatiID, int inNodeID, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.ByteArrayHolder outVarBagData, javax.xml.rpc.holders.IntHolder outSize) throws java.rmi.RemoteException
    {
        impl.funCPGetOrderVariation(inPatiID, inNodeID, outRes, outVarBagData, outSize);
    }

    public int funCPSetOrderVariation(java.lang.String inPatiID, byte[] inOrderVarData, int inSize) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetOrderVariation(inPatiID, inOrderVarData, inSize);
        return ret;
    }

    public int funCPSetItemSet(java.lang.String inPatiID, int inStateType, int inSize, byte[] stateData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetItemSet(inPatiID, inStateType, inSize, stateData);
        return ret;
    }

    public int funCPSetLIS(java.lang.String inPatiID, int inSize, byte[] inLISData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetLIS(inPatiID, inSize, inLISData);
        return ret;
    }

    public int funCPSetPACS(java.lang.String inPatiID, int inSize, byte[] inPACSData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetPACS(inPatiID, inSize, inPACSData);
        return ret;
    }

    public int funCPSetFee(java.lang.String inPatiID, int inSize, byte[] inFeeData) throws java.rmi.RemoteException
    {
        int ret = impl.funCPSetFee(inPatiID, inSize, inFeeData);
        return ret;
    }

    public void funCPGetDictVariation(java.lang.String in, javax.xml.rpc.holders.IntHolder outRes, javax.xml.rpc.holders.IntHolder outSize, javax.xml.rpc.holders.ByteArrayHolder outDictData) throws java.rmi.RemoteException
    {
        impl.funCPGetDictVariation(in, outRes, outSize, outDictData);
    }

}
