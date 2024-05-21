package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //分页查询功能,userId为按用户查询，默认为0（所有用户）
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);
    // @Param("userId")用于为参数写一个别名，
    // 若方法中仅有一个参数，并且在<if>中使用，就必须加别名。
    int selectDiscussPostRows(@Param("userId") int userId);


}
