package jkl.lkj.Article.service;


import jkl.lkj.Article.entity.LeaveMessage;

import java.util.List;


public interface LeaveMessageService {


    int create(String name, String email, String phone, String message,String ip);

    /**
     * 返回所有
     */
    List<LeaveMessage> getAllLeaveMessage();

    /**
     * 根据Id删除
     *
     * @param Id
     */
    int deleteById(Long Id);


}
