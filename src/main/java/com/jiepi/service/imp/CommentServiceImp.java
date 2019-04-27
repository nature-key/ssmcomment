package com.jiepi.service.imp;

import com.jiepi.bean.Comment;
import com.jiepi.bean.Orders;
import com.jiepi.constant.CommentStateConst;
import com.jiepi.dao.CommentDao;
import com.jiepi.dao.OrderDao;
import com.jiepi.dto.CommentForSubmitDto;
import com.jiepi.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CommentDao commentDao;
    @Transactional
    @Override
    public boolean add(CommentForSubmitDto commentForSubmitDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentForSubmitDto, comment);
        comment.setId(null);
        comment.setOrdersId(commentForSubmitDto.getId());
        comment.setCreateTime(new Date());
        // 保存评论
        commentDao.insert(comment);
//        int i= 3/0;
        Orders orders = new Orders();
        orders.setId(commentForSubmitDto.getId());
        orders.setCommentState(CommentStateConst.HAS_COMMENT);
        // 更新订单评论状态
        orderDao.update(orders);
        return true;

    }
}
