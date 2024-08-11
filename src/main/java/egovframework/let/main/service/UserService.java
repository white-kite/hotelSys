package egovframework.let.main.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import javax.annotation.Resource;

//import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.main.data.User;
import egovframework.let.main.data.UserMapper;

@Service("userService")
public class UserService {
	
	@Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
    	List<User> users = userMapper.selectAllUsers();
        System.out.println("Loaded Users: " + users); // 로그 추가
        return users;
    }
    
    public User userLogin(String name, String rawPassword) {
    	// 입력된 비밀번호를 해싱
        String encryptedPassword = hashPassword(rawPassword);

        // 해싱된 비밀번호를 사용하여 사용자 조회
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("pass", encryptedPassword);
    	
        User user = userMapper.selectUserByLogin(params);
        return user;
    }
    
    public User createUser(String userId, String rawPassword, String name, String etc) {
        // 비밀번호 해싱 (예: SHA-256)
        String encryptedPassword = hashPassword(rawPassword);
        
        // UUID 생성
        String uuid = UUID.randomUUID().toString();
        
        User user = new User();
        user.setId(uuid);  // UUID를 id로 설정
        user.setUser_id(userId);
        user.setPass(encryptedPassword);
        user.setName(name);
        user.setEtc(etc);

        // 사용자 저장
        int rowsInserted = userMapper.userCreate(user);

        // 삽입이 성공했으면 user 객체 반환
        if (rowsInserted > 0) {
            return user;
        } else {
            throw new RuntimeException("User could not be created");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

}
