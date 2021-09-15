package com.haojing.dicing.dao;

import com.haojing.dicing.entity.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yeweiping on 2020/9/15.
 */
@Mapper
public interface AwardMapper {

    List<Award> findAll();
}
