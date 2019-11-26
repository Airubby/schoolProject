package com.loncom.ismac.servlet;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.RquestMsg;
import com.loncom.ismac.bean.RquestObject;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.scanning.PackageClass;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.soket.service.impl.BaseSocketClient;
import com.loncom.ismac.soket.service.impl.LoncomipDataAddOutClient;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CookiesUtil;
import com.loncom.ismac.util.UtilTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 处理所有接口映射
 */
@SuppressWarnings({ "rawtypes", "unused", "unchecked", "null", "resource" })

 @WebServlet("/ismacsite/*")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String otioncontent = "";// 操作日志内容;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	public static String storePath, reportname;
	public String username = "";
	protected IBaseService baseservice = new BaseServiceImpl();
	private BaseSocketClient tcpclient=new LoncomipDataAddOutClient();
	boolean islog = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json; charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("utf-8");
		response.setHeader("P3P","CP=CAO PSA OUR");
		RquestMsg data = new RquestMsg();
		//request.getSession(true);
		this.request = request;
		boolean bs = true;
		OutputStream outs = null;
		
		String sid =getRequest().getSession().getId();
		
		String uri = request.getRequestURI();// 获取URI路径
		String url = uri.substring(uri.lastIndexOf("/ismacsite") + "/ismacsite".length(), uri.length());// 截取，访问的URL
		//System.out.println("SID:" + BaseUtil.isNotNull(AppContext.getSID().get(sid) + "") + "**********SID" + sid + "session"
		//		+ request.getSession().getId()+"SESSIONTIME:  "+request.getSession().getMaxInactiveInterval());
		
if (BaseUtil.isNotNull(AppContext.getSID().get(sid)+"") ||
		 "/user/login".equals(url) ||"user/out".equals(url) ||
		 "/slide/query".equals(url)||"/service/topInfo".equals(url)||"/service/moreDayInfo".equals(url)||
		 "/service/tableTitle".equals(url)||"/service/typeInfo".equals(url)) {
		this.username =getCookie("userid");
		
		/*if ( "/User/login".equals(url)){*/
		 
		
		/*}*/
		AppContext.getSID().put(sid, new Date().getTime());
		
		Map<String, String[]> map = request.getParameterMap();
		/*if ("/customreport/generate".equals(url)) {
			String parm = request.getParameter("parm");
			JSONObject jsonObject = JSONObject.fromObject(parm);
			reportname = jsonObject.getString("reportname");

		} else if ("/customreport/export".equals(url)) {
			storePath = getServletContext().getRealPath("/excelfiles/");

		}*/
		PackageClass packageobj = BaseUtil.getPackClass(url);// 获取模块对象
		String obj = request.getParameter("model");
		BaseServlet baseservlet = null;
		
		try {
			if (packageobj != null) {
				Class clazz = Class.forName(packageobj.getClassurl());
				
				baseservlet =(BaseServlet) requestParameterToBean(request,clazz);
				/*if (obj != null) {
					baseservlet = (BaseServlet) JSONObject.toBean(JSONObject.fromObject(obj), baseservlet.getClass());
				}*/
				baseservlet.setRequest(request);
				baseservlet.setUsername(this.username);
				Method m1 = clazz.getDeclaredMethod(packageobj.getMethods());
				Object msg = m1.invoke(baseservlet);// 调用方法
				if (msg instanceof RquestMsg) {// 返回数据判断
					data = (RquestMsg) msg;
				} else if (msg instanceof File) {
					String fileName = reportname;
					if (UtilTool.isNotNull(fileName)) {

						fileName = "download";
					}
					
				//	response.reset();
					fileName = URLEncoder.encode(fileName + ".xls", "UTF-8");
					response.setHeader("content-type", "text/html;charset=UTF-8");
					response.setContentType("application/x-msdownload");
					response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
					outs = response.getOutputStream();
					File file = (File) msg;
					FileInputStream inputStream = new FileInputStream(file);
					outs = response.getOutputStream();
					byte b[] = new byte[1024];
					int n;
					while ((n = inputStream.read(b)) != -1) {
						outs.write(b, 0, n);
					}

					inputStream.close();

					outs.flush();
					outs.close();
					bs = false;
				} else if (msg instanceof HSSFWorkbook) {
					//response.reset();
					response.setHeader("content-type", "text/html;charset=UTF-8");
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment;filename=export.xls");
					HSSFWorkbook wb = (HSSFWorkbook) msg;
					outs = response.getOutputStream();
					wb.write(outs);
					wb.close();
					outs.flush();
					outs.close();
					bs = false;
				} else if (msg instanceof SXSSFWorkbook) {
				//	response.reset();
					response.setHeader("content-type", "text/html;charset=UTF-8");
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment;filename=export.xls");
					SXSSFWorkbook wb = (SXSSFWorkbook) msg;
					outs = response.getOutputStream();
					wb.write(outs);
					wb.close();
					outs.flush();
					outs.close();
					bs = false;
				} else {
					RquestObject data1 = new RquestObject();
					data1.setErr_code("0");
					data1.setData(msg);
					data1.setErr_msg("操作成功！");
					data = data1;
				}
				//getCookie("roolid");
				
				if (packageobj.getIslog()) {
					// 日志
					Logs.SysLog("00102", BaseUtil.isNotNull(username)==true?username:"admin", packageobj.getModulrname() + ":" +packageobj.getOperation()  + ","
							+ baseservlet.getOtioncontent(), url, BaseUtil.getIPAddress(request));
				}
			} else {
				data.setErr_msg("接口方法未找到!");
				data.setErr_code("1");
				data.setErr_msg("操作失败!");
				// 日志
				Logs.SysLog("00102", BaseUtil.isNotNull(username)==true?username:"admin",
						packageobj.getModulrname() + ":" + packageobj.getOperation() + ",接口方法为找到!", url,
						BaseUtil.getIPAddress(request));
			}

		} catch (Exception e) {
			e.printStackTrace();
			Logs.log(e);
			data.setErr_msg("操作失败!");
			// 日志
			Logs.SysLog("00103", BaseUtil.isNotNull(username)==true?username:"admin",
					packageobj.getModulrname()+ ":" +packageobj.getOperation()  + "访问接口方法不存在或者类内部错误!" + e.getMessage(),
					url, BaseUtil.getIPAddress(request));
			data.setErr_code("1");
			if (e instanceof InvocationTargetException) {
				InvocationTargetException targetExeption = (InvocationTargetException) e;
				String err_msg = targetExeption.getTargetException().getMessage();
				if(BaseUtil.isNotNull(err_msg)){
					if (err_msg.contains("<BusinessException>`")) {
						data.setErr_msg(err_msg.replace("<BusinessException>", ""));
					}
				}
			}

			if (outs != null) {
				outs.flush();
				outs.close();
			}
		}
	
		}else{ data.setErr_msg("请重新登录!"); data.setErr_code("-1"); }

		if (bs) {
			  //CookiesUtil.setCookie(response, "JSESSIONID", getRequest().getSession().getId(), 100000);
			PrintWriter out = response.getWriter();
			JSONObject json = JSONObject.fromObject(data);
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getRequestPayload(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = req.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	

	public String getCookie(String key) {
		Cookie cookie = CookiesUtil.getCookieByName(request, key);
		if (cookie != null) {
			return cookie.getValue();
		}
		return "";
	}

	
	public void setParameter(Map<String, String[]> map, Class clazz) throws Exception{
	    
	    Field[] fields = clazz.getDeclaredFields();
	    for(Field field:fields){
	        String fieldName = field.getName();
	        if(map.get(fieldName) != null){
	        	
	        	UserBean  user=(UserBean) JSONObject.toBean(JSONObject.fromObject(map.get(fieldName)[0]), UserBean.class);	
	        	
	            field.set(this,user);
	        }
	    }
	}
	
	public  <T>T requestParameterToBean(HttpServletRequest request,Class<T> clszz) throws Exception {
	    T obj = null;
	    BeanInfo beanInfo=null;
	    try {
	        //获取该类的信息
	        beanInfo = Introspector.getBeanInfo(clszz);
	        //实例化该class
	        obj = clszz.newInstance();
	    } catch (IllegalAccessException e) {
	        //log.error("实例化 JavaBean 失败");
	    } catch (IntrospectionException e) {
	      //  LOGGER.error("获取分析类属性失败");
	    } catch (InstantiationException e) {
	       // LOGGER.error("实例化 JavaBean 失败");
	    }
	    //获取该类属性的描述
	    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	    Enumeration en = request.getParameterNames();
	    Object bean=null;
	    Method method=null;
	    String beantype="";
	    while (en.hasMoreElements()) {
	        String paramName = (String) en.nextElement();
	        PropertyDescriptor descriptor;
	        for(int i=0;i<propertyDescriptors.length;i++){
	        	if ("model".equals(propertyDescriptors[i].getName())) {
					if (bean == null) {
						bean = propertyDescriptors[i].getPropertyType().newInstance();
					}
					String value = request.getParameter(paramName);
					if (!"[]".equals(value)) {
						beantype = getObStyle(bean.getClass(), paramName);
						if (!"java.util.List".equals(beantype)) {
							BeanUtils.setProperty(bean, paramName, value);
						} else {
							BeanUtils.setProperty(bean, paramName, JSONArray.fromObject(value));
						}
						method = propertyDescriptors[i].getWriteMethod();
						method.invoke(obj, new Object[] { bean });
					}
				}
	        	
	        	
	            if(paramName.equals(propertyDescriptors[i].getName())&&!"class".equals(propertyDescriptors[i].getName())){
	                descriptor = propertyDescriptors[i];
	               // Type[] types=  descriptor.getPropertyEditorClass()
	                
	                String className = descriptor.getPropertyType().getName();
	             
	                method = descriptor.getWriteMethod();
	                if (!("undefined".equals(request.getParameter(paramName))||"null".equals(request.getParameter(paramName)))){
	                    Object value;
	                    //这里的类型不一一枚举，若传过来的class还有别的类型，在这里加上
	                    if(className.equals("java.lang.Boolean")){
	                        value = Boolean.parseBoolean(request.getParameter(paramName));
	                    }else if(className.equals("java.lang.Integer")){
	                        value = Integer.parseInt(request.getParameter(paramName));
	                    }else if(className.equals("java.lang.String")){
	                    	value = request.getParameter(paramName);
	                    }else if(className.equals("java.util.List")){
	                    	ParameterizedType type=(ParameterizedType) descriptor.getReadMethod().getGenericReturnType();
	                    	value = request.getParameter(paramName);
	                    	if(type!=null){
	                    		Class clazz = Class.forName(type.getActualTypeArguments()[0].getTypeName());
		                    	value= JSONArray.toList(JSONArray.fromObject(value),clazz);	

	                    	}else{
		                    	value= JSONArray.toList(JSONArray.fromObject(value));	
	                    	}
	                    	
	                    	
	                    } else {
	                        value =request.getParameter(paramName);
	                        value= JSONObject.toBean(JSONObject.fromObject(value), method.getParameterTypes()[0]);	
	                      /*  if( instanceof ArrayList){
	                        	
	                        }else{
		                       
	                        }*/
	                    }
	                    try {
	                        method.invoke(obj, new Object[]{value});
	                    }catch (IllegalAccessException e) {
	                      //  LOGGER.error("调用set方法失败");
	                    } catch (InvocationTargetException e) {
	                       // LOGGER.error("字段映射失败");
	                    }

	                }
	            }
	        }
	    }
	    return obj;
	}
	
	/**
	 *      * 根据字段名得到类型      * @param ca  要操作的类      * @param name  要获类型的字段名     
	 * * @return      * @throws Exception      *     
	 */
	public static String getObStyle(Class ca, String name) throws Exception {
		Class c = null;
		Field[] field = ca.getDeclaredFields();
		Object info = ca.newInstance();
		for (int i = 0; i < field.length; i++) {
			if (field[i].getName().equals(name)) {
				c = field[i].getType();
				break;
			}
		}
		return c == null ? "" : c.getName();
	}
	
	/**
	 * 获取Cookie登录用户的访问权限
	 * 
	 * @return
	 */
	public String getRoleAddrList() {
		//getCookie("")
		String roleid=getCookie("userid");
		if("admin".equals(roleid)){
			return "0";
		}
		if(AppContext.getUserMap().get(roleid)!=null) {
			return  AppContext.getUserMap().get(roleid).getAddrorrole().equals("")?"-1":AppContext.getUserMap().get(roleid).getAddrorrole();
		}
		return "-1";
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				// delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	protected String getStringFromStream(HttpServletRequest req) {
		ServletInputStream is;
		try {
			is = req.getInputStream();
			int nRead = 1;
			int nTotalRead = 0;
			byte[] bytes = new byte[10240];
			while (nRead > 0) {
				nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
				if (nRead > 0)
					nTotalRead = nTotalRead + nRead;
			}
			String str = new String(bytes, 0, nTotalRead, "utf-8");
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getOtioncontent() {
		return otioncontent;
	}

	public void setOtioncontent(String otioncontent) {
		this.otioncontent = otioncontent;
	}

	protected String getPath(String url) {

		return getRequest().getServletContext().getRealPath(url);
	}
	

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public BaseSocketClient getTcpclient() {
		return tcpclient;
	}

	public void setTcpclient(BaseSocketClient tcpclient) {
		this.tcpclient = tcpclient;
	}
	
}
