//package tech.wetech.admin.szh.jishu.utils;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.client.ClientHttpRequestFactory;
//import org.springframework.http.client.SimpleClientHttpRequestFactory;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.web.client.RestTemplate;
//
///**
// * RestTemplate配置类
// */
//@Configuration
//public class RestTemplateHttpsConfig {
//
//    
//    
//    @Bean(name = "restTemplatehttps")
//    public RestTemplate restTemplatehttps(HttpsClientRequestFactory factory) {
//        RestTemplate r = new RestTemplate(factory);
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        messageConverters.add(stringHttpMessageConverter);
//        r.setMessageConverters(messageConverters);
//        return r;
//    }
//
//    
//    
//    @Bean
//    public HttpsClientRequestFactory simpleHttpsClientHttpRequestFactory() {
//        HttpsClientRequestFactory factory = new HttpsClientRequestFactory();
//        factory.setReadTimeout(5000);//ms
//        factory.setConnectTimeout(15000);//ms
//        
//        return factory;
//    }
//}