package com.loncom.ismac.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loncom.ismac.annotation.MethodInfo;
import com.loncom.ismac.annotation.Modular;
import com.loncom.ismac.application.AppContext;
import com.loncom.ismac.bean.RquestObject;
import com.loncom.ismac.database.bean.DatabaseBean;
import com.loncom.ismac.util.BaseUtil;
import com.loncom.ismac.util.UtilTime;

import net.sf.json.JSONArray;


@Modular(MODULARNAME="数据库备份模块")
public class DataBaseLogAction extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	@MethodInfo(METHOD="/database/query",LOGSNAME="分页查询备份数据库",ISLOG=false)
	public String query() throws Exception{
		DatabaseBean obj=new DatabaseBean();
		return JSONArray.fromObject(baseservice.query(obj)).toString();
	}
	
	@MethodInfo(METHOD="/database/back",LOGSNAME="还原数据库",ISLOG=false)
	public RquestObject back() throws Exception{
		RquestObject remsg = new RquestObject();
		String time=getRequest().getParameter("time");
//		DatabaseBean obj=new DatabaseBean();
		String savePath=AppContext.getPropSet("savePath").trim();
		String url=AppContext.getPropSet("sys_url").split("/")[2];
		String databaseName=AppContext.getPropSet("sys_url").split("/")[3];
		String hostIP=url.split(":")[0];
		String port=url.split(":")[1];
		String userName=AppContext.getPropSet("sys_username");
		String password=AppContext.getPropSet("sys_password"); 
		try {
            Runtime runtime = Runtime.getRuntime();
            String backsql="mysql -h "+hostIP+" -u"+userName+" -p"+password+" --default-character-set=utf8 "+databaseName;
//            Process process = runtime.exec("mysql -hlocalhost -uroot -prooter --default-character-set=utf8 ismacnx");
            Process process = runtime.exec(backsql);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(savePath+time+".sql"), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            // System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
            remsg.setErr_code("0");
			remsg.setErr_msg("还原成功!");
        } catch (IOException e) {
            e.printStackTrace();
            remsg.setErr_code("1");
			remsg.setErr_msg("还原失败，可能是备份的数据库被删除!");
        }
		return remsg;
	}
	public static void main(String[] args) {
		DataBaseLogAction d=new DataBaseLogAction();
		try {
			d.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
