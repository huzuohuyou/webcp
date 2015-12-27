package com.goodwillcis.cp.cpinfo;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	/*LCPMaster.java
	LCPNode.java
	LCPNodeDoctorItem.java
	LCPNodeDoctorPoint.java
	LCPNodeNurseItem.java
	LCPNodeNursePoint.java
	LCPNodeOrderItem.java
	LCPNodeOrderPoint.java*/
	
	//LCPMaster lcp=new LCPMaster();
		
		Object[] params=new Object[]{"10014"};
		LCPMaster lcp=new LCPMaster();
		//lcp.applyParams(params);
		
	lcp.applyParams(params);
	for(int i=0;i<lcp.getRowCount();i++){
	  Object [] ob=  lcp.getRowData(i);
	  for(int j=0;j<ob.length;j++){
	      Object a= ob[j];
	      System.out.print(a);
	  }
	    System.out.println();
	}
	//System.out.println(lcp.getRowCount());
	
    }

}
