package com.loncom.ismac.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTimeThread;
import com.loncom.ismac.util.UtilTool;

public class Test {

	public static void main(String[] args) throws ParseException {
		//UtilTime.getTimes("2019-03-27 17:21:43").getTime();
		String val =UtilTool.cutFloat2(UtilTool.parseFloat("560.03") - UtilTool.parseFloat("560.02") + "");
	 System.out.println(val);
	}

}
