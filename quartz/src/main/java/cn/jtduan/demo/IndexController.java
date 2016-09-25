package cn.jtduan.demo;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by djt on 9/24/16.
 * One Job can has many triggers
 * but One trigger can only has one job
 */
@Controller
public class IndexController {

    /**
     * trigger '/add' url before
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("")
    @ResponseBody
    public String index() throws SchedulerException {
        SchedulerFactoryBean bean = SpringUtil.getBean(SchedulerFactoryBean.class);
        Trigger.TriggerState s =bean.getScheduler().getTriggerState(TriggerKey.triggerKey("newtrigger", "group1"));
        System.out.println(s);
        return "index";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add() throws SchedulerException {
        SchedulerFactoryBean bean = SpringUtil.getBean(SchedulerFactoryBean.class);
        if(!bean.getScheduler().checkExists(JobKey.jobKey("newjob", "group1"))) {
            JobDetail job = newJob(MyJob.class).withIdentity("newjob", "group1").build();
            CronTrigger trigger = newTrigger().withIdentity("newtrigger", "group1").withSchedule(cronSchedule("0/2 * * * * ?"))
                    .build();
            bean.getScheduler().scheduleJob(job, trigger);
        }

        bean.getScheduler().getJobGroupNames().forEach((d)->{
            System.out.println(d);
        });

        bean.getScheduler().getJobKeys(GroupMatcher.anyGroup()).forEach((d)->{
            System.out.println("job:"+d);
        });

        bean.getScheduler().getTriggerKeys(GroupMatcher.anyGroup()).forEach(System.out::println);

        return "success";
    }

    @RequestMapping("pause")
    @ResponseBody
    public String pause() throws SchedulerException {
        SchedulerFactoryBean bean = SpringUtil.getBean(SchedulerFactoryBean.class);

        if(!bean.getScheduler().checkExists(JobKey.jobKey("newjob", "group1"))) {
            return "visit '/add' first...";
        }
        bean.getScheduler().pauseJob(JobKey.jobKey("newjob", "group1"));
        return "success";
    }


    @RequestMapping("resume")
    @ResponseBody
    public String resume() throws SchedulerException {
        SchedulerFactoryBean bean = SpringUtil.getBean(SchedulerFactoryBean.class);

        if(!bean.getScheduler().checkExists(JobKey.jobKey("newjob", "group1"))) {
            return "visit '/add' first...";
        }

        bean.getScheduler().resumeJob(JobKey.jobKey("newjob", "group1"));
        return "success";
    }
    @RequestMapping("immediately")
    @ResponseBody
    public String immediately() throws SchedulerException {
        SchedulerFactoryBean bean = SpringUtil.getBean(SchedulerFactoryBean.class);
        /**
         * start the custom job immediately
         */
        bean.getScheduler().triggerJob(JobKey.jobKey("job1"));
        return "success";
    }

}
