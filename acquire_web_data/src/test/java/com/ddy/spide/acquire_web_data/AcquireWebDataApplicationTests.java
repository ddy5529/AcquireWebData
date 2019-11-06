package com.ddy.spide.acquire_web_data;

import com.ddy.spide.acquire_web_data.interfaces.LogRecord;
import com.ddy.spide.acquire_web_data.service.Redis2Service;
import com.ddy.spide.acquire_web_data.service.RedisService;
import com.ddy.spide.acquire_web_data.service.SpideSINAFinanceService;
import com.ddy.spide.acquire_web_data.utils.LogUtils;
import com.ddy.spide.acquire_web_data.utils.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class AcquireWebDataApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(AcquireWebDataApplicationTests.class);

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired(required = false)
    WebTestClient client;

    @Test
    public void getHello() {
        client.get().uri("/hello").exchange().expectStatus().isOk();
    }

    @Autowired
    private RedisService redisService;

    @Autowired
    private SpideSINAFinanceService spideSINAFinanceService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redis2Service redis2Service;

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


    @Test
    public void redisTest1(){
//        stringRedisTemplate.execute();
        stringRedisTemplate.getExpire("", TimeUnit.SECONDS);
        Map map=redisService.getAll();
        System.out.println("OK");
    }

    @Test
    public void redisTest() throws Exception {
        redisService.getAll();
    }

    @Test
    public void testDb(){
        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
        System.out.println("当前所在的db："+jedisConnectionFactory.getDatabase());
        jedisConnectionFactory.setDatabase(2);
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        jedisConnectionFactory.resetConnection();
        System.out.println("当前所在的db："+jedisConnectionFactory.getDatabase());
    }

    @Test
    public void test122(){
        redisService.getAll();
        redis2Service.getAll();
    }


}
