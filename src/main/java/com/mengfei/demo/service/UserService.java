package com.mengfei.demo.service;

import com.mengfei.demo.pojo.demo5.User;
import com.mengfei.demo.pojo.demo6.UserInfo;
import com.mengfei.demo.repository.demo6.UserInfoRepository;
import com.mengfei.demo.repository.demo5.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    //单数据源的事务回滚
    @Transactional(value = "demo5TransactionManager", rollbackFor = Exception.class)
    public String saveUser(User user) throws Exception{
        User user1 = userRepository.save(user);
        if(null == user1){
            user1 = new User();
        }

        int i = 10/0;

        return user1.toString();
    }

    //多数据源的事务回滚，这里如果使用demo5TransactionManager，那么它将只会回滚数据库demo5中的事务
    //@Transactional(value = "demo5TransactionManager", rollbackFor = Exception.class)
    @Transactional(value = "jtaTransactionManager", rollbackFor = Exception.class)
    public String saveUser(User user, UserInfo userInfo) throws Exception{
        User user1 = userRepository.save(user);
        if(null == user1){
            user1 = new User();
        }

        UserInfo userInfo1 = userInfoRepository.save(userInfo);
        if(null == userInfo1){
            userInfo1 = new UserInfo();
        }

        int i = 10/0;

        return user1 + "==" + userInfo1;
    }
}
