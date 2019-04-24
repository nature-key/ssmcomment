package com.jiepi.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiepi.bean.BusinessList;
import com.jiepi.constant.ApiCodeEnum;
import com.jiepi.dto.AdDto;
import com.jiepi.dto.ApiCodeDto;
import com.jiepi.dto.BusinessDto;
import com.jiepi.dto.BusinessListDto;
import com.jiepi.service.BusinessService;
import com.jiepi.service.MemberService;
import com.jiepi.service.imp.AdServiceImp;
import com.jiepi.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdServiceImp adServiceImp;

    @Resource
    private BusinessService businessService;

    @Value("${ad.number}")
    private int adNumber;

    @Value("${businessHome.number}")
    private int businessHomeNumber;

    @Value("${businessSearch.number}")
    private int businessSearchNumber;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    public List<AdDto> homead() throws Exception {
        AdDto adDto = new AdDto();
        List<AdDto> adDtos = adServiceImp.searchByPage(adDto);
        return adDtos;
    }

    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto businessDto) throws Exception {

        businessDto.getPage().setPageNumber(businessHomeNumber);
        BusinessListDto businessListDto = businessService.searchByPageForApi(businessDto);

        return businessListDto;

//        ObjectMapper mapper = new ObjectMapper();
//        String str = "{\"hasMore\":true,\"data\":[{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\"title\":\"汉堡大大\",\"subTitle\":\"叫我汉堡大大，还你多彩口味\",\"price\":\"28\",\"distance\":\"120m\",\"mumber\":\"389\",\"id\":\"6399747255039152\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\"title\":\"北京开源饭店\",\"subTitle\":\"[望京]自助晚餐\",\"price\":\"98\",\"distance\":\"140m\",\"mumber\":\"689\",\"id\":\"02119371110780932\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\"title\":\"服装定制\",\"subTitle\":\"原价xx元，现价xx元，可修改一次\",\"price\":\"1980\",\"distance\":\"160\",\"mumber\":\"106\",\"id\":\"27443506148353647\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\"title\":\"婚纱摄影\",\"subTitle\":\"免费试穿，拍照留念\",\"price\":\"2899\",\"distance\":\"160\",\"mumber\":\"58\",\"id\":\"17718554204751857\"},{\"img\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\"title\":\"麻辣串串烧\",\"subTitle\":\"双人免费套餐等你抢购\",\"price\":\"0\",\"distance\":\"160\",\"mumber\":\"1426\",\"id\":\"3202751987941732\"}]}";
//        return mapper.readValue(str, new TypeReference<BusinessList>() {
//        });
    }

    @RequestMapping(value = "submitComment", method = RequestMethod.POST)
    public Map<String, Object> submitComment() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errno", 0);
        map.put("msg", "ok");
        return map;
    }

    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam Long phone) {
        ApiCodeDto apiCodeDto;
        if (memberService.select(phone)) {
            String code = String.valueOf(CommonUtil.random(6));
            if (memberService.saveCode(phone + "", code)) {
                System.out.print(phone + "===" + code);
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
            } else {
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }

        } else {
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return apiCodeDto;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long username, @RequestParam("code") String code) {
        ApiCodeDto apiCodeDto;
        String memberServiceCode = memberService.getCode(username);
        if (memberServiceCode != null) {
            if (memberServiceCode.equals(code)) {
                String uuid = CommonUtil.getUUID();
                memberService.saveToken(uuid, username);
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                apiCodeDto.setToken(uuid);
            } else {
                apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        } else {
            apiCodeDto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }

        return apiCodeDto;

    }
}
