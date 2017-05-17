package cn.jtduan.util.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by djt on 9/24/16.
 */
@Configuration
public class SchedledConfiguration {

    /**
     * JobDetail
     * @param scheduledTasks
     * @return
     */
    @Bean(name ="job1")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetObject(new ScheduledTasks());
        bean.setTargetMethod("currentTime");
        bean.setConcurrent(false);
        bean.setName("job1");
        return bean;
    }

    @Bean(name ="trigger1")
    public CronTriggerFactoryBean myConf2(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/4 * * * * ?");
        bean.setName("trigger1");
        bean.setJobDetail(detailFactoryBean().getObject());
        return bean;
    }



    /**
     * JobDetail
     * @return
     */
    @Bean(name ="job2")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MyJob.class);
        bean.setDurability(true);
        bean.setName("job2");
        return bean;
    }

    @Bean(name ="trigger2")
    public CronTriggerFactoryBean myConf(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("0/4 * * * * ?");
        bean.setName("trigger2");
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        return bean;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setTriggers(myConf().getObject(),myConf2().getObject());
        schedulerFactory.setJobDetails(jobDetailFactoryBean().getObject(),detailFactoryBean().getObject());
        return schedulerFactory;
    }

}
