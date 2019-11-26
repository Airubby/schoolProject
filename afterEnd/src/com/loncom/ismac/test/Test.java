package com.loncom.ismac.test;

import java.text.ParseException;
import java.util.Date;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.task.MonthQuartz;
import com.loncom.ismac.task.TaskQuartz;
import com.loncom.ismac.util.UtilTime;

public class Test {

	public static void main(String[] args) throws ParseException {
		//UtilTime.getTimes("2019-03-27 17:21:43").getTime();
		try {
			System.out.println(UtilTime.getNowBeforeYear(new Date(),-1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
