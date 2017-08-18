package com.zg.number.mapper;

import com.zg.number.bean.ShouYi;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wrx on 2017/8/16.
 * 计算用户每天收益的收益总和 *
 */
@Component
@Mapper
public interface ShouYiLvMapper {
/**
 * 根据投资的日期进行分组,并计算出每天的投资总额
 */
public List<ShouYi> sumSY(Integer uid);

}
