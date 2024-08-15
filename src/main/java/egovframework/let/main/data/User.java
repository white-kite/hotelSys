package egovframework.let.main.data;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	
	private String id;
	private String userId;
	private String pass;
	private String name;
	private String role = "user";
    private String status = "active";
    
    // user_detail 테이블
    private String etc;
    private String phoneNumber;
}
