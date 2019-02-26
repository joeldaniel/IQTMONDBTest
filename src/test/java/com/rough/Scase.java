package com.rough;
import java.io.FileNotFoundException; 
import java.io.PrintWriter; 
import java.util.LinkedHashMap; 
import java.util.Map; 
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Scase {

@SuppressWarnings("unchecked")
public static void main(String[] args) throws IOException {

	HashMap<String, String> HM = new HashMap<String, String>();
	File excelfile = new File(System.getProperty("user.dir") + "\\JsonBuilder.xlsx");
	FileInputStream fis = new FileInputStream(excelfile);

	XSSFWorkbook wb = new XSSFWorkbook(fis);
	Sheet sheet = wb.getSheet("Tests"); // Get Your Sheet.

	for (Row row : sheet) { // For each Row.


		Cell cell1 = row.getCell(0); // Get the Cell at the Index / Column you want.
		System.out.println(cell1.toString());

		Cell cell = row.getCell(1); // Get the Cell at the Index / Column you want.
		System.out.println(cell.toString());
		HM.put(cell1.toString(), cell.toString());

	}
	// creating JSONObject
	JSONArray tests = new JSONArray();
	JSONObject jo = new JSONObject();

	String I_col = HM.get("I_Columns");
	String M_col = HM.get("M_Columns");
	String[] I_cols = I_col.split(",");
	String[] M_cols = M_col.split(",");

	for (int i = 0; i < I_cols.length; i++) {

		JSONObject joe = new JSONObject();
		// JSONArray ja = new JSONArray();

		Map m = new LinkedHashMap(4);

		m.put("_Table", HM.get("I_Table"));
		m.put("_primaryId", HM.get("I_Primary"));
		m.put("_primaryIdQuery",
				"select " + HM.get("I_Primary") + " from " + HM.get("I_Table") + " where " + HM.get("I_Where"));
		m.put("_colName", I_cols[i]);
		
		joe.put("IQT", m);


		Map n = new LinkedHashMap(3);
		n.put("_Table", HM.get("M_Table"));
		n.put("_primaryId", HM.get("M_Primary"));
		n.put("_colName", M_cols[i]);

		joe.put("MON", n);
		tests.add(joe);
	}

	jo.put("Tests", tests);



	// writing JSON to file:"JSONExample.json" in cwd
	PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "\\src\\test\\resources\\JsonOutput\\"+"JSONExample.json");
	pw.write(jo.toJSONString());

	pw.flush();
	pw.close();


}
}
