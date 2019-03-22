package com.mengfei.demo;

import com.mengfei.demo.pojo.demo5.User;
import com.mengfei.demo.pojo.demo6.UserInfo;
import com.mengfei.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJtatransactionApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void singleTransactionTest(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPwd("z001");

        try{
            String str = userService.saveUser(user);
            System.out.println(str);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    public void manyTransactionTest(){
        User user = new User();
        user.setUsername("zhangsan");
        user.setPwd("z001");

        UserInfo userInfo = new UserInfo();
        userInfo.setDescInfo("这一个关于账号zhangsan的详细描述！");

        try{
            String str = userService.saveUser(user,userInfo);
            System.out.println(str);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
