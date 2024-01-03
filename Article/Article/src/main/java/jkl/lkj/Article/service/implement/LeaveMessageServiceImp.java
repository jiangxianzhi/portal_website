package jkl.lkj.Article.service.implement;

import jkl.lkj.Article.entity.LeaveMessage;
import jkl.lkj.Article.repository.LeaveMessageRepository;
import jkl.lkj.Article.service.LeaveMessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LeaveMessageServiceImp implements LeaveMessageService {
    private final LeaveMessageRepository leaveMessageRepository;
    public  LeaveMessageServiceImp(LeaveMessageRepository leaveMessageRepository){
        this.leaveMessageRepository = leaveMessageRepository;
    }

    @Override
    public  int create(String name, String email,String phone,String message,String ip ){
try{
//    添加判断，从已存得数据中和新数据对比，如果同一天中有同一个name，email，ip，phone那么就不能储存
LeaveMessage inComingLeaveMessage = new LeaveMessage(name,email,phone,message,ip);
   List<LeaveMessage> allLeaveMessage = leaveMessageRepository.findAll();
   for(LeaveMessage tmpMessage:allLeaveMessage){
       if(tmpMessage.getDate().equals(inComingLeaveMessage.getDate())){
           if(tmpMessage.getIp().equals(inComingLeaveMessage.getIp())||
           tmpMessage.getName().equals(inComingLeaveMessage.getName())||
           tmpMessage.getPhone().equals(inComingLeaveMessage.getPhone())||
           tmpMessage.getEmail().equals(inComingLeaveMessage.getEmail())){
               System.out.println("the same user leave Message twice in a day");
               return 0;
           }
       }
   }
    leaveMessageRepository.save(inComingLeaveMessage );
    return  1;
}catch (Exception e){
    System.out.println("leave message unsuccessful "+e);
    return 0;
}
    }

    @Override
    public List<LeaveMessage> getAllLeaveMessage(){
try{
    return  leaveMessageRepository.findAll();

}catch (Exception e){
    System.out.println("can not get all LeaveMessage ");
    return new ArrayList<>();
}
    }

    @Override
    public  int deleteById(Long Id){
        try{
            leaveMessageRepository.deleteById(Id);
            return 1;

        }catch (Exception e){
            System.out.println("delete leaveMessage failed");
            return 0;
        }
    }


}
