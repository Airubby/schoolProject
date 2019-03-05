package com.loncom.ismac.task;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class TaskQuartz {
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";  
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME"; 
    
    /**
     * 任务调度执行类
     */
    public void executes() {
    	addJob("rpt1",RptDevQuartz.class,"0 */10 * * * ?","");//定时执行执行生成报表
    	
    	addJob("delTables",DelTablesQuartz.class,"57 59 23 * * ? *","1"); //定时检测报表状态  当大于配置保存天数值，进行删除数据操作 
    }
    
    /** 
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
     * @param jobName 任务名 
     * @param cls 任务 
     * @param time 时间设置 
     */  
    public static void addJob(String jobName, Class cls, String time,Object param) {  
        try {  
            Scheduler sched = gSchedulerFactory.getScheduler();  
            // 任务名，任务组，任务执行类  
            JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, cls);  
            //可以传递参数  
            jobDetail.getJobDataMap().put("param", param);  
            // 触发器  
            CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);  
            // 触发器名,触发器组  
            trigger.setCronExpression(time);  
            // 触发器时间设定  
            sched.scheduleJob(jobDetail, trigger);  
            
            // 启动  
            if (!sched.isShutdown()) {  
                sched.start();  
            }  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
    
}
