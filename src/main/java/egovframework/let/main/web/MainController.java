package egovframework.let.main.web;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.let.main.data.LoginResponse;
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
	
	@PostMapping("/login")
	public LoginResponse login(@RequestParam("name") String name, @RequestParam("pass") String rawPassword) {

	    // 받은 id와 pass를 콘솔에 출력
	    System.out.println("Received Name: " + name);
	    System.out.println("Received Password: " + rawPassword);
	    
	    name = name.trim();
	    rawPassword = rawPassword.trim();
	    
	    if (name.isEmpty() || rawPassword.isEmpty()) {
	        return new LoginResponse(false, "Name and password must not be null", null);
	    }
	    
	    User user = userService.userLogin(name, rawPassword);
	    
	    if (user == null) {
	        return new LoginResponse(false, "Login failed. Invalid name or password.", null);
	    }

	    return new LoginResponse(true, "Login successful", user);
	}
	
	@GetMapping("/userlist")
    public List<User> userList() {
		List<User> users = userService.getAllUsers();
		for (User user : users) {
            System.out.println("Returning User: id=" + user.getId() + ", name=" + user.getName());
        }
        return users;
    }
	
	@PostMapping("/usercreate")
    public User userCreate(@RequestParam("user_id") String userId,
                           @RequestParam("pass") String rawPassword,
                           @RequestParam("name") String name,
                           @RequestParam("etc") String etc) {
        
        User user = userService.createUser(userId, rawPassword, name, etc);
        return user;
    }

}
