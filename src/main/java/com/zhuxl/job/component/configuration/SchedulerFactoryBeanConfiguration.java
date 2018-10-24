package com.zhuxl.job.component.configuration;

import com.zhuxl.job.component.listener.JobListenerImpl;
import com.zhuxl.job.component.listener.SchedulerListenerImpl;
import com.zhuxl.job.component.listener.TriggerListenerImpl;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

/**
 * @author zhuxl
 */
@Configuration
public class SchedulerFactoryBeanConfiguration {

    @Autowired
    private QuartzSchedulerConfiguration schedulerConfiguration;

    @Autowired
    private QuartzJobStoreConfiguration jobStoreConfiguration;

    @Autowired
    private QuartzThreadPoolConfiguration threadPoolConfiguration;

    @Autowired
    private QuartzDataSourceConfiguration dataSourceConfiguration;

    @Autowired
    private QuartzPluginConfiguration pluginConfiguration;

    @Bean("schedulerFactoryBean")
    @DependsOn("mybatisSqlInterceptor")
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setOverwriteExistingJobs(true);
        factoryBean.setAutoStartup(true);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        return factoryBean;
    }

    @Bean("scheduler")
    public Scheduler getScheduler() {
        Scheduler scheduler = schedulerFactoryBean().getScheduler();
        try {
            scheduler.getListenerManager().addTriggerListener(new TriggerListenerImpl());
            scheduler.getListenerManager().addJobListener(new JobListenerImpl());
            scheduler.getListenerManager().addSchedulerListener(new SchedulerListenerImpl());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }

    /**
     * 定时任务集群配置
     * 设置属性
     *
     * @return
     */
    private Properties quartzProperties() {
        Properties prop = new Properties();
        prop.put("quartz.scheduler.instanceName", schedulerConfiguration.getInstanceName());
        prop.put("org.quartz.scheduler.instanceId", schedulerConfiguration.getInstanceId());
        prop.put("org.quartz.scheduler.skipUpdateCheck", schedulerConfiguration.isSkipUpdateCheck());
        prop.put("org.quartz.scheduler.jmx.export", schedulerConfiguration.isJmxExport());

        prop.put("org.quartz.jobStore.class", jobStoreConfiguration.getClazz());
        prop.put("org.quartz.jobStore.driverDelegateClass", jobStoreConfiguration.getDriverDelegateClass());
        prop.put("org.quartz.jobStore.dataSource", jobStoreConfiguration.getDataSource());
        prop.put("org.quartz.jobStore.tablePrefix", jobStoreConfiguration.getTablePrefix());
        prop.put("org.quartz.jobStore.isClustered", jobStoreConfiguration.getCluster());
        prop.put("org.quartz.jobStore.clusterCheckinInterval", jobStoreConfiguration.getClusterCheckinInterval());
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", jobStoreConfiguration.getMaxMisfiresToHandleAtATime());
        prop.put("org.quartz.jobStore.misfireThreshold", jobStoreConfiguration.getMisfireThreshold());
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", jobStoreConfiguration.isTxIsolationLevelSerializable());
        prop.put("org.quartz.jobStore.selectWithLockSQL", jobStoreConfiguration.getSelectWithLockSql());

        prop.put("org.quartz.threadPool.class", threadPoolConfiguration.getClazz());
        prop.put("org.quartz.threadPool.threadCount", threadPoolConfiguration.getThreadCount());
        prop.put("org.quartz.threadPool.threadPriority", threadPoolConfiguration.getThreadPriority());
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", threadPoolConfiguration.isThreadsInheritContextClassLoaderOfInitializingThread());

        prop.put("org.quartz.dataSource.myDS.driver", dataSourceConfiguration.getDriver());
        prop.put("org.quartz.dataSource.myDS.URL", dataSourceConfiguration.getUrl());
        prop.put("org.quartz.dataSource.myDS.user", dataSourceConfiguration.getUser());
        prop.put("org.quartz.dataSource.myDS.password", dataSourceConfiguration.getPassword());
        prop.put("org.quartz.dataSource.myDS.maxConnections", dataSourceConfiguration.getMaxConnections());

//        prop.put("org.quartz.plugin.triggHistory.class", pluginConfiguration.getTriggHistoryClass());
//        prop.put("org.quartz.plugin.shutdownhook.class", pluginConfiguration.getShutdownhookClass());
//        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", pluginConfiguration.isShutdownhookCleanShutdown());
        return prop;
    }
}
