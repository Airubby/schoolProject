package com.loncom.ismac.user.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 树形
 * @author youtao
 *
 */
public class TreeService {
	private String id;//编码
    private String label; //指定节点标签为节点
    private List<TreeService> children=new ArrayList<TreeService>();//集合
    private boolean disabled;//是否禁用
    private boolean isLeaf;//否为叶子节点
    private Map<String,Object> map=new HashMap<String,Object>();
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<TreeService> getChildren() {
		return children;
	}
	public void setChildren(List<TreeService> children) {
		this.children = children;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public boolean isLeaf() {
		return isLeaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}


	
	
    
}
