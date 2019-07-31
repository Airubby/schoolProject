package com.loncom.ismac.task.his;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTimeThread;

public class HisDev {
	
	private static long datatime = UtilTime.getTimeMax();
	private static String devtable = String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
	private static int comitcount = 20;
	
	
	static List<DataPack> datapacklist = new ArrayList<DataPack>();
	
	public synchronized static void HisStorageDev(DataPack data) throws Exception {
		// TODO Auto-generated method stub
		datapacklist.add(data);
		if(datapacklist.size()>comitcount) {
			try {
				StringBuffer buffer=new StringBuffer();
				IBaseService service = new BaseServiceImpl();
				String table=String.format(CMD.HIS_DEVTABLE, UtilTimeThread.format(new Date(), "yyyyMMdd"));
				int count=service.getCount(String.format(CMD.IS_HIS_BASE, table), DB.HIS);
				if(count<=0) {
					service.exeSql(String.format(CMD.CREATE_HIS_DEV, table), DB.HIS);
				}
				buffer.append(String.format(CMD.HIS_DEVINSERT, table));
				for(DataPack dataPack:datapacklist) {
					String time=BaseUtil.isNotNull(dataPack.getMarktime())?dataPack.getMarktime():UtilTimeThread.format(new Date(), "yyyy-MM-dd HH:mm:ss");
					if(BaseUtil.isNotNull(dataPack.getValue())) {
						buffer.append(String.format(CMD.HIS_DEVINSERT_VALUES, dataPack.getMgrobjid(),
								dataPack.getPropertyId(), dataPack.getValue(), time)); 
					}
				}
				service.exeSql(buffer.substring(0, buffer.toString().lastIndexOf(",")), DB.HIS);
			} catch (Exception e) {
				e.printStackTrace();
			   throw new Exception("执行sql语句失败");
			}finally {
				datapacklist = new ArrayList<DataPack>();
			}
			
			
		}
	}
}
