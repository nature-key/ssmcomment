package com.jiepi.task;

import com.jiepi.bean.SysParam;
import com.jiepi.dao.BusinessDao;
import com.jiepi.dao.SysParamDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component("BusinessTask")
public class BusinessTask {

    private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);

    @Autowired
    private BusinessDao businessDao;

    @Autowired
    private SysParamDao sysParamDao;

    public void syncBusiness() {
        logger.info("update-----------------------------------");
        SysParam sysParam = sysParamDao.select("last_key_number");
        Map<String, Date> map = new HashMap<>();
        map.put("startTime", sysParam!=null?sysParam.getParamValue():null);
        Date date = new Date();
        map.put("endTime", date);
        int i = businessDao.updateNumber(map);
        SysParam sysParamupdate = new SysParam();
        sysParamupdate.setParamKey("last_key_number");
        sysParamupdate.setParamValue(date);
        sysParamDao.upDateByKey(sysParamupdate);
        logger.info("同步数据{}", i);
    }
}
