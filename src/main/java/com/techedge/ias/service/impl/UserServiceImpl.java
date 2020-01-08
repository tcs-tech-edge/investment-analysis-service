package com.techedge.ias.service.impl;

import com.techedge.ias.data.entity.User;
import com.techedge.ias.data.repository.UserRepository;
import com.techedge.ias.mapper.UserMapper;
import com.techedge.ias.model.UserDetail;
import com.techedge.ias.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetail addUser(UserDetail userDetail) {
        User user = userMapper.map(userDetail);
        userRepository.save(user);
        return userDetail;
    }

    @Override
    public List<UserDetail> getAllUsers() {
        List<UserDetail> userDetails = new ArrayList<>();
        userRepository.findAll().forEach(user -> userDetails.add(userMapper.map(user)));
        return userDetails;
    }
}
