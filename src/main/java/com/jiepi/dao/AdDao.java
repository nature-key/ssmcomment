package com.jiepi.dao;

import com.jiepi.bean.Ad;
import com.jiepi.dto.AdDto;

import java.util.List;

public interface AdDao {

    List<AdDto>  searchByPage(Ad ad);

    int  insert(Ad ad);

    int remove(String id);

    AdDto searchById(String id);
}
