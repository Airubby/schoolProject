package com.loncom.ismac.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.xml.ClassroomXml;
import com.loncom.ismac.bean.xml.GroupXml;
import com.loncom.ismac.database.bean.DatabaseBean;
import com.loncom.ismac.jdbc.DB;
import com.loncom.ismac.lservice.bean.Service;
import com.loncom.ismac.service.IBaseService;
import com.loncom.ismac.service.impl.BaseServiceImpl;
import com.loncom.ismac.user.bean.UserBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.CMD;
import com.loncom.ismac.util.UtilTime;
import com.loncom.ismac.util.UtilTool;

public class BackupMySqlQuartz implements Job{
	Object obj=null;
	IBaseService baseservice= new BaseServiceImpl();
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		obj=new BackupMySqlQuartz();
		try {
			task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void task() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("备份mysql数据库");
		DatabaseBean obj=new DatabaseBean();
		String savePath=AppContext.getPropSet("savePath").trim();
		String url=AppContext.getPropSet("sys_url").split("/")[2];
		String databaseName=AppContext.getPropSet("sys_url").split("/")[3];
		String hostIP=url.split(":")[0];
		String port=url.split(":")[1];
		String userName=AppContext.getPropSet("sys_username");
		String password=AppContext.getPropSet("sys_password");
		String time=UtilTime.getNow();
		String fileName=UtilTime.getNows()+".sql";
		File saveFile = new File(savePath);  
        if (!saveFile.exists()) {// 如果目录不存在  
            saveFile.mkdirs();// 创建文件夹  
        }  
        if(!savePath.endsWith(File.separator)){  
            savePath = savePath + File.separator;  
        }  
          
        try {
            Runtime rt = Runtime.getRuntime();
            String dump="mysqldump -h "+hostIP+" -u"+userName+" -p"+password+" "+databaseName;
            // 调用 调用mysql的安装目录的命令
//            Process child = rt.exec("mysqldump -h localhost -uroot -prooter ismacnx");
            Process child = rt.exec(dump);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
 
            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
 
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
 
            // 要用来做导入用的sql目标文件：
//            FileOutputStream fout = new FileOutputStream("d:\\test2222.sql");
            FileOutputStream fout = new FileOutputStream(savePath+fileName);
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
            
            obj.setId(BaseUtil.getUUID());
            obj.setTime(time);
            baseservice.Add(obj);
            
 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
