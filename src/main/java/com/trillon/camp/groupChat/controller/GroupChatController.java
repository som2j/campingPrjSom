package com.trillon.camp.groupChat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trillon.camp.group.dto.CampingGroup;
import com.trillon.camp.group.dto.GroupMember;
import com.trillon.camp.group.service.GroupSerivce;
import com.trillon.camp.groupChat.dto.ChatRoom;
import com.trillon.camp.groupChat.service.GroupChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/groupChat")
@RequiredArgsConstructor
public class GroupChatController {
	
	private final GroupChatService groupChatService;
	private final GroupSerivce groupSerivce;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	
	@GetMapping("/groupChatList")
	public void groupChatList(Model model,String userId) {
	
		System.out.println("groupChatList");
		userId="user1";
		System.out.println(groupChatService.selectAllMygroupChatList(userId));
		List<GroupMember> GroupMembers = groupChatService.selectAllChatRoomList(userId);
		List<CampingGroup> campingGroups = groupChatService.selectAllMygroupChatList(userId);
		Map<String, Object> MyGroupMap = new HashMap<>();
		MyGroupMap.put("GroupMember", GroupMembers);
		MyGroupMap.put("campingGroup", campingGroups);
		
		model.addAttribute("MyGroup", MyGroupMap);	
	}
	
	
	
	@GetMapping("/groupChat")
	public void groupChat() {
		System.out.println("groupChat");
	}
	
	@PostMapping("/createGroup")
	@ResponseBody
	public String createChat(@RequestBody CampingGroup campingGroup) {
		System.out.println("Post : createGroup");
		ChatRoom chatRoom = new ChatRoom();
		Map<String, Object> commandMap = new HashMap<>();
		
		groupChatService.insertNewGroup(campingGroup);
		chatRoom.setRoomId();
		commandMap.put("roomId", chatRoom);
		commandMap.put("groupIdx", groupSerivce.selectNewGampingGroupIdx());
		
		groupChatService.insertNewGroupChat(commandMap);
		
		
//		ChatRoom newChatRoom = chatRoom;
//		newChatRoom.setRoomId();
//		System.out.println(newChatRoom);
//		groupChatService.createNewChatRoom(newChatRoom);
		return "성공";
	}
	

	
	@GetMapping("/chatRoom")
    public void getRoom(@RequestParam("roomId") String roomId,@RequestParam("groupIdx") String groupIdx, Model model){
		
        log.info("# get Chat Room, roomID : " + roomId);
        List<ChatRoom> chatRooms = groupChatService.findRoomById(roomId);
        for (ChatRoom Room : chatRooms) {
			System.out.println(Room);
		}
        ChatRoom chatRoom  = new ChatRoom();
        chatRoom.setRoomId(chatRooms.get(0).getRoomId());
        
        model.addAttribute("room", chatRoom);
        model.addAttribute("groupIdx", groupIdx);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
