package com.loncom.ismac.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.loncom.ismac.application.AppContext;

@SuppressWarnings("unused")

public class OnlineUserListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	    HttpSession session=event.getSession();
		String id=session.getId()+session.getCreationTime();
		//SummerConstant.UserMap.put(id,Boolean.TRUE);//添加用户

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session=event.getSession();
        String id=session.getId()+session.getCreationTime();
        System.out.println("sessions失效!"+session.getId());
        AppContext.getSID().remove(session.getId());
    

	}

}
