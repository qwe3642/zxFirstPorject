package com.developproject.refexample;

import org.quartz.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler=schedFact.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();
        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("job1","group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("定时任务开始执行");
        scheduler.start();
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("定时任务结束");
    }
}
