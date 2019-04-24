package com.jiepi.service.imp;

import com.jiepi.bean.Ad;
import com.jiepi.dao.AdDao;
import com.jiepi.dto.AdDto;
import com.jiepi.service.AdService;
import com.jiepi.util.FileUtiles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdServiceImp implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.url}")
    private String adImageUrl;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

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


    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath + fileName);
            File fileFolder = new File(adImageSavePath);
            if (fileFolder.exists()) {
                fileFolder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(file);
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            } catch (Exception error) {
                System.out.print(error.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }


    public AdDto find(String id) {
        AdDto adDto = adDao.searchById(id);
        return adDto;
    }

    public AdDto searchById(String id) {
        AdDto adDto = adDao.searchById(id);
        return adDto;
    }

    public int remove(String id) {
        AdDto adDto = adDao.searchById(id);
        System.out.println(adDto);
        FileUtiles.delete(adImageSavePath + adDto.getImgFile());
        int count = adDao.remove(id);
        return count;
    }
}
