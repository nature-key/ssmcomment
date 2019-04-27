package com.jiepi.service;

import com.jiepi.dto.CommentForSubmitDto;

public interface CommentService {

    /**
     * 保存评论
     * @param commentForSubmitDto 提交的评论DTO对象
     * @return 是否保存成功：true-成功，false-失败
     */
    boolean add(CommentForSubmitDto commentForSubmitDto);
}
