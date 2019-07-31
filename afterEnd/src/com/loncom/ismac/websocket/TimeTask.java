package com.loncom.ismac.websocket;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimeTask {
	Timer timer = null;

	public void run() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				for (AsynServlet obj : AsynServlet.webSocketSet) {
					if (obj.getTime() != 0) {
						System.out.println(obj.getTimes() + ":" + obj.getTime());
						if (obj.getTimes() == obj.getTime()) {
							obj.setYzcount(obj.getYzcount() + 1);
							System.out.println("验证次数:" + obj.getYzcount());
							if (obj.getYzcount() == 3) {
								try {
									System.out.println("关闭掉");
									obj.getSession().close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						} else {
							obj.setYzcount(0);
							obj.setTimes(obj.getTime());
						}
					}
				}
			}
		}, 3000, 3000);// 设定指定的时间time,此处为2000毫秒

	}

}
