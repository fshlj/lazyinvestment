package com.zg.number.mapper;

import com.zg.number.bean.Record;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by WangHongChuan on 2017/8/14.
 */
@Component
@Mapper
public interface CapitalMapper {
    //根据id查询当前用户投资记录
    List<Record> findCapital(int uid);
}
