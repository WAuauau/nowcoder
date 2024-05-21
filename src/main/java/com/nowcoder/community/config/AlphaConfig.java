package com.nowcoder.community.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;

@Configuration  //配置类
public class AlphaConfig {
    @Bean   //定义第三方bean 方法名就是bean的名
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat(("yyyy-MM-dd HH:mm:ss"));
    }
}
