package com.loncom.ismac.timegroup.service.impl;

import java.util.List;

import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.timegroup.bean.TimeDetail;
import com.loncom.ismac.timegroup.bean.TimeGroup;
import com.loncom.ismac.timegroup.service.ITimegroupService;
import com.loncom.ismac.util.BaseUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;

public class TimegroupServiceImpl extends BaseServiceImpl<TimeGroup> implements ITimegroupService {

//	@Override
//	public void addTime(TimeGroup obj, List<MorphDynaBean> item) {
//		// TODO Auto-generated method stub
//		System.out.println(item);
//		String sql = "INSERT INTO timegroup(`id`,`name`,`start_time`,`end_time`) values('%s','%s','%s','%s')";
//		try {
//			String id=BaseUtil.getUUID();
//			sql=String.format(sql, id,obj.getName(),obj.getStart_time(),obj.getEnd_time());
//			exeSql(sql);
//			for(int i=0;i<item.size();i++) {
//				MorphDynaBean _item=item.get(i);
//				JSONObject egJo=JSONObject.formObjct(_item);
//				System.out.println(_item);
//				String id1=BaseUtil.getUUID();
////				sql = "INSERT INTO `timedetail` (`id`,`timeweek`, `begintime`, `endtime`, `timegroupid`) VALUES ('%s','%s','%s','%s')";
////				sql = String.format(sql,id1, _item.getTimeweek(),_item.getBegintime(),_item.getEndtime(),_item.getTimegroupid(), id);
////				exeSql(sql);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//	}

	
	public void addTime(TimeGroup obj, List<TimeDetail> item) throws Exception {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO timegroup(`id`,`name`,`start_time`,`end_time`) values('%s','%s','%s','%s')";
		try {
			String id=BaseUtil.getUUID();
			sql=String.format(sql, id,obj.getName(),obj.getStart_time(),obj.getEnd_time());
			exeSql(sql);
			System.out.println(item);
			for(int i=0;i<item.size();i++) {
				TimeDetail _item=item.get(i);
				System.out.println(_item);
				String id1=BaseUtil.getUUID();
				sql = "INSERT INTO `timedetail` (`id`,`timeweek`, `begintime`, `endtime`, `timegroupid`) VALUES ('%s','%s','%s','%s')";
				sql = String.format(sql,id1, _item.getTimeweek(),_item.getBegintime(),_item.getEndtime(),_item.getTimegroupid(), id);
				exeSql(sql);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
