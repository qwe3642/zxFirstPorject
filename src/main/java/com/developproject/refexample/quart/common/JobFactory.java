package com.developproject.refexample.quart.common;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

public class JobFactory {

    public static  JobFactory jobFactory=new JobFactory();

    private static Scheduler scheduler;

    public static JobFactory getInstance(){
        return jobFactory;
    }

    private JobFactory(){

    }

    public static void initScheduler() {
        try{
            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            scheduler=schedFact.getScheduler();
            scheduler.start();
        }catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
