package egovframework.let.main.data;

import java.util.List;
import java.util.Map;


public interface UserMapper {
	
	
	public List<User> selectAllUsers();
	
	User selectUser(User user);
	
	int userCreate(User user);

}
