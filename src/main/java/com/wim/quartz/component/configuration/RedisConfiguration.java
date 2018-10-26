package com.wim.quartz.component.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.component.configuration
 * @description
 * @time 2018/10/25 9:22
 */
@ConfigurationProperties(prefix = "redis-cluster")
@Configuration
@Getter
@Setter
public class RedisConfiguration {
    private List<RedisClusterNode> redisNodes;

    private Integer maxRedirects;

    @Getter
    @Setter
    static class RedisClusterNode {
        String host;
        Integer port;
    }

    public List<RedisNode> getRedisClusterNodes() {
        if (redisNodes != null && redisNodes.size() > 0) {
            List<RedisNode> nodes = new ArrayList<RedisNode>();
            for (RedisClusterNode rcn : redisNodes) {
                nodes.add(new RedisNode(rcn.getHost(), rcn.getPort()));
            }
            return nodes;
        }
        return new ArrayList<RedisNode>();
    }
}
