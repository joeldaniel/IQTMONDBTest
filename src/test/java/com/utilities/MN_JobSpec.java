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


public class MN_JobSpec extends Testbase {
	public static HashMap<String, HashMap<String, String>> JobspecDetails = new HashMap<String, HashMap<String, String>>();
	
	String url=Config.getProperty("MNDBUrl");
	String user = Config.getProperty("MNDBUsername");
	String password = Config.getProperty("MNDBPassWord");
	
	public HashMap<String,HashMap<String, String>> jobspec() throws ClassNotFoundException{
		JobspecDetails.clear();
		Connection con;
		Statement stmt;
		ResultSet rs; 
		String query="Select iqpid, spec_desc, hours_makeready, hours_run, length, width, number_out_for_oper, qty_makeready, qty_net, qty_spoilage, quantity_per_hour, work_center_id,  printflowcostcenterid\r\n" + 
				"from jobspecoperation\r\n" + 
				"where job_id= '10766' and sub_job_id = ''\r\n" + 
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
			HashMap<String, String> JobSpecChild = new HashMap<String, String>();
			System.out.println(query);
			int keyval=1;
			while(rs.next())
			{
				String iqpid=rs.getString("iqpid");
				JobSpecChild.put("iqpid", iqpid != null ? iqpid : "");
				String spec_desc=rs.getString("spec_desc");
				JobSpecChild.put("spec_desc", spec_desc != null ? spec_desc : "");
				String hours_makeready=rs.getString("hours_makeready");
				JobSpecChild.put("hours_makeready", hours_makeready != null ? hours_makeready : "");
				String hours_run=rs.getString("hours_run");
				JobSpecChild.put("hours_run", hours_run != null ? hours_run : "");
				String length=rs.getString("length");
				JobSpecChild.put("length", length != null ? length : "");
				String width=rs.getString("width");
				JobSpecChild.put("width", width != null ? width : "");
				String number_out_for_oper=rs.getString("number_out_for_oper");
				JobSpecChild.put("number_out_for_oper", number_out_for_oper != null ? number_out_for_oper : "");
				String qty_makeready=rs.getString("qty_makeready");
				JobSpecChild.put("qty_makeready", qty_makeready != null ? qty_makeready : "");
				String qty_net=rs.getString("qty_net");
				JobSpecChild.put("qty_net", qty_net != null ? qty_net : "");
				String qty_spoilage=rs.getString("qty_spoilage");
				JobSpecChild.put("qty_spoilage", qty_spoilage != null ? qty_spoilage : "");
				String quantity_per_hour=rs.getString("quantity_per_hour");
				JobSpecChild.put("quantity_per_hour", quantity_per_hour != null ? quantity_per_hour : "");
				String work_center_id=rs.getString("work_center_id");
				JobSpecChild.put("work_center_id", work_center_id != null ? work_center_id : "");
				String printflowcostcenterid=rs.getString("printflowcostcenterid");
				JobSpecChild.put("printflowcostcenterid", printflowcostcenterid != null ? printflowcostcenterid : "");
				
				//System.out.println(Collections.singletonList(JobspecDetails));
				JobspecDetails.put(keyval+"", JobSpecChild);
				++keyval;
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
