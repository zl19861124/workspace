package com.micro.webpage;

import com.micro.webpage.model.TestTab;
import com.micro.webpage.service.RedisUtil;
import com.micro.webpage.service.TestTabService;
import com.micro.webpage.utils.GetSpringBeanUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class WebPageApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(WebPageApplicationTests.class);
    @Autowired
    TestTabService testTabService;
    @Test
    void contextLoads1() {
        List<TestTab> user = this.testTabService.getAll();
        log.info(String.valueOf(user.get(0)));
    }

    @Test
    void contextLoads()
    {
        //不能通过new，否则为出现无法自动注意的情况
        RedisUtil jedisPool = GetSpringBeanUtil.getBean(RedisUtil.class);
        log.info("添加redis状态："+jedisPool.set("aa","123"));
		Object str =  jedisPool.get("aa");
		log.info("通过redis取值"+(String) str);

    }
}
