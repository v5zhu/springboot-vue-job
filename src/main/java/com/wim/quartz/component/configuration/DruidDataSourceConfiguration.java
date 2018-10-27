package com.wim.quartz.component.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * @author zhuxiaolong
 * @project BDC_CS
 * @package com.wim.quartz.component.configuration
 * @description
 * @time 2018/10/27 23:36
 */
@Configuration
@ConfigurationProperties("spring.datasource")
@Getter
@Setter
public class DruidDataSourceConfiguration {
    private static Logger logger = LoggerFactory.getLogger(DruidDataSourceConfiguration.class);

    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private String type = "com.alibaba.druid.pool.DruidDataSource";

    // 初始化连接
    private int initialSize = 5;
    private int minIdle = 5;
    // 最大连接数量
    private int maxActive = 20;
    // 超时等待时间以毫秒为单位
    private int maxWait = 60000;
    private int timeBetweenEvictionRunsMillis = 60000;
    private int minEvictableIdleTimeMillis = 300000;
    private String validationQuery = "SELECT 1 FROM DUAL";
    private boolean testWhileIdle = true;
    private boolean testOnBorrow = false;
    private boolean testOnReturn = false;
    private boolean poolPreparedStatements = true;
    private int maxPoolPreparedStatementPerConnectionSize = 20;
    private String filters = "stat,wall";
    private String connectionProperties;

    @Bean
    public DruidDataSource getDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration Exception", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;

    }

}
