package com.jiepi.service.imp;

import com.jiepi.bean.Business;
import com.jiepi.bean.Page;
import com.jiepi.constant.CategoryConst;
import com.jiepi.dao.BusinessDao;
import com.jiepi.dto.BusinessDto;
import com.jiepi.dto.BusinessListDto;
import com.jiepi.service.BusinessService;
import com.jiepi.util.CommonUtil;
import com.jiepi.util.FileUtiles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessServiceImp implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Value("${businessImage.savePath}")
    private String savePath;

    @Value("${businessImage.url}")
    private String url;

    public boolean insert(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);
        if (businessDto.getImgFile() != null && businessDto.getImgFile().getSize() > 0) {
            try {
                String fileName = FileUtiles.save(businessDto.getImgFile(), savePath);
                business.setImgFileName(fileName);
                business.setNumber(0);
                business.setCommentTotalNum(0l);
                business.setStarTotalNum(0l);
                businessDao.insert(business);
                return true;

            } catch (IllegalStateException | IOException e) {

                return false;
            }

        }

        return false;
    }


    @Override
    public BusinessDto searchById(Long id) {
        BusinessDto businessDto = new BusinessDto();
        Business business = businessDao.selectById(id);
        BeanUtils.copyProperties(business, businessDto);
        businessDto.setImg(url + business.getImgFileName());
        businessDto.setStar(this.getStar(business));
        return businessDto;
    }

    private int getStar(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int) (business.getStarTotalNum() / business.getCommentTotalNum());
        }
        return 0;

    }

    @Override
    public List<BusinessDto> searchByPage(BusinessDto businessDto) {
        List<BusinessDto> result = new ArrayList<>();
        Business business = new Business();
        BeanUtils.copyProperties(businessDto,business);
        List<Business> businesses = businessDao.selectByPage(business);
        for (Business business1 : businesses) {
            BusinessDto businessDto1 = new BusinessDto();
            BeanUtils.copyProperties(business1,businessDto1);
            result.add(businessDto1);
            businessDto1.setImg(url+business1.getImgFileName());
            businessDto1.setStar(this.getStar(business1));
        }
        return result;
    }

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();
        Business businessForSelect  = new Business();
        BeanUtils.copyProperties(businessDto,businessForSelect);
        if(CommonUtil.isEmpty(businessDto.getKeyword())){
            businessForSelect.setTitle(businessDto.getKeyword());
            businessForSelect.setSubtitle(businessDto.getKeyword());
            businessForSelect.setDesc(businessDto.getKeyword());
        }
        if(businessDto.getCategory()!=null&& CategoryConst.ALL.equals(businessDto.getCategory())){
            businessForSelect.setCategory(null);
        }

        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage+1);
        List<Business> businesses = businessDao.selectLikeByPage(businessForSelect);

        // 经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());
        for (Business business : businesses) {
            BusinessDto businessDto1 = new BusinessDto();
            result.getData().add(businessDto1);
            BeanUtils.copyProperties(business,businessDto1);
            businessDto1.setImg(url+business.getImgFileName());
            businessDto1.setMumber(business.getNumber());
            businessDto1.setStar(this.getStar(business));
            businessDto1.setPage(businessForSelect.getPage());

        }


        return result;
    }
}
