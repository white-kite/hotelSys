package egovframework.let.main.data;

import java.util.List;
import java.util.Map;


public interface UserMapper {
	
	
	public List<User> selectAllUsers();
	
	User selectUserByLogin(Map<String, Object> params);
	
	int userCreate(User user);

}
