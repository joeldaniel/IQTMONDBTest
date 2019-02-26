package com.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.base.Testbase;
import com.relevantcodes.extentreports.LogStatus;


public class MN_JobSpec_List extends Testbase {
	public static List<ArrayList<String>> JobspecDetails = new ArrayList<ArrayList<String>>();
	//public static ArrayList<String> arr1=new ArrayList<>();
	
	String url=Config.getProperty("MNDBUrl");
	String user = Config.getProperty("MNDBUsername");
	String password = Config.getProperty("MNDBPassWord");
	
	public List<ArrayList<String>> jobspec(String job, String subjob) throws ClassNotFoundException{
		
		Connection con;
		Statement stmt;
		ResultSet rs; 
		String query="Select iqpid, spec_desc, hours_makeready, hours_run, length, width, number_out_for_oper, qty_makeready, qty_net, qty_spoilage, quantity_per_hour, work_center_id,  printflowcostcenterid\r\n" + 
				"from jobspecoperation\r\n" + 
				"where job_id= '"+job+"' and sub_job_id = '"+subjob+"'\r\n" + 
				"order by 1 asc";
		
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println(url +" : "+ user +" : "+ password);
			System.out.println("Connected to sql db");
			
			
			// getting Statement object to execute query
			stmt = con.createStatement();

			//execute query
			rs=stmt.executeQuery(query);
			
			//loop
			//HashMap<String, String> JobSpecChild = new HashMap<String, String>();

			System.out.println(query);
			
			while(rs.next())
			{
				ArrayList<String> JobSpecChild = new ArrayList<>();
				JobSpecChild.clear();
				String iqpid=rs.getString("iqpid");
				JobSpecChild.add(iqpid);
				String spec_desc=rs.getString("spec_desc");
				JobSpecChild.add(spec_desc);
				String hours_makeready=rs.getString("hours_makeready");
				JobSpecChild.add(hours_makeready);
				String hours_run=rs.getString("hours_run");
				JobSpecChild.add(hours_run);
				String length=rs.getString("length");
				JobSpecChild.add(length);
				String width=rs.getString("width");
				JobSpecChild.add(width);
				String number_out_for_oper=rs.getString("number_out_for_oper");
				JobSpecChild.add(number_out_for_oper);
				String qty_makeready=rs.getString("qty_makeready");
				JobSpecChild.add(qty_makeready);
				String qty_net=rs.getString("qty_net");
				JobSpecChild.add(qty_net);
				String qty_spoilage=rs.getString("qty_spoilage");
				JobSpecChild.add(qty_spoilage);
				String quantity_per_hour=rs.getString("quantity_per_hour");
				JobSpecChild.add(quantity_per_hour);
				String work_center_id=rs.getString("work_center_id");
				JobSpecChild.add(work_center_id);
				String printflowcostcenterid=rs.getString("printflowcostcenterid");
				JobSpecChild.add(printflowcostcenterid);
				
				//System.out.println(Collections.singletonList(JobspecDetails));
				JobspecDetails.add(JobSpecChild);
				
				
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("failed");
		}
		
		
		
		
		return JobspecDetails;
		
		
	}
	

}
