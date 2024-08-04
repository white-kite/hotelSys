package egovframework.let.main.service;

import java.util.List;

//import javax.annotation.Resource;

//import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.main.data.User;
import egovframework.let.main.data.UserMapper;

@Service("userService")
public class UserService {

	/*
	@Resource(name="sqlSessionTemplate")
    private SqlSessionTemplate sqlSession;

    public List<User> selectAllUsers() {
        return sqlSession.getMapper(UserMapper.class).selectAllUsers();
    }
    */
	
	@Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
    	List<User> users = userMapper.selectAllUsers();
        System.out.println("Loaded Users: " + users); // 로그 추가
        return users;
    }
    
    public boolean validateUser(String name, String pass) {
    	User user = userMapper.selectUserById(name);
    	if (user == null) { // id 없음
    		return false;
    	}
    	
    	return pass.equals(user.getPass()); // 비밀번호 일치 여부
    }

}
