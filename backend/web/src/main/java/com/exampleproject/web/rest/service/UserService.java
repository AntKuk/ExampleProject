package com.exampleproject.web.rest.service;

import com.exampleproject.model.shared.UserDto;
import com.exampleproject.web.rest.dao.UserDao;
import com.exampleproject.web.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("userDAO") UserDao userDao) {
        this.userDao = userDao;
    }


    public boolean isUser(UserDto user) {
        return userDao.isUser(user);
    }

    public boolean add(UserDto userDto) {
        if(!isUser(userDto)) {
            User user = new User(userDto.getLogin(), userDto.getPwd());
            userDao.add(user);
            return true;
        }
        return false;

    }

    public List<UserDto> getAll() {
        List<User> listDB = userDao.getAll();
        return fromDao(listDB);
    }

    public void delete(Integer id) {
        userDao.deleteById(id);
    }
    private List<UserDto> fromDao(List<User> userDaos) {
        List<UserDto> userDtos = new ArrayList<>();
        for(User userDao : userDaos) {
            UserDto userDto = new UserDto();
            userDto.setId(userDao.getId());
            userDto.setLogin(userDao.getLogin());
            userDtos.add(userDto);
        }

        return userDtos;
    }



}
