package egovframework.let.main.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.let.main.data.Response;
import egovframework.let.main.data.User;
import egovframework.let.main.service.UserService;

@RestController
@RequestMapping("/api")
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/help")
	public String sayHello() {
		String result="Hello eGovFramework!!";  
		return result;
	}
	
	@GetMapping("/userlist")
    public Response<List<User>> userList() {
		List<User> users = userService.getAllUsers();
		for (User user : users) {
            System.out.println("Returning User: id=" + user.getId() + ", name=" + user.getName());
        }
		return new Response<>(true, "User list retrieved successfully", users);
    }
	
	@PostMapping("/login")
	public Response<User> login(@RequestParam("userId") String userId, @RequestParam("pass") String rawPassword) {

	    // 받은 id와 pass를 콘솔에 출력
	    System.out.println("Received user_id: " + userId);
	    System.out.println("Received Password: " + rawPassword);
	    
	    userId = userId.trim();
	    rawPassword = rawPassword.trim();
	    
	    if (userId.isEmpty() || rawPassword.isEmpty()) {
	        return new Response<>(false, "userId and password must not be null", null);
	    }
	    
	    // User 객체 생성
	    User loginUser = new User();
	    loginUser.setUserId(userId);
	    loginUser.setPass(rawPassword);
	    
	    User user = userService.userLogin(loginUser);
	    
	    if (user == null) {
	        return new Response<>(false, "Login failed. Invalid name or password.", null);
	    }

	    return new Response<>(true, "Login success", user);
	    
	}
	
	
	
	@PostMapping("/usercreate")
    public Response<User> userCreate(@RequestParam("user_id") String userId,
                           @RequestParam("pass") String rawPassword,
                           @RequestParam("name") String name,
                           @RequestParam("etc") String etc) {
        
		User user = new User();
		user.setUserId(userId);
		user.setPass(rawPassword);
		user.setName(name);
		user.setEtc(etc);
		
        user = userService.createUser(user);
        return new Response<>(true, "User created successfully", user);
    }
	

}
