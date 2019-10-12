package com.ddy.spide.acquire_web_data.service;

import com.ddy.spide.acquire_web_data.dao.StockMarkDao;
import com.ddy.spide.acquire_web_data.model.StockMark;
import com.ddy.spide.acquire_web_data.utils.DataUtils;
import com.ddy.spide.acquire_web_data.utils.FileUtils;
import com.ddy.spide.acquire_web_data.utils.LogUtils;
import com.ddy.spide.acquire_web_data.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SpideSINAFinanceServiceImpl implements SpideSINAFinanceService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int hightCount = 0;//破高记录数
    private static Double aDouble = 0.000;

    private static int lowCount = 0;//破低记录数
    private static Double bDouble = 0.000;

    @Autowired
    private StockMarkDao stockMarkDao;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostConstruct
    public void init() {
        //TODO:这里要改成解析文件，然后分析所有行里面的内容，然后得出最高值和最低值
        Double temp = getFirstData();
        aDouble = temp;  //TODO：破高记录次数应该放到redis中，该对象的持续时间应该是18小时，对应着股票交易截止时间到次日股票交易时间
        bDouble = temp;   //TODO:坡低记录次数应该放到redis中
    }

    private Double getFirstData() {
        String webUrl = "https://hq.sinajs.cn/?_=0.08652063062388238&list=sh600498";
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(webUrl, String.class);
//        LogUtils.printLog(logger,responseEntity.getBody());
        String bodyStr = responseEntity.getBody();
        String price = getPrice(bodyStr).get("price");
        return Double.parseDouble(price);
    }

    @Override
    public String pushWebSocketData() {
        return null;
    }

    @Override
    public void dayEnd(List<String> paramStockNumList) {
        //TODO：从redis中获取 产品编号+下划线+类型 作为主键 ，然后值作为
        ResponseEntity<String> responseEntity = getStockListDataByList(paramStockNumList);
        String bodyStr = responseEntity.getBody();
        Map<String, String> stockMap = getPrice(bodyStr);
        StockMark stockMark = new StockMark();
        stockMark.setStockName(stockMap.get("name"));
        stockMark.setBreakTheHightRecordCount(hightCount);
        stockMark.setBreakTheLowRecordCount(lowCount);
        stockMark.setCreateTime(new Date());
        stockMark.setUpdateTime(new Date());
        stockMark.setHightRecord(Double.parseDouble(stockMap.get("hightprice")));
        stockMark.setLowRecord(Double.parseDouble(stockMap.get("lowprice")));
        stockMark.setStockNum(600498 + "");
        stockMarkDao.save(stockMark);

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
        ResponseEntity<String> responseEntity = restTemplate().getForEntity(urlAllStr, String.class);
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
        if (Double.parseDouble(price) > aDouble) {
            aDouble = Double.parseDouble(price);
            hightCount++;
            LogUtils.printLog(logger, StringUtils.getSplitStr("{}突破新高：{}，破高次数：{}", stockName, price, hightCount + ""));
        }
        if (Double.parseDouble(price) < bDouble) {
            bDouble = Double.parseDouble(price);
            lowCount++;
            LogUtils.printLog(logger, StringUtils.getSplitStr("{}突破新低：{}，破低次数：{}", stockName, price, lowCount + ""));
        }
        //printTotalInfo(price);
        try {
            FileUtils.appendWtiteRootFile(DataUtils.getSimpleNowDate(), "烽火通信.data", bodyStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getTypeByVarStr(String varStr) {
        int resultInt = 0;
        //此处做匹配
        return resultInt;
    }

    /**
     * 0，是名称
     * 1，是今天最低
     * 2，上次收盘价格
     * 3，当前价格
     * 4，当天最高价格
     * 5，开盘价
     * 6，当前交易买入价格
     * 7，当前加以卖出价格
     */
    private Map<String, String> getPrice(String bodyStr) {
        String content = bodyStr.substring(bodyStr.indexOf("\""), bodyStr.lastIndexOf("\""));
        String arr[] = content.split(",");
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("name", arr[0]);
        resultMap.put("lowprice", arr[1]);
        resultMap.put("yesterdayPrice", arr[2]);
        resultMap.put("price", arr[3]);
        resultMap.put("hightprice", arr[4]);
        resultMap.put("openprice", arr[5]);
        resultMap.put("thisshopprice", arr[6]);
        resultMap.put("thissellprice", arr[7]);
        return resultMap;
    }

    @Override
    public void calculateStockFluctuationRate() {

    }

    @Override
    public void calculateStockCapital() {

    }

}
