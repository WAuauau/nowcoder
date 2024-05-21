package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service  // 想让bean通过容器管理 就要在类前加 Service/repository/controller/Component
//@Scope("prototype") 	// 被spring容器管理的bean 默认只实例化一个(singleton)。  prototype 每次请求直接实例化新的bean
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct //让容器在构造器之后自动调用该方法
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy //让容器在销毁前自动调用该方法
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
