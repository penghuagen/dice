package com.haojing.dicing.dao;

import com.haojing.dicing.entity.Desk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Mapper
public interface DeskMapper {

    Desk findByDeskNo(int deskNo);

    List<Desk> findAll();

    int update(Desk desk);

    int resetDesk(@Param("deskNo") int deskNo, @Param("startTime")Date startTime);

//    int startAllDesks();
}
