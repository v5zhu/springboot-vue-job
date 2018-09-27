package com.zhuxl.job.component.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "quartz-cluster.scheduler")
@Getter
@Setter
class QuartzSchedulerConfiguration {
    private String instanceName;

    private String instanceId;

    private boolean skipUpdateCheck;

    private boolean jmxExport;
}

@Configuration
@ConfigurationProperties(prefix = "quartz-cluster.job-store")
@Getter
@Setter
class QuartzJobStoreConfiguration {
    private String clazz;

    private String driverDelegateClass;

    private String dataSource;

    private String tablePrefix;

    /**不能为int*/
    private String cluster;

    /**不能为int*/
    private String clusterCheckinInterval;

    /**不能为int*/
    private String maxMisfiresToHandleAtATime;

    /**不能为int*/
    private String misfireThreshold;

    private boolean txIsolationLevelSerializable;

    private String selectWithLockSql;
}

@Configuration
@ConfigurationProperties(prefix = "quartz-cluster.thread-pool")
@Getter
@Setter
class QuartzThreadPoolConfiguration {
    private String clazz;

    /**不能为int*/
    private String threadCount;

    /**不能为int*/
    private String threadPriority;

    private boolean threadsInheritContextClassLoaderOfInitializingThread;
}

@Configuration
@ConfigurationProperties(prefix = "quartz-cluster.data-source")
@Getter
@Setter
class QuartzDataSourceConfiguration {
    private String driver;

    private String url;

    private String user;

    private String password;

    private int maxConnections;
}

@Configuration
@ConfigurationProperties(prefix = "quartz-cluster.plugin")
@Getter
@Setter
class QuartzPluginConfiguration {
    private String triggHistoryClass;

    private String shutdownhookClass;

    private boolean shutdownhookCleanShutdown;

}