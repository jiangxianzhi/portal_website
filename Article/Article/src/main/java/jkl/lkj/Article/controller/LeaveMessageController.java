package jkl.lkj.Article.controller;

import jkl.lkj.Article.entity.LeaveMessage;
import jkl.lkj.Article.service.FakeJWTVerify;
import jkl.lkj.Article.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "/leaveMessage")
public class LeaveMessageController {
    //    验证密码， 通过token
    private final FakeJWTVerify fakeJWTVerify;
    private final LeaveMessageService leaveMessageService;
    @Autowired
    public LeaveMessageController(LeaveMessageService leaveMessageService,FakeJWTVerify fakeJWTVerify){
        this.leaveMessageService = leaveMessageService;
        this.fakeJWTVerify = fakeJWTVerify;
    }

    @GetMapping
    List<LeaveMessage> getAllLeaveMessage(  @RequestHeader("Authorization") String authorizationHeader){
        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }
       if(tmp){
           return  leaveMessageService.getAllLeaveMessage();
       }else{
           return null;
       }
    }
    @GetMapping("/{id}")
    int deleteById(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorizationHeader){
        boolean tmp = false;

        try {
            tmp = fakeJWTVerify.verifyValue(authorizationHeader);
        } catch (Exception e) {
            System.out.println(e);
        }
      if(tmp){
          return  leaveMessageService.deleteById(id);
      }else {
          return  0;
      }
    }
    @PostMapping
    int createLeaveMessage(@RequestBody LeaveMessage leaveMessage){
        System.out.println(leaveMessage);
        return leaveMessageService.create(leaveMessage.getName(),leaveMessage.getEmail(),leaveMessage.getPhone(),leaveMessage.getMessage(),leaveMessage.getIp());

    }

}
