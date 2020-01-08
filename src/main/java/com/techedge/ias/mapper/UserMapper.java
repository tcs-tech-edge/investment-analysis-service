package com.techedge.ias.mapper;

import com.techedge.ias.data.entity.User;
import com.techedge.ias.model.UserDetail;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserDetail userDetail) {
        User user = new User();
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        return user;
    }

    public UserDetail map(User user) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(user.getFirstName());
        userDetail.setLastName(user.getLastName());
        return userDetail;
    }
}
