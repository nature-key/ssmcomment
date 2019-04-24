package com.jiepi.service;

import com.jiepi.bean.Business;
import com.jiepi.dto.BusinessDto;
import com.jiepi.dto.BusinessListDto;

import java.util.List;

public interface BusinessService {

    boolean insert(BusinessDto businessDto);

    BusinessDto searchById(Long id);

    List<BusinessDto>  searchByPage(BusinessDto businessDto);


    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
