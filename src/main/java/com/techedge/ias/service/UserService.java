package com.techedge.ias.service;

import com.techedge.ias.model.UserDetail;

import java.util.List;

public interface UserService {

    UserDetail addUser(UserDetail userDetail);

    List<UserDetail> getAllUsers();
}
