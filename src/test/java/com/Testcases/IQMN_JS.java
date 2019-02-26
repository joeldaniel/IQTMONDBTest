package com.Testcases;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.Testbase;

import com.utilities.IQ_JobSpec;
import com.utilities.MN_JobSpec;


public class IQMN_JS extends Testbase{
	@Test
	public void iqmn_js() throws InterruptedException, ClassNotFoundException{
		test = extent.createTest("functionality1Test2");
				
		HashMap<String, HashMap<String, String>> IQ_JobSpecDetail = new HashMap<String, HashMap<String, String>>();
		HashMap<String, HashMap<String, String>> MN_JobSpecDetail = new HashMap<String, HashMap<String, String>>();
		
		//Querying Iquote Details
		IQ_JobSpec iqdb=new IQ_JobSpec();
		IQ_JobSpecDetail=iqdb.jobspec();
		//Querying Monarch Details
		MN_JobSpec mndb = new MN_JobSpec();
		MN_JobSpecDetail=mndb.jobspec();
		int c=0;
		
		if(IQ_JobSpecDetail.size()==MN_JobSpecDetail.size()){
			System.out.println("Rows in both tables are equal");
			for(int i=0;i<=IQ_JobSpecDetail.size();i++) {
				for(Entry<String, HashMap<String, String>> entry:IQ_JobSpecDetail.entrySet()) {
					for(Entry<String, HashMap<String, String>> entry1:MN_JobSpecDetail.entrySet()) {
						if(entry.getValue().equals(entry1.getValue())) {
							System.out.println("True");
							c++;
							break;
						}
					}
				}
			}
			if(c==IQ_JobSpecDetail.size()) {
				System.out.println("Values matched"+c);
			}
			
			
			
		}
		test.log(Status.INFO, "Second");
		
		
		
		
	}
}
