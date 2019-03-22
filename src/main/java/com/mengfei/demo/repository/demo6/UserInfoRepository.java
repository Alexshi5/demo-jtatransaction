package com.mengfei.demo.repository.demo6;

import com.mengfei.demo.pojo.demo6.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
}
