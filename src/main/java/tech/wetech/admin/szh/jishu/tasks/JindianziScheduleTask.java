/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.tasks;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;
import tech.wetech.admin.szh.jishu.service.QiandaoLeibieService;
import tech.wetech.admin.szh.jishu.service.QiandaoZhanghuService;
import tech.wetech.admin.szh.jishu.utils.B65_md5;
import tech.wetech.admin.szh.jishu.utils.HttpClientUtil;
import tech.wetech.admin.szh.jishu.utils.SSLClient;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2020-1-17 14:17:59
 */
@Component      //1.主要用于标记配置类，兼备Component的效果。
public class JindianziScheduleTask {
    @Autowired
    QiandaoZhanghuService qiandaoZhanghuService;
    @Autowired
    QiandaoLeibieService qiandaoLeibieService;
   
    
    String loginurl = "https://ycpt.cpoc.cn/qiantai/login_adminLogin";
    
    String qiandaourl = "https://ycpt.cpoc.cn/qd_qd.action?a=1";
    
//3.添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(cron = "0 0 8,16 * * ? ")
    private void configureTasks() {
        for(QiandaoZhanghu q :qiandaoZhanghuService.queryOne(qiandaoLeibieService.queryById(1L))){
            try {
                HttpClient httpClient = new SSLClient();
                HttpPost httppost = new HttpPost(loginurl);
                List<NameValuePair> list = new LinkedList<>();
                System.out.println(B65_md5.jiami(q.getPassword()));
                BasicNameValuePair param1 = new BasicNameValuePair("uname", q.getUsername());
                BasicNameValuePair param2 = new BasicNameValuePair("passwd",B65_md5.jiami(q.getPassword()));
                BasicNameValuePair param3 = new BasicNameValuePair("mmqd", "1110");
                list.add(param1);
                list.add(param2);
                list.add(param3);
                UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
                httppost.setEntity(entityParam);
                System.out.println();
                if("success".equals(HttpClientUtil.doPost(httpClient, httppost,  "utf8"))){
                    HttpGet huoquitemGet = new HttpGet(qiandaourl);
                    //huoquitemGet.addHeader("cookie", "__guid=200960680.4174066801499797500.1568775922789.7405; JSESSIONID=2A57AF5E7FA2F00C69DC9833990388A7; tempUserName=320525198901135339; tempUserPwd=63559627qq");
                    String vas = HttpClientUtil.doGet(httpClient, huoquitemGet, "utf8");
                    if(vas.contains("加油！您已经签到")){
                        System.out.println(vas.split("<p class=\"eveyday-date\">")[1].split("</p>")[0]);
                    }
                    
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(JindianziScheduleTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
