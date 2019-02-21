package com.loncom.ismac.timegroup.service;

import java.util.List;

import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.timegroup.bean.TimeDetail;
import com.loncom.ismac.timegroup.bean.TimeGroup;

import net.sf.ezmorph.bean.MorphDynaBean;

public interface ITimegroupService extends IBaseService<TimeGroup>{

//	void addTime(TimeGroup obj, List<MorphDynaBean> item);

	void addTime(TimeGroup obj, List<TimeDetail> item) throws Exception;
	
}
