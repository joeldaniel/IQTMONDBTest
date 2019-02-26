package com.resources;

import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.Status;
import com.base.DBUtil;
import com.base.Testbase;

public class Basefile extends Testbase {
	
	public void executetestcase(String testcase) throws ClassNotFoundException, SQLException {

	test = extent.createTest(testcase);
	ResultSet rs,rs1,rs2;
	String mnurl = Config.getProperty("MNDBUrl");
	String mnuser = Config.getProperty("MNDBUsername");
	String mnpassword = Config.getProperty("MNDBPassWord");
	String iqurl = Config.getProperty("IQDBUrl");
	String iquser = Config.getProperty("IQDBUsername");
	String iqpassword = Config.getProperty("IQDBPassWord");
	test.log(Status.INFO, "Monarch DB URL : "+mnurl +" Monarch DB User : "+ mnuser+" Monarch DB Password : "+ mnpassword );
	
	test.log(Status.INFO, "Iquote DB URL : "+iqurl+" Iquote DB User : "+iquser+" Iquote DB Password : "+iqpassword);
		
	int rccount = 0, rowcount = 0;

	DBUtil monarchConnection = new DBUtil(mnurl, mnuser, mnpassword);
	DBUtil iQuoteConnection = new DBUtil(iqurl, iquser, iqpassword);

	try {

		Object obj = new JSONParser().parse(
				new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\"+testcase+".json"));

		JSONObject jsonObject = (JSONObject) obj;
		JSONArray companyList = (JSONArray) jsonObject.get("Tests");

		Iterator itr2 = companyList.iterator();
		
		while (itr2.hasNext()) {
			Iterator<Map.Entry> itr1 = ((Map) itr2.next()).entrySet().iterator();

			while (itr1.hasNext()) {
				Map.Entry IQT = itr1.next();
				Map.Entry MON = itr1.next();
				JSONObject joe = (JSONObject) IQT.getValue();
				String pids = (String) joe.get("_primaryIdQuery");
				String query = pids.replace("##JobID##", Inputs.getProperty("Job")).replace("##SubJobID##",
						Inputs.getProperty("Subjob"));
				try {
					rs = monarchConnection.RunQuery(query);

					System.out.println(query);
					test.log(Status.INFO, query);

					while (rs.next()) {
						rowcount += 1;
						String col = (String) joe.get("_primaryId");
						String colval = rs.getString(col);
						String colname = (String) joe.get("_colName");
						String query1 = "Select " + colname + " from " + (String) joe.get("_Table") + " where "
								+ (String) joe.get("_primaryId") + "=" + "'" + colval + "'";
						System.out.println(query1);
						test.log(Status.INFO, query1);
						try {
							rs1 = monarchConnection.RunQuery(query1);
							while (rs1.next()) {
								String val = rs1.getString(colname);
								System.out.println(val);
								
								JSONObject dan = (JSONObject) MON.getValue();
								String query2 = "Select " + (String) dan.get("_colName") + " from "
										+ (String) dan.get("_Table") + " where " + (String) dan.get("_primaryId")
										+ "=" + "'" + colval + "'";
								System.out.println(query2);
								test.log(Status.INFO, query2);
								try {
									rs2 = iQuoteConnection.RunQuery(query2);
									while (rs2.next()) {
										String val1 = rs1.getString((String) dan.get("_colName"));
										System.out.println(val1);
										
										if (val.equals(val1)) {
											rccount += 1;
											test.log(Status.PASS, "IQT : "+val+" MON : "+val1);
										}
										else {
											System.out.println("val : "+val+"val1 : "+val1);
											test.log(Status.FAIL, "IQT : "+val+" MON : "+val1);
										}
									}

								} catch (SQLException e) {
									e.printStackTrace();
									System.out.println("failed");
									test.log(Status.FAIL, "Failed");
								}

							}

						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("failed");
							test.log(Status.FAIL, "Failed");
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("failed");
					test.log(Status.FAIL, "Failed");
				}
			}
			
		}
		if (rccount == rowcount) {
			System.out.println("Everything matched: \nrccount  : " + rccount + "\nrowcount : " + rowcount);
			test.log(Status.PASS, "Everything matched : \nrccount  : " + rccount + "\nrowcount : " + rowcount);
		}
		else
		{
			System.out.println("failed : \nrccount  : " + rccount + "\nrowcount : " + rowcount);
			test.log(Status.FAIL, "Everything matched : \nrccount  : " + rccount + "\nrowcount : " + rowcount);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		monarchConnection.Closeconnection();
		iQuoteConnection.Closeconnection();
		
	}
	}	

}
