package com.jiepi.dao;

import com.jiepi.bean.Business;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BusinessDao {

    int insert (Business business);

    Business selectById(Long id);

    List<Business> selectByPage(Business business);

    List<Business> selectLikeByPage(Business business);

    int updateNumber(Map<String,Date> map);
}
