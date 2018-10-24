/**
 * All rights Reserved, Designed By MiGu Copyright(C) 2017 Company MiGu Co., Ltd.
 */
package com.zhuxl.job.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


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

}

