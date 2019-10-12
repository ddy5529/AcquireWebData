package com.ddy.spide.acquire_web_data;

import com.ddy.spide.acquire_web_data.interfaces.LogRecord;
import com.ddy.spide.acquire_web_data.service.RedisService;
import com.ddy.spide.acquire_web_data.service.SpideSINAFinanceService;
import com.ddy.spide.acquire_web_data.utils.LogUtils;
import com.ddy.spide.acquire_web_data.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcquireWebDataApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(AcquireWebDataApplicationTests.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private SpideSINAFinanceService spideSINAFinanceService;

    @Test
    public void contextLoads() {

    }

    @LogRecord(descript = "测试----------------------")
    @Test
    public void getSinaData(){
        List<String> list=new ArrayList<>();
        list.add("sh600498"); //烽火
        list.add("sz002155"); //赤峰黄金
        String result;
        String baseUrl="https://hq.sinajs.cn/etag.php";
        String subUrl="?_=1569550023346&list="+ StringUtils.getStrByList(list,",");
        String sendUrl=baseUrl+subUrl;
        ResponseEntity<String> forEntity = restTemplate().getForEntity(sendUrl, String.class);
        result = forEntity.getBody();
        LogUtils.printLog(logger,result);

    }

    @Test
    public void main(){
        String webUrl="https://hq.sinajs.cn/rn=1570674094594&list=sh600498,sh600498_i,bk_new_dzxx";
        ResponseEntity<String> responseEntity= restTemplate().getForEntity(webUrl,String.class);
//        LogUtils.printLog(logger,responseEntity.getBody());
        String bodyStr=responseEntity.getBody();
        String webResultArr[]= bodyStr.split("\n");
        System.out.println(webResultArr);
    }

    @Test
    public void test1(){
        List<String> list=new ArrayList<>();
        list.add("sh600498");
        spideSINAFinanceService.getStockByTheSequenceNum(list);
    }

    @Autowired
    private RedisService redisService;

    @Test
    public void redisTest() throws Exception {
        redisService.setKey("hello","hello1");
        System.out.println(redisService.getKey("hello"));
        redisService.setKey("hello","hello22");
        System.out.println(redisService.getKey("hello"));
        redisService.setKey("hello","hello333");
        System.out.println(redisService.getKey("hello"));
        redisService.setKey("hello","hello4444");
        System.out.println(redisService.getKey("hello"));
        redisService.deleteKey("hello");
        System.out.println("_"+redisService.getKey("hello")+"null");
    }

}
