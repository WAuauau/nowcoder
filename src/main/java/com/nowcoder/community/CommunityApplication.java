package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// controller包：使用Spring MVC架构，对应MVC的controller
// service：手动创建的业务类，SpringIOC管理的一些java文件  DAO包：使用Mybatis框架。共同组成MVC中的model
// 视图层： JSP或其他视图技术，对应MVC中的view

@SpringBootApplication  //程序入口用的注解
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
