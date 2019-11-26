package com.loncom.ismac.service;

import java.util.Map;

/**
 * 主界面
 * @author youtao
 *
 */
public interface IMainService {

	Map<String,Object> queryGroup(String group,String floor)throws Exception;
}
