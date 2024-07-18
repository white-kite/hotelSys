package egovframework.let.main.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
	@GetMapping("/help")
	public String sayHello() {
		String result="Hello eGovFramework!! name : ";  
		return result;
	}

}
