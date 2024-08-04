package egovframework.let.main.data;

import java.util.List;

public interface UserMapper {
	public List<User> selectAllUsers();
	
	public User selectUserById(String id);

}
