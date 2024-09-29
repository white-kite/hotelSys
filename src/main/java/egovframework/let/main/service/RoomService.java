package egovframework.let.main.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.let.main.data.Room;
import egovframework.let.main.data.RoomMapper;

@Service("roomService")
public class RoomService {
	
	@Autowired
	private RoomMapper roomMapper;
	
	public List<Room> selectRoom(Room room) {
		return roomMapper.selectRoom(room);
	}
	
	public int createRoom(Room room) {
		// UUID 생성
        String uuid = UUID.randomUUID().toString();
        
        room.setId(uuid);
        return roomMapper.createRoom(room);
	}
	
	public int deleteRoom(Room room) {
		return roomMapper.deleteRoom(room);
	}
	
	public int updateRoom(Room room) {
		return roomMapper.updateRoom(room);
	}
	
}
