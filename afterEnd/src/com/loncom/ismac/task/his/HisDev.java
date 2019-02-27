package com.loncom.ismac.task.his;

import java.util.ArrayList;
import java.util.List;

import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;

public class HisDev {
	
	private static long datatime = UtilTime.getTimeMax();
	private static String devtable = "";
	private static int comitcount = 10;
	
	static List<DataPack> datapcklist = new ArrayList<DataPack>();
	
	public static void HisStorageDev(DataPack data) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buffer=new StringBuffer();
		IBaseService service = new BaseServiceImpl();
		Boolean bs=false;
		datapcklist.add(data);
		if(datapcklist.size()>comitcount) {
			devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
			for (DataPack dataPack : datapcklist) {
				long time=UtilTime.getTimes(dataPack.getMarktime()).getTime();
				if(time>datatime) {
					if(bs) {
						service.exeSql(buffer.substring(0, buffer.toString().lastIndexOf(",")), DB.HIS);
					}
					devtable=String.format(CMD.HIS_DEVTABLE, UtilTime.getYMD());
					service.exeSql(String.format(CMD.CREATE_HIS_DEV, devtable),DB.HIS);
					buffer=new StringBuffer();
					buffer.append(String.format(CMD.HIS_DEVINSERT, devtable));
					buffer.append(String.format(CMD.HIS_DEVINSERT_VALUES, dataPack.getMgrobjid(),
							dataPack.getPropertyId(), dataPack.getValue(), dataPack.getMarktime()));
					datatime = UtilTime.getTimeMax();
				}else {
					buffer.append(String.format(CMD.HIS_DEVINSERT_VALUES, dataPack.getMgrobjid(),
							dataPack.getPropertyId(), dataPack.getValue(), dataPack.getMarktime())); 
					bs=true;
				}
			}
			service.exeSql(buffer.substring(0, buffer.toString().lastIndexOf(",")), DB.HIS);
		}
		
	}

}
