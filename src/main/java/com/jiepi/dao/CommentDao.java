package com.jiepi.dao;

import com.jiepi.bean.Comment;

import java.util.List;

public interface CommentDao {

    int insert(Comment comment);

    List<Comment> selectByPage(Comment comment);
}
