package egovframework.let.main.data;

import java.util.List;

public interface RoomMapper {

	List<Room> selectRoom(Room room);
	
	int createRoom(Room room);
	
	int deleteRoom(Room room);
	
	int updateRoom(Room room);
	
}
