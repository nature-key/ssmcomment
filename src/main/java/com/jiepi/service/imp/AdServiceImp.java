package com.jiepi.service.imp;

import com.jiepi.bean.Ad;
import com.jiepi.dao.AdDao;
import com.jiepi.dto.AdDto;
import com.jiepi.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImp implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.url}")
    private String adImageUrl;

    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto, condition);
        List<AdDto> adDtos = adDao.searchByPage(condition);
        for (Ad ad : adDtos) {
            AdDto adDtoTemp = new AdDto();
            BeanUtils.copyProperties(ad, adDtoTemp);
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            result.add(adDtoTemp);
        }
        return result;
    }



}
