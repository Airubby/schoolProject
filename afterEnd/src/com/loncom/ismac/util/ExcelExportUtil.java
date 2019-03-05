package com.loncom.ismac.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.servlet.ServiceAction;


public class ExcelExportUtil {
	
	/**
	 * 设置excel的第一行标题
	 * 
	 * @param row
	 * @param style
	 * @param map
	 */
	private static void setExcelTitle(Row row, CellStyle style, Map<Integer, String> map) {
		row.setRowStyle(style);
		createCellSetValue(row, map);
	}
	/**
	 * 使用row对象创建对应的列和设置列的值</br>
	 * map的key是列序号，value是列的值
	 * 
	 * @param row
	 * @param map
	 */
	private static void createCellSetValue(Row row, Map<Integer, String> map) {
		Set<Integer> keys = map.keySet();
	
		for (Integer key : keys) {
			row.createCell(key).setCellValue(map.get(key));
		}
	}
	public static void excelPower(SXSSFWorkbook wb, String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		SXSSFSheet sh = (SXSSFSheet) wb.createSheet("用电数据");// 建立新的sheet对象
		sh.setRandomAccessWindowSize(-1);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		Row row = sh.createRow(0); // 创建第一行对象
		// 第四步，创建单元格，并设置值表头 设置表头居中
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式
		
		
		IBaseService baseservice= new BaseServiceImpl();
		List<ClassroomXml> classlist=new ArrayList<ClassroomXml>();
		List<ClassroomXml> dormlist=new ArrayList<ClassroomXml>();
		List title=new ArrayList<>();
		String sql="",rpttable="",allsql = "",tsql="";
		for(Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				title.add(groupXml.getGroupname());
				classlist=BaseUtil.getService(groupXml.getGroupno(), "classroom", null, null);
				dormlist=BaseUtil.getService(groupXml.getGroupno(), "officeroom", null, null);
				for(ClassroomXml classroomXml : classlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					sql=" select * from %s where time >= '%s' and time < '%s' UNION";
					sql=String.format(sql,rpttable,startTime,endTime);
					allsql+=sql;
				}
				for (ClassroomXml classroomXml : dormlist) {
					rpttable=String.format(CMD.RPT_DEVTABLE, classroomXml.getCode());
					sql=" select * from %s where time >= '%s' and time < '%s' UNION";
					sql=String.format(sql,rpttable,startTime,endTime);
					allsql+=sql;
				}
			}
		}
		allsql=allsql.substring(0, allsql.length()-5);
		tsql="select cast(sum(a.onevalue) as decimal(10,2)) as onevalue,cast(sum(a.twovalue) as decimal(10,2)) as twovalue,cast(sum(a.allvalue) as decimal(10,2)) as allvalue, time "
				+ "from ("+allsql+") a group by a.time";
		List<Map<String, Object>> list = baseservice.getSqlListS(tsql,DB.HIS);
		System.out.println(list);
		
		// -----------定义表头-----------
		Map<Integer, String> titleMap = new HashMap<Integer, String>();
		titleMap.put(0, "时间");
		titleMap.put(1, "总用电量");
		for(int i=0;i<title.size();i++) {
			titleMap.put(Integer.parseInt(i+2+""), (String) title.get(i));
		}
		setExcelTitle(row, style, titleMap);
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> dataMap =  list.get(i);
			row = sh.createRow(i + 1);
			Map<Integer, String> excelMap = dataMapToExcelMapNew(dataMap,title);
			createCellSetValue(row, excelMap);
			dataMap.clear();
			excelMap.clear();
		}
		
	}
	
	public static Map<Integer, String> dataMapToExcelMapNew(Map<String, Object> dataMap,List titleMap){
		Map<Integer, String> excelMap=new HashMap<Integer, String>();

		excelMap.put(0, (String) dataMap.get("TIME"));
		excelMap.put(1, (String) dataMap.get("ALLVALUE"));
		for(int i=0;i<titleMap.size();i++) {
//			Map<String,Object> tmap=new HashMap<String,Object>();
//			tmap=(Map<String, Object>) titleMap.get(i);
//			String value=(String) dataMap.get((String) tmap.get("prop"));
			String value="";
			if(i==0) {
				value=(String) dataMap.get("ONEVALUE");
			}else {
				value=(String) dataMap.get("TWOVALUE");
			}
			if(!BaseUtil.isNotNull(value)) {
				value="-";
			}
			excelMap.put(Integer.parseInt(i+2+""), value);
		}
		return excelMap;
	}

}
