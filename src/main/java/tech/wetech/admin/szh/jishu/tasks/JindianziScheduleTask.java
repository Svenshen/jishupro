/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.tasks;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;
import tech.wetech.admin.szh.jishu.domain.Qiandaodianzilog;
import tech.wetech.admin.szh.jishu.service.QiandaoLeibieService;
import tech.wetech.admin.szh.jishu.service.QiandaoZhanghuService;
import tech.wetech.admin.szh.jishu.service.QiandaodianzilogService;
import tech.wetech.admin.szh.jishu.utils.B65_md5;
import tech.wetech.admin.szh.jishu.utils.HttpClientUtil;
import tech.wetech.admin.szh.jishu.utils.SSLClient;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2020-1-17 14:17:59
 */
@Component
public class JindianziScheduleTask {
    @Autowired
    QiandaoZhanghuService qiandaoZhanghuService;
    @Autowired
    QiandaoLeibieService qiandaoLeibieService;
    @Autowired
    QiandaodianzilogService qiandaodianzilogService;
    @Autowired
    JindianziServiceImpl jindianziServiceImpl;
     
    
    
    
    private void sleep(int time){
        Random r = new Random();
        try {
            Thread.sleep(Long.valueOf(r.nextInt(time)));
        } catch (InterruptedException ex) {
            Logger.getLogger(JindianziScheduleTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    //3.添加定时任务
    //@Scheduled(cron = "0 51 * * * ?")
    @Scheduled(cron = "0 0 8,16 * * ? ")
    private void configureTasks() {
        sleep(1000*60*15);
        for(QiandaoZhanghu q :qiandaoZhanghuService.queryOne(qiandaoLeibieService.queryById(1L))){
            jindianziServiceImpl.executeAsync(q);
        }
    }

   
}
