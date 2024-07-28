package egovframework.let.main.data;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	private String id;
	private String user_id;
	private String pass;
	private String name;
	private String role = "user";
    private String status = "active";
    private String etc;
}
