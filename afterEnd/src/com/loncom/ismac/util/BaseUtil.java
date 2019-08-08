package com.loncom.ismac.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.Validate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.loncom.ismac.annotation.Attachment;
import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.annotation.Table;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.DataPack;
import com.loncom.ismac.bean.DevheadBean;
import com.loncom.ismac.bean.MessagePack;
import com.loncom.ismac.bean.PageBean;
import com.loncom.ismac.bean.Rquest;
import com.loncom.ismac.bean.tm.Dataroot1;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.DevheadXml;
import com.loncom.ismac.bean.xml.DeviceHeadListXml;
import com.loncom.ismac.bean.xml.DevvouXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.bean.xml.RootXml;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.logs.Logs;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.scanning.PackageClass;
import com.loncom.ismac.scanning.PackageClassTable;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.task.his.HisDev;
import com.loncom.ismac.websocket.AsynServlet;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

@SuppressWarnings({ "unchecked", "rawtypes", "finally", "unused" })

public class BaseUtil {
	private static final String String = null;
	private static Log logger = LogFactory.getLog(BaseUtil.class);
	// public static ByteBuffer buffer=ByteBuffer.allocate(102400);
	private static int lengths = -1;

	/**
	 * 类映射变量自动绑定参数
	 * 
	 * @param model
	 *            绑定类
	 * @param map
	 *            绑定参数提供方
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static String setAllComponentsName(String classurl, Map map)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class classa = Class.forName(classurl);
		Object model = (Object) classa.newInstance();

		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				// 获取原来的访问控制权限
				boolean accessFlag = field[j].isAccessible();
				// 修改访问控制权限
				field[j].setAccessible(true);
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (map.size() > 0) {
					Set<Map.Entry<String, String>> set = map.entrySet();
					for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
						boolean bl = BaseUtil.getZdType(type, model, name, entry.getKey().toLowerCase(),
								entry.getValue());
						if (bl) {
							map.remove(entry.getKey());
							break;
						}
					}
					// System.out.println(name+":"+field[j].get(model));
					// 恢复访问控制权限
					field[j].setAccessible(accessFlag);
				} else {
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} finally {
			JSONArray js = JSONArray.fromObject(model);
			return js.toString();
		}
	}

	public static byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		System.arraycopy(src, begin, bs, 0, count);
		return bs;
	}
	/**
	 * 传入全类名获得对应类中所有方法名和参数名
	 * 
	 * @param classurl
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void setAllComponentsName(String classurl, List<Object> nameList) {
		try {
			String modularname = "";
			PackageClass packclass = new PackageClass();
			Class clazz = Class.forName(classurl);
			if (clazz.isAnnotationPresent(Modular.class) == true) {
				Modular xs = (Modular) clazz.getAnnotation(Modular.class);
				modularname = xs.MODULARNAME();
			}
			Method[] methods = clazz.getMethods();
			for (int i = 0; i < methods.length; i++) {
				packclass = new PackageClass();
				String methodName = methods[i].getName();
				packclass.setMethods(methodName);
				packclass.setClassurl(classurl);
				if (methods[i].isAnnotationPresent(MethodInfo.class) == true) {
					MethodInfo xs = methods[i].getAnnotation(MethodInfo.class);
					packclass.setMethodsurl(xs.METHOD());
					packclass.setOperation(xs.LOGSNAME());
					packclass.setModulrname(modularname);
					packclass.setIslog(xs.ISLOG());
					nameList.add(packclass);

				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// System.out.println(nameList.size());

	}

	/**
	 * 传入全类名获得对应类中所有方法名和参数名
	 * 
	 * @param classurl
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void setAllTableName(String classurl, List<Object> nameList) {
		try {
			String modularname = "";
			PackageClassTable packclass = new PackageClassTable();
			Class clazz = Class.forName(classurl);
			if (clazz.isAnnotationPresent(Table.class) == true) {
				Table xs = (Table) clazz.getAnnotation(Table.class);
				packclass.setTable(xs.NAME());
				packclass.setUrl(classurl);
				nameList.add(packclass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// System.out.println(nameList.size());

	}

	/**
	 * 类映射变量自动绑定参数
	 * 
	 * @param model
	 *            绑定类
	 * @param map
	 *            绑定参数提供方
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object setAllComponentsName(Class classurl, Map map)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/* Class classa = Class.forName(classurl); */
		Object model = classurl
				.newInstance();/* (Object) classa.newInstance(); */

		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				// 获取原来的访问控制权限
				boolean accessFlag = field[j].isAccessible();
				// 修改访问控制权限
				field[j].setAccessible(true);
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (map.size() > 0) {
					Set<Map.Entry<String, String>> set = map.entrySet();
					for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

						boolean bl = BaseUtil.getZdType(type, model, name, entry.getKey().toLowerCase(),
								entry.getValue() + "");

						if (bl) {
							map.remove(entry.getKey());
							break;
						}

					}
					// System.out.println(name+":"+field[j].get(model));
					// 恢复访问控制权限
					field[j].setAccessible(accessFlag);
				} else {
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			return model;
		}
	}

	public static Object setAllComponentsNameApp(Class classurl, Map map)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		/* Class classa = Class.forName(classurl); */
		Object model = classurl
				.newInstance();/* (Object) classa.newInstance(); */

		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				// 获取原来的访问控制权限
				boolean accessFlag = field[j].isAccessible();
				// 修改访问控制权限
				field[j].setAccessible(true);
				String name = field[j].getName(); // 获取属性的名字
				name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (map.size() > 0) {
					Set<Map.Entry<String, String>> set = map.entrySet();
					for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();

						boolean bl = BaseUtil.getZdType(type, model, name, entry.getKey(), entry.getValue() + "");

						if (bl) {
							map.remove(entry.getKey());
							break;
						}

					}
					// System.out.println(name+":"+field[j].get(model));
					// 恢复访问控制权限
					field[j].setAccessible(accessFlag);
				} else {
					break;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			return model;
		}
	}

	/**
	 * 根据实体类映射出需要操作的字段
	 * 
	 * @param classurl
	 *            实体类
	 * @return 条件集合
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */

	public static List<Map<String, Object>> setAllComponentsName(Object classurl, String key)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Object model = classurl;
		Field[] field = getAllFields(model.getClass()); // 获取实体类的所有属性，返回Field数组

		for (int j = 0; j < field.length; j++) { // 遍历所有属性
			map = new HashMap<String, Object>();
			String name = field[j].getName(); // 获取属性的名字
			name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
			Method m = model.getClass().getMethod("get" + name);
			Object value = m.invoke(model);
			if (value != null) {
				if (isNotNull(value + "")) {
					if (field[j].isAnnotationPresent(Attachment.class) == true) {
						Attachment xs = field[j].getAnnotation(Attachment.class);
						if (xs.ISENABLE()) {
							map.put("attachment", xs);
							map.put("key", name);
							map.put("value", value);
							list.add(map);
						} else if (CMD.SELECT.equals(key) && !xs.ISENABLE()) {
							map.put("attachment", xs);
							if (!"".equals(xs.COLUMNNAME())) {
								map.put("key", xs.COLUMNNAME());

							} else {
								map.put("key", name);

							}
							map.put("value", value);
							list.add(map);
						}

					} else {
						map.put("key", name);
						map.put("value", value);
						list.add(map);
					}
				}else{
					if (field[j].isAnnotationPresent(Attachment.class) == true) {
						Attachment xs = field[j].getAnnotation(Attachment.class);
						if (xs.UPDATENULL()) {
							map.put("attachment", xs);
							map.put("value", value);
							map.put("key", name);
							list.add(map);
						} 
					}
				}
			} else {

				if (field[j].isAnnotationPresent(Attachment.class) == true) {
					Attachment xs = field[j].getAnnotation(Attachment.class);
					if (CMD.SELECT.equals(key) && !"".equals(xs.SQL())) {
						map.put("attachment", xs);
						map.put("key", name);
						map.put("value", value);
						list.add(map);
					}
				}
			}

		}
		return list;
	}

	public static Field[] getAllFields(final Class<?> cls) {
		final List<Field> allFieldsList = getAllFieldsList(cls);
		return allFieldsList.toArray(new Field[allFieldsList.size()]);
	}

	public static List<Field> getAllFieldsList(final Class<?> cls) {
		Validate.isTrue(cls != null, "The class must not be null");
		final List<Field> allFields = new ArrayList<Field>();
		Class<?> currentClass = cls;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			for (final Field field : declaredFields) {
				allFields.add(field);
			}
			currentClass = currentClass.getSuperclass();
		}
		return allFields;
	}
	/*
	 * public static String SqlSplit(String type,String name,Object model,String
	 * attachment) throws IllegalAccessException, IllegalArgumentException,
	 * InvocationTargetException, NoSuchMethodException, SecurityException{
	 * String str="";
	 * 
	 * 
	 * if(value!=null){ str=name+" "+sutAttrachment(attachment,value); } return
	 * str;
	 * 
	 * }
	 */

	public static String sutAttrachment(String attachment, Object value) {

		if (value instanceof String) {
			if ("like".equals(attachment)) {
				return attachment + " '%" + value + "%'";
			} else {
				return attachment + " '" + value + "'";
			}
		} else {
			return attachment + " " + value + "";
		}

	}

	public static boolean getZdType(String type, Object model, String name, String namev, String values)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException,
			NoSuchMethodException {
		namev = namev.substring(0, 1).toUpperCase() + namev.substring(1);

		if (name.equals(namev)) {
			if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
															// "，后面跟类名
				Method m = model.getClass().getMethod("get" + name);
				m = model.getClass().getMethod("set" + name, String.class);
				m.invoke(model, values);
				return true;
			}
			if (type.equals("class java.lang.Integer")) {
				Method m = model.getClass().getMethod("get" + name);
				m = model.getClass().getMethod("set" + name, Integer.class);
				m.invoke(model, Integer.parseInt(values));
				return true;
			}
			if (type.equals("class java.lang.Boolean")) {
				Method m = model.getClass().getMethod("get" + name);
				m = model.getClass().getMethod("set" + name, Boolean.class);
				m.invoke(model, values);
				return true;
			}
			if (type.equals("class java.util.Date")) {
				Method m = model.getClass().getMethod("get" + name);
				m = model.getClass().getMethod("set" + name, Date.class);
				m.invoke(model, values);
				return true;
			}
			if (type.equals("int")) {
				Method m = model.getClass().getMethod("get" + name);
				m = model.getClass().getMethod("set" + name, int.class);
				m.invoke(model, values);
				return true;
			}

		}
		return false;
	}

	/**
	 * 解码Byte数组为String
	 * 
	 * @param bytes
	 *            Byte数组
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getStringByte(byte[] data) throws UnsupportedEncodingException {
		String a = new String(data, 24, data.length - 30);
		return a;
	}

	/**
	 * 解码Byte数据为INt
	 * 
	 * @param res
	 *            Byte数组
	 * @param index
	 *            起始位置
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static int getIntByte(byte[] data, int index) throws UnsupportedEncodingException {
		int targets = (data[index] < 0 ? (data[index] & 0xff) & 0xff : data[index] & 0xff)
				| (((data[index + 1] < 0 ? data[index + 1] & 0xff : data[index + 1]) << 8) & 0xff00)
				| (((data[index + 2] < 0 ? data[index + 2] & 0xff : data[index + 2]) << 24) >>> 8)
				| (data[index + 3] < 0 ? data[index + 3] & 0xff : data[index + 3] << 24);
		return targets;
	}

	// byte 数组与 int 的相互转换
	public static int byteArrayToInt(byte[] b, int index) {
		return b[index + 3] & 0xFF | (b[index + 2] & 0xFF) << 8 | (b[index + 1] & 0xFF) << 16 | (b[index] & 0xFF) << 24;
	}

	/*
	 * public static int getIntByte(byte[] b,int index) { return b[index+3] &
	 * 0xFF | (b[index+2] & 0xFF) << 8 | (b[index+1] & 0xFF) << 16 | (b[index+0]
	 * & 0xFF) << 24; }
	 */

	/**
	 * 解码Byteshort
	 * 
	 * @param res
	 *            Byte数组
	 * @param index
	 *            起始位置
	 * @return
	 */
	public static short getShortByte(byte[] data, int index) {
		return (short) (((data[index + 1] << 8) | data[index + 0] & 0xff));
	}

	/**
	 * 注释：short到字节数组的转换！
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] shortToByte(short number) {
		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();//
			temp = temp >> 8;// 向右移8位
		}
		return b;
	}

	/**
	 * 字符串解析成Byte数组
	 * 
	 * @param s字符串
	 * @return
	 */
	public static byte[] getByteString(String s) {
		return s.getBytes();
	}

	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) (a & 0xFF), (byte) ((a >> 8) & 0xFF), (byte) ((a >> 16) & 0xFF),
				(byte) ((a >> 24) & 0xFF)

		};
	}

	/**
	 * 转换short为byte
	 * 
	 * @param b
	 * @param s
	 *            需要转换的short
	 * @param index
	 */
	public static byte[] getByteShort(short s) {
		byte b[] = new byte[2];
		b[1] = (byte) (s >> 8);
		b[0] = (byte) (s >> 0);
		return b;
	}

	public static long getLongByte(byte[] bb) {
		return ((((long) bb[0] & 0xff) << 56) | (((long) bb[1] & 0xff) << 48) | (((long) bb[2] & 0xff) << 40)
				| (((long) bb[3] & 0xff) << 32) | (((long) bb[4] & 0xff) << 24) | (((long) bb[5] & 0xff) << 16)
				| (((long) bb[6] & 0xff) << 8) | (((long) bb[7] & 0xff) << 0));
	}


	public static String getAddDevStr(String[] value) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(
				"INSERT INTO `devvou` (`AgentBM`, `DevName`, `State`, `MgrObjId`, `ID`, `ch`, `WrkState`, `DEBUG`, `SecondID`, `DataChar`, `RecHis`, `Report`, `EventAlarm`, `UnEventAlarm`, `EmaxTimes`, `NoEvent`, `Value`, `TM`, `Unit`, `vFormat`, `DefValue`, `MaxAlarm`, `MaxAlarm2`, `MaxAlarm3`, `MaxAlarm4`, `MaxEvent`, `MinAlarm`, `MinAlarm2`, `MinAlarm3`, `MinAlarm4`, `MinEvent`, `AlarmRang`, `Stride`, `StrideMarginValue`, `MaxContentDo`, `MaxContentUnDo`, `MinContentDo`, `MinContentUnDo`, `State0`, `State1`, `ActionAlarm`, `ActionEvent`, `ActionContent`, `Tx`, `MasterId`, `DebugData`, `Calib`, `BGPos`, `Len`, `NoTimes`, `Rang`, `TimeNo`, `Times`, `RxTime`, `CrcType`, `MaxValue`, `MinValue`, `PublishMsg`, `enablekpi`, `signal_group`, `indicator_group`) VALUES ( ");

		for (String string : value) {
			if ("".equals(string)) {
				buffer.append(null + ",");
			} else {
				buffer.append("'" + string + "',");
			}

		}

		buffer = buffer.delete(buffer.lastIndexOf(","), buffer.lastIndexOf(",") + 1);

		buffer.append(");");
		return buffer.toString();

	}

	/**
	 * GID唯一标示
	 * 
	 * @return
	 */
	public static String getUUID() {
		String codeFormat = "%s%s";
		Random rand = new Random();
		int random = rand.nextInt(99);
		String time = System.nanoTime() + "";
		String date = time.substring(time.length() - 8, time.length());
		return String.format(codeFormat, date, random);
	}

	/**
	 * session唯一标示
	 * 
	 * @return
	 */
	public static String getSID() {
		String codeFormat = "%s%s";
		Random rand = new Random();
		int random = rand.nextInt(10000);
		
		String date = UtilTime.fromDateToString(new Date(), "HHmmss");
		return String.format(codeFormat, date, random);
	}

	

	
	/**
	 * 判断是否有rpt并根据教学楼数量动态创建
	 * @param rpttable 
	 * @throws Exception 
	 */
	public static void createRPT(String rpttable) throws Exception {
		IBaseService baseservice= new BaseServiceImpl();
		String sql="";
		//按楼创建value值
		for(int i=0;i<AppContext.getService().get(0).getGroupcontrol().getGroup().size();i++) {
			sql+="  `value"+i+"` varchar(20) DEFAULT NULL,";
		}
		String CREATE_RPT_DEV=" CREATE TABLE %s ( "+
	    		"  `id` int(11) NOT NULL AUTO_INCREMENT,"+
	    		"  `mgrobjid` varchar(50) DEFAULT NULL,"+
	    		"  `pointid` varchar(50) DEFAULT NULL,"+
	    		"  `value` varchar(20) DEFAULT NULL,"+
	    		"  `time` varchar(20) DEFAULT NULL,"+
	    		"  `allvalue` varchar(20) DEFAULT NULL,"+
	    		sql+
	    	"	  PRIMARY KEY (`id`)"+
	    	"	)";
		baseservice.exeSql(String.format(CREATE_RPT_DEV, rpttable), DB.HIS);
	}
	/**
	 * 返回教学楼的数量
	 * @param rpttable 
	 * @throws Exception 
	 */
	public static int floorNum() {
		IBaseService baseservice= new BaseServiceImpl();
		int num=AppContext.getService().get(0).getGroupcontrol().getGroup().size();
		return num;
	}


	/**
	 * 执行脚本
	 * 
	 * @param shellString
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean callShell(String path) {
		boolean flag = false;
		String cmdstring = "chmod a+x " + path;
		try {

			Process process = Runtime.getRuntime().exec(cmdstring);
			process.waitFor();

			cmdstring = "bash " + path;
			process = Runtime.getRuntime().exec(cmdstring);
			List<String> processList = new ArrayList<String>();
			final BufferedReader brError = new BufferedReader(
					new InputStreamReader(process.getErrorStream(), "gb2312"));
			final BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
			int exitValue = process.waitFor();
			if (0 == exitValue) {
				// 0表示成功
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;

	}

	public static String SyslogType(String type) {
		if ("1".equals(type)) {
			return "新增";
		} else if ("2".equals(type)) {
			return "修改";
		} else if ("3".equals(type)) {
			return "删除";
		} else {
			return "其他";
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            创建路径
	 * @param files
	 *            文件内容
	 * @throws IOException
	 */

	public static void FoundFile(String path, String files) throws IOException {
		OutputStream output = null;
		try {
			File file = new File(path + "/template.json");
			if (!file.getParentFile().exists()) { // 如果文件的目录不存在
				file.getParentFile().mkdirs(); // 创建目录
			}
			// 2: 实例化OutputString 对象
			output = new FileOutputStream(file);
			// 3: 准备好实现内容的输出
			byte bytes[] = files.getBytes("UTF-8");
			output.write(bytes);
			// 4: 资源操作的最后必须关闭
			output.close();
		} finally {
			if (output != null) {
				output.close();
			}
		}

	}

	/**
	 * 用缓冲区读写，来提升读写效率。
	 * 
	 * @throws IOException
	 */
	public static boolean CopyRighWay(String Path, String copypath) throws IOException {

		// 缓冲字节流一次读取一个字节数组
		File start = new File(Path);
		File end = new File(copypath);
		BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(start));
		BufferedOutputStream bfo = new BufferedOutputStream(new FileOutputStream(end));
		byte[] by = new byte[1024];
		int len = 0;
		while ((len = bfi.read(by)) != -1) {
			bfo.write(by, 0, len);
		}
		bfi.close();
		bfo.close();
		return true;
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 *
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean DeleteFile(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + fileName + "不存在！");
			return false;
		} else {
			if (file.isFile())
				return deleteFile(fileName);
			else
				return deleteDirectory(fileName);
		}
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 *
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}

	// base64字符串转化成图片
	public static byte[] GenerateImage(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		BASE64Decoder base64Decoder = new BASE64Decoder();
		BASE64Decoder decoder = base64Decoder;
		imgStr = imgStr.substring("data:image/png;base64,".length(), imgStr.length());
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	// base64字符串转化成图片
	public static boolean GenerateImage(String path, String imgStr) { // 对字节数组字符串进行Base64解码并生成图片
		imgStr = imgStr.replace(" ", "+");
		imgStr = imgStr.substring("data:image/png;base64,".length(), imgStr.length());
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = path + "/thumbnail.png";// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取一个目录下面所有文件名称
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<String> getFiles(String path) {
		ArrayList<String> files = new ArrayList<String>();
		File file = new File(path);
		File[] tempList = file.listFiles();

		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				// System.out.println("文 件：" + tempList[i]);
				files.add(tempList[i].getName());
			}
			if (tempList[i].isDirectory()) {
				// System.out.println("文件夹：" + tempList[i]);
			}
		}
		return files;
	}

	

	/**
	 * 是否空
	 * 
	 * @param value
	 *            判断值
	 * @return true 不是空 false 空
	 */
	public static boolean isNotNull(String value) {

		if (value != null && !value.equals("") && !value.equals("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取key值
	 * 
	 * @param one
	 *            原串
	 * @param key
	 *            获取键值
	 */
	public static String getKeyVla(String one, String key) {

		if (isNotNull(one)) {
			key += "=";
			one = one.substring(one.indexOf(key) + key.length());
			if (isNotNull(one)) {
				int i = one.indexOf("##");
				if (i == -1) {
					i = one.indexOf("^^");
				}
				return one.substring(0, i);
			}
			return "";

		}

		return "";
	}

	public static void getKeyVlas(String one, Map map) {

		if (isNotNull(one)) {
			one = one.replace('"', '-');
			String[] split = one.split("-,");
			if (split.length > 0) {
				for (String string : split) {
					String[] splist = string.split("-:");
					map.put(splist[0].substring(1, splist[0].length()), splist[1].substring(1, splist[1].length()));
				}
			}

		}

	}

	/**
	 * 根据type获取Action
	 * 
	 * @param type
	 *            KEY
	 * @return
	 */
	public static Map<String, String> getAction(String type) {
		Map<String, String> map = new HashMap<String, String>();
		if ("devall".contains(type)) {
			map.put("action", "/Dev/getdevtree");
			map.put("type", "devouall");
			return map;
		} else if ("devreport".contains(type)) {
			map.put("action", "/Dev/getdevtree");
			map.put("type", "devoureport");
			return map;
		} else if ("devouall".contains(type)) {
			map.put("action", "/devvou/gettree");
			map.put("type", "all");
		} else if ("devoureport".contains(type)) {
			map.put("action", "/devvou/gettree");
			map.put("type", "report");
		}

		return map;
	}

	/**
	 * 判断当前时间是否在 list时间范围内
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isAlarmTime(List list) {
		for (Object object : list) {
			Map map = (Map) object;
			long beginlong = UtilTime.getHmsLong(map.get("BEGINTIME") + "");
			long endlong = UtilTime.getHmsLong(map.get("ENDTIME") + "");
			long tilong = UtilTime.getTimeCurrent();
			System.out.println("beginlong:" + beginlong + "endlong:" + endlong + "tilong:" + tilong);
			if (beginlong <= tilong && endlong >= tilong) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 获取开始时间
	 * 
	 * @param rpttype
	 * @param day
	 * @return
	 */
	public static String getTimebegintime(String rpttype, String day) {
		if ("1".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getBeginOfDay();
			} else {
				return UtilTime.getThedaybefore().split(",")[0];
			}

		} else if ("2".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getTimeInterval(new Date()).split(",")[0];
			} else {
				return UtilTime.getLastTimeInterval().split(",")[0];
			}
		} else if ("3".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getMonthend().split(",")[0];
			} else {
				return UtilTime.getTheMonthend().split(",")[0];
			}
		}
		return null;
	}

	/**
	 * 字符串转化成为16进制字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String strTo16(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	/**
	 * 获取结束时间
	 * 
	 * @param rpttype
	 * @param day
	 * @return
	 */
	public static String getTimeendtime(String rpttype, String day) {
		if ("1".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getNow();
			} else {
				return UtilTime.getThedaybefore().split(",")[1];
			}

		} else if ("2".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getTimeInterval(new Date()).split(",")[1];
			} else {
				return UtilTime.getLastTimeInterval().split(",")[1];
			}
		} else if ("3".equals(rpttype)) {
			if ("0".equals(day)) {
				return UtilTime.getMonthend().split(",")[1];
			} else {
				return UtilTime.getTheMonthend().split(",")[1];
			}
		}
		return null;
	}

	public static String getTime(String rpttype, String day, String key) {
		if ("endtime".equals(key)) {
			return getTimeendtime(rpttype, day);
		} else {
			return getTimebegintime(rpttype, day);
		}
	}
	/**
	 * 拼接分页查询sql<br/>
	 * 1 MYSQL 2 Dm 3 Oralce
	 * 
	 * @param from
	 *            查询表SQL语句
	 * @param page
	 *            分页对象
	 * @return
	 */
	public static String joinPageQuerySql(String from, PageBean page) {

		String datakey = AppContext.getPropSet("sys_driverClass");
		StringBuilder buff = new StringBuilder();

		switch (getDataDriver(datakey)) {
		case 1:
			buff.append(from);
			buff.append(" LIMIT ?,?");
			break;
		case 2:
		case 3:
			buff.append("select a.* from (select  a1.*,rownum rn from ( ");
			buff.append(from);
			buff.append(") a1 where rownum <=");
			buff.append(page.getPageSize() + page.getStarindex());
			buff.append(") a where rn>");
			buff.append(page.getStarindex());
			break;
		default:
			buff.append(from);
			break;
		}

		return buff.toString();
	}
	

	/**
	 * 获取数据库驱动映射ID
	 * 
	 * @param driver
	 * @return
	 */
	public static int getDataDriver(String driver) {

		if (driver.lastIndexOf("mysql") > -1) {

			return 1;
		} else if (driver.lastIndexOf("dm") > -1) {

			return 2;
		} else if (driver.lastIndexOf("oracle") > -1) {

			return 3;
		}

		return 1;
	}
	public static String getIPAddress(HttpServletRequest request) {
		String ip = null;

		// X-Forwarded-For：Squid 服务代理
		String ipAddresses = request.getHeader("X-Forwarded-For");

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// Proxy-Client-IP：apache 服务代理
			ipAddresses = request.getHeader("Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// WL-Proxy-Client-IP：weblogic 服务代理
			ipAddresses = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// HTTP_CLIENT_IP：有些代理服务器
			ipAddresses = request.getHeader("HTTP_CLIENT_IP");
		}

		if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			// X-Real-IP：nginx服务代理
			ipAddresses = request.getHeader("X-Real-IP");
		}

		// 有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
		if (ipAddresses != null && ipAddresses.length() != 0) {
			ip = ipAddresses.split(",")[0];
		}

		// 还是不能获取到，最后再通过request.getRemoteAddr();获取
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
			ip = request.getRemoteAddr();
			if ("0:0:0:0:0:0:0:1".equals(ip)) {
				ip = "localhost";
			}
		}
		return ip;
	}
	
	/**
	 * 根据路径查找接口层访问对象
	 * 
	 * @param key
	 *            路径键
	 * @return
	 */
	public static PackageClass getPackClass(String key) {
		PackageClass packageobj = null;
		for (Object obj : AppContext.getPackagelist()) {
			PackageClass packageclass = (PackageClass) obj;
			if (packageclass.getMethodsurl().equals(key)) {
				packageobj = packageclass;
				break;
			}
		}
		return packageobj;
	}
	
	/**
	 * V3解析数据
	 * 
	 * @param data
	 *            byte数据源
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	public synchronized static void dataAnalysis(byte[] data)
			throws UnsupportedEncodingException, InterruptedException {

		MessagePack msgPack = new MessagePack();

		msgPack.Decoding(data);

		byte[] bodaydata = msgPack.getDataBody();

		if (bodaydata != null && bodaydata.length > 0) {

			int index = 0;

			StringBuilder surplusStr = new StringBuilder();

			do {

				byte[] tempdata = null;

				if (bodaydata.length - index < 4096) {
					tempdata = new byte[bodaydata.length - index];
				} else {
					tempdata = new byte[4096];
				}

				System.arraycopy(bodaydata, index, tempdata, 0, tempdata.length);

				surplusStr.append(new String(tempdata, "utf-8"));

				int tempIndex = surplusStr.toString().lastIndexOf('}');

				String tempStri = surplusStr.toString().substring(0, tempIndex + 1).concat("]");

				if (tempStri.startsWith(",")) {
					tempStri = "[" + tempStri.substring(1);
				} else if (tempStri.startsWith("{")) {
					tempStri = "[" + tempStri.substring(0);
				}
				surplusStr.delete(0, tempIndex + 1);
				if (3 == msgPack.getDataType()) {
				//	analysisAlarm(tempStri);
				} else if (1 == msgPack.getDataType()) {
					HandleData(tempStri);
				} else if (4 == msgPack.getDataType()) {
					 HandleState(tempStri);
				} else if (2 == msgPack.getDataType()) {
//				 HandleCom(tempStri);
				} else if (5 == msgPack.getDataType()) {
					//AirTypeData(tempStri);

				}
				index += tempdata.length;
			} while (index < bodaydata.length);

			msgPack = null;
			data = null;
		}

	}
	
	/**
	 * 设备状态包
	 * 
	 * @param data
	 */
	public static void HandleCom(String data) {
//		System.out.println("*******************"+data);
	}
	public static void HandleState(String data) {
		//System.out.println(data);
		JSONArray array = JSONArray.fromObject(data);
		JSONObject obj;
		//commStatus 0 正常
		for(int i=0;i<array.size();i++) {
			obj=JSONObject.fromObject(array.get(i));
			//System.out.println(!obj.get("commStatus").equals("0"));
			if(!obj.get("commStatus").equals("0")) {
				clearDevdata(obj.get("mgrObjId")+"",null);
			}
		}
		
		
	}
	
	/**
	 * 清除指定设备或者指定设备属性数据
	 * 
	 * @param mgrobjid
	 *            设备ID
	 * @param pointid
	 *            属性id
	 */
	public static void clearDevdata(String mgrobjid, String pointid) {
		DevheadBean devhend = AppContext.getDevhead().get(mgrobjid);
		if (devhend != null) {
			if (BaseUtil.isNotNull(pointid)) {
				devhend.getItem().get(mgrobjid + "_" + pointid).setValue("");
				send(devhend.getItem().get(mgrobjid + "_" + pointid));
			} else {
				Iterator<Entry<String, DevvouXml>> iter = devhend.getItem().entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, DevvouXml> entry = iter.next();
					entry.getValue().setValue("");
					send(entry.getValue());
				}
			}
		}
	}
	
	public static void removeDevValue(DeviceHeadListXml devicelist) {
		if(devicelist!=null) {
			DevheadBean devhead = new DevheadBean();
			for(DevheadXml devheadx : devicelist.getDev()) {
				for(DevvouXml devvouxml : devheadx.getPoint()) {
					devhead.getItem().get(devheadx.getDevid() + "_" + devvouxml.getId()).setValue("");
					send(devhead.getItem().get(devheadx.getDevid() + "_" + devvouxml.getId()));
				}
			}
		}
	}

	public static void send(DevvouXml dev) {
		List list=new ArrayList<>();
//		List<DataPack> datalist = new ArrayList<DataPack>();
		Map<String,Object> map=new HashMap<String,Object>();
//		Rquest<DataPack> request = new Rquest<DataPack>();
//		DataPack datapack = new DataPack();
////		datapack.setMgrobjid(dev.getMgrobjid());
////		datapack.setPropertyId(dev.getId());
////		datapack.setValue(dev.getValue());
//		datalist.add(datapack);
		// HisDev.HisStorageDev(datalist);
//		request.setData(datalist);
//		request.setCmd("data");
		
		map.put("key", dev.getMgrobjid()+"_"+dev.getId());
		map.put("value", dev.getValue());
		list.add(map);
		
	//	JSONObject json1 = JSONObject.fromObject(request);
	//	System.out.println(json1.toString());
		JSONArray json2=JSONArray.fromObject(list);
		setMsg(json2.toString());
	}
	
	/**
	 * 发送实时值
	 * 
	 * @param data
	 * @throws InterruptedException
	 */

	public static void HandleData(String data) throws InterruptedException {
		JSONArray array = JSONArray.fromObject(data);
		MorphDynaBean bean = null;
		List<DataPack> datalist = new ArrayList<DataPack>();
		List json = JSONArray.toList(array);
		DataPack datapack = null;
		Map<String,Object> map=new HashMap<String,Object>();
		List list=new ArrayList<>();
		for (Object object : json) {
			map=new HashMap<String,Object>();
			bean = (MorphDynaBean) object;
			datapack = new DataPack();
			datapack.setAgentbm(bean.get("agentBM") + "");
			datapack.setDatachar(bean.get("dataType") + "");
			datapack.setMgrobjid(bean.get("mgrObjId") + "");
			datapack.setPropertyId(bean.get("propertyId") + "");
			datapack.setValue(bean.get("value") + "");
			datapack.setMarktime(bean.get("markTime") + "");
			
			datalist.add(datapack);
			
			map.put("key", bean.get("mgrObjId")+"_"+bean.get("propertyId"));
			map.put("value", bean.get("value"));
			list.add(map);
			// AppContext.hisQueueVouData.put(datapack);
			try {
				//System.out.println(datapack.getMarktime());
				HisDev.HisStorageDev(datapack);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Rquest<DataPack> request = new Rquest<DataPack>();
		// HisDev.HisStorageDev(datalist);
		AppContext.UpdateDevvouValue(datalist);
		request.setData(datalist);
		request.setCmd("data");
		JSONObject json1 = JSONObject.fromObject(request);
//		System.out.println("实时字符串:" + json1.toString());
		
		JSONArray json2=JSONArray.fromObject(list);
		setMsg(json2.toString());
	}
	
	/**
	 * 发送数据
	 * @param string
	 */
	public synchronized static void setMsg(String msg) {
		for (AsynServlet asyn : AsynServlet.webSocketSet) {
			try {
				synchronized (asyn){
					asyn.sendMessage(msg);
				}
				//测试
//				Map<String,Object> map=new HashMap<String,Object>();
//				map.put("key", "80294_000001");
//				map.put("value","11111");
//				List datalist=new ArrayList<>();
//				datalist.add(map);
//				JSONArray json=JSONArray.fromObject(datalist);
//				asyn.sendMessage(json.toString());
			} catch (IOException e) {
				Logs.logasyn("数据发送失败!");
			}
		}
	}

	/**
	 * 获取属性对象
	 * 
	 * @param agentbm
	 *            站点编码
	 * @param mgrobjid
	 *            设备ID
	 * @param id
	 *            设备属性ID
	 */

	public static String getDevvouValue(String mgrobjid, String id) {
		DevvouXml devvou = null;
		DevheadBean devhead = AppContext.getDevhead().get(mgrobjid);
		if (devhead != null) {
			devvou = devhead.getItem().get(mgrobjid + "_" + id);
			if(devvou!=null) {
				if("commstatus".equals(devvou.getDatachar()) || "digital".equals(devvou.getDatachar())){
					return BaseUtil.isNotNull(devvou.getValue())?devvou.getValue():"0";
				}
			}
		}
		
		return devvou != null ? UtilTool.cutFloat2(devvou.getValue()) :"0";
	}
	
	/**
	 * 获取服务的信息（教学楼，教室等）
	 * groupno:楼
	 * classno:教室办公室：classroom/officeroom
	 * floor:是第几层楼
	 * classname:教室办公室名称
	 * code:教室办公室编码
	 */
	public static List getService(String groupno,String classno,String floor,String classname,String code) {
		List<ClassroomXml> list=new ArrayList<ClassroomXml>();
		for (Service service : AppContext.getService()) {
			for (GroupXml groupXml : service.getGroupcontrol().getGroup()) {
				String groupno1=groupXml.getGroupno();
				if(!BaseUtil.isNotNull(groupno)||groupno.equals(groupno1)) {
					if(!BaseUtil.isNotNull(classno)||classno.equals("classroom")) {
						if(groupXml.getClassroomgroup().getItem()!=null)
						for (ClassroomXml classroomXml: groupXml.getClassroomgroup().getItem()) {
							classroomXml.setServerid(service.getId());
							//如果获取详情就直接获取后return
							if(BaseUtil.isNotNull(code)&&classroomXml.getCode().equals(code)) {
								List<ClassroomXml> detaillist=new ArrayList<ClassroomXml>();
								detaillist.add(classroomXml);
								return detaillist;
							}
							//有楼层，有名字
							if(BaseUtil.isNotNull(floor)&&BaseUtil.isNotNull(classname)) {
								if(classroomXml.getClassname().indexOf(classname)!=-1
										&&BaseUtil.isNotNull(classroomXml.getFloor())
										&&classroomXml.getFloor().equals(floor)) {
									list.add(classroomXml);
								}
							}else if(!BaseUtil.isNotNull(floor) && BaseUtil.isNotNull(classname)) {
								if(classroomXml.getClassname().indexOf(classname)!=-1) {
									list.add(classroomXml);
								}
							}else if(BaseUtil.isNotNull(floor) && !BaseUtil.isNotNull(classname)) {
								if(BaseUtil.isNotNull(classroomXml.getFloor())&&classroomXml.getFloor().equals(floor)) {
									list.add(classroomXml);
								}
							}else {
								list.add(classroomXml);
							}
							
						}
					}
					if(!BaseUtil.isNotNull(classno)||classno.equals("officeroom")) {
						if(groupXml.getOfficegroup().getItem()!=null) {
							for (ClassroomXml classroomXml : groupXml.getOfficegroup().getItem()) {
								classroomXml.setServerid(service.getId());
								//如果获取详情就直接获取后return
								if(BaseUtil.isNotNull(code)&&classroomXml.getCode().equals(code)) {
									List<ClassroomXml> detaillist=new ArrayList<ClassroomXml>();
									detaillist.add(classroomXml);
									return detaillist;
								}
								//有楼层，有名字
								if(BaseUtil.isNotNull(floor)&&BaseUtil.isNotNull(classname)) {
									if(classroomXml.getClassname().indexOf(classname)!=-1
											&&BaseUtil.isNotNull(classroomXml.getFloor())
											&&classroomXml.getFloor().equals(floor)) {
										list.add(classroomXml);
									}
								}else if(!BaseUtil.isNotNull(floor) && BaseUtil.isNotNull(classname)) {
									if(classroomXml.getClassname().indexOf(classname)!=-1) {
										list.add(classroomXml);
									}
								}else if(BaseUtil.isNotNull(floor) && !BaseUtil.isNotNull(classname)) {
									if(classroomXml.getFloor().equals(floor)) {
										list.add(classroomXml);
									}
								}else {
									list.add(classroomXml);
								}
							}
						}
					}
				}
			}
		}
		return list;
	}
	public static List<GroupXml> getFloor(){
		List<GroupXml> list=new ArrayList<GroupXml>();
		for (GroupXml groupXml : AppContext.getService().get(0).getGroupcontrol().getGroup()) {
			list.add(groupXml);
		}
		return list;
	}
	public static Service getService(String serviceid){
		for (Service service : AppContext.getService()) {
			if(service.getId().equals(serviceid)) {
				return service;
			}
		}
		return null;
	}
	
	/**
	 * 对象转换XML
	 * 
	 * @param groupxml
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void ObjToXMl(RootXml groupxml, String filepath) throws IOException, JAXBException {
		if (groupxml != null) {
			String content = JaxbUtil.toXml(groupxml);
			System.out.println(content);
			FileUtil.writeFileContent(filepath, content);
		}
	}
	/**
	 * 对象转换XML
	 * 
	 * @param groupxml
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void ObjToXMl(Dataroot1 groupxml, String filepath) throws IOException, JAXBException {
		if (groupxml != null) {
			String content = JaxbUtil.toXml(groupxml);
			FileUtil.writeFileContent(filepath, content);
		}
	}
	
	
	public static List downSort(List<Map<String, Object>> list,String type) {
		List backlist=new ArrayList<>();
		for(int i=0;i<list.size()-1;i++) {
			for(int j=0;j<list.size()-1-i;j++) {
				if(UtilTool.parseFloat(list.get(j).get(type)+"")<UtilTool.parseFloat(list.get(j+1).get(type)+"")) {
					Map<String, Object> temp=list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
		return list;
	}

}
