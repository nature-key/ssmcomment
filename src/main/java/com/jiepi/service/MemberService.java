package com.jiepi.service;

import com.jiepi.bean.Member;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface MemberService {

    Boolean select(Long phone);

    Boolean saveCode(String phone,String code);

    public String getCode(Long phone);
    public void saveToken(String token, Long phone);

    public Long getIdByPhone(Long phone);

    Long getPhone(String token);
}
