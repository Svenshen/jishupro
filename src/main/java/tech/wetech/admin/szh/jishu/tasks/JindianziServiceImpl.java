/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
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
 * @date 2020-1-19 14:11:43
 */
@Service
public class JindianziServiceImpl implements AsyncService<QiandaoZhanghu>{

        
    @Autowired
    QiandaoZhanghuService qiandaoZhanghuService;
    @Autowired
    QiandaoLeibieService qiandaoLeibieService;
    @Autowired
    QiandaodianzilogService qiandaodianzilogService;
    
    String loginurl = "https://ycpt.cpoc.cn/qiantai/login_adminLogin";
    
    String qiandaourl = "https://ycpt.cpoc.cn/qd_qd.action?a=1";
    
     private void sleep(int time){
        Random r = new Random();
        try {
            Thread.sleep(Long.valueOf(r.nextInt(time)));
        } catch (InterruptedException ex) {
            Logger.getLogger(JindianziScheduleTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void executeAsync() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(QiandaoZhanghu t) {
        renwu(t);
    }

     private void renwu(QiandaoZhanghu q){
        
            try {
                HttpClient httpClient = new SSLClient();
                HttpPost httppost = new HttpPost(loginurl);
                List<NameValuePair> list = new LinkedList<>();
                System.out.println(q.getUsername());
                //System.out.println(B65_md5.jiami(q.getPassword()));
                BasicNameValuePair param1 = new BasicNameValuePair("uname", q.getUsername());
                BasicNameValuePair param2 = new BasicNameValuePair("passwd",B65_md5.jiami(q.getPassword()));
                BasicNameValuePair param3 = new BasicNameValuePair("mmqd", "1110");
                list.add(param1);
                list.add(param2);
                list.add(param3);
                UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
                httppost.setEntity(entityParam);
                
                
                if("success".equals(HttpClientUtil.doPost(httpClient, httppost,  "utf8"))){
                    HttpGet huoquitemGet = new HttpGet(qiandaourl);
                    //huoquitemGet.addHeader("cookie", "__guid=200960680.4174066801499797500.1568775922789.7405; JSESSIONID=2A57AF5E7FA2F00C69DC9833990388A7; tempUserName=320525198901135339; tempUserPwd=63559627qq");
                    String vas = HttpClientUtil.doGet(httpClient, huoquitemGet, "utf8");
                    if(vas.contains("加油！您已经签到")){
                        System.out.println(vas.split("<p class=\"eveyday-date\">")[1].split("</p>")[0]);
                    }
                    sleep(1000*60*3);
                    //https://ycpt.cpoc.cn/dianziQT/dianziQT_searchDianzi?pagerMethod=next&currentPage1=2&keyword=&startdate=2020-01-12&enddate=2020-01-19&dianziType=new&orgid=&orderString=&recentFlag=&bk=1&cxlb=&tjbq=&sfyd=0&stuOrgid=
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    String data = formatter.format(date);
                    String listdianziurl = "https://ycpt.cpoc.cn/dianziQT/dianziQT_searchDianzi?pagerMethod=next&currentPage1="+"0"+"&keyword=&startdate="+data+"&enddate="+data+"&dianziType=new&orgid=&orderString=&recentFlag=&bk=1&cxlb=&tjbq=&sfyd=0&stuOrgid=";
                    huoquitemGet = new HttpGet(listdianziurl);
                    vas = HttpClientUtil.doGet(httpClient, huoquitemGet, "utf8");
                    int pages = Integer.valueOf(vas.split("value=\"共&nbsp;")[1].split("&nbsp;页")[0]);
                    for(int i=0;i<pages;i++){
                        sleep(1000*60*3);
                        listdianziurl = "https://ycpt.cpoc.cn/dianziQT/dianziQT_searchDianzi?pagerMethod=next&currentPage1="+i+"&keyword=&startdate="+data+"&enddate="+data+"&dianziType=new&orgid=&orderString=&recentFlag=&bk=1&cxlb=&tjbq=&sfyd=0&stuOrgid=";
                        huoquitemGet = new HttpGet(listdianziurl);
                        vas = HttpClientUtil.doGet(httpClient, huoquitemGet, "utf8");
                        //System.out.println(vas);
                        String[] dianzis = vas.split("onclick=\"viewDianzi[(]'");
                        for(int j = 1;j<dianzis.length;j++){
                            sleep(1000*60*3);
                            String dianziid = dianzis[j].split("'")[0];
                            Qiandaodianzilog qq = new Qiandaodianzilog();
                            qq.setDianziid(dianziid);
                            qq.setUser(q.getUser());
                            if(!qiandaodianzilogService.existsByOne(qq)){
                                String dianzanurl = "https://ycpt.cpoc.cn/dianziQT/dianziQT_dianZan?dianziID="+dianziid;
                                huoquitemGet = new HttpGet(dianzanurl);
                                //System.out.println(HttpClientUtil.doGet(httpClient, huoquitemGet, "utf8"));
                                String pinglunurl = "https://ycpt.cpoc.cn/dianziQT/dianziQT_pingLun?dianziID="+dianziid+"&plid=&plnr=%E7%82%B9%E5%AD%90%E4%B8%8D%E9%94%99%EF%BC%8C%E5%80%BC%E5%BE%97%E5%AD%A6%E4%B9%A0%EF%BC%81%EF%BC%81";
                                httppost = new HttpPost(pinglunurl);
                                //System.out.println(HttpClientUtil.doPost(httpClient, httppost,  "utf8"));
                                Qiandaodianzilog n = new Qiandaodianzilog();
                                n.setDianziid(dianziid);
                                n.setUser(q.getUser());
                                qiandaodianzilogService.saveAndupdate(n);
                            }
                        }
                    }
                }
                
            } catch (Exception ex) {
                Logger.getLogger(JindianziScheduleTask.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
