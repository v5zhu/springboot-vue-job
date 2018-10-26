/**
 * All rights Reserved, Designed By MiGu Copyright(C) 2017 Company MiGu Co., Ltd.
 */
package com.zhuxl.job.framework.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author zhuxiaolong
 */
@Component
public class RedisClient {

    @Qualifier("redisSessionTemplate")
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    public boolean isExistKey(String key) {
        System.out.println("检测到是否存在key" + redisTemplate.hasKey(key));
        return redisTemplate.hasKey(key);

    }

    public Long nextDistributeUniqueId(Integer businessCode) {
        // 前缀业务编码+时间（精确日）
        // ID=业务编码4位+时间（精确日）17位+自增序列6位
        String prefix = new StringBuffer(26)
                .append(businessCode)
                .append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
                .toString();

        // 首先查询redis是否存在prefix key
        // 存在则直接自增1
        Long result = redisTemplate.opsForValue().increment(prefix, 1L);
        // 每毫秒允许最多999999个自增ID,允许高并发，因此这里暂时不做其他溢出处理
        String postfix = StringUtils.leftPad(String.valueOf(result), 6, "0");
        Long uniqueId = Long.valueOf(prefix + postfix);
        long s=1001001002018102518L;
        return uniqueId;
    }

}

