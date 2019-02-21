package com.loncom.ismac.scanning;

public class PackageClass {
	
	private String classurl;//类全路径
	private String methodsurl;//调用方法路径
	private String methods;//调用方法
	private String operation;//操作名称
	private String modulrname;//模块名称
	private boolean islog;//是否记录日志
	public String getClassurl() {
		return classurl;
	}
	public void setClassurl(String classurl) {
		this.classurl = classurl;
	}
	public String getMethodsurl() {
		return methodsurl;
	}
	public void setMethodsurl(String methodsurl) {
		this.methodsurl = methodsurl;
	}
	public String getMethods() {
		return methods;
	}
	public void setMethods(String methods) {
		this.methods = methods;
	}
	
	public String toString(){
		return this.getClassurl()+":"+this.getMethods()+":"+this.getMethodsurl();
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getModulrname() {
		return modulrname;
	}
	public void setModulrname(String modulrname) {
		this.modulrname = modulrname;
	}
	public boolean getIslog() {
		return islog;
	}
	public void setIslog(boolean islog) {
		this.islog = islog;
	}
	

}
