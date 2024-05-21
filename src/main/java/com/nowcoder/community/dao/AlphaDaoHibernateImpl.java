package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")
// implements: 声明自己使用一个或者多个接口。用来实现接口中定义的抽象方法，即接口方法一般为空，必须重写才能使用。
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select()
    {
        return "Hibernate";
    }

}
