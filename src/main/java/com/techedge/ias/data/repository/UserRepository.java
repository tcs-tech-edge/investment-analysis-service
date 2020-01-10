package com.techedge.ias.data.repository;

import com.techedge.ias.data.entity.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {

}
