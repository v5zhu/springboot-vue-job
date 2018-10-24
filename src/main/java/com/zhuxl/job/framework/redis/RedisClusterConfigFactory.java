package com.zhuxl.job.framework.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * RedisHttpSessionConfiguration的配置文件 引入RedisHttpSessionConfiguration.class
 * maxInactiveIntervalInSeconds设置session在redis里的超时
 * 该类使用与spring data redis 2.0版本以上的用法
 */
@Configuration
public class RedisClusterConfigFactory {


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(redisClusterConfiguration());
        return jedisConnectionFactory;
    }

    @Bean
    public RedisConnection redisConnection() {

        return jedisConnectionFactory().getClusterConnection();
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        List<RedisNode> redisNodeList = new ArrayList<RedisNode>();
        redisNodeList.add(new RedisNode("47.96.28.236", 7000));
        redisNodeList.add(new RedisNode("47.96.28.236", 7001));
        redisNodeList.add(new RedisNode("47.96.28.236", 7002));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(redisNodeList);
        return redisClusterConfiguration;
    }


    @Bean(name = "redisSessionTemplate")
    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer<String> serializer1 = new StringRedisSerializer();
        RedisSerializer<Object> serializer2 = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(serializer1);
        redisTemplate.setValueSerializer(serializer2);
        redisTemplate.setHashKeySerializer(serializer1);
        redisTemplate.setHashValueSerializer(serializer2);

        return redisTemplate;
    }
}
