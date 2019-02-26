package com.Testcases;

import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.testng.annotations.Test;

import com.base.Testbase;
import com.aventstack.extentreports.Status;
import com.base.DBUtil;
import com.resources.Basefile;
public class IQMN_JS_JN2 extends Testbase {
	@Test
	public static void Testing() throws ClassNotFoundException, SQLException {
		Basefile bs = new Basefile();
		bs.executetestcase("JobSpecMaterial");
	}
}
