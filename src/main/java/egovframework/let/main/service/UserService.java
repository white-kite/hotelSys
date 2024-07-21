package egovframework.let.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.main.data.User;
import egovframework.let.main.data.UserMapper;

@Service
public class UserService {
	@Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }

}
