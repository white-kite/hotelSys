package egovframework.let.main.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String login() {
		
		String result;
		
		result="로그인 실패";
		
		result="로그인 성공";
		
		return result;
	}
	
	@GetMapping("/userlist")
    public List<User> userList() {
		List<User> users = userService.getAllUsers();
		for (User user : users) {
            System.out.println("Returning User: id=" + user.getId() + ", name=" + user.getName());
        }
        return users;
    }

}
