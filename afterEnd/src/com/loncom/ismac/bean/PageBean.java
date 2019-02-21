package com.loncom.ismac.bean;

import java.util.List;

import com.loncom.ismac.annotation.Attachment;

/**
 * 结果集
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings({"rawtypes","unused"})
public class PageBean  {
	
	
	
	/** 每页显示数量 */
	@Attachment(ISENABLE=false)
	protected int pageSize=5000;


	/** 结果集合 */
	@Attachment(ISENABLE=false)
	protected List items;

	/** 开始条数 */
	@Attachment(ISENABLE=false)
	protected int starindex;


	/** 下一页 */
	@Attachment(ISENABLE=false)
	protected int pageIndex;
	
	/**
	 * 总条数
	 */
	@Attachment(ISENABLE=false)
	protected int count;


	

	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageIndex() {
		return pageIndex;
	}


	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	
	public int getStarindex() {
		if(getPageIndex()==1 || getPageIndex()==0){
			return 0;
		}else{
			return getPageSize()*(getPageIndex()-1);
		}
	}


	public void setStarindex(int starindex) {
		this.starindex = starindex;
	}


	public List getItems() {
		return items;
	}


	public void setItems(List items) {
		this.items = items;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

}
