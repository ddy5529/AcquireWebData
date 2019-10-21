package com.ddy.spide.acquire_web_data.service;

import com.ddy.spide.acquire_web_data.dao.StockMarkDao;
import com.ddy.spide.acquire_web_data.model.StockMark;
import com.ddy.spide.acquire_web_data.utils.DataUtils;
import com.ddy.spide.acquire_web_data.utils.FileUtils;
import com.ddy.spide.acquire_web_data.utils.LogUtils;
import com.ddy.spide.acquire_web_data.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SpideSINAFinanceServiceImpl implements SpideSINAFinanceService, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int hightCount = 0;//破高记录数
    private static Double aDouble = 0.000;

    private static int lowCount = 0;//破低记录数
    private static Double bDouble = 0.000;

    @Autowired
    private StockMarkDao stockMarkDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MonitoringRedisService monitoringRedisService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println(this.getClass().getName()+"|PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName()+"|afterPropertiesSet");
        init();
    }

    public void init() {
        String paramStockNum="sh600498";
        //TODO:这里要改成解析文件，然后分析所有行里面的内容，然后得出最高值和最低值
        String _hightprice= redisService.getKey(paramStockNum+"_hightprice");
        if (StringUtils.isNotEmpty(_hightprice)){
            //TODO：破高记录次数应该放到redis中，该对象的持续时间应该是18小时，对应着股票交易截止时间到次日股票交易时间
            aDouble = Double.parseDouble(_hightprice);
        }else{
            aDouble=getHightPrice(paramStockNum);
        }
        String _lowprice = redisService.getKey(paramStockNum+"_lowprice");
        if (StringUtils.isNotEmpty(_lowprice)){
            //TODO:坡低记录次数应该放到redis中
            bDouble = Double.parseDouble(_lowprice);
        }else{
            bDouble=getLowPrice(paramStockNum);
        }
    }

    @Override
    public void dayEnd(List<String> paramStockNumList) {
        //TODO：从redis中获取 产品编号+下划线+类型 作为主键 ，然后值作为
        ResponseEntity<String> responseEntity = getStockListDataByList(paramStockNumList);
        if (responseEntity==null){
            return;
        }
        String bodyStr = responseEntity.getBody();
        for (String paramStockNum : paramStockNumList) {
            Map<String, String> stockMap = getPrice(bodyStr);
            String hightprice= stockMap.get("hightPrice");
            String lowprice =stockMap.get("lowPrice");
            StockMark stockMark = new StockMark();
            stockMark.setStockName(stockMap.get("name"));
            stockMark.setBreakTheHightRecordCount(hightCount);
            stockMark.setBreakTheLowRecordCount(lowCount);
            stockMark.setCreateTime(new Date());
            stockMark.setUpdateTime(new Date());
            stockMark.setHightRecord(Double.parseDouble(hightprice));
            stockMark.setLowRecord(Double.parseDouble(lowprice));
            stockMark.setStockNum(paramStockNum);
            stockMarkDao.save(stockMark);
        }

    }

    private ResponseEntity<String> getStockListDataByList(List<String> paramStockNumList) {
        StringBuilder urlStr = new StringBuilder();
        urlStr.append("https://hq.sinajs.cn/?_=0.08652063062388238");
        if (paramStockNumList != null && paramStockNumList.size() > 0) {
            urlStr.append("&list=");
            for (int i = 0; i < paramStockNumList.size(); i++) {
                if (i == 0) {
                    urlStr.append(paramStockNumList.get(i));
                } else {
                    urlStr.append(",").append(paramStockNumList.get(i));
                }
            }
        }
        String urlAllStr = urlStr.toString();
        ResponseEntity<String> responseEntity =null;
        try {
            responseEntity=restTemplate().getForEntity(urlAllStr, String.class);
        }catch (Exception e){

        }
        return responseEntity;
    }

    /**
     * 输出清洗后的数据
     */
    private void printTotalInfo(String price) {
        logger.info("当前购买的股票：名称：{},当前价格：{},买入价格:{},当前收益{},买入数量：{},总计手续费：{}，买入手续费:{}，卖出手续费：{}，使用的证券商名称：{}");
    }

    @Override
    public void getStockByTheSequenceNum(List<String> paramStockNumList) {
        ResponseEntity<String> responseEntity = getStockListDataByList(paramStockNumList);
        String bodyStr = responseEntity.getBody();
        String webResultArr[] = bodyStr.split("\n");
        for (String webResultStr : webResultArr) {
            String varStr = webResultStr.substring(0, webResultStr.lastIndexOf("\""));
            int varType = getTypeByVarStr(varStr);
            switch (varType) {
                case 0:
                    operationStockPrice(bodyStr);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 处理股票
     */
    private void operationStockPrice(String bodyStr) {
        Map<String, String> resultMap = getPrice(bodyStr);
        String price = resultMap.get("price");
        String stockName = resultMap.get("name");
        logger.info("{}当前价格：{}", stockName, price);
        String paramStockNum="sh600498";
        if (Double.parseDouble(price) > aDouble) {
            aDouble = Double.parseDouble(price);
            hightCount++;
            String stockInfo=StringUtils.getSplitStr("{}突破新高：{}，破高次数：{}", stockName, price, hightCount + "");
            LogUtils.printLog(logger, stockInfo);
            redisService.setDefaultTimeKey(paramStockNum+"_HightPrice_Count",hightCount+"");
            pushWebSocketData(stockInfo);
        }
        if (Double.parseDouble(price) < bDouble) {
            bDouble = Double.parseDouble(price);
            lowCount++;
            String stockInfo=StringUtils.getSplitStr("{}突破新低：{}，破低次数：{}", stockName, price, lowCount + "");
            LogUtils.printLog(logger, stockInfo);
            redisService.setDefaultTimeKey(paramStockNum+"_LowPrice_Count",lowCount+"");
            pushWebSocketData(stockInfo);
        }
        try {
            FileUtils.appendWtiteRootFile(DataUtils.getSimpleNowDate(), "烽火通信.data", bodyStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**获取数据的类型*/
    private int getTypeByVarStr(String varStr) {
        int resultInt = 0;
        //此处做匹配
        return resultInt;
    }

    /**
     * 0，是名称
     * 1，当天开盘价
     * 2，昨天收盘价格
     * 3，当前价格
     * 4，当天最高价格
     * 5，当天最低价
     * 6，当前交易买入价格
     * 7，当前加以卖出价格
     */
    private Map<String, String> getPrice(String bodyStr) {
        String stockNum=bodyStr.substring(11,bodyStr.indexOf("="));
        String content = bodyStr.substring(bodyStr.indexOf("\""), bodyStr.lastIndexOf("\""));
        String arr[] = content.split(",");
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("stockNum",stockNum);
        resultMap.put("name", arr[0]);
        resultMap.put("lowPrice", arr[5]);
        resultMap.put("yesterayPrice", arr[2]);
        resultMap.put("price", arr[3]);
        resultMap.put("hightPrice", arr[4]);
        resultMap.put("openPrice", arr[1]);
        resultMap.put("thisShopPrice", arr[6]);
        resultMap.put("thisSellPrice", arr[7]);

        redisService.setDefaultTimeKey(stockNum+"_hightprice",arr[4]+"");
        redisService.setDefaultTimeKey(stockNum+"_lowprice",arr[5]+"");
        return resultMap;
    }

    @Override
    public String pushWebSocketData(String msg) {
        monitoringRedisService.pushWebServiceMessage(msg);
        return null;
    }

    @Override
    public void calculateStockFluctuationRate() {

    }

    @Override
    public void calculateStockCapital() {

    }

    private Double getLowPrice(String stockNum) {
        //sh600498
        String webUrl = "https://hq.sinajs.cn/?_=0.08652063062388238&list="+stockNum;
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(webUrl, String.class);
        String bodyStr = responseEntity.getBody();
        String price = getPrice(bodyStr).get("lowPrice");
        return Double.parseDouble(price);
    }

    private Double getHightPrice(String stockNum) {
        //sh600498
        String webUrl = "https://hq.sinajs.cn/?_=0.08652063062388238&list="+stockNum;
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(webUrl, String.class);
        String bodyStr = responseEntity.getBody();
        String price = getPrice(bodyStr).get("hightPrice");
        return Double.parseDouble(price);
    }

}
