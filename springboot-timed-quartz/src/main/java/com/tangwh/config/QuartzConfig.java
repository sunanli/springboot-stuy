package com.tangwh.config;

import com.sun.org.apache.regexp.internal.RE;
import com.tangwh.job.MySecondJob;
import org.quartz.JobDataMap;
import org.quartz.SchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

import java.util.Date;

@Configuration
public class QuartzConfig {

    /**
     * 不支持传参数 定时任务主要干什么
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(){


        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        // bean 的类名首字母小写
        bean.setTargetBeanName("myFirstJob");
        // 方法
        bean.setTargetMethod("sayHello");
        return bean;

    }


    /**
     * 支持传参 定时任务主要干什么
     * @return
     */
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean(){

        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        // 配置定时任务
        JobDataMap map = new JobDataMap();
        map.put("name", "javaboy");
        bean.setJobDataMap(map);



        return bean;
    }


    /**
     * 触发器
     * @return
     */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean(){


        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        // 绑定的事件
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        // 触发的时间
        bean.setStartTime(new Date());
        // 间隔 2s
        bean.setRepeatInterval(2000);
        // 重复次数 3
        bean.setRepeatCount(3);

        return bean;
    }



    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean(){


        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        bean.setCronExpression("* * * * * ?");
        return bean;

    }
    @Bean
    SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());


        return bean;
    }


}
