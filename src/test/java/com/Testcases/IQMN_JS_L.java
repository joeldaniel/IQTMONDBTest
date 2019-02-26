package com.Testcases;

import java.util.ArrayList;

import java.util.List;


import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.Testbase;


import com.utilities.IQ_JobSpec_List;

import com.utilities.MN_JobSpec_List;


public class IQMN_JS_L extends Testbase{
	
	
	@Test
	public void iqmn_js() throws InterruptedException, ClassNotFoundException{
		test = extent.createTest("functionality1Test1");
				
		List<ArrayList<String>> IQ_JobSpecDetail = new ArrayList<ArrayList<String>>();
		List<ArrayList<String>> MN_JobSpecDetail = new ArrayList<ArrayList<String>>();
		
		
		//Querying Iquote Details
		IQ_JobSpec_List iqdb=new IQ_JobSpec_List();
		IQ_JobSpecDetail=iqdb.jobspec(Inputs.getProperty("Job"),Inputs.getProperty("Subjob"));
		//Querying Monarch Details
		MN_JobSpec_List mndb = new MN_JobSpec_List();
		MN_JobSpecDetail=mndb.jobspec(Inputs.getProperty("Job"),Inputs.getProperty("Subjob"));
		
		
		if(IQ_JobSpecDetail.size()==MN_JobSpecDetail.size()){
			System.out.println("Rows in both tables are equal");
			if(IQ_JobSpecDetail.equals(MN_JobSpecDetail))
				System.out.println("Values matched");
			System.out.println("Matching failed");
		
						
		}
		
		test.log(Status.INFO, "First one");
		
		
		
	}
}
