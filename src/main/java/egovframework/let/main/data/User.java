package egovframework.let.main.data;


import lombok.Data;

@Data
public class User {
	
	private String id;
	private String user_id;
	private String pass;
	private String name;
	private String role = "user";
    private String status = "active";
    private String etc;
}
