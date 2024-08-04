package egovframework.let.main.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String login(@RequestParam("name") String name, @RequestParam("pass") String pass) {
		
		// 받은 id와 pass를 콘솔에 출력
	    System.out.println("Received Name: " + name);
	    System.out.println("Received Password: " + pass);
	    
	    name = name.trim();
	    pass = pass.trim();
	    
	    String result ="login fail";
	    
	    if (name == null || pass == null ) {
	    	result = "enter your name and password!!";
	    } else if(userService.validateUser(name, pass)) { // true 값이 넘어오면 실행
	    	result="login success";
	    }
	    
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
