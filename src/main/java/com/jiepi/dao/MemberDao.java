package com.jiepi.dao;

import com.jiepi.bean.Member;

import java.util.List;

public interface MemberDao {

    List<Member>  select (Member member);
}
