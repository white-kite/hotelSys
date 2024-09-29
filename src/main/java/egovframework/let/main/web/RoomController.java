package egovframework.let.main.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.let.main.data.Response;
import egovframework.let.main.data.Room;
import egovframework.let.main.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping("/select")
	public Response<List<Room>> selectRoom(
	        @RequestParam(value = "id", required = false) String id,
	        @RequestParam(value = "name", required = false) String name,
	        @RequestParam(value = "etc", required = false) String etc) {

	    // 값이 없으면 null로 설정
	    Room room = new Room();
	    room.setId(id);
	    room.setName(name);
	    room.setEtc(etc);

	    List<Room> selectedRooms = roomService.selectRoom(room);

	    if (selectedRooms != null && !selectedRooms.isEmpty()) {
	        return new Response<>(true, "Room selected", selectedRooms);
	    } else {
	        return new Response<>(false, "No rooms found", null);
	    }
	}
	
	@PostMapping("/create")
    public Response<Room> createRoom(@RequestBody Room room) {
        int rowsInserted = roomService.createRoom(room);

        if (rowsInserted > 0) {
            return new Response<>(true, "Room created", room);
        } else {
            return new Response<>(false, "Room creation failed", null);
        }
    }

    // 방 정보 업데이트 (PUT)
    @PutMapping("/update")
    public Response<Room> updateRoom(@RequestBody Room room) {
        int rowsUpdated = roomService.updateRoom(room);

        if (rowsUpdated > 0) {
            return new Response<>(true, "Room updated", room);
        } else {
            return new Response<>(false, "Room update failed", null);
        }
    }

    // 방 삭제 (DELETE)
    @DeleteMapping("/delete")
    public Response<Void> deleteRoom(@RequestParam("id") String id) {
        Room room = new Room();
        room.setId(id);

        int rowsDeleted = roomService.deleteRoom(room);

        if (rowsDeleted > 0) {
            return new Response<>(true, "Room deleted", null);
        } else {
            return new Response<>(false, "Room deletion failed", null);
        }
    }


}
