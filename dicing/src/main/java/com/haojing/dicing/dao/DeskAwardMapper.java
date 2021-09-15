package com.haojing.dicing.dao;

import com.haojing.dicing.entity.DeskAward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Mapper
public interface DeskAwardMapper {

    List<DeskAward> findByDeskNo(int deskNo);

    int updateRemaining(@Param("deskAwardId") int deskAwardId, @Param("remaining") int remaining);

    int removeByDeskNo(int deskNo);

    int init(int deskNo);
}
