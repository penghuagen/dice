package com.haojing.dicing.dao;

import com.haojing.dicing.entity.DicingLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Mapper
public interface DicingLogMapper {

    List<DicingLog> findAwardByUserId(long userId);

    List<DicingLog> findZYByDeskNo(int deskNo);

    void add(DicingLog dicingLog);

    int invalidZYByUserIdAndDeskNo(@Param("deskNo") int deskNo, @Param("userId") long userId);

    List<DicingLog> findValidByDeskNo(int deskNo);

    List<DicingLog> getDicingLogs(@Param("deskNo") int deskNo, @Param("startDicingLogId") Integer startDicingLogId
        , @Param("pageSize") int pageSize);

    int removeByDeskNo(int deskNo);
}
