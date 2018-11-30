package com.wim.quartz.service;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.wim.quartz.JobApplication;
import com.wim.quartz.business.entity.BizType;
import com.wim.quartz.business.service.BizTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhuxiaolong
 * @project springboot-vue-quartz
 * @package com.wim.quartz.service
 * @description
 * @time 2018/11/29 16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApplication.class)
@ActiveProfiles("test")
public class BizTypeServiceTest {
    @Autowired
    private BizTypeService bizTypeService;

    @Test
    public void test() {
        PageInfo<BizType> page = bizTypeService.bizTypeList(1, 10);

        System.out.println(JSONArray.toJSONString(page.getList()));
    }
}
