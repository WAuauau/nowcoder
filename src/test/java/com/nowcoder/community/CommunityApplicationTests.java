package com.nowcoder.community;


import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class CommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;  //spring 容器。一个接口
	// ApplicationContext extends HierarchicalBeanFactory
	// HierarchicalBeanFactory extends BeanFactory
	// BeanFactory ：spring 容器的顶层接口
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;

	}
	/* ****************主动获取容器 不太方便**************** */
	// 容器管理 bean的创建
	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("alphaHibernate",AlphaDao.class); //  得到的类型默认为Object 所以需要强制转型  或者加参数 AlphaDao.class
		System.out.println(alphaDao.select());
	}

	// 容器管理 bean的初始化和销毁
	@Test
	public void testBeanManagement(){

		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

	}
	// 容器管理 第三方bean（类在jar包中），需要自己写配置类，在配置类中通过bean注解进行声明
	@Test
	public void testBeanConfig() {

		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));

	}
	/* *********************************************** */


	/* ****************如何更方便使用容器（依赖注入）**************** */
	@Autowired // 1.该注解也可加载类的构造器前，通过构造器注入 2.把注解加到set方法前
	@Qualifier("alphaHibernate")  //当前bean依赖的是接口，接口关联多个类时 ，通过bean的名字改变注入的对象
	private AlphaDao alphaDao;  //把AlphaDao注入给该属性，直接使用该属性

	@Autowired
	private AlphaService alphaService;  //把AlphaService注入给该属性，直接使用该属性

	@Autowired
	private SimpleDateFormat simpleDateFormat;  //把SimpleDateFormat注入给该属性，直接使用该属性


	//依赖注入
	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}

