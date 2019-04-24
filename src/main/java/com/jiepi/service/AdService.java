package com.jiepi.service;

import com.jiepi.dto.AdDto;

import java.util.List;

public interface AdService {


    List<AdDto>  searchByPage(AdDto adDto);

     boolean add(AdDto adDto);

     int remove(String id);

     AdDto find(String id);

}
