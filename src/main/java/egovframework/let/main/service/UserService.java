package egovframework.let.main.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.main.data.Response;
import egovframework.let.main.data.User;
import egovframework.let.main.data.UserMapper;
import egovframework.let.utl.PasswordUtil;

@Service("userService")
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
    	List<User> users = userMapper.selectAllUsers();
    	logger.info("Loaded Users: " + users); // 로그 추가
        return users;
    }
    
    public User selectUser(User user) {
    	
    	if(user.getPass() != null) {
	    	// 입력된 비밀번호를 해싱
	        String encryptedPassword = PasswordUtil.hashPassword(user.getPass());
	
	        // 해싱된 비밀번호를 사용하여 사용자 조회
	        user.setPass(encryptedPassword);
    	}
        user = userMapper.selectUser(user);
        return user;
    }
    
    public Response<User> createUser(User user) {
        // 비밀번호 해싱 (예: SHA-256)
        String encryptedPassword = PasswordUtil.hashPassword(user.getPass());
        
        // UUID 생성
        String uuid = UUID.randomUUID().toString();
        
        user.setId(uuid);  // UUID를 id로 설정
        user.setPass(encryptedPassword);
        
        
        // 중복된 userId가 있는지 확인 (User 객체의 나머지 필드는 null로 설정)
        User checkUser = new User();
        checkUser.setUserId(user.getUserId());

        User existingUser = userMapper.selectUser(checkUser);
        if (existingUser != null) {
            //throw new RuntimeException("Duplicate user ID");
        	return new Response<>(false, "userId ust not be duplicated", null);
        }

        // 사용자 저장
        int rowsInserted = userMapper.userCreate(user);
        int rowsInserted2 = userMapper.userDetailCreate(user);

        // 저장 성공 시 user 객체 반환
        if (rowsInserted > 0 && rowsInserted2 > 0) {
        	return new Response<>(true, "User created successfully", user);
        } else {
            return new Response<>(false, "User could not be created", null);
        }
    }

}
