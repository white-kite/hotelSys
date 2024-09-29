package egovframework.let.main.data;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL) //Jackson의 직렬화 과정에서 필드가 null일 경우 해당 필드를 생략하도록 하는 어노테이션
public class Room {
	private String id;
	private String name;
	private String etc;
}
