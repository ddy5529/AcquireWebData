package com.ddy.spide.acquire_web_data.service;

import com.ddy.spide.acquire_web_data.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SpideSINAFinanceServiceImpl implements SpideSINAFinanceService {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Override
    public void getFHTXData() {
        String webUrl="https://hq.sinajs.cn/?_=0.08652063062388238&list=sh600498";
        ResponseEntity<String> responseEntity= restTemplate().getForEntity(webUrl,String.class);
//        LogUtils.printLog(logger,responseEntity.getBody());
        String bodyStr=responseEntity.getBody();
        String price=getPrice(bodyStr);
        logger.info("烽火当前价格：{}",price);

    }

    private String getPrice(String bodyStr) {
        String content= bodyStr.substring(bodyStr.indexOf("\""),bodyStr.lastIndexOf("\""));
        String arr[]=content.split(",");
        return arr!=null?arr[3]:"";
    }


}
