package com.zhuxl.job.component.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.zhuxl.job.component.configuration
 * @description
 * @time 2018/10/26 18:13
 */
@Configuration
@ConfigurationProperties(prefix = "snowflake")
@Getter
@Setter
public class SnowflakeIdConfiguration {
    private String timeFormat;

    private String startTime;

    private Long dataCenterId;

    private Long workerId;

    @Getter
    @Setter
    static class Bit {
        private Long dataCenterIdBits;

        private Long workerIdBits;

        private Long sequenceBits;
    }

}
