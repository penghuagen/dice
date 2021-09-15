package com.haojing.dicing.dao;

import com.haojing.dicing.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Mapper
public interface UserMapper {

    List<User> findByDeskNo(int deskNo);

    User find(long userId);

    List<User> findAll();

    int updateAvator(@Param("userId") long userId, @Param("avator") String avator);

    int countByDeskNo(int deskNo);

    User findByDeskNoAndSeqNo(@Param("deskNo") int deskNo, @Param("seqNo") int seqNo);

    int updateReady(@Param("userId") long userId, @Param("ready") boolean ready);
}
