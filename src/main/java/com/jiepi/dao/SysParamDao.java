package com.jiepi.dao;

import com.jiepi.bean.SysParam;

public interface SysParamDao {

    SysParam select(String key);

    void upDateByKey(SysParam sysParam);
}
