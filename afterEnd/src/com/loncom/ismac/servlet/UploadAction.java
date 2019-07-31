package com.loncom.ismac.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.bean.Rquest;
import com.loncom.ismac.bean.RquestObject;
import com.loncom.ismac.util.BaseUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UploadAction
 */
@Modular(MODULARNAME="上传模块")
public class UploadAction extends BaseServlet {
	
	private static String slideImg ="";
	private static int num=0;
	/**
	 * 上传图片
	 * @return
	 */
	@MethodInfo(METHOD="/uploadImg")
	public RquestObject uploadImg() {
		
		PrintWriter pw = null;
		RquestObject remsg = new RquestObject();
		try {
			// 设置编码
			// 设置系统环境
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 文件存储的路径
			String storePath = getRequest().getServletContext().getRealPath("/static/upload");
			if (!new File(storePath).exists()) {
				new File(storePath).mkdirs();
			}
			String fileName = null;// 文件名
			boolean isMultipart = ServletFileUpload.isMultipartContent(getRequest());
			if (!isMultipart) {
				remsg.setErr_code("0");
				remsg.setErr_msg("传输方式有错误");
				return remsg;
			}
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(10 * 1024 * 1024);// 设置单个文件大小不能超过10兆
			upload.setSizeMax(10 * 1024 * 1024);// 设置总文件上传大小不能超过10兆
			// 监听上传进度
			upload.setProgressListener(new ProgressListener() {

				// pBytesRead：当前以读取到的字节数
				// pContentLength：文件的长度
				// pItems:第几项
				public void update(long pBytesRead, long pContentLength, int pItems) {
					Rquest<Map<String, Long>> request = new Rquest<Map<String, Long>>();
					List<Map<String, Long>> datalist = new ArrayList<Map<String, Long>>();

					Map<String, Long> datamap = new HashMap<String, Long>();
					datamap.put("upsize", pBytesRead);
					datamap.put("counsize", pContentLength);
					datalist.add(datamap);
					request.setData(datalist);
					request.setCmd("data");
					JSONObject json1 = JSONObject.fromObject(request);
					BaseUtil.setMsg(json1.toString());

				}
			});
			// 解析
			List<FileItem> items = upload.parseRequest(getRequest());
			System.out.println(items);
			for (FileItem item : items) {
				if (item.isFormField())// 普通字段，表单提交过来的
				{
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
				} else {
					InputStream in = item.getInputStream();
					fileName = item.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					if (fileName.lastIndexOf(".") >= -1) {
						fileName = BaseUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
					} else {
						fileName = BaseUtil.getUUID() + "_" + fileName;
					}
					remsg.setData(fileName);
					// 按日期来建文件夹
					// String newStorePath = makeStorePath(storePath);
					String newStorePath = storePath;
					String storeFile = newStorePath + "/" + fileName;
					OutputStream out = new FileOutputStream(storeFile);
					byte[] b = new byte[1024];
					int len = -1;
					while ((len = in.read(b)) != -1) {
						out.write(b, 0, len);
					}
					in.close();
					out.close();
					
				}
			}
			
			remsg.setErr_code("0");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			remsg.setErr_code("1");
			remsg.setErr_msg("后台服务错误!");
		}
		return remsg;
		
	}
}
