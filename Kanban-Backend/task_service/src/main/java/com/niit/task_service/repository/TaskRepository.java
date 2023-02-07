package com.niit.task_service.repository;


import com.niit.user_service.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<User,String> {
}
