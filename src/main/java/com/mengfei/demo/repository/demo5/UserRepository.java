package com.mengfei.demo.repository.demo5;

import com.mengfei.demo.pojo.demo5.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
