package com.niit.user_authentication_service.repositroy;

import com.niit.user_authentication_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,String> {
   User findByEmailIdAndPassword(String emailId,String password);
}
