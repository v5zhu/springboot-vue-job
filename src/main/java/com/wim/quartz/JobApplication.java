package com.wim.quartz;

import com.wim.component.redis.annotation.EnableRedisClusterClient;
import com.wim.component.snowflake.annotation.EnableSnowflakeIdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author xiyuxuan
 */
@SpringBootApplication(
        scanBasePackages = {"com.wim.quartz", "com.wim.component"},
        exclude = DataSourceAutoConfiguration.class
)
@ServletComponentScan
@EnableSnowflakeIdWorker
@EnableRedisClusterClient
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

}
