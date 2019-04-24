package com.jiepi.service.imp;

import com.jiepi.bean.Member;
import com.jiepi.cache.CodeCache;
import com.jiepi.cache.TokenCache;
import com.jiepi.dao.MemberDao;
import com.jiepi.service.MemberService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Boolean select(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> members = memberDao.select(member);
        return members != null && members.size() > 0;
    }


    @Override
    public Boolean saveCode(String phone, String code) {
        CodeCache instance = CodeCache.getInstance();
        return instance.save(Long.parseLong(phone), code);
    }

    //    @Override
//    public Boolean select(Member member) {
//        return null;
//    }
    @Override
    public String getCode(Long phone) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    @Override
    public void saveToken(String token, Long phone) {
        TokenCache instance1 = TokenCache.getInstance();
        instance1.save(token, phone);
    }
}
