package com.loncom.ismac.thread;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.task.his.HisDev;

public class HisProcesser extends Thread{
	boolean isRunning=true;
	
	public void run() {
		while(isRunning) {
			try {
				DataPack data = AppContext.hisQueueVouData.take();
				HisDev.HisStorageDev(data);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
